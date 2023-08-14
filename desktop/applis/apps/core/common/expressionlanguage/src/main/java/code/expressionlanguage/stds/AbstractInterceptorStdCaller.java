package code.expressionlanguage.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.Initializer;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.Struct;
import code.threads.AbstractAtomicBoolean;

public interface AbstractInterceptorStdCaller {
    boolean stop(Initializer _init, ContextEl _owner, StackCall _stackCall);
    boolean stopNormal(Initializer _init, ContextEl _owner, StackCall _stackCall);
    ArgumentWrapper invoke(StdCaller _caller,AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall);
    AbsCollection<BreakPointBlockPair> newBreakPointKeyStringCollection();
    AbsCollection<WatchPointBlockPair> newWatchPointKeyStringCollection();
    AbsCollection<ExcPointBlockPair> newExcPointKeyStringCollection();
    AbsCollection<AbsCallContraints> newExecFileBlockTraceIndexCollection();
    AbsCollection<MethodPointBlockPair> newMethodPointKeyStringCollection();
    AbstractAtomicBoolean newAtBool();
}
