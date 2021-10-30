package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.ThreadStruct;
import code.threads.AbstractThread;

public final class FctThread implements StdCaller {
    private final CustAliases custAliases;

    public FctThread(CustAliases _custAliases) {
        this.custAliases = _custAliases;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
            custAliases.processFailInit(_cont, _stackCall);
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        Struct runnable_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        AbstractThread thread_;
        if (runnable_ instanceof Runnable) {
            thread_ = custAliases.getInfos().getThreadFactory().newThread((Runnable) runnable_);
        } else {
            thread_ = custAliases.getInfos().getThreadFactory().newThread(null);
        }
        ThreadStruct std_ = new ThreadStruct(thread_,custAliases.getInfos().getThreadFactory().newAtomicBoolean());
        return new ArgumentWrapper(std_);
    }
}
