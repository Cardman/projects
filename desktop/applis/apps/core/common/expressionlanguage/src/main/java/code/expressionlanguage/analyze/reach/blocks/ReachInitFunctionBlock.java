package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.MemberCallingsBlock;

public final class ReachInitFunctionBlock extends ReachMemberCallingsBlock {
    private final MemberCallingsBlock meta;
    public ReachInitFunctionBlock(MemberCallingsBlock _info) {
        super(_info);
        meta = _info;
    }

    @Override
    public String getSignature(AnalyzedPageEl _page) {
        return meta.getSignature(_page);
    }
}
