package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.Throwing;
import code.expressionlanguage.analyze.assign.opers.AssOperationNode;
import code.expressionlanguage.analyze.assign.opers.AssUtil;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.util.CustList;

public final class AssThrowing extends AssAbruptBlock implements AssBuildableElMethod {
    private CustList<AssOperationNode> opList;
    AssThrowing(boolean _completeNormally, boolean _completeNormallyGroup, Throwing _th) {
        super(_completeNormally,_completeNormallyGroup);
        opList = AssUtil.getExecutableNodes(_th.getRoot());
    }

    @Override
    public void buildExpressionLanguage(AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        AssUtil.getSortedDescNodes(_a,opList.last(),this, _page);
    }
}
