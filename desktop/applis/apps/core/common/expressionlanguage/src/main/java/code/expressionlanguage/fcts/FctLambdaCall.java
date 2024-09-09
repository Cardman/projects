package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class FctLambdaCall extends FctReflection {
    private final int ref;
    public FctLambdaCall(int _refer) {
        ref = _refer;
    }
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        Struct instance_ = _firstArgs.getArgumentWrappers().get(0).getValue();
        if (!(instance_ instanceof ArrayStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(FctReflection.getNpe(_cont, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        ArrayStruct arr_ = (ArrayStruct) instance_;
        ExecInvokingOperation.prepareCallDynReflect(_instance, arr_,ref, _cont, _stackCall);
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
