package code.formathtml;



import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.StringMap;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class RenderIfTest extends CommonRender {
    @Test
    public void process1Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\">ONE</c:if><c:elseif condition=\"!&quot;&quot;.isEmpty()\">NOT EMPTY</c:elseif></body></html>";
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
        assertEq("<html><body/></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process2Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\">ONE</c:if><c:elseif condition=\"!&quot;string&quot;.isEmpty()\">NOT EMPTY</c:elseif></body></html>";
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
        assertEq("<html><body>NOT EMPTY</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process3Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\">ONE</c:if><c:elseif condition=\"!&quot;&quot;.isEmpty()\">NOT EMPTY</c:elseif><c:else>EMPTY</c:else></body></html>";
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
        assertEq("<html><body>EMPTY</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process4Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;s&quot;.length()==1\">ONE</c:if><c:elseif condition=\"!&quot;&quot;.isEmpty()\">NOT EMPTY</c:elseif><c:else>EMPTY</c:else></body></html>";
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
        assertEq("<html><body>ONE</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process5Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\">ONE</c:if><c:elseif condition=\"!&quot;string&quot;.isEmpty()\">NOT EMPTY</c:elseif><c:else>EMPTY</c:else></body></html>";
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
        assertEq("<html><body>NOT EMPTY</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process6Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\">ONE</c:if></body></html>";
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
        assertEq("<html><body/></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process7Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;s&quot;.length()==1\">ONE</c:if></body></html>";
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
        assertEq("<html><body>ONE</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process8Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\">ONE</c:if><c:elseif condition=\"&quot;string&quot;.length()==2\">TWO</c:elseif><c:elseif condition=\"!&quot;string&quot;.isEmpty()\">NOT EMPTY</c:elseif><c:else>EMPTY</c:else></body></html>";
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
        assertEq("<html><body>NOT EMPTY</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));

    }
    @Test
    public void process9Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1/0\">ONE</c:if><c:elseif condition=\"!&quot;&quot;.isEmpty()\">NOT EMPTY</c:elseif></body></html>";
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
        RendBlock.getRes(rendDocumentBlock_,conf_);
        assertNotNull(getException(conf_));
    }
    @Test
    public void process10Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\">ONE</c:if><c:elseif condition=\"&quot;&quot;.length()&gt;1/0\">NOT EMPTY</c:elseif></body></html>";
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
        RendBlock.getRes(rendDocumentBlock_,conf_);
        assertNotNull(getException(conf_));
    }
    @Test
    public void process11Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\" label='label'>ONE</c:if><c:elseif condition=\"&quot;string&quot;.length()==2\">TWO</c:elseif><c:elseif condition=\"!&quot;string&quot;.isEmpty()\">NOT EMPTY<c:break label='label'/></c:elseif><c:else>EMPTY</c:else></body></html>";
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
        assertEq("<html><body>NOT EMPTY</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));

    }
    @Test
    public void process12Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;s&quot;.length()==1\" label='label'>ONE<c:break label='label'/></c:if><c:elseif condition=\"!&quot;&quot;.isEmpty()\">NOT EMPTY</c:elseif><c:else>EMPTY</c:else></body></html>";
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
        assertEq("<html><body>ONE</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process13Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\" label='label'>ONE</c:if><c:elseif condition=\"!&quot;&quot;.isEmpty()\">NOT EMPTY</c:elseif><c:else>EMPTY<c:break label='label'/></c:else></body></html>";
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
        assertEq("<html><body>EMPTY</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process14Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\">ONE</c:if>\n<c:elseif condition=\"!&quot;&quot;.isEmpty()\">NOT EMPTY</c:elseif></body></html>";
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
        assertEq("<html><body/></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process15Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\">ONE</c:if>\n<c:elseif condition=\"!&quot;string&quot;.isEmpty()\">NOT EMPTY</c:elseif></body></html>";
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
        assertEq("<html><body>NOT EMPTY</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process16Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\">ONE</c:if>\n<c:elseif condition=\"!&quot;&quot;.isEmpty()\">NOT EMPTY</c:elseif>\n<c:else>EMPTY</c:else></body></html>";
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
        assertEq("<html><body>EMPTY</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process17Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\">ONE</c:if>\n<c:else>EMPTY</c:else></body></html>";
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
        assertEq("<html><body>EMPTY</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process18Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\">ONE</c:if>\n<c:elseif condition=\"&quot;string&quot;.length()==2\">TWO</c:elseif>\n<c:elseif condition=\"!&quot;string&quot;.isEmpty()\">NOT EMPTY</c:elseif>\n<c:else>EMPTY</c:else></body></html>";
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
        assertEq("<html><body>NOT EMPTY</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));

    }
    @Test
    public void process1FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:else>EMPTY</c:else></body></html>";
        Configuration conf_ = contextElThird();
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
        String html_ = "<html><body><c:elseif condition=\"&quot;string&quot;.length()==2\">TWO</c:elseif>\n<c:elseif condition=\"!&quot;string&quot;.isEmpty()\">NOT EMPTY</c:elseif>\n<c:else>EMPTY</c:else></body></html>";
        Configuration conf_ = contextElThird();
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
        String html_ = "<html><body>\n<c:else>EMPTY</c:else></body></html>";
        Configuration conf_ = contextElThird();
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
    public void process4FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>\n<c:elseif condition=\"&quot;string&quot;.length()==2\">TWO</c:elseif>\n<c:elseif condition=\"!&quot;string&quot;.isEmpty()\">NOT EMPTY</c:elseif>\n<c:else>EMPTY</c:else></body></html>";
        Configuration conf_ = contextElThird();
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
    public void process5FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"1\">ONE</c:if><c:elseif condition=\"!&quot;&quot;.isEmpty()\">NOT EMPTY</c:elseif></body></html>";
        Configuration conf_ = contextElThird();
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
