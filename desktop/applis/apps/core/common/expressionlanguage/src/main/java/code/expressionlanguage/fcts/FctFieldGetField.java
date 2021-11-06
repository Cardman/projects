package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.CustomReflectGetField;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.FieldMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class FctFieldGetField extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        FieldMetaInfo field_ = (FieldMetaInfo) _instance;
        Struct inst_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        if (!field_.isInvokable()) {
            _stackCall.setCallingState(new CustomFoundExc(getNonInvokableError(_cont,field_, _stackCall)));
        } else {
            _stackCall.setCallingState(new CustomReflectGetField(ReflectingType.GET_FIELD, field_, new Argument(inst_), false));
        }
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
