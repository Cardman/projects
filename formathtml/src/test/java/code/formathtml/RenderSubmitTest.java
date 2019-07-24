package code.formathtml;

import code.bean.Bean;
import code.bean.translator.Translator;
import code.expressionlanguage.AnalyzedPageEl;
import code.formathtml.classes.BeanOne;
import code.formathtml.classes.BeanTwo;
import code.formathtml.classes.MyTranslator;
import code.formathtml.util.BeanStruct;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class RenderSubmitTest extends CommonRender {

    @Test
    public void process1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html><body><c:submit message=\"msg_example,three\" param0=\"text\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        BeanTwo beanTwo_ = new BeanTwo();
        Configuration conf_ = contextElThird(new StringMap<String>());
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.getAnalyzingDoc().setFiles(files_);
        conf_.getAnalyzingDoc().setLanguage(locale_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body><input value=\"desc &amp;lt;text&amp;gt;\" type=\"submit\"/></body></html>", FormatHtml.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }

    @Test
    public void process2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html bean=\"bean_two\"><body><c:submit message=\"msg_example,three\" param0=\"{typedString}\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setTypedString("TITLE");
        Configuration conf_ = contextElSec();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.getBuiltBeans().put("bean_two", new BeanStruct(beanTwo_));
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.getAnalyzingDoc().setFiles(files_);
        conf_.getAnalyzingDoc().setLanguage(locale_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html bean=\"bean_two\"><body><input value=\"desc &amp;lt;TITLE2&amp;gt;\" type=\"submit\"/></body></html>", FormatHtml.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }

    @Test
    public void process3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html bean=\"bean_two\"><body><c:submit message=\"msg_example,three\" param0=\"{1/0}\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setTypedString("TITLE");
        Configuration conf_ = contextElSec();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.getBuiltBeans().put("bean_two", new BeanStruct(beanTwo_));
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.getAnalyzingDoc().setFiles(files_);
        conf_.getAnalyzingDoc().setLanguage(locale_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        FormatHtml.getRes(rendDocumentBlock_,conf_);
        assertNotNull(conf_.getException());
    }

    @Test
    public void process1FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html bean=\"bean_two\"><body><c:submit message=\"msg_example,five\" param0=\"{typedString}\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setTypedString("TITLE");
        Configuration conf_ = contextElSec();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.getBuiltBeans().put("bean_two", new BeanStruct(beanTwo_));
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.getAnalyzingDoc().setFiles(files_);
        conf_.getAnalyzingDoc().setLanguage(locale_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        assertTrue(!conf_.getClasses().isEmptyErrors());
    }

    @Test
    public void process2FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html bean=\"bean_two\"><body><c:submit message=\"msg_example_bis,three\" param0=\"text\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        BeanTwo beanTwo_ = new BeanTwo();
        Configuration conf_ = contextElSec();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.getAnalyzingDoc().setFiles(files_);
        conf_.getAnalyzingDoc().setLanguage(locale_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        assertTrue(!conf_.getClasses().isEmptyErrors());
    }

    @Test
    public void process3FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html bean=\"bean_two\"><body><c:submit message=\"msg_example,three\" param0=\"text\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        BeanTwo beanTwo_ = new BeanTwo();
        Configuration conf_ = contextElSec();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", StringList.concat(relative_,"2"));
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.getAnalyzingDoc().setFiles(files_);
        conf_.getAnalyzingDoc().setLanguage(locale_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        assertTrue(!conf_.getClasses().isEmptyErrors());
    }
}
