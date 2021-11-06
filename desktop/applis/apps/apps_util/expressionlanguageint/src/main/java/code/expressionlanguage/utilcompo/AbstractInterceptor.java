package code.expressionlanguage.utilcompo;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.structs.Struct;
import code.threads.AbstractConcurrentMap;
import code.threads.AbstractThread;

public interface AbstractInterceptor {
    AbstractInterceptorStdCaller newInterceptorStdCaller(String _cl);

    AbstractConcurrentMap<String, Struct> newMapStringStruct();
    AbstractConcurrentMap<AbstractThread,Struct> newMapAbstractThreadStruct();
    AbstractConcurrentMap<Struct,Struct> newMapStructStruct();
}
