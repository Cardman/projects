package code.expressionlanguage.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.util.CustomFoundBlock;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.methods.util.ParentStackBlock;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.util.IdMap;

public final class StaticInitPageEl extends AbstractPageEl implements WithElPageEl {

    private IdMap<InitBlock, Boolean> processedBlocks = new IdMap<InitBlock, Boolean>();

    @Override
    public boolean checkCondition(ContextEl _context) {
        Classes classes_ = _context.getClasses();

        String curClass_ = getGlobalClass();
        String curClassBase_ = Templates.getIdFromAllTypes(curClass_);
        RootBlock root_ =  classes_.getClassBody(curClassBase_);
        if (root_ instanceof UniqueRootedBlock) {
            String gene_ = ((UniqueRootedBlock) root_).getImportedDirectGenericSuperClass();
            String superClass_ = Templates.getIdFromAllTypes(gene_);
            if (classes_.getClassBody(superClass_) != null) {
                if (ExecInvokingOperation.hasToExit(_context, superClass_)) {
                    return false;
                }
            }
            for (String i: root_.getStaticInitImportedInterfaces()) {
                if (ExecInvokingOperation.hasToExit(_context, i)) {
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public void tryProcessEl(ContextEl _context) {
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
        endRoot(_context);
    }
    @Override
    public ParentStackBlock getNextBlock(Block _bl) {
        ParentStackBlock parElt_;
        Block nextSibling_ = _bl.getNextSibling();
        if (nextSibling_ != null) {
            parElt_ = new ParentStackBlock(null);
        } else {
            parElt_ = null;
        }
        return parElt_;
    }

    @Override
    public void postBlock(ContextEl _context) {
        String curClass_ = getGlobalClass();
        String curClassBase_ = Templates.getIdFromAllTypes(curClass_);
        _context.getClasses().getLocks().successClass(curClassBase_);
        setNullReadWrite();
    }

    private void endRoot(ContextEl _context) {
        Classes classes_ = _context.getClasses();
        String curClass_ = getGlobalClass();
        String curClassBase_ = Templates.getIdFromAllTypes(curClass_);
        classes_.getLocks().successClass(curClassBase_);
        setNullReadWrite();
    }

    public IdMap<InitBlock, Boolean> getProcessedBlocks() {
        return processedBlocks;
    }
}
