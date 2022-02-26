package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.formathtml.analyze.AnalyzingDoc;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AnaRendFinallyEval extends AnaRendParentBlock implements AnaRendEval {
    AnaRendFinallyEval(int _offset) {
        super(_offset);
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        AnaRendBlock pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof AnaRendAbstractCatchEval)) {
            if (!(pBlock_ instanceof AnaRendTryEval)) {
                if (!isPossibleEmpty(pBlock_)) {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFile(_page.getCurrentFile());
                    un_.setIndexFile(getOffset());
                    un_.buildError(_page.getAnalysisMessages().getUnexpectedCatchElseFinally(),
                            _page.getKeyWords().getKeyWordFinally(),
                            StringUtil.join(
                                    new StringList(
                                            _page.getKeyWords().getKeyWordCatch(),
                                            _page.getKeyWords().getKeyWordTry()
                                    ),
                                    OR_ERR));
                    AnalyzingDoc.addError(un_, _page);
                } else if (!(pBlock_.getPreviousSibling() instanceof AnaRendAbstractCatchEval)) {
                    if (!(pBlock_.getPreviousSibling() instanceof AnaRendTryEval)) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFile(_page.getCurrentFile());
                        un_.setIndexFile(getOffset());
                        un_.buildError(_page.getAnalysisMessages().getUnexpectedCatchElseFinally(),
                                _page.getKeyWords().getKeyWordFinally(),
                                StringUtil.join(
                                        new StringList(
                                                _page.getKeyWords().getKeyWordCatch(),
                                                _page.getKeyWords().getKeyWordTry()
                                        ),
                                        OR_ERR));
                        AnalyzingDoc.addError(un_, _page);
                    }
                }
            }
        }
    }

    public String getRealLabel() {
        AnaRendBlock p_ = getPreviousSibling();
        while (!(p_ instanceof AnaRendTryEval)) {
            if (p_ == null) {
                return EMPTY_STRING;
            }
            p_ = p_.getPreviousSibling();
        }
        return ((AnaRendTryEval)p_).getLabel();
    }
}
