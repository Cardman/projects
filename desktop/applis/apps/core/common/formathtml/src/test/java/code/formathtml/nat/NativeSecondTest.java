package code.formathtml.nat;

import code.bean.Bean;
import code.bean.BeanStruct;
import code.bean.nat.DefaultInitialization;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.DefaultFileBuilder;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ValidatorStandard;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.EquallableExUtil;
import code.formathtml.Navigation;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.classes.BeanOne;
import code.formathtml.classes.BeanTwo;
import code.formathtml.classes.MyValidator;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.fwd.RendForwardInfos;
import code.formathtml.structs.BeanInfo;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.DualConfigurationContext;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class NativeSecondTest {
    @Test
    public void process1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><ul><c:for var=\"s\" list=\"strings()\" className='java.lang.String'><li>{s.length()}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        SimpleOne bean_ = BeanTestNatLgNamesImpl.newBeanOne();
        assertEq("<html><body><ul><li>5</li><li>6</li></ul></body></html>", getNatRes(folder_, relative_, html_, bean_));
    }
    @Test
    public void process2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><c:if condition='!strings().isEmpty()'>Not Empty</c:if></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        SimpleOne bean_ = BeanTestNatLgNamesImpl.newBeanOne();
        assertEq("<html><body>Not Empty</body></html>", getNatRes(folder_, relative_, html_, bean_));
    }
    @Test
    public void process3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_one\"><body><table><c:for key=\"k\" keyClassName=\"java.lang.String\" value=\"v\" varClassName=\"$int\" map=\"tree()\"><tr><td>{k}</td><td>{v}</td></tr></c:for></table></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        SimpleOne bean_ = BeanTestNatLgNamesImpl.newBeanOne();
        assertEq("<html><body><table><tr><td>ONE</td><td>1</td></tr><tr><td>TWO</td><td>2</td></tr></table></body></html>", getNatRes(folder_, relative_, html_, bean_));
    }
    private String getNatRes(String _folder, String _relative, String _html, SimpleOne _bean) {
        NativeOtherAnalyzedTestConfiguration conf_ = contextElSec();

        setupNative(_folder, _relative, conf_.getDual());
        putBean(_bean, "bean_one", conf_.getAdv());
        addBeanInfo(conf_,"bean_one",new BeanStruct(_bean));
        AnalyzingDoc analyzingDoc_ = conf_.getAnalyzingDoc();
        setLocalFiles(conf_, analyzingDoc_);
        conf_.getAnalyzing().setForEachFetch(new NativeTestForEachFetch(conf_.getAdv()));
        analyzeInner(conf_.getConfiguration(),conf_, _html);
        RendForwardInfos.buildExec(analyzingDoc_, conf_.getAnalyzed(), new Forwards(), conf_.getConfiguration());
        setFirst(conf_);
        assertTrue(conf_.isEmptyErrors());
        String res_ = getSampleRes(conf_.getConfiguration(), conf_.getConfiguration().getRenders().getVal("page1.html"), conf_.getAdv(), conf_.getContext());
        assertNull(getException(conf_));
        return res_;
    }

    private static Struct getException(NativeOtherAnalyzedTestConfiguration _cont) {
        CallingState str_ = _cont.getContext().getCallingState();
        if (str_ instanceof Struct) {
            return (Struct) str_;
        }
        return null;
    }

    private NativeOtherAnalyzedTestConfiguration contextElSec() {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        return buildNat(conf_);
    }
    private static NativeOtherAnalyzedTestConfiguration buildNat(Configuration _conf) {
        Options opt_ = new Options();
        NativeOtherAnalyzedTestContext cont_ = buildStdOne(opt_);
        return new NativeOtherAnalyzedTestConfiguration(_conf,cont_, cont_.getForwards(), cont_.getStds());
    }
    private static void setupBeansAfter(NativeOtherAnalyzedTestConfiguration _conf) {
        cleanBeans(_conf);
        for (EntryCust<String, Struct> e: _conf.getConfiguration().getBuiltBeans().entryList()) {
            putBean((Bean) ((BeanStruct) e.getValue()).getInstance(), _conf, e.getKey());
        }
    }

    private static void cleanBeans(NativeOtherAnalyzedTestConfiguration _conf) {
        _conf.getAdv().getBeans().clear();
    }

    private static void initSessionNat(NativeOtherAnalyzedTestConfiguration _conf,Navigation _nav) {
        StringMap<BeanInfo> map_ = new StringMap<BeanInfo>();
        BeanTestNatLgNames standards_ = _conf.getAdv();
        for (EntryCust<String, Bean> e: standards_.getBeans().entryList()) {
            BeanInfo i_ = new BeanInfo();
            i_.setClassName(e.getValue().getClassName());
            i_.setScope(e.getValue().getScope());
            map_.addEntry(e.getKey(),i_);
        }
        _nav.getSession().setBeansInfos(map_);
        _nav.setLanguages(new StringList(_nav.getLanguage()));
        AnalyzingDoc analyzingDoc_ = _conf.getAnalyzingDoc();
        analyzingDoc_.setup(_conf.getConfiguration(), _conf.getAdv(), _conf.getDual());
        setupAna(analyzingDoc_, _conf.getAnalyzing());
        _conf.getAnalyzing().setForEachFetch(new NativeTestForEachFetch(standards_));
        _nav.initInstancesPattern(_conf.getAnalyzing(), analyzingDoc_);
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        StringMap<AnaRendDocumentBlock> d_ = _nav.analyzedRenders(page_, standards_, analyzingDoc_, _conf.getDual());
        _conf.setAnalyzed(d_);
        RendForwardInfos.buildExec(analyzingDoc_, d_, _conf.getForwards(), _conf.getConfiguration());
        _nav.initializeRendSession(_conf.getContext(), _conf.getAdv());
    }

    private static RendDocumentBlock buildRendWithTwoNativeBean(String _html, String _htmlTwo, BeanOne _bean, BeanTwo _beanTwo, NativeOtherAnalyzedTestConfiguration _conf) {
        Configuration c_ = _conf.getConfiguration();
        addBeanInfo(_conf,"bean_one", new BeanStruct(_bean));
        addBeanInfo(_conf,"bean_two", new BeanStruct(_beanTwo));
        analyzeInner(c_,_conf, _html,_htmlTwo);
        RendForwardInfos.buildExec(_conf.getAnalyzingDoc(), _conf.getAnalyzed(), new Forwards(), _conf.getConfiguration());
        setFirst(_conf);
        return _conf.getConfiguration().getRenders().getVal("page1.html");
    }

    private static void analyzeInner(Configuration _conf, NativeOtherAnalyzedTestConfiguration _a, String... _html) {
        StringMap<AnaRendDocumentBlock> d_ = analyze(_conf, _a, _a.getAnalyzingDoc(), _html);
        _a.setAnalyzed(d_);
    }

    private static void setLocalFiles(NativeOtherAnalyzedTestConfiguration _context, AnalyzingDoc _analyzingDoc) {
        AnalyzedPageEl analyzing_ = _context.getAnalyzing();
        Configuration conf_ = _context.getConfiguration();
        conf_.setCurrentLanguage("en");
        _analyzingDoc.setup(conf_, _context.getAdv(), _context.getDual());
        setInnerLocalFiles(_analyzingDoc, analyzing_);
    }

    private static void setInnerLocalFiles(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _analyzing) {
        _analyzingDoc.setLanguages(new StringList("en"));
        setupAna(_analyzingDoc, _analyzing);
    }
    private static StringMap<AnaRendDocumentBlock> analyze(Configuration _conf, NativeOtherAnalyzedTestConfiguration _a, AnalyzingDoc _analyzingDoc, String... _html) {
        int c_ = 1;
        StringMap<AnaRendDocumentBlock> d_ = new StringMap<AnaRendDocumentBlock>();
        for (String h: _html) {
            Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(h).getDocument();
            AnaRendDocumentBlock anaDoc_ = AnaRendDocumentBlock.newRendDocumentBlock("c:", doc_, h, _a.getAnalyzing().getPrimTypes(), _conf.getCurrentUrl(), _conf.getRendKeyWords());
            d_.addEntry("page"+c_+".html",anaDoc_);
            c_++;
        }
        setLocalFiles(_a, _analyzingDoc);
        for (AnaRendDocumentBlock v: d_.values()) {
            v.buildFctInstructions(_analyzingDoc, _a.getAnalyzing());
        }
        return d_;
    }
    protected static void setFirst(NativeOtherAnalyzedTestConfiguration _cont) {
        RendDocumentBlock doc_ = _cont.getConfiguration().getRenders().getVal("page1.html");
        _cont.getConfiguration().setRendDocumentBlock(doc_);
    }

    private static NativeOtherAnalyzedTestContext buildStdOne(Options _opt) {
        BeanTestNatLgNames lgNames_ = new BeanTestNatLgNamesImpl();
        basicStandards(lgNames_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        ContextEl contextEl_ = ContextFactory.simpleBuild((int) IndexConstants.INDEX_NOT_FOUND_ELT, _opt, lgNames_, tabWidth_);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        ContextFactory.validatedStds(a_, kw_, new CustList<CommentDelimiters>(), _opt, contextEl_.getClasses().getCommon(), null, DefaultFileBuilder.newInstance(lgNames_.getContent()), lgNames_.getContent(), tabWidth_, page_);
        lgNames_.build();
        ValidatorStandard.setupOverrides(page_);
        return new NativeOtherAnalyzedTestContext(contextEl_, page_, new Forwards(), lgNames_);
    }

    private static void addBeanInfo(NativeOtherAnalyzedTestConfiguration _conf, String _id, Struct _str) {
        BeanInfo b_ = new BeanInfo();
        b_.setClassName(_str.getClassName(_conf.getContext()));
        b_.setResolvedClassName(_str.getClassName(_conf.getContext()));
        _conf.getConfiguration().getBeansInfos().addEntry(_id,b_);
        _conf.getConfiguration().getBuiltBeans().addEntry(_id,_str);
    }
    private static void basicStandards(BeanLgNames _lgNames) {
        DefaultInitialization.basicStandards(_lgNames);
    }


    private static void setupNative(String _folder, String _relative, DualConfigurationContext _context) {
        setup(_folder, _relative, _context);
    }

    private static void putBean(Bean _beanTwo, String _key, LgNames _stds) {
        ((BeanTestNatLgNames) _stds).getBeans().put(_key, _beanTwo);
    }

    private static void setupNative(String _folder, String _relative, NativeOtherAnalyzedTestConfiguration _context) {
        setup(_folder, _relative, _context.getDual());
    }

    private static void setupNative2(String _folder, String _relative, NativeOtherAnalyzedTestConfiguration _conf) {
        _conf.getConfiguration().setFirstUrl("page2.html");
        setup(_folder, _relative, _conf.getDual());
    }
    private static void setupVal(String _folder, String _relative, NativeOtherAnalyzedTestConfiguration _conf) {
        _conf.getConfiguration().setFirstUrl("page1.html");
        setup(_folder, _relative, _conf.getDual());
        _conf.getAdv().getValidators().put("rate_val", new MyValidator());
    }

    private static void setSess(Configuration _conf, Navigation _nav) {
        _nav.setSession(_conf);
    }

    private static void setSess(NativeOtherAnalyzedTestConfiguration _conf, Navigation _nav) {
        _nav.setSession(_conf.getConfiguration());
    }

    private static Bean getBean(NativeOtherAnalyzedTestConfiguration _conf, String _key) {
        return _conf.getAdv().getBeans().getVal(_key);
    }

    private static void putBean(Bean _beanTwo, NativeOtherAnalyzedTestConfiguration _conf, String _key) {
        putBean(_beanTwo, _key, _conf.getAdv());
    }

    private static String getSampleRes(Configuration _conf, RendDocumentBlock _rendDocumentBlock, BeanLgNames _stds, ContextEl _ctx) {
        return getRes(_conf,_rendDocumentBlock, _stds, _ctx);
    }

    private static String getSampleRes(NativeOtherAnalyzedTestConfiguration _conf, RendDocumentBlock _rendDocumentBlock) {
        return getSampleRes(_conf.getConfiguration(), _rendDocumentBlock, _conf.getAdv(), _conf.getContext());
    }

    private static void preinit(NativeOtherAnalyzedTestConfiguration _conf) {
        _conf.getAdv().preInitBeans(_conf.getConfiguration());
    }

    private static String getRes(Configuration _conf, RendDocumentBlock _doc, BeanLgNames _stds, ContextEl _context) {
        return RendBlock.getRes(_doc, _conf, _stds, _context);
    }

    private static Navigation newNavigation(NativeOtherAnalyzedTestConfiguration _conf) {
        Navigation nav_ = new Navigation();
        nav_.setSession(_conf.getConfiguration());

        return nav_;
    }

    private static void setFiles(StringMap<String> _files, NativeOtherAnalyzedTestConfiguration _conf) {
        _conf.getConfiguration().setFiles(_files);
    }

    private static void setup(String _folder, String _relative, DualConfigurationContext _conf) {
        setup(_folder, _conf);
        _conf.getProperties().put("msg_example", _relative);
    }

    private static void setup(String _folder, DualConfigurationContext _conf) {
        _conf.setMessagesFolder(_folder);
        _conf.setProperties(new StringMap<String>());
    }

    private static void setupAna(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page) {
        AnalyzingDoc.setupInts(_page, _analyzingDoc);
    }

}
