package code.formathtml;

import code.bean.translator.Translator;
import code.formathtml.classes.MyTranslator;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.StringMap;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class RenderWhileTest extends CommonRender {
    @Test
    public void process1Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:while condition='i;.&lt;=2'>{i;.}<br/><c:set value='i;.++'/></c:while></body></html>";
        Configuration conf_ = contextElSec();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>0<br/>1<br/>2<br/></body></html>",FormatHtml.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process2Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:while condition='i;.&gt;=2'>{i;.}<br/><c:set value='i;.++'/></c:while></body></html>";
        Configuration conf_ = contextElSec();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body/></html>",FormatHtml.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process4Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:while condition='i;.&lt;2'><c:set className='$var' value='j=0'/><c:while condition='j;.&lt;2'>{i;.}-{j;.}<br/><c:set value='j;.++'/></c:while><br/><c:set value='i;.++'/></c:while></body></html>";
        Configuration conf_ = contextElSec();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>0-0<br/>0-1<br/><br/>1-0<br/>1-1<br/><br/></body></html>",FormatHtml.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process5Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:while condition='i;.&gt;=2/0'>{i;.}<br/><c:set value='i;.++'/></c:while></body></html>";
        Configuration conf_ = contextElSec();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        FormatHtml.getRes(rendDocumentBlock_,conf_);
        assertNotNull(conf_.getException());
    }
    @Test
    public void process6Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:while condition='i;.&gt;=2/(i;.-1)'>{i;.}<br/><c:set value='i;.++'/></c:while></body></html>";
        Configuration conf_ = contextElSec();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        FormatHtml.getRes(rendDocumentBlock_,conf_);
        assertNotNull(conf_.getException());
    }
}
