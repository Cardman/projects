package code.expressionlanguage.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.RootBlock;

public final class DefaultHiddenTypes implements AbstractHiddenTypes {
    private final ContextEl context;

    public DefaultHiddenTypes(ContextEl context) {
        this.context = context;
    }

    @Override
    public boolean isHidden(AccessingImportingBlock _global, RootBlock _type) {
        return _global.isTypeHidden(_type, context);
    }
}
