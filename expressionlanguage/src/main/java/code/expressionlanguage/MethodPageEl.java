package code.expressionlanguage;

import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.BracedBlock;
import code.expressionlanguage.methods.StackableBlock;
import code.expressionlanguage.methods.WithEl;
import code.expressionlanguage.methods.util.ParentStackBlock;

public final class MethodPageEl extends AbstractPageEl {

    @Override
    public boolean checkCondition(ContextEl _context) {
        return true;
    }

    @Override
    public void setReturnedArgument() {
        Argument void_ = Argument.createVoid();
        setReturnedArgument(void_);
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
            if (!noBlock()) {
                parElt_ =  new ParentStackBlock(n_);
            } else {
                //directly at the root => last element in the block root
                parElt_ = null;
            }
        } else {
            parElt_ = null;
        }
        return parElt_;
    }
    @Override
    public void postBlock(ContextEl _context) {
        Block root_ = getBlockRoot();
        Argument global_ = getGlobalArgument();
        Argument arg_ = PrimitiveTypeUtil.defaultValue(root_, global_, _context);
        setReturnedArgument(arg_);
        setNullReadWrite();
    }

    @Override
    public void postReturn(ContextEl _context) {
        setNullReadWrite();
    }

    @Override
    public void endRoot(ContextEl _context) {
        Block root_ = getBlockRoot();
        setReturnedArgument(PrimitiveTypeUtil.defaultValue(root_, getGlobalArgument(), _context));
        setNullReadWrite();
    }

}
