package code.formathtml;

import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.util.AnalyzingDoc;
import code.util.StringList;

public final class RendNullCatchEval extends RendAbstractCatchEval {
    RendNullCatchEval(OffsetsBlock _offset) {
        super(_offset);
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
                            _cont.getContext().getAnalyzing().getKeyWords().getKeyWordCatch(),
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
                                _cont.getContext().getAnalyzing().getKeyWords().getKeyWordCatch(),
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
}
