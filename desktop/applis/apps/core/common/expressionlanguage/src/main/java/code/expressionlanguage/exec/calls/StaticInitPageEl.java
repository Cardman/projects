package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.CustomFoundBlock;
import code.expressionlanguage.exec.calls.util.ReadWrite;

import code.util.IdMap;

public final class StaticInitPageEl extends AbstractPageEl {

    private Argument fwd;
    private IdMap<ExecInitBlock, Boolean> processedBlocks = new IdMap<ExecInitBlock, Boolean>();

    @Override
    public boolean checkCondition(ContextEl _context) {

        ExecBlock blockRoot_ = getBlockRoot();
        //Super interfaces have no super classes
        if (blockRoot_ instanceof ExecUniqueRootedBlock) {
            ExecUniqueRootedBlock exUniq_ = (ExecUniqueRootedBlock)blockRoot_;
            String gene_ = exUniq_.getImportedDirectGenericSuperClass();
            String superClass_ = StringExpUtil.getIdFromAllTypes(gene_);
            //initialize the super class first
            if (_context.getExiting().hasToExit(superClass_)) {
                return false;
            }
            for (String i: exUniq_.getStaticInitImportedInterfaces()) {
                //then initialize the additional super interfaces (not provided by the super class)
                if (_context.getExiting().hasToExit(i)) {
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public void tryProcessEl(ContextEl _context) {
        //initializing static fields in the type walk through
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
        if (en_ instanceof ExecStaticBlock) {
            if (!processedBlocks.getVal((ExecInitBlock)en_)) {
                processedBlocks.put((ExecInitBlock)en_, true);
                CustomFoundBlock cust_ = new CustomFoundBlock(getGlobalClass(), getGlobalArgument(),getBlockRootType(), (ExecInitBlock)en_);
                _context.setCallingState(cust_);
                return;
            }
            en_.processBlock(_context);
            return;
        }
        if (en_ instanceof ExecInstanceBlock) {
            en_.processBlock(_context);
            return;
        }
        setNullReadWrite();
    }

    public IdMap<ExecInitBlock, Boolean> getProcessedBlocks() {
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

    public void setFwd(Argument fwd) {
        this.fwd = fwd;
    }
}
