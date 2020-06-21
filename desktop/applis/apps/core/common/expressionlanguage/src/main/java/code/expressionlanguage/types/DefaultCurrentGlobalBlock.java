package code.expressionlanguage.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.AccessedBlock;
import code.expressionlanguage.exec.blocks.ExecAccessingImportingBlock;

public final class DefaultCurrentGlobalBlock implements AbstractCurrentGlobalBlock {
    private final ContextEl context;

    public DefaultCurrentGlobalBlock(ContextEl context) {
        this.context = context;
    }

    @Override
    public AccessedBlock getCurrentGlobalBlockImporting() {
        return context.getCurrentGlobalBlockImporting();
    }

    @Override
    public ExecAccessingImportingBlock getImportingAcces() {
        return context.getImportingAcces();
    }

    @Override
    public AccessedBlock getCurrentGlobalBlock() {
        return context.getCurrentGlobalBlock();
    }

    @Override
    public AccessedBlock getCurrentGlobalBlock(AccessedBlock _bl) {
        return context.getCurrentGlobalBlock(_bl);
    }
}
