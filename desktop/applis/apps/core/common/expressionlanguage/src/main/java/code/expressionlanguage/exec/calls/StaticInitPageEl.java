package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.CustomFoundBlock;

import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.BoolVal;

public final class StaticInitPageEl extends AbstractInitPageEl {

    private Argument fwd;
    private final IdMap<ExecInitBlock, BoolVal> processedBlocks = new IdMap<ExecInitBlock, BoolVal>();

    public StaticInitPageEl(CustList<ExecBlock> _visited, ExecFormattedRootBlock _global) {
        super(_visited,_global);
    }

    @Override
    public void processTagsBase(ContextEl _context, StackCall _stack){
        ExecRootBlock blockRoot_ = getBlockRootType();
        //Super interfaces have no super classes
        String gene_ = blockRoot_.getImportedDirectGenericSuperClass();
        String superClass_ = StringExpUtil.getIdFromAllTypes(gene_);
        //initialize the super class first
        if (_context.getExiting().hasToExit(_stack, superClass_)) {
            return;
        }
        for (String i: blockRoot_.getStaticInitImportedInterfaces()) {
            //then initialize the additional super interfaces (not provided by the super class)
            if (_context.getExiting().hasToExit(_stack, i)) {
                return;
            }
        }
        //initializing static fields in the type walk through
        ExecBlock en_ = getBlock();
        if (en_ instanceof ExecElementBlock) {
            setGlobalOffset(en_.getOffsetTrim());
            setOffset(0);
            ((ExecElementBlock)en_).processEl(_context,_stack,this);
            return;
        }
        if (en_ instanceof ExecInnerElementBlock) {
            setGlobalOffset(en_.getOffsetTrim());
            setOffset(0);
            ((ExecInnerElementBlock)en_).processEl(_context,_stack,this);
            return;
        }
        if (en_ instanceof ExecFieldBlock) {
            setGlobalOffset(en_.getOffsetTrim());
            setOffset(0);
            ((ExecFieldBlock)en_).processEl(_context,_stack,this);
            return;
        }
        if (en_ instanceof ExecStaticBlock && processedBlocks.getVal((ExecInitBlock) en_) == BoolVal.FALSE) {
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

    public void sucessClass(ContextEl _context) {
        String curClass_ = getGlobalClass().getFormatted();
        String curClassBase_ = StringExpUtil.getIdFromAllTypes(curClass_);
        _context.getLocks().successClass(curClassBase_);
    }

    public Argument getFwd() {
        return fwd;
    }

    public void setFwd(Argument _fwd) {
        this.fwd = _fwd;
    }
}
