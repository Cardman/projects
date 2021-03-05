package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AbsBk;

public final class ReachEmptyInstruction extends ReachLeaf implements ReachBuildableElMethod {
    protected ReachEmptyInstruction(AbsBk _info) {
        super(_info);
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        //
    }
}
