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
import code.formathtml.classes.SimpleMathFactory;
import code.formathtml.util.BeanLgNames;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.StringList;
import code.util.StringMap;


public class FormatHtmlWhileTest {

    @Test
    public void processHtml212Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        StringBuilder html_ = new StringBuilder("<html xmlns:c='javahtml'>");
        html_.append("<body>");
        html_.append("<c:set var='p' className='code.formathtml.classes.PickableList' expression='$new code.formathtml.classes.PickableList()'/>");
        html_.append("<c:set expression='p;.getList().add(0)'/>");
        html_.append("<c:set expression='p;.getList().add(2)'/>");
        html_.append("<c:while condition='p;.removeAndExistAfter(1i)'/>");
        html_.append("AFTER WHILE</body></html>");
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
        Document doc_ = DocumentBuilder.parseSax(html_.toString());
        conf_.setHtml(html_.toString());
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>AFTER WHILE</body></html>", render_);
    }

    @Test
    public void processHtml213Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        StringBuilder html_ = new StringBuilder("<html xmlns:c='javahtml'>");
        html_.append("<body>");
        html_.append("<c:set var='p' className='code.formathtml.classes.PickableList' expression='$new code.formathtml.classes.PickableList()'/>");
        html_.append("<c:set expression='p;.getList().add(0)'/>");
        html_.append("<c:set expression='p;.getList().add(2)'/>");
        html_.append("<c:do>");
        html_.append("{p;.getList().last()}");
        html_.append("</c:do>");
        html_.append("<c:while condition='p;.removeAndExistAfter(1i)'/>");
        html_.append("AFTER WHILE</body></html>");
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
        Document doc_ = DocumentBuilder.parseSax(html_.toString());
        conf_.setHtml(html_.toString());
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>20AFTER WHILE</body></html>", render_);
    }

    @Test
    public void processHtml214Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        StringBuilder html_ = new StringBuilder("<html xmlns:c='javahtml'>");
        html_.append("<body>");
        html_.append("<c:set var='p' className='code.formathtml.classes.PickableList' expression='$new code.formathtml.classes.PickableList()'/>");
        html_.append("<c:set expression='p;.getList().add(0)'/>");
        html_.append("<c:set expression='p;.getList().add(2)'/>");
        html_.append("<c:do>");
        html_.append("{p;.getList().last()}");
        html_.append("</c:do>");
        html_.append("<c:while condition='p;.removeAndExistAfter(2i)'/>");
        html_.append("AFTER WHILE</body></html>");
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
        Document doc_ = DocumentBuilder.parseSax(html_.toString());
        conf_.setHtml(html_.toString());
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>2AFTER WHILE</body></html>", render_);
    }

    @Test
    public void processHtml215Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        StringBuilder html_ = new StringBuilder("<html xmlns:c='javahtml'>");
        html_.append("<body>");
        html_.append("<c:set var='p' className='code.formathtml.classes.PickableList' expression='$new code.formathtml.classes.PickableList()'/>");
        html_.append("<c:set var='i' className='java.lang.Long' expression='0'/>");
        html_.append("<c:do>");
        html_.append("<c:set expression='p;.getList().clear()'/>");
        html_.append("<c:set expression='p;.getList().add(0)'/>");
        html_.append("<c:set expression='p;.getList().add(2)'/>");
        html_.append("<c:do>");
        html_.append("{p;.getList().last()} {i;.} ");
        html_.append("</c:do>");
        html_.append("<c:while condition='p;.removeAndExistAfter(1i)'/>");
        html_.append("<c:set var='i' className='java.lang.Long' expression='i;.+1'/>");
        html_.append("</c:do>");
        html_.append("<c:while condition='i;.&lt;2'/>");
        html_.append("AFTER WHILE</body></html>");
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
        Document doc_ = DocumentBuilder.parseSax(html_.toString());
        conf_.setHtml(html_.toString());
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>2 0 0 0 2 1 0 1 AFTER WHILE</body></html>", render_);
    }

    @Test
    public void processHtml216Test() {
        String locale_ = "LOCALE";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        StringBuilder html_ = new StringBuilder("<html xmlns:c='javahtml'>");
        html_.append("<body>");
        html_.append("<c:set var='p' className='code.formathtml.classes.PickableList' expression='$new code.formathtml.classes.PickableList()'/>");
        html_.append("<c:set expression='p;.getList().add(0)'/>");
        html_.append("<c:set expression='p;.getList().add(2)'/>");
        html_.append("<c:do/>");
        html_.append("<c:while condition='p;.removeAndExistAfter(1i)'/>");
        html_.append("AFTER WHILE</body></html>");
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
        Document doc_ = DocumentBuilder.parseSax(html_.toString());
        conf_.setHtml(html_.toString());
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>AFTER WHILE</body></html>", render_);
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
