package code.vi.sys.impl;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.Struct;

public final class DefInterceptorStdCaller implements AbstractInterceptorStdCaller {
    private final String cl;
    public DefInterceptorStdCaller(String _cl) {
        cl = _cl;
    }
    @Override
    public ArgumentWrapper invoke(StdCaller _caller, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        try {
            return _caller.call(_exit, _cont, _instance, _firstArgs, _stackCall);
        } catch (Exception e) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, cl, _stackCall)));
            return new ArgumentWrapper(null);
        }
    }
}
