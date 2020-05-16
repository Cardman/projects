package code.formathtml.nat;

import code.bean.Bean;
import code.bean.BeanInfo;
import code.bean.validator.Validator;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.*;
import code.formathtml.classes.*;
import code.formathtml.structs.BeanStruct;
import code.formathtml.structs.StdStruct;
import code.formathtml.util.*;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.*;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public final class NativeTest extends CommonRender {
    private static final String COMPOSITE = "code.expressionlanguage.classes.Composite";
    private static final String ALIAS_BEAN_ONE = "code.expressionlanguage.classes.BeanOne";
    @Test
    public void processAffect2Test() {
        Configuration context_ = contextEl();
        CustLgNames custLgNames_ = (CustLgNames) context_.getContext().getStandards();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Composite c_ = new Composite();
        lv_.setStruct(new StdStruct(c_, custLgNames_.getAliasComposite()));
        lv_.setClassName("code.expressionlanguage.classes.Composite");
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        assertEq(0, c_.getInteger());
        processEl("v.integer=12i", context_);
        assertEq(COMPOSITE, lv_.getClassName());
        assertEq(12, c_.getInteger());
    }
    @Test
    public void processEl9Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = processEl("composite.integer", context_);
        assertEq(0, ((NumberStruct)arg_.getStruct()).longStruct());
    }

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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        Configuration context_ = contextElSec();
        context_.setBeans(new StringMap<Bean>());
        context_.setMessagesFolder(folder_);
        context_.setProperties(new StringMap<String>());
        context_.getProperties().put("msg_example", relative_);
        buildRendWithOneNativeBean(html_, bean_, context_);
        assertTrue(!context_.isEmptyErrors());
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
        Configuration context_ = contextElSec();
        context_.setBeans(new StringMap<Bean>());
        context_.setMessagesFolder(folder_);
        context_.setProperties(new StringMap<String>());
        context_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneNativeBean(html_, bean_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html><body>Not empty</body></html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
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
        Configuration context_ = contextElSec();
        context_.setBeans(new StringMap<Bean>());
        context_.setMessagesFolder(folder_);
        context_.setProperties(new StringMap<String>());
        context_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneNativeBean(html_, bean_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html><body>Empty</body></html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
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
        Configuration context_ = contextElSec();
        context_.setBeans(new StringMap<Bean>());
        context_.setMessagesFolder(folder_);
        context_.setProperties(new StringMap<String>());
        context_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneNativeBean(html_, bean_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html><body><ul><li>5</li><li>6</li></ul></body></html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
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
        Configuration context_ = contextElSec();
        context_.setBeans(new StringMap<Bean>());
        context_.setMessagesFolder(folder_);
        context_.setProperties(new StringMap<String>());
        context_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneNativeBean(html_, bean_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html><body><ul><li>5</li><li>6</li></ul></body></html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
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
        Configuration context_ = contextElSec();
        context_.setBeans(new StringMap<Bean>());
        context_.setMessagesFolder(folder_);
        context_.setProperties(new StringMap<String>());
        context_.getProperties().put("msg_example", relative_);
        buildRendWithOneNativeBean(html_, bean_, context_);
        assertTrue(!context_.isEmptyErrors());
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
        Configuration conf_ = contextElSec();
        conf_.setBeans(new StringMap<Bean>());
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneNativeBean(html_, bean_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body><table><tr><td>ONE</td><td>1</td></tr><tr><td>TWO</td><td>2</td></tr></table></body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElSec();
        conf_.setBeans(new StringMap<Bean>());
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneNativeBean(html_, bean_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body><table><tr><td>ONE</td><td>1</td></tr><tr><td>TWO</td><td>2</td></tr></table></body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElSec();
        conf_.setBeans(new StringMap<Bean>());
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneNativeBean(html_, bean_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body><table><tr><td>ONE</td><td>1</td><td>ONE</td><td>1</td></tr><tr><td>ONE</td><td>1</td><td>TWO</td><td>2</td></tr><tr><td>TWO</td><td>2</td><td>ONE</td><td>1</td></tr><tr><td>TWO</td><td>2</td><td>TWO</td><td>2</td></tr></table></body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        n_.loadConfiguration(xmlConf_,"",lgNames_);
        n_.setFiles(files_);
        n_.setupRendClassesInit();
        n_.initializeRendSession();
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
        ContextEl cont_ = getContextEl();
        conf_.setContext(cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        cont_.setExecutingInstance(conf_);
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
        n_.setSession(conf_);
        n_.setFiles(files_);
        n_.setupRendClassesInit();
        n_.initializeRendSession();
        assertEq("<html><body><form action=\"\" name=\"myform\" c:command=\"go\" n-f=\"0\"><input type=\"text\" name=\"bean_two.typedString\" n-i=\"0\" value=\"TYPED_STRING\"/></form></body></html>", n_.getHtmlText());
        assertEq("page2.html", n_.getCurrentUrl());
    }

    private static ContextEl getContextEl() {
        Options opt_ = new Options();
        return buildStdOne(opt_);
    }

    private static Argument processEl(String _el, Configuration _cont) {
        if (_cont.hasPages() && _cont.getContext().getAnalyzing() != null) {
            _cont.getContext().setGlobalClass(_cont.getLastPage().getGlobalClass());
        }
        return RenderExpUtil.processEl(_el, 0, _cont);
    }
    private static void addBean(Configuration _conf, Object _bean) {
        _conf.getLastPage().setGlobalArgumentStruct(StdStruct.newInstance(_bean, NativeTest.ALIAS_BEAN_ONE),_conf);
        _conf.getContext().setGlobalClass(NativeTest.ALIAS_BEAN_ONE);
    }
    private Configuration contextEl() {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        Options opt_ = new Options();
        ContextEl cont_ = buildStdTwo(opt_);
        assertTrue(cont_.isEmptyErrors());
        conf_.setContext(cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        cont_.setExecutingInstance(conf_);
        return conf_;
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
        Configuration conf_ = contextElSec();

        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page2.html");
        conf_.setValidators(new StringMap<Validator>());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("change", "page1.html");
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        ((BeanNatLgNames)nav_.getSession().getStandards()).setDataBase(new Composite());
        nav_.setLanguage(locale_);
        nav_.setSession(conf_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        nav_.getSession().getRenderFiles().add("page2.html");
        initSession(nav_);
        assertEq("page2.html", nav_.getCurrentUrl());
        nav_.getHtmlPage().setUrl(0);
        nav_.processRendFormRequest();
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body>HEAD<a c:command=\"$bean_one.goToNullPage\" href=\"\" n-a=\"0\"/></body></html>", nav_.getHtmlText());
        assertSame(conf_.getBeans().getVal("bean_one").getForms(), conf_.getBeans().getVal("bean_two").getForms());
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
        Configuration conf_ = contextElSec();
        conf_.setBeans(new StringMap<Bean>());
        conf_.setFirstUrl("page1.html");
        bean_.getForms().put("key", "sample_value");
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.getAdvStandards().preInitBeans(conf_);


        RendDocumentBlock rendDocumentBlock_ = buildRendWithTwoNativeBean(html_, htmlTwo_, bean_, beanTwo_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body><a href=\"\" c:command=\"go\" n-a=\"0\">Test {0}2</a></body></html>", RendBlock.getRes(rendDocumentBlock_, conf_));
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
        Configuration conf_ = contextElSec();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.getAdvStandards().preInitBeans(conf_);
        conf_.getAnalyzingDoc().setFiles(files_);

        RendDocumentBlock rendDocumentBlock_ = buildRendWithTwoNativeBean(html_, htmlTwo_, bean_, beanTwo_, conf_);
        assertTrue(conf_.isEmptyErrors());
        String render_ = RendBlock.getRes(rendDocumentBlock_, conf_);
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
        Configuration conf_ = contextElSec();

        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page1.html");
        conf_.setValidators(new StringMap<Validator>());
        conf_.getValidators().put("rate_val", new MyValidator());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        nav_.setSession(conf_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        initSession(nav_);
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
        nav_.processRendFormRequest();
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_two", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.goToPage\" action=\"\" n-f=\"0\"><input id=\"txt\" type=\"text\" name=\"bean_two.selectedString\" c:validator=\"rate_val\" n-i=\"0\" value=\"ONE_TWO\"/><span c:for=\"txt\">ONE_TWO is not a no zero rate</span></form></body></html>", nav_.getHtmlText());
        beanTwo_ = (BeanOne) conf_.getBeans().getVal("bean_two");
        StringMapObject map_ = beanTwo_.getForms();
        assertEq(0, map_.size());
        assertEq(0, conf_.getBeans().getVal("bean_two").getForms().size());
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
        Configuration conf_ = contextElSec();

        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page1.html");
        conf_.setValidators(new StringMap<Validator>());
        conf_.getValidators().put("rate_val", new MyValidator());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        nav_.setSession(conf_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        initSession(nav_);
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
        nav_.processRendFormRequest();
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_two", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input id=\"txt\" type=\"number\" name=\"bean_two.typedInt\" c:validator=\"rate_val\" n-i=\"0\" value=\"0\"/><span c:for=\"txt\"/></form></body></html>", nav_.getHtmlText());
        beanTwo_ = (BeanTwo) conf_.getBeans().getVal("bean_two");
        StringMapObject map_ = beanTwo_.getForms();
        assertEq(0, map_.size());
        assertEq(0, conf_.getBeans().getVal("bean_two").getForms().size());
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
        Configuration conf_ = contextElSec();

        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page1.html");
        conf_.setValidators(new StringMap<Validator>());
        conf_.getValidators().put("rate_val", new MyValidator());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        nav_.setSession(conf_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        initSession(nav_);
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
        nav_.processRendFormRequest();
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_two", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input id=\"txt\" type=\"number\" name=\"bean_two.typedInt\" c:validator=\"rate_val\" n-i=\"0\" value=\"0\"/><span c:for=\"txt\"/><input id=\"txt2\" type=\"text\" name=\"bean_two.typedString\" n-i=\"1\" value=\"TYPED_STRING\"/></form></body></html>", nav_.getHtmlText());
        beanTwo_ = (BeanTwo) conf_.getBeans().getVal("bean_two");
        StringMapObject map_ = beanTwo_.getForms();
        assertEq(0, map_.size());
        assertEq(0, conf_.getBeans().getVal("bean_two").getForms().size());
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
        Configuration conf_ = contextElSec();

        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.getBeans().put("bean_one", beanOne_);
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page1.html");
        conf_.setValidators(new StringMap<Validator>());
        conf_.getValidators().put("rate_val", new MyValidator());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        nav_.setSession(conf_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        nav_.getSession().getRenderFiles().add("page2.html");
        initSession(nav_);
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
        nav_.processRendFormRequest();
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_two", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input id=\"txt\" type=\"number\" name=\"bean_two.typedInt\" c:validator=\"rate_val\" n-i=\"0\" value=\"0\"/><span c:for=\"txt\"/><input id=\"txt2\" type=\"text\" name=\"bean_one.selectedString\" n-i=\"1\" value=\"ONE\"/></form></body></html>", nav_.getHtmlText());
        beanTwo_ = (BeanTwo) conf_.getBeans().getVal("bean_two");
        StringMapObject map_ = beanTwo_.getForms();
        assertEq(0, map_.size());
        assertEq(0, conf_.getBeans().getVal("bean_two").getForms().size());
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
        Configuration conf_ = contextElSec();

        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page2.html");
        conf_.setValidators(new StringMap<Validator>());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        conf_.setNavigation(new StringMap<StringMap<String>>());
        Navigation nav_ = newNavigation(conf_);
        ((BeanNatLgNames)nav_.getSession().getStandards()).setDataBase(new Composite());
        nav_.setLanguage(locale_);
        nav_.setSession(conf_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page2.html");
        initSession(nav_);
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
        Configuration conf_ = contextElSec();

        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page2.html");
        conf_.setValidators(new StringMap<Validator>());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        conf_.setNavigation(new StringMap<StringMap<String>>());
        Navigation nav_ = newNavigation(conf_);
        ((BeanNatLgNames)nav_.getSession().getStandards()).setDataBase(new Composite());
        nav_.setLanguage(locale_);
        nav_.setSession(conf_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page2.html");
        initSession(nav_);
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
        Configuration conf_ = contextElSec();

        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page2.html");
        conf_.setValidators(new StringMap<Validator>());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        conf_.setNavigation(new StringMap<StringMap<String>>());
        Navigation nav_ = newNavigation(conf_);
        ((BeanNatLgNames)nav_.getSession().getStandards()).setDataBase(new Composite());
        nav_.setLanguage(locale_);
        nav_.setSession(conf_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page2.html");
        initSession(nav_);
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
        Configuration conf_ = contextElSec();

        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page2.html");
        conf_.setValidators(new StringMap<Validator>());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        conf_.setNavigation(new StringMap<StringMap<String>>());
        Navigation nav_ = newNavigation(conf_);
        ((BeanNatLgNames)nav_.getSession().getStandards()).setDataBase(new Composite());
        nav_.setLanguage(locale_);
        nav_.setSession(conf_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page2.html");
        initSession(nav_);
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
        Configuration conf_ = contextElSec();

        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page1.html");
        conf_.setValidators(new StringMap<Validator>());
        conf_.getValidators().put("rate_val", new MyValidator());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        nav_.setSession(conf_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        initSession(nav_);
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
        nav_.processRendFormRequest();
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_two", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input type=\"number\" name=\"bean_two.nullableInt\" n-i=\"0\" value=\"10\"/></form></body></html>", nav_.getHtmlText());
        beanTwo_ = (BeanTwo) conf_.getBeans().getVal("bean_two");
        StringMapObject map_ = beanTwo_.getForms();
        assertEq(8, map_.size());
        assertEq(8, conf_.getBeans().getVal("bean_two").getForms().size());
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
        Configuration conf_ = contextElSec();

        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page1.html");
        conf_.setValidators(new StringMap<Validator>());
        conf_.getValidators().put("rate_val", new MyValidator());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        nav_.setSession(conf_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        initSession(nav_);
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
        nav_.processRendFormRequest();
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_two", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input type=\"number\" name=\"bean_two.nullableInt\" n-i=\"0\" value=\"\"/></form></body></html>", nav_.getHtmlText());
        beanTwo_ = (BeanTwo) conf_.getBeans().getVal("bean_two");
        StringMapObject map_ = beanTwo_.getForms();
        assertEq(8, map_.size());
        assertEq(8, conf_.getBeans().getVal("bean_two").getForms().size());
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
        Configuration conf_ = contextElSec();

        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page1.html");
        conf_.setValidators(new StringMap<Validator>());
        conf_.getValidators().put("rate_val", new MyValidator());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        nav_.setSession(conf_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        initSession(nav_);
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
        nav_.processRendFormRequest();
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_two", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input type=\"checkbox\" name=\"bean_two.checked\" n-i=\"0\" checked=\"checked\"/></form></body></html>", nav_.getHtmlText());
        beanTwo_ = (BeanTwo) conf_.getBeans().getVal("bean_two");
        StringMapObject map_ = beanTwo_.getForms();
        assertEq(8, map_.size());
        assertEq(8, conf_.getBeans().getVal("bean_two").getForms().size());
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
        Configuration conf_ = contextElSec();

        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page1.html");
        conf_.setValidators(new StringMap<Validator>());
        conf_.getValidators().put("rate_val", new MyValidator());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        nav_.setSession(conf_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        initSession(nav_);
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
        nav_.processRendFormRequest();
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_two", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input c:className=\"code.formathtml.classes.Rate\" type=\"text\" name=\"bean_two.rate\" n-i=\"0\" value=\"1/2\"/></form></body></html>", nav_.getHtmlText());
        beanTwo_ = (BeanTwo) conf_.getBeans().getVal("bean_two");
        StringMapObject map_ = beanTwo_.getForms();
        assertEq(8, map_.size());
        assertEq(8, conf_.getBeans().getVal("bean_two").getForms().size());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

    }

    @Test
    public void processNav16Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form c:command=\"$go\"><input type=\"number\" name=\"typedShort\" c:varValue=\"typedShort\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", htmlTwo_);
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setScope("session");
        Configuration conf_ = contextElSec();

        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page1.html");
        conf_.setValidators(new StringMap<Validator>());
        conf_.getValidators().put("rate_val", new MyValidator());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        nav_.setSession(conf_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        initSession(nav_);
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
        nav_.processRendFormRequest();
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_two", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input type=\"number\" name=\"bean_two.typedShort\" n-i=\"0\" value=\"12\"/></form></body></html>", nav_.getHtmlText());
        beanTwo_ = (BeanTwo) conf_.getBeans().getVal("bean_two");
        StringMapObject map_ = beanTwo_.getForms();
        assertEq(8, map_.size());
        assertEq(8, conf_.getBeans().getVal("bean_two").getForms().size());
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
        Configuration conf_ = contextElSec();

        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page1.html");
        conf_.setValidators(new StringMap<Validator>());
        conf_.getValidators().put("rate_val", new MyValidator());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        nav_.setSession(conf_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        initSession(nav_);
        assertEq("page1.html", nav_.getCurrentUrl());

    }

    @Test
    public void processNav18Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_one\"><body><form action=\"DELETE\" name=\"myform\" c:command=\"go\"><c:select className='code.formathtml.classes.EnumNumber' id=\"combo\" default=\"\" name=\"chosenNumber\" varValue=\"chosenNumber\" map=\"map\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page2.html", htmlTwo_);
        BeanOne bean_ = new BeanOne();
        bean_.setScope("request");
        Configuration conf_ = contextElSec();

        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page2.html");
        conf_.setValidators(new StringMap<Validator>());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        conf_.setNavigation(new StringMap<StringMap<String>>());
        Navigation nav_ = newNavigation(conf_);
        ((BeanNatLgNames)nav_.getSession().getStandards()).setDataBase(new Composite());
        nav_.setLanguage(locale_);
        nav_.setSession(conf_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page2.html");
        initSession(nav_);
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
        Configuration conf_ = contextElSec();

        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page2.html");
        conf_.setValidators(new StringMap<Validator>());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("change", "page1.html");
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        ((BeanNatLgNames)nav_.getSession().getStandards()).setDataBase(new Composite());
        nav_.setLanguage(locale_);
        nav_.setSession(conf_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        nav_.getSession().getRenderFiles().add("page2.html");
        initSession(nav_);
        assertEq("page2.html", nav_.getCurrentUrl());
        nav_.getHtmlPage().setUrl(0);
        nav_.processRendFormRequest();
        setupBeansAfter(conf_);
        nav_.rendRefresh();
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body>HEAD<a c:command=\"$bean_one.goToNullPage\" href=\"\" n-a=\"0\"/></body></html>", nav_.getHtmlText());
        assertSame(conf_.getBeans().getVal("bean_one").getForms(), conf_.getBeans().getVal("bean_two").getForms());
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
        Configuration conf_ = contextElSec();

        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page2.html");
        conf_.setValidators(new StringMap<Validator>());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("change", "page1.html");
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        ((BeanNatLgNames)nav_.getSession().getStandards()).setDataBase(null);
        nav_.setLanguage(locale_);
        nav_.setSession(conf_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        nav_.getSession().getRenderFiles().add("page2.html");
        initSession(nav_);
        assertEq("page2.html", nav_.getCurrentUrl());
        nav_.getHtmlPage().setUrl(0);
        nav_.processRendFormRequest();
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body>HEAD<a c:command=\"$bean_one.goToNullPage\" href=\"\" n-a=\"0\"/></body></html>", nav_.getHtmlText());
        assertSame(conf_.getBeans().getVal("bean_one").getForms(), conf_.getBeans().getVal("bean_two").getForms());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());
//        new StdStruct()

    }
    private static void setupBeansAfter(Configuration _conf) {
        cleanBeans(_conf);
        for (EntryCust<String, Struct> e: _conf.getBuiltBeans().entryList()) {
            _conf.getBeans().put(e.getKey(), (Bean) ((BeanStruct) e.getValue()).getInstance());
        }
    }

    private static void cleanBeans(Configuration _conf) {
        _conf.getBeans().clear();
    }

    private static void initSession(Navigation _nav) {
        StringMap<BeanInfo> map_ = new StringMap<BeanInfo>();
        for (EntryCust<String, Bean> e: _nav.getSession().getBeans().entryList()) {
            BeanInfo i_ = new BeanInfo();
            i_.setClassName(e.getValue().getClassName());
            i_.setScope(e.getValue().getScope());
            map_.addEntry(e.getKey(),i_);
        }
        _nav.getSession().setBeansInfos(map_);
        _nav.setLanguages(new StringList(_nav.getLanguage()));
        _nav.getSession().getContext().setAnalyzing();
        _nav.getSession().getAnalyzing().setProcessKeyWord(new AdvancedProcessKeyWord(_nav.getSession()));
        _nav.getSession().getAnalyzing().setHiddenTypes(new AdvancedHiddenTypes(_nav.getSession()));
        _nav.getSession().getAnalyzing().setCurrentGlobalBlock(new AdvancedCurrentGlobalBlock(_nav.getSession()));
        _nav.getSession().getAnalyzing().setCurrentConstraints(new AdvancedCurrentConstraints());
        _nav.getSession().getAnalyzing().setAnnotationAnalysis(new AdvancedAnnotationAnalysis());
        _nav.initInstancesPattern();
        _nav.setupRenders();
        tryInitStaticlyTypes(_nav.getSession());
        _nav.initializeRendSession();
    }

    private Configuration contextElSec() {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        ContextEl cont_ = getContextEl();
        conf_.setContext(cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        cont_.setExecutingInstance(conf_);
        return conf_;
    }

    private static RendDocumentBlock buildRendWithOneNativeBean(String html_, BeanOne bean_, Configuration context_) {
        setLocale("en", context_);
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        context_.getBeans().put("bean_one", bean_);
        addBeanInfo(context_,"bean_one",new BeanStruct(bean_));
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(context_, "c:", doc_, html_);
        context_.getContext().setAnalyzing();
        context_.getAnalyzing().setProcessKeyWord(new AdvancedProcessKeyWord(context_));
        context_.getAnalyzing().setHiddenTypes(new AdvancedHiddenTypes(context_));
        context_.getAnalyzing().setCurrentGlobalBlock(new AdvancedCurrentGlobalBlock(context_));
        context_.getAnalyzing().setCurrentConstraints(new AdvancedCurrentConstraints());
        context_.getAnalyzing().setAnnotationAnalysis(new AdvancedAnnotationAnalysis());
        rendDocumentBlock_.buildFctInstructions(context_);
        context_.setDocument(doc_);
        return rendDocumentBlock_;
    }

    private static RendDocumentBlock buildRendWithTwoNativeBean(String html_, String htmlTwo_, BeanOne bean_, BeanTwo beanTwo_, Configuration conf_) {
        setLocale("en", conf_);
        addBeanInfo(conf_,"bean_one", new BeanStruct(bean_));
        addBeanInfo(conf_,"bean_two", new BeanStruct(beanTwo_));
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        Document docSec_ = DocumentBuilder.parseSaxNotNullRowCol(htmlTwo_).getDocument();
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        RendDocumentBlock rendDocumentBlockSec_ = RendBlock.newRendDocumentBlock(conf_, "c:", docSec_, htmlTwo_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getRenders().put("page2.html",rendDocumentBlockSec_);
        conf_.getContext().setAnalyzing();
        conf_.getAnalyzing().setProcessKeyWord(new AdvancedProcessKeyWord(conf_));
        conf_.getAnalyzing().setHiddenTypes(new AdvancedHiddenTypes(conf_));
        conf_.getAnalyzing().setCurrentGlobalBlock(new AdvancedCurrentGlobalBlock(conf_));
        conf_.getAnalyzing().setCurrentConstraints(new AdvancedCurrentConstraints());
        conf_.getAnalyzing().setAnnotationAnalysis(new AdvancedAnnotationAnalysis());
        rendDocumentBlock_.buildFctInstructions(conf_);
        rendDocumentBlockSec_.buildFctInstructions(conf_);
        conf_.setDocument(doc_);
        return rendDocumentBlock_;
    }

    private static ContextEl buildStdOne(Options _opt) {
        BeanLgNames lgNames_ = new CustBeanLgNames();
        basicStandards(lgNames_);
        return InitializationLgNames.build(CustList.INDEX_NOT_FOUND_ELT,lgNames_, _opt);
    }
    private static ContextEl buildStdTwo(Options _opt) {
        BeanLgNames lgNames_ = new CustLgNames();
        basicStandards(lgNames_);
        lgNames_.setAliasMath("java.lang.$math");
        return InitializationLgNames.build(CustList.INDEX_NOT_FOUND_ELT,lgNames_, _opt);
    }

    private static void addBeanInfo(Configuration _conf, String _id, Struct _str) {
        BeanInfo b_ = new BeanInfo();
        b_.setClassName(_str.getClassName(_conf));
        _conf.getBeansInfos().addEntry(_id,b_);
        _conf.getBuiltBeans().addEntry(_id,_str);
    }
    private static void basicStandards(BeanLgNames _lgNames) {
        _lgNames.setDefaultPkg("java.lang");
        _lgNames.setAliasObject("java.lang.Object");
        _lgNames.setAliasVoid("$void");
        _lgNames.setAliasCharSequence("java.lang.CharSequence");
        _lgNames.setAliasCompareTo("compareTo");
        _lgNames.setAliasCompare("compare");
        _lgNames.setAliasEquals("equals");
        _lgNames.setAliasToStringMethod("toString");
        _lgNames.setAliasValueOfMethod("valueOf");
        _lgNames.setAliasMaxValueField("MAX_VALUE");
        _lgNames.setAliasMinValueField("MIN_VALUE");
        _lgNames.setAliasIteratorType("java.lang.$iterator");
        _lgNames.setAliasIterator("iterator");
        _lgNames.setAliasIterable("java.lang.$iterable");
        _lgNames.setAliasEnumParam("java.lang.$Enum");
        _lgNames.setAliasEnumType("java.lang.$en");
        _lgNames.setAliasEnums("java.lang.$enums");
        _lgNames.setAliasReplacement("code.util.Replacement");
        _lgNames.setAliasStore("code.expressionlanguage.exceptions.DynamicArrayStoreException");
        _lgNames.setAliasNullPe("code.util.exceptions.NullObjectException");
        _lgNames.setAliasBadEncode("java.lang.$enc");
        _lgNames.setAliasBadIndex("code.expressionlanguage.exceptions.BadIndexException");
        _lgNames.setAliasIllegalArg("code.expressionlanguage.exceptions.IllegalArgument");
        _lgNames.setAliasBadSize("code.expressionlanguage.exceptions.NegativeSizeException");
        _lgNames.setAliasError("java.lang.Exception");
        _lgNames.setAliasGetMessage("getMessage");
        _lgNames.setAliasCastType("code.expressionlanguage.exceptions.DynamicCastClassException");
        _lgNames.setAliasDivisionZero("code.expressionlanguage.exceptions.DivideZeroException");
        _lgNames.setAliasMath("java.lang.Math");
        _lgNames.setAliasAbs("abs");
        _lgNames.setAliasMod("mod");
        _lgNames.setAliasQuot("quot");
        _lgNames.setAliasSof("code.expressionlanguage.exceptions.StackOverFlow");
        _lgNames.setAliasNbFormat("java.lang.badFormat");
        _lgNames.setAliasPrimBoolean("$boolean");
        _lgNames.setAliasPrimByte("$byte");
        _lgNames.setAliasPrimShort("$short");
        _lgNames.setAliasPrimChar("$char");
        _lgNames.setAliasPrimInteger("$int");
        _lgNames.setAliasPrimLong("$long");
        _lgNames.setAliasPrimFloat("$float");
        _lgNames.setAliasPrimDouble("$double");
        _lgNames.setAliasBoolean("java.lang.Boolean");
        _lgNames.setAliasByte("java.lang.Byte");
        _lgNames.setAliasShort("java.lang.Short");
        _lgNames.setAliasCharacter("java.lang.Character");
        _lgNames.setAliasInteger("java.lang.Integer");
        _lgNames.setAliasLong("java.lang.Long");
        _lgNames.setAliasFloat("java.lang.Float");
        _lgNames.setAliasDouble("java.lang.Double");
        _lgNames.setAliasNumber("java.lang.Number");
        _lgNames.setAliasParseBoolean("parseBoolean");
        _lgNames.setAliasParseByte("parseByte");
        _lgNames.setAliasParseShort("parseShort");
        _lgNames.setAliasParseInt("parseInt");
        _lgNames.setAliasParseLong("parseLong");
        _lgNames.setAliasParseFloat("parseFloat");
        _lgNames.setAliasParseDouble("parseDouble");
        _lgNames.setAliasParseByteOrNull("parseByteOrNull");
        _lgNames.setAliasParseShortOrNull("parseShortOrNull");
        _lgNames.setAliasParseIntOrNull("parseIntOrNull");
        _lgNames.setAliasParseLongOrNull("parseLongOrNull");
        _lgNames.setAliasParseFloatOrNull("parseFloatOrNull");
        _lgNames.setAliasParseDoubleOrNull("parseDoubleOrNull");
        _lgNames.setAliasBooleanValue("booleanValue");
        _lgNames.setAliasByteValue("byteValue");
        _lgNames.setAliasShortValue("shortValue");
        _lgNames.setAliasCharValue("charValue");
        _lgNames.setAliasIntValue("intValue");
        _lgNames.setAliasLongValue("longValue");
        _lgNames.setAliasFloatValue("floatValue");
        _lgNames.setAliasDoubleValue("doubleValue");
        _lgNames.setAliasDigit("digit");
        _lgNames.setAliasIsDigit("isDigit");
        _lgNames.setAliasIsLetter("isLetter");
        _lgNames.setAliasIsLetterOrDigit("isLetterOrDigit");
        _lgNames.setAliasIsWordChar("isWordChar");
        _lgNames.setAliasIsLowerCase("isLowerCase");
        _lgNames.setAliasIsUpperCase("isUpperCase");
        _lgNames.setAliasIsWhitespace("isWhitespace");
        _lgNames.setAliasIsSpace("isSpace");
        _lgNames.setAliasIsInfinite("isInfinite");
        _lgNames.setAliasIsNan("isNan");
        _lgNames.setAliasForDigit("forDigit");
        _lgNames.setAliasGetDirectionality("getDirectionality");
        _lgNames.setAliasGetType("getType");
        _lgNames.setAliasGetCharType("getType");
        _lgNames.setAliasString("java.lang.String");
        _lgNames.setAliasLength("length");
        _lgNames.setAliasCharAt("charAt");
        _lgNames.setAliasToCharArray("toCharArray");
        _lgNames.setAliasSplit("split");
        _lgNames.setAliasSplitChars("splitChars");
        _lgNames.setAliasSplitStrings("splitStrings");
        _lgNames.setAliasReplace("replace");
        _lgNames.setAliasReplaceMultiple("replaceMultiple");
        _lgNames.setAliasEqualsIgnoreCase("equalsIgnoreCase");
        _lgNames.setAliasCompareToIgnoreCase("compareToIgnoreCase");
        _lgNames.setAliasContains("contains");
        _lgNames.setAliasEndsWith("endsWith");
        _lgNames.setAliasStartsWith("startsWith");
        _lgNames.setAliasIndexOf("indexOf");
        _lgNames.setAliasFormat("format");
        _lgNames.setAliasGetBytes("getBytes");
        _lgNames.setAliasIsEmpty("isEmpty");
        _lgNames.setAliasLastIndexOf("lastIndexOf");
        _lgNames.setAliasRegionMatches("regionMatches");
        _lgNames.setAliasSubstring("substring");
        _lgNames.setAliasSubSequence("subSequence");
        _lgNames.setAliasToLowerCase("toLowerCase");
        _lgNames.setAliasToUpperCase("toUpperCase");
        _lgNames.setAliasTrim("trim");
        _lgNames.setAliasStringBuilder("java.lang.StringBuilder");
        _lgNames.setAliasAppend("append");
        _lgNames.setAliasCapacity("capacity");
        _lgNames.setAliasClear("clear");
        _lgNames.setAliasDelete("delete");
        _lgNames.setAliasDeleteCharAt("deleteCharAt");
        _lgNames.setAliasEnsureCapacity("ensureCapacity");
        _lgNames.setAliasInsert("insert");
        _lgNames.setAliasReverse("reverse");
        _lgNames.setAliasSetCharAt("setCharAt");
        _lgNames.setAliasSetLength("setLength");
        _lgNames.setAliasSame("same");
        _lgNames.setAliasTrimToSize("trimToSize");
        _lgNames.setAliasErrorInitClass("java.lang.$defErrorClass");
        _lgNames.setAliasClone("clone");
        _lgNames.setAliasReadResources("readContent");
        _lgNames.setAliasReadResourcesNames("readNames");
        _lgNames.setAliasResources("java.lang.Resources");
        _lgNames.setAliasEnumValues("values");
        _lgNames.setAliasInvokeTarget("java.lang.$invokeTaget");
        _lgNames.setAliasGetCause("getCause");
        _lgNames.setAliasClassNotFoundError("java.lang.$classNotFound");
        _lgNames.setAliasGetVariableOwner("getVariableOwner");
        _lgNames.setAliasGetGenericVariableOwner("getGenericVariableOwner");
        _lgNames.setAliasGetString("getString");
        _lgNames.setAliasClassType("java.lang.$Class");
        _lgNames.setAliasStackTraceElement("java.lang.$stack");
        _lgNames.setAliasCurrentStack("current");
        _lgNames.setAliasCurrentFullStack("currentFull");
        _lgNames.setAliasFct("java.lang.$Fct");
        _lgNames.setAliasCall("call");
        _lgNames.setAliasAnnotationType("java.lang.$Annotation");
        _lgNames.setAliasAnnotated("java.lang.$Annotated");
        _lgNames.setAliasGetAnnotations("getAnnotations");
        _lgNames.setAliasGetDefaultValue("getDefaultValue");
        _lgNames.setAliasGetAnnotationsParameters("getAnnotationsParameters");
        _lgNames.setAliasGetDeclaredConstructors("getDeclaredConstructors");
        _lgNames.setAliasGetDeclaredFields("getDeclaredFields");
        _lgNames.setAliasGetDeclaredMethods("getDeclaredMethods");
        _lgNames.setAliasGetDeclaredStaticMethods("getDeclaredStaticMethods");
        _lgNames.setAliasMakeGeneric("makeGeneric");
        _lgNames.setAliasGetAllClasses("getAllClasses");
        _lgNames.setAliasGetOperators("getOperators");
        _lgNames.setAliasConstructor("java.lang.$Constructor");
        _lgNames.setAliasField("java.lang.$Field");
        _lgNames.setAliasMethod("java.lang.$Method");
        _lgNames.setAliasInvoke("invoke");
        _lgNames.setAliasInvokeDirect("invokeDirect");
        _lgNames.setAliasNewInstance("newInstance");
        _lgNames.setAliasIsAbstract("isAbstract");
        _lgNames.setAliasGetFileName("getFileName");
        _lgNames.setAliasGetName("getName");
        _lgNames.setAliasGetPrettyName("getPrettyName");
        _lgNames.setAliasGetPrettySingleName("getPrettySingleName");
        _lgNames.setAliasGetField("get");
        _lgNames.setAliasSetField("set");
        _lgNames.setAliasGetClass("getClass");
        _lgNames.setAliasGetEnclosingType("getEnclosingType");
        _lgNames.setAliasGetDeclaredClasses("getDeclaredClasses");
        _lgNames.setAliasForName("forName");
        _lgNames.setAliasObjectsUtil("java.lang.$ObjectsUtil");
        _lgNames.setAliasStringUtil("java.lang.$StringUtil");
        _lgNames.setAliasSameRef("eq");
        _lgNames.setAliasGetParent("getParent");
        _lgNames.setAliasSetParent("setParent");
        _lgNames.setAliasNext("next");
        _lgNames.setAliasHasNext("hasNext");
        _lgNames.setAliasIterableTable("java.lang.$iterableTable");
        _lgNames.setAliasIteratorTable("iteratorTable");
        _lgNames.setAliasIteratorTableType("java.lang.$iteratorTable");
        _lgNames.setAliasHasNextPair("hasNextPair");
        _lgNames.setAliasNextPair("nextPair");
        _lgNames.setAliasPairType("java.lang.$pair");
        _lgNames.setAliasGetFirst("getFirst");
        _lgNames.setAliasGetSecond("getSecond");
        _lgNames.setAliasName("name");
        _lgNames.setAliasOrdinal("ordinal");
        _lgNames.setAliasEnumName("$name");
        _lgNames.setAliasEnumOrdinal("$ordinal");
        _lgNames.setAliasEnumPredValueOf("valueOf");
        _lgNames.setAliasIterableVar("T");
        _lgNames.setAliasIteratorTypeVar("T");
        _lgNames.setAliasIterableTableVarFirst("T");
        _lgNames.setAliasIterableTableVarSecond("U");
        _lgNames.setAliasIteratorTableTypeVarFirst("T");
        _lgNames.setAliasIteratorTableTypeVarSecond("U");
        _lgNames.setAliasPairTypeVarFirst("T");
        _lgNames.setAliasPairTypeVarSecond("U");
        _lgNames.setAliasEnumParamVar("T");
        _lgNames.setAliasGetOldString("getOldString");
        _lgNames.setAliasGetNewString("getNewString");
        _lgNames.setAliasGetSuperClass("getSuperClass");
        _lgNames.setAliasGetGenericSuperClass("getGenericSuperClass");
        _lgNames.setAliasGetInterfaces("getInterfaces");
        _lgNames.setAliasGetGenericInterfaces("getGenericInterfaces");
        _lgNames.setAliasGetLowerBounds("getLowerBounds");
        _lgNames.setAliasGetUpperBounds("getUpperBounds");
        _lgNames.setAliasGetComponentType("getComponentType");
        _lgNames.setAliasMakeArray("makeArray");
        _lgNames.setAliasGetParameterTypes("getParameterTypes");
        _lgNames.setAliasGetTypeParameters("getTypeParameters");
        _lgNames.setAliasGetParameterNames("getGenericParameterTypes");
        _lgNames.setAliasGetGenericReturnType("getGenericReturnType");
        _lgNames.setAliasGetReturnType("getReturnType");
        _lgNames.setAliasGetActualTypeArguments("getActualTypeArguments");
        _lgNames.setAliasGetFieldType("getType");
        _lgNames.setAliasGetGenericType("getGenericType");
        _lgNames.setAliasIsFinal("isFinal");
        _lgNames.setAliasIsTypeVariable("isTypeVariable");
        _lgNames.setAliasIsVariable("isVariable");
        _lgNames.setAliasIsStatic("isStatic");
        _lgNames.setAliasIsStaticCall("isStaticCall");
        _lgNames.setAliasIsInstanceMethod("isInstanceMethod");
        _lgNames.setAliasIsVarargs("isVarargs");
        _lgNames.setAliasIsNormal("isNormal");
        _lgNames.setAliasIsPublic("isPublic");
        _lgNames.setAliasIsProtected("isProtected");
        _lgNames.setAliasIsPackage("isPackage");
        _lgNames.setAliasIsPrivate("isPrivate");
        _lgNames.setAliasIsClass("isClass");
        _lgNames.setAliasIsWildCard("isWildCard");
        _lgNames.setAliasIsInterface("isInterface");
        _lgNames.setAliasIsEnum("isEnum");
        _lgNames.setAliasIsPrimitive("isPrimitive");
        _lgNames.setAliasIsArray("isArray");
        _lgNames.setAliasIsAnnotation("isAnnotation");
        _lgNames.setAliasMakeWildCard("makeWildCard");
        _lgNames.setAliasIsInstance("isInstance");
        _lgNames.setAliasIsAssignableFrom("isAssignableFrom");
        _lgNames.setAliasInit("init");
        _lgNames.setAliasDefaultInstance("defaultInstance");
        _lgNames.setAliasEnumValueOf("enumValueOf");
        _lgNames.setAliasGetEnumConstants("getEnumConstants");
        _lgNames.setAliasGetGenericBounds("getGenericBounds");
        _lgNames.setAliasGetBounds("getBounds");
        _lgNames.setAliasArrayNewInstance("newArrayInstance");
        _lgNames.setAliasArrayGet("get");
        _lgNames.setAliasArraySet("set");
        _lgNames.setAliasArrayGetLength("getLength");
        _lgNames.setAliasGetDeclaringClass("getDeclaringClass");
        _lgNames.setAliasBinQuot("binQuot");
        _lgNames.setAliasBinMod("binMod");
        _lgNames.setAliasPlus("plus");
        _lgNames.setAliasMinus("minus");
        _lgNames.setAliasMult("mult");
        _lgNames.setAliasAnd("and");
        _lgNames.setAliasOr("or");
        _lgNames.setAliasXor("xor");
        _lgNames.setAliasNegBin("negBin");
        _lgNames.setAliasNeg("neg");
        _lgNames.setAliasLt("lt");
        _lgNames.setAliasGt("gt");
        _lgNames.setAliasLe("le");
        _lgNames.setAliasGe("ge");
        _lgNames.setAliasShiftLeft("shiftLeft");
        _lgNames.setAliasShiftRight("shiftRight");
        _lgNames.setAliasBitShiftLeft("bitShiftLeft");
        _lgNames.setAliasBitShiftRight("bitShiftRight");
        _lgNames.setAliasRotateLeft("rotateLeft");
        _lgNames.setAliasRotateRight("rotateRight");
        _lgNames.setAliasRandom("random");
        _lgNames.getDisplayedStrings().setFalseString("false");
        _lgNames.getDisplayedStrings().setTrueString("true");
        _lgNames.getDisplayedStrings().setNullString("");
        _lgNames.getDisplayedStrings().setNotNullCoverString("not null");
        _lgNames.getDisplayedStrings().setNullCoverString("null");
        _lgNames.getDisplayedStrings().setStaticCallString("staticCall");
        _lgNames.getDisplayedStrings().setStaticString("static");
    }

}
