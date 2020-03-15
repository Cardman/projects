package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.errors.custom.UndefinedVariableError;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.ConstType;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.util.CustList;
import code.util.StringList;

public final class FinalVariableOperation extends LeafOperation {

    private String variableName = EMPTY_STRING;
    private int off;
    private int delta;
    private ConstType type;

    public FinalVariableOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        int relativeOff_ = _op.getOffset();
        String originalStr_ = _op.getValues().getValue(CustList.FIRST_INDEX);
        delta = _op.getDelta();
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
            _conf.addError(und_);
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
            _conf.addError(und_);
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
            _conf.addError(und_);
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
        _conf.addError(und_);
        setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
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

    public int getDelta() {
        return delta;
    }
}
