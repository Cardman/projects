package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.AbstractCallFctOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class ReachInstanceFctStdOperation extends ReachStdFctOption implements ReachPossibleIntermediateDotted {
    private Argument previous;
    ReachInstanceFctStdOperation(AbstractCallFctOperation _meta, OperationNode _info) {
        super(_meta,_info);
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
        Struct res_ = previous.getStruct();
        if (res_ == NullStruct.NULL_VALUE) {
            return;
        }
        resultMethod(_page,res_, getClassMethodId());
    }

    @Override
    public void setPreviousArgument(Argument _argument) {
        previous = _argument;
    }
}
