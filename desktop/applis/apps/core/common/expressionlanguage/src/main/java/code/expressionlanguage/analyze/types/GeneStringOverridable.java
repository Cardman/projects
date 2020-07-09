package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.blocks.OverridableBlock;

public class GeneStringOverridable {
    private final String geneString;
    private final OverridableBlock block;

    public GeneStringOverridable(String _geneString, OverridableBlock _block) {
        geneString = _geneString;
        block = _block;
    }

    public String getGeneString() {
        return geneString;
    }

    public OverridableBlock getBlock() {
        return block;
    }
}
