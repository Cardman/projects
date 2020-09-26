package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;

public final class ReachArrayFieldOperation extends ReachMethodOperation implements ReachCalculable,ReachPossibleIntermediateDotted {
    private Argument previous;
    ReachArrayFieldOperation(OperationNode _info) {
        super(_info);
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        checkNull(previous,_page);
    }

    @Override
    public void setPreviousArgument(Argument _argument) {
        previous = _argument;
    }
}
