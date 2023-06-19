package code.expressionlanguage.sample;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.Initializer;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.Struct;

public final class ElInterceptorStdCaller implements AbstractInterceptorStdCaller {
    @Override
    public boolean stop(Initializer _init, ContextEl _owner, StackCall _stackCall) {
        return _init.stop(_owner, _stackCall);
    }
    @Override
    public boolean stopNormal(Initializer _init, ContextEl _owner, StackCall _stackCall) {
        return _init.stopNormal(_owner, _stackCall);
    }

    @Override
    public boolean exitAfterCallInt(Initializer _init, ContextEl _owner, StackCall _stack) {
        return _init.exitAfterCallInt(_owner, _stack);
    }

    @Override
    public ArgumentWrapper invoke(StdCaller _caller, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return _caller.call(_exit, _cont, _instance, _firstArgs, _stackCall);
    }
}
