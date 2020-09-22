package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.assign.util.AssignedVariables;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.util.IdMap;

public abstract class AssNamedFunctionBlock extends AssMemberCallingsBlock {
    AssNamedFunctionBlock(boolean _completeNormally, boolean _completeNormallyGroup) {
        super(_completeNormally,_completeNormallyGroup);
    }

    @Override
    public void setAssignmentAfterCall(AssignedVariablesBlock _anEl, AnalyzedPageEl _page) {
        setAssignmentAfter(_anEl,  _page);
    }

    @Override
    public void setAssignmentBeforeCall(AssBlock _prev, AssignedVariablesBlock _anEl) {
        AssignedVariables ass_;
        IdMap<AssBlock, AssignedVariables> id_ = _anEl.getFinalVariables();
        ass_ = _anEl.getFinalVariablesGlobal();
        id_.put(this, ass_);
    }
}
