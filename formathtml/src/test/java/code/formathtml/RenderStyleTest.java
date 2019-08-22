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

public final class RenderStyleTest extends CommonRender {
    @Test
    public void process1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String html_ = "<html><head><link href=\"main.css\" rel=\"stylesheet\"/></head><style>CONTENT</style><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("main.css", ".classTest{color:blue;}");
        Configuration conf_ = contextElThird();
        conf_.setBeans(new StringMap<Bean>());
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
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
        assertEq("<html><head><link href=\"main.css\" rel=\"stylesheet\"/><style>.classTest{color:blue;}</style></head><style>.classTest{color:blue;}CONTENT</style><body/></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String html_ = "<html><head><link href=\"main.css\" rel=\"stylesheet\" param0=\".classTest'{'color:{&quot;blue&quot;};'}'\"/></head><style>CONTENT</style><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("main.css", "{0}");
        Configuration conf_ = contextElThird();
        conf_.setBeans(new StringMap<Bean>());
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
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
        assertEq("<html><head><link href=\"main.css\" rel=\"stylesheet\"/><style>.classTest{color:blue;}</style></head><style>{0}CONTENT</style><body/></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String html_ = "<html><head><style>CONTENT</style><link href=\"main.css\" rel=\"stylesheet\" param0=\".classTest'{'color:{&quot;blue&quot;};'}'\"/></head><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("main.css", "{0}");
        Configuration conf_ = contextElThird();
        conf_.setBeans(new StringMap<Bean>());
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
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
        assertEq("<html><head><style>CONTENT.classTest{color:blue;}</style><link href=\"main.css\" rel=\"stylesheet\"/></head><body/></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String html_ = "<html><head><style/><link href=\"main.css\" rel=\"stylesheet\" param0=\".classTest'{'color:{&quot;blue&quot;};'}'\"/></head><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("main.css", "{0}");
        Configuration conf_ = contextElThird();
        conf_.setBeans(new StringMap<Bean>());
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
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
        assertEq("<html><head><style>.classTest{color:blue;}</style><link href=\"main.css\" rel=\"stylesheet\"/></head><body/></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String html_ = "<html><head><link rel=\"stylesheet\"/></head><style>CONTENT</style><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("main.css", ".classTest{color:blue;}");
        Configuration conf_ = contextElThird();
        conf_.setBeans(new StringMap<Bean>());
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
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
        assertEq("<html><head><link rel=\"stylesheet\"/></head><style>CONTENT</style><body/></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process6Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String html_ = "<html><link href=\"main.css\" rel=\"stylesheet\"/><style>CONTENT</style><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("main.css", ".classTest{color:blue;}");
        Configuration conf_ = contextElThird();
        conf_.setBeans(new StringMap<Bean>());
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
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
        assertEq("<html><link href=\"main.css\" rel=\"stylesheet\"/><style>.classTest{color:blue;}CONTENT</style><body/></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process7Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String html_ = "<html><head><link href=\"main.css\"/></head><style>CONTENT</style><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("main.css", ".classTest{color:blue;}");
        Configuration conf_ = contextElThird();
        conf_.setBeans(new StringMap<Bean>());
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
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
        assertEq("<html><head><link href=\"main.css\"/></head><style>CONTENT</style><body/></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process8Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String html_ = "<html><head><link href=\"main.css\" rel=\"stylesheet\"/></head><style>CONTENT</style><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("main1.css", ".classTest{color:blue;}");
        Configuration conf_ = contextElThird();
        conf_.setBeans(new StringMap<Bean>());
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
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
        assertEq("<html><head><link href=\"main.css\" rel=\"stylesheet\"/></head><style>CONTENT</style><body/></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process9Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String html_ = "<html><head><link href=\"main.css\" rel=\"stylesheet\" param0=\".classTest'{'color:{&quot;blue&quot;+1/0};'}'\"/></head><style>CONTENT</style><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("main.css", "{0}");
        Configuration conf_ = contextElThird();
        conf_.setBeans(new StringMap<Bean>());
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
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
}
