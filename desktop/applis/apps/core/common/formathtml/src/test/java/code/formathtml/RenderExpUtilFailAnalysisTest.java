package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.Delimiters;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.functionid.MethodId;
import code.formathtml.analyze.AnalyzingDoc;
import code.util.StringMap;
import org.junit.Test;

public final class RenderExpUtilFailAnalysisTest extends CommonRenderExpUtil {
    private static final String ARR_INT = "[$int";
    @Test
    public void processEl448Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static Ex<String> inst=$new Ex<>();\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(T v){\n");
        xml_.append("  $return ($int)inst+($int)v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasEr(files_, "pkg.Ex.$this"));
    }

    @Test
    public void processEl6FailTest() {
        assertTrue(hasEr(new StringMap<String>(), "1&&0"));
    }
    @Test
    public void processEl9FailTest() {
        assertTrue(hasEr(new StringMap<String>(), "$true<$false"));
    }
    @Test
    public void processEl20FailTest() {
        assertTrue(hasEr(new StringMap<String>(), "\"\".$classchoice(Number)format(\"6\")"));
    }
    @Test
    public void processEl21FailTest() {
        assertTrue(hasEr(new StringMap<String>(), "\"\".$superaccess(Number)format(\"6\")"));
    }
    @Test
    public void processEl24FailTest() {
        assertTrue(hasEr(new StringMap<String>(), "$firstopt(6)*(7+8)"));
    }
    @Test
    public void processEl25FailTest() {
        assertTrue(hasEr(new StringMap<String>(), "\"\".format(\"6\",$vararg(6))"));
    }
    @Test
    public void processEl26FailTest() {
        assertTrue(hasEr(new StringMap<String>(), "\"\".format($vararg(6),\"6\")"));
    }
    @Test
    public void processEl27FailTest() {
        assertTrue(hasEr(new StringMap<String>(), "$vararg(java.lang.Object)*(7+8)"));
    }
    @Test
    public void processEl28FailTest() {
        assertTrue(hasEr(new StringMap<String>(), "\"\".format($vararg(6),\"6\")"));
    }
    @Test
    public void processEl29FailTest() {
        assertTrue(hasEr(new StringMap<String>(), "\"\".format($firstopt(6),\"6\")"));
    }
    @Test
    public void processEl30FailTest() {
        assertTrue(hasEr(new StringMap<String>(), ""));
    }

    @Test
    public void processEl33FailTest() {
        assertTrue(hasEr(new StringMap<String>(), "1<2<3"));
    }

    @Test
    public void processEl34FailTest() {
        assertTrue(hasEr(new StringMap<String>(), "f(,)"));
    }
    @Test
    public void processAffect17FailTest() {
        assertTrue(hasEr(new StringMap<String>(), "$this()"));
    }
    @Test
    public void processAffect18FailTest() {
        assertTrue(hasEr(new StringMap<String>(), "v(1)"));
    }
    @Test
    public void processAffect19FailTest() {
        assertTrue(hasEr(new StringMap<String>(), "v(1)"));
    }
    @Test
    public void processAffect2FailTest() {
        AnalyzedPageEl context_ = getCheckedConfigurationVar("$int", "v=12i 1", new StringMap<String>());
        assertFalse(isEmptyErrors(context_));
    }

    @Test
    public void processAffect3FailTest() {
        AnalyzedPageEl context_ = getCheckedConfigurationVar(ARR_INT, "v[0i]=\"12i\"", new StringMap<String>());
        assertFalse(isEmptyErrors(context_));
    }
    @Test
    public void processEl200Test() {
        assertTrue(hasEr(new StringMap<String>(), "$($byte)$null"));
    }
    @Test
    public void processEl384FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $final $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasEr(files_, "$new pkg.Ex(5).inst=10"));
    }
    @Test
    public void processEl453Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo {\n");
        xml_.append(" ONE,TWO;\n");
        xml_.append(" $static{\n");
        xml_.append("  Other.v++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Other{\n");
        xml_.append(" $public $static $int v;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasEr(files_, "$new pkg.ExTwo()"));
    }
    @Test
    public void processEl453_Test() {
        assertTrue(hasEr(new StringMap<String>(), "\"\".()"));
    }
    @Test
    public void processEl455Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static $int inst=14;\n");
        xml_.append(" $public $int res(T v){\n");
        xml_.append("  $return ($int)inst+($int)v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasEr(files_, "$new pkg.Ex<$int>().res($id(pkg.Ex,S),15)"));
    }
    @Test
    public void processEl456Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static $int inst=14;\n");
        xml_.append(" $public $int res(T v){\n");
        xml_.append("  $return ($int)inst+($int)v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasEr(files_, "$new pkg.Ex<$int>().res($id(pkg.Ex,pkg.Ex<pkg.Ex>),15)"));
    }
    @Test
    public void processEl457Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static $int inst=14;\n");
        xml_.append(" $public $int res(T v){\n");
        xml_.append("  $return ($int)inst+($int)v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasEr(files_, "$new pkg.Ex<$int>().res($id(pkg.Ex,pkg.Ex<),15)"));
    }
    @Test
    public void processEl458Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static $int inst=14;\n");
        xml_.append(" $public $int res(T v){\n");
        xml_.append("  $return ($int)inst+($int)v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasEr(files_, "$new pkg.Ex<$int>().res($id(pkg.Ex,$void),15)"));
    }
    @Test
    public void processEl459Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static $int inst=14;\n");
        xml_.append(" $public $int res(T v){\n");
        xml_.append("  $return ($int)inst+($int)v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasEr(files_, "$new pkg.Ex<$int>().res($id(pkg.ExInex),15)"));
    }
    @Test
    public void processEl461Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Ex<T> {\n");
        xml_.append(" $public $static $int inst=14;\n");
        xml_.append(" $public $int res(T v){\n");
        xml_.append("  $return ($int)inst+($int)v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasEr(files_, "$new pkg.Outer.Ex<$int>().res($id($void,T),15)"));
    }
    @Test
    public void processEl462Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Ex<T> {\n");
        xml_.append(" $public $static $int inst=14;\n");
        xml_.append(" $public $int res(T v){\n");
        xml_.append("  $return ($int)inst+($int)v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasEr(files_, "$new pkg.Outer.Ex<$int>().res($id(pkg.Outer.ExInex,T),15)"));
    }
    @Test
    public void processEl464Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Ex<T:pkg.Outer> {\n");
        xml_.append(" $public $static $int inst=14;\n");
        xml_.append(" $public $int res(T v){\n");
        xml_.append("  $return ($int)inst+($int)v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasEr(files_, "$new $void<>[i]+$new pkg.Outer.Ex<pkg.Outer.Ex<$int>>()"));
    }
    @Test
    public void processEl465Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Ex<T:pkg.Outer> {\n");
        xml_.append(" $public $static $int inst=14;\n");
        xml_.append(" $public $int res(T v){\n");
        xml_.append("  $return ($int)inst+($int)v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasEr(files_, "@Arobase()"));
    }

    private static boolean hasEr(StringMap<String> _files, String _s) {
        DualNavigationContext a_ = getConfigurationQuick(_files);
        getCheckedConfiguration(a_, _s, new AnalyzingDoc());
        return a_.getDualAnalyzedContext().getAnalyzed().notAllEmptyErrors();
    }

    private static AnalyzedPageEl getCheckedConfigurationVar(String _intType, String _s, StringMap<String> _files) {
        DualNavigationContext a_ = getConfigurationQuick(_files);
        AnaLocalVariable lv_ = new AnaLocalVariable();
        lv_.setClassName(_intType);
        processFail(_s, a_, new AnalyzingDoc(), lv_, "v");
        return a_.getDualAnalyzedContext().getAnalyzed();
    }

    private static DualNavigationContext getCheckedConfiguration(DualNavigationContext _configuration, String _s,AnalyzingDoc _doc) {
        processFail(_s, _configuration, _doc);
        return _configuration;
    }


    private static void processFail(String _el, DualNavigationContext _cont, AnalyzingDoc _analyzingDoc, AnaLocalVariable _local, String _name) {
        AnalyzedPageEl page_ = _cont.getDualAnalyzedContext().getAnalyzed();
        boolean accept_ = page_.isAcceptCommaInstr();
        String currentVarSetting_ = page_.getCurrentVarSetting();
        String globalClass_ = page_.getGlobalClass();
        _analyzingDoc.setup(_cont.getNavigation().getSession(), _cont.getDualAnalyzedContext().getContext().getProperties(), _cont.getDualAnalyzedContext().getContext().getMessagesFolder());
        AnalyzingDoc.setupInts(page_);
        page_.globalType(new AnaFormattedRootBlock(page_,globalClass_));
        AnaLocalVariable a_ = new AnaLocalVariable();
        a_.setClassName(_local.getClassName());
        a_.setConstType(ConstType.LOC_VAR);
        page_.getInfosVars().put(_name, a_);
        page_.setAcceptCommaInstr(accept_);
        page_.setCurrentVarSetting(currentVarSetting_);
        page_.setAccessStaticContext(MethodId.getKind(true));
        Delimiters d_ = checkSyntax(_cont, _el);
        OperationsSequence opTwo_ = rendOpSeq(0, _cont, d_, _el, _analyzingDoc);
        OperationNode op_ = rendOp(0, _cont, opTwo_, _analyzingDoc);
        getSortedDescNodes(_cont, op_, _analyzingDoc,d_);
    }

    private static void processFail(String _el, DualNavigationContext _cont, AnalyzingDoc _analyzingDoc) {
        AnalyzedPageEl page_ = _cont.getDualAnalyzedContext().getAnalyzed();
        boolean accept_ = page_.isAcceptCommaInstr();
        String currentVarSetting_ = page_.getCurrentVarSetting();
        String globalClass_ = page_.getGlobalClass();
        _analyzingDoc.setup(_cont.getNavigation().getSession(), _cont.getDualAnalyzedContext().getContext().getProperties(), _cont.getDualAnalyzedContext().getContext().getMessagesFolder());
        AnalyzingDoc.setupInts(page_);
        page_.globalType(new AnaFormattedRootBlock(page_,globalClass_));
        page_.setAcceptCommaInstr(accept_);
        page_.setCurrentVarSetting(currentVarSetting_);
        page_.setAccessStaticContext(MethodId.getKind(true));
        Delimiters d_ = checkSyntax(_cont, _el);
        OperationsSequence opTwo_ = rendOpSeq(0, _cont, d_, _el, _analyzingDoc);
        OperationNode op_ = rendOp(0, _cont, opTwo_, _analyzingDoc);
        getSortedDescNodes(_cont, op_, _analyzingDoc,d_);
    }

}
