package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.formathtml.analyze.AnalyzingDoc;

public final class AnaRendCatchEval extends AnaRendAbstractCatchEval implements WithRendFilterContent {

    private final RendFilterContent filterContent;

    AnaRendCatchEval(OffsetStringInfo _className, OffsetStringInfo _variable, OffsetStringInfo _value, int _offset) {
        super(_offset);
        filterContent = new RendFilterContent(_className,_variable,_value);
    }

    @Override
    public RendFilterContent getFilterContent() {
        return filterContent;
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        filterContent.buildExpressionLanguage(this,_anaDoc,_page,new AnaClassArgumentMatching(""),true);
        checkTreeTry(_page, _page.getKeyWords().getKeyWordCatch());
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        filterContent.removeAllVars(_ip);
    }

}
