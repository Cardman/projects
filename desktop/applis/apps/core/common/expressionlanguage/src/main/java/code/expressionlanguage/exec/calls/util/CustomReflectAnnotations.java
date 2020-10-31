package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ReflectingType;
import code.util.CustList;

public final class CustomReflectAnnotations extends AbstractReflectElement {

    private final ReflectingType reflect;

    private final Argument gl;

    public CustomReflectAnnotations(ReflectingType _reflect, Argument _gl,
                                    CustList<Argument> _arguments, boolean _lambda) {
        super(_arguments,_lambda);
        reflect = _reflect;
        gl = _gl;
    }

    public ReflectingType getReflect() {
        return reflect;
    }

    public Argument getGl() {
        return gl;
    }
}