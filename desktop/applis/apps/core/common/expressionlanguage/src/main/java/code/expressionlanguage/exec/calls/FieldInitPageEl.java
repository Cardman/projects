package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.CustomFoundBlock;
import code.expressionlanguage.exec.calls.util.ReadWrite;

import code.util.IdMap;

public final class FieldInitPageEl extends AbstractPageEl {

    private IdMap<ExecInitBlock, Boolean> processedBlocks = new IdMap<ExecInitBlock, Boolean>();

    @Override
    public boolean checkCondition(ContextEl _context) {
        return true;
    }

    @Override
    public void tryProcessEl(ContextEl _context) {
        //initializing instance fields in the type walk through
        ReadWrite rw_ = getReadWrite();
        ExecBlock en_ = rw_.getBlock();
        if (en_ instanceof WithEl) {
            ((WithEl)en_).processEl(_context);
            return;
        }
        if (en_ instanceof ExecNamedFunctionBlock) {
            en_.processBlock(_context);
            return;
        }
        if (en_ instanceof ExecRootBlock) {
            en_.processBlock(_context);
            return;
        }
        if (en_ instanceof ExecStaticBlock) {
            en_.processBlock(_context);
            return;
        }
        if (en_ instanceof ExecInstanceBlock) {
            if (!processedBlocks.getVal((ExecInitBlock)en_)) {
                processedBlocks.put((ExecInitBlock)en_, true);
                CustomFoundBlock cust_ = new CustomFoundBlock(getGlobalClass(), getGlobalArgument(), (ExecInitBlock)en_);
                _context.setCallingState(cust_);
                return;
            }
            en_.processBlock(_context);
            return;
        }
        setNullReadWrite();
    }


    public IdMap<ExecInitBlock, Boolean> getProcessedBlocks() {
        return processedBlocks;
    }

}
