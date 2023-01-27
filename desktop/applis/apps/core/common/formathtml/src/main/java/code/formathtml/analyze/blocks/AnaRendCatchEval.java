package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetBooleanInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.formathtml.analyze.AnalyzingDoc;

public final class AnaRendCatchEval extends AnaRendAbstractCatchEval implements WithRendFilterContent {

    private final RendFilterContent filterContent;

    private final boolean throwIfGuardError;
    AnaRendCatchEval(OffsetStringInfo _className, OffsetStringInfo _variable, OffsetStringInfo _value, OffsetBooleanInfo _thr, int _offset) {
        super(_offset);
        filterContent = new RendFilterContent(_className,_variable,_value);
        throwIfGuardError = _thr.isInfo();
    }

    @Override
    public RendFilterContent getFilterContent() {
        return filterContent;
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        filterContent.setKeyWord(_page.getKeyWords().getKeyWordCatch());
        filterContent.setKeyWordContainer(_page.getKeyWords().getKeyWordTry());
        filterContent.buildExpressionLanguage(this,_anaDoc,_page,new AnaClassArgumentMatching(""),true);
        checkTreeTry(_page, _page.getKeyWords().getKeyWordCatch());
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        filterContent.removeAllVars(_ip);
    }

    public boolean isThrowIfGuardError() {
        return throwIfGuardError;
    }
}
