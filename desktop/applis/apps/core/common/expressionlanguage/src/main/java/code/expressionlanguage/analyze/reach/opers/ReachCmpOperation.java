package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.CmpOperation;
import code.util.CustList;

public final class ReachCmpOperation extends ReachMethodOperation implements ReachCalculable {
    private boolean stringCompare;
    private String op;
    ReachCmpOperation(CmpOperation _info) {
        super(_info);
        stringCompare = _info.isStringCompare();
        op = _info.getOp();
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
        Argument arg_;
        if (stringCompare) {
            arg_ = CmpOperation.calculateCommonStr(first_, second_, op);
        } else {
            arg_ = CmpOperation.calculateCommonNb(first_, second_, op);
        }
        setSimpleArgumentAna(arg_);
    }
}
