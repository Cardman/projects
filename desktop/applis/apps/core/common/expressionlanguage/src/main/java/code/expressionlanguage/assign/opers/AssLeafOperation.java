package code.expressionlanguage.assign.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.assign.blocks.AssBlock;
import code.expressionlanguage.assign.util.AssignedVariables;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.assign.util.Assignment;
import code.expressionlanguage.assign.util.AssignmentsUtil;
import code.util.CustList;
import code.util.StringMap;

public abstract class AssLeafOperation extends AssOperationNode {
    AssLeafOperation(OperationNode _ex) {
        super(_ex);
    }

    @Override
    public void analyzeAssignmentAfter(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a) {
        boolean isBool_;
        isBool_ = getResultClass().isBoolType(_conf.getAnalyzing());
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
