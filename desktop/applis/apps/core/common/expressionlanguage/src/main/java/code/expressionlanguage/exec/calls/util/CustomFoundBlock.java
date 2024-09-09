package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecInitBlock;
import code.expressionlanguage.exec.calls.AbstractInitPageEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.structs.Struct;

public final class CustomFoundBlock implements CallingState,GlobalClassCallingState {

    private final ExecFormattedRootBlock className;

    private final Struct currentObject;

    private final ExecInitBlock block;

    public CustomFoundBlock(AbstractInitPageEl _init,
                            ExecInitBlock _block) {
        className = _init.getGlobalClass();
        currentObject = _init.getGlobalStruct();
        block = _block;
    }

    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stack) {
        return ExecutingUtil.createBlockPageEl(_context,getClassName(), getCurrentObject(), getBlock());
    }
    public ExecFormattedRootBlock getClassName() {
        return className;
    }

    public Struct getCurrentObject() {
        return currentObject;
    }

    public ExecInitBlock getBlock() {
        return block;
    }
}
