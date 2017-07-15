package code.maths.litteral;
import code.maths.litteral.exceptions.NotNumberException;
import code.util.StringList;
import code.util.StringMap;



public final class AddOperation extends NumericOperation {

    public AddOperation(String _el, int _index, StringMap<String> _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b, int _offset) {
        if (StringList.quickEq(_op.trim(), PLUS)) {
            return calculateSum(_a, _b);
        }
        return calculateDiff(_a, _b);
    }

    @Override
    MathType analyzeOper(MathType _a, String _op, MathType _b, int _offset) {
        if (_a != MathType.RATE || _b != MathType.RATE) {
            throw new NotNumberException(String.valueOf(_offset));
        }
        return getResultClass(_a, _b);
    }

}
