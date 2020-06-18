package code.formathtml.util;

import code.expressionlanguage.exec.blocks.ExecAccessingImportingBlock;
import code.expressionlanguage.types.AbstractCurrentGlobalBlock;
import code.formathtml.Configuration;

public final class AdvancedCurrentGlobalBlock implements AbstractCurrentGlobalBlock {
    private final Configuration configuration;

    public AdvancedCurrentGlobalBlock(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public ExecAccessingImportingBlock getCurrentGlobalBlock() {
        return configuration.getCurrentGlobalBlock();
    }

    @Override
    public ExecAccessingImportingBlock getCurrentGlobalBlock(ExecAccessingImportingBlock _bl) {
        return configuration.getCurrentGlobalBlock(_bl);
    }
}
