package code.formathtml;
import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import code.bean.Bean;
import code.bean.translator.Translator;
import code.bean.validator.Validator;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.variables.VariableSuffix;
import code.formathtml.classes.BeanFive;
import code.formathtml.classes.BeanOne;
import code.formathtml.classes.BeanSeven;
import code.formathtml.classes.BeanSix;
import code.formathtml.classes.BeanThree;
import code.formathtml.classes.BeanTwo;
import code.formathtml.classes.MyTranslator;
import code.formathtml.classes.SimpleMathFactory;
import code.formathtml.util.BeanLgNames;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.StringList;
import code.util.StringMap;
import code.util.StringMapObject;

@SuppressWarnings("static-method")
public class FormatHtmlTest {
    @Test
    public void processHtml1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><ul><c:for var=\"s\" list=\"composite.strings\"><li>{s;}</li></c:for></ul></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);

        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><ul><li>FIRST</li><li>SECOND</li></ul></body></html>", render_);
    }
    @Test
    public void processHtml2Test() {
        String locale_ = "en";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);

        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>NOT EMPTY</body></html>", render_);
    }
    @Test
    public void processHtml3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:if condition=\"composite.strings.isEmpty()\">EMPTY</c:if><c:elseif condition=\"hasMoreThanOne()\"/><c:else>OTHER</c:else>NEXT</body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);

        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>NEXT</body></html>", render_);
    }
    @Test
    public void processHtml4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:if condition=\"composite.strings.isEmpty()\">EMPTY</c:if><c:else/>NEXT</body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);

        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>NEXT</body></html>", render_);
    }

    @Test
    public void processHtml5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><div><c:for var=\"s\" list=\"composite.strings\"><ul>{s;}<br/><c:for var=\"t\" list=\"composite.strings\" className='java.lang.String'><li>{t;}</li><c:if condition=\"!t;isEmpty()\"/><c:else>NOT DISPLAYED</c:else></c:for></ul></c:for></div><br/></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);

        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><div><ul>FIRST<br/><li>FIRST</li><li>SECOND</li></ul><ul>SECOND<br/><li>FIRST</li><li>SECOND</li></ul></div><br/></body></html>", render_);
    }

    @Test
    public void processHtml6Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><table><c:for key=\"k\" value=\"v\" map=\"tree\"><tr><td>{k;}</td><td>{v;}</td></tr></c:for></table></body></html>";
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


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><table><tr><td>ONE</td><td>1</td></tr><tr><td>TWO</td><td>2</td></tr></table></body></html>", render_);
    }

    @Test
    public void processHtml7Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><table><c:for key=\"k\" value=\"v\" map=\"tree\" keyClassName='java.lang.String'><tr><td>{k;length()}</td><td>{v;}</td></tr></c:for></table></body></html>";
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


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><table><tr><td>3</td><td>1</td></tr><tr><td>3</td><td>2</td></tr></table></body></html>", render_);
    }

    @Test
    public void processHtml8Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><ul><c:for var=\"s\" list=\"composite.strings\" className='java.lang.String'><li>{s;length()}</li></c:for></ul></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><ul><li>5</li><li>6</li></ul></body></html>", render_);
    }

    @Test
    public void processHtml9Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select list=\"combobox\"/></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select c:className=\"\" name=\"\"><option value=\"ONE\">ONE</option><option value=\"TWO\">TWO</option><option value=\"THREE\">THREE</option><option value=\"FOUR\">FOUR</option><option value=\"FIVE\">FIVE</option><option value=\"SIX\">SIX</option></select></body></html>", render_);
    }

    @Test
    public void processHtml10Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select className=\"code.formathtml.classes.EnumNumber\" name=\"chosenNumber\" list=\"combobox\"/></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select n-i=\"0\" c:className=\"code.formathtml.classes.EnumNumber\" name=\"bean_one.chosenNumber\"><option value=\"ONE\">ONE</option><option value=\"TWO\">TWO</option><option value=\"THREE\">THREE</option><option value=\"FOUR\">FOUR</option><option value=\"FIVE\">FIVE</option><option value=\"SIX\">SIX</option></select></body></html>", render_);
    }

    @Test
    public void processHtml11Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select varValue=\"chosenNumber\" className=\"code.formathtml.classes.EnumNumber\" name=\"chosenNumber\" list=\"combobox\"/></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select n-i=\"0\" c:className=\"code.formathtml.classes.EnumNumber\" name=\"bean_one.chosenNumber\"><option selected=\"selected\" value=\"ONE\">ONE</option><option value=\"TWO\">TWO</option><option value=\"THREE\">THREE</option><option value=\"FOUR\">FOUR</option><option value=\"FIVE\">FIVE</option><option value=\"SIX\">SIX</option></select></body></html>", render_);
    }

    @Test
    public void processHtml12Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select update=\"\" default=\"TWO\" varValue=\"chosenNumber\" className=\"code.formathtml.classes.EnumNumber\" name=\"chosenNumber\" list=\"combobox\"/></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select n-i=\"0\" c:className=\"code.formathtml.classes.EnumNumber\" name=\"bean_one.chosenNumber\"><option selected=\"selected\" value=\"ONE\">ONE</option><option value=\"TWO\">TWO</option><option value=\"THREE\">THREE</option><option value=\"FOUR\">FOUR</option><option value=\"FIVE\">FIVE</option><option value=\"SIX\">SIX</option></select></body></html>", render_);
    }

    @Test
    public void processHtml13Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select default=\"TWO\" varValue=\"chosenNumber\" className=\"code.formathtml.classes.EnumNumber\" name=\"chosenNumber\" list=\"combobox\"/></body></html>";
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
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select n-i=\"0\" c:className=\"code.formathtml.classes.EnumNumber\" name=\"bean_one.chosenNumber\"><option value=\"ONE\">ONE</option><option selected=\"selected\" value=\"TWO\">TWO</option><option value=\"THREE\">THREE</option><option value=\"FOUR\">FOUR</option><option value=\"FIVE\">FIVE</option><option value=\"SIX\">SIX</option></select></body></html>", render_);
    }

    @Test
    public void processHtml14Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select default=\"TWO\" update=\"\" className=\"code.formathtml.classes.EnumNumber\" name=\"chosenNumber\" list=\"combobox\"/></body></html>";
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select n-i=\"0\" c:className=\"code.formathtml.classes.EnumNumber\" name=\"bean_one.chosenNumber\"><option value=\"ONE\">ONE</option><option selected=\"selected\" value=\"TWO\">TWO</option><option value=\"THREE\">THREE</option><option value=\"FOUR\">FOUR</option><option value=\"FIVE\">FIVE</option><option value=\"SIX\">SIX</option></select></body></html>", render_);
    }

    @Test
    public void processHtml15Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select default=\"$getDefaultChoice()\" update=\"\" className=\"code.formathtml.classes.EnumNumber\" name=\"chosenNumber\" list=\"combobox\"/></body></html>";
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select n-i=\"0\" c:className=\"code.formathtml.classes.EnumNumber\" name=\"bean_one.chosenNumber\"><option value=\"ONE\">ONE</option><option value=\"TWO\">TWO</option><option value=\"THREE\">THREE</option><option value=\"FOUR\">FOUR</option><option value=\"FIVE\">FIVE</option><option selected=\"selected\" value=\"SIX\">SIX</option></select></body></html>", render_);
    }


    @Test
    public void processHtml16Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><table><c:for key=\"k\" value=\"v\" map=\"map\"><tr><td>{k;}</td><td>{v;}</td></tr></c:for></table></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getMap().put("ONE", 1);
        bean_.getMap().put("TWO", 2);
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><table><tr><td>ONE</td><td>1</td></tr><tr><td>TWO</td><td>2</td></tr></table></body></html>", render_);
    }

    @Test
    public void processHtml17Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html xmlns:c='javahtml'><body><table><c:for key=\"k\" value=\"v\" map=\"map\"><tr><td>{k;}</td><td>{v;}</td></tr></c:for></table></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getMap().put("ONE", 1);
        bean_.getMap().put("TWO", 2);
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><table><tr><td>ONE</td><td>1</td></tr><tr><td>TWO</td><td>2</td></tr></table></body></html>", render_);
    }

    @Test
    public void processHtml18Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select map=\"translations\"/></body></html>";
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select c:className=\"\" name=\"\"><option value=\"ONE\">1</option><option value=\"TWO\">2</option><option value=\"THREE\">3</option><option value=\"FOUR\">4</option><option value=\"FIVE\">5</option><option value=\"SIX\">6</option></select></body></html>", render_);
    }

    @Test
    public void processHtml19Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select map=\"tree\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select c:className=\"\" name=\"\"><option value=\"ONE\">1</option><option value=\"TWO\">2</option></select></body></html>", render_);
    }

    @Test
    public void processHtml20Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select varValue=\"chosenNumber\" className=\"code.formathtml.classes.EnumNumber\" name=\"chosenNumber\" map=\"translations\"/></body></html>";
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select n-i=\"0\" c:className=\"code.formathtml.classes.EnumNumber\" name=\"bean_one.chosenNumber\"><option selected=\"selected\" value=\"ONE\">1</option><option value=\"TWO\">2</option><option value=\"THREE\">3</option><option value=\"FOUR\">4</option><option value=\"FIVE\">5</option><option value=\"SIX\">6</option></select></body></html>", render_);
    }

    @Test
    public void processHtml21Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select default=\"TWO\" varValue=\"selectedString\" update=\"\" className=\"code.formathtml.classes.EnumNumber\" name=\"chosenNumber\" map=\"tree\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select n-i=\"0\" c:className=\"code.formathtml.classes.EnumNumber\" name=\"bean_one.chosenNumber\"><option selected=\"selected\" value=\"ONE\">1</option><option value=\"TWO\">2</option></select></body></html>", render_);
    }

    @Test
    public void processHtml22Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select default=\"TWO\" varValue=\"selectedString\" className=\"code.formathtml.classes.EnumNumber\" name=\"chosenNumber\" map=\"tree\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select n-i=\"0\" c:className=\"code.formathtml.classes.EnumNumber\" name=\"bean_one.chosenNumber\"><option value=\"ONE\">1</option><option selected=\"selected\" value=\"TWO\">2</option></select></body></html>", render_);
    }

    @Test
    public void processHtml23Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select default=\"TWO\" varValue=\"chosenNumber\" className=\"code.formathtml.classes.EnumNumber\" name=\"chosenNumber\" map=\"translations\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select n-i=\"0\" c:className=\"code.formathtml.classes.EnumNumber\" name=\"bean_one.chosenNumber\"><option value=\"ONE\">1</option><option selected=\"selected\" value=\"TWO\">2</option><option value=\"THREE\">3</option><option value=\"FOUR\">4</option><option value=\"FIVE\">5</option><option value=\"SIX\">6</option></select></body></html>", render_);
    }

    @Test
    public void processHtml24Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select default=\"$chosenNumber\" update=\"\" className=\"code.formathtml.classes.EnumNumber\" name=\"chosenNumber\" map=\"translations\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select n-i=\"0\" c:className=\"code.formathtml.classes.EnumNumber\" name=\"bean_one.chosenNumber\"><option selected=\"selected\" value=\"ONE\">1</option><option value=\"TWO\">2</option><option value=\"THREE\">3</option><option value=\"FOUR\">4</option><option value=\"FIVE\">5</option><option value=\"SIX\">6</option></select></body></html>", render_);
    }

    @Test
    public void processHtml25Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select default=\"$selectedString\" className=\"code.formathtml.classes.EnumNumber\" name=\"chosenNumber\" map=\"tree\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select n-i=\"0\" c:className=\"code.formathtml.classes.EnumNumber\" name=\"bean_one.chosenNumber\"><option selected=\"selected\" value=\"ONE\">1</option><option value=\"TWO\">2</option></select></body></html>", render_);
    }

    @Test
    public void processHtml26Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><ul><c:for var=\"s\" list=\"composite.strings\"><li>{s;;}</li></c:for></ul></body></html>";
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><ul><li>0</li><li>1</li></ul></body></html>", render_);
    }

    @Test
    public void processHtml27Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for key=\"k\" value=\"v\" map=\"numbers\">{k;} - <c:for var=\"w\" list=\"v;\">{w;};</c:for><br/></c:for></body></html>";
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
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>ONE - 1;<br/>THREE - 4;5;6;<br/>TWO - 2;3;<br/></body></html>", render_);
    }

    @Test
    public void processHtml28Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for key=\"k\" value=\"v\" map=\"numbers\">{k;} - <a title=\"sample_title\">{getDouble(k;;)};</a><br/></c:for></body></html>";
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
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>ONE - <a title=\"sample_title\">0;</a><br/>THREE - <a title=\"sample_title\">2;</a><br/>TWO - <a title=\"sample_title\">4;</a><br/></body></html>", render_);
    }

    @Test
    public void processHtml29Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for key=\"k\" value=\"v\" varClassName='ls' map=\"numbers\">{k;};<c:set var=\"v\" expression=\"v;size()\" className=\"java.lang.Integer\"/><c:set var=\"v\" expression=\"v;.longValue()\" className=\"java.lang.Long\"/><c:for var=\"w\" list=\"getList(v;.)\">{w;}</c:for><br/></c:for></body></html>";
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
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>ONE;456<br/>THREE;23<br/>TWO;23<br/></body></html>", render_);
    }

    @Test
    public void processHtml30Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select id=\"element\" list=\"combobox\"/></body></html>";
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select c:className=\"\" id=\"element\" name=\"\" c:validator=\"\"><option value=\"ONE\">ONE</option><option value=\"TWO\">TWO</option><option value=\"THREE\">THREE</option><option value=\"FOUR\">FOUR</option><option value=\"FIVE\">FIVE</option><option value=\"SIX\">SIX</option></select></body></html>", render_);
    }

    @Test
    public void processHtml31Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select id=\"element\" map=\"translations\"/></body></html>";
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select c:className=\"\" id=\"element\" name=\"\" c:validator=\"\"><option value=\"ONE\">1</option><option value=\"TWO\">2</option><option value=\"THREE\">3</option><option value=\"FOUR\">4</option><option value=\"FIVE\">5</option><option value=\"SIX\">6</option></select></body></html>", render_);
    }

    @Test
    public void processHtml32Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select map=\"map\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getMap().put(null, 0);
        bean_.getMap().put("TWO", 1);
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select c:className=\"\" name=\"\"><option value=\"TWO\">1</option></select></body></html>", render_);
    }

    @Test
    public void processHtml33Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select map=\"map\" default=\"TWO\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getMap().put(null, 0);
        bean_.getMap().put("TWO", 1);
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select c:className=\"\" name=\"\"><option selected=\"selected\" value=\"TWO\">1</option></select></body></html>", render_);
    }

    @Test
    public void processHtml34Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select list=\"combobox\" multiple=\"multiple\"/></body></html>";
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select c:className=\"\" name=\"\" multiple=\"multiple\"><option value=\"ONE\">ONE</option><option value=\"TWO\">TWO</option><option value=\"THREE\">THREE</option><option value=\"FOUR\">FOUR</option><option value=\"FIVE\">FIVE</option><option value=\"SIX\">SIX</option></select></body></html>", render_);
    }

    @Test
    public void processHtml35Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select className=\"code.formathtml.classes.EnumNumber\" name=\"chosenNumbers\" list=\"combobox\" multiple=\"multiple\"/></body></html>";
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select n-i=\"0\" c:className=\"code.formathtml.classes.EnumNumber\" name=\"bean_one.chosenNumbers\" multiple=\"multiple\"><option value=\"ONE\">ONE</option><option value=\"TWO\">TWO</option><option value=\"THREE\">THREE</option><option value=\"FOUR\">FOUR</option><option value=\"FIVE\">FIVE</option><option value=\"SIX\">SIX</option></select></body></html>", render_);
    }

    @Test
    public void processHtml36Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select varValue=\"chosenNumbers\" className=\"code.formathtml.classes.EnumNumber\" name=\"chosenNumbers\" list=\"combobox\" multiple=\"multiple\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanFive bean_ = new BeanFive();
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select n-i=\"0\" c:className=\"code.formathtml.classes.EnumNumber\" name=\"bean_one.chosenNumbers\" multiple=\"multiple\"><option selected=\"selected\" value=\"ONE\">ONE</option><option value=\"TWO\">TWO</option><option value=\"THREE\">THREE</option><option value=\"FOUR\" selected=\"selected\">FOUR</option><option value=\"FIVE\">FIVE</option><option value=\"SIX\">SIX</option></select></body></html>", render_);
    }

    @Test
    public void processHtml37Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select update=\"\" default=\"TWO,THREE\" varValue=\"chosenNumbers\" className=\"code.formathtml.classes.EnumNumber\" name=\"chosenNumbers\" list=\"combobox\" multiple=\"multiple\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanFive bean_ = new BeanFive();
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select n-i=\"0\" c:className=\"code.formathtml.classes.EnumNumber\" name=\"bean_one.chosenNumbers\" multiple=\"multiple\"><option selected=\"selected\" value=\"ONE\">ONE</option><option value=\"TWO\">TWO</option><option value=\"THREE\">THREE</option><option value=\"FOUR\" selected=\"selected\">FOUR</option><option value=\"FIVE\">FIVE</option><option value=\"SIX\">SIX</option></select></body></html>", render_);
    }

    @Test
    public void processHtml38Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select default=\"TWO,THREE\" varValue=\"chosenNumbers\" className=\"code.formathtml.classes.EnumNumber\" name=\"chosenNumbers\" list=\"combobox\" multiple=\"multiple\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanFive bean_ = new BeanFive();
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select n-i=\"0\" c:className=\"code.formathtml.classes.EnumNumber\" name=\"bean_one.chosenNumbers\" multiple=\"multiple\"><option value=\"ONE\">ONE</option><option selected=\"selected\" value=\"TWO\">TWO</option><option value=\"THREE\" selected=\"selected\">THREE</option><option value=\"FOUR\">FOUR</option><option value=\"FIVE\">FIVE</option><option value=\"SIX\">SIX</option></select></body></html>", render_);
    }

    @Test
    public void processHtml39Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select default=\"TWO,THREE\" update=\"\" className=\"code.formathtml.classes.EnumNumber\" name=\"chosenNumbers\" list=\"combobox\" multiple=\"multiple\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanFive bean_ = new BeanFive();
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select n-i=\"0\" c:className=\"code.formathtml.classes.EnumNumber\" name=\"bean_one.chosenNumbers\" multiple=\"multiple\"><option value=\"ONE\">ONE</option><option selected=\"selected\" value=\"TWO\">TWO</option><option value=\"THREE\" selected=\"selected\">THREE</option><option value=\"FOUR\">FOUR</option><option value=\"FIVE\">FIVE</option><option value=\"SIX\">SIX</option></select></body></html>", render_);
    }

    @Test
    public void processHtml40Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select default=\"$getDefaultChoices()\" update=\"\" className=\"code.formathtml.classes.EnumNumber\" name=\"chosenNumbers\" list=\"combobox\" multiple=\"multiple\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanFive bean_ = new BeanFive();
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select n-i=\"0\" c:className=\"code.formathtml.classes.EnumNumber\" name=\"bean_one.chosenNumbers\" multiple=\"multiple\"><option value=\"ONE\">ONE</option><option value=\"TWO\">TWO</option><option value=\"THREE\">THREE</option><option value=\"FOUR\">FOUR</option><option value=\"FIVE\">FIVE</option><option selected=\"selected\" value=\"SIX\">SIX</option></select></body></html>", render_);
    }

    @Test
    public void processHtml41Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select map=\"translations\" multiple=\"multiple\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanFive bean_ = new BeanFive();
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select c:className=\"\" name=\"\" multiple=\"multiple\"><option value=\"ONE\">1</option><option value=\"TWO\">2</option><option value=\"THREE\">3</option><option value=\"FOUR\">4</option><option value=\"FIVE\">5</option><option value=\"SIX\">6</option></select></body></html>", render_);
    }

    @Test
    public void processHtml42Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select map=\"tree\" multiple=\"multiple\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanFive bean_ = new BeanFive();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select c:className=\"\" name=\"\" multiple=\"multiple\"><option value=\"ONE\">1</option><option value=\"TWO\">2</option></select></body></html>", render_);
    }

    @Test
    public void processHtml43Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select varValue=\"chosenNumbers\" className=\"code.formathtml.classes.EnumNumber\" name=\"chosenNumbers\" map=\"translations\" multiple=\"multiple\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanFive bean_ = new BeanFive();
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select n-i=\"0\" c:className=\"code.formathtml.classes.EnumNumber\" name=\"bean_one.chosenNumbers\" multiple=\"multiple\"><option selected=\"selected\" value=\"ONE\">1</option><option value=\"TWO\">2</option><option value=\"THREE\">3</option><option value=\"FOUR\" selected=\"selected\">4</option><option value=\"FIVE\">5</option><option value=\"SIX\">6</option></select></body></html>", render_);
    }

    @Test
    public void processHtml44Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select default=\"TWO,THREE\" varValue=\"selectedStrings\" update=\"\" className=\"code.formathtml.classes.EnumNumber\" name=\"chosenNumbers\" map=\"tree\" multiple=\"multiple\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanFive bean_ = new BeanFive();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select n-i=\"0\" c:className=\"code.formathtml.classes.EnumNumber\" name=\"bean_one.chosenNumbers\" multiple=\"multiple\"><option selected=\"selected\" value=\"ONE\">1</option><option value=\"TWO\">2</option></select></body></html>", render_);
    }

    @Test
    public void processHtml45Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select default=\"TWO,THREE\" varValue=\"selectedStrings\" className=\"code.formathtml.classes.EnumNumber\" name=\"chosenNumbers\" map=\"tree\" multiple=\"multiple\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanFive bean_ = new BeanFive();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select n-i=\"0\" c:className=\"code.formathtml.classes.EnumNumber\" name=\"bean_one.chosenNumbers\" multiple=\"multiple\"><option value=\"ONE\">1</option><option value=\"TWO\" selected=\"selected\">2</option></select></body></html>", render_);
    }

    @Test
    public void processHtml46Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select default=\"TWO,THREE\" varValue=\"chosenNumbers\" className=\"code.formathtml.classes.EnumNumber\" name=\"chosenNumbers\" map=\"translations\" multiple=\"multiple\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanFive bean_ = new BeanFive();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select n-i=\"0\" c:className=\"code.formathtml.classes.EnumNumber\" name=\"bean_one.chosenNumbers\" multiple=\"multiple\"><option value=\"ONE\">1</option><option value=\"TWO\" selected=\"selected\">2</option><option value=\"THREE\" selected=\"selected\">3</option><option value=\"FOUR\">4</option><option value=\"FIVE\">5</option><option value=\"SIX\">6</option></select></body></html>", render_);
    }

    @Test
    public void processHtml47Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select default=\"$chosenNumbers\" update=\"\" className=\"code.formathtml.classes.EnumNumber\" name=\"chosenNumbers\" map=\"translations\" multiple=\"multiple\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanFive bean_ = new BeanFive();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select n-i=\"0\" c:className=\"code.formathtml.classes.EnumNumber\" name=\"bean_one.chosenNumbers\" multiple=\"multiple\"><option selected=\"selected\" value=\"ONE\">1</option><option value=\"TWO\">2</option><option value=\"THREE\">3</option><option value=\"FOUR\" selected=\"selected\">4</option><option value=\"FIVE\">5</option><option value=\"SIX\">6</option></select></body></html>", render_);
    }

    @Test
    public void processHtml48Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select default=\"$selectedStrings\" className=\"code.formathtml.classes.EnumNumber\" name=\"chosenNumbers\" map=\"tree\" multiple=\"multiple\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanFive bean_ = new BeanFive();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select n-i=\"0\" c:className=\"code.formathtml.classes.EnumNumber\" name=\"bean_one.chosenNumbers\" multiple=\"multiple\"><option selected=\"selected\" value=\"ONE\">1</option><option value=\"TWO\">2</option></select></body></html>", render_);
    }

    @Test
    public void processHtml49Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select groupId=\"element\" list=\"combobox\"/></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select c:className=\"\" c:groupId=\"element\" name=\"\" c:validator=\"\"><option value=\"ONE\">ONE</option><option value=\"TWO\">TWO</option><option value=\"THREE\">THREE</option><option value=\"FOUR\">FOUR</option><option value=\"FIVE\">FIVE</option><option value=\"SIX\">SIX</option></select></body></html>", render_);
    }
    @Test
    public void processHtml50Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select groupId=\"element\" map=\"translations\"/></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select c:className=\"\" c:groupId=\"element\" name=\"\" c:validator=\"\"><option value=\"ONE\">1</option><option value=\"TWO\">2</option><option value=\"THREE\">3</option><option value=\"FOUR\">4</option><option value=\"FIVE\">5</option><option value=\"SIX\">6</option></select></body></html>", render_);
    }

    @Test
    public void processHtml51Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:if condition=\"composite.strings.isEmpty()\">EMPTY</c:if><c:else>NOT EMPTY</c:else></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>NOT EMPTY</body></html>", render_);
    }

    @Test
    public void processHtml52Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:if condition=\"composite.strings.isEmpty()\">EMPTY</c:if><c:else>NOT EMPTY</c:else></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);

        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>EMPTY</body></html>", render_);
    }

    @Test
    public void processHtml54Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:if condition=\"composite.strings.isEmpty()\">EMPTY</c:if><c:elseif condition=\"hasMoreThanOne()\">NOT EMPTY</c:elseif></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);

        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>EMPTY</body></html>", render_);
    }

    @Test
    public void processHtml55Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:if condition=\"composite.strings.isEmpty()\">EMPTY</c:if><c:elseif condition=\"hasMoreThanOne()\">NOT EMPTY</c:elseif><c:else>ONE ELEMENT</c:else></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>ONE ELEMENT</body></html>", render_);
    }

    @Test
    public void processHtml57Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var=\"s\" list=\"composite.strings\">EMPTY</c:for></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body/></html>", render_);
    }

    @Test
    public void processHtml58Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var=\"s\" list=\"composite.strings\">EMPTY</c:for><br/></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><br/></body></html>", render_);
    }

    @Test
    public void processHtml59Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var=\"s\" list=\"composite.strings\"/></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body/></html>", render_);
    }

    @Test
    public void processHtml60Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var=\"s\" list=\"composite.strings\"/><br/></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><br/></body></html>", render_);
    }
    @Test
    public void processHtml61Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:if condition=\"composite.strings.isEmpty()\">EMPTY</c:if><c:elseif condition=\"hasMoreThanOne()\">NOT EMPTY</c:elseif><c:else>OTHER</c:else></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);

        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>EMPTY</body></html>", render_);
    }

    @Test
    public void processHtml62Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:if condition=\"composite.strings.isEmpty()\">EMPTY</c:if><c:elseif condition=\"hasMoreThanOne()\">NOT EMPTY</c:elseif><c:elseif condition=\"hasMoreThanZero()\">ONE</c:elseif><c:else>OTHER</c:else></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);

        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>EMPTY</body></html>", render_);
    }

    @Test
    public void processHtml63Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><div><c:if condition=\"composite.strings.isEmpty()\">EMPTY</c:if><c:elseif condition=\"hasMoreThanOne()\">NOT EMPTY</c:elseif><c:elseif condition=\"hasMoreThanZero()\">ONE</c:elseif></div><br/></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);

        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><div>EMPTY</div><br/></body></html>", render_);
    }

    @Test
    public void processHtml64Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:if condition=\"composite.strings.isEmpty()\">EMPTY</c:if>\t\n\t<c:else>NOT EMPTY</c:else></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>\t\n\tNOT EMPTY</body></html>", render_);
    }

    @Test
    public void processHtml65Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:if condition=\"composite.strings.isEmpty()\">EMPTY</c:if>END</body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);

        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>EMPTYEND</body></html>", render_);
    }

    @Ignore
    @Test
    public void processHtml66Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:if condition=\"composite.strings.isEmpty()\">EMPTY</c:if>END<c:else>NOT EMPTY</c:else></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);

        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>END</body></html>", render_);
    }

    @Test
    public void processHtml67Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><div><c:if condition=\"composite.strings.isEmpty()\">EMPTY</c:if><c:elseif condition=\"hasMoreThanOne()\">NOT EMPTY</c:elseif>\t\n\t<c:elseif condition=\"hasMoreThanZero()\">ONE</c:elseif></div><br/></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);

        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><div>EMPTY\t\n\t</div><br/></body></html>", render_);
    }

    @Test
    public void processHtml68Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><ul><c:for var=\"s\" list=\"composite.strings\"><li>{s;;}</li></c:for></ul></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);

        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><ul><li>0</li><li>1</li></ul></body></html>", render_);
    }

    @Test
    public void processHtml69Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><ul><c:for var=\"s\" list=\"composite.strings\"><li><c:if condition=\"!getTrans(s;;).isEmpty()\">{s;}</c:if></li></c:for></ul></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);

        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><ul><li>FIRST</li><li>SECOND</li></ul></body></html>", render_);
    }

    @Test
    public void processHtml70Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var=\"s\" list=\"composite.strings\"><c:for var=\"t\" list=\"composite.strings\">{s;}-{t;}-</c:for></c:for></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);

        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>FIRST-FIRST-FIRST-SECOND-SECOND-FIRST-SECOND-SECOND-</body></html>", render_);
    }

    @Test
    public void processHtml71Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var=\"s\" list=\"composite.strings\"><ul>{s;}<br/><c:for var=\"t\" list=\"composite.strings\"><li>{t;}</li></c:for></ul></c:for></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);

        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><ul>FIRST<br/><li>FIRST</li><li>SECOND</li></ul><ul>SECOND<br/><li>FIRST</li><li>SECOND</li></ul></body></html>", render_);
    }

    @Test
    public void processHtml72Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><div><c:for var=\"s\" list=\"composite.strings\"><ul>{s;}<br/><c:for var=\"t\" list=\"composite.strings\"><li>{t;}</li></c:for></ul></c:for></div><br/></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);

        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><div><ul>FIRST<br/><li>FIRST</li><li>SECOND</li></ul><ul>SECOND<br/><li>FIRST</li><li>SECOND</li></ul></div><br/></body></html>", render_);
    }

    @Test
    public void processHtml73Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><div><c:for var=\"s\" list=\"composite.strings\"><ul>{s;}<br/><c:for var=\"t\" list=\"composite.strings\" className='java.lang.String'><li>{t;}</li><c:if condition=\"!t;isEmpty()\"/></c:for></ul></c:for></div><br/></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);

        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><div><ul>FIRST<br/><li>FIRST</li><li>SECOND</li></ul><ul>SECOND<br/><li>FIRST</li><li>SECOND</li></ul></div><br/></body></html>", render_);
    }

    @Test
    public void processHtml74Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><div><c:for var=\"s\" list=\"composite.strings\"><input type=\"text\" name=\"s;\" c:varValue=\"s;\"/></c:for></div><br/></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><div><input n-i=\"0\" type=\"text\" name=\"bean_one.s;\" value=\"FIRST\"/><input n-i=\"1\" type=\"text\" name=\"bean_one.s;\" value=\"SECOND\"/></div><br/></body></html>", render_);
    }

    @Test
    public void processHtml75Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><div><c:for var=\"c\" list=\"getComposites()\" className='code.formathtml.classes.Composite'><input type=\"text\" name=\"c;string\" c:varValue=\"c;string\"/></c:for></div><br/></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><div><input n-i=\"0\" type=\"text\" name=\"bean_one.c;string\" value=\"0\"/><input n-i=\"1\" type=\"text\" name=\"bean_one.c;string\" value=\"1\"/></div><br/></body></html>", render_);
    }

    @Test
    public void processHtml76Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><div><c:for var=\"s\" list=\"composite.strings\"><c:for var=\"t\" list=\"composite.strings\"><c:for var=\"u\" list=\"composite.strings\"><span class=\"$getSpanClasses(,,)\">IN</span></c:for></c:for></c:for></div><br/></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><div><span class=\"a0b0c0\">IN</span><span class=\"a0b0c1\">IN</span><span class=\"a0b1c0\">IN</span><span class=\"a0b1c1\">IN</span><span class=\"a1b0c0\">IN</span><span class=\"a1b0c1\">IN</span><span class=\"a1b1c0\">IN</span><span class=\"a1b1c1\">IN</span></div><br/></body></html>", render_);
    }

    @Test
    public void processHtml77Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><div><c:for var=\"s\" list=\"composite.strings\"><span class=\"$getSpanClass()\">IN</span></c:for></div><br/></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><div><span class=\"a0\">IN</span><span class=\"a1\">IN</span></div><br/></body></html>", render_);
    }

    @Test
    public void processHtml78Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><div><c:for var=\"s\" list=\"composite.strings\"><span class=\"$commonClass\">IN</span></c:for></div><br/></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><div><span class=\"abba\">IN</span><span class=\"abba\">IN</span></div><br/></body></html>", render_);
    }

    @Test
    public void processHtml79Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html xmlns:c='javahtml'><body><c:for key=\"k\" value=\"v\" map=\"numbers\">{k;} - <c:for var=\"w\" list=\"v;\"><input type=\"text\" name=\"w;\"/></c:for><br/></c:for></body></html>";
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



        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>ONE - <input n-i=\"0\" type=\"text\" name=\"bean_one.w;\"/><br/>THREE - <input n-i=\"1\" type=\"text\" name=\"bean_one.w;\"/><input n-i=\"2\" type=\"text\" name=\"bean_one.w;\"/><input n-i=\"3\" type=\"text\" name=\"bean_one.w;\"/><br/>TWO - <input n-i=\"4\" type=\"text\" name=\"bean_one.w;\"/><input n-i=\"5\" type=\"text\" name=\"bean_one.w;\"/><br/></body></html>", render_);
    }

    @Test
    public void processHtml80Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html xmlns:c='javahtml'><body><c:for key=\"k\" value=\"v\" map=\"numbers\">{k;} - <input type=\"text\" id=\"numbers[{k;;}]sortedNumberKeys!key\"/><br/></c:for></body></html>";
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


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>ONE - <input type=\"text\" id=\"numbers[0]sortedNumberKeys!key\"/><br/>THREE - <input type=\"text\" id=\"numbers[1]sortedNumberKeys!key\"/><br/>TWO - <input type=\"text\" id=\"numbers[2]sortedNumberKeys!key\"/><br/></body></html>", render_);
    }

    @Test
    public void processHtml81Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"v\" expression='$null'/><c:for key=\"k\" value=\"v\" map=\"numbers\" varClassName='ls'><c:for var=\"w\" list=\"v;\"><span id=\"numbers[{k;;}]sortedNumberKeys!value.getReverse()[{w;;}]\"/></c:for></c:for></body></html>";
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
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><span id=\"numbers[0]sortedNumberKeys!value.getReverse()[0]\"/><span id=\"numbers[1]sortedNumberKeys!value.getReverse()[0]\"/><span id=\"numbers[1]sortedNumberKeys!value.getReverse()[1]\"/><span id=\"numbers[1]sortedNumberKeys!value.getReverse()[2]\"/><span id=\"numbers[2]sortedNumberKeys!value.getReverse()[0]\"/><span id=\"numbers[2]sortedNumberKeys!value.getReverse()[1]\"/></body></html>", render_);
    }

    @Test
    public void processHtml82Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html xmlns:c='javahtml'><body><c:for key=\"k\" value=\"v\" map=\"numbers\"><span c:groupId=\"{k;;}\"/></c:for></body></html>";
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
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><span c:groupId=\"0\"/><span c:groupId=\"1\"/><span c:groupId=\"2\"/></body></html>", render_);
    }

    @Test
    public void processHtml83Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><textarea c:varValue=\"getComposite().string\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
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
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><textarea></textarea></body></html>", render_);
    }

    @Test
    public void processHtml84Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><textarea c:varValue=\"getComposite().string\">INIT</textarea></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().setString("THEN");
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
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><textarea>THEN</textarea></body></html>", render_);
    }

    @Test
    public void processHtml85Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"l\" expression=\"composite.strings\" className='code.util.StringList'/>{l;.size()}</body></html>";
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>2</body></html>", render_);
    }

    @Test
    public void processHtml86Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"l\" expression=\"composite.strings\" className='code.util.StringList'/>{l;.display()}</body></html>";
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>[FIRST,SECOND]</body></html>", render_);
    }

    @Test
    public void processHtml87Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"l\" expression=\"composite.strings\"/><c:for var=\"e\" list=\"l;.\"><a>{e;}</a></c:for></body></html>";
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><a>FIRST</a><a>SECOND</a></body></html>", render_);
    }

    @Test
    public void processHtml88Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"l\" expression=\"composite.strings\" className='code.util.StringList'/><c:for var=\"e\" list=\"l;.getReverse()\"><a>{e;}</a></c:for></body></html>";
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><a>SECOND</a><a>FIRST</a></body></html>", render_);
    }

    @Test
    public void processHtml89Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"l\" expression=\"composite.integer\" className='$int'/>{l;.}</body></html>";
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>5</body></html>", render_);
    }

    @Test
    public void processHtml90Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"l\" expression=\"8\" className='$int'/>{l;.}</body></html>";
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>8</body></html>", render_);
    }

    @Test
    public void processHtml91Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"l\" expression=\"8\" className='$double'/>{l;.}</body></html>";
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>8.0</body></html>", render_);
    }

    @Test
    public void processHtml92Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"l\" expression=\"8\"/>{l;.}</body></html>";
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>8</body></html>", render_);
    }

    @Test
    public void processHtml93Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"l\" expression=\"-1\" className='$short'/>{l;.}_<c:set var=\"l\" expression=\"9\" className='$byte'/>{l;.}_<c:set var=\"l\" expression=\"8\" className='$float'/>{l;.}_<c:set var=\"l\" expression=\"composite.myChar\" className='$char'/>{l;.}</body></html>";
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>-1_9_8.0_t</body></html>", render_);
    }

    @Test
    public void processHtml94Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"l\" expression=\"composite.displayed\" className='$boolean'/><c:if condition=\"l;.\">DISPLAY</c:if></body></html>";
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>DISPLAY</body></html>", render_);
    }

    @Test
    public void processHtml95Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"l\" expression=\"$null\"/><c:if isnull=\"l;.\">NULL_VAR</c:if></body></html>";
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
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>NULL_VAR</body></html>", render_);
    }

    @Test
    public void processHtml96Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"n\" expression=\"0\" className='$int'/>{($(java.lang.String)composite.strings.get(0i)).charAt(n;.)} {composite.strings.get(n;.)} {($(java.lang.String)composite.getStringElt(n;.)).charAt(n;.)} <c:set var=\"l\" expression=\"2\" className='$int'/>{composite.summum(l;.)}<c:set var=\"l\" expression=\"1\" className='$int'/>{composite.strings.get(l;.)}<c:for var=\"c\" list=\"getComposites()\" className='code.formathtml.classes.Composite'>{c;strings.get(0i)} {($(java.lang.String)c;strings.get(0i)).length()} {c;summum(l;.)}</c:for></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>F FIRST F 7SECONDF 1 1S 1 1</body></html>", render_);
    }

    @Test
    public void processHtml97Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"n\" mathexpr=\"1+1\" isbool=\"false\"/>{n;.}</body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>2</body></html>", render_);
    }

    @Test
    public void processHtml98Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"n\" mathexpr=\"false|true\" isbool=\"true\"/>{n;.}<c:set var=\"n\" mathexpr=\"false\" isbool=\"true\"/>{n;.}</body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>truefalse</body></html>", render_);
    }

    @Test
    public void processHtml99Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"n\" mathexpr=\"{composite.integer}+1\" isbool=\"false\"/>{n;.}</body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>6</body></html>", render_);
    }

    @Test
    public void processHtml100Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"n\" mathexpr=\"{composite.integer}+1\" isbool=\"false\" className=\"java.lang.Long\"/>{getSpanClass(n;.)}</body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>a6</body></html>", render_);
    }
    @Test
    public void processHtml102Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:while mathexpr=\"{composite.strings.isEmpty()}\">EMPTY</c:while></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body/></html>", render_);
    }

    @Test
    public void processHtml103Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"nb\" mathexpr=\"0\"/><c:while mathexpr=\"{nb;.}!=10\">{nb;.}_<c:set var=\"nb\" mathexpr=\"{nb;.}+1\"/></c:while></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>0_1_2_3_4_5_6_7_8_9_</body></html>", render_);
    }

    @Test
    public void processHtml104Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"nb\" mathexpr=\"0\"/><c:while mathexpr=\"{nb;.}!=2\"><c:set var=\"nbTwo\" mathexpr=\"3\"/>{nb;.}_<c:while mathexpr=\"{nbTwo;.}!=5\">{nbTwo;.},<c:set var=\"nbTwo\" mathexpr=\"{nbTwo;.}+1\"/></c:while><c:set var=\"nb\" mathexpr=\"{nb;.}+1\"/></c:while></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>0_3,4,1_3,4,</body></html>", render_);
    }

    @Test
    public void processHtml105Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"str\" expression=\"abba\" isstringconst=\"true\"/>{str;.}</body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>abba</body></html>", render_);
    }

    @Test
    public void processHtml106Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"str\" expression=\"abba\" isstringconst=\"true\" className=\"java.lang.String\"/>{getStandard(str;.)}</body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>abba</body></html>", render_);
    }

    @Test
    public void processHtml107Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"str\" isstringconst=\"true\"/>{str;.}</body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body></body></html>", render_);
    }

    @Test
    public void processHtml108Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"str\" isstringconst=\"true\" className=\"java.lang.String\"/>{getStandard(str;.)}</body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body></body></html>", render_);
    }

    @Test
    public void processHtml109Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"str\" expression=\"const\" isstringconst=\"true\" className=\"java.lang.Object\"/><c:while defined=\"str\">{str;.}<c:unset var=\"str\"/><c:continue/>NOT DISPLAYED</c:while></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>const</body></html>", render_);
    }


    @Test
    public void processHtml110Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"str\" expression=\"const\" isstringconst=\"true\" className=\"java.lang.Object\"/><c:while defined=\"str\">{str;.}<c:break/>NOT DISPLAYED<c:unset var=\"str\"/></c:while></body></html>";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>const</body></html>", render_);
    }

    @Test
    public void processHtml111Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html xmlns:c='javahtml'><body><c:for key=\"k\" value=\"v\" map=\"numbers\">{k;} - <input type=\"text\" id=\"\\{numbers[{k;;}]sortedNumberKeys!key\"/><br/></c:for></body></html>";
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


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>ONE - <input type=\"text\" id=\"{numbers[0]sortedNumberKeys!key\"/><br/>THREE - <input type=\"text\" id=\"{numbers[1]sortedNumberKeys!key\"/><br/>TWO - <input type=\"text\" id=\"{numbers[2]sortedNumberKeys!key\"/><br/></body></html>", render_);
    }
    @Test
    public void processHtml1111Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html xmlns:c='javahtml'><body><c:for key=\"k\" value=\"v\" map=\"numbers\">{k;} - <input type=\"text\" id=\"\\}numbers[{k;;}]sortedNumberKeys!key\"/><br/></c:for></body></html>";
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


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>ONE - <input type=\"text\" id=\"}numbers[0]sortedNumberKeys!key\"/><br/>THREE - <input type=\"text\" id=\"}numbers[1]sortedNumberKeys!key\"/><br/>TWO - <input type=\"text\" id=\"}numbers[2]sortedNumberKeys!key\"/><br/></body></html>", render_);
    }
    @Test
    public void processHtml112Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html xmlns:c='javahtml'><body><c:for key=\"k\" value=\"v\" map=\"numbers\">{k;} - <input type=\"text\" id=\"\\\\numbers[{k;;}]sortedNumberKeys!key\"/><br/></c:for></body></html>";
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


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>ONE - <input type=\"text\" id=\"\\numbers[0]sortedNumberKeys!key\"/><br/>THREE - <input type=\"text\" id=\"\\numbers[1]sortedNumberKeys!key\"/><br/>TWO - <input type=\"text\" id=\"\\numbers[2]sortedNumberKeys!key\"/><br/></body></html>", render_);
    }

    @Test
    public void processHtml113Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html xmlns:c='javahtml'><body><c:for key=\"k\" value=\"v\" map=\"numbers\">{k;} - <input type=\"text\" id=\"{k;;}{k;;}\"/><br/></c:for></body></html>";
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


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>ONE - <input type=\"text\" id=\"00\"/><br/>THREE - <input type=\"text\" id=\"11\"/><br/>TWO - <input type=\"text\" id=\"22\"/><br/></body></html>", render_);
    }

    @Test
    public void processHtml114Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var=\"k\" from=\"0\" to=\"2\" eq=\"true\" step=\"1\">{k;} - <input type=\"text\" id=\"{k;;}{k;;}\"/><br/></c:for></body></html>";
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


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>0 - <input type=\"text\" id=\"00\"/><br/>1 - <input type=\"text\" id=\"11\"/><br/>2 - <input type=\"text\" id=\"22\"/><br/></body></html>", render_);
    }


    @Test
    public void processHtml115Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var=\"k\" from=\"0\" to=\"2\" eq=\"true\" step=\"2\">{k;} - <input type=\"text\" id=\"{k;;}{k;;}\"/><br/></c:for></body></html>";
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


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>0 - <input type=\"text\" id=\"00\"/><br/>2 - <input type=\"text\" id=\"11\"/><br/></body></html>", render_);
    }


    @Test
    public void processHtml116Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var=\"k\" from=\"0\" to=\"2\" eq=\"true\" step=\"-1\">{k;} - <input type=\"text\" id=\"{k;;}{k;;}\"/><br/></c:for></body></html>";
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


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>0 - <input type=\"text\" id=\"00\"/><br/>1 - <input type=\"text\" id=\"11\"/><br/>2 - <input type=\"text\" id=\"22\"/><br/></body></html>", render_);
    }


    @Test
    public void processHtml117Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var=\"k\" from=\"0\" to=\"2\" eq=\"true\" step=\"-2\">{k;} - <input type=\"text\" id=\"{k;;}{k;;}\"/><br/></c:for></body></html>";
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


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>0 - <input type=\"text\" id=\"00\"/><br/>2 - <input type=\"text\" id=\"11\"/><br/></body></html>", render_);
    }


    @Test
    public void processHtml118Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var=\"k\" from=\"2\" to=\"0\" eq=\"true\" step=\"1\">{k;} - <input type=\"text\" id=\"{k;;}{k;;}\"/><br/></c:for></body></html>";
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


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>2 - <input type=\"text\" id=\"00\"/><br/>1 - <input type=\"text\" id=\"11\"/><br/>0 - <input type=\"text\" id=\"22\"/><br/></body></html>", render_);
    }


    @Test
    public void processHtml119Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var=\"k\" from=\"2\" to=\"0\" eq=\"true\" step=\"2\">{k;} - <input type=\"text\" id=\"{k;;}{k;;}\"/><br/></c:for></body></html>";
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


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>2 - <input type=\"text\" id=\"00\"/><br/>0 - <input type=\"text\" id=\"11\"/><br/></body></html>", render_);
    }


    @Test
    public void processHtml120Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var=\"k\" from=\"2\" to=\"0\" eq=\"true\" step=\"-1\">{k;} - <input type=\"text\" id=\"{k;;}{k;;}\"/><br/></c:for></body></html>";
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


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>2 - <input type=\"text\" id=\"00\"/><br/>1 - <input type=\"text\" id=\"11\"/><br/>0 - <input type=\"text\" id=\"22\"/><br/></body></html>", render_);
    }


    @Test
    public void processHtml121Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var=\"k\" from=\"2\" to=\"0\" eq=\"true\" step=\"-2\">{k;} - <input type=\"text\" id=\"{k;;}{k;;}\"/><br/></c:for></body></html>";
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


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>2 - <input type=\"text\" id=\"00\"/><br/>0 - <input type=\"text\" id=\"11\"/><br/></body></html>", render_);
    }


    @Test
    public void processHtml122Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var=\"k\" from=\"0\" to=\"2\" step=\"1\">{k;} - <input type=\"text\" id=\"{k;;}{k;;}\"/><br/></c:for></body></html>";
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


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>0 - <input type=\"text\" id=\"00\"/><br/>1 - <input type=\"text\" id=\"11\"/><br/></body></html>", render_);
    }


    @Test
    public void processHtml123Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var=\"k\" from=\"0\" to=\"2\" step=\"2\">{k;} - <input type=\"text\" id=\"{k;;}{k;;}\"/><br/></c:for></body></html>";
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


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>0 - <input type=\"text\" id=\"00\"/><br/></body></html>", render_);
    }


    @Test
    public void processHtml124Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var=\"k\" from=\"0\" to=\"2\" step=\"-1\">{k;} - <input type=\"text\" id=\"{k;;}{k;;}\"/><br/></c:for></body></html>";
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


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>0 - <input type=\"text\" id=\"00\"/><br/>1 - <input type=\"text\" id=\"11\"/><br/></body></html>", render_);
    }


    @Test
    public void processHtml125Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var=\"k\" from=\"0\" to=\"2\" step=\"-2\">{k;} - <input type=\"text\" id=\"{k;;}{k;;}\"/><br/></c:for></body></html>";
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


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>0 - <input type=\"text\" id=\"00\"/><br/></body></html>", render_);
    }


    @Test
    public void processHtml126Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var=\"k\" from=\"2\" to=\"0\" step=\"1\">{k;} - <input type=\"text\" id=\"{k;;}{k;;}\"/><br/></c:for></body></html>";
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


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>2 - <input type=\"text\" id=\"00\"/><br/>1 - <input type=\"text\" id=\"11\"/><br/></body></html>", render_);
    }


    @Test
    public void processHtml127Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var=\"k\" from=\"2\" to=\"0\" step=\"2\">{k;} - <input type=\"text\" id=\"{k;;}{k;;}\"/><br/></c:for></body></html>";
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


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>2 - <input type=\"text\" id=\"00\"/><br/></body></html>", render_);
    }


    @Test
    public void processHtml128Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var=\"k\" from=\"2\" to=\"0\" step=\"-1\">{k;} - <input type=\"text\" id=\"{k;;}{k;;}\"/><br/></c:for></body></html>";
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


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>2 - <input type=\"text\" id=\"00\"/><br/>1 - <input type=\"text\" id=\"11\"/><br/></body></html>", render_);
    }

    @Test
    public void processHtml129Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var=\"k\" from=\"2\" to=\"0\" step=\"-2\">{k;} - <input type=\"text\" id=\"{k;;}{k;;}\"/><br/></c:for></body></html>";
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


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>2 - <input type=\"text\" id=\"00\"/><br/></body></html>", render_);
    }

    @Test
    public void processHtml130Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for className='java.lang.Integer' var=\"k\" from=\"0\" to=\"1\" step=\"1\">{k; $instanceof java.lang.Integer}</c:for></body></html>";
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


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>true</body></html>", render_);
    }


    @Test
    public void processHtml131Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for var=\"k\" from=\"2\" to=\"0\" step=\"0\">{k;} - <input type=\"text\" id=\"{k;;}{k;;}\"/><br/></c:for></body></html>";
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


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body/></html>", render_);
    }

    @Test
    public void processHtml212Test() {
        String locale_ = "en";
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);

        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);


        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>NOT EMPTY</body></html>", render_);
    }

    @Test
    public void processHtml213Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select varValue=\"chosenNumbers\" className=\"code.formathtml.classes.EnumNumbers\" name=\"chosenNumbers\" list=\"combobox\" multiple=''/></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select n-i=\"0\" c:className=\"code.formathtml.classes.EnumNumbers\" multiple='multiple' name=\"bean_one.chosenNumbers\"><option selected=\"selected\" value=\"ONE\">ONE</option><option value=\"TWO\">TWO</option><option value=\"THREE\">THREE</option><option selected=\"selected\" value=\"FOUR\">FOUR</option><option value=\"FIVE\">FIVE</option><option value=\"SIX\">SIX</option></select></body></html>", render_);
    }

    @Test
    public void processHtml214Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select varValue=\"chosenNumbers\" name=\"chosenNumbers\" list=\"combobox\" multiple=''/></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select n-i=\"0\" c:className=\"\" multiple='multiple' name=\"bean_one.chosenNumbers\"><option selected=\"selected\" value=\"ONE\">ONE</option><option value=\"TWO\">TWO</option><option value=\"THREE\">THREE</option><option selected=\"selected\" value=\"FOUR\">FOUR</option><option value=\"FIVE\">FIVE</option><option value=\"SIX\">SIX</option></select></body></html>", render_);
    }
    @Test
    public void processHtml1FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><table><c:for key=\"k\" value=\"k\" map=\"tree\"><tr><td>{k;}</td></tr></c:for></table></body></html>";
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
        FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertNotNull(conf_.getContext().getException());
        
    }

    @Test
    public void processHtml2FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:for key=\"k\" value=\"v\" map=\"numbers\"><c:for var=\"v\" list=\"v;\">{v;}</c:for></c:for></body></html>";
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
        FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertNotNull(conf_.getContext().getException());
        
    }

    @Test
    public void processHtml4FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"l\" expression=\"composite.map\" className='$int'/><c:if isnull=\"l;.\">NULL_VAR</c:if></body></html>";
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
        conf_.setHtml(html_);
        setup(conf_);
        
        FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertNotNull(conf_.getContext().getException());
    }

    @Test
    public void processHtml5FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"l\" expression=\"composite.strings\" className=\"lse\"/><c:for var=\"e\" list=\"l;.getReverse()\"><a>{e;}</a></c:for></body></html>";
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
        conf_.setHtml(html_);
        setup(conf_);
        
        FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertNotNull(conf_.getContext().getException());
    }

    @Test
    public void processHtml6FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body>{($(formathtml.classe.Composite)composite).integer}</body></html>";
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
        conf_.setHtml(html_);
        setup(conf_);
        
        FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertNotNull(conf_.getContext().getException());
    }

    @Test
    public void processVarInputValue1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><form><input type=\"text\" c:varValue=\"typedString\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setTypedString("EXAMPLE");
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);



        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><form n-f=\"0\" ><input type=\"text\" value=\"EXAMPLE\"/></form></body></html>", render_);
    }

    @Test
    public void processVarInputValue2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><form><input type=\"text\" c:varValue=\"typedString\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);



        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><form n-f=\"0\" ><input type=\"text\" value=\"\"/></form></body></html>", render_);
    }

    @Test
    public void processVarInputValue3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><form><input type=\"checkbox\" c:varValue=\"checked\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setChecked(true);
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);



        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><form n-f=\"0\"><input checked=\"checked\" type=\"checkbox\"/></form></body></html>", render_);
    }

    @Test
    public void processVarInputValue4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><form><input type=\"checkbox\" c:varValue=\"checked\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setChecked(false);
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);



        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><form n-f=\"0\"><input type=\"checkbox\"/></form></body></html>", render_);
    }

    @Test
    public void processVarInputValue5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><form><input type=\"checkbox\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setChecked(false);
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);



        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><form n-f=\"0\"><input type=\"checkbox\"/></form></body></html>", render_);
    }

    @Test
    public void processVarInputValue6Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><form><input type=\"radio\" c:varValue=\"1\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setChecked(false);
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);



        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><form n-f=\"0\"><input type=\"radio\" value=\"1\"/></form></body></html>", render_);
    }

    @Test
    public void processVarInputValue7Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><form><textarea c:varValue=\"typedString\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setChecked(false);
        beanTwo_.setTypedString("TYPED");
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);



        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><form n-f=\"0\"><textarea>TYPED</textarea></form></body></html>", render_);
    }

    @Test
    public void formatClassAndMessage1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><p class=\"$typedString\"></p></body></html>";

        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setTypedString("EXAMPLE");
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);




        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><p class=\"EXAMPLE\"/></body></html>", render_);
    }

    @Test
    public void formatClassAndMessage2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><p class=\"typedString\"></p></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setTypedString("EXAMPLE");
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);




        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><p class=\"typedString\"/></body></html>", render_);
    }

    @Test
    public void formatClassAndMessage3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><p class=\"$typedString\"></p></body></html>";

        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);




        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><p/></body></html>", render_);
    }

    @Test
    public void formatClassAndMessage4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:message value=\"msg_example,three\"><param value=\"''\" quoted=\"quoted\"/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);





        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>desc &lt;''&gt;</body></html>", render_);
    }

    @Test
    public void formatClassAndMessage5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:message value=\"msg_example,three\"><param value=\"''\" quoted=\"\"/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);





        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>desc &lt;''&gt;</body></html>", render_);
    }

    @Test
    public void processMessages1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:message value=\"msg_example,three\" escaped=\"escaped\"><param value=\"''\" quoted=\"quoted\"/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);






        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>desc &amp;lt;''&amp;gt;</body></html>", render_);
    }

    @Test
    public void processMessages2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt; &amp;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:message value=\"msg_example,three\" escaped=\"escaped\"><param value=\"''\" quoted=\"quoted\"/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);






        
        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>desc &amp;lt;''&amp;gt; &amp;amp;</body></html>", render_);

    }

    @Test
    public void processMessages3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description & <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:message value=\"msg_example,two\" escaped=\"escaped\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);






        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>Description &amp; &lt;a href=\"\"&gt;two&lt;/a&gt;</body></html>", render_);
    }

    @Test
    public void processMessages4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:message value=\"msg_example,two\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);






        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>Description <a href=\"\">two</a></body></html>", render_);
    }

    @Test
    public void processMessages5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &amp;lt;{0}&amp;gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:message value=\"msg_example,three\"><param value=\"''\" quoted=\"quoted\"/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);






        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>desc &amp;lt;''&amp;gt;</body></html>", render_);

    }

    @Test
    public void processMessages6Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &amp;lt;{0}&amp;gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><a class=\"a_class\">EXAMPLE</a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);






        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><a class=\"a_class\">EXAMPLE</a></body></html>", render_);
    }

    @Test
    public void processMessages7Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &amp;lt;{0}&amp;gt;\nfour=''asp''\ntitle=My page title";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><head><title><c:message value=\"msg_example,title\"/></title></head><body><a class=\"a_class\">EXAMPLE</a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);






        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><head><title>My page title</title></head><body><a class=\"a_class\">EXAMPLE</a></body></html>", render_);
    }

    @Test
    public void processMessages8Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\"><span>two</span></a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:message value=\"msg_example,two\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);






        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>Description <a href=\"\"><span>two</span></a></body></html>", render_);
    }


    @Test
    public void processMessages9Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:message value=\"msg_example,three\" escaped=\"escaped\" escapedamp='true'><param value=\"''\" quoted=\"quoted\"/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);






        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>desc &amp;lt;''&amp;gt;</body></html>", render_);
    }

    @Test
    public void processMessages10Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt; &amp;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:message value=\"msg_example,three\" escaped=\"escaped\" escapedamp='true'><param value=\"''\" quoted=\"quoted\"/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);






        
        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>desc &amp;lt;''&amp;gt; &amp;</body></html>", render_);

    }

    @Test
    public void processMessages11Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description & <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:message value=\"msg_example,two\" escaped=\"escaped\" escapedamp='true'/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);






        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>Description &amp; &lt;a href=\"\"&gt;two&lt;/a&gt;</body></html>", render_);
    }

    @Test
    public void processMessages12Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:message value=\"msg_example,two\" escapedamp='true'/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);






        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>Description <a href=\"\">two</a></body></html>", render_);
    }

    @Test
    public void processMessages13Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &amp;lt;{0}&amp;gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:message value=\"msg_example,three\" escapedamp='true'><param value=\"''\" quoted=\"quoted\"/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);






        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>desc &lt;''&gt;</body></html>", render_);

    }

    @Test
    public void processMessages14Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &amp;lt;{0}&amp;gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><a class=\"a_class\">EXAMPLE</a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);






        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><a class=\"a_class\">EXAMPLE</a></body></html>", render_);
    }

    @Test
    public void processMessages15Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &amp;lt;{0}&amp;gt;\nfour=''asp''\ntitle=My page title";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><head><title><c:message value=\"msg_example,title\" escapedamp='true'/></title></head><body><a class=\"a_class\">EXAMPLE</a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);






        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><head><title>My page title</title></head><body><a class=\"a_class\">EXAMPLE</a></body></html>", render_);
    }

    @Test
    public void processMessages16Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\"><span>two</span></a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:message value=\"msg_example,two\" escapedamp='true'/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);






        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>Description <a href=\"\"><span>two</span></a></body></html>", render_);
    }

    @Test
    public void processSubmitTags1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:submit message=\"msg_example,three\" param0=\"''\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);







        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><input type=\"submit\" value=\"desc &amp;lt;''&amp;gt;\"/></body></html>", render_);
    }

    @Test
    public void processSubmitTags2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:submit message=\"msg_example,three\" param0=\"$typedString\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);







        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><input type=\"submit\" value=\"desc &amp;lt;TITLE&amp;gt;\"/></body></html>", render_);
    }

    @Test
    public void processSubmitTags3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:submit message=\"sample/file,three\" param0=\"$typedString\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);







        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><input type=\"submit\" value=\"desc &amp;lt;TITLE&amp;gt;\"/></body></html>", render_);
    }

    @Test
    public void processTitles1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:message value=\"msg_example,three\" escaped=\"escaped\"><param value=\"''\" quoted=\"quoted\"/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);








        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>desc &amp;lt;''&amp;gt;</body></html>", render_);
    }

    @Test
    public void processTitles2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:a value=\"msg_example,three\" param0=\"sample\" c:command='$ex'>Content</c:a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_one", bean_);
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);








        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><a href='' n-a='0' title=\"desc &amp;lt;sample&amp;gt;\" c:command='$bean_two.ex'>Content</a></body></html>", render_);
    }

    @Test
    public void processTitles3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:a value=\"msg_example,three\" param0=\"$typedString\">Content</c:a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);








        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><a title=\"desc &amp;lt;TITLE&amp;gt;\">Content</a></body></html>", render_);
    }

    @Test
    public void processTitles4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:a value=\"sample/file,three\" param0=\"$typedString\">Content</c:a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);








        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><a title=\"desc &amp;lt;TITLE&amp;gt;\">Content</a></body></html>", render_);
    }

    @Test
    public void processRadio1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><form><input name=\"typedString\" type=\"text\"/><input type=\"text\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);









        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><form n-f=\"0\"><input n-i=\"0\" name=\"bean_two.typedString\" type=\"text\"/><input type=\"text\"/></form></body></html>", render_);
    }

    @Test
    public void processRadio2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_three\" xmlns:c='javahtml'><body>HEAD<form action=\"\" c:command=\"page1.html\" name=\"myform\"><c:for var=\"n\" list=\"numbers\"><input type=\"radio\" name=\"index\" c:varValue=\"n;\"/></c:for><c:for var=\"n\" list=\"numbersTwo\"><input type=\"radio\" name=\"indexTwo\" c:varValue=\"n;\"/></c:for><input type=\"submit\" value=\"OK\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        BeanThree bean_ = new BeanThree();
        
        bean_.setScope("session");
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_three", bean_);
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
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);









        String render_ = FormatHtml.processHtml(doc_, "bean_three", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>HEAD<form n-f=\"0\" action=\"\" c:command=\"page1.html\" name=\"myform\"><input n-i=\"0\" name=\"bean_three.index\" type=\"radio\" value=\"2\"/><input n-i=\"0\" checked=\"checked\" name=\"bean_three.index\" type=\"radio\" value=\"4\"/><input n-i=\"0\" name=\"bean_three.index\" type=\"radio\" value=\"6\"/><input n-i=\"1\" name=\"bean_three.indexTwo\" type=\"radio\" value=\"2\"/><input n-i=\"1\" name=\"bean_three.indexTwo\" type=\"radio\" value=\"4\"/><input n-i=\"1\" checked=\"checked\" name=\"bean_three.indexTwo\" type=\"radio\" value=\"6\"/><input type=\"submit\" value=\"OK\"/></form></body></html>", render_);
    }

    @Test
    public void processRadio3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_six\" xmlns:c='javahtml'><body>HEAD<form action=\"\" c:command=\"page1.html\" name=\"myform\"><input className=\"code.formathtml.classes.EnumNumber\" type=\"radio\" name=\"myEnumOne\" value=\"ONE\"/><input className=\"code.formathtml.classes.EnumNumber\" type=\"radio\" name=\"myEnumOne\" value=\"TWO\"/><input type=\"submit\" value=\"OK\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        BeanSix bean_ = new BeanSix();
        
        bean_.setScope("session");
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_six", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page1.html");
        conf_.setValidators(new StringMap<Validator>());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        conf_.setNavigation(new StringMap<StringMap<String>>());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);









        String render_ = FormatHtml.processHtml(doc_, "bean_six", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>HEAD<form n-f=\"0\" action=\"\" c:command=\"page1.html\" name=\"myform\"><input n-i=\"0\" className=\"code.formathtml.classes.EnumNumber\" type=\"radio\" name=\"bean_six.myEnumOne\" value=\"ONE\"/><input n-i=\"0\" className=\"code.formathtml.classes.EnumNumber\" type=\"radio\" name=\"bean_six.myEnumOne\" value=\"TWO\"/><input type=\"submit\" value=\"OK\"/></form></body></html>", render_);
    }


    @Test
    public void processRadio4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_six\" xmlns:c='javahtml'><body>HEAD<form action=\"\" c:command=\"page1.html\" name=\"myform\"><input className=\"code.formathtml.classes.EnumNumber\" type=\"radio\" name=\"myEnumTwo\" value=\"ONE\"/><input className=\"code.formathtml.classes.EnumNumber\" type=\"radio\" name=\"myEnumTwo\" value=\"TWO\"/><input type=\"submit\" value=\"OK\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        BeanSix bean_ = new BeanSix();
        
        bean_.setScope("session");
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_six", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page1.html");
        conf_.setValidators(new StringMap<Validator>());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        conf_.setNavigation(new StringMap<StringMap<String>>());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);









        String render_ = FormatHtml.processHtml(doc_, "bean_six", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>HEAD<form n-f=\"0\" action=\"\" c:command=\"page1.html\" name=\"myform\"><input n-i=\"0\" className=\"code.formathtml.classes.EnumNumber\" checked=\"checked\" type=\"radio\" name=\"bean_six.myEnumTwo\" value=\"ONE\"/><input n-i=\"0\" className=\"code.formathtml.classes.EnumNumber\" type=\"radio\" name=\"bean_six.myEnumTwo\" value=\"TWO\"/><input type=\"submit\" value=\"OK\"/></form></body></html>", render_);
    }

    @Test
    public void processRadio5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_six\" xmlns:c='javahtml'><body>HEAD<form action=\"\" c:command=\"page1.html\" name=\"myform\"><input c:className=\"code.formathtml.classes.EnumNumber\" type=\"radio\" name=\"myEnumThree\" value=\"ONE\"/><input c:className=\"code.formathtml.classes.EnumNumber\" type=\"radio\" name=\"myEnumThree\" value=\"TWO\"/><input type=\"submit\" value=\"OK\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        BeanSix bean_ = new BeanSix();
        
        bean_.setScope("session");
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_six", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setFirstUrl("page1.html");
        conf_.setValidators(new StringMap<Validator>());
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        conf_.setNavigation(new StringMap<StringMap<String>>());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);









        String render_ = FormatHtml.processHtml(doc_, "bean_six", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>HEAD<form n-f=\"0\" action=\"\" c:command=\"page1.html\" name=\"myform\"><input n-i=\"0\" c:className=\"code.formathtml.classes.EnumNumber\" type=\"radio\" name=\"bean_six.myEnumThree\" value=\"ONE\"/><input n-i=\"0\" c:className=\"code.formathtml.classes.EnumNumber\" type=\"radio\" name=\"bean_six.myEnumThree\" value=\"TWO\"/><input type=\"submit\" value=\"OK\"/></form></body></html>", render_);
    }

    @Test
    public void processVarInputName1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><form><input name=\"typedString\" type=\"text\"/><input type=\"text\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);










        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><form n-f=\"0\"><input n-i=\"0\" name=\"bean_two.typedString\" type=\"text\"/><input type=\"text\"/></form></body></html>", render_);
    }

    @Test
    public void processVarInputName2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select name=\"chosenNumber\" list=\"combobox\"/></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);










        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select n-i=\"0\" c:className=\"\" name=\"bean_one.chosenNumber\"><option value=\"ONE\">ONE</option><option value=\"TWO\">TWO</option><option value=\"THREE\">THREE</option><option value=\"FOUR\">FOUR</option><option value=\"FIVE\">FIVE</option><option value=\"SIX\">SIX</option></select></body></html>", render_);
    }

    @Test
    public void processVarInputName3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:select list=\"combobox\"/></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);










        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><select c:className=\"\" name=\"\"><option value=\"ONE\">ONE</option><option value=\"TWO\">TWO</option><option value=\"THREE\">THREE</option><option value=\"FOUR\">FOUR</option><option value=\"FIVE\">FIVE</option><option value=\"SIX\">SIX</option></select></body></html>", render_);
    }

    @Ignore
    @Test
    public void processVarInputName4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:field value=\"combobox\"/></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);











        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);

        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><c_field value=\"combobox\"/></body></html>", render_);
    }

    @Ignore
    @Test
    public void processVarInputName5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:field value=\"1\"/></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);











        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><c_field value=\"1\"/></body></html>", render_);
    }

    @Ignore
    @Test
    public void processVarInputName6Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:field/></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);











        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><c_field/></body></html>", render_);
    }

    @Test
    public void processVarInputName7Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><a href=\"$element\">LINK_ONE</a><a href=\"\" c:command=\"$element\">LINK_TWO</a><a/></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);










        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><a n-a=\"0\" href=\"$bean_one.element\">LINK_ONE</a><a n-a=\"1\" c:command=\"$bean_one.element\" href=\"\">LINK_TWO</a><a/></body></html>", render_);
    }

    @Test
    public void processVarInputName8Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><form action=\"$element\">LINK_ONE</form><form action=\"\" c:command=\"$element\">LINK_TWO</form><form/></body></html>";
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);










        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><form n-f=\"0\" action=\"$bean_one.element\">LINK_ONE</form><form n-f=\"1\" action=\"\" c:command=\"$bean_one.element\">LINK_TWO</form><form n-f=\"2\"/></body></html>", render_);
    }

    @Test
    public void processVarInputName9Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><form><textarea name=\"typedString\" c:varValue=\"typedString\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setChecked(false);
        beanTwo_.setTypedString("TYPED");
        conf_.getBeans().put("bean_two", beanTwo_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);










        String render_ = FormatHtml.processHtml(doc_, "bean_two", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><form n-f=\"0\"><textarea n-i=\"0\" name=\"bean_two.typedString\">TYPED</textarea></form></body></html>", render_);
    }

    @Test
    public void processImages1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body>NO IMAGE</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("my_image", "encoded_image");
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);










        
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>NO IMAGE</body></html>", render_);
    }

    @Test
    public void processImages2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><img src=\"my_image\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("my_image", "encoded_image");
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);










        
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><img src=\"encoded_image\"/></body></html>", render_);
    }

    @Test
    public void processImages3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><img src=\"\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("my_image", "encoded_image");
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);










        
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><img src=\"\"/></body></html>", render_);
    }

    @Test
    public void processImages4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><img src=\"my_image\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("my_image", "encoded_image");
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
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);












        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><img src=\"encoded_image\"/></body></html>", render_);
    }

    @Test
    public void processImages5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><img src=\"my_image\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("my_image", "encoded_image");
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);











        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><img src=\"encoded_image\"/></body></html>", render_);
    }

    @Test
    public void processImagesTags1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><img src=\"\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("my_image", "encoded_image");
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);












        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><img src=\"\"/></body></html>", render_);
    }

    @Test
    public void processImagesTags2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:img src=\"\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("my_image", "encoded_image");
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
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);












        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><img src=\"\"/></body></html>", render_);
    }

    @Test
    public void processCss1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><img src=\"\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("my_image", "encoded_image");
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
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);













        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><img src=\"\"/></body></html>", render_);
    }

    @Test
    public void processCss2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><head><link/></head><body><img src=\"\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("my_image", "encoded_image");
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
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);













        assertXmlEqualRuntime("<html xmlns:c='javahtml'><head><link/></head><body><img src=\"\"/></body></html>", render_);
    }

    @Test
    public void processCss3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><head><link rel=\"stylesheet\">CONTENT</link></head><body><p>ERROR</p><img src=\"\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("my_image", "encoded_image");
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
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);













        assertXmlEqualRuntime("<html xmlns:c='javahtml'><head><link rel=\"stylesheet\">CONTENT</link></head><body><p>ERROR</p><img src=\"\"/></body></html>", render_);
    }

    @Test
    public void processCss4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><head><link href=\"main.css\" rel=\"stylesheet\"/></head><body><p>ERROR</p><img src=\"\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("my_image", "encoded_image");
        files_.put("main.css", ".classTest{color:blue;}");
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
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);














        assertXmlEqualRuntime("<html xmlns:c='javahtml'><head><link href=\"main.css\" rel=\"stylesheet\"/><style>.classTest{color:blue;}</style></head><body><p>ERROR</p><img src=\"\"/></body></html>", render_);
    }

    @Test
    public void processCss5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><head><link href=\"main.css\" rel=\"stylesheet\"/></head><style>CONTENT</style><body><p>ERROR</p><img src=\"\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("my_image", "encoded_image");
        files_.put("main.css", ".classTest{color:blue;}");
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
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);














        assertXmlEqualRuntime("<html xmlns:c='javahtml'><head><link href=\"main.css\" rel=\"stylesheet\"/><style>.classTest{color:blue;}</style></head><style>.classTest{color:blue;}CONTENT</style><body><p>ERROR</p><img src=\"\"/></body></html>", render_);
    }

    @Test
    public void processCss6Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><head><link href=\"main.css\" rel=\"stylesheet\"/></head><style/><body><p>ERROR</p><img src=\"\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("my_image", "encoded_image");
        files_.put("main.css", ".classTest{color:blue;}");
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
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);














        assertXmlEqualRuntime("<html xmlns:c='javahtml'><head><link href=\"main.css\" rel=\"stylesheet\"/><style>.classTest{color:blue;}</style></head><style>.classTest{color:blue;}</style><body><p>ERROR</p><img src=\"\"/></body></html>", render_);
    }

    @Test
    public void processCss7Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><head><style>CONTENT</style><link href=\"main.css\" rel=\"stylesheet\"/></head><body><p>ERROR</p><img src=\"\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("my_image", "encoded_image");
        files_.put("main.css", ".classTest{color:blue;}");
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
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);














        assertXmlEqualRuntime("<html xmlns:c='javahtml'><head><style>CONTENT.classTest{color:blue;}</style><link href=\"main.css\" rel=\"stylesheet\"/></head><body><p>ERROR</p><img src=\"\"/></body></html>", render_);
    }

    @Test
    public void processCss8Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><head><style/><link href=\"main.css\" rel=\"stylesheet\"/></head><body><p>ERROR</p><img src=\"\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("my_image", "encoded_image");
        files_.put("main.css", ".classTest{color:blue;}");
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
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);














        assertXmlEqualRuntime("<html xmlns:c='javahtml'><head><style>.classTest{color:blue;}</style><link href=\"main.css\" rel=\"stylesheet\"/></head><body><p>ERROR</p><img src=\"\"/></body></html>", render_);
    }

    @Test
    public void processCss9Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><head><link href=\"main.css\" rel=\"stylesheet\"/><link href=\"main2.css\" rel=\"stylesheet\"/></head><body><p>ERROR</p><img src=\"\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("my_image", "encoded_image");
        files_.put("main.css", ".classTest{color:blue;}");
        files_.put("main2.css", ".secClassTest{color:red;}");
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
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);















        assertXmlEqualRuntime("<html xmlns:c='javahtml'><head><link href=\"main.css\" rel=\"stylesheet\"/><style>.classTest{color:blue;}.secClassTest{color:red;}</style><link href=\"main2.css\" rel=\"stylesheet\"/></head><body><p>ERROR</p><img src=\"\"/></body></html>", render_);
    }

    @Test
    public void processCss10Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><head><style>CONTENT</style><link href=\"main.css\" rel=\"stylesheet\"/><link href=\"main2.css\" rel=\"stylesheet\"/></head><body><p>ERROR</p><img src=\"\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("my_image", "encoded_image");
        files_.put("main.css", ".classTest{color:blue;}");
        files_.put("main2.css", ".secClassTest{color:red;}");
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
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);














        assertXmlEqualRuntime("<html xmlns:c='javahtml'><head><style>CONTENT.classTest{color:blue;}.secClassTest{color:red;}</style><link href=\"main.css\" rel=\"stylesheet\"/><link href=\"main2.css\" rel=\"stylesheet\"/></head><body><p>ERROR</p><img src=\"\"/></body></html>", render_);
    }

    @Test
    public void processCss11Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><head><style/><link href=\"main.css\" rel=\"stylesheet\"/><link href=\"main2.css\" rel=\"stylesheet\"/></head><body><p>ERROR</p><img src=\"\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("my_image", "encoded_image");
        files_.put("main.css", ".classTest{color:blue;}");
        files_.put("main2.css", ".secClassTest{color:red;}");
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
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);














        assertXmlEqualRuntime("<html xmlns:c='javahtml'><head><style>.classTest{color:blue;}.secClassTest{color:red;}</style><link href=\"main.css\" rel=\"stylesheet\"/><link href=\"main2.css\" rel=\"stylesheet\"/></head><body><p>ERROR</p><img src=\"\"/></body></html>", render_);
    }

    @Test
    public void processCss12Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><head><link href=\"main.css\" rel=\"stylesheet\" param0='blue'/></head><body><p>ERROR</p><img src=\"\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("my_image", "encoded_image");
        files_.put("main.css", ".classTest'{'color:{0};'}'");
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
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);














        assertXmlEqualRuntime("<html xmlns:c='javahtml'><head><link href=\"main.css\" rel=\"stylesheet\"/><style>.classTest{color:blue;}</style></head><body><p>ERROR</p><img src=\"\"/></body></html>", render_);
    }


    @Test
    public void processCss13Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><head><link href=\"main.css\" rel=\"stylesheet\" param0='$getComposite().getStrings().get(2i)'/></head><body><p>ERROR</p><img src=\"\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("my_image", "encoded_image");
        files_.put("main.css", ".classTest'{'color:{0};'}'");
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().getStrings().add("blue");
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
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);














        assertXmlEqualRuntime("<html xmlns:c='javahtml'><head><link href=\"main.css\" rel=\"stylesheet\"/><style>.classTest{color:blue;}</style></head><body><p>ERROR</p><img src=\"\"/></body></html>", render_);
    }

    @Test
    public void processScript1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><img src=\"\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("my_image", "encoded_image");
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);














        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><img src=\"\"/></body></html>", render_);
    }

    @Test
    public void processScript2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String script_ = "function displayResult() { document.getElementById(\"myHeader\").innerHTML = \"Have a nice day!\"; }";
        String html_ = "<html xmlns:c='javahtml'><body><script/><img src=\"\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("my_image", "encoded_image");
        files_.put("myscript.js", script_);
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);














        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><script/><img src=\"\"/></body></html>", render_);
    }

    @Test
    public void processScript3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String script_ = "function displayResult() { document.getElementById(\"myHeader\").innerHTML = \"Have a nice day!\"; }";
        String html_ = "<html xmlns:c='javahtml'><body><script href=\"myscript.js\"/><img src=\"\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("my_image", "encoded_image");
        files_.put("myscript.js", script_);
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
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);














        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><script href=\"myscript.js\" type=\"text/javascript\">function displayResult() { document.getElementById(\"myHeader\").innerHTML = \"Have a nice day!\"; }</script><img src=\"\"/></body></html>", render_);
    }

    @Test
    public void processSpansErrors1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><img src=\"\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("my_image", "encoded_image");
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
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);















        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><img src=\"\"/></body></html>", render_);
    }

    @Test
    public void processSpansErrors2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String script_ = "function displayResult() { document.getElementById(\"myHeader\").innerHTML = \"Have a nice day!\"; }";
        String html_ = "<html xmlns:c='javahtml'><body><span/><img src=\"\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("my_image", "encoded_image");
        files_.put("myscript.js", script_);
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
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);














        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><span/><img src=\"\"/></body></html>", render_);
    }

    @Test
    public void processSpansErrors3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String script_ = "function displayResult() { document.getElementById(\"myHeader\").innerHTML = \"Have a nice day!\"; }";
        String html_ = "<html xmlns:c='javahtml'><body><span c:for=\"id_loc\"/><img src=\"\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("my_image", "encoded_image");
        files_.put("myscript.js", script_);
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
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);














        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><span c:for=\"id_loc\"> </span><img src=\"\"/></body></html>", render_);
    }

    @Test
    public void processSpansErrors4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String script_ = "function displayResult() { document.getElementById(\"myHeader\").innerHTML = \"Have a nice day!\"; }";
        String html_ = "<html xmlns:c='javahtml'><body><span for=\"id_loc\">But no hide</span><img src=\"\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("my_image", "encoded_image");
        files_.put("myscript.js", script_);
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
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><span for=\"id_loc\">But no hide</span><img src=\"\"/></body></html>", render_);
    }

    @Test
    public void processHtmlJava1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:a value=\"sample/file,three\" param0=\"$typedString\">Content</c:a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        conf_.setHtml(html_);
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processHtmlJava(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><a title=\"desc &amp;lt;TITLE2&amp;gt;\">Content</a></body></html>", render_);
        assertEq("TITLE2", beanTwo_.getTypedString());
    }

    @Test
    public void processHtmlJava2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html xmlns:c='javahtml'><body><c:a value=\"sample/file,three\" param0=\"TITLE\">Content</c:a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        conf_.setHtml(html_);
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processHtmlJava(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><a title=\"desc &amp;lt;TITLE&amp;gt;\">Content</a></body></html>", render_);
    }

    @Test
    public void processHtmlJava3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><c:tr_begin/>Content<c:tr_end/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        conf_.setHtml(html_);
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processHtmlJava(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><tr>Content</tr></body></html>", render_);
        assertEq("TITLE2", beanTwo_.getTypedString());
    }

    @Test
    public void processHtmlJava4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html xmlns:c='javahtml'><body><c:tr_begin my_attr=\"ex\"/>Content<c:tr_end/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        conf_.setHtml(html_);
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processHtmlJava(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><tr my_attr=\"ex\">Content</tr></body></html>", render_);
    }

    @Test
    public void processHtmlJava5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><tr>Content1<c:tr_end/><c:tr_begin/>Content2</tr></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processHtmlJava(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><tr>Content1</tr><tr>Content2</tr></body></html>", render_);
        assertEq("TITLE2", beanTwo_.getTypedString());
    }

    @Test
    public void processHtmlJava6Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html xmlns:c='javahtml'><body><tr>Content1<c:tr_end/><c:tr_begin my_attr=\"ex\"/>Content2</tr></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processHtmlJava(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><tr>Content1</tr><tr my_attr=\"ex\">Content2</tr></body></html>", render_);
    }

    @Test
    public void processHtmlJava7Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html xmlns:c='javahtml'><body><tr>Content1<c:tr_end disappear=\"disappear\"/><c:tr_begin my_attr=\"ex\"/>Content2</tr></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processHtmlJava(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><tr>Content1</tr><tr my_attr=\"ex\">Content2</tr></body></html>", render_);
    }

    @Test
    public void processImports1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc <{0}>\nfour=''asp''";
        String html_ = "<html xmlns:c='javahtml'><body><c:a value=\"sample/file,three\" param0=\"TITLE\">Content</c:a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><a title=\"desc &lt;TITLE&gt;\">Content</a></body></html>", render_);
    }

    @Test
    public void processImports2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html xmlns:c='javahtml'><body><a href=\"DELETE\" c:command=\"go\">Content</a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
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
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><a n-a=\"0\" c:command=\"go\" href=\"\">Content</a></body></html>", render_);
    }

    @Test
    public void processImports3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body><c:import page=\"\"/></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><a href=\"DELETE\" c:command=\"go\">Content</a></body></html>";
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
    public void processImports4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body><c:import page=\"page2.html\"/></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><a href=\"DELETE\" c:command=\"go\">Content</a></body></html>";
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
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><a n-a=\"0\" c:command=\"go\" href=\"\">Content</a></body></html>", render_);
    }

    @Test
    public void processImports5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body><c:import page=\"page2.html\"><a/><c:package name=\"code.formathtml.classes\"><a/><c:class name=\"BeanTwo\"><a/><c:field prepare=\"$intern.typedString=message\"><a/></c:field></c:class></c:package></c:import></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><a href=\"DELETE\" c:command=\"go\">{typedString}</a></body></html>";
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
        bean_.getForms().put("key", "sample_value");
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
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><a n-a=\"0\" c:command=\"go\" href=\"\">Test {0}2</a></body></html>", render_);
        assertEq(1, beanTwo_.getForms().size());
        assertEq("key", beanTwo_.getForms().getKeys().first());
        assertEq("sample_value", (String)beanTwo_.getForms().values().first());
    }

    @Test
    public void processImports6Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body>HEAD<a href=\"\"/><c:import page=\"page2.html\"><a/><c:package name=\"code.formathtml.classes\"><a/><c:class name=\"BeanTwo\"><a/><c:field prepare=\"$intern.typedInt=4i\"><a/></c:field></c:class></c:package></c:import></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body> NEXT<a href=\"DELETE\" c:command=\"go\">{typedInt}</a></body></html>";
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
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>HEAD<a href=\"\"/> NEXT<a n-a=\"0\" c:command=\"go\" href=\"\">4</a></body></html>", render_);
    }

    @Test
    public void processImports7Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body>HEAD<a href=\"\"/><c:import page=\"page2.html\"><a/><c:package name=\"code.formathtml.classes\"><a/><c:class name=\"BeanTwo\"><a/><c:field prepare=\"$intern.typedInt=$($int)4\"><a/></c:field></c:class></c:package></c:import></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body> NEXT<form action=\"DELETE\" c:command=\"go\">{typedInt}</form><form action=\"go\">{typedInt}</form></body></html>";
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
        conf_.setHtml(htmlTwo_);
        conf_.setDocument(DocumentBuilder.parseSax(htmlTwo_));
        setup(conf_);
        String render_ = FormatHtml.processImports(htmlTwo_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body> NEXT<form n-f=\"0\" action=\"\" c:command=\"go\">0</form><form n-f=\"1\" action=\"go\">0</form></body></html>", render_);
    }

    
    @Ignore
    @Test
    public void processImports8Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body>HEAD<a href=\"\"/><c:import page=\"page1.html\"/></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body> NEXT<form action=\"DELETE\" c:command=\"go\">{typedInt}</form><form action=\"go\">{typedInt}</form></body></html>";
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
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>HEAD<a href=\"\"/>HEAD<a href=\"\"/>HEAD<a href=\"\"/>HEAD<a href=\"\"/></body></html>", render_);

    }

    @Test
    public void processImports9Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body><c:import page=\"page2.html\" keepfields=\"y\"><a/><c:package name=\"code.formathtml.classes\"><a/><c:class name=\"BeanTwo\"><a/><c:field prepare=\"$intern.typedString=message\"><a/></c:field></c:class></c:package></c:import></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><a href=\"DELETE\" c:command=\"go\">{typedString}</a></body></html>";
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
        bean_.getForms().put("key", "sample_value");
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
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processImports(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><a n-a=\"0\" c:command=\"go\" href=\"\">Test {0}2</a></body></html>", render_);
        assertEq(0, beanTwo_.getForms().size());
    }

    @Test
    public void processImports10Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body><c:import page=\"page2.html\"><a/><c:package name=\"code.formathtml.classes\"><a/><c:class name=\"BeanTwo\"><a/><c:field prepare=\"$intern.typedString=message\"><a/></c:field></c:class></c:package></c:import>Next text</body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><a href=\"DELETE\" c:command=\"go\">{typedString}</a></body></html>";
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
        bean_.getForms().put("key", "sample_value");
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
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processImports(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><a n-a=\"0\" c:command=\"go\" href=\"\">Test {0}2</a>Next text</body></html>", render_);
        assertEq(1, beanTwo_.getForms().size());
        assertEq("key", beanTwo_.getForms().getKeys().first());
        assertEq("sample_value", (String)beanTwo_.getForms().values().first());
    }
    @Test
    public void processImports11Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body><c:import page=\"page2.html\" keepfields=\"y\"><a/><c:package name=\"code.formathtml.classes\"><a/><c:class name=\"BeanTwo\"><a/><c:field prepare=\"$intern.typedString=message\"><a/></c:field></c:class></c:package><c:form form=\"key\"/></c:import></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><a href=\"DELETE\" c:command=\"go\">{typedString}</a></body></html>";
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
        bean_.getForms().put("key", "sample_value");
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
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processImports(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><a n-a=\"0\" c:command=\"go\" href=\"\">Test {0}2</a></body></html>", render_);
        assertEq(1, beanTwo_.getForms().size());
        assertEq("key", beanTwo_.getForms().getKeys().first());
        assertEq("sample_value", (String)beanTwo_.getForms().values().first());
    }

    @Test
    public void processImports12Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=<html xmlns:c=\"javahtml\" xmlns=\"javahtml\">Description <a href=\"\" c:command=\"$go\">two</a></html>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body><c:import page=\"page2.html\" keepfields=\"y\"><a/><c:package name=\"code.formathtml.classes\"><a/><c:class name=\"BeanTwo\"><a/><c:field prepare=\"$intern.typedString=message\"><a/></c:field></c:class></c:package><c:form form=\"key\"/></c:import></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><a href=\"DELETE\" c:command=\"go\">{typedString}</a><c:message value='msg_example,two'/></body></html>";
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
        bean_.getForms().put("key", "sample_value");
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
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processImports(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><a n-a=\"0\" c:command=\"go\" href=\"\">Test {0}2</a>Description <a n-a='1' href=\"\" c:command='$bean_two.go'>two</a></body></html>", render_);
        assertEq(1, beanTwo_.getForms().size());
        assertEq("key", beanTwo_.getForms().getKeys().first());
        assertEq("sample_value", (String)beanTwo_.getForms().values().first());
    }

    @Test
    public void processImports13Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=<html>Description <a href=\"$go\">two</a></html>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body><c:import page=\"page2.html\" keepfields=\"y\"><a/><c:package name=\"code.formathtml.classes\"><a/><c:class name=\"BeanTwo\"><a/><c:field prepare=\"$intern.typedString=message\"><a/></c:field></c:class></c:package><c:form form=\"key\"/></c:import></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><a href=\"DELETE\" c:command=\"go\">{typedString}</a><c:message value='msg_example,two'/></body></html>";
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
        bean_.getForms().put("key", "sample_value");
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
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processImports(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><a n-a=\"0\" c:command=\"go\" href=\"\">Test {0}2</a>Description <a n-a='1' href=\"$bean_two.go\">two</a></body></html>", render_);
        assertEq(1, beanTwo_.getForms().size());
        assertEq("key", beanTwo_.getForms().getKeys().first());
        assertEq("sample_value", (String)beanTwo_.getForms().values().first());
    }
    @Test
    public void processImports14Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body><c:import page=\"page2.html\"><a/><c:package name=\"code.formathtml.classes\"><a/><c:class name=\"BeanTwo\"><a/><c:field prepare=\"$intern.setTypedString(message)\"><a/></c:field></c:class></c:package></c:import></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><a href=\"DELETE\" c:command=\"go\">{typedString}</a></body></html>";
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
        bean_.getForms().put("key", "sample_value");
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
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processImports(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><a n-a=\"0\" c:command=\"go\" href=\"\">Test {0}2</a></body></html>", render_);
        assertEq(1, beanTwo_.getForms().size());
        assertEq("key", beanTwo_.getForms().getKeys().first());
        assertEq("sample_value", (String)beanTwo_.getForms().values().first());
    }

    @Test
    public void processImports15Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body>HEAD<a href=\"\"/><c:import page=\"page2.html\"><a/><c:package name=\"code.formathtml.classes\"><a/><c:class name=\"BeanTwo\"><a/><c:field prepare=\"$intern.setTypedInt(4i)\"><a/></c:field></c:class></c:package></c:import></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body> NEXT<a href=\"DELETE\" c:command=\"go\">{typedInt}</a></body></html>";
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
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processImports(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>HEAD<a href=\"\"/> NEXT<a n-a=\"0\" c:command=\"go\" href=\"\">4</a></body></html>", render_);
    }

    @Test
    public void processImports16Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body>HEAD<a href=\"\"/><c:import page=\"page2.html\"><a/><c:package name=\"code.formathtml.classes\"><a/><c:class name=\"BeanTwo\"><a/><c:field prepare=\"$intern.setTypedInt(4i)\"><a/></c:field></c:class></c:package></c:import></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><emb><ed>T</ed><ed>T</ed></emb> NEXT<a href=\"DELETE\" c:command=\"go\">{typedInt}</a></body></html>";
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
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processImports(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>HEAD<a href=\"\"/><emb><ed>T</ed><ed>T</ed></emb> NEXT<a n-a=\"0\" c:command=\"go\" href=\"\">4</a></body></html>", render_);
    }

    @Test
    public void processImports17Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body><c:import page=\"page2.html\"><a/><c:package name=\"code.formathtml.classes\"><a/><c:class name=\"BeanTwo\"><a/><c:field prepare=\"$intern.typedString=message\"><a/></c:field></c:class></c:package></c:import>Next text</body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><a href=\"DELETE\" c:command=\"go\">{typedString}</a></body></html>";
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
        bean_.getForms().put("key", "sample_value");
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
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processImports(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><a n-a=\"0\" c:command=\"go\" href=\"\">Test {0}2</a>Next text</body></html>", render_);
        assertEq(1, beanTwo_.getForms().size());
        assertEq("key", beanTwo_.getForms().getKeys().first());
        assertEq("sample_value", (String)beanTwo_.getForms().values().first());
    }

    @Test
    public void processImports18Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body><c:import page=\"page2.html\"><a/></c:import>Next text</body></html>";
        String htmlTwo_ = "<html xmlns:c='javahtml'><body><a href=\"DELETE\" c:command=\"go\">typedString</a></body></html>";
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
        bean_.getForms().put("key", "sample_value");
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
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processImports(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><a n-a=\"0\" c:command=\"go\" href=\"\">typedString</a>Next text</body></html>", render_);
        assertEq(0, beanTwo_.getForms().size());
    }

    @Test
    public void processImports19Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body><div><c:import page=\"page2.html\"><a/></c:import></div>Next text</body></html>";
        String htmlTwo_ = "<html xmlns:c='javahtml'><body><a href=\"DELETE\" c:command=\"go\">typedString</a></body></html>";
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
        bean_.getForms().put("key", "sample_value");
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
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processImports(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><div><a n-a=\"0\" c:command=\"go\" href=\"\">typedString</a></div>Next text</body></html>", render_);
        assertEq(0, beanTwo_.getForms().size());
    }

    @Test
    public void processImports20Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body><div><c:import page=\"page2.html\"><a/></c:import></div>Next text</body></html>";
        String htmlTwo_ = "<html xmlns:c='javahtml'><body><a href=\"DELETE\" c:command=\"go\">typedString</a><c:return/>NOT DISPLAYED</body></html>";
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
        bean_.getForms().put("key", "sample_value");
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
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processImports(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><div><a n-a=\"0\" c:command=\"go\" href=\"\">typedString</a></div>Next text</body></html>", render_);
        assertEq(0, beanTwo_.getForms().size());
    }

    @Test
    public void processImports21Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body><div><c:import page=\"page2.html\"><a/></c:import></div>Next text</body></html>";
        String htmlTwo_ = "<html xmlns:c='javahtml'><body><a href=\"DELETE\" c:command=\"go\">typedString</a><c:exit/>NOT DISPLAYED</body></html>";
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
        bean_.getForms().put("key", "sample_value");
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
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processImports(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><div><a n-a=\"0\" c:command=\"go\" href=\"\">typedString</a></div></body></html>", render_);
        assertEq(0, beanTwo_.getForms().size());
    }

    @Test
    public void processImports22Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"list\" expression=\"$new code.util.StringList()\" className='code.util.StringList'/>{list;.size()}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>0</body></html>", render_);
    }

    @Test
    public void processImports23Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"list\" expression=\"$new code.formathtml.classes.GeneObjs()\" className=\"code.formathtml.classes.GeneObjs\"/><c:set var=\"listTwo\" expression=\"$new code.formathtml.classes.GeneObjs(list;.)\" className=\"code.formathtml.classes.GeneObjs\"/>{listTwo;.size()}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>0</body></html>", render_);
    }

    @Test
    public void processImports24Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"list\" expression=\"$new code.formathtml.classes.GeneObjs()\"/><c:set var=\"listTwo\" expression=\"$new code.formathtml.classes.GeneObjs($vararg(java.lang.Object),$firstopt(list;.))\" className=\"code.formathtml.classes.GeneObjs\"/>{listTwo;.size()}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>1</body></html>", render_);
    }


    @Test
    public void processImports25Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"elt\" expression=\"2\"/><c:set var=\"elt\" expression=\"elt;.\" className=\"java.lang.Object\"/><c:set var=\"list\" expression=\"$new code.formathtml.classes.GeneObjs()\" className='code.formathtml.classes.GeneObjs'/><c:set expression=\"list;.add(elt;.)\"/>{list;.size()}-{list;.get(0i)}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>1-2</body></html>", render_);
    }

    @Test
    public void processImports26Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body>{getDouble(1.5)}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        BeanSeven bean_ = new BeanSeven();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getComposites().get(0).setStrings(new StringList("F"));
        bean_.getComposites().get(1).setStrings(new StringList("S"));
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_seven", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        conf_.setMathFactory(new SimpleMathFactory());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_seven", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>3.0</body></html>", render_);
    }

    @Test
    public void processImports27Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body>{$static(code.formathtml.classes.BeanUtil).sum(1i,2i)}_{$static(code.formathtml.classes.BeanUtil).NB_BEANS}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        BeanSeven bean_ = new BeanSeven();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getComposites().get(0).setStrings(new StringList("F"));
        bean_.getComposites().get(1).setStrings(new StringList("S"));
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_seven", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        conf_.setMathFactory(new SimpleMathFactory());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_seven", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>3_8</body></html>", render_);
    }


    @Test
    public void processImports28Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"str\" isstringconst=\"true\" expression=\"argument\"/><c:set var=\"compos\" expression=\"$new code.formathtml.classes.Composite(str;.)\" className='code.formathtml.classes.Composite'/>{compos;.string}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        BeanSeven bean_ = new BeanSeven();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getComposites().get(0).setStrings(new StringList("F"));
        bean_.getComposites().get(1).setStrings(new StringList("S"));
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_seven", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        conf_.setMathFactory(new SimpleMathFactory());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_seven", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>argument</body></html>", render_);
    }

    @Test
    public void processImports29Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"array\" expression=\"$new $int[2i]\" className='$int[]'/>{array;.length}_{array;.[0i]}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>2_0</body></html>", render_);
    }


    @Test
    public void processImports30Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"array\" expression=\"$new $int[2i][]\" className='$int[][]'/>{array;.length}_{array;.[0i]}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>2_</body></html>", render_);
    }

    @Test
    public void processImports31Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"arrays\" expression=\"$new $int[2i][]\" className='$int[][]'/><c:set var=\"array\" expression=\"$new $int[1i]\" className='$int[]'/><c:set expression=\"arrays;.[0i]=array;.\"/>{arrays;.length}_{arrays;.[0i].length}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>2_1</body></html>", render_);
    }


    @Test
    public void processImports32Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"arrays\" expression=\"$new $int[2i][]\" className=\"$int[][]\"/><c:set var=\"array\" expression=\"$new $int[1i]\" className=\"$int[]\"/><c:set expression=\"arrays;.[0i]=array;.\"/>{arrays;.length}_{arrays;.[0i].length}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>2_1</body></html>", render_);
    }

    @Test
    public void processImports33Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"arrays\" expression=\"$new java.lang.Integer[2i][]\" className=\"java.lang.Integer[][]\"/><c:set var=\"array\" expression=\"$new java.lang.Integer[1i]\" className=\"java.lang.Integer[]\"/><c:set expression=\"arrays;.[0i]=array;.\"/>{arrays;.length}_{arrays;.[0i].length}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>2_1</body></html>", render_);
    }

    @Test
    public void processImports34Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"arrays\" expression=\"$new java.lang.Object[2i][]\" className=\"java.lang.Object[][]\"/><c:set var=\"array\" expression=\"$new java.lang.Integer[1i]\" className=\"java.lang.Integer[]\"/><c:set expression=\"arrays;.[0i]=array;.\"/>{arrays;.length}_{arrays;.[0i].length}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>2_1</body></html>", render_);
    }

    @Test
    public void processImports35Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_seven\" xmlns:c='javahtml'><body><form c:command=\"$validateIntsSave\"><c:for var=\"i\" list=\"$new $int[]{1i,3i}\">{i;;}_{i;}<input type=\"text\" c:className=\"java.lang.Integer\" name=\"i;\" c:varValue=\"i;\"/></c:for></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        Configuration conf_ = newConfiguration();
        BeanSeven bean_ = new BeanSeven();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getComposites().get(0).setStrings(new StringList("F"));
        bean_.getComposites().get(1).setStrings(new StringList("S"));
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_seven", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        conf_.setMathFactory(new SimpleMathFactory());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_seven", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><form n-f='0' action='' c:command='$bean_seven.validateIntsSave'>0_1<input n-i='0' type='text' c:className='java.lang.Integer' name='bean_seven.i;' value='1'/>1_3<input n-i='1' type='text' c:className='java.lang.Integer' name='bean_seven.i;' value='3'/></form></body></html>", render_);
    }

    @Test
    public void processImports36Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_seven\" xmlns:c='javahtml'><body><c:set var='loc' expression='composite'/><c:if refeq='composite==loc;.'>EQ REF</c:if><c:else>DIFF EQ</c:else></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        BeanSeven bean_ = new BeanSeven();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getComposites().get(0).setStrings(new StringList("F"));
        bean_.getComposites().get(1).setStrings(new StringList("S"));
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_seven", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        conf_.setMathFactory(new SimpleMathFactory());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_seven", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>EQ REF</body></html>", render_);
    }

    @Test
    public void processImports37Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_seven\" xmlns:c='javahtml'><body><c:set var='str' expression='1' isstringconst='true'/><c:set var='o1' expression='$new code.formathtml.classes.RateEq(str;.)' className='code.formathtml.classes.RateEq'/><c:set var='o2' expression='$new code.formathtml.classes.RateEq(o1;.)' className='code.formathtml.classes.RateEq'/><c:if refeq='o1;.!=o2;.'>DIFF REF</c:if><c:else>EQ REF</c:else><br/><c:if condition='!o1;.eq(o2;.)'>DIFF OBJ</c:if><c:else>EQ OBJ</c:else></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        BeanSeven bean_ = new BeanSeven();
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getComposites().get(0).setStrings(new StringList("F"));
        bean_.getComposites().get(1).setStrings(new StringList("S"));
        Configuration conf_ = newConfiguration();
        conf_.setBeans(new StringMap<Bean>());
        conf_.getBeans().put("bean_seven", bean_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        conf_.setTranslators(new StringMap<Translator>());
        conf_.getTranslators().put("trans", new MyTranslator());
        conf_.setMathFactory(new SimpleMathFactory());
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_seven", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>DIFF REF<br/>EQ OBJ</body></html>", render_);
    }

    @Test
    public void processImports38Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"list\" expression=\"$new code.formathtml.classes.GeneObjs()\" className=\"code.formathtml.classes.GeneObjs\"/><c:set expression='list;.add($(java.lang.Object)1i)'/><c:set expression='list;.add($(java.lang.Object)2i)'/><c:set var=\"listTwo\" expression=\"$new code.formathtml.classes.GeneObjs(list;.)\" className=\"code.formathtml.classes.GeneObjs\"/>{listTwo;.size()}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>2</body></html>", render_);
    }


    @Test
    public void processImports39Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"list\" expression=\"$new code.formathtml.classes.GeneObjs()\" className=\"code.formathtml.classes.GeneObjs\"/><c:set expression='list;.add($(java.lang.Object)1i)'/><c:set expression='list;.add($(java.lang.Object)2i)'/><c:set var=\"listTwo\" expression=\"$new code.formathtml.classes.GeneObjs($vararg(java.lang.Object),$firstopt($(java.lang.Object)list;.))\" className=\"code.formathtml.classes.GeneObjs\"/>{listTwo;.size()}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>1</body></html>", render_);
    }

    @Test
    public void processImports40Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body>HEAD<a href=\"\"/><c:import page=\"page2.html\"><a/><c:package name=\"code.formathtml.classes\"><a/><c:class name=\"BeanTwo\"><a/><c:field prepare=\"$intern.setTypedInt(4b)\"><a/></c:field></c:class></c:package></c:import></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body> NEXT<a href=\"DELETE\" c:command=\"go\">{typedInt}</a></body></html>";
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
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        String render_ = FormatHtml.processImports(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>HEAD<a href=\"\"/> NEXT<a n-a=\"0\" c:command=\"go\" href=\"\">4</a></body></html>", render_);
    }

    @Test
    public void processImports41Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"arrays\" expression=\"$new java.lang.String[2i]\" className=\"java.lang.String[]\"/><c:set expression=\"arrays;.[0i]=&quot;ab&quot;\"/>{arrays;.length}_{arrays;.[0i]}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setHtml(html_);
        conf_.setDocument(doc_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>2_ab</body></html>", render_);
    }

    @Test
    public void processImports42Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"list\" expression=\"$new code.formathtml.classes.GeneObjs()\" className='java.lang.Object'/><c:set var=\"listTwo\" expression=\"$new code.formathtml.classes.GeneObjs(list;.)\" className=\"code.formathtml.classes.GeneObjs\"/>{listTwo;.size()}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);
        String render_ = FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>1</body></html>", render_);
    }

    @Test
    public void processImports43Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body>HEAD<a href=\"\"/><c:import page=\"page2.html\"><a/><c:package name=\"code.formathtml.classes\"><a/><c:class name=\"BeanTwo\"><a/><c:field prepare=\"$intern.typedInt=4i\"><a/></c:field></c:class></c:package></c:import></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body> NEXT<a href=\"DELETE\" c:command=\"go\">{typedInt}</a></body></html>";
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
        assertEq(0, beanTwo_.getTypedInt());
        String render_ = FormatHtml.processImports(html_, conf_, locale_, files_);
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body>HEAD<a href=\"\"/> NEXT<a n-a=\"0\" c:command=\"go\" href=\"\">4</a></body></html>", render_);
        assertEq(4, beanTwo_.getTypedInt());
    }
    @Test
    public void processImports44Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body><c:import page=\"page2.html\"><a/><c:package name=\"code.formathtml.classes\"><a/><c:class name=\"BeanTwo\"><a/><c:field prepare=\"$intern.typedString=message\"><a/></c:field></c:class></c:package></c:import></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><a href=\"DELETE\" c:command=\"go\">{typedString}</a></body></html>";
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
        bean_.getForms().put("key", "sample_value");
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
        assertEq("TITLE",beanTwo_.getTypedString());
        String render_ = FormatHtml.processImports(html_, conf_, locale_, files_);
        assertEq("Test {0}2",beanTwo_.getTypedString());
        assertXmlEqualRuntime("<html xmlns:c='javahtml'><body><a n-a=\"0\" c:command=\"go\" href=\"\">Test {0}2</a></body></html>", render_);
        assertEq(1, beanTwo_.getForms().size());
        assertEq("key", beanTwo_.getForms().getKeys().first());
        assertEq("sample_value", (String)beanTwo_.getForms().values().first());
    }
    @Test
    public void processImports1FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\" xmlns:c='javahtml'><body><c:import page=\"page2.html\" keepfields=\"y\"><a/><c:package name=\"formathtml.classe\"><a/><c:class name=\"BeanTwo\"><a/><c:field prepare=\"$intern.typedString=message\"><a/></c:field></c:class></c:package></c:import></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><a href=\"DELETE\" c:command=\"go\">{typedString}</a></body></html>";
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
        bean_.getForms().put("key", "sample_value");
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
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        FormatHtml.processImports(html_, conf_, locale_, files_);
        assertNotNull(conf_.getContext().getException());



    }

    @Test
    public void processImports2FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html xmlns:c='javahtml'><body><c:import page=\"page2.html\" keepfields=\"y\"><a/><c:package name=\"code.formathtml.classes\"><a/><c:class name=\"BeanTwo\"><a/><c:field prepare=\"$intern.typedString=message\"><a/></c:field></c:class></c:package></c:import></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><a href=\"DELETE\" c:command=\"go\">{typedString}</a></body></html>";
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
        bean_.getForms().put("key", "sample_value");
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
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        FormatHtml.processImports(html_, conf_, locale_, files_);
        assertNotNull(conf_.getContext().getException());



    }

    @Test
    public void processImports3FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html xmlns:c='javahtml'><body><c:import page=\"page2.html\" keepfields=\"y\"><a/><c:package name=\"code.formathtml.classes\"><a/><c:class name=\"BeanTwo\"><a/><c:field prepare=\"$intern.typedString=message\"><a/></c:field></c:class></c:package><c:form form=\"key\"/></c:import></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\" xmlns:c='javahtml'><body><a href=\"DELETE\" c:command=\"go\">{typedString}</a></body></html>";
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
        bean_.getForms().put("key", "sample_value");
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
        conf_.setDocument(DocumentBuilder.parseSax(html_));
        setup(conf_);
        FormatHtml.processImports(html_, conf_, locale_, files_);
        assertNotNull(conf_.getContext().getException());



    }

    @Test
    public void processImports5FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"nb\" expression=\"1\" className='$int'/><c:set var=\"compos\" expression=\"$new code.formathtml.classes.Composite(nb;.)\"/>{compos;.}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);
        FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertNotNull(conf_.getContext().getException());
    }

    @Test
    public void processImports6FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"nb\" expression=\"1\"/><c:set var=\"compos\" expression=\"$new code.formathtml.classes.Composite(nb;.)\"/>{compos;.}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);
        FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertNotNull(conf_.getContext().getException());
    }

    @Test
    public void processImports7FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"compos\" expression=\"$new formathtml.classe.AbstractBean()\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);
        FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertNotNull(conf_.getContext().getException());
    }

    @Test
    public void processImports8FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"compos\" expression=\"$new code.formathtml.classes.AbstractBean()\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);
        FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertNotNull(conf_.getContext().getException());
    }


    @Test
    public void processImports9FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html xmlns:c='javahtml'><body><c:set var=\"arrays\" expression=\"$new java.lang.Integer[2i][]\" className=\"java.lang.Integer[][]\"/><c:set var=\"array\" expression=\"$new java.lang.Integer[1i]\" className=\"java.lang.Integer[]\"/><c:set expression=\"arrays;.[0i]=arrays;.\"/>{arrays;.length}_{arrays;.[0].length}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
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
        Document doc_ = DocumentBuilder.parseSax(html_);
        conf_.setDocument(doc_);
        conf_.setHtml(html_);
        setup(conf_);
        FormatHtml.processHtml(doc_, "bean_one", conf_, locale_, files_);
        assertNotNull(conf_.getContext().getException());
    }
    private static void setup(Configuration _conf) {
        _conf.setSepPrefix("c");
        _conf.setupValiatorsTranslators("en");
    }

    private static void assertXmlEqualRuntime(String _htmlExp, String _htmlRes) {
        assertTrue(DocumentBuilder.equalsDocs(_htmlExp, _htmlRes));
    }

    private static Configuration newConfiguration() {
        Configuration conf_ = new Configuration();
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl context_ = InitializationLgNames.buildStdOne(opt_);
        conf_.setContext(context_);
        conf_.setStandards((BeanLgNames) context_.getStandards());
        return conf_;
    }
}
