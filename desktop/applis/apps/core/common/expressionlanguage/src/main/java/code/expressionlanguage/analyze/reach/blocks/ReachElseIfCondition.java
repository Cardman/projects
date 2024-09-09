package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.ElseIfCondition;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ReachElseIfCondition extends ReachCondition implements ReachBreakableBlock {
    private final String label;
    public ReachElseIfCondition(ElseIfCondition _info) {
        super(_info);
        label = _info.getRealLabelInfo().getInfo();
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    private boolean canBeIncrementedCurGroup() {
        ReachBlock next_ = getNextSibling();
        return next_ instanceof ReachElseIfCondition || next_ instanceof ReachElseCondition;
    }
    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        if (canBeIncrementedCurGroup()) {
            return;
        }
        Struct arg_ = getArgument();
        boolean abr_ = ArgumentListCall.isTrueValue(arg_);
        if (!abr_) {
            return;
        }
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

    @Override
    public boolean accessibleCondition() {
        Struct arg_ = getArgument();
        return ArgumentListCall.isNotFalseValue(arg_);
    }
    @Override
    public boolean accessibleForNext() {
        Struct arg_ = getArgument();
        return !ArgumentListCall.isTrueValue(arg_);
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
}
