package code.formathtml;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import code.bean.Bean;
import code.bean.translator.Translator;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.exceptions.BadConditionExpressionException;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.classes.BeanOne;
import code.formathtml.classes.Composite;
import code.formathtml.classes.EnumNumber;
import code.formathtml.classes.EnumNumbers;
import code.formathtml.classes.MyTranslator;
import code.formathtml.classes.Rate;
import code.formathtml.classes.SimpleMathFactory;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.BeanStruct;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.sml.NodeList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.StringList;
import code.util.StringMap;

@SuppressWarnings("static-method")
public class ExtractConditionTest {


    @Test
    public void evaluateCondition1Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:if condition=\"composite.strings.isEmpty()\">EMPTY</c:if></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        addImportingPage(conf_, false);
        addBean(conf_, bean_);
        ImportingPage ip_ = conf_.getLastPage();
        Document doc_ = DocumentBuilder.parseSax(html_);
        Element elt_ = getElement(doc_, "c:if", 0);
//        conf_.setHtml(html_);
//        conf_.setDocument(doc_);
        assertTrue(!ExtractCondition.evaluateCondition(elt_, conf_, ip_));
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
//        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body/></html>", render_);
    }

    @Test
    public void evaluateCondition2Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:if condition=\"composite.strings.isEmpty()\">EMPTY</c:if></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().setInteger(5);
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        addImportingPage(conf_, false);
        addBean(conf_, bean_);
        ImportingPage ip_ = conf_.getLastPage();
        Document doc_ = DocumentBuilder.parseSax(html_);
        Element elt_ = getElement(doc_, "c:if", 0);
        assertTrue(ExtractCondition.evaluateCondition(elt_, conf_, ip_));
//        conf_.setHtml(html_);
//        conf_.setDocument(doc_);
//        String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
//        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>EMPTY</c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><c_tmp>EMPTY</c_tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>EMPTY</body></html>", render_);
    }

    @Test
    public void evaluateCondition3Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:if condition=\"!composite.strings.isEmpty()\">NOT EMPTY</c:if></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        addImportingPage(conf_, false);
        addBean(conf_, bean_);
        ImportingPage ip_ = conf_.getLastPage();
        Document doc_ = DocumentBuilder.parseSax(html_);
        Element elt_ = getElement(doc_, "c:if", 0);
        assertTrue(ExtractCondition.evaluateCondition(elt_, conf_, ip_));
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
//        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>NOT EMPTY</c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><c_tmp>NOT EMPTY</c_tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>NOT EMPTY</body></html>", render_);
    }

    @Test
    public void evaluateCondition4Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:if condition=\"!composite.strings.isEmpty()\">NOT EMPTY</c:if></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().setInteger(5);
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        addImportingPage(conf_, false);
        addBean(conf_, bean_);
        ImportingPage ip_ = conf_.getLastPage();
        Document doc_ = DocumentBuilder.parseSax(html_);
        Element elt_ = getElement(doc_, "c:if", 0);
        assertTrue(!ExtractCondition.evaluateCondition(elt_, conf_, ip_));
//        String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
//        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body/></html>", render_);
    }

    @Test
    public void evaluateCondition5Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:if condition=\"composite.strings.isEmpty()\">EMPTY</c:if><c:elseif condition=\"hasMoreThanOne()\">NOT EMPTY</c:elseif></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        addImportingPage(conf_, false);
        addBean(conf_, bean_);
        ImportingPage ip_ = conf_.getLastPage();
        Document doc_ = DocumentBuilder.parseSax(html_);
        Element elt_ = getElement(doc_, "c:if", 0);
        assertTrue(!ExtractCondition.evaluateCondition(elt_, conf_, ip_));
        elt_ = getElement(doc_, "c:elseif", 0);
        assertTrue(ExtractCondition.evaluateCondition(elt_, conf_, ip_));
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
//        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>NOT EMPTY</body></html>", render_);
    }

    @Test
    public void evaluateCondition6Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:if condition=\"composite.strings.isEmpty()\">EMPTY</c:if><c:elseif condition=\"hasMoreThanOne()\">NOT EMPTY</c:elseif><br/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().setInteger(5);
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        addImportingPage(conf_, false);
        addBean(conf_, bean_);
        ImportingPage ip_ = conf_.getLastPage();
        Document doc_ = DocumentBuilder.parseSax(html_);
        Element elt_ = getElement(doc_, "c:if", 0);
        assertTrue(!ExtractCondition.evaluateCondition(elt_, conf_, ip_));
        elt_ = getElement(doc_, "c:elseif", 0);
        assertTrue(!ExtractCondition.evaluateCondition(elt_, conf_, ip_));
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
//        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><br/></body></html>", render_);
    }

    @Test
    public void evaluateCondition7Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:if mathexpr=\"`composite.strings.isEmpty()`\">EMPTY</c:if><c:else>NOT EMPTY</c:else></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getComposites().get(0).setStrings(new StringList("F"));
        bean_.getComposites().get(1).setStrings(new StringList("S"));
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        conf_.setMathFactory(new SimpleMathFactory());
        addImportingPage(conf_, false);
        addBean(conf_, bean_);
        ImportingPage ip_ = conf_.getLastPage();
        Document doc_ = DocumentBuilder.parseSax(html_);
        Element elt_ = getElement(doc_, "c:if", 0);
        assertTrue(!ExtractCondition.evaluateCondition(elt_, conf_, ip_));
//        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>NOT EMPTY</body></html>", render_);
    }
    @Test(expected=BadConditionExpressionException.class)
    public void evaluateCondition1FailTest() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:if condition=\"!!\">BAD CODE</c:if></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        addImportingPage(conf_, false);
        addBean(conf_, bean_);
        ImportingPage ip_ = conf_.getLastPage();
        Document doc_ = DocumentBuilder.parseSax(html_);
        Element elt_ = getElement(doc_, "c:if", 0);
        ExtractCondition.evaluateCondition(elt_, conf_, ip_);
//        FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        //FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
    }

    @Test
    public void evaluateGenericCondition1Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:if condition=\"composite.strings.isEmpty()\">EMPTY</c:if></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        addImportingPage(conf_, false);
        addBean(conf_, bean_);
        ImportingPage ip_ = conf_.getLastPage();
        Element elt_ = getElement(doc_, "c:if", 0);
//        conf_.setHtml(html_);
        assertTrue(!ExtractCondition.evaluateGenericCondition(elt_, conf_, ip_));
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
//        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body/></html>", render_);
    }

    @Test
    public void evaluateGenericCondition2Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:if condition=\"composite.strings.isEmpty()\">EMPTY</c:if><c:elseif condition=\"hasMoreThanOne()\">NOT EMPTY</c:elseif><br/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().setInteger(5);
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        addImportingPage(conf_, false);
        addBean(conf_, bean_);
        ImportingPage ip_ = conf_.getLastPage();
        Element elt_ = getElement(doc_, "c:if", 0);
        assertTrue(!ExtractCondition.evaluateGenericCondition(elt_, conf_, ip_));
        elt_ = getElement(doc_, "c:elseif", 0);
        assertTrue(!ExtractCondition.evaluateGenericCondition(elt_, conf_, ip_));
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
//        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><br/></body></html>", render_);
    }

    @Test
    public void evaluateGenericCondition3Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:ifdefparam defined=\"p\">EMPTY</c:ifdefparam><br/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().setInteger(5);
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        addImportingPage(conf_, false);
        addBean(conf_, bean_);
        ImportingPage ip_ = conf_.getLastPage();
        Element elt_ = getElement(doc_, "c:ifdefparam", 0);
        assertTrue(!ExtractCondition.evaluateGenericCondition(elt_, conf_, ip_));
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
//        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><br/></body></html>", render_);
    }

    @Test
    public void evaluateGenericCondition4Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:ifdefparam defined=\"p\">EMPTY</c:ifdefparam><c:elseifdefparam defined=\"e\">EMPTY</c:elseifdefparam><br/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().setInteger(5);
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        addImportingPage(conf_, false);
        addBean(conf_, bean_);
        ImportingPage ip_ = conf_.getLastPage();
        LocalVariable localVariable_ = new LocalVariable();
        ip_.getParameters().put("e", localVariable_);
        Element elt_ = getElement(doc_, "c:ifdefparam", 0);
        assertTrue(!ExtractCondition.evaluateGenericCondition(elt_, conf_, ip_));
        elt_ = getElement(doc_, "c:elseifdefparam", 0);
        assertTrue(ExtractCondition.evaluateGenericCondition(elt_, conf_, ip_));
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
//        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><br/></body></html>", render_);
    }


    @Test
    public void evaluateGenericCondition5Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:ifdefretval defined=\"p\">EMPTY</c:ifdefretval><br/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().setInteger(5);
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        addImportingPage(conf_, false);
        addBean(conf_, bean_);
        ImportingPage ip_ = conf_.getLastPage();
        Element elt_ = getElement(doc_, "c:ifdefretval", 0);
        assertTrue(!ExtractCondition.evaluateGenericCondition(elt_, conf_, ip_));
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
//        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><br/></body></html>", render_);
    }

    @Test
    public void evaluateGenericCondition6Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:ifdefretval defined=\"p\">EMPTY</c:ifdefretval><c:elseifdefretval defined=\"e\">EMPTY</c:elseifdefretval><br/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().setInteger(5);
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        addImportingPage(conf_, false);
        addBean(conf_, bean_);
        ImportingPage ip_ = conf_.getLastPage();
        LocalVariable localVariable_ = new LocalVariable();
        ip_.getReturnedValues().put("e", localVariable_);
        Element elt_ = getElement(doc_, "c:ifdefretval", 0);
        assertTrue(!ExtractCondition.evaluateGenericCondition(elt_, conf_, ip_));
        elt_ = getElement(doc_, "c:elseifdefretval", 0);
        assertTrue(ExtractCondition.evaluateGenericCondition(elt_, conf_, ip_));
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
//        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><br/></body></html>", render_);
    }

    @Test(expected=BadConditionExpressionException.class)
    public void evaluateGenericCondition1FailTest() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:ifdefretval>EMPTY</c:ifdefretval><br/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().setInteger(5);
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        addImportingPage(conf_, false);
        addBean(conf_, bean_);
        ImportingPage ip_ = conf_.getLastPage();
        LocalVariable localVariable_ = new LocalVariable();
        ip_.getReturnedValues().put("e", localVariable_);
        Element elt_ = getElement(doc_, "c:ifdefretval", 0);
        ExtractCondition.evaluateGenericCondition(elt_, conf_, ip_);
    }

    @Ignore
    @Test
    public void print() {
        ContextEl context_ = new ContextEl();
        context_.setClasses(new Classes());
        BeanLgNames lgNames_ = InitializationLgNames.initStandards(context_);
        StringBuilder method_ = new StringBuilder("    public ResultErrorStd getOtherResult(ContextEl _cont, ConstructorId _method, Object... _args) {\n");
        method_.append("        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();\n");
        method_.append("        ResultErrorStd res_ = new ResultErrorStd();\n");
        method_.append("        Object instance_ =  _instance.getInstance();\n");
        method_.append("        String className_ = _method.getClassName();\n");
        for (EntryCust<String, StandardType> t: lgNames_.getStandards().entryList()) {
            if (t.getKey().startsWith("java.")) {
                continue;
            }
            if (t.getValue().getConstructors().isEmpty()) {
                method_.append("        if (StringList.quickEq(className_,\""+t.getKey()+"\")) {\n");
                method_.append("            res_.setResult(new StdStruct(new "+t.getKey().substring(t.getKey().lastIndexOf('.')+1)+"(");
                method_.append("),\""+t.getKey()+"\"));\n");
                method_.append("            return res_;\n");
                method_.append("        }\n");
                continue;
            }
            if (t.getValue().getConstructors().size() > 1) {
                for (StandardConstructor f: t.getValue().getConstructors()) {
                    System.err.println(f.getSignature());
                }
                continue;
            }
            for (StandardConstructor f: t.getValue().getConstructors()) {
                method_.append("        if (StringList.quickEq(className_,\""+t.getKey()+"\")) {\n");
                method_.append("                res_.setResult(new StdStruct(new "+t.getKey().substring(t.getKey().lastIndexOf('.')+1)+"(");
                StringList params_ = new StringList();
                int i_ = 0;
                for (String p: f.getParametersTypes()) {
                    String p_= "";
                    if (StringList.quickEq(p, lgNames_.getAliasPrimBoolean()) || StringList.quickEq(p, lgNames_.getAliasBoolean())) {
                        p_ += "(Boolean)";
                    } else if (StringList.quickEq(p, lgNames_.getAliasDouble()) || StringList.quickEq(p, lgNames_.getAliasPrimDouble())){
                        p_ +=("(Double)");
                    } else if (StringList.quickEq(p, lgNames_.getAliasFloat()) || StringList.quickEq(p, lgNames_.getAliasPrimFloat())){
                        p_ +=("(Float)");
                    } else if (StringList.quickEq(p, lgNames_.getAliasLong()) || StringList.quickEq(p, lgNames_.getAliasPrimLong())){
                        p_ +=("(Long)");
                    } else if (StringList.quickEq(p, lgNames_.getAliasInteger()) || StringList.quickEq(p, lgNames_.getAliasPrimInteger())){
                        p_ +=("(Integer)");
                    } else if (StringList.quickEq(p, lgNames_.getAliasCharacter()) || StringList.quickEq(p, lgNames_.getAliasPrimChar())){
                        p_ +=("(Character)");
                    } else if (StringList.quickEq(p, lgNames_.getAliasShort()) || StringList.quickEq(p, lgNames_.getAliasPrimShort())){
                        p_ +=("(Short)");
                    } else if (StringList.quickEq(p, lgNames_.getAliasByte()) || StringList.quickEq(p, lgNames_.getAliasPrimByte())){
                        p_ +=("(Byte)");
                    } else if (StringList.quickEq(p, lgNames_.getAliasString())){
                        p_ +=("(String)");
                    } else if (StringList.quickEq(p, StringList.class.getName())){
                        p_ +=("(StringList)");
                    } else if (StringList.quickEq(p, EnumNumber.class.getName())){
                        p_ +=("(EnumNumber)");
                    } else if (StringList.quickEq(p, EnumNumbers.class.getName())){
                        p_ +=("(EnumNumbers)");
                    } else if (StringList.quickEq(p, Composite.class.getName())){
                        p_ +=("(Composite)");
                    } else if (StringList.quickEq(p, Rate.class.getName())){
                        p_ +=("(Rate)");
                    } else if (StringList.quickEq(p, "[$int")){
                        p_ +=("(int[])");
                    }
                    p_ += "_args["+i_+"]";
                    params_.add(p_);
                    i_++;
                }
                method_.append(params_.join(","));
                method_.append(");\n");
                method_.append("            return res_;\n");
                method_.append("        }\n");
            }
//            method_.append("        if (StringList.quickEq(className_,\""+t.getKey()+"\")) {\n");
//            String st_ = t.getKey().substring(t.getKey().lastIndexOf('.')+1);
//            method_.append("            "+st_+" i_ = ("+st_+")instance_;\n");
//            for (EntryCust<MethodId, StandardMethod> f: t.getValue().getMethods().entryList()) {
//                int count_ = 0;
//                for (EntryCust<MethodId, EqList<ClassMethodId>> e: t.getValue().getAllOverridingMethods().entryList()) {
//                    if (StringList.quickEq(e.getKey().getName(), f.getKey().getName())) {
//                        count_++;
//                    }
//                }
//                if (count_ > 1) {
//                    System.err.println(f.getKey().getSignature());
//                    continue;
//                }
//                method_.append("            if (StringList.quickEq(fieldName_,\""+f.getKey().getName()+"\")) {\n");
//                String fType_ = f.getValue().getReturnType(lgNames_);
//                if (StringList.quickEq(fType_, lgNames_.getAliasVoid())) {
//                    method_.append("                i_."+f.getValue().getName()+"(");
//                    StringList params_ = new StringList();
//                    int i_ = 0;
//                    for (String p: f.getKey().getParametersTypes()) {
//                        String p_= "";
//                        if (StringList.quickEq(p, lgNames_.getAliasPrimBoolean()) || StringList.quickEq(p, lgNames_.getAliasBoolean())) {
//                            p_ += "(Boolean)";
//                        } else if (StringList.quickEq(p, lgNames_.getAliasDouble()) || StringList.quickEq(p, lgNames_.getAliasPrimDouble())){
//                            p_ +=("(Double)");
//                        } else if (StringList.quickEq(p, lgNames_.getAliasFloat()) || StringList.quickEq(p, lgNames_.getAliasPrimFloat())){
//                            p_ +=("(Float)");
//                        } else if (StringList.quickEq(p, lgNames_.getAliasLong()) || StringList.quickEq(p, lgNames_.getAliasPrimLong())){
//                            p_ +=("(Long)");
//                        } else if (StringList.quickEq(p, lgNames_.getAliasInteger()) || StringList.quickEq(p, lgNames_.getAliasPrimInteger())){
//                            p_ +=("(Integer)");
//                        } else if (StringList.quickEq(p, lgNames_.getAliasCharacter()) || StringList.quickEq(p, lgNames_.getAliasPrimChar())){
//                            p_ +=("(Character)");
//                        } else if (StringList.quickEq(p, lgNames_.getAliasShort()) || StringList.quickEq(p, lgNames_.getAliasPrimShort())){
//                            p_ +=("(Short)");
//                        } else if (StringList.quickEq(p, lgNames_.getAliasByte()) || StringList.quickEq(p, lgNames_.getAliasPrimByte())){
//                            p_ +=("(Byte)");
//                        } else if (StringList.quickEq(p, lgNames_.getAliasString())){
//                            p_ +=("(String)");
//                        } else if (StringList.quickEq(p, StringList.class.getName())){
//                            p_ +=("(StringList)");
//                        } else if (StringList.quickEq(p, EnumNumber.class.getName())){
//                            p_ +=("(EnumNumber)");
//                        } else if (StringList.quickEq(p, EnumNumbers.class.getName())){
//                            p_ +=("(EnumNumbers)");
//                        } else if (StringList.quickEq(p, Composite.class.getName())){
//                            p_ +=("(Composite)");
//                        } else if (StringList.quickEq(p, Rate.class.getName())){
//                            p_ +=("(Rate)");
//                        } else if (StringList.quickEq(p, "[$int")){
//                            p_ +=("(int[])");
//                        }
//                        p_ += "_args["+i_+"]";
//                        params_.add(p_);
//                        i_++;
//                    }
//                    method_.append(params_.join(","));
//                    method_.append(");\n");
//                    method_.append("                res_.setResult(NullStruct.NULL_VALUE);\n");
//                    method_.append("                return res_;\n");
//                    method_.append("            }\n");
//                    continue;
//                }
//                method_.append("                res_.setResult(");
//                boolean end_ = true;
//                if (StringList.quickEq(fType_, lgNames_.getAliasPrimBoolean()) || StringList.quickEq(fType_, lgNames_.getAliasBoolean())) {
//                    method_.append("new BooleanStruct((Boolean)");
//                } else if (StringList.quickEq(fType_, lgNames_.getAliasDouble()) || StringList.quickEq(fType_, lgNames_.getAliasPrimDouble())){
//                    method_.append("new DoubleStruct((Double)");
//                } else if (StringList.quickEq(fType_, lgNames_.getAliasFloat()) || StringList.quickEq(fType_, lgNames_.getAliasPrimFloat())){
//                    method_.append("new FloatStruct((Float)");
//                } else if (StringList.quickEq(fType_, lgNames_.getAliasLong()) || StringList.quickEq(fType_, lgNames_.getAliasPrimLong())){
//                    method_.append("new LongStruct((Long)");
//                } else if (StringList.quickEq(fType_, lgNames_.getAliasInteger()) || StringList.quickEq(fType_, lgNames_.getAliasPrimInteger())){
//                    method_.append("new IntStruct((Integer)");
//                } else if (StringList.quickEq(fType_, lgNames_.getAliasCharacter()) || StringList.quickEq(fType_, lgNames_.getAliasPrimChar())){
//                    method_.append("new CharStruct((Character)");
//                } else if (StringList.quickEq(fType_, lgNames_.getAliasShort()) || StringList.quickEq(fType_, lgNames_.getAliasPrimShort())){
//                    method_.append("new ShortStruct((Short)");
//                } else if (StringList.quickEq(fType_, lgNames_.getAliasByte()) || StringList.quickEq(fType_, lgNames_.getAliasPrimByte())){
//                    method_.append("new ByteStruct((Byte)");
//                } else if (StringList.quickEq(fType_, lgNames_.getAliasString())){
//                    method_.append("new StringStruct((String)");
//                } else {
//                    end_ = false;
//                    method_.append("new StdStruct(");
//                    //get_.append("                res_.setResult(new StdStruct(i_.set"+camel(f.getValue().getFieldName())+"(),\""+fType_+"\"));\n");
//                }
//                method_.append("i_."+f.getValue().getName()+"(");
//                StringList params_ = new StringList();
//                int i_ = 0;
//                for (String p: f.getKey().getParametersTypes()) {
//                    String p_= "";
//                    if (StringList.quickEq(p, lgNames_.getAliasPrimBoolean()) || StringList.quickEq(p, lgNames_.getAliasBoolean())) {
//                        p_ += "(Boolean)";
//                    } else if (StringList.quickEq(p, lgNames_.getAliasDouble()) || StringList.quickEq(p, lgNames_.getAliasPrimDouble())){
//                        p_ +=("(Double)");
//                    } else if (StringList.quickEq(p, lgNames_.getAliasFloat()) || StringList.quickEq(p, lgNames_.getAliasPrimFloat())){
//                        p_ +=("(Float)");
//                    } else if (StringList.quickEq(p, lgNames_.getAliasLong()) || StringList.quickEq(p, lgNames_.getAliasPrimLong())){
//                        p_ +=("(Long)");
//                    } else if (StringList.quickEq(p, lgNames_.getAliasInteger()) || StringList.quickEq(p, lgNames_.getAliasPrimInteger())){
//                        p_ +=("(Integer)");
//                    } else if (StringList.quickEq(p, lgNames_.getAliasCharacter()) || StringList.quickEq(p, lgNames_.getAliasPrimChar())){
//                        p_ +=("(Character)");
//                    } else if (StringList.quickEq(p, lgNames_.getAliasShort()) || StringList.quickEq(p, lgNames_.getAliasPrimShort())){
//                        p_ +=("(Short)");
//                    } else if (StringList.quickEq(p, lgNames_.getAliasByte()) || StringList.quickEq(p, lgNames_.getAliasPrimByte())){
//                        p_ +=("(Byte)");
//                    } else if (StringList.quickEq(p, lgNames_.getAliasString())){
//                        p_ +=("(String)");
//                    } else if (StringList.quickEq(p, StringList.class.getName())){
//                        p_ +=("(StringList)");
//                    } else if (StringList.quickEq(p, EnumNumber.class.getName())){
//                        p_ +=("(EnumNumber)");
//                    } else if (StringList.quickEq(p, EnumNumbers.class.getName())){
//                        p_ +=("(EnumNumbers)");
//                    } else if (StringList.quickEq(p, Composite.class.getName())){
//                        p_ +=("(Composite)");
//                    } else if (StringList.quickEq(p, Rate.class.getName())){
//                        p_ +=("(Rate)");
//                    } else if (StringList.quickEq(p, "[$int")){
//                        p_ +=("(int[])");
//                    }
//                    p_ += "_args["+i_+"]";
//                    params_.add(p_);
//                    i_++;
//                }
//                method_.append(params_.join(","));
//                if (end_) {
//                    method_.append(")));\n");
//                } else {
//                    method_.append("),\""+fType_+"\"));\n");
//                }
//                method_.append("                return res_;\n");
//                method_.append("            }\n");
//            }
            method_.append("        }\n");
        }
        method_.append("        return res_;\n");
        method_.append("    }\n");
        System.out.println(method_);
    }
    private static String camel(String _string) {
        char f_ = _string.charAt(0);
        return Character.toUpperCase(f_)+_string.substring(1);
    }
    private static Configuration newConfiguration() {
        Configuration conf_ = new Configuration();
        ContextEl context_ = new ContextEl();
        context_.setClasses(new Classes());
        conf_.setStandards(InitializationLgNames.initStandards(context_));
        return conf_;
    }

    private static Element getElement(Document _doc, String _tag, int _index) {
        NodeList elts_ = _doc.getElementsByTagName(_tag);
        return (Element) elts_.item(_index);
    }

    private static void addImportingPage(Configuration _conf, boolean _rendering) {
        _conf.setSepPrefix("c");
        _conf.setupValiatorsTranslators("LOCALE");
        _conf.addPage(new ImportingPage(_rendering));
        _conf.getLastPage().setPrefix(_conf.getPrefix());
    }

    private static void addBean(Configuration _conf, Bean _bean) {
//        LoopVariable lv_ = new LoopVariable();
//        lv_.setElement(_bean);
//        lv_.setExtendedExpression("");
//        _conf.getLastPage().getVars().put("", lv_);
        _conf.getLastPage().setGlobalClass(_bean.getClassName());
        _conf.getLastPage().setGlobalArgumentStruct(new BeanStruct(_bean), _conf);
    }
}
