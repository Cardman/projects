package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.NamedArgumentOperation;
import code.util.CustList;

public final class ReachNamedArgumentOperation  extends ReachMethodOperation implements ReachCalculable {
    private int index;

    ReachNamedArgumentOperation(NamedArgumentOperation _info) {
        super(_info);
        index = _info.getIndex();
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        if (!allAreDefined(this)) {
            return;
        }
        CustList<ReachOperationNode> chidren_ = getChildrenNodes();
        setSimpleArgumentAna(chidren_.first().getArgument());
    }

    public int getIndex() {
        return index;
    }
}