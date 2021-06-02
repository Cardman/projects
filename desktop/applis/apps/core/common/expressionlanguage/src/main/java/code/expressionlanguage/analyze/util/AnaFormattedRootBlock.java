package code.expressionlanguage.analyze.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.common.StringExpUtil;

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
    public AnaFormattedRootBlock(AnalyzedPageEl _page, String _formatted) {
        this.rootBlock = _page.getAnaClassBody(StringExpUtil.getIdFromAllTypes(_formatted));
        this.formatted = _formatted;
    }
    public static AnaFormattedRootBlock defValue() {
        return new AnaFormattedRootBlock((RootBlock)null,"");
    }
    public static AnaFormattedRootBlock copy(AnaFormattedRootBlock _from) {
        return new AnaFormattedRootBlock(_from.rootBlock,_from.formatted);
    }

    public static AnaFormattedRootBlock quickFormat(AnaFormattedRootBlock _sub, AnaFormattedRootBlock _sup) {
        String format_ = AnaInherits.quickFormat(_sub, _sup.formatted);
        return new AnaFormattedRootBlock(_sup.rootBlock, format_);
    }

    public static AnaFormattedRootBlock format(AnaFormattedRootBlock _sub, AnaFormattedRootBlock _sup) {
        String format_ = AnaInherits.format(_sub, _sup.formatted);
        return new AnaFormattedRootBlock(_sup.rootBlock, format_);
    }
    public RootBlock getRootBlock() {
        return rootBlock;
    }

    public String getFormatted() {
        return formatted;
    }
}
