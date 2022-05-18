package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.CmpOperation;
import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.util.CustList;

public final class ReachCmpOperation extends ReachMethodOperation implements ReachCalculable {
    private final CommonOperSymbol symbol;
    ReachCmpOperation(CmpOperation _info, CommonOperSymbol _s) {
        super(_info);
        symbol = _s;
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        if (!allAreDefined(this)) {
            return;
        }
        CustList<ReachOperationNode> ch_ = getChildrenNodes();
        for (ReachOperationNode r: ch_) {
            checkNull(r.getArgument(),_page);
        }
        boolean null_ = false;
        for (ReachOperationNode r: ch_) {
            if (Argument.isNullValue(r.getArgument())) {
                null_ = true;
            }
        }
        if (null_) {
            return;
        }
        Argument first_ = ch_.first().getArgument();
        Argument second_ = ch_.last().getArgument();
        Argument arg_ = new Argument(symbol.calculateOperator(first_.getStruct(), second_.getStruct(),(byte)0));
        setSimpleArgumentAna(arg_);
    }
}
