package code.expressionlanguage.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentsUtil;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;
import code.util.*;
import code.util.StringMap;

public final class OrOperation extends QuickOperation {

    public OrOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyzeAssignmentBeforeNextSibling(ContextEl _conf,
            OperationNode _nextSibling, OperationNode _previous) {
        analyzeFalseAssignmentBeforeNextSibling(_conf, _nextSibling, _previous);
    }

    @Override
    public void analyzeAssignmentAfter(ContextEl _conf) {
        Block block_ = _conf.getAnalyzing().getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<OperationNode> children_ = getChildrenNodes();
        OperationNode last_ = children_.last();
        OperationNode prev_ = children_.get(children_.size() - 2);
        StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(last_);
        CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(last_);
        CustList<StringMap<Assignment>> mutableAfterLast_ = vars_.getMutableLoop().getVal(last_);
        StringMap<Assignment> fieldsAfterBefLast_ = vars_.getFields().getVal(prev_);
        CustList<StringMap<Assignment>> variablesAfterBefLast_ = vars_.getVariables().getVal(prev_);
        CustList<StringMap<Assignment>> mutableAfterBefLast_ = vars_.getMutableLoop().getVal(prev_);
        vars_.getFields().put(this, AssignmentsUtil.or(fieldsAfterLast_,fieldsAfterBefLast_));
        vars_.getVariables().put(this, AssignmentsUtil.or(variablesAfterLast_,variablesAfterBefLast_));
        vars_.getMutableLoop().put(this, AssignmentsUtil.or(mutableAfterLast_,mutableAfterBefLast_));
    }
    @Override
    public BooleanStruct absorbingStruct() {
        return BooleanStruct.of(true);
    }
}
