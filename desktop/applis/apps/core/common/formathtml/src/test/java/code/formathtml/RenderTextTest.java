package code.formathtml;

import code.util.StringMap;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class RenderTextTest extends CommonRender {
    @Test
    public void process0Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$new $int[]{0,1}}</body></html>";
        Configuration conf_ = contextElFive();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>[$int</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process1Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$new $int[]{0,1}.length}</body></html>";
        Configuration conf_ = contextElFive();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>2</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }

    @Test
    public void process2Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>'{escaped}'</body></html>";
        Configuration conf_ = contextElFive();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>{escaped}</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process3Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>'{escaped}'{1+1}</body></html>";
        Configuration conf_ = contextElFive();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>{escaped}2</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process4Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>'{''escaped}'{1+1}</body></html>";
        Configuration conf_ = contextElFive();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>{'escaped}2</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process5Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>''{1+1}</body></html>";
        Configuration conf_ = contextElFive();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>'2</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process6Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$new $int[]{0,1}.length}</body></html>";
        Configuration conf_ = contextElFive(new StringMap<String>());
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>2</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process7Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$new pkg.Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFive(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>2,4</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process8Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$new pkg.Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return $null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFive(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body></body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }

    @Test
    public void process9Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$new pkg.Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]/0+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFive(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_,conf_);
        assertNotNull(getException(conf_));
    }
    @Test
    public void process10Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$bool($true,1,0)}</body></html>";
        Configuration conf_ = contextElFive();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>1</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process11Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='String' value='i=$new pkg.BeanOne.Inner()'/>{i}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        Configuration conf_ = contextElFive(filesSec_);

        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page1.html");

        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getAnalyzingDoc().setFiles(files_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>txt</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process12Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='String' value='i=$new pkg.BeanOne.Inner()'/>{i},{pkg.BeanOne.v}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $int v;");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append("  $static {");
        file_.append("   v++;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        Configuration conf_ = contextElFive(filesSec_);

        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page1.html");

        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getAnalyzingDoc().setFiles(files_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>txt,1</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process13Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=&quot;maniere&quot;'/>{i.textField},{pkg.BeanOne.v}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $int v;");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static Inner $(String v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.textField=v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $static {");
        file_.append("   v++;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        Configuration conf_ = contextElFive(filesSec_);

        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page1.html");

        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getAnalyzingDoc().setFiles(files_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>maniere,1</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process14Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=&quot;man&quot;+&quot;iere&quot;'/>{i.textField},{pkg.BeanOne.v}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $int v;");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static Inner $(String v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.textField=v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $static {");
        file_.append("   v++;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        Configuration conf_ = contextElFive(filesSec_);

        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page1.html");

        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getAnalyzingDoc().setFiles(files_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>maniere,1</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process15Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:try><c:set className='pkg.BeanOne.Inner' value='i=&quot;man&quot;+&quot;iere&quot;'/></c:try><c:finally><c:set className='pkg.BeanOne.Inner' value='i=&quot;man&quot;+&quot;iere&quot;'/></c:finally></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $int v;");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static Inner $(String v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.textField=v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $static {");
        file_.append("   v/=0;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        Configuration conf_ = contextElFive(filesSec_);

        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page1.html");

        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getAnalyzingDoc().setFiles(files_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_,conf_);
        assertNotNull(getException(conf_));
    }
    @Test
    public void process16Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=&quot;man&quot;+&quot;iere&quot;'/>{i.textField},{pkg.BeanOne.v}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $int v;");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static Inner $(String v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.textField=v;");
        file_.append("   $int j = 1/0;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $static {");
        file_.append("   v++;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        Configuration conf_ = contextElFive(filesSec_);

        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page1.html");

        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getAnalyzingDoc().setFiles(files_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_,conf_);
        assertNotNull(getException(conf_));
    }
    @Test
    public void process1FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{1+1</body></html>";
        Configuration conf_ = contextElFive();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void process2FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>}1+1</body></html>";
        Configuration conf_ = contextElFive();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void process3FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set value='{(1})'/></body></html>";
        Configuration conf_ = contextElFive();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void process4FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>1+1{</body></html>";
        Configuration conf_ = contextElFive();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void process5FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>1+1{}<br/>'</body></html>";
        Configuration conf_ = contextElFive();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void process6FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set value=\"({)}&quot;&quot;\"/></body></html>";
        Configuration conf_ = contextElFive();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void process7FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$(Inexist)$null}</body></html>";
        Configuration conf_ = contextElFive();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void process8FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$new pkg.Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFive(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
}
