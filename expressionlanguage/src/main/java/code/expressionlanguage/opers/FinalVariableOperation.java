package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ConstType;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.errors.custom.UndefinedVariableError;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class FinalVariableOperation extends VariableLeafOperation {

    private String variableName = EMPTY_STRING;
    private int off;
    private ConstType type;

    public FinalVariableOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        int relativeOff_ = _op.getOffset();
        String originalStr_ = _op.getValues().getValue(CustList.FIRST_INDEX);
        off = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        type = _op.getConstType();
    }

    @Override
    public void analyze(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off, _conf);
        LgNames stds_ = _conf.getStandards();
        if (type == ConstType.PARAM) {
            variableName = str_;
            LocalVariable locVar_ = _conf.getParameters().getVal(variableName);
            if (locVar_ != null) {
                String paramType_ = locVar_.getClassName();
                if (paramType_.endsWith(VARARG_SUFFIX)) {
                    paramType_ = StringList.replace(paramType_, VARARG_SUFFIX, EMPTY_STRING);
                    paramType_ = PrimitiveTypeUtil.getPrettyArrayType(paramType_);
                }
                setResultClass(new ClassArgumentMatching(paramType_));
                return;
            }
            UndefinedVariableError und_ = new UndefinedVariableError();
            und_.setId(variableName);
            und_.setFileName(_conf.getCurrentFileName());
            und_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(und_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        if (type == ConstType.CATCH_VAR) {
            variableName = str_;
            LocalVariable locVar_ = _conf.getCatchVar(variableName);
            if (locVar_ != null) {
                setResultClass(new ClassArgumentMatching(locVar_.getClassName()));
                return;
            }
            UndefinedVariableError und_ = new UndefinedVariableError();
            und_.setId(variableName);
            und_.setFileName(_conf.getCurrentFileName());
            und_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(und_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        if (type == ConstType.LOOP_INDEX) {
            variableName = str_;
            LoopVariable locVar_ = _conf.getVar(variableName);
            if (locVar_ != null) {
                setResultClass(new ClassArgumentMatching(locVar_.getIndexClassName()));
                return;
            }
            UndefinedVariableError und_ = new UndefinedVariableError();
            und_.setId(variableName);
            und_.setFileName(_conf.getCurrentFileName());
            und_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(und_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        variableName = str_;
        LoopVariable locVar_ = _conf.getVar(variableName);
        if (locVar_ != null) {
            setResultClass(new ClassArgumentMatching(locVar_.getClassName()));
            return;
        }
        UndefinedVariableError und_ = new UndefinedVariableError();
        und_.setId(variableName);
        und_.setFileName(_conf.getCurrentFileName());
        und_.setIndexFile(_conf.getCurrentLocationIndex());
        _conf.getClasses().addError(und_);
        setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        boolean isBool_;
        isBool_ = getResultClass().isBoolType(_conf);
        analyzeAssignmentAfter(_conf, isBool_);
    }

    @Override
    public void tryCalculateNode(Analyzable _conf) {
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        Argument arg_ = getCommonArgument(_conf);
        if (_conf.getContextEl().hasException()) {
            return;
        }
        if (arg_ == null) {
            return;
        }
        setSimpleArgument(arg_, _conf);
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        Argument arg_ = getCommonArgument(_conf);
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }
    Argument getCommonArgument(ExecutableCode _conf) {
        Argument a_ = new Argument();
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        PageEl ip_ = _conf.getOperationPageEl();
        OperationsSequence op_ = getOperations();
        if (op_.getConstType() == ConstType.PARAM) {
            LocalVariable locVar_ = ip_.getParameters().getVal(variableName);
            a_ = new Argument();
            a_.setStruct(locVar_.getStruct());
            return a_;
        }
        if (op_.getConstType() == ConstType.CATCH_VAR) {
            LocalVariable locVar_ = ip_.getCatchVars().getVal(variableName);
            a_ = new Argument();
            a_.setStruct(locVar_.getStruct());
            return a_;
        }
        if (op_.getConstType() == ConstType.LOOP_INDEX) {
            LoopVariable locVar_ = ip_.getVars().getVal(variableName);
            a_ = new Argument();
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(locVar_.getIndexClassName());
            LgNames stds_ = _conf.getStandards();
            LongStruct str_ = new LongStruct(locVar_.getIndex());
            Struct value_ = PrimitiveTypeUtil.convertObject(clArg_, str_, stds_);
            a_.setStruct(value_);
            return a_;
        }
        LoopVariable locVar_ = ip_.getVars().getVal(variableName);
        a_ = new Argument();
        a_.setStruct(locVar_.getStruct());
        return a_;
    }

    public String getVariableName() {
        return variableName;
    }

    public ConstType getType() {
        return type;
    }

    public int getOff() {
        return off;
    }

}
