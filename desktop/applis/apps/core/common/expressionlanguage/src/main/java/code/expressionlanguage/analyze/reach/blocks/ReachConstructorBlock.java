package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.ConstructorBlock;

public final class ReachConstructorBlock extends ReachNamedFunctionBlock {
    private ConstructorBlock meta;
    protected ReachConstructorBlock(ConstructorBlock _info) {
        super(_info);
        meta = _info;
    }

    @Override
    public void setAssignmentAfterCallReadOnly(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        meta.checkInterfaces(_page);
    }

}
