package code.expressionlanguage.exec.calls.util;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.Parameters;
import code.util.CustList;

public final class CustomFoundMethod implements CallingState {

    private final Argument gl;

    private final String className;

    private final ExecRootBlock rootBlock;

    private final ExecNamedFunctionBlock id;

    private final Parameters arguments;

    public CustomFoundMethod(Argument _gl, String _className, ExecRootBlock _rootBlock, ExecNamedFunctionBlock _id, Parameters _arguments) {
        gl = _gl;
        className = _className;
        rootBlock = _rootBlock;
        id = _id;
        arguments = _arguments;
    }

    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context) {
        return ExecutingUtil.createCallingMethod(_context,this);
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

    public Parameters getArguments() {
        return arguments;
    }

}
