package code.formathtml;

import code.bean.Bean;
import code.bean.translator.Translator;
import code.formathtml.classes.BeanOne;
import code.formathtml.classes.BeanTwo;
import code.formathtml.classes.MyTranslator;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.StringMap;
import code.util.StringMapObject;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class RenderImportTest extends CommonRender {
    @Test
    public void process1Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:import page=\"page2.html\"/></body></html>";
        String htmlTwo_ = "<html><body><td>Content</td></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        Configuration conf_ = contextElSec();
        conf_.setBeans(new StringMap<Bean>());
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        Document secDoc_ = DocumentBuilder.parseSax(htmlTwo_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_);
        RendDocumentBlock rendSecDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", secDoc_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getRenders().put("page2.html",rendSecDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        rendSecDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body><td>Content</td></body></html>",FormatHtml.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process2Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:import page=\"page2.html\"/></body></html>";
        String htmlTwo_ = "<html><body><td>Content</td><c:if condition='$true'>Next</c:if>After</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        Configuration conf_ = contextElSec();
        conf_.setBeans(new StringMap<Bean>());
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        Document secDoc_ = DocumentBuilder.parseSax(htmlTwo_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_);
        RendDocumentBlock rendSecDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", secDoc_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getRenders().put("page2.html",rendSecDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        rendSecDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body><td>Content</td>NextAfter</body></html>",FormatHtml.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process3Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:import page=\"page2.html\"/></body></html>";
        String htmlTwo_ = "<html><body><td>Content</td><c:for var=\"t\" list=\"$new java.lang.Integer[]{1}\" className='$int'>{t;}</c:for>After</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        Configuration conf_ = contextElSec();
        conf_.setBeans(new StringMap<Bean>());
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        Document secDoc_ = DocumentBuilder.parseSax(htmlTwo_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_);
        RendDocumentBlock rendSecDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", secDoc_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getRenders().put("page2.html",rendSecDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        rendSecDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body><td>Content</td>1After</body></html>",FormatHtml.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process4Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:import page=\"page2.html\"/></body></html>";
        String htmlTwo_ = "<html><body><td>Content</td><c:for var=\"t\" list=\"$new java.lang.Integer[]{1}\" className='$int'>{t;}</c:for>{2/0}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        Configuration conf_ = contextElSec();
        conf_.setBeans(new StringMap<Bean>());
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        Document secDoc_ = DocumentBuilder.parseSax(htmlTwo_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_);
        RendDocumentBlock rendSecDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", secDoc_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getRenders().put("page2.html",rendSecDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        rendSecDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        FormatHtml.getRes(rendDocumentBlock_,conf_);
        assertNotNull(conf_.getException());
    }
    @Test
    public void process5Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:import page=\"page2.html\"/></body></html>";
        String htmlTwo_ = "<html><body><td>Content</td><c:for var=\"t\" list=\"$new java.lang.Integer[]{1}\" className='$int'>{t;}</c:for><c:set className='$int' value='i=1/0'/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        Configuration conf_ = contextElSec();
        conf_.setBeans(new StringMap<Bean>());
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        Document secDoc_ = DocumentBuilder.parseSax(htmlTwo_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_);
        RendDocumentBlock rendSecDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", secDoc_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getRenders().put("page2.html",rendSecDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        rendSecDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        FormatHtml.getRes(rendDocumentBlock_,conf_);
        assertNotNull(conf_.getException());
    }
}
