package code.expressionlanguage.analyze.assign.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.assign.blocks.AssBlock;
import code.expressionlanguage.analyze.assign.util.AssignedVariables;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.analyze.assign.util.Assignment;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;

public final class AssTernaryOperation extends AssMultMethodOperation {
    AssTernaryOperation(OperationNode _ex) {
        super(_ex);
    }

    @Override
    public void analyzeAssignmentBeforeNextSibling(AssBlock _ass, AssignedVariablesBlock _a, AssOperationNode _nextSibling, AssOperationNode _previous) {
        AssOperationNode firstChild_ = getFirstChild();
        if (firstChild_ == _previous) {
            analyzeTrueAssignmentBeforeNextSibling(_ass,_a, _nextSibling, firstChild_);
        } else {
            analyzeFalseAssignmentBeforeNextSibling(_ass,_a, _nextSibling, firstChild_);
        }
    }

    @Override
    public void analyzeAssignmentAfter(AssBlock _ass, AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        CustList<AssOperationNode> children_ = getChildrenNodes();
        StringMap<Assignment> fieldsAfter_ = new StringMap<Assignment>();
        StringMap<Assignment> variablesAfter_ = new StringMap<Assignment>();
        AssOperationNode last_ = children_.last();
        StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(last_);
        StringMap<Assignment> variablesAfterLast_ = vars_.getVariables().getVal(last_);

        AssOperationNode befLast_ = children_.get(children_.size() - 2);
        StringMap<Assignment> fieldsAfterBefLast_ = vars_.getFields().getVal(befLast_);
        StringMap<Assignment> variablesAfterBefLast_ = vars_.getVariables().getVal(befLast_);
        boolean toBoolean_ = getResultClass().isBoolType(_page);
        for (EntryCust<String, Assignment> e: fieldsAfterLast_.entryList()) {
            Assignment b_ = e.getValue();
            Assignment p_ = fieldsAfterBefLast_.getVal(e.getKey());
            Assignment r_ = Assignment.ternary(p_, b_, toBoolean_);
            fieldsAfter_.put(e.getKey(), r_);
        }
        for (EntryCust<String, Assignment> e: variablesAfterLast_.entryList()) {
            Assignment b_ = e.getValue();
            Assignment p_ = variablesAfterBefLast_.getVal(e.getKey());
            Assignment r_ = Assignment.ternary(p_, b_, toBoolean_);
            variablesAfter_.put(e.getKey(), r_);
        }
        vars_.getFields().put(this, fieldsAfter_);
        vars_.getVariables().put(this, variablesAfter_);
    }
}
