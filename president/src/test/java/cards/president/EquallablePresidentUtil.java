package cards.president;
import code.util.StringList;

public final class EquallablePresidentUtil {
    private static final String EXPECTED_NULL = "the result is not null.";
    private static final String EXPECTED_NOT_NULL = "the result is null.";

    private static final String EXPECTED_BUT_WAS = "expected:{0} but was:{1}";

    private EquallablePresidentUtil() {
    }

    public static void assertEq(HandPresident _expected, HandPresident _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (_expected.eq(_result)) {
            return;
        }
        assertError(_expected, _result);
    }

//    public static void assertEq(BidBeloteSuit _expected, BidBeloteSuit _result) {
//        if (checkNullity(_expected, _result)) {
//            return;
//        }
//        if (_expected.eq(_result)) {
//            return;
//        }
//        assertError(_expected, _result);
//    }

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
