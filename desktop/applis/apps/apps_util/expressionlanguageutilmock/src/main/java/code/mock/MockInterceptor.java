package code.mock;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.expressionlanguage.utilcompo.StructCallable;
import code.threads.AbstractBaseExecutorServiceParam;
import code.threads.AbstractConcurrentMap;
import code.threads.AbstractThread;
import code.util.IntWrapCallable;

public final class MockInterceptor implements AbstractInterceptor {

    private final MockInterceptorStdCaller mockInterceptorStdCaller = new MockInterceptorStdCaller();

    @Override
    public AbstractInterceptorStdCaller newInterceptorStdCaller(String _s) {
        return mockInterceptorStdCaller;
    }

    @Override
    public AbstractConcurrentMap<String, Struct> newMapStringStruct() {
        return new MockConcurrentMapStringStruct();
    }

    @Override
    public AbstractConcurrentMap<AbstractThread, Struct> newMapAbstractThreadStruct() {
        return new MockConcurrentMapThreadStruct();
    }

    @Override
    public AbstractConcurrentMap<Struct, Struct> newMapStructStruct() {
        return new MockConcurrentMapStructStruct();
    }

    @Override
    public IntWrapCallable<Struct> wrap(StructCallable _call) {
        return new MockWrapCallable(_call);
    }

    @Override
    public AbstractBaseExecutorServiceParam<Struct> newExecutorService(int _nbThreads) {
        return new MockBaseExecutorServiceParam<Struct>();
    }

    @Override
    public AbstractBaseExecutorServiceParam<Struct> newExecutorService() {
        return new MockBaseExecutorServiceParam<Struct>();
    }
}
