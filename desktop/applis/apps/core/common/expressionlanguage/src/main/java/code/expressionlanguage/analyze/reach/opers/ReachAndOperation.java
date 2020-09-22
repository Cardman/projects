package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;

public final class ReachAndOperation extends ReachQuickOperation {
    ReachAndOperation(OperationNode _info) {
        super(_info);
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        tryGetResult(this, false);
    }
}
