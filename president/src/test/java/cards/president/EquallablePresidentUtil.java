package cards.president;
import code.util.CustList;
import org.junit.Assert;

import cards.president.enumerations.CardPresident;
import cards.president.enumerations.Playing;
import code.util.StringList;

public final class EquallablePresidentUtil {

    private static final String DIFF = " != ";

    private EquallablePresidentUtil() {
    }
    public static void assertEq(long _expected, long _result) {
        Assert.assertTrue(StringList.concat(Long.toString(_expected),DIFF,Long.toString(_result)), sameValue(_expected, _result));
    }
    public static void assertEq(long _expected, int _result) {
        Assert.assertTrue(StringList.concat(Long.toString(_expected),DIFF,Long.toString(_result)), sameValue(_expected, _result));
    }
    private static boolean sameValue(long _expected, long _result) {
        return _expected == _result;
    }
    public static void assertEq(HandPresident _expected, HandPresident _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(eq(_expected, _result));
    }

    public static void assertEq(Playing _expected, Playing _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(CardPresident _expected, CardPresident _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(String _expected, String _result) {
        Assert.assertTrue(StringList.quickEq(_expected,_result));
    }
    private static boolean eq(HandPresident _current, HandPresident _o) {
        if(_o.total()!= _current.total()) {
            return false;
        }
        boolean id_=true;
        int nbCards_ = _current.total();
        for (int i = CustList.FIRST_INDEX; i < nbCards_; i++) {
            if (_o.carte(i) != _current.carte(i)) {
                id_ = false;
            }
        }
        return id_;
    }
}
