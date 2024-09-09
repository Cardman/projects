package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractCallingInstancingPageEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.structs.Struct;

public final class NotInitializedFields implements CallingState,GlobalClassCallingState {

    private final ExecFormattedRootBlock className;
    private final ExecRootBlock rootBlock;

    private final Struct currentObject;

    public NotInitializedFields(AbstractCallingInstancingPageEl _caller) {
        className = _caller.getGlobalClass();
        rootBlock = _caller.getBlockRootType();
        currentObject = _caller.getGlobalStruct();
    }

    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stack) {
        return ExecutingUtil.createInitFields(getRootBlock(),getClassName(), getCurrentObject());
    }
    public ExecFormattedRootBlock getClassName() {
        return className;
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }

    public Struct getCurrentObject() {
        return currentObject;
    }
}
