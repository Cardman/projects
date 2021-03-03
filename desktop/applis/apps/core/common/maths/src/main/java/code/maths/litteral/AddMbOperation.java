package code.maths.litteral;
import code.util.core.StringUtil;


public final class AddMbOperation extends NumericMbOperation {

    public AddMbOperation(int _index,
                          int _indexChild, MethodMbOperation _m, MbOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    MbArgument calculateOper(MbArgument _a, String _op, MbArgument _b, int _offset, ErrorStatus _error) {
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
