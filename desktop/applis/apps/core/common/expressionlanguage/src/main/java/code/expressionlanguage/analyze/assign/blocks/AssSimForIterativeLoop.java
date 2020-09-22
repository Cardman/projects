package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.ForIterativeLoop;
import code.expressionlanguage.analyze.assign.opers.AssOperationNode;
import code.expressionlanguage.analyze.assign.opers.AssUtil;
import code.expressionlanguage.analyze.assign.util.*;
import code.util.*;

public final class AssSimForIterativeLoop extends AssBracedStack implements AssBuildableElMethod {

    private CustList<AssOperationNode> opInit;

    private CustList<AssOperationNode> opExp;

    private CustList<AssOperationNode> opStep;
    AssSimForIterativeLoop(boolean _completeNormally, boolean _completeNormallyGroup, ForIterativeLoop _for) {
        super(_completeNormally, _completeNormallyGroup);
        opInit = AssUtil.getSimExecutableNodes(_for.getRootInit());
        opExp = AssUtil.getSimExecutableNodes(_for.getRootExp());
        opStep = AssUtil.getSimExecutableNodes(_for.getRootStep());
    }

    @Override
    public void buildExpressionLanguage(AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        AssUtil.getSimSortedDescNodes(_a,opInit.last(),this, _page);
        AssUtil.getSimSortedDescNodes(_a,opExp.last(),this, _page);
        AssUtil.getSimSortedDescNodes(_a,opStep.last(),this, _page);
    }

}
