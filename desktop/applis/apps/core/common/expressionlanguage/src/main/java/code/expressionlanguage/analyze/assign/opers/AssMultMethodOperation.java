package code.expressionlanguage.analyze.assign.opers;

import code.expressionlanguage.analyze.assign.blocks.AssBlock;
import code.expressionlanguage.analyze.assign.util.AssignedVariables;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.analyze.assign.util.Assignment;
import code.expressionlanguage.analyze.assign.util.AssignmentsUtil;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.util.StringMap;

public abstract class AssMultMethodOperation extends AssMethodOperation implements AssOperationNodeFull{

    protected AssMultMethodOperation(OperationNode _ex) {
        super(_ex);
    }

    public static void analyzeTrueAssignmentBeforeNextSibling(AssBlock _ass, AssignedVariablesBlock _a, AssOperationNode _nextSibling, AssOperationNode _previous) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        StringMap<Assignment> fieldsAfter_;
        StringMap<Assignment> variablesAfter_;
        fieldsAfter_ = vars_.getFields().getVal(_previous);
        variablesAfter_ = vars_.getVariables().getVal(_previous);
        vars_.getFieldsBefore().put(_nextSibling, AssignmentsUtil.assignWhenTrue(fieldsAfter_));
        vars_.getVariablesBefore().put(_nextSibling, AssignmentsUtil.assignWhenTrue(variablesAfter_));
    }
    public static void analyzeFalseAssignmentBeforeNextSibling(AssBlock _ass, AssignedVariablesBlock _a, AssOperationNode _nextSibling, AssOperationNode _previous) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        StringMap<Assignment> fieldsAfter_;
        StringMap<Assignment> variablesAfter_;
        fieldsAfter_ = vars_.getFields().getVal(_previous);
        variablesAfter_ = vars_.getVariables().getVal(_previous);
        vars_.getFieldsBefore().put(_nextSibling, AssignmentsUtil.assignWhenFalse(fieldsAfter_));
        vars_.getVariablesBefore().put(_nextSibling, AssignmentsUtil.assignWhenFalse(variablesAfter_));
    }
    public static void analyzeStdAssignmentBeforeNextSibling(AssBlock _ass, AssignedVariablesBlock _a, AssOperationNode _nextSibling, AssOperationNode _previous) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        StringMap<Assignment> fieldsAfter_;
        StringMap<Assignment> variablesAfter_;
        fieldsAfter_ = vars_.getFields().getVal(_previous);
        variablesAfter_ = vars_.getVariables().getVal(_previous);
        vars_.getFieldsBefore().put(_nextSibling, AssignmentsUtil.assignBefore(fieldsAfter_));
        vars_.getVariablesBefore().put(_nextSibling, AssignmentsUtil.assignBefore(variablesAfter_));
    }
    public final void tryAnalyzeAssignmentBeforeNextSibling(AssBlock _ass, AssignedVariablesBlock _a, AssOperationNode _nextSibling, AssOperationNode _previous) {
        analyzeAssignmentBeforeNextSibling(_ass,_a,_nextSibling, _previous);
    }
    public abstract void analyzeAssignmentBeforeNextSibling(AssBlock _ass, AssignedVariablesBlock _a, AssOperationNode _nextSibling, AssOperationNode _previous);

}
