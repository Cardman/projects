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
import code.expressionlanguage.structs.Struct;
import code.threads.*;

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
}
