package code.expressionlanguage.utilcompo;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.structs.Struct;
import code.threads.*;
import code.util.IntWrapCallable;

public interface AbstractInterceptor {
    AbstractInterceptorStdCaller newInterceptorStdCaller(String _cl);

    AbstractConcurrentMap<String, Struct> newMapStringStruct();
    AbstractConcurrentMap<AbstractThread,Struct> newMapAbstractThreadStruct();
    AbstractConcurrentMap<Struct,Struct> newMapStructStruct();
    IntWrapCallable<Struct> wrap(StructCallable _call);
    AbstractBaseExecutorServiceParam<Struct> newExecutorService();
    AbstractBaseExecutorServiceParam<Struct> newExecutorService(int _nbThreads);
}
