package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.opers.AssOperationNode;
import code.expressionlanguage.assign.opers.AssUtil;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.methods.Throwing;
import code.util.CustList;

public final class AssThrowing extends AssAbruptBlock implements AssBuildableElMethod {
    private CustList<AssOperationNode> opList;
    AssThrowing(boolean _completeNormally, boolean _completeNormallyGroup, Throwing _th) {
        super(_completeNormally,_completeNormallyGroup);
        opList = AssUtil.getExecutableNodes(_th.getOpThrow());
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont, AssignedVariablesBlock _a) {
        AssUtil.getSortedDescNodes(_a,opList.last(),this,_cont);
    }
}
