package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.*;
import code.util.CustList;
import code.util.core.StringUtil;

public final class FctFileZipText extends FctFileAbs {
    private final String aliasEntryBinary;

    public FctFileZipText(CustAliases _custAliases, String _aliasEntryBinary) {
        super(_custAliases);
        this.aliasEntryBinary = _aliasEntryBinary;
    }

    @Override
    public ArgumentWrapper file(FileInfos _infos, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<EntryBinaryStruct> bins_ = new CustList<EntryBinaryStruct>();
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct name_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct parts_ = argumentWrappers_.get(1).getValue().getStruct();
        if (parts_ instanceof ArrayStruct) {
            ArrayStruct arr_ = (ArrayStruct) parts_;
            for (Struct s: arr_.list()) {
                if (s instanceof EntryTextStruct) {
                    EntryTextStruct cont_ = (EntryTextStruct)s;
                    byte[] encoded_ = StringUtil.encode(cont_.getText().getInstance());
                    String contType_ = _cont.getStandards().getContent().getPrimTypes().getAliasPrimByte();
                    contType_ = StringExpUtil.getPrettyArrayType(contType_);
                    int bLen_ = encoded_.length;
                    ArrayStruct bs_ = new ArrayStruct(bLen_,contType_);
                    for (int j = 0; j < bLen_; j++) {
                        bs_.set(j, new ByteStruct(encoded_[j]));
                    }
                    EntryBinaryStruct bin_ = new EntryBinaryStruct(cont_.getName(), bs_);
                    bin_.setLongTime(cont_.getLongTime());
                    bins_.add(bin_);
                }
            }
        }
        int bLen_ = bins_.size();
        String arrType_ = StringExpUtil.getPrettyArrayType(aliasEntryBinary);
        ArrayStruct bs_ = new ArrayStruct(bLen_,arrType_);
        for (int j = 0; j < bLen_; j++) {
            bs_.set(j, bins_.get(j));
        }
        byte[] finalFile_ = ZipBinStructUtil.getZipBinFileAsArray(bs_,_infos.getZipFact());
        if (finalFile_ != null) {
            StringStruct str_ = (StringStruct)name_;
            return new ArgumentWrapper(BooleanStruct.of(_infos.getFileSystem().writeFile(str_.getInstance(),finalFile_, (RunnableContextEl) _cont)));
        }
        return new ArgumentWrapper(BooleanStruct.of(false));
    }
}
