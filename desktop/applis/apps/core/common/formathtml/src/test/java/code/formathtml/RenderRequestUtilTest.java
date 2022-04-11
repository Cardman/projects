package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.DefNodeContainer;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public final class RenderRequestUtilTest extends CommonRender {

    @Test
    public void redirect1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click({1})\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><a c:command=\"$click()\">two</a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
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
        DualNavigationContext dual_ = buildNav();


        setup(folder_, relative_, files_, dual_);
        ContextEl ctx_ = elOneBean(filesSec_, filRend(oneFile(html_), files_), dual_);

        RendStackCall build = new RendStackCall(InitPhase.NOTHING, ctx_);
        successRes(build,ctx_, dual_.getDualAnalyzedContext().getStds(), dual_.getNavigation().getSession());
        addInnerPage(build);
        Struct build_ = dual_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one");
        Struct value_ = redirect(new Argument(build_), 0, dual_.getDualAnalyzedContext().getStds(), ctx_, build);
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
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
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
        DualNavigationContext dual_ = buildNav();


        setup(folder_, relative_, files_, dual_);
        ContextEl ctx_ = elOneBean(filesSec_, filRend(oneFile(html_), files_),dual_);

        RendStackCall st = new RendStackCall(InitPhase.NOTHING, ctx_);
        successRes(st,ctx_, dual_.getDualAnalyzedContext().getStds(),  dual_.getNavigation().getSession());
        assertEq(1, st.getHtmlPage().getCallsExps().size());
    }

    @Test
    public void redirect3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click({1})\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><a c:command=\"$click({nb})\">two</a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
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
        DualNavigationContext dual_ = buildNav();


        setup(folder_, relative_, files_, dual_.getDualAnalyzedContext().getContext(),  dual_.getNavigation().getSession());
        ContextEl ctx_ = elOneBean(filesSec_, filRend(oneFile(html_), files_), dual_);

        RendStackCall build = new RendStackCall(InitPhase.NOTHING, ctx_);
        successRes(build,ctx_, dual_.getDualAnalyzedContext().getStds(),  dual_.getNavigation().getSession());
        addInnerPage(build);
        Struct build_ =  dual_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one");
        Struct value_ = redirect(new Argument(build_), 0, dual_.getDualAnalyzedContext().getStds(), ctx_, build);
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
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
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
        DualNavigationContext a_ = buildNav();


        setup(folder_, relative_, files_, a_);
        ContextEl ctx_ = elOneBean(filesSec_, filRend(oneFile(html_), files_), a_);

        RendStackCall build = new RendStackCall(InitPhase.NOTHING, ctx_);
        successRes(build,ctx_, a_);
        assertEq(1, build.getHtmlPage().getCallsExps().size());
    }

    @Test
    public void redirect5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click({1})\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><a c:command=\"$click()\">two</a><a c:command=\"$click2()\">four</a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
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
        DualNavigationContext a_ = buildNav();


        setup(folder_, relative_, files_, a_);
        ContextEl ctx_ = elOneBean(filesSec_, filRend(oneFile(html_), files_), a_);

        RendStackCall build = new RendStackCall(InitPhase.NOTHING, ctx_);
        successRes(build,ctx_, a_);
        
        
        Configuration conf_ = a_.getNavigation().getSession();
        addInnerPage(build);
        Struct build_ = a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one");
        Struct value_ = redirect(new Argument(build_), 1, a_.getDualAnalyzedContext().getStds(), ctx_, build);
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
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
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
        DualNavigationContext a_ = buildNav();


        setup(folder_, relative_, files_, a_);
        ContextEl ctx_ = elOneBean(filesSec_, filRend(oneFile(html_), files_), a_);

        RendStackCall build = new RendStackCall(InitPhase.NOTHING, ctx_);
        successRes(build,ctx_, a_);
        assertEq(2, build.getHtmlPage().getCallsExps().size());
    }
    @Test
    public void redirect7Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click({1})\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><a c:command=\"$click({nb})\">two</a><a c:command=\"$click2({nb})\">four</a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
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
        DualNavigationContext a_ = buildNav();


        setup(folder_, relative_, files_, a_);
        ContextEl ctx_ = elOneBean(filesSec_, filRend(oneFile(html_), files_), a_);

        RendStackCall build = new RendStackCall(InitPhase.NOTHING, ctx_);
        successRes(build,ctx_, a_);
        
        
        Configuration conf_ = a_.getNavigation().getSession();
        addInnerPage(build);
        Struct build_ = a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one");
        Struct value_ = redirect(new Argument(build_), 1, a_.getDualAnalyzedContext().getStds(), ctx_, build);
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
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
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
        DualNavigationContext a_ = buildNav();


        setup(folder_, relative_, files_, a_);
        ContextEl ctx_ = elOneBean(filesSec_, filRend(oneFile(html_), files_), a_);

        RendStackCall build = new RendStackCall(InitPhase.NOTHING, ctx_);
        successRes(build,ctx_, a_);
        assertEq(2, build.getHtmlPage().getCallsExps().size());
    }

    @Test
    public void redirect9Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click({1})\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><a c:command=\"$click()\">two</a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
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
        DualNavigationContext a_ = buildNav();


        setup(folder_, relative_, files_, a_);
        ContextEl ctx_ = elOneBean(filesSec_, filRend(oneFile(html_), files_), a_);

        RendStackCall build = new RendStackCall(InitPhase.NOTHING, ctx_);
        successRes(build,ctx_, a_);
        
        
        Configuration conf_ = a_.getNavigation().getSession();
        addInnerPage(build);
        Struct build_ = a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one");
        redirect(new Argument(build_), 0, a_.getDualAnalyzedContext().getStds(), ctx_, build);
        assertNotNull(getException(build));
    }

    @Test
    public void process10Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param value='&quot;Text&quot;'/><c:param value='2'/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
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
        DualNavigationContext a_ = buildNav();


        setup(folder_, relative_, files_, a_);
        ContextEl ctx_ = elOneBean(filesSec_, filRend(oneFile(html_), files_), a_);

        RendStackCall build = new RendStackCall(InitPhase.NOTHING, ctx_);
        successRes(build,ctx_, a_);
        assertEq(1,  build.getHtmlPage().getCallsExps().size());
    }
    @Test
    public void process11Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description \nthree=desc &lt;{0}&gt;<a c:command=\"$click\">two</a>After\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:message value=\"msg_example,three\"><c:param value='&quot;Text&quot;'/><c:param value='2'/></c:message></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
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
        DualNavigationContext a_ = buildNav();


        setup(folder_, relative_, files_, a_);
        ContextEl ctx_ = elOneBean(filesSec_, filRend(oneFile(html_), files_), a_);

        RendStackCall build = new RendStackCall(InitPhase.NOTHING, ctx_);
        successRes(build,ctx_, a_);
        assertEq(1,  build.getHtmlPage().getCallsExps().size());
    }


    @Test
    public void setRendObject1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<form action=\"\" c:command=\"page1.html\" name=\"myform\"><c:for var=\"n\" list=\"numbers\"><input type=\"radio\" name=\"index\" c:varValue=\"n\"/></c:for><c:for var=\"n\" list=\"numbersTwo\"><input type=\"radio\" name=\"indexTwo\" c:varValue=\"n\"/></c:for><input type=\"submit\" value=\"OK\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
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
        DualNavigationContext a_ = buildNav();


        setFirst("page1.html", a_.getNavigation().getSession());
        setup(folder_, relative_, files_, a_);
        setNavigation(a_.getNavigation().getSession());
        ContextEl ctx_ = elOneBean(filesSec_, oneFile(html_), a_);
        RendStackCall build = new RendStackCall(InitPhase.NOTHING, ctx_);
        successRes(build,ctx_, a_);
        
        
        Configuration conf_ = a_.getNavigation().getSession();
        addInnerPage(build);
        DefNodeContainer nCont_ = build.getFormParts().getContainersMap().firstValue().getValue(0);
        DefNodeContainer nContBis_ = build.getFormParts().getContainersMap().firstValue().getValue(1);
        setRendObject(nCont_, new IntStruct(2), ctx_);
        Struct build_ = a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one");
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
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
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
        DualNavigationContext a_ = buildNav();


        setup(folder_, relative_, files_, a_);
        ContextEl ctx_ = elOneBean(filesSec_, filRend(oneFile(html_), files_), a_);

        RendStackCall build = new RendStackCall(InitPhase.NOTHING, ctx_);
        successRes(build,ctx_, a_);
        
        
        Configuration conf_ = a_.getNavigation().getSession();
        addInnerPage(build);
        DefNodeContainer nCont_ = build.getFormParts().getContainersMap().firstValue().getValue(0);
        setRendObject(nCont_, new IntStruct(2), ctx_);
        Struct build_ = a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one");
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
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
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
        DualNavigationContext a_ = buildNav();


        setFirst("page1.html", a_.getNavigation().getSession());
        setup(folder_, relative_, files_, a_);
        setNavigation(a_.getNavigation().getSession());
        ContextEl ctx_ = elOneBean(filesSec_, oneFile(html_), a_);
        RendStackCall build = new RendStackCall(InitPhase.NOTHING, ctx_);
        successRes(build,ctx_, a_);
        
        
        Configuration conf_ = a_.getNavigation().getSession();
        addInnerPage(build);
        DefNodeContainer nCont_ = build.getFormParts().getContainersMap().firstValue().getValue(0);
        setRendObject(nCont_, new IntStruct(9), ctx_);
        Struct build_ = a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one");
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
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
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
        DualNavigationContext a_ = buildNav();


        setFirst("page1.html", a_.getNavigation().getSession());
        setup(folder_, relative_, files_, a_);
        setNavigation(a_.getNavigation().getSession());
        ContextEl ctx_ = elOneBean(filesSec_, oneFile(html_), a_);
        RendStackCall build = new RendStackCall(InitPhase.NOTHING, ctx_);
        successRes(build,ctx_, a_);
        
        
        Configuration conf_ = a_.getNavigation().getSession();
        addInnerPage(build);
        DefNodeContainer nCont_ = build.getFormParts().getContainersMap().firstValue().getValue(0);
        setRendObject(nCont_, new IntStruct(9), ctx_);
        Struct build_ = a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one");
        assertEq(9, ((NumberStruct)((ArrayStruct) getStruct(build_,new ClassField("pkg.BeanOne","numbers"))).getInstance()[0]).intStruct());
        assertEq(4, ((NumberStruct)((ArrayStruct) getStruct(build_,new ClassField("pkg.BeanOne","numbers"))).getInstance()[1]).intStruct());
        assertEq(6, ((NumberStruct)((ArrayStruct) getStruct(build_,new ClassField("pkg.BeanOne","numbers"))).getInstance()[2]).intStruct());
        assertEq(2, ((NumberStruct)((ArrayStruct) getStruct(build_,new ClassField("pkg.BeanOne","numbersTwo"))).getInstance()[0]).intStruct());
        assertEq(4, ((NumberStruct)((ArrayStruct) getStruct(build_,new ClassField("pkg.BeanOne","numbersTwo"))).getInstance()[1]).intStruct());
        assertEq(6, ((NumberStruct)((ArrayStruct) getStruct(build_,new ClassField("pkg.BeanOne","numbersTwo"))).getInstance()[2]).intStruct());
    }

    private static void setRendObject(DefNodeContainer _nCont, IntStruct _attribute, ContextEl _ctx) {
        RendStackCall rendStackCall_ = new RendStackCall(InitPhase.NOTHING, _ctx);
        rendStackCall_.addPage(new ImportingPage());
        RendRequestUtil.setRendObject(_nCont, _attribute, _ctx, rendStackCall_);
    }


    private static Struct redirect(Argument _bean, int _url, BeanCustLgNames _advStandards, ContextEl _context, RendStackCall _rendStackCall) {
        DefHtmlPage _htmlPage = _rendStackCall.getHtmlPage();
        StringList varNames_ = _htmlPage.getAnchorsVars().get(_url);
        CustList<RendDynOperationNode> exps_ = _htmlPage.getCallsExps().get(_url);
        StringList args_ = _htmlPage.getAnchorsArgs().get(_url);
        return ((BeanCustLgNames) _advStandards).redir(_bean, varNames_, exps_, args_, _context, _rendStackCall);
    }

}
