package code.formathtml.render;

import code.sml.*;
import code.util.LongTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;
import code.util.core.NumberUtil;
import org.junit.Assert;

public abstract class EquallableRenderAdvUtil {
    public static void assertNotNull(Node _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(IntComponent _value) {
        Assert.assertNotNull(_value);
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
    public static void assertSame(BoolVal _expected, BoolVal _result) {
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

    public static void form(HtmlPageInt _page, long _f, long _n) {
        LongTreeMap<NodeContainer> v_ = new LongTreeMap<NodeContainer>();
        v_.addEntry(_n,new NodeContainer());
        ((HtmlPage)_page).getContainersBase().addEntry(_f, v_);
    }

    public static void form(HtmlPageInt _page, long _f, long _n, long _o) {
        LongTreeMap<NodeContainer> v_ = new LongTreeMap<NodeContainer>();
        v_.addEntry(_n,new NodeContainer());
        v_.addEntry(_o,new NodeContainer());
        ((HtmlPage)_page).getContainersBase().addEntry(_f, v_);
    }

    public static void form(HtmlPageInt _page, long _n) {
        LongTreeMap<NodeContainer> v_ = new LongTreeMap<NodeContainer>();
        ((HtmlPage)_page).getContainersBase().addEntry(_n, v_);
    }
    protected static MetaDocument getMetaDocument(String _nav) {
        return MetaDocument.newInstance(DocumentBuilder.parseSaxNotNullRowCol(_nav).getDocument(),new RendKeyWordsGroup(),"ABCDEF",new SampleCharacterCaseConverter());
    }
    protected static MetaDocument getMetaDocumentSpec(String _nav) {
        return MetaDocument.newInstance(DocumentBuilder.parseSaxNotNullRowCol(_nav).getDocument(),new RendKeyWordsGroup(),"ABCDEF",new SampleNotCharacterCaseConverter());
    }
    public static long[] lgs(long... _args) {
        return _args;
    }
    public static double[] dbs(double... _args) {
        return _args;
    }
}
