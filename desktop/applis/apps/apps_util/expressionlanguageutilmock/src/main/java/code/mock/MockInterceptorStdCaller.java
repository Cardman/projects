package code.mock;

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

public final class MockInterceptorStdCaller implements AbstractInterceptorStdCaller {
    @Override
    public boolean stop(Initializer _defaultInitializer, ContextEl _contextEl, StackCall _stackCall) {
        return _defaultInitializer.stop(_contextEl,_stackCall);
    }
    @Override
    public boolean stopNormal(Initializer _defaultInitializer, ContextEl _contextEl, StackCall _stackCall) {
        return _defaultInitializer.stopNormal(_contextEl,_stackCall);
    }

    @Override
    public ArgumentWrapper invoke(StdCaller _stdCaller, AbstractExiting _abstractExiting, ContextEl _contextEl, Struct _struct, ArgumentListCall _argumentListCall, StackCall _stackCall) {
        return _stdCaller.call(_abstractExiting,_contextEl,_struct,_argumentListCall,_stackCall);
    }

    @Override
    public AbsCollection<BreakPointBlockKey> newBreakPointKeyIdStringCollection() {
        return new ConcList<BreakPointBlockKey>(this);
    }
    @Override
    public AbsCollection<BreakPointCondition> newBreakPointConditionCollection() {
        return new ConcList<BreakPointCondition>(this);
    }
    @Override
    public AbsCollection<BreakPointBlockPair> newBreakPointKeyStringCollection() {
        return new ConcList<BreakPointBlockPair>(this);
    }

    @Override
    public AbsCollection<WatchPointBlockPair> newWatchPointKeyStringCollection() {
        return new ConcList<WatchPointBlockPair>(this);
    }

    @Override
    public AbsCollection<AbsCallContraints> newExecFileBlockTraceIndexCollection() {
        return new ConcList<AbsCallContraints>(this);
    }

    @Override
    public AbsCollection<ExcPointBlockPair> newExcPointKeyStringCollection() {
        return new ConcList<ExcPointBlockPair>(this);
    }

    @Override
    public AbsCollection<MethodPointBlockPair> newMethodPointKeyStringCollection() {
        return new ConcList<MethodPointBlockPair>(this);
    }

    @Override
    public AbsCollection<StdMethodPointBlockPair> newStdMethodPointKeyStringCollection() {
        return new ConcList<StdMethodPointBlockPair>(this);
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
