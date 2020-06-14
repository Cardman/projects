package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.methods.ReflectingType;
import code.util.CustList;

public final class CustomReflectMethod implements CallingState {

    private final ReflectingType reflect;

    private final Argument gl;

    private final CustList<Argument> arguments;

    private final boolean lambda;

    public CustomReflectMethod(ReflectingType _reflect, Argument _gl,
            CustList<Argument> _arguments, boolean _lambda) {
        reflect = _reflect;
        gl = _gl;
        arguments = _arguments;
        lambda = _lambda;
    }

    public ReflectingType getReflect() {
        return reflect;
    }

    public Argument getGl() {
        return gl;
    }

    public CustList<Argument> getArguments() {
        return arguments;
    }
    public boolean isLambda() {
        return lambda;
    }
}
