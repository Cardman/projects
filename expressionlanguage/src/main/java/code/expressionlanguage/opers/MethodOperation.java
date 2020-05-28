package code.expressionlanguage.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.exec.ParentOperable;
import code.expressionlanguage.opers.exec.ReductibleOperable;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.AssignmentsUtil;
import code.util.CustList;
import code.util.*;
import code.util.StringMap;

public abstract class MethodOperation extends OperationNode implements ReductibleOperable, ParentOperable {

    private OperationNode firstChild;

    private IntTreeMap<String> children;

    public MethodOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        children = new IntTreeMap<String>();
        calculateChildren();
    }

    public final void tryAnalyzeAssignmentBefore(ContextEl _conf, OperationNode _firstChild) {
        analyzeAssignmentBefore(_conf, _firstChild);
    }
    public void analyzeAssignmentBefore(ContextEl _conf, OperationNode _firstChild) {
        Block block_ = _conf.getAnalyzing().getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
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
    public final void tryAnalyzeAssignmentBeforeNextSibling(ContextEl _conf, OperationNode _nextSibling, OperationNode _previous) {
        analyzeAssignmentBeforeNextSibling(_conf, _nextSibling, _previous);
    }
    public abstract void analyzeAssignmentBeforeNextSibling(ContextEl _conf, OperationNode _nextSibling, OperationNode _previous);
    public static void analyzeTrueAssignmentBeforeNextSibling(ContextEl _conf, OperationNode _nextSibling, OperationNode _previous) {
        Block block_ = _conf.getAnalyzing().getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        StringMap<Assignment> fieldsAfter_;
        CustList<StringMap<Assignment>> variablesAfter_;
        CustList<StringMap<Assignment>> mutableAfter_;
        fieldsAfter_ = vars_.getFields().getVal(_previous);
        variablesAfter_ = vars_.getVariables().getVal(_previous);
        mutableAfter_ = vars_.getMutableLoop().getVal(_previous);
        vars_.getFieldsBefore().put(_nextSibling, AssignmentsUtil.assignWhenTrue(fieldsAfter_));
        vars_.getVariablesBefore().put(_nextSibling, AssignmentsUtil.assignWhenTrue(variablesAfter_));
        vars_.getMutableLoopBefore().put(_nextSibling, AssignmentsUtil.assignWhenTrue(mutableAfter_));
    }
    public static void analyzeFalseAssignmentBeforeNextSibling(ContextEl _conf, OperationNode _nextSibling, OperationNode _previous) {
        Block block_ = _conf.getAnalyzing().getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        StringMap<Assignment> fieldsAfter_;
        CustList<StringMap<Assignment>> variablesAfter_;
        CustList<StringMap<Assignment>> mutableAfter_;
        fieldsAfter_ = vars_.getFields().getVal(_previous);
        variablesAfter_ = vars_.getVariables().getVal(_previous);
        mutableAfter_ = vars_.getMutableLoop().getVal(_previous);
        vars_.getFieldsBefore().put(_nextSibling, AssignmentsUtil.assignWhenFalse(fieldsAfter_));
        vars_.getVariablesBefore().put(_nextSibling, AssignmentsUtil.assignWhenFalse(variablesAfter_));
        vars_.getMutableLoopBefore().put(_nextSibling, AssignmentsUtil.assignWhenFalse(mutableAfter_));
    }
    public static void analyzeStdAssignmentBeforeNextSibling(ContextEl _conf, OperationNode _nextSibling, OperationNode _previous) {
        Block block_ = _conf.getAnalyzing().getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        StringMap<Assignment> fieldsAfter_;
        CustList<StringMap<Assignment>> variablesAfter_;
        CustList<StringMap<Assignment>> mutableAfter_;
        fieldsAfter_ = vars_.getFields().getVal(_previous);
        variablesAfter_ = vars_.getVariables().getVal(_previous);
        mutableAfter_ = vars_.getMutableLoop().getVal(_previous);
        vars_.getFieldsBefore().put(_nextSibling, AssignmentsUtil.assignBefore(fieldsAfter_));
        vars_.getVariablesBefore().put(_nextSibling, AssignmentsUtil.assignBefore(variablesAfter_));
        vars_.getMutableLoopBefore().put(_nextSibling, AssignmentsUtil.assignBefore(mutableAfter_));
    }
    public void analyzeStdAssignmentAfter(ContextEl _conf) {
        Block block_ = _conf.getAnalyzing().getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<OperationNode> children_ = getChildrenNodes();
        CustList<OperationNode> filter_ = ElUtil.filterInvoking(children_);
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
        OperationNode last_ = filter_.last();
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
    public void tryCalculateNode(ContextEl _conf) {
        tryCalculateNode(this, _conf);
    }
    public static void tryCalculateNode(MethodOperation _par, ContextEl _conf) {
        CustList<Operable> children_ = _par.getChildrenOperable();
        for (Operable o: children_) {
            if (o.getArgument() == null) {
                return;
            }
        }
        _par.quickCalculate(_conf);
    }
    public void quickCalculate(ContextEl _conf) {
    }

    public final void appendChild(OperationNode _child) {
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        OperationNode child_ = firstChild;
        while (true) {
            OperationNode sibling_ = child_.getNextSibling();
            if (sibling_ == null) {
                child_.setNextSibling(_child);
                return;
            }
            child_ = sibling_;
        }
    }

    public final CustList<Operable> getChildrenOperable() {
        CustList<Operable> list_ = new CustList<Operable>();
        OperationNode elt_ = getFirstChild();
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }
    public final CustList<OperationNode> getChildrenNodes() {
        CustList<OperationNode> list_ = new CustList<OperationNode>();
        OperationNode elt_ = getFirstChild();
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }

    abstract void calculateChildren();

    @Override
    public final OperationNode getFirstChild() {
        return firstChild;
    }

    public final IntTreeMap< String> getChildren() {
        return children;
    }

}
