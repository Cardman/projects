package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.assign.util.AssignedVariables;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.analyze.assign.util.SimpleAssignment;
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
    public void setAssignmentBeforeChild(AssignedVariablesBlock _a) {
        assignWhenFalse(true, _a);
    }

    @Override
    public void setAssignmentAfter(AssignedVariablesBlock _anEl, AnalyzedPageEl _page) {
        super.setAssignmentAfter(_anEl, _page);
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
        StringMap<SimpleAssignment> afterVars_;
        after_ = buildAssFieldsAfterIf(false, prev_, _anEl);
        assTar_.getFieldsRoot().putAllMap(after_);
        afterVars_ = buildAssVariablesAfterIf(false, prev_, _anEl);
        assTar_.getVariablesRoot().clear();
        assTar_.getVariablesRoot().putAllMap(afterVars_);
    }

    @Override
    public String getRealLabel() {
        return label;
    }
}
