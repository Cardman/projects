package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.blocks.AbstractCatchEval;
import code.expressionlanguage.analyze.blocks.AnalyzingEl;

public final class ReachNullCatchEval extends ReachAbstractCatchEval {
    public ReachNullCatchEval(AbstractCatchEval _info) {
        super(_info);
    }

    @Override
    public void reach(AnalyzingEl _anEl) {
        reachCatch(_anEl);
    }

    public void reachCatch(AnalyzingEl _anEl) {
        ReachBlock p_ = getPreviousSibling();
        boolean reachCatch_ = true;
        while (!(p_ instanceof ReachTryEval)) {
            if (p_ instanceof ReachNullCatchEval|| p_ == null) {
                if (p_ != null) {
                    reachCatch_ = false;
                }
                break;
            }
            p_ = p_.getPreviousSibling();
        }
        if (reachCatch_) {
            _anEl.reach(this);
        } else {
            _anEl.unreach(this);
        }
    }
}
