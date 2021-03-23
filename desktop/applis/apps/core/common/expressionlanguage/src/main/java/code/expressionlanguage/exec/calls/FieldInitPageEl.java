package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.CustomFoundBlock;

import code.util.CustList;
import code.util.IdMap;
import code.util.core.BoolVal;

public final class FieldInitPageEl extends AbstractInitPageEl {

    private final IdMap<ExecInitBlock, BoolVal> processedBlocks = new IdMap<ExecInitBlock, BoolVal>();

    public FieldInitPageEl(CustList<ExecBlock> _visited) {
        super(_visited);
    }

    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        return true;
    }

    @Override
    public void tryProcessEl(ContextEl _context, StackCall _stack) {
        //initializing instance fields in the type walk through
        ExecBlock en_ = getBlock();
        if (en_ instanceof ExecFieldBlock) {
            ((ExecFieldBlock)en_).processEl(_context,_stack,this);
            return;
        }
        if (en_ instanceof ExecAnnotationMethodBlock) {
            ((ExecAnnotationMethodBlock)en_).processEl(_context,_stack,this);
            return;
        }
        if (en_ instanceof ExecInstanceBlock && processedBlocks.getVal((ExecInitBlock) en_) == BoolVal.FALSE) {
            processedBlocks.put((ExecInitBlock) en_, BoolVal.TRUE);
            CustomFoundBlock cust_ = new CustomFoundBlock(getGlobalClass(), getGlobalArgument(), getBlockRootType(), (ExecInitBlock) en_);
            _stack.setCallingState(cust_);
            return;
        }
        if (en_ != null) {
            en_.processMemberBlock(this);
            return;
        }
        setNullReadWrite();
    }


    public IdMap<ExecInitBlock, BoolVal> getProcessedBlocks() {
        return processedBlocks;
    }

}
