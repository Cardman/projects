package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.RangeOperation;
import code.expressionlanguage.fcts.FctRangeUnlimitedStep;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ReachRangeOperation extends ReachMethodOperation implements ReachCalculable {

    private final boolean implicitMiddle;

    public ReachRangeOperation(RangeOperation _info) {
        super(_info);
        implicitMiddle = _info.isImplicitMiddle();
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        if (!allAreDefined(this)) {
            return;
        }
        CustList<ReachOperationNode> childrenNodes_ = getChildrenNodes();
        CustList<Argument> argsList_ = new CustList<Argument>();
        for (ReachOperationNode r: childrenNodes_) {
            Argument arg_ = r.getArgument();
            checkNull(arg_,_page);
            argsList_.add(arg_);
        }
        int len_ = argsList_.size();
        Struct[] args_ = new Struct[len_];
        for (int i = 0; i < len_; i++) {
            args_[i] = argsList_.get(i).getStruct();
        }
        Struct range_;
        if (args_.length == 2 && implicitMiddle) {
            range_ = FctRangeUnlimitedStep.rangeUnlimitStep(args_);
        } else {
            range_ = FctRangeUnlimitedStep.range(args_);
        }
        if (range_ != null) {
            setSimpleArgumentAna(new Argument(range_));
        }
    }
}
