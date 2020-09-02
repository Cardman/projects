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

public abstract class AssMultMethodOperation extends AssMethodOperation {

    AssMultMethodOperation(OperationNode _ex) {
        super(_ex);
    }

    public static void analyzeTrueAssignmentBeforeNextSibling(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a, AssOperationNode _nextSibling, AssOperationNode _previous) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        StringMap<Assignment> fieldsAfter_;
        StringMap<Assignment> variablesAfter_;
        fieldsAfter_ = vars_.getFields().getVal(_previous);
        variablesAfter_ = vars_.getVariables().getVal(_previous);
        vars_.getFieldsBefore().put(_nextSibling, AssignmentsUtil.assignWhenTrue(fieldsAfter_));
        vars_.getVariablesBefore().put(_nextSibling, AssignmentsUtil.assignWhenTrue(variablesAfter_));
    }
    public static void analyzeFalseAssignmentBeforeNextSibling(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a, AssOperationNode _nextSibling, AssOperationNode _previous) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        StringMap<Assignment> fieldsAfter_;
        StringMap<Assignment> variablesAfter_;
        fieldsAfter_ = vars_.getFields().getVal(_previous);
        variablesAfter_ = vars_.getVariables().getVal(_previous);
        vars_.getFieldsBefore().put(_nextSibling, AssignmentsUtil.assignWhenFalse(fieldsAfter_));
        vars_.getVariablesBefore().put(_nextSibling, AssignmentsUtil.assignWhenFalse(variablesAfter_));
    }
    public static void analyzeStdAssignmentBeforeNextSibling(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a, AssOperationNode _nextSibling, AssOperationNode _previous) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        StringMap<Assignment> fieldsAfter_;
        StringMap<Assignment> variablesAfter_;
        fieldsAfter_ = vars_.getFields().getVal(_previous);
        variablesAfter_ = vars_.getVariables().getVal(_previous);
        vars_.getFieldsBefore().put(_nextSibling, AssignmentsUtil.assignBefore(fieldsAfter_));
        vars_.getVariablesBefore().put(_nextSibling, AssignmentsUtil.assignBefore(variablesAfter_));
    }
    public final void tryAnalyzeAssignmentBeforeNextSibling(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a, AssOperationNode _nextSibling, AssOperationNode _previous) {
        analyzeAssignmentBeforeNextSibling(_conf, _ass,_a,_nextSibling, _previous);
    }
    public abstract void analyzeAssignmentBeforeNextSibling(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a, AssOperationNode _nextSibling, AssOperationNode _previous);

}
