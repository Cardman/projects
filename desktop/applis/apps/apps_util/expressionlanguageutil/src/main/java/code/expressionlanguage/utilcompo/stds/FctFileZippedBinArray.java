package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.expressionlanguage.utilcompo.ZipBinStructUtil;

public final class FctFileZippedBinArray extends FctFileAbs {
    public FctFileZippedBinArray(CustAliases _custAliases) {
        super(_custAliases);
    }

    @Override
    public ArgumentWrapper file(FileInfos _infos, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(ZipBinStructUtil.zippedBinaryFilesByteArray(_firstArgs.getArgumentWrappers().get(0).getValue().getStruct(), (RunnableContextEl) _cont));
    }

    @Override
    public boolean guard(ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return true;
    }
}
