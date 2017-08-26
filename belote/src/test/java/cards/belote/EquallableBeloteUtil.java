package cards.belote;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.Suit;
import code.util.StringList;

public final class EquallableBeloteUtil {
    private static final String EXPECTED_NULL = "the result is not null.";
    private static final String EXPECTED_NOT_NULL = "the result is null.";

    private static final String EXPECTED_BUT_WAS = "expected:{0} but was:{1}";

    private EquallableBeloteUtil() {
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
}
