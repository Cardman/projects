package code.formathtml;

import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.stacks.RendReadWrite;
import code.util.StringList;

public final class RendNullCatchEval extends RendAbstractCatchEval {
    RendNullCatchEval(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont,RendDocumentBlock _doc) {
        RendBlock pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof RendAbstractCatchEval)) {
            if (!(pBlock_ instanceof RendTryEval)) {
                if (!(pBlock_ instanceof RendPossibleEmpty)) {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(_cont.getCurrentFileName());
                    un_.setIndexFile(getOffset().getOffsetTrim());
                    un_.buildError(_cont.getContext().getAnalysisMessages().getUnexpectedCatchElseFinally(),
                            _cont.getContext().getKeyWords().getKeyWordCatch(),
                            StringList.join(
                                    new StringList(
                                            _cont.getContext().getKeyWords().getKeyWordCatch(),
                                            _cont.getContext().getKeyWords().getKeyWordTry()
                                    ),
                                    OR_ERR));
                    _cont.addError(un_);
                } else if (!(pBlock_.getPreviousSibling() instanceof RendAbstractCatchEval)) {
                    if (!(pBlock_.getPreviousSibling() instanceof RendTryEval)) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(_cont.getCurrentFileName());
                        un_.setIndexFile(getOffset().getOffsetTrim());
                        un_.buildError(_cont.getContext().getAnalysisMessages().getUnexpectedCatchElseFinally(),
                                _cont.getContext().getKeyWords().getKeyWordCatch(),
                                StringList.join(
                                        new StringList(
                                                _cont.getContext().getKeyWords().getKeyWordCatch(),
                                                _cont.getContext().getKeyWords().getKeyWordTry()
                                        ),
                                        OR_ERR));
                        _cont.addError(un_);
                    }
                }
            }
        }
    }
}
