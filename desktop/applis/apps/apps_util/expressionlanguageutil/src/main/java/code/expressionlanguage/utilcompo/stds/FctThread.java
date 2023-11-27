package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.dbg.AbsLogDbg;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AbsThreadStruct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.ThreadDbgStruct;
import code.expressionlanguage.utilcompo.ThreadStruct;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractThread;

public final class FctThread implements StdCaller {
    private final CustAliases custAliases;
    private final String intro;

    public FctThread(CustAliases _custAliases, String _i) {
        this.custAliases = _custAliases;
        intro = _i;
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
        return new ArgumentWrapper(build(_stackCall.getStopper().getLogger(),thread_,custAliases.getInfos().getThreadFactory().newAtomicBoolean(),runnable_, intro+":"+runnable_.getClassName(_cont)));
    }
    public static AbsThreadStruct build(AbsLogDbg _log, AbstractThread _thread, AbstractAtomicBoolean _ended, Struct _r, String _intro) {
        if (_log != null) {
            _log.log(_intro);
            return new ThreadDbgStruct(_thread, _ended, _r);
        }
        return new ThreadStruct(_thread, _ended, _r);
    }
}
