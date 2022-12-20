package code.mock;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.threads.AbstractConcurrentMap;
import code.threads.AbstractThread;

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
}
