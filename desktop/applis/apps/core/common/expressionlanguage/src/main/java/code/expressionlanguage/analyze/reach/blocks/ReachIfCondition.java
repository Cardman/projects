package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.ConditionBlock;

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
        Argument arg_ = getArgument();
        boolean abr_ = Argument.isTrueValue(arg_);
        if (!abr_) {
            return;
        }
        if (!_anEl.canCompleteNormally(this)) {
            _anEl.completeAbruptGroup(this);
        }
    }
    @Override
    public boolean accessibleCondition() {
        Argument arg_ = getArgument();
        return Argument.isNotFalseValue(arg_);
    }
    @Override
    public boolean accessibleForNext() {
        Argument arg_ = getArgument();
        return !Argument.isTrueValue(arg_);
    }
    private boolean canBeIncrementedCurGroup() {
        ReachBlock next_ = getNextSibling();
        return next_ instanceof ReachElseIfCondition || next_ instanceof ReachElseCondition;
    }
}
