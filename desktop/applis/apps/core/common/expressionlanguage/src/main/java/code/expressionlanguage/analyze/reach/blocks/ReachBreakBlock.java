package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.BreakBlock;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ReachBreakBlock extends ReachAbruptBlock {
    private String label;
    protected ReachBreakBlock(BreakBlock _info) {
        super(_info);
        label = _info.getLabel();
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
    }
    @Override
    public void abrupt(AnalyzingEl _anEl) {
        super.abrupt(_anEl);
        boolean childOfBreakable_ = false;
        ReachBracedBlock b_ = getParent();
        while (b_ != null) {
            if (b_ instanceof ReachBreakableBlock) {
                if (label.isEmpty()) {
                    if (b_ instanceof ReachLoop || b_ instanceof ReachSwitchBlock) {
                        childOfBreakable_ = true;
                        break;
                    }
                } else {
                    if (StringUtil.quickEq(label, ((ReachBreakableBlock)b_).getRealLabel())){
                        childOfBreakable_ = true;
                        break;
                    }
                }
            }
            b_ = b_.getParent();
        }
        if (!childOfBreakable_) {
            return;
        }
        IdMap<ReachBreakBlock, ReachBreakableBlock> breakables_ = _anEl.getReachBreakables();
        breakables_.put(this, (ReachBreakableBlock) b_);
    }

}
