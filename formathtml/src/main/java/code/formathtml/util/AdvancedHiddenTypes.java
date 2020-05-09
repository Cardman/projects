package code.formathtml.util;

import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.types.AbstractHiddenTypes;
import code.formathtml.Configuration;

public final class AdvancedHiddenTypes implements AbstractHiddenTypes {
    private final Configuration configuration;

    public AdvancedHiddenTypes(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public boolean isHidden(AccessingImportingBlock _global, RootBlock _type) {
        if (_global == null) {
            return false;
        }
        return _global.isTypeHidden(_type, configuration);
    }
}
