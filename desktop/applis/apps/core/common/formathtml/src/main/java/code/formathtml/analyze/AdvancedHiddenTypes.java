package code.formathtml.analyze;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.blocks.AccessingImportingBlock;
import code.expressionlanguage.analyze.AbstractHiddenTypes;

public final class AdvancedHiddenTypes implements AbstractHiddenTypes {
    private final AnalyzedPageEl configuration;

    public AdvancedHiddenTypes(AnalyzedPageEl _configuration) {
        this.configuration = _configuration;
    }

    @Override
    public boolean isHidden(AccessingImportingBlock _global, RootBlock _type) {
        if (_global == null) {
            return false;
        }
        Accessed a_ = new Accessed(_type.getAccess(), _type.getPackageName(), _type.getParentType(), _type);
        return _global.isTypeHidden(a_, configuration);
    }
}
