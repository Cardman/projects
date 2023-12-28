package code.gui.files;

import code.gui.AbsButton;
import code.gui.AbsTextField;
import code.mock.MockCustComponent;
import org.junit.Assert;

public abstract class EquallableGuiCommonUtil {
    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }

    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }

    public static void tryType(AbsTextField _field, String _txt) {
        assertTrue(((MockCustComponent)_field).isDeepAccessible());
        _field.setText(_txt);
    }
    public static void tryClick(AbsButton _but) {
        assertTrue(((MockCustComponent)_but).isDeepAccessible());
        _but.getActionListeners().get(0).action();
    }
    public static double[] dbs(double... _args) {
        return _args;
    }
}
