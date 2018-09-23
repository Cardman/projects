package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.util.CustList;

public class CustomReflectMethod {

    private final ReflectingType reflect;

    private final Argument gl;

    private final CustList<Argument> arguments;

    public CustomReflectMethod(ReflectingType _reflect, Argument _gl,
            CustList<Argument> _arguments) {
        reflect = _reflect;
        gl = _gl;
        arguments = _arguments;
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
}
