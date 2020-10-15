package code.expressionlanguage.exec.util;

import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;

public final class PolymorphMethod {
    private final ExecRootBlock rootBlock;
    private final ExecNamedFunctionBlock named;

    public PolymorphMethod(ExecRootBlock _rootBlock, ExecNamedFunctionBlock _named) {
        this.rootBlock = _rootBlock;
        this.named = _named;
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }

    public ExecNamedFunctionBlock getNamed() {
        return named;
    }
}
