package code.maths.litteral;
import code.util.core.StringUtil;


public final class MultMbOperation extends NumericMbOperation {

    public MultMbOperation(int _index,
                           int _indexChild, MethodMbOperation _m, MbOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    MbArgument calculateOper(MbArgument _a, String _op, MbArgument _b, int _offset, ErrorStatus _error) {
        if (StringUtil.quickEq(_op.trim(), MULT)) {
            return calculateMult(_a, _b);
        }
        return calculateDiv(_a, _b, _offset, _error);
    }

    @Override
    MathType analyzeOper(MathType _a, String _op, MathType _b, int _offset, ErrorStatus _error) {
        if (_a != MathType.RATE || _b != MathType.RATE) {
            _error.setIndex(_offset);
            _error.setError(true);
            return MathType.NOTHING;
        }
        return MathType.RATE;
    }

}
