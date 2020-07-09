package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.blocks.OverridableBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;

public class GeneStringOverridable {
    private final String geneString;
    private final RootBlock type;
    private final OverridableBlock block;

    public GeneStringOverridable(String _geneString, RootBlock _type, OverridableBlock _block) {
        geneString = _geneString;
        type = _type;
        block = _block;
    }

    public String getGeneString() {
        return geneString;
    }

    public RootBlock getType() {
        return type;
    }

    public OverridableBlock getBlock() {
        return block;
    }
}
