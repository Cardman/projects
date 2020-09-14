package code.formathtml;

import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.stacks.RendAbruptCallingFinally;
import code.formathtml.stacks.RendExceptionCallingFinally;
import code.formathtml.stacks.RendTryBlockStack;
import code.formathtml.util.AnalyzingDoc;
import code.util.StringList;

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
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc, AnalyzingDoc _anaDoc) {
        RendBlock pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof RendAbstractCatchEval)) {
            if (!(pBlock_ instanceof RendTryEval)) {
                if (!(pBlock_ instanceof RendPossibleEmpty)) {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(_anaDoc.getFileName());
                    un_.setIndexFile(getOffset().getOffsetTrim());
                    un_.buildError(_cont.getContext().getAnalyzing().getAnalysisMessages().getUnexpectedCatchElseFinally(),
                            _cont.getContext().getAnalyzing().getKeyWords().getKeyWordFinally(),
                            StringList.join(
                                    new StringList(
                                            _cont.getContext().getAnalyzing().getKeyWords().getKeyWordCatch(),
                                            _cont.getContext().getAnalyzing().getKeyWords().getKeyWordTry()
                                    ),
                                    OR_ERR));
                    Configuration.addError(un_, _anaDoc, _cont.getContext().getAnalyzing());
                } else if (!(pBlock_.getPreviousSibling() instanceof RendAbstractCatchEval)) {
                    if (!(pBlock_.getPreviousSibling() instanceof RendTryEval)) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(_anaDoc.getFileName());
                        un_.setIndexFile(getOffset().getOffsetTrim());
                        un_.buildError(_cont.getContext().getAnalyzing().getAnalysisMessages().getUnexpectedCatchElseFinally(),
                                _cont.getContext().getAnalyzing().getKeyWords().getKeyWordFinally(),
                                StringList.join(
                                        new StringList(
                                                _cont.getContext().getAnalyzing().getKeyWords().getKeyWordCatch(),
                                                _cont.getContext().getAnalyzing().getKeyWords().getKeyWordTry()
                                        ),
                                        OR_ERR));
                        Configuration.addError(un_, _anaDoc, _cont.getContext().getAnalyzing());
                    }
                }
            }
        }
    }


    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendTryBlockStack ts_ = (RendTryBlockStack) ip_.getRendLastStack();
        ts_.setCurrentVisitedBlock(this);
        if (ts_.isVisitedFinally()) {
            processBlockAndRemove(_cont);
            return;
        }
        ts_.setVisitedFinally(true);
        ip_.getRendReadWrite().setRead(getFirstChild());
    }

    @Override
    public void exitStack(Configuration _context) {
        ImportingPage ip_ = _context.getLastPage();
        RendTryBlockStack tryStack_ = (RendTryBlockStack) ip_.getRendLastStack();
        RendAbruptCallingFinally call_ = tryStack_.getCalling();
        if (call_ != null) {
            RendCallingFinally callingFinally_ = call_.getCallingFinally();
            if (call_ instanceof RendExceptionCallingFinally) {
                _context.setException(((RendExceptionCallingFinally)call_).getException());
            }
            callingFinally_.removeBlockFinally(_context);
        }
    }
}
