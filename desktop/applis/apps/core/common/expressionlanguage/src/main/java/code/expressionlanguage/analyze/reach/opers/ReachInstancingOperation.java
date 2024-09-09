package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.analyze.AnaApplyCoreMethodUtil;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.StandardInstancingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ReachInstancingOperation extends ReachInvokingOperation {
    private final StandardConstructor constructor;

    ReachInstancingOperation(StandardInstancingOperation _meta) {
        super(_meta);
        constructor = _meta.getInstancingCommonContent().getConstructor();
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        if (!allAreDefined(this)) {
            return;
        }
        CustList<Struct> firstArgs_ = getArguments();
        Struct out_ = AnaApplyCoreMethodUtil.newAnalyzisInstanceStd(constructor, _page, ArgumentListCall.toArgArray(firstArgs_));
        if (out_ == null) {
            return;
        }
        setSimpleArgumentAna(out_);
    }


}
