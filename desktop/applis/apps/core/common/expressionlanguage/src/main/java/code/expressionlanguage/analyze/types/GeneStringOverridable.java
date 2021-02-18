package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.blocks.NamedCalledFunctionBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;

public class GeneStringOverridable {
    private final String geneString;
    private final RootBlock type;
    private final NamedCalledFunctionBlock block;

    public GeneStringOverridable(String _geneString, RootBlock _type, NamedCalledFunctionBlock _block) {
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

    public NamedCalledFunctionBlock getBlock() {
        return block;
    }
}
