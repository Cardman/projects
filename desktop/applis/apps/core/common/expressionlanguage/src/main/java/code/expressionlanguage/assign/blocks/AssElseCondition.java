package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.util.AssignedVariables;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.assign.util.SimpleAssignment;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;

public final class AssElseCondition extends AssBracedStack implements AssBreakableBlock {
    private String label;
    AssElseCondition(boolean _completeNormally, boolean _completeNormallyGroup, String _label) {
        super(_completeNormally, _completeNormallyGroup);
        label = _label;
    }

    @Override
    public void setAssignmentBeforeChild(ContextEl _an, AssignedVariablesBlock _a) {
        assignWhenFalse(true, _an, _a);
    }

    @Override
    public void setAssignmentAfter(ContextEl _an, AssignedVariablesBlock _anEl) {
        super.setAssignmentAfter(_an, _anEl);
        AssBlock pBlock_ = getPreviousSibling();
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
        AssignedVariables assTar_ = id_.getVal(this);
        StringMap<SimpleAssignment> after_;
        CustList<StringMap<SimpleAssignment>> afterVars_;
        CustList<StringMap<SimpleAssignment>> mutableVars_;
        after_ = buildAssFieldsAfterIf(false, prev_, _an, _anEl);
        assTar_.getFieldsRoot().putAllMap(after_);
        afterVars_ = buildAssVariablesAfterIf(false, prev_, _an, _anEl);
        assTar_.getVariablesRoot().clear();
        assTar_.getVariablesRoot().addAllElts(afterVars_);
        mutableVars_ = buildAssMutableLoopAfterIf(false, prev_, _an, _anEl);
        assTar_.getMutableLoopRoot().clear();
        assTar_.getMutableLoopRoot().addAllElts(mutableVars_);
    }

    @Override
    public String getRealLabel() {
        return label;
    }
}
