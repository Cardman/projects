package code.formathtml.util;

import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.types.AbstractCurrentGlobalBlock;
import code.formathtml.Configuration;

public final class AdvancedCurrentGlobalBlock implements AbstractCurrentGlobalBlock {
    private final Configuration configuration;

    public AdvancedCurrentGlobalBlock(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public AccessingImportingBlock getCurrentGlobalBlock() {
        return configuration.getCurrentGlobalBlock();
    }

    @Override
    public AccessingImportingBlock getCurrentGlobalBlock(AccessingImportingBlock _bl) {
        return configuration.getCurrentGlobalBlock(_bl);
    }
}
