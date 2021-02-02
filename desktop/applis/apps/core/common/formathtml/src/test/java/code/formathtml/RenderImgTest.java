package code.formathtml;

import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class RenderImgTest extends CommonRender {
    @Test
    public void process0Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:img src=\"{info}\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int info = 7;");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append(" $public $void click(){");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body><img src=\"7\"/></body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><img src=\"{info}\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("img.txt", "content");
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public String info = \"img.txt\";");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append(" $public $void click(){");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body><img src=\"content\"/></body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }

    @Test
    public void process2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><img src=\"{info}\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("img.txt", "content");
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public String info = \"img2.txt\";");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append(" $public $void click(){");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body><img src=\"{info}\"/></body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><img name=\"\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("img.txt", "content");
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public String info = \"img.txt\";");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append(" $public $void click(){");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body><img name=\"\"/></body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><img src=\"{info/0}\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("img.txt", "content");
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int info = 1;");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append(" $public $void click(){");
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
        String html_ = "<html c:bean=\"bean_one\"><body><img name=\"{info/0}\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("img.txt", "content");
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int info = 1;");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append(" $public $void click(){");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertNotNull(getExOneBean(folder_, relative_, html_, files_, filesSec_));
    }

    private String getResOneBean(String _folder, String _relative, String _html, StringMap<String> _files, StringMap<String> _filesSec) {
        return getCommOneBean(_folder,_relative,_html,_files,_filesSec);
    }
    private Struct getExOneBean(String _folder, String _relative, String _html, StringMap<String> _files, StringMap<String> _filesSec) {
        return getCommExOneBean(_folder,_relative,_html,_files,_filesSec);
    }
}
