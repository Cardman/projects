package code.formathtml;

import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class RenderMessageTest extends CommonRender {
    @Test
    public void process1Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"click\">two</a>After\nfour=''asp''";
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param value='&quot;Text&quot;'/></c:message></body></html>";
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
        assertEq("<html><body>desc &#60;Text&#62;<a c:command=\"bean_one.click\" n-a=\"0\">two</a>After</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process2Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"click({1})\">two</a>After\nfour=''asp''";
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param value='&quot;Text&quot;'/><c:param value='2'/></c:message></body></html>";
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
        file_.append(" $public $int click($int i){");
        file_.append("  $return i*2;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>desc &#60;Text&#62;<a c:command=\"bean_one.click(2)\" n-a=\"0\">two</a>After</body></html>", getAncOneBean(folder_, relative_, html_, files_, filesSec_));
    }

    @Test
    public void process3Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"link\">two</a>After\nfour=''asp''";
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param value='&quot;Text&quot;'/></c:message></body></html>";
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
        file_.append(" $public $int click($int i){");
        file_.append("  $return i*2;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>desc &#60;Text&#62;<a c:command=\"bean_one.link\" n-a=\"0\">two</a>After</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process4Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<br/>After\nfour=''asp''";
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param value='&quot;Text&quot;'/></c:message></body></html>";
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
        file_.append(" $public $int click($int i){");
        file_.append("  $return i*2;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>desc &#60;Text&#62;<br/>After</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process5Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<br/>After\nfour=''asp''";
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html c:bean=\"bean_one\"><body><c:message escaped='escaped' value=\"msg_example,three\"><c:param value='&quot;Text&quot;'/></c:message></body></html>";
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
        file_.append(" $public $int click($int i){");
        file_.append("  $return i*2;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>desc &#38;lt;Text&#38;gt;&#60;br/&#62;After</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process6Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a name=\"link\">two</a>After\nfour=''asp''";
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param value='&quot;Text&quot;'/></c:message></body></html>";
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
        file_.append(" $public $int click($int i){");
        file_.append("  $return i*2;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>desc &#60;Text&#62;<a name=\"link\">two</a>After</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process7Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a href=\"link\">two</a>After\nfour=''asp''";
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param value='&quot;Text&quot;'/></c:message></body></html>";
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
        file_.append(" $public $int click($int i){");
        file_.append("  $return i*2;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>desc &#60;Text&#62;<a href=\"link\" n-a=\"0\">two</a>After</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process8Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc {0}<a href=\"link\">two</a>After\nfour=''asp''";
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param quoted='' value='&lt;Text/&gt;'/></c:message></body></html>";
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
        file_.append(" $public $int click($int i){");
        file_.append("  $return i*2;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>desc <Text/><a href=\"link\" n-a=\"0\">two</a>After</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process9Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc {0}<a href=\"link\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param quoted='' escaped='' value='{Text}'/></c:message></body></html>";
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
        file_.append(" $public $int click($int i){");
        file_.append("  $return i*2;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>desc '{'Text'}'<a href=\"link\" n-a=\"0\">two</a>After</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void proces10Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc {0}<a href=\"link\">two</a>After\nfour=''asp''";
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param escaped='' value=\"&quot;&quot;+'{'+2+'}'\"/></c:message></body></html>";
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
        file_.append(" $public $int click($int i){");
        file_.append("  $return i*2;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>desc '{'2'}'<a href=\"link\" n-a=\"0\">two</a>After</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }

    @Test
    public void process11Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"click\">two</a>After\nfour=''asp''";
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html c:bean=\"bean_one\"><body><c:set className='$var' value='tmpLoc=0'/><c:message value=\"msg_example,three\"><c:param value='&quot;Text&quot;'/></c:message></body></html>";
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
        assertEq("<html><body>desc &#60;Text&#62;<a c:command=\"bean_one.click\" n-a=\"0\">two</a>After</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process12Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$var' value='tmpLoc=0'/><c:message value=\"msg_example,three\"><c:param value='1/0'/></c:message></body></html>";
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
        assertNotNull(getExOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void proces13Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc {0}<a href=\"link\">two</a>After\nfour=''asp''";
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param escaped='' value=\"&quot;&quot;+$new pkg.MyInt(2)\"/></c:message></body></html>";
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
        file_.append(" $public $int click($int i){");
        file_.append("  $return i*2;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.MyInt{");
        file_.append(" $public $int index;");
        file_.append(" $public MyInt(){}");
        file_.append(" $public MyInt($int p){");
        file_.append("  index = p;");
        file_.append(" }");
        file_.append(" $public String $toString()\n");
        file_.append(" {\n");
        file_.append("  $return \"\"+index;\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>desc 2<a href=\"link\" n-a=\"0\">two</a>After</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void proces14Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc {0}<a href=\"link\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param escaped='' value=\"$new pkg.MyInt(2)\"/></c:message></body></html>";
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
        file_.append(" $public $int click($int i){");
        file_.append("  $return i*2;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.MyInt{");
        file_.append(" $public $int index;");
        file_.append(" $public MyInt(){}");
        file_.append(" $public MyInt($int p){");
        file_.append("  index = p;");
        file_.append(" }");
        file_.append(" $public String $toString()\n");
        file_.append(" {\n");
        file_.append("  $return \"\"+index/0;\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertNotNull(getExOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process15Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='code.bean.Message' value='m=code.bean.Message.newStandardMessage()'/>{m==m};{m==code.bean.Message.newStandardMessage()}</body></html>";
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
        assertEq("<html><body>true;false</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }

    private Struct getExOneBean(String _folder, String _relative, String _html, StringMap<String> _files, StringMap<String> _filesSec) {
        return getCommExOneBean(_folder,_relative,_html,_files,_filesSec);
    }


    private String getResOneBean(String _folder, String _relative, String _html, StringMap<String> _files, StringMap<String> _filesSec) {
        return getCommOneBean(_folder, _relative, _html, _files, _filesSec);
    }

    @Test
    public void process1FailTest() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"click\">two</a>After<\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"/></body></html>";
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
        assertTrue(hasErrOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process2FailTest() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"click\">two</a>After<\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,five\"/></body></html>";
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
        assertTrue(hasErrOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process3FailTest() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"cl+1+$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='q=0,q=0'/><c:message value=\"msg_example,three\"/><a c:command=\"cl+1+$click\">two</a></body></html>";
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
        assertTrue(hasErrOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process4FailTest() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"click((o,p),(1),p,(q))\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='q=0,q=0'/><c:message value=\"msg_example,three\"/><a c:command=\"click((o,p),(1),p,(q))\">two</a></body></html>";
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
        assertTrue(hasErrOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process5FailTest() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"click((o,p),(1),p,(q),(tmpLoc))\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><<c:set className='$int' value='q=0,q=0'/><c:message value=\"msg_example,three\"><p value='0'></p></c:message><a c:command=\"click((o,p),(1),p,(q),(tmpLoc))\">two</a></body></html>";
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
        assertTrue(hasErrOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    private boolean hasErrOneBean(String _folder, String _relative, String _html, StringMap<String> _files, StringMap<String> _filesSec) {
        return hasCommErrOneBean(_folder,_relative,_html,_files,_filesSec);
    }
}
