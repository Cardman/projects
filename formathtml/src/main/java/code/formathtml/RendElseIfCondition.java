package code.formathtml;

import code.expressionlanguage.errors.custom.UnexpectedTagName;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.util.RendIfStack;
import code.formathtml.util.RendReadWrite;

public final class RendElseIfCondition extends RendCondition implements RendBreakableBlock {
    RendElseIfCondition(OffsetStringInfo _condition, OffsetsBlock _offset) {
        super(_condition, _offset);
    }

    @Override
    public String getRealLabel() {
        RendBlock p_ = getPreviousSibling();
        while (!(p_ instanceof RendIfCondition)) {
            if (p_ == null) {
                return EMPTY_STRING;
            }
            p_ = p_.getPreviousSibling();
        }
        return ((RendIfCondition)p_).getLabel();
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc) {
        super.buildExpressionLanguage(_cont, _doc);
        RendBlock pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof RendIfCondition)) {
            if (!(pBlock_ instanceof RendElseIfCondition)) {
                if (!(pBlock_ instanceof RendPossibleEmpty)) {
                    UnexpectedTagName un_ = new UnexpectedTagName();
//                un_.setFileName(getFile().getFileName());
                    un_.setIndexFile(getOffset().getOffsetTrim());
                    _cont.getClasses().addError(un_);
                } else if (!(pBlock_.getPreviousSibling() instanceof RendIfCondition)){
                    if (!(pBlock_.getPreviousSibling() instanceof RendElseIfCondition)){
                        UnexpectedTagName un_ = new UnexpectedTagName();
//                un_.setFileName(getFile().getFileName());
                        un_.setIndexFile(getOffset().getOffsetTrim());
                        _cont.getClasses().addError(un_);
                    }
                }
            }
        }
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendIfStack if_ = (RendIfStack) ip_.getRendLastStack();
        if_.setCurentVisitedBlock(this);
        if (!if_.isEntered()) {
            Boolean assert_ = evaluateCondition(_cont);
            if (assert_ == null) {
                return;
            }
            if (assert_) {
                if_.setEntered(true);
                rw_.setRead(getFirstChild());
                return;
            }
        }
        if (if_.getLastBlock() == this) {
            ip_.removeRendLastBlock();
            processBlock(_cont);
            return;
        }
        rw_.setRead(getNextSibling());
    }

    @Override
    public void exitStack(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendIfStack if_ = (RendIfStack) ip_.getRendLastStack();
        if (if_.getLastBlock() == this) {
            rw_.setRead(this);
        } else {
            rw_.setRead(getNextSibling());
        }
    }
}
