package code.mock;

import cards.belote.enumerations.*;
import cards.consts.*;
import cards.president.enumerations.*;
import cards.tarot.enumerations.*;
import code.maths.*;
import code.util.core.*;
import org.junit.Assert;

public abstract class EquallableCardsMockUtil {
    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }

    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }
    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(DealingTarot _expected, DealingTarot _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(DealingBelote _expected, DealingBelote _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(Suit _expected, Suit _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(CardPresident _expected, CardPresident _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(CardBelote _expected, CardBelote _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(CardTarot _expected, CardTarot _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(Handfuls _expected, Handfuls _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(DeclaresBelote _expected, DeclaresBelote _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(BidTarot _expected, BidTarot _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(BidBelote _expected, BidBelote _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(BoolVal _expected, BoolVal _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(Rate _expected, Rate _result) {
        Assert.assertEquals(_expected.toNumberString(), _result.toNumberString());
    }
    public static void assertEq(LgInt _expected, LgInt _result) {
        Assert.assertEquals(_expected.toNumberString(), _result.toNumberString());
    }
}
