package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.util.AssignedVariables;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.methods.DoBlock;
import code.util.IdMap;

public final class AssDoBlock extends AssBracedStack implements AssLoop {
    private String label;
    AssDoBlock(boolean _completeNormally, boolean _completeNormallyGroup, DoBlock _do) {
        super(_completeNormally, _completeNormallyGroup);
        label = _do.getRealLabel();
    }

    @Override
    public void setAssignmentBeforeNextSibling(ContextEl _an, AssignedVariablesBlock _a) {
        AssBlock nextSibling_ = getNextSibling();
        AssignedVariables assBl_ = nextSibling_.buildNewAssignedVariable();
        IdMap<AssBlock, AssignedVariables> id_ = _a.getFinalVariables();
        assBl_.getFieldsRootBefore().putAllMap(buildAssListFieldBeforeIncrPart(_an, _a));
        assBl_.getVariablesRootBefore().addAllElts(buildAssListLocVarBeforeIncrPart(_an, _a));
        assBl_.getMutableLoopRootBefore().addAllElts(buildAssListMutableLoopBeforeIncrPart(_an, _a));
        id_.put(nextSibling_, assBl_);
    }

    @Override
    public String getRealLabel() {
        return label;
    }
}
