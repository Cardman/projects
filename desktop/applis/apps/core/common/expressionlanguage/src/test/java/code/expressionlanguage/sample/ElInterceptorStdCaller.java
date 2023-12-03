package code.expressionlanguage.sample;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.Initializer;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.threads.*;
import code.util.EntryCust;

public final class ElInterceptorStdCaller implements AbstractInterceptorStdCaller {
    @Override
    public boolean stop(Initializer _init, ContextEl _owner, StackCall _stackCall) {
        return _init.stop(_owner, _stackCall);
    }
    @Override
    public boolean stopNormal(Initializer _init, ContextEl _owner, StackCall _stackCall) {
        return _init.stopNormal(_owner, _stackCall);
    }

    @Override
    public ArgumentWrapper invoke(StdCaller _caller, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return _caller.call(_exit, _cont, _instance, _firstArgs, _stackCall);
    }

    @Override
    public AbsCollection<BreakPointBlockKey> newBreakPointKeyIdStringCollection() {
        return new ConcList<BreakPointBlockKey>(new BreakPointBlockPairKeyIdString(),this);
    }
    @Override
    public AbsCollection<BreakPointCondition> newBreakPointConditionCollection() {
        return new ConcList<BreakPointCondition>(new BpcKeyString(),this);
    }
    @Override
    public AbsCollection<BreakPointBlockPair> newBreakPointKeyStringCollection() {
        return new ConcList<BreakPointBlockPair>(new BreakPointBlockPairKeyString(),this);
    }

    @Override
    public AbsCollection<TypePointBlockPair> newTypePointKeyStringCollection() {
        return new ConcList<TypePointBlockPair>(new TypePointBlockPairKeyString(),this);
    }

    @Override
    public AbsCollection<WatchPointBlockPair> newWatchPointKeyStringCollection() {
        return new ConcList<WatchPointBlockPair>(new WatchPointBlockPairKeyString(),this);
    }

    @Override
    public AbsCollection<AbsCallContraints> newExecFileBlockTraceIndexCollection() {
        return new ConcList<AbsCallContraints>(new ExecFileBlockTraceIndexKeyString(),this);
    }

    @Override
    public AbsCollection<ArrPointBlockPair> newArrPointKeyStringCollection() {
        return new ConcList<ArrPointBlockPair>(new ArrKeyString(),this);
    }

    @Override
    public AbsCollection<ExcPointBlockPair> newExcPointKeyStringCollection() {
        return new ConcList<ExcPointBlockPair>(new ExcKeyString(),this);
    }

    @Override
    public AbsCollection<ParPointBlockPair> newParPointKeyStringCollection() {
        return new ConcList<ParPointBlockPair>(new ParKeyString(), this);
    }

    @Override
    public AbsCollection<MethodPointBlockPair> newMethodPointKeyStringCollection() {
        return new ConcList<MethodPointBlockPair>(new MethodKeyString(),this);
    }

    @Override
    public AbsCollection<StdMethodPointBlockPair> newStdMethodPointKeyStringCollection() {
        return new ConcList<StdMethodPointBlockPair>(new StdMethodKeyString(),this);
    }

    @Override
    public AbsCollection<OperNatPointBlockPair> newOperNatPointKeyStringCollection() {
        return new ConcList<OperNatPointBlockPair>(new OperNatKeyString(), this);
    }

    @Override
    public AbsCollection<EntryCust<String, Integer>> newStringNumberCollection() {
        return new ConcList<EntryCust<String, Integer>>(new EntryCustKeyString(), this);
    }

    @Override
    public AbstractAtomicBoolean newAtBool() {
        return new ConcreteBoolean();
    }
    @Override
    public AbstractAtomicInteger newAtInt() {
        return new ConcreteInteger();
    }

    @Override
    public AbstractAtomicRef<StrResultContextLambda> newAtLda() {
        return new ConcreteRef<StrResultContextLambda>(new StrResultContextLambda());
    }

    @Override
    public AbstractAtomicRef<Struct> newAtObj() {
        return new ConcreteRef<Struct>(NullStruct.NULL_VALUE);
    }

    @Override
    public AbstractAtomicRef<Struct> newAtObj(Struct _v) {
        return new ConcreteRef<Struct>(_v);
    }
}
