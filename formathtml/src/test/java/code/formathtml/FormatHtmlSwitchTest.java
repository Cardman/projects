package code.formathtml;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.bean.Bean;
import code.bean.translator.Translator;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.variables.VariableSuffix;
import code.formathtml.classes.BeanOne;
import code.formathtml.classes.MyTranslator;
import code.formathtml.util.BeanLgNames;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.StringMap;


public class FormatHtmlSwitchTest {

    @Test
    public void processHtml159Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='3' className='$long'/><c:switch expression='v;.'/>NEXT</body></html>";
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
//        assertXMLEqualRuntime("<html xmlns:c='javahtml' xmlns='javahtml'><body><c_tmp>ONE - <c_tmp>1;</c_tmp><br/></c_tmp><c_tmp>THREE - <c_tmp>4;</c_tmp><c_tmp>5;</c_tmp><c_tmp>6;</c_tmp><br/></c_tmp><c_tmp>TWO - <c_tmp>2;</c_tmp><c_tmp>3;</c_tmp><br/></c_tmp></body></html>", render_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>NEXT</body></html>", render_);
    }

    @Test
    public void processHtml160Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='3' className='$long'/><c:switch expression='v;.'><c:case expression='1'/></c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>NEXT</body></html>", render_);
    }

    @Test
    public void processHtml161Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='3' className='$long'/><c:switch expression='v;.'><c:case expression='1'/><c:default/></c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>NEXT</body></html>", render_);
    }

    @Test
    public void processHtml162Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='3' className='$long'/><c:switch expression='v;.'><c:default/></c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>NEXT</body></html>", render_);
    }

    @Test
    public void processHtml163Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='3' className='$long'/><c:switch expression='v;.'><c:case expression='3'/></c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>NEXT</body></html>", render_);
    }

    @Test
    public void processHtml164Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='3' className='$long'/><c:switch expression='v;.'><c:case expression='3'/><c:default/></c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>NEXT</body></html>", render_);
    }

    @Test
    public void processHtml165Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='3' className='$long'/><c:switch expression='v;.'><c:case expression='1'/><c:case expression='3'/></c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>NEXT</body></html>", render_);
    }

    @Test
    public void processHtml166Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='3' className='$long'/><c:switch expression='v;.'><c:case expression='1'/><c:case expression='3'/><c:default/></c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>NEXT</body></html>", render_);
    }

    @Test
    public void processHtml167Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='3' className='$long'/><c:switch expression='v;.'><c:case expression='1'/><c:case expression='3'/></c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>NEXT</body></html>", render_);
    }

    @Test
    public void processHtml168Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='3' className='$long'/><c:switch expression='v;.'><c:case expression='1'/><c:case expression='3'/><c:default/></c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>NEXT</body></html>", render_);
    }

    @Test
    public void processHtml169Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='3' className='$long'/><c:switch expression='v;.'><c:case expression='1'>ONE</c:case></c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>NEXT</body></html>", render_);
    }

    @Test
    public void processHtml170Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='3' className='$long'/><c:switch expression='v;.'><c:case expression='1'>ONE</c:case><c:default>DEFAULT</c:default></c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>DEFAULTNEXT</body></html>", render_);
    }

    @Test
    public void processHtml171Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='3' className='$long'/><c:switch expression='v;.'><c:default>DEFAULT</c:default></c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>DEFAULTNEXT</body></html>", render_);
    }

    @Test
    public void processHtml172Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='3' className='$long'/><c:switch expression='v;.'><c:case expression='3'>THREE</c:case></c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>THREENEXT</body></html>", render_);
    }

    @Test
    public void processHtml173Test() {
        //TODO break test
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='3' className='$long'/><c:switch expression='v;.'><c:case expression='3'>THREE</c:case><c:default>DEFAULT</c:default></c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>THREEDEFAULTNEXT</body></html>", render_);
    }

    @Test
    public void processHtml174Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='3' className='$long'/><c:switch expression='v;.'><c:case expression='1'>ONE</c:case><c:case expression='3'>THREE</c:case></c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>THREENEXT</body></html>", render_);
    }

    @Test
    public void processHtml175Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='3' className='$long'/><c:switch expression='v;.'><c:case expression='1'>ONE</c:case><c:case expression='3'>THREE</c:case><c:default>DEFAULT</c:default></c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>THREEDEFAULTNEXT</body></html>", render_);
    }

    @Test
    public void processHtml176Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='1' className='$long'/><c:switch expression='v;.'><c:case expression='1'>ONE</c:case><c:case expression='3'>THREE</c:case></c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>ONETHREENEXT</body></html>", render_);
    }

    @Test
    public void processHtml177Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='1' className='$long'/><c:switch expression='v;.'><c:case expression='1'>ONE</c:case><c:case expression='3'>THREE</c:case><c:default>DEFAULT</c:default></c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>ONETHREEDEFAULTNEXT</body></html>", render_);
    }

    @Test
    public void processHtml178Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='1' className='$long'/><c:switch expression='v;.'>\n<c:case expression='1'>ONE</c:case>\n<c:case expression='3'>THREE</c:case>\n<c:default>DEFAULT</c:default>\n</c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>\nONE\nTHREE\nDEFAULT\nNEXT</body></html>", render_);
    }


    @Test
    public void processHtml179Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='1' className='$long'/><c:switch expression='v;.'>\n<c:case expression='1'>ONE</c:case>\n<c:case expression='3'>THREE<c:break/></c:case>\n<c:default>DEFAULT</c:default>\n</c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>\nONE\nTHREENEXT</body></html>", render_);
    }


    @Test
    public void processHtml180Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='1' className='$long'/><c:switch expression='v;.'>\n<c:case expression='1'>ONE<c:break/></c:case>\n<c:case expression='3'>THREE</c:case>\n<c:default>DEFAULT</c:default>\n</c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>\nONENEXT</body></html>", render_);
    }


    @Test
    public void processHtml181Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='3' className='$long'/><c:switch expression='v;.'>\n<c:default>DEFAULT</c:default>\n<c:case expression='1'>ONE<c:break/></c:case>\n<c:case expression='3'>THREE</c:case>\n</c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>\nDEFAULT\nONENEXT</body></html>", render_);
    }

    @Test
    public void processHtml182Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='3' className='$long'/><c:switch expression='v;.'><c:default/><c:case expression='1'>ONE</c:case></c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>ONENEXT</body></html>", render_);
    }

    @Test
    public void processHtml183Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='1' className='$long'/><c:switch expression='v;.'><c:default/><c:case expression='1'>ONE</c:case></c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>ONENEXT</body></html>", render_);
    }

    @Test
    public void processHtml184Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='3' className='$long'/><c:switch expression='v;.'><c:default/><c:case expression='2'/><c:case expression='1'>ONE</c:case></c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>ONENEXT</body></html>", render_);
    }

    @Test
    public void processHtml185Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='3' className='$long'/><c:switch expression='v;.'><c:default/><c:case expression='2'/></c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>NEXT</body></html>", render_);
    }

    @Test
    public void processHtml186Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='3' className='$long'/><c:switch expression='v;.'><c:case expression='3'>THREE</c:case><c:default/><c:case expression='2'/></c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>THREENEXT</body></html>", render_);
    }


    @Test
    public void processHtml187Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var='v' from='0' to='5' step='1'><c:switch expression='v;'><c:case expression='0'>ZERO_{v;}</c:case><c:case expression='1'>ONE_{v;}<c:break/></c:case><c:case expression='2'>TWO_{v;}</c:case><c:case expression='3'>THREE_{v;}<c:break/></c:case><c:default>LIMIT{v;}</c:default></c:switch></c:for>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>ZERO_0ONE_0ONE_1TWO_2THREE_2THREE_3LIMIT4NEXT</body></html>", render_);
    }

    @Test
    public void processHtml188Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var='v' from='0' to='5' step='1'><c:switch expression='v;'><c:case expression='0'>ZERO_{v;}</c:case><c:case expression='1'>ONE_{v;}<c:break/></c:case><c:case expression='2'>TWO_{v;}</c:case><c:case expression='3'>THREE_{v;}<c:break/></c:case><c:default>LIMIT{v;}</c:default></c:switch></c:for>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>ZERO_0ONE_0ONE_1TWO_2THREE_2THREE_3LIMIT4NEXT</body></html>", render_);
    }

    @Test
    public void processHtml210Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var='v' expression='3' className='$long'/><c:switch expression='v;.'><c:case expression='3'/><c:case expression='1'>THREE</c:case></c:switch>NEXT</body></html>";
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
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>THREENEXT</body></html>", render_);
    }

    private static void setup(Configuration _conf) {
        _conf.setSepPrefix("c");
        _conf.setupValiatorsTranslators("LOCALE");
    }

    private static void assertXmlEqualRuntime(String _htmlExp, String _htmlRes) {
        assertTrue(DocumentBuilder.equalsDocs(_htmlExp, _htmlRes));
    }

    private static Configuration newConfiguration() {
        Configuration conf_ = EquallableExUtil.newConfiguration();
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl context_ = InitializationLgNames.buildStdOne(opt_);
        conf_.setContext(context_);
        conf_.setStandards((BeanLgNames) context_.getStandards());
        return conf_;
    }
}
