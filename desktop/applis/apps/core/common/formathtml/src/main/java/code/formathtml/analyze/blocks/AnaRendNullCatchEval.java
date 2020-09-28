package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.formathtml.Configuration;
import code.formathtml.analyze.AnalyzingDoc;
import code.util.StringList;

public final class AnaRendNullCatchEval extends AnaRendAbstractCatchEval {
    AnaRendNullCatchEval(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        AnaRendBlock pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof AnaRendAbstractCatchEval)) {
            if (!(pBlock_ instanceof AnaRendTryEval)) {
                if (!(pBlock_ instanceof AnaRendPossibleEmpty)) {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(_anaDoc.getFileName());
                    un_.setIndexFile(getOffset().getOffsetTrim());
                    un_.buildError(_page.getAnalysisMessages().getUnexpectedCatchElseFinally(),
                            _page.getKeyWords().getKeyWordCatch(),
                            StringList.join(
                                    new StringList(
                                            _page.getKeyWords().getKeyWordCatch(),
                                            _page.getKeyWords().getKeyWordTry()
                                    ),
                                    OR_ERR));
                    AnalyzingDoc.addError(un_, _anaDoc, _page);
                } else if (!(pBlock_.getPreviousSibling() instanceof AnaRendAbstractCatchEval)) {
                    if (!(pBlock_.getPreviousSibling() instanceof AnaRendTryEval)) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(_anaDoc.getFileName());
                        un_.setIndexFile(getOffset().getOffsetTrim());
                        un_.buildError(_page.getAnalysisMessages().getUnexpectedCatchElseFinally(),
                                _page.getKeyWords().getKeyWordCatch(),
                                StringList.join(
                                        new StringList(
                                                _page.getKeyWords().getKeyWordCatch(),
                                                _page.getKeyWords().getKeyWordTry()
                                        ),
                                        OR_ERR));
                        AnalyzingDoc.addError(un_, _anaDoc, _page);
                    }
                }
            }
        }
    }
}
