package code.expressionlanguage;

import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;

public class InvokingMethod {

    private final Argument gl;

    private final String className;

    private final MethodId id;

    private final CustList<Argument> arguments;

    public InvokingMethod(Argument _gl, String _className, MethodId _id,
            CustList<Argument> _arguments) {
        gl = _gl;
        className = _className;
        id = _id;
        arguments = _arguments;
    }

    public Argument getGl() {
        return gl;
    }

    public String getClassName() {
        return className;
    }

    public MethodId getId() {
        return id;
    }

    public CustList<Argument> getArguments() {
        return arguments;
    }
}
