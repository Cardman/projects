package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.assign.opers.AssOperationNode;
import code.expressionlanguage.analyze.assign.opers.AssUtil;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.analyze.blocks.ForLoopPart;
import code.expressionlanguage.analyze.blocks.ForMutableIterativeLoop;
import code.util.CustList;
import code.util.StringList;
import code.util.core.BoolVal;

public final class AssSimForMutableIterativeLoop extends AssBracedBlock implements AssSimBuildableElMethod {
    private final int initOffset;
    private final int expressionOffset;
    private final int stepOffset;

    private final CustList<AssOperationNode> opInit;

    private final CustList<AssOperationNode> opExp;

    private final CustList<AssOperationNode> opStep;

    private final StringList assignedVariables = new StringList();
    public AssSimForMutableIterativeLoop(boolean _completeNormally, boolean _completeNormallyGroup, ForMutableIterativeLoop _block) {
        super(_completeNormally,_completeNormallyGroup);
        opInit = AssUtil.getSimExecutableNodes(_block.getRootInit());
        opExp = AssUtil.getSimExecutableNodes(_block.getRootExp());
        opStep = AssUtil.getSimExecutableNodes(_block.getRootStep());
        initOffset = _block.getInitOffset();
        expressionOffset = _block.getExpressionOffset();
        stepOffset = _block.getStepOffset();
    }

    @Override
    public void buildExpressionLanguage(AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        _page.setForLoopPartState(ForLoopPart.INIT);
        if (!opInit.isEmpty()) {
            _page.setSumOffset(initOffset);
            _page.zeroOffset();
            AssUtil.getSimSortedDescNodes(_a,opInit.last(),this, _page);
        }
        for (String v: assignedVariables) {
            _a.getVariables().put(v, BoolVal.TRUE);
        }
        _page.setForLoopPartState(ForLoopPart.CONDITION);
        if (!opExp.isEmpty()) {
            _page.setSumOffset(expressionOffset);
            _page.zeroOffset();
            AssUtil.getSimSortedDescNodes(_a,opExp.last(),this, _page);
        }
    }
    public void buildIncrementPart(AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        _page.setForLoopPartState(ForLoopPart.STEP);
        if (!opStep.isEmpty()) {
            _page.setSumOffset(stepOffset);
            _page.zeroOffset();
            AssUtil.getSimSortedDescNodes(_a,opStep.last(),this, _page);
        }
    }

    public StringList getAssignedVariables() {
        return assignedVariables;
    }
}
