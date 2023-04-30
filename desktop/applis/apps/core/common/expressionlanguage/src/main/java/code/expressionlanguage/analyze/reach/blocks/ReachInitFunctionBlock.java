package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.blocks.MemberCallingsBlock;
import code.expressionlanguage.common.DisplayedStrings;

public final class ReachInitFunctionBlock extends ReachMemberCallingsBlock {
    private final MemberCallingsBlock meta;
    public ReachInitFunctionBlock(MemberCallingsBlock _info) {
        super(_info);
        meta = _info;
    }

    @Override
    public String getSignature(DisplayedStrings _page) {
        return meta.getSignature(_page);
    }
}
