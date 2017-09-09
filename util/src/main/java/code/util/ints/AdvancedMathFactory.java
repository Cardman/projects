package code.util.ints;
import code.util.StringMap;

public interface AdvancedMathFactory<T,U extends NumericableString<T>,V extends NumericableString<Boolean>>
        extends MathFactory {

    char getSepartorSetChar();
    String getTrueString();
    String getFalseString();
    U createNumericableString(String _chaineNumerique, StringMap<String> _vars);
    V createBooleanString(String _chaineNumerique, StringMap<String> _vars);
    Boolean evaluateBoolean(String _booleanString, StringMap<String> _variables, Boolean _default);
    T evaluateNumericable(String _numericString, StringMap<String> _variables, T _default);

    //used
    T evaluatePositiveOrZeroExp(String _numericString, StringMap<String> _variables, T _default);

    T evaluatePositiveExp(String _numericString, StringMap<String> _variables, T _default);
}
