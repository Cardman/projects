package code.util.opers;
import java.math.BigDecimal;
import java.math.BigInteger;

import code.util.BigIntegers;
import code.util.GenericNumbers;
import code.util.StringList;
import code.util.ints.Equallable;

public final class EquallableUtil {
    
    private static final String EXPECTED_NULL = "the result is not null.";
    
    private static final String EXPECTED_NOT_NULL = "the result is null.";

    private static final String EXPECTED_BUT_WAS = "expected:{0} but was:{1}";

    private EquallableUtil() {
    }
    
    public static void assertEq(Equallable<?> _expected, Equallable<?> _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        Equallable<? super Equallable<?>> cast_;
        cast_ = (Equallable<? super Equallable<?>>)_expected;
        //This line fail if the arguments are not of the same class
        if (cast_.eq(_result)) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEq(StringList _expected, StringList _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (_expected.eq(_result)) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEq(Character _expected, Character _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (sameValue(_expected, _result)) {
            return;
        }
        assertError(_expected, _result);
    }

    /**If Enum<?> is replaced by a variable type, then the code works because cast are done before calling this method*/
    public static void assertEq(Enum<?> _expected, Enum<?> _result) {
        if (_expected == _result) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEq(Boolean _expected, Object _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (_expected.booleanValue() == ((Boolean)_result).booleanValue()) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEq(String _expected, Object _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (StringList.quickEq(_expected, (String)_result)) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEq(Number _expected, Number _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (sameValue(_expected, _result)) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEq(BigInteger _expected, BigInteger _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (BigIntegers.eq(_expected, _result)) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEq(BigDecimal _expected, BigDecimal _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (GenericNumbers.eq(_expected, _result)) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEqNb(Number _expected, Object _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (_expected.equals(_result)) {
            return;
        }
        assertError(_expected, _result);
    }

    private static boolean checkNullity(Object _expected, Object _result) {
        if (allNull(_expected, _result)) {
            return true;
        }
        if (onlyOneNull(_expected, _result)) {
            if (_expected == null) {
                throw new AssertionError(EXPECTED_NULL);
            }
            throw new AssertionError(EXPECTED_NOT_NULL);
        }
        return false;
    }

    private static void assertError(Object _expected, Object _result) {
        String message_;
        message_ = StringList.simpleFormat(EXPECTED_BUT_WAS, _expected, _result);
        throw new AssertionError(message_);
    }

    private static boolean allNull(Object _expected, Object _result) {
        return _expected == null && _result == null;
    }

    private static boolean onlyOneNull(Object _expected, Object _result) {
        if (_expected == null) {
            return _result != null;
        }
        return _result == null;
    }
    
    private static boolean sameValue(Object _expected, Object _result) {
        boolean second_ = false;
        if (_result instanceof Number) {
            second_ = true;
        }
        if (_result instanceof Character) {
            second_ = true;
        }
        if (!second_) {
            return false;
        }
        if (_expected instanceof Double || _expected instanceof Float) {
            if (_result instanceof Number) {
                return ((Number)_expected).doubleValue() == ((Number)_result).doubleValue();
            }
            return false;
        }
        if (_expected instanceof Number && _result instanceof Number) {
            return ((Number)_expected).longValue() == ((Number)_result).longValue();
        }
        if (_expected instanceof Number && _result instanceof Character) {
            return ((Number)_expected).longValue() == ((Character)_result).charValue();
        }
        if (_expected instanceof Character && _result instanceof Number) {
            return ((Character)_expected).charValue() == ((Number)_result).longValue();
        }
        return ((Character)_expected).charValue() == ((Character)_result).charValue();
    }
}
