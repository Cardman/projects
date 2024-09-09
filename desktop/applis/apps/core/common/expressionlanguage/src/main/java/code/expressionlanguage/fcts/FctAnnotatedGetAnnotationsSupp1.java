package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomReflectAnnotations;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctAnnotatedGetAnnotationsSupp1 extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        MethodMetaInfo annotated_ = (MethodMetaInfo)_instance;
        _stackCall.setCallingState(new CustomReflectAnnotations(ReflectingType.ANNOT_SUPP,annotated_, new CustList<Struct>(_firstArgs.getArgumentWrappers().get(0).getValue())));
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
