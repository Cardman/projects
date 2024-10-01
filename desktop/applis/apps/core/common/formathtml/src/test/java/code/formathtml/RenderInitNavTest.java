package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AbsAliasFileBuilder;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.DefAliasFileBuilder;
import code.expressionlanguage.analyze.DefSymbolFactory;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.common.MessagesCdmBase;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ValidatorStandard;
import code.expressionlanguage.stds.ListLoggableLgNames;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.common.AdvFileEscapedCalc;
import code.formathtml.exec.RendStackCall;
import code.formathtml.structs.BeanInfo;
import code.formathtml.util.*;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class RenderInitNavTest extends CommonRender {
    @Test
    public void process1Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><a href=\"page2.html\"/></body></html>";
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
        Configuration conf_ =  EquallableRenderUtil.newConfiguration();
        conf_.setPrefix("c");
        DualNavigationContext a_ = buildNav(conf_);
        setFirst(a_,"page1.html");
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        a_.getDualAnalyzedContext().getContext().setFilesConfName("conf");
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page1.html");
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("pkg.BeanOne");
        addInfo(conf_, i_);
        conf_.init(a_.getDualAnalyzedContext().getContext());
        Navigation n_ = a_.getNavigation();
        n_.setSession(conf_);
        n_.setFiles(files_);
        ContextEl res_ = setupRendClassesInitStdMess(a_, n_);
        init(res_,a_, n_);
        assertEq("<html><body><a href=\"page2.html\" n-a=\"0\"/></body></html>",n_.getHtmlText());
    }
    @Test
    public void process2Test() {
        String locale_ = EN;
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
        Configuration conf_ =  EquallableRenderUtil.newConfiguration();
        conf_.setPrefix("c");
        DualNavigationContext a_ = buildNav(conf_);
        setFirst(a_,"page1.html");
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        a_.getDualAnalyzedContext().getContext().setFilesConfName("conf");
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page1.html");
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("pkg.BeanOne");
        addInfo(conf_, i_);
        conf_.init(a_.getDualAnalyzedContext().getContext());
        Navigation n_ = a_.getNavigation();
        n_.setSession(conf_);
        n_.setFiles(files_);
        assertNull(setupRendClassesInitStdMess(a_, n_));
    }

    @Test
    public void process3Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><a c:command=\"page2.html\"/>{"+ReadConfiguration.FIELD+"}</body></html>";
        String htmlTwo_ = "<html><body>Next</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int "+ReadConfiguration.FIELD+";");
        file_.append("}");
        files_.put("my_file",file_.toString());
        file_ = new StringBuilder();
        file_.append("my_file2");
        files_.put("conf",file_.toString());
        Configuration conf_ =  EquallableRenderUtil.newConfiguration();
        conf_.setPrefix("c");
        DualNavigationContext a_ = buildNav(conf_);
        setFirst(a_,"page1.html");
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        a_.getDualAnalyzedContext().getContext().setFilesConfName("conf");
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page1.html");
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("pkg.BeanOne");
        addInfo(conf_, i_);
        conf_.init(a_.getDualAnalyzedContext().getContext());
        Navigation n_ = a_.getNavigation();
        n_.setSession(conf_);
        n_.setFiles(files_);
        assertNull(setupRendClassesInitStdMess(a_, n_));
    }
    @Test
    public void process4Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><a c:command=\"page2.html\"/>{"+ReadConfiguration.FIELD+"}</body></html>";
        String htmlTwo_ = "<html><body>Next</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int "+ReadConfiguration.FIELD+";");
        file_.append("}");
        files_.put("my_file",file_.toString());
        file_ = new StringBuilder();
        file_.append("my_file");
        files_.put("conf",file_.toString());
        Configuration conf_ =  EquallableRenderUtil.newConfiguration();
        conf_.setPrefix("c");
        DualNavigationContext a_ = buildNav(conf_);
        setFirst(a_,"page1.html");
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        a_.getDualAnalyzedContext().getContext().setFilesConfName("conf2");
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page1.html");
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("pkg.BeanOne");
        addInfo(conf_, i_);
        conf_.init(a_.getDualAnalyzedContext().getContext());
        Navigation n_ = a_.getNavigation();
        n_.setDataBaseStruct(NullStruct.NULL_VALUE);
        n_.setSession(conf_);
        n_.setFiles(files_);
        assertNull(setupRendClassesInitStdMess(a_, n_));
    }
    @Test
    public void process5Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><a href=\"page2.html\"/></body></html>";
        String htmlTwo_ = "<html><body>Next</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.Re:code.bean.Reinitialised{$public $boolean reinitialise(String _0,String _1,String _2,String _3,String _4,String _5){$return  _2 != _3?$false: $true;}}");
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
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FIRST_URL+"' "+ReadConfiguration.VALUE+"='page1.html'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PREFIX+"' "+ReadConfiguration.VALUE+"='c'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.NAVIGATION+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='res'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n "+ReadConfiguration.FIELD+"=\""+ReadConfiguration.CONTEXT+"\">\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.MESSAGES_FOLDER+"' "+ReadConfiguration.VALUE+"='messages'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FILES_CONF_NAME+"' "+ReadConfiguration.VALUE+"='conf'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.BEANS+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.SCOPE+"' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.CLASS_NAME+"' "+ReadConfiguration.VALUE+"='pkg.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PROPERTIES+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='msg_cust'/>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.VALUE+"='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.ADDED_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page1.html'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.RENDER_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page1.html'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_VALIDATORS+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='my_val'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_REINIT+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.Re'/>\n" +
                "\t</sm>\n" +
                "</cfg>";
        Navigation n_ = new Navigation();
        DualAnalyzedContext page_ = loadConfiguration(lgNames_, xmlConf_, n_);
        n_.setFiles(files_);
        ContextEl ctx_ = setupRendClassesInit(n_, lgNames_, page_);
        BeanCustLgNames stds_ = page_.getStds();
        RendStackCall rendStackCall_ = new RendStackCall(InitPhase.NOTHING, ctx_);
        initializeRendSessionDoc(stds_, ctx_, n_, rendStackCall_);
        assertEq("<html><body><a href=\"page2.html\" n-a=\"0\"/></body></html>",n_.getHtmlText());
        assertEq(2,page_.getContext().getAddedFiles().size());
        assertEq(0,n_.getLanguages().size());
    }
    @Test
    public void process6Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><a href=\"page2.html\"/></body></html>";
        String htmlTwo_ = "<html><body>Next</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.Re:code.bean.Reinitialised{$public $boolean reinitialise(String _0,String _1,String _2,String _3,String _4,String _5){$return  _2 != _3?$false: $true;}}");
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
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FIRST_URL+"' "+ReadConfiguration.VALUE+"='page1.html'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PREFIX+"' "+ReadConfiguration.VALUE+"='c'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.NAVIGATION+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='res'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n "+ReadConfiguration.FIELD+"=\""+ReadConfiguration.CONTEXT+"\">\n" +
                "\t\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.STACK_OVER_FLOW+"' "+ReadConfiguration.VALUE+"='-1'/>\n" +
                "\t\t<o "+ReadConfiguration.FIELD+"='"+ReadConfiguration.OPTIONS+"'>\n" +
//                "\t\t\t<v "+ReadConfiguration.FIELD+"='suffixVar' "+ReadConfiguration.VALUE+"='NONE'/>\n" +
//                "\t\t\t<b "+ReadConfiguration.FIELD+"='initializeStaticClassFirst' "+ReadConfiguration.VALUE+"='true'/>\n" +
                "\t\t\t<i "+ReadConfiguration.FIELD+"='inex'/>\n" +
                "\t\t</o>\n" +
                "\t\t<i "+ReadConfiguration.FIELD+"='inex'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.MESSAGES_FOLDER+"' "+ReadConfiguration.VALUE+"='messages'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FILES_CONF_NAME+"' "+ReadConfiguration.VALUE+"='conf'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.BEANS+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.SCOPE+"' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.CLASS_NAME+"' "+ReadConfiguration.VALUE+"='pkg.BeanOne'/>\n" +
                "\t\t\t<i "+ReadConfiguration.FIELD+"='inex'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PROPERTIES+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='msg_cust'/>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.VALUE+"='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.ADDED_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page1.html'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.RENDER_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page1.html'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_VALIDATORS+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='my_val'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "\t<i "+ReadConfiguration.FIELD+"='inex'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_REINIT+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.Re'/>\n" +
                "\t</sm>\n" +
                "</cfg>\n" +
                "\n";
        Navigation n_ = new Navigation();
        DualAnalyzedContext page_ = loadConfiguration(lgNames_, xmlConf_, n_);
        n_.setFiles(files_);
        ContextEl ctx_ = setupRendClassesInit(n_, lgNames_, page_);
        BeanCustLgNames stds_ = page_.getStds();
        RendStackCall rendStackCall_ = new RendStackCall(InitPhase.NOTHING, ctx_);
        initializeRendSessionDoc(stds_, ctx_, n_, rendStackCall_);
        assertEq("<html><body><a href=\"page2.html\" n-a=\"0\"/></body></html>",n_.getHtmlText());
    }
    @Test
    public void process7Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><a href=\"page2.html\"/></body></html>";
        String htmlTwo_ = "<html><body>Next</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.Re:code.bean.Reinitialised{$public $boolean reinitialise(String _0,String _1,String _2,String _3,String _4,String _5){$return  _2 != _3?$false: $true;}}");
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
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FIRST_URL+"' "+ReadConfiguration.VALUE+"='page1.html'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PREFIX+"' "+ReadConfiguration.VALUE+"='c'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.NAVIGATION+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='res'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n "+ReadConfiguration.FIELD+"=\""+ReadConfiguration.CONTEXT+"\">\n" +
                "\t\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.STACK_OVER_FLOW+"' "+ReadConfiguration.VALUE+"='-1'/>\n" +
                "\t\t<o "+ReadConfiguration.FIELD+"='"+ReadConfiguration.OPTIONS+"'>\n" +
//                "\t\t\t<v "+ReadConfiguration.FIELD+"='suffixVar' "+ReadConfiguration.VALUE+"='NONE'/>\n" +
//                "\t\t\t<b "+ReadConfiguration.FIELD+"='initializeStaticClassFirst' "+ReadConfiguration.VALUE+"='true'/>\n" +
                "\t\t\t<i "+ReadConfiguration.FIELD+"='"+ReadConfiguration.CLASSES+"' "+ReadConfiguration.VALUE+"='pkg.BeanTwo'/>\n" +
                "\t\t\t<i "+ReadConfiguration.FIELD+"='"+ReadConfiguration.CLASSES+"' "+ReadConfiguration.VALUE+"='pkg.BeanThree'/>\n" +
                "\t\t</o>\n" +
                "\t\t<i "+ReadConfiguration.FIELD+"='inex'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.MESSAGES_FOLDER+"' "+ReadConfiguration.VALUE+"='messages'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FILES_CONF_NAME+"' "+ReadConfiguration.VALUE+"='conf'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.BEANS+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.SCOPE+"' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.CLASS_NAME+"' "+ReadConfiguration.VALUE+"='pkg.BeanOne'/>\n" +
                "\t\t\t<i "+ReadConfiguration.FIELD+"='inex'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PROPERTIES+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='msg_cust'/>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.VALUE+"='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.ADDED_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page1.html'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.RENDER_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page1.html'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_VALIDATORS+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='my_val'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "\t<i "+ReadConfiguration.FIELD+"='inex'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_REINIT+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.Re'/>\n" +
                "\t</sm>\n" +
                "</cfg>\n" +
                "\n";
        Navigation n_ = new Navigation();
        DualAnalyzedContext page_ = loadConfiguration(lgNames_, xmlConf_, n_);
        n_.setFiles(files_);
        ContextEl ctx_ = setupRendClassesInit(n_, lgNames_, page_);
        BeanCustLgNames stds_ = page_.getStds();
        RendStackCall rendStackCall_ = new RendStackCall(InitPhase.NOTHING, ctx_);
        initializeRendSessionDoc(stds_, ctx_, n_, rendStackCall_);
        assertEq("<html><body><a href=\"page2.html\" n-a=\"0\"/></body></html>",n_.getHtmlText());
    }

    @Test
    public void process7_Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><a href=\"page2.html\"/>{java.lang.Resources.readContent(\"res.txt\")}</body></html>";
        String htmlTwo_ = "<html><body>Next</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.Re:code.bean.Reinitialised{$public $boolean reinitialise(String _0,String _1,String _2,String _3,String _4,String _5){$return  _2 != _3?$false: $true;}}");
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
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        files_.put("res.txt", "content");
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FIRST_URL+"' "+ReadConfiguration.VALUE+"='page1.html'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PREFIX+"' "+ReadConfiguration.VALUE+"='c'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.NAVIGATION+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='res'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n "+ReadConfiguration.FIELD+"=\""+ReadConfiguration.CONTEXT+"\">\n" +
                "\t\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.STACK_OVER_FLOW+"' "+ReadConfiguration.VALUE+"='-1'/>\n" +
                "\t\t<o "+ReadConfiguration.FIELD+"='"+ReadConfiguration.OPTIONS+"'>\n" +
//                "\t\t\t<v "+ReadConfiguration.FIELD+"='suffixVar' "+ReadConfiguration.VALUE+"='NONE'/>\n" +
//                "\t\t\t<b "+ReadConfiguration.FIELD+"='initializeStaticClassFirst' "+ReadConfiguration.VALUE+"='true'/>\n" +
                "\t\t\t<i "+ReadConfiguration.FIELD+"='"+ReadConfiguration.CLASSES+"' "+ReadConfiguration.VALUE+"='pkg.BeanTwo'/>\n" +
                "\t\t\t<i "+ReadConfiguration.FIELD+"='"+ReadConfiguration.CLASSES+"' "+ReadConfiguration.VALUE+"='pkg.BeanThree'/>\n" +
                "\t\t</o>\n" +
                "\t\t<i "+ReadConfiguration.FIELD+"='inex'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.MESSAGES_FOLDER+"' "+ReadConfiguration.VALUE+"='messages'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FILES_CONF_NAME+"' "+ReadConfiguration.VALUE+"='conf'/>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.RESOURCES+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.VALUE+"='res.txt'/>\n" +
                "\t</sl>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.BEANS+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.SCOPE+"' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.CLASS_NAME+"' "+ReadConfiguration.VALUE+"='pkg.BeanOne'/>\n" +
                "\t\t\t<i "+ReadConfiguration.FIELD+"='inex'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PROPERTIES+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='msg_cust'/>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.VALUE+"='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.ADDED_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page1.html'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.RENDER_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page1.html'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_VALIDATORS+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='my_val'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "\t<i "+ReadConfiguration.FIELD+"='inex'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_REINIT+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.Re'/>\n" +
                "\t</sm>\n" +
                "</cfg>\n" +
                "\n";
        Navigation n_ = new Navigation();
        DualAnalyzedContext page_ = loadConfiguration(lgNames_, xmlConf_, n_);
        n_.setFiles(files_);
        ContextEl ctx_ = setupRendClassesInit(n_, lgNames_, page_);
        BeanCustLgNames stds_ = page_.getStds();
        RendStackCall rendStackCall_ = new RendStackCall(InitPhase.NOTHING, ctx_);
        initializeRendSessionDoc(stds_, ctx_, n_, rendStackCall_);
        assertEq("<html><body><a href=\"page2.html\" n-a=\"0\"/>content</body></html>",n_.getHtmlText());
    }

    @Test
    public void process8Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><a href=\"page2.html\"/>{$class(java.lang.$math).getDeclaredMethods(&quot;mod&quot;,$true,$false,$class($int),$class($int))[0i].invoke($null,4i,3i)}</body></html>";
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
        Configuration conf_ =  EquallableRenderUtil.newConfiguration();
        conf_.setPrefix("c");
        DualNavigationContext a_ = buildNav(conf_);
        setFirst(a_,"page1.html");
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", "lt&60;gt&62;amp&38;quot&34;"+html_);
        files_.put("page2.html", "lt&60;gt&62;amp&38;quot&34;"+htmlTwo_);
        a_.getDualAnalyzedContext().getContext().setFilesConfName("conf");
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page1.html");
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("pkg.BeanOne");
        addInfo(conf_, i_);
        conf_.init(a_.getDualAnalyzedContext().getContext());
        Navigation n_ = a_.getNavigation();
        n_.setSession(conf_);
        n_.setFiles(files_);
        ContextEl res_ = setupRendClassesInitStdMess(a_, n_);
        init(res_,a_, n_);
        assertEq("<html><body><a href=\"page2.html\" n-a=\"0\"/>1</body></html>",n_.getHtmlText());
    }

    @Test
    public void process1FailTest() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><a c:command=\"page2.html\"/></body></html>";
        String htmlTwo_ = "<html><body>Next</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.Re:code.bean.Reinitialised{$public $boolean reinitialise(String _0,String _1,String _2,String _3,String _4,String _5){$return  _2 != _3?$false: $true;}}");
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
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FIRST_URL+"' "+ReadConfiguration.VALUE+"='page1.html'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PREFIX+"' "+ReadConfiguration.VALUE+"='c'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.NAVIGATION+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='res'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.MESSAGES_FOLDER+"' "+ReadConfiguration.VALUE+"='messages'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FILES_CONF_NAME+"' "+ReadConfiguration.VALUE+"='conf'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.BEANS+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.SCOPE+"' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.CLASS_NAME+"' "+ReadConfiguration.VALUE+"='pkg.BeanOne'/>\n" +
                "\t\t\t<i "+ReadConfiguration.FIELD+"='inex'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PROPERTIES+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='msg_cust'/>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.VALUE+"='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.ADDED_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page1.html'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.RENDER_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page1.html'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_VALIDATORS+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='my_val'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "\t<i "+ReadConfiguration.FIELD+"='inex'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_REINIT+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.Re'/>\n" +
                "\t</sm>\n" +
                "</cfg>\n" +
                "\n";
        Navigation n_ = new Navigation();
        assertTrue(loadConfiguration(lgNames_, xmlConf_, n_).getContext().isKo());
    }
    @Test
    public void process2FailTest() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><a c:command=\"page2.html\"/></body></html>";
        String htmlTwo_ = "<html><body>Next</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.Re:code.bean.Reinitialised{$public $boolean reinitialise(String _0,String _1,String _2,String _3,String _4,String _5){$return  _2 != _3?$false: $true;}}");
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
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        String xmlConf_ = "";
        Navigation n_ = new Navigation();
        assertTrue(loadConfiguration(lgNames_, xmlConf_, n_).getContext().isKo());
    }
    @Test
    public void process3FailTest() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><a c:command=\"page2.html\"/></body></html>";
        String htmlTwo_ = "<html><body>Next</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.Re:code.bean.Reinitialised{$public $boolean reinitialise(String _0,String _1,String _2,String _3,String _4,String _5){$return  _2 != _3?$false: $true;}}");
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
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FIRST_URL+"' "+ReadConfiguration.VALUE+"='page1.html'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PREFIX+"' "+ReadConfiguration.VALUE+"='c'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.NAVIGATION+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='res'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n "+ReadConfiguration.FIELD+"=\""+ReadConfiguration.CONTEXT+"\">\n" +
                "\t\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.STACK_OVER_FLOW+"' "+ReadConfiguration.VALUE+"='-1'/>\n" +
                "\t\t<o "+ReadConfiguration.FIELD+"='"+ReadConfiguration.OPTIONS+"'>\n" +
//                "\t\t\t<v "+ReadConfiguration.FIELD+"='suffixVar' "+ReadConfiguration.VALUE+"='NONE'/>\n" +
//                "\t\t\t<b "+ReadConfiguration.FIELD+"='initializeStaticClassFirst' "+ReadConfiguration.VALUE+"='true'/>\n" +
                "\t\t\t<i "+ReadConfiguration.FIELD+"='inex'/>\n" +
                "\t\t</o>\n" +
                "\t\t<i "+ReadConfiguration.FIELD+"='inex'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.MESSAGES_FOLDER+"' "+ReadConfiguration.VALUE+"='messages'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FILES_CONF_NAME+"' "+ReadConfiguration.VALUE+"='conf'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.BEANS+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.SCOPE+"' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.CLASS_NAME+"' "+ReadConfiguration.VALUE+"='pkg.BeanOne'/>\n" +
                "\t\t\t<i "+ReadConfiguration.FIELD+"='inex'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PROPERTIES+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='msg_cust'/>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.VALUE+"='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.ADDED_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page1.html'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.RENDER_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page1.html'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_VALIDATORS+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='my_val'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "\t<i "+ReadConfiguration.FIELD+"='inex'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_REINIT+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.Re'/>\n" +
                "\t</sm>\n" +
                "</cfg>\n" +
                "\n";
        Navigation n_ = new Navigation();
        assertTrue(loadConfiguration(lgNames_, xmlConf_, n_).getContext().isKo());
    }
    @Test
    public void process4FailTest() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><a c:command=\"page2.html\"/></body></html>";
        String htmlTwo_ = "<html><body>Next</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.Re:code.bean.Reinitialised{$public $boolean reinitialise(String _0,String _1,String _2,String _3,String _4,String _5){$return  _2 != _3?$false: $true;}}");
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
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FIRST_URL+"' "+ReadConfiguration.VALUE+"='page1.html'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PREFIX+"' "+ReadConfiguration.VALUE+"='c'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.NAVIGATION+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='res'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n "+ReadConfiguration.FIELD+"=\""+ReadConfiguration.CONTEXT+"\">\n" +
                "\t\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.STACK_OVER_FLOW+"' "+ReadConfiguration.VALUE+"='-1'/>\n" +
                "\t\t<o "+ReadConfiguration.FIELD+"='"+ReadConfiguration.OPTIONS+"'>\n" +
//                "\t\t\t<v "+ReadConfiguration.FIELD+"='suffixVar' "+ReadConfiguration.VALUE+"='NONE'/>\n" +
//                "\t\t\t<b "+ReadConfiguration.FIELD+"='initializeStaticClassFirst' "+ReadConfiguration.VALUE+"='true'/>\n" +
                "\t\t\t<i "+ReadConfiguration.FIELD+"='inex'/>\n" +
                "\t\t</o>\n" +
                "\t\t<i "+ReadConfiguration.FIELD+"='inex'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.MESSAGES_FOLDER+"' "+ReadConfiguration.VALUE+"='messages'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FILES_CONF_NAME+"' "+ReadConfiguration.VALUE+"='conf'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.BEANS+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.SCOPE+"' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.CLASS_NAME+"' "+ReadConfiguration.VALUE+"='pkg.BeanOne'/>\n" +
                "\t\t\t<i "+ReadConfiguration.FIELD+"='inex'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PROPERTIES+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='msg_cust'/>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.VALUE+"='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.ADDED_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page1.html'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.RENDER_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page1.html'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_VALIDATORS+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='my_val'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "\t<i "+ReadConfiguration.FIELD+"='inex'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_REINIT+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.Re'/>\n" +
                "\t</sm>\n" +
                "</cfg>\n" +
                "\n";
        Navigation n_ = new Navigation();
        assertTrue(loadConfiguration(lgNames_, xmlConf_, n_).getContext().isKo());
    }

    @Test
    public void execute1() {
        assertEq("",initDbKoConf());
    }

    @Test
    public void execute2() {
        assertEq("",initDbKoConfHere());
    }

    @Test
    public void execute3() {
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FIRST_URL+"' "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PREFIX+"' "+ReadConfiguration.VALUE+"='c'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.NAVIGATION+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='res'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n "+ReadConfiguration.FIELD+"=\""+ReadConfiguration.CONTEXT+"\">\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.MESSAGES_FOLDER+"' "+ReadConfiguration.VALUE+"='messages'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FILES_CONF_NAME+"' "+ReadConfiguration.VALUE+"='conf'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.BEANS+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.SCOPE+"' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.CLASS_NAME+"' "+ReadConfiguration.VALUE+"='pkg.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PROPERTIES+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='msg_cust'/>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.VALUE+"='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.ADDED_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.RENDER_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_VALIDATORS+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='my_val'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_REINIT+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.Re'/>\n" +
                "\t</sm>\n" +
                "</cfg>";
        assertEq("",initDbOkConfNoCtx(xmlConf_));
    }

    @Test
    public void execute4() {
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FIRST_URL+"' "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PREFIX+"' "+ReadConfiguration.VALUE+"='c'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.NAVIGATION+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='res'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n "+ReadConfiguration.FIELD+"=\""+ReadConfiguration.CONTEXT+"\">\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.MESSAGES_FOLDER+"' "+ReadConfiguration.VALUE+"='messages'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FILES_CONF_NAME+"' "+ReadConfiguration.VALUE+"='conf_cl.txt'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.BEANS+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.SCOPE+"' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.CLASS_NAME+"' "+ReadConfiguration.VALUE+"='pkg.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PROPERTIES+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='msg_cust'/>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.VALUE+"='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.ADDED_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.RENDER_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_VALIDATORS+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='my_val'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_REINIT+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.Re'/>\n" +
                "\t</sm>\n" +
                "\t<_ "+ReadConfiguration.FIELD+"='"+ReadConfiguration.INIT_DB+"' "+ReadConfiguration.VALUE+"='cl.Init.init'/>\n" +
                "</cfg>";
        assertEq("", StringUtil.nullToEmpty(initDbOkConfCtx(xmlConf_, "$public $class cl.Init{$public $static Object init(String[] names, String[] contents){$throw $null;}}","<html><body>_</body></html>")));
    }

    @Test
    public void execute5() {
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FIRST_URL+"' "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PREFIX+"' "+ReadConfiguration.VALUE+"='c'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.NAVIGATION+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='res'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n "+ReadConfiguration.FIELD+"=\""+ReadConfiguration.CONTEXT+"\">\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.MESSAGES_FOLDER+"' "+ReadConfiguration.VALUE+"='messages'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FILES_CONF_NAME+"' "+ReadConfiguration.VALUE+"='conf_cl.txt'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.BEANS+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.SCOPE+"' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.CLASS_NAME+"' "+ReadConfiguration.VALUE+"='pkg.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PROPERTIES+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='msg_cust'/>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.VALUE+"='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.ADDED_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.RENDER_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_VALIDATORS+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='my_val'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_REINIT+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.Re'/>\n" +
                "\t</sm>\n" +
                "\t<_ "+ReadConfiguration.FIELD+"='"+ReadConfiguration.INIT_DB+"' "+ReadConfiguration.VALUE+"='cl.Init.init'/>\n" +
                "</cfg>";
        assertEq("java.lang.$defErrorClass\n" +
                "\n" +
                "my_file:1,24:23\n" +
                "cl.Init.", StringUtil.nullToEmpty(initDbOkConfCtx(xmlConf_, "$public $class cl.Init{$static{$throw $null;}$public $static Object init(String[] names, String[] contents){$throw $null;}}","<html><body>_</body></html>")));
    }

    @Test
    public void execute6() {
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FIRST_URL+"' "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PREFIX+"' "+ReadConfiguration.VALUE+"='c'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.NAVIGATION+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='res'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n "+ReadConfiguration.FIELD+"=\""+ReadConfiguration.CONTEXT+"\">\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.MESSAGES_FOLDER+"' "+ReadConfiguration.VALUE+"='messages'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FILES_CONF_NAME+"' "+ReadConfiguration.VALUE+"='conf_cl.txt'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.BEANS+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.SCOPE+"' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.CLASS_NAME+"' "+ReadConfiguration.VALUE+"='pkg.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PROPERTIES+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='msg_cust'/>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.VALUE+"='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.ADDED_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.RENDER_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_VALIDATORS+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='my_val'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_REINIT+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.Re'/>\n" +
                "\t</sm>\n" +
                "\t<_ "+ReadConfiguration.FIELD+"='"+ReadConfiguration.INIT_DB+"' "+ReadConfiguration.VALUE+"='cl.Init.init'/>\n" +
                "</cfg>";
        assertEq("", StringUtil.nullToEmpty(initDbOkConfCtx(xmlConf_, "$public $class cl.Init{$public $static Object init(String[] names, String[] contents){$return $new String[0];}}","<html><body>_</body></html>")));
    }

    @Test
    public void execute7() {
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FIRST_URL+"' "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PREFIX+"' "+ReadConfiguration.VALUE+"='c'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.NAVIGATION+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='res'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n "+ReadConfiguration.FIELD+"=\""+ReadConfiguration.CONTEXT+"\">\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.MESSAGES_FOLDER+"' "+ReadConfiguration.VALUE+"='messages'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FILES_CONF_NAME+"' "+ReadConfiguration.VALUE+"='conf_cl.txt'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.BEANS+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.SCOPE+"' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.CLASS_NAME+"' "+ReadConfiguration.VALUE+"='pkg.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PROPERTIES+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='msg_cust'/>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.VALUE+"='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.ADDED_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.RENDER_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_VALIDATORS+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='my_val'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_REINIT+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.Re'/>\n" +
                "\t</sm>\n" +
                "\t<_ "+ReadConfiguration.FIELD+"='"+ReadConfiguration.INIT_DB+"' "+ReadConfiguration.VALUE+"='cl.Init.'/>\n" +
                "</cfg>";
        assertEq("", StringUtil.nullToEmpty(initDbOkConfCtx(xmlConf_, "$public $class cl.Init{$public $static Object init(String[] names, String[] contents){$return $new String[0];}}","<html><body>_</body></html>")));
    }

    @Test
    public void execute8() {
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FIRST_URL+"' "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PREFIX+"' "+ReadConfiguration.VALUE+"='c'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.NAVIGATION+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='res'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.VALUE+"='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n "+ReadConfiguration.FIELD+"=\""+ReadConfiguration.CONTEXT+"\">\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer "+ReadConfiguration.FIELD+"='"+ReadConfiguration.TAB_WIDTH+"' "+ReadConfiguration.VALUE+"='4'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.MESSAGES_FOLDER+"' "+ReadConfiguration.VALUE+"='messages'/>\n" +
                "\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.FILES_CONF_NAME+"' "+ReadConfiguration.VALUE+"='conf_cl.txt'/>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.BEANS+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.SCOPE+"' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t\t<java.lang.String "+ReadConfiguration.FIELD+"='"+ReadConfiguration.CLASS_NAME+"' "+ReadConfiguration.VALUE+"='pkg.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.PROPERTIES+"'>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='msg_cust'/>\n" +
                "\t\t<java.lang.String "+ReadConfiguration.VALUE+"='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.ADDED_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl "+ReadConfiguration.FIELD+"='"+ReadConfiguration.RENDER_FILES+"'>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_VALIDATORS+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='my_val'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "\t<sm "+ReadConfiguration.FIELD+"='"+ReadConfiguration.LATE_REINIT+"'>\n" +
                "\t\t<str "+ReadConfiguration.KEY+"='' "+ReadConfiguration.VALUE+"='session'/>\n" +
                "\t\t<str "+ReadConfiguration.VALUE+"='pkg.Re'/>\n" +
                "\t</sm>\n" +
                "\t<_ "+ReadConfiguration.FIELD+"='"+ReadConfiguration.INIT_DB+"'/>\n" +
                "</cfg>";
        assertEq("", StringUtil.nullToEmpty(initDbOkConfCtx(xmlConf_, "$public $class cl.Init{$public $static Object init(String[] names, String[] contents){$return $new String[0];}}","<html><body>_</body></html>")));
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
        _lgNames.getContent().getNbAlias().setAliasAlpha("alpha");
        _lgNames.getContent().getNbAlias().setAliasAlphaHex("alphaHex");
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
        _lgNames.getContent().getReflect().setAliasCallRef("callRef");
        _lgNames.getContent().getReflect().setAliasCallRefAfter("callRefAfter");
        _lgNames.getContent().getReflect().setAliasMetaInfo("metaInfo");
        _lgNames.getContent().getReflect().setAliasInstance("instance");
        _lgNames.getContent().getReflect().setAliasAnnotationType("java.lang.$Annotation");
        _lgNames.getContent().getReflect().setAliasAnnotated("java.lang.$Annotated");
        _lgNames.getContent().getReflect().setAliasGlobal("global");
        _lgNames.getContent().getReflect().setAliasGetAnnotations("getAnnotations");
        _lgNames.getContent().getReflect().setAliasGetAnnotationsSupp("getAnnotationsSupp");
        _lgNames.getContent().getReflect().setAliasGetDefaultValue("getDefaultValue");
        _lgNames.getContent().getReflect().setAliasGetAnnotationsParameters("getAnnotationsParameters");
        _lgNames.getContent().getReflect().setAliasGetDeclaredConstructors("getDeclaredConstructors");
        _lgNames.getContent().getReflect().setAliasGetDeclaredFields("getDeclaredFields");
        _lgNames.getContent().getReflect().setAliasGetDeclaredAnonymousTypes("getDeclaredAnonymousTypes");
        _lgNames.getContent().getReflect().setAliasGetDeclaredAnonymousLambda("getDeclaredAnonymousLambda");
        _lgNames.getContent().getReflect().setAliasGetDeclaredAnonymousLambdaLocalVars("getDeclaredAnonymousLambdaLocVars");
        _lgNames.getContent().getReflect().setAliasGetDeclaredAnonymousLambdaLocalVarsNb("getDeclaredAnonymousLambdaLocVarsNb");
        _lgNames.getContent().getReflect().setAliasGetDeclaredAnonymousLambdaLoopVars("getDeclaredAnonymousLambdaLoopVars");
        _lgNames.getContent().getReflect().setAliasGetDeclaredBlocks("getDeclaredBlocks");
        _lgNames.getContent().getReflect().setAliasGetDeclaredSwitchMethods("getDeclaredSwitchMethods");
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
        _lgNames.getContent().getReflect().setAliasInvokeRef("invokeRef");
        _lgNames.getContent().getReflect().setAliasInvokeRefAfter("invokeRefAfter");
        _lgNames.getContent().getReflect().setAliasInvokeDirect("invokeDirect");
        _lgNames.getContent().getReflect().setAliasInvokeDirectRef("invokeDirectRef");
        _lgNames.getContent().getReflect().setAliasInvokeDirectRefAfter("invokeDirectRefAfter");
        _lgNames.getContent().getReflect().setAliasNewInstance("newInstance");
        _lgNames.getContent().getReflect().setAliasNewInstanceRef("newInstanceRef");
        _lgNames.getContent().getReflect().setAliasNewInstanceRefAfter("newInstanceRefAfter");
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
        _lgNames.getContent().getCoreNames().setAliasRange("java.lang.Range");
        _lgNames.getContent().getCoreNames().setAliasRangeLower("lower");
        _lgNames.getContent().getCoreNames().setAliasRangeUpper("upper");
        _lgNames.getContent().getCoreNames().setAliasRangeStep("step");
        _lgNames.getContent().getCoreNames().setAliasRangeUnlimited("unlimited");
        _lgNames.getContent().getCoreNames().setAliasRangeUnlimitedStep("unlimitedStep");
        _lgNames.getContent().getCoreNames().setAliasObjectsUtil("java.lang.$ObjectsUtil");
        _lgNames.getContent().getCoreNames().setAliasStringUtil("java.lang.$StringUtil");
        _lgNames.getContent().getCoreNames().setAliasStringUtilValueOf("valueOf");
        _lgNames.getContent().getCoreNames().setAliasSameRef("eq");
        _lgNames.getContent().getCoreNames().setAliasGetParent("getParent");
        _lgNames.getContent().getCoreNames().setAliasSetParent("setParent");
        _lgNames.getContent().getCoreNames().setAliasGetFct("getFct");
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
        _lgNames.getContent().getReflect().setAliasIsSpecialClass("isSpeClass");
        _lgNames.getContent().getReflect().setAliasIsSpecialMuClass("isSpeMuClass");
        _lgNames.getContent().getReflect().setAliasIsWildCard("isWildCard");
        _lgNames.getContent().getReflect().setAliasIsRefType("isRefType");
        _lgNames.getContent().getReflect().setAliasIsInterface("isInterface");
        _lgNames.getContent().getReflect().setAliasIsEnum("isEnum");
        _lgNames.getContent().getReflect().setAliasIsPrimitive("isPrimitive");
        _lgNames.getContent().getReflect().setAliasIsArray("isArray");
        _lgNames.getContent().getReflect().setAliasIsAnnotation("isAnnotation");
        _lgNames.getContent().getReflect().setAliasMakeWildCard("makeWildCard");
        _lgNames.getContent().getReflect().setAliasMakeRef("makeRefType");
        _lgNames.getContent().getReflect().setAliasIsInstance("isInstance");
        _lgNames.getContent().getReflect().setAliasIsAssignableFrom("isAssignableFrom");
        _lgNames.getContent().getReflect().setAliasInit("init");
        _lgNames.getContent().getReflect().setAliasTryWrap("tryWrap");
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
        _lgNames.getContent().getMathRef().setAliasNativeRandom("natRandom");
        _lgNames.getContent().getMathRef().setAliasEval("eval");
        _lgNames.getContent().getMathRef().setAliasSeed("seed");
        _lgNames.getContent().getMathRef().setAliasSeedSpecGenerator("seedGenerator");
        _lgNames.getContent().getMathRef().setAliasSeedSpecDoubleGenerator("seedDoubleGenerator");
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
        _lgNames.getDisplayedStrings().setAlpha(MessagesCdmBase.DEF_ALPHA);
        _lgNames.getDisplayedStrings().setAlphaHex(MessagesCdmBase.DEF_ALPHA_HEX);
    }

    private static DualNavigationContext buildNav(Configuration _conf) {
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
        lgNames_.getBeanAliases().setAliasReinitInterface("code.bean.Reinitialised");
        lgNames_.getBeanAliases().setAliasReinitMethod("reinitialise");
        lgNames_.getBeanAliases().setAliasBean("code.bean.Bean");
        lgNames_.getBeanAliases().setAliasStringMapObject("code.formathtml.nat.StringMapObject");
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
        lgNames_.getBeanAliases().setAliasDocument("code.bean.Document");
        lgNames_.getBeanAliases().setAliasDocumentAll("all");
        lgNames_.getBeanAliases().setAliasNewMessage("newStandardMessage");
        lgNames_.getBeanAliases().setAliasMessageFormat("format");
        lgNames_.getBeanAliases().setAliasMessageGetArgs("getArgs");
        lgNames_.getBeanAliases().setAliasMessageSetArgs("setArgs");
        InitializationLgNamesRender.basicStandards(lgNames_);
        lgNames_.getContent().getMathRef().setAliasMath("java.lang.$math");
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        page_.setMappingKeyWords(lgNames_.mappingKeywords());
        page_.setMappingAliases(lgNames_.mappingAliases());
        BeanFileBuilder fileBuilder_ = BeanFileBuilder.newInstance(lgNames_.getContent(), lgNames_.getBeanAliases());
        updateMockBuilders(lgNames_,page_);
        Forwards fwd_ = fwd(lgNames_, fileBuilder_, opt_);
        page_.setLogErr(fwd_);
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(fwd_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgNames_.getContent(), page_);
        lgNames_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        DualConfigurationContext dual_ = new DualConfigurationContext();
        Navigation nav_ = new Navigation();
        nav_.setSession(_conf);
        FileBlock file_ = new FileBlock(0, false, "", new AdvFileEscapedCalc(new IntTreeMap<Integer>()));
        file_.metrics("");
        return new DualNavigationContext(nav_, new DualAnalyzedContext(fwd_,page_,lgNames_,dual_, file_));
    }

    public static void updateMockBuilders(BeanCustLgNames _stds, AnalyzedPageEl _page) {
        CustList<AbsAliasFileBuilder> builders_ = new CustList<AbsAliasFileBuilder>();
        builders_.add(new DefAliasFileBuilder());
        builders_.add(_stds.getBeanAliases());
        CustList<AbsAliasFileBuilder> fbs_ = _page.getFileBuilders();
        fbs_.clear();
        fbs_.addAllElts(builders_);
    }
    private static ContextEl setupRendClassesInitStdMess(DualNavigationContext _a, Navigation _n) {
        return _a.getDualAnalyzedContext().getStds().setupAll(_a,new DefRenderContextGenerator()).getContext();
    }

    private void init(ContextEl _ctx,DualNavigationContext _a, Navigation _n) {
        BeanCustLgNames stds_ = _a.getDualAnalyzedContext().getStds();
        RendStackCall rendStackCall_ = new RendStackCall(InitPhase.NOTHING,_ctx);
        initializeRendSessionDoc(stds_, _ctx, _n, rendStackCall_);
    }

    private void initializeRendSessionDoc(BeanCustLgNames _stds, ContextEl _ctx, Navigation _n, RendStackCall _rendStackCall) {
        _stds.initializeRendSessionDoc(_ctx, _n, _rendStackCall);
    }

    private static ContextEl setupRendClassesInit(Navigation _nav, BeanCustLgNames _stds, DualAnalyzedContext _dual) {
        return _stds.setupAll(new DualNavigationContext(_nav, _dual),new DefRenderContextGenerator()).getContext();
    }

    private void addInfo(Configuration _conf, BeanInfo _i) {
        _conf.getBeansInfos().addEntry("bean_one", _i);
    }

    private static DualAnalyzedContext loadConfiguration(BeanCustLgNames _lgNames, String _xmlConf, Navigation _n) {
        DefaultConfigurationLoader def_ = new DefaultConfigurationLoader(_lgNames,new ListLoggableLgNames());
        CustList<AbsAliasFileBuilder> builders_ = new CustList<AbsAliasFileBuilder>();
        builders_.add(new DefAliasFileBuilder());
        builders_.add(_lgNames.getBeanAliases());
        return _n.loadConfiguration(_xmlConf, "", _lgNames, BeanFileBuilder.newInstance(_lgNames.getContent(),_lgNames.getBeanAliases()), builders_, def_);
    }
    private static String initDbOkConfCtx(String _xmlConf, String _brCode, String _page) {
        DefaultInitialization de_ = initDbOkConfBuild(_xmlConf, _brCode, _page);
        String ex_ = de_.execute(new Navigation(),new DefRenderContextGenerator());
        assertEq("ABCDEF",de_.getKeyWordDigit());
        assertNotNull(de_.getContext());
        return ex_;
    }

    private static String initDbOkConfNoCtx(String _xmlConf) {
        DefaultInitialization de_ = initDbOkConfBuild(_xmlConf, "", "");
        String ex_ = de_.execute(new Navigation(),new DefRenderContextGenerator());
        assertNull(de_.getContext());
        return ex_;
    }

    private static DefaultInitialization initDbOkConfBuild(String _xmlConf, String _brCode, String _page) {
        BeanCustLgNamesImpl stds_ = new BeanCustLgNamesImpl();
        basicStandards(stds_);
        String cn_ = "conf.xml";
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry(cn_, _xmlConf);
        files_.addEntry("conf_cl.txt","my_file");
        files_.addEntry("my_file", _brCode);
        files_.addEntry("page.html", _page);
        DefaultInitialization de_ = new DefaultInitialization(stds_, new DefSymbolFactory(), "", cn_, files_);
        de_.setLog(new ListLoggableLgNames());
        return de_;
    }

    private static String initDbKoConf() {
        BeanCustLgNamesImpl stds_ = new BeanCustLgNamesImpl();
        basicStandards(stds_);
        DefaultInitialization de_ = new DefaultInitialization(stds_,new DefSymbolFactory(),"","conf.xml",new StringMap<String>());
        de_.setLog(new ListLoggableLgNames());
        return de_.execute(new Navigation(),new DefRenderContextGenerator());
    }

    private static String initDbKoConfHere() {
        BeanCustLgNamesImpl stds_ = new BeanCustLgNamesImpl();
        basicStandards(stds_);
        String cn_ = "conf.xml";
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry(cn_," ");
        DefaultInitialization de_ = new DefaultInitialization(stds_,new DefSymbolFactory(),"",cn_,files_);
        de_.setLog(new ListLoggableLgNames());
        return de_.execute(new Navigation(),new DefRenderContextGenerator());
    }
}
