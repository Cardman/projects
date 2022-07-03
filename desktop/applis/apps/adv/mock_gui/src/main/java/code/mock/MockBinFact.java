package code.mock;

import code.maths.montecarlo.AbstractGenerator;
import code.stream.core.AbstractBinFact;
import code.threads.FileStruct;
import code.util.StringList;
import code.util.core.StringUtil;

public final class MockBinFact implements AbstractBinFact {
    private final MockRand mockRand;
    private final MockFileSet fileSet;

    public MockBinFact(AbstractGenerator _gen, MockFileSet _mfs) {
        mockRand = new MockRand(_gen);
        fileSet = _mfs;
    }

    @Override
    public byte[] loadFile(String _s) {
        return load(_s, fileSet).getContent();
    }

    public static FileStruct load(String _s, MockFileSet _files) {
        String abs_ = MockFile.absolute(_files, _s);
        FileStruct val_ = _files.getFiles().getVal(abs_);
        if (val_ == null) {
            return new FileStruct(null,0);
        }
        return val_;
    }


    @Override
    public boolean writeFile(String _nomFichier, byte[] _text) {
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
            fileSet.getFiles().put(abs_,new FileStruct(_text,fileSet.getMockMillis().millis()));
            return true;
        }
        return false;
    }

    private boolean notDir(FileStruct _folder) {
        return _folder == null || _folder.getContent() != null;
    }

}
