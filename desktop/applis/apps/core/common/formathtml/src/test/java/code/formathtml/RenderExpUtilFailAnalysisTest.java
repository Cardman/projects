package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.formathtml.util.AdvancedFullStack;
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
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static Ex<String> inst=$new Ex<>();\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(#T v){\n");
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        processEl("pkg.Ex.$this", conf_);
        assertNotNull(getException(conf_));
    }

    @Test
    public void processEl6FailTest() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        processEl("1&&0", context_);
        assertTrue(!context_.isEmptyErrors());
    }
    @Test
    public void processEl9FailTest() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        processEl("$true<$false", context_);
        assertTrue(!context_.isEmptyErrors());
    }

    @Test
    public void processEl24FailTest() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        String el_ = "$firstopt(6)*(7+8)";
        processEl(el_, context_);
        assertTrue(!context_.isEmptyErrors());
    }
    @Test
    public void processEl25FailTest() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        String el_ = "\"\".format(\"6\",$vararg(6))";
        processEl(el_, context_);
        assertTrue(!context_.isEmptyErrors());
    }
    @Test
    public void processEl26FailTest() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        String el_ = "\"\".format($vararg(6),\"6\")";
        processEl(el_, context_);
        assertTrue(!context_.isEmptyErrors());
    }
    @Test
    public void processEl27FailTest() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        String el_ = "$vararg(java.lang.Object)*(7+8)";
        processEl(el_, context_);
        assertTrue(!context_.isEmptyErrors());
    }
    @Test
    public void processEl28FailTest() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        String el_ = "\"\".format($vararg(6),\"6\")";
        processEl(el_, context_);
        assertTrue(!context_.isEmptyErrors());
    }
    @Test
    public void processEl29FailTest() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        String el_ = "\"\".format($firstopt(6),\"6\")";
        processEl(el_, context_);
        assertTrue(!context_.isEmptyErrors());
    }
    @Test
    public void processEl30FailTest() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        String el_ = "";
        processEl(el_, context_);
        assertNotNull(getException(context_));
    }

    @Test
    public void processEl33FailTest() {
        Configuration conf_ = getConfiguration4();
        addImportingPage(conf_);
        String el_ = "1<2<3";
        processEl(el_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }

    @Test
    public void processEl34FailTest() {
        Configuration conf_ = getConfiguration4();
        addImportingPage(conf_);
        String el_ = "f(,)";
        processEl(el_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void processAffect17FailTest() {
        Configuration context_ = getConfiguration4();
        LgNames custLgNames_ = context_.getContext().getStandards();
        String primIntType_ = custLgNames_.getAliasPrimInteger();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] exp_ = new Struct[1];
        exp_[0] = new IntStruct(0);
        lv_.setStruct(new ArrayStruct(exp_, StringExpUtil.getPrettyArrayType(primIntType_)));
        lv_.setClassName(StringExpUtil.getPrettyArrayType(primIntType_));
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        processEl("$this()", context_);
        assertNotNull(getException(context_));
    }
    @Test
    public void processAffect18FailTest() {
        Configuration context_ = getConfiguration4();
        LgNames custLgNames_ = context_.getContext().getStandards();
        String primIntType_ = custLgNames_.getAliasPrimInteger();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] exp_ = new Struct[1];
        exp_[0] = new IntStruct(0);
        lv_.setStruct(new ArrayStruct(exp_, StringExpUtil.getPrettyArrayType(primIntType_)));
        lv_.setClassName(StringExpUtil.getPrettyArrayType(primIntType_));
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        processEl("v(1)", context_);
        assertNotNull(getException(context_));
    }
    @Test
    public void processAffect19FailTest() {
        Configuration context_ = getConfiguration4();
        LgNames custLgNames_ = context_.getContext().getStandards();
        String primIntType_ = custLgNames_.getAliasPrimInteger();
        addImportingPage(context_);
        context_.getLastPage().setGlobalArgumentStruct(NullStruct.NULL_VALUE,context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] exp_ = new Struct[1];
        exp_[0] = new IntStruct(0);
        lv_.setStruct(new ArrayStruct(exp_, StringExpUtil.getPrettyArrayType(primIntType_)));
        lv_.setClassName(StringExpUtil.getPrettyArrayType(primIntType_));
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        processEl("v(1)", context_);
        assertNotNull(getException(context_));
    }
    @Test
    public void processAffect2FailTest() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(1));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().getPageEl().getParameters().putAllMap(localVars_);
        processEl("v=12i", context_);
        assertTrue(!context_.isEmptyErrors());
    }
    @Test
    public void processAffect3FailTest() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] c_ = new Struct[1];
        c_[0] = new IntStruct(0);
        lv_.setStruct(new ArrayStruct(c_, ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        processEl("v[0i]=\"12i\"", context_);
        assertTrue(!context_.isEmptyErrors());
    }
    @Test
    public void processEl200Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        processEl("$($byte)$null", context_);
        assertTrue(!context_.isEmptyErrors());
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        processEl("$new pkg.Ex(5).inst=10", conf_);
        assertTrue(!conf_.isEmptyErrors());
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        processEl("$new pkg.ExTwo()", conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void processEl453_Test() {
        Configuration conf_ = getConfiguration4();
        addImportingPage(conf_);
        processEl("\"\".()", conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void processEl455Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst=14;\n");
        xml_.append(" $public $int res(#T v){\n");
        xml_.append("  $return ($int)inst+($int)v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        processEl("$new pkg.Ex<$int>().res($id(pkg.Ex,#S),15)", conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void processEl456Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst=14;\n");
        xml_.append(" $public $int res(#T v){\n");
        xml_.append("  $return ($int)inst+($int)v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        processEl("$new pkg.Ex<$int>().res($id(pkg.Ex,pkg.Ex<pkg.Ex>),15)", conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void processEl457Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst=14;\n");
        xml_.append(" $public $int res(#T v){\n");
        xml_.append("  $return ($int)inst+($int)v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        processEl("$new pkg.Ex<$int>().res($id(pkg.Ex,pkg.Ex<),15)", conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void processEl458Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst=14;\n");
        xml_.append(" $public $int res(#T v){\n");
        xml_.append("  $return ($int)inst+($int)v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        processEl("$new pkg.Ex<$int>().res($id(pkg.Ex,$void),15)", conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void processEl459Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst=14;\n");
        xml_.append(" $public $int res(#T v){\n");
        xml_.append("  $return ($int)inst+($int)v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        processEl("$new pkg.Ex<$int>().res($id(pkg.ExInex),15)", conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void processEl461Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Ex<#T> {\n");
        xml_.append(" $public $static $int inst=14;\n");
        xml_.append(" $public $int res(#T v){\n");
        xml_.append("  $return ($int)inst+($int)v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration5(files_);
        addImportingPage(conf_);
        processEl("$new pkg.Outer.Ex<$int>().res($id($void,#T),15)", conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void processEl462Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Ex<#T> {\n");
        xml_.append(" $public $static $int inst=14;\n");
        xml_.append(" $public $int res(#T v){\n");
        xml_.append("  $return ($int)inst+($int)v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        processEl("$new pkg.Outer.Ex<$int>().res($id(pkg.Outer.ExInex,#T),15)", conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void processEl464Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $class Ex<#T:pkg.Outer> {\n");
        xml_.append(" $public $static $int inst=14;\n");
        xml_.append(" $public $int res(#T v){\n");
        xml_.append("  $return ($int)inst+($int)v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        processEl("$new $void<>[i]+$new pkg.Outer.Ex<pkg.Outer.Ex<$int>>()", conf_);
        assertTrue(!conf_.isEmptyErrors());
    }

    private static void processEl(String _el, Configuration _cont) {
        if (_cont.hasPages() && _cont.getContext().getAnalyzing() != null) {
            _cont.getContext().setGlobalClass(_cont.getLastPage().getGlobalClass());
        }
        processEl(_el, 0, _cont);
        assertTrue(!_cont.isEmptyErrors());
    }

    private static Configuration getConfiguration4() {
        return getConfiguration4(new StringMap<String>());
    }
    private static Configuration getConfiguration4(StringMap<String> _files) {
        return getConfiguration4(_files,true);
    }
    private static Configuration getConfiguration4(StringMap<String> _files, boolean _init) {
        Configuration conf_ = getConfiguration(_files, _init);
        return conf_;
    }

    private static Configuration getConfiguration(StringMap<String> _files, boolean _init) {
        Configuration conf_ = EquallableExUtil.newConfiguration();
        Options opt_ = new Options();
        ContextEl cont_ = InitializationLgNames.buildStdThree(opt_);
        conf_.setContext(cont_);
        cont_.setFullStack(new AdvancedFullStack(conf_));
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        standards_.setHeaders(getHeaders(_files, cont_));
        assertTrue(cont_.isEmptyErrors());
        ((BeanCustLgNames)standards_).buildIterables(conf_);
        return conf_;
    }

    private static Configuration getConfiguration5(StringMap<String> _files) {
        return getConfiguration5(_files,true);
    }
    private static Configuration getConfiguration5(StringMap<String> _files, boolean _init) {
        Configuration conf_ = getConfiguration(_files, _init);
        return conf_;
    }

}
