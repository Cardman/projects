package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.ElseIfCondition;
import code.expressionlanguage.assign.util.AssignedBooleanVariables;
import code.expressionlanguage.assign.util.AssignedVariables;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.assign.util.SimpleAssignment;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;

public final class AssElseIfCondition extends AssCondition implements AssBreakableBlock {
    private String label;
    AssElseIfCondition(boolean _completeNormally, boolean _completeNormallyGroup, String _label, ElseIfCondition _c) {
        super(_completeNormally, _completeNormallyGroup,_c);
        label = _label;
    }
    @Override
    public void setAssignmentBeforeNextSibling(AssignedVariablesBlock _anEl) {
        if (!canBeIncrementedCurGroup()) {
            super.setAssignmentBeforeNextSibling(_anEl);
            return;
        }
        assignWhenFalse(false, _anEl);
    }
    @Override
    public void setAssignmentBeforeChild(AssignedVariablesBlock _anEl) {
        assignWhenTrue(_anEl);
    }

    @Override
    public void setAssignmentAfter(AssignedVariablesBlock _anEl, AnalyzedPageEl _page) {
        super.setAssignmentAfter(_anEl, _page);
        AssBlock pBlock_ = getPreviousSibling();
        if (canBeIncrementedCurGroup()) {
            return;
        }
        CustList<AssBlock> prev_ = new CustList<AssBlock>();
        prev_.add(this);
        while (!(pBlock_ instanceof AssIfCondition)) {
            if (pBlock_ == null) {
                break;
            }
            if (pBlock_ instanceof AssElseIfCondition) {
                prev_.add(pBlock_);
            }
            pBlock_ = pBlock_.getPreviousSibling();
        }
        if (pBlock_ != null) {
            prev_.add(pBlock_);
        }
        IdMap<AssBlock, AssignedVariables> id_ = _anEl.getFinalVariables();
        AssignedBooleanVariables assTar_ = (AssignedBooleanVariables) id_.getVal(this);
        StringMap<SimpleAssignment> after_;
        StringMap<SimpleAssignment> afterVars_;
        after_ = buildAssFieldsAfterIf(true, prev_, _anEl);
        assTar_.getFieldsRoot().putAllMap(after_);
        afterVars_ = buildAssVariablesAfterIf(true, prev_, _anEl);
        assTar_.getVariablesRoot().clear();
        assTar_.getVariablesRoot().putAllMap(afterVars_);
    }

    private boolean canBeIncrementedCurGroup() {
        AssBlock next_ = getNextSibling();
        return next_ instanceof AssElseIfCondition || next_ instanceof AssElseCondition;
    }
    @Override
    public String getRealLabel() {
        return label;
    }
}
