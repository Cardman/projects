package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.ElseCondition;
import code.util.CustList;

public final class ReachElseCondition extends ReachBreakableBlockImpl implements ReachBreakableBlock,ReachAbruptGroup {
    public ReachElseCondition(ElseCondition _info) {
        super(_info,_info);
    }


    @Override
    public void reach(AnalyzingEl _anEl) {
        ReachBlock p_ = getPreviousSibling();
        if (_anEl.isReachable(p_) && p_.accessibleForNext()) {
            _anEl.reach(this);
        } else {
            _anEl.unreach(this);
        }
    }
    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        CustList<ReachBlock> group_ = getConditionBlocks();
        boolean canCmpNormally_ = false;
        for (ReachBlock b: group_) {
            if (_anEl.canCompleteNormally(b)) {
                canCmpNormally_ = true;
                break;
            }
        }
        if (!canCmpNormally_) {
            for (ReachBlock b: group_) {
                _anEl.completeAbruptGroup(b);
            }
        }
    }
}
