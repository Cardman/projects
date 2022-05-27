package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.assign.opers.AssOperationNode;
import code.expressionlanguage.analyze.assign.opers.AssUtil;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.analyze.blocks.ForIterativeLoop;
import code.util.CustList;

public final class AssSimForIterativeLoop extends AssBracedStack implements AssSimBuildableElMethod {

    private final int initOffset;
    private final int expressionOffset;
    private final int stepOffset;
    private final CustList<AssOperationNode> opInit;

    private final CustList<AssOperationNode> opExp;

    private final CustList<AssOperationNode> opStep;
    public AssSimForIterativeLoop(boolean _completeNormally, boolean _completeNormallyGroup, ForIterativeLoop _for) {
        super(_completeNormally, _completeNormallyGroup);
        opInit = AssUtil.getSimExecutableNodes(_for.getRootInit());
        opExp = AssUtil.getSimExecutableNodes(_for.getRootExp());
        opStep = AssUtil.getSimExecutableNodes(_for.getRootStep());
        initOffset = _for.getInitOffset();
        expressionOffset = _for.getExpressionOffset();
        stepOffset = _for.getStepOffset();
    }

    @Override
    public void buildExpressionLanguage(AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        _page.setSumOffset(initOffset);
        _page.zeroOffset();
        AssUtil.getSimSortedDescNodes(_a,opInit.last(),this, _page);
        _page.setSumOffset(expressionOffset);
        _page.zeroOffset();
        AssUtil.getSimSortedDescNodes(_a,opExp.last(),this, _page);
        _page.setSumOffset(stepOffset);
        _page.zeroOffset();
        AssUtil.getSimSortedDescNodes(_a,opStep.last(),this, _page);
    }

}
