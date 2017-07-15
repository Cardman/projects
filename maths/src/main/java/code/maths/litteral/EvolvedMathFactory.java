package code.maths.litteral;
import code.maths.MathList;
import code.maths.Rate;
import code.util.StringMap;
import code.util.ints.AdvancedMathFactory;

public class EvolvedMathFactory implements AdvancedMathFactory<Rate,EvolvedNumString,EvolvedBooleanString > {

    @Override
    public Rate evaluateDirectlyRate(String _numExp) {
//        arg = MathUtil.processEl(_numExp, 0, false, new Map<String,String>());
//        NumericString num_ = new NumericString(_numExp, new Map<String, String>());
//        num_.evaluateExp(false);
        return (Rate) MathUtil.processEl(_numExp, 0, false, new StringMap<String>()).getObject();
    }

    @Override
    public Boolean evaluateDirectlyBoolean(String _booleanExp) {
//        BooleanString bool_ = new BooleanString(_booleanExp, new Map<String, String>());
//        bool_.evaluateExp(false);
//        return bool_.getResult();
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
    public Rate evaluate(String _numericString, StringMap<String> _variables,
            Rate _default) {
        try {
            return (Rate) MathUtil.processEl(_numericString, 0, false, _variables).getObject();
        } catch (RuntimeException _0) {
            return new Rate(_default);
        }
//        return NumericString.evaluate(_numericString, _variables, _default);
    }

    @Override
    public Rate evaluatePositiveOrZeroExp(String _numericString,
            StringMap<String> _variables, Rate _default) {
        try {
            Rate r_ = (Rate) MathUtil.processEl(_numericString, 0, false, _variables).getObject();
            if (!r_.isZeroOrGt()) {
                return _default.absNb();
            }
            return r_;
        } catch (RuntimeException _0) {
            return _default.absNb();
        }
//        return NumericString.evaluatePositiveOrZeroExp(_numericString, _variables, _default);
    }

    @Override
    public Rate evaluatePositiveExp(String _numericString,
            StringMap<String> _variables, Rate _default) {
        try {
            Rate r_ = (Rate) MathUtil.processEl(_numericString, 0, false, _variables).getObject();
            if (r_.isZero()) {
                return _default.absNb();
            }
            if (!r_.isZeroOrGt()) {
                return _default.absNb();
            }
            return r_;
        } catch (RuntimeException _0) {
            return _default.absNb();
        }
//        return NumericString.evaluatePositiveExp(_numericString, _variables, _default);
    }

    @Override
    public Boolean evaluate(String _booleanString,
            StringMap<String> _variables, Boolean _default) {
        try {
            return (Boolean) MathUtil.processEl(_booleanString, 0, false, _variables).getObject();
        } catch (RuntimeException _0) {
            return _default;
        }
//        return BooleanString.evaluate(_booleanString, _variables, _default);
    }


}
