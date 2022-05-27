package code.expressionlanguage.analyze.assign.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.assign.blocks.AssBlock;
import code.expressionlanguage.analyze.assign.util.AssignedVariables;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.analyze.assign.util.Assignment;
import code.expressionlanguage.analyze.assign.util.AssignmentsUtil;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.util.StringMap;

public abstract class AssLeafOperation extends AssOperationNode {
    protected AssLeafOperation(OperationNode _ex) {
        super(_ex);
    }

    public void analyzeAssignmentAfter(AssBlock _ass, AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        boolean isBool_;
        isBool_ = getResultClass().isBoolType(_page);
        analyzeAssignmentAfter(_ass,_a, isBool_);
    }

    public void analyzeAssignmentAfter(AssBlock _ass, AssignedVariablesBlock _a, boolean _bool) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        StringMap<Assignment> fieldsAfter_ = new StringMap<Assignment>();
        StringMap<Assignment> variablesAfter_ = new StringMap<Assignment>();

        fieldsAfter_.putAllMap(AssignmentsUtil.assignAfter(_bool,vars_.getFieldsBefore().getVal(this)));
        variablesAfter_.putAllMap(AssignmentsUtil.assignAfter(_bool,vars_.getVariablesBefore().getVal(this)));
        vars_.getFields().put(this, fieldsAfter_);
        vars_.getVariables().put(this, variablesAfter_);
    }
    @Override
    public final AssOperationNode getFirstChild() {
        return null;
    }
}
