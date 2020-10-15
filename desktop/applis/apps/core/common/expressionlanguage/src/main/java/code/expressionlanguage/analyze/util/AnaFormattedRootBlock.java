package code.expressionlanguage.analyze.util;

import code.expressionlanguage.analyze.blocks.RootBlock;

public final class AnaFormattedRootBlock {
    private final RootBlock rootBlock;
    private final String formatted;

    public AnaFormattedRootBlock(RootBlock _rootBlock, String _formatted) {
        this.rootBlock = _rootBlock;
        this.formatted = _formatted;
    }

    public RootBlock getRootBlock() {
        return rootBlock;
    }

    public String getFormatted() {
        return formatted;
    }
}
