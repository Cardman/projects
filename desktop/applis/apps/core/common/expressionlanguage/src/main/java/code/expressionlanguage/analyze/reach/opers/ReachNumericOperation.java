package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.NumericOperation;
import code.util.CustList;

public abstract class ReachNumericOperation extends ReachMethodOperation implements ReachCalculable {

    private String op;
    ReachNumericOperation(NumericOperation _info) {
        super(_info);
        op = _info.getOp();
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        if (!allAreDefined(this)) {
            return;
        }
        CustList<ReachOperationNode> chidren_ = getChildrenNodes();
        Argument a_ = chidren_.first().getArgument();
        Argument c_ = chidren_.last().getArgument();
        Argument r_ = calculateOperAna(a_, op, c_, _page);
        if (r_.isNull()) {
            return;
        }
        setSimpleArgumentAna(r_);
    }

    abstract Argument calculateOperAna(Argument _a, String _op, Argument _b, AnalyzedPageEl _page);

}
