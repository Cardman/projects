package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.formathtml.analyze.AnalyzingDoc;

public final class AnaRendFinallyEval extends AnaRendParentBlock implements AnaRendBreakableBlock {
    AnaRendFinallyEval(int _offset) {
        super(_offset);
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        checkTreeTry(_page,_page.getKeyWords().getKeyWordFinally());
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
