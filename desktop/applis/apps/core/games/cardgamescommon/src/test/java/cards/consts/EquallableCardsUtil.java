package cards.consts;

import code.maths.Rate;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Assert;

public abstract class EquallableCardsUtil {

    private static final String DIFF = " != ";

    public static void assertNotNull(StringMap<String> _value) {
        Assert.assertNotNull(_value);
    }

    public static void assertNotNull(String _value) {
        Assert.assertNotNull(_value);
    }

    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }

    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }
    public static void assertTrue(String _mess,boolean _value) {
        Assert.assertTrue(_mess,_value);
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(long _expected, long _result) {
        Assert.assertTrue(StringUtil.concat(Long.toString(_expected),DIFF,Long.toString(_result)), sameValue(_expected, _result));
    }

    public static void assertEq(Rate _expected, Rate _result) {
        Assert.assertTrue(StringUtil.concat(_expected.toNumberString(),DIFF,_result.toNumberString()), Rate.eq(_expected, _result));
    }
    public static void assertSame(EndGameState _expected, EndGameState _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(Role _expected, Role _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(PossibleTrickWinner _expected, PossibleTrickWinner _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(MixCardsChoice _expected, MixCardsChoice _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(GameType _expected, GameType _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(Order _expected, Order _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(Suit _expected, Suit _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertSame(CardChar _expected, CardChar _result) {
        Assert.assertSame(_expected, _result);
    }
    private static boolean sameValue(long _expected, long _result) {
        return _expected == _result;
    }

    
    public static void assertEq(Suit _expected, Suit _result) {
        Assert.assertSame(_expected, _result);
    }

}
