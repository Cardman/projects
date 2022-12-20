package code.mock;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.DefaultInitializer;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.Struct;

public final class MockInterceptorStdCaller implements AbstractInterceptorStdCaller {
    @Override
    public boolean stop(DefaultInitializer _defaultInitializer, ContextEl _contextEl, StackCall _stackCall) {
        return _defaultInitializer.stop(_contextEl,_stackCall);
    }

    @Override
    public boolean exitAfterCallInt(DefaultInitializer _defaultInitializer, ContextEl _contextEl, StackCall _stackCall) {
        return _defaultInitializer.exitAfterCallInt(_contextEl,_stackCall);
    }

    @Override
    public ArgumentWrapper invoke(StdCaller _stdCaller, AbstractExiting _abstractExiting, ContextEl _contextEl, Struct _struct, ArgumentListCall _argumentListCall, StackCall _stackCall) {
        return _stdCaller.call(_abstractExiting,_contextEl,_struct,_argumentListCall,_stackCall);
    }
}
