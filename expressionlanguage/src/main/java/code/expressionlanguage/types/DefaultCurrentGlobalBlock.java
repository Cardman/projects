package code.expressionlanguage.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.AccessingImportingBlock;

public final class DefaultCurrentGlobalBlock implements AbstractCurrentGlobalBlock {
    private final ContextEl context;

    public DefaultCurrentGlobalBlock(ContextEl context) {
        this.context = context;
    }

    @Override
    public AccessingImportingBlock getCurrentGlobalBlock() {
        return context.getCurrentGlobalBlock();
    }

    @Override
    public AccessingImportingBlock getCurrentGlobalBlock(AccessingImportingBlock _bl) {
        return context.getCurrentGlobalBlock(_bl);
    }
}
