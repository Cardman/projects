package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.symbols.AnaOperSymbol;
import code.util.CustList;

public final class ReachNumericOperation extends ReachMethodOperation implements ReachCalculable {

    private final AnaOperSymbol symbol;
    public ReachNumericOperation(OperationNode _info, AnaOperSymbol _sym) {
        super(_info);
        symbol = _sym;
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        if (!allAreDefined(this)) {
            return;
        }
        CustList<ReachOperationNode> chidren_ = getChildrenNodes();
        Argument a_ = chidren_.first().getArgument();
        Argument c_ = chidren_.last().getArgument();
        Argument r_ = new Argument(symbol.calculateOperator(a_.getStruct(),c_.getStruct(), getResultClass().getUnwrapObjectNb(), _page));
        if (r_.isNull()) {
            return;
        }
        setSimpleArgumentAna(r_);
    }

}
