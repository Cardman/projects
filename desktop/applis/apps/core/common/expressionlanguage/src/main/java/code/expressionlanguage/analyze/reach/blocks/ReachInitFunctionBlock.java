package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.MemberCallingsBlock;

public final class ReachInitFunctionBlock extends ReachMemberCallingsBlock {
    private MemberCallingsBlock meta;
    protected ReachInitFunctionBlock(MemberCallingsBlock _info) {
        super(_info);
        meta = _info;
    }
    @Override
    public void setAssignmentAfterCallReadOnly(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        //always void
    }

    @Override
    public String getSignature(AnalyzedPageEl _page) {
        return meta.getSignature(_page);
    }
}
