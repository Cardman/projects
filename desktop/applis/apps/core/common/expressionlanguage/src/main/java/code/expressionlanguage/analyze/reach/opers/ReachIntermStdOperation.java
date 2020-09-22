package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;

public final class ReachIntermStdOperation extends ReachMethodOperation implements ReachCalculable, ReachPossibleIntermediateDotted {
    private boolean inter;
    private Argument previous;
    ReachIntermStdOperation(OperationNode _info, boolean _inter) {
        super(_info);
        inter = _inter;
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        if (inter) {
            checkNull(previous,_page);
        }
    }

    @Override
    public void setPreviousArgument(Argument _argument) {
        previous = _argument;
    }
}
