package code.expressionlanguage.analyze.assign.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.assign.blocks.AssBlock;
import code.expressionlanguage.analyze.assign.util.AssignedVariables;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.analyze.assign.util.Assignment;
import code.expressionlanguage.analyze.assign.util.AssignmentsUtil;
import code.util.CustList;
import code.util.StringMap;

public final class AssOrOperation extends AssMultMethodOperation {
    AssOrOperation(OperationNode _ex) {
        super(_ex);
    }

    @Override
    public void analyzeAssignmentBeforeNextSibling(AssBlock _ass, AssignedVariablesBlock _a, AssOperationNode _nextSibling, AssOperationNode _previous) {
        analyzeFalseAssignmentBeforeNextSibling(_ass,_a, _nextSibling, _previous);
    }

    @Override
    public void analyzeAssignmentAfter(AssBlock _ass, AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        AssignedVariables varsOr_ = _a.getFinalVariables().getVal(_ass);
        CustList<AssOperationNode> childrenOr_ = getChildrenNodes();
        AssOperationNode last_ = childrenOr_.last();
        AssOperationNode prev_ = childrenOr_.get(childrenOr_.size() - 2);
        StringMap<Assignment> fieldsAfterLast_ = varsOr_.getFields().getVal(last_);
        StringMap<Assignment> variablesAfterLast_ = varsOr_.getVariables().getVal(last_);
        StringMap<Assignment> fieldsAfterBefLast_ = varsOr_.getFields().getVal(prev_);
        StringMap<Assignment> variablesAfterBefLast_ = varsOr_.getVariables().getVal(prev_);
        varsOr_.getFields().put(this, AssignmentsUtil.or(fieldsAfterLast_,fieldsAfterBefLast_));
        varsOr_.getVariables().put(this, AssignmentsUtil.or(variablesAfterLast_,variablesAfterBefLast_));
    }
}
