package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;

import code.formathtml.util.NodeContainer;
import code.util.StringMap;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;

public final class RenderRequestUtilTest extends CommonRender {

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
        file_.append(" $public $int nb=5;");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append(" $public $int click(){");
        file_.append("  $return 2;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        AnalyzedTestConfiguration session_ = simulateNav(folder_, relative_, html_, files_, filesSec_);
        Configuration conf_ = session_.getConfiguration();
        addInnerPage(conf_);
        Struct build_ = conf_.getBuiltBeans().getVal("bean_one");
        setGlobalArgumentStruct(session_, build_);
        Struct value_ = redirect(session_, new Argument(build_), 0);
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
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append(" $public $int click(){");
        file_.append("  $return 2;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        AnalyzedTestConfiguration session_ = simulateNav(folder_, relative_, html_, files_, filesSec_);
        Configuration conf_ = session_.getConfiguration();
        addInnerPage(conf_);
        Struct build_ = conf_.getBuiltBeans().getVal("bean_one");
        setGlobalArgumentStruct(session_, build_);
        Struct value_ = redirect(session_, new Argument(build_), 0);
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
        file_.append(" $public $int nb=5;");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append(" $public $int click($int i){");
        file_.append("  $return i*2;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        AnalyzedTestConfiguration session_ = simulateNav(folder_, relative_, html_, files_, filesSec_);
        Configuration conf_ = session_.getConfiguration();
        addInnerPage(conf_);
        Struct build_ = conf_.getBuiltBeans().getVal("bean_one");
        setGlobalArgumentStruct(session_, build_);
        Struct value_ = redirect(session_, new Argument(build_), 0);
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
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append(" $public $int click($int i){");
        file_.append("  $return i*2;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        AnalyzedTestConfiguration session_ = simulateNav(folder_, relative_, html_, files_, filesSec_);
        Configuration conf_ = session_.getConfiguration();
        addInnerPage(conf_);
        Struct build_ = conf_.getBuiltBeans().getVal("bean_one");
        setGlobalArgumentStruct(session_, build_);
        Struct value_ = redirect(session_, new Argument(build_), 0);
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
        file_.append(" $public $int nb=5;");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append(" $public $int click(){");
        file_.append("  $return 2;");
        file_.append(" }");
        file_.append(" $public $int click2(){");
        file_.append("  $return 4;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        AnalyzedTestConfiguration session_ = simulateNav(folder_, relative_, html_, files_, filesSec_);
        Configuration conf_ = session_.getConfiguration();
        addInnerPage(conf_);
        Struct build_ = conf_.getBuiltBeans().getVal("bean_one");
        setGlobalArgumentStruct(session_, build_);
        Struct value_ = redirect(session_, new Argument(build_), 1);
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
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append(" $public $int click(){");
        file_.append("  $return 2;");
        file_.append(" }");
        file_.append(" $public $int click2(){");
        file_.append("  $return 4;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        AnalyzedTestConfiguration session_ = simulateNav(folder_, relative_, html_, files_, filesSec_);
        Configuration conf_ = session_.getConfiguration();
        addInnerPage(conf_);
        Struct build_ = conf_.getBuiltBeans().getVal("bean_one");
        setGlobalArgumentStruct(session_, build_);
        Struct value_ = redirect(session_, new Argument(build_), 1);
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
        file_.append(" $public $int nb=5;");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append(" $public $int click($int i){");
        file_.append("  $return i*2;");
        file_.append(" }");
        file_.append(" $public $int click2($int i){");
        file_.append("  $return i*4;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        AnalyzedTestConfiguration session_ = simulateNav(folder_, relative_, html_, files_, filesSec_);
        Configuration conf_ = session_.getConfiguration();
        addInnerPage(conf_);
        Struct build_ = conf_.getBuiltBeans().getVal("bean_one");
        setGlobalArgumentStruct(session_, build_);
        Struct value_ = redirect(session_, new Argument(build_), 1);
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
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append(" $public $int click($int i){");
        file_.append("  $return i*2;");
        file_.append(" }");
        file_.append(" $public $int click2($int i){");
        file_.append("  $return i*4;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        AnalyzedTestConfiguration session_ = simulateNav(folder_, relative_, html_, files_, filesSec_);
        Configuration conf_ = session_.getConfiguration();
        addInnerPage(conf_);
        Struct build_ = conf_.getBuiltBeans().getVal("bean_one");
        setGlobalArgumentStruct(session_, build_);
        Struct value_ = redirect(session_, new Argument(build_), 1);
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
        file_.append(" $public $int nb=5;");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append(" $public $int click(){");
        file_.append("  $return 1/0;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        AnalyzedTestConfiguration session_ = simulateNavEx(folder_, relative_, html_, files_, filesSec_);
        Configuration conf_ = session_.getConfiguration();
        addInnerPage(conf_);
        Struct build_ = conf_.getBuiltBeans().getVal("bean_one");
        setGlobalArgumentStruct(session_, build_);
        redirect(session_, new Argument(build_), 0);
        assertNotNull(getException(session_));
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
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append(" $public $int click(){");
        file_.append("  $return 1/0;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        AnalyzedTestConfiguration session_ = simulateNavEx(folder_, relative_, html_, files_, filesSec_);
        Configuration conf_ = session_.getConfiguration();
        addInnerPage(conf_);
        Struct build_ = conf_.getBuiltBeans().getVal("bean_one");
        setGlobalArgumentStruct(session_, build_);
        redirect(session_, new Argument(build_), 0);
        assertNotNull(getException(session_));
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
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append(" $public $int click(){");
        file_.append("  $return 1/0;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        AnalyzedTestConfiguration session_ = simulateNavEx(folder_, relative_, html_, files_, filesSec_);
        Configuration conf_ = session_.getConfiguration();
        addInnerPage(conf_);
        Struct build_ = conf_.getBuiltBeans().getVal("bean_one");
        setGlobalArgumentStruct(session_, build_);
        redirect(session_, new Argument(build_), 0);
        assertNotNull(getException(session_));
    }

    private static void setGlobalArgumentStruct(AnalyzedTestConfiguration conf_, Struct build_) {
        conf_.getLastPage().setGlobalArgumentStruct(build_);
    }


    @Test
    public void setRendObject1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<form action=\"\" c:command=\"page1.html\" name=\"myform\"><c:for var=\"n\" list=\"numbers\"><input type=\"radio\" name=\"index\" c:varValue=\"n\"/></c:for><c:for var=\"n\" list=\"numbersTwo\"><input type=\"radio\" name=\"indexTwo\" c:varValue=\"n\"/></c:for><input type=\"submit\" value=\"OK\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int index;");
        file_.append(" $public $int indexTwo;");
        file_.append(" $public $int[] numbers;");
        file_.append(" $public $int[] numbersTwo;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  numbers={2,4,6};");
        file_.append("  numbersTwo={2,4,6};");
        file_.append("  index=4;");
        file_.append("  indexTwo=6;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        AnalyzedTestConfiguration session_ = simulateNav2(folder_, relative_, html_, files_, filesSec_);
        Configuration conf_ = session_.getConfiguration();
        addInnerPage(conf_);
        NodeContainer nCont_ = conf_.getContainersMap().firstValue().getValue(0);
        NodeContainer nContBis_ = conf_.getContainersMap().firstValue().getValue(1);
        setRendObject(session_, nCont_, new IntStruct(2));
        Struct build_ = conf_.getBuiltBeans().getVal("bean_one");
        assertEq(2, ((NumberStruct)getStruct(build_,nCont_.getIdField())).intStruct());
        assertEq(6, ((NumberStruct)getStruct(build_,nContBis_.getIdField())).intStruct());
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
        file_.append(" $public Dto first;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  first=$new Dto(4);");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.Dto{");
        file_.append(" $public $int value;");
        file_.append(" $public Dto($int p){");
        file_.append("  value = p;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        AnalyzedTestConfiguration session_ = simulateNav(folder_, relative_, html_, files_, filesSec_);
        Configuration conf_ = session_.getConfiguration();
        addInnerPage(conf_);
        NodeContainer nCont_ = conf_.getContainersMap().firstValue().getValue(0);
        setRendObject(session_, nCont_, new IntStruct(2));
        Struct build_ = conf_.getBuiltBeans().getVal("bean_one");
        Struct dto_ = getStruct(build_,new ClassField("pkg.BeanOne", "first"));
        assertEq(2, ((NumberStruct)getStruct(dto_,nCont_.getIdField())).intStruct());
    }
    @Test
    public void setRendObject3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<form action=\"\" c:command=\"page1.html\" name=\"myform\"><c:for var=\"n\" list=\"numbers\"><input type=\"radio\" name=\"numbers[([n])]\" c:varValue=\"n\"/></c:for><c:for var=\"n\" list=\"numbersTwo\"><input type=\"radio\" name=\"numbersTwo[([n])]\" c:varValue=\"n\"/></c:for><input type=\"submit\" value=\"OK\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int index;");
        file_.append(" $public $int indexTwo;");
        file_.append(" $public $int[] numbers;");
        file_.append(" $public $int[] numbersTwo;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  numbers={2,4,6};");
        file_.append("  numbersTwo={2,4,6};");
        file_.append("  index=4;");
        file_.append("  indexTwo=6;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        AnalyzedTestConfiguration session_ = simulateNav2(folder_, relative_, html_, files_, filesSec_);
        Configuration conf_ = session_.getConfiguration();
        addInnerPage(conf_);
        NodeContainer nCont_ = conf_.getContainersMap().firstValue().getValue(0);
        setRendObject(session_, nCont_, new IntStruct(9));
        Struct build_ = conf_.getBuiltBeans().getVal("bean_one");
        assertEq(9, ((NumberStruct)((ArrayStruct) getStruct(build_,new ClassField("pkg.BeanOne","numbers"))).getInstance()[0]).intStruct());
        assertEq(4, ((NumberStruct)((ArrayStruct) getStruct(build_,new ClassField("pkg.BeanOne","numbers"))).getInstance()[1]).intStruct());
        assertEq(6, ((NumberStruct)((ArrayStruct) getStruct(build_,new ClassField("pkg.BeanOne","numbers"))).getInstance()[2]).intStruct());
        assertEq(2, ((NumberStruct)((ArrayStruct) getStruct(build_,new ClassField("pkg.BeanOne","numbersTwo"))).getInstance()[0]).intStruct());
        assertEq(4, ((NumberStruct)((ArrayStruct) getStruct(build_,new ClassField("pkg.BeanOne","numbersTwo"))).getInstance()[1]).intStruct());
        assertEq(6, ((NumberStruct)((ArrayStruct) getStruct(build_,new ClassField("pkg.BeanOne","numbersTwo"))).getInstance()[2]).intStruct());
    }
    @Test
    public void setRendObject4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<form action=\"\" c:command=\"page1.html\" name=\"myform\"><c:for var=\"n\" list=\"numbers\"><input type=\"radio\" name=\"$this[([n])]\" c:varValue=\"n\"/></c:for><c:for var=\"n\" list=\"numbersTwo\"><input type=\"radio\" name=\"$this[([n])]\" c:varValue=\"n\"/></c:for><input type=\"submit\" value=\"OK\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int index;");
        file_.append(" $public $int indexTwo;");
        file_.append(" $public $int[] numbers;");
        file_.append(" $public $int[] numbersTwo;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  numbers={2,4,6};");
        file_.append("  numbersTwo={2,4,6};");
        file_.append("  index=4;");
        file_.append("  indexTwo=6;");
        file_.append(" }");
        file_.append(" $public $int $this($int i){");
        file_.append("  $return numbers[i];");
        file_.append(" }");
        file_.append(" $public $void $this($int i){");
        file_.append("  numbers[i]=$value;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        AnalyzedTestConfiguration session_ = simulateNav2(folder_, relative_, html_, files_, filesSec_);
        Configuration conf_ = session_.getConfiguration();
        addInnerPage(conf_);
        NodeContainer nCont_ = conf_.getContainersMap().firstValue().getValue(0);
        setRendObject(session_, nCont_, new IntStruct(9));
        Struct build_ = conf_.getBuiltBeans().getVal("bean_one");
        assertEq(9, ((NumberStruct)((ArrayStruct) getStruct(build_,new ClassField("pkg.BeanOne","numbers"))).getInstance()[0]).intStruct());
        assertEq(4, ((NumberStruct)((ArrayStruct) getStruct(build_,new ClassField("pkg.BeanOne","numbers"))).getInstance()[1]).intStruct());
        assertEq(6, ((NumberStruct)((ArrayStruct) getStruct(build_,new ClassField("pkg.BeanOne","numbers"))).getInstance()[2]).intStruct());
        assertEq(2, ((NumberStruct)((ArrayStruct) getStruct(build_,new ClassField("pkg.BeanOne","numbersTwo"))).getInstance()[0]).intStruct());
        assertEq(4, ((NumberStruct)((ArrayStruct) getStruct(build_,new ClassField("pkg.BeanOne","numbersTwo"))).getInstance()[1]).intStruct());
        assertEq(6, ((NumberStruct)((ArrayStruct) getStruct(build_,new ClassField("pkg.BeanOne","numbersTwo"))).getInstance()[2]).intStruct());
    }

    private static void setRendObject(AnalyzedTestConfiguration conf_, NodeContainer nCont_, IntStruct _attribute) {
        RendRequestUtil.setRendObject(conf_.getConfiguration(),nCont_, _attribute, conf_.getAdvStandards(), conf_.getContext());
    }


    private static Struct redirect(AnalyzedTestConfiguration conf_, Argument _bean, int _url) {
        return RendRequestUtil.redirect(conf_.getConfiguration(), _bean, _url, conf_.getAdvStandards(), conf_.getContext());
    }

}
