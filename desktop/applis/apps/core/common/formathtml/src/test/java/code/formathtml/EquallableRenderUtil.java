package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.stacks.RendAbstractStask;
import code.formathtml.render.BorderEnum;
import code.formathtml.render.IntComponent;
import code.formathtml.render.MetaLayout;
import code.formathtml.render.MetaPointForm;
import code.sml.Node;
import code.util.core.StringUtil;
import org.junit.Assert;

import code.util.StringList;

public abstract class EquallableRenderUtil {

    public static void assertNotNull(OperationNode _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Struct _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Node _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(IntComponent _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(ContextEl _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(CallingState _value) {
        Assert.assertNotNull(_value);
    }

    public static void assertNull(RendDynOperationNode _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(RendAbstractStask _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(ContextEl _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(CallingState _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(Node _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(IntComponent _value) {
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
    public static void assertSame(MetaPointForm _expected, MetaPointForm _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(Struct _expected, Struct _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(MetaLayout _expected, MetaLayout _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(BorderEnum _expected, BorderEnum _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(IntComponent _expected, IntComponent _result) {
        Assert.assertSame(_expected, _result);
    }

    public static Configuration newConfiguration() {
        return new Configuration();
    }
    public static String formatFile(String _folder, String _locale, String _relative) {
        return StringUtil.concat(_folder,"/",_locale,"/",_relative,".properties");
    }

    public static void assertEq(boolean _expected, boolean _result) {
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(double _expected, double _result) {
        Assert.assertEquals(Double.toString(_expected),Double.toString(_result));
    }

    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(int _expected, int _result) {
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(char _expected, char _result) {
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(StringList _expected, StringList _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected.getList(), _result.getList());
    }
}
