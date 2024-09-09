package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.ConditionBlock;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.Struct;

public final class ReachIfCondition extends ReachCondition implements ReachBreakableBlock {
    private final String label;
    public ReachIfCondition(ConditionBlock _info) {
        super(_info);
        label = _info.getLabel();
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        if (canBeIncrementedCurGroup()) {
            return;
        }
        Struct arg_ = getArgument();
        boolean abr_ = ArgumentListCall.isTrueValue(arg_);
        if (!abr_) {
            return;
        }
        if (!_anEl.canCompleteNormally(this)) {
            _anEl.completeAbruptGroup(this);
        }
    }
    @Override
    public boolean accessibleCondition() {
        Struct arg_ = getArgument();
        return ArgumentListCall.isNotFalseValue(arg_);
    }
    @Override
    public boolean accessibleForNext() {
        Struct arg_ = getArgument();
        return !ArgumentListCall.isTrueValue(arg_);
    }
    private boolean canBeIncrementedCurGroup() {
        ReachBlock next_ = getNextSibling();
        return next_ instanceof ReachElseIfCondition || next_ instanceof ReachElseCondition;
    }
}
