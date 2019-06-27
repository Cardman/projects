package code.formathtml;
import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import code.bean.Bean;
import code.bean.translator.Translator;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.VariableSuffix;
import code.formathtml.classes.BeanFour;
import code.formathtml.classes.BeanOne;
import code.formathtml.classes.BeanSeven;
import code.formathtml.classes.BeanTwo;
import code.formathtml.classes.Composite;
import code.formathtml.classes.MyTranslator;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.BeanStruct;
import code.formathtml.util.NodeContainer;
import code.formathtml.util.ValueChangeEvent;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;


public class HtmlRequestTest {
    private static final String COMPOSITE = "code.formathtml.classes.Composite";

    @Test
    public void setObject1Test() {
        Composite composite_ = new Composite();
        Configuration conf_ = newConfiguration();
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        NodeContainer nc_ = new NodeContainer("integer");
        nc_.setLastToken("integer");
        nc_.setObject(composite_, conf_.getContext());
        nc_.getNodeInformation().setVarMethod("");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasInteger());
//      HtmlRequest.setObject(conf_, composite_, "integer", "", 7, "", conf_.getStandards().getAliasInteger(), new List<Long>());
        HtmlRequest.setObject(conf_, nc_, new IntStruct(7), new Longs());
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
        NodeContainer nc_ = new NodeContainer("integer");
        nc_.setLastToken("integer");
        nc_.setTypedStruct(new IntStruct(8));
        nc_.setObject(bean_.getComposite(), conf_.getContext());
        nc_.getNodeInformation().setVarMethod("");
        nc_.getNodeInformation().setChanging("updateValue");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasInteger());
//        HtmlRequest.setObject(conf_, bean_, "composite.integer", "", 88, "updateValue", conf_.getStandards().getAliasInteger(), new List<Long>(4L));
        HtmlRequest.setObject(conf_, nc_, new IntStruct(88), new Longs(4L));
        assertEq(88, bean_.getComposite().getInteger());
        assertEq(1, bean_.getComposite().getStrings().size());
        assertEq("88 8", bean_.getComposite().getStrings().first());
        ValueChangeEvent changing_ = bean_.getComposite().getChanging();
        int new_ = ((NumberStruct) changing_.getNewValue()).intStruct();
        assertEq(88, new_);
        int old_ = ((NumberStruct) changing_.getOldValue()).intStruct();
        assertEq(8, old_);
        Longs list_ = changing_.getIndexes();
        assertEq(1, list_.size());
        assertEq(4, list_.first());
    }

    @Test
    public void setObject3Test() {
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().setInteger(8);
        Configuration conf_ = newConfiguration();
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        NodeContainer nc_ = new NodeContainer("integer");
        nc_.setLastToken("integer");
        nc_.setObject(bean_.getComposite(), conf_.getContext());
        nc_.getNodeInformation().setVarMethod("");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasInteger());
        //HtmlRequest.setObject(conf_, bean_, "composite.integer", "", 88, "", conf_.getStandards().getAliasInteger(), new List<Long>(4L));
        HtmlRequest.setObject(conf_, nc_, new IntStruct(88), new Longs(4L));
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
        NodeContainer nc_ = new NodeContainer("invisibleField");
        nc_.setLastToken("invisibleField");
        nc_.setObject(bean_);
        nc_.getNodeInformation().setVarMethod("setInvisibleField");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasInteger());
//        HtmlRequest.setObject(conf_, bean_, "invisibleField", "setInvisibleField", 7, "", conf_.getStandards().getAliasInteger(),new List<Long>());
        HtmlRequest.setObject(conf_, nc_, new IntStruct(7),new Longs());
        assertEq(7, bean_.getInvisibleField());
    }

    @Test
    public void setObject6Test() {
        BeanFour bean_ = new BeanFour();
        bean_.getComposite().setInteger(8);
        Configuration conf_ = newConfiguration();
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        NodeContainer nc_ = new NodeContainer("integer");
        nc_.setLastToken("integer");
        nc_.setObject(bean_.getComposite(), conf_.getContext());
        nc_.getNodeInformation().setVarMethod("");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasInteger());
//        HtmlRequest.setObject(conf_, bean_, "getComposite().integer", "", 88, "", conf_.getStandards().getAliasInteger(),new List<Long>(4L));
        HtmlRequest.setObject(conf_, nc_, new IntStruct(88), new Longs(4L));
        assertEq(88, bean_.getComposite().getInteger());
    }

    @Test
    public void setObject7Test() {
        BeanFour bean_ = new BeanFour();
        Configuration conf_ = newConfiguration();
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        conf_.getLastPage().setGlobalArgumentObj(bean_);
        NodeContainer nc_ = new NodeContainer("invisibleField");
        nc_.setLastToken("invisibleField");
        nc_.setObject(bean_);
        nc_.getNodeInformation().setVarMethod("setInvisibleIntField");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasPrimInteger());
        HtmlRequest.setObject(conf_, nc_, new IntStruct(7),new Longs());
        assertEq(7, bean_.getInvisibleField());
    }

    @Test
    public void setObject8Test() {
        BeanSeven bean_ = new BeanSeven();
        Configuration conf_ = newConfiguration();
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        NodeContainer nc_ = new NodeContainer("");
        nc_.setIndex(0);
        nc_.setLastToken("");
        nc_.setTypedField(bean_.getStrings().first());
        nc_.setObject(bean_.getStrings(), conf_.getContext());
        nc_.getNodeInformation().setVarMethod("");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasString());
        HtmlRequest.setObject(conf_, nc_, new StringStruct("NEW"),new Longs());
        assertEq("NEW", bean_.getStrings().first());
    }

    @Test
    public void setObject9Test() {
        BeanSeven bean_ = new BeanSeven();
        Configuration conf_ = newConfiguration();
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        NodeContainer nc_ = new NodeContainer("");
        nc_.setIndex(0);
        nc_.setKey(true);
        nc_.setTypedField(bean_.getTree().getKey(0));
        nc_.setObject(bean_.getTree(), conf_.getContext());
        nc_.getNodeInformation().setVarMethod("");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasString());
        HtmlRequest.setObject(conf_, nc_, new StringStruct("keythree"),new Longs());
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
        NodeContainer nc_ = new NodeContainer("");
        nc_.setIndex(0);
        nc_.setTypedStruct(new IntStruct(bean_.getTree().getValue(0)));
        nc_.setObject(bean_.getTree(), conf_.getContext());
        nc_.getNodeInformation().setVarMethod("");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasInteger());
        HtmlRequest.setObject(conf_, nc_, new IntStruct(3),new Longs());
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
        NodeContainer nc_ = new NodeContainer("");
        nc_.setIndex(0);
        nc_.setTypedStruct(new IntStruct(1));
        nc_.setStruct(arr_);
        nc_.getNodeInformation().setVarMethod("");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasPrimInteger());
        HtmlRequest.setObject(conf_, nc_, new IntStruct(5),new Longs());
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
        NodeContainer nc_ = new NodeContainer("");
        nc_.setIndex(0);
        nc_.setTypedField(bean_.getStrings().first());
        nc_.setObject(bean_.getStrings(), conf_.getContext());
        nc_.getNodeInformation().setVarMethod("");
        nc_.getNodeInformation().setChanging("updateValue");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasString());
        HtmlRequest.setObject(conf_, nc_, new StringStruct("NEW"),new Longs());
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
        NodeContainer nc_ = new NodeContainer("integer");
        nc_.setLastToken("integer");
        nc_.setObject(composite_, conf_.getContext());
        nc_.getNodeInformation().setVarMethod("inexistantmethod");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasInteger());
        HtmlRequest.setObject(conf_, nc_, new IntStruct(7), new Longs());
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
        NodeContainer nc_ = new NodeContainer("hello");
        nc_.setLastToken("hello");
        nc_.setObject(bean_);
        nc_.getNodeInformation().setVarMethod("setter");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasString());
//        HtmlRequest.setObject(conf_, bean_, "hello", "setter", "ex", "", conf_.getStandards().getAliasString(),new List<Long>());
        HtmlRequest.setObject(conf_, nc_, new StringStruct("ex"),new Longs());
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
        conf_.setStandards((BeanLgNames) context_.getStandards());
        return conf_;
    }
}
