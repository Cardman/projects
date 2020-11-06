package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.DefaultConstantsCalculator;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ValidatorStandard;
import code.formathtml.structs.BeanInfo;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.util.*;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.*;

public final class RenderInitNavTest extends CommonRender {
    @Test
    public void process1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><a c:command=\"page2.html\"/></body></html>";
        String htmlTwo_ = "<html><body>Next</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public String click($int p){");
        file_.append("  $return $bool(p>2,\"val1\",\"val2\");");
        file_.append(" }");
        file_.append("}");
        files_.put("my_file",file_.toString());
        file_ = new StringBuilder();
        file_.append("my_file");
        files_.put("conf",file_.toString());
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c");
        AnalyzedTestConfigurationBis a_ = buildNav(conf_);
        setFirst(a_,"page1.html");
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        a_.getDual().setFilesConfName("conf");
        a_.getDual().getRenderFiles().add("page1.html");
        a_.getDual().getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("pkg.BeanOne");
        conf_.getBeansInfos().addEntry("bean_one",i_);
        conf_.init(a_.getDual());
        Navigation n_ = new Navigation();
        n_.setSession(conf_);
        n_.setFiles(files_);
        assertTrue(setupRendClassesInitStdMess(a_, n_));
        n_.initializeRendSession(a_.getContext(), a_.getAdvStandards());
        assertEq("<html><body><a c:command=\"page2.html\" href=\"\" n-a=\"0\"/></body></html>",n_.getHtmlText());
    }
    @Test
    public void process2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><a c:command=\"page2.html\"/></body></html>";
        String htmlTwo_ = "<html><body>Next</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] click($int p){");
        file_.append("  $return p;");
        file_.append(" }");
        file_.append("}");
        files_.put("my_file",file_.toString());
        file_ = new StringBuilder();
        file_.append("my_file");
        files_.put("conf",file_.toString());
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c");
        AnalyzedTestConfigurationBis a_ = buildNav(conf_);
        setFirst(a_,"page1.html");
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        a_.getDual().setFilesConfName("conf");
        a_.getDual().getRenderFiles().add("page1.html");
        a_.getDual().getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("pkg.BeanOne");
        conf_.getBeansInfos().addEntry("bean_one",i_);
        conf_.init(a_.getDual());
        Navigation n_ = new Navigation();
        n_.setSession(conf_);
        n_.setFiles(files_);
        assertTrue(!setupRendClassesInitStdMess(a_, n_));
    }

    @Test
    public void process3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><a c:command=\"page2.html\"/>{field}</body></html>";
        String htmlTwo_ = "<html><body>Next</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int field;");
        file_.append("}");
        files_.put("my_file",file_.toString());
        file_ = new StringBuilder();
        file_.append("my_file2");
        files_.put("conf",file_.toString());
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c");
        AnalyzedTestConfigurationBis a_ = buildNav(conf_);
        setFirst(a_,"page1.html");
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        a_.getDual().setFilesConfName("conf");
        a_.getDual().getRenderFiles().add("page1.html");
        a_.getDual().getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("pkg.BeanOne");
        conf_.getBeansInfos().addEntry("bean_one",i_);
        conf_.init(a_.getDual());
        Navigation n_ = new Navigation();
        n_.setSession(conf_);
        n_.setFiles(files_);
        assertTrue(!setupRendClassesInitStdMess(a_, n_));
    }
    @Test
    public void process4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><a c:command=\"page2.html\"/>{field}</body></html>";
        String htmlTwo_ = "<html><body>Next</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int field;");
        file_.append("}");
        files_.put("my_file",file_.toString());
        file_ = new StringBuilder();
        file_.append("my_file");
        files_.put("conf",file_.toString());
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c");
        AnalyzedTestConfigurationBis a_ = buildNav(conf_);
        setFirst(a_,"page1.html");
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        a_.getDual().setFilesConfName("conf2");
        a_.getDual().getRenderFiles().add("page1.html");
        a_.getDual().getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("pkg.BeanOne");
        conf_.getBeansInfos().addEntry("bean_one",i_);
        conf_.init(a_.getDual());
        Navigation n_ = new Navigation();
        n_.setDataBaseStruct(NullStruct.NULL_VALUE);
        n_.setSession(conf_);
        n_.setFiles(files_);
        assertTrue(!setupRendClassesInitStdMess(a_, n_));
    }
    @Test
    public void process5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><a c:command=\"page2.html\"/></body></html>";
        String htmlTwo_ = "<html><body>Next</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class [code.bean.Message;] pkg.MyVal:code.bean.Validator{");
        file_.append(" $public Message validate(Object n,Object o,Object b,Object[] f,String c,String fd){");
        file_.append("  $return $null;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public String click($int p){");
        file_.append("  $return $bool(p>2,\"val1\",\"val2\");");
        file_.append(" }");
        file_.append("}");
        files_.put("my_file",file_.toString());
        file_ = new StringBuilder();
        file_.append("my_file");
        files_.put("conf",file_.toString());


        BeanCustLgNames lgNames_ = new BeanCustLgNamesImpl();
        basicStandards(lgNames_);
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String field='firstUrl' value='page1.html'/>\n" +
                "\t<java.lang.String field='prefix' value='c'/>\n" +
                "\t<sm field='navigation'>\n" +
                "\t\t<java.lang.String key='' value='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String key='' value='res'/>\n" +
                "\t\t\t<java.lang.String value='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n field=\"context\">\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<java.lang.String field='messagesFolder' value='messages'/>\n" +
                "\t<java.lang.String field='filesConfName' value='conf'/>\n" +
                "\t<sm field='beans'>\n" +
                "\t\t<java.lang.String key='' value='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='pkg.BeanOne'/>\n" +
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
                "\t<sm field='lateValidators'>\n" +
                "\t\t<str key='' value='my_val'/>\n" +
                "\t\t<str value='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "</cfg>";
        Navigation n_ = new Navigation();
        DualAnalyzedContext page_ = loadConfiguration(lgNames_, xmlConf_, n_);
        n_.setFiles(files_);
        assertTrue(setupRendClassesInit(n_, lgNames_, page_));
        n_.initializeRendSession(page_.getContext().getContext(), page_.getStds());
        assertEq("<html><body><a c:command=\"page2.html\" href=\"\" n-a=\"0\"/></body></html>",n_.getHtmlText());
        assertEq(2,page_.getContext().getAddedFiles().size());
        assertEq(0,n_.getLanguages().size());
    }
    @Test
    public void process6Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><a c:command=\"page2.html\"/></body></html>";
        String htmlTwo_ = "<html><body>Next</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class [code.bean.Message;] pkg.MyVal:code.bean.Validator{");
        file_.append(" $public Message validate(Object n,Object o,Object b,Object[] f,String c,String fd){");
        file_.append("  $return $null;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public String click($int p){");
        file_.append("  $return $bool(p>2,\"val1\",\"val2\");");
        file_.append(" }");
        file_.append("}");
        files_.put("my_file",file_.toString());
        file_ = new StringBuilder();
        file_.append("my_file");
        files_.put("conf",file_.toString());
        BeanCustLgNames lgNames_ = new BeanCustLgNamesImpl();
        basicStandards(lgNames_);
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String field='firstUrl' value='page1.html'/>\n" +
                "\t<java.lang.String field='prefix' value='c'/>\n" +
                "\t<sm field='navigation'>\n" +
                "\t\t<java.lang.String key='' value='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String key='' value='res'/>\n" +
                "\t\t\t<java.lang.String value='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n field=\"context\">\n" +
                "\t\t<java.lang.Integer field='stackOverFlow' value='-1'/>\n" +
                "\t\t<o field='options'>\n" +
                "\t\t\t<v field='suffixVar' value='NONE'/>\n" +
                "\t\t\t<b field='initializeStaticClassFirst' value='true'/>\n" +
                "\t\t\t<i field='inex'/>\n" +
                "\t\t</o>\n" +
                "\t\t<i field='inex'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<java.lang.String field='messagesFolder' value='messages'/>\n" +
                "\t<java.lang.String field='filesConfName' value='conf'/>\n" +
                "\t<sm field='beans'>\n" +
                "\t\t<java.lang.String key='' value='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='pkg.BeanOne'/>\n" +
                "\t\t\t<i field='inex'/>\n" +
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
                "\t<sm field='lateValidators'>\n" +
                "\t\t<str key='' value='my_val'/>\n" +
                "\t\t<str value='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "\t<i field='inex'/>\n" +
                "</cfg>\n" +
                "\n";
        Navigation n_ = new Navigation();
        DualAnalyzedContext page_ = loadConfiguration(lgNames_, xmlConf_, n_);
        n_.setFiles(files_);
        assertTrue(setupRendClassesInit(n_, lgNames_, page_));
        n_.initializeRendSession(page_.getContext().getContext(), page_.getStds());
        assertEq("<html><body><a c:command=\"page2.html\" href=\"\" n-a=\"0\"/></body></html>",n_.getHtmlText());
    }
    @Test
    public void process7Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><a c:command=\"page2.html\"/></body></html>";
        String htmlTwo_ = "<html><body>Next</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class [code.bean.Message;] pkg.MyVal:code.bean.Validator{");
        file_.append(" $public Message validate(Object n,Object o,Object b,Object[] f,String c,String fd){");
        file_.append("  $return $null;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append(" $public $static $int v = BeanThree.v++;");
        file_.append("}");
        file_.append("$public $class pkg.BeanThree:code.bean.Bean{");
        file_.append(" $public $static $int v = BeanTwo.v++;");
        file_.append("}");
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public String click($int p){");
        file_.append("  $return $bool(p>2,\"val1\",\"val2\");");
        file_.append(" }");
        file_.append("}");
        files_.put("my_file",file_.toString());
        file_ = new StringBuilder();
        file_.append("my_file");
        files_.put("conf",file_.toString());
        BeanCustLgNames lgNames_ = new BeanCustLgNamesImpl();
        basicStandards(lgNames_);
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String field='firstUrl' value='page1.html'/>\n" +
                "\t<java.lang.String field='prefix' value='c'/>\n" +
                "\t<sm field='navigation'>\n" +
                "\t\t<java.lang.String key='' value='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String key='' value='res'/>\n" +
                "\t\t\t<java.lang.String value='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n field=\"context\">\n" +
                "\t\t<java.lang.Integer field='stackOverFlow' value='-1'/>\n" +
                "\t\t<o field='options'>\n" +
                "\t\t\t<v field='suffixVar' value='NONE'/>\n" +
                "\t\t\t<b field='initializeStaticClassFirst' value='true'/>\n" +
                "\t\t\t<i field='classes' value='pkg.BeanTwo'/>\n" +
                "\t\t\t<i field='classes' value='pkg.BeanThree'/>\n" +
                "\t\t</o>\n" +
                "\t\t<i field='inex'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<java.lang.String field='messagesFolder' value='messages'/>\n" +
                "\t<java.lang.String field='filesConfName' value='conf'/>\n" +
                "\t<sm field='beans'>\n" +
                "\t\t<java.lang.String key='' value='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='pkg.BeanOne'/>\n" +
                "\t\t\t<i field='inex'/>\n" +
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
                "\t<sm field='lateValidators'>\n" +
                "\t\t<str key='' value='my_val'/>\n" +
                "\t\t<str value='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "\t<i field='inex'/>\n" +
                "</cfg>\n" +
                "\n";
        Navigation n_ = new Navigation();
        DualAnalyzedContext page_ = loadConfiguration(lgNames_, xmlConf_, n_);
        n_.setFiles(files_);
        assertTrue(setupRendClassesInit(n_, lgNames_, page_));
        n_.initializeRendSession(page_.getContext().getContext(), page_.getStds());
        assertEq("<html><body><a c:command=\"page2.html\" href=\"\" n-a=\"0\"/></body></html>",n_.getHtmlText());
    }

    private static DualAnalyzedContext loadConfiguration(BeanCustLgNames _lgNames, String _xmlConf, Navigation _n) {
        DefaultConfigurationLoader def_ = new DefaultConfigurationLoader(_lgNames);
        return _n.loadConfiguration(_xmlConf, "", _lgNames, BeanFileBuilder.newInstance(_lgNames.getContent(),_lgNames.getBeanAliases()), def_);
    }

    @Test
    public void process8Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><a c:command=\"page2.html\"/>{$class(java.lang.$math).getDeclaredMethods(&quot;mod&quot;,$true,$false,$class($int),$class($int))[0i].invoke($null,4i,3i)}</body></html>";
        String htmlTwo_ = "<html><body>Next</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public String click($int p){");
        file_.append("  $return $bool(p>2,\"val1\",\"val2\");");
        file_.append(" }");
        file_.append("}");
        files_.put("my_file",file_.toString());
        file_ = new StringBuilder();
        file_.append("my_file");
        files_.put("conf",file_.toString());
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c");
        AnalyzedTestConfigurationBis a_ = buildNav(conf_);
        setFirst(a_,"page1.html");
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        a_.getDual().setFilesConfName("conf");
        a_.getDual().getRenderFiles().add("page1.html");
        a_.getDual().getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("pkg.BeanOne");
        conf_.getBeansInfos().addEntry("bean_one",i_);
        conf_.init(a_.getDual());
        Navigation n_ = new Navigation();
        n_.setSession(conf_);
        n_.setFiles(files_);
        assertTrue(setupRendClassesInitStdMess(a_, n_));
        n_.initializeRendSession(a_.getContext(), a_.getAdvStandards());
        assertEq("<html><body><a c:command=\"page2.html\" href=\"\" n-a=\"0\"/>1</body></html>",n_.getHtmlText());
    }

    @Test
    public void process1FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><a c:command=\"page2.html\"/></body></html>";
        String htmlTwo_ = "<html><body>Next</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class [code.bean.Message;] pkg.MyVal:code.bean.Validator{");
        file_.append(" $public Message validate(Object n,Object o,Object b,Object f,String c,String fd){");
        file_.append("  $return $null;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public String click($int p){");
        file_.append("  $return $bool(p:.:>2,\"val1\",\"val2\");");
        file_.append(" }");
        file_.append("}");
        files_.put("my_file",file_.toString());
        file_ = new StringBuilder();
        file_.append("my_file");
        files_.put("conf",file_.toString());


        BeanCustLgNames lgNames_ = new BeanCustLgNamesImpl();
        basicStandards(lgNames_);
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String field='firstUrl' value='page1.html'/>\n" +
                "\t<java.lang.String field='prefix' value='c'/>\n" +
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
                "\t\t\t<java.lang.String field='className' value='pkg.BeanOne'/>\n" +
                "\t\t\t<i field='inex'/>\n" +
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
                "\t<sm field='lateValidators'>\n" +
                "\t\t<str key='' value='my_val'/>\n" +
                "\t\t<str value='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "\t<i field='inex'/>\n" +
                "</cfg>\n" +
                "\n";
        Navigation n_ = new Navigation();
        assertNull(loadConfiguration(lgNames_, xmlConf_, n_).getContext());
    }
    @Test
    public void process2FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><a c:command=\"page2.html\"/></body></html>";
        String htmlTwo_ = "<html><body>Next</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class [code.bean.Message;] pkg.MyVal:code.bean.Validator{");
        file_.append(" $public Message validate(Object n,Object o,Object b,Object f,String c,String fd){");
        file_.append("  $return $null;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public String click($int p){");
        file_.append("  $return $bool(p:.:>2,\"val1\",\"val2\");");
        file_.append(" }");
        file_.append("}");
        files_.put("my_file",file_.toString());
        file_ = new StringBuilder();
        file_.append("my_file");
        files_.put("conf",file_.toString());


        BeanCustLgNames lgNames_ = new BeanCustLgNamesImpl();
        basicStandards(lgNames_);
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        String xmlConf_ = "";
        Navigation n_ = new Navigation();
        assertNull(loadConfiguration(lgNames_, xmlConf_, n_).getContext());
    }
    @Test
    public void process3FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><a c:command=\"page2.html\"/></body></html>";
        String htmlTwo_ = "<html><body>Next</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class [code.bean.Message;] pkg.MyVal:code.bean.Validator{");
        file_.append(" $public Message validate(Object n,Object o,Object b,Object f,String c,String fd){");
        file_.append("  $return $null;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public String click($int p){");
        file_.append("  $return $bool(p>2,\"val1\",\"val2\");");
        file_.append(" }");
        file_.append("}");
        files_.put("my_file",file_.toString());
        file_ = new StringBuilder();
        file_.append("my_file");
        files_.put("conf",file_.toString());
        BeanCustLgNames lgNames_ = new BeanCustLgNamesFailImpl();
        basicStandards(lgNames_);
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String field='firstUrl' value='page1.html'/>\n" +
                "\t<java.lang.String field='prefix' value='c'/>\n" +
                "\t<sm field='navigation'>\n" +
                "\t\t<java.lang.String key='' value='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String key='' value='res'/>\n" +
                "\t\t\t<java.lang.String value='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n field=\"context\">\n" +
                "\t\t<java.lang.Integer field='stackOverFlow' value='-1'/>\n" +
                "\t\t<o field='options'>\n" +
                "\t\t\t<v field='suffixVar' value='NONE'/>\n" +
                "\t\t\t<b field='initializeStaticClassFirst' value='true'/>\n" +
                "\t\t\t<i field='inex'/>\n" +
                "\t\t</o>\n" +
                "\t\t<i field='inex'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<java.lang.String field='messagesFolder' value='messages'/>\n" +
                "\t<java.lang.String field='filesConfName' value='conf'/>\n" +
                "\t<sm field='beans'>\n" +
                "\t\t<java.lang.String key='' value='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='pkg.BeanOne'/>\n" +
                "\t\t\t<i field='inex'/>\n" +
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
                "\t<sm field='lateValidators'>\n" +
                "\t\t<str key='' value='my_val'/>\n" +
                "\t\t<str value='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "\t<i field='inex'/>\n" +
                "</cfg>\n" +
                "\n";
        Navigation n_ = new Navigation();
        assertNull(loadConfiguration(lgNames_, xmlConf_, n_).getContext());
    }
    @Test
    public void process4FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><a c:command=\"page2.html\"/></body></html>";
        String htmlTwo_ = "<html><body>Next</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class [code.bean.Message;] pkg.MyVal:code.bean.Validator{");
        file_.append(" $public Message validate(Object n,Object o,Object b,Object f,String c,String fd){");
        file_.append("  $return $null;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public String click($int p){");
        file_.append("  $return $bool(p>2,\"val1\",\"val2\");");
        file_.append(" }");
        file_.append("}");
        files_.put("my_file",file_.toString());
        file_ = new StringBuilder();
        file_.append("my_file");
        files_.put("conf",file_.toString());
        BeanCustLgNames lgNames_ = new BeanCustLgNamesFailMessImpl();
        basicStandards(lgNames_);
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String field='firstUrl' value='page1.html'/>\n" +
                "\t<java.lang.String field='prefix' value='c'/>\n" +
                "\t<sm field='navigation'>\n" +
                "\t\t<java.lang.String key='' value='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String key='' value='res'/>\n" +
                "\t\t\t<java.lang.String value='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n field=\"context\">\n" +
                "\t\t<java.lang.Integer field='stackOverFlow' value='-1'/>\n" +
                "\t\t<o field='options'>\n" +
                "\t\t\t<v field='suffixVar' value='NONE'/>\n" +
                "\t\t\t<b field='initializeStaticClassFirst' value='true'/>\n" +
                "\t\t\t<i field='inex'/>\n" +
                "\t\t</o>\n" +
                "\t\t<i field='inex'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<java.lang.String field='messagesFolder' value='messages'/>\n" +
                "\t<java.lang.String field='filesConfName' value='conf'/>\n" +
                "\t<sm field='beans'>\n" +
                "\t\t<java.lang.String key='' value='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='pkg.BeanOne'/>\n" +
                "\t\t\t<i field='inex'/>\n" +
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
                "\t<sm field='lateValidators'>\n" +
                "\t\t<str key='' value='my_val'/>\n" +
                "\t\t<str value='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "\t<i field='inex'/>\n" +
                "</cfg>\n" +
                "\n";
        Navigation n_ = new Navigation();
        assertNull(loadConfiguration(lgNames_, xmlConf_, n_).getContext());
    }
    private static void basicStandards(BeanLgNames _lgNames) {
        _lgNames.getContent().setDefaultPkg("java.lang");
        _lgNames.getContent().getCoreNames().setAliasObject("java.lang.Object");
        _lgNames.getContent().getCoreNames().setAliasVoid("$void");
        _lgNames.getContent().getCharSeq().setAliasCharSequence("java.lang.CharSequence");
        _lgNames.getContent().getCharSeq().setAliasCharSequenceCompareTo("compareTo");
        _lgNames.getContent().getCharSeq().setAliasStringCompare("compare");
        _lgNames.getContent().getCharSeq().setAliasCharSequenceEquals("equals");
        _lgNames.getContent().getNbAlias().setAliasCompareTo("compareTo");
        _lgNames.getContent().getNbAlias().setAliasCompare("compare");
        _lgNames.getContent().getNbAlias().setAliasEquals("equals");
        _lgNames.getContent().getNbAlias().setAliasToStringMethod("toString");
        _lgNames.getContent().getNbAlias().setAliasSignum("sgn");
        _lgNames.getContent().getNbAlias().setAliasToBinString("bin");
        _lgNames.getContent().getNbAlias().setAliasToOctString("oct");
        _lgNames.getContent().getNbAlias().setAliasToHexString("hex");
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
        _lgNames.getContent().getCoreNames().setAliasBadArgNumber("code.expressionlanguage.exceptions.BadArgNumber");
        _lgNames.getContent().getCoreNames().setAliasAbstractTypeErr("code.expressionlanguage.exceptions.AbstractTypeErr");
        _lgNames.getContent().getCoreNames().setAliasIllegalType("code.expressionlanguage.exceptions.IllegalType");
        _lgNames.getContent().getCoreNames().setAliasNonInvokable("code.expressionlanguage.exceptions.NonInvokable");
        _lgNames.getContent().getCoreNames().setAliasIllegalArg("code.expressionlanguage.exceptions.IllegalArgument");
        _lgNames.getContent().getCoreNames().setAliasBadSize("code.expressionlanguage.exceptions.NegativeSizeException");
        _lgNames.getContent().getCoreNames().setAliasError("java.lang.Exception");
        _lgNames.getContent().getCoreNames().setAliasErrorCurrentStack("current");
        _lgNames.getContent().getCoreNames().setAliasErrorToString("toString");
        _lgNames.getContent().getCoreNames().setAliasGetMessage("getMessage");
        _lgNames.getContent().getCoreNames().setAliasCastType("code.expressionlanguage.exceptions.DynamicCastClassException");
        _lgNames.getContent().getCoreNames().setAliasDivisionZero("code.expressionlanguage.exceptions.DivideZeroException");
        _lgNames.getContent().getMathRef().setAliasMath("java.lang.Math");
        _lgNames.getContent().getMathRef().setAliasAbs("abs");
        _lgNames.getContent().getMathRef().setAliasMax("max");
        _lgNames.getContent().getMathRef().setAliasMin("min");
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
        _lgNames.getContent().getCharSeq().setAliasStringValueOf("valueOf");
        _lgNames.getContent().getCharSeq().setAliasLength("length");
        _lgNames.getContent().getCharSeq().setAliasCharAt("charAt");
        _lgNames.getContent().getCharSeq().setAliasToCharArray("toCharArray");
        _lgNames.getContent().getCharSeq().setAliasCharSequenceToString("toString");
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
        _lgNames.getContent().getStackElt().setAliasStackTraceElementToString("toString");
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
        _lgNames.getContent().getCoreNames().setAliasStringUtilValueOf("valueOf");
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

    private static AnalyzedTestConfigurationBis buildNav(Configuration _conf) {
        Options opt_ = new Options();
        opt_.setReadOnly(true);
        BeanCustLgNames lgNames_ = new BeanCustLgNamesImpl();
        lgNames_.getBeanAliases().setAliasMapKeys("keys");
        lgNames_.getBeanAliases().setAliasMapValues("values");
        lgNames_.getBeanAliases().setAliasMapIndexOfEntry("indexOfEntry");
        lgNames_.getBeanAliases().setAliasMapAddEntry("addEntry");
        lgNames_.getBeanAliases().setAliasMapGetValue("getValue");
        lgNames_.getBeanAliases().setAliasMapFirstValue("firstValue");
        lgNames_.getBeanAliases().setAliasMapLastValue("lastValue");
        lgNames_.getBeanAliases().setAliasMapSetValue("setValue");
        lgNames_.getBeanAliases().setAliasMapPut("put");
        lgNames_.getBeanAliases().setAliasMapPutAll("putAll");
        lgNames_.getBeanAliases().setAliasMapContains("contains");
        lgNames_.getBeanAliases().setAliasMapGetVal("getVal");
        lgNames_.getBeanAliases().setAliasMapRemoveKey("removeKey");
        lgNames_.getBeanAliases().setAliasMapGetKey("getKey");
        lgNames_.getBeanAliases().setAliasMapFirstKey("firstKey");
        lgNames_.getBeanAliases().setAliasMapLastKey("lastKey");
        lgNames_.getBeanAliases().setAliasMapSetKey("setKey");
        lgNames_.getBeanAliases().setAliasMapSize("size");
        lgNames_.getBeanAliases().setAliasMapIsEmpty("isEmpty");
        lgNames_.getBeanAliases().setAliasMapClear("clear");
        lgNames_.getBeanAliases().setAliasValidator("code.bean.Validator");
        lgNames_.getBeanAliases().setAliasValidate("validate");
        lgNames_.getBeanAliases().setAliasBean("code.bean.Bean");
        lgNames_.getBeanAliases().setAliasStringMapObject("code.util.StringMapObject");
        lgNames_.getBeanAliases().setAliasForms("forms");
        lgNames_.getBeanAliases().setAliasGetForms("getForms");
        lgNames_.getBeanAliases().setAliasSetForms("setForms");
        lgNames_.getBeanAliases().setAliasLanguage("language");
        lgNames_.getBeanAliases().setAliasGetLanguage("getLanguage");
        lgNames_.getBeanAliases().setAliasSetLanguage("setLanguage");
        lgNames_.getBeanAliases().setAliasScope("scope");
        lgNames_.getBeanAliases().setAliasGetScope("getScope");
        lgNames_.getBeanAliases().setAliasSetScope("setScope");
        lgNames_.getBeanAliases().setAliasDataBaseField("dataBase");
        lgNames_.getBeanAliases().setAliasGetDataBase("getDataBase");
        lgNames_.getBeanAliases().setAliasSetDataBase("setDataBase");
        lgNames_.getBeanAliases().setAliasBeforeDisplaying("beforeDisplaying");
        lgNames_.getBeanAliases().setAliasMessage("code.bean.Message");
        lgNames_.getBeanAliases().setAliasNewMessage("newStandardMessage");
        lgNames_.getBeanAliases().setAliasMessageFormat("format");
        lgNames_.getBeanAliases().setAliasMessageGetArgs("getArgs");
        lgNames_.getBeanAliases().setAliasMessageSetArgs("setArgs");
        InitializationLgNames.basicStandards(lgNames_);
        lgNames_.getContent().getMathRef().setAliasMath("java.lang.$math");
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        ContextEl contextEl_ = ContextFactory.simpleBuild((int) IndexConstants.INDEX_NOT_FOUND_ELT, opt_, lgNames_, tabWidth_);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        ContextFactory.validatedStds(a_, kw_, new CustList<CommentDelimiters>(), opt_, contextEl_.getClasses().getCommon(), new DefaultConstantsCalculator(lgNames_.getNbAlias()), BeanFileBuilder.newInstance(lgNames_.getContent(),lgNames_.getBeanAliases()), lgNames_.getContent(), tabWidth_, page_);
        lgNames_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return new AnalyzedTestConfigurationBis(_conf, lgNames_, contextEl_, new DualConfigurationContext(), page_);
    }
    private static boolean setupRendClassesInitStdMess(AnalyzedTestConfigurationBis _a, Navigation _n) {
        DualConfigurationContext d_ = _a.getDual();
        DualAnalyzedContext dual_ = new DualAnalyzedContext(_a.getAnalyzing(),_a.getAdvStandards(),d_);
        return _a.getAdvStandards().setupAll(_n, _n.getSession(), _n.getFiles(), dual_).isAllEmptyErrors();
    }

    private static boolean setupRendClassesInit(Navigation _nav, BeanLgNames _stds, DualAnalyzedContext _dual) {
        return _stds.setupAll(_nav, _nav.getSession(), _nav.getFiles(), _dual).isAllEmptyErrors();
    }
}
