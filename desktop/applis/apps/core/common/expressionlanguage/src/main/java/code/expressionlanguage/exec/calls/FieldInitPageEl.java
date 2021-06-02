package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.CustomFoundBlock;

import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.BoolVal;

public final class FieldInitPageEl extends AbstractInitPageEl {

    private final IdMap<ExecInitBlock, BoolVal> processedBlocks = new IdMap<ExecInitBlock, BoolVal>();

    public FieldInitPageEl(CustList<ExecBlock> _visited, ExecFormattedRootBlock _global) {
        super(_visited,_global);
    }

    @Override
    public void processTagsBase(ContextEl _context, StackCall _stack){
        //initializing instance fields in the type walk through
        ExecBlock en_ = getBlock();
        if (en_ instanceof ExecFieldBlock) {
            setGlobalOffset(en_.getOffsetTrim());
            setOffset(0);
            ((ExecFieldBlock)en_).processEl(_context,_stack,this);
            return;
        }
        if (en_ instanceof ExecAnnotationMethodBlock) {
            setGlobalOffset(en_.getOffsetTrim());
            setOffset(0);
            ((ExecAnnotationMethodBlock)en_).processEl(_context,_stack,this);
            return;
        }
        if (en_ instanceof ExecInstanceBlock && processedBlocks.getVal((ExecInitBlock) en_) == BoolVal.FALSE) {
            setGlobalOffset(en_.getOffsetTrim());
            setOffset(0);
            processedBlocks.put((ExecInitBlock) en_, BoolVal.TRUE);
            CustomFoundBlock cust_ = new CustomFoundBlock(this, (ExecInitBlock) en_);
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
