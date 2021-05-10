package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractCallingInstancingPageEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;

public final class NotInitializedFields implements CallingState {

    private final String className;
    private final ExecRootBlock rootBlock;

    private final Argument currentObject;

    public NotInitializedFields(AbstractCallingInstancingPageEl _caller) {
        className = _caller.getGlobalClass();
        rootBlock = _caller.getBlockRootType();
        currentObject = _caller.getGlobalArgument();
    }

    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stack) {
        return ExecutingUtil.createInitFields(getRootBlock(),getClassName(), getCurrentObject());
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
