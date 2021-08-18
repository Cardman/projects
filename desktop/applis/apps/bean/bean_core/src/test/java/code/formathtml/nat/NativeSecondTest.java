package code.formathtml.nat;

import code.bean.Bean;
import code.bean.nat.DefaultInitialization;
import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.DefaultFileBuilder;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ValidatorStandard;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.EquallableBeanCoreUtil;
import code.formathtml.HtmlPage;
import code.formathtml.Navigation;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.exec.RendStackCall;
import code.formathtml.sample.BeanOne;
import code.formathtml.sample.BeanTwo;
import code.formathtml.sample.MyValidator;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.fwd.RendForwardInfos;
import code.formathtml.structs.BeanInfo;
import code.formathtml.util.*;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.*;
import org.junit.Test;

public final class NativeSecondTest extends EquallableBeanCoreUtil {
    @Test
    public void process00Test() {
        assertEq(0,db(0));
    }
    @Test
    public void process0Test() {
        assertEq(2,db(1));
    }
    private static int db(int _arg) {
        return _arg * 2;
    }
//    @Test
    public void process1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><ul><c:for var=\"s\" list=\"strings()\" className='java.lang.String'><li>{length(s)}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        SimpleOne bean_ = BeanTestNatLgNamesImpl.newBeanOne();
        assertEq("<html><body><ul><li>5</li><li>6</li></ul></body></html>", getNatRes(folder_, relative_, html_, bean_));
    }
    //    @Test
    public void process2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><c:if condition='!strings().isEmpty()'>Not Empty</c:if></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        SimpleOne bean_ = BeanTestNatLgNamesImpl.newBeanOne();
        assertEq("<html><body>Not Empty</body></html>", getNatRes(folder_, relative_, html_, bean_));
    }
    // @Test
    public void process3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_one\"><body><table><c:for key=\"k\" keyClassName=\"java.lang.String\" value=\"v\" varClassName=\"$int\" map=\"tree()\"><tr><td>{k}</td><td>{v}</td></tr></c:for></table></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        SimpleOne bean_ = BeanTestNatLgNamesImpl.newBeanOne();
        assertEq("<html><body><table><tr><td>ONE</td><td>1</td></tr><tr><td>TWO</td><td>2</td></tr></table></body></html>", getNatRes(folder_, relative_, html_, bean_));
    }
    //@Test
    public void process4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String htmlTwo_ = "<html c:bean=\"bean_one\"><body><form action=\"DELETE\" name=\"myform\" c:command=\"go\"><input type='text' name=\"typedString\" c:varValue=\"typedString()\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        BeanTestNatLgNames lgNames_ = new BeanTestNatLgNamesImpl();
        basicStandards(lgNames_);
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", htmlTwo_);
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String field='firstUrl' value='page1.html'/>\n" +
                "\t<java.lang.String field='prefix' value='c'/>\n" +
                "\t<java.lang.String field='dataBaseClassName' value='java.lang.Object'/>\n" +
                "\t<sm field='navigation'/>\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<java.lang.String field='messagesFolder' value='messages'/>\n" +
                "\t<java.lang.String field='filesConfName' value='conf'/>\n" +
                "\t<sm field='beans'>\n" +
                "\t\t<java.lang.String key='' value='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='simple.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm field='properties'>\n" +
                "\t\t<java.lang.String key='' value='msg_cust'/>\n" +
                "\t\t<java.lang.String value='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl field='addedFiles'>\n" +
                "\t\t<str value='page1.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl field='renderFiles'>\n" +
                "\t\t<str value='page1.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm field='validators'/>\n" +
                "\t<i field='inex'/>\n" +
                "</cfg>\n" +
                "\n";
        Navigation n_ = new Navigation();
        NativeTestConfigurationLoader nat_ = new NativeTestConfigurationLoader(lgNames_);
        DualAnalyzedContext du_ = n_.loadConfiguration(xmlConf_, "", lgNames_, DefaultFileBuilder.newInstance(lgNames_.getContent()), nat_);
        n_.setFiles(files_);
        lgNames_.setupAll(n_, n_.getSession(), n_.getFiles(), du_);
        ContextEl generate_ = du_.getForwards().generate(du_.getContext().getOptions());
        n_.initializeRendSession(generate_, du_.getStds(), new RendStackCall(InitPhase.NOTHING,generate_));
        assertEq("<html><body><form action=\"\" name=\"myform\" c:command=\"go\" n-f=\"0\"><input type=\"text\" name=\"bean_one.typedString\" n-i=\"0\" value=\"TYPED_STRING\"/></form></body></html>", n_.getHtmlText());
    }
    //@Test
    public void process5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String htmlTwo_ = "<html c:bean=\"bean_one\"><body><form action=\"DELETE\" name=\"myform\" c:command=\"page1.html\"><input type='text' name=\"typedString\" c:varValue=\"typedString()\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        BeanTestNatLgNames lgNames_ = new BeanTestNatLgNamesImpl();
        basicStandards(lgNames_);
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", htmlTwo_);
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String field='firstUrl' value='page1.html'/>\n" +
                "\t<java.lang.String field='prefix' value='c'/>\n" +
                "\t<java.lang.String field='dataBaseClassName' value='java.lang.Object'/>\n" +
                "\t<sm field='navigation'/>\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<java.lang.String field='messagesFolder' value='messages'/>\n" +
                "\t<java.lang.String field='filesConfName' value='conf'/>\n" +
                "\t<sm field='beans'>\n" +
                "\t\t<java.lang.String key='' value='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='simple.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm field='properties'>\n" +
                "\t\t<java.lang.String key='' value='msg_cust'/>\n" +
                "\t\t<java.lang.String value='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl field='addedFiles'>\n" +
                "\t\t<str value='page1.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl field='renderFiles'>\n" +
                "\t\t<str value='page1.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm field='validators'/>\n" +
                "\t<i field='inex'/>\n" +
                "</cfg>\n" +
                "\n";
        Navigation n_ = new Navigation();
        NativeTestConfigurationLoader nat_ = new NativeTestConfigurationLoader(lgNames_);
        DualAnalyzedContext du_ = n_.loadConfiguration(xmlConf_, "", lgNames_, DefaultFileBuilder.newInstance(lgNames_.getContent()), nat_);
        n_.setFiles(files_);
        lgNames_.setupAll(n_, n_.getSession(), n_.getFiles(), du_);
        ContextEl generate_ = du_.getForwards().generate(du_.getContext().getOptions());
        RendStackCall rendStackCall_ = new RendStackCall(InitPhase.NOTHING, generate_);
        n_.initializeRendSession(generate_, du_.getStds(), rendStackCall_);
        HtmlPage htmlPage_ = n_.getHtmlPage();
        LongMap<LongTreeMap<NodeContainer>> containersMap_;
        containersMap_ = htmlPage_.getContainers();
        LongTreeMap< NodeContainer> containers_ = containersMap_.getVal(0L);
        NodeContainer nc_;
        NodeInformations ni_;
        StringList values_;
        nc_ = containers_.getVal(0L);
        nc_.setEnabled(true);
        ni_ = nc_.getNodeInformation();
        values_ = new StringList();
        values_.add("ONE_TWO");
        ni_.setValue(values_);
        n_.getHtmlPage().setUrl(0);
        n_.processRendFormRequest(lgNames_, generate_, rendStackCall_);
        assertEq("page1.html", n_.getCurrentUrl());
        assertEq("bean_one", n_.getCurrentBeanName());
        assertEq("<html><body><form action=\"\" name=\"myform\" c:command=\"page1.html\" n-f=\"0\"><input type=\"text\" name=\"bean_one.typedString\" n-i=\"0\" value=\"ONE_TWO\"/></form></body></html>", n_.getHtmlText());
        SimpleOne beanTwo_ = (SimpleOne) lgNames_.getBean("bean_one").getBean();
        StringMapObjectSample map_ = beanTwo_.getForms();
        assertEq(0, map_.size());
        assertEq("ONE_TWO", beanTwo_.getTypedString());
    }
    // @Test
    public void process6Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String htmlTwo_ = "<html c:bean=\"bean_one\"><body><form action=\"DELETE\" name=\"myform\" c:command=\"page1.html\"><c:for className='simple.Input' var='e' list='typedStrings()'><input type='text' name=\"e.typedString\" c:varValue=\"e.typedString()\"/></c:for></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        BeanTestNatLgNames lgNames_ = new BeanTestNatLgNamesImpl();
        basicStandards(lgNames_);
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", htmlTwo_);
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String field='firstUrl' value='page1.html'/>\n" +
                "\t<java.lang.String field='prefix' value='c'/>\n" +
                "\t<java.lang.String field='dataBaseClassName' value='java.lang.Object'/>\n" +
                "\t<sm field='navigation'/>\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<java.lang.String field='messagesFolder' value='messages'/>\n" +
                "\t<java.lang.String field='filesConfName' value='conf'/>\n" +
                "\t<sm field='beans'>\n" +
                "\t\t<java.lang.String key='' value='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='simple.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm field='properties'>\n" +
                "\t\t<java.lang.String key='' value='msg_cust'/>\n" +
                "\t\t<java.lang.String value='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl field='addedFiles'>\n" +
                "\t\t<str value='page1.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl field='renderFiles'>\n" +
                "\t\t<str value='page1.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm field='validators'/>\n" +
                "\t<i field='inex'/>\n" +
                "</cfg>\n" +
                "\n";
        Navigation n_ = new Navigation();
        NativeTestConfigurationLoader nat_ = new NativeTestConfigurationLoader(lgNames_);
        DualAnalyzedContext du_ = n_.loadConfiguration(xmlConf_, "", lgNames_, DefaultFileBuilder.newInstance(lgNames_.getContent()), nat_);
        n_.setFiles(files_);
        lgNames_.setupAll(n_, n_.getSession(), n_.getFiles(), du_);
        ContextEl generate_ = du_.getForwards().generate(du_.getContext().getOptions());
        RendStackCall rendStackCall_ = new RendStackCall(InitPhase.NOTHING, generate_);
        n_.initializeRendSession(generate_, du_.getStds(), rendStackCall_);
        assertEq("<html><body><form action=\"\" name=\"myform\" c:command=\"page1.html\" n-f=\"0\"><input type=\"text\" name=\"bean_one.e.typedString\" n-i=\"0\" value=\"ONE\"/><input type=\"text\" name=\"bean_one.e.typedString\" n-i=\"1\" value=\"TWO\"/></form></body></html>", n_.getHtmlText());
        HtmlPage htmlPage_ = n_.getHtmlPage();
        LongMap<LongTreeMap<NodeContainer>> containersMap_;
        containersMap_ = htmlPage_.getContainers();
        LongTreeMap< NodeContainer> containers_ = containersMap_.getVal(0L);
        NodeContainer nc_;
        NodeInformations ni_;
        StringList values_;
        nc_ = containers_.getVal(0L);
        nc_.setEnabled(true);
        ni_ = nc_.getNodeInformation();
        values_ = new StringList();
        values_.add("ONE_");
        ni_.setValue(values_);
        nc_ = containers_.getVal(1L);
        nc_.setEnabled(true);
        ni_ = nc_.getNodeInformation();
        values_ = new StringList();
        values_.add("TWO_");
        ni_.setValue(values_);
        n_.getHtmlPage().setUrl(0);
        n_.processRendFormRequest(lgNames_, generate_, rendStackCall_);
        assertEq("page1.html", n_.getCurrentUrl());
        assertEq("bean_one", n_.getCurrentBeanName());
        assertEq("<html><body><form action=\"\" name=\"myform\" c:command=\"page1.html\" n-f=\"0\"><input type=\"text\" name=\"bean_one.e.typedString\" n-i=\"0\" value=\"ONE_\"/><input type=\"text\" name=\"bean_one.e.typedString\" n-i=\"1\" value=\"TWO_\"/></form></body></html>", n_.getHtmlText());
        SimpleOne beanTwo_ = (SimpleOne) lgNames_.getBean("bean_one").getBean();
        StringMapObjectSample map_ = beanTwo_.getForms();
        assertEq(0, map_.size());
        assertEq("ONE_", beanTwo_.getTypedStrings().first().getTypedString());
        assertEq("TWO_", beanTwo_.getTypedStrings().last().getTypedString());
    }
    private String getNatRes(String _folder, String _relative, String _html, SimpleOne _bean) {
        NativeOtherAnalyzedTestConfiguration conf_ = contextElSec();

        setupNative(_folder, _relative, conf_.getDual());
        putBean(_bean, "bean_one", conf_.getAdv());
        addBeanInfo(conf_,"bean_one",new BeanStruct(_bean));
        AnalyzingDoc analyzingDoc_ = conf_.getAnalyzingDoc();
        setLocalFiles(conf_, analyzingDoc_);
        analyzeInner(conf_.getConfiguration(),conf_, _html);
        RendForwardInfos.buildExec(analyzingDoc_, conf_.getAnalyzed(), conf_.getForwards(), conf_.getConfiguration());
        setFirst(conf_);
        ContextEl generate_ = conf_.getForwards().generate(new Options());
        RendStackCall built_ = conf_.build(InitPhase.NOTHING, generate_);
        return getSampleRes(conf_.getConfiguration(), conf_.getConfiguration().getRenders().getVal("page1.html"), conf_.getAdv(), generate_, built_);
    }

    private NativeOtherAnalyzedTestConfiguration contextElSec() {
        Configuration conf_ =  EquallableBeanCoreUtil.newConfiguration();
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
            putBean(((BeanStruct) e.getValue()).getInstance(), _conf, e.getKey());
        }
    }

    private static void cleanBeans(NativeOtherAnalyzedTestConfiguration _conf) {
//        _conf.getAdv().getBeans().clear();
    }

//    private static void initSessionNat(NativeOtherAnalyzedTestConfiguration _conf,Navigation _nav) {
//        StringMap<BeanInfo> map_ = new StringMap<BeanInfo>();
//        BeanTestNatLgNames standards_ = _conf.getAdv();
//        for (EntryCust<String, Bean> e: standards_.getBeans().entryList()) {
//            BeanInfo i_ = new BeanInfo();
//            i_.setClassName(e.getValue().getClassName());
//            i_.setScope(e.getValue().getScope());
//            map_.addEntry(e.getKey(),i_);
//        }
//        _nav.getSession().setBeansInfos(map_);
//        _nav.setLanguages(new StringList(_nav.getLanguage()));
//        AnalyzingDoc analyzingDoc_ = _conf.getAnalyzingDoc();
//        analyzingDoc_.setup(_conf.getConfiguration(), _conf.getDual());
//        setupAna(analyzingDoc_, _conf.getAnalyzing());
//        BeanTestNatLgNames.initInstancesPattern(_conf.getConfiguration(), analyzingDoc_);
//        AnalyzedPageEl page_ = _conf.getAnalyzing();
//        StringMap<AnaRendDocumentBlock> d_ = _nav.analyzedRenders(page_, standards_, analyzingDoc_, _conf.getDual());
//        _conf.setAnalyzed(d_);
//        RendForwardInfos.buildExec(analyzingDoc_, d_, _conf.getForwards(), _conf.getConfiguration());
//        _nav.initializeRendSession(null, _conf.getAdv(), new RendStackCall(InitPhase.NOTHING,null));
//    }

    private static RendDocumentBlock buildRendWithTwoNativeBean(String _html, String _htmlTwo, BeanOne _bean, BeanTwo _beanTwo, NativeOtherAnalyzedTestConfiguration _conf) {
        Configuration c_ = _conf.getConfiguration();
        addBeanInfo(_conf,"bean_one", new BeanStruct(_bean));
        addBeanInfo(_conf,"bean_two", new BeanStruct(_beanTwo));
        analyzeInner(c_,_conf, _html,_htmlTwo);
        RendForwardInfos.buildExec(_conf.getAnalyzingDoc(), _conf.getAnalyzed(), null, _conf.getConfiguration());
        setFirst(_conf);
        return _conf.getConfiguration().getRenders().getVal("page1.html");
    }

    private static void analyzeInner(Configuration _conf, NativeOtherAnalyzedTestConfiguration _a, String... _html) {
        StringMap<AnaRendDocumentBlock> d_ = analyze(_conf, _a, _a.getAnalyzingDoc(), _html);
        _a.setAnalyzed(d_);
    }

    private static void setLocalFiles(NativeOtherAnalyzedTestConfiguration _context, AnalyzingDoc _analyzingDoc) {
        NatAnalyzedCode analyzing_ = _context.getAnalyzing();
        Configuration conf_ = _context.getConfiguration();
        conf_.setCurrentLanguage("en");
        _analyzingDoc.setup(conf_, _context.getDual());
        setInnerLocalFiles(_analyzingDoc, analyzing_);
    }

    private static void setInnerLocalFiles(AnalyzingDoc _analyzingDoc, NatAnalyzedCode _analyzing) {
        _analyzingDoc.setLanguages(new StringList("en"));
        setupAna(_analyzingDoc, _analyzing);
    }
    private static StringMap<AnaRendDocumentBlock> analyze(Configuration _conf, NativeOtherAnalyzedTestConfiguration _a, AnalyzingDoc _analyzingDoc, String... _html) {
        int c_ = 1;
        StringMap<AnaRendDocumentBlock> d_ = new StringMap<AnaRendDocumentBlock>();
        for (String h: _html) {
            Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(h).getDocument();
            AnaRendDocumentBlock anaDoc_ = AnaRendDocumentBlock.newRendDocumentBlock("c:", doc_, h, null, "page1.html", _conf.getRendKeyWords());
            d_.addEntry("page"+c_+".html",anaDoc_);
            c_++;
        }
        setLocalFiles(_a, _analyzingDoc);
        for (AnaRendDocumentBlock v: d_.values()) {
            v.buildFctInstructions(_analyzingDoc, null, _analyzingDoc.getBeansInfosBefore());
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
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgNames_.getContent());
        Forwards forwards_ = new Forwards(lgNames_, fileBuilder_, _opt);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), _opt, lgNames_.getContent(), page_);
        lgNames_.build();
        ValidatorStandard.setupOverrides(page_);
        return new NativeOtherAnalyzedTestContext(null, forwards_, lgNames_);
    }

    private static void addBeanInfo(NativeOtherAnalyzedTestConfiguration _conf, String _id, Struct _str) {
        BeanInfo b_ = new BeanInfo();
        b_.setClassName(_str.getClassName(null));
        b_.setResolvedClassName(_str.getClassName(null));
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
//        ((BeanTestNatLgNames) _stds).getBeans().put(_key, _beanTwo);
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

//    private static Bean getBean(NativeOtherAnalyzedTestConfiguration _conf, String _key) {
//        return _conf.getAdv().getBeans().getVal(_key);
//    }

    private static void putBean(Bean _beanTwo, NativeOtherAnalyzedTestConfiguration _conf, String _key) {
        putBean(_beanTwo, _key, _conf.getAdv());
    }

    private static String getSampleRes(Configuration _conf, RendDocumentBlock _rendDocumentBlock, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStackCall) {
        return getRes(_conf,_rendDocumentBlock, _stds, _ctx, _rendStackCall);
    }

    private static String getSampleRes(NativeOtherAnalyzedTestConfiguration _conf, RendDocumentBlock _rendDocumentBlock) {
        return getSampleRes(_conf.getConfiguration(), _rendDocumentBlock, _conf.getAdv(), null, null);
    }

    private static void preinit(NativeOtherAnalyzedTestConfiguration _conf) {
        _conf.getAdv().preInitBeans(_conf.getConfiguration());
    }

    private static String getRes(Configuration _conf, RendDocumentBlock _doc, BeanLgNames _stds, ContextEl _context, RendStackCall _rendStackCall) {
        return RendBlock.getRes(_doc, _conf, _stds, _context, _rendStackCall, "page1.html");
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

    private static void setupAna(AnalyzingDoc _analyzingDoc, NatAnalyzedCode _page) {
//        AnalyzingDoc.setupInts(_page, _analyzingDoc);
    }

}
