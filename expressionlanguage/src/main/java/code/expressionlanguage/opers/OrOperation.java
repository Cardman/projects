package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.BooleanAssignment;
import code.util.CustList;
import code.util.EntryCust;
import code.util.NatTreeMap;
import code.util.StringMap;

public final class OrOperation extends QuickOperation {

    public OrOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _nextSibling, OperationNode _previous) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        StringMap<Assignment> fieldsAfter_;
        CustList<StringMap<Assignment>> variablesAfter_;
        fieldsAfter_ = vars_.getFields().getVal(_previous);
        variablesAfter_ = vars_.getVariables().getVal(_previous);
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
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<OperationNode> children_ = getChildrenNodes();
        OperationNode last_ = children_.last();
        OperationNode prev_ = children_.get(children_.size() - 2);
        StringMap<Assignment> fieldsAfter_ = new StringMap<Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(last_);
        CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(last_);
        StringMap<Assignment> fieldsAfterBefLast_ = vars_.getFields().getVal(prev_);
        CustList<StringMap<Assignment>> variablesAfterBefLast_ = vars_.getVariables().getVal(prev_);
        for (EntryCust<String, Assignment> e: fieldsAfterLast_.entryList()) {
            BooleanAssignment b_ = e.getValue().toBoolAssign();
            BooleanAssignment p_ = fieldsAfterBefLast_.getVal(e.getKey()).toBoolAssign();
            BooleanAssignment r_ = b_.or(p_);
            fieldsAfter_.put(e.getKey(), r_);
        }
        vars_.getFields().put(this, fieldsAfter_);
        for (StringMap<Assignment> s: variablesAfterLast_) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            int index_ = variablesAfter_.size();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                BooleanAssignment b_ = e.getValue().toBoolAssign();
                BooleanAssignment p_ = variablesAfterBefLast_.get(index_).getVal(e.getKey()).toBoolAssign();
                BooleanAssignment r_ = b_.or(p_);
                sm_.put(e.getKey(), r_);
            }
            variablesAfter_.add(sm_);
        }
        vars_.getVariables().put(this, variablesAfter_);
    }
    @Override
    boolean absorbingValue() {
        return true;
    }

}
