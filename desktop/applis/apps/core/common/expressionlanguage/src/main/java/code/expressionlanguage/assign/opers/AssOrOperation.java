package code.expressionlanguage.assign.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.assign.blocks.AssBlock;
import code.expressionlanguage.assign.util.AssignedVariables;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.assign.util.Assignment;
import code.expressionlanguage.assign.util.AssignmentsUtil;
import code.util.CustList;
import code.util.StringMap;

public final class AssOrOperation extends AssMultMethodOperation {
    AssOrOperation(OperationNode _ex) {
        super(_ex);
    }

    @Override
    public void analyzeAssignmentBeforeNextSibling(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a, AssOperationNode _nextSibling, AssOperationNode _previous) {
        analyzeFalseAssignmentBeforeNextSibling(_conf,_ass,_a, _nextSibling, _previous);
    }

    @Override
    public void analyzeAssignmentAfter(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        CustList<AssOperationNode> children_ = getChildrenNodes();
        AssOperationNode last_ = children_.last();
        AssOperationNode prev_ = children_.get(children_.size() - 2);
        StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(last_);
        StringMap<Assignment> variablesAfterLast_ = vars_.getVariables().getVal(last_);
        StringMap<Assignment> fieldsAfterBefLast_ = vars_.getFields().getVal(prev_);
        StringMap<Assignment> variablesAfterBefLast_ = vars_.getVariables().getVal(prev_);
        vars_.getFields().put(this, AssignmentsUtil.or(fieldsAfterLast_,fieldsAfterBefLast_));
        vars_.getVariables().put(this, AssignmentsUtil.or(variablesAfterLast_,variablesAfterBefLast_));
    }
}
