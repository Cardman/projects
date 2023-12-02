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
import code.threads.AbstractAtomicInteger;
import code.threads.AbstractAtomicRef;
import code.util.EntryCust;

public interface AbstractInterceptorStdCaller {
    boolean stop(Initializer _init, ContextEl _owner, StackCall _stackCall);
    boolean stopNormal(Initializer _init, ContextEl _owner, StackCall _stackCall);
    ArgumentWrapper invoke(StdCaller _caller,AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall);
    AbsCollection<BreakPointBlockKey> newBreakPointKeyIdStringCollection();
    AbsCollection<BreakPointCondition> newBreakPointConditionCollection();
    AbsCollection<EntryCust<String,Integer>> newStringNumberCollection();
    AbsCollection<BreakPointBlockPair> newBreakPointKeyStringCollection();
    AbsCollection<TypePointBlockPair> newTypePointKeyStringCollection();
    AbsCollection<WatchPointBlockPair> newWatchPointKeyStringCollection();
    AbsCollection<ArrPointBlockPair> newArrPointKeyStringCollection();
    AbsCollection<ExcPointBlockPair> newExcPointKeyStringCollection();
    AbsCollection<ParPointBlockPair> newParPointKeyStringCollection();
    AbsCollection<AbsCallContraints> newExecFileBlockTraceIndexCollection();
    AbsCollection<MethodPointBlockPair> newMethodPointKeyStringCollection();
    AbsCollection<StdMethodPointBlockPair> newStdMethodPointKeyStringCollection();
    AbsCollection<AbsOperNatPointBlockPair> newOperNatPointKeyStringCollection();
    AbstractAtomicBoolean newAtBool();
    AbstractAtomicInteger newAtInt();
    AbstractAtomicRef<StrResultContextLambda> newAtLda();
    AbstractAtomicRef<Struct> newAtObj();
    AbstractAtomicRef<Struct> newAtObj(Struct _v);
}
