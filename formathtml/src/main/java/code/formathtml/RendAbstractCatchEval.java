package code.formathtml;

import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.util.RendReadWrite;
import code.formathtml.util.RendTryBlockStack;

public abstract class RendAbstractCatchEval extends RendParentBlock implements RendEval {
    RendAbstractCatchEval(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public String getRealLabel() {
        RendBlock p_ = getPreviousSibling();
        while (!(p_ instanceof RendTryEval)) {
            if (p_ == null) {
                return EMPTY_STRING;
            }
            p_ = p_.getPreviousSibling();
        }
        return ((RendTryEval)p_).getLabel();
    }

    @Override
    public final void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendTryBlockStack ts_ = (RendTryBlockStack) ip_.getRendLastStack();
        ts_.setException(NullStruct.NULL_VALUE);
        if (ts_.getLastBlock() == this) {
            ip_.removeRendLastBlock();
            processBlock(_cont);
        } else {
            ts_.setCurrentBlock(this);
            rw_.setRead(getNextSibling());
        }
    }

    @Override
    public void processToFinally(ImportingPage _ip, RendTryBlockStack _stack) {
        removeLocalVars(_ip);
        RendParentBlock br_ = _stack.getLastBlock();
        if (br_ instanceof RendFinallyEval) {
            _ip.getRendReadWrite().setRead(br_);
            _ip.setFinallyToProcess(true);
            return;
        }
        _ip.removeRendLastBlock();
    }
}
