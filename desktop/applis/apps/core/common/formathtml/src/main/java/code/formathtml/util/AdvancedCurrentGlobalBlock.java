package code.formathtml.util;

import code.expressionlanguage.exec.blocks.AccessedBlock;
import code.expressionlanguage.exec.blocks.ExecAccessingImportingBlock;
import code.expressionlanguage.types.AbstractCurrentGlobalBlock;
import code.formathtml.Configuration;

public final class AdvancedCurrentGlobalBlock implements AbstractCurrentGlobalBlock {
    private final Configuration configuration;

    public AdvancedCurrentGlobalBlock(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public AccessedBlock getCurrentGlobalBlockImporting() {
        return configuration.getCurrentGlobalBlock();
    }

    @Override
    public ExecAccessingImportingBlock getImportingAcces() {
        return configuration.getCurrentGlobalBlock();
    }

    @Override
    public AccessedBlock getCurrentGlobalBlock() {
        return configuration.getCurrentGlobalBlock();
    }

    @Override
    public AccessedBlock getCurrentGlobalBlock(AccessedBlock _bl) {
        return configuration.getCurrentGlobalBlock(_bl);
    }
}
