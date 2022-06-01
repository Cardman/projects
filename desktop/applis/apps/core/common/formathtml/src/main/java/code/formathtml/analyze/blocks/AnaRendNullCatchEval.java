package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.formathtml.analyze.AnalyzingDoc;

public final class AnaRendNullCatchEval extends AnaRendAbstractCatchEval {
    AnaRendNullCatchEval(int _offset) {
        super(_offset);
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        checkTreeTry(_page, _page.getKeyWords().getKeyWordCatch());
    }
}
