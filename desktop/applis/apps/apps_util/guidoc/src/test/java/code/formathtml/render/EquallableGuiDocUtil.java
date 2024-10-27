package code.formathtml.render;

import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.document.TestedGuiDocImgAnimAttr;
import code.gui.events.AlwaysActionListenerAct;
import code.gui.initialize.AbstractProgramInfos;
import code.images.BaseSixtyFourUtil;
import code.maths.montecarlo.*;
import code.mock.*;
import code.sml.*;
import code.util.CustList;
import code.util.LongTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;
import code.util.core.SortConstants;
import code.util.core.StringUtil;
import org.junit.Assert;

public abstract class EquallableGuiDocUtil {
    public static final String BASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
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
        _rend.setFrame(_rend.getGene().getFrameFactory().newCommonFrame());
//        _rend.setBase(BASE);
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
        AbsButton b_ = pr_.getCompoFactory().newPlainButton();
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
        return withDoc(_rend, _txt, _dest, res_);
    }

    public static RenderedPage withImageDoc(RenderedPage _rend, String _txt) {
        DocumentResult res_ = DocumentBuilder.newDocumentBuilder().parse(_txt);
        String src_ = res_.getDocument().getElementsByTagName("img").item(0).getAttribute("src");
        res_.getDocument().getElementsByTagName("img").item(0).removeAttribute("src");
        TestedGuiDocImgAnimAttr anim_ = new TestedGuiDocImgAnimAttr("src");
        CustList<String> ls_ = StringUtil.splitChars(src_, '=');
        anim_.setAnim(BaseSixtyFourUtil.getImagesByString(ls_,BASE));
        res_.getDocument().getElementsByTagName("img").item(0).getAttributes().add(anim_);
        return withDoc(_rend, _txt, "", res_);
    }
    public static RenderedPage withDoc(RenderedPage _rend, String _txt, String _dest, DocumentResult _res) {
        RendKeyWordsGroup keys_ = new RendKeyWordsGroup();
        _rend.setKeys(keys_);
        NavigationCore co_ = new NavigationCore();
        _rend.setNavCore(co_);
        _rend.setFiles(new StringMap<String>());
        _rend.setLanguage("",new StringList());
        co_.setCurrentUrl(_dest);
        co_.setupText(_txt,_res.getDocument(),keys_.getKeyWordsTags().getKeyWordHead(),keys_.getKeyWordsAttrs().getAttrTitle());
        _rend.setKeyWordDigit(_rend.getKeyWordDigit());
//        _rend.setBase(_rend.getBase());
        _rend.setArea(_rend.getArea());
        _rend.initNav(co_,keys_);
        _rend.setupText();
        return _rend;
    }
    protected static MetaDocument getMetaDocument(String _nav) {
        return MetaDocument.newInstance(DocumentBuilder.parseSaxNotNullRowCol(_nav).getDocument(),new RendKeyWordsGroup(),"ABCDEF",new MockCharacterCaseConverter());
    }
    public static RenderedPage newRenderedPage(MockProgramInfos _pr) {
        return new RenderedPage(_pr.getCompoFactory().newAbsScrollPane(), _pr,new MockCharacterCaseConverter(), new AlwaysActionListenerAct());
    }
    public static RenderedPage newRenderedPageLg(MockProgramInfos _pr) {
        LanguageComponentButtons buttons_ = new LanguageComponentButtons(_pr, new AlwaysActionListenerAct());
        buttons_.init(_pr,null,null);
        return new RenderedPage(_pr.getCompoFactory().newAbsScrollPane(), _pr,new MockCharacterCaseConverter(), new AlwaysActionListenerAct(), buttons_);
    }
    public static MockProgramInfos newMockProgramInfos(CustomSeedGene _s, MockFileSet _set) {
        return MockProgramInfos.inst("", "", _s, _set);
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
        return SortConstants.wrapByteArray(MockZipFact.wrapInts(_files));
    }
    public static MockNameFile[] wrap(MockNameFile... _files) {
        return _files;
    }
}
