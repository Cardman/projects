package code.expressionlanguage.exec.util;

import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;

public final class PolymorphMethod {
    private final ExecRootBlock rootBlock;
    private final ExecNamedFunctionBlock named;

    public PolymorphMethod(ExecRootBlock rootBlock, ExecNamedFunctionBlock named) {
        this.rootBlock = rootBlock;
        this.named = named;
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }

    public ExecNamedFunctionBlock getNamed() {
        return named;
    }
}
