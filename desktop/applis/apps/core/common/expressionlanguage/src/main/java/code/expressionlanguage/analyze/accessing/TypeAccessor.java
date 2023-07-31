package code.expressionlanguage.analyze.accessing;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.blocks.AccessingImportingBlock;
import code.expressionlanguage.common.StringExpUtil;


public final class TypeAccessor implements AccessingImportingBlock {
    private final String fullName;

    public TypeAccessor(String _fullName) {
        this.fullName = _fullName;
    }

    @Override
    public boolean isTypeHidden(Accessed _class, AnalyzedPageEl _analyzable) {
        return !ContextUtil.canAccessType(_analyzable.getAnaClassBody(StringExpUtil.getIdFromAllTypes(fullName)), _class);
    }

}
