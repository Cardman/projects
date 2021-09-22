package code.formathtml;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.DefaultInitializer;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.Struct;

public final class TestedRenderInterceptorStdCaller implements AbstractInterceptorStdCaller {
    @Override
    public boolean stop(DefaultInitializer _init, ContextEl _owner, StackCall _stackCall) {
        return _init.stop(_owner, _stackCall);
    }

    @Override
    public boolean exitAfterCallInt(DefaultInitializer _init, ContextEl _owner, StackCall _stack) {
        return _init.exitAfterCallInt(_owner, _stack);
    }

    @Override
    public ArgumentWrapper invoke(StdCaller _caller, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return _caller.call(_exit, _cont, _instance, _firstArgs, _stackCall);
    }
}
