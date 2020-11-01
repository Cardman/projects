package code.expressionlanguage.exec.calls.util;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.Parameters;

public final class CustomFoundCast implements CallingState {

    private final String className;
    private final ExecRootBlock rootBlock;
    private final ExecNamedFunctionBlock id;

    private final Parameters arguments;

    public CustomFoundCast(String _className, ExecRootBlock _rootBlock,ExecNamedFunctionBlock _id, Parameters _arguments) {
        className = _className;
        rootBlock = _rootBlock;
        id = _id;
        arguments = _arguments;
    }
    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context) {
        return ExecutingUtil.createCallingCast(_context,this);
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
