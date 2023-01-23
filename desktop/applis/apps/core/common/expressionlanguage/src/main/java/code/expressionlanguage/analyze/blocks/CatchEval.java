package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetStringInfo;

public final class CatchEval extends AbstractCatchEval implements BuildableElMethod, WithFilterContent {

    private final FilterContent filterContent;

    public CatchEval(OffsetStringInfo _value, int _offset, String _declaringType, OffsetStringInfo _variable, OffsetStringInfo _condition) {
        super(_offset);
        filterContent = new FilterContent(_value, _declaringType, _variable, _condition);
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
        filterContent.buildExpressionLanguageReadOnly(this,_page,true,"");
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        filterContent.removeAllVars(_ip);
    }

}
