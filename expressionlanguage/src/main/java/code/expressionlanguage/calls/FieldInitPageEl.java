package code.expressionlanguage.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.util.CustomFoundBlock;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.methods.util.ParentStackBlock;
import code.util.IdMap;

public final class FieldInitPageEl extends AbstractPageEl implements WithElPageEl {

    private IdMap<InitBlock, Boolean> processedBlocks = new IdMap<InitBlock, Boolean>();

    @Override
    public boolean checkCondition(ContextEl _context) {
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
            en_.processBlock(_context);
            return;
        }
        if (en_ instanceof InstanceBlock) {
            if (!processedBlocks.getVal((InitBlock)en_)) {
                processedBlocks.put((InitBlock)en_, true);
                CustomFoundBlock cust_ = new CustomFoundBlock(getGlobalClass(), getGlobalArgument(), (InitBlock)en_);
                _context.setFoundBlock(cust_);
                return;
            }
            en_.processBlock(_context);
            return;
        }
        endRoot();
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
        setNullReadWrite();
    }

    private void endRoot() {
        setNullReadWrite();
    }

    public IdMap<InitBlock, Boolean> getProcessedBlocks() {
        return processedBlocks;
    }

}
