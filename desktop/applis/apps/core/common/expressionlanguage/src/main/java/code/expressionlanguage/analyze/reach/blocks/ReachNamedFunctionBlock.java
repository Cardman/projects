package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.expressionlanguage.common.DisplayedStrings;

public abstract class ReachNamedFunctionBlock extends ReachMemberCallingsBlock implements ReachMemberCallingsBlockSide{
    private final NamedFunctionBlock method;
    protected ReachNamedFunctionBlock(NamedFunctionBlock _info) {
        super(_info);
        method = _info;
    }

    @Override
    public String getSignature(DisplayedStrings _page) {
        return method.getSignature(_page);
    }
}
