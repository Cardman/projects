package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.dbg.AbsLogDbg;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.threads.ThreadUtil;

public final class FctThreadSleep implements StdCaller {
    private final CustAliases custAliases;
    private final String id;

    public FctThreadSleep(CustAliases _custAliases, String _i) {
        this.custAliases = _custAliases;
        id = _i;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
            custAliases.processFailInit(_cont, _stackCall);
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        Struct arg_ = _firstArgs.getArgumentWrappers().get(0).getValue();
        if (!(arg_ instanceof NumberStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasNullPe(), _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        log(_stackCall,Long.toString(((NumberStruct)arg_).longStruct()), id);
        AbsLogDbg lg_ = _stackCall.getStopper().getLogger();
        if (lg_ != null) {
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return new ArgumentWrapper(BooleanStruct.of(ThreadUtil.sleep(((RunnableContextEl) _cont).getCurrentThreadFactory(),((NumberStruct)arg_).longStruct())));
    }

    public static void log(StackCall _stackCall, String _res, String _id) {
        AbsLogDbg lg_ = _stackCall.getStopper().getLogger();
        if (lg_ != null) {
            lg_.log(_id +"=>"+ _res);
        }
    }
}
