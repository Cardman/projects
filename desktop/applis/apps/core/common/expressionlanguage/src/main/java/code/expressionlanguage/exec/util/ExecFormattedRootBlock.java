package code.expressionlanguage.exec.util;

import code.expressionlanguage.exec.blocks.ExecRootBlock;

public final class ExecFormattedRootBlock {
    private final ExecRootBlock rootBlock;
    private final String formatted;

    public ExecFormattedRootBlock(ExecRootBlock _rootBlock, String _formatted) {
        this.rootBlock = _rootBlock;
        this.formatted = _formatted;
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }

    public String getFormatted() {
        return formatted;
    }
}
