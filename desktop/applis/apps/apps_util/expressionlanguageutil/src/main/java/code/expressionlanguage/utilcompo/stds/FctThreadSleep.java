package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.threads.ThreadUtil;

public final class FctThreadSleep implements StdCaller {
    private final CustAliases custAliases;

    public FctThreadSleep(CustAliases _custAliases) {
        this.custAliases = _custAliases;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
            custAliases.processFailInit(_cont, _stackCall);
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        Struct arg_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        if (!(arg_ instanceof NumberStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasNullPe(), _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return new ArgumentWrapper(BooleanStruct.of(ThreadUtil.sleep(((RunnableContextEl) _cont).getCurrentThreadFactory(),((NumberStruct)arg_).longStruct())));
    }
}
