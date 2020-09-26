package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.DeclareVariable;

public final class ReachDeclareVariable extends ReachLeaf implements ReachBuildableElMethod {

    protected ReachDeclareVariable(DeclareVariable _info) {
        super(_info);
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
    }
}
