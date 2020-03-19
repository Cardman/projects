package code.formathtml;

import code.expressionlanguage.ConditionReturn;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.stacks.RendIfStack;
import code.formathtml.stacks.RendReadWrite;
import code.util.StringList;

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
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(_cont.getCurrentFileName());
                    un_.setIndexFile(getOffset().getOffsetTrim());
                    un_.buildError(_cont.getContext().getAnalysisMessages().getUnexpectedCatchElseFinally(),
                            _cont.getContext().getKeyWords().getKeyWordElseif(),
                            StringList.join(
                                    new StringList(
                                            _cont.getContext().getKeyWords().getKeyWordIf(),
                                            _cont.getContext().getKeyWords().getKeyWordElseif()
                                    ),
                                    OR_ERR));
                    _cont.addError(un_);
                } else if (!(pBlock_.getPreviousSibling() instanceof RendIfCondition)){
                    if (!(pBlock_.getPreviousSibling() instanceof RendElseIfCondition)){
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(_cont.getCurrentFileName());
                        un_.setIndexFile(getOffset().getOffsetTrim());
                        un_.buildError(_cont.getContext().getAnalysisMessages().getUnexpectedCatchElseFinally(),
                                _cont.getContext().getKeyWords().getKeyWordElseif(),
                                StringList.join(
                                        new StringList(
                                                _cont.getContext().getKeyWords().getKeyWordIf(),
                                                _cont.getContext().getKeyWords().getKeyWordElseif()
                                        ),
                                        OR_ERR));
                        _cont.addError(un_);
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
        if_.setCurrentVisitedBlock(this);
        if (!if_.isEntered()) {
            ConditionReturn assert_ = evaluateCondition(_cont);
            if (assert_ == ConditionReturn.CALL_EX) {
                return;
            }
            if (assert_ == ConditionReturn.YES) {
                if_.setEntered(true);
                rw_.setRead(getFirstChild());
                return;
            }
        }
        if (if_.getLastBlock() == this) {
            processBlockAndRemove(_cont);
            return;
        }
        rw_.setRead(getNextSibling());
    }

    @Override
    public void exitStack(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendIfStack if_ = (RendIfStack) ip_.getRendLastStack();
        if (if_.getLastBlock() != this) {
            rw_.setRead(getNextSibling());
        }
    }
}
