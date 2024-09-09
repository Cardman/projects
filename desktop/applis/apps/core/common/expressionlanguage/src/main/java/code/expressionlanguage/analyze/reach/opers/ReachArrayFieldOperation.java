package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.structs.Struct;

public final class ReachArrayFieldOperation extends ReachMethodOperation implements ReachCalculable,ReachPossibleIntermediateDotted {
    private Struct previous;
    ReachArrayFieldOperation(OperationNode _info) {
        super(_info);
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        checkNull(previous,_page);
    }

    @Override
    public void setPreviousArgument(Struct _argument) {
        previous = _argument;
    }
}
