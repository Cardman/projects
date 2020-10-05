package code.formathtml.nat;

import code.bean.Bean;
import code.bean.nat.NativeForEachFetch;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.DefaultFileBuilder;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.ValidatorStandard;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.IntStruct;
import code.formathtml.Configuration;
import code.formathtml.EquallableExUtil;
import code.formathtml.HtmlPage;
import code.formathtml.Navigation;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.classes.BeanFive;
import code.formathtml.classes.BeanOne;
import code.formathtml.classes.BeanTwo;
import code.formathtml.classes.Composite;
import code.formathtml.classes.CustBeanLgNames;
import code.formathtml.classes.MyValidator;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.exec.AdvancedFullStack;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.fwd.RendForwardInfos;
import code.formathtml.structs.BeanInfo;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.Struct;
import code.bean.BeanStruct;
import code.bean.nat.BeanNatLgNames;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.DualAnalyzedContext;
import code.formathtml.util.NodeContainer;
import code.formathtml.util.NodeInformations;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.CustList;
import code.util.EntryCust;
import code.util.LongMap;
import code.util.LongTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.StringMapObject;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.*;

public final class NativeTest {


    @Test
    public void process0FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><ul><c:for var=\"s\" list=\"&quot;&quot;\" className='java.lang.String'><li>{s;length()}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.setLanguage("");
        assertNotNull(bean_.getLanguage());
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq(0,(Integer)BeanNatLgNames.adaptedArg(new IntStruct(0)));
        assertTrue(hasNatErr(folder_, relative_, html_, bean_));
        assertNull(bean_.getDataBase());
    }

    @Test
    public void process0Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><c:if condition='!composite.strings.isEmpty()'>Not empty</c:if></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body>Not empty</body></html>", getNatRes(folder_, relative_, html_, bean_));
    }

    @Test
    public void process00Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><c:if condition='composite.strings.isEmpty()'>Empty</c:if></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().setInteger(5);
        assertEq("<html><body>Empty</body></html>", getNatRes(folder_, relative_, html_, bean_));
    }
    @Test
    public void process1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><ul><c:for var=\"s\" list=\"composite.strings\" className='java.lang.String'><li>{s.length()}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body><ul><li>5</li><li>6</li></ul></body></html>", getNatRes(folder_, relative_, html_, bean_));
    }

    @Test
    public void process2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><ul><c:for var=\"s\" list=\"composite.strings\" className='$var'><li>{s.length()}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body><ul><li>5</li><li>6</li></ul></body></html>", getNatRes(folder_, relative_, html_, bean_));
    }

    @Test
    public void process1FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><ul><c:for var=\"s\" list=\"composite.strings\" className='java.lang.Integer'><li>{s.length()}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertTrue(hasNatErr(folder_, relative_, html_, bean_));
    }

    @Test
    public void process3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_one\"><body><table><c:for key=\"k\" keyClassName=\"java.lang.String\" value=\"v\" varClassName=\"$int\" map=\"tree\"><tr><td>{k}</td><td>{v}</td></tr></c:for></table></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        assertEq("<html><body><table><tr><td>ONE</td><td>1</td></tr><tr><td>TWO</td><td>2</td></tr></table></body></html>", getNatRes(folder_, relative_, html_, bean_));
    }

    @Test
    public void process5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_one\"><body><table><c:for key=\"k\" value=\"v\" map=\"tree\" keyClassName='java.lang.Object' varClassName='java.lang.Object'><tr><td>{k}</td><td>{v}</td></tr></c:for></table></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        assertEq("<html><body><table><tr><td>ONE</td><td>1</td></tr><tr><td>TWO</td><td>2</td></tr></table></body></html>", getNatRes(folder_, relative_, html_, bean_));
    }

    @Test
    public void process6Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_one\"><body><table><c:for key=\"k\" value=\"v\" map=\"tree\" keyClassName='java.lang.Object' varClassName='java.lang.Object'><c:for key=\"l\" value=\"w\" map=\"tree\" keyClassName='java.lang.Object' varClassName='java.lang.Object'><tr><td>{k}</td><td>{v}</td><td>{l}</td><td>{w}</td></tr></c:for></c:for></table></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        assertEq("<html><body><table><tr><td>ONE</td><td>1</td><td>ONE</td><td>1</td></tr><tr><td>ONE</td><td>1</td><td>TWO</td><td>2</td></tr><tr><td>TWO</td><td>2</td><td>ONE</td><td>1</td></tr><tr><td>TWO</td><td>2</td><td>TWO</td><td>2</td></tr></table></body></html>", getNatRes(folder_, relative_, html_, bean_));
    }

    @Test
    public void process7Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<a c:command=\"goToNullPage\" href=\"\"/></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form action=\"DELETE\" name=\"myform\" c:command=\"go\"><input type='text' name=\"typedString\" c:varValue=\"typedString\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        BeanLgNames lgNames_ = new CustBeanLgNames();
        basicStandards(lgNames_);
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String field='firstUrl' value='page2.html'/>\n" +
                "\t<java.lang.String field='prefix' value='c'/>\n" +
                "\t<java.lang.String field='dataBaseClassName' value='java.lang.Object'/>\n" +
                "\t<sm field='navigation'>\n" +
                "\t\t<java.lang.String key='' value='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String key='' value='res'/>\n" +
                "\t\t\t<java.lang.String value='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<java.lang.String field='messagesFolder' value='messages'/>\n" +
                "\t<java.lang.String field='filesConfName' value='conf'/>\n" +
                "\t<sm field='beans'>\n" +
                "\t\t<java.lang.String key='' value='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='code.formathtml.classes.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t\t<java.lang.String key='' value='bean_two'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='code.formathtml.classes.BeanTwo'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm field='properties'>\n" +
                "\t\t<java.lang.String key='' value='msg_cust'/>\n" +
                "\t\t<java.lang.String value='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl field='addedFiles'>\n" +
                "\t\t<str value='page1.html'/>\n" +
                "\t\t<str value='page2.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl field='renderFiles'>\n" +
                "\t\t<str value='page1.html'/>\n" +
                "\t\t<str value='page2.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm field='validators'>\n" +
                "\t\t<str key='' value='my_val'/>\n" +
                "\t\t<str value='code.formathtml.classes.MyValidator'/>\n" +
                "\t</sm>\n" +
                "\t<i field='inex'/>\n" +
                "</cfg>\n" +
                "\n";
        Navigation n_ = new Navigation();
        RendAnalysisMessages rend_ = new RendAnalysisMessages();
        DualAnalyzedContext du_ = n_.loadConfiguration(xmlConf_, "", lgNames_, rend_, DefaultFileBuilder.newInstance(lgNames_.getContent()));
        n_.setFiles(files_);
        n_.setupRendClassesInit(lgNames_, rend_, du_);
        n_.initializeRendSession(du_.getContext(), du_.getStds());
        assertEq("<html><body><form action=\"\" name=\"myform\" c:command=\"go\" n-f=\"0\"><input type=\"text\" name=\"bean_two.typedString\" n-i=\"0\" value=\"TYPED_STRING\"/></form></body></html>", n_.getHtmlText());
    }
    @Test
    public void processNatTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<a c:command=\"goToNullPage\" href=\"\"/></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form action=\"DELETE\" name=\"myform\" c:command=\"go\"><input type='text' name=\"typedString\" c:varValue=\"typedString\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c");
        NativeAnalyzedTestConfiguration a_ = buildNat(conf_);
        conf_.setFirstUrl("page2.html");
        conf_.getRenderFiles().add("page1.html");
        conf_.getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("code.formathtml.classes.BeanOne");
        conf_.getBeansInfos().addEntry("bean_one",i_);
        i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("code.formathtml.classes.BeanTwo");
        conf_.getBeansInfos().addEntry("bean_two",i_);
        conf_.init();
        Navigation n_ = new Navigation();
        setSess(conf_, n_);
        n_.setFiles(files_);
        n_.setupRendClassesInit(a_.getAdv(), new RendAnalysisMessages(), new DualAnalyzedContext(a_.getAnalyzing(),a_.getAdv(),a_.getContext()));
        n_.initializeRendSession(a_.getContext(), a_.getAdv());
        assertEq("<html><body><form action=\"\" name=\"myform\" c:command=\"go\" n-f=\"0\"><input type=\"text\" name=\"bean_two.typedString\" n-i=\"0\" value=\"TYPED_STRING\"/></form></body></html>", n_.getHtmlText());
        assertEq("page2.html", n_.getCurrentUrl());
    }

    private static NativeAnalyzedTestConfiguration buildNat(Configuration conf_) {
        Options opt_ = new Options();
        NativeAnalyzedTestContext cont_ = buildStdOne(opt_);
        return new NativeAnalyzedTestConfiguration(conf_,cont_, cont_.getForwards(), cont_.getStds());
    }

    @Test
    public void processNav1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<a c:command=\"$goToNullPage\" href=\"\"/></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form action=\"DELETE\" name=\"myform\" c:command=\"$go\"></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        BeanOne bean_ = new BeanOne();
        bean_.setScope("request");
        BeanFive beanTwo_ = new BeanFive();
        beanTwo_.setScope("request");
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(bean_, conf_, "bean_one");
        putBean(beanTwo_, conf_, "bean_two");
        setupNative2(folder_, relative_, conf_);


        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("change", "page1.html");
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        conf_.getAdv().setDataBase(new Composite());
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        nav_.getSession().getRenderFiles().add("page2.html");
        initSessionNat(conf_,nav_);
        assertEq("page2.html", nav_.getCurrentUrl());
        nav_.getHtmlPage().setUrl(0);
        nav_.processRendFormRequest((BeanNatLgNames)conf_.getContext().getStandards(), conf_.getContext());
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body>HEAD<a c:command=\"$bean_one.goToNullPage\" href=\"\" n-a=\"0\"/></body></html>", nav_.getHtmlText());
        assertSame(getBean(conf_, "bean_one").getForms(), getBean(conf_, "bean_two").getForms());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

    }


    @Test
    public void processNav2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:import page=\"page2.html\"><a/><c:package name=\"code.formathtml.classes\"><a/><c:class name=\"BeanTwo\"><a/><c:field prepare=\"$intern.typedString=message\"><a/></c:field></c:class></c:package></c:import></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><a href=\"DELETE\" c:command=\"go\">{typedString}</a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        bean_.setForms(new StringMapObject());
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setTypedString("TITLE");
        beanTwo_.setForms(new StringMapObject());
        NativeAnalyzedTestConfiguration conf_ = contextElSec();
        
        conf_.getConfiguration().setFirstUrl("page1.html");
        bean_.getForms().put("key", "sample_value");
        setupNative(folder_, relative_, conf_);
        preinit(conf_);


        RendDocumentBlock rendDocumentBlock_ = buildRendWithTwoNativeBean(html_, htmlTwo_, bean_, beanTwo_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body><a href=\"\" c:command=\"go\" n-a=\"0\">Test {0}2</a></body></html>", getSampleRes(conf_, rendDocumentBlock_));
        assertNull(getException(conf_));
        assertEq(1, beanTwo_.getForms().size());
        assertEq("key", beanTwo_.getForms().getKeys().first());
        assertEq("sample_value", (String)beanTwo_.getForms().values().first());
    }

    @Test
    public void processNav3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a c:command=\"$go\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:import page=\"page2.html\" keepfields=\"y\"><c:package name=\"code.formathtml.classes\"><c:class name=\"BeanTwo\"><c:field prepare=\"$intern.typedString=message\"></c:field></c:class></c:package><c:form form=\"key\"/></c:import></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><a href=\"DELETE\" c:command=\"go\">{typedString}</a><c:message value='msg_example,two'/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        bean_.setForms(new StringMapObject());
        bean_.getForms().put("key", "sample_value");
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setTypedString("TITLE");
        beanTwo_.setForms(new StringMapObject());
        NativeAnalyzedTestConfiguration conf_ = contextElSec();
        setupNative(folder_, relative_, conf_);
        preinit(conf_);
        setFiles(files_, conf_);

        RendDocumentBlock rendDocumentBlock_ = buildRendWithTwoNativeBean(html_, htmlTwo_, bean_, beanTwo_, conf_);
        assertTrue(conf_.isEmptyErrors());
        String render_ = getSampleRes(conf_, rendDocumentBlock_);
        assertEq("<html><body><a href=\"\" c:command=\"go\" n-a=\"0\">Test {0}2</a>Description <a c:command=\"$bean_two.go\" href=\"\" n-a=\"1\">two</a></body></html>", render_);
        assertEq(1, beanTwo_.getForms().size());
        assertEq("key", beanTwo_.getForms().getKeys().first());
        assertEq("sample_value", (String)beanTwo_.getForms().values().first());
    }

    @Test
    public void processNav4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form c:command=\"$goToPage\"><input id=\"txt\" type=\"text\" name=\"selectedString\" c:varValue=\"selectedString\" c:validator=\"rate_val\"/><span c:for=\"txt\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", htmlTwo_);
        BeanOne beanTwo_ = new BeanOne();
        beanTwo_.setScope("request");
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(beanTwo_, conf_, "bean_two");
        setupVal(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        initSessionNat(conf_,nav_);
        HtmlPage htmlPage_ = nav_.getHtmlPage();
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
        nav_.getHtmlPage().setUrl(0);
        nav_.processRendFormRequest((BeanNatLgNames)conf_.getContext().getStandards(), conf_.getContext());
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_two", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.goToPage\" action=\"\" n-f=\"0\"><input id=\"txt\" type=\"text\" name=\"bean_two.selectedString\" c:validator=\"rate_val\" n-i=\"0\" value=\"ONE_TWO\"/><span c:for=\"txt\">ONE_TWO is not a no zero rate</span></form></body></html>", nav_.getHtmlText());
        beanTwo_ = (BeanOne) getBean(conf_, "bean_two");
        StringMapObject map_ = beanTwo_.getForms();
        assertEq(0, map_.size());
        assertEq(0, getBean(conf_, "bean_two").getForms().size());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

    }

    @Test
    public void processNav5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form c:command=\"$go\"><input id=\"txt\" type=\"number\" name=\"typedInt\" c:varValue=\"typedInt\" c:validator=\"rate_val\"/><span c:for=\"txt\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", htmlTwo_);
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setScope("request");
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(beanTwo_, conf_, "bean_two");
        setupVal(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        initSessionNat(conf_,nav_);
        HtmlPage htmlPage_ = nav_.getHtmlPage();
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
        nav_.getHtmlPage().setUrl(0);
        nav_.processRendFormRequest((BeanNatLgNames)conf_.getContext().getStandards(), conf_.getContext());
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_two", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input id=\"txt\" type=\"number\" name=\"bean_two.typedInt\" c:validator=\"rate_val\" n-i=\"0\" value=\"0\"/><span c:for=\"txt\"/></form></body></html>", nav_.getHtmlText());
        beanTwo_ = (BeanTwo) getBean(conf_, "bean_two");
        StringMapObject map_ = beanTwo_.getForms();
        assertEq(0, map_.size());
        assertEq(0, getBean(conf_, "bean_two").getForms().size());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

    }

    @Test
    public void processNav6Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form c:command=\"$go\"><input id=\"txt\" type=\"number\" name=\"typedInt\" c:varValue=\"typedInt\" c:validator=\"rate_val\"/><span c:for=\"txt\"/><input id=\"txt2\" type=\"text\" name=\"typedString\" c:varValue=\"typedString\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", htmlTwo_);
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setScope("request");
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(beanTwo_, conf_, "bean_two");
        setupVal(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        initSessionNat(conf_,nav_);
        HtmlPage htmlPage_ = nav_.getHtmlPage();
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
        nc_ = containers_.getVal(1L);
        nc_.setEnabled(true);
        ni_ = nc_.getNodeInformation();
        values_ = new StringList();
        values_.add("TYPED_STRING");
        ni_.setValue(values_);
        nav_.getHtmlPage().setUrl(0);
        nav_.processRendFormRequest((BeanNatLgNames)conf_.getContext().getStandards(), conf_.getContext());
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_two", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input id=\"txt\" type=\"number\" name=\"bean_two.typedInt\" c:validator=\"rate_val\" n-i=\"0\" value=\"0\"/><span c:for=\"txt\"/><input id=\"txt2\" type=\"text\" name=\"bean_two.typedString\" n-i=\"1\" value=\"TYPED_STRING\"/></form></body></html>", nav_.getHtmlText());
        beanTwo_ = (BeanTwo) getBean(conf_, "bean_two");
        StringMapObject map_ = beanTwo_.getForms();
        assertEq(0, map_.size());
        assertEq(0, getBean(conf_, "bean_two").getForms().size());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

    }

    @Test
    public void processNav7Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\"><body><form c:command=\"$go\"><input id=\"txt\" type=\"number\" name=\"typedInt\" c:varValue=\"typedInt\" c:validator=\"rate_val\"/><span c:for=\"txt\"/><c:import page='page2.html'/></form></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_one\"><body><input id=\"txt2\" type=\"text\" name=\"selectedString\" c:varValue=\"selectedString\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        BeanOne beanOne_ = new BeanOne();
        beanOne_.setScope("request");
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setScope("request");
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(beanTwo_, conf_, "bean_two");
        putBean(beanOne_, conf_, "bean_one");
        setupVal(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        nav_.getSession().getRenderFiles().add("page2.html");
        initSessionNat(conf_,nav_);
        HtmlPage htmlPage_ = nav_.getHtmlPage();
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
        nc_ = containers_.getVal(1L);
        nc_.setEnabled(true);
        ni_ = nc_.getNodeInformation();
        values_ = new StringList();
        values_.add("ONE");
        ni_.setValue(values_);
        nav_.getHtmlPage().setUrl(0);
        nav_.processRendFormRequest((BeanNatLgNames)conf_.getContext().getStandards(), conf_.getContext());
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_two", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input id=\"txt\" type=\"number\" name=\"bean_two.typedInt\" c:validator=\"rate_val\" n-i=\"0\" value=\"0\"/><span c:for=\"txt\"/><input id=\"txt2\" type=\"text\" name=\"bean_one.selectedString\" n-i=\"1\" value=\"ONE\"/></form></body></html>", nav_.getHtmlText());
        beanTwo_ = (BeanTwo) getBean(conf_, "bean_two");
        StringMapObject map_ = beanTwo_.getForms();
        assertEq(0, map_.size());
        assertEq(0, getBean(conf_, "bean_two").getForms().size());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

    }

    @Test
    public void processNav8Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_one\"><body><form action=\"DELETE\" name=\"myform\" c:command=\"go\"><c:select id=\"combo\" default=\"\" name=\"selectedString\" varValue=\"selectedString\" map=\"map\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page2.html", htmlTwo_);
        BeanOne bean_ = new BeanOne();
        bean_.setScope("request");
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(bean_, conf_, "bean_one");
        setupNative2(folder_, relative_, conf_);


        conf_.setNavigation(new StringMap<StringMap<String>>());
        Navigation nav_ = newNavigation(conf_);
        conf_.getAdv().setDataBase(new Composite());
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page2.html");
        initSessionNat(conf_,nav_);
        assertEq("page2.html", nav_.getCurrentUrl());
        assertEq("<html><body><form action=\"\" name=\"myform\" c:command=\"go\" n-f=\"0\"><select id=\"combo\" name=\"bean_one.selectedString\" n-i=\"0\"><option value=\"ONE\" selected=\"selected\">1</option><option value=\"TWO\">2</option></select></form></body></html>", nav_.getHtmlText());

    }

    @Test
    public void processNav9Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_one\"><body>{invokeMethod(0)}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page2.html", htmlTwo_);
        BeanOne bean_ = new BeanOne();
        bean_.setScope("request");
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(bean_, conf_, "bean_one");
        setupNative2(folder_, relative_, conf_);


        conf_.setNavigation(new StringMap<StringMap<String>>());
        Navigation nav_ = newNavigation(conf_);
        conf_.getAdv().setDataBase(new Composite());
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page2.html");
        initSessionNat(conf_,nav_);
        assertEq("page2.html", nav_.getCurrentUrl());
        assertEq("<html><body>returned value</body></html>", nav_.getHtmlText());

    }

    @Test
    public void processNav10Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_one\"><body>{invokeMethod(0L)}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page2.html", htmlTwo_);
        BeanOne bean_ = new BeanOne();
        bean_.setScope("request");
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(bean_, conf_, "bean_one");
        setupNative2(folder_, relative_, conf_);


        conf_.setNavigation(new StringMap<StringMap<String>>());
        Navigation nav_ = newNavigation(conf_);
        conf_.getAdv().setDataBase(new Composite());
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page2.html");
        initSessionNat(conf_,nav_);
        assertEq("page2.html", nav_.getCurrentUrl());
        assertEq("<html><body>returned value</body></html>", nav_.getHtmlText());

    }

    @Test
    public void processNav11Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_one\"><body>{getList(5)}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page2.html", htmlTwo_);
        BeanOne bean_ = new BeanOne();
        bean_.setScope("request");
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(bean_, conf_, "bean_one");
        setupNative2(folder_, relative_, conf_);


        conf_.setNavigation(new StringMap<StringMap<String>>());
        Navigation nav_ = newNavigation(conf_);
        conf_.getAdv().setDataBase(new Composite());
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page2.html");
        initSessionNat(conf_,nav_);
        assertEq("page2.html", nav_.getCurrentUrl());
        assertEq("<html><body>5</body></html>", nav_.getHtmlText());

    }

    @Test
    public void processNav12Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form c:command=\"$go\"><input type=\"number\" name=\"nullableInt\" c:varValue=\"nullableInt\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", htmlTwo_);
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setScope("session");
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(beanTwo_, conf_, "bean_two");
        setupVal(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        initSessionNat(conf_,nav_);
        HtmlPage htmlPage_ = nav_.getHtmlPage();
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
        values_.add("10");
        ni_.setValue(values_);
        nav_.getHtmlPage().setUrl(0);
        nav_.processRendFormRequest((BeanNatLgNames)conf_.getContext().getStandards(), conf_.getContext());
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_two", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input type=\"number\" name=\"bean_two.nullableInt\" n-i=\"0\" value=\"10\"/></form></body></html>", nav_.getHtmlText());
        beanTwo_ = (BeanTwo) getBean(conf_, "bean_two");
        StringMapObject map_ = beanTwo_.getForms();
        assertEq(8, map_.size());
        assertEq(8, getBean(conf_, "bean_two").getForms().size());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

    }

    @Test
    public void processNav13Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form c:command=\"$go\"><input type=\"number\" name=\"nullableInt\" c:varValue=\"nullableInt\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", htmlTwo_);
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setScope("session");
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(beanTwo_, conf_, "bean_two");
        setupVal(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        initSessionNat(conf_,nav_);
        HtmlPage htmlPage_ = nav_.getHtmlPage();
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
        values_.add("");
        ni_.setValue(values_);
        nav_.getHtmlPage().setUrl(0);
        nav_.processRendFormRequest((BeanNatLgNames)conf_.getContext().getStandards(), conf_.getContext());
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_two", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input type=\"number\" name=\"bean_two.nullableInt\" n-i=\"0\" value=\"\"/></form></body></html>", nav_.getHtmlText());
        beanTwo_ = (BeanTwo) getBean(conf_, "bean_two");
        StringMapObject map_ = beanTwo_.getForms();
        assertEq(8, map_.size());
        assertEq(8, getBean(conf_, "bean_two").getForms().size());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

    }

    @Test
    public void processNav14Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form c:command=\"$go\"><input type=\"checkbox\" name=\"checked\" c:varValue=\"checked\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", htmlTwo_);
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setScope("session");
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(beanTwo_, conf_, "bean_two");
        setupVal(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        initSessionNat(conf_,nav_);
        HtmlPage htmlPage_ = nav_.getHtmlPage();
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
        values_.add("on");
        ni_.setValue(values_);
        nav_.getHtmlPage().setUrl(0);
        nav_.processRendFormRequest((BeanNatLgNames)conf_.getContext().getStandards(), conf_.getContext());
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_two", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input type=\"checkbox\" name=\"bean_two.checked\" n-i=\"0\" checked=\"checked\"/></form></body></html>", nav_.getHtmlText());
        beanTwo_ = (BeanTwo) getBean(conf_, "bean_two");
        StringMapObject map_ = beanTwo_.getForms();
        assertEq(8, map_.size());
        assertEq(8, getBean(conf_, "bean_two").getForms().size());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

    }

    @Test
    public void processNav15Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form c:command=\"$go\"><input c:className='code.formathtml.classes.Rate' type=\"text\" name=\"rate\" c:varValue=\"rate\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", htmlTwo_);
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setScope("session");
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(beanTwo_, conf_, "bean_two");
        setupVal(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        initSessionNat(conf_,nav_);
        HtmlPage htmlPage_ = nav_.getHtmlPage();
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
        values_.add("1/2");
        ni_.setValue(values_);
        nav_.getHtmlPage().setUrl(0);
        nav_.processRendFormRequest((BeanNatLgNames)conf_.getContext().getStandards(), conf_.getContext());
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_two", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input c:className=\"code.formathtml.classes.Rate\" type=\"text\" name=\"bean_two.rate\" n-i=\"0\" value=\"1/2\"/></form></body></html>", nav_.getHtmlText());
        beanTwo_ = (BeanTwo) getBean(conf_, "bean_two");
        StringMapObject map_ = beanTwo_.getForms();
        assertEq(8, map_.size());
        assertEq(8, getBean(conf_, "bean_two").getForms().size());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

    }

    @Test
    public void processNav16Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form c:command=\"$go\"><input c:className='$short' type=\"number\" name=\"typedShort\" c:varValue=\"typedShort\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", htmlTwo_);
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setScope("session");
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(beanTwo_, conf_, "bean_two");
        setupVal(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        initSessionNat(conf_,nav_);
        HtmlPage htmlPage_ = nav_.getHtmlPage();
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
        values_.add("12");
        ni_.setValue(values_);
        nav_.getHtmlPage().setUrl(0);
        nav_.processRendFormRequest((BeanNatLgNames)conf_.getContext().getStandards(), conf_.getContext());
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_two", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input c:className=\"$short\" type=\"number\" name=\"bean_two.typedShort\" n-i=\"0\" value=\"12\"/></form></body></html>", nav_.getHtmlText());
        beanTwo_ = (BeanTwo) getBean(conf_, "bean_two");
        StringMapObject map_ = beanTwo_.getForms();
        assertEq(8, map_.size());
        assertEq(8, getBean(conf_, "bean_two").getForms().size());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

    }

    @Test
    public void processNav17Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body>{$new $int[]{}}{$this}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", htmlTwo_);
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setScope("session");
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(beanTwo_, conf_, "bean_two");
        setupVal(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        initSessionNat(conf_,nav_);
        assertEq("page1.html", nav_.getCurrentUrl());

    }

    @Test
    public void processNav18Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_one\"><body><form action=\"DELETE\" name=\"myform\" c:command=\"go\"><c:select id=\"combo\" default=\"\" name=\"chosenNumber\" varValue=\"chosenNumber\" map=\"map\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page2.html", htmlTwo_);
        BeanOne bean_ = new BeanOne();
        bean_.setScope("request");
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(bean_, conf_, "bean_one");
        setupNative2(folder_, relative_, conf_);


        conf_.setNavigation(new StringMap<StringMap<String>>());
        Navigation nav_ = newNavigation(conf_);
        conf_.getAdv().setDataBase(new Composite());
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page2.html");
        initSessionNat(conf_,nav_);
        assertEq("page2.html", nav_.getCurrentUrl());
        assertEq("<html><body><form action=\"\" name=\"myform\" c:command=\"go\" n-f=\"0\"><select id=\"combo\" name=\"bean_one.chosenNumber\" n-i=\"0\"><option value=\"ONE\">1</option><option value=\"TWO\">2</option></select></form></body></html>", nav_.getHtmlText());

    }
    @Test
    public void refreshTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<a c:command=\"$goToNullPage\" href=\"\"/></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form action=\"DELETE\" name=\"myform\" c:command=\"$go\"></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        BeanOne bean_ = new BeanOne();
        bean_.setScope("request");
        BeanFive beanTwo_ = new BeanFive();
        beanTwo_.setScope("request");
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(bean_, conf_, "bean_one");
        putBean(beanTwo_, conf_, "bean_two");
        setupNative2(folder_, relative_, conf_);


        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("change", "page1.html");
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        conf_.getAdv().setDataBase(new Composite());
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        nav_.getSession().getRenderFiles().add("page2.html");
        initSessionNat(conf_,nav_);
        assertEq("page2.html", nav_.getCurrentUrl());
        nav_.getHtmlPage().setUrl(0);
        nav_.processRendFormRequest((BeanNatLgNames)conf_.getContext().getStandards(), conf_.getContext());
        setupBeansAfter(conf_);
        ((BeanNatLgNames)conf_.getContext().getStandards()).rendRefresh(nav_, conf_.getContext());
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body>HEAD<a c:command=\"$bean_one.goToNullPage\" href=\"\" n-a=\"0\"/></body></html>", nav_.getHtmlText());
        assertSame(getBean(conf_, "bean_one").getForms(), getBean(conf_, "bean_two").getForms());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

    }
    @Test
    public void processNavNullDbTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<a c:command=\"$goToNullPage\" href=\"\"/></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form action=\"DELETE\" name=\"myform\" c:command=\"$go\"></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        BeanOne bean_ = new BeanOne();
        bean_.setScope("request");
        BeanFive beanTwo_ = new BeanFive();
        beanTwo_.setScope("request");
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(bean_, conf_, "bean_one");
        putBean(beanTwo_, conf_, "bean_two");
        setupNative2(folder_, relative_, conf_);


        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("change", "page1.html");
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        conf_.getAdv().setDataBase(null);
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        nav_.getSession().getRenderFiles().add("page2.html");
        initSessionNat(conf_,nav_);
        assertEq("page2.html", nav_.getCurrentUrl());
        nav_.getHtmlPage().setUrl(0);
        nav_.processRendFormRequest((BeanNatLgNames)conf_.getContext().getStandards(), conf_.getContext());
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body>HEAD<a c:command=\"$bean_one.goToNullPage\" href=\"\" n-a=\"0\"/></body></html>", nav_.getHtmlText());
        assertSame(getBean(conf_, "bean_one").getForms(), getBean(conf_, "bean_two").getForms());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

    }

    private static void setupBeansAfter(NativeAnalyzedTestConfiguration _conf) {
        cleanBeans(_conf);
        for (EntryCust<String, Struct> e: _conf.getConfiguration().getBuiltBeans().entryList()) {
            putBean((Bean) ((BeanStruct) e.getValue()).getInstance(), _conf, e.getKey());
        }
    }

    private static void cleanBeans(NativeAnalyzedTestConfiguration _conf) {
        _conf.getAdv().getBeans().clear();
    }

    private static void initSessionNat(NativeAnalyzedTestConfiguration _conf,Navigation _nav) {
        StringMap<BeanInfo> map_ = new StringMap<BeanInfo>();
        BeanNatLgNames standards_ = _conf.getAdv();
        for (EntryCust<String, Bean> e: standards_.getBeans().entryList()) {
            BeanInfo i_ = new BeanInfo();
            i_.setClassName(e.getValue().getClassName());
            i_.setScope(e.getValue().getScope());
            map_.addEntry(e.getKey(),i_);
        }
        _nav.getSession().setBeansInfos(map_);
        _nav.setLanguages(new StringList(_nav.getLanguage()));
        AnalyzingDoc analyzingDoc_ = _conf.getAnalyzingDoc();
        analyzingDoc_.setup(_conf.getConfiguration(), _conf.getAdv());
        setupAna(analyzingDoc_, _conf.getAnalyzing());
        _conf.getAnalyzing().setForEachFetch(new NativeForEachFetch(standards_));
        _nav.initInstancesPattern(_conf.getAnalyzing(), analyzingDoc_);
        AnalyzedPageEl _page = _conf.getAnalyzing();
        StringMap<AnaRendDocumentBlock> d_ = _nav.analyzedRenders(_page, standards_, analyzingDoc_.getRendAnalysisMessages(), analyzingDoc_);
        _conf.setAnalyzed(d_);
        RendForwardInfos.buildExec(analyzingDoc_, d_, _conf.getForwards(), _conf.getConfiguration());
        _nav.initializeRendSession(_conf.getContext(), _conf.getAdv());
    }

    private boolean hasNatErr(String folder_, String relative_, String html_, BeanOne bean_) {
        NativeAnalyzedTestConfiguration context_ = contextElSec();

        setupNative(folder_, relative_, context_.getConfiguration());
        putBean(bean_, "bean_one", context_.getAdv());
        addBeanInfo(context_,"bean_one",new BeanStruct(bean_));
        context_.getAnalyzing().setForEachFetch(new NativeForEachFetch(context_.getAdv()));
        analyzeInner(context_.getConfiguration(),context_, html_);
        return !context_.isEmptyErrors();
    }

    private String getNatRes(String folder_, String relative_, String html_, BeanOne bean_) {
        NativeAnalyzedTestConfiguration conf_ = contextElSec();

        setupNative(folder_, relative_, conf_.getConfiguration());
        putBean(bean_, "bean_one", conf_.getAdv());
        addBeanInfo(conf_,"bean_one",new BeanStruct(bean_));
        AnalyzingDoc analyzingDoc_ = conf_.getAnalyzingDoc();
        setLocalFiles(conf_, analyzingDoc_);
        conf_.getAnalyzing().setForEachFetch(new NativeForEachFetch(conf_.getAdv()));
        analyzeInner(conf_.getConfiguration(),conf_, html_);
        RendForwardInfos.buildExec(analyzingDoc_, conf_.getAnalyzed(), new Forwards(), conf_.getConfiguration());
        setFirst(conf_);
        assertTrue(conf_.isEmptyErrors());
        String res = getSampleRes(conf_.getConfiguration(), conf_.getConfiguration().getRenders().getVal("page1.html"), conf_.getAdv(), conf_.getContext());
        assertNull(getException(conf_));
        return res;
    }

    private NativeAnalyzedTestConfiguration contextElSec() {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        return buildNat(conf_);
    }

    private static RendDocumentBlock buildRendWithTwoNativeBean(String html_, String htmlTwo_, BeanOne bean_, BeanTwo beanTwo_, NativeAnalyzedTestConfiguration conf_) {
        Configuration c_ = conf_.getConfiguration();
        addBeanInfo(conf_,"bean_one", new BeanStruct(bean_));
        addBeanInfo(conf_,"bean_two", new BeanStruct(beanTwo_));
        analyzeInner(c_,conf_, html_,htmlTwo_);
        RendForwardInfos.buildExec(conf_.getAnalyzingDoc(), conf_.getAnalyzed(), new Forwards(), conf_.getConfiguration());
        setFirst(conf_);
        return conf_.getConfiguration().getRenders().getVal("page1.html");
    }

    private static void analyzeInner(Configuration conf_, NativeAnalyzedTestConfiguration a_, String... _html) {
        StringMap<AnaRendDocumentBlock> d_ = analyze(conf_, a_, a_.getAnalyzingDoc(), _html);
        a_.setAnalyzed(d_);
    }

    private static void setLocalFiles(NativeAnalyzedTestConfiguration context_, AnalyzingDoc _analyzingDoc) {
        AnalyzedPageEl analyzing_ = context_.getAnalyzing();
        Configuration conf_ = context_.getConfiguration();
        setInnerLocalFilesLg(_analyzingDoc, analyzing_, conf_, context_.getAdv());
    }

    private static void setInnerLocalFilesLg(AnalyzingDoc _analyzingDoc, AnalyzedPageEl analyzing_, Configuration conf_, BeanLgNames _advStandards) {
        conf_.setCurrentLanguage("en");
        _analyzingDoc.setup(conf_, _advStandards);
        setInnerLocalFiles(_analyzingDoc, analyzing_);
    }

    private static void setInnerLocalFiles(AnalyzingDoc _analyzingDoc, AnalyzedPageEl analyzing) {
        _analyzingDoc.setLanguages(new StringList("en"));
        setupAna(_analyzingDoc, analyzing);
    }
    private static StringMap<AnaRendDocumentBlock> analyze(Configuration conf_, NativeAnalyzedTestConfiguration a_, AnalyzingDoc analyzingDoc_, String... _html) {
        int c_ = 1;
        StringMap<AnaRendDocumentBlock> d_ = new StringMap<AnaRendDocumentBlock>();
        for (String h: _html) {
            Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(h).getDocument();
            AnaRendDocumentBlock anaDoc_ = AnaRendDocumentBlock.newRendDocumentBlock("c:", doc_, h, a_.getAnalyzing().getPrimTypes(), conf_.getCurrentUrl(), conf_.getRendKeyWords());
            d_.addEntry("page"+c_+".html",anaDoc_);
            c_++;
        }
        setLocalFiles(a_, analyzingDoc_);
        for (AnaRendDocumentBlock v: d_.values()) {
            v.buildFctInstructions(analyzingDoc_, a_.getAnalyzing());
        }
        return d_;
    }
    protected static void setFirst(NativeAnalyzedTestConfiguration cont_) {
        RendDocumentBlock doc_ = cont_.getConfiguration().getRenders().getVal("page1.html");
        cont_.getConfiguration().setRendDocumentBlock(doc_);
    }

    private static NativeAnalyzedTestContext buildStdOne(Options _opt) {
        BeanNatLgNames lgNames_ = new CustBeanLgNames();
        basicStandards(lgNames_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        ContextEl contextEl_ = ContextFactory.simpleBuild((int) CustList.INDEX_NOT_FOUND_ELT, _opt, lgNames_, tabWidth_);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        ContextFactory.validatedStds(a_, kw_, new CustList<CommentDelimiters>(), _opt, contextEl_.getClasses().getCommon(), null, DefaultFileBuilder.newInstance(lgNames_.getContent()), lgNames_.getContent(), tabWidth_, page_);
        lgNames_.build();
        ValidatorStandard.setupOverrides(page_);
        return new NativeAnalyzedTestContext(contextEl_, page_, new Forwards(), lgNames_);
    }

    private static void addBeanInfo(NativeAnalyzedTestConfiguration _conf, String _id, Struct _str) {
        BeanInfo b_ = new BeanInfo();
        b_.setClassName(_str.getClassName(_conf.getContext()));
        b_.setResolvedClassName(_str.getClassName(_conf.getContext()));
        _conf.getConfiguration().getBeansInfos().addEntry(_id,b_);
        _conf.getConfiguration().getBuiltBeans().addEntry(_id,_str);
    }
    private static void basicStandards(BeanLgNames _lgNames) {
        _lgNames.getContent().setDefaultPkg("java.lang");
        _lgNames.getContent().getCoreNames().setAliasObject("java.lang.Object");
        _lgNames.getContent().getCoreNames().setAliasVoid("$void");
        _lgNames.getContent().getCharSeq().setAliasCharSequence("java.lang.CharSequence");
        _lgNames.getContent().getNbAlias().setAliasCompareTo("compareTo");
        _lgNames.getContent().getNbAlias().setAliasCompare("compare");
        _lgNames.getContent().getNbAlias().setAliasEquals("equals");
        _lgNames.getContent().getNbAlias().setAliasToStringMethod("toString");
        _lgNames.getContent().getNbAlias().setAliasValueOfMethod("valueOf");
        _lgNames.getContent().getNbAlias().setAliasMaxValueField("MAX_VALUE");
        _lgNames.getContent().getNbAlias().setAliasMinValueField("MIN_VALUE");
        _lgNames.getContent().getNbAlias().setAliasPlusInfinityField("PLUS_INFINITY");
        _lgNames.getContent().getNbAlias().setAliasMinusInfinityField("MINUS_INFINITY");
        _lgNames.getContent().getNbAlias().setAliasNanField("NAN");
        _lgNames.getContent().getPredefTypes().setAliasIteratorType("java.lang.$iterator");
        _lgNames.getContent().getPredefTypes().setAliasIterator("iterator");
        _lgNames.getContent().getPredefTypes().setAliasIterable("java.lang.$iterable");
        _lgNames.getContent().getPredefTypes().setAliasEnumParam("java.lang.$Enum");
        _lgNames.getContent().getPredefTypes().setAliasEnumType("java.lang.$en");
        _lgNames.getContent().getCoreNames().setAliasEnums("java.lang.$enums");
        _lgNames.getContent().getCharSeq().setAliasReplacement("code.util.Replacement");
        _lgNames.getContent().getCoreNames().setAliasStore("code.expressionlanguage.exceptions.DynamicArrayStoreException");
        _lgNames.getContent().getCoreNames().setAliasNullPe("code.util.exceptions.NullObjectException");
        _lgNames.getContent().getCoreNames().setAliasBadEncode("java.lang.$enc");
        _lgNames.getContent().getCoreNames().setAliasBadIndex("code.expressionlanguage.exceptions.BadIndexException");
        _lgNames.getContent().getCoreNames().setAliasIllegalArg("code.expressionlanguage.exceptions.IllegalArgument");
        _lgNames.getContent().getCoreNames().setAliasBadSize("code.expressionlanguage.exceptions.NegativeSizeException");
        _lgNames.getContent().getCoreNames().setAliasError("java.lang.Exception");
        _lgNames.getContent().getCoreNames().setAliasGetMessage("getMessage");
        _lgNames.getContent().getCoreNames().setAliasCastType("code.expressionlanguage.exceptions.DynamicCastClassException");
        _lgNames.getContent().getCoreNames().setAliasDivisionZero("code.expressionlanguage.exceptions.DivideZeroException");
        _lgNames.getContent().getMathRef().setAliasMath("java.lang.Math");
        _lgNames.getContent().getMathRef().setAliasAbs("abs");
        _lgNames.getContent().getMathRef().setAliasMod("mod");
        _lgNames.getContent().getMathRef().setAliasQuot("quot");
        _lgNames.getContent().getCoreNames().setAliasSof("code.expressionlanguage.exceptions.StackOverFlow");
        _lgNames.getContent().getCoreNames().setAliasNbFormat("java.lang.badFormat");
        _lgNames.getContent().getPrimTypes().setAliasPrimBoolean("$boolean");
        _lgNames.getContent().getPrimTypes().setAliasPrimByte("$byte");
        _lgNames.getContent().getPrimTypes().setAliasPrimShort("$short");
        _lgNames.getContent().getPrimTypes().setAliasPrimChar("$char");
        _lgNames.getContent().getPrimTypes().setAliasPrimInteger("$int");
        _lgNames.getContent().getPrimTypes().setAliasPrimLong("$long");
        _lgNames.getContent().getPrimTypes().setAliasPrimFloat("$float");
        _lgNames.getContent().getPrimTypes().setAliasPrimDouble("$double");
        _lgNames.getContent().getNbAlias().setAliasBoolean("java.lang.Boolean");
        _lgNames.getContent().getNbAlias().setAliasByte("java.lang.Byte");
        _lgNames.getContent().getNbAlias().setAliasShort("java.lang.Short");
        _lgNames.getContent().getNbAlias().setAliasCharacter("java.lang.Character");
        _lgNames.getContent().getNbAlias().setAliasInteger("java.lang.Integer");
        _lgNames.getContent().getNbAlias().setAliasLong("java.lang.Long");
        _lgNames.getContent().getNbAlias().setAliasFloat("java.lang.Float");
        _lgNames.getContent().getNbAlias().setAliasDouble("java.lang.Double");
        _lgNames.getContent().getNbAlias().setAliasNumber("java.lang.Number");
        _lgNames.getContent().getNbAlias().setAliasParseBoolean("parseBoolean");
        _lgNames.getContent().getNbAlias().setAliasParseByte("parseByte");
        _lgNames.getContent().getNbAlias().setAliasParseShort("parseShort");
        _lgNames.getContent().getNbAlias().setAliasParseInt("parseInt");
        _lgNames.getContent().getNbAlias().setAliasParseLong("parseLong");
        _lgNames.getContent().getNbAlias().setAliasParseFloat("parseFloat");
        _lgNames.getContent().getNbAlias().setAliasParseDouble("parseDouble");
        _lgNames.getContent().getNbAlias().setAliasParseByteOrNull("parseByteOrNull");
        _lgNames.getContent().getNbAlias().setAliasParseShortOrNull("parseShortOrNull");
        _lgNames.getContent().getNbAlias().setAliasParseIntOrNull("parseIntOrNull");
        _lgNames.getContent().getNbAlias().setAliasParseLongOrNull("parseLongOrNull");
        _lgNames.getContent().getNbAlias().setAliasParseFloatOrNull("parseFloatOrNull");
        _lgNames.getContent().getNbAlias().setAliasParseDoubleOrNull("parseDoubleOrNull");
        _lgNames.getContent().getNbAlias().setAliasBooleanValue("booleanValue");
        _lgNames.getContent().getNbAlias().setAliasByteValue("byteValue");
        _lgNames.getContent().getNbAlias().setAliasShortValue("shortValue");
        _lgNames.getContent().getNbAlias().setAliasCharValue("charValue");
        _lgNames.getContent().getNbAlias().setAliasIntValue("intValue");
        _lgNames.getContent().getNbAlias().setAliasLongValue("longValue");
        _lgNames.getContent().getNbAlias().setAliasFloatValue("floatValue");
        _lgNames.getContent().getNbAlias().setAliasDoubleValue("doubleValue");
        _lgNames.getContent().getNbAlias().setAliasDigit("digit");
        _lgNames.getContent().getNbAlias().setAliasIsDigit("isDigit");
        _lgNames.getContent().getNbAlias().setAliasIsLetter("isLetter");
        _lgNames.getContent().getNbAlias().setAliasIsLetterOrDigit("isLetterOrDigit");
        _lgNames.getContent().getNbAlias().setAliasIsWordChar("isWordChar");
        _lgNames.getContent().getNbAlias().setAliasIsLowerCase("isLowerCase");
        _lgNames.getContent().getNbAlias().setAliasIsUpperCase("isUpperCase");
        _lgNames.getContent().getNbAlias().setAliasIsWhitespace("isWhitespace");
        _lgNames.getContent().getNbAlias().setAliasIsSpace("isSpace");
        _lgNames.getContent().getNbAlias().setAliasIsInfinite("isInfinite");
        _lgNames.getContent().getNbAlias().setAliasIsNan("isNan");
        _lgNames.getContent().getNbAlias().setAliasForDigit("forDigit");
        _lgNames.getContent().getNbAlias().setAliasGetDirectionality("getDirectionality");
        _lgNames.getContent().getReflect().setAliasGetType("getType");
        _lgNames.getContent().getNbAlias().setAliasGetCharType("getType");
        _lgNames.getContent().getCharSeq().setAliasString("java.lang.String");
        _lgNames.getContent().getCharSeq().setAliasLength("length");
        _lgNames.getContent().getCharSeq().setAliasCharAt("charAt");
        _lgNames.getContent().getCharSeq().setAliasToCharArray("toCharArray");
        _lgNames.getContent().getCharSeq().setAliasSplit("split");
        _lgNames.getContent().getCharSeq().setAliasSplitChars("splitChars");
        _lgNames.getContent().getCharSeq().setAliasSplitStrings("splitStrings");
        _lgNames.getContent().getCharSeq().setAliasReplace("replace");
        _lgNames.getContent().getCharSeq().setAliasReplaceString("replace");
        _lgNames.getContent().getCharSeq().setAliasReplaceMultiple("replaceMultiple");
        _lgNames.getContent().getCharSeq().setAliasEqualsIgnoreCase("equalsIgnoreCase");
        _lgNames.getContent().getCharSeq().setAliasCompareToIgnoreCase("compareToIgnoreCase");
        _lgNames.getContent().getCharSeq().setAliasContains("contains");
        _lgNames.getContent().getCharSeq().setAliasEndsWith("endsWith");
        _lgNames.getContent().getCharSeq().setAliasStartsWith("startsWith");
        _lgNames.getContent().getCharSeq().setAliasIndexOf("indexOf");
        _lgNames.getContent().getCharSeq().setAliasFormat("format");
        _lgNames.getContent().getCharSeq().setAliasGetBytes("getBytes");
        _lgNames.getContent().getCharSeq().setAliasIsEmpty("isEmpty");
        _lgNames.getContent().getCharSeq().setAliasLastIndexOf("lastIndexOf");
        _lgNames.getContent().getCharSeq().setAliasRegionMatches("regionMatches");
        _lgNames.getContent().getCharSeq().setAliasSubstring("substring");
        _lgNames.getContent().getCharSeq().setAliasSubSequence("subSequence");
        _lgNames.getContent().getCharSeq().setAliasToLowerCase("toLowerCase");
        _lgNames.getContent().getCharSeq().setAliasToUpperCase("toUpperCase");
        _lgNames.getContent().getNbAlias().setAliasToLowerCaseChar("toLowerCase");
        _lgNames.getContent().getNbAlias().setAliasToUpperCaseChar("toUpperCase");
        _lgNames.getContent().getCharSeq().setAliasTrim("trim");
        _lgNames.getContent().getCharSeq().setAliasStringBuilder("java.lang.StringBuilder");
        _lgNames.getContent().getCharSeq().setAliasAppend("append");
        _lgNames.getContent().getCharSeq().setAliasCapacity("capacity");
        _lgNames.getContent().getCharSeq().setAliasClear("clear");
        _lgNames.getContent().getCharSeq().setAliasDelete("delete");
        _lgNames.getContent().getCharSeq().setAliasDeleteCharAt("deleteCharAt");
        _lgNames.getContent().getCharSeq().setAliasEnsureCapacity("ensureCapacity");
        _lgNames.getContent().getCharSeq().setAliasInsert("insert");
        _lgNames.getContent().getCharSeq().setAliasReverse("reverse");
        _lgNames.getContent().getCharSeq().setAliasSetCharAt("setCharAt");
        _lgNames.getContent().getCharSeq().setAliasSetLength("setLength");
        _lgNames.getContent().getCharSeq().setAliasSame("same");
        _lgNames.getContent().getCharSeq().setAliasTrimToSize("trimToSize");
        _lgNames.getContent().getCoreNames().setAliasErrorInitClass("java.lang.$defErrorClass");
        _lgNames.getContent().getCoreNames().setAliasClone("clone");
        _lgNames.getContent().getCoreNames().setAliasReadResources("readContent");
        _lgNames.getContent().getCoreNames().setAliasReadResourcesIndex("index");
        _lgNames.getContent().getCoreNames().setAliasReadResourcesNames("readNames");
        _lgNames.getContent().getCoreNames().setAliasReadResourcesNamesLength("nbNames");
        _lgNames.getContent().getCoreNames().setAliasResources("java.lang.Resources");
        _lgNames.getContent().getCoreNames().setAliasArrayLength("length");
        _lgNames.getContent().getPredefTypes().setAliasEnumValues("values");
        _lgNames.getContent().getReflect().setAliasInvokeTarget("java.lang.$invokeTaget");
        _lgNames.getContent().getCoreNames().setAliasGetCause("getCause");
        _lgNames.getContent().getReflect().setAliasClassNotFoundError("java.lang.$classNotFound");
        _lgNames.getContent().getReflect().setAliasGetVariableOwner("getVariableOwner");
        _lgNames.getContent().getReflect().setAliasGetGenericVariableOwner("getGenericVariableOwner");
        _lgNames.getContent().getReflect().setAliasGetString("getString");
        _lgNames.getContent().getReflect().setAliasClassType("java.lang.$Class");
        _lgNames.getContent().getStackElt().setAliasStackTraceElement("java.lang.$stack");
        _lgNames.getContent().getStackElt().setAliasCurrentStack("current");
        _lgNames.getContent().getStackElt().setAliasCurrentFullStack("currentFull");
        _lgNames.getContent().getReflect().setAliasFct("java.lang.$Fct");
        _lgNames.getContent().getReflect().setAliasCall("call");
        _lgNames.getContent().getReflect().setAliasMetaInfo("metaInfo");
        _lgNames.getContent().getReflect().setAliasInstance("instance");
        _lgNames.getContent().getReflect().setAliasAnnotationType("java.lang.$Annotation");
        _lgNames.getContent().getReflect().setAliasAnnotated("java.lang.$Annotated");
        _lgNames.getContent().getReflect().setAliasGetAnnotations("getAnnotations");
        _lgNames.getContent().getReflect().setAliasGetDefaultValue("getDefaultValue");
        _lgNames.getContent().getReflect().setAliasGetAnnotationsParameters("getAnnotationsParameters");
        _lgNames.getContent().getReflect().setAliasGetDeclaredConstructors("getDeclaredConstructors");
        _lgNames.getContent().getReflect().setAliasGetDeclaredFields("getDeclaredFields");
        _lgNames.getContent().getReflect().setAliasGetDeclaredAnonymousTypes("getDeclaredAnonymousTypes");
        _lgNames.getContent().getReflect().setAliasGetDeclaredAnonymousLambda("getDeclaredAnonymousLambda");
        _lgNames.getContent().getReflect().setAliasGetDeclaredAnonymousLambdaLocalVars("getDeclaredAnonymousLambdaLocVars");
        _lgNames.getContent().getReflect().setAliasGetDeclaredAnonymousLambdaLoopVars("getDeclaredAnonymousLambdaLoopVars");
        _lgNames.getContent().getReflect().setAliasGetDeclaredBlocks("getDeclaredBlocks");
        _lgNames.getContent().getReflect().setAliasGetDeclaredLocalTypes("getDeclaredLocalTypes");
        _lgNames.getContent().getReflect().setAliasGetDeclaredMethods("getDeclaredMethods");
        _lgNames.getContent().getReflect().setAliasGetDeclaredExplicits("getDeclaredExplicits");
        _lgNames.getContent().getReflect().setAliasGetDeclaredImplicits("getDeclaredImplicits");
        _lgNames.getContent().getReflect().setAliasGetDeclaredTrueOperators("getDeclaredTrueOperators");
        _lgNames.getContent().getReflect().setAliasGetDeclaredFalseOperators("getDeclaredFalseOperators");
        _lgNames.getContent().getReflect().setAliasGetDeclaredStaticMethods("getDeclaredStaticMethods");
        _lgNames.getContent().getReflect().setAliasMakeGeneric("makeGeneric");
        _lgNames.getContent().getReflect().setAliasGetAllClasses("getAllClasses");
        _lgNames.getContent().getReflect().setAliasGetOperators("getOperators");
        _lgNames.getContent().getReflect().setAliasConstructor("java.lang.$Constructor");
        _lgNames.getContent().getReflect().setAliasField("java.lang.$Field");
        _lgNames.getContent().getReflect().setAliasMethod("java.lang.$Method");
        _lgNames.getContent().getReflect().setAliasInvoke("invoke");
        _lgNames.getContent().getReflect().setAliasInvokeDirect("invokeDirect");
        _lgNames.getContent().getReflect().setAliasNewInstance("newInstance");
        _lgNames.getContent().getReflect().setAliasIsAbstract("isAbstract");
        _lgNames.getContent().getReflect().setAliasGetFileName("getFileName");
        _lgNames.getContent().getReflect().setAliasGetName("getName");
        _lgNames.getContent().getReflect().setAliasGetPrettyName("getPrettyName");
        _lgNames.getContent().getReflect().setAliasGetPrettySingleName("getPrettySingleName");
        _lgNames.getContent().getReflect().setAliasGetField("get");
        _lgNames.getContent().getReflect().setAliasSetField("set");
        _lgNames.getContent().getReflect().setAliasGetClass("getClass");
        _lgNames.getContent().getReflect().setAliasGetEnclosingType("getEnclosingType");
        _lgNames.getContent().getReflect().setAliasGetDeclaredClasses("getDeclaredClasses");
        _lgNames.getContent().getReflect().setAliasForName("forName");
        _lgNames.getContent().getCoreNames().setAliasObjectsUtil("java.lang.$ObjectsUtil");
        _lgNames.getContent().getCoreNames().setAliasStringUtil("java.lang.$StringUtil");
        _lgNames.getContent().getCoreNames().setAliasSameRef("eq");
        _lgNames.getContent().getCoreNames().setAliasGetParent("getParent");
        _lgNames.getContent().getCoreNames().setAliasSetParent("setParent");
        _lgNames.getContent().getPredefTypes().setAliasNext("next");
        _lgNames.getContent().getPredefTypes().setAliasHasNext("hasNext");
        _lgNames.getContent().getPredefTypes().setAliasIterableTable("java.lang.$iterableTable");
        _lgNames.getContent().getPredefTypes().setAliasIteratorTable("iteratorTable");
        _lgNames.getContent().getPredefTypes().setAliasIteratorTableType("java.lang.$iteratorTable");
        _lgNames.getContent().getPredefTypes().setAliasHasNextPair("hasNextPair");
        _lgNames.getContent().getPredefTypes().setAliasNextPair("nextPair");
        _lgNames.getContent().getPredefTypes().setAliasPairType("java.lang.$pair");
        _lgNames.getContent().getPredefTypes().setAliasGetFirst("getFirst");
        _lgNames.getContent().getPredefTypes().setAliasGetSecond("getSecond");
        _lgNames.getContent().getCoreNames().setAliasName("name");
        _lgNames.getContent().getCoreNames().setAliasOrdinal("ordinal");
        _lgNames.getContent().getPredefTypes().setAliasEnumName("$name");
        _lgNames.getContent().getPredefTypes().setAliasEnumOrdinal("$ordinal");
        _lgNames.getContent().getPredefTypes().setAliasEnumPredValueOf("valueOf");
        _lgNames.getContent().getPredefTypes().setAliasIterableVar("T");
        _lgNames.getContent().getPredefTypes().setAliasIteratorTypeVar("T");
        _lgNames.getContent().getPredefTypes().setAliasIterableTableVarFirst("T");
        _lgNames.getContent().getPredefTypes().setAliasIterableTableVarSecond("U");
        _lgNames.getContent().getPredefTypes().setAliasIteratorTableTypeVarFirst("T");
        _lgNames.getContent().getPredefTypes().setAliasIteratorTableTypeVarSecond("U");
        _lgNames.getContent().getPredefTypes().setAliasPairTypeVarFirst("T");
        _lgNames.getContent().getPredefTypes().setAliasPairTypeVarSecond("U");
        _lgNames.getContent().getPredefTypes().setAliasEnumParamVar("T");
        _lgNames.getContent().getPredefTypes().setAliasSeedGenerator("java.lang.Generator");
        _lgNames.getContent().getPredefTypes().setAliasSeedDoubleGenerator("java.lang.DoubleGenerator");
        _lgNames.getContent().getPredefTypes().setAliasSeedGet("get");
        _lgNames.getContent().getCharSeq().setAliasGetOldString("getOldString");
        _lgNames.getContent().getCharSeq().setAliasGetNewString("getNewString");
        _lgNames.getContent().getReflect().setAliasGetSuperClass("getSuperClass");
        _lgNames.getContent().getReflect().setAliasGetGenericSuperClass("getGenericSuperClass");
        _lgNames.getContent().getReflect().setAliasGetInterfaces("getInterfaces");
        _lgNames.getContent().getReflect().setAliasGetGenericInterfaces("getGenericInterfaces");
        _lgNames.getContent().getReflect().setAliasGetLowerBounds("getLowerBounds");
        _lgNames.getContent().getReflect().setAliasGetUpperBounds("getUpperBounds");
        _lgNames.getContent().getReflect().setAliasGetComponentType("getComponentType");
        _lgNames.getContent().getReflect().setAliasMakeArray("makeArray");
        _lgNames.getContent().getReflect().setAliasGetParameterTypes("getParameterTypes");
        _lgNames.getContent().getReflect().setAliasGetTypeParameters("getTypeParameters");
        _lgNames.getContent().getReflect().setAliasGetParameterNames("getGenericParameterTypes");
        _lgNames.getContent().getReflect().setAliasGetGenericReturnType("getGenericReturnType");
        _lgNames.getContent().getReflect().setAliasGetReturnType("getReturnType");
        _lgNames.getContent().getReflect().setAliasGetActualTypeArguments("getActualTypeArguments");
        _lgNames.getContent().getReflect().setAliasGetType("getType");
        _lgNames.getContent().getReflect().setAliasGetGenericType("getGenericType");
        _lgNames.getContent().getReflect().setAliasIsFinal("isFinal");
        _lgNames.getContent().getReflect().setAliasIsTypeVariable("isTypeVariable");
        _lgNames.getContent().getReflect().setAliasIsVariable("isVariable");
        _lgNames.getContent().getReflect().setAliasIsStatic("isStatic");
        _lgNames.getContent().getReflect().setAliasIsStaticCall("isStaticCall");
        _lgNames.getContent().getReflect().setAliasIsInstanceMethod("isInstanceMethod");
        _lgNames.getContent().getReflect().setAliasIsVarargs("isVarargs");
        _lgNames.getContent().getReflect().setAliasIsNormal("isNormal");
        _lgNames.getContent().getReflect().setAliasIsPublic("isPublic");
        _lgNames.getContent().getReflect().setAliasIsProtected("isProtected");
        _lgNames.getContent().getReflect().setAliasIsPackage("isPackage");
        _lgNames.getContent().getReflect().setAliasIsPrivate("isPrivate");
        _lgNames.getContent().getReflect().setAliasIsClass("isClass");
        _lgNames.getContent().getReflect().setAliasIsWildCard("isWildCard");
        _lgNames.getContent().getReflect().setAliasIsInterface("isInterface");
        _lgNames.getContent().getReflect().setAliasIsEnum("isEnum");
        _lgNames.getContent().getReflect().setAliasIsPrimitive("isPrimitive");
        _lgNames.getContent().getReflect().setAliasIsArray("isArray");
        _lgNames.getContent().getReflect().setAliasIsAnnotation("isAnnotation");
        _lgNames.getContent().getReflect().setAliasMakeWildCard("makeWildCard");
        _lgNames.getContent().getReflect().setAliasIsInstance("isInstance");
        _lgNames.getContent().getReflect().setAliasIsAssignableFrom("isAssignableFrom");
        _lgNames.getContent().getReflect().setAliasInit("init");
        _lgNames.getContent().getReflect().setAliasDefaultInstance("defaultInstance");
        _lgNames.getContent().getReflect().setAliasEnumValueOf("enumValueOf");
        _lgNames.getContent().getReflect().setAliasGetEnumConstants("getEnumConstants");
        _lgNames.getContent().getReflect().setAliasGetGenericBounds("getGenericBounds");
        _lgNames.getContent().getReflect().setAliasGetBounds("getBounds");
        _lgNames.getContent().getReflect().setAliasArrayNewInstance("newArrayInstance");
        _lgNames.getContent().getReflect().setAliasArrayGet("get");
        _lgNames.getContent().getReflect().setAliasArraySet("set");
        _lgNames.getContent().getReflect().setAliasArrayGetLength("getLength");
        _lgNames.getContent().getReflect().setAliasGetDeclaringClass("getDeclaringClass");
        _lgNames.getContent().getMathRef().setAliasBinQuot("binQuot");
        _lgNames.getContent().getMathRef().setAliasBinMod("binMod");
        _lgNames.getContent().getMathRef().setAliasPlus("plus");
        _lgNames.getContent().getMathRef().setAliasMinus("minus");
        _lgNames.getContent().getMathRef().setAliasMult("mult");
        _lgNames.getContent().getMathRef().setAliasAnd("and");
        _lgNames.getContent().getMathRef().setAliasOr("or");
        _lgNames.getContent().getMathRef().setAliasXor("xor");
        _lgNames.getContent().getMathRef().setAliasNegBin("negBin");
        _lgNames.getContent().getMathRef().setAliasNeg("neg");
        _lgNames.getContent().getMathRef().setAliasLt("lt");
        _lgNames.getContent().getMathRef().setAliasGt("gt");
        _lgNames.getContent().getMathRef().setAliasLe("le");
        _lgNames.getContent().getMathRef().setAliasGe("ge");
        _lgNames.getContent().getMathRef().setAliasShiftLeft("shiftLeft");
        _lgNames.getContent().getMathRef().setAliasShiftRight("shiftRight");
        _lgNames.getContent().getMathRef().setAliasBitShiftLeft("bitShiftLeft");
        _lgNames.getContent().getMathRef().setAliasBitShiftRight("bitShiftRight");
        _lgNames.getContent().getMathRef().setAliasRotateLeft("rotateLeft");
        _lgNames.getContent().getMathRef().setAliasRotateRight("rotateRight");
        _lgNames.getContent().getMathRef().setAliasRandom("random");
        _lgNames.getContent().getMathRef().setAliasSeed("seed");
        _lgNames.getDisplayedStrings().setFalseString("false");
        _lgNames.getDisplayedStrings().setTrueString("true");
        _lgNames.getDisplayedStrings().setNullString("");
        _lgNames.getDisplayedStrings().setNotNullCoverString("not null");
        _lgNames.getDisplayedStrings().setNullCoverString("null");
        _lgNames.getDisplayedStrings().setStaticCallString("staticCall");
        _lgNames.getDisplayedStrings().setStaticString("static");
        _lgNames.getDisplayedStrings().setInfinity("Infinity");
        _lgNames.getDisplayedStrings().setNan("Nan");
        _lgNames.getDisplayedStrings().setExponent("E");
    }


    private static void setupNative(String folder_, String relative_, Configuration context_) {
        setup(folder_, relative_, context_);
    }

    private static void putBean(Bean beanTwo_, String _key, LgNames _stds) {
        ((BeanNatLgNames) _stds).getBeans().put(_key, beanTwo_);
    }

    private static void setupNative(String folder_, String relative_, NativeAnalyzedTestConfiguration context_) {
        setup(folder_, relative_, context_.getConfiguration());
    }

    private static void setupNative2(String folder_, String relative_, NativeAnalyzedTestConfiguration conf_) {
        conf_.getConfiguration().setFirstUrl("page2.html");
        setup(folder_, relative_, conf_.getConfiguration());
    }
    private static void setupVal(String folder_, String relative_, NativeAnalyzedTestConfiguration conf_) {
        conf_.getConfiguration().setFirstUrl("page1.html");
        setup(folder_, relative_, conf_.getConfiguration());
        conf_.getAdv().getValidators().put("rate_val", new MyValidator());
    }

    private static void setSess(Configuration conf_, Navigation nav_) {
        nav_.setSession(conf_);
    }

    private static void setSess(NativeAnalyzedTestConfiguration conf_, Navigation nav_) {
        nav_.setSession(conf_.getConfiguration());
    }

    private static Bean getBean(NativeAnalyzedTestConfiguration conf_, String _key) {
        return conf_.getAdv().getBeans().getVal(_key);
    }

    private static void putBean(Bean beanTwo_, NativeAnalyzedTestConfiguration conf_, String _key) {
        putBean(beanTwo_, _key, conf_.getAdv());
    }

    private static String getSampleRes(Configuration conf_, RendDocumentBlock rendDocumentBlock_, BeanLgNames _stds, ContextEl _ctx) {
        return getRes(conf_,rendDocumentBlock_, _stds, _ctx);
    }

    private static String getSampleRes(NativeAnalyzedTestConfiguration conf_, RendDocumentBlock rendDocumentBlock_) {
        return getSampleRes(conf_.getConfiguration(), rendDocumentBlock_, conf_.getAdv(), conf_.getContext());
    }

    private static void preinit(NativeAnalyzedTestConfiguration conf_) {
        conf_.getAdv().preInitBeans(conf_.getConfiguration());
    }

    private static String getRes(Configuration conf_, RendDocumentBlock _doc, BeanLgNames _stds, ContextEl _context) {
        return RendBlock.getRes(_doc, conf_, _stds, _context);
    }

    private static Navigation newNavigation(NativeAnalyzedTestConfiguration _conf) {
        Navigation nav_ = new Navigation();
        nav_.setSession(_conf.getConfiguration());
        ContextEl context_ = _conf.getContext();
        setStack(nav_.getSession(), context_);
        return nav_;
    }

    private static void setStack(Configuration conf_1, ContextEl cont_) {
        cont_.setFullStack(new AdvancedFullStack(conf_1, cont_));
    }

    private static void setFiles(StringMap<String> files_, NativeAnalyzedTestConfiguration conf_) {
        conf_.getConfiguration().setFiles(files_);
    }

    private static Struct getException(NativeAnalyzedTestConfiguration _cont) {
        CallingState str_ = _cont.getContext().getCallingState();
        if (str_ instanceof Struct) {
            return (Struct) str_;
        }
        return null;
    }

    private static void setup(String folder_, String relative_, Configuration conf_) {
        setup(folder_, conf_);
        conf_.getProperties().put("msg_example", relative_);
    }

    private static void setup(String folder_, Configuration conf_) {
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
    }

    private static void setupAna(AnalyzingDoc _analyzingDoc, AnalyzedPageEl page_) {
        AnalyzingDoc.setupInts(page_, _analyzingDoc);
    }

}
