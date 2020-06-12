package code.expressionlanguage.assign.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.blocks.AssBlock;
import code.expressionlanguage.assign.util.AssignedVariables;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.assign.util.Assignment;
import code.expressionlanguage.assign.util.AssignmentsUtil;
import code.util.CustList;
import code.util.StringMap;

public abstract class AssLeafOperation extends AssOperationNode {
    AssLeafOperation(ExecOperationNode _ex) {
        super(_ex);
    }
    public void analyzeNotBoolAssignmentAfter(AssBlock _ass, AssignedVariablesBlock _a) {
        analyzeAssignmentAfter(_ass,_a, false);
    }
    @Override
    public void analyzeAssignmentAfter(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a) {
        boolean isBool_;
        isBool_ = getResultClass().isBoolType(_conf);
        analyzeAssignmentAfter(_ass,_a, isBool_);
    }

    public void analyzeAssignmentAfter(AssBlock _ass, AssignedVariablesBlock _a, boolean _bool) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        StringMap<Assignment> fieldsAfter_ = new StringMap<Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        CustList<StringMap<Assignment>> mutableAfter_ = new CustList<StringMap<Assignment>>();

        fieldsAfter_.putAllMap(AssignmentsUtil.assignAfter(_bool,vars_.getFieldsBefore().getVal(this)));
        variablesAfter_.addAllElts(AssignmentsUtil.assignAfter(_bool,vars_.getVariablesBefore().getVal(this)));
        mutableAfter_.addAllElts(AssignmentsUtil.assignAfter(_bool,vars_.getMutableLoopBefore().getVal(this)));
        vars_.getFields().put(this, fieldsAfter_);
        vars_.getVariables().put(this, variablesAfter_);
        vars_.getMutableLoop().put(this, mutableAfter_);
    }
    @Override
    public final AssOperationNode getFirstChild() {
        return null;
    }
}
