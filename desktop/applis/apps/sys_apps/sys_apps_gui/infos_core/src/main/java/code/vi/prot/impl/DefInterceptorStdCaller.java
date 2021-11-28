package code.vi.prot.impl;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.DefaultInitializer;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.Struct;

public final class DefInterceptorStdCaller implements AbstractInterceptorStdCaller {
    private final String cl;
    private final AbsErrGenerator gene;
    public DefInterceptorStdCaller(String _cl, AbsErrGenerator _gene) {
        this.cl = _cl;
        this.gene = _gene;
    }

    @Override
    public boolean stop(DefaultInitializer _init, ContextEl _owner, StackCall _stackCall) {
        try {
            return tryStop(_init, _owner, _stackCall);
        } catch (OutOfMemoryError e) {
            return tryThrowException(_owner, _stackCall);
        }
    }

    private boolean tryStop(DefaultInitializer _init, ContextEl _owner, StackCall _stackCall) {
        try {
            return _init.stop(_owner, _stackCall);
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
    public boolean exitAfterCallInt(DefaultInitializer _init, ContextEl _owner, StackCall _stack) {
        try {
            return tryExit(_init, _owner, _stack);
        } catch (OutOfMemoryError e) {
            return true;
        }
    }

    private static boolean tryExit(DefaultInitializer _init, ContextEl _owner, StackCall _stack) {
        try {
            return _init.exitAfterCallInt(_owner, _stack);
        } catch (Exception e) {
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
}
