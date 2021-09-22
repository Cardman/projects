package code.expressionlanguage.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.DefaultInitializer;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.Struct;

public interface AbstractInterceptorStdCaller {
    boolean stop(DefaultInitializer _init, ContextEl _owner, StackCall _stackCall);
    boolean exitAfterCallInt(DefaultInitializer _init, ContextEl _owner, StackCall _stack);
    ArgumentWrapper invoke(StdCaller _caller,AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall);
}
