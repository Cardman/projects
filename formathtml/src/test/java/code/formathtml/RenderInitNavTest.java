package code.formathtml;

import code.bean.BeanInfo;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.variables.VariableSuffix;
import code.formathtml.util.BeanLgNames;
import code.util.StringMap;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;

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
        conf_.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdThree(opt_);
        conf_.setContext(cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
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
        n_.setupRendClasses();
        n_.initializeRendSession();
        assertEq("<html><body><a c:command=\"page2.html\"/></body></html>",n_.getHtmlText());
    }

}
