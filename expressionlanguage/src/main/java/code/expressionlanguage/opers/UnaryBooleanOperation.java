package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.UnexpectedTypeOperationError;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.exec.ParentOperable;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentsUtil;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.*;
import code.util.StringMap;

public final class UnaryBooleanOperation extends AbstractUnaryOperation {

    public UnaryBooleanOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyzeUnary(Analyzable _conf) {
        LgNames stds_ = _conf.getStandards();
        String booleanPrimType_ = stds_.getAliasPrimBoolean();
        OperationNode child_ = getFirstChild();
        ClassArgumentMatching clMatch_;
        clMatch_ = child_.getResultClass();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
        String booleanType_ = stds_.getAliasBoolean();
        if (!clMatch_.isBoolType(_conf)) {
            ClassArgumentMatching cl_ = child_.getResultClass();
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setIndexFile(_conf.getCurrentLocationIndex());
            un_.setFileName(_conf.getCurrentFileName());
            un_.setExpectedResult(booleanType_);
            un_.setOperands(cl_);
            _conf.getClasses().addError(un_);
        }
        clMatch_.setUnwrapObject(booleanPrimType_);
        child_.cancelArgument();
        setResultClass(new ClassArgumentMatching(booleanPrimType_));
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        tryGetArg(this,_conf);
    }

    public static void tryGetArg(ParentOperable _par, Analyzable _conf) {
        CustList<Operable> chidren_ = _par.getChildrenOperable();
        Argument arg_ = chidren_.first().getArgument();
        Struct value_ = arg_.getStruct();
        if (!(value_ instanceof BooleanStruct)) {
            return;
        }
        BooleanStruct o_ = (BooleanStruct) value_;
        Boolean b_ = o_.getInstance();
        b_ = !b_;
        Argument a_ = new Argument();
        a_.setObject(b_);
        _par.setSimpleArgumentAna(a_, _conf);
    }
    @Override
    void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        OperationNode last_ = getFirstChild();
        StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(last_);
        CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(last_);
        CustList<StringMap<Assignment>> mutableAfterLast_ = vars_.getVariables().getVal(last_);
        vars_.getFields().put(this, AssignmentsUtil.neg(fieldsAfterLast_));
        vars_.getVariables().put(this, AssignmentsUtil.neg(variablesAfterLast_));
        vars_.getMutableLoop().put(this, AssignmentsUtil.neg(mutableAfterLast_));
    }
}
