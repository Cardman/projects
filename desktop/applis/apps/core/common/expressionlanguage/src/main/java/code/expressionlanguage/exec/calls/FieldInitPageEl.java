package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.CustomFoundBlock;

import code.util.IdMap;
import code.util.core.BoolVal;

public final class FieldInitPageEl extends AbstractPageEl {

    private final IdMap<ExecInitBlock, BoolVal> processedBlocks = new IdMap<ExecInitBlock, BoolVal>();

    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        return true;
    }

    @Override
    public void tryProcessEl(ContextEl _context, StackCall _stack) {
        //initializing instance fields in the type walk through
        ExecBlock en_ = getBlock();
        if (en_ instanceof WithEl) {
            ((WithEl)en_).processEl(_context, _stack);
            return;
        }
        if (en_ instanceof ExecNamedFunctionBlock) {
            en_.processMemberBlock(_stack);
            return;
        }
        if (en_ instanceof ExecStaticBlock) {
            en_.processMemberBlock(_stack);
            return;
        }
        if (en_ instanceof ExecInstanceBlock) {
            if (processedBlocks.getVal((ExecInitBlock)en_) == BoolVal.FALSE) {
                processedBlocks.put((ExecInitBlock)en_, BoolVal.TRUE);
                CustomFoundBlock cust_ = new CustomFoundBlock(getGlobalClass(), getGlobalArgument(),getBlockRootType(), (ExecInitBlock)en_);
                _stack.setCallingState(cust_);
                return;
            }
            en_.processMemberBlock(_stack);
            return;
        }
        setNullReadWrite();
    }


    public IdMap<ExecInitBlock, BoolVal> getProcessedBlocks() {
        return processedBlocks;
    }

}
