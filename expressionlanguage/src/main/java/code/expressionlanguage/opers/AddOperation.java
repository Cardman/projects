package code.expressionlanguage.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.exceptions.InvokeRedinedMethException;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ResultOperand;
import code.util.StringList;
import code.util.exceptions.NullObjectException;



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
        return calculateSum(_arg, _cont, a_, false, false);
    }

    static Argument removeOne(Argument _arg, ContextEl _cont) {
        byte b_ = 1;
        Argument a_ = new Argument();
        a_.setObject(b_);
        return calculateDiff(_arg, _cont, a_);
    }

    /**@throws InvokeRedinedMethException
    @throws NullObjectException*/
    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b, ContextEl _cont) {
        if (StringList.quickEq(_op.trim(), PLUS)) {
            return calculateSum(_a, _cont, _b, catChars, catString);
        }
        return calculateDiff(_a, _cont, _b);
    }

    @Override
    ResultOperand analyzeOper(ClassArgumentMatching _a, String _op, ClassArgumentMatching _b, ContextEl _cont) {
        ResultOperand res_ = new ResultOperand();
        String stringType_ = _cont.getStandards().getAliasString();
        String charType_ = _cont.getStandards().getAliasPrimChar();
        if (StringList.quickEq(_op.trim(), PLUS)) {
            if (_a.matchClass(stringType_) || _b.matchClass(stringType_)) {
                res_.setResult(new ClassArgumentMatching(stringType_));
                res_.setCatString(true);
                return res_;
            }
            if (PrimitiveTypeUtil.toPrimitive(_a, true, _cont).matchClass(charType_)) {
                if (PrimitiveTypeUtil.toPrimitive(_b, true, _cont).matchClass(charType_)) {
                    res_.setResult(new ClassArgumentMatching(stringType_));
                    res_.setCatChars(true);
                    return res_;
                }
            }
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
