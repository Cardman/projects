package cards.president;
import org.junit.Assert;

import cards.president.enumerations.CardPresident;
import cards.president.enumerations.Playing;

public abstract class EquallablePresidentUtil {


    public static void assertNotNull(Object _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNull(Object _value) {
        Assert.assertNull(_value);
    }

    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }
    public static void assertTrue(String _mess,boolean _value) {
        Assert.assertTrue(_mess,_value);
    }
    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }
    public static void assertSame(Object _expected, Object _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertNotSame(Object _expected, Object _result) {
        Assert.assertNotSame(_expected, _result);
    }

    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(long _expected, int _result) {
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(HandPresident _expected, HandPresident _result) {
        Assert.assertNotNull(_result);
        int size_ = _expected.total();
        Assert.assertEquals(size_, _result.total());
        for (int i = 0; i < size_; i++) {
            assertEq(_expected.carte(i),_result.carte(i));
        }
    }

    public static void assertEq(Playing _expected, Playing _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(CardPresident _expected, CardPresident _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(String _expected, String _result) {
        Assert.assertEquals(_expected,_result);
    }
}
