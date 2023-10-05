package code.expressionlanguage.analyze.accessing;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AccessingImportingBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.util.ContextUtil;


public final class TypeAccessor implements AccessingImportingBlock {
    private final RootBlock fullName;

    public TypeAccessor(RootBlock _fullName) {
        this.fullName = _fullName;
    }

    @Override
    public boolean isTypeHidden(AnalyzedPageEl _page, Accessed _class) {
        return !ContextUtil.canAccessType(_page,fullName, _class);
    }

}
