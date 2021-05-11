package code.expressionlanguage.analyze.util;

import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.inherits.AnaInherits;

public final class AnaFormattedRootBlock {
    private final RootBlock rootBlock;
    private final String formatted;

    public AnaFormattedRootBlock(RootBlock _rootBlock) {
        this.rootBlock = _rootBlock;
        this.formatted = _rootBlock.getGenericString();
    }
    public AnaFormattedRootBlock(RootBlock _rootBlock, String _formatted) {
        this.rootBlock = _rootBlock;
        this.formatted = _formatted;
    }

    public static AnaFormattedRootBlock format(AnaFormattedRootBlock _sub, AnaFormattedRootBlock _sup) {
        String format_ = AnaInherits.quickFormat(_sub, _sup.formatted);
        return new AnaFormattedRootBlock(_sup.rootBlock, format_);
    }
    public RootBlock getRootBlock() {
        return rootBlock;
    }

    public String getFormatted() {
        return formatted;
    }
}
