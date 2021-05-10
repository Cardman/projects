package code.expressionlanguage.exec.calls.util;


import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;

public final class NotInitializedClass implements CallingState {

    private final String className;
    private final ExecRootBlock rootBlock;
    private final Argument argument;

    public NotInitializedClass(String _className, ExecRootBlock _rootBlock, Argument _argument) {
        className = _className;
        rootBlock = _rootBlock;
        argument = _argument;
    }
    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stack) {
        return ExecutingUtil.createInstancingClass(this);
    }
    public String getClassName() {
        return className;
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }

    public Argument getArgument() {
        return argument;
    }
}
