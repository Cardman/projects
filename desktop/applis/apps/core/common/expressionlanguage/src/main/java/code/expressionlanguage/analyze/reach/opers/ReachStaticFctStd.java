package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.AbstractCallFctOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.structs.NullStruct;

public final class ReachStaticFctStd extends ReachStdFctOption {
    ReachStaticFctStd(AbstractCallFctOperation _meta, OperationNode _info) {
        super(_meta,_info);
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        if (!allAreDefined(this)) {
            return;
        }
        resultMethod(_page,NullStruct.NULL_VALUE, getClassMethodId());
    }
}
