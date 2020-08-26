package code.expressionlanguage.exec.calls.util;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;

public final class CustomFoundMethod implements CallingState {

    private final Argument gl;

    private final String className;

    private final ExecNamedFunctionBlock id;

    private final CustList<Argument> arguments;

    private final Argument right;

    public CustomFoundMethod(Argument _gl, String _className, ExecNamedFunctionBlock _id, CustList<Argument> _arguments, Argument _right) {
        gl = _gl;
        className = _className;
        id = _id;
        arguments = _arguments;
        right = _right;
    }

    public Argument getGl() {
        return gl;
    }

    public String getClassName() {
        return className;
    }

    public ExecNamedFunctionBlock getId() {
        return id;
    }

    public CustList<Argument> getArguments() {
        return arguments;
    }

    public Argument getRight() {
        return right;
    }
}
