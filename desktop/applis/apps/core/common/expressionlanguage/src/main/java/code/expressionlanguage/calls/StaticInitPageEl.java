package code.expressionlanguage.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.util.CustomFoundBlock;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.util.IdMap;

public final class StaticInitPageEl extends AbstractPageEl {

    private IdMap<InitBlock, Boolean> processedBlocks = new IdMap<InitBlock, Boolean>();

    @Override
    public boolean checkCondition(ContextEl _context) {
        Classes classes_ = _context.getClasses();

        String curClass_ = getGlobalClass();
        String curClassBase_ = Templates.getIdFromAllTypes(curClass_);
        RootBlock root_ =  classes_.getClassBody(curClassBase_);
        //Super interfaces have no super classes
        if (root_ instanceof UniqueRootedBlock) {
            String gene_ = ((UniqueRootedBlock) root_).getImportedDirectGenericSuperClass();
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
        Block en_ = rw_.getBlock();
        if (en_ instanceof WithEl) {
            ((WithEl)en_).processEl(_context);
            return;
        }
        if (en_ instanceof Returnable) {
            en_.processBlock(_context);
            return;
        }
        if (en_ instanceof RootBlock) {
            en_.processBlock(_context);
            return;
        }
        if (en_ instanceof StaticBlock) {
            if (!processedBlocks.getVal((InitBlock)en_)) {
                processedBlocks.put((InitBlock)en_, true);
                CustomFoundBlock cust_ = new CustomFoundBlock(getGlobalClass(), getGlobalArgument(), (InitBlock)en_);
                _context.setCallingState(cust_);
                return;
            }
            en_.processBlock(_context);
            return;
        }
        if (en_ instanceof InstanceBlock) {
            en_.processBlock(_context);
            return;
        }
        setNullReadWrite();
    }

    public IdMap<InitBlock, Boolean> getProcessedBlocks() {
        return processedBlocks;
    }

    public void sucessClass(ContextEl _context) {
        String curClass_ = getGlobalClass();
        String curClassBase_ = Templates.getIdFromAllTypes(curClass_);
        _context.getClasses().getLocks().successClass(curClassBase_);
    }
}
