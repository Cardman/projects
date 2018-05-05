package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ResultOperand;
import code.util.StringList;


public final class MultOperation extends NumericOperation {

    public MultOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b, ContextEl _cont, boolean _offset) {
        if (StringList.quickEq(_op.trim(), MULT)) {
            return calculateMult(_a, _cont, _b, _offset);
        }
        if (StringList.quickEq(_op.trim(), DIV)) {
            return calculateDiv(_a, _cont, _b, _offset);
        }
        return calculateMod(_a, _cont, _b, _offset);
    }

    @Override
    ResultOperand analyzeOper(ClassArgumentMatching _a, String _op, ClassArgumentMatching _b, Analyzable _cont) {
        ResultOperand res_ = new ResultOperand();
        res_.setResult(getResultClass(_a, _cont, _b));
        return res_;
    }

    @Override
    void setCatenize(ResultOperand _res) {
    }

}
