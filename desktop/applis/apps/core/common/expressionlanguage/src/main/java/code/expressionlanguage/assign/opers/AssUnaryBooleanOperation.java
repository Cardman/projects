package code.expressionlanguage.assign.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.blocks.AssBlock;
import code.expressionlanguage.assign.util.AssignedVariables;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.assign.util.Assignment;
import code.expressionlanguage.assign.util.AssignmentsUtil;
import code.util.CustList;
import code.util.StringMap;

public final class AssUnaryBooleanOperation extends AssMethodOperation {
    AssUnaryBooleanOperation(ExecOperationNode _ex) {
        super(_ex);
    }

    @Override
    public void analyzeAssignmentAfter(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        AssOperationNode last_ = getFirstChild();
        StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(last_);
        CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(last_);
        CustList<StringMap<Assignment>> mutableAfterLast_ = vars_.getMutableLoop().getVal(last_);
        vars_.getFields().put(this, AssignmentsUtil.neg(fieldsAfterLast_));
        vars_.getVariables().put(this, AssignmentsUtil.neg(variablesAfterLast_));
        vars_.getMutableLoop().put(this, AssignmentsUtil.neg(mutableAfterLast_));
    }
}
