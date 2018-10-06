package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.util.UnexpectedTypeOperationError;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ResultOperand;
import code.util.StringList;



public final class AddOperation extends NumericOperation {

    private boolean catString;
    private boolean catChars;

    public AddOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    static Argument addOne(Argument _arg, ExecutableCode _cont, ClassArgumentMatching _cl) {
        byte b_ = 1;
        Argument a_ = new Argument();
        a_.setObject(b_);
        return calculateSum(_arg, a_, false, false, _cont, _cl);
    }

    static Argument removeOne(Argument _arg, ExecutableCode _cont, ClassArgumentMatching _cl) {
        byte b_ = 1;
        Argument a_ = new Argument();
        a_.setObject(b_);
        return calculateDiff(_arg, a_, _cont, _cl);
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b, ExecutableCode _cont) {
        if (StringList.quickEq(_op.trim(), PLUS)) {
            return calculateSum(_a, _b, catChars, catString, _cont, getResultClass());
        }
        return calculateDiff(_a, _b, _cont, getResultClass());
    }

    @Override
    Argument calculateOperAna(Argument _a, String _op, Argument _b, Analyzable _cont) {
        if (!catChars && !catString) {
            if (_a.isNull() || _b.isNull()) {
                return Argument.createVoid();
            }
        }
        if (StringList.quickEq(_op.trim(), PLUS)) {
            return calculateSum(_a, _b, catChars, catString, _cont, getResultClass());
        }
        return calculateDiff(_a, _b, _cont, getResultClass());
    }

    @Override
    ResultOperand analyzeOper(ClassArgumentMatching _a, String _op, ClassArgumentMatching _b, Analyzable _cont) {
        ResultOperand res_ = new ResultOperand();
        String stringType_ = _cont.getStandards().getAliasString();
        String stringBuilderType_ = _cont.getStandards().getAliasStringBuilder();
        if (StringList.quickEq(_op.trim(), PLUS)) {
            int oa_ = PrimitiveTypeUtil.getOrderClass(_a, _cont);
            int ob_ = PrimitiveTypeUtil.getOrderClass(_b, _cont);
            if (oa_ > 0 && ob_ > 0) {
                _a.setUnwrapObject(PrimitiveTypeUtil.toPrimitive(_a, true, _cont));
                _b.setUnwrapObject(PrimitiveTypeUtil.toPrimitive(_b, true, _cont));
                res_.setResult(getQuickResultClass(_a, oa_, _cont, _b, ob_));
                return res_;
            }
            boolean str_ = false;
            if (_a.matchClass(stringType_)) {
                if (_b.matchClass(stringType_)) {
                    str_ = true;
                } else if (_b.matchClass(stringBuilderType_)) {
                    str_ = true;
                } else if (_b.isVariable()) {
                    str_ = true;
                } else if (PrimitiveTypeUtil.isPrimitiveOrWrapper(_b, _cont)) {
                    str_ = true;
                }
            }
            if (_b.matchClass(stringType_)) {
                if (_a.matchClass(stringBuilderType_)) {
                    str_ = true;
                } else if (_a.isVariable()) {
                    str_ = true;
                } else if (PrimitiveTypeUtil.isPrimitiveOrWrapper(_a, _cont)) {
                    str_ = true;
                }
            }
            if (str_) {
                res_.setResult(new ClassArgumentMatching(stringType_));
                res_.setCatString(true);
                return res_;
            }
            String exp_ = _cont.getStandards().getAliasNumber();
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setRc(_cont.getCurrentLocation());
            un_.setFileName(_cont.getCurrentFileName());
            un_.setExpectedResult(exp_);
            un_.setOperands(_a,_b);
            _cont.getClasses().addError(un_);
            ClassArgumentMatching arg_ = new ClassArgumentMatching(exp_);
            res_.setResult(arg_);
            return res_;
        }
        _a.setUnwrapObject(PrimitiveTypeUtil.toPrimitive(_a, true, _cont));
        _b.setUnwrapObject(PrimitiveTypeUtil.toPrimitive(_b, true, _cont));
        res_.setResult(getResultClass(_a, _cont, _b));
        return res_;
    }

    @Override
    void setCatenize(ResultOperand _res) {
        catString = _res.isCatString();
        catChars = _res.isCatChars();
    }

}
