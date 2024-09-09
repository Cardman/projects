package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.CmpOperation;
import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.expressionlanguage.common.symbol.SymbolConstants;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
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
            if (r.getArgument() == NullStruct.NULL_VALUE) {
                null_ = true;
            }
        }
        if (null_) {
            return;
        }
        Struct first_ = ch_.first().getArgument();
        Struct second_ = ch_.last().getArgument();
        Struct arg_ = SymbolConstants.calculateOperator(symbol, first_, second_);
        setSimpleArgumentAna(arg_);
    }
}
