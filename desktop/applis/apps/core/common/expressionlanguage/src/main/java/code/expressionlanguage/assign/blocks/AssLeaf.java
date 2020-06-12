package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;

public abstract class AssLeaf extends AssBlock {
    AssLeaf(boolean _completeNormally,boolean _completeNormallyGroup) {
        super(_completeNormally,_completeNormallyGroup);
    }

    @Override
    public AssBlock getFirstChild() {
        return null;
    }

    @Override
    public void setAssignmentAfter(ContextEl _an, AssignedVariablesBlock _anEl) {
    }
}
