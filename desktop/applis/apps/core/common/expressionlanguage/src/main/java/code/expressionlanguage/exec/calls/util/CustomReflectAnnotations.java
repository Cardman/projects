package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.structs.AnnotatedStruct;
import code.util.CustList;

public final class CustomReflectAnnotations extends AbstractReflectElement {

    private final ReflectingType reflect;

    private final AnnotatedStruct gl;

    public CustomReflectAnnotations(ReflectingType _reflect, AnnotatedStruct _gl,
                                    CustList<Argument> _arguments, boolean _lambda) {
        super(_arguments,_lambda);
        reflect = _reflect;
        gl = _gl;
    }

    public ReflectingType getReflect() {
        return reflect;
    }

    public AnnotatedStruct getGl() {
        return gl;
    }
}