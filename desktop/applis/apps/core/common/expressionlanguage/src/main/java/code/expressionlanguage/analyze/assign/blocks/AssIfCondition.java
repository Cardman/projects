package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.assign.util.AssignedBooleanVariables;
import code.expressionlanguage.analyze.assign.util.AssignedVariables;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.analyze.assign.util.SimpleAssignment;
import code.expressionlanguage.analyze.blocks.ConditionBlock;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;

public final class AssIfCondition extends AssCondition implements AssBreakableBlock {
    private final String label;
    AssIfCondition(boolean _completeNormally, boolean _completeNormallyGroup, String _label, ConditionBlock _c) {
        super(_completeNormally, _completeNormallyGroup,_c);
        label = _label;
    }

    @Override
    public void setAssignmentBeforeChild(AssignedVariablesBlock _a) {
        assignWhenTrue(_a);
    }

    @Override
    public void setAssignmentBeforeNextSibling(AssignedVariablesBlock _a) {
        if (!canBeIncrementedCurGroup()) {
            super.setAssignmentBeforeNextSibling(_a);
            return;
        }
        assignWhenFalse(false, _a);
    }

    @Override
    public void setAssignmentAfter(AssignedVariablesBlock _anEl, AnalyzedPageEl _page) {
        super.setAssignmentAfter(_anEl, _page);
        if (canBeIncrementedCurGroup()) {
            return;
        }
        IdMap<AssBlock, AssignedVariables> id_ = _anEl.getFinalVariables();
        AssignedBooleanVariables assTar_ = (AssignedBooleanVariables) id_.getVal(this);
        StringMap<SimpleAssignment> after_;
        StringMap<SimpleAssignment> afterVars_;
        after_ = buildAssFieldsAfterIf(true, new CustList<AssBlock>(this), _anEl);
        assTar_.getFieldsRoot().putAllMap(after_);
        afterVars_ = buildAssVariablesAfterIf(true, new CustList<AssBlock>(this), _anEl);
        assTar_.getVariablesRoot().clear();
        assTar_.getVariablesRoot().putAllMap(afterVars_);
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    private boolean canBeIncrementedCurGroup() {
        AssBlock next_ = getNextSibling();
        return next_ instanceof AssElseIfCondition || next_ instanceof AssElseCondition;
    }
}
