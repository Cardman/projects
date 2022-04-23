package code.formathtml;

import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class RenderSubmitTest extends CommonRender {

    @Test
    public void process1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html><body><c:submit message=\"msg_example,three\" param0='\"text\"'/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        assertEq("<html><body><input value=\"desc &amp;lt;text&amp;gt;\" type=\"submit\"/></body></html>", getRes(folder_, relative_, html_, files_));
    }

    @Test
    public void process2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:submit message=\"msg_example,three\" param0=\"typedString\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public String typedString;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  typedString=\"TITLE2\";");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body><input value=\"desc &amp;lt;TITLE2&amp;gt;\" type=\"submit\"/></body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }

    @Test
    public void process3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html><body><c:submit message=\"msg_example,three\" param0=\"1/0\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        assertNotNull(getEx(folder_, relative_, html_, files_, new StringMap<String>()));
    }

    private String getRes(String _folder, String _relative, String _html, StringMap<String> _files) {
        return getCommRes(_folder,_relative,_html,_files,new StringMap<String>());
    }

    private String getResOneBean(String _folder, String _relative, String _html, StringMap<String> _files, StringMap<String> _filesSec) {
        return getCommOneBean(_folder,_relative,_html,_files,_filesSec);
    }


    @Test
    public void process1FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:submit message=\"msg_example,five\" param0=\"{typedString}\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public String typedString;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  typedString=\"TITLE2\";");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertTrue(hasErrOneBean(folder_, html_, files_, filesSec_, relative_));
    }

    @Test
    public void process2FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:submit message=\"msg_example_bis,three\" param0=\"text\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public String typedString;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  typedString=\"TITLE2\";");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertTrue(hasErrOneBean(folder_, html_, files_, filesSec_, relative_));
    }

    @Test
    public void process3FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:submit message=\"msg_example,three\" param0=\"text\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public String typedString;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  typedString=\"TITLE2\";");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertTrue(hasErrOneBean(folder_, html_, files_, filesSec_, StringUtil.concat(relative_, "2")));
    }

    private boolean hasErrOneBean(String _folder, String _html, StringMap<String> _files, StringMap<String> _filesSec, String _concat) {
        return hasCommErrOneBean(_folder,_concat,_html,_files,_filesSec);
    }
}
