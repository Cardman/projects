package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.util.CustList;

public final class FctFileWriteBin extends FctFileAbs {
    public FctFileWriteBin(CustAliases _custAliases) {
        super(_custAliases);
    }

    @Override
    public ArgumentWrapper file(FileInfos _infos, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct name_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct parts_ = argumentWrappers_.get(1).getValue().getStruct();
        String file_ = ((StringStruct)name_).getInstance();
        if (!(parts_ instanceof ArrayStruct)) {
            return new ArgumentWrapper(BooleanStruct.of(false));
        }
        ArrayStruct arr_ = (ArrayStruct) parts_;
        int len_ = arr_.getLength();
        byte[] bin_ = new byte[len_];
        for (int i = 0; i < len_; i++) {
            Struct byte_ = arr_.get(i);
            if (!(byte_ instanceof NumberStruct)) {
                continue;
            }
            bin_[i] = ((NumberStruct)byte_).byteStruct();
        }
        return new ArgumentWrapper(BooleanStruct.of(_infos.getFileSystem().writeFile(file_, bin_, (RunnableContextEl) _cont)));
    }
}
