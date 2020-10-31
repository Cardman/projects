package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.CustomFoundBlock;

import code.util.IdMap;
import code.util.core.BoolVal;

public final class FieldInitPageEl extends AbstractPageEl {

    private IdMap<ExecInitBlock, BoolVal> processedBlocks = new IdMap<ExecInitBlock, BoolVal>();

    @Override
    public boolean checkCondition(ContextEl _context) {
        return true;
    }

    @Override
    public void tryProcessEl(ContextEl _context) {
        //initializing instance fields in the type walk through
        ExecBlock en_ = getBlock();
        if (en_ instanceof WithEl) {
            ((WithEl)en_).processEl(_context);
            return;
        }
        if (en_ instanceof ExecNamedFunctionBlock) {
            en_.processBlock(_context);
            return;
        }
        if (en_ instanceof ExecStaticBlock) {
            en_.processBlock(_context);
            return;
        }
        if (en_ instanceof ExecInstanceBlock) {
            if (processedBlocks.getVal((ExecInitBlock)en_) == BoolVal.FALSE) {
                processedBlocks.put((ExecInitBlock)en_, BoolVal.TRUE);
                CustomFoundBlock cust_ = new CustomFoundBlock(getGlobalClass(), getGlobalArgument(),getBlockRootType(), (ExecInitBlock)en_);
                _context.setCallingState(cust_);
                return;
            }
            en_.processBlock(_context);
            return;
        }
        setNullReadWrite();
    }


    public IdMap<ExecInitBlock, BoolVal> getProcessedBlocks() {
        return processedBlocks;
    }

}
