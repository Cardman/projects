package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.exec.ReductibleOperable;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.BooleanAssignment;
import code.util.CustList;
import code.util.EntryCust;
import code.util.NatTreeMap;
import code.util.StringMap;

public abstract class MethodOperation extends OperationNode implements ReductibleOperable {

    private OperationNode firstChild;

    private NatTreeMap<Integer,String> children;

    public MethodOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        children = new NatTreeMap<Integer,String>();
        calculateChildren();
    }

    public final void tryAnalyzeAssignmentBefore(Analyzable _conf, OperationNode _firstChild) {
        Block currentBlock_ = _conf.getCurrentBlock();
        if (currentBlock_  == null) {
            return;
        }
        analyzeAssignmentBefore(_conf, _firstChild);
    }
    public void analyzeAssignmentBefore(Analyzable _conf, OperationNode _firstChild) {
        Block block_ = _conf.getCurrentBlock();
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
    public final void tryAnalyzeAssignmentBeforeNextSibling(Analyzable _conf, OperationNode _nextSibling, OperationNode _previous) {
        Block currentBlock_ = _conf.getCurrentBlock();
        if (currentBlock_  == null) {
            return;
        }
        analyzeAssignmentBeforeNextSibling(_conf, _nextSibling, _previous);
    }
    public abstract void analyzeAssignmentBeforeNextSibling(Analyzable _conf, OperationNode _nextSibling, OperationNode _previous);
    public static void analyzeTrueAssignmentBeforeNextSibling(Analyzable _conf, OperationNode _nextSibling, OperationNode _previous) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        StringMap<Assignment> fieldsAfter_;
        CustList<StringMap<Assignment>> variablesAfter_;
        CustList<StringMap<Assignment>> mutableAfter_;
        fieldsAfter_ = vars_.getFields().getVal(_previous);
        variablesAfter_ = vars_.getVariables().getVal(_previous);
        mutableAfter_ = vars_.getMutableLoop().getVal(_previous);
        StringMap<AssignmentBefore> fieldsBefore_ = new StringMap<AssignmentBefore>();
        for (EntryCust<String, Assignment> e: fieldsAfter_.entryList()) {
            BooleanAssignment b_ = e.getValue().toBoolAssign();
            AssignmentBefore a_ = b_.copyWhenTrue();
            fieldsBefore_.put(e.getKey(), a_);
        }
        vars_.getFieldsBefore().put(_nextSibling, fieldsBefore_);
        CustList<StringMap<AssignmentBefore>> variablesBefore_ = new CustList<StringMap<AssignmentBefore>>();
        for (StringMap<Assignment> s: variablesAfter_) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                BooleanAssignment b_ = e.getValue().toBoolAssign();
                AssignmentBefore a_ = b_.copyWhenTrue();
                sm_.put(e.getKey(), a_);
            }
            variablesBefore_.add(sm_);
        }
        vars_.getVariablesBefore().put(_nextSibling, variablesBefore_);
        CustList<StringMap<AssignmentBefore>> mutableBefore_ = new CustList<StringMap<AssignmentBefore>>();
        for (StringMap<Assignment> s: mutableAfter_) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                BooleanAssignment b_ = e.getValue().toBoolAssign();
                AssignmentBefore a_ = b_.copyWhenTrue();
                sm_.put(e.getKey(), a_);
            }
            mutableBefore_.add(sm_);
        }
        vars_.getMutableLoopBefore().put(_nextSibling, mutableBefore_);
    }
    public static void analyzeFalseAssignmentBeforeNextSibling(Analyzable _conf, OperationNode _nextSibling, OperationNode _previous) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        StringMap<Assignment> fieldsAfter_;
        CustList<StringMap<Assignment>> variablesAfter_;
        CustList<StringMap<Assignment>> mutableAfter_;
        fieldsAfter_ = vars_.getFields().getVal(_previous);
        variablesAfter_ = vars_.getVariables().getVal(_previous);
        mutableAfter_ = vars_.getMutableLoop().getVal(_previous);
        StringMap<AssignmentBefore> fieldsBefore_ = new StringMap<AssignmentBefore>();
        for (EntryCust<String, Assignment> e: fieldsAfter_.entryList()) {
            BooleanAssignment b_ = e.getValue().toBoolAssign();
            AssignmentBefore a_ = b_.copyWhenFalse();
            fieldsBefore_.put(e.getKey(), a_);
        }
        vars_.getFieldsBefore().put(_nextSibling, fieldsBefore_);
        CustList<StringMap<AssignmentBefore>> variablesBefore_ = new CustList<StringMap<AssignmentBefore>>();
        for (StringMap<Assignment> s: variablesAfter_) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                BooleanAssignment b_ = e.getValue().toBoolAssign();
                AssignmentBefore a_ = b_.copyWhenFalse();
                sm_.put(e.getKey(), a_);
            }
            variablesBefore_.add(sm_);
        }
        vars_.getVariablesBefore().put(_nextSibling, variablesBefore_);
        CustList<StringMap<AssignmentBefore>> mutableBefore_ = new CustList<StringMap<AssignmentBefore>>();
        for (StringMap<Assignment> s: mutableAfter_) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                BooleanAssignment b_ = e.getValue().toBoolAssign();
                AssignmentBefore a_ = b_.copyWhenFalse();
                sm_.put(e.getKey(), a_);
            }
            mutableBefore_.add(sm_);
        }
        vars_.getMutableLoopBefore().put(_nextSibling, mutableBefore_);
    }
    public static void analyzeStdAssignmentBeforeNextSibling(Analyzable _conf, OperationNode _nextSibling, OperationNode _previous) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        StringMap<Assignment> fieldsAfter_;
        CustList<StringMap<Assignment>> variablesAfter_;
        CustList<StringMap<Assignment>> mutableAfter_;
        fieldsAfter_ = vars_.getFields().getVal(_previous);
        variablesAfter_ = vars_.getVariables().getVal(_previous);
        mutableAfter_ = vars_.getMutableLoop().getVal(_previous);
        StringMap<AssignmentBefore> fieldsBefore_ = new StringMap<AssignmentBefore>();
        CustList<StringMap<AssignmentBefore>> variablesBefore_ = new CustList<StringMap<AssignmentBefore>>();
        CustList<StringMap<AssignmentBefore>> mutableBefore_ = new CustList<StringMap<AssignmentBefore>>();
        //TODO null fieldsAfter_ when local vars: a = value: duplicate field
        for (EntryCust<String, Assignment> e: fieldsAfter_.entryList()) {
            Assignment b_ = e.getValue();
            fieldsBefore_.put(e.getKey(), b_.assignBefore());
        }
        vars_.getFieldsBefore().put(_nextSibling, fieldsBefore_);

        for (StringMap<Assignment> s: variablesAfter_) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment b_ = e.getValue();
                sm_.put(e.getKey(), b_.assignBefore());
            }
            variablesBefore_.add(sm_);
        }
        vars_.getVariablesBefore().put(_nextSibling, variablesBefore_);
        for (StringMap<Assignment> s: mutableAfter_) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment b_ = e.getValue();
                sm_.put(e.getKey(), b_.assignBefore());
            }
            mutableBefore_.add(sm_);
        }
        vars_.getMutableLoopBefore().put(_nextSibling, mutableBefore_);
    }
    public void analyzeStdAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<OperationNode> children_ = getChildrenNodes();
        CustList<OperationNode> filter_ = ElUtil.filterInvoking(children_);
        StringMap<Assignment> fieldsAfter_ = new StringMap<Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        CustList<StringMap<Assignment>> mutableAfter_ = new CustList<StringMap<Assignment>>();
        boolean isBool_;
        isBool_ = getResultClass().isBoolType(_conf);
        if (filter_.isEmpty()) {
            for (EntryCust<String, AssignmentBefore> e: vars_.getFieldsBefore().getVal(this).entryList()) {
                AssignmentBefore b_ = e.getValue();
                fieldsAfter_.put(e.getKey(), b_.assignAfter(isBool_));
            }
            for (StringMap<AssignmentBefore> s: vars_.getVariablesBefore().getVal(this)) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                    AssignmentBefore b_ = e.getValue();
                    sm_.put(e.getKey(), b_.assignAfter(isBool_));
                }
                variablesAfter_.add(sm_);
            }
            for (StringMap<AssignmentBefore> s: vars_.getMutableLoopBefore().getVal(this)) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                    AssignmentBefore b_ = e.getValue();
                    sm_.put(e.getKey(), b_.assignAfter(isBool_));
                }
                mutableAfter_.add(sm_);
            }
            vars_.getFields().put(this, fieldsAfter_);
            vars_.getVariables().put(this, variablesAfter_);
            vars_.getMutableLoop().put(this, mutableAfter_);
            return;
        }
        OperationNode last_ = filter_.last();
        StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(last_);
        CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(last_);
        CustList<StringMap<Assignment>> mutableAfterLast_ = vars_.getMutableLoop().getVal(last_);
        for (EntryCust<String, Assignment> e: fieldsAfterLast_.entryList()) {
            Assignment b_ = e.getValue();
            fieldsAfter_.put(e.getKey(), b_.assign(isBool_));
        }
        for (StringMap<Assignment> s: variablesAfterLast_) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment b_ = e.getValue();
                sm_.put(e.getKey(), b_.assign(isBool_));
            }
            variablesAfter_.add(sm_);
        }
        for (StringMap<Assignment> s: mutableAfterLast_) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment b_ = e.getValue();
                sm_.put(e.getKey(), b_.assign(isBool_));
            }
            mutableAfter_.add(sm_);
        }
        vars_.getFields().put(this, fieldsAfter_);
        vars_.getVariables().put(this, variablesAfter_);
        vars_.getMutableLoop().put(this, mutableAfter_);
    }
    @Override
    public void tryCalculateNode(Analyzable _conf) {
        CustList<OperationNode> children_ = getChildrenNodes();
        for (OperationNode o: children_) {
            if (o.getArgument() == null) {
                return;
            }
        }
        quickCalculate(_conf);
    }
    public void quickCalculate(Analyzable _conf) {
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

    @Override
    public final CustList<Operable> getChildrenOperable() {
        CustList<Operable> list_ = new CustList<Operable>();
        OperationNode firstChild_ = getFirstChild();
        OperationNode elt_ = firstChild_;
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }
    public final CustList<OperationNode> getChildrenNodes() {
        CustList<OperationNode> list_ = new CustList<OperationNode>();
        OperationNode firstChild_ = getFirstChild();
        OperationNode elt_ = firstChild_;
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

    public final NatTreeMap<Integer, String> getChildren() {
        return children;
    }
}
