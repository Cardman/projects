package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.ConditionBlock;
import code.expressionlanguage.analyze.assign.opers.AssOperationNode;
import code.expressionlanguage.analyze.assign.opers.AssUtil;
import code.expressionlanguage.analyze.assign.util.AssignedBooleanVariables;
import code.expressionlanguage.analyze.assign.util.AssignedVariables;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.util.CustList;

public abstract class AssCondition extends AssBracedStack implements AssBuildableElMethod {

    private final CustList<AssOperationNode> opCondition;
    AssCondition(boolean _completeNormally, boolean _completeNormallyGroup, ConditionBlock _c) {
        super(_completeNormally, _completeNormallyGroup);
        opCondition = AssUtil.getExecutableNodes(_c.getRoot());
    }

    @Override
    public void buildExpressionLanguage(AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        AssUtil.getSortedDescNodes(_a,opCondition.last(),this, _page);
        buildConditions(_a);
    }

    @Override
    protected AssignedVariables buildNewAssignedVariable() {
        return new AssignedBooleanVariables();
    }
}
