package cards.president;
import org.junit.Assert;

import cards.president.enumerations.CardPresident;
import cards.president.enumerations.Playing;
import code.util.StringList;

public final class EquallablePresidentUtil {

    private static final String DIFF = " != ";

    private EquallablePresidentUtil() {
    }
    public static void assertEq(Number _expected, Number _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.toString(),DIFF,_result.toString()), sameValue(_expected, _result));
    }
    private static boolean sameValue(Number _expected, Number _result) {
        if (_expected instanceof Double || _expected instanceof Float) {
            return _expected.doubleValue() == _result.doubleValue();
        }
        return _expected.longValue() == _result.longValue();
    }
    public static void assertEq(HandPresident _expected, HandPresident _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.display(),DIFF,_result.display()), _expected.eq(_result));
    }

    public static void assertEq(Playing _expected, Playing _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(CardPresident _expected, CardPresident _result) {
        Assert.assertSame(_expected, _result);
    }
}
