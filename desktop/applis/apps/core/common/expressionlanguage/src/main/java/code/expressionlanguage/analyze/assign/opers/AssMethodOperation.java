package code.expressionlanguage.analyze.assign.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.CastOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.assign.blocks.AssBlock;
import code.expressionlanguage.analyze.assign.util.AssignedVariables;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.analyze.assign.util.Assignment;
import code.expressionlanguage.analyze.assign.util.AssignmentBefore;
import code.expressionlanguage.analyze.assign.util.AssignmentsUtil;
import code.util.CustList;
import code.util.StringMap;

public abstract class AssMethodOperation extends AssOperationNode {

    private AssOperationNode firstChild;
    private boolean cast;
    AssMethodOperation(OperationNode _ex) {
        super(_ex);
        cast = _ex instanceof CastOperation;
    }

    public boolean isCast() {
        return cast;
    }

    public final void appendChild(AssOperationNode _child) {
        _child.setParent(this);
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        AssOperationNode child_ = firstChild;
        while (true) {
            AssOperationNode sibling_ = child_.getNextSibling();
            if (sibling_ == null) {
                child_.setNextSibling(_child);
                return;
            }
            child_ = sibling_;
        }
    }

    public final CustList<AssOperationNode> getChildrenNodes() {
        CustList<AssOperationNode> list_ = new CustList<AssOperationNode>();
        AssOperationNode elt_ = getFirstChild();
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }

    public final void tryAnalyzeAssignmentBefore(AssBlock _ass, AssignedVariablesBlock _a, AssOperationNode _firstChild) {
        analyzeAssignmentBefore(_ass,_a,_firstChild);
    }
    public void analyzeAssignmentBefore(AssBlock _ass, AssignedVariablesBlock _a, AssOperationNode _firstChild) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        StringMap<AssignmentBefore> fields_;
        StringMap<AssignmentBefore> variables_;
        fields_ = vars_.getFieldsBefore().getVal(this);
        variables_ = vars_.getVariablesBefore().getVal(this);
        vars_.getFieldsBefore().put(_firstChild, fields_);
        vars_.getVariablesBefore().put(_firstChild, variables_);
    }

    public void analyzeStdAssignmentAfter(AssBlock _ass, AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        CustList<AssOperationNode> children_ = getChildrenNodes();
        StringMap<Assignment> fieldsAfter_ = new StringMap<Assignment>();
        StringMap<Assignment> variablesAfter_ = new StringMap<Assignment>();
        boolean isBool_;
        isBool_ = getResultClass().isBoolType(_page);
        if (children_.isEmpty()) {
            fieldsAfter_.putAllMap(AssignmentsUtil.assignAfter(isBool_,vars_.getFieldsBefore().getVal(this)));
            variablesAfter_.putAllMap(AssignmentsUtil.assignAfter(isBool_,vars_.getVariablesBefore().getVal(this)));
            vars_.getFields().put(this, fieldsAfter_);
            vars_.getVariables().put(this, variablesAfter_);
            return;
        }
        AssOperationNode last_ = children_.last();
        StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(last_);
        StringMap<Assignment> variablesAfterLast_ = vars_.getVariables().getVal(last_);
        fieldsAfter_.putAllMap(AssignmentsUtil.assignGene(isBool_,fieldsAfterLast_));
        variablesAfter_.putAllMap(AssignmentsUtil.assignGene(isBool_,variablesAfterLast_));
        vars_.getFields().put(this, fieldsAfter_);
        vars_.getVariables().put(this, variablesAfter_);
    }
    @Override
    public final AssOperationNode getFirstChild() {
        return firstChild;
    }


}
