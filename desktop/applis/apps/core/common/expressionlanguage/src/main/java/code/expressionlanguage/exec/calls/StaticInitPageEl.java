package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.BoolVal;

public final class StaticInitPageEl extends AbstractInitPageEl {
    private boolean checkBegin;
    private Struct fwd;
    private final IdMap<ExecInitBlock, BoolVal> processedBlocks = new IdMap<ExecInitBlock, BoolVal>();

    public StaticInitPageEl(CustList<ExecBlock> _visited, ExecFormattedRootBlock _global) {
        super(_visited,_global);
    }

    @Override
    public void processTagsBase(ContextEl _context, StackCall _stack){
        ExecRootBlock blockRoot_ = getBlockRootType();
        if (!checkBegin) {
            globalOffset(blockRoot_.getIdRowCol());
            if (ExecHelperBlocks.checkBp(_stack,this,null)){
                return;
            }
            checkBegin = true;
        }
        //Super interfaces have no super classes
        //initialize the super class first
        if (_context.getExiting().hasToExit(_stack, blockRoot_.getUniqueType())) {
            return;
        }
        for (ExecRootBlock i: blockRoot_.getStaticInitImportedInterfaces()) {
            //then initialize the additional super interfaces (not provided by the super class)
            if (_context.getExiting().hasToExit(_stack, i)) {
                return;
            }
        }
        //initializing static fields in the type walk through
        ExecBlock en_ = getBlock();
        if (en_ instanceof ExecInfoBlock) {
            ((ExecInfoBlock)en_).getElementContent().processEl(_context,_stack,this, en_);
            return;
        }
        block(_stack, processedBlocks);
    }

    public IdMap<ExecInitBlock, BoolVal> getProcessedBlocks() {
        return processedBlocks;
    }

    public void sucessClass(ContextEl _context) {
        ExecRootBlock curClass_ = getBlockRootType();
        _context.getLocks().successClass(curClass_);
    }

    public Struct getFwd() {
        return fwd;
    }

    public void setFwd(Struct _fwd) {
        this.fwd = _fwd;
    }
}
