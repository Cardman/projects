package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.blocks.NamedCalledFunctionBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;

public class GeneStringOverridable {
    private final AnaFormattedRootBlock format;
    private final String geneString;
    private final RootBlock type;
    private final NamedCalledFunctionBlock block;

    public GeneStringOverridable(AnaFormattedRootBlock _format, NamedCalledFunctionBlock _block) {
        format = _format;
        geneString = _format.getFormatted();
        type = _format.getRootBlock();
        block = _block;
    }

    public AnaFormattedRootBlock getFormat() {
        return format;
    }

    public String getGeneString() {
        return geneString;
    }

    public RootBlock getType() {
        return type;
    }

    public NamedCalledFunctionBlock getBlock() {
        return block;
    }
}
