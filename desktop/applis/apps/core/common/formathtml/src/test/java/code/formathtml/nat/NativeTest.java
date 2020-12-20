package code.formathtml.nat;

import code.bean.Bean;
import code.bean.nat.*;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.DefaultFileBuilder;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.ValidatorStandard;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.IntStruct;
import code.formathtml.*;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.sample.BeanFive;
import code.formathtml.sample.BeanOne;
import code.formathtml.sample.BeanTwo;
import code.formathtml.sample.Composite;
import code.formathtml.sample.CustBeanLgNames;
import code.formathtml.sample.MyValidator;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.fwd.RendForwardInfos;
import code.formathtml.structs.BeanInfo;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.Struct;
import code.bean.BeanStruct;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.util.*;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.CustList;
import code.util.EntryCust;
import code.util.LongMap;
import code.util.LongTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.StringMapObject;
import code.util.core.IndexConstants;
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
        BeanNatLgNames lgNames_ = new CustBeanLgNames();
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
        NativeConfigurationLoader nat_ = new NativeConfigurationLoader(lgNames_);
        DualAnalyzedContext du_ = n_.loadConfiguration(xmlConf_, "", lgNames_, DefaultFileBuilder.newInstance(lgNames_.getContent()), nat_);
        n_.setFiles(files_);
        lgNames_.setupAll(n_, n_.getSession(), n_.getFiles(), du_);
        n_.initializeRendSession(du_.getContext().getContext(), du_.getStds());
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
        a_.getDual().getRenderFiles().add("page1.html");
        a_.getDual().getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("code.formathtml.classes.BeanOne");
        conf_.getBeansInfos().addEntry("bean_one",i_);
        i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("code.formathtml.classes.BeanTwo");
        conf_.getBeansInfos().addEntry("bean_two",i_);
        conf_.init(a_.getDual());
        Navigation n_ = new Navigation();
        setSess(conf_, n_);
        n_.setFiles(files_);
        a_.getAdv().setupAll(n_, n_.getSession(), n_.getFiles(), new DualAnalyzedContext(a_.getAnalyzing(),a_.getAdv(),a_.getDual()));
        n_.initializeRendSession(a_.getContext(), a_.getAdv());
        assertEq("<html><body><form action=\"\" name=\"myform\" c:command=\"go\" n-f=\"0\"><input type=\"text\" name=\"bean_two.typedString\" n-i=\"0\" value=\"TYPED_STRING\"/></form></body></html>", n_.getHtmlText());
        assertEq("page2.html", n_.getCurrentUrl());
    }

    private static NativeAnalyzedTestConfiguration buildNat(Configuration _conf) {
        Options opt_ = new Options();
        NativeAnalyzedTestContext cont_ = buildStdOne(opt_);
        return new NativeAnalyzedTestConfiguration(_conf,cont_, cont_.getForwards(), cont_.getStds());
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
        conf_.getDual().getRenderFiles().add("page1.html");
        conf_.getDual().getRenderFiles().add("page2.html");
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
        conf_.getDual().getRenderFiles().add("page1.html");
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
        conf_.getDual().getRenderFiles().add("page1.html");
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
        conf_.getDual().getRenderFiles().add("page1.html");
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
        conf_.getDual().getRenderFiles().add("page1.html");
        conf_.getDual().getRenderFiles().add("page2.html");
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
        conf_.getDual().getRenderFiles().add("page2.html");
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
        conf_.getDual().getRenderFiles().add("page2.html");
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
        conf_.getDual().getRenderFiles().add("page2.html");
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
        conf_.getDual().getRenderFiles().add("page2.html");
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
        conf_.getDual().getRenderFiles().add("page1.html");
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
        conf_.getDual().getRenderFiles().add("page1.html");
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
        conf_.getDual().getRenderFiles().add("page1.html");
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
        conf_.getDual().getRenderFiles().add("page1.html");
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
        conf_.getDual().getRenderFiles().add("page1.html");
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
        conf_.getDual().getRenderFiles().add("page1.html");
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
        conf_.getDual().getRenderFiles().add("page2.html");
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
        conf_.getDual().getRenderFiles().add("page1.html");
        conf_.getDual().getRenderFiles().add("page2.html");
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
        conf_.getDual().getRenderFiles().add("page1.html");
        conf_.getDual().getRenderFiles().add("page2.html");
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
        analyzingDoc_.setup(_conf.getConfiguration(), _conf.getDual());
        setupAna(analyzingDoc_, _conf.getAnalyzing());
        _conf.getAnalyzing().setForEachFetch(new NativeForEachFetch(standards_));
        BeanNatLgNames.initInstancesPattern(_conf.getConfiguration(), analyzingDoc_);
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        StringMap<AnaRendDocumentBlock> d_ = _nav.analyzedRenders(page_, standards_, analyzingDoc_, _conf.getDual());
        _conf.setAnalyzed(d_);
        RendForwardInfos.buildExec(analyzingDoc_, d_, _conf.getForwards(), _conf.getConfiguration());
        _nav.initializeRendSession(_conf.getContext(), _conf.getAdv());
    }

    private boolean hasNatErr(String _folder, String _relative, String _html, BeanOne _bean) {
        NativeAnalyzedTestConfiguration context_ = contextElSec();

        setupNative(_folder, _relative, context_.getDual());
        putBean(_bean, "bean_one", context_.getAdv());
        addBeanInfo(context_,"bean_one",new BeanStruct(_bean));
        context_.getAnalyzing().setForEachFetch(new NativeForEachFetch(context_.getAdv()));
        analyzeInner(context_.getConfiguration(),context_, _html);
        return !context_.isEmptyErrors();
    }

    private String getNatRes(String _folder, String _relative, String _html, BeanOne _bean) {
        NativeAnalyzedTestConfiguration conf_ = contextElSec();

        setupNative(_folder, _relative, conf_.getDual());
        putBean(_bean, "bean_one", conf_.getAdv());
        addBeanInfo(conf_,"bean_one",new BeanStruct(_bean));
        AnalyzingDoc analyzingDoc_ = conf_.getAnalyzingDoc();
        setLocalFiles(conf_, analyzingDoc_);
        conf_.getAnalyzing().setForEachFetch(new NativeForEachFetch(conf_.getAdv()));
        analyzeInner(conf_.getConfiguration(),conf_, _html);
        RendForwardInfos.buildExec(analyzingDoc_, conf_.getAnalyzed(), new Forwards(), conf_.getConfiguration());
        setFirst(conf_);
        assertTrue(conf_.isEmptyErrors());
        String res_ = getSampleRes(conf_.getConfiguration(), conf_.getConfiguration().getRenders().getVal("page1.html"), conf_.getAdv(), conf_.getContext());
        assertNull(getException(conf_));
        return res_;
    }

    private NativeAnalyzedTestConfiguration contextElSec() {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        return buildNat(conf_);
    }

    private static RendDocumentBlock buildRendWithTwoNativeBean(String _html, String _htmlTwo, BeanOne _bean, BeanTwo _beanTwo, NativeAnalyzedTestConfiguration _conf) {
        Configuration c_ = _conf.getConfiguration();
        addBeanInfo(_conf,"bean_one", new BeanStruct(_bean));
        addBeanInfo(_conf,"bean_two", new BeanStruct(_beanTwo));
        analyzeInner(c_,_conf, _html,_htmlTwo);
        RendForwardInfos.buildExec(_conf.getAnalyzingDoc(), _conf.getAnalyzed(), new Forwards(), _conf.getConfiguration());
        setFirst(_conf);
        return _conf.getConfiguration().getRenders().getVal("page1.html");
    }

    private static void analyzeInner(Configuration _conf, NativeAnalyzedTestConfiguration _a, String... _html) {
        StringMap<AnaRendDocumentBlock> d_ = analyze(_conf, _a, _a.getAnalyzingDoc(), _html);
        _a.setAnalyzed(d_);
    }

    private static void setLocalFiles(NativeAnalyzedTestConfiguration _context, AnalyzingDoc _analyzingDoc) {
        AnalyzedPageEl analyzing_ = _context.getAnalyzing();
        Configuration conf_ = _context.getConfiguration();
        conf_.setCurrentLanguage("en");
        _analyzingDoc.setup(conf_, _context.getDual());
        setInnerLocalFiles(_analyzingDoc, analyzing_);
    }

    private static void setInnerLocalFiles(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _analyzing) {
        _analyzingDoc.setLanguages(new StringList("en"));
        setupAna(_analyzingDoc, _analyzing);
    }
    private static StringMap<AnaRendDocumentBlock> analyze(Configuration _conf, NativeAnalyzedTestConfiguration _a, AnalyzingDoc _analyzingDoc, String... _html) {
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
    protected static void setFirst(NativeAnalyzedTestConfiguration _cont) {
        RendDocumentBlock doc_ = _cont.getConfiguration().getRenders().getVal("page1.html");
        _cont.getConfiguration().setRendDocumentBlock(doc_);
    }

    private static NativeAnalyzedTestContext buildStdOne(Options _opt) {
        BeanNatLgNames lgNames_ = new CustBeanLgNames();
        basicStandards(lgNames_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        ContextEl contextEl_ = ContextFactory.simpleBuild((int) IndexConstants.INDEX_NOT_FOUND_ELT, _opt, lgNames_, tabWidth_);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        ContextFactory.validatedStds(lgNames_, a_, kw_, new CustList<CommentDelimiters>(), _opt, contextEl_.getClasses().getCommon(), null, DefaultFileBuilder.newInstance(lgNames_.getContent()), lgNames_.getContent(), tabWidth_, page_, new NativeFieldFilter());
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
        DefaultInitialization.basicStandards(_lgNames);
    }


    private static void setupNative(String _folder, String _relative, DualConfigurationContext _context) {
        setup(_folder, _relative, _context);
    }

    private static void putBean(Bean _beanTwo, String _key, LgNames _stds) {
        ((BeanNatLgNames) _stds).getBeans().put(_key, _beanTwo);
    }

    private static void setupNative(String _folder, String _relative, NativeAnalyzedTestConfiguration _context) {
        setup(_folder, _relative, _context.getDual());
    }

    private static void setupNative2(String _folder, String _relative, NativeAnalyzedTestConfiguration _conf) {
        _conf.getConfiguration().setFirstUrl("page2.html");
        setup(_folder, _relative, _conf.getDual());
    }
    private static void setupVal(String _folder, String _relative, NativeAnalyzedTestConfiguration _conf) {
        _conf.getConfiguration().setFirstUrl("page1.html");
        setup(_folder, _relative, _conf.getDual());
        _conf.getAdv().getValidators().put("rate_val", new MyValidator());
    }

    private static void setSess(Configuration _conf, Navigation _nav) {
        _nav.setSession(_conf);
    }

    private static void setSess(NativeAnalyzedTestConfiguration _conf, Navigation _nav) {
        _nav.setSession(_conf.getConfiguration());
    }

    private static Bean getBean(NativeAnalyzedTestConfiguration _conf, String _key) {
        return _conf.getAdv().getBeans().getVal(_key);
    }

    private static void putBean(Bean _beanTwo, NativeAnalyzedTestConfiguration _conf, String _key) {
        putBean(_beanTwo, _key, _conf.getAdv());
    }

    private static String getSampleRes(Configuration _conf, RendDocumentBlock _rendDocumentBlock, BeanLgNames _stds, ContextEl _ctx) {
        return getRes(_conf,_rendDocumentBlock, _stds, _ctx);
    }

    private static String getSampleRes(NativeAnalyzedTestConfiguration _conf, RendDocumentBlock _rendDocumentBlock) {
        return getSampleRes(_conf.getConfiguration(), _rendDocumentBlock, _conf.getAdv(), _conf.getContext());
    }

    private static void preinit(NativeAnalyzedTestConfiguration _conf) {
        _conf.getAdv().preInitBeans(_conf.getConfiguration());
    }

    private static String getRes(Configuration _conf, RendDocumentBlock _doc, BeanLgNames _stds, ContextEl _context) {
        return RendBlock.getRes(_doc, _conf, _stds, _context);
    }

    private static Navigation newNavigation(NativeAnalyzedTestConfiguration _conf) {
        Navigation nav_ = new Navigation();
        nav_.setSession(_conf.getConfiguration());

        return nav_;
    }

    private static void setFiles(StringMap<String> _files, NativeAnalyzedTestConfiguration _conf) {
        _conf.getConfiguration().setFiles(_files);
    }

    private static Struct getException(NativeAnalyzedTestConfiguration _cont) {
        CallingState str_ = _cont.getContext().getCallingState();
        if (str_ instanceof CustomFoundExc) {
            return ((CustomFoundExc) str_).getStruct();
        }
        return null;
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
