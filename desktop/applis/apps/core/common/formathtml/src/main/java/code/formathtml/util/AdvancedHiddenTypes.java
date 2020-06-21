package code.formathtml.util;

import code.expressionlanguage.analyze.accessing.Accessed;
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
        Accessed a_ = new Accessed(_type.getAccess(), _type.getPackageName(), _type.getParentFullName(), _type.getFullName(), _type.getOuterFullName());
        return _global.isTypeHidden(a_, configuration.getContext());
    }
}
