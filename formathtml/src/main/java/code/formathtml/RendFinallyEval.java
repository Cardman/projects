package code.formathtml;

import code.expressionlanguage.errors.custom.UnexpectedTagName;
import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.util.RendReadWrite;
import code.formathtml.util.RendTryBlockStack;

public final class RendFinallyEval extends RendParentBlock implements RendEval {
    RendFinallyEval(OffsetsBlock _offset) {
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
    public void buildExpressionLanguage(Configuration _cont,RendDocumentBlock _doc) {
        RendBlock pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof RendAbstractCatchEval)) {
            if (!(pBlock_ instanceof RendTryEval)) {
                if (!(pBlock_ instanceof RendPossibleEmpty)) {
                    UnexpectedTagName un_ = new UnexpectedTagName();
                    un_.setFileName(_cont.getCurrentFileName());
                    un_.setIndexFile(getOffset().getOffsetTrim());
                    _cont.getClasses().addError(un_);
                } else if (!(pBlock_.getPreviousSibling() instanceof RendAbstractCatchEval)) {
                    if (!(pBlock_.getPreviousSibling() instanceof RendTryEval)) {
                        UnexpectedTagName un_ = new UnexpectedTagName();
                        un_.setFileName(_cont.getCurrentFileName());
                        un_.setIndexFile(getOffset().getOffsetTrim());
                        _cont.getClasses().addError(un_);
                    }
                }
            }
        }
    }

    @Override
    public void processToFinally(ImportingPage _ip, RendTryBlockStack _stack) {
        removeLocalVars(_ip);
        _ip.removeRendLastBlock();
    }


    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendTryBlockStack ts_ = (RendTryBlockStack) ip_.getRendLastStack();
        ts_.setCurrentBlock(this);
        if (ts_.isVisitedFinally()) {
            ip_.removeRendLastBlock();
            processBlock(_cont);
            return;
        }
        ts_.setVisitedFinally(true);
        ip_.getRendReadWrite().setRead(getFirstChild());
    }

    @Override
    public void exitStack(Configuration _context) {
        ImportingPage ip_ = _context.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendTryBlockStack tryStack_ = (RendTryBlockStack) ip_.getRendLastStack();
        RendCallingFinally call_ = tryStack_.getCalling();
        if (call_ != null) {
            ip_.removeRendLastBlock();
            if (call_ instanceof RendLocalThrowing) {
                _context.setException(tryStack_.getException());
            }
            call_.removeBlockFinally(_context);
            return;
        }
        rw_.setRead(this);
    }
}
