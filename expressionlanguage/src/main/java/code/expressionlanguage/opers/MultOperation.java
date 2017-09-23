package code.expressionlanguage.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.exceptions.DivideZeroException;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.StringList;
import code.util.exceptions.NullObjectException;


public final class MultOperation extends NumericOperation {

    public MultOperation(int _index, ContextEl _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _importingPage, _indexChild, _m, _op);
    }

    /**@throws DivideZeroException
    @throws NullObjectException*/
    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b, ContextEl _cont) {
        if (StringList.quickEq(_op.trim(), MULT)) {
            return calculateMult(_a, _cont, _b);
        }
        if (StringList.quickEq(_op.trim(), DIV)) {
            return calculateDiv(_a, _cont, _b);
        }
        return calculateMod(_a, _cont, _b);
    }

    @Override
    ClassArgumentMatching analyzeOper(ClassArgumentMatching _a, String _op, ClassArgumentMatching _b, ContextEl _cont) {
        return getResultClass(_a, _cont, _b);
    }

}
