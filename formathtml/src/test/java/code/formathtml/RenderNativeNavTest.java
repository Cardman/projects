package code.formathtml;

import code.bean.Bean;
import code.bean.BeanInfo;

import code.bean.validator.Validator;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.structs.Struct;
import code.formathtml.classes.*;
import code.formathtml.structs.BeanStruct;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.NodeContainer;
import code.formathtml.util.NodeInformations;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.*;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.*;

public final class RenderNativeNavTest extends CommonRender {

    @Test
    public void process1Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<a c:command=\"$goToNullPage\" href=\"\"/></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form action=\"DELETE\" name=\"myform\" c:command=\"$go\"><c:select className=\"code.formathtml.classes.EnumNumbers\" id=\"combo\" default=\"\" name=\"chosenNumbers\" varValue=\"chosenNumbers\" map=\"translations\" multiple=\"multiple\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        BeanOne bean_ = new BeanOne();
        bean_.setScope("request");
        BeanFive beanTwo_ = new BeanFive();
        beanTwo_.setScope("request");
        Configuration conf_ = contextElSec();
        conf_.setSepPrefix("c");
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
        nav_.setDataBase(new Composite());
        nav_.setLanguage(locale_);
        nav_.setSession(conf_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        nav_.getSession().getRenderFiles().add("page2.html");
        initSession(nav_);
        assertEq("page2.html", nav_.getCurrentUrl());
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
        values_.add("TWO");
        values_.add("THREE");
        ni_.setValue(values_);
        nav_.getHtmlPage().setUrl(0);
        nav_.processRendFormRequest();
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body>HEAD<a c:command=\"$bean_one.goToNullPage\" href=\"\" n-a=\"0\"/></body></html>", nav_.getHtmlText());
        bean_ = (BeanOne) conf_.getBeans().getVal("bean_one");
        StringMapObject map_ = bean_.getForms();
        assertEq(3, map_.size());
        StringList stLi_ = (StringList) map_.getVal("selectedStrings");
        assertEq(2, stLi_.size());
        assertEq("ONE", stLi_.first());
        assertEq("FOUR", stLi_.last());
        EnumNumbers l_ = (EnumNumbers) map_.getVal("chosenNumbers");
        assertEq(2, l_.size());
        assertEq(EnumNumber.TWO, l_.first());
        assertEq(EnumNumber.THREE, l_.last());
        assertTrue(map_.contains("chosenNumbersNull"));
        assertNull(map_.getVal("chosenNumbersNull"));
        assertSame(conf_.getBeans().getVal("bean_one").getForms(), conf_.getBeans().getVal("bean_two").getForms());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());
        
    }

    @Test
    public void process2Test() {
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
        addBeanInfo(conf_,"bean_one", new BeanStruct(bean_));
        addBeanInfo(conf_,"bean_two", new BeanStruct(beanTwo_));
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.getAdvStandards().preInitBeans(conf_);


        conf_.setHtml(html_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        Document docSec_ = DocumentBuilder.parseSax(htmlTwo_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        RendDocumentBlock rendDocumentBlockSec_ = RendBlock.newRendDocumentBlock(conf_, "c:", docSec_, htmlTwo_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getRenders().put("page2.html",rendDocumentBlockSec_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        rendDocumentBlockSec_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body><a href=\"\" c:command=\"go\" n-a=\"0\">Test {0}2</a></body></html>", RendBlock.getRes(rendDocumentBlock_, conf_));
        assertNull(conf_.getException());
        assertEq(1, beanTwo_.getForms().size());
        assertEq("key", beanTwo_.getForms().getKeys().first());
        assertEq("sample_value", (String)beanTwo_.getForms().values().first());
    }

    @Test
    public void process3Test() {
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
        addBeanInfo(conf_,"bean_one", new BeanStruct(bean_));
        addBeanInfo(conf_,"bean_two", new BeanStruct(beanTwo_));
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.getAdvStandards().preInitBeans(conf_);

        Document doc_ = DocumentBuilder.parseSax(html_);
        Document docSec_ = DocumentBuilder.parseSax(htmlTwo_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        RendDocumentBlock rendDocumentBlockSec_ = RendBlock.newRendDocumentBlock(conf_, "c:", docSec_, htmlTwo_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getRenders().put("page2.html",rendDocumentBlockSec_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        setLocale(locale_, conf_);
        conf_.getAnalyzingDoc().setFiles(files_);
        rendDocumentBlock_.buildFctInstructions(conf_);
        rendDocumentBlockSec_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        String render_ = RendBlock.getRes(rendDocumentBlock_, conf_);
        assertEq("<html><body><a href=\"\" c:command=\"go\" n-a=\"0\">Test {0}2</a>Description <a c:command=\"$bean_two.go\" href=\"\" n-a=\"1\">two</a></body></html>", render_);
        assertEq(1, beanTwo_.getForms().size());
        assertEq("key", beanTwo_.getForms().getKeys().first());
        assertEq("sample_value", (String)beanTwo_.getForms().values().first());
    }

    @Test
    public void process4Test() {
        String locale_ = "LOCALE";
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
        conf_.setSepPrefix("c");
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
    public void process5Test() {
        String locale_ = "LOCALE";
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
        conf_.setSepPrefix("c");
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
    public void process6Test() {
        String locale_ = "LOCALE";
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
        conf_.setSepPrefix("c");
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
    public void process7Test() {
        String locale_ = "LOCALE";
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
        conf_.setSepPrefix("c");
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
    public void process8Test() {
        String locale_ = "LOCALE";
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
        conf_.setSepPrefix("c");
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page2.html");
        conf_.setValidators(new StringMap<Validator>());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        conf_.setNavigation(new StringMap<StringMap<String>>());
        Navigation nav_ = newNavigation(conf_);
        nav_.setDataBase(new Composite());
        nav_.setLanguage(locale_);
        nav_.setSession(conf_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page2.html");
        initSession(nav_);
        assertEq("page2.html", nav_.getCurrentUrl());
        assertEq("<html><body><form action=\"\" name=\"myform\" c:command=\"go\" n-f=\"0\"><select id=\"combo\" name=\"bean_one.selectedString\" n-i=\"0\"><option value=\"ONE\" selected=\"selected\">1</option><option value=\"TWO\">2</option></select></form></body></html>", nav_.getHtmlText());

    }

    @Test
    public void process9Test() {
        String locale_ = "LOCALE";
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
        conf_.setSepPrefix("c");
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page2.html");
        conf_.setValidators(new StringMap<Validator>());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        conf_.setNavigation(new StringMap<StringMap<String>>());
        Navigation nav_ = newNavigation(conf_);
        nav_.setDataBase(new Composite());
        nav_.setLanguage(locale_);
        nav_.setSession(conf_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page2.html");
        initSession(nav_);
        assertEq("page2.html", nav_.getCurrentUrl());
        assertEq("<html><body>returned value</body></html>", nav_.getHtmlText());

    }

    @Test
    public void process10Test() {
        String locale_ = "LOCALE";
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
        conf_.setSepPrefix("c");
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page2.html");
        conf_.setValidators(new StringMap<Validator>());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        conf_.setNavigation(new StringMap<StringMap<String>>());
        Navigation nav_ = newNavigation(conf_);
        nav_.setDataBase(new Composite());
        nav_.setLanguage(locale_);
        nav_.setSession(conf_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page2.html");
        initSession(nav_);
        assertEq("page2.html", nav_.getCurrentUrl());
        assertEq("<html><body>returned value</body></html>", nav_.getHtmlText());

    }

    @Test
    public void process11Test() {
        String locale_ = "LOCALE";
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
        conf_.setSepPrefix("c");
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page2.html");
        conf_.setValidators(new StringMap<Validator>());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        conf_.setNavigation(new StringMap<StringMap<String>>());
        Navigation nav_ = newNavigation(conf_);
        nav_.setDataBase(new Composite());
        nav_.setLanguage(locale_);
        nav_.setSession(conf_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page2.html");
        initSession(nav_);
        assertEq("page2.html", nav_.getCurrentUrl());
        assertEq("<html><body>5</body></html>", nav_.getHtmlText());

    }

    @Test
    public void process12Test() {
        String locale_ = "LOCALE";
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
        conf_.setSepPrefix("c");
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
    public void process13Test() {
        String locale_ = "LOCALE";
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
        conf_.setSepPrefix("c");
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
    public void process14Test() {
        String locale_ = "LOCALE";
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
        conf_.setSepPrefix("c");
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
    public void process15Test() {
        String locale_ = "LOCALE";
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
        conf_.setSepPrefix("c");
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
    public void process16Test() {
        String locale_ = "LOCALE";
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
        conf_.setSepPrefix("c");
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
    public void process17Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body>{$new $int[]\\{\\}}{$this}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", htmlTwo_);
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setScope("session");
        Configuration conf_ = contextElSec();
        conf_.setSepPrefix("c");
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
            map_.add(e.getKey(),i_);
        }
        _nav.getSession().setBeansInfos(map_);
        _nav.setLanguages(new StringList(_nav.getLanguage()));
        _nav.getSession().getContext().setAnalyzing(new AnalyzedPageEl());
        _nav.initInstancesPattern();
        _nav.setupRenders();
        _nav.initializeRendSession();
    }

}
