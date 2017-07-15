package code.maths.litteral;
import code.maths.Rate;
import code.util.StringMap;
import code.util.ints.AdvancedMathFactory;

public class StandardMathFactory implements AdvancedMathFactory<Rate,NumericString,BooleanString > {

    @Override
    public Rate evaluateDirectlyRate(String _numExp) {
        NumericString num_ = new NumericString(_numExp, new StringMap<String>());
        num_.evaluateExp(false);
        return num_.getResult();
    }

    @Override
    public Boolean evaluateDirectlyBoolean(String _booleanExp) {
        BooleanString bool_ = new BooleanString(_booleanExp, new StringMap<String>());
        bool_.evaluateExp(false);
        return bool_.getResult();
    }

    @Override
    public String toString(Object _gotArg) {
        if (_gotArg instanceof Boolean) {
            if ((Boolean) _gotArg) {
                return BooleanString.TRUE;
            }
            return BooleanString.FALSE;
        }
        return String.valueOf(_gotArg);
    }

    @Override
    public NumericString createNumericableString(String _chaineNumerique,
            StringMap<String> _vars) {
        return new NumericString(_chaineNumerique, _vars);
    }

    @Override
    public BooleanString createBooleanString(String _chaineBooleenne,
            StringMap<String> _vars) {
        return new BooleanString(_chaineBooleenne, _vars);
    }

    @Override
    public String getTrueString() {
        return BooleanString.TRUE;
    }

    @Override
    public String getFalseString() {
        return BooleanString.FALSE;
    }

    @Override
    public char getSepartorSetChar() {
        return NumericString.SEPARATOR_SET_CHAR;
    }

    @Override
    public Rate evaluate(String _numericString, StringMap<String> _variables,
            Rate _default) {
        return NumericString.evaluate(_numericString, _variables, _default);
    }

    @Override
    public Rate evaluatePositiveOrZeroExp(String _numericString,
            StringMap<String> _variables, Rate _default) {
        return NumericString.evaluatePositiveOrZeroExp(_numericString, _variables, _default);
    }

    @Override
    public Rate evaluatePositiveExp(String _numericString,
            StringMap<String> _variables, Rate _default) {
        return NumericString.evaluatePositiveExp(_numericString, _variables, _default);
    }

    @Override
    public Boolean evaluate(String _booleanString,
            StringMap<String> _variables, Boolean _default) {
        return BooleanString.evaluate(_booleanString, _variables, _default);
    }


}
