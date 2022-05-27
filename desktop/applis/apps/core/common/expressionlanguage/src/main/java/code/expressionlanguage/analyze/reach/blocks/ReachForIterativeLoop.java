package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.ForIterativeLoop;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;

public final class ReachForIterativeLoop extends ReachForLabelled implements ReachBreakableBlock,ReachBuildableElMethod,ReachAbruptGroup {
    private final ForIterativeLoop meta;
    public ReachForIterativeLoop(ForIterativeLoop _info) {
        super(_info,_info.getLabel());
        meta = _info;
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        _page.setSumOffset(meta.getInitOffset());
        _page.zeroOffset();
        OperationNode rInit_ = meta.getRootInit();
        ReachOperationUtil.tryCalculate(rInit_, _page);
        _page.setSumOffset(meta.getExpressionOffset());
        _page.zeroOffset();
        OperationNode rExp_ = meta.getRootExp();
        ReachOperationUtil.tryCalculate(rExp_, _page);
        _page.setSumOffset(meta.getStepOffset());
        _page.zeroOffset();
        OperationNode rStep_ = meta.getRootStep();
        ReachOperationUtil.tryCalculate(rStep_, _page);
    }

    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        if (!_anEl.isReachable(this)) {
            _anEl.completeAbruptGroup(this);
        }
    }
}
