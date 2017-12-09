package code.formathtml;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.w3c.dom.Document;

import code.bean.Bean;
import code.bean.translator.Translator;
import code.expressionlanguage.exceptions.DivideZeroException;
import code.expressionlanguage.methods.exceptions.BadTryException;
import code.formathtml.classes.BeanOne;
import code.formathtml.classes.MyTranslator;
import code.formathtml.exceptions.RenderingException;
import code.util.StringMap;
import code.xml.XmlParser;
import code.xml.components.DocumentBuilder;

@SuppressWarnings("static-method")
public class FormatHtmlTryCatchFinallyTest {

    private static final String DIV_ZERO = DivideZeroException.class.getName();

    @Test
    public void processHtml189Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try>{1+1}</c:try><c:catch className='"+DIV_ZERO+"' var='e'>Divide Zero</c:catch><c:finally>END BLOCK</c:finally></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(folder_+"/"+locale_+"/"+relative_+".properties", content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        Configuration conf_ = new Configuration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = XmlParser.parseSaxHtml(html_, false, true);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>2END BLOCK</body></html>", render_);
    }

    @Test
    public void processHtml190Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try>{1+1}</c:try><c:finally>END BLOCK</c:finally></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(folder_+"/"+locale_+"/"+relative_+".properties", content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        Configuration conf_ = new Configuration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = XmlParser.parseSaxHtml(html_, false, true);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>2END BLOCK</body></html>", render_);
    }

    @Test
    public void processHtml191Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:try>{1/0}</c:try><c:finally>END BLOCK</c:finally></c:try><c:catch className='"+DIV_ZERO+"' var='e'>Divide Zero</c:catch></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(folder_+"/"+locale_+"/"+relative_+".properties", content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        Configuration conf_ = new Configuration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = XmlParser.parseSaxHtml(html_, false, true);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>END BLOCKDivide Zero</body></html>", render_);
    }

    @Test
    public void processHtml192Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:try><c:throw expression='^new.java.io.IOException()'/></c:try><c:finally>END BLOCK</c:finally></c:try><c:catch className='java.io.IOException' var='e'>Divide Zero</c:catch></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(folder_+"/"+locale_+"/"+relative_+".properties", content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        Configuration conf_ = new Configuration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = XmlParser.parseSaxHtml(html_, false, true);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>END BLOCKDivide Zero</body></html>", render_);
    }

    @Test
    public void processHtml193Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try>{1/0}</c:try><c:catch className='"+DIV_ZERO+"' var='e'>Divide Zero</c:catch><c:finally>END BLOCK</c:finally></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(folder_+"/"+locale_+"/"+relative_+".properties", content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        Configuration conf_ = new Configuration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = XmlParser.parseSaxHtml(html_, false, true);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>Divide ZeroEND BLOCK</body></html>", render_);
    }


    @Test
    public void processHtml194Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:try>{1/0}</c:try><c:finally>END BLOCK</c:finally></c:try><c:catch className='"+DIV_ZERO+"' var='e'>Divide Zero</c:catch><c:finally>OUTER</c:finally></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(folder_+"/"+locale_+"/"+relative_+".properties", content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        Configuration conf_ = new Configuration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = XmlParser.parseSaxHtml(html_, false, true);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>END BLOCKDivide ZeroOUTER</body></html>", render_);
    }

    @Test
    public void processHtml195Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:try>{1/0}</c:try><c:catch className='"+DIV_ZERO+"' var='e'>END BLOCK<c:throw expression='^new.java.io.IOException()'/></c:catch></c:try><c:catch className='java.lang.Exception' var='e'>Divide Zero</c:catch><c:finally>OUTER</c:finally></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(folder_+"/"+locale_+"/"+relative_+".properties", content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        Configuration conf_ = new Configuration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = XmlParser.parseSaxHtml(html_, false, true);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>END BLOCKDivide ZeroOUTER</body></html>", render_);
    }

//    @Ignore
    @Test
    public void processHtml196Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:try>{1/0}</c:try><c:finally>END BLOCK<c:throw expression='^new.java.io.IOException()'/></c:finally></c:try><c:catch className='java.lang.Exception' var='e'>{e;..getClass().getName()}</c:catch><c:finally>OUTER</c:finally></body></html>";
//        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:try>{1/0}</c:try><c:finally>END BLOCK{1/0}</c:finally></c:try><c:catch className='java.lang.Exception' var='e'>{e;..getClass().getName()}</c:catch><c:finally>OUTER</c:finally></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(folder_+"/"+locale_+"/"+relative_+".properties", content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        Configuration conf_ = new Configuration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = XmlParser.parseSaxHtml(html_, false, true);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>END BLOCKjava.io.IOExceptionOUTER</body></html>", render_);
    }

    @Test
    public void processHtml197Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:try>{1/0}</c:try><c:finally>END BLOCK{1/0}</c:finally></c:try><c:catch className='java.lang.Exception' var='e'>{e;..getClass().getName()}</c:catch><c:finally>OUTER</c:finally></body></html>";
//        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:try>{1/0}</c:try><c:finally>END BLOCK{1/0}</c:finally></c:try><c:catch className='java.lang.Exception' var='e'>{e;..getClass().getName()}</c:catch><c:finally>OUTER</c:finally></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(folder_+"/"+locale_+"/"+relative_+".properties", content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        Configuration conf_ = new Configuration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = XmlParser.parseSaxHtml(html_, false, true);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>"+DIV_ZERO+"OUTER</body></html>", render_);
    }

    @Test
    public void processHtml198Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try>{1+1}<c:return/></c:try><c:catch className='"+DIV_ZERO+"' var='e'>Divide Zero</c:catch><c:finally>END BLOCK</c:finally></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(folder_+"/"+locale_+"/"+relative_+".properties", content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        Configuration conf_ = new Configuration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = XmlParser.parseSaxHtml(html_, false, true);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>2END BLOCK</body></html>", render_);
    }

    @Test
    public void processHtml199Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try>{1+1}<c:return/></c:try><c:catch className='"+DIV_ZERO+"' var='e'>Divide Zero</c:catch><c:finally>END BLOCK</c:finally>NOT DISPLAYED</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(folder_+"/"+locale_+"/"+relative_+".properties", content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        Configuration conf_ = new Configuration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = XmlParser.parseSaxHtml(html_, false, true);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>2END BLOCK</body></html>", render_);
    }

    @Test
    public void processHtml200Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try>{1/0}</c:try><c:catch className='"+DIV_ZERO+"' var='e'>Divide Zero<c:return/></c:catch><c:finally>END BLOCK</c:finally>NOT DISPLAYED</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(folder_+"/"+locale_+"/"+relative_+".properties", content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        Configuration conf_ = new Configuration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = XmlParser.parseSaxHtml(html_, false, true);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>Divide ZeroEND BLOCK</body></html>", render_);
    }

    @Test
    public void processHtml201Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try>{1+1}</c:try><c:catch className='"+DIV_ZERO+"' var='e'>Divide Zero</c:catch><c:finally>END BLOCK</c:finally>DISPLAYED</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(folder_+"/"+locale_+"/"+relative_+".properties", content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        Configuration conf_ = new Configuration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = XmlParser.parseSaxHtml(html_, false, true);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>2END BLOCKDISPLAYED</body></html>", render_);
    }

    @Test
    public void processHtml202Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try>{1/0}</c:try><c:catch className='"+DIV_ZERO+"' var='e'>Divide Zero</c:catch><c:finally>END BLOCK</c:finally>DISPLAYED</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(folder_+"/"+locale_+"/"+relative_+".properties", content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        Configuration conf_ = new Configuration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = XmlParser.parseSaxHtml(html_, false, true);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>Divide ZeroEND BLOCKDISPLAYED</body></html>", render_);
    }

    @Test
    public void processHtml203Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:try>{1/0}</c:try><c:catch className='java.lang.Exception' var='e'><c:throw expression='e;..'/></c:catch><c:finally>END BLOCK{1/0}</c:finally></c:try><c:catch className='java.lang.Exception' var='e'>{e;..getClass().getName()}</c:catch><c:finally>OUTER</c:finally></body></html>";
//        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:try>{1/0}</c:try><c:finally>END BLOCK{1/0}</c:finally></c:try><c:catch className='java.lang.Exception' var='e'>{e;..getClass().getName()}</c:catch><c:finally>OUTER</c:finally></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(folder_+"/"+locale_+"/"+relative_+".properties", content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        Configuration conf_ = new Configuration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = XmlParser.parseSaxHtml(html_, false, true);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>"+DIV_ZERO+"OUTER</body></html>", render_);
    }


    @Test
    public void processHtml204Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:try>{1/0}</c:try><c:catch className='java.lang.Exception' var='e'><c:throw expression='e;..'/></c:catch></c:try><c:catch className='java.lang.Exception' var='e'>{e;..getClass().getName()}</c:catch><c:finally>OUTER</c:finally></body></html>";
//        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:try>{1/0}</c:try><c:finally>END BLOCK{1/0}</c:finally></c:try><c:catch className='java.lang.Exception' var='e'>{e;..getClass().getName()}</c:catch><c:finally>OUTER</c:finally></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(folder_+"/"+locale_+"/"+relative_+".properties", content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        Configuration conf_ = new Configuration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = XmlParser.parseSaxHtml(html_, false, true);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>"+DIV_ZERO+"OUTER</body></html>", render_);
    }

    @Test
    public void processHtml205Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
//        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:try>{1/0}</c:try><c:catch className='java.lang.Exception' var='e'><c:throw expression='e;..'/></c:catch><c:finally>END BLOCK{class(\"java.lang.String\",8i)}</c:finally></c:try><c:catch className='java.lang.Exception' var='e'>{e;..getClass().getName()}</c:catch><c:finally>OUTER</c:finally></body></html>";
        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:try>{1/0}</c:try><c:catch className='java.lang.Exception' var='e'><c:throw expression='e;..'/></c:catch><c:finally>END BLOCK{^class(\"java.lang.String\",8i)}</c:finally></c:try><c:catch className='java.lang.Exception' var='e'>{e;..getClass().getName()}</c:catch><c:finally>OUTER</c:finally></body></html>";
//        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:try>{1/0}</c:try><c:finally>END BLOCK{1/0}</c:finally></c:try><c:catch className='java.lang.Exception' var='e'>{e;..getClass().getName()}</c:catch><c:finally>OUTER</c:finally></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(folder_+"/"+locale_+"/"+relative_+".properties", content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        Configuration conf_ = new Configuration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = XmlParser.parseSaxHtml(html_, false, true);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>code.expressionlanguage.exceptions.DynamicCastClassExceptionOUTER</body></html>", render_);
    }

    @Test
    public void processHtml206Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
//        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:try>{1/0}</c:try><c:catch className='java.lang.Exception' var='e'>{class(\"java.lang.String\",8i)}</c:catch></c:try><c:catch className='java.lang.Exception' var='e'>{e;..getClass().getName()}</c:catch><c:finally>OUTER</c:finally></body></html>";
        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:try>{1/0}</c:try><c:catch className='java.lang.Exception' var='e'>{^class(\"java.lang.String\",8i)}</c:catch></c:try><c:catch className='java.lang.Exception' var='e'>{e;..getClass().getName()}</c:catch><c:finally>OUTER</c:finally></body></html>";
//        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:try>{1/0}</c:try><c:finally>END BLOCK{1/0}</c:finally></c:try><c:catch className='java.lang.Exception' var='e'>{e;..getClass().getName()}</c:catch><c:finally>OUTER</c:finally></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(folder_+"/"+locale_+"/"+relative_+".properties", content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        Configuration conf_ = new Configuration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = XmlParser.parseSaxHtml(html_, false, true);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>code.expressionlanguage.exceptions.DynamicCastClassExceptionOUTER</body></html>", render_);
    }

    @Test
    public void processHtml207Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
//        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:try>{1/0}</c:try><c:catch className='java.lang.Exception' var='e'>EXCEPTION</c:catch><c:finally>{class(\"java.lang.String\",8i)}</c:finally></c:try><c:catch className='java.lang.Exception' var='e'>{e;..getClass().getName()}</c:catch><c:finally>OUTER</c:finally></body></html>";
        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:try>{1/0}</c:try><c:catch className='java.lang.Exception' var='e'>EXCEPTION</c:catch><c:finally>{^class(\"java.lang.String\",8i)}</c:finally></c:try><c:catch className='java.lang.Exception' var='e'>{e;..getClass().getName()}</c:catch><c:finally>OUTER</c:finally></body></html>";
//        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:try>{1/0}</c:try><c:finally>END BLOCK{1/0}</c:finally></c:try><c:catch className='java.lang.Exception' var='e'>{e;..getClass().getName()}</c:catch><c:finally>OUTER</c:finally></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(folder_+"/"+locale_+"/"+relative_+".properties", content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        Configuration conf_ = new Configuration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = XmlParser.parseSaxHtml(html_, false, true);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>EXCEPTIONcode.expressionlanguage.exceptions.DynamicCastClassExceptionOUTER</body></html>", render_);
    }

    @Test
    public void processHtml208Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:try>{1/0}</c:try><c:catch className='"+DIV_ZERO+"' var='e'>Divide Zero<c:return/></c:catch><c:finally>FIRST</c:finally></c:try><c:finally>SECOND</c:finally>NOT DISPLAYED</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(folder_+"/"+locale_+"/"+relative_+".properties", content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        Configuration conf_ = new Configuration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = XmlParser.parseSaxHtml(html_, false, true);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>Divide ZeroFIRSTSECOND</body></html>", render_);
    }

    @Test
    public void processHtml209Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:try>{1+1}<c:return/></c:try><c:catch className='"+DIV_ZERO+"' var='e'>Divide Zero</c:catch><c:finally>FIRST</c:finally></c:try><c:finally>SECOND</c:finally>NOT DISPLAYED</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(folder_+"/"+locale_+"/"+relative_+".properties", content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        Configuration conf_ = new Configuration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = XmlParser.parseSaxHtml(html_, false, true);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>2FIRSTSECOND</body></html>", render_);
    }

    @Test(expected=BadTryException.class)
    public void processHtml217Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try>{1+1}</c:try><c:catch className='"+DIV_ZERO+"' var='e'>Divide Zero</c:catch><c:finally/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(folder_+"/"+locale_+"/"+relative_+".properties", content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        Configuration conf_ = new Configuration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = XmlParser.parseSaxHtml(html_, false, true);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>2</body></html>", render_);
    }

    @Test(expected=BadTryException.class)
    public void processHtml218Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:try>{1/0}</c:try><c:finally/></c:try><c:catch className='"+DIV_ZERO+"' var='e'>Divide Zero</c:catch></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(folder_+"/"+locale_+"/"+relative_+".properties", content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        Configuration conf_ = new Configuration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = XmlParser.parseSaxHtml(html_, false, true);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>Divide Zero</body></html>", render_);
    }

    @Test(expected=BadTryException.class)
    public void processHtml219Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try>{1+1}<c:return/></c:try><c:catch className='"+DIV_ZERO+"' var='e'>Divide Zero</c:catch><c:finally/>NOT DISPLAYED</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(folder_+"/"+locale_+"/"+relative_+".properties", content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        Configuration conf_ = new Configuration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = XmlParser.parseSaxHtml(html_, false, true);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
//        assertXMLEqualNoPrefix("<html><body><c:tmp>ONE - <c:tmp>1;</c:tmp><br/></c:tmp><c:tmp>THREE - <c:tmp>4;</c:tmp><c:tmp>5;</c:tmp><c:tmp>6;</c:tmp><br/></c:tmp><c:tmp>TWO - <c:tmp>2;</c:tmp><c:tmp>3;</c:tmp><br/></c:tmp></body></html>", render_);
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body>2</body></html>", render_);
    }

    @Test(expected=RenderingException.class)
    public void processHtml1FailTest() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:try><c:try>{1/0}</c:try><c:catch className='"+DIV_ZERO+"' var='e'>END BLOCK<c:throw expression='^new.java.io.IOException()'/></c:catch></c:try><c:catch className='"+DIV_ZERO+"' var='e'>Divide Zero</c:catch><c:finally>OUTER</c:finally></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(folder_+"/"+locale_+"/"+relative_+".properties", content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        Configuration conf_ = new Configuration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = XmlParser.parseSaxHtml(html_, false, true);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        //String render_ = FormatHtml.processHtml(doc_.getDocumentElement(), conf_, files_, bean_);
        FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
    }

    private static void setup(Configuration _conf) {
        _conf.setupValiatorsTranslators("LOCALE");
    }

    private static void assertXmlEqualRuntime(String _htmlExp, String _htmlRes) {
        assertTrue(DocumentBuilder.equalsDocs(_htmlExp, _htmlRes));
    }
}
