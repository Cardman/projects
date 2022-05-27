package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.CatchEval;
import code.util.StringList;

public final class ReachCatchEval extends ReachAbstractCatchEval {
    private final CatchEval meta;
    public ReachCatchEval(CatchEval _info) {
        super(_info);
        meta = _info;
    }

    public void reachCatch(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        StringList classes_ = new StringList();
        ReachBlock p_ = getPreviousSibling();
        while (!(p_ instanceof ReachTryEval)) {
            if (p_ instanceof ReachCatchEval) {
                classes_.add(((ReachCatchEval)p_).meta.getImportedClassName());
            }
            if (p_ == null) {
                break;
            }
            p_ = p_.getPreviousSibling();
        }
        _anEl.setArgMapping(meta.getImportedClassName());
        boolean reachCatch_ = true;
        for (String c: classes_) {
            _anEl.setParamMapping(c);
            if (_anEl.isCorrectMapping(_page)) {
                reachCatch_ = false;
                break;
            }
        }
        if (reachCatch_) {
            _anEl.reach(this);
        } else {
            _anEl.unreach(this);
        }
    }

}
