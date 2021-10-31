package code.vi.sys.impl;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.stds.ApplyCoreMethodUtil;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.threads.AbstractConcurrentMap;
import code.threads.AbstractThread;

public final class DefInterceptor implements AbstractInterceptor {
    @Override
    public AbstractInterceptorStdCaller newInterceptorStdCaller(String _cl) {
        return new DefInterceptorStdCaller(_cl);
    }

    @Override
    public ResultErrorStd invoke(String _cl,ContextEl _cont, ClassMethodId _method, Struct _struct, AbstractExiting _exit, StackCall _stackCall, Argument... _args) {
        try {
            return ApplyCoreMethodUtil.invokeBase(_cont, _method, _struct, _exit, _args, _stackCall);
        } catch (RuntimeException e) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, _cl, _stackCall)));
            return new ResultErrorStd();
        }
    }
    @Override
    public AbstractConcurrentMap<String, Struct> newMapStringStruct() {
        return new DefConcurrentMap<String, Struct>();
    }

    @Override
    public AbstractConcurrentMap<AbstractThread, Struct> newMapAbstractThreadStruct() {
        return new DefConcurrentMap<AbstractThread, Struct>();
    }

    @Override
    public AbstractConcurrentMap<Struct, Struct> newMapStructStruct() {
        return new DefConcurrentMap<Struct, Struct>();
    }

}
