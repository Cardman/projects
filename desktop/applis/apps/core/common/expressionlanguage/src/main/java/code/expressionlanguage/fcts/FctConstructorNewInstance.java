package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.CustomReflectConstructor;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ConstructorMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class FctConstructorNewInstance extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        ConstructorMetaInfo ctor_ = (ConstructorMetaInfo) _instance;
        Struct inst_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        if (!ctor_.isInvokable()) {
            _stackCall.setCallingState(new CustomFoundExc(getNonInvokableError(_cont,ctor_, _stackCall)));
        } else {
            _stackCall.setCallingState(new CustomReflectConstructor(ctor_, inst_));
        }
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
