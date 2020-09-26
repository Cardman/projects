package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.ForMutableIterativeLoop;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.util.EntryCust;
import code.util.IdMap;

public final class ReachForMutableIterativeLoop extends ReachBracedBlock implements ReachLoop {

    private ForMutableIterativeLoop meta;
    private String label;
    protected ReachForMutableIterativeLoop(ForMutableIterativeLoop _info) {
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
        if (rInit_ != null) {
            ReachOperationUtil.tryCalculate(rInit_, _page);
        }
        _page.setGlobalOffset(meta.getExpressionOffset());
        _page.setOffset(0);
        OperationNode rExp_ = meta.getRootExp();
        if (rExp_ == null) {
            meta.setAlwaysTrue(true);
        } else {
            meta.setArgument(ReachOperationUtil.tryCalculate(rExp_, _page));
        }
        _page.setGlobalOffset(meta.getStepOffset());
        _page.setOffset(0);
        OperationNode rStep_ = meta.getRootStep();
        if (rStep_ != null) {
            ReachOperationUtil.tryCalculate(rStep_, _page);
        }
    }

    @Override
    public boolean accessibleCondition() {
        if (meta.isAlwaysTrue()) {
            return true;
        }
        Argument arg_ = meta.getArgument();
        return Argument.isNotFalseValue(arg_);
    }
    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        boolean abr_ = true;
        boolean proc_ = true;
        if (!meta.isAlwaysTrue()) {
            Argument arg_ = meta.getArgument();
            proc_ = Argument.isTrueValue(arg_);
        }
        if (_anEl.isReachable(this)) {
            if (!proc_) {
                abr_ = false;
            }
        }
        IdMap<ReachBreakBlock, ReachBreakableBlock> breakables_;
        breakables_ = _anEl.getReachBreakables();
        for (EntryCust<ReachBreakBlock, ReachBreakableBlock> e: breakables_.entryList()) {
            if (e.getValue() == this && _anEl.isReachable(e.getKey())) {
                abr_ = false;
                break;
            }
        }
        if (abr_) {
            _anEl.completeAbruptGroup(this);
        }
    }

}
