package code.formathtml;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.bean.Bean;
import code.bean.translator.Translator;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.VariableSuffix;
import code.expressionlanguage.options.Options;
import code.formathtml.classes.BeanOne;
import code.formathtml.classes.BeanTwo;
import code.formathtml.classes.MyTranslator;
import code.formathtml.util.BeanLgNames;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.StringMap;
import code.util.StringMapObject;

@SuppressWarnings("static-method")
public class FormatHtmlTryCatchTest {

    @Test
    public void processHtml132Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try>{1+1}</c:try><c:catch className='code.expressionlanguage.exceptions.DivideZeroException' var='e'>Divide Zero</c:catch></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>2</body></html>", render_);
    }
    @Test
    public void processHtml133Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try>{1/0}</c:try><c:catch className='code.expressionlanguage.exceptions.DivideZeroException' var='e'>Divide Zero</c:catch></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>Divide Zero</body></html>", render_);
    }
    @Test
    public void processHtml134Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><div><c:try>{1/0}</c:try><c:catch className='code.expressionlanguage.exceptions.DivideZeroException' var='e'><span>Divide Zero</span></c:catch></div></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><div><span>Divide Zero</span></div></body></html>", render_);
    }
    @Test
    public void processHtml135Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try>{1/0}</c:try><c:catch className='code.expressionlanguage.exceptions.DivideZeroException' var='e'>Divide Zero</c:catch><c:catch className='java.lang.Exception' var='e'>RTE</c:catch></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>Divide Zero</body></html>", render_);
    }
    @Test
    public void processHtml136Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
//        String html_ = "<html xmlns:c='javahtml'><body><c:try>{class(\"toto\")}</c:try><c:catch className='code.expressionlanguage.exceptions.DivideZeroException' var='e'>Divide Zero</c:catch><c:catch className='java.lang.Exception' var='e'>RTE</c:catch></body></html>";
        String html_ = "<html xmlns:c='javahtml'><body><c:try>{$(java.lang.String)1i}</c:try><c:catch className='code.expressionlanguage.exceptions.DivideZeroException' var='e'>Divide Zero</c:catch><c:catch className='java.lang.Exception' var='e'>RTE</c:catch></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>RTE</body></html>", render_);
    }
    @Test
    public void processHtml137Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
//        String html_ = "<html xmlns:c='javahtml'><body><c:for var='i' from='0' to='2' step='1'><c:try>{class(\"toto\")}</c:try><c:catch className='code.expressionlanguage.exceptions.DivideZeroException' var='e'>Divide Zero</c:catch><c:catch className='java.lang.Exception' var='e'>RTE</c:catch></c:for></body></html>";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var='i' from='0' to='2' step='1'><c:try>{$(java.lang.String)1i}</c:try><c:catch className='code.expressionlanguage.exceptions.DivideZeroException' var='e'>Divide Zero</c:catch><c:catch className='java.lang.Exception' var='e'>RTE</c:catch></c:for></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>RTERTE</body></html>", render_);
    }
    @Test
    public void processHtml138Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var='i' from='0' to='2' step='1'><c:try>{1/i;}</c:try><c:catch className='code.expressionlanguage.exceptions.DivideZeroException' var='e'>Divide Zero</c:catch><c:catch className='java.lang.Exception' var='e'>RTE</c:catch></c:for></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>Divide Zero1</body></html>", render_);
    }
    @Test
    public void processHtml139Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:for var='i' from='0' to='2' step='1'>{1/i;}</c:for></c:try><c:catch className='code.expressionlanguage.exceptions.DivideZeroException' var='e'>Divide Zero</c:catch><c:catch className='java.lang.Exception' var='e'>RTE</c:catch></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>Divide Zero</body></html>", render_);
    }
    @Test
    public void processHtml140Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:for var='i' from='0' to='2' step='1'>{1/i;}</c:for></c:try><c:catch className='code.expressionlanguage.exceptions.DivideZeroException' var='e'><c:for var='i' from='0' to='2' step='1'>Divide Zero{i;}</c:for></c:catch><c:catch className='java.lang.Exception' var='e'>RTE</c:catch></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>Divide Zero0Divide Zero1</body></html>", render_);
    }
    @Test
    public void processHtml141Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:try><c:for var='i' from='0' to='2' step='1'>{1/i;}</c:for></c:try><c:catch className='code.expressionlanguage.exceptions.DivideZeroException' var='e'><c:for var='i' from='0' to='2' step='1'>Divide Zero{i;}</c:for></c:catch><c:catch className='java.lang.Exception' var='e'>RTE_ex0</c:catch></c:try><c:catch className='java.lang.Exception' var='e'>RTE_ex0</c:catch></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>Divide Zero0Divide Zero1</body></html>", render_);
    }
    @Test
    public void processHtml142Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try>{1/0}</c:try><c:catch className='java.lang.Exception' var='f'><c:try><c:for var='i' from='0' to='2' step='1'>{1/i;}</c:for></c:try><c:catch className='code.expressionlanguage.exceptions.DivideZeroException' var='e'><c:for var='i' from='0' to='2' step='1'>Divide Zero{i;}</c:for></c:catch><c:catch className='java.lang.Exception' var='e'>RTE_ex0</c:catch></c:catch></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>Divide Zero0Divide Zero1</body></html>", render_);
    }
    @Test
    public void processHtml143Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body><c:try><c:import page=\"page2.html\"/></c:try><c:catch className='java.lang.Exception' var='e'>RTE</c:catch></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body>{1/0}</body></html>";
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
        bean_.setForms(new StringMapObject());
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setTypedString("TITLE");
        beanTwo_.setForms(new StringMapObject());
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        conf_.setHtml(html_);
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processImports(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>RTE</body></html>", render_);
    }
    @Test
    public void processHtml144Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body><c:try><c:import page=\"page2.html\"/></c:try><c:catch className='java.lang.Exception' var='e'>RTE</c:catch></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:try>{1/0}</c:try><c:catch className='code.util.exceptions.NullObjectException' var='e'>NPE</c:catch></body></html>";
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
        bean_.setForms(new StringMapObject());
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setTypedString("TITLE");
        beanTwo_.setForms(new StringMapObject());
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        conf_.setHtml(html_);
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processImports(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>RTE</body></html>", render_);
    }
    @Test
    public void processHtml145Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body><c:try><c:import page=\"page2.html\"/></c:try><c:catch className='java.lang.Exception' var='e'>RTE</c:catch></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:try><c:try>{1/0}</c:try><c:catch className='code.util.exceptions.NullObjectException' var='e'>NPE</c:catch></c:try><c:catch className='code.expressionlanguage.exceptions.DivideZeroException' var='e'>Divide Zero</c:catch></body></html>";
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
        bean_.setForms(new StringMapObject());
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setTypedString("TITLE");
        beanTwo_.setForms(new StringMapObject());
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        conf_.setHtml(html_);
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processImports(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>Divide Zero</body></html>", render_);
    }
    @Test
    public void processHtml146Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body><c:try><c:import page=\"page2.html\"/></c:try><c:catch className='java.lang.Exception' var='e'>RTE</c:catch></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:try><c:try>{($(java.lang.String)$null).length()}</c:try><c:catch className='$badEl' var='e'>NPE</c:catch></c:try><c:catch className='code.expressionlanguage.exceptions.DivideZeroException' var='e'>Divide Zero</c:catch></body></html>";
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
        bean_.setForms(new StringMapObject());
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setTypedString("TITLE");
        beanTwo_.setForms(new StringMapObject());
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        conf_.setHtml(html_);
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processImports(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>NPE</body></html>", render_);
    }
    @Test
    public void processHtml147Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body><c:try><c:import page=\"page2.html\"/></c:try><c:catch className='java.lang.Exception' var='e'>RTE</c:catch></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:try>{($(java.lang.String)$null).length()}</c:try><c:catch className='code.expressionlanguage.exceptions.DivideZeroException' var='e'>Divide Zero</c:catch></body></html>";
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
        bean_.setForms(new StringMapObject());
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setTypedString("TITLE");
        beanTwo_.setForms(new StringMapObject());
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        conf_.setHtml(html_);
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processImports(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>RTE</body></html>", render_);
    }
    @Test
    public void processHtml148Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body><c:try><c:import page=\"page2.html\"/></c:try><c:catch className='java.lang.Exception' var='e'/></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:try>{($(java.lang.String)$null).length()}</c:try><c:catch className='code.expressionlanguage.exceptions.DivideZeroException' var='e'>Divide Zero</c:catch></body></html>";
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
        bean_.setForms(new StringMapObject());
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setTypedString("TITLE");
        beanTwo_.setForms(new StringMapObject());
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        conf_.setHtml(html_);
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processImports(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body/></html>", render_);
    }
    @Test
    public void processHtml149Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body><c:try><c:import page=\"page2.html\"/></c:try><c:catch className='java.lang.String' var='e'>{e;.. $instanceof java.lang.String}</c:catch></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:try><c:throw expression='$new java.lang.String()'/></c:try><c:catch className='code.expressionlanguage.exceptions.DivideZeroException' var='e'>Divide Zero</c:catch></body></html>";
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
        bean_.setForms(new StringMapObject());
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setTypedString("TITLE");
        beanTwo_.setForms(new StringMapObject());
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        conf_.setHtml(html_);
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processImports(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>true</body></html>", render_);
    }
    @Test
    public void processHtml150Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:for var='i' from='0' to='2' step='1'>{1/i;}</c:for></c:try><c:catch className='code.expressionlanguage.exceptions.DivideZeroException' var='e'><c:for var='i' from='0' to='2' step='1'><c:for var='j' from='2' to='4' step='1'>Divide Zero{i;}_{j;}</c:for></c:for></c:catch><c:catch className='java.lang.Exception' var='e'>RTE</c:catch></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>Divide Zero0_2Divide Zero0_3Divide Zero1_2Divide Zero1_3</body></html>", render_);
    }

    @Test
    public void processHtml152Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var='i' from='0' to='4' step='1'><c:try><c:if condition='i;%2==0'><c:continue/></c:if>{i;}_{1/i;};</c:try><c:catch className='code.expressionlanguage.exceptions.DivideZeroException' var='e'>Divide Zero</c:catch><c:catch className='java.lang.Exception' var='e'>RTE</c:catch></c:for></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>1_1;3_0;</body></html>", render_);
    }

    @Test
    public void processHtml153Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var='i' from='0' to='4' step='1'><c:try><c:if condition='i;&gt;=2'><c:break/></c:if>{i;}_{1/i;};</c:try><c:catch className='code.expressionlanguage.exceptions.DivideZeroException' var='e'>Divide Zero</c:catch><c:catch className='java.lang.Exception' var='e'>RTE</c:catch></c:for></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>Divide Zero1_1;</body></html>", render_);
    }


    @Test
    public void processHtml154Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:for var='i' from='0' to='4' step='1'><c:try><c:if condition='i;%2==0'><c:continue/></c:if>{i;}_{1/i;};</c:try><c:catch className='code.expressionlanguage.exceptions.DivideZeroException' var='e'>Divide Zero</c:catch><c:catch className='java.lang.Exception' var='e'>RTE</c:catch></c:for>{1/0}</c:try><c:catch className='java.lang.Exception' var='e'>EXC</c:catch></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>1_1;3_0;EXC</body></html>", render_);
    }

    @Test
    public void processHtml155Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:for var='i' from='0' to='4' step='1'><c:try><c:if condition='i;&gt;=2'><c:break/></c:if>{i;}_{1/i;};</c:try><c:catch className='code.expressionlanguage.exceptions.DivideZeroException' var='e'>Divide Zero</c:catch><c:catch className='java.lang.Exception' var='e'>RTE</c:catch></c:for>{1/0}</c:try><c:catch className='java.lang.Exception' var='e'>EXC</c:catch></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>Divide Zero1_1;EXC</body></html>", render_);
    }

    @Test
    public void processHtml156Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:for var='i' from='0' to='4' step='1'><c:try>{i;}_{1/i;};</c:try><c:catch className='code.expressionlanguage.exceptions.DivideZeroException' var='e'>Divide Zero<c:continue/></c:catch><c:catch className='java.lang.Exception' var='e'>RTE</c:catch>OK</c:for>{1/0}</c:try><c:catch className='java.lang.Exception' var='e'>EXC</c:catch></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>Divide Zero1_1;OK2_0;OK3_0;OKEXC</body></html>", render_);
    }

    @Test
    public void processHtml157Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:for var='i' from='3' to='0' eq='true' step='-1'><c:try>{i;}_{1/i;};</c:try><c:catch className='code.expressionlanguage.exceptions.DivideZeroException' var='e'>Divide Zero<c:break/></c:catch><c:catch className='java.lang.Exception' var='e'>RTE</c:catch>OK</c:for>{1/0}</c:try><c:catch className='java.lang.Exception' var='e'>EXC</c:catch></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>3_0;OK2_0;OK1_1;OKDivide ZeroEXC</body></html>", render_);
    }

    @Test
    public void processHtml158Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:try>{1/0}</c:try><c:catch className='code.expressionlanguage.exceptions.DivideZeroException' var='e'>{1/0}<span>NOT_PASS</span></c:catch><c:catch className='java.lang.Exception' var='e'>RTE</c:catch>NOT READ</c:try><c:catch className='java.lang.Exception' var='e'>EXC</c:catch></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>EXC</body></html>", render_);
    }

    @Test
    public void processHtml159Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var='i' from='0' to='2' step='1'><c:try><c:if condition='i;%2==0'>{i;}_{1/i;};</c:if></c:try><c:catch className='code.expressionlanguage.exceptions.DivideZeroException' var='e'>Divide Zero</c:catch><c:catch className='java.lang.Exception' var='e'>RTE</c:catch></c:for></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>Divide Zero</body></html>", render_);
    }

    @Test
    public void processHtml160Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var='i' from='0' to='2' step='1'><c:try><c:if condition='i;%2==0'><c:throw expression='1/0'/></c:if></c:try><c:catch className='code.util.exceptions.NullObjectException' var='e'>Divide Zero</c:catch><c:catch className='java.lang.Exception' var='e'>RTE</c:catch></c:for></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>RTE</body></html>", render_);
    }

    private static void setup(Configuration _conf) {
        _conf.setSepPrefix("c");
        _conf.setupValiatorsTranslators("LOCALE");
    }

    //updateValue
    private static void assertXmlEqualRuntime(String _htmlExp, String _htmlRes) {
        assertTrue(DocumentBuilder.equalsDocs(_htmlExp, _htmlRes));
    }

    private static Configuration newConfiguration() {
        Configuration conf_ = new Configuration();
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSpecialEnumsMethods(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl context_ = InitializationLgNames.buildStdOne(opt_);
        conf_.setContext(context_);
        conf_.setStandards((BeanLgNames) context_.getStandards());
        return conf_;
    }
}
