package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.UnaryOperation;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;

public final class ReachUnaryOperation extends ReachMethodOperation implements ReachCalculable {
    private String oper;
    ReachUnaryOperation(UnaryOperation _info) {
        super(_info);
        oper = _info.getOper();
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
        AnaClassArgumentMatching to_ = getInfo().getResultClass();
        Argument out_;
        if (StringList.quickEq(oper, PLUS)) {
            out_ = new Argument(NumParsers.idNumber(NumParsers.convertToNumber(nb_), to_.getUnwrapObjectNb()));
        } else {
            out_ = new Argument(NumParsers.opposite(NumParsers.convertToNumber(nb_), to_.getUnwrapObjectNb()));
        }
        setSimpleArgumentAna(out_);
    }
}
