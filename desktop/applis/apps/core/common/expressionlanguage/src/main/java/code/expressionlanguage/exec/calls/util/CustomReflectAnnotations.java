package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.structs.AnnotatedStruct;
import code.util.CustList;

public final class CustomReflectAnnotations extends AbstractReflectElement {

    private final ReflectingType reflect;

    private final AnnotatedStruct gl;

    private final CustList<Argument> arguments;
    public CustomReflectAnnotations(ReflectingType _reflect, AnnotatedStruct _gl,
                                    CustList<Argument> _arguments, boolean _lambda) {
        super(_lambda);
        reflect = _reflect;
        gl = _gl;
        arguments = _arguments;
    }

    public ReflectingType getReflect() {
        return reflect;
    }

    public AnnotatedStruct getGl() {
        return gl;
    }

    public CustList<Argument> getArguments() {
        return arguments;
    }
}