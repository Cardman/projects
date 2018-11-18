package code.formathtml;
import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Ignore;
import org.junit.Test;

import code.bean.Bean;
import code.bean.translator.Translator;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.VariableSuffix;
import code.expressionlanguage.opers.util.IntStruct;
import code.expressionlanguage.opers.util.LongStruct;
import code.expressionlanguage.opers.util.StringStruct;
import code.formathtml.classes.BeanFour;
import code.formathtml.classes.BeanOne;
import code.formathtml.classes.BeanSeven;
import code.formathtml.classes.BeanTwo;
import code.formathtml.classes.Composite;
import code.formathtml.classes.MyTranslator;
import code.formathtml.util.BeanStruct;
import code.formathtml.util.NodeContainer;
import code.formathtml.util.ValueChangeEvent;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

@SuppressWarnings("static-method")
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
        nc_.setObject(composite_, conf_.toContextEl());
        nc_.getNodeInformation().setVarMethod("");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasInteger());
//      HtmlRequest.setObject(conf_, composite_, "integer", "", 7, "", conf_.getStandards().getAliasInteger(), new List<Long>());
        HtmlRequest.setObject(conf_, nc_, new IntStruct(7), new Numbers<Long>());
        assertEq(7, composite_.getInteger());
    }

    @Test
    public void setObject2Test() {
        BeanOne bean_ = new BeanOne();
        bean_.getComposite().setInteger(8);
        Configuration conf_ = newConfiguration();
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        conf_.getLastPage().setGlobalArgumentObj(bean_.getComposite(),COMPOSITE, conf_.toContextEl());
        NodeContainer nc_ = new NodeContainer("integer");
        nc_.setLastToken("integer");
        nc_.setTypedField(8);
        nc_.setObject(bean_.getComposite(), conf_.toContextEl());
        nc_.getNodeInformation().setVarMethod("");
        nc_.getNodeInformation().setChanging("updateValue");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasInteger());
//        HtmlRequest.setObject(conf_, bean_, "composite.integer", "", 88, "updateValue", conf_.getStandards().getAliasInteger(), new List<Long>(4L));
        HtmlRequest.setObject(conf_, nc_, new IntStruct(88), new Numbers<Long>(4L));
        assertEq(88, bean_.getComposite().getInteger());
        assertEq(1, bean_.getComposite().getStrings().size());
        assertEq("88 8", bean_.getComposite().getStrings().first());
        ValueChangeEvent changing_ = bean_.getComposite().getChanging();
        int new_ = (Integer) changing_.getNewValue();
        assertEq(88, new_);
        int old_ = (Integer) changing_.getOldValue();
        assertEq(8, old_);
        Numbers<Long> list_ = changing_.getIndexes();
        assertEq(1, list_.size());
        assertEq(4, list_.first().intValue());
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
        nc_.setObject(bean_.getComposite(), conf_.toContextEl());
        nc_.getNodeInformation().setVarMethod("");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasInteger());
        //HtmlRequest.setObject(conf_, bean_, "composite.integer", "", 88, "", conf_.getStandards().getAliasInteger(), new List<Long>(4L));
        HtmlRequest.setObject(conf_, nc_, new IntStruct(88), new Numbers<Long>(4L));
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
//        HtmlRequest.setObject(conf_, nc_, composite_, new Numbers<Long>(4L));
//        assertEq(21, bean_.getComposite().getInteger());
//        assertEq(1, bean_.getComposite().getStrings().size());
//        assertEq("21,0,[],null,null,null 8,0,[],null,null,null", bean_.getComposite().getStrings().first());
//        ValueChangeEvent changing_ = bean_.getChanging();
//        Composite new_ = (Composite) changing_.getNewValue();
//        assertEq(21, new_.getInteger());
//        Composite old_ = (Composite) changing_.getOldValue();
//        assertEq(8, old_.getInteger());
//        Numbers<Long> list_ = changing_.getIndexes();
//        assertEq(1, list_.size());
//        assertEq(4, list_.first().intValue());
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
        HtmlRequest.setObject(conf_, nc_, new IntStruct(7),new Numbers<Long>());
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
        nc_.setObject(bean_.getComposite(), conf_.toContextEl());
        nc_.getNodeInformation().setVarMethod("");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasInteger());
//        HtmlRequest.setObject(conf_, bean_, "getComposite().integer", "", 88, "", conf_.getStandards().getAliasInteger(),new List<Long>(4L));
        HtmlRequest.setObject(conf_, nc_, new IntStruct(88), new Numbers<Long>(4L));
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
        HtmlRequest.setObject(conf_, nc_, new IntStruct(7),new Numbers<Long>());
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
        nc_.setObject(bean_.getStrings(), conf_.toContextEl());
        nc_.getNodeInformation().setVarMethod("");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasString());
        HtmlRequest.setObject(conf_, nc_, new StringStruct("NEW"),new Numbers<Long>());
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
        nc_.setObject(bean_.getTree(), conf_.toContextEl());
        nc_.getNodeInformation().setVarMethod("");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasString());
        HtmlRequest.setObject(conf_, nc_, new StringStruct("keythree"),new Numbers<Long>());
        assertEq(2, bean_.getTree().size());
        assertEq(1, bean_.getTree().getVal("keythree").intValue());
        assertEq(2, bean_.getTree().getVal("keytwo").intValue());
    }

    @Test
    public void setObject10Test() {
        BeanSeven bean_ = new BeanSeven();
        Configuration conf_ = newConfiguration();
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        NodeContainer nc_ = new NodeContainer("");
        nc_.setIndex(0);
        nc_.setTypedField(bean_.getTree().getValue(0));
        nc_.setObject(bean_.getTree(), conf_.toContextEl());
        nc_.getNodeInformation().setVarMethod("");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasInteger());
        HtmlRequest.setObject(conf_, nc_, new IntStruct(3),new Numbers<Long>());
        assertEq(2, bean_.getTree().size());
        assertEq(3, bean_.getTree().getVal("keyone").intValue());
        assertEq(2, bean_.getTree().getVal("keytwo").intValue());
    }


    @Test
    public void setObject11Test() {
        BeanSeven bean_ = new BeanSeven();
        Configuration conf_ = newConfiguration();
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        NodeContainer nc_ = new NodeContainer("");
        nc_.setIndex(0);
        nc_.setTypedField(bean_.getArrayInt()[0]);
        nc_.setObject(bean_.getArrayInt(), conf_.toContextEl());
        nc_.getNodeInformation().setVarMethod("");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasPrimInteger());
        HtmlRequest.setObject(conf_, nc_, new IntStruct(5),new Numbers<Long>());
        assertEq(2, bean_.getArrayInt().length);
        assertEq(5, bean_.getArrayInt()[0]);
        assertEq(3, bean_.getArrayInt()[1]);
    }

    @Ignore
    @Test
    public void setObject12Test() {
        BeanSeven bean_ = new BeanSeven();
        bean_.getComposite().setStrings(new StringList());
        Configuration conf_ = newConfiguration();
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        NodeContainer nc_ = new NodeContainer("");
        nc_.setIndex(0);
        nc_.setTypedField(bean_.getStrings().first());
        nc_.setObject(bean_.getStrings(), conf_.toContextEl());
        nc_.getNodeInformation().setVarMethod("");
        nc_.getNodeInformation().setChanging("updateValue");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasString());
        HtmlRequest.setObject(conf_, nc_, new StringStruct("NEW"),new Numbers<Long>());
        assertEq("NEW", bean_.getStrings().first());
        StringList values_ = bean_.getComposite().getStrings();
        assertEq(1, values_.size());
        assertEq("FIRST NEW", values_.first());
    }

//    @Ignore
//    @Test
//    public void setObject13Test() {
//        BeanSeven bean_ = new BeanSeven();
//        Configuration conf_ = newConfiguration();
//        setup(conf_);
//        conf_.addPage(new ImportingPage(true));
//        NodeContainer nc_ = new NodeContainer("");
//        nc_.setIndex(0);
//        nc_.setTypedField(bean_.getTree().getKey(0));
//        nc_.setObject(bean_.getTree(), "code.util.NatTreeMap");
//        nc_.getNodeInformation().setVarMethod("");
//        nc_.getNodeInformation().setChanging("updateValue");
//        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasString());
//        HtmlRequest.setObject(conf_, nc_, new StringStruct("keythree"),new Numbers<Long>());
//        assertEq(2, bean_.getTree().size());
//        assertEq(1, bean_.getTree().getVal("keythree").intValue());
//        assertEq(2, bean_.getTree().getVal("keytwo").intValue());
//        StringList values_ = bean_.getComposite().getStrings();
//        assertEq(1, values_.size());
//        assertEq("keyone keythree", values_.first());
//    }

//    @Ignore
//    @Test
//    public void setObject14Test() {
//        BeanSeven bean_ = new BeanSeven();
//        Configuration conf_ = newConfiguration();
//        setup(conf_);
//        conf_.addPage(new ImportingPage(true));
//        NodeContainer nc_ = new NodeContainer("");
//        nc_.setIndex(0);
//        nc_.setTypedField(bean_.getTree().getValue(0));
//        nc_.setObject(bean_.getTree(), "code.util.NatTreeMap");
//        nc_.getNodeInformation().setVarMethod("");
//        nc_.getNodeInformation().setChanging("updateValue");
//        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasInteger());
//        HtmlRequest.setObject(conf_, nc_, new IntStruct(3),new Numbers<Long>());
//        assertEq(2, bean_.getTree().size());
//        assertEq(3, bean_.getTree().getVal("keyone").intValue());
//        assertEq(2, bean_.getTree().getVal("keytwo").intValue());
//        StringList values_ = bean_.getComposite().getStrings();
//        assertEq(1, values_.size());
//        assertEq("1 3", values_.first());
//    }


//    @Ignore
//    @Test
//    public void setObject15Test() {
//        BeanSeven bean_ = new BeanSeven();
//        Configuration conf_ = newConfiguration();
//        setup(conf_);
//        conf_.addPage(new ImportingPage(true));
//        NodeContainer nc_ = new NodeContainer("");
//        nc_.setIndex(0);
//        nc_.setTypedField(bean_.getArrayInt()[0]);
//        nc_.setObject(bean_.getArrayInt(), "[$int");
//        nc_.getNodeInformation().setVarMethod("");
//        nc_.getNodeInformation().setChanging("updateValue");
//        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasPrimInteger());
//        HtmlRequest.setObject(conf_, nc_, new IntStruct(5),new Numbers<Long>());
//        assertEq(2, bean_.getArrayInt().length);
//        assertEq(5, bean_.getArrayInt()[0]);
//        assertEq(3, bean_.getArrayInt()[1]);
//        StringList values_ = bean_.getComposite().getStrings();
//        assertEq(1, values_.size());
//        assertEq("1 5", values_.first());
//    }

//    @Test(expected=NoSuchDeclaredMethodException.class)
//    public void setObject1FailTest() {
//        Composite composite_ = new Composite();
//        composite_.setInteger(21);
//        composite_.setStrings(new StringList());
//        BeanOne bean_ = new BeanOne();
//        bean_.getComposite().setInteger(8);
//        Configuration conf_ = newConfiguration();
//        setup(conf_);
//        conf_.addPage(new ImportingPage(true));
//        conf_.getLastPage().setGlobalArgumentObj(bean_);
//        NodeContainer nc_ = new NodeContainer("composite");
//        nc_.setTypedField(bean_.getComposite());
//        nc_.setLastToken("composite");
//        nc_.setObject(bean_);
//        nc_.getNodeInformation().setVarMethod("");
//        nc_.getNodeInformation().setChanging("updateValues");
//        nc_.getNodeInformation().setInputClass(Composite.class.getName());
////        HtmlRequest.setObject(conf_, bean_, "composite", "", composite_, "updateValues", Composite.class.getName(), new List<Long>(4L));
//        HtmlRequest.setObject(conf_, nc_, composite_, new Numbers<Long>(4L));
//    }

//    @Test(expected=InvokeException.class)
//    public void setObject2FailTest() {
//        Composite composite_ = new Composite();
//        composite_.setInteger(21);
//        BeanOne bean_ = new BeanOne();
//        bean_.getComposite().setInteger(8);
//        Configuration conf_ = newConfiguration();
//        setup(conf_);
//        conf_.addPage(new ImportingPage(true));
//        conf_.getLastPage().setGlobalArgumentObj(bean_);
//        NodeContainer nc_ = new NodeContainer("composite");
//        nc_.setTypedField(bean_.getComposite());
//        nc_.setLastToken("composite");
//        nc_.setObject(bean_);
//        nc_.getNodeInformation().setVarMethod("");
//        nc_.getNodeInformation().setChanging("updateValue");
//        nc_.getNodeInformation().setInputClass(Composite.class.getName());
////        HtmlRequest.setObject(conf_, bean_, "composite", "", composite_, "updateValue", Composite.class.getName(), new List<Long>(4L));
//        HtmlRequest.setObject(conf_, nc_, composite_, new Numbers<Long>(4L));
//    }

//    @Test(expected=SetterException.class)
//    public void setObject3FailTest() {
//        Composite composite_ = new Composite();
//        composite_.setInteger(21);
//        BeanOne bean_ = new BeanOne();
//        bean_.getComposite().setInteger(8);
//        Configuration conf_ = newConfiguration();
//        setup(conf_);
//        conf_.addPage(new ImportingPage(true));
//        NodeContainer nc_ = new NodeContainer("integer");
//        nc_.setTypedField(8);
//        nc_.setLastToken("integer");
//        nc_.setObject(bean_.getComposite());
//        nc_.getNodeInformation().setVarMethod("");
//        nc_.getNodeInformation().setChanging("updateValue");
//        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasInteger());
////        HtmlRequest.setObject(conf_, bean_, "composite.integer", "", composite_, "updateValue", conf_.getStandards().getAliasInteger(), new List<Long>(4L));
//        HtmlRequest.setObject(conf_, nc_, composite_, new Numbers<Long>(4L));
//    }

    @Test
    public void setObject4FailTest() {
        Composite composite_ = new Composite();
        Configuration conf_ = newConfiguration();
        setup(conf_);
        conf_.addPage(new ImportingPage(true));
        conf_.getLastPage().setGlobalArgumentObj(composite_,COMPOSITE, conf_.toContextEl());
        NodeContainer nc_ = new NodeContainer("integer");
        nc_.setLastToken("integer");
        nc_.setObject(composite_, conf_.toContextEl());
        nc_.getNodeInformation().setVarMethod("inexistantmethod");
        nc_.getNodeInformation().setChanging("");
        nc_.getNodeInformation().setInputClass(conf_.getStandards().getAliasInteger());
        HtmlRequest.setObject(conf_, nc_, new IntStruct(7), new Numbers<Long>());
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
        HtmlRequest.setObject(conf_, nc_, new StringStruct("ex"),new Numbers<Long>());
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
        String return_ = (String) HtmlRequest.invokeMethodWithNumbers(conf_, new BeanStruct(bean_), "invokeMethod", arg_).getInstance();
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
        String return_ = (String) HtmlRequest.invokeMethodWithNumbers(conf_, new BeanStruct(bean_), "composite.internMethod").getInstance();
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
        String render_ = HtmlRequest.formatErrorMessage(conf_, "msg_example,three", false, locale_, files_, "", "EX");
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
        String render_ = HtmlRequest.formatErrorMessage(conf_, "sample/file,three", false, locale_, files_, "", "EX");
        assertEq("desc &lt;EX&gt;", render_);
    }

    private static void setup(Configuration _conf) {
        _conf.setSepPrefix("c");
        _conf.setupValiatorsTranslators("en");
    }

    private static Configuration newConfiguration() {
        Configuration conf_ = new Configuration();
        ContextEl context_ = new ContextEl();
        context_.getOptions().setEndLineSemiColumn(false);
        context_.getOptions().setSpecialEnumsMethods(false);
        context_.getOptions().setSuffixVar(VariableSuffix.DISTINCT);
        conf_.setStandards(InitializationLgNames.initStandards(context_));
        conf_.setContext(context_);
        context_.initError();
        return conf_;
    }
}
