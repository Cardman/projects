package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
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

    static Argument addOne(Argument _arg, ContextEl _cont) {
        byte b_ = 1;
        Argument a_ = new Argument();
        a_.setObject(b_);
        return calculateSum(_arg, _cont, a_, true, false, false);
    }

    static Argument removeOne(Argument _arg, ContextEl _cont) {
        byte b_ = 1;
        Argument a_ = new Argument();
        a_.setObject(b_);
        return calculateDiff(_arg, _cont, a_, true);
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b, ContextEl _cont, boolean _offset) {
        if (StringList.quickEq(_op.trim(), PLUS)) {
            return calculateSum(_a, _cont, _b, _offset, catChars, catString);
        }
        return calculateDiff(_a, _cont, _b, _offset);
    }

    @Override
    ResultOperand analyzeOper(ClassArgumentMatching _a, String _op, ClassArgumentMatching _b, Analyzable _cont) {
        ResultOperand res_ = new ResultOperand();
        String stringType_ = _cont.getStandards().getAliasString();
        String stringBuilderType_ = _cont.getStandards().getAliasStringBuilder();
        String charType_ = _cont.getStandards().getAliasPrimChar();
        if (StringList.quickEq(_op.trim(), PLUS)) {
            if (PrimitiveTypeUtil.toPrimitive(_a, true, _cont).matchClass(charType_)) {
                if (PrimitiveTypeUtil.toPrimitive(_b, true, _cont).matchClass(charType_)) {
                    res_.setResult(new ClassArgumentMatching(stringType_));
                    res_.setCatChars(true);
                    return res_;
                }
            }
            int oa_ = PrimitiveTypeUtil.getOrderClass(_a, _cont);
            int ob_ = PrimitiveTypeUtil.getOrderClass(_b, _cont);
            if (oa_ > 0 && ob_ > 0) {
                res_.setResult(getQuickResultClass(_a, oa_, _cont, _b, ob_));
                return res_;
            }
            boolean str_ = false;
            if (_a.matchClass(stringType_)) {
                if (_b.matchClass(stringType_)) {
                    str_ = true;
                } else if (_b.matchClass(stringBuilderType_)) {
                    str_ = true;
                } else if (_b.getName().isEmpty()) {
                    str_ = true;
                } else if (PrimitiveTypeUtil.isPrimitiveOrWrapper(_b.getName(), _cont)) {
                    str_ = true;
                }
            }
            if (_b.matchClass(stringType_)) {
                if (_a.matchClass(stringBuilderType_)) {
                    str_ = true;
                } else if (_a.getName().isEmpty()) {
                    str_ = true;
                } else if (PrimitiveTypeUtil.isPrimitiveOrWrapper(_a.getName(), _cont)) {
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
            un_.setOperands(new StringList(_a.getName(),_b.getName()));
            _cont.getClasses().getErrorsDet().add(un_);
            ClassArgumentMatching arg_ = new ClassArgumentMatching(exp_);
            res_.setResult(arg_);
            return res_;
        }
        res_.setResult(getResultClass(_a, _cont, _b));
        return res_;
    }

    @Override
    void setCatenize(ResultOperand _res) {
        catString = _res.isCatString();
        catChars = _res.isCatChars();
    }

}
