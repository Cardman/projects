package code.expressionlanguage.exec.calls.util;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecInitBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractInitPageEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;

public final class CustomFoundBlock implements CallingState {

    private final ExecFormattedRootBlock className;

    private final Argument currentObject;

    private final ExecRootBlock rootBlock;
    private final ExecInitBlock block;

    public CustomFoundBlock(AbstractInitPageEl _init,
                            ExecInitBlock _block) {
        className = _init.getGlobalClass();
        currentObject = _init.getGlobalArgument();
        rootBlock = _init.getBlockRootType();
        block = _block;
    }

    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stack) {
        return ExecutingUtil.createBlockPageEl(_context,getClassName(), getCurrentObject(),getRootBlock(), getBlock());
    }
    public ExecFormattedRootBlock getClassName() {
        return className;
    }

    public Argument getCurrentObject() {
        return currentObject;
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }

    public ExecInitBlock getBlock() {
        return block;
    }
}
