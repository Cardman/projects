package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.structs.Struct;

public final class ReachIntermStdOperation extends ReachMethodOperation implements ReachCalculable, ReachPossibleIntermediateDotted {
    private final boolean inter;
    private Struct previous;
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
    public void setPreviousArgument(Struct _argument) {
        previous = _argument;
    }
}
