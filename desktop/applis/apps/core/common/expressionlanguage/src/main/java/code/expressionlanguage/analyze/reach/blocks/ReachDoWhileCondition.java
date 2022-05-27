package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.ConditionBlock;
import code.util.EntryCust;
import code.util.IdMap;

public final class ReachDoWhileCondition extends ReachCondition {
    public ReachDoWhileCondition(ConditionBlock _info) {
        super(_info);
    }

    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        ReachBreakableBlock previous_ = (ReachBreakableBlock) getPreviousSibling();
        boolean abr_ = true;
        ReachBlock last_ = getPreviousSibling().getFirstChild();
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        Argument arg_ = getArgument();
        boolean proc_ = Argument.isTrueValue(arg_);
        if (!proc_ && _anEl.canCompleteNormallyGroup(last_)) {
            abr_ = false;
        }
        if (!proc_) {
            IdMap<ReachContinueBlock, ReachBreakableBlock> breakables_;
            breakables_ = _anEl.getReachContinuables();
            for (EntryCust<ReachContinueBlock, ReachBreakableBlock> e: breakables_.entryList()) {
                if (e.getValue() == previous_ && _anEl.isReachable(e.getKey())) {
                    abr_ = false;
                    break;
                }
            }
        }
        IdMap<ReachBreakBlock, ReachBreakableBlock> breakables_;
        breakables_ = _anEl.getReachBreakables();
        for (EntryCust<ReachBreakBlock, ReachBreakableBlock> e: breakables_.entryList()) {
            if (e.getValue() == previous_ && _anEl.isReachable(e.getKey())) {
                abr_ = false;
                break;
            }
        }
        if (abr_) {
            _anEl.completeAbruptGroup(this);
        }
    }

}
