package code.vi.prot.impl;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.expressionlanguage.utilcompo.StructCallable;
import code.threads.AbstractBaseExecutorServiceParam;
import code.threads.AbstractConcurrentMap;
import code.threads.AbstractThread;
import code.util.IntWrapCallable;

public final class DefInterceptor implements AbstractInterceptor {
    private final AbsErrGenerator gene;
    public DefInterceptor(AbsErrGenerator _gene) {
        this.gene = _gene;
    }
    @Override
    public AbstractInterceptorStdCaller newInterceptorStdCaller(String _cl) {
        return new DefInterceptorStdCaller(_cl,gene);
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

    @Override
    public IntWrapCallable<Struct> wrap(StructCallable _call) {
        return new DefCallableStruct(_call);
    }

    @Override
    public AbstractBaseExecutorServiceParam<Struct> newExecutorService(int _nbThreads) {
        return new DefaultExecutorServiceParam<Struct>(_nbThreads);
    }

    @Override
    public AbstractBaseExecutorServiceParam<Struct> newExecutorService() {
        return new DefaultExecutorServiceParam<Struct>();
    }
}
