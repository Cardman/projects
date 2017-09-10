package code.maths;

import java.math.BigDecimal;
import java.math.BigInteger;

import code.maths.geo.CustPoint;
import code.maths.geo.CustPointThreeDims;
import code.util.BigIntegers;
import code.util.GenericNumbers;
import code.util.StringList;

public final class EquallableMathUtil {

    private static final String DIFF = " != ";

    private EquallableMathUtil() {
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
    public static void assertEq(Boolean _expected, Object _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (_expected.booleanValue() == ((Boolean)_result).booleanValue()) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEq(Rate _expected, Rate _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (_expected.eq(_result)) {
            return;
        }
        assertError(_expected, _result);
    }
    
    public static void assertEq(LgInt _expected, LgInt _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (_expected.eq(_result)) {
            return;
        }
        assertError(_expected, _result);
    }
    
    public static void assertEq(CustPoint _expected, CustPoint _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (_expected.eq(_result)) {
            return;
        }
        assertError(_expected, _result);
    }
    
    public static void assertEq(CustPointThreeDims _expected, CustPointThreeDims _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (_expected.eq(_result)) {
            return;
        }
        assertError(_expected, _result);
    }

    private static boolean checkNullity(Object _expected, Object _result) {
        if (allNull(_expected, _result)) {
            return true;
        }
        if (onlyOneNull(_expected, _result)) {
            throw new AssertionError(null);
        }
        return false;
    }

    private static void assertError(Object _expected, Object _result) {
        throw new AssertionError(_expected+DIFF+_result);
    }

    private static boolean allNull(Object _expected, Object _result) {
        return _expected == null && _result == null;
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
    private static boolean onlyOneNull(Object _expected, Object _result) {
        if (_expected == null) {
            return _result != null;
        }
        return _result == null;
    }
}
