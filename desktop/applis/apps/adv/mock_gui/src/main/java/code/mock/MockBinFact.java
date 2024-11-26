package code.mock;

import code.gui.GuiConstants;
import code.stream.BytesInfo;
import code.stream.core.AbstractBinFact;
import code.threads.FileStruct;
import code.util.StringList;
import code.util.core.StringUtil;
import code.util.ints.*;

public final class MockBinFact implements AbstractBinFact {
    private final MockAbsRand mockRand;
    private final MockFileSet fileSet;

    public MockBinFact(MockAbsRand _gen, MockFileSet _mfs) {
        mockRand = _gen;
        fileSet = _mfs;
    }
    public static String contentsOfFile(AbstractBinFact _bin,String _nomFichier, UniformingString _apply) {
        BytesInfo bs_ = _bin.loadFile(_nomFichier);
        String dec_ = dec(bs_);
        if (dec_ == null) {
            return null;
        }
        return _apply.apply(dec_);
    }
    public static boolean write(AbstractBinFact _bin,String _nomFichier, String _text, boolean _append) {
        return _bin.writeFile(_nomFichier,StringUtil.encode(_text),_append);
    }
    private static String dec(BytesInfo _bs) {
        if (_bs.isNul()) {
            return null;
        }
        return StringUtil.decode(_bs.getBytes());
    }
    @Override
    public BytesInfo loadFile(String _s) {
        return load(_s, fileSet);
    }

    public static BytesInfo load(String _s, MockFileSet _files) {
        String abs_ = MockFile.absolute(_files, _s);
        FileStruct val_ = _files.getFiles().getVal(abs_);
        if (val_ == null) {
            return new BytesInfo(new byte[0],true);
        }
        return new BytesInfo(GuiConstants.nullToEmpty(val_.getContent()),val_.getContent() == null);
    }


    @Override
    public boolean writeFile(String _nomFichier, byte[] _text, boolean _append) {
        String abs_ = MockFile.absolute(fileSet, _nomFichier);
        String link_ = fileSet.linkedRoot(abs_);
        if (!fileSet.getValidating().okPath(abs_.substring(link_.length()),'/')) {
            return false;
        }
        StringList parts_ = StringUtil.splitChar(abs_, '/');
        String parent_ = StringUtil.join(parts_.mid(0,parts_.size()-1),'/');
        FileStruct fol_ = fileSet.getFiles().getVal(parent_);
        if (!StringUtil.contains(fileSet.getRoots(),parent_+"/")&&notDir(fol_)) {
            return false;
        }
        FileStruct val_ = fileSet.getFiles().getVal(abs_);
        if (!notDir(val_)) {
            return false;
        }
        if (mockRand.edit()) {
            MockBinFactory.writeOrAppend(abs_,_text,_append,fileSet);
            return true;
        }
        return false;
    }

    private static boolean notDir(FileStruct _folder) {
        return _folder == null || _folder.getContent() != null;
    }

}
