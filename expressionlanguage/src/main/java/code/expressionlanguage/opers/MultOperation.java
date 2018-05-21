package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ResultOperand;
import code.util.StringList;


public final class MultOperation extends NumericOperation {

    public MultOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    Argument calculateOperAna(Argument _a, String _op, Argument _b,
            Analyzable _an) {
        if (_a.isNull() || _b.isNull()) {
            return Argument.createVoid();
        }
        if (StringList.quickEq(_op.trim(), MULT)) {
            return calculateMult(_a, _b);
        }
        if (StringList.quickEq(_op.trim(), DIV)) {
            return calculateDiv(_a, _b);
        }
        return calculateMod(_a, _b);
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b, ExecutableCode _cont) {
        if (StringList.quickEq(_op.trim(), MULT)) {
            return calculateMult(_a, _b);
        }
        if (StringList.quickEq(_op.trim(), DIV)) {
            return calculateDivEx(_a, _cont, _b);
        }
        return calculateModEx(_a, _cont, _b);
    }

    @Override
    ResultOperand analyzeOper(ClassArgumentMatching _a, String _op, ClassArgumentMatching _b, Analyzable _cont) {
        ResultOperand res_ = new ResultOperand();
        _a.setUnwrapObject(PrimitiveTypeUtil.toPrimitive(_a, true, _cont).getName());
        _b.setUnwrapObject(PrimitiveTypeUtil.toPrimitive(_b, true, _cont).getName());
        res_.setResult(getResultClass(_a, _cont, _b));
        return res_;
    }

    @Override
    void setCatenize(ResultOperand _res) {
    }

}
