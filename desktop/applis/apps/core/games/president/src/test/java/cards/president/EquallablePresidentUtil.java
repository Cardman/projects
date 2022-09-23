package cards.president;
import cards.consts.CardChar;
import code.util.core.BoolVal;
import org.junit.Assert;

import cards.president.enumerations.CardPresident;
import cards.president.enumerations.Playing;

public abstract class EquallablePresidentUtil {


    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }
    public static void assertTrue(String _mess,boolean _value) {
        Assert.assertTrue(_mess,_value);
    }
    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }
    public static void assertSame(TrickPresident _expected, TrickPresident _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(GamePresident _expected, GamePresident _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(CardPresident _expected, CardPresident _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(CardChar _expected, CardChar _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertNotSame(RulesPresident _expected, RulesPresident _result) {
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
        Assert.assertEquals(_expected.getCards().getList(),_result.getCards().getList());
    }

    public static void assertEq(Playing _expected, Playing _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(BoolVal _expected, BoolVal _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(CardPresident _expected, CardPresident _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(String _expected, String _result) {
        Assert.assertEquals(_expected,_result);
    }
}
