package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.assign.util.AssignedVariables;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.util.IdMap;

public final class AssDoBlock extends AssBracedStack implements AssBreakableBlock {
    private final String label;
    AssDoBlock(boolean _completeNormally, boolean _completeNormallyGroup, String _do) {
        super(_completeNormally, _completeNormallyGroup);
        label = _do;
    }

    @Override
    public void setAssignmentBeforeNextSibling(AssignedVariablesBlock _a) {
        AssBlock nextSibling_ = getNextSibling();
        AssignedVariables assBl_ = nextSibling_.buildNewAssignedVariable();
        IdMap<AssBlock, AssignedVariables> id_ = _a.getFinalVariables();
        assBl_.getFieldsRootBefore().putAllMap(buildAssListFieldBeforeIncrPart(_a));
        assBl_.getVariablesRootBefore().putAllMap(buildAssListLocVarBeforeIncrPart(_a));
        id_.put(nextSibling_, assBl_);
    }

    @Override
    public String getRealLabel() {
        return label;
    }
}
