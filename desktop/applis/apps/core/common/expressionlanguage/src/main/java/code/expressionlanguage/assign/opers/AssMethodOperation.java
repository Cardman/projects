package code.expressionlanguage.assign.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.blocks.AssBlock;
import code.expressionlanguage.assign.util.AssignedVariables;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.assign.util.Assignment;
import code.expressionlanguage.assign.util.AssignmentBefore;
import code.expressionlanguage.assign.util.AssignmentsUtil;
import code.util.CustList;
import code.util.StringMap;

public abstract class AssMethodOperation extends AssOperationNode {

    private AssOperationNode firstChild;
    AssMethodOperation(ExecOperationNode _ex) {
        super(_ex);
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

    public final void tryAnalyzeAssignmentBefore(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a, AssOperationNode _firstChild) {
        analyzeAssignmentBefore(_conf, _ass,_a,_firstChild);
    }
    public void analyzeAssignmentBefore(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a, AssOperationNode _firstChild) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        StringMap<AssignmentBefore> fields_;
        CustList<StringMap<AssignmentBefore>> variables_;
        CustList<StringMap<AssignmentBefore>> mutable_;
        fields_ = vars_.getFieldsBefore().getVal(this);
        variables_ = vars_.getVariablesBefore().getVal(this);
        mutable_ = vars_.getMutableLoopBefore().getVal(this);
        vars_.getFieldsBefore().put(_firstChild, fields_);
        vars_.getVariablesBefore().put(_firstChild, variables_);
        vars_.getMutableLoopBefore().put(_firstChild, mutable_);
    }

    public void analyzeStdAssignmentAfter(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        CustList<AssOperationNode> children_ = getChildrenNodes();
        CustList<AssOperationNode> filter_ = AssUtil.filterInvoking(children_);
        StringMap<Assignment> fieldsAfter_ = new StringMap<Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        CustList<StringMap<Assignment>> mutableAfter_ = new CustList<StringMap<Assignment>>();
        boolean isBool_;
        isBool_ = getResultClass().isBoolType(_conf);
        if (filter_.isEmpty()) {
            fieldsAfter_.putAllMap(AssignmentsUtil.assignAfter(isBool_,vars_.getFieldsBefore().getVal(this)));
            variablesAfter_.addAllElts(AssignmentsUtil.assignAfter(isBool_,vars_.getVariablesBefore().getVal(this)));
            mutableAfter_.addAllElts(AssignmentsUtil.assignAfter(isBool_,vars_.getMutableLoopBefore().getVal(this)));
            vars_.getFields().put(this, fieldsAfter_);
            vars_.getVariables().put(this, variablesAfter_);
            vars_.getMutableLoop().put(this, mutableAfter_);
            return;
        }
        AssOperationNode last_ = filter_.last();
        StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(last_);
        CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(last_);
        CustList<StringMap<Assignment>> mutableAfterLast_ = vars_.getMutableLoop().getVal(last_);
        fieldsAfter_.putAllMap(AssignmentsUtil.assignGene(isBool_,fieldsAfterLast_));
        variablesAfter_.addAllElts(AssignmentsUtil.assignGene(isBool_,variablesAfterLast_));
        mutableAfter_.addAllElts(AssignmentsUtil.assignGene(isBool_,mutableAfterLast_));
        vars_.getFields().put(this, fieldsAfter_);
        vars_.getVariables().put(this, variablesAfter_);
        vars_.getMutableLoop().put(this, mutableAfter_);
    }
    @Override
    public final AssOperationNode getFirstChild() {
        return firstChild;
    }


}
