package code.maths.litteral;
import code.util.core.StringUtil;


public final class AddOperation extends NumericOperation {

    public AddOperation(int _index,
                        int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b, int _offset, ErrorStatus _error) {
        if (StringUtil.quickEq(_op.trim(), PLUS)) {
            return calculateSum(_a, _b);
        }
        return calculateDiff(_a, _b);
    }

    @Override
    MathType analyzeOper(MathType _a, String _op, MathType _b, int _offset, ErrorStatus _error) {
        if (_a != MathType.RATE || _b != MathType.RATE) {
            _error.setError(true);
            _error.setIndex(_offset);
            return MathType.NOTHING;
        }
        return MathType.RATE;
    }

}
