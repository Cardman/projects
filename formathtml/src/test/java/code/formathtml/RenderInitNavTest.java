package code.formathtml;

import code.bean.BeanInfo;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.variables.VariableSuffix;
import code.formathtml.classes.CustBeanLgNames;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.BeanLgNames;
import code.util.StringMap;
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
        file_.append("  $return $bool(p;.;>2,\"val1\",\"val2\"):");
        file_.append(" }");
        file_.append("}");
        files_.put("my_file",file_.toString());
        file_ = new StringBuilder();
        file_.append("my_file");
        files_.put("conf",file_.toString());
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c");
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdThree(opt_);
        conf_.setContext(cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        cont_.setExecutingInstance(conf_);
        conf_.setFirstUrl("page1.html");
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        conf_.setFilesConfName("conf");
        conf_.getRenderFiles().add("page1.html");
        conf_.getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("pkg.BeanOne");
        conf_.getBeansInfos().addEntry("bean_one",i_);
        conf_.init();
        Navigation n_ = new Navigation();
        n_.setSession(conf_);
        n_.setFiles(files_);
        n_.setupRendClassesInit();
        n_.initializeRendSession();
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
        file_.append("  $return p;.;:");
        file_.append(" }");
        file_.append("}");
        files_.put("my_file",file_.toString());
        file_ = new StringBuilder();
        file_.append("my_file");
        files_.put("conf",file_.toString());
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c");
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdThree(opt_);
        conf_.setContext(cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        cont_.setExecutingInstance(conf_);
        conf_.setFirstUrl("page1.html");
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        conf_.setFilesConfName("conf");
        conf_.getRenderFiles().add("page1.html");
        conf_.getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("pkg.BeanOne");
        conf_.getBeansInfos().addEntry("bean_one",i_);
        conf_.init();
        Navigation n_ = new Navigation();
        n_.setSession(conf_);
        n_.setFiles(files_);
        n_.setupRendClassesInit();
        n_.initializeRendSession();
        assertTrue(!n_.getSession().isEmptyErrors());
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
        file_.append(" $public $int field:");
        file_.append("}");
        files_.put("my_file",file_.toString());
        file_ = new StringBuilder();
        file_.append("my_file2");
        files_.put("conf",file_.toString());
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c");
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdThree(opt_);
        conf_.setContext(cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        cont_.setExecutingInstance(conf_);
        conf_.setFirstUrl("page1.html");
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        conf_.setFilesConfName("conf");
        conf_.getRenderFiles().add("page1.html");
        conf_.getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("pkg.BeanOne");
        conf_.getBeansInfos().addEntry("bean_one",i_);
        conf_.init();
        Navigation n_ = new Navigation();
        n_.setSession(conf_);
        n_.setFiles(files_);
        n_.setupRendClassesInit();
        n_.initializeRendSession();
        assertTrue(!n_.getSession().isEmptyErrors());
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
        file_.append(" $public $int field:");
        file_.append("}");
        files_.put("my_file",file_.toString());
        file_ = new StringBuilder();
        file_.append("my_file");
        files_.put("conf",file_.toString());
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c");
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdThree(opt_);
        conf_.setContext(cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        cont_.setExecutingInstance(conf_);
        conf_.setFirstUrl("page1.html");
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        conf_.setFilesConfName("conf2");
        conf_.getRenderFiles().add("page1.html");
        conf_.getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("pkg.BeanOne");
        conf_.getBeansInfos().addEntry("bean_one",i_);
        conf_.init();
        Navigation n_ = new Navigation();
        n_.setSession(conf_);
        n_.setFiles(files_);
        n_.setupRendClassesInit();
        n_.initializeRendSession();
        assertTrue(!n_.getSession().isEmptyErrors());
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
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        BeanLgNames lgNames_ = new BeanCustLgNamesImpl();
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
        n_.loadConfiguration(xmlConf_,"",lgNames_);
        n_.setFiles(files_);
        n_.setupRendClassesInit();
        n_.initializeRendSession();
        assertEq("<html><body><a c:command=\"page2.html\" href=\"\" n-a=\"0\"/></body></html>",n_.getHtmlText());
        assertEq(2,n_.getSession().getAddedFiles().size());
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
        BeanLgNames lgNames_ = new BeanCustLgNamesImpl();
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
        n_.loadConfiguration(xmlConf_,"",lgNames_);
        n_.setFiles(files_);
        n_.setupRendClassesInit();
        n_.initializeRendSession();
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
        file_.append(" $public Message validate(Object n,Object o,Object b,Object f,String c,String fd){");
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
        BeanLgNames lgNames_ = new BeanCustLgNamesImpl();
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
        n_.loadConfiguration(xmlConf_,"",lgNames_);
        n_.setFiles(files_);
        n_.setupRendClassesInit();
        n_.initializeRendSession();
        assertEq("<html><body><a c:command=\"page2.html\" href=\"\" n-a=\"0\"/></body></html>",n_.getHtmlText());
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
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        BeanLgNames lgNames_ = new BeanCustLgNamesImpl();
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
        n_.loadConfiguration(xmlConf_,"",lgNames_);
        assertTrue(n_.isError());
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
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        BeanLgNames lgNames_ = new BeanCustLgNamesImpl();
        basicStandards(lgNames_);
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        String xmlConf_ = "";
        Navigation n_ = new Navigation();
        n_.loadConfiguration(xmlConf_,"",lgNames_);
        assertTrue(n_.isError());
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
        BeanLgNames lgNames_ = new BeanCustLgNamesFailImpl();
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
        n_.loadConfiguration(xmlConf_,"",lgNames_);
        assertTrue(n_.isError());
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
        BeanLgNames lgNames_ = new BeanCustLgNamesFailMessImpl();
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
        n_.loadConfiguration(xmlConf_,"",lgNames_);
        assertTrue(n_.isError());
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
        _lgNames.setFalseString("false");
        _lgNames.setTrueString("true");
        _lgNames.setNullString("");
    }

}
