package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.RunnableContextEl;

public final class FctFileGetLength extends FctFileAbs {
    public FctFileGetLength(CustAliases _custAliases) {
        super(_custAliases);
    }

    @Override
    public ArgumentWrapper file(FileInfos _infos, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        String file_ = ((StringStruct)_firstArgs.getArgumentWrappers().get(0).getValue()).getInstance();
        return new ArgumentWrapper(new LongStruct(_infos.getFileSystem().length(file_, ((RunnableContextEl) _cont).getCurrentDir())));
    }
}
