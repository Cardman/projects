package code.maths.litteral;
import code.maths.litteral.exceptions.NotNumberException;
import code.util.StringList;
import code.util.StringMap;


public final class MultOperation extends NumericOperation {

    public MultOperation(String _el, int _index, StringMap<String> _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b, int _offset) {
        if (StringList.quickEq(_op.trim(), MULT)) {
            return calculateMult(_a, _b);
        }
        return calculateDiv(_a, _b, _offset);
    }

    @Override
    MathType analyzeOper(MathType _a, String _op, MathType _b, int _offset) {
        if (_a != MathType.RATE || _b != MathType.RATE) {
            throw new NotNumberException(String.valueOf(_offset));
        }
        return getResultClass(_a, _b);
    }

}
