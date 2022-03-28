package code.formathtml;

import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class RenderFormTest extends CommonRender {
    @Test
    public void process1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><form action=\"\" c:command=\"$click()\" name=\"myform\"><input type=\"radio\" name=\"first.value\" c:varValue=\"2\"/><input type=\"radio\" name=\"first.value\" c:varValue=\"4\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public Dto first;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  first=$new Dto(4);");
        file_.append(" }");
        file_.append(" $public $void click(){");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.Dto{");
        file_.append(" $public $int value;");
        file_.append(" $public Dto($int p){");
        file_.append("  value = p;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body><form action=\"\" c:command=\"$bean_one\" name=\"myform\" c:sgn=\"pkg.BeanOne.click()\" n-f=\"0\"><input type=\"radio\" name=\"bean_one.first.value\" n-i=\"0\" value=\"2\"/><input type=\"radio\" name=\"bean_one.first.value\" n-i=\"0\" value=\"4\" checked=\"checked\"/></form></body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }

    @Test
    public void process2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><form action=\"\" c:command=\"$click({0})\" name=\"myform\"><input type=\"radio\" name=\"first.value\" c:varValue=\"2\"/><input type=\"radio\" name=\"first.value\" c:varValue=\"4\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public Dto first;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  first=$new Dto(4);");
        file_.append(" }");
        file_.append(" $public $void click($int i){");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.Dto{");
        file_.append(" $public $int value;");
        file_.append(" $public Dto($int p){");
        file_.append("  value = p;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body><form action=\"\" c:command=\"$bean_one\" name=\"myform\" c:sgn=\"pkg.BeanOne.click($int)\" n-f=\"0\"><input type=\"radio\" name=\"bean_one.first.value\" n-i=\"0\" value=\"2\"/><input type=\"radio\" name=\"bean_one.first.value\" n-i=\"0\" value=\"4\" checked=\"checked\"/></form></body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><form name=\"myform\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public Dto first;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  first=$new Dto(4);");
        file_.append(" }");
        file_.append(" $public $void click(){");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.Dto{");
        file_.append(" $public $int value;");
        file_.append(" $public Dto($int p){");
        file_.append("  value = p;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body><form name=\"myform\" n-f=\"0\"/></body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><form action=\"\" c:command=\"$click({1/0})\" name=\"myform\"><input type=\"radio\" name=\"first.value\" c:varValue=\"2\"/><input type=\"radio\" name=\"first.value\" c:varValue=\"4\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public Dto first;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  first=$new Dto(4);");
        file_.append(" }");
        file_.append(" $public $void click($int i){");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.Dto{");
        file_.append(" $public $int value;");
        file_.append(" $public Dto($int p){");
        file_.append("  value = p;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertNotNull(getExOneBean(folder_, relative_, html_, files_, filesSec_));
    }

    @Test
    public void process5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><form action=\"\" c:command=\"$click()\"/><form action=\"\" c:command=\"$click2()\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public Dto first;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  first=$new Dto(4);");
        file_.append(" }");
        file_.append(" $public $void click(){");
        file_.append(" }");
        file_.append(" $public $void click2(){");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.Dto{");
        file_.append(" $public $int value;");
        file_.append(" $public Dto($int p){");
        file_.append("  value = p;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body><form action=\"\" c:command=\"$bean_one\" c:sgn=\"pkg.BeanOne.click()\" n-f=\"0\"/><form action=\"\" c:command=\"$bean_one\" c:sgn=\"pkg.BeanOne.click2()\" n-f=\"1\"/></body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }

    private Struct getExOneBean(String _folder, String _relative, String _html, StringMap<String> _files, StringMap<String> _filesSec) {
        return getCommExOneBean(_folder,_relative,_html,_files,_filesSec);
    }
    private String getResOneBean(String _folder, String _relative, String _html, StringMap<String> _files, StringMap<String> _filesSec) {
        return getCommOneBean(_folder,_relative,_html,_files,_filesSec);
    }

    @Test
    public void process1FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><form action=\"\" c:command=\"$click({&quot;&quot;},{$null})\" name=\"myform\"><input type=\"radio\" name=\"first.value\" c:varValue=\"2\"/><input type=\"radio\" name=\"first.value\" c:varValue=\"4\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public Dto first;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  first=$new Dto(4);");
        file_.append(" }");
        file_.append(" $public $void click($int i){");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.Dto{");
        file_.append(" $public $int value;");
        file_.append(" $public Dto($int p){");
        file_.append("  value = p;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertTrue(hasErrOneBean(folder_, relative_, html_, files_, filesSec_));
    }

    private boolean hasErrOneBean(String _folder, String _relative, String _html, StringMap<String> _files, StringMap<String> _filesSec) {
        return hasCommErrOneBean(_folder,_relative,_html,_files,_filesSec);
    }
}
