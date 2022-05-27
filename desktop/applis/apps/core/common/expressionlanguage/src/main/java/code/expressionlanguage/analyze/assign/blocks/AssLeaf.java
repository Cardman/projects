package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;

public abstract class AssLeaf extends AssBlock {
    protected AssLeaf(boolean _completeNormally,boolean _completeNormallyGroup) {
        super(_completeNormally,_completeNormallyGroup);
    }

    @Override
    public AssBlock getFirstChild() {
        return null;
    }

    @Override
    public void setAssignmentAfter(AssignedVariablesBlock _anEl, AnalyzedPageEl _page) {
    }
}
