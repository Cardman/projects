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
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.SortedClassField;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.util.CustList;
import code.util.EqList;
import code.util.IdMap;
import code.util.StringList;

public final class FinalVariableOperation extends LeafOperation {

    private String variableName = EMPTY_STRING;

    public FinalVariableOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public void analyze(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_) + relativeOff_;
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        LgNames stds_ = _conf.getStandards();
        if (op_.getConstType() == ConstType.PARAM) {
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
            und_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(und_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        if (op_.getConstType() == ConstType.CATCH_VAR) {
            variableName = str_;
            LocalVariable locVar_ = _conf.getCatchVar(variableName);
            if (locVar_ != null) {
                setResultClass(new ClassArgumentMatching(locVar_.getClassName()));
                return;
            }
            UndefinedVariableError und_ = new UndefinedVariableError();
            und_.setId(variableName);
            und_.setFileName(_conf.getCurrentFileName());
            und_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(und_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        if (op_.getConstType() == ConstType.LOOP_INDEX) {
            variableName = str_;
            LoopVariable locVar_ = _conf.getVar(variableName);
            if (locVar_ != null) {
                setResultClass(new ClassArgumentMatching(locVar_.getIndexClassName()));
                return;
            }
            UndefinedVariableError und_ = new UndefinedVariableError();
            und_.setId(variableName);
            und_.setFileName(_conf.getCurrentFileName());
            und_.setRc(_conf.getCurrentLocation());
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
        und_.setRc(_conf.getCurrentLocation());
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
    public void tryCalculateNode(ContextEl _conf,
            EqList<SortedClassField> _list, SortedClassField _current) {
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
        if (_conf.hasExceptionOrFailInit()) {
            return arg_;
        }
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
            a_.setStruct(PrimitiveTypeUtil.convertObject(clArg_, new LongStruct(locVar_.getIndex()), _conf));
            return a_;
        }
        LoopVariable locVar_ = ip_.getVars().getVal(variableName);
        a_ = new Argument();
        a_.setStruct(locVar_.getStruct());
        return a_;
    }
    @Override
    public boolean isCalculated(IdMap<OperationNode, ArgumentsPair> _nodes) {
        OperationNode op_ = this;
        while (op_ != null) {
            if (_nodes.getVal(op_).getArgument() != null) {
                return true;
            }
            op_ = op_.getParent();
        }
        return false;
    }

    @Override
    public boolean isCalculated() {
        OperationNode op_ = this;
        while (op_ != null) {
            if (op_.getArgument() != null) {
                return true;
            }
            op_ = op_.getParent();
        }
        return false;
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

}
