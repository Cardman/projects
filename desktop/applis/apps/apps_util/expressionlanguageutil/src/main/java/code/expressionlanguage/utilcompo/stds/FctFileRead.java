package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.RunnableContextEl;

public final class FctFileRead extends FctFileAbs {
    public FctFileRead(CustAliases _custAliases) {
        super(_custAliases);
    }

    @Override
    public ArgumentWrapper file(FileInfos _infos, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        StringStruct str_ = (StringStruct)_firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        String read_ = _infos.getFileSystem().contentsOfFile(str_.getInstance(), (RunnableContextEl) _cont);
        if (read_ == null) {
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return new ArgumentWrapper(new StringStruct(read_));
    }
}
