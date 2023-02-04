package code.formathtml.render;

import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.montecarlo.*;
import code.mock.*;
import code.sml.*;
import code.util.LongTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;
import code.util.core.NumberUtil;
import org.junit.Assert;

public abstract class EquallableGuiDocUtil {
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
    public static RenderedPage withFrame(RenderedPage _rend) {
        _rend.setFrame(_rend.getGene().getFrameFactory().newCommonFrame("",_rend.getGene(),null));
        return _rend;
    }
    public static RenderedPage withStd(RenderedPage _rend) {
        MockWithPageInfos page_ = new MockWithPageInfos();
        _rend.setStandards(page_);
        return _rend;
    }
    public static RenderedPage withDoc(RenderedPage _rend, String _txt) {
        return withDoc(_rend,_txt,"");
    }
    public static RenderedPage withFinder(RenderedPage _rend) {
        AbstractProgramInfos pr_ = _rend.getGene();
        AbsPlainButton b_ = pr_.getCompoFactory().newPlainButton();
        _rend.addFinder(pr_.getCompoFactory().newTextField(), b_);
        return _rend;
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
    public static RenderedPage withDoc(RenderedPage _rend, String _txt, String _dest) {
        DocumentResult res_ = DocumentBuilder.newDocumentBuilder().parse(_txt);
        RendKeyWordsGroup keys_ = new RendKeyWordsGroup();
        _rend.setKeys(keys_);
        NavigationCore co_ = new NavigationCore();
        _rend.setNavCore(co_);
        _rend.setFiles(new StringMap<String>());
        _rend.setLanguage("");
        _rend.setLanguage("",new StringList());
        co_.setCurrentUrl(_dest);
        co_.setupText(_txt,res_.getDocument(),keys_.getKeyWordsTags().getKeyWordHead(),keys_.getKeyWordsAttrs().getAttrTitle());
        _rend.setKeyWordDigit(_rend.getKeyWordDigit());
        _rend.setArea(_rend.getArea());
        _rend.initNav(co_,keys_);
        _rend.setupText();
        ((MockCompoFactory)_rend.getGene().getCompoFactory()).invoke();
        ((MockCompoFactory)_rend.getGene().getCompoFactory()).getLater().clear();
        return _rend;
    }
    protected static MetaDocument getMetaDocument(String _nav) {
        return MetaDocument.newInstance(DocumentBuilder.parseSaxNotNullRowCol(_nav).getDocument(),new RendKeyWordsGroup(),"ABCDEF",new MockCharacterCaseConverter());
    }
    public static RenderedPage newRenderedPage(MockProgramInfos _pr) {
        return new RenderedPage(_pr.getCompoFactory().newAbsScrollPane(), _pr,new MockCharacterCaseConverter());
    }
    public static MockProgramInfos newMockProgramInfos(CustomSeedGene _s, MockFileSet _set) {
        return new MockProgramInfos("", "", new MockEventListIncr(_s,new int[0],new String[0],new TextAnswerValue[0]), _set);
    }
    public static MockProgramInfos newMockProgramInfos(MockEventListIncr _s, MockFileSet _set) {
        return new MockProgramInfos("", "", _s, _set);
    }
    public static MockFileSet fileSet(long _initMillis, long[] _incrs, String... _roots) {
        return new MockFileSet(_initMillis,_incrs,_roots);
    }
    public static long[] lgs(long... _args) {
        return _args;
    }
    public static double[] dbs(double... _args) {
        return _args;
    }
    public static byte[] wrapInts(int... _files) {
        return NumberUtil.wrapByteArray(MockZipFact.wrapInts(_files));
    }
    public static MockNameFile[] wrap(MockNameFile... _files) {
        return _files;
    }
}
