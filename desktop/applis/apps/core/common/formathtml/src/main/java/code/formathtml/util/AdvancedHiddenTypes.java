package code.formathtml.util;

import code.expressionlanguage.exec.blocks.ExecAccessingImportingBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.types.AbstractHiddenTypes;
import code.formathtml.Configuration;

public final class AdvancedHiddenTypes implements AbstractHiddenTypes {
    private final Configuration configuration;

    public AdvancedHiddenTypes(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public boolean isHidden(ExecAccessingImportingBlock _global, ExecRootBlock _type) {
        if (_global == null) {
            return false;
        }
        return _global.isTypeHidden(_type, configuration.getContext());
    }
}
