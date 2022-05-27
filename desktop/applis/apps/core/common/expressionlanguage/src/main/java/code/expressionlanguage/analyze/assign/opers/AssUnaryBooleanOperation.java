package code.expressionlanguage.analyze.assign.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.assign.blocks.AssBlock;
import code.expressionlanguage.analyze.assign.util.AssignedVariables;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.analyze.assign.util.Assignment;
import code.expressionlanguage.analyze.assign.util.AssignmentsUtil;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.util.StringMap;

public final class AssUnaryBooleanOperation extends AssMethodOperation implements AssOperationNodeFull {
    public AssUnaryBooleanOperation(OperationNode _ex) {
        super(_ex);
    }

    @Override
    public void analyzeAssignmentAfter(AssBlock _ass, AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        AssOperationNode last_ = getFirstChild();
        StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(last_);
        StringMap<Assignment> variablesAfterLast_ = vars_.getVariables().getVal(last_);
        vars_.getFields().put(this, AssignmentsUtil.neg(fieldsAfterLast_));
        vars_.getVariables().put(this, AssignmentsUtil.neg(variablesAfterLast_));
    }
}
