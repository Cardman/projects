package code.expressionlanguage;

import code.expressionlanguage.methods.AloneBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.BracedBlock;
import code.expressionlanguage.methods.FunctionBlock;
import code.expressionlanguage.methods.InstanceBlock;
import code.expressionlanguage.methods.Returnable;
import code.expressionlanguage.methods.StackableBlock;
import code.expressionlanguage.methods.StaticBlock;
import code.expressionlanguage.methods.WithEl;
import code.expressionlanguage.methods.util.ParentStackBlock;

public final class FieldInitPageEl extends AbstractPageEl {

    @Override
    public boolean checkCondition(ContextEl _context) {
        return true;
    }

    @Override
    public void tryProcessEl(ContextEl _context) {
        ReadWrite rw_ = getReadWrite();
        Block en_ = rw_.getBlock();
        if (en_ instanceof WithEl) {
            setCurrentBlock(en_);
            ((WithEl)en_).processEl(_context);
            return;
        }
        if (en_ instanceof Returnable) {
            en_.processBlock(_context);
            return;
        }
        if (en_ instanceof StaticBlock) {
            en_.processBlock(_context);
            return;
        }
        if (en_ instanceof InstanceBlock) {
            rw_.setBlock(en_.getFirstChild());
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
        } else if (_bl instanceof StackableBlock) {
            BracedBlock n_ = _bl.getParent();
            //n_ != null because strictly in class
            AbstractPageEl ip_ = _conf.getLastPage();
            Block root_ = ip_.getBlockRoot();
            if (!ip_.noBlock()) {
                parElt_ =  new ParentStackBlock(n_);
            } else if (n_ == root_) {
                //directly at the root => last element in the block root
                parElt_ = null;
            } else {
                Block next_ = n_.getNextSibling();
                if (next_ != null) {
                    parElt_ = new ParentStackBlock(null);
                } else {
                    parElt_ = null;
                }
            }
        } else {
            parElt_ = null;
        }
        return parElt_;
    }
    @Override
    public void postBlock(ContextEl _context) {
        setNullReadWrite();
    }

    @Override
    public void endRoot(ContextEl _context) {
        setNullReadWrite();
    }

    @Override
    public void postReturn(ContextEl _context) {
        Block bl_ = getCurrentBlock();
        FunctionBlock f_ = bl_.getFunction();
        if (!(f_ instanceof AloneBlock)) {
            setNullReadWrite();
            return;
        }
        Block bn_ = ((AloneBlock)f_).getNextSibling();
        ReadWrite rw_ = getReadWrite();
        if (bn_ != null) {
            rw_.setBlock(bn_);
            return;
        }
        setNullReadWrite();
    }

    @Override
    public void setReturnedArgument() {
    }

}
