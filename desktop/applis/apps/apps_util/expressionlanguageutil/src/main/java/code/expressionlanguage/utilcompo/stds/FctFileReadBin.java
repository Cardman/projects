package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.RunnableContextEl;

public final class FctFileReadBin extends FctFileAbs {
    public FctFileReadBin(CustAliases _custAliases) {
        super(_custAliases);
    }

    @Override
    public ArgumentWrapper file(FileInfos _infos, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        StringStruct str_ = (StringStruct)_firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        byte[] read_ = _infos.getFileSystem().loadFile(str_.getInstance(), (RunnableContextEl) _cont);
        if (read_ == null) {
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        int len_ = read_.length;
        ArrayStruct bin_ = new ArrayStruct(len_, StringExpUtil.getPrettyArrayType(_cont.getStandards().getContent().getPrimTypes().getAliasPrimByte()));
        for (int i = 0; i < len_; i++) {
            bin_.set(i, new ByteStruct(read_[i]));
        }
        return new ArgumentWrapper(bin_);
    }
}
