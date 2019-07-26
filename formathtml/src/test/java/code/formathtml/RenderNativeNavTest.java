package code.formathtml;

import code.bean.Bean;
import code.bean.BeanInfo;
import code.bean.translator.Translator;
import code.bean.validator.Validator;
import code.expressionlanguage.structs.Struct;
import code.formathtml.classes.*;
import code.formathtml.structs.BeanStruct;
import code.formathtml.util.NodeContainer;
import code.formathtml.util.NodeInformations;
import code.util.*;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

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
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("change", "page1.html");
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
        assertEq(0,nav_.getTooltips().size());
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
        _nav.initializeRendSession();
    }
}
