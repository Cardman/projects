package code.formathtml;

import code.formathtml.classes.EnumNumber;
import code.util.StringList;

public class EquallableExUtil {

    private static final String EXPECTED_BUT_WAS = "expected:{0} but was:{1}";

    private EquallableExUtil() {
    }

    public static void assertEq(EnumNumber _expected, EnumNumber _result) {
        if (_expected == _result) {
            return;
        }
        assertError(_expected, _result);
    }

    private static void assertError(Object _expected, Object _result) {
        String message_;
        message_ = StringList.simpleFormat(EXPECTED_BUT_WAS, _expected, _result);
        throw new AssertionError(message_);
    }
}
