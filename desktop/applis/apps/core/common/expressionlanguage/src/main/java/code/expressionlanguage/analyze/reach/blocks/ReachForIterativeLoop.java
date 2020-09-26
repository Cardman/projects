package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.ForIterativeLoop;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;

public class ReachForIterativeLoop extends ReachBracedBlock implements ReachLoop {
    private ForIterativeLoop meta;
    private String label;
    protected ReachForIterativeLoop(ForIterativeLoop _info) {
        super(_info);
        meta = _info;
        label = _info.getLabel();
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        _page.setGlobalOffset(meta.getInitOffset());
        _page.setOffset(0);
        OperationNode rInit_ = meta.getRootInit();
        ReachOperationUtil.tryCalculate(rInit_, _page);
        _page.setGlobalOffset(meta.getExpressionOffset());
        _page.setOffset(0);
        OperationNode rExp_ = meta.getRootExp();
        ReachOperationUtil.tryCalculate(rExp_, _page);
        _page.setGlobalOffset(meta.getStepOffset());
        _page.setOffset(0);
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
