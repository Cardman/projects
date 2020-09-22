package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.NamedArgumentOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.util.CustList;

public final class ReachIdOperation extends ReachMethodOperation implements ReachCalculable {
    ReachIdOperation(OperationNode _info) {
        super(_info);
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        if (!allAreDefined(this)) {
            return;
        }
        CustList<ReachOperationNode> chidren_ = getChildrenNodes();
        setSimpleArgumentAna(chidren_.first().getArgument());
    }
}
