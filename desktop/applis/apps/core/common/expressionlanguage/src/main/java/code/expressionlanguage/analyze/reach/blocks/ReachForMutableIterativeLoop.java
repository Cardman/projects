package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.ForMutableIterativeLoop;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;

public final class ReachForMutableIterativeLoop extends ReachForLabelled implements ReachBreakableBlock,ReachBuildableElMethod,ReachAbruptGroup {

    private final ForMutableIterativeLoop mut;
    public ReachForMutableIterativeLoop(ForMutableIterativeLoop _info) {
        super(_info,_info.getLabel());
        mut = _info;
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        _page.setSumOffset(mut.getInitOffset());
        _page.zeroOffset();
        OperationNode rInit_ = mut.getRootInit();
        if (rInit_ != null) {
            ReachOperationUtil.tryCalculate(rInit_, _page);
        }
        _page.setSumOffset(mut.getExpressionOffset());
        _page.zeroOffset();
        OperationNode rExp_ = mut.getRootExp();
        if (rExp_ == null) {
            mut.setAlwaysTrue(true);
        } else {
            mut.setArgument(ReachOperationUtil.tryCalculate(rExp_, _page));
        }
        _page.setSumOffset(mut.getStepOffset());
        _page.zeroOffset();
        OperationNode rStep_ = mut.getRootStep();
        if (rStep_ != null) {
            ReachOperationUtil.tryCalculate(rStep_, _page);
        }
    }

    @Override
    public boolean accessibleCondition() {
        if (mut.isAlwaysTrue()) {
            return true;
        }
        Argument arg_ = mut.getArgument();
        return Argument.isNotFalseValue(arg_);
    }
    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        boolean proc_ = true;
        if (!mut.isAlwaysTrue()) {
            Argument arg_ = mut.getArgument();
            proc_ = Argument.isTrueValue(arg_);
        }

        ReachWhileCondition.abrWhileMutable(_anEl,proc_,this);
    }

}
