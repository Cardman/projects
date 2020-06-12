package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.util.AssignedVariables;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.util.IdMap;

public abstract class AssNamedFunctionBlock extends AssMemberCallingsBlock {
    AssNamedFunctionBlock(boolean _completeNormally, boolean _completeNormallyGroup) {
        super(_completeNormally,_completeNormallyGroup);
    }

    @Override
    public void setAssignmentAfterCall(ContextEl _an, AssignedVariablesBlock _anEl) {
        setAssignmentAfter(_an,_anEl);
    }

    @Override
    public void setAssignmentBeforeCall(ContextEl _an, AssBlock _prev,AssignedVariablesBlock _anEl) {
        AssignedVariables ass_;
        IdMap<AssBlock, AssignedVariables> id_ = _anEl.getFinalVariables();
        ass_ = _anEl.getFinalVariablesGlobal();
        id_.put(this, ass_);
    }
}
