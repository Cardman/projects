package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.symbols.AnaOperSymbol;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
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
        Struct a_ = chidren_.first().getArgument();
        Struct c_ = chidren_.last().getArgument();
        Struct r_ = symbol.calculateOperator(a_,c_, _page);
        if (r_ == NullStruct.NULL_VALUE) {
            return;
        }
        setSimpleArgumentAna(r_);
    }

}
