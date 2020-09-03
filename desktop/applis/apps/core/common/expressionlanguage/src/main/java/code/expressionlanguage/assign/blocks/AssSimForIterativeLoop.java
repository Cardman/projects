package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.ForIterativeLoop;
import code.expressionlanguage.assign.opers.AssOperationNode;
import code.expressionlanguage.assign.opers.AssUtil;
import code.expressionlanguage.assign.util.*;
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
    public void buildExpressionLanguage(ContextEl _cont, AssignedVariablesBlock _a) {
        AssUtil.getSimSortedDescNodes(_a,opInit.last(),this,_cont);
        AssUtil.getSimSortedDescNodes(_a,opExp.last(),this,_cont);
        AssUtil.getSimSortedDescNodes(_a,opStep.last(),this,_cont);
    }

}
