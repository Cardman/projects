package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.CatchEval;
import code.expressionlanguage.analyze.blocks.FilterContent;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;

public final class ReachCatchEval extends ReachAbstractCatchEval implements ReachBuildableElMethod,ReachFilterContent {
    private final FilterContent filterContent;
    public ReachCatchEval(CatchEval _info) {
        super(_info);
        filterContent = _info.getFilterContent();
    }

    public FilterContent getFilterContent() {
        return filterContent;
    }
    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        ReachCaseCondition.buildExpressionLanguageReadOnly(_page,new AnaClassArgumentMatching(""),true,this,this);
    }

    public void reachCatch(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        ReachCaseCondition.processFilter(_anEl, _page,this,this);
    }

}
