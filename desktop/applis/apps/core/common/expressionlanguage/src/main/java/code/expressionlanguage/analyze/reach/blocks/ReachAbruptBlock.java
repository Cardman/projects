package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.AbsBk;

public abstract class ReachAbruptBlock extends ReachLeaf implements ReachBuildableElMethod {
    protected ReachAbruptBlock(AbsBk _info) {
        super(_info);
    }

    @Override
    public void abrupt(AnalyzingEl _anEl) {
        _anEl.completeAbruptGroup(this);
    }
}
