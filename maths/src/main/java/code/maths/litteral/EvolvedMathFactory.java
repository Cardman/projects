package code.maths.litteral;
import code.maths.LgInt;
import code.maths.MathList;
import code.maths.Rate;
import code.util.StringMap;
import code.util.ints.AdvancedMathFactory;

public final class EvolvedMathFactory implements AdvancedMathFactory<Rate> {

    private final LgInt maxRd = LgInt.getMaxLongPlusOne();

    @Override
    public Rate evaluateDirectlyRate(String _numExp) {
        return (Rate) MathUtil.processEl(_numExp, 0, false, new StringMap<String>()).getObject();
    }

    @Override
    public Boolean evaluateDirectlyBoolean(String _booleanExp) {
        return (Boolean) MathUtil.processEl(_booleanExp, 0, false, new StringMap<String>()).getObject();
    }

    @Override
    public String toString(Object _gotArg) {
        if (_gotArg instanceof Boolean) {
            if ((Boolean) _gotArg) {
                return getTrueString();
            }
            return getFalseString();
        }
        if (_gotArg instanceof MathList) {
            return ((MathList)_gotArg).escapedList();
        }
        if (_gotArg instanceof LgInt) {
            return ((LgInt)_gotArg).toNumberString();
        }
        if (_gotArg instanceof Rate) {
            return ((Rate)_gotArg).toNumberString();
        }
        return String.valueOf(_gotArg);
    }

    @Override
    public EvolvedNumString createNumericableString(String _chaineNumerique,
            StringMap<String> _vars) {
        return new EvolvedNumString(_chaineNumerique, _vars);
    }

    @Override
    public EvolvedBooleanString createBooleanString(String _chaineBooleenne,
            StringMap<String> _vars) {
        return new EvolvedBooleanString(_chaineBooleenne, _vars);
    }

    @Override
    public String getTrueString() {
        return OperationNode.TRUE_STRING;
    }

    @Override
    public String getFalseString() {
        return OperationNode.FALSE_STRING;
    }

    @Override
    public char getSepartorSetChar() {
        return NumericString.SEPARATOR_SET_CHAR;
    }

    @Override
    public Rate evaluateNumericable(String _numericString, StringMap<String> _variables,
            Rate _default) {
        Object obj_ = MathUtil.processEl(_numericString, 0, false, _variables).getObject();
        if (obj_ instanceof Rate) {
            return (Rate) obj_;
        }
        return new Rate(_default);
    }

    @Override
    public Rate evaluatePositiveOrZeroExp(String _numericString,
            StringMap<String> _variables, Rate _default) {
        Object obj_ = MathUtil.processEl(_numericString, 0, false, _variables).getObject();
        if (!(obj_ instanceof Rate)) {
            return _default.absNb();
        }
        Rate r_ = (Rate) obj_;
        if (!r_.isZeroOrGt()) {
            return _default.absNb();
        }
        return r_;
    }

    @Override
    public Rate evaluatePositiveExp(String _numericString,
            StringMap<String> _variables, Rate _default) {
        Object obj_ = MathUtil.processEl(_numericString, 0, false, _variables).getObject();
        if (!(obj_ instanceof Rate)) {
            return _default.absNb();
        }
        Rate r_ = (Rate) obj_;
        if (r_.isZero()) {
            return _default.absNb();
        }
        if (!r_.isZeroOrGt()) {
            return _default.absNb();
        }
        return r_;
    }

    @Override
    public Boolean evaluateBoolean(String _booleanString,
            StringMap<String> _variables, Boolean _default) {
        Object obj_ = MathUtil.processEl(_booleanString, 0, false, _variables).getObject();
        if (!(obj_ instanceof Boolean)) {
            return _default;
        }
        return (Boolean)obj_;
    }
    public LgInt getMaxRandomNb() {
        return maxRd;
    }
}
