package code.gui;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractAdvGraphicListGenerator;
import code.gui.initialize.AbstractGraphicComboBoxGenerator;
import code.gui.initialize.AbstractGraphicStringListGenerator;
import code.maths.montecarlo.AbstractGenerator;
import code.stream.core.AbstractBinFact;
import code.stream.core.AbstractTextFact;
import code.stream.core.AbstractZipFact;
import code.threads.ThState;
import org.junit.Assert;

public abstract class EquallableIntGuiUtil {
    public static void assertNull(AbstractInterceptor _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(AbstractImageFactory _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(AbstractNameValidating _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(AbstractGraphicStringListGenerator _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(AbstractGraphicComboBoxGenerator _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(AbstractAdvGraphicListGenerator _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(AbstractGenerator _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(AbsCompoFactory _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(AbstractBinFact _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(AbstractZipFact _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(AbstractTextFact _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected, _result);
    }
    public static void assertSame(ThState _expected, ThState _result) {
        Assert.assertSame(_expected, _result);
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

}
