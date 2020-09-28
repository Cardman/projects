package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.blocks.ExecAccessingImportingBlock;

public final class DefaultHiddenTypes implements AbstractHiddenTypes {
    private final AnalyzedPageEl context;

    public DefaultHiddenTypes(AnalyzedPageEl context) {
        this.context = context;
    }

    @Override
    public boolean isHidden(ExecAccessingImportingBlock _global, RootBlock _type) {
        Accessed a_ = new Accessed(_type.getAccess(), _type.getPackageName(), _type.getParentFullName(), _type.getFullName(), _type.getOuterFullName());
        return _global.isTypeHidden(a_, context);
    }
}
