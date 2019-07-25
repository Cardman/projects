package code.formathtml;
import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import code.bean.validator.Validator;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.structs.*;
import code.formathtml.classes.*;
import code.sml.Document;
import code.sml.DocumentBuilder;
import org.junit.Test;

import code.bean.Bean;
import code.bean.translator.Translator;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.variables.VariableSuffix;
import code.formathtml.util.BeanLgNames;
import code.formathtml.structs.BeanStruct;
import code.formathtml.util.NodeContainer;
import code.formathtml.util.ValueChangeEvent;
import code.util.StringList;
import code.util.StringMap;


public class HtmlRequestTest extends CommonRender {
    private static final String COMPOSITE = "code.formathtml.classes.Composite";

    @Test
    public void setObject1Test() {
        Composite composite_ = new Composite();
        Configuration conf_ = newConfiguration();
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        NodeContainer nc_ = new NodeContainer();
        nc_.setLastToken("integer");
        nc_.setObject(composite_, conf_.getContext());
        nc_.getNodeInformation().setVarMethod("");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasInteger());
//      HtmlRequest.setObject(conf_, composite_, "integer", "", 7, "", conf_.getStandards().getAliasInteger(), new List<Long>());
        HtmlRequest.setObject(conf_, nc_, new IntStruct(7));
        assertEq(7, composite_.getInteger());
    }

    @Test
    public void setObject2Test() {
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().setInteger(8);
        Configuration conf_ = newConfiguration();
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        conf_.getLastPage().setGlobalArgumentObj(bean_.getComposite(),COMPOSITE, conf_.getContext());
        NodeContainer nc_ = new NodeContainer();
        nc_.setLastToken("integer");
        nc_.setTypedStruct(new IntStruct(8));
        nc_.setObject(bean_.getComposite(), conf_.getContext());
        nc_.getNodeInformation().setVarMethod("");
        nc_.getNodeInformation().setChanging("updateValue");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasInteger());
//        HtmlRequest.setObject(conf_, bean_, "composite.integer", "", 88, "updateValue", conf_.getStandards().getAliasInteger(), new List<Long>(4L));
        HtmlRequest.setObject(conf_, nc_, new IntStruct(88));
        assertEq(88, bean_.getComposite().getInteger());
        assertEq(1, bean_.getComposite().getStrings().size());
        assertEq("88 8", bean_.getComposite().getStrings().first());
        ValueChangeEvent changing_ = bean_.getComposite().getChanging();
        int new_ = ((NumberStruct) changing_.getNewValue()).intStruct();
        assertEq(88, new_);
        int old_ = ((NumberStruct) changing_.getOldValue()).intStruct();
        assertEq(8, old_);
    }

    @Test
    public void setObject3Test() {
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().setInteger(8);
        Configuration conf_ = newConfiguration();
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        NodeContainer nc_ = new NodeContainer();
        nc_.setLastToken("integer");
        nc_.setObject(bean_.getComposite(), conf_.getContext());
        nc_.getNodeInformation().setVarMethod("");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasInteger());
        //HtmlRequest.setObject(conf_, bean_, "composite.integer", "", 88, "", conf_.getStandards().getAliasInteger(), new List<Long>(4L));
        HtmlRequest.setObject(conf_, nc_, new IntStruct(88));
        assertEq(88, bean_.getComposite().getInteger());
        assertEq(0, bean_.getComposite().getStrings().size());
        assertNull(bean_.getChanging());
    }

//    @Test
//    public void setObject4Test() {
//        Composite composite_ = new Composite();
//        composite_.setInteger(21);
//        composite_.setStrings(new StringList());
//        BeanOne bean_ = new BeanOne();
//        bean_.getComposite().setInteger(8);
//        Configuration conf_ = newConfiguration();
//        setup(conf_);
//        conf_.addPage(new ImportingPage(true));
//        conf_.getLastPage().setGlobalArgumentObj(bean_.getComposite());
//        NodeContainer nc_ = new NodeContainer("composite");
//        nc_.setLastToken("composite");
//        nc_.setTypedField(bean_.getComposite());
//        nc_.setObject(bean_);
//        nc_.getNodeInformation().setVarMethod("");
//        nc_.getNodeInformation().setChanging("updateValue");
//        nc_.getNodeInformation().setInputClass(Composite.class.getName());
////        HtmlRequest.setObject(conf_, bean_, "composite", "", composite_, "updateValue", Composite.class.getName(), new List<Long>(4L));
//        HtmlRequest.setObject(conf_, nc_, composite_, new Longs(4L));
//        assertEq(21, bean_.getComposite().getInteger());
//        assertEq(1, bean_.getComposite().getStrings().size());
//        assertEq("21,0,[],null,null,null 8,0,[],null,null,null", bean_.getComposite().getStrings().first());
//        ValueChangeEvent changing_ = bean_.getChanging();
//        Composite new_ = (Composite) changing_.getNewValue();
//        assertEq(21, new_.getInteger());
//        Composite old_ = (Composite) changing_.getOldValue();
//        assertEq(8, old_.getInteger());
//        Longs list_ = changing_.getIndexes();
//        assertEq(1, list_.size());
//        assertEq(4, list_.first());
//    }

    @Test
    public void setObject5Test() {
        BeanFour bean_ = new BeanFour();
        Configuration conf_ = newConfiguration();
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        conf_.getLastPage().setGlobalArgumentObj(bean_);
        NodeContainer nc_ = new NodeContainer();
        nc_.setLastToken("invisibleField");
        nc_.setObject(bean_);
        nc_.getNodeInformation().setVarMethod("setInvisibleField");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasInteger());
//        HtmlRequest.setObject(conf_, bean_, "invisibleField", "setInvisibleField", 7, "", conf_.getStandards().getAliasInteger(),new List<Long>());
        HtmlRequest.setObject(conf_, nc_, new IntStruct(7));
        assertEq(7, bean_.getInvisibleField());
    }

    @Test
    public void setObject6Test() {
        BeanFour bean_ = new BeanFour();
        bean_.getComposite().setInteger(8);
        Configuration conf_ = newConfiguration();
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        NodeContainer nc_ = new NodeContainer();
        nc_.setLastToken("integer");
        nc_.setObject(bean_.getComposite(), conf_.getContext());
        nc_.getNodeInformation().setVarMethod("");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasInteger());
//        HtmlRequest.setObject(conf_, bean_, "getComposite().integer", "", 88, "", conf_.getStandards().getAliasInteger(),new List<Long>(4L));
        HtmlRequest.setObject(conf_, nc_, new IntStruct(88));
        assertEq(88, bean_.getComposite().getInteger());
    }

    @Test
    public void setObject7Test() {
        BeanFour bean_ = new BeanFour();
        Configuration conf_ = newConfiguration();
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        conf_.getLastPage().setGlobalArgumentObj(bean_);
        NodeContainer nc_ = new NodeContainer();
        nc_.setLastToken("invisibleField");
        nc_.setObject(bean_);
        nc_.getNodeInformation().setVarMethod("setInvisibleIntField");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasPrimInteger());
        HtmlRequest.setObject(conf_, nc_, new IntStruct(7));
        assertEq(7, bean_.getInvisibleField());
    }

    @Test
    public void setObject8Test() {
        BeanSeven bean_ = new BeanSeven();
        Configuration conf_ = newConfiguration();
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        NodeContainer nc_ = new NodeContainer();
        nc_.setIndex(0);
        nc_.setLastToken("");
        nc_.setTypedField(bean_.getStrings().first());
        nc_.setObject(bean_.getStrings(), conf_.getContext());
        nc_.getNodeInformation().setVarMethod("");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasString());
        HtmlRequest.setObject(conf_, nc_, new StringStruct("NEW"));
        assertEq("NEW", bean_.getStrings().first());
    }

    @Test
    public void setObject9Test() {
        BeanSeven bean_ = new BeanSeven();
        Configuration conf_ = newConfiguration();
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        NodeContainer nc_ = new NodeContainer();
        nc_.setIndex(0);
        nc_.setKey(true);
        nc_.setTypedField(bean_.getTree().getKey(0));
        nc_.setObject(bean_.getTree(), conf_.getContext());
        nc_.getNodeInformation().setVarMethod("");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasString());
        HtmlRequest.setObject(conf_, nc_, new StringStruct("keythree"));
        assertEq(2, bean_.getTree().size());
        assertEq(1, bean_.getTree().getVal("keythree"));
        assertEq(2, bean_.getTree().getVal("keytwo"));
    }

    @Test
    public void setObject10Test() {
        BeanSeven bean_ = new BeanSeven();
        Configuration conf_ = newConfiguration();
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        NodeContainer nc_ = new NodeContainer();
        nc_.setIndex(0);
        nc_.setTypedStruct(new IntStruct(bean_.getTree().getValue(0)));
        nc_.setObject(bean_.getTree(), conf_.getContext());
        nc_.getNodeInformation().setVarMethod("");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasInteger());
        HtmlRequest.setObject(conf_, nc_, new IntStruct(3));
        assertEq(2, bean_.getTree().size());
        assertEq(3, bean_.getTree().getVal("keyone"));
        assertEq(2, bean_.getTree().getVal("keytwo"));
    }


    @Test
    public void setObject11Test() {
        Configuration conf_ = newConfiguration();
        ArrayStruct arr_;
        String int_ = conf_.getStandards().getAliasPrimInteger();
        int_ = PrimitiveTypeUtil.getPrettyArrayType(int_);
        Struct[] inst_ = new Struct[2];
        inst_[0]=new IntStruct(1);
        inst_[1]=new IntStruct(3);
        arr_ = new ArrayStruct(inst_, int_);
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        NodeContainer nc_ = new NodeContainer();
        nc_.setIndex(0);
        nc_.setTypedStruct(new IntStruct(1));
        nc_.setStruct(arr_);
        nc_.getNodeInformation().setVarMethod("");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasPrimInteger());
        HtmlRequest.setObject(conf_, nc_, new IntStruct(5));
        assertEq(2, inst_.length);
        assertEq(5, ((NumberStruct) inst_[0]).intStruct());
        assertEq(3, ((NumberStruct) inst_[1]).intStruct());
    }

    @Test
    public void setObject12Test() {
        BeanSeven bean_ = new BeanSeven();
        bean_.getComposite().setStrings(new StringList());
        Configuration conf_ = newConfiguration();
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        conf_.getLastPage().setGlobalArgumentObj(bean_);
        NodeContainer nc_ = new NodeContainer();
        nc_.setIndex(0);
        nc_.setTypedField(bean_.getStrings().first());
        nc_.setObject(bean_.getStrings(), conf_.getContext());
        nc_.getNodeInformation().setVarMethod("");
        nc_.getNodeInformation().setChanging("updateValue");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasString());
        HtmlRequest.setObject(conf_, nc_, new StringStruct("NEW"));
        assertEq("NEW", bean_.getStrings().first());
        StringList values_ = bean_.getComposite().getStrings();
        assertEq(0, values_.size());
    }


    @Test
    public void setObject4FailTest() {
        Composite composite_ = new Composite();
        Configuration conf_ = newConfiguration();
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        conf_.getLastPage().setGlobalArgumentObj(composite_,COMPOSITE, conf_.getContext());
        NodeContainer nc_ = new NodeContainer();
        nc_.setLastToken("integer");
        nc_.setObject(composite_, conf_.getContext());
        nc_.getNodeInformation().setVarMethod("inexistantmethod");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasInteger());
        HtmlRequest.setObject(conf_, nc_, new IntStruct(7));
        assertNotNull(conf_.getContext().getException());
//        assertEq(7, composite_.getInteger());
    }

    @Test
    public void setObject5FailTest() {
        BeanFour bean_ = new BeanFour();
        bean_.setHello("world");
        Configuration conf_ = newConfiguration();
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        conf_.getLastPage().setGlobalArgumentObj(bean_);
        NodeContainer nc_ = new NodeContainer();
        nc_.setLastToken("hello");
        nc_.setObject(bean_);
        nc_.getNodeInformation().setVarMethod("setter");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasString());
//        HtmlRequest.setObject(conf_, bean_, "hello", "setter", "ex", "", conf_.getStandards().getAliasString(),new List<Long>());
        HtmlRequest.setObject(conf_, nc_, new StringStruct("ex"));
        assertNotNull(conf_.getContext().getException());
//        assertEq("ex",bean_.getHello());
    }

    @Test
    public void invokeMethodWithNumbers1Test() {
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().setInteger(8);
        Configuration conf_ = newConfiguration();
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        conf_.getLastPage().setGlobalArgumentObj(bean_);
        Argument arg_ = new Argument(new LongStruct(7));
        String return_ = ((StringStruct) HtmlRequest.invokeMethodWithNumbers(conf_, new BeanStruct(bean_), "invokeMethod", arg_)).getInstance();
        assertEq("returned value",return_);
        assertEq(1, bean_.getComposite().getStrings().size());
        assertEq("7", bean_.getComposite().getStrings().first());
    }

    @Test
    public void invokeMethodWithNumbers2Test() {
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().setInteger(8);
        Configuration conf_ = newConfiguration();
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        conf_.getLastPage().setGlobalArgumentObj(bean_);
        String return_ = ((StringStruct) HtmlRequest.invokeMethodWithNumbers(conf_, new BeanStruct(bean_), "composite.internMethod")).getInstance();
        assertEq("sample",return_);
    }

    @Test
    public void invokeMethodWithNumbers1FailTest() {
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().setInteger(8);
        Configuration conf_ = newConfiguration();
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        conf_.getLastPage().setGlobalArgumentObj(bean_);
        Argument arg_ = new Argument(new LongStruct(7));
        HtmlRequest.invokeMethodWithNumbers(conf_, new BeanStruct(bean_), "invokeMethods", arg_);
        assertNotNull(conf_.getContext().getException());
    }

    @Test
    public void invokeMethodWithNumbers2FailTest() {
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().setInteger(8);
        bean_.getComposite().setStrings(null);
        Configuration conf_ = newConfiguration();
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        conf_.getLastPage().setGlobalArgumentObj(bean_);
        Argument arg_ = new Argument(new LongStruct(7));
        HtmlRequest.invokeMethodWithNumbers(conf_, new BeanStruct(bean_), "invokeMethod", arg_);
        assertNotNull(conf_.getContext().getException());
    }
//
//    @Test(expected=BadAccessException.class)
//    public void invokeMethodWithNumbers3FailTest() {
//        BeanOne bean_ = new BeanOne();
//        bean_.getComposite().setInteger(8);
//        Configuration conf_ = newConfiguration();
//        setup(conf_);
//        conf_.addPage(new ImportingPage(true));
//        conf_.getLastPage().setGlobalArgumentObj(bean_);
//        HtmlRequest.invokeMethodWithNumbers(conf_, new BeanStruct(bean_), "composite.privateMethod");
//    }

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
        Struct bean_ = ElRenderUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        conf_.getBuiltBeans().put("bean_one",bean_);
        conf_.clearPages();
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
        FormatHtml.getRes(rendDocumentBlock_,conf_);
        addImportingPage(conf_);
        conf_.getLastPage().setGlobalArgumentStruct(bean_,conf_);
        Struct value_ = HtmlRequest.redirect(conf_, new Argument(bean_), 0);
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
        Struct bean_ = ElRenderUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        conf_.getBuiltBeans().put("bean_one",bean_);
        conf_.clearPages();
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
        FormatHtml.getRes(rendDocumentBlock_,conf_);
        addImportingPage(conf_);
        conf_.getLastPage().setGlobalArgumentStruct(bean_,conf_);
        Struct value_ = HtmlRequest.redirect(conf_, new Argument(bean_), 0);
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
        Struct bean_ = ElRenderUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        conf_.getBuiltBeans().put("bean_one",bean_);
        conf_.clearPages();
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
        FormatHtml.getRes(rendDocumentBlock_,conf_);
        addImportingPage(conf_);
        conf_.getLastPage().setGlobalArgumentStruct(bean_,conf_);
        Struct value_ = HtmlRequest.redirect(conf_, new Argument(bean_), 0);
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
        Struct bean_ = ElRenderUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        conf_.getBuiltBeans().put("bean_one",bean_);
        conf_.clearPages();
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
        FormatHtml.getRes(rendDocumentBlock_,conf_);
        addImportingPage(conf_);
        conf_.getLastPage().setGlobalArgumentStruct(bean_,conf_);
        Struct value_ = HtmlRequest.redirect(conf_, new Argument(bean_), 0);
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
        Struct bean_ = ElRenderUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        conf_.getBuiltBeans().put("bean_one",bean_);
        conf_.clearPages();
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
        FormatHtml.getRes(rendDocumentBlock_,conf_);
        addImportingPage(conf_);
        conf_.getLastPage().setGlobalArgumentStruct(bean_,conf_);
        Struct value_ = HtmlRequest.redirect(conf_, new Argument(bean_), 1);
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
        Struct bean_ = ElRenderUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        conf_.getBuiltBeans().put("bean_one",bean_);
        conf_.clearPages();
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
        FormatHtml.getRes(rendDocumentBlock_,conf_);
        addImportingPage(conf_);
        conf_.getLastPage().setGlobalArgumentStruct(bean_,conf_);
        Struct value_ = HtmlRequest.redirect(conf_, new Argument(bean_), 1);
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
        Struct bean_ = ElRenderUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        conf_.getBuiltBeans().put("bean_one",bean_);
        conf_.clearPages();
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
        FormatHtml.getRes(rendDocumentBlock_,conf_);
        addImportingPage(conf_);
        conf_.getLastPage().setGlobalArgumentStruct(bean_,conf_);
        Struct value_ = HtmlRequest.redirect(conf_, new Argument(bean_), 1);
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
        Struct bean_ = ElRenderUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        conf_.getBuiltBeans().put("bean_one",bean_);
        conf_.clearPages();
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
        FormatHtml.getRes(rendDocumentBlock_,conf_);
        addImportingPage(conf_);
        conf_.getLastPage().setGlobalArgumentStruct(bean_,conf_);
        Struct value_ = HtmlRequest.redirect(conf_, new Argument(bean_), 1);
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
        Struct bean_ = ElRenderUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        conf_.getBuiltBeans().put("bean_one",bean_);
        conf_.clearPages();
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
        FormatHtml.getRes(rendDocumentBlock_,conf_);
        addImportingPage(conf_);
        conf_.getLastPage().setGlobalArgumentStruct(bean_,conf_);
        HtmlRequest.redirect(conf_, new Argument(bean_), 0);
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
        Struct bean_ = ElRenderUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        conf_.getBuiltBeans().put("bean_one",bean_);
        conf_.clearPages();
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
        FormatHtml.getRes(rendDocumentBlock_,conf_);
        addImportingPage(conf_);
        conf_.getLastPage().setGlobalArgumentStruct(bean_,conf_);
        HtmlRequest.redirect(conf_, new Argument(bean_), 0);
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
        Struct bean_ = ElRenderUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        conf_.getBuiltBeans().put("bean_one",bean_);
        conf_.clearPages();
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
        FormatHtml.getRes(rendDocumentBlock_,conf_);
        addImportingPage(conf_);
        conf_.getLastPage().setGlobalArgumentStruct(bean_,conf_);
        HtmlRequest.invokeMethodWithNumbersBis(conf_, "");
        assertNotNull(conf_.getException());
    }
    @Test
    public void setRendObject1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_three\"><body>HEAD<form action=\"\" c:command=\"page1.html\" name=\"myform\"><c:for var=\"n\" list=\"numbers\"><input type=\"radio\" name=\"index\" c:varValue=\"n;\"/></c:for><c:for var=\"n\" list=\"numbersTwo\"><input type=\"radio\" name=\"indexTwo\" c:varValue=\"n;\"/></c:for><input type=\"submit\" value=\"OK\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        BeanThree bean_ = new BeanThree();

        bean_.setScope("session");
        Configuration conf_ = contextElSec();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_three", bean_);
        conf_.getBuiltBeans().put("bean_three",new BeanStruct(bean_));
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page1.html");
        conf_.setValidators(new StringMap<Validator>());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        conf_.setNavigation(new StringMap<StringMap<String>>());
        bean_.getNumbers().add(2);
        bean_.getNumbers().add(4);
        bean_.getNumbers().add(6);
        bean_.getNumbersTwo().add(2);
        bean_.getNumbersTwo().add(4);
        bean_.getNumbersTwo().add(6);

        bean_.setIndex(4);
        bean_.setIndexTwo(6);
        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        FormatHtml.getRes(rendDocumentBlock_,conf_);
        addImportingPage(conf_);
        HtmlRequest.setRendObject(conf_,conf_.getContainersMap().firstValue().getValue(0),new IntStruct(2));
        assertEq(2,bean_.getIndex());
        assertEq(6,bean_.getIndexTwo());
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
//        file_.append(" $public Dto second:");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  first=$new Dto(4):");
//        file_.append("  second=$new Dto(6):");
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
        Struct bean_ = ElRenderUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        conf_.getBuiltBeans().put("bean_one",bean_);
        conf_.clearPages();
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
        FormatHtml.getRes(rendDocumentBlock_, conf_);
        addImportingPage(conf_);
        NodeContainer nCont_ = conf_.getContainersMap().firstValue().getValue(0);
        HtmlRequest.setRendObject(conf_, nCont_,new IntStruct(2));
        Struct dto_ = ((FieldableStruct) bean_).getStruct(new ClassField("pkg.BeanOne", "first"));
        assertEq(2, ((NumberStruct)((FieldableStruct)dto_).getStruct(nCont_.getIdField())).intStruct());
    }
    @Test
    public void formatErrorMessage1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html bean=\"bean_one\"><body>HEAD<a href=\"\"/><c:import page=\"page1.html\"/></body></html>";
        String htmlTwo_ = "<html bean=\"bean_two\"><body> NEXT<form action=\"DELETE\" command=\"go\">{typedInt}</form><form action=\"go\">{typedInt}</form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setTypedString("TITLE");
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        setup(conf_);
        String render_ = HtmlRequest.formatErrorMessage(conf_, "msg_example,three", false, locale_, files_, "", new StringList("EX"));
        assertEq("desc &lt;EX&gt;", render_);
    }

    @Test
    public void formatErrorMessage2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html bean=\"bean_one\"><body>HEAD<a href=\"\"/><c:import page=\"page1.html\"/></body></html>";
        String htmlTwo_ = "<html bean=\"bean_two\"><body> NEXT<form action=\"DELETE\" command=\"go\">{typedInt}</form><form action=\"go\">{typedInt}</form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setTypedString("TITLE");
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        setup(conf_);
        String render_ = HtmlRequest.formatErrorMessage(conf_, "sample/file,three", false, locale_, files_, "", new StringList("EX"));
        assertEq("desc &lt;EX&gt;", render_);
    }

    private static void setup(Configuration _conf) {
        _conf.setSepPrefix("c");
        _conf.setupValiatorsTranslators("en");
    }

    private static Configuration newConfiguration() {
        Configuration conf_ = EquallableExUtil.newConfiguration();
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl context_ = InitializationLgNames.buildStdOne(opt_);
        conf_.setContext(context_);
        BeanLgNames standards_ = (BeanLgNames) context_.getStandards();
        conf_.setStandards(standards_);
        standards_.buildIterables(conf_);
        return conf_;
    }
}
