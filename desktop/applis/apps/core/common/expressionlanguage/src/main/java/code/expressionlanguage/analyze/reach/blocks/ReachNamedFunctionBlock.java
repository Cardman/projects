package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;

public abstract class ReachNamedFunctionBlock extends ReachMemberCallingsBlock {
    private NamedFunctionBlock method;
    protected ReachNamedFunctionBlock(NamedFunctionBlock _info) {
        super(_info);
        method = _info;
    }

    @Override
    public String getSignature(AnalyzedPageEl _page) {
        return method.getSignature(_page);
    }
}
