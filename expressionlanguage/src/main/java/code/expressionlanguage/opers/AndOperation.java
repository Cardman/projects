package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentsUtil;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;
import code.util.*;
import code.util.StringMap;

public final class AndOperation extends QuickOperation {

    public AndOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

    @Override
    public BooleanStruct absorbingStruct() {
        return new BooleanStruct(false);
    }

    @Override
    public void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _nextSibling, OperationNode _previous) {
        analyzeTrueAssignmentBeforeNextSibling(_conf, _nextSibling, _previous);
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
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
        vars_.getFields().put(this, AssignmentsUtil.and(fieldsAfterLast_,fieldsAfterBefLast_));
        vars_.getVariables().put(this, AssignmentsUtil.and(variablesAfterLast_,variablesAfterBefLast_));
        vars_.getMutableLoop().put(this, AssignmentsUtil.and(mutableAfterLast_,mutableAfterBefLast_));
    }

}
