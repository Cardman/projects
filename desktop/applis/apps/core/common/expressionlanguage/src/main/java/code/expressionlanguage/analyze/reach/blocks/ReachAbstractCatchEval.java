package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.blocks.AbstractCatchEval;
import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.util.CustList;

public abstract class ReachAbstractCatchEval extends ReachBracedBlock implements ReachEval {
    private String label;
    protected ReachAbstractCatchEval(AbstractCatchEval _info) {
        super(_info);
        label = _info.getRealLabel();
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        if (canBeIncrementedCurGroup()) {
            return;
        }
        CustList<ReachBlock> group_ = new CustList<ReachBlock>();
        group_.add(this);
        group_.addAllElts(getTryBlocks());
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

    private boolean canBeIncrementedCurGroup() {
        ReachBlock next_ = getNextSibling();
        return next_ instanceof ReachAbstractCatchEval || next_ instanceof ReachFinallyEval;
    }
}
