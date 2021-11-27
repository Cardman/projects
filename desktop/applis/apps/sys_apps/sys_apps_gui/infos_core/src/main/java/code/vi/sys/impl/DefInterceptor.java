package code.vi.sys.impl;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.threads.AbstractConcurrentMap;
import code.threads.AbstractThread;
import code.vi.prot.impl.DefConcurrentMap;

public final class DefInterceptor implements AbstractInterceptor {
    @Override
    public AbstractInterceptorStdCaller newInterceptorStdCaller(String _cl) {
        return new DefInterceptorStdCaller(_cl);
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
