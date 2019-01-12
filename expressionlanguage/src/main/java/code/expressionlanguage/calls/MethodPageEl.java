package code.expressionlanguage.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.BracedBlock;
import code.expressionlanguage.methods.WithEl;
import code.expressionlanguage.methods.util.ParentStackBlock;

public final class MethodPageEl extends AbstractPageEl implements ForwardPageEl,ReturnablePageEl,WithElPageEl {

    private Argument returnedArgument;

    public MethodPageEl(ContextEl _context) {
        Block root_ = getBlockRoot();
        Argument global_ = getGlobalArgument();
        returnedArgument = PrimitiveTypeUtil.defaultValue(root_, global_, _context);
    }
    public Argument getReturnedArgument() {
        return returnedArgument;
    }
    @Override
    public void setReturnedArgument(Argument _returnedArgument) {
        returnedArgument = _returnedArgument;
    }

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
        endRoot();
    }
    @Override
    public ParentStackBlock getNextBlock(Block _bl) {
        ParentStackBlock parElt_;
        Block nextSibling_ = _bl.getNextSibling();
        if (nextSibling_ != null) {
            parElt_ = new ParentStackBlock(null);
        } else {
            BracedBlock n_ = _bl.getParent();
            //n_ != null because strictly in class
            if (hasBlock()) {
                parElt_ =  new ParentStackBlock(n_);
            } else {
                //directly at the root => last element in the block root
                parElt_ = null;
            }
        }
        return parElt_;
    }
    @Override
    public void postBlock(ContextEl _context) {
        setNullReadWrite();
    }

    @Override
    public void postReturn() {
        setNullReadWrite();
    }

    public void endRoot() {
        setNullReadWrite();
    }

    @Override
    public boolean forwardTo(AbstractPageEl _page, ContextEl _context) {
        Argument a_ = getReturnedArgument();
        return _page.receive(a_, _context);
    }

}
