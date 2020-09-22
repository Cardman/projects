package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnaApplyCoreMethodUtil;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.StandardInstancingOperation;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ReachInstancingOperation extends ReachInvokingOperation {
    private ConstructorId constId;
    ReachInstancingOperation(StandardInstancingOperation _meta) {
        super(_meta);
        constId = _meta.getConstId();
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        if (!allAreDefined(this)) {
            return;
        }
        CustList<Argument> firstArgs_ = getArguments();
        Struct out_ = AnaApplyCoreMethodUtil.newAnalyzisInstanceStd(constId, _page, Argument.toArgArray(firstArgs_));
        if (out_ == null) {
            return;
        }
        Argument arg_ = new Argument(out_);
        setSimpleArgumentAna(arg_);
    }


}
