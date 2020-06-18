package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.CustomFoundBlock;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.*;
import code.util.IdMap;

public final class StaticInitPageEl extends AbstractPageEl {

    private IdMap<ExecInitBlock, Boolean> processedBlocks = new IdMap<ExecInitBlock, Boolean>();

    @Override
    public boolean checkCondition(ContextEl _context) {
        Classes classes_ = _context.getClasses();

        String curClass_ = getGlobalClass();
        String curClassBase_ = Templates.getIdFromAllTypes(curClass_);
        ExecRootBlock root_ =  classes_.getClassBody(curClassBase_);
        //Super interfaces have no super classes
        if (root_ instanceof ExecUniqueRootedBlock) {
            String gene_ = ((ExecUniqueRootedBlock) root_).getImportedDirectGenericSuperClass();
            String superClass_ = Templates.getIdFromAllTypes(gene_);
            if (classes_.getClassBody(superClass_) != null) {
                //initialize the super class first
                if (_context.hasToExit(superClass_)) {
                    return false;
                }
            }
            for (String i: root_.getStaticInitImportedInterfaces()) {
                //then initialize the additional super interfaces (not provided by the super class)
                if (_context.hasToExit(i)) {
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
        if (en_ instanceof Returnable) {
            en_.processBlock(_context);
            return;
        }
        if (en_ instanceof ExecRootBlock) {
            en_.processBlock(_context);
            return;
        }
        if (en_ instanceof ExecStaticBlock) {
            if (!processedBlocks.getVal((ExecInitBlock)en_)) {
                processedBlocks.put((ExecInitBlock)en_, true);
                CustomFoundBlock cust_ = new CustomFoundBlock(getGlobalClass(), getGlobalArgument(), (ExecInitBlock)en_);
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
        String curClassBase_ = Templates.getIdFromAllTypes(curClass_);
        _context.getClasses().getLocks().successClass(curClassBase_);
    }
}
