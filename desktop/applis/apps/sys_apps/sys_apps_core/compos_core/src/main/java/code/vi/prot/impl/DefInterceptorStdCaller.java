package code.vi.prot.impl;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.Initializer;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractAtomicInteger;
import code.threads.AbstractAtomicRef;
import code.util.EntryCust;

public final class DefInterceptorStdCaller implements AbstractInterceptorStdCaller {
    private final String cl;
    private final AbsErrGenerator gene;
    public DefInterceptorStdCaller(String _cl, AbsErrGenerator _gene) {
        this.cl = _cl;
        this.gene = _gene;
    }

    @Override
    public boolean stop(Initializer _init, ContextEl _owner, StackCall _stackCall) {
        try {
            return tryStop(_init, _owner, _stackCall);
        } catch (OutOfMemoryError e) {
            return tryThrowException(_owner, _stackCall);
        }
    }

    private boolean tryStop(Initializer _init, ContextEl _owner, StackCall _stackCall) {
        try {
            return _init.stop(_owner, _stackCall);
        } catch (Exception e) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_owner, cl, _stackCall)));
            return false;
        }
    }

    @Override
    public boolean stopNormal(Initializer _init, ContextEl _owner, StackCall _stackCall) {
        try {
            return tryStopNormal(_init, _owner, _stackCall);
        } catch (OutOfMemoryError e) {
            return tryThrowException(_owner, _stackCall);
        }
    }

    private boolean tryStopNormal(Initializer _init, ContextEl _owner, StackCall _stackCall) {
        try {
            return _init.stopNormal(_owner, _stackCall);
        } catch (Exception e) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_owner, cl, _stackCall)));
            return false;
        }
    }
    private boolean tryThrowException(ContextEl _owner, StackCall _stackCall) {
        try {
            _stackCall.setCallingState(new CustomFoundExc(gene.generate(cl,_owner,_stackCall)));
            return false;
        } catch (OutOfMemoryError e) {
            return true;
        }
    }

    @Override
    public ArgumentWrapper invoke(StdCaller _caller, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        try {
            return _caller.call(_exit, _cont, _instance, _firstArgs, _stackCall);
        } catch (Exception e) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, cl, _stackCall)));
            return new ArgumentWrapper(null);
        }
    }

    @Override
    public AbsCollection<BreakPointBlockKey> newBreakPointKeyIdStringCollection() {
        return new ConcMap<BreakPointBlockKey>(new BreakPointBlockPairKeyIdString(),this);
    }

    @Override
    public AbsCollection<BreakPointCondition> newBreakPointConditionCollection() {
        return new ConcMap<BreakPointCondition>(new BpcKeyString(), this);
    }

    @Override
    public AbsCollection<BreakPointBlockPair> newBreakPointKeyStringCollection() {
        return new ConcMap<BreakPointBlockPair>(new BreakPointBlockPairKeyString(),this);
    }

    @Override
    public AbsCollection<WatchPointBlockPair> newWatchPointKeyStringCollection() {
        return new ConcMap<WatchPointBlockPair>(new WatchPointBlockPairKeyString(),this);
    }

    @Override
    public AbsCollection<AbsCallContraints> newExecFileBlockTraceIndexCollection() {
        return new ConcMap<AbsCallContraints>(new ExecFileBlockTraceIndexKeyString(),this);
    }

    @Override
    public AbsCollection<ArrPointBlockPair> newArrPointKeyStringCollection() {
        return new ConcMap<ArrPointBlockPair>(new ArrKeyString(),this);
    }

    @Override
    public AbsCollection<ExcPointBlockPair> newExcPointKeyStringCollection() {
        return new ConcMap<ExcPointBlockPair>(new ExcKeyString(),this);
    }

    @Override
    public AbsCollection<ParPointBlockPair> newParPointKeyStringCollection() {
        return new ConcMap<ParPointBlockPair>(new ParKeyString(),this);
    }

    @Override
    public AbsCollection<MethodPointBlockPair> newMethodPointKeyStringCollection() {
        return new ConcMap<MethodPointBlockPair>(new MethodKeyString(),this);
    }

    @Override
    public AbsCollection<StdMethodPointBlockPair> newStdMethodPointKeyStringCollection() {
        return new ConcMap<StdMethodPointBlockPair>(new StdMethodKeyString(),this);
    }

    @Override
    public AbsCollection<OperNatPointBlockPair> newOperNatPointKeyStringCollection() {
        return new ConcMap<OperNatPointBlockPair>(new OperNatKeyString(), this);
    }

    @Override
    public AbsCollection<EntryCust<String, Integer>> newStringNumberCollection() {
        return new ConcList<EntryCust<String, Integer>>(new EntryCustKeyString(),this);
    }

    @Override
    public AbstractAtomicBoolean newAtBool() {
        return new DefAtomicBoolean();
    }

    @Override
    public AbstractAtomicInteger newAtInt() {
        return new DefAtomicInteger();
    }

    @Override
    public AbstractAtomicRef<StrResultContextLambda> newAtLda() {
        return new DefAtomicRef<StrResultContextLambda>(new StrResultContextLambda());
    }

    @Override
    public AbstractAtomicRef<Struct> newAtObj() {
        return new DefAtomicRef<Struct>(NullStruct.NULL_VALUE);
    }

    @Override
    public AbstractAtomicRef<Struct> newAtObj(Struct _v) {
        return new DefAtomicRef<Struct>(_v);
    }
}
