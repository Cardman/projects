package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.NoExiting;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.ExecClassesUtil;
import code.expressionlanguage.exec.InitClassState;
import code.expressionlanguage.analyze.instr.ElResolver;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.IndexConstants;
import org.junit.Test;

public final class RenderExpUtilSucessTest extends CommonRender {
    private static final String ARR_INT = "[$int";
    private static final String ARR_ARR_INT = "[[$int";
    private static final String ARR_INTEGER = "[java.lang.Integer";
    private static final String ARR_ARR_INTEGER = "[[java.lang.Integer";

    @Test
    public void processEl1Test() {
        Argument arg_ = processEl(new StringMap<String>(), "5");
        assertEq(5L, getNumber(arg_));
    }
    @Test
    public void processEl2Test() {
        Argument arg_ = processEl(new StringMap<String>(), "$static(java.lang.Long).MAX_VALUE");
        assertEq(Long.MAX_VALUE, getNumber(arg_));
    }
    @Test
    public void processEl3Test() {
        Argument arg_ = processEl(new StringMap<String>(), "(1+2)*3");
        assertEq(9L, getNumber(arg_));
    }
    @Test
    public void processEl4Test() {
        Argument arg_ = processEl(new StringMap<String>(), "1- -1");
        assertEq(2L, getNumber(arg_));
    }
    @Test
    public void processEl5Test() {
        Argument arg_ = processEl(new StringMap<String>(), "1+2*3");
        assertEq(7L, getNumber(arg_));
    }
    @Test
    public void processEl6Test() {
        Argument arg_ = processEl(new StringMap<String>(), "- -1");
        assertEq(1L, getNumber(arg_));
    }
    @Test
    public void processEl7Test() {
        Argument arg_ = processEl(new StringMap<String>(), "$static($math).abs(-8l)");
        assertEq(8L, getNumber(arg_));
    }
    @Test
    public void processEl8Test() {
        Argument arg_ = processEl(new StringMap<String>(), "$static($math).abs(8l)");
        assertEq(8L, getNumber(arg_));
    }
    @Test
    public void processEl10Test() {
        Argument arg_ = processEl(new StringMap<String>(), "40908c");
        assertEq(40908, getNumber(arg_));
    }
    @Test
    public void processEl11Test() {
        Argument arg_ = processEl(new StringMap<String>(), "'\\u9fcb'");
        assertEq(40907, getNumber(arg_));
    }
    @Test
    public void processEl12Test() {
        Argument arg_ = processEl(new StringMap<String>(), "'\\\\'");
        assertEq('\\', getNumber(arg_));
    }
    @Test
    public void processEl13Test() {
        Argument arg_ = processEl(new StringMap<String>(), "'\\''");
        assertEq('\'', getNumber(arg_));
    }
    @Test
    public void processEl14Test() {
        Argument arg_ = processEl(new StringMap<String>(), "'\"'");
        assertEq('"', getNumber(arg_));
    }
    @Test
    public void processEl15Test() {
        Argument arg_ = processEl(new StringMap<String>(), "'\\n'");
        assertEq('\n', getNumber(arg_));
    }

    @Test
    public void processEl16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument arg_ = processElNormalField(files_, new ClassField("pkg.Ex", "inst"), "pkg.Ex", new IntStruct(2), "v", "pkg.Ex");
        assertEq(2, getNumber(arg_));
    }

    @Test
    public void processEl17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestConfiguration context_ = getConfigurationQuick(files_);
        setGlobalType(context_, "pkg.Ex");
        CustList<OperationNode> all_ = getQuickAnalyzedFwd("v.inst", 0, context_, context_.getAnalyzingDoc(),"pkg.Ex","v",false);
        addImportingPage(context_);
        Struct str_ = initAndSet(context_, new ClassField("pkg.Ex", "inst"), new IntStruct(2), "pkg.Ex");
        context_.getLastPage().getPageEl().getRefParams().addEntry("v",new VariableWrapper(LocalVariable.newLocalVariable(str_,"pkg.Ex")));
        Argument arg_ = buildAndCalculate(context_, all_);
        assertEq(2, getNumber(arg_));
    }
    @Test
    public void processEl18Test() {
        Argument arg_ = processEl(new StringMap<String>(), "5 $instanceof java.lang.Number");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl19Test() {
        Argument arg_ = processEl(new StringMap<String>(), "'5' $instanceof java.lang.Number");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl20Test() {
        Argument arg_ = processEl(new StringMap<String>(), "!('5' $instanceof java.lang.Number)");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl21Test() {
        Argument arg_ = processEl(new StringMap<String>(), "1+1==2");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl22Test() {
        Argument arg_ = processEl(new StringMap<String>(), "1+1!=2");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl23Test() {
        Argument arg_ = processEl(new StringMap<String>(), "1+1==2&&1+0==8");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl24Test() {
        Argument arg_ = processEl(new StringMap<String>(), "1+1!=2||1+7==8");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl25Test() {
        Argument arg_ = processEl(new StringMap<String>(), "1+1==2&&(1+0==8||3*3==9)");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl26Test() {
        Argument arg_ = processEl(new StringMap<String>(), "1+1==2||1+6==8&&1==1");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl29Test() {
        Argument arg_ = processEl(new StringMap<String>(), "1+1==2||1/0>8");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl31Test() {
        Argument arg_ = processEl(new StringMap<String>(), "$static($math).abs(-8i)");
        assertEq(8, getNumber(arg_));
    }
    @Test
    public void processEl32Test() {
        Argument arg_ = processEl(new StringMap<String>(), "$static($math).abs(8i)");
        assertEq(8, getNumber(arg_));
    }
    @Test
    public void processEl33Test() {
        Argument arg_ = processEl(new StringMap<String>(), "$static($math).abs(-8I)");
        assertEq(8, getNumber(arg_));
    }
    @Test
    public void processEl34Test() {
        Argument arg_ = processEl(new StringMap<String>(), "$static($math).abs(8I)");
        assertEq(8, getNumber(arg_));
    }
    @Test
    public void processEl35Test() {
        Argument arg_ = processEl(new StringMap<String>(), "$static($math).abs(-8L)");
        assertEq(8L, getNumber(arg_));
    }
    @Test
    public void processEl36Test() {
        Argument arg_ = processEl(new StringMap<String>(), "$static($math).abs(8L)");
        assertEq(8L, getNumber(arg_));
    }
    @Test
    public void processEl39Test() {
        Argument arg_ = processEl(new StringMap<String>(), "$null $instanceof java.lang.Object");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl58Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        String stringType_ = context_.getAliasString();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("7"));
        lv_.setClassName(stringType_);
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("8"));
        lv_.setClassName(stringType_);
        localVars_.put("d", lv_);
        lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("varargs;{0} {1} {2}"));
        lv_.setClassName(stringType_);
        localVars_.put("f", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        Argument arg_ = calcLow("f.format($vararg(java.lang.CharSequence),$firstopt(v),d,v)", context_);
        assertEq("varargs;7 8 7",getString(arg_));
    }
    @Test
    public void processEl59Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        String stringType_ = context_.getAliasString();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("varargs;{0} {1} {2}"));
        lv_.setClassName(stringType_);
        localVars_.put("f", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        Argument arg_ = calcLow("f.format($vararg(java.lang.CharSequence))", context_);
        assertEq("varargs;{0} {1} {2}",getString(arg_));
    }
    @Test
    public void processEl60Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        String stringType_ = context_.getAliasString();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("7"));
        lv_.setClassName(stringType_);
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("8"));
        lv_.setClassName(stringType_);
        localVars_.put("d", lv_);
        lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("varargs;{0} {1} {2}"));
        lv_.setClassName(stringType_);
        localVars_.put("f", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        Argument arg_ = calcLow("f.format(v,d,v)", context_);
        assertEq("varargs;7 8 7",getString(arg_));
    }
    @Test
    public void processEl61Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        String stringType_ = context_.getAliasString();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("varargs;{0} {1} {2}"));
        lv_.setClassName(stringType_);
        localVars_.put("f", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        Argument arg_ = calcLow("f.format()", context_);
        assertEq("varargs;{0} {1} {2}",getString(arg_));
    }

    @Test
    public void processEl63Test() {
        Argument arg_ = processElNormal3("$new $int[1i]", new StringMap<String>());
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INT, ((ArrayStruct) res_).getClassName());
        assertEq(1, (((ArrayStruct) res_).getInstance()).length);
        assertEq(0, ((NumberStruct) (((ArrayStruct) res_).getInstance())[0]).intStruct());
    }
    @Test
    public void processEl64Test() {
        Argument arg_ = processElNormal3("$new $int[1i][]", new StringMap<String>());
        Struct res_ = arg_.getStruct();
        assertEq(ARR_ARR_INT, ((ArrayStruct) res_).getClassName());
        assertEq(1, (((ArrayStruct) res_).getInstance()).length);
        assertSame(NullStruct.NULL_VALUE, (((ArrayStruct) res_).getInstance())[0]);
    }
    @Test
    public void processEl65Test() {
        Argument arg_ = processElNormal3("$new java.lang.Integer[2i]", new StringMap<String>());
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INTEGER, ((ArrayStruct) res_).getClassName());
        assertEq(2, (((ArrayStruct) res_).getInstance()).length);
        assertSame(NullStruct.NULL_VALUE, (((ArrayStruct) res_).getInstance())[0]);
        assertSame(NullStruct.NULL_VALUE, (((ArrayStruct) res_).getInstance())[1]);
    }
    @Test
    public void processEl66Test() {
        Argument arg_ = processElNormal3("$new java.lang.Integer[2i][]", new StringMap<String>());
        Struct res_ = arg_.getStruct();
        assertEq(ARR_ARR_INTEGER, ((ArrayStruct) res_).getClassName());
        assertEq(2, (((ArrayStruct) res_).getInstance()).length);
        assertSame(NullStruct.NULL_VALUE, (((ArrayStruct) res_).getInstance())[0]);
        assertSame(NullStruct.NULL_VALUE, (((ArrayStruct) res_).getInstance())[1]);
    }
    @Test
    public void processEl68Test() {
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        setValues(lv_, 0, 0, ARR_INT);
        localVars_.put("arrays", lv_);
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        CommonRender.setLocalVars(context_, localVars_);
        Argument arg_ = calcLow("arrays[0i]", context_);
        assertEq(0, getNumber(arg_));
    }
    @Test
    public void processEl69Test() {
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        setPairs(lv_);
        localVars_.put("arrays", lv_);
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        CommonRender.setLocalVars(context_, localVars_);
        Argument arg_ = calcLow("arrays[0i].length", context_);
        assertEq(2, getNumber(arg_));
    }
    @Test
    public void processEl70Test() {
        Argument arg_ = processEl(new StringMap<String>(), "!!$false");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl72Test() {
        Argument arg_ = processEl(new StringMap<String>(), "$static(java.lang.Byte).MAX_VALUE");
        assertEq((byte)127, getNumber(arg_));
    }
    @Test
    public void processEl77Test() {
        Argument arg_ = processEl(new StringMap<String>(), "(\"Hello\\\\\"+\"World\").length()");
        assertEq(11, getNumber(arg_));
    }
    @Test
    public void processEl78Test() {
        Argument arg_ = processEl(new StringMap<String>(), "(\"Hello\\\"\"+\"World\").length()");
        assertEq(11, getNumber(arg_));
    }
    @Test
    public void processEl79Test() {
        Argument arg_ = processEl(new StringMap<String>(), "(\"Hello\\\\\"+'\\\\').length()");
        assertEq(7, getNumber(arg_));
    }
    @Test
    public void processEl80Test() {
        Argument arg_ = processEl(new StringMap<String>(), "(\"Hello\\\"\"+'\\'').length()");
        assertEq(7, getNumber(arg_));
    }
    @Test
    public void processEl81Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        String stringType_ = context_.getAliasString();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("7"));
        lv_.setClassName(stringType_);
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("8"));
        lv_.setClassName(stringType_);
        localVars_.put("d", lv_);
        lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("varargs;{0} {1} {2}"));
        lv_.setClassName(stringType_);
        localVars_.put("f", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        Argument arg_ = calcLow("(f.format($vararg(java.lang.CharSequence),$firstopt(v),d,v)+'\\'').length()", context_);
        assertEq(14, getNumber(arg_));
    }
    @Test
    public void processEl82Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        setVal(lv_, 8, ARR_INT);
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        Argument arg_ = calcLow("$static($math).abs(v[0i]+2)*2", context_);
        assertEq(20L, getNumber(arg_));
    }
    @Test
    public void processEl83Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        setVal(lv_, 8, ARR_INT);
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        Argument arg_ = calcLow("(v[0i]+2)*2", context_);
        assertEq(20L, getNumber(arg_));
    }
    @Test
    public void processEl87Test() {
        Argument arg_ = processElNormal3("$bool(1>0,0i,1i)", new StringMap<String>());
        assertEq(0, getNumber(arg_));
    }
    @Test
    public void processEl88Test() {
        Argument arg_ = processElNormal3("$bool(1<0,0i,1i)", new StringMap<String>());
        assertEq(1, getNumber(arg_));
    }
    @Test
    public void processEl89Test() {
        Argument arg_ = processElNormal3("$bool(1>0,0i,1i/0i)", new StringMap<String>());
        assertEq(0, getNumber(arg_));
    }
    @Test
    public void processEl90Test() {
        Argument arg_ = processElNormal3("$bool(1<0,1i/0i,1i)", new StringMap<String>());
        assertEq(1, getNumber(arg_));
    }
    @Test
    public void processEl95Test() {
        Argument arg_ = processElNormal3("($new $int[1i])[0i]", new StringMap<String>());
        assertEq(0, getNumber(arg_));
    }
    @Test
    public void processEl96Test() {
        Argument arg_ = processElNormal3("$new $int[]{2i}", new StringMap<String>());
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INT, ((ArrayStruct) res_).getClassName());
        Struct[] o_ = ((ArrayStruct) res_).getInstance();
        assertEq(1, o_.length);
        assertEq(2, ((NumberStruct)o_[0]).intStruct());
    }
    @Test
    public void processEl97Test() {
        Argument arg_ = processElNormal3("$new $int[]{3i,7i}", new StringMap<String>());
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INT, ((ArrayStruct) res_).getClassName());
        Struct[] o_ = ((ArrayStruct) res_).getInstance();
        assertEq(2, o_.length);
        assertEq(3, ((NumberStruct)o_[0]).intStruct());
        assertEq(7, ((NumberStruct)o_[1]).intStruct());
    }
    @Test
    public void processEl98Test() {
        Argument arg_ = processElNormal3("$new $int[]{}", new StringMap<String>());
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INT, ((ArrayStruct) res_).getClassName());
        Struct[] o_ = ((ArrayStruct) res_).getInstance();
        assertEq(0, o_.length);
    }
    @Test
    public void processEl99Test() {
        Argument arg_ = processElNormal3("$new java.lang.Integer[]{3i,7i}", new StringMap<String>());
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INTEGER, ((ArrayStruct) res_).getClassName());
        Struct[] o_ = ((ArrayStruct) res_).getInstance();
        assertEq(2, o_.length);
        assertEq(3, ((NumberStruct)o_[0]).intStruct());
        assertEq(7, ((NumberStruct)o_[1]).intStruct());
    }
    @Test
    public void processEl100Test() {
        Argument arg_ = processDelimiters(" {(1+2)*3+\" hello\"+\" world {every body ;)\"} ", 43);
        assertEq("9 hello world {every body ;)",getString(arg_));
    }
    @Test
    public void processEl101Test() {
        Argument arg_ = processDelimiters(" {(\"hello \"+\"world\").length()} ", 30);
        assertEq(11, getNumber(arg_));
    }
    @Test
    public void processEl105Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument arg_ = processEl(files_, "$static(pkg.Ex).exmeth()");
        assertEq(9, getNumber(arg_));
    }
    @Test
    public void processEl106Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument arg_ = processEl(files_, "$static(pkg.Ex).exmeth(6i)");
        assertEq(15, getNumber(arg_));
    }
    @Test
    public void processEl107Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestConfiguration cont_ = getConfigurationQuick(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormalLow("$new pkg.Ex()", cont_, "pkg.Ex");
        assertEq("pkg.Ex",arg_.getStruct().getClassName(cont_.getContext()));
    }
    @Test
    public void processEl109Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument arg_ = processEl(files_, "$classchoice(pkg.Ex)exmeth(6i)");
        assertEq(15, getNumber(arg_));
    }
    @Test
    public void processEl110Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst=2i;\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument arg_ = processEl(files_, "$classchoice(pkg.Ex)inst");
        assertEq(2, getNumber(arg_));
    }
    @Test
    public void processEl112Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestConfiguration context_ = getConfigurationQuick(files_);
        setGlobalType(context_, "pkg.Ex");
        CustList<OperationNode> all_ = getQuickAnalyzedFwd("v.inst", 0, context_, context_.getAnalyzingDoc(),"pkg.Ex","v",false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct str_ = initAndSet(context_, new ClassField("pkg.Ex", "inst"), new IntStruct(2), "pkg.Ex");
        lv_.setStruct(str_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        for (EntryCust<String, LocalVariable> e: localVars_.entryList()) {
            context_.getLastPage().getPageEl().getRefParams().addEntry(e.getKey(),new VariableWrapper(e.getValue()));
        }
        Argument arg_ = buildAndCalculate(context_, all_);
        assertEq(2, getNumber(arg_));
    }
    @Test
    public void processEl119Test() {
        Argument arg_ = processEl(new StringMap<String>(), "(1y+2y)*3");
        assertEq(9L, getNumber(arg_));
    }
    @Test
    public void processEl120Test() {
        Argument arg_ = processEl(new StringMap<String>(), "(1s+2y)*3");
        assertEq(9L, getNumber(arg_));
    }
    @Test
    public void processEl121Test() {
        Argument arg_ = processEl(new StringMap<String>(), "- -1y");
        assertEq(1, getNumber(arg_));
    }
    @Test
    public void processEl122Test() {
        Argument arg_ = processEl(new StringMap<String>(), "-1y");
        assertEq(-1, getNumber(arg_));
    }
    @Test
    public void processEl123Test() {
        Argument arg_ = processEl(new StringMap<String>(), "java.lang.Byte.MAX_VALUE+java.lang.Byte.MAX_VALUE");
        int max_ = Byte.MAX_VALUE+Byte.MAX_VALUE;
        assertEq(max_, getNumber(arg_));
    }
    @Test
    public void processEl123FailTest() {
        Argument arg_ = processEl(new StringMap<String>(), "+1y");
        assertEq(1, getNumber(arg_));
    }
    @Test
    public void processEl124Test() {
        Argument arg_ = processEl(new StringMap<String>(), "+-1y");
        assertEq(-1, getNumber(arg_));
    }
    @Test
    public void processEl125Test() {
        Argument arg_ = processEl(new StringMap<String>(), "-.25e0+.5");
        assertEq(0.25d, getDouble(arg_));
    }
    @Test
    public void processEl126Test() {
        Argument arg_ = processDelimiters(" {(1+2)*3+\" hello\"+\" world {every body ;)\"}{5*8} ", 43);
        assertEq("9 hello world {every body ;)",getString(arg_));
    }
    @Test
    public void processEl126_Test() {
        Argument arg_ = processDoubleDelimiters(" {(1+2)*3+\" hello\"+\" world {every body ;)\"}{5*8} ", 44, 48);
        assertEq(40, getNumber(arg_));
    }
    @Test
    public void processEl127Test() {
        Argument arg_ = processDelimiters(" {(\"hello \"+\"world\").length()}{5*8} ", 30);
        assertEq(11, getNumber(arg_));
    }
    @Test
    public void processEl127_Test() {
        Argument arg_ = processDoubleDelimiters(" {(\"hello \"+\"world\").length()}{5*8} ", 31, 35);
        assertEq(40, getNumber(arg_));
    }

    @Test
    public void processEl128Test() {
        Argument arg_ = processEl(new StringMap<String>(), "1_0+2*3");
        assertEq(16L, getNumber(arg_));
    }
    @Test
    public void processEl129Test() {
        Argument arg_ = processEl(new StringMap<String>(), "$static($math).mod(-8l,3l)");
        assertEq(1L, getNumber(arg_));
    }
    @Test
    public void processEl130Test() {
        Argument arg_ = processEl(new StringMap<String>(), "$static($math).quot(-8l,3l)");
        assertEq(-3L, getNumber(arg_));
    }
    @Test
    public void processEl131Test() {
        Argument arg_ = processElNormal3("$new $int[1i][1i]", new StringMap<String>());
        Struct res_ = arg_.getStruct();
        assertEq(ARR_ARR_INT, ((ArrayStruct) res_).getClassName());
        assertEq(1, (((ArrayStruct) res_).getInstance()).length);
        assertEq(ARR_INT, ((ArrayStruct)(((ArrayStruct) res_).getInstance())[0]).getClassName());
        assertEq(1, (((ArrayStruct)(((ArrayStruct) res_).getInstance())[0]).getInstance()).length);
        assertEq(0, ((NumberStruct) (((ArrayStruct)(((ArrayStruct) res_).getInstance())[0]).getInstance())[0]).intStruct());
    }
    @Test
    public void processEl132Test() {
        Argument arg_ = processElNormal3("$new java.lang.Integer[1i][1i]", new StringMap<String>());
        Struct res_ = arg_.getStruct();
        assertEq(ARR_ARR_INTEGER, ((ArrayStruct) res_).getClassName());
        assertEq(1, (((ArrayStruct) res_).getInstance()).length);
        assertEq(ARR_INTEGER, ((ArrayStruct)(((ArrayStruct) res_).getInstance())[0]).getClassName());
        assertSame(NullStruct.NULL_VALUE, (((ArrayStruct)(((ArrayStruct) res_).getInstance())[0]).getInstance())[0]);
    }
    @Test
    public void processEl133Test() {
        Argument arg_ = processEl(new StringMap<String>(), "($double)(1 + 2) * 3.0");
        assertEq(9L, getNumber(arg_));
    }
    @Test
    public void processEl134Test() {
        Argument arg_ = processEl(new StringMap<String>(), " 2.0 + ($double)$static($math). quot( -8l, 3l) + 3.0");
        assertEq(2L, getNumber(arg_));
    }
    @Test
    public void processEl135Test() {
        Argument arg_ = processEl(new StringMap<String>(), "1 + 2 ");
        assertEq(3L, getNumber(arg_));
    }
    @Test
    public void processEl136Test() {
        Argument arg_ = processEl(new StringMap<String>(), "1. + 2. ");
        assertEq(3L, getNumber(arg_));
    }
    @Test
    public void processEl137Test() {
        Argument arg_ = processEl(new StringMap<String>(), "1.d + 2.d ");
        assertEq(3L, getNumber(arg_));
    }
    @Test
    public void processEl138Test() {
        Argument arg_ = processEl(new StringMap<String>(), "-.2_5e0+.5");
        assertEq(0.25d, getDouble(arg_));
    }
    @Test
    public void processEl139Test() {
        Argument arg_ = processEl(new StringMap<String>(), "-.25e0_0+.5");
        assertEq(0.25d, getDouble(arg_));
    }
    @Test
    public void processEl140Test() {
        Argument arg_ = processEl(new StringMap<String>(), "1_0.d + 2.d ");
        assertEq(12L, getNumber(arg_));
    }
    @Test
    public void processEl141Test() {
        Argument arg_ = processEl(new StringMap<String>(), "1.05e1");
        assertEq(10.5d, getDouble(arg_));
    }
    @Test
    public void processEl142Test() {
        Argument arg_ = processEl(new StringMap<String>(), "1.00625e1");
        assertEq(10.0625d, getDouble(arg_));
    }
    @Test
    public void processEl143Test() {
        Argument arg_ = processEl(new StringMap<String>(), "100.625e-1");
        assertEq(10.0625d, getDouble(arg_));
    }
    @Test
    public void processEl144Test() {
        Argument arg_ = processEl(new StringMap<String>(), "100.625");
        assertEq(100.625d, getDouble(arg_));
    }
    @Test
    public void processEl145Test() {
        Argument arg_ = processEl(new StringMap<String>(), "123456789123456789123456789.0");
        assertEq(1.2345678912345678912e26, getDouble(arg_));
    }
    @Test
    public void processEl147Test() {
        Argument arg_ = processEl(new StringMap<String>(), "123456789123456789123456789.");
        assertEq(1.2345678912345678912e26, getDouble(arg_));
    }
    @Test
    public void processEl148Test() {
        Argument arg_ = processEl(new StringMap<String>(), "123456789123456789123456789.e-1");
        assertEq(1.2345678912345678912e25, getDouble(arg_));
    }
    @Test
    public void processEl149Test() {
        Argument arg_ = processEl(new StringMap<String>(), "123456789123456789123456789.e1");
        assertEq(1.2345678912345678912e27, getDouble(arg_));
    }
    @Test
    public void processEl150Test() {
        Argument arg_ = processEl(new StringMap<String>(), "123456.e1");
        assertEq(1234560, getDouble(arg_));
    }
    @Test
    public void processEl151Test() {
        Argument arg_ = processEl(new StringMap<String>(), ".078125e-1");
        assertEq(.078125e-1, getDouble(arg_));
    }
    @Test
    public void processEl152Test() {
        Argument arg_ = processEl(new StringMap<String>(), "123456789123456789123456789.0e-36");
        assertEq(1.2345678912345678912e-10, getDouble(arg_));
    }
    @Test
    public void processEl153Test() {
        Argument arg_ = processEl(new StringMap<String>(), "0.0e-36");
        assertEq(0.0, getDouble(arg_));
    }
    @Test
    public void processEl154Test() {
        Argument arg_ = processEl(new StringMap<String>(), "-0.0e-36");
        assertEq(-0.0, getDouble(arg_));
    }
    @Test
    public void processEl155Test() {
        Argument arg_ = processEl(new StringMap<String>(), "0.625e-1");
        assertEq(0.0625, getDouble(arg_));
    }
    @Test
    public void processEl156Test() {
        Argument arg_ = processEl(new StringMap<String>(), ".625e-1");
        assertEq(0.0625, getDouble(arg_));
    }
    @Test
    public void processEl157Test() {
        Argument arg_ = processEl(new StringMap<String>(), "0.625e1");
        assertEq(6.25, getDouble(arg_));
    }
    @Test
    public void processEl158Test() {
        Argument arg_ = processEl(new StringMap<String>(), ".625e1");
        assertEq(6.25, getDouble(arg_));
    }
    @Test
    public void processEl159Test() {
        Argument arg_ = processEl(new StringMap<String>(), "0.625e0");
        assertEq(0.625, getDouble(arg_));
    }
    @Test
    public void processEl160Test() {
        Argument arg_ = processEl(new StringMap<String>(), ".625e0");
        assertEq(0.625, getDouble(arg_));
    }
    @Test
    public void processEl161Test() {
        Argument arg_ = processEl(new StringMap<String>(), "-.625e1");
        assertEq(-6.25, getDouble(arg_));
    }
    @Test
    public void processEl162Test() {
        Argument arg_ = processEl(new StringMap<String>(), "-.6e1");
        assertEq(-6.0, getDouble(arg_));
    }
    @Test
    public void processEl163Test() {
        Argument arg_ = processEl(new StringMap<String>(), "-.60e1");
        assertEq(-6.0, getDouble(arg_));
    }
    @Test
    public void processEl164Test() {
        Argument arg_ = processEl(new StringMap<String>(), ".6e1");
        assertEq(6.0, getDouble(arg_));
    }
    @Test
    public void processEl165Test() {
        Argument arg_ = processEl(new StringMap<String>(), ".6e2");
        assertEq(60.0, getDouble(arg_));
    }
    @Test
    public void processEl166Test() {
        Argument arg_ = processEl(new StringMap<String>(), "123456789123456789123456789.1e1");
        assertEq(1.2345678912345678912e27, getDouble(arg_));
    }
    @Test
    public void processEl167Test() {
        Argument arg_ = processEl(new StringMap<String>(), "100.e-1");
        assertEq(10.0, getDouble(arg_));
    }
    @Test
    public void processEl168Test() {
        Argument arg_ = processEl(new StringMap<String>(), "-100.e-1");
        assertEq(-10.0, getDouble(arg_));
    }
    @Test
    public void processEl169Test() {
        Argument arg_ = processEl(new StringMap<String>(), "-1.e1");
        assertEq(-10.0, getDouble(arg_));
    }
    @Test
    public void processEl170Test() {
        Argument arg_ = processEl(new StringMap<String>(), "-1.");
        assertEq(-1.0, getDouble(arg_));
    }
    @Test
    public void processEl171Test() {
        Argument arg_ = processEl(new StringMap<String>(), "1e-123456789123456789123");
        assertEq(0.0, getDouble(arg_));
    }
    @Test
    public void processEl172Test() {
        Argument arg_ = processEl(new StringMap<String>(), "-1e-123456789123456789123");
        assertEq(-0.0, getDouble(arg_));
    }
    @Test
    public void processEl173Test() {
        Argument arg_ = processEl(new StringMap<String>(), "1e123456789123456789123");
        assertEq(Double.POSITIVE_INFINITY, getDouble(arg_));
    }
    @Test
    public void processEl174Test() {
        Argument arg_ = processEl(new StringMap<String>(), "-1e123456789123456789123");
        assertEq(Double.NEGATIVE_INFINITY, getDouble(arg_));
    }
    @Test
    public void processEl175Test() {
        Argument arg_ = processEl(new StringMap<String>(), "'\\u9FCB'");
        assertEq(40907, getNumber(arg_));
    }
    @Test
    public void processEl176Test() {
        Argument arg_ = processEl(new StringMap<String>(), "\"\\u9FCB\"");
        assertEq("\u9fcb",getString(arg_));
    }
    @Test
    public void processEl177Test() {
        Argument arg_ = processEl(new StringMap<String>(), "\"\\u9fcb\"");
        assertEq("\u9fcb",getString(arg_));
    }
    @Test
    public void processEl178Test() {
        Argument arg_ = processEl(new StringMap<String>(), "$static(java.lang.Long) .MAX_VALUE");
        assertEq(Long.MAX_VALUE, getNumber(arg_));
    }

    @Test
    public void processEl331Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(StringExpUtil.getPrettyArrayType(context_.getAliasPrimInteger()));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        CommonRender.setLocalVars(context_, localVars_);
        calcLow("arg={2}", context_);
        ArrayStruct struct_ = (ArrayStruct)lv_.getStruct();
        assertEq(1,struct_.getInstance().length);
        assertEq(2,((NumberStruct)struct_.getInstance()[0]).intStruct());
    }

    @Test
    public void processEl332Test() {
        Argument argument_ = processElNormal1Int(2, "arg<<2", "arg");
        assertEq(8,getNumber(argument_));
    }

    @Test
    public void processEl333Test() {
        Argument argument_ = processElNormal1Int(8, "arg>>2", "arg");
        assertEq(2,getNumber(argument_));
    }

    @Test
    public void processEl334Test() {
        Argument argument_ = processElNormal1Int(2, "arg<<<2", "arg");
        assertEq(8,getNumber(argument_));
    }

    @Test
    public void processEl335Test() {
        Argument argument_ = processElNormal1Int(8, "arg>>>2", "arg");
        assertEq(2,getNumber(argument_));
    }

    @Test
    public void processEl336Test() {
        Argument argument_ = processElNormal1Int(2, "arg<<<<2", "arg");
        assertEq(8,getNumber(argument_));
    }

    @Test
    public void processEl337Test() {
        Argument argument_ = processElNormal1Int(8, "arg>>>>2", "arg");
        assertEq(2,getNumber(argument_));
    }

    @Test
    public void processEl338Test() {
        Argument argument_ = processElNormal1Int(8, "arg>2", "arg");
        assertTrue(argument_.isTrue());
    }

    @Test
    public void processEl339Test() {
        Argument argument_ = processElNormal2BoolVars(false, false, "arg&&arg2");
        assertTrue(argument_.isFalse());
    }
    @Test
    public void processEl340Test() {
        Argument argument_ = processElNormal2BoolVars(true, false, "arg&&arg2");
        assertTrue(argument_.isFalse());
    }
    @Test
    public void processEl341Test() {
        Argument argument_ = processElNormal2BoolVars(true, true, "arg&&arg2");
        assertTrue(argument_.isTrue());
    }

    @Test
    public void processEl342Test() {
        Argument argument_ = processElNormal1Int(10, "arg&3", "arg");
        assertEq(2,getNumber(argument_));
    }

    @Test
    public void processEl343Test() {
        Argument argument_ = processElNormal1Int(8, "arg|2", "arg");
        assertEq(10,getNumber(argument_));
    }

    @Test
    public void processEl344Test() {
        Argument argument_ = processElNormal1Int(5, "arg^3", "arg");
        assertEq(6,getNumber(argument_));
    }

    @Test
    public void processEl345Test() {
        Argument argument_ = processElNormal2BoolVars(true, true, "arg||arg2");
        assertTrue(argument_.isTrue());
    }
    @Test
    public void processEl346Test() {
        Argument argument_ = processElNormal2BoolVars(false, true, "arg||arg2");
        assertTrue(argument_.isTrue());
    }
    @Test
    public void processEl347Test() {
        Argument argument_ = processElNormal2BoolVars(false, false, "arg||arg2");
        assertTrue(argument_.isFalse());
    }

    @Test
    public void processEl348Test() {
        Argument argument_ = processElNormal1String("arg>\"2\"");
        assertTrue(argument_.isTrue());
    }

    @Test
    public void processEl349Test() {
        Argument argument_ = processElNormal1Int(5, "~arg", "arg");
        assertEq(-6,getNumber(argument_));
    }

    @Test
    public void processEl350Test() {
        Argument argument_ = processElNormal1Int(5, "+arg", "arg");
        assertEq(5,getNumber(argument_));
    }

    @Test
    public void processEl351Test() {
        Argument argument_ = processElNormalBool(true, "!arg");
        assertTrue(argument_.isFalse());
    }

    @Test
    public void processEl352Test() {
        Argument argument_ = processElNormalBool(false, "!arg");
        assertTrue(argument_.isTrue());
    }

    @Test
    public void processEl353Test() {
        Argument argument_ = processElNormalBool(false, "$bool(arg,5,6)");
        assertEq(6,getNumber(argument_));
    }
    @Test
    public void processEl354Test() {
        Argument argument_ = processElNormalBool(true, "$bool(arg,5,6)");
        assertEq(5,getNumber(argument_));
    }

    @Test
    public void processEl355Test() {
        Argument argument_ = processElNormal1Int(5, "arg%3", "arg");
        assertEq(2,getNumber(argument_));
    }
    @Test
    public void processEl356Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestConfiguration context_ = getConfigurationQuick(files_);
        addImportingPage(context_);
        setGlobalType(context_, "pkg.Ex");
        context_.getAnalyzingDoc().setup(context_.getConfiguration(), context_.getDual());
        String globalClass_ = context_.getAnalyzing().getGlobalClass();
        setupAna(context_.getAnalyzingDoc(), context_.getAnalyzing());
        context_.getAnalyzing().setGlobalType(context_.getAnalyzing().getAnaClassBody(StringExpUtil.getIdFromAllTypes(globalClass_)));
        context_.getAnalyzing().setAccessStaticContext(MethodId.getKind(false));
        Delimiters d_ = checkSyntax(context_, "$this.inst", 0);
        String el_ = "$this.inst";
        OperationsSequence opTwo_ = rendOpSeq(0, context_, d_, el_);
        OperationNode op_ = rendOp(0, context_, opTwo_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        Classes.forwardAndClear(context_.getContext(), context_.getAnalyzing(), context_.getForwards());
        Struct str_ = initAndSet(context_, new ClassField("pkg.Ex", "inst"), new IntStruct(2), "pkg.Ex");
        setGlobalArgumentStruct(context_, str_);
        Argument arg_ = buildAndCalculate(context_, all_);
        assertEq(2, getNumber(arg_));
    }

    @Test
    public void processEl357Test() {
        Argument argument_ = processElNormalBoolInt(false, "$bool(arg,5+arg2,6+arg2)", new IntStruct(0));
        assertEq(6,getNumber(argument_));
    }
    @Test
    public void processEl358Test() {
        Argument argument_ = processElNormalBoolInt(true, "$bool(arg,5+arg2,6+arg2)", new IntStruct(0));
        assertEq(5,getNumber(argument_));
    }

    @Test
    public void processEl500Test() {
        Argument argument_ = processElNormal2BoolVars(false, false, "arg&&=1/0>1");
        assertTrue(argument_.isFalse());
    }
    @Test
    public void processEl500_Test() {
        Argument argument_ = processElNormal2BoolVars(false, false, "arg??=1/0>1");
        assertTrue(argument_.isFalse());
    }
    @Test
    public void processEl501_Test() {
        Argument argument_ = processElNormal2BooleanVars("arg??=arg2");
        assertTrue(argument_.isFalse());
    }

    @Test
    public void processEl500___Test() {
        Argument argument_ = processElNormal2BoolVars(false, false, "arg&&&=1/0>1");
        assertTrue(argument_.isFalse());
    }
    @Test
    public void processEl500____Test() {
        Argument argument_ = processElNormal2BoolVars(false, false, "arg???=1/0>1");
        assertTrue(argument_.isFalse());
    }
    @Test
    public void processEl501___Test() {
        Argument argument_ = processElNormal2BooleanVars("arg???=arg2");
        assertTrue(argument_.isFalse());
    }
    @Test
    public void processEl500__Test() {
        Argument argument_ = processElNormal2BoolVars(false, false, "arg??arg2");
        assertTrue(argument_.isFalse());
    }
    @Test
    public void processEl501__Test() {
        Argument argument_ = processElNormal2BooleanVars("arg??arg2");
        assertTrue(argument_.isFalse());
    }
    @Test
    public void processEl501Test() {
        Argument argument_ = processElNormal2BoolVars(true, false, "arg||=1/0>1");
        assertTrue(argument_.isTrue());
    }
    @Test
    public void processEl502Test() {
        Argument argument_ = processElNormal2BoolVars(true, false, "arg&&=arg2");
        assertTrue(argument_.isFalse());
    }
    @Test
    public void processEl503Test() {
        Argument argument_ = processElNormal2BoolVars(false, true, "arg||=arg2");
        assertTrue(argument_.isTrue());
    }
    @Test
    public void processEl_501Test() {
        Argument argument_ = processElNormal2BoolVars(true, false, "arg|||=1/0>1");
        assertTrue(argument_.isTrue());
    }
    @Test
    public void processEl_502Test() {
        Argument argument_ = processElNormal2BoolVars(true, false, "arg&&&=arg2");
        assertTrue(argument_.isFalse());
    }
    @Test
    public void processEl_503Test() {
        Argument argument_ = processElNormal2BoolVars(false, true, "arg|||=arg2");
        assertTrue(argument_.isTrue());
    }

    @Test
    public void processEl198Test() {
        Argument arg_ = processEl(new StringMap<String>(), "$(java.lang.Number)5");
        assertEq(5L, getNumber(arg_));
    }
    @Test
    public void processEl199Test() {
        Argument arg_ = processEl(new StringMap<String>(), "$($byte)5");
        assertEq(5L, getNumber(arg_));
    }
    @Test
    public void processEl201Test() {
        Argument arg_ = processElNormal3("$(java.lang.Byte)$null", new StringMap<String>());
        assertSame(NullStruct.NULL_VALUE, arg_.getStruct());
    }

    @Test
    public void processEl203Test() {
        Argument arg_ = processEl(new StringMap<String>(), "1!=2!=3");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl210Test() {
        Argument arg_ = processEl(new StringMap<String>(), "'1'+'2'");
        assertEq(99, getNumber(arg_));
    }
    @Test
    public void processEl211Test() {
        Argument arg_ = processEl(new StringMap<String>(), "\"\"+$new $char[]{'1','2'}[0]+$new $char[]{'1','2'}[1]");
        assertEq("12",getString(arg_));
    }
    @Test
    public void processEl212Test() {
        Argument arg_ = processEl(new StringMap<String>(), "('1'+'2')*3i");
        assertEq(297, getNumber(arg_));
    }
    @Test
    public void processEl213Test() {
        Argument arg_ = processEl(new StringMap<String>(), "'1'>1i");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl214Test() {
        Argument arg_ = processEl(new StringMap<String>(), "'1'<1i");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl215Test() {
        Argument arg_ = processEl(new StringMap<String>(), "'1'<1i");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl216Test() {
        Argument arg_ = processEl(new StringMap<String>(), "'1'>1i");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl217Test() {
        Argument arg_ = processEl(new StringMap<String>(), "'1'==49i");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl218Test() {
        Argument arg_ = processEl(new StringMap<String>(), "49i=='1'");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl213FailTest() {
        Argument arg_ = processEl(new StringMap<String>(), "('1'+'2')*'3'");
        assertEq(5049, getNumber(arg_));
    }
    @Test
    public void processEl219Test() {
        Argument arg_ = processEl(new StringMap<String>(), "6 + $($int) - $static($math).quot(8,5) - 2");
        assertEq(3, getNumber(arg_));
    }
    @Test
    public void processEl220Test() {
        Argument arg_ = processEl(new StringMap<String>(), "$class($int).getName()");
        assertEq("$int",getString(arg_));
    }
    @Test
    public void processEl221Test() {
        Argument arg_ = processEl(new StringMap<String>(), "$class(java.lang.Integer).getName()");
        assertEq("java.lang.Integer",getString(arg_));
    }
    @Test
    public void processEl222Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument arg_ = processEl(files_, "$class(pkg.Ex).getName()");
        assertEq("pkg.Ex",getString(arg_));
    }
    @Test
    public void processEl223Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument arg_ = processEl(files_, "$class(pkg.Ex).getName()");
        assertEq("pkg.Ex",getString(arg_));
    }
    @Test
    public void processEl224Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return $class(T).getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument arg_ = processEl(files_, "$new pkg.Ex<java.lang.Integer>().exmeth()");
        assertEq("java.lang.Integer",getString(arg_));
    }
    @Test
    public void processEl225Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<T>).getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument arg_ = processEl(files_, "$new pkg.Ex<java.lang.Integer>().exmeth()");
        assertEq("pkg.Ex<java.lang.Integer>",getString(arg_));
    }
    @Test
    public void processEl226Test() {
        Argument arg_ = processEl(new StringMap<String>(), "$static($Class).getClass(\"\").getName()");
        assertEq("java.lang.String",getString(arg_));
    }
    @Test
    public void processEl227Test() {
        Argument arg_ = processEl(new StringMap<String>(), "$static($Class).getClass(1i).getName()");
        assertEq("java.lang.Integer",getString(arg_));
    }
    @Test
    public void processEl228Test() {
        Argument arg_ = processElNormal3("$static($Class).getClass($null)", new StringMap<String>());
        assertTrue(arg_.isNull());
    }
    @Test
    public void processEl229Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<T>).getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument arg_ = processEl(files_, "$static($Class).getClass($new pkg.Ex<java.lang.Integer>()).getName()");
        assertEq("pkg.Ex<java.lang.Integer>",getString(arg_));
    }
    @Test
    public void processEl230Test() {
        Argument arg_ = processEl(new StringMap<String>(), "$class($int[]).getName()");
        assertEq("[$int",getString(arg_));
    }
    @Test
    public void processEl231Test() {
        Argument arg_ = processEl(new StringMap<String>(), "$class(java.lang.Integer[]).getName()");
        assertEq("[java.lang.Integer",getString(arg_));
    }
    @Test
    public void processEl232Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument arg_ = processEl(files_, "$class(pkg.Ex[]).getName()");
        assertEq("[pkg.Ex",getString(arg_));
    }
    @Test
    public void processEl233Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument arg_ = processEl(files_, "$class(pkg.Ex[]).getName()");
        assertEq("[pkg.Ex",getString(arg_));
    }
    @Test
    public void processEl234Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument arg_ = processEl(files_, "$class(pkg.Ex<java.lang.Integer>[]).getName()");
        assertEq("[pkg.Ex<java.lang.Integer>",getString(arg_));
    }
    @Test
    public void processEl235Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return $class(T[]).getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument arg_ = processEl(files_, "$new pkg.Ex<java.lang.Integer>().exmeth()");
        assertEq("[java.lang.Integer",getString(arg_));
    }
    @Test
    public void processEl236Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<T[]>).getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument arg_ = processEl(files_, "$new pkg.Ex<java.lang.Integer>().exmeth()");
        assertEq("pkg.Ex<[java.lang.Integer>",getString(arg_));
    }
    @Test
    public void processEl237Test() {
        Argument arg_ = processEl(new StringMap<String>(), "$static($Class).getClass($new $int[]{1i}).getName()");
        assertEq("[$int",getString(arg_));
    }
    @Test
    public void processEl238Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument arg_ = processEl(files_, "$static($Class).forName(\"pkg.Ex\",$true).getName()");
        assertEq("pkg.Ex",getString(arg_));
    }
    @Test
    public void processEl239Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i);\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $static($Class).forName(\"pkg.Ex\",$true).getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestConfiguration cont_ = getConfigurationQuick(files_);
        setGlobalType(cont_, "pkg.ExTwo");
        addImportingPage(cont_);
        CustList<OperationNode> all_ = getQuickAnalyzed("$static(pkg.ExTwo).exmeth()", 0, cont_, cont_.getAnalyzingDoc());
        Argument arg_ = buildAndCalculateFwd(cont_, all_);
        assertEq(14, ((NumberStruct) getStaticField(cont_)).intStruct());
        assertEq("pkg.Ex",getString(arg_));
    }
    @Test
    public void processEl240Test() {
        Argument arg_ = processEl(new StringMap<String>(), "$class($void).getName()");
        assertEq("$void",getString(arg_));
    }
    @Test
    public void processEl241Test() {
        Argument arg_ = processEl(new StringMap<String>(), "$static($Class).forName(\"$void\",$true).getName()");
        assertEq("$void",getString(arg_));
    }
    @Test
    public void processEl250Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i);\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class($Class).getDeclaredMethods(\"forName\",$true,$false,$class(java.lang.String),$class($boolean))[0i];\n");
        xml_.append("  m.invoke($null,\"pkg.Ex\",$true);\n");
        xml_.append("  $return $($int) $static(pkg.Ex).inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $abstract java.lang.String exmeth();\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Argument arg_ = processElLow(files_, "$static(pkg.ExTwo).exmeth()");
        assertEq(14, getNumber(arg_));
    }
    @Test
    public void processEl251Test() {
        Argument arg_ = processElLow(new StringMap<String>(), "$class($math).getDeclaredMethods(\"mod\",$true,$false,$class($int),$class($int))[0i].invoke($null,4i,3i)");
        assertEq(1, getNumber(arg_));
    }
    @Test
    public void processEl252Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i);\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$true,$false,$class($int))[0i];\n");
        xml_.append("  $return $($int) m.invoke($null,6i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $abstract java.lang.String exmeth();\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        AnalyzedTestConfiguration cont_ = getConfigurationQuick(files_);
        addImportingPage(cont_);
        Argument arg_ = calcLow("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(14, ((NumberStruct) getStaticField(cont_)).intStruct());
        assertEq(15, getNumber(arg_));
    }
    @Test
    public void processEl253Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst = 5i;\n");
        xml_.append(" $public $normal $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$false,$false)[0i];\n");
        xml_.append("  $return $($int) m.invoke($new pkg.Ex());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $abstract java.lang.String exmeth();\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Argument arg_ = processElLow(files_, "$static(pkg.ExTwo).exmeth()");
        assertEq(14, getNumber(arg_));
    }
    @Test
    public void processEl254Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst = 5i;\n");
        xml_.append(" $public $normal $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.ExAbs).getDeclaredMethods(\"exmeth\",$false,$false)[0i];\n");
        xml_.append("  $return $(java.lang.String) m.invoke($new pkg.ExConc());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $abstract java.lang.String exmeth();\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Argument arg_ = processElLow(files_, "$static(pkg.ExTwo).exmeth()");
        assertEq("out",getString(arg_));
    }
    @Test
    public void processEl255Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i);\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.ExAbs).getDeclaredMethods(\"exmeth\",$false,$false)[0i];\n");
        xml_.append("  $return $(java.lang.String) m.invokeDirect($new pkg.ExConc());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Argument arg_ = processElLow(files_, "$static(pkg.ExTwo).exmeth()");
        assertEq("super",getString(arg_));
    }
    @Test
    public void processEl256Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i);\n");
        xml_.append(" $public $static $int exmeth($int... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach($int i:e){\n");
        xml_.append("   t+=i;;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$true,$true,$class($int))[0i];\n");
        xml_.append("  $return $($int) m.invoke($null,$new java.lang.Object[]{$new $int[]{4i,6i}});\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Argument arg_ = processElLow(files_, "$static(pkg.ExTwo).exmeth()");
        assertEq(19, getNumber(arg_));
    }
    @Test
    public void processEl257Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $normal $int exmeth(T e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+$($int)e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex<java.lang.Integer>).getDeclaredMethods(\"exmeth\",$false,$false,$class(java.lang.Integer))[0i];\n");
        xml_.append("  $return $($int) m.invoke($new pkg.Ex<java.lang.Integer>(),5i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Argument arg_ = processElLow(files_, "$static(pkg.ExTwo).exmeth()");
        assertEq(14, getNumber(arg_));
    }
    @Test
    public void processEl258Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $normal $int exmeth(T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach(T i:e){\n");
        xml_.append("   t+=$($int)i;;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex<java.lang.Integer>).getDeclaredMethods(\"exmeth\",$false,$true,$class(java.lang.Integer))[0i];\n");
        xml_.append("  $return $($int) m.invoke($new pkg.Ex<java.lang.Integer>(),$new java.lang.Object[]{$new java.lang.Integer[]{4i,6i}});\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Argument arg_ = processElLow(files_, "$static(pkg.ExTwo).exmeth()");
        assertEq(19, getNumber(arg_));
    }
    @Test
    public void processEl259Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $normal $int exmeth(T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(T i:e){\n");
        xml_.append("    t+=$($int)i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex<java.lang.Integer>).getDeclaredMethods(\"exmeth\",$false,$true,$class(java.lang.Integer))[0i];\n");
        xml_.append("  $return $($int) m.invoke($new pkg.Ex<java.lang.Integer>(),$(java.lang.Object)$null);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Argument arg_ = processElLow(files_, "$static(pkg.ExTwo).exmeth()");
        assertEq(9, getNumber(arg_));
    }
    @Test
    public void processEl260Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $normal $int exmeth(T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(T i:e){\n");
        xml_.append("    t+=$($int)i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class($Method).getDeclaredMethods(\"invoke\",$false,$true,$class(java.lang.Object),$class(java.lang.Object))[0i];\n");
        xml_.append("  $Method mtwo = $class(pkg.Ex<java.lang.Integer>).getDeclaredMethods(\"exmeth\",$false,$true,$class(java.lang.Integer))[0i];\n");
        xml_.append("  $return $($int) m.invoke(mtwo,$new pkg.Ex<java.lang.Integer>(),$new java.lang.Object[]{$new java.lang.Integer[]{4i,6i}});\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Argument arg_ = processElLow(files_, "$static(pkg.ExTwo).exmeth()");
        assertEq(19, getNumber(arg_));
    }
    @Test
    public void processEl261Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static $int inst=6i;\n");
        xml_.append(" $public $static $void set($int i){\n");
        xml_.append("  inst+=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(T i:e){\n");
        xml_.append("    t+=$($int)i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static java.lang.Object exmeth(){\n");
        xml_.append("  $Method m = $class($Method).getDeclaredMethods(\"invoke\",$false,$true,$class(java.lang.Object),$class(java.lang.Object))[0i];\n");
        xml_.append("  $Method mtwo = $class(pkg.Ex<java.lang.Integer>).getDeclaredMethods(\"set\",$true,$false,$class($int))[0i];\n");
        xml_.append("  $return m.invoke(mtwo,$null,$new java.lang.Object[]{4i});\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        AnalyzedTestConfiguration cont_ = getConfigurationQuick(files_);
        addImportingPage(cont_);
        Argument arg_ = calcLow("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(10, ((NumberStruct) getStaticField(cont_)).intStruct());
        assertTrue(arg_.isNull());
    }

    @Test
    public void processEl267Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach($int i:e){\n");
        xml_.append("   t+=i;;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$false,$true,$class($int))[0i];\n");
        xml_.append("  $Constructor c = $class(pkg.Ex).makeGeneric().getDeclaredConstructors($false)[0i];\n");
        xml_.append("  $return $($int) m.invoke(c.newInstance(),$new java.lang.Object[]{$new $int[]{4i,6i}});\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Argument arg_ = processElLow(files_, "$static(pkg.ExTwo).exmeth()");
        assertEq(19, getNumber(arg_));
    }
    @Test
    public void processEl268Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public ($int p,$int q){\n");
        xml_.append("  inst=p+q;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach($int i:e){\n");
        xml_.append("   t+=i;;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t+inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$false,$true,$class($int))[0i];\n");
        xml_.append("  $Constructor c = $class(pkg.Ex).getDeclaredConstructors($false,$class($int),$class($int))[0i];\n");
        xml_.append("  $return $($int) m.invoke(c.newInstance(1i,2i),$new java.lang.Object[]{$new $int[]{4i,6i}});\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Argument arg_ = processElLow(files_, "$static(pkg.ExTwo).exmeth()");
        assertEq(22, getNumber(arg_));
    }
    @Test
    public void processEl269Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public (T e){\n");
        xml_.append("  inst=$($int)e;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach(T i:e){\n");
        xml_.append("   t+=$($int)i;;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t+inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex<java.lang.Integer>).getDeclaredMethods(\"exmeth\",$false,$true,$class(java.lang.Integer))[0i];\n");
        xml_.append("  $Constructor c = $class(pkg.Ex).makeGeneric($class(java.lang.Integer)).getDeclaredConstructors($false,$class(java.lang.Integer))[0i];\n");
        xml_.append("  $return $($int) m.invoke(c.newInstance(9i),$new java.lang.Object[]{$new java.lang.Integer[]{4i,6i}});\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Argument arg_ = processElLow(files_, "$static(pkg.ExTwo).exmeth()");
        assertEq(28, getNumber(arg_));
    }
    @Test
    public void processEl270Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public (T... e){\n");
        xml_.append("  $foreach(T i:e){\n");
        xml_.append("   inst+=$($int)i;;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach(T i:e){\n");
        xml_.append("   t+=$($int)i;;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t+inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex<java.lang.Integer>).getDeclaredMethods(\"exmeth\",$false,$true,$class(java.lang.Integer))[0i];\n");
        xml_.append("  $Constructor c = $class(pkg.Ex).makeGeneric($class(java.lang.Integer)).getDeclaredConstructors($true,$class(java.lang.Integer))[0i];\n");
        xml_.append("  $return $($int) m.invoke(c.newInstance($new java.lang.Object[]{$new java.lang.Integer[]{9i,4i}}),$new java.lang.Object[]{$new java.lang.Integer[]{4i,6i}});\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Argument arg_ = processElLow(files_, "$static(pkg.ExTwo).exmeth()");
        assertEq(32, getNumber(arg_));
    }
    @Test
    public void processEl271Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public (T... e){\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(T i:e){\n");
        xml_.append("    inst+=$($int)i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach(T i:e){\n");
        xml_.append("   t+=$($int)i;;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t+inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex<java.lang.Integer>).getDeclaredMethods(\"exmeth\",$false,$true,$class(java.lang.Integer))[0i];\n");
        xml_.append("  $Constructor c = $class(pkg.Ex).makeGeneric($class(java.lang.Integer)).getDeclaredConstructors($true,$class(java.lang.Integer))[0i];\n");
        xml_.append("  $return $($int) m.invoke(c.newInstance($(java.lang.Object)$null),$new java.lang.Object[]{$new java.lang.Integer[]{4i,6i}});\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Argument arg_ = processElLow(files_, "$static(pkg.ExTwo).exmeth()");
        assertEq(19, getNumber(arg_));
    }

    @Test
    public void processEl275Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $normal $int exmeth(T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(T i:e){\n");
        xml_.append("    t+=$($int)i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method mthree = $class($Constructor).getDeclaredMethods(\"newInstance\",$false,$true,$class(java.lang.Object))[0i];\n");
        xml_.append("  $Constructor c = $class(pkg.Ex<java.lang.Integer>).getDeclaredConstructors($false)[0i];\n");
        xml_.append("  pkg.Ex<java.lang.Integer> res = $(pkg.Ex<java.lang.Integer>) mthree.invoke(c,$(java.lang.Object)$new java.lang.Object[]{});\n");
        xml_.append("  $Method m = $class($Method).getDeclaredMethods(\"invoke\",$false,$true,$class(java.lang.Object),$class(java.lang.Object))[0i];\n");
        xml_.append("  $Method mtwo = $class(pkg.Ex<java.lang.Integer>).getDeclaredMethods(\"exmeth\",$false,$true,$class(java.lang.Integer))[0i];\n");
        xml_.append("  $return $($int) m.invoke(mtwo,res,$new java.lang.Object[]{$new java.lang.Integer[]{4i,6i}});\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Argument arg_ = processElLow(files_, "$static(pkg.ExTwo).exmeth()");
        assertEq(19, getNumber(arg_));
    }
    @Test
    public void processEl276Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $normal $int exmeth(T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(T i:e){\n");
        xml_.append("    t+=$($int)i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $static(pkg.Ex).inst=19i;\n");
        xml_.append("  $return $static(pkg.Ex).inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Argument arg_ = processEl(files_, "$static(pkg.ExTwo).exmeth()");
        assertEq(19, getNumber(arg_));
    }
    @Test
    public void processEl277Test() {
        Argument arg_ = processEl(new StringMap<String>(), "$static($Class).getAllClasses().length > 10");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl278Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static $final $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst=15i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(T i:e){\n");
        xml_.append("    t+=$($int)i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.Ex).inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Argument arg_ = processEl(files_, "$static(pkg.ExTwo).exmeth()");
        assertEq(15, getNumber(arg_));
    }
    @Test
    public void processEl282Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=15i;\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach($int i:e){\n");
        xml_.append("    t+=i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $($int)$class(pkg.Ex).getDeclaredFields(\"inst\")[0i].get($new pkg.Ex());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Argument arg_ = processElLow(files_, "$static(pkg.ExTwo).exmeth()");
        assertEq(15, getNumber(arg_));
    }
    @Test
    public void processEl283Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=15i;\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach($int i:e){\n");
        xml_.append("    t+=i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static java.lang.StringBuilder exmeth(){\n");
        xml_.append("  $return $(java.lang.StringBuilder)$class(java.lang.StringBuilder).getDeclaredConstructors($false,$class(java.lang.String))[0i].newInstance(\"hello\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Argument arg_ = processElLow(files_, "$static(pkg.ExTwo).exmeth()");
        assertEq("hello", getString(arg_));
    }
    @Test
    public void processEl286Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static java.lang.Integer inst=15i;\n");
        xml_.append(" $public $normal $int exmeth(T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(T i:e){\n");
        xml_.append("    t+=$($int)i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static java.lang.Integer exmeth(){\n");
        xml_.append("  $class(pkg.Ex).getDeclaredFields(\"inst\")[0i].set($null,$null);\n");
        xml_.append("  $return $static(pkg.Ex).inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Argument arg_ = processElNormal3Low("$static(pkg.ExTwo).exmeth()", files_);
        assertTrue(arg_.isNull());
    }
    @Test
    public void processEl291Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=15i;\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach($int i:e){\n");
        xml_.append("    t+=$($int)i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static java.lang.Integer exmeth(){\n");
        xml_.append("  pkg.Ex out = $new pkg.Ex();\n");
        xml_.append("  $class(pkg.Ex).getDeclaredFields(\"inst\")[0i].set(out,16i);\n");
        xml_.append("  $return out.inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Argument arg_ = processElLow(files_, "$static(pkg.ExTwo).exmeth()");
        assertEq(16, getNumber(arg_));
    }
    @Test
    public void processEl292Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public java.lang.Integer inst=15i;\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach($int i:e){\n");
        xml_.append("    t+=$($int)i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static java.lang.Integer exmeth(){\n");
        xml_.append("  pkg.Ex out = $new pkg.Ex();\n");
        xml_.append("  $class(pkg.Ex).getDeclaredFields(\"inst\")[0i].set(out,$null);\n");
        xml_.append("  $return out.inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Argument arg_ = processElNormal3Low("$static(pkg.ExTwo).exmeth()", files_);
        assertTrue(arg_.isNull());
    }
    @Test
    public void processEl293Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T inst;\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach($int i:e){\n");
        xml_.append("    t+=$($int)i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static java.lang.Integer exmeth(){\n");
        xml_.append("  pkg.Ex<java.lang.Integer> out = $new pkg.Ex<java.lang.Integer>();\n");
        xml_.append("  $class(pkg.Ex).getDeclaredFields(\"inst\")[0i].set(out,16i);\n");
        xml_.append("  $return $(java.lang.Integer) out.inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Argument arg_ = processElLow(files_, "$static(pkg.ExTwo).exmeth()");
        assertEq(16, getNumber(arg_));
    }
    @Test
    public void processEl299Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> : pkg.ExAbs {\n");
        xml_.append(" $public $static $final $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst=$static(pkg.ExAbs).sup;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(T i:e){\n");
        xml_.append("    t+=$($int)i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.Ex).inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $protected $static $final $int sup=15i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        Argument arg_ = processEl(files_, "$static(pkg.ExTwo).exmeth()");
        assertEq(15, getNumber(arg_));
    }
    @Test
    public void processEl300Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> : pkg.ExAbs {\n");
        xml_.append(" $public $static $final $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst=$static(pkg.ExAbs).sup;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(T i:e){\n");
        xml_.append("    t+=$($int)i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.Ex).inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $protected $static $final $int sup;\n");
        xml_.append(" $static{\n");
        xml_.append("  sup=15i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        Argument arg_ = processEl(files_, "$static(pkg.ExTwo).exmeth()");
        assertEq(15, getNumber(arg_));
    }
    @Test
    public void processEl301Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> : pkg.ExAbs {\n");
        xml_.append(" $public $static $final $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst=sup;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(T i:e){\n");
        xml_.append("    t+=$($int)i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.Ex).inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $protected $static $final $int sup;\n");
        xml_.append(" $static{\n");
        xml_.append("  sup=15i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        Argument arg_ = processEl(files_, "$static(pkg.ExTwo).exmeth()");
        assertEq(15, getNumber(arg_));
    }
    @Test
    public void processEl302Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> : pkg.ExAbs {\n");
        xml_.append(" $public $static $final $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst=$static(pkg.ExAbs).sup;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(T i:e){\n");
        xml_.append("    t+=$($int)i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.Ex).inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $protected $static $final $int sup;\n");
        xml_.append(" $static{\n");
        xml_.append("  sup=15i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        Argument arg_ = processEl(files_, "$static(pkg.ExTwo).exmeth()");
        assertEq(15, getNumber(arg_));
    }
    @Test
    public void processEl307Test() {
        Argument arg_ = processElNormal3Low("$class(java.lang.String[]).getDeclaredMethods(\"clone\",$false,$false)[0i].invoke($new java.lang.Number[]{})", new StringMap<String>());
        assertTrue(arg_.getStruct() instanceof ArrayStruct);
        assertEq("[java.lang.Number",((ArrayStruct)arg_.getStruct()).getClassName());
        assertEq(0,(((ArrayStruct)arg_.getStruct()).getInstance()).length);
    }

    @Test
    public void processEl308Test() {
        Argument arg_ = processElNormal3Low("$class(java.lang.String[]).getDeclaredMethods(\"clone\",$false,$false)[0i].invoke($new java.lang.String[]{})", new StringMap<String>());
        assertTrue(arg_.getStruct() instanceof ArrayStruct);
        assertEq("[java.lang.String",((ArrayStruct)arg_.getStruct()).getClassName());
        assertEq(0,(((ArrayStruct)arg_.getStruct()).getInstance()).length);
    }
    @Test
    public void processEl309Test() {
        Argument arg_ = processElNormal3Low("$class(java.lang.String[]).getDeclaredMethods(\"clone\",$false,$false)[0i].invoke($new java.lang.String[]{\"sample\"})", new StringMap<String>());
        assertTrue(arg_.getStruct() instanceof ArrayStruct);
        assertEq("[java.lang.String",((ArrayStruct)arg_.getStruct()).getClassName());
        assertEq(1,(((ArrayStruct)arg_.getStruct()).getInstance()).length);
        assertEq("sample",((StringStruct)(((ArrayStruct)arg_.getStruct()).getInstance())[0]).getInstance());
    }
    @Test
    public void processEl310Test() {
        Argument arg_ = processElNormal3Low("$class(java.lang.Object[]).getDeclaredMethods(\"clone\",$false,$false)[0i].invoke($new java.lang.String[]{\"sample\"})", new StringMap<String>());
        assertTrue(arg_.getStruct() instanceof ArrayStruct);
        assertEq("[java.lang.String",((ArrayStruct)arg_.getStruct()).getClassName());
        assertEq(1,(((ArrayStruct)arg_.getStruct()).getInstance()).length);
        assertEq("sample",((StringStruct)(((ArrayStruct)arg_.getStruct()).getInstance())[0]).getInstance());
    }
    @Test
    public void processEl311Test() {
        Argument arg_ = processEl(new StringMap<String>(), "$math.abs(-8l)");
        assertEq(8L, getNumber(arg_));
    }
    @Test
    public void processEl312Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument arg_ = processEl(files_, "pkg.Ex.exmeth()");
        assertEq(9, getNumber(arg_));
    }
    @Test
    public void processEl317Test() {
        Argument arg_ = processDelimiters(" {(1+2)*3+\" hello\"+\" world {every body ;)\\\\\\\"\"+$new $int[]{0i,1i}.length} ", 73);
        assertEq("9 hello world {every body ;)\\\"2",getString(arg_));
    }

    @Test
    public void processEl318Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(0);\n");
        xml_.append(" $public $static $int exmeth(java.lang.Integer e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $static($Class).forName(\"[pkg.Ex\",$true).getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Argument arg_ = processElNormalNotInit(files_, "$static(pkg.ExTwo).exmeth()");
        assertEq("[pkg.Ex",getString(arg_));
    }
    @Test
    public void processEl319Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(0);\n");
        xml_.append(" $public $static $int exmeth(java.lang.Integer e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $static($Class).forName(\"pkg.Ex[]\",$true).getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Argument arg_ = processElNormalNotInit(files_, "$static(pkg.ExTwo).exmeth()");
        assertEq("[pkg.Ex",getString(arg_));
    }

    @Test
    public void processEl320Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++;\n");
        xml_.append("  inst++;\n");
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
        Argument arg_ = processElNormalInit(files_, "$static(pkg.ExTwo).exmeth()+pkg.Ex.inst","pkg.Ex");
        assertEq(6,getNumber(arg_));
    }
    @Test
    public void processEl321Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++;\n");
        xml_.append("  inst++;\n");
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
        Argument arg_ = processElNormalInit(files_, "$new{} pkg.Ex().inst","pkg.Ex");
        assertEq(1,getNumber(arg_));
    }
    @Test
    public void processEl322Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append("  $public $int inst=5;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++;\n");
        xml_.append("  inst++;\n");
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
        Argument arg_ = processElNormalInit(files_, "$new pkg.Ex().$new Inner().inst","pkg.Ex");
        assertEq(5,getNumber(arg_));
    }
    @Test
    public void processEl359Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++;\n");
        xml_.append("  inst++;\n");
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
        Argument arg_ = processElNormalInit(files_, "$new{} pkg.Ex().inst","pkg.Ex");
        assertEq(1,getNumber(arg_));
    }

    @Test
    public void processEl360Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++;\n");
        xml_.append("  inst++;\n");
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
        Argument arg_ = processElNormalInit(files_, "$new{} pkg.Ex(5).inst","pkg.Ex");
        assertEq(5,getNumber(arg_));
    }
    @Test
    public void processEl361Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        StringMap<LoopVariable> localVars_ = new StringMap<LoopVariable>();
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndexClassName(context_.getAliasPrimLong());
        lv_.setIndex(5);
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().getVars().addAllEntries(localVars_);
        Argument argument_ = calcLow("([arg])", context_);
        assertEq(5,getNumber(argument_));
    }

    @Test
    public void processEl364Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth($int q,$int...p){\n");
        xml_.append("  $var sum = q;\n");
        xml_.append("  $foreach($var i:p){\n");
        xml_.append("   sum+=i;;\n");
        xml_.append("  }\n");
        xml_.append("  $return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Argument arg_ = processEl(files_, "pkg.ExTwo.exmeth($vararg($int),4,$firstopt(8))");
        assertEq(12,getNumber(arg_));
    }
    @Test
    public void processEl365Test() {
        Argument argument_ = processElNormalBoolInt(true, "($boolean)arg", new IntStruct(0));
        assertTrue(argument_.isTrue());
    }
    @Test
    public void processEl366Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getAliasString());
        lv_.setStruct(new StringStruct("str"));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        CommonRender.setLocalVars(context_, localVars_);
        Argument argument_ = calcLow("(String)arg", context_);
        assertEq("str",getString(argument_));
    }
    @Test
    public void processEl369Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Argument arg_ = processEl(files_, "1 $instanceof pkg.ExTwo");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl370Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Argument arg_ = processEl(files_, "$new pkg.ExTwo<java.lang.Object>() $instanceof pkg.ExTwo");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl371Test() {
        Argument argument_ = processElNormal1String("arg!=\"2\"");
        assertTrue(argument_.isTrue());
    }

    @Test
    public void processEl372Test() {
        Argument argument_ = processElNormal1String("arg==\"2\"");
        assertTrue(argument_.isFalse());
    }

    @Test
    public void processEl373Test() {
        Argument argument_ = processElNormal1String("arg!=\"8\"");
        assertTrue(argument_.isFalse());
    }

    @Test
    public void processEl374Test() {
        Argument argument_ = processElNormal1String("arg==\"8\"");
        assertTrue(argument_.isTrue());
    }

    @Test
    public void processEl376Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        setVal(lv_, 8, ARR_INT);
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        Argument arg_ = calcLow("v.clone()", context_);
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq(1, arr_.getInstance().length);
        assertEq(8, ((NumberStruct)arr_.getInstance()[0]).intStruct());
    }

    @Test
    public void processEl377Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth($int q,$int...p){\n");
        xml_.append("  $var sum = q;\n");
        xml_.append("  $foreach($var i:p){\n");
        xml_.append("   sum+=i;;\n");
        xml_.append("  }\n");
        xml_.append("  $return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Argument arg_ = processEl(files_, "pkg.ExTwo.exmeth($id(pkg.ExTwo,$static,$int,$int...),4,8)");
        assertEq(12,getNumber(arg_));
    }
    @Test
    public void processEl378Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth($int q,$int p){\n");
        xml_.append("  $return q+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Argument arg_ = processEl(files_, "pkg.ExTwo.exmeth($id(pkg.ExTwo,$static,$int,$int),4,8)");
        assertEq(12,getNumber(arg_));
    }
    @Test
    public void procesAffect0Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestConfiguration context_ = getConfigurationQuick(files_);
        setGlobalType(context_, "pkg.Ex");
        CustList<OperationNode> all_ = getQuickAnalyzedFwd("(v)=$this.inst", 0, context_, context_.getAnalyzingDoc(),context_.getAliasPrimInteger(),"v", false);
        addImportingPage(context_);
        Struct str_ = initAndSet(context_, new ClassField("pkg.Ex", "inst"), new IntStruct(2), "pkg.Ex");
        setGlobalArgumentStruct(context_, str_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(0));
        lv_.setClassName(context_.getAliasPrimInteger());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        buildAndCalculate(context_, all_);
        assertEq(2, ((NumberStruct)lv_.getStruct()).intStruct());
    }
    @Test
    public void procesAffect000Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestConfiguration context_ = getConfigurationQuick(files_);
        setGlobalType(context_, "pkg.Ex");
        CustList<OperationNode> all_ = getQuickAnalyzedFwd("$this.inst=(v)", 0, context_, context_.getAnalyzingDoc(),context_.getAliasPrimInteger(),"v",false);
        addImportingPage(context_);
        Struct str_ = init(context_, "pkg.Ex");
        setGlobalArgumentStruct(context_, str_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(2));
        lv_.setClassName(context_.getAliasPrimInteger());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        buildAndCalculate(context_, all_);
        assertEq(2, ((NumberStruct)lv_.getStruct()).intStruct());
    }

    @Test
    public void processEl379Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++;\n");
        xml_.append("  inst++;\n");
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
        Argument arg_ = processElNormalInit(files_, "++pkg.Ex.inst","pkg.Ex");
        assertEq(2,getNumber(arg_));
    }
    @Test
    public void processEl380Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++;\n");
        xml_.append("  inst++;\n");
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
        Argument arg_ = processElNormalInit(files_, "pkg.Ex.inst++","pkg.Ex");
        assertEq(1,getNumber(arg_));
    }
    @Test
    public void processEl381Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++;\n");
        xml_.append("  inst++;\n");
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
        Argument arg_ = processElNormalInit(files_, "pkg.Ex.inst+=5","pkg.Ex");
        assertEq(6,getNumber(arg_));
    }

    @Test
    public void processEl384Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst;\n");
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
        Argument argument_ = processEl(files_, "$new{} pkg.Ex(5).inst=10","pkg.Ex");
        assertEq(10,getNumber(argument_));
    }
    @Test
    public void processEl387Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
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
        Argument argument_ = processEl(files_, "pkg.Ex.inst=10","pkg.Ex");
        assertEq(10,getNumber(argument_));
    }
    @Test
    public void processEl389Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res($int v){\n");
        xml_.append("  $return inst+v;\n");
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
        Argument argument_ = processEl(files_, "$new{} pkg.Ex(52).$that.res(8)","pkg.Ex");
        assertEq(60,getNumber(argument_));
    }
    @Test
    public void processEl390Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++;\n");
        xml_.append("  inst++;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;\n");
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
        Argument argument_ = processEl(files_, "pkg.Ex.res(8)","pkg.Ex");
        assertEq(9,getNumber(argument_));
    }
    @Test
    public void processEl393Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res($int v){\n");
        xml_.append("  $return inst+v;\n");
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
        Argument argument_ = processEl(files_, "$new pkg.Ex(52).$classchoice(pkg.Ex)res(8)","pkg.Ex");
        assertEq(60,getNumber(argument_));
    }

    @Test
    public void processEl394Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++;\n");
        xml_.append("  inst++;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;\n");
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
        Argument argument_ = processEl(files_, "pkg.Ex.$classchoice(pkg.Ex)res(8)","pkg.Ex");
        assertEq(9,getNumber(argument_));
    }
    @Test
    public void processEl398Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res($int v){\n");
        xml_.append("  $return inst+v;\n");
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
        Argument argument_ = processEl(files_, "$new pkg.Ex(52).$superaccess(pkg.Ex)res(8)","pkg.Ex");
        assertEq(60,getNumber(argument_));
    }
    @Test
    public void processEl399Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++;\n");
        xml_.append("  inst++;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;\n");
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
        Argument argument_ = processEl(files_, "pkg.Ex.$superaccess(pkg.Ex)res(8)","pkg.Ex");
        assertEq(9,getNumber(argument_));
    }
    @Test
    public void processEl402Test() {
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
        Argument argument_ = processEl(files_, "$values(pkg.ExTwo).length","pkg.ExTwo");
        assertEq(2,getNumber(argument_));
    }
    @Test
    public void processEl403Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo {\n");
        xml_.append(" ONE{(){myval=5;}},TWO{(){myval=7;}};\n");
        xml_.append(" $static{\n");
        xml_.append("  Other.v++;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int myval;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Other{\n");
        xml_.append(" $public $static $int v;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Argument argument_ = processEl(files_, "$valueOf(pkg.ExTwo,\"ONE\").myval","pkg.ExTwo-ONE","pkg.ExTwo-TWO","pkg.ExTwo");
        assertEq(5,getNumber(argument_));
    }
    @Test
    public void processEl404Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo {\n");
        xml_.append(" ONE{(){myval=5;}},TWO{(){myval=7;}};\n");
        xml_.append(" $static{\n");
        xml_.append("  Other.v++;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int myval;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Other{\n");
        xml_.append(" $public $static $int v;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Argument argument_ = processEl(files_, "$valueOf(pkg.ExTwo,\"TWO\").myval","pkg.ExTwo-ONE","pkg.ExTwo-TWO","pkg.ExTwo");
        assertEq(7,getNumber(argument_));
    }
    @Test
    public void processEl408Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo {\n");
        xml_.append(" ONE,TWO\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Argument argument_ = processEl(files_, "$values(pkg.ExTwo).length");
        assertEq(2,getNumber(argument_));
    }
    @Test
    public void processEl409Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo {\n");
        xml_.append(" ONE{(){myval=5;}},TWO{(){myval=7;}};\n");
        xml_.append(" $public $int myval;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Argument argument_ = processEl(files_, "$valueOf(pkg.ExTwo,\"ONE\").myval");
        assertEq(5,getNumber(argument_));
    }
    @Test
    public void processEl410Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo {\n");
        xml_.append(" ONE{(){myval=5;}},TWO{(){myval=7;}};\n");
        xml_.append(" $public $int myval;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Argument argument_ = processEl(files_, "$valueOf(pkg.ExTwo,\"TWO\").myval");
        assertEq(7,getNumber(argument_));
    }
    @Test
    public void processEl411Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "$lambda(pkg.Ex,,inst,$int).call(4)");
        assertEq(4,getNumber(argument_));
    }
    @Test
    public void processEl412Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "$lambda(java.lang.String,length).call(\"mystr\")");
        assertEq(5,getNumber(argument_));
    }
    @Test
    public void processEl413Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "((java.lang.String)$lambda(java.lang.String,$new,$char...).call($new $char[]{'m','y','s','t','r'})).length()");
        assertEq(5,getNumber(argument_));
    }
    @Test
    public void processEl414Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "\"mystr\".$lambda(java.lang.String,length).call()");
        assertEq(5,getNumber(argument_));
    }
    @Test
    public void processEl415Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst++;\n");
        xml_.append("  inst=\"mystr\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "pkg.Ex.inst.$lambda(java.lang.String,length).call()","pkg.Ex");
        assertEq(5,getNumber(argument_));
    }
    @Test
    public void processEl416Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst++;\n");
        xml_.append("  inst=\"\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "$lambda(pkg.Ex,,inst,java.lang.String).call(\"mystr\")","pkg.Ex");
        assertEq("mystr",getString(argument_));
    }
    @Test
    public void processEl417Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex a,pkg.Ex b){\n");
        xml_.append(" $var o = $new pkg.Ex();\n");
        xml_.append(" o.inst = a.inst+b.inst;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "($new pkg.Ex(6)+$new pkg.Ex(8)).inst");
        assertEq(14,getNumber(argument_));
    }
    @Test
    public void processEl417_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex a,pkg.Ex b){\n");
        xml_.append(" $var o = $new pkg.Ex();\n");
        xml_.append(" o.inst = a.inst+b.inst;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "$operator(+)($new pkg.Ex(6),$new pkg.Ex(8)).inst");
        assertEq(14,getNumber(argument_));
    }
    @Test
    public void processEl418_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $operator+ Ex(Ex a,Ex b){\n");
        xml_.append("  $var o = $new Ex();\n");
        xml_.append("  o.inst = a.inst+b.inst;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "$operator(+,pkg.Ex)($new pkg.Ex(6),$new pkg.Ex(8)).inst");
        assertEq(14,getNumber(argument_));
    }
    @Test
    public void processEl418___Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int {\n");
        xml_.append(" $int pl($int a, $int b);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "($(pkg.Int)($static().$lambda($math,plus,$int,$int),$interfaces(pkg.Int)())).pl(6,8)");
        assertEq(14,getNumber(argument_));
    }
    @Test
    public void processEl419___Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int:Int2 {\n");
        xml_.append(" $int pl($int a, $int b);\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Int2 {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "($(pkg.Int)($static().$lambda($math,plus,$int,$int),$interfaces(pkg.Int2)(),$interfaces(pkg.Int)())).pl(6,8)");
        assertEq(14,getNumber(argument_));
    }
    @Test
    public void processEl422___Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int:Int2 {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Int2 {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "$($int)(1??2)");
        assertEq(1,getNumber(argument_));
    }
    @Test
    public void processEl423___Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int:Int2 {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Int2 {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "$($int)($null??2)");
        assertEq(2,getNumber(argument_));
    }
    @Test
    public void processEl424___Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int:Int2 {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Int2 {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "\"\"?.length()");
        assertEq(0,getNumber(argument_));
    }
    @Test
    public void processEl_424___Test() {
        StringMap<String> files_ = new StringMap<String>();
        Argument argument_ = processEl(files_, "($(java.lang.String)$null)?.$lambda(java.lang.String,length).call()");
        assertEq(0,getNumber(argument_));
    }
    @Test
    public void processEl_425___Test() {
        StringMap<String> files_ = new StringMap<String>();
        Argument argument_ = processEl(files_, "($(java.lang.String)(($(java.lang.String)$null)?.$lambda(java.lang.String,length).call()==0?$null:$null))?.$lambda(java.lang.String,length).call()");
        assertEq(0,getNumber(argument_));
    }
    @Test
    public void processEl425___Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Cl {\n");
        xml_.append(" $public String str;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "($new pkg.Cl().str)?.length()");
        assertEq(0,getNumber(argument_));
    }
    @Test
    public void processEl426___Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Cl {\n");
        xml_.append(" $public String str;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "$new pkg.Cl()?.str=\"hello\"");
        assertEq("hello",getString(argument_));
    }
    @Test
    public void processEl427___Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ClOwner {\n");
        xml_.append(" $public Cl owner;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Cl {\n");
        xml_.append(" $public String str;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processElNormal3("$new pkg.ClOwner()?.owner?.str=\"hello\"", files_);
        
        assertSame(NullStruct.NULL_VALUE,argument_.getStruct());
    }
    @Test
    public void processEl428___Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Cl {\n");
        xml_.append(" $public String str=\"\";\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "$new pkg.Cl()?.str+=\"hello\"");
        assertEq("hello",getString(argument_));
    }
    @Test
    public void processEl429___Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ClOwner {\n");
        xml_.append(" $public Cl owner;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Cl {\n");
        xml_.append(" $public String str;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processElNormal3("$new pkg.ClOwner()?.owner?.str+=\"hello\"", files_);
        
        assertSame(NullStruct.NULL_VALUE,argument_.getStruct());
    }
    @Test
    public void processEl430___Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Cl {\n");
        xml_.append(" $public $int str=5;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "$new pkg.Cl()?.str++");
        assertEq(5,getNumber(argument_));
    }
    @Test
    public void processEl431___Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Cl {\n");
        xml_.append(" $public $int str=5;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "++$new pkg.Cl()?.str");
        assertEq(6,getNumber(argument_));
    }
    @Test
    public void processEl432___Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ClOwner {\n");
        xml_.append(" $public Cl owner;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Cl {\n");
        xml_.append(" $public $int str=5;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processElNormal3("$new pkg.ClOwner()?.owner?.str++", files_);
        
        assertEq(0,getNumber(argument_));
    }

    @Test
    public void processEl433___Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ClOwner {\n");
        xml_.append(" $public Cl owner;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Cl {\n");
        xml_.append(" $public $int str=5;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processElNormal3("++$new pkg.ClOwner()?.owner?.str", files_);
        
        assertEq(0,getNumber(argument_));
    }
    @Test
    public void processEl418Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex a,pkg.Ex b){\n");
        xml_.append(" $var o = $new pkg.Ex();\n");
        xml_.append(" o.inst = a.inst+b.inst;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex res = $new Ex(6);\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "(pkg.Ex.res+=$new pkg.Ex(8)).inst");
        assertEq(14,getNumber(argument_));
    }
    @Test
    public void processEl419Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex a){\n");
        xml_.append(" $var o = $new pkg.Ex();\n");
        xml_.append(" o.inst = a.inst+1;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex res = $new Ex(6);\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "(pkg.Ex.res++).inst");
        assertEq(6,getNumber(argument_));
    }
    @Test
    public void processEl420Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex a){\n");
        xml_.append(" $var o = $new pkg.Ex();\n");
        xml_.append(" o.inst = a.inst+1;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex res = $new Ex(6);\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "(++pkg.Ex.res).inst");
        assertEq(7,getNumber(argument_));
    }
    @Test
    public void processEl421Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex a,pkg.Ex b){\n");
        xml_.append(" $var o = $new pkg.Ex();\n");
        xml_.append(" o.inst = a.inst+b.inst;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex[] res = {$new Ex(6)};\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "(pkg.Ex.res[0]+=$new pkg.Ex(8)).inst");
        assertEq(14,getNumber(argument_));
    }
    @Test
    public void processEl422Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex a){\n");
        xml_.append(" $var o = $new pkg.Ex();\n");
        xml_.append(" o.inst = a.inst+1;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex[] res = {$new Ex(6)};\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "(pkg.Ex.res[0]++).inst");
        assertEq(6,getNumber(argument_));
    }
    @Test
    public void processEl423Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex a){\n");
        xml_.append(" $var o = $new pkg.Ex();\n");
        xml_.append(" o.inst = a.inst+1;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex[] res = {$new Ex(6)};\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "(++pkg.Ex.res[0]).inst");
        assertEq(7,getNumber(argument_));
    }
    @Test
    public void processEl424Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex a,pkg.Ex b){\n");
        xml_.append(" $var o = $new pkg.Ex();\n");
        xml_.append(" o.inst = a.inst+b.inst;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public Ex[] res = {$new Ex(6)};\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static ExTwo res = $new ExTwo();\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "(pkg.Ex.res.res[0]+=$new pkg.Ex(8)).inst","pkg.Ex");
        assertEq(14,getNumber(argument_));
    }
    @Test
    public void processEl425Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex a){\n");
        xml_.append(" $var o = $new pkg.Ex();\n");
        xml_.append(" o.inst = a.inst+1;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public Ex[] res = {$new Ex(6)};\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static ExTwo res = $new ExTwo();\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "(pkg.Ex.res.res[0]++).inst","pkg.Ex");
        assertEq(6,getNumber(argument_));
    }
    @Test
    public void processEl426Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex a){\n");
        xml_.append(" $var o = $new pkg.Ex();\n");
        xml_.append(" o.inst = a.inst+1;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public Ex[] res = {$new Ex(6)};\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static ExTwo res = $new ExTwo();\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "(++pkg.Ex.res.res[0]).inst","pkg.Ex");
        assertEq(7,getNumber(argument_));
    }
    @Test
    public void processEl427Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex a,pkg.Ex b){\n");
        xml_.append(" $var o = $new pkg.Ex();\n");
        xml_.append(" o.inst = a.inst+b.inst;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public Ex res = $new Ex(6);\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static ExTwo res = $new ExTwo();\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "(pkg.Ex.res.res+=$new pkg.Ex(8)).inst","pkg.Ex");
        assertEq(14,getNumber(argument_));
    }
    @Test
    public void processEl428Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex a){\n");
        xml_.append(" $var o = $new pkg.Ex();\n");
        xml_.append(" o.inst = a.inst+1;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public Ex res = $new Ex(6);\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static ExTwo res = $new ExTwo();\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "(pkg.Ex.res.res++).inst","pkg.Ex");
        assertEq(6,getNumber(argument_));
    }
    @Test
    public void processEl429Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex a){\n");
        xml_.append(" $var o = $new pkg.Ex();\n");
        xml_.append(" o.inst = a.inst+1;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public Ex res = $new Ex(6);\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static ExTwo res = $new ExTwo();\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "(++pkg.Ex.res.res).inst","pkg.Ex");
        assertEq(7,getNumber(argument_));
    }
    @Test
    public void processEl430Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex a,pkg.Ex b){\n");
        xml_.append(" $var o = $new pkg.Ex();\n");
        xml_.append(" o.inst = a.inst+b.inst;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public Ex res = $new Ex(6);\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static ExTwo res = $new ExTwo();\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestConfiguration conf_ = getConfigurationQuick(files_,"pkg.Ex");
        setGlobalType(conf_, "pkg.Ex");
        CustList<OperationNode> all_ = getQuickAnalyzedFwd("(v+=$new pkg.Ex(8)).inst", 0, conf_, conf_.getAnalyzingDoc(),"pkg.Ex","v",false);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = init(conf_,"pkg.Ex");
        setStruct(value_,new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        CommonRender.setLocalVars(conf_, localVars_);
        Argument argument_ = buildAndCalculate(conf_, all_);
        assertEq(14,getNumber(argument_));
        assertEq(14,((NumberStruct)getStruct(lv_.getStruct(),new ClassField("pkg.Ex","inst"))).intStruct());
    }

    @Test
    public void processEl431Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex a){\n");
        xml_.append(" $var o = $new pkg.Ex();\n");
        xml_.append(" o.inst = a.inst+1;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public Ex res = $new Ex(6);\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static ExTwo res = $new ExTwo();\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestConfiguration conf_ = getConfigurationQuick(files_,"pkg.Ex");
        setGlobalType(conf_, "pkg.Ex");
        CustList<OperationNode> all_ = getQuickAnalyzedFwd("(v++).inst", 0, conf_, conf_.getAnalyzingDoc(),"pkg.Ex","v",false);
        LocalVariable lv_ = new LocalVariable();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        Struct value_ = init(conf_,"pkg.Ex");
        setStruct(value_,new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        CommonRender.setLocalVars(conf_, localVars_);
        Argument argument_ = buildAndCalculate(conf_, all_);
        assertEq(6,getNumber(argument_));
        assertEq(7,((NumberStruct)getStruct(lv_.getStruct(),new ClassField("pkg.Ex","inst"))).intStruct());
    }
    @Test
    public void processEl432Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex a){\n");
        xml_.append(" $var o = $new pkg.Ex();\n");
        xml_.append(" o.inst = a.inst+1;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public Ex res = $new Ex(6);\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static ExTwo res = $new ExTwo();\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestConfiguration conf_ = getConfigurationQuick(files_,"pkg.Ex");
        setGlobalType(conf_, "pkg.Ex");
        CustList<OperationNode> all_ = getQuickAnalyzedFwd("(++v).inst", 0, conf_, conf_.getAnalyzingDoc(),"pkg.Ex","v",false);
        LocalVariable lv_ = new LocalVariable();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        Struct value_ = init(conf_,"pkg.Ex");
        setStruct(value_,new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        CommonRender.setLocalVars(conf_, localVars_);
        Argument argument_ = buildAndCalculate(conf_, all_);
        assertEq(7,getNumber(argument_));
        assertEq(7,((NumberStruct)getStruct(lv_.getStruct(),new ClassField("pkg.Ex","inst"))).intStruct());
    }
    @Test
    public void processEl434Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[1];\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int $this($int p){\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  inst[p]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processElNormalVar2(files_, "$new pkg.Ex(5)[0]", new ClassField("pkg.Ex", "inst"), "pkg.Ex", new IntStruct(6), "v", "pkg.Ex");
        assertEq(5,getNumber(argument_));
    }

    @Test
    public void processEl439Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int myval;\n");
        xml_.append(" $public ExTwo(){}\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  myval=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public ExTwo[] inst={$new ExTwo()};\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=$new ExTwo(p);\n");
        xml_.append(" }\n");
        xml_.append(" $public ExTwo $this($int p){\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  inst[p]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processElNormalVar2(files_, "$new pkg.Ex(5)[0].myval", new ClassField("pkg.Ex", "inst"), "pkg.Ex", new IntStruct(6), "v", "pkg.Ex");
        assertEq(5,getNumber(argument_));
    }
    @Test
    public void processEl435Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[1];\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int $this($int p){\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  inst[p]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processElNormalVar2Low(files_, "$new pkg.Ex(5)[0]=15", new ClassField("pkg.Ex", "inst"), "pkg.Ex", new IntStruct(6), "v", "pkg.Ex");
        assertEq(15,getNumber(argument_));
    }
    @Test
    public void processEl436Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[1];\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int $this($int p){\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  inst[p]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processElNormalVar2Low(files_, "$new pkg.Ex(5)[0]+=15", new ClassField("pkg.Ex", "inst"), "pkg.Ex", new IntStruct(6), "v", "pkg.Ex");
        assertEq(20,getNumber(argument_));
    }
    @Test
    public void processEl437Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[1];\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int $this($int p){\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  inst[p]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processElNormalVar2Low(files_, "++$new pkg.Ex(5)[0]", new ClassField("pkg.Ex", "inst"), "pkg.Ex", new IntStruct(6), "v", "pkg.Ex");
        assertEq(6,getNumber(argument_));
    }
    @Test
    public void processEl438Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[1];\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int $this($int p){\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  inst[p]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processElNormalVar2Low(files_, "$new pkg.Ex(5)[0]++", new ClassField("pkg.Ex", "inst"), "pkg.Ex", new IntStruct(6), "v", "pkg.Ex");
        assertEq(5,getNumber(argument_));
    }
    @Test
    public void processEl440Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int myval;\n");
        xml_.append(" $public ExTwo(){}\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  myval=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public ExTwo[] inst={$new ExTwo()};\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=$new ExTwo(p);\n");
        xml_.append(" }\n");
        xml_.append(" $public ExTwo $this($int p){\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  inst[p]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processElNormalVar2Low(files_, "($new pkg.Ex(5)[0]=$new pkg.ExTwo(15)).myval", new ClassField("pkg.Ex", "inst"), "pkg.Ex", new IntStruct(6), "v", "pkg.Ex");
        assertEq(15,getNumber(argument_));
    }
    @Test
    public void processEl441Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.ExTwo(pkg.ExTwo a, pkg.ExTwo b) {\n");
        xml_.append(" $var o =$new pkg.ExTwo();\n");
        xml_.append(" o.myval=a.myval+b.myval;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int myval;\n");
        xml_.append(" $public ExTwo(){}\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  myval=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public ExTwo[] inst={$new ExTwo()};\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=$new ExTwo(p);\n");
        xml_.append(" }\n");
        xml_.append(" $public ExTwo $this($int p){\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  inst[p]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processElNormalVar2Low(files_, "($new pkg.Ex(5)[0]+=$new pkg.ExTwo(15)).myval", new ClassField("pkg.Ex", "inst"), "pkg.Ex", new IntStruct(6), "v", "pkg.Ex");
        assertEq(20,getNumber(argument_));
    }
    @Test
    public void processEl442Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.ExTwo(pkg.ExTwo a) {\n");
        xml_.append(" $var o =$new pkg.ExTwo();\n");
        xml_.append(" o.myval=a.myval+1;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int myval;\n");
        xml_.append(" $public ExTwo(){}\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  myval=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public ExTwo[] inst={$new ExTwo()};\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=$new ExTwo(p);\n");
        xml_.append(" }\n");
        xml_.append(" $public ExTwo $this($int p){\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  inst[p]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processElNormalVar2Low(files_, "(++$new pkg.Ex(5)[0]).myval", new ClassField("pkg.Ex", "inst"), "pkg.Ex", new IntStruct(6), "v", "pkg.Ex");
        assertEq(6,getNumber(argument_));
    }
    @Test
    public void processEl443Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.ExTwo(pkg.ExTwo a) {\n");
        xml_.append(" $var o =$new pkg.ExTwo();\n");
        xml_.append(" o.myval=a.myval+1;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int myval;\n");
        xml_.append(" $public ExTwo(){}\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  myval=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public ExTwo[] inst={$new ExTwo()};\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=$new ExTwo(p);\n");
        xml_.append(" }\n");
        xml_.append(" $public ExTwo $this($int p){\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  inst[p]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processElNormalVar2Low(files_, "($new pkg.Ex(5)[0]++).myval", new ClassField("pkg.Ex", "inst"), "pkg.Ex", new IntStruct(6), "v", "pkg.Ex");
        assertEq(5,getNumber(argument_));
    }
    @Test
    public void processEl445Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int myval;\n");
        xml_.append(" $public ExTwo(){}\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  myval=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub:Ex {\n");
        xml_.append(" $public ExSub(){}\n");
        xml_.append(" $public ExSub($int p){\n");
        xml_.append("  $super(p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public ExTwo[] inst={$new ExTwo()};\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=$new ExTwo(p);\n");
        xml_.append(" }\n");
        xml_.append(" $public ExTwo $this($int p){\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  inst[p]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processElNormalVar2(files_, "$new pkg.ExSub(5).$super[0].myval", new ClassField("pkg.Ex", "inst"), "pkg.Ex", new IntStruct(6), "v", "pkg.Ex");
        assertEq(5,getNumber(argument_));
    }

    @Test
    public void processEl449Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  $return \"\"+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument arg_ = processEl(files_, "pkg.Apply.test()");
        assertEq("2,4",getString(arg_));
    }
    @Test
    public void processEl452Test() {
        StringMap<String> files_ = new StringMap<String>();
        Argument argument_ = processEl(files_, "$defaultValue($char)");
        assertEq(0,getChar(argument_));
    }
    @Test
    public void processEl454Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static $int inst=14;\n");
        xml_.append(" $public $int res(T v){\n");
        xml_.append("  $return ($int)inst+($int)v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "$new pkg.Ex<$int>().res($id(pkg.Ex,T),15)");
        assertEq(29,getNumber(argument_));
    }
    @Test
    public void processEl460Test() {
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
        Argument argument_ = processEl(files_, "$new pkg.Outer.Ex<$int>().res($id(pkg.Outer.Ex,T),15)");
        assertEq(29,getNumber(argument_));
    }
    @Test
    public void processEl463Test() {
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
        Argument argument_ = processEl(files_, "$new pkg.Outer.Ex<$int>().res($id(pkg.Outer.Ex,T),15)");
        assertEq(29,getNumber(argument_));
    }
    @Test
    public void processEl465Test() {
        Argument arg_ = processElNormal3("explicit($int)5", new StringMap<String>());
        
        assertEq(5, getNumber(arg_));

    }

    @Test
    public void processEl466Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static Ex explicit($int v){\n");
        xml_.append("  Ex o = $new Ex();\n");
        xml_.append("  o.field = v;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument arg_ = processElNormal2(files_, "explicit(pkg.Ex)5", "pkg.Ex");
        
        Struct struct_ = arg_.getStruct();
        assertEq(5, ((IntStruct)getStruct(struct_,new ClassField("pkg.Ex","field"))).intStruct());

    }
    @Test
    public void processEl468Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $staticCall $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument arg_ = processEl(files_, "$staticCall(pkg.Ex).exmeth()");
        assertEq(9, getNumber(arg_));
    }

    @Test
    public void processEl469Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Interface {\n");
        xml_.append(" $int opTwo($int a,$int b);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument arg_ = processEl(files_, "((pkg.Interface) $static($math).$lambda($math,plus,$int,$int)).opTwo(1,2)");
        assertEq(3, getNumber(arg_));
    }
    @Test
    public void processEl470Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $staticCall T exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return (T)(1i+$($int)t);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument arg_ = processEl(files_, "$staticCall(pkg.Ex<$int>).exmeth()");
        assertEq(9, getNumber(arg_));
    }
    @Test
    public void processEl471Test() {
        StringMap<String> files_ = new StringMap<String>();
        Argument argument_ = processEl(files_, "$default(0)");
        assertEq(0,getNumber(argument_));
    }

    @Test
    public void processEl472Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth($int q,$int...p){\n");
        xml_.append("  $var sum = q;\n");
        xml_.append("  $return sum+(p==$null?8:5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Argument arg_ = processEl(files_, "pkg.ExTwo.exmeth($vararg($int),4,$firstopt($null))");
        assertEq(12,getNumber(arg_));
    }
    @Test
    public void processEl473Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static Ex $($int v){\n");
        xml_.append("  Ex o = $new Ex();\n");
        xml_.append("  o.field = v;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument arg_ = processElNormal2(files_, "$(pkg.Ex,$int)5", "pkg.Ex");
        
        Struct struct_ = arg_.getStruct();
        assertEq(5, ((IntStruct)getStruct(struct_,new ClassField("pkg.Ex","field"))).intStruct());

    }

    @Test
    public void processEl474Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "($($Field)$lambda(pkg.Ex,,inst,$int).metaInfo()).getType().getName()");
        assertEq("$int",getString(argument_));
    }
    @Test
    public void processEl475Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "($Class.getClass($(pkg.Ex)$new pkg.Ex().$lambda(pkg.Ex,,inst,$int).instance())).getName()");
        assertEq("pkg.Ex",getString(argument_));
    }
    @Test
    public void processEl476Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst(){$return 4;};\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument argument_ = processEl(files_, "$lambda(pkg.Ex,inst).call()");
        assertEq(4,getNumber(argument_));
    }
    @Test
    public void processEl477Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int j,$int k){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=j+k;\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument arg_ = processEl(files_, "$static(pkg.Ex).exmeth(k:5,j:3)");
        assertEq(9, getNumber(arg_));
    }
    @Test
    public void processEl478Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int j,$int k){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=j+k;\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Argument arg_ = processEl(files_, "$static(pkg.Ex).exmeth(j:3,k:5)");
        assertEq(9, getNumber(arg_));
    }
    @Test
    public void procesAffect00Test() {
        LocalVariable lv_ = processElNormal1Int2(0, "(v)=1i");
        assertEq(1, ((NumberStruct)lv_.getStruct()).intStruct());
    }
    @Test
    public void processAffect1Test() {
        LocalVariable lv_ = processElNormal1Int2(0, "v=1i");
        assertEq(1, ((NumberStruct)lv_.getStruct()).intStruct());
    }

    @Test
    public void processAffect3Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        setVal(lv_, 0, ARR_INT);
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        calcLow("v[0i]=12i", context_);
        assertEq(12, ((NumberStruct) ((ArrayStruct)lv_.getStruct()).getInstance()[0]).intStruct());
    }
    @Test
    public void processAffect4Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        setArrays(lv_);
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        calcLow("v[0i][0i]=12i", context_);
        assertEq(12, ((NumberStruct)(((ArrayStruct)((ArrayStruct)lv_.getStruct()).getInstance()[0]).getInstance())[0]).intStruct());
    }
    @Test
    public void processAffect5Test() {
        LocalVariable lv_ = processElNormal1Int2(1, "v+=1i");
        assertEq(2, ((NumberStruct)lv_.getStruct()).intStruct());
    }
    @Test
    public void processAffect6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Composite {\n");
        xml_.append(" $public $int integer;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestConfiguration cont_ = getConfigurationQuick(files_);
        CustList<OperationNode> all_ = getQuickAnalyzedFwd("v.integer-=12i", 0, cont_, cont_.getAnalyzingDoc(),"pkg.Composite","v",false);
        addImportingPage(cont_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct var_ = init(cont_,"pkg.Composite");
        lv_.setStruct(var_);
        lv_.setClassName("pkg.Composite");
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(cont_, localVars_);
        setGlobalType(cont_, "pkg.Composite");
        Argument res_ = buildAndCalculate(cont_, all_);
        assertEq(-12, ((NumberStruct)getStruct(var_,new ClassField("pkg.Composite","integer"))).intStruct());
        assertEq(-12, getNumber(res_));
    }
    @Test
    public void processAffect7Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        ArrayStruct in_ = setVal(lv_, 0, ARR_INT);
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        Argument res_ = calcLow("v[0i]-=12i", context_);
        assertEq(-12, ((NumberStruct) in_.get(0)).intStruct());
        assertEq(-12, getNumber(res_));
    }
    @Test
    public void processAffect8Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        ArrayStruct in_ = setArrays(lv_);
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        Argument res_ = calcLow("v[0i][0i]-=12i", context_);
        assertEq(-12, ((NumberStruct)(((ArrayStruct)in_.get(0)).getInstance())[0]).intStruct());
        assertEq(-12, getNumber(res_));
    }
    @Test
    public void processAffect9Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        ArrayStruct in_ = setArrays(lv_);
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        Argument res_ = calcLow("v[0i][0i]++", context_);
        assertEq(1, ((NumberStruct)(((ArrayStruct)in_.get(0)).getInstance())[0]).intStruct());
        assertEq(0, getNumber(res_));
    }

    @Test
    public void processAffect10Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        ArrayStruct in_ = setArrays(lv_);
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        Argument res_ = calcLow("v[0i][0i]--", context_);
        assertEq(-1, ((NumberStruct)(((ArrayStruct)in_.get(0)).getInstance())[0]).intStruct());
        assertEq(0, getNumber(res_));
    }
    @Test
    public void processAffect11Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        ArrayStruct in_ = setArrays(lv_);
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        Argument res_ = calcLow("++v[0i][0i]", context_);
        assertEq(1, ((NumberStruct)(((ArrayStruct)in_.get(0)).getInstance())[0]).intStruct());
        assertEq(1, getNumber(res_));
    }
    @Test
    public void processAffect12Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        ArrayStruct in_ = setArrays(lv_);
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        Argument res_ = calcLow("--v[0i][0i]", context_);
        assertEq(-1, ((NumberStruct)(((ArrayStruct)in_.get(0)).getInstance())[0]).intStruct());
        assertEq(-1, getNumber(res_));
    }
    @Test
    public void processAffect21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static java.lang.Integer inst;\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestConfiguration cont_ = getConfigurationQuick(files_);
        addImportingPage(cont_);
        calcLow("$classchoice(pkg.Ex)inst=2i", cont_);
        Struct fieldValue_ = getStaticField(cont_);
        assertEq(2, ((NumberStruct) fieldValue_).intStruct());
    }
    @Test
    public void processAffect22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static java.lang.Integer inst;\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestConfiguration cont_ = getConfigurationQuick(files_);
        setGlobalType(cont_, "pkg.Ex");
        CustList<OperationNode> all_ = getQuickAnalyzedFwd("$classchoice(pkg.Ex)inst=v", 0, cont_, cont_.getAnalyzingDoc(),cont_.getAliasInteger(),"v",true);
        addImportingPage(cont_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(NullStruct.NULL_VALUE);
        lv_.setClassName(cont_.getAliasInteger());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(cont_, localVars_);
        buildAndCalculate(cont_, all_);
        Struct arg_ = getStaticField(cont_);
        assertSame(NullStruct.NULL_VALUE,arg_);
    }
    @Test
    public void processAffect23Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("add "));
        lv_.setClassName(context_.getAliasString());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        calcLow("v+=1i", context_);
        assertEq("add 1", ((StringStruct)lv_.getStruct()).getInstance());
    }
    @Test
    public void processAffect24Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        String arrayType_ = StringExpUtil.getPrettyArrayType(context_.getAliasString());
        ArrayStruct array_ = new ArrayStruct(1, arrayType_);
        array_.set(0, new StringStruct("add "));
        lv_.setStruct(array_);
        lv_.setClassName(arrayType_);
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        calcLow("v[0i]+=1i", context_);
        assertEq("add 1",((StringStruct)(((ArrayStruct) lv_.getStruct()).getInstance())[0]).getInstance());
    }
    @Test
    public void processAffect25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int[] inst=$new $int[1i];\n");
        xml_.append(" $public $static $int[] exmeth(){\n");
        xml_.append("  $return inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestConfiguration cont_ = getConfigurationQuick(files_);
        addImportingPage(cont_);
        setGlobalType(cont_, "pkg.Ex");
        CustList<OperationNode> all_ = getQuickAnalyzed("$static(pkg.Ex).exmeth()[0i]=2i", 0, cont_, cont_.getAnalyzingDoc());
        buildAndCalculateFwd(cont_, all_);
        Struct fieldValue_ = getStaticField(cont_);
        Struct[] res_ = ((ArrayStruct) fieldValue_).getInstance();
        assertEq(1,res_.length);
        assertEq(2,((NumberStruct)res_[0]).intStruct());
    }
    @Test
    public void processAffect26Test() {
        LocalVariable lv_ = processElNormal1Bool1(true, "v&=$false");
        assertTrue(BooleanStruct.isFalse(lv_.getStruct()));
    }
    @Test
    public void processAffect27Test() {
        LocalVariable lv_ = processElNormal1Bool1(false, "v|=$true");
        assertTrue(BooleanStruct.isTrue(lv_.getStruct()));
    }

    @Test
    public void processAffect28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Composite {\n");
        xml_.append(" $public $int integer;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestConfiguration cont_ = getConfigurationQuick(files_);
        setGlobalType(cont_, "pkg.Composite");
        CustList<OperationNode> all_ = getQuickAnalyzedFwd("v.integer++", 0, cont_, cont_.getAnalyzingDoc(),"pkg.Composite","v",false);
        addImportingPage(cont_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct var_ = init(cont_,"pkg.Composite");
        lv_.setStruct(var_);
        lv_.setClassName("pkg.Composite");
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(cont_, localVars_);
        Argument res_ = buildAndCalculate(cont_, all_);
        assertEq(1, ((NumberStruct)getStruct(var_,new ClassField("pkg.Composite","integer"))).intStruct());
        assertEq(0, getNumber(res_));
    }
    @Test
    public void processAffect29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Composite {\n");
        xml_.append(" $public $int integer;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestConfiguration cont_ = getConfigurationQuick(files_);
        setGlobalType(cont_, "pkg.Composite");
        CustList<OperationNode> all_ = getQuickAnalyzedFwd("++v.integer", 0, cont_, cont_.getAnalyzingDoc(),"pkg.Composite","v",false);
        addImportingPage(cont_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct var_ = init(cont_,"pkg.Composite");
        lv_.setStruct(var_);
        lv_.setClassName("pkg.Composite");
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(cont_, localVars_);
        Argument res_ = buildAndCalculate(cont_, all_);
        assertEq(1, ((NumberStruct)getStruct(var_,new ClassField("pkg.Composite","integer"))).intStruct());
        assertEq(1, getNumber(res_));
    }

    @Test
    public void processAffect30Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Composite {\n");
        xml_.append(" $public $int integer;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestConfiguration cont_ = getConfigurationQuick(files_);
        setGlobalType(cont_, "pkg.Composite");
        CustList<OperationNode> all_ = getQuickAnalyzedFwd("(v.integer-=12i)", 0, cont_, cont_.getAnalyzingDoc(),"pkg.Composite","v",false);
        addImportingPage(cont_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct var_ = init(cont_,"pkg.Composite");
        lv_.setStruct(var_);
        lv_.setClassName("pkg.Composite");
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(cont_, localVars_);
        Argument res_ = buildAndCalculate(cont_, all_);
        assertEq(-12, ((NumberStruct)getStruct(var_,new ClassField("pkg.Composite","integer"))).intStruct());
        assertEq(-12, getNumber(res_));
    }
    @Test
    public void processAffect31Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        ArrayStruct in_ = setArrays(lv_);
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        calcLow("(v[0i][0i])=12i", context_);
        assertEq(12, ((NumberStruct)(((ArrayStruct)in_.get(0)).getInstance())[0]).intStruct());
    }

    @Test
    public void processEl179Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getAliasPrimInteger());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        setupAnalyzing(context_);
        String elr_ = "v+=1i";
        Delimiters d_ = checkSyntax(context_, elr_, 0);
        assertTrue(d_.getBadOffset() < 0);
        OperationsSequence opTwo_ = getOperationsSequence(0, elr_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        assertTrue(context_.isEmptyErrors());
        calculate(all_, context_);
        assertEq(4, ((NumberStruct)lv_.getStruct()).intStruct());
    }

    @Test
    public void processEl180Test() {
        LocalVariable lv_ = getModBoolVar(true, "v&=$false");
        assertTrue(BooleanStruct.isFalse(lv_.getStruct()));
    }

    @Test
    public void processEl181Test() {
        LocalVariable lv_ = getModBoolVar(false, "v|=$true");
        assertTrue(BooleanStruct.isTrue(lv_.getStruct()));
    }
    @Test
    public void processEl186Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getAliasPrimInteger());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        setupAnalyzing(context_);
        String elr_ = "v==1i";
        Delimiters d_ = checkSyntax(context_, elr_, 0);
        assertTrue(d_.getBadOffset() < 0);
        OperationsSequence opTwo_ = getOperationsSequence(0, elr_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        assertTrue(context_.isEmptyErrors());
        Argument arg_ = calculate(all_, context_);
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl187Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getAliasPrimInteger());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        setupAnalyzing(context_);
        String elr_ = "v++";
        Delimiters d_ = checkSyntax(context_, elr_, 0);
        assertTrue(d_.getBadOffset() < 0);
        OperationsSequence opTwo_ = getOperationsSequence(0, elr_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        assertTrue(context_.isEmptyErrors());
        Argument arg_ = calculate(all_, context_);
        assertEq(4, ((NumberStruct)lv_.getStruct()).intStruct());
        assertEq(3, getNumber(arg_));
    }
    @Test
    public void processEl188Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getAliasPrimInteger());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        setupAnalyzing(context_);
        String elr_ = "++v";
        Delimiters d_ = checkSyntax(context_, elr_, 0);
        assertTrue(d_.getBadOffset() < 0);
        OperationsSequence opTwo_ = getOperationsSequence(0, elr_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        assertTrue(context_.isEmptyErrors());
        Argument arg_ = calculate(all_, context_);
        assertEq(4, ((NumberStruct)lv_.getStruct()).intStruct());
        assertEq(4, getNumber(arg_));
    }
    @Test
    public void processEl189Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        ArrayStruct in_ = setVal(lv_, 5, ARR_INT);
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        setupAnalyzing(context_);
        String elr_ = "v[0i]++";
        Delimiters d_ = checkSyntax(context_, elr_, 0);
        assertTrue(d_.getBadOffset() < 0);
        OperationsSequence opTwo_ = getOperationsSequence(0, elr_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        assertTrue(context_.isEmptyErrors());
        Argument arg_ = calculate(all_, context_);
        assertEq(6, ((NumberStruct) in_.get(0)).intStruct());
        assertEq(5, getNumber(arg_));
    }
    @Test
    public void processEl190Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        ArrayStruct in_ = setVal(lv_, 5, ARR_INT);
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        setupAnalyzing(context_);
        String elr_ = "++v[0i]";
        Delimiters d_ = checkSyntax(context_, elr_, 0);
        assertTrue(d_.getBadOffset() < 0);
        OperationsSequence opTwo_ = getOperationsSequence(0, elr_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        assertTrue(context_.isEmptyErrors());
        Argument arg_ = calculate(all_, context_);
        assertEq(6, ((NumberStruct) in_.get(0)).intStruct());
        assertEq(6, getNumber(arg_));
    }
    @Test
    public void processEl191Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getAliasPrimInteger());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        setupAnalyzing(context_);
        String elr_ = "v+=2i";
        Delimiters d_ = checkSyntax(context_, elr_, 0);
        assertTrue(d_.getBadOffset() < 0);
        OperationsSequence opTwo_ = getOperationsSequence(0, elr_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        assertTrue(context_.isEmptyErrors());
        Argument arg_ = calculate(all_, context_);
        assertEq(5, ((NumberStruct)lv_.getStruct()).intStruct());
        assertEq(5, getNumber(arg_));
    }
    @Test
    public void processEl192Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        ArrayStruct in_ = setVal(lv_, 5, ARR_INT);
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        setupAnalyzing(context_);
        String elr_ = "v[0i]+=3i";
        Delimiters d_ = checkSyntax(context_, elr_, 0);
        assertTrue(d_.getBadOffset() < 0);
        OperationsSequence opTwo_ = getOperationsSequence(0, elr_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        assertTrue(context_.isEmptyErrors());
        Argument arg_ = calculate(all_, context_);
        assertEq(8, ((NumberStruct) in_.get(0)).intStruct());
        assertEq(8, getNumber(arg_));
    }
    @Test
    public void processEl193Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getAliasPrimInteger());
        localVars_.put("v", lv_);
        LocalVariable lv2_ = new LocalVariable();
        lv2_.setStruct(new IntStruct(12));
        lv2_.setClassName(context_.getAliasPrimInteger());
        localVars_.put("v2", lv2_);
        CommonRender.setLocalVars(context_, localVars_);
        setupAnalyzing(context_);
        String elr_ = "v+++v2";
        Delimiters d_ = checkSyntax(context_, elr_, 0);
        assertTrue(d_.getBadOffset() < 0);
        OperationsSequence opTwo_ = getOperationsSequence(0, elr_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        assertTrue(context_.isEmptyErrors());
        Argument arg_ = calculate(all_, context_);
        assertEq(12, ((NumberStruct)lv2_.getStruct()).intStruct());
        assertEq(4, ((NumberStruct)lv_.getStruct()).intStruct());
        assertEq(15, getNumber(arg_));
    }

    @Test
    public void processEl194Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getAliasPrimInteger());
        localVars_.put("v", lv_);
        LocalVariable lv2_ = new LocalVariable();
        lv2_.setStruct(new IntStruct(12));
        lv2_.setClassName(context_.getAliasPrimInteger());
        localVars_.put("v2", lv2_);
        CommonRender.setLocalVars(context_, localVars_);
        setupAnalyzing(context_);
        String elr_ = "v---v2";
        Delimiters d_ = checkSyntax(context_, elr_, 0);
        assertTrue(d_.getBadOffset() < 0);
        OperationsSequence opTwo_ = getOperationsSequence(0, elr_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        assertTrue(context_.isEmptyErrors());
        Argument arg_ = calculate(all_, context_);
        assertEq(12, ((NumberStruct)lv2_.getStruct()).intStruct());
        assertEq(2, ((NumberStruct)lv_.getStruct()).intStruct());
        assertEq(-9, getNumber(arg_));
    }
    @Test
    public void processEl195Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getAliasPrimInteger());
        localVars_.put("v", lv_);
        LocalVariable lv2_ = new LocalVariable();
        lv2_.setStruct(new IntStruct(12));
        lv2_.setClassName(context_.getAliasPrimInteger());
        localVars_.put("v2", lv2_);
        CommonRender.setLocalVars(context_, localVars_);
        setupAnalyzing(context_);
        String elr_ = "v=++v2";
        Delimiters d_ = checkSyntax(context_, elr_, 0);
        assertTrue(d_.getBadOffset() < 0);
        OperationsSequence opTwo_ = getOperationsSequence(0, elr_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        assertTrue(context_.isEmptyErrors());
        Argument arg_ = calculate(all_, context_);
        assertEq(13, ((NumberStruct)lv2_.getStruct()).intStruct());
        assertEq(13, ((NumberStruct)lv_.getStruct()).intStruct());
        assertEq(13, getNumber(arg_));
    }
    @Test
    public void processEl196Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getAliasPrimInteger());
        localVars_.put("v", lv_);
        LocalVariable lv2_ = new LocalVariable();
        lv2_.setStruct(new IntStruct(12));
        lv2_.setClassName(context_.getAliasPrimInteger());
        localVars_.put("v2", lv2_);
        CommonRender.setLocalVars(context_, localVars_);
        setupAnalyzing(context_);
        String elr_ = "v= ++v2";
        Delimiters d_ = checkSyntax(context_, elr_, 0);
        assertTrue(d_.getBadOffset() < 0);
        OperationsSequence opTwo_ = getOperationsSequence(0, elr_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        assertTrue(context_.isEmptyErrors());
        Argument arg_ = calculate(all_, context_);
        assertEq(13, ((NumberStruct)lv2_.getStruct()).intStruct());
        assertEq(13, ((NumberStruct)lv_.getStruct()).intStruct());
        assertEq(13, getNumber(arg_));
    }
    @Test
    public void processEl197Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        context_.getAnalyzingDoc().setup(context_.getConfiguration(), context_.getDual());
        setupAna(context_.getAnalyzingDoc(), context_.getAnalyzing());
        String elr_ = "+1y";
        Delimiters d_ = checkSyntax(context_, elr_, 0);
        assertTrue(d_.getBadOffset() < 0);
        OperationsSequence opTwo_ = getOperationsSequence(0, elr_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        assertTrue(context_.isEmptyErrors());
        Argument arg_ = calculate(all_, context_);
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl305Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getAliasPrimInteger());
        localVars_.put("v", lv_);
        LocalVariable lv2_ = new LocalVariable();
        lv2_.setStruct(new IntStruct(12));
        lv2_.setClassName(context_.getAliasPrimInteger());
        localVars_.put("v2", lv2_);
        CommonRender.setLocalVars(context_, localVars_);
        setupAnalyzing(context_);
        String elr_ = "v=v2=4i";
        Delimiters d_ = checkSyntax(context_, elr_, 0);
        assertTrue(d_.getBadOffset() < 0);
        OperationsSequence opTwo_ = getOperationsSequence(0, elr_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        assertTrue(context_.isEmptyErrors());
        Argument arg_ = calculate(all_, context_);
        assertEq(4, ((NumberStruct)lv2_.getStruct()).intStruct());
        assertEq(4, ((NumberStruct)lv_.getStruct()).intStruct());
        assertEq(4, getNumber(arg_));
    }

    private static Argument processElNormal1String(String _s) {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getAliasString());
        lv_.setStruct(new StringStruct("8"));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        CommonRender.setLocalVars(context_, localVars_);
        return calcLow(_s, context_);
    }

    private static Argument processElNormal2BooleanVars(String _s) {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getAliasBoolean());
        lv_.setStruct(NullStruct.NULL_VALUE);
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getAliasBoolean());
        lv_.setStruct(BooleanStruct.of(false));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        CommonRender.setLocalVars(context_, localVars_);
        return calcLow(_s, context_);
    }

    private static Argument processElNormalBoolInt(boolean _b, String _s, IntStruct _sec) {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(_b));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getAliasPrimInteger());
        lv_.setStruct(_sec);
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        CommonRender.setLocalVars(context_, localVars_);
        return calcLow(_s, context_);
    }

    private static Argument processElNormalBool(boolean _b, String _s) {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(_b));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        CommonRender.setLocalVars(context_, localVars_);
        return calcLow(_s, context_);
    }

    private static Argument processElNormal1Int(int _i, String _s, String _varName) {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getAliasPrimInteger());
        lv_.setStruct(new IntStruct(_i));
        localVars_.put(_varName, lv_);
        addImportingPage(context_);
        CommonRender.setLocalVars(context_, localVars_);
        return calcLow(_s, context_);
    }

    private static Argument calcLow(String _s, AnalyzedTestConfiguration _context) {
        setGlobalType(_context, _context.getLastPage().getGlobalArgument().getStruct().getClassName(_context.getContext()));
        _context.getAnalyzingDoc().setup(_context.getConfiguration(), _context.getDual());
        setupAnalyzing(_context, _context.getLastPage(), _context.getAnalyzingDoc());
        Argument argGl_ = _context.getRendStackCall().getPageEl().getGlobalArgument();
        boolean static_ = argGl_.isNull();
        _context.getAnalyzing().setAccessStaticContext(MethodId.getKind(static_));
        Delimiters d_ = checkSyntax(_context, _s, 0);
        OperationsSequence opTwo_ = rendOpSeq(0, _context, d_, _s);
        OperationNode op_ = rendOp(0, _context, opTwo_);
        CustList<OperationNode> all_ = getSortedDescNodes(_context, op_);
        Classes.forwardAndClear(_context.getContext(), _context.getAnalyzing(), _context.getForwards());
        CustList<RendDynOperationNode> out_ = getExecutableNodes(_context, all_);
        assertTrue(_context.isEmptyErrors());
        ExecClassesUtil.forwardClassesMetaInfos(_context.getContext());
        out_ = CommonRender.getReducedNodes(out_.last());
        ExecClassesUtil.tryInitStaticlyTypes(_context.getContext(),_context.getAnalyzing().getOptions());
        Argument arg_ = caculateReuse(_context, out_);
        checkNullEx(_context);
        return arg_;
    }

    private static Argument buildAndCalculate(AnalyzedTestConfiguration _context, CustList<OperationNode> _all) {
        CustList<RendDynOperationNode> out_ = getExecutableNodes(_context, _all);
        assertTrue(_context.isEmptyErrors());
        out_ = CommonRender.getReducedNodes(out_.last());
        ExecClassesUtil.tryInitStaticlyTypes(_context.getContext(),_context.getAnalyzing().getOptions());
        Argument arg_ = caculateReuse(_context, out_);
        checkNullEx(_context);
        return arg_;
    }

    private static Argument buildAndCalculateFwd(AnalyzedTestConfiguration _context, CustList<OperationNode> _all) {
        Classes.forwardAndClear(_context.getContext(), _context.getAnalyzing(), _context.getForwards());
        CustList<RendDynOperationNode> out_ = getExecutableNodes(_context, _all);
        assertTrue(_context.isEmptyErrors());
        out_ = CommonRender.getReducedNodes(out_.last());
        ExecClassesUtil.tryInitStaticlyTypes(_context.getContext(),_context.getAnalyzing().getOptions());
        Argument arg_ = caculateReuse(_context, out_);
        checkNullEx(_context);
        return arg_;
    }

    private static Argument processElNormal2BoolVars(boolean _b, boolean _b2, String _s) {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(_b));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(_b2));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        CommonRender.setLocalVars(context_, localVars_);
        return calcLow(_s, context_);
    }

    private static Argument processElNormalVar2(StringMap<String> _files, String _s, ClassField _keyField, String _className, Struct _value, String _varName, String _init) {
        AnalyzedTestConfiguration conf_ = getConfigurationQuick(_files);
        addImportingPage(conf_);
        return calcLow(_s, conf_);
    }

    private static Argument processElNormalVar2Low(StringMap<String> _files, String _s, ClassField _keyField, String _className, Struct _value, String _varName, String _init) {
        AnalyzedTestConfiguration conf_ = getConfigurationQuick(_files);
        ContextEl cont_ = conf_.getContext();
        addImportingPage(conf_);
        conf_.getAnalyzingDoc().setup(conf_.getConfiguration(), conf_.getDual());
        setupAnalyzing(conf_, conf_.getLastPage(), conf_.getAnalyzingDoc());
        Argument argGl_ = conf_.getRendStackCall().getPageEl().getGlobalArgument();
        boolean static_ = argGl_.isNull();
        conf_.getAnalyzing().setAccessStaticContext(MethodId.getKind(static_));
        Delimiters d_ = checkSyntax(conf_, _s, 0);
        OperationsSequence opTwo_ = rendOpSeq(0, conf_, d_, _s);
        OperationNode op_ = rendOp(0, conf_, opTwo_);
        CustList<OperationNode> all_ = getSortedDescNodes(conf_, op_);
        conf_.getAdvStandards().forwardAndClear(conf_.getConfiguration(),conf_.getAnalyzing(),conf_.getAnalyzingDoc(),conf_.getForwards(),conf_.getAnalyzed(),cont_);
        CustList<RendDynOperationNode> out_ = getExecutableNodes(conf_, all_);
        assertTrue(conf_.isEmptyErrors());
        ExecClassesUtil.forwardClassesMetaInfos(conf_.getContext());
        out_ = CommonRender.getReducedNodes(out_.last());
        Argument arg_ = caculateReuse(conf_, out_);
        checkNullEx(conf_);
        return arg_;
    }

    private static LocalVariable processElNormal1Bool1(boolean _b, String _s) {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(BooleanStruct.of(_b));
        lv_.setClassName(context_.getAliasPrimBoolean());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        calcLow(_s, context_);
        return lv_;
    }

    private static LocalVariable processElNormal1Int2(int _i, String _s) {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(_i));
        lv_.setClassName(context_.getAliasPrimInteger());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        calcLow(_s, context_);
        return lv_;
    }

    private static Argument processElNormalLow(String _el, AnalyzedTestConfiguration _cont, String _expClass) {
        Argument arg_ = calcLow(_el, _cont);
        assertEq(_expClass,arg_.getStruct().getClassName(_cont.getContext()));
        return arg_;
    }

    private static Argument calculate(CustList<OperationNode> _ops, AnalyzedTestConfiguration _an) {
        CustList<RendDynOperationNode> out_ = getExecutableNodes(_an, _ops);
        out_ = CommonRender.getReducedNodes(out_.last());
        Argument arg_ = caculateReuse(_an, out_);
        checkNullEx(_an);
        return arg_;
    }

    private static Argument processElNormal2(StringMap<String> _files, String _s, String _expClass) {
        AnalyzedTestConfiguration conf_ = getConfigurationQuick(_files);
        addImportingPage(conf_);
        return processElNormalLow(_s, conf_, _expClass);
    }

    private static Argument processElNormalNotInit(StringMap<String> _files, String _s,String... _types) {
        AnalyzedTestConfiguration cont_ = getConfigurationQuick(_files,_types);
        addImportingPage(cont_);
        return calcLow(_s, cont_);
    }

    private static boolean isInitialized(AnalyzedTestConfiguration _cont) {
        return _cont.getContext().getLocks().getState("pkg.Ex") != InitClassState.NOT_YET;
    }

    private static Argument processElNormalInit(StringMap<String> _files, String _s, String... _types) {
        AnalyzedTestConfiguration conf_ = getConfigurationQuick(_files,_types);
        addImportingPage(conf_);
        Argument arg_ = calcLow(_s, conf_);
        assertTrue(isInitialized(conf_));
        assertSame(conf_.getContext().getLocks().getState("pkg.Ex"), InitClassState.SUCCESS);
        return arg_;
    }

    private static Argument processEl(StringMap<String> _files, String _s, String... _types) {
        return processElNormal3(_s, _files,_types);
    }

    private static Argument processElLow(StringMap<String> _files, String _s, String... _types) {
        return processElNormal3Low(_s, _files,_types);
    }

    private static LocalVariable getModBoolVar(boolean _b, String _s) {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(BooleanStruct.of(_b));
        lv_.setClassName(context_.getAliasPrimBoolean());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        setupAnalyzing(context_);
        Delimiters d_ = checkSyntax(context_, _s, 0);
        assertTrue(d_.getBadOffset() < 0);
        OperationsSequence opTwo_ = getOperationsSequence(0, _s, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        assertTrue(context_.isEmptyErrors());
        ForwardInfos.generalForward(context_.getAnalyzing(),context_.getForwards(),context_.getContext());
        CustList<RendDynOperationNode> out_ = getExecutableNodes(context_, all_);
        out_ = CommonRender.getReducedNodes(out_.last());
        ExecClassesUtil.tryInitStaticlyTypes(context_.getContext(), context_.getAnalyzing().getOptions());
        caculateReuse(context_, out_);
        checkNullEx(context_);
        return lv_;
    }

    private static AnalyzedTestConfiguration getConfigurationQuick(StringMap<String> _files,String... _types) {
        Configuration conf_ = EquallableExUtil.newConfiguration();
        AnalyzedTestConfiguration a_ = build(conf_,_types);
        getHeaders(_files, a_);
        assertTrue(isEmptyErrors(a_));
        return a_;
    }

    private static void setPairs(LocalVariable _lv) {
        ArrayStruct arrArr_ = new ArrayStruct(2, ARR_ARR_INT);
        ArrayStruct first_ = new ArrayStruct(2, ARR_INT);
        first_.set(0,new IntStruct(0));
        first_.set(1,new IntStruct(0));
        arrArr_.set(0, first_);
        ArrayStruct sec_ = new ArrayStruct(2, ARR_INT);
        sec_.set(0,new IntStruct(0));
        sec_.set(1,new IntStruct(0));
        arrArr_.set(1, sec_);
        _lv.setStruct(arrArr_);
        _lv.setClassName(ARR_ARR_INT);
    }

    private static ArrayStruct setVal(LocalVariable _lv, int _value, String _arrInt) {
        ArrayStruct arr_ = new ArrayStruct(1, _arrInt);
        arr_.set(0,new IntStruct(_value));
        _lv.setStruct(arr_);
        _lv.setClassName(_arrInt);
        return arr_;
    }

    private static ArrayStruct setArrays(LocalVariable _lv) {
        ArrayStruct arrArr_ = new ArrayStruct(1, ARR_ARR_INT);
        ArrayStruct arr_ = new ArrayStruct(1, ARR_INT);
        arr_.set(0, new IntStruct(0));
        arrArr_.set(0, arr_);
        _lv.setStruct(arrArr_);
        _lv.setClassName(ARR_ARR_INT);
        return arrArr_;
    }

    private static void setValues(LocalVariable _lv, int _one, int _two, String _arrInt) {
        ArrayStruct arr_ = new ArrayStruct(2, _arrInt);
        arr_.set(0,new IntStruct(_one));
        arr_.set(1,new IntStruct(_two));
        _lv.setStruct(arr_);
        _lv.setClassName(_arrInt);
    }


    private static Argument processDoubleDelimiters(String _s, int _i, int _i2) {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        AnalyzingDoc analyzingDoc_ = context_.getAnalyzingDoc();
        setupAnalyzing(context_);
        Delimiters d1_ = checkDel(_s, 2, context_);
        assertTrue(d1_.getBadOffset() < 0);
        int beg1_ = d1_.getIndexBegin();
        int end1_ = d1_.getIndexEnd();
        analyzingDoc_.setNextIndex(end1_ +2);
        String el1_ = _s.substring(beg1_, end1_ +1);
        OperationsSequence opTwo1_ = rendOpSeq(context_, d1_, el1_, 2);
        OperationNode op1_ = rendOp(context_, opTwo1_);
        CustList<OperationNode> all1_ = getSortedDescNodes(context_, op1_);
        ForwardInfos.generalForward(context_.getAnalyzing(),context_.getForwards(),context_.getContext());
        ExecClassesUtil.tryInitStaticlyTypes(context_.getContext(), context_.getAnalyzing().getOptions());
        CustList<RendDynOperationNode> out1_ = getExecutableNodes(context_, all1_);
        assertTrue(isEmptyErrors(context_));
        out1_ = CommonRender.getReducedNodes(out1_.last());
        caculateReuse(context_, out1_);
        checkNullEx(context_);
        setupAnalyzing(context_);
        Delimiters d_ = checkDel(_s, _i, context_);
        assertTrue(d_.getBadOffset() < 0);
        int beg_ = d_.getIndexBegin();
        int end_ = d_.getIndexEnd();
        analyzingDoc_.setNextIndex(end_+2);
        String el_ = _s.substring(beg_,end_+1);
        OperationsSequence opTwo_ = rendOpSeq(_i, context_, d_, el_);
        OperationNode op_ = rendOp(_i, context_, opTwo_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        CustList<RendDynOperationNode> out_ = getExecutableNodes(context_, all_);
        assertTrue(isEmptyErrors(context_));
        out_ = CommonRender.getReducedNodes(out_.last());
        Argument arg_ = caculateReuse(context_, out_);
        checkNullEx(context_);
        assertEq(_i2, analyzingDoc_.getNextIndex());
        return arg_;
    }

    private static Struct initAndSet(AnalyzedTestConfiguration _context, ClassField _keyField, Struct _value, String _className) {
        Struct str_ = init(_context, _className);
        setStruct(str_, _keyField, _value);
        return str_;
    }

    private static Struct init(AnalyzedTestConfiguration _context, String _className) {
        return _context.getContext().getInit().processInit(_context.getContext(), NullStruct.NULL_VALUE, _className,_context.getContext().getClasses().getClassBody(_className), "", -1);
    }

    private static Argument processElNormal3(String _el, StringMap<String> _files, String... _types) {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(_files,_types);
        addImportingPage(context_);
        return calcLow(_el, context_);
    }

    private static Argument processElNormal3Low(String _el, StringMap<String> _files, String... _types) {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(_files,_types);
        addImportingPage(context_);
        setGlobalType(context_, context_.getLastPage().getGlobalArgument().getStruct().getClassName(context_.getContext()));
        context_.getAnalyzingDoc().setup(context_.getConfiguration(), context_.getDual());
        setupAnalyzing(context_, context_.getLastPage(), context_.getAnalyzingDoc());
        Argument argGl_ = context_.getRendStackCall().getPageEl().getGlobalArgument();
        boolean static_ = argGl_.isNull();
        context_.getAnalyzing().setAccessStaticContext(MethodId.getKind(static_));
        Delimiters d_ = checkSyntax(context_, _el, 0);
        OperationsSequence opTwo_ = rendOpSeq(0, context_, d_, _el);
        OperationNode op_ = rendOp(0, context_, opTwo_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        assertTrue(context_.isEmptyErrors());
        Classes.forwardAndClear(context_.getContext(), context_.getAnalyzing(), context_.getForwards());
        CustList<RendDynOperationNode> out_ = getExecutableNodes(context_, all_);
        ExecClassesUtil.forwardClassesMetaInfos(context_.getContext());
        out_ = CommonRender.getReducedNodes(out_.last());
        ExecClassesUtil.tryInitStaticlyTypes(context_.getContext(),context_.getAnalyzing().getOptions());
        Argument arg_ = caculateReuse(context_, out_);
        checkNullEx(context_);
        return arg_;
    }

    private static Argument caculateReuse(AnalyzedTestConfiguration _conf, CustList<RendDynOperationNode> _out) {
        _conf.getContext().setExiting(new NoExiting());
        return new Argument(calculateReuse(_conf,_out));
    }

    private static Delimiters checkDel(String _s, int _i, AnalyzedTestConfiguration _context) {
        return ElResolver.checkSyntaxDelimiters(_s, _i, _context.getAnalyzing());
    }

    private static Argument processDelimiters(String _s, int _i) {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        addImportingPage(context_);
        AnalyzingDoc analyzingDoc_ = context_.getAnalyzingDoc();
        setupAnalyzing(context_);
        Delimiters d_ = checkDel(_s, 2, context_);
        assertTrue(d_.getBadOffset() < 0);
        int beg_ = d_.getIndexBegin();
        int end_ = d_.getIndexEnd();
        analyzingDoc_.setNextIndex(end_+2);
        String el_ = _s.substring(beg_,end_+1);
        OperationsSequence opTwo_ = rendOpSeq(context_, d_, el_, 2);
        OperationNode op_ = rendOp(context_, opTwo_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        ForwardInfos.generalForward(context_.getAnalyzing(),context_.getForwards(),context_.getContext());
        ExecClassesUtil.tryInitStaticlyTypes(context_.getContext(), context_.getAnalyzing().getOptions());
        CustList<RendDynOperationNode> out_ = getExecutableNodes(context_, all_);
        assertTrue(isEmptyErrors(context_));
        out_ = CommonRender.getReducedNodes(out_.last());
        Argument arg_ = caculateReuse(context_, out_);
        checkNullEx(context_);
        assertEq(_i, analyzingDoc_.getNextIndex());
        return arg_;
    }

    private static OperationsSequence rendOpSeq(AnalyzedTestConfiguration _context, Delimiters _d, String _el, int _off) {
        return rendOpSeq(_off, _context, _d, _el);
    }

    private static OperationNode rendOp(AnalyzedTestConfiguration _context, OperationsSequence _opTwo) {
        return rendOp(2, _context, _opTwo);
    }

    private static Argument processElNormalField(StringMap<String> _files, ClassField _keyField, String _clasName, IntStruct _value, String _varName, String _init) {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(_files);
        setGlobalType(context_, _clasName);
        CustList<OperationNode> all_ = getQuickAnalyzedFwd("v.inst", 0, context_, context_.getAnalyzingDoc(),_clasName,_varName,false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct str_ = initAndSet(context_, _keyField, _value, _init);
        lv_.setStruct(str_);
        lv_.setClassName(_clasName);
        localVars_.put(_varName, lv_);
        CommonRender.setLocalVars(context_, localVars_);
        return buildAndCalculate(context_, all_);
    }

    private static String getString(Argument _arg) {
        return ((CharSequenceStruct)_arg.getStruct()).toStringInstance();
    }
    private static long getNumber(Argument _arg) {
        return ((NumberStruct)_arg.getStruct()).longStruct();
    }
    private static double getDouble(Argument _arg) {
        return ((NumberStruct)_arg.getStruct()).doubleStruct();
    }
    private static char getChar(Argument _arg) {
        return ((CharStruct)_arg.getStruct()).getChar();
    }

    private static void setupAnalyzing(AnalyzedTestConfiguration _context) {
        _context.getAnalyzingDoc().setup(_context.getConfiguration(), _context.getDual());
        setupAnalyzing(_context, _context.getLastPage(), _context.getAnalyzingDoc());
    }

    private static Struct getStaticField(AnalyzedTestConfiguration _cont) {
        StringMap<StringMap<Struct>> staticFields_ = _cont.getClasses().getStaticFields();
        return NumParsers.getStaticField(new ClassField("pkg.Ex", "inst"), staticFields_);
    }

    private static void setGlobalArgumentStruct(AnalyzedTestConfiguration _context, Struct _str) {
        _context.getLastPage().setGlobalArgumentStruct(_str);
    }

    private static void setupAnalyzingOneVar(AnalyzedPageEl _analyzing, AnalyzingDoc _analyzingDoc, String _type, String _varName) {

        String globalClass_ = _analyzing.getGlobalClass();
        setupAna(_analyzingDoc, _analyzing);
        _analyzing.setGlobalType(_analyzing.getAnaClassBody(StringExpUtil.getIdFromAllTypes(globalClass_)));
        AnaLocalVariable a_ = new AnaLocalVariable();
        a_.setClassName(_type);
        a_.setConstType(ConstType.LOC_VAR);
        _analyzing.getInfosVars().put(_varName, a_);
    }
    private static CustList<OperationNode> getQuickAnalyzedFwd(String _el, int _index, AnalyzedTestConfiguration _conf, AnalyzingDoc _analyzingDoc, String _type, String _varName, boolean _static) {
        _analyzingDoc.setup(_conf.getConfiguration(), _conf.getDual());
        setupAnalyzingOneVar(_conf.getAnalyzing(), _conf.getAnalyzingDoc(), _type, _varName);
        _conf.getAnalyzing().setAccessStaticContext(MethodId.getKind(_static));
        Delimiters d_ = checkSyntax(_conf, _el, _index);
        String el_ = _el.substring(_index);
        OperationsSequence opTwo_ = rendOpSeq(_index, _conf, d_, el_);
        OperationNode op_ = rendOp(_index, _conf, opTwo_);
        CustList<OperationNode> ops_ = getSortedDescNodes(_conf, op_);
        Classes.forwardAndClear(_conf.getContext(), _conf.getAnalyzing(), _conf.getForwards());
        return ops_;
    }

    private static void setGlobalType(AnalyzedTestConfiguration _context, String _clasName) {
        _context.getAnalyzing().setGlobalClass(_clasName);
        _context.getAnalyzing().setGlobalType(_context.getAnalyzing().getAnaClassBody(StringExpUtil.getIdFromAllTypes(_clasName)));
    }

    private static void checkNullEx(AnalyzedTestConfiguration _context) {
        assertNull(getStackCall(_context).getCallingState());
    }

    private static StackCall getStackCall(AnalyzedTestConfiguration _context) {
        return _context.getStackCall();
    }


}
