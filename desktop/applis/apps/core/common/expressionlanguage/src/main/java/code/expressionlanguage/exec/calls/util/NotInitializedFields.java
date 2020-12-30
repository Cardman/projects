package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;

public final class NotInitializedFields implements CallingState {

    private final String className;
    private final ExecRootBlock rootBlock;

    private final Argument currentObject;

    public NotInitializedFields(String _className,
                                ExecRootBlock _rootBlock,
            Argument _currentObject) {
        className = _className;
        rootBlock = _rootBlock;
        currentObject = _currentObject;
    }

    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stack) {
        return ExecutingUtil.createInitFields(getRootBlock(),getClassName(), getCurrentObject(), _stack);
    }
    public String getClassName() {
        return className;
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }

    public Argument getCurrentObject() {
        return currentObject;
    }
}
