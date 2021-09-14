package code.expressionlanguage.utilcompo;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.Struct;
import code.threads.AbstractConcurrentMap;
import code.threads.AbstractThread;

public interface AbstractInterceptor {
    AbstractInterceptorStdCaller newInterceptorStdCaller(String _cl);
    ResultErrorStd instance(String _cl,ContextEl _cont, ConstructorId _method, StackCall _stackCall, Argument... _args);
    ResultErrorStd invoke(String _cl,ContextEl _cont, ClassMethodId _method, Struct _struct, AbstractExiting _exit, StackCall _stackCall, Argument... _args);
    AbstractConcurrentMap<String, Struct> newMapStringStruct();
    AbstractConcurrentMap<AbstractThread,Struct> newMapAbstractThreadStruct();
    AbstractConcurrentMap<Struct,Struct> newMapStructStruct();
}
