package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomReflectAnnotations;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.AnnotatedStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctAnnotatedGetAnnotationsParam0 extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        AnnotatedStruct annotated_ = NumParsers.getAnnotated(_instance);
        _stackCall.setCallingState(new CustomReflectAnnotations(ReflectingType.ANNOTATION_PARAM, annotated_, new CustList<Struct>()));
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
