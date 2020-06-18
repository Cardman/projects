package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.util.AssignedVariables;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.methods.TryEval;
import code.util.CustList;
import code.util.IdMap;

public final class AssTryEval extends AssBracedStack implements AssEval,AssBreakableBlock {
    private String label;
    AssTryEval(boolean _completeNormally, boolean _completeNormallyGroup, String _t) {
        super(_completeNormally,_completeNormallyGroup);
        label = _t;
    }

    @Override
    public void setAssignmentBeforeNextSibling(ContextEl _an, AssignedVariablesBlock _anEl) {
        IdMap<AssBlock, AssignedVariables> id_ = _anEl.getFinalVariables();
        AssBlock nextSibling_ = getNextSibling();
        AssignedVariables assBl_ = nextSibling_.buildNewAssignedVariable();
        CustList<AssCatchEval> catchs_ = new CustList<AssCatchEval>();
        assBl_.getFieldsRootBefore().putAllMap(buildAssFieldsBefNextCatchFinally(this,_an, _anEl,catchs_));
        assBl_.getVariablesRootBefore().addAllElts(buildAssVarsBefNextCatchFinally(this,_an, _anEl,catchs_));
        assBl_.getMutableLoopRootBefore().addAllElts(buildAssMutableLoopBefNextCatchFinally(this,_an, _anEl,catchs_));
        id_.put(nextSibling_, assBl_);
    }

    @Override
    public String getRealLabel() {
        return label;
    }
}
