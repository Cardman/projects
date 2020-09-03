package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.ForLoopPart;
import code.expressionlanguage.analyze.blocks.ForMutableIterativeLoop;
import code.expressionlanguage.assign.opers.AssOperationNode;
import code.expressionlanguage.assign.opers.AssUtil;
import code.expressionlanguage.assign.util.*;
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
    public void buildExpressionLanguage(ContextEl _cont, AssignedVariablesBlock _a) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setForLoopPartState(ForLoopPart.INIT);
        if (!opInit.isEmpty()) {
            AssUtil.getSimSortedDescNodes(_a,opInit.last(),this,_cont);
        }
        for (String v: assignedVariables) {
            _a.getVariables().put(v,true);
        }
        page_.setForLoopPartState(ForLoopPart.CONDITION);
        if (!opExp.isEmpty()) {
            AssUtil.getSimSortedDescNodes(_a,opExp.last(),this,_cont);
        }
    }
    public void buildIncrementPart(ContextEl _an, AssignedVariablesBlock _a) {
        AnalyzedPageEl page_ = _an.getAnalyzing();
        page_.setForLoopPartState(ForLoopPart.STEP);
        if (!opStep.isEmpty()) {
            AssUtil.getSimSortedDescNodes(_a,opStep.last(),this,_an);
        }
    }

    public StringList getAssignedVariables() {
        return assignedVariables;
    }
}
