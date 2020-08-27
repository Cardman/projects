package code.expressionlanguage.exec.calls.util;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.util.CustList;

public final class CustomFoundMethod implements CallingState {

    private final Argument gl;

    private final String className;

    private final ExecRootBlock rootBlock;

    private final ExecNamedFunctionBlock id;

    private final CustList<Argument> arguments;

    private final Argument right;

    public CustomFoundMethod(Argument _gl, String _className, ExecRootBlock _rootBlock, ExecNamedFunctionBlock _id, CustList<Argument> _arguments, Argument _right) {
        gl = _gl;
        className = _className;
        rootBlock = _rootBlock;
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

    public ExecRootBlock getRootBlock() {
        return rootBlock;
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
