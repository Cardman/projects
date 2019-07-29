package code.formathtml;

import code.bean.Bean;

import code.bean.validator.Validator;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;

import code.formathtml.util.NodeContainer;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.StringMap;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;

public final class RenderRequestUtilTest extends CommonRender {

    @Test
    public void redirect1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click({1})\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><a c:command=\"$click\">two</a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int nb=5:");
        file_.append(" $public $int[] array:");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2}:");
        file_.append(" }");
        file_.append(" $public $int click(){");
        file_.append("  $return 2:");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        Configuration conf_ = contextElThird(filesSec_);
        conf_.setBeans(new StringMap<Bean>());
        addImportingPage(conf_);
        Struct bean_ = RenderExpUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        conf_.getBuiltBeans().put("bean_one",bean_);
        conf_.clearPages();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.getAnalyzingDoc().setFiles(files_);
        conf_.getAnalyzingDoc().setLanguage(locale_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        RendBlock.getRes(rendDocumentBlock_,conf_);
        addImportingPage(conf_);
        conf_.getLastPage().setGlobalArgumentStruct(bean_,conf_);
        Struct value_ = RendRequestUtil.redirect(conf_, new Argument(bean_), 0);
        assertEq(2,((NumberStruct)value_).intStruct());
    }
    @Test
    public void process2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param value='&quot;Text&quot;'/><c:param value='2'/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array:");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2}:");
        file_.append(" }");
        file_.append(" $public $int click(){");
        file_.append("  $return 2:");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        Configuration conf_ = contextElThird(filesSec_);
        conf_.setBeans(new StringMap<Bean>());
        addImportingPage(conf_);
        Struct bean_ = RenderExpUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        conf_.getBuiltBeans().put("bean_one",bean_);
        conf_.clearPages();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.getAnalyzingDoc().setFiles(files_);
        conf_.getAnalyzingDoc().setLanguage(locale_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        RendBlock.getRes(rendDocumentBlock_,conf_);
        addImportingPage(conf_);
        conf_.getLastPage().setGlobalArgumentStruct(bean_,conf_);
        Struct value_ = RendRequestUtil.redirect(conf_, new Argument(bean_), 0);
        assertEq(2,((NumberStruct)value_).intStruct());
    }
    @Test
    public void redirect3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click({1})\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><a c:command=\"$click({nb})\">two</a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int nb=5:");
        file_.append(" $public $int[] array:");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2}:");
        file_.append(" }");
        file_.append(" $public $int click($int i){");
        file_.append("  $return i;.;*2:");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        Configuration conf_ = contextElThird(filesSec_);
        conf_.setBeans(new StringMap<Bean>());
        addImportingPage(conf_);
        Struct bean_ = RenderExpUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        conf_.getBuiltBeans().put("bean_one",bean_);
        conf_.clearPages();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.getAnalyzingDoc().setFiles(files_);
        conf_.getAnalyzingDoc().setLanguage(locale_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        RendBlock.getRes(rendDocumentBlock_,conf_);
        addImportingPage(conf_);
        conf_.getLastPage().setGlobalArgumentStruct(bean_,conf_);
        Struct value_ = RendRequestUtil.redirect(conf_, new Argument(bean_), 0);
        assertEq(10,((NumberStruct)value_).intStruct());
    }
    @Test
    public void process4Test() {
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
        file_.append(" $public $int[] array:");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2}:");
        file_.append(" }");
        file_.append(" $public $int click($int i){");
        file_.append("  $return i;.;*2:");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        Configuration conf_ = contextElThird(filesSec_);
        conf_.setBeans(new StringMap<Bean>());
        addImportingPage(conf_);
        Struct bean_ = RenderExpUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        conf_.getBuiltBeans().put("bean_one",bean_);
        conf_.clearPages();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.getAnalyzingDoc().setFiles(files_);
        conf_.getAnalyzingDoc().setLanguage(locale_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        RendBlock.getRes(rendDocumentBlock_,conf_);
        addImportingPage(conf_);
        conf_.getLastPage().setGlobalArgumentStruct(bean_,conf_);
        Struct value_ = RendRequestUtil.redirect(conf_, new Argument(bean_), 0);
        assertEq(4,((NumberStruct)value_).intStruct());
    }

    @Test
    public void redirect5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click({1})\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><a c:command=\"$click\">two</a><a c:command=\"$click2\">four</a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int nb=5:");
        file_.append(" $public $int[] array:");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2}:");
        file_.append(" }");
        file_.append(" $public $int click(){");
        file_.append("  $return 2:");
        file_.append(" }");
        file_.append(" $public $int click2(){");
        file_.append("  $return 4:");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        Configuration conf_ = contextElThird(filesSec_);
        conf_.setBeans(new StringMap<Bean>());
        addImportingPage(conf_);
        Struct bean_ = RenderExpUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        conf_.getBuiltBeans().put("bean_one",bean_);
        conf_.clearPages();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.getAnalyzingDoc().setFiles(files_);
        conf_.getAnalyzingDoc().setLanguage(locale_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        RendBlock.getRes(rendDocumentBlock_,conf_);
        addImportingPage(conf_);
        conf_.getLastPage().setGlobalArgumentStruct(bean_,conf_);
        Struct value_ = RendRequestUtil.redirect(conf_, new Argument(bean_), 1);
        assertEq(4,((NumberStruct)value_).intStruct());
    }
    @Test
    public void process6Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a><a c:command=\"$click2\">four</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param value='&quot;Text&quot;'/><c:param value='2'/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array:");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2}:");
        file_.append(" }");
        file_.append(" $public $int click(){");
        file_.append("  $return 2:");
        file_.append(" }");
        file_.append(" $public $int click2(){");
        file_.append("  $return 4:");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        Configuration conf_ = contextElThird(filesSec_);
        conf_.setBeans(new StringMap<Bean>());
        addImportingPage(conf_);
        Struct bean_ = RenderExpUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        conf_.getBuiltBeans().put("bean_one",bean_);
        conf_.clearPages();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.getAnalyzingDoc().setFiles(files_);
        conf_.getAnalyzingDoc().setLanguage(locale_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        RendBlock.getRes(rendDocumentBlock_,conf_);
        addImportingPage(conf_);
        conf_.getLastPage().setGlobalArgumentStruct(bean_,conf_);
        Struct value_ = RendRequestUtil.redirect(conf_, new Argument(bean_), 1);
        assertEq(4,((NumberStruct)value_).intStruct());
    }
    @Test
    public void redirect7Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click({1})\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><a c:command=\"$click({nb})\">two</a><a c:command=\"$click2({nb})\">four</a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int nb=5:");
        file_.append(" $public $int[] array:");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2}:");
        file_.append(" }");
        file_.append(" $public $int click($int i){");
        file_.append("  $return i;.;*2:");
        file_.append(" }");
        file_.append(" $public $int click2($int i){");
        file_.append("  $return i;.;*4:");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        Configuration conf_ = contextElThird(filesSec_);
        conf_.setBeans(new StringMap<Bean>());
        addImportingPage(conf_);
        Struct bean_ = RenderExpUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        conf_.getBuiltBeans().put("bean_one",bean_);
        conf_.clearPages();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.getAnalyzingDoc().setFiles(files_);
        conf_.getAnalyzingDoc().setLanguage(locale_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        RendBlock.getRes(rendDocumentBlock_,conf_);
        addImportingPage(conf_);
        conf_.getLastPage().setGlobalArgumentStruct(bean_,conf_);
        Struct value_ = RendRequestUtil.redirect(conf_, new Argument(bean_), 1);
        assertEq(20,((NumberStruct)value_).intStruct());
    }
    @Test
    public void process8Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click({1})\">two</a><a c:command=\"$click2({1})\">four</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param value='&quot;Text&quot;'/><c:param value='2'/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array:");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2}:");
        file_.append(" }");
        file_.append(" $public $int click($int i){");
        file_.append("  $return i;.;*2:");
        file_.append(" }");
        file_.append(" $public $int click2($int i){");
        file_.append("  $return i;.;*4:");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        Configuration conf_ = contextElThird(filesSec_);
        conf_.setBeans(new StringMap<Bean>());
        addImportingPage(conf_);
        Struct bean_ = RenderExpUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        conf_.getBuiltBeans().put("bean_one",bean_);
        conf_.clearPages();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.getAnalyzingDoc().setFiles(files_);
        conf_.getAnalyzingDoc().setLanguage(locale_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        RendBlock.getRes(rendDocumentBlock_,conf_);
        addImportingPage(conf_);
        conf_.getLastPage().setGlobalArgumentStruct(bean_,conf_);
        Struct value_ = RendRequestUtil.redirect(conf_, new Argument(bean_), 1);
        assertEq(8,((NumberStruct)value_).intStruct());
    }

    @Test
    public void redirect9Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click({1})\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><a c:command=\"$click\">two</a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int nb=5:");
        file_.append(" $public $int[] array:");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2}:");
        file_.append(" }");
        file_.append(" $public $int click(){");
        file_.append("  $return 1/0:");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        Configuration conf_ = contextElThird(filesSec_);
        conf_.setBeans(new StringMap<Bean>());
        addImportingPage(conf_);
        Struct bean_ = RenderExpUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        conf_.getBuiltBeans().put("bean_one",bean_);
        conf_.clearPages();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.getAnalyzingDoc().setFiles(files_);
        conf_.getAnalyzingDoc().setLanguage(locale_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        RendBlock.getRes(rendDocumentBlock_,conf_);
        addImportingPage(conf_);
        conf_.getLastPage().setGlobalArgumentStruct(bean_,conf_);
        RendRequestUtil.redirect(conf_, new Argument(bean_), 0);
        assertNotNull(conf_.getException());
    }
    @Test
    public void process10Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param value='&quot;Text&quot;'/><c:param value='2'/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array:");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2}:");
        file_.append(" }");
        file_.append(" $public $int click(){");
        file_.append("  $return 1/0:");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        Configuration conf_ = contextElThird(filesSec_);
        conf_.setBeans(new StringMap<Bean>());
        addImportingPage(conf_);
        Struct bean_ = RenderExpUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        conf_.getBuiltBeans().put("bean_one",bean_);
        conf_.clearPages();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.getAnalyzingDoc().setFiles(files_);
        conf_.getAnalyzingDoc().setLanguage(locale_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        RendBlock.getRes(rendDocumentBlock_,conf_);
        addImportingPage(conf_);
        conf_.getLastPage().setGlobalArgumentStruct(bean_,conf_);
        RendRequestUtil.redirect(conf_, new Argument(bean_), 0);
        assertNotNull(conf_.getException());
    }
    @Test
    public void process11Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param value='&quot;Text&quot;'/><c:param value='2'/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array:");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2}:");
        file_.append(" }");
        file_.append(" $public $int click(){");
        file_.append("  $return 1/0:");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        Configuration conf_ = contextElThird(filesSec_);
        conf_.setBeans(new StringMap<Bean>());
        addImportingPage(conf_);
        Struct bean_ = RenderExpUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        conf_.getBuiltBeans().put("bean_one",bean_);
        conf_.clearPages();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.getAnalyzingDoc().setFiles(files_);
        conf_.getAnalyzingDoc().setLanguage(locale_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        RendBlock.getRes(rendDocumentBlock_,conf_);
        addImportingPage(conf_);
        conf_.getLastPage().setGlobalArgumentStruct(bean_,conf_);
        RendRequestUtil.invokeMethodWithNumbersBis(conf_, "");
        assertNotNull(conf_.getException());
    }
    @Test
    public void setRendObject1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<form action=\"\" c:command=\"page1.html\" name=\"myform\"><c:for var=\"n\" list=\"numbers\"><input type=\"radio\" name=\"index\" c:varValue=\"n;\"/></c:for><c:for var=\"n\" list=\"numbersTwo\"><input type=\"radio\" name=\"indexTwo\" c:varValue=\"n;\"/></c:for><input type=\"submit\" value=\"OK\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int index:");
        file_.append(" $public $int indexTwo:");
        file_.append(" $public $int[] numbers:");
        file_.append(" $public $int[] numbersTwo:");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  numbers={2,4,6}:");
        file_.append("  numbersTwo={2,4,6}:");
        file_.append("  index=4:");
        file_.append("  indexTwo=6:");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        Configuration conf_ = contextElThird(filesSec_);
        conf_.setBeans(new StringMap<Bean>());
        addImportingPage(conf_);
        Struct bean_ = RenderExpUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        conf_.getBuiltBeans().put("bean_one",bean_);
        conf_.clearPages();
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page1.html");
        conf_.setValidators(new StringMap<Validator>());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        conf_.setNavigation(new StringMap<StringMap<String>>());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.getAnalyzingDoc().setFiles(files_);
        conf_.getAnalyzingDoc().setLanguage(locale_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        RendBlock.getRes(rendDocumentBlock_,conf_);
        addImportingPage(conf_);
        NodeContainer nCont_ = conf_.getContainersMap().firstValue().getValue(0);
        NodeContainer nContBis_ = conf_.getContainersMap().firstValue().getValue(1);
        RendRequestUtil.setRendObject(conf_,nCont_,new IntStruct(2));
        assertEq(2, ((NumberStruct)((FieldableStruct)bean_).getStruct(nCont_.getIdField())).intStruct());
        assertEq(6, ((NumberStruct)((FieldableStruct)bean_).getStruct(nContBis_.getIdField())).intStruct());
    }
    @Test
    public void setRendObject2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><form action=\"\" c:command=\"page1.html\" name=\"myform\"><input type=\"radio\" name=\"first.value\" c:varValue=\"2\"/><input type=\"radio\" name=\"first.value\" c:varValue=\"4\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public Dto first:");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  first=$new Dto(4):");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.Dto{");
        file_.append(" $public $int value:");
        file_.append(" $public Dto($int p){");
        file_.append("  value = p;.;:");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        Configuration conf_ = contextElThird(filesSec_);
        conf_.setBeans(new StringMap<Bean>());
        addImportingPage(conf_);
        Struct bean_ = RenderExpUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        conf_.getBuiltBeans().put("bean_one",bean_);
        conf_.clearPages();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.getAnalyzingDoc().setFiles(files_);
        conf_.getAnalyzingDoc().setLanguage(locale_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        RendBlock.getRes(rendDocumentBlock_, conf_);
        addImportingPage(conf_);
        NodeContainer nCont_ = conf_.getContainersMap().firstValue().getValue(0);
        RendRequestUtil.setRendObject(conf_, nCont_,new IntStruct(2));
        Struct dto_ = ((FieldableStruct) bean_).getStruct(new ClassField("pkg.BeanOne", "first"));
        assertEq(2, ((NumberStruct)((FieldableStruct)dto_).getStruct(nCont_.getIdField())).intStruct());
    }
}
