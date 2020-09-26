package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.DefaultCondition;

public final class ReachDefaultCondition extends ReachSwitchPartBlock {
    protected ReachDefaultCondition(DefaultCondition _info) {
        super(_info);
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
    }

}
