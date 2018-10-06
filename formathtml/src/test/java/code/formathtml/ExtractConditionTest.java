package code.formathtml;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.bean.Bean;
import code.bean.translator.Translator;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.VariableSuffix;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.classes.BeanOne;
import code.formathtml.classes.MyTranslator;
import code.formathtml.classes.SimpleMathFactory;
import code.formathtml.util.BeanStruct;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.sml.ElementList;
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
    @Test
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
        assertNotNull(conf_.getContext().getException());
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

    @Test
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
        assertNotNull(conf_.getContext().getException());
    }

    private static Configuration newConfiguration() {
        Configuration conf_ = new Configuration();
        ContextEl context_ = new ContextEl();
        context_.getOptions().setSuffixVar(VariableSuffix.DISTINCT);
        context_.getOptions().setMultipleAffectations(false);
        conf_.setStandards(InitializationLgNames.initStandards(context_));
        conf_.setContext(context_);
        context_.initError();
        return conf_;
    }

    private static Element getElement(Document _doc, String _tag, int _index) {
        ElementList elts_ = _doc.getElementsByTagName(_tag);
        return elts_.item(_index);
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
