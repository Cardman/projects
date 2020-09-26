package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.Block;

public final class ReachEmptyInstruction extends ReachLeaf implements ReachBuildableElMethod {
    protected ReachEmptyInstruction(Block _info) {
        super(_info);
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
    }
}
