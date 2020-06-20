package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.opers.AssOperationNode;
import code.expressionlanguage.assign.opers.AssUtil;
import code.expressionlanguage.assign.util.AssignedBooleanVariables;
import code.expressionlanguage.assign.util.AssignedVariables;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.exec.blocks.ExecCondition;
import code.util.CustList;

public abstract class AssCondition extends AssBracedStack implements AssBuildableElMethod {

    private CustList<AssOperationNode> opCondition;
    AssCondition(boolean _completeNormally, boolean _completeNormallyGroup, ExecCondition _c) {
        super(_completeNormally, _completeNormallyGroup);
        opCondition = AssUtil.getExecutableNodes(_c.getOpCondition());
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont, AssignedVariablesBlock _a) {
        AssUtil.getSortedDescNodes(_a,opCondition.last(),this,_cont);
        buildConditions(_cont,_a);
    }

    @Override
    protected AssignedVariables buildNewAssignedVariable() {
        return new AssignedBooleanVariables();
    }
}
