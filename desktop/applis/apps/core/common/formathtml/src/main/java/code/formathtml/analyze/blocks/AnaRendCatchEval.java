package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetBooleanInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.formathtml.analyze.AnalyzingDoc;

public final class AnaRendCatchEval extends AnaRendAbstractCatchEval implements WithRendFilterContent {

    private final RendFilterContent filterContent;

    private final boolean throwIfGuardError;
    private final boolean catchAll;
    AnaRendCatchEval(OffsetStringInfo _className, OffsetStringInfo _variable, OffsetStringInfo _value, OffsetStringInfo _condition, OffsetBooleanInfo _thr, int _offset, boolean _ca) {
        super(_offset);
        filterContent = new RendFilterContent(_className,_variable,_value,_condition);
        throwIfGuardError = _thr.isInfo();
        catchAll = _ca;
    }

    @Override
    public RendFilterContent getFilterContent() {
        return filterContent;
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        filterContent.setKeyWord(_page.getKeyWords().getKeyWordCatch());
        filterContent.setKeyWordContainer(_page.getKeyWords().getKeyWordTry());
        if (catchAll && getNextSibling() instanceof AnaRendCatchEval) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(getOffset());
            //key word len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedDefDup(),
                    _page.getKeyWords().getKeyWordCatch(),
                    _page.getKeyWords().getKeyWordTry());
            AnalyzingDoc.addError(un_, _page);
        }
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

    public boolean isCatchAll() {
        return catchAll;
    }
}
