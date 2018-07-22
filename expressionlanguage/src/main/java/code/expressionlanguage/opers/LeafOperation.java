package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;

public abstract class LeafOperation extends OperationNode {

    LeafOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }
    public void analyzeNotBoolAssignmentAfter(Analyzable _conf) {
        analyzeAssignmentAfter(_conf, false);
    }

    public void analyzeAssignmentAfter(Analyzable _conf, boolean _bool) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        StringMap<Assignment> fieldsAfter_ = new StringMap<Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        CustList<StringMap<Assignment>> mutableAfter_ = new CustList<StringMap<Assignment>>();

        for (EntryCust<String, AssignmentBefore> e: vars_.getFieldsBefore().getVal(this).entryList()) {
            AssignmentBefore b_ = e.getValue();
            fieldsAfter_.put(e.getKey(), b_.assignAfter(_bool));
        }
        for (StringMap<AssignmentBefore> s: vars_.getVariablesBefore().getVal(this)) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                AssignmentBefore b_ = e.getValue();
                sm_.put(e.getKey(), b_.assignAfter(_bool));
            }
            variablesAfter_.add(sm_);
        }
        for (StringMap<AssignmentBefore> s: vars_.getMutableLoopBefore().getVal(this)) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                AssignmentBefore b_ = e.getValue();
                sm_.put(e.getKey(), b_.assignAfter(_bool));
            }
            mutableAfter_.add(sm_);
        }
        vars_.getFields().put(this, fieldsAfter_);
        vars_.getVariables().put(this, variablesAfter_);
        vars_.getMutableLoop().put(this, mutableAfter_);
    }

    @Override
    public final OperationNode getFirstChild() {
        return null;
    }
}
