package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.ForLoopPart;
import code.expressionlanguage.analyze.blocks.ForMutableIterativeLoop;
import code.expressionlanguage.analyze.assign.opers.AssOperationNode;
import code.expressionlanguage.analyze.assign.opers.AssUtil;
import code.expressionlanguage.analyze.assign.util.*;
import code.util.*;

public final class AssSimForMutableIterativeLoop extends AssBracedBlock implements AssBuildableElMethod {

    private CustList<AssOperationNode> opInit;

    private CustList<AssOperationNode> opExp;

    private CustList<AssOperationNode> opStep;

    private StringList assignedVariables = new StringList();
    AssSimForMutableIterativeLoop(boolean _completeNormally, boolean _completeNormallyGroup, ForMutableIterativeLoop _block) {
        super(_completeNormally,_completeNormallyGroup);
        opInit = AssUtil.getSimExecutableNodes(_block.getRootInit());
        opExp = AssUtil.getSimExecutableNodes(_block.getRootExp());
        opStep = AssUtil.getSimExecutableNodes(_block.getRootStep());
    }

    @Override
    public void buildExpressionLanguage(AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        _page.setForLoopPartState(ForLoopPart.INIT);
        if (!opInit.isEmpty()) {
            AssUtil.getSimSortedDescNodes(_a,opInit.last(),this, _page);
        }
        for (String v: assignedVariables) {
            _a.getVariables().put(v,true);
        }
        _page.setForLoopPartState(ForLoopPart.CONDITION);
        if (!opExp.isEmpty()) {
            AssUtil.getSimSortedDescNodes(_a,opExp.last(),this, _page);
        }
    }
    public void buildIncrementPart(AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        _page.setForLoopPartState(ForLoopPart.STEP);
        if (!opStep.isEmpty()) {
            AssUtil.getSimSortedDescNodes(_a,opStep.last(),this, _page);
        }
    }

    public StringList getAssignedVariables() {
        return assignedVariables;
    }
}
