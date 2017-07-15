package code.util.ints;


public interface MathFactory<T> {

//    T evaluateDirectlyRateVars(String _numExp, Map<String,String> _map,T _default);

//    T evaluateDirectlyRateVars(String _numExp, Map<String,String> _map);

//    T evaluateDirectlyRate(String _numExp, T _default);

    T evaluateDirectlyRate(String _numExp);

    Boolean evaluateDirectlyBoolean(String _booleanExp);

//    Boolean evaluateDirectlyBoolean(String _booleanExp, Boolean _default);

//    Boolean evaluateDirectlyBooleanVars(String _booleanExp, Map<String,String> _map);

//    Boolean evaluateDirectlyBooleanVars(String _booleanExp, Map<String,String> _map, Boolean _default);


//    T evaluateRateVars(String _numExp, Map<String,String> _map, boolean _check,T _default);

//    T evaluateRateVars(String _numExp, Map<String,String> _map, boolean _check);

//    T evaluateRate(String _numExp, boolean _check, T _default);

//    T evaluateRate(String _numExp, boolean _check);

//    Boolean evaluateBoolean(String _booleanExp, boolean _check);

//    Boolean evaluateBoolean(String _booleanExp, boolean _check, Boolean _default);

//    Boolean evaluateBooleanVars(String _booleanExp, Map<String,String> _map, boolean _check);

//    Boolean evaluateBooleanVars(String _booleanExp, Map<String,String> _map, boolean _check, Boolean _default);

    String toString(Object _gotArg);

}
