package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.util.AssignedVariables;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.assign.util.SimpleAssignment;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;

public final class AssFinallyEval extends AssBracedStack implements AssEval,AssBreakableBlock {
    private String label;
    AssFinallyEval(boolean _completeNormally, boolean _completeNormallyGroup, String _f) {
        super(_completeNormally,_completeNormallyGroup);
        label = _f;
    }
    @Override
    public void setAssignmentAfter(ContextEl _an, AssignedVariablesBlock _anEl) {
        super.setAssignmentAfter(_an, _anEl);
        AssBlock pBlock_ = getPreviousSibling();
        CustList<AssBlock> prev_ = new CustList<AssBlock>();
        while (!(pBlock_ instanceof AssTryEval)) {
            if (!(pBlock_ instanceof AssEval)) {
                break;
            }
            prev_.add(pBlock_);
            pBlock_ = pBlock_.getPreviousSibling();
        }
        if (pBlock_ instanceof AssEval) {
            prev_.add(pBlock_);
        }
        IdMap<AssBlock, AssignedVariables> id_ = _anEl.getFinalVariables();
        AssignedVariables assTar_ = id_.getVal(this);
        StringMap<SimpleAssignment> after_;
        CustList<StringMap<SimpleAssignment>> afterVars_;
        CustList<StringMap<SimpleAssignment>> mutableVars_;
        after_ = buildAssFieldsAfterFinally(prev_, _an, _anEl);
        assTar_.getFieldsRoot().putAllMap(after_);
        afterVars_ = buildAssVariablesAfterFinally(prev_, _an, _anEl);
        assTar_.getVariablesRoot().clear();
        assTar_.getVariablesRoot().addAllElts(afterVars_);
        mutableVars_ = buildAssMutableLoopAfterFinally(prev_, _an, _anEl);
        assTar_.getMutableLoopRoot().clear();
        assTar_.getMutableLoopRoot().addAllElts(mutableVars_);
    }

    @Override
    public String getRealLabel() {
        return label;
    }
}
