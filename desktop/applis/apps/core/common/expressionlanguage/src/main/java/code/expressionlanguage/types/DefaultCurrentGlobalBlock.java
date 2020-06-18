package code.expressionlanguage.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecAccessingImportingBlock;

public final class DefaultCurrentGlobalBlock implements AbstractCurrentGlobalBlock {
    private final ContextEl context;

    public DefaultCurrentGlobalBlock(ContextEl context) {
        this.context = context;
    }

    @Override
    public ExecAccessingImportingBlock getCurrentGlobalBlock() {
        return context.getCurrentGlobalBlock();
    }

    @Override
    public ExecAccessingImportingBlock getCurrentGlobalBlock(ExecAccessingImportingBlock _bl) {
        return context.getCurrentGlobalBlock(_bl);
    }
}
