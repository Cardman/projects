package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.blocks.AnalyzingEl;

public abstract class ReachLeaf extends ReachBlock {
    protected ReachLeaf(AbsBk _info) {
        super(_info);
    }

    @Override
    public void reach(AnalyzingEl _anEl) {
        reachLeaf(_anEl);
    }

    public void reachLeaf(AnalyzingEl _anEl) {
        reachAdv(_anEl);
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
