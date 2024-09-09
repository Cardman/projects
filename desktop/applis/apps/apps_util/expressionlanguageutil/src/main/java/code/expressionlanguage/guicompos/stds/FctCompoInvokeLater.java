package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecRunMethodState;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.dbg.AbsLogDbg;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.CustComponentStruct;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.RunnableContextEl;

public final class FctCompoInvokeLater implements StdCaller {
    private final CustAliases custAliases;
    private final GuiExecutingBlocks guiEx;
    private final String id;

    public FctCompoInvokeLater(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _i) {
        this.custAliases = _custAliases;
        this.guiEx = _guiEx;
        id = _i;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
            custAliases.processFailInit(_cont, _stackCall);
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        AbsLogDbg log_ = _stackCall.getStopper().getLogger();
        Struct arg_ = _firstArgs.getArgumentWrappers().get(0).getValue();
        if (log_ != null) {
            procRunnable(_cont, _stackCall, log_, arg_, id);
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        CustComponentStruct.invokeLater((RunnableContextEl) _cont, guiEx.getFrames(), arg_);
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }

    public static void procRunnable(ContextEl _cont, StackCall _stackCall, AbsLogDbg _log, Struct _arg, String _id) {
        if (_arg != NullStruct.NULL_VALUE) {
            _log.log(_id +":"+ _arg.getClassName(_cont));
            _stackCall.setCallingState(new ExecRunMethodState(_arg));
        } else {
            _log.log(_id +":");
        }
    }
}
