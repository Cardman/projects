package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetBooleanInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;

public final class CatchEval extends AbstractCatchEval implements BuildableElMethod, WithFilterContent {

    private final FilterContent filterContent;

    private final boolean throwIfGuardError;
    private final boolean catchAll;
    public CatchEval(OffsetStringInfo _value, int _offset, String _declaringType, OffsetStringInfo _variable, OffsetStringInfo _condition, OffsetBooleanInfo _thr, boolean _ca) {
        super(_offset);
        filterContent = new FilterContent(_value, _declaringType, _variable, _condition);
        throwIfGuardError = _thr.isInfo();
        catchAll = _ca;
    }

    public FilterContent getFilterContent() {
        return filterContent;
    }

    public int getClassNameOffset() {
        return filterContent.getValueOffset();
    }
    public int getVariableNameOffset() {
        return filterContent.getVariableOffset();
    }
    public String getClassName() {
        return filterContent.getDeclaringType();
    }

    public String getVariableName() {
        return filterContent.getVariableName();
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        filterContent.setKeyWord(_page.getKeyWords().getKeyWordCatch());
        filterContent.setKeyWordContainer(_page.getKeyWords().getKeyWordTry());
        if (catchAll && getNextSibling() instanceof AbstractCatchEval) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(getFile());
            un_.setIndexFile(getOffset());
            //key word len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedDefDup(),
                    _page.getKeyWords().getKeyWordCatch(),
                    _page.getKeyWords().getKeyWordTry());
            _page.addLocError(un_);
            addErrorBlock(un_.getBuiltError());
        }
        filterContent.buildExpressionLanguageReadOnly(this,_page,true,"");
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
