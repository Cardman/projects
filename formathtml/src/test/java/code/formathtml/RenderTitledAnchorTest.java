package code.formathtml;

import code.bean.Bean;
import code.expressionlanguage.AnalyzedPageEl;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.StringMap;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class RenderTitledAnchorTest extends CommonRender {

    @Test
    public void process1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html><body><c:a value=\"msg_example,three\" param0=\"TITLE\">Content</c:a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        Configuration conf_ = contextElThird();
        conf_.setBeans(new StringMap<Bean>());
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.getAnalyzingDoc().setFiles(files_);
        setLocale(locale_, conf_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        
        conf_.setDocument(doc_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body><a title=\"desc &amp;lt;TITLE&amp;gt;\">Content</a></body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }

    @Test
    public void process2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html><body><c:a value=\"msg_example,three\" param0=\"{1/0}\">Content</c:a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        Configuration conf_ = contextElThird();
        conf_.setBeans(new StringMap<Bean>());
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.getAnalyzingDoc().setFiles(files_);
        setLocale(locale_, conf_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        
        conf_.setDocument(doc_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_,conf_);
        assertNotNull(conf_.getException());
    }

    @Test
    public void process1FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html><body><c:a value=\"msg_example,five\">Content</c:a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        Configuration conf_ = contextElThird();
        conf_.setBeans(new StringMap<Bean>());
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.getAnalyzingDoc().setFiles(files_);
        setLocale(locale_, conf_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        
        conf_.setDocument(doc_);
        assertTrue(!conf_.getClasses().isEmptyErrors());
    }
}
