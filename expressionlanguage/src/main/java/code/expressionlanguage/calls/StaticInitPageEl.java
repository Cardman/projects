package code.expressionlanguage.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.Templates;
import code.expressionlanguage.calls.util.CustomFoundBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.InitBlock;
import code.expressionlanguage.methods.InstanceBlock;
import code.expressionlanguage.methods.Returnable;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.StaticBlock;
import code.expressionlanguage.methods.UniqueRootedBlock;
import code.expressionlanguage.methods.WithEl;
import code.expressionlanguage.methods.util.ParentStackBlock;
import code.expressionlanguage.opers.InvokingOperation;
import code.util.IdMap;

public final class StaticInitPageEl extends AbstractPageEl {

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
                if (InvokingOperation.hasToExit(_context, superClass_)) {
                    return false;
                }
            }
        }
        for (String i: root_.getStaticInitImportedInterfaces()) {
            if (InvokingOperation.hasToExit(_context, i)) {
                return false;
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
                _context.setFoundBlock(cust_);
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
    public ParentStackBlock getNextBlock(Block _bl,ContextEl _conf) {
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
        _context.getClasses().getLocks().successClass(_context, curClassBase_);
        setNullReadWrite();
    }

    @Override
    public void endRoot(ContextEl _context) {
        Classes classes_ = _context.getClasses();
        String curClass_ = getGlobalClass();
        String curClassBase_ = Templates.getIdFromAllTypes(curClass_);
        classes_.getLocks().successClass(_context, curClassBase_);
        setNullReadWrite();
    }

    public IdMap<InitBlock, Boolean> getProcessedBlocks() {
        return processedBlocks;
    }
}
