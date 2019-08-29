package code.formathtml;

import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.StringMap;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class RenderAliasTest extends CommonRender {
    @Test
    public void process1Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.Ex;'><body>{$new Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4}:\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1]:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>2,4</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process2Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.*;'><body>{$new Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4}:\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1]:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>2,4</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process3Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkgtwo.ExTwo;pkg.Ex;'><body>{$new Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.ExTwo {}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4}:\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1]:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>2,4</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process4Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkgtwo.*;pkg.Ex;'><body>{$new Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Ex {}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4}:\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1]:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>2,4</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process5Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.*;pkgtwo.ExTwo;'><body>{$new Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.ExTwo {}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4}:\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1]:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>2,4</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process6Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.*;pkgtwo.Ex;'><body>{$new Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.ExTwo {}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4}:\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1]:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>2,4</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process7Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.Ex;pkg.ExTwo;'><body>{$new Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.ExTwo {}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4}:\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1]:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>2,4</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process8Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.*;pkgtwo.*;'><body>{$new Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.ExTwo {}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4}:\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1]:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>2,4</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process9Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.Ex;'><body>{$new Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4}:\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1]:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFourth(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>2,4</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process10Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.*;'><body>{$new Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4}:\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1]:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFourth(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>2,4</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process11Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkgtwo.ExTwo;pkg.Ex;'><body>{$new Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.ExTwo {}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4}:\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1]:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFourth(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>2,4</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process12Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkgtwo.*;pkg.Ex;'><body>{$new Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Ex {}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4}:\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1]:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFourth(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>2,4</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process13Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.*;pkgtwo.ExTwo;'><body>{$new Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.ExTwo {}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4}:\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1]:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFourth(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>2,4</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process14Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.*;pkgtwo.Ex;'><body>{$new Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.ExTwo {}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4}:\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1]:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFourth(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>2,4</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process15Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.Ex;pkg.ExTwo;'><body>{$new Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.ExTwo {}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4}:\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1]:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFourth(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>2,4</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process16Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.*;pkgtwo.*;'><body>{$new Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.ExTwo {}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4}:\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1]:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFourth(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>2,4</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process17Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.Ex;'><body>{$new Ex&lt;$int&gt;().res($id(Ex,#T),15)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $int res(#T v){\n");
        xml_.append("  $return ($int)inst+($int)v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process18Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.*;'><body>{$new Ex&lt;$int&gt;().res($id(Ex,#T),15)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $int res(#T v){\n");
        xml_.append("  $return ($int)inst+($int)v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process19Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.Ex;'><body>{$new Ex&lt;$int&gt;().res($id(Ex,#T),15)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $int res(#T v){\n");
        xml_.append("  $return ($int)inst+($int)v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFourth(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process20Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.*;'><body>{$new Ex&lt;$int&gt;().res($id(Ex,#T),15)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $int res(#T v){\n");
        xml_.append("  $return ($int)inst+($int)v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFourth(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process21Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.Ex;'><body>{$new Ex(14).res($id(Ex,Ex),$new Ex(15))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(Ex v){\n");
        xml_.append("  $return ance+v;.;ance:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFourth(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process22Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.*;'><body>{$new Ex(14).res($id(Ex,Ex),$new Ex(15))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(Ex v){\n");
        xml_.append("  $return ance+v;.;ance:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFourth(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process23Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.Ex;pkg.Integer;'><body>{$new Ex(14).res($id(Ex,Integer),15)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(Integer v){\n");
        xml_.append("  $return ance+v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFourth(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process24Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.*;'><body>{$new Ex(14).res($id(Ex,Integer),15)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(Integer v){\n");
        xml_.append("  $return ance+v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFourth(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process25Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$new StringBuilder(\"hello\")}</body></html>";
        Configuration conf_ = contextElFourth();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>hello</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process26Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$new StringBuilder(\"hello\")}</body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>hello</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process27Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Ex.res;'><body>{res($id(pkg.Ex,$static,$int),15)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process28Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Ex.*;'><body>{res($id(pkg.Ex,$static,$int),15)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process29Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.Ex;$static pkg.Ex.res;'><body>{res($id(Ex,$static,$int),15)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process30Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.*;$static pkg.Ex.*;'><body>{res($id(Ex,$static,$int),15)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process31Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Ex.res;'><body>{res($id(pkg.Ex,$static,$int),15)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res($int v){\n");
        xml_.append("  $return inst+v;.;+2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process32Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Ex.*;'><body>{res($id(pkg.Ex,$static,$int),15)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res($int v){\n");
        xml_.append("  $return inst+v;.;+2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process33Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Ex.res;'><body>{res($id(pkg.Ex,$static,$int),15)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int res2($int v){\n");
        xml_.append("  $return inst+v;.;+2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process34Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Ex.*;'><body>{res($id(pkg.Ex,$static,$int),15)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int res2($int v){\n");
        xml_.append("  $return inst+v;.;+2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process35Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Ex.res;'><body>{res(15)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex:ExSuper {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;+10:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSuper {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process36Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Ex.*;'><body>{res(15)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex:ExSuper {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;+10:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSuper {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process37Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Ex.res;'><body>{res(15)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex:ExSuper {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSuper {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $private $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;+10:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process38Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Ex.*;'><body>{res(15)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex:ExSuper {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSuper {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $private $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;+10:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process39Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Ex.res;'><body>{res(15)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex:ExSuper {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSuper {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;+10:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process40Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Ex.*;'><body>{res(15)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex:ExSuper {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSuper {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;+10:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process41Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.ExTwo.res;$static pkg.Ex.res;'><body>{res(15)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex:ExSuper {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSuper {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $private $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;+10:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process42Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.ExTwo.*;$static pkg.Ex.*;'><body>{res(15)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex:ExSuper {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSuper {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $private $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;+10:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process43Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Ex.res;$static pkg.ExSuper.*;'><body>{res(15)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSuper {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;+10:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process44Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Ex.inst;'><body>{inst}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSuper {\n");
        xml_.append(" $public $static $int inst=15:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>14</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process45Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Ex.*;'><body>{inst}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSuper {\n");
        xml_.append(" $public $static $int inst=15:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>14</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process46Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Ex.inst;$static pkg.ExSuper.*;'><body>{inst}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSuper {\n");
        xml_.append(" $public $static $int inst=15:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>14</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process47Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Ex.*;'><body>{inst}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex:ExSuper {\n");
        xml_.append(" $static $int inst=15:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSuper {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>14</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process48Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Ex.*;'><body>{inst}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex:ExSuper {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSuper {\n");
        xml_.append(" $static $int inst=15:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>14</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process49Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Ex.*;'><body>{inst}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex:ExSuper {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSuper {\n");
        xml_.append(" $private $static $int inst=15:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>14</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process50Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Ex.*;'><body>{inst}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex:ExSuper {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSuper {\n");
        xml_.append(" $int inst=15:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>14</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process51Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Ex.inst;$static pkg.Ex.inst2;'><body>{inst}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $static $int inst2=15:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>14</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process52Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Ex.*;'><body>{inst}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $static $int inst2=15:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>14</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process53Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Ex.inst;pkg.ExSuper;'><body>{inst+ExSuper.inst}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSuper {\n");
        xml_.append(" $public $static $int inst=15:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process54Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Ex.*;pkg.ExSuper;'><body>{inst+ExSuper.inst}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSuper {\n");
        xml_.append(" $public $static $int inst=15:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process55Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Ex.inst;$static pkg.ExInex.inst;'><body>{inst}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSuper {\n");
        xml_.append(" $public $static $int inst=15:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>14</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process56Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Ex.*;$static pkg.ExInex.*;'><body>{inst}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSuper {\n");
        xml_.append(" $public $static $int inst=15:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>14</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process57Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.Outer..Ex;'><body>{$new Ex&lt;$int&gt;().res($id(Ex,#T),15)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Ex<#T> {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $int res(#T v){\n");
        xml_.append("  $return ($int)inst+($int)v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process58Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.Outer..*;'><body>{$new Ex&lt;$int&gt;().res($id(Ex,#T),15)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Ex<#T> {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $int res(#T v){\n");
        xml_.append("  $return ($int)inst+($int)v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process59Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Outer.Ex;'><body>{$new Ex&lt;$int&gt;().res($id(pkg.Outer.Ex,#T),15)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Ex<#T> {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $int res(#T v){\n");
        xml_.append("  $return ($int)inst+($int)v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFourth(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process60Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Outer.*;'><body>{$new Ex&lt;$int&gt;().res($id(pkg.Outer.Ex,#T),15)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Ex<#T> {\n");
        xml_.append(" $public $static $int inst=14:\n");
        xml_.append(" $public $int res(#T v){\n");
        xml_.append("  $return ($int)inst+($int)v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFourth(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process61Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Outer.Ex;'><body>{$new Ex(14).res($id(pkg.Outer.Ex,Ex),$new Ex(15))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Ex {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(Ex v){\n");
        xml_.append("  $return ance+v;.;ance:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFourth(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process62Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Outer.*;'><body>{$new Ex(14).res($id(pkg.Outer.Ex,Ex),$new Ex(15))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Ex {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(Ex v){\n");
        xml_.append("  $return ance+v;.;ance:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFourth(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process63Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.Outer.Ex;'><body>{$new Ex(14).res($id(pkg.Outer.Ex,Ex),$new Ex(15))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Ex {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(Ex v){\n");
        xml_.append("  $return ance+v;.;ance:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFourth(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process64Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.Outer.*;'><body>{$new Ex(14).res($id(pkg.Outer.Ex,Ex),$new Ex(15))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Ex {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(Ex v){\n");
        xml_.append("  $return ance+v;.;ance:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFourth(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process65Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.Outer..Ex;'><body>{$new ..Ex(14).res($id(pkg.Outer..Ex,..Ex),$new ..Ex(15))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Ex {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(..Ex v){\n");
        xml_.append("  $return ance+v;.;ance:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process66Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.Outer..*;'><body>{$new ..Ex(14).res($id(pkg.Outer..Ex,..Ex),$new ..Ex(15))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Ex {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(..Ex v){\n");
        xml_.append("  $return ance+v;.;ance:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process67Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.Outer..Inner;'><body>{$new ..Inner..Ex(14).res($id(pkg.Outer..Inner..Ex,..Inner..Ex),$new ..Inner..Ex(15))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Inner {\n");
        xml_.append("$public $static $class Ex {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(..Ex v){\n");
        xml_.append("  $return ance+v;.;ance:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process68Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.Outer..*;'><body>{$new ..Inner..Ex(14).res($id(pkg.Outer..Inner..Ex,..Inner..Ex),$new ..Inner..Ex(15))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Inner {\n");
        xml_.append("$public $static $class Ex {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(..Ex v){\n");
        xml_.append("  $return ance+v;.;ance:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process69Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.Outer.Inner;'><body>{$new Inner.Ex(14).res($id(pkg.Outer.Inner.Ex,Inner.Ex),$new Inner.Ex(15))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Inner {\n");
        xml_.append("$public $static $class Ex {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(Ex v){\n");
        xml_.append("  $return ance+v;.;ance:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFourth(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process70Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.Outer.*;'><body>{$new Inner.Ex(14).res($id(pkg.Outer.Inner.Ex,Inner.Ex),$new Inner.Ex(15))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Inner {\n");
        xml_.append("$public $static $class Ex {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(Ex v){\n");
        xml_.append("  $return ance+v;.;ance:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFourth(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process71Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.Outer.Inner.Ex;'><body>{$new Ex(14).res($id(pkg.Outer.Inner.Ex,Ex),$new Ex(15))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Inner {\n");
        xml_.append("$public $static $class Ex {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(Ex v){\n");
        xml_.append("  $return ance+v;.;ance:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFourth(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process72Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.Outer.Inner.*;'><body>{$new Ex(14).res($id(pkg.Outer.Inner.Ex,Ex),$new Ex(15))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Inner {\n");
        xml_.append("$public $static $class Ex {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(Ex v){\n");
        xml_.append("  $return ance+v;.;ance:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFourth(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process73Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Outer.Inner.Ex;'><body>{$new Ex(14).res($id(pkg.Outer.Inner.Ex,Ex),$new Ex(15))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Inner {\n");
        xml_.append("$public $static $class Ex {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(Ex v){\n");
        xml_.append("  $return ance+v;.;ance:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFourth(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process74Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Outer.Inner.*;'><body>{$new Ex(14).res($id(pkg.Outer.Inner.Ex,Ex),$new Ex(15))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Inner {\n");
        xml_.append("$public $static $class Ex {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(Ex v){\n");
        xml_.append("  $return ance+v;.;ance:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElFourth(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process75Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.Outer..Inner..Ex;'><body>{$new ..Ex(14).res($id(pkg.Outer..Inner..Ex,..Ex),$new ..Ex(15))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Inner {\n");
        xml_.append("$public $static $class Ex {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(..Ex v){\n");
        xml_.append("  $return ance+v;.;ance:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process76Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='pkg.Outer..Inner..*;'><body>{$new ..Ex(14).res($id(pkg.Outer..Inner..Ex,..Ex),$new ..Ex(15))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Inner {\n");
        xml_.append("$public $static $class Ex {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(..Ex v){\n");
        xml_.append("  $return ance+v;.;ance:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process77Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Outer..Inner..Ex;'><body>{$new ..Ex(14).res($id(pkg.Outer..Inner..Ex,pkg.Outer..Inner..Ex),$new ..Ex(15))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Inner {\n");
        xml_.append("$public $static $class Ex {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(..Ex v){\n");
        xml_.append("  $return ance+v;.;ance:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process78Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Outer..Inner..*;'><body>{$new ..Ex(14).res($id(pkg.Outer..Inner..Ex,pkg.Outer..Inner..Ex),$new ..Ex(15))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Inner {\n");
        xml_.append("$public $static $class Ex {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(..Ex v){\n");
        xml_.append("  $return ance+v;.;ance:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process79Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Outer..Inner..Ex;$static pkg.Outer..Inner..ExTwo;'><body>{$new ..Ex(14).res($id(pkg.Outer..Inner..Ex,pkg.Outer..Inner..Ex),$new ..Ex(15))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Inner {\n");
        xml_.append("$public $static $class Ex {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(..Ex v){\n");
        xml_.append("  $return ance+v;.;ance:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process80Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Outer..Inner..*;$static pkg.Outer..InnerTwo..*;'><body>{$new ..Ex(14).res($id(pkg.Outer..Inner..Ex,pkg.Outer..Inner..Ex),$new ..Ex(15))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Inner {\n");
        xml_.append("$public $static $class Ex {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(..Ex v){\n");
        xml_.append("  $return ance+v;.;ance:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process81Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Outer..Inner..Ex;$static pkg.Outer..InnerTwo..Ex;'><body>{$new ..Ex(14).res($id(pkg.Outer..Inner..Ex,pkg.Outer..Inner..Ex),$new ..Ex(15))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Inner {\n");
        xml_.append("$public $static $class Ex {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(..Ex v){\n");
        xml_.append("  $return ance+v;.;ance:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process82Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Outer..Inner..*;$static pkg.Outer..InnerTwo..Ex;'><body>{$new ..Ex(14).res($id(pkg.Outer..Inner..Ex,pkg.Outer..Inner..Ex),$new ..Ex(15))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Inner {\n");
        xml_.append("$public $static $class Ex {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(..Ex v){\n");
        xml_.append("  $return ance+v;.;ance:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>29</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process1FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$new pkg.Ex(5)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$class pkg.Ex {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(!conf_.getClasses().isEmptyErrors());
    }
    @Test
    public void process2FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Outer..InnerTwo..Ex;'><body>{($void)$null+$new ..Ex(14).res($id(pkg.Outer..Inner..Ex,pkg.Outer..Inner..Ex),$new ..Ex(15))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Inner {\n");
        xml_.append("$public $static $class Ex {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(..Ex v){\n");
        xml_.append("  $return ance+v;.;ance:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(!conf_.getClasses().isEmptyErrors());
    }
    @Test
    public void process3FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:alias='$static pkg.Outer..InnerTwo..Ex;'><body>{($void)$null+$new ..Ex(14).res($id(pkg.Outer..Inner..Ex,pkg.Outer..Inner..Ex),$new ..Ex(15))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Inner {\n");
        xml_.append("$public $static $class Ex {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  ance = p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(..Ex v){\n");
        xml_.append("  $return ance+v;.;ance:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(!conf_.getClasses().isEmptyErrors());
    }
}
