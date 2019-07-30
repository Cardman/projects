package code.formathtml;

import code.bean.Bean;

import code.bean.validator.Validator;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.structs.Struct;

import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.StringMap;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class RenderRadioTest extends CommonRender {
    @Test
    public void process1Test() {
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
        setLocale(locale_, conf_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>HEAD<form action=\"\" c:command=\"page1.html\" name=\"myform\" n-f=\"0\"><input type=\"radio\" name=\"bean_one.index\" n-i=\"0\" value=\"2\"/><input type=\"radio\" name=\"bean_one.index\" n-i=\"0\" value=\"4\" checked=\"checked\"/><input type=\"radio\" name=\"bean_one.index\" n-i=\"0\" value=\"6\"/><input type=\"radio\" name=\"bean_one.indexTwo\" n-i=\"1\" value=\"2\"/><input type=\"radio\" name=\"bean_one.indexTwo\" n-i=\"1\" value=\"4\"/><input type=\"radio\" name=\"bean_one.indexTwo\" n-i=\"1\" value=\"6\" checked=\"checked\"/><input type=\"submit\" value=\"OK\"/></form></body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process2Test() {
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
        setLocale(locale_, conf_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        String res_ = RendBlock.getRes(rendDocumentBlock_, conf_);
        assertEq("<html><body><form action=\"\" c:command=\"page1.html\" name=\"myform\" n-f=\"0\"><input type=\"radio\" name=\"bean_one.first.value\" n-i=\"0\" value=\"2\"/><input type=\"radio\" name=\"bean_one.first.value\" n-i=\"0\" value=\"4\" checked=\"checked\"/></form></body></html>",res_);
        assertNull(conf_.getException());
    }
    @Test
    public void process3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><form action=\"\" c:command=\"page1.html\" name=\"myform\"><input type=\"radio\" name=\"first.value\" c:varValue=\"2\"/><input type=\"radio\" name=\"first.value\" c:varValue=\"4\"/><input type=\"radio\" name=\"second.value\" c:varValue=\"6\"/><input type=\"radio\" name=\"second.value\" c:varValue=\"8\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public Dto first:");
        file_.append(" $public Dto second:");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  first=$new Dto(4):");
        file_.append("  second=$new Dto(6):");
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
        setLocale(locale_, conf_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        String res_ = RendBlock.getRes(rendDocumentBlock_, conf_);
        assertEq("<html><body><form action=\"\" c:command=\"page1.html\" name=\"myform\" n-f=\"0\"><input type=\"radio\" name=\"bean_one.first.value\" n-i=\"0\" value=\"2\"/><input type=\"radio\" name=\"bean_one.first.value\" n-i=\"0\" value=\"4\" checked=\"checked\"/><input type=\"radio\" name=\"bean_one.second.value\" n-i=\"1\" value=\"6\" checked=\"checked\"/><input type=\"radio\" name=\"bean_one.second.value\" n-i=\"1\" value=\"8\"/></form></body></html>",res_);
        assertNull(conf_.getException());
    }
    @Test
    public void process4Test() {
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
        file_.append("  first=$new Dto():");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.Dto{");
        file_.append(" $public Integer value:");
        file_.append(" $public Dto($int p){");
        file_.append("  value = p;.;:");
        file_.append(" }");
        file_.append(" $public Dto(){");
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
        setLocale(locale_, conf_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        String res_ = RendBlock.getRes(rendDocumentBlock_, conf_);
        assertEq("<html><body><form action=\"\" c:command=\"page1.html\" name=\"myform\" n-f=\"0\"><input type=\"radio\" name=\"bean_one.first.value\" n-i=\"0\" value=\"2\"/><input type=\"radio\" name=\"bean_one.first.value\" n-i=\"0\" value=\"4\"/></form></body></html>",res_);
        assertNull(conf_.getException());
    }
    @Test
    public void process5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><form action=\"\" c:command=\"page1.html\" name=\"myform\"><input type=\"radio\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public Dto first:");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  first=$new Dto():");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.Dto{");
        file_.append(" $public Integer value:");
        file_.append(" $public Dto($int p){");
        file_.append("  value = p;.;:");
        file_.append(" }");
        file_.append(" $public Dto(){");
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
        setLocale(locale_, conf_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        String res_ = RendBlock.getRes(rendDocumentBlock_, conf_);
        assertEq("<html><body><form action=\"\" c:command=\"page1.html\" name=\"myform\" n-f=\"0\"><input type=\"radio\"/></form></body></html>",res_);
        assertNull(conf_.getException());
    }
    @Test
    public void process6Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<form action=\"\" c:command=\"page1.html\" name=\"myform\"><c:for var=\"n\" list=\"numbers\"><input type=\"radio\" name=\"(index)\" c:varValue=\"n;\"/></c:for><c:for var=\"n\" list=\"numbersTwo\"><input type=\"radio\" name=\"indexTwo\" c:varValue=\"n;\"/></c:for><input type=\"submit\" value=\"OK\"/></form></body></html>";
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
        setLocale(locale_, conf_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>HEAD<form action=\"\" c:command=\"page1.html\" name=\"myform\" n-f=\"0\"><input type=\"radio\" name=\"bean_one.(index)\" n-i=\"0\" value=\"2\"/><input type=\"radio\" name=\"bean_one.(index)\" n-i=\"0\" value=\"4\" checked=\"checked\"/><input type=\"radio\" name=\"bean_one.(index)\" n-i=\"0\" value=\"6\"/><input type=\"radio\" name=\"bean_one.indexTwo\" n-i=\"1\" value=\"2\"/><input type=\"radio\" name=\"bean_one.indexTwo\" n-i=\"1\" value=\"4\"/><input type=\"radio\" name=\"bean_one.indexTwo\" n-i=\"1\" value=\"6\" checked=\"checked\"/><input type=\"submit\" value=\"OK\"/></form></body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process7Test() {
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
        setLocale(locale_, conf_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_, conf_);
        assertNotNull(conf_.getException());
    }
    @Test
    public void process8Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><form action=\"\" c:command=\"page1.html\" name=\"myform\"><input type=\"radio\" name=\"first.value\" c:varValue=\"1/0\"/><input type=\"radio\" name=\"first.value\" c:varValue=\"4\"/></form></body></html>";
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
        setLocale(locale_, conf_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_, conf_);
        assertNotNull(conf_.getException());
    }
    @Test
    public void process9Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<form action=\"\" c:command=\"page1.html\" name=\"myform\"><c:for var=\"n\" list=\"numbers\"><input type=\"radio\" name=\"index\" c:varValue=\"n;\" c:convertFieldValue='convertField'/></c:for><c:for var=\"n\" list=\"numbersTwo\"><input type=\"radio\" name=\"indexTwo\" c:varValue=\"n;\" c:convertFieldValue='convertField'/></c:for><input type=\"submit\" value=\"OK\"/></form></body></html>";
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
        file_.append(" $public String convertField(Object p){");
        file_.append("  $return \"\"+($int)p;.;:");
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
        setLocale(locale_, conf_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>HEAD<form action=\"\" c:command=\"page1.html\" name=\"myform\" n-f=\"0\"><input type=\"radio\" name=\"bean_one.index\" n-i=\"0\" value=\"2\"/><input type=\"radio\" name=\"bean_one.index\" n-i=\"0\" value=\"4\" checked=\"checked\"/><input type=\"radio\" name=\"bean_one.index\" n-i=\"0\" value=\"6\"/><input type=\"radio\" name=\"bean_one.indexTwo\" n-i=\"1\" value=\"2\"/><input type=\"radio\" name=\"bean_one.indexTwo\" n-i=\"1\" value=\"4\"/><input type=\"radio\" name=\"bean_one.indexTwo\" n-i=\"1\" value=\"6\" checked=\"checked\"/><input type=\"submit\" value=\"OK\"/></form></body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(conf_.getException());
    }
    @Test
    public void process10Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<form action=\"\" c:command=\"page1.html\" name=\"myform\"><c:for var=\"n\" list=\"numbers\"><input type=\"radio\" name=\"index\" c:varValue=\"n;\" c:convertFieldValue='convertField'/></c:for><c:for var=\"n\" list=\"numbersTwo\"><input type=\"radio\" name=\"indexTwo\" c:varValue=\"n;\" c:convertFieldValue='convertField'/></c:for><input type=\"submit\" value=\"OK\"/></form></body></html>";
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
        file_.append(" $public String convertField(Object p){");
        file_.append("  $return (String)p;.;:");
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
        setLocale(locale_, conf_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
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
        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<form action=\"\" c:command=\"page1.html\" name=\"myform\"><c:for var=\"n\" list=\"numbers\"><input type=\"radio\" name=\"0\" c:varValue=\"n;\"/></c:for><c:for var=\"n\" list=\"numbersTwo\"><input type=\"radio\" name=\"indexTwo\" c:varValue=\"n;\"/></c:for><input type=\"submit\" value=\"OK\"/></form></body></html>";
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
        setLocale(locale_, conf_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(!conf_.getClasses().isEmptyErrors());
    }
    @Test
    public void process2FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><form c:command=\"$validate\"><c:for var=\"n\" list=\"numbers\"><input type=\"radio\" name=\"index\" c:varValue=\"n;\" c:convertValue='conv'/></c:for><c:for var=\"n\" list=\"numbersTwo\"><input type=\"radio\" name=\"indexTwo\" c:varValue=\"n;\" c:convertValue='conv'/></c:for><input type=\"submit\" value=\"OK\"/></form></body></html>";
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
        file_.append("  $var ch = getForms().getVal(\"index\"):");
        file_.append("  $if (ch;. != $null){");
        file_.append("   index=($int)ch;.:");
        file_.append("  }");
        file_.append("  ch;. = getForms().getVal(\"indexTwo\"):");
        file_.append("  $if (ch;. != $null){");
        file_.append("   indexTwo=($int)ch;.:");
        file_.append("  }");
        file_.append(" }");
        file_.append(" $public $void validate(){");
        file_.append("  getForms().put(\"index\",index):");
        file_.append("  getForms().put(\"indexTwo\",indexTwo):");
        file_.append(" }");
        file_.append(" $public String conv(String s){");
        file_.append("  $return s;.;:");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        Configuration conf_ = contextElThird(filesSec_);
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page1.html");
        addImportingPage(conf_);
        Struct bean_ = RenderExpUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        conf_.getBuiltBeans().put("bean_one",bean_);
        conf_.clearPages();
        conf_.setValidators(new StringMap<Validator>());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.getAnalyzingDoc().setFiles(files_);
        setLocale(locale_, conf_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(!conf_.getClasses().isEmptyErrors());
    }
    @Test
    public void process3FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<form action=\"\" c:command=\"page1.html\" name=\"myform\"><c:for var=\"n\" list=\"numbers\"><input type=\"radio\" name=\"index\" c:varValue=\"n;\" c:convertFieldValue='convertField'/></c:for><c:for var=\"n\" list=\"numbersTwo\"><input type=\"radio\" name=\"indexTwo\" c:varValue=\"n;\" c:convertFieldValue='convertField'/></c:for><input type=\"submit\" value=\"OK\"/></form></body></html>";
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
        file_.append(" $public Object convertField(Object p){");
        file_.append("  $return p;.;:");
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
        setLocale(locale_, conf_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(!conf_.getClasses().isEmptyErrors());
    }
}
