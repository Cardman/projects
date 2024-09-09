package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.AbstractCallFctOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class ReachInstanceFctStdOperation extends ReachInvokingOperation implements ReachPossibleIntermediateDotted {
    private Struct previous;
    ReachInstanceFctStdOperation(StandardMethod _standardMethod, AbstractCallFctOperation _meta, OperationNode _info) {
        super(_standardMethod, _meta,_info);
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        checkNull(previous,_page);
        if (!allAreDefined(this)) {
            return;
        }
        if (previous == null) {
            return;
        }
        Struct res_ = previous;
        if (res_ == NullStruct.NULL_VALUE) {
            return;
        }
        resultMethod(_page,res_);
    }

    @Override
    public void setPreviousArgument(Struct _argument) {
        previous = _argument;
    }
}
