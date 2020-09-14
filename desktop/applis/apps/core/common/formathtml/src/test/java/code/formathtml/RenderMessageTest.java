package code.formathtml;

import code.util.StringMap;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class RenderMessageTest extends CommonRender {
    @Test
    public void process1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param value='&quot;Text&quot;'/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        Configuration conf_ = contextElFive(filesSec_);
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>desc &lt;Text&gt;<a c:command=\"$bean_one.click\" href=\"\" n-a=\"0\">two</a>After</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click({1})\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param value='&quot;Text&quot;'/><c:param value='2'/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        Configuration conf_ = contextElFive(filesSec_);
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>desc &lt;Text&gt;<a c:command=\"$bean_one.click(2)\" href=\"\" n-a=\"0\">two</a>After</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
        assertEq(1,conf_.getHtmlPage().getAnchorsArgs().size());
        assertEq("2",conf_.getHtmlPage().getAnchorsArgs().last().last());
    }
    @Test
    public void process3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"link\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param value='&quot;Text&quot;'/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        Configuration conf_ = contextElFive(filesSec_);
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>desc &lt;Text&gt;<a c:command=\"link\" href=\"\" n-a=\"0\">two</a>After</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<br/>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param value='&quot;Text&quot;'/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        Configuration conf_ = contextElFive(filesSec_);
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>desc &lt;Text&gt;<br/>After</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<br/>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:message escaped='escaped' value=\"msg_example,three\"><c:param value='&quot;Text&quot;'/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        Configuration conf_ = contextElFive(filesSec_);
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>desc &amp;lt;Text&amp;gt;&lt;br/&gt;After</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process6Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a name=\"link\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param value='&quot;Text&quot;'/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        Configuration conf_ = contextElFive(filesSec_);
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>desc &lt;Text&gt;<a name=\"link\">two</a>After</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process7Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a href=\"link\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param value='&quot;Text&quot;'/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        Configuration conf_ = contextElFive(filesSec_);
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>desc &lt;Text&gt;<a href=\"link\" n-a=\"0\">two</a>After</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process8Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc {0}<a href=\"link\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param quoted='' value='&lt;Text/&gt;'/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        Configuration conf_ = contextElFive(filesSec_);
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>desc <Text/><a href=\"link\" n-a=\"0\">two</a>After</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process9Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc {0}<a href=\"link\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param quoted='' escaped='' value='{Text}'/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        Configuration conf_ = contextElFive(filesSec_);
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>desc '{'Text'}'<a href=\"link\" n-a=\"0\">two</a>After</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void proces10Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc {0}<a href=\"link\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param escaped='' value=\"&quot;&quot;+'{'+2+'}'\"/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        Configuration conf_ = contextElFive(filesSec_);
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>desc '{'2'}'<a href=\"link\" n-a=\"0\">two</a>After</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }

    @Test
    public void process11Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$var' value='tmpLoc=0'/><c:message value=\"msg_example,three\"><c:param value='&quot;Text&quot;'/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        Configuration conf_ = contextElFive(filesSec_);
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>desc &lt;Text&gt;<a c:command=\"$bean_one.click\" href=\"\" n-a=\"0\">two</a>After</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process12Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$var' value='tmpLoc=0'/><c:message value=\"msg_example,three\"><c:param value='1/0'/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        Configuration conf_ = contextElFive(filesSec_);
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_,conf_);
        assertNotNull(getException(conf_));
    }
    @Test
    public void proces13Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc {0}<a href=\"link\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param escaped='' value=\"&quot;&quot;+$new pkg.MyInt(2)\"/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        Configuration conf_ = contextElFive(filesSec_);
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>desc 2<a href=\"link\" n-a=\"0\">two</a>After</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void proces14Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc {0}<a href=\"link\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param escaped='' value=\"$new pkg.MyInt(2)\"/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        Configuration conf_ = contextElFive(filesSec_);
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_,conf_);
        assertNotNull(getException(conf_));
    }
    @Test
    public void process15Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='code.bean.Message' value='m=code.bean.Message.newStandardMessage()'/>{m==m};{m==code.bean.Message.newStandardMessage()}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        Configuration conf_ = contextElFive(filesSec_);
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>true;false</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process1FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After<\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        Configuration conf_ = contextElFive(filesSec_);
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void process2FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After<\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,five\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        Configuration conf_ = contextElFive(filesSec_);
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
}
