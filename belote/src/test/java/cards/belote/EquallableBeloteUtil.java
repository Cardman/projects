package cards.belote;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.Suit;

public final class EquallableBeloteUtil {

    private static final String DIFF = " != ";

    private EquallableBeloteUtil() {
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
    public static void assertEq(HandBelote _expected, HandBelote _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (_expected.eq(_result)) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEq(BidBeloteSuit _expected, BidBeloteSuit _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (_expected.eq(_result)) {
            return;
        }
        assertError(_expected, _result);
    }
    
    public static void assertEq(Suit _expected, Suit _result) {
        if (_expected == _result) {
            return;
        }
        assertError(_expected, _result);
    }
    
    public static void assertEq(CardBelote _expected, CardBelote _result) {
        if (_expected == _result) {
            return;
        }
        assertError(_expected, _result);
    }
    
    public static void assertEq(BidBelote _expected, BidBelote _result) {
        if (_expected == _result) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEq(DeclaresBelote _expected, DeclaresBelote _result) {
        if (_expected == _result) {
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

    private static boolean onlyOneNull(Object _expected, Object _result) {
        if (_expected == null) {
            return _result != null;
        }
        return _result == null;
    }
}
