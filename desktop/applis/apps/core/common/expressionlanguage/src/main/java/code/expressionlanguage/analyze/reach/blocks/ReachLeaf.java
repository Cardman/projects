package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.blocks.BracedBlock;

public abstract class ReachLeaf extends ReachBlock {
    protected ReachLeaf(Block _info) {
        super(_info);
    }

    @Override
    public void reach(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        ReachBlock prev_ = getPreviousSibling();
        ReachBracedBlock br_ = getParent();
        if (prev_ == null) {
            if (_anEl.isReachable(br_) && br_.accessibleCondition()) {
                _anEl.reach(this);
            } else {
                _anEl.unreach(this);
            }
        } else {
            super.reach(_anEl, _page);
        }
    }
    @Override
    public void abrupt(AnalyzingEl _anEl) {
        if (!_anEl.isReachable(this)) {
            _anEl.completeAbruptGroup(this);
        }
    }
    @Override
    public ReachBlock getFirstChild() {
        return null;
    }
}
