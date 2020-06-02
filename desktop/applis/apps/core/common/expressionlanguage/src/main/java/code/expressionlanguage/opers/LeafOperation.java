package code.expressionlanguage.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentsUtil;
import code.util.CustList;
import code.util.StringMap;

public abstract class LeafOperation extends OperationNode {

    protected LeafOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }
    public void analyzeNotBoolAssignmentAfter(ContextEl _conf) {
        analyzeAssignmentAfter(_conf, false);
    }

    @Override
    public void analyzeAssignmentAfter(ContextEl _conf) {
        boolean isBool_;
        isBool_ = getResultClass().isBoolType(_conf);
        analyzeAssignmentAfter(_conf, isBool_);
    }

    public void analyzeAssignmentAfter(ContextEl _conf, boolean _bool) {
        Block block_ = _conf.getAnalyzing().getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        StringMap<Assignment> fieldsAfter_ = new StringMap<Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        CustList<StringMap<Assignment>> mutableAfter_ = new CustList<StringMap<Assignment>>();

        fieldsAfter_.putAllMap(AssignmentsUtil.assignAfter(_bool,vars_.getFieldsBefore().getVal(this)));
        variablesAfter_.addAllElts(AssignmentsUtil.assignAfter(_bool,vars_.getVariablesBefore().getVal(this)));
        mutableAfter_.addAllElts(AssignmentsUtil.assignAfter(_bool,vars_.getMutableLoopBefore().getVal(this)));
        vars_.getFields().put(this, fieldsAfter_);
        vars_.getVariables().put(this, variablesAfter_);
        vars_.getMutableLoop().put(this, mutableAfter_);
    }

    @Override
    public final OperationNode getFirstChild() {
        return null;
    }
}
