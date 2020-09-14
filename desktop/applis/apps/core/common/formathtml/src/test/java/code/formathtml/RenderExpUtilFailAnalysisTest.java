package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.Options;
import code.formathtml.util.AdvancedFullStack;
import code.formathtml.util.AnalyzingDoc;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.BeanLgNames;
import code.util.StringMap;
import org.junit.Test;

import static org.junit.Assert.*;

public final class RenderExpUtilFailAnalysisTest extends CommonRender {
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
        assertTrue(hasErr(getConfiguration(files_), "pkg.Ex.$this"));
    }

    @Test
    public void processEl6FailTest() {
        assertTrue(hasErr(getConfiguration(), "1&&0"));
    }
    @Test
    public void processEl9FailTest() {
        assertTrue(hasErr(getConfiguration(), "$true<$false"));
    }
    @Test
    public void processEl20FailTest() {
        assertTrue(hasErr(getConfiguration(), "\"\".$classchoice(Number)format(\"6\")"));
    }
    @Test
    public void processEl21FailTest() {
        assertTrue(hasErr(getConfiguration(), "\"\".$superaccess(Number)format(\"6\")"));
    }
    @Test
    public void processEl24FailTest() {
        assertTrue(hasErr(getConfiguration(), "$firstopt(6)*(7+8)"));
    }
    @Test
    public void processEl25FailTest() {
        assertTrue(hasErr(getConfiguration(), "\"\".format(\"6\",$vararg(6))"));
    }
    @Test
    public void processEl26FailTest() {
        assertTrue(hasErr(getConfiguration(), "\"\".format($vararg(6),\"6\")"));
    }
    @Test
    public void processEl27FailTest() {
        assertTrue(hasErr(getConfiguration(), "$vararg(java.lang.Object)*(7+8)"));
    }
    @Test
    public void processEl28FailTest() {
        assertTrue(hasErr(getConfiguration(), "\"\".format($vararg(6),\"6\")"));
    }
    @Test
    public void processEl29FailTest() {
        assertTrue(hasErr(getConfiguration(), "\"\".format($firstopt(6),\"6\")"));
    }
    @Test
    public void processEl30FailTest() {
        assertTrue(hasErr(getConfiguration(), ""));
    }

    @Test
    public void processEl33FailTest() {
        assertTrue(hasErr(getConfiguration(), "1<2<3"));
    }

    @Test
    public void processEl34FailTest() {
        assertTrue(hasErr(getConfiguration(), "f(,)"));
    }
    @Test
    public void processAffect17FailTest() {
        assertTrue(hasErr(getConfiguration(), "$this()"));
    }
    @Test
    public void processAffect18FailTest() {
        assertTrue(hasErr(getConfiguration(), "v(1)"));
    }
    @Test
    public void processAffect19FailTest() {
        assertTrue(hasErr(getConfiguration(), "v(1)"));
    }
    @Test
    public void processAffect2FailTest() {
        Configuration context_ = getCheckedConfigurationVar("$int", "v=12i 1");
        assertTrue(!context_.isEmptyErrors());
    }

    @Test
    public void processAffect3FailTest() {
        Configuration context_ = getCheckedConfigurationVar(ARR_INT, "v[0i]=\"12i\"");
        assertTrue(!context_.isEmptyErrors());
    }
    @Test
    public void processEl200Test() {
        assertTrue(hasErr(getConfiguration(), "$($byte)$null"));
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
        assertTrue(hasErr(getConfiguration(files_), "$new pkg.Ex(5).inst=10"));
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
        assertTrue(hasErr(getConfiguration(files_), "$new pkg.ExTwo()"));
    }
    @Test
    public void processEl453_Test() {
        assertTrue(hasErr(getConfiguration(), "\"\".()"));
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
        assertTrue(hasErr(getConfiguration(files_), "$new pkg.Ex<$int>().res($id(pkg.Ex,S),15)"));
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
        assertTrue(hasErr(getConfiguration(files_), "$new pkg.Ex<$int>().res($id(pkg.Ex,pkg.Ex<pkg.Ex>),15)"));
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
        assertTrue(hasErr(getConfiguration(files_), "$new pkg.Ex<$int>().res($id(pkg.Ex,pkg.Ex<),15)"));
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
        assertTrue(hasErr(getConfiguration(files_), "$new pkg.Ex<$int>().res($id(pkg.Ex,$void),15)"));
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
        assertTrue(hasErr(getConfiguration(files_), "$new pkg.Ex<$int>().res($id(pkg.ExInex),15)"));
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
        assertTrue(hasErr(getConfiguration(files_), "$new pkg.Outer.Ex<$int>().res($id($void,T),15)"));
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
        assertTrue(hasErr(getConfiguration(files_), "$new pkg.Outer.Ex<$int>().res($id(pkg.Outer.ExInex,T),15)"));
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
        assertTrue(hasErr(getConfiguration(files_), "$new $void<>[i]+$new pkg.Outer.Ex<pkg.Outer.Ex<$int>>()"));
    }

    private static boolean hasErr(Configuration configuration, String s) {
        Configuration conf_ = getCheckedConfiguration(configuration, s);
        return !conf_.isEmptyErrors();
    }

    private static Configuration getCheckedConfigurationVar(String _intType, String s) {
        Configuration context_ = getConfiguration();
        AnaLocalVariable lv_ = new AnaLocalVariable();
        lv_.setClassName(_intType);
        processFail(s, context_, new PairVar("v", lv_));
        return context_;
    }
    private static Configuration getCheckedConfiguration(Configuration configuration, String s) {
        processFail(s, configuration);
        return configuration;
    }
    private static void processFail(String _el, Configuration _cont,PairVar... _vars) {
        processFail(_el, 0, _cont,_vars);
    }
    private static void processFail(String _el, int _index, Configuration _conf, PairVar... _vars) {
        AnalyzedPageEl page_ = _conf.getContext().getAnalyzing();
        boolean merged_ = page_.isMerged();
        boolean accept_ = page_.isAcceptCommaInstr();
        String currentVarSetting_ = page_.getCurrentVarSetting();
        String globalClass_ = page_.getGlobalClass();
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        Configuration.setupInts(page_, analyzingDoc_);
        page_.setGlobalClass(globalClass_);
        page_.setGlobalType(page_.getAnaClassBody(StringExpUtil.getIdFromAllTypes(globalClass_)));
        for (PairVar e: _vars) {
            AnaLocalVariable a_ = new AnaLocalVariable();
            a_.setClassName(e.getLocal().getClassName());
            a_.setConstType(ConstType.LOC_VAR);
            page_.getInfosVars().put(e.getName(), a_);
        }
        page_.setMerged(merged_);
        page_.setAcceptCommaInstr(accept_);
        page_.setCurrentVarSetting(currentVarSetting_);
        page_.setAccessStaticContext(MethodId.getKind(true));
        getList(_el, _index, _conf, analyzingDoc_);
    }
    private static Configuration getConfiguration() {
        return getConfiguration(new StringMap<String>());
    }

    private static Configuration getConfiguration(StringMap<String> _files) {
        Configuration conf_ = EquallableExUtil.newConfiguration();
        Options opt_ = new Options();
        opt_.setReadOnly(true);
        ContextEl cont_ = InitializationLgNames.buildStdThree(opt_);
        conf_.setContext(cont_);
        cont_.setFullStack(new AdvancedFullStack(conf_));
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        getHeaders(_files, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_);
        return conf_;
    }

}
