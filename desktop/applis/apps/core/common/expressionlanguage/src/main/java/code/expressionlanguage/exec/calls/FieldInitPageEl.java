package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecAnnotationMethodBlock;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecFieldBlock;
import code.expressionlanguage.exec.blocks.ExecInitBlock;
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
            ((ExecFieldBlock)en_).getElementContent().processEl(_context,_stack,this, en_);
            return;
        }
        if (en_ instanceof ExecAnnotationMethodBlock) {
            ((ExecAnnotationMethodBlock)en_).processEl(_context,_stack,this);
            return;
        }
        block(_stack, processedBlocks);
    }

    public IdMap<ExecInitBlock, BoolVal> getProcessedBlocks() {
        return processedBlocks;
    }

}
