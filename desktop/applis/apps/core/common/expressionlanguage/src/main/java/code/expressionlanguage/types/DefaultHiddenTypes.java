package code.expressionlanguage.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecAccessingImportingBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;

public final class DefaultHiddenTypes implements AbstractHiddenTypes {
    private final ContextEl context;

    public DefaultHiddenTypes(ContextEl context) {
        this.context = context;
    }

    @Override
    public boolean isHidden(ExecAccessingImportingBlock _global, ExecRootBlock _type) {
        return _global.isTypeHidden(_type, context);
    }
}
