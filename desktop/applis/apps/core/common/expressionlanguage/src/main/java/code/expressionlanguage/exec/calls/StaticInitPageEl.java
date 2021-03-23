package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.CustomFoundBlock;

import code.util.IdMap;
import code.util.core.BoolVal;

public final class StaticInitPageEl extends AbstractPageEl {

    private Argument fwd;
    private final IdMap<ExecInitBlock, BoolVal> processedBlocks = new IdMap<ExecInitBlock, BoolVal>();

    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {

        ExecRootBlock blockRoot_ = getBlockRootType();
        //Super interfaces have no super classes
        String gene_ = blockRoot_.getImportedDirectGenericSuperClass();
        String superClass_ = StringExpUtil.getIdFromAllTypes(gene_);
        //initialize the super class first
        if (_context.getExiting().hasToExit(_stack, superClass_)) {
            return false;
        }
        for (String i: blockRoot_.getStaticInitImportedInterfaces()) {
            //then initialize the additional super interfaces (not provided by the super class)
            if (_context.getExiting().hasToExit(_stack, i)) {
                return false;
            }
        }
        return true;
    }
    @Override
    public void tryProcessEl(ContextEl _context, StackCall _stack) {
        //initializing static fields in the type walk through
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
            if (processedBlocks.getVal((ExecInitBlock)en_) == BoolVal.FALSE) {
                processedBlocks.put((ExecInitBlock)en_, BoolVal.TRUE);
                CustomFoundBlock cust_ = new CustomFoundBlock(getGlobalClass(), getGlobalArgument(),getBlockRootType(), (ExecInitBlock)en_);
                _stack.setCallingState(cust_);
                return;
            }
            en_.processMemberBlock(_stack);
            return;
        }
        if (en_ instanceof ExecInstanceBlock) {
            en_.processMemberBlock(_stack);
            return;
        }
        setNullReadWrite();
    }

    public IdMap<ExecInitBlock, BoolVal> getProcessedBlocks() {
        return processedBlocks;
    }

    public void sucessClass(ContextEl _context) {
        String curClass_ = getGlobalClass();
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
