package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ReachUnaryBinOperation extends ReachMethodOperation implements ReachCalculable {
    ReachUnaryBinOperation(OperationNode _info) {
        super(_info);
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        if (!allAreDefined(this)) {
            return;
        }
        CustList<ReachOperationNode> chidren_ = getChildrenNodes();
        Argument arg_ = chidren_.first().getArgument();
        Struct nb_ = arg_.getStruct();
        if (!(nb_ instanceof NumberStruct)) {
            return;
        }
        AnaClassArgumentMatching res_ = getInfo().getResultClass();
        Argument out_ = new Argument(NumParsers.negBinNumber(NumParsers.convertToNumber(nb_), res_.getUnwrapObjectNb()));
        setSimpleArgumentAna(out_);
    }
}
