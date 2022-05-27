package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.BreakBlock;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ReachBreakBlock extends ReachAbruptBlock {
    private final String label;
    public ReachBreakBlock(BreakBlock _info) {
        super(_info);
        label = _info.getLabel();
    }

    @Override
    public void abrupt(AnalyzingEl _anEl) {
        super.abrupt(_anEl);
        boolean childOfBreakable_ = false;
        ReachBracedBlock b_ = getParent();
        while (b_ != null) {
            if (exitLoop(b_)) {
                childOfBreakable_ = true;
                break;
            }
            b_ = b_.getParent();
        }
        if (!childOfBreakable_) {
            return;
        }
        IdMap<ReachBreakBlock, ReachBreakableBlock> breakables_ = _anEl.getReachBreakables();
        breakables_.put(this, (ReachBreakableBlock) b_);
    }
    private boolean exitLoop(ReachBracedBlock _b) {
        if (_b instanceof ReachBreakableBlock) {
            if (label.isEmpty()) {
                return isLoop(_b) || _b instanceof ReachSwitchBlock;
            }
            return StringUtil.quickEq(label, ((ReachBreakableBlock) _b).getRealLabel());
        }
        return false;
    }

    public static boolean isLoop(ReachBlock _b) {
        return _b instanceof ReachForIterativeLoop || _b instanceof ReachForMutableIterativeLoop|| _b instanceof ReachForEachLoop || _b instanceof ReachForEachTable ||_b instanceof ReachDoBlock || _b instanceof ReachWhileCondition;
    }
}
