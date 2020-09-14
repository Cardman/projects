package code.formathtml;

import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.stacks.RendIfStack;
import code.util.StringList;

public final class RendElseCondition extends RendParentBlock implements RendWithEl, RendReducableOperations, RendBuildableElMethod,RendBreakableBlock {

    RendElseCondition(OffsetsBlock _offset) {
        super(_offset);
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
    public void buildExpressionLanguage(Configuration _cont,RendDocumentBlock _doc) {
        RendBlock pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof RendIfCondition)) {
            if (!(pBlock_ instanceof RendElseIfCondition)) {
                if (!(pBlock_ instanceof RendPossibleEmpty)) {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(_cont.getAnalyzingDoc().getFileName());
                    un_.setIndexFile(getOffset().getOffsetTrim());
                    un_.buildError(_cont.getContext().getAnalyzing().getAnalysisMessages().getUnexpectedCatchElseFinally(),
                            _cont.getContext().getAnalyzing().getKeyWords().getKeyWordElse(),
                            StringList.join(
                                    new StringList(
                                            _cont.getContext().getAnalyzing().getKeyWords().getKeyWordIf(),
                                            _cont.getContext().getAnalyzing().getKeyWords().getKeyWordElseif()
                                    ),
                                    OR_ERR));
                    Configuration.addError(un_, _cont.getAnalyzingDoc(), _cont.getContext().getAnalyzing());
                } else if (!(pBlock_.getPreviousSibling() instanceof RendIfCondition)){
                    if (!(pBlock_.getPreviousSibling() instanceof RendElseIfCondition)){
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(_cont.getAnalyzingDoc().getFileName());
                        un_.setIndexFile(getOffset().getOffsetTrim());
                        un_.buildError(_cont.getContext().getAnalyzing().getAnalysisMessages().getUnexpectedCatchElseFinally(),
                                _cont.getContext().getAnalyzing().getKeyWords().getKeyWordElse(),
                                StringList.join(
                                        new StringList(
                                                _cont.getContext().getAnalyzing().getKeyWords().getKeyWordIf(),
                                                _cont.getContext().getAnalyzing().getKeyWords().getKeyWordElseif()
                                        ),
                                        OR_ERR));
                        Configuration.addError(un_, _cont.getAnalyzingDoc(), _cont.getContext().getAnalyzing());
                    }
                }
            }
        }
    }

    @Override
    public void reduce(Configuration _context) {

    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendIfStack if_ = (RendIfStack) ip_.getRendLastStack();
        if_.setCurrentVisitedBlock(this);
        if (!if_.isEntered()) {
            if_.setEntered(true);
            ip_.getRendReadWrite().setRead(getFirstChild());
            return;
        }
        processBlockAndRemove(_cont);
    }

}
