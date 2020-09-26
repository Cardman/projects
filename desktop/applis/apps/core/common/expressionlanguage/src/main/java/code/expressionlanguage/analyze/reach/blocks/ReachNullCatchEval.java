package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AbstractCatchEval;
import code.expressionlanguage.analyze.blocks.AnalyzingEl;

public final class ReachNullCatchEval extends ReachAbstractCatchEval {
    protected ReachNullCatchEval(AbstractCatchEval _info) {
        super(_info);
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
    }

    @Override
    public void reach(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        ReachBlock p_ = getPreviousSibling();
        boolean reachCatch_ = true;
        while (!(p_ instanceof ReachTryEval)) {
            if (p_ instanceof ReachNullCatchEval) {
                reachCatch_ = false;
                break;
            }
            if (p_ == null) {
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
