package code.expressionlanguage.analyze.accessing;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.blocks.AccessibleBlock;
import code.expressionlanguage.analyze.blocks.AccessingImportingBlock;


public final class TypeAccessor implements AccessingImportingBlock {
    private final String fullName;

    public TypeAccessor(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean isTypeHidden(AccessibleBlock _class, AnalyzedPageEl _analyzable) {
        return !ContextUtil.canAccessType(fullName, _class, _analyzable);
    }

}
