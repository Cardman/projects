package code.formathtml;

import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class RenderAnchorTest extends CommonRender {
    @Test
    public void process1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><a c:command=\"$click()\">two</a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append(" $public $void click(){");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body><a c:command=\"$bean_one\" c:sgn=\"pkg.BeanOne.click()\" href=\"\" n-a=\"0\">two</a></body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }

    @Test
    public void process2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click({1})\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><a c:command=\"$click({nb})\">two</a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int nb=5;");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append(" $public $int click($int i){");
        file_.append("  $return i*2;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body><a c:command=\"$bean_one\" c:sgn=\"pkg.BeanOne.click($int)\" href=\"\" n-a=\"0\">two</a></body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><a c:command=\"page1.html\">two</a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append(" $public $void click(){");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body><a c:command=\"page1.html\" href=\"\" n-a=\"0\">two</a></body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><a name='\"sample\"'>two</a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append(" $public $void click(){");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body><a name=\"sample\">two</a></body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click({1})\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><a c:command=\"$click({1/0})\">two</a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int nb=5;");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append(" $public $int click($int i){");
        file_.append("  $return i*2;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertNotNull(getExOneBean(folder_, relative_, html_, files_, filesSec_));
    }

    private Struct getExOneBean(String _folder, String _relative, String _html, StringMap<String> _files, StringMap<String> _filesSec) {
        return getCommExOneBean(_folder, _relative, _html, _files, _filesSec);
    }

    private String getResOneBean(String _folder, String _relative, String _html, StringMap<String> _files, StringMap<String> _filesSec) {
        return getCommOneBean(_folder,_relative,_html,_files,_filesSec);
    }

    @Test
    public void process1FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click({1})\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><a c:command='$click({\"\"},{$null})'>two</a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int nb=5;");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append(" $public $int click($int i){");
        file_.append("  $return i*2;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertTrue(hasErrOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process2FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click({1})\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><a c:command='$click({},{$null})'>two</a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int nb=5;");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append(" $public $int click($int i){");
        file_.append("  $return i*2;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertTrue(hasErrOneBean(folder_, relative_, html_, files_, filesSec_));
    }

    @Test
    public void process3FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click({1})\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><a c:command=\"$clicked({nb})\">two</a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int nb=5;");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append(" $public $int click($int i){");
        file_.append("  $return i*2;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertTrue(hasErrOneBean(folder_, relative_, html_, files_, filesSec_));
    }

    @Test
    public void process4FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click({1})\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='q=0'/><a c:command=\"$clicked({nb},q)\">two</a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int nb=5;");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append(" $public $int click($int i){");
        file_.append("  $return i*2;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertTrue(hasErrOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    private boolean hasErrOneBean(String _folder, String _relative, String _html, StringMap<String> _files, StringMap<String> _filesSec) {
        return hasCommErrOneBean(_folder, _relative, _html, _files, _filesSec);
    }

}
