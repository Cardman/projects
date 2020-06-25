package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.common.Delimiters;
import code.expressionlanguage.instr.ElResolver;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.analyze.blocks.Classes;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.AdvancedFullStack;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.*;

public final class RenderExpUtilSucessTest extends CommonRender {
    private static final String ARR_INT = "[$int";
    private static final String ARR_ARR_INT = "[[$int";
    private static final String ARR_INTEGER = "[java.lang.Integer";
    private static final String ARR_ARR_INTEGER = "[[java.lang.Integer";

    @Test
    public void processEl1Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("5", context_);
        assertEq(5L, getNumber(arg_));
    }
    @Test
    public void processEl2Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$static(java.lang.Long).MAX_VALUE", context_);
        assertEq(Long.MAX_VALUE, getNumber(arg_));
    }
    @Test
    public void processEl3Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("(1+2)*3", context_);
        assertEq(9L, getNumber(arg_));
    }
    @Test
    public void processEl4Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("1- -1", context_);
        assertEq(2L, getNumber(arg_));
    }
    @Test
    public void processEl5Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("1+2*3", context_);
        assertEq(7L, getNumber(arg_));
    }
    @Test
    public void processEl6Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("- -1", context_);
        assertEq(1L, getNumber(arg_));
    }
    @Test
    public void processEl7Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$static($math).abs(-8l)", context_);
        assertEq(8L, getNumber(arg_));
    }
    @Test
    public void processEl8Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$static($math).abs(8l)", context_);
        assertEq(8L, getNumber(arg_));
    }
    @Test
    public void processEl10Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("40908c", context_);
        assertEq(40908, getNumber(arg_));
    }
    @Test
    public void processEl11Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("'\\u9fcb'", context_);
        assertEq(40907, getNumber(arg_));
    }
    @Test
    public void processEl12Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("'\\\\'", context_);
        assertEq('\\', getNumber(arg_));
    }
    @Test
    public void processEl13Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("'\\''", context_);
        assertEq('\'', getNumber(arg_));
    }
    @Test
    public void processEl14Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("'\"'", context_);
        assertEq('"', getNumber(arg_));
    }
    @Test
    public void processEl15Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("'\\n'", context_);
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
        Configuration context_ = getConfiguration4(files_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct str_ = context_.getContext().getInit().processInit(context_.getContext(), NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        setStruct(str_,new ClassField("pkg.Ex","inst"), new IntStruct(2));
        lv_.setStruct(str_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = processElNormal("v.inst", context_);
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
        Configuration context_ = getConfiguration4(files_);
        addImportingPage(context_);
        StringMap<LoopVariable> localVars_ = new StringMap<LoopVariable>();
        LoopVariable lv_ = new LoopVariable();
        Struct str_ = context_.getContext().getInit().processInit(context_.getContext(), NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        setStruct(str_,new ClassField("pkg.Ex","inst"), new IntStruct(2));
        lv_.setStruct(str_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        context_.getLastPage().getVars().putAllMap(localVars_);
        Argument arg_ = processElNormal("v.inst", context_);
        assertEq(2, getNumber(arg_));
    }
    @Test
    public void processEl18Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("5 $instanceof java.lang.Number", context_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl19Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("'5' $instanceof java.lang.Number", context_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl20Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("!('5' $instanceof java.lang.Number)", context_);
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl21Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("1+1==2", context_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl22Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("1+1!=2", context_);
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl23Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("1+1==2&&1+0==8", context_);
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl24Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("1+1!=2||1+7==8", context_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl25Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("1+1==2&&(1+0==8||3*3==9)", context_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl26Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("1+1==2||1+6==8&&1==1", context_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl29Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("1+1==2||1/0>8", context_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl31Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$static($math).abs(-8i)", context_);
        assertEq(8, getNumber(arg_));
    }
    @Test
    public void processEl32Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$static($math).abs(8i)", context_);
        assertEq(8, getNumber(arg_));
    }
    @Test
    public void processEl33Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$static($math).abs(-8I)", context_);
        assertEq(8, getNumber(arg_));
    }
    @Test
    public void processEl34Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$static($math).abs(8I)", context_);
        assertEq(8, getNumber(arg_));
    }
    @Test
    public void processEl35Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$static($math).abs(-8L)", context_);
        assertEq(8L, getNumber(arg_));
    }
    @Test
    public void processEl36Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$static($math).abs(8L)", context_);
        assertEq(8L, getNumber(arg_));
    }
    @Test
    public void processEl39Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$null $instanceof java.lang.Object", context_);
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl58Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        String stringType_ = context_.getStandards().getAliasString();
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
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = processElNormal("f.format($vararg(java.lang.CharSequence),$firstopt(v),d,v)", context_);
        assertEq("varargs;7 8 7",getString(arg_));
    }
    @Test
    public void processEl59Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        String stringType_ = context_.getStandards().getAliasString();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("7"));
        lv_.setClassName(stringType_);
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("8"));
        lv_.setClassName(stringType_);
        localVars_.put("2", lv_);
        lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("varargs;{0} {1} {2}"));
        lv_.setClassName(stringType_);
        localVars_.put("f", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = processElNormal("f.format($vararg(java.lang.CharSequence))", context_);
        assertEq("varargs;{0} {1} {2}",getString(arg_));
    }
    @Test
    public void processEl60Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        String stringType_ = context_.getStandards().getAliasString();
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
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = processElNormal("f.format(v,d,v)", context_);
        assertEq("varargs;7 8 7",getString(arg_));
    }
    @Test
    public void processEl61Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        String stringType_ = context_.getStandards().getAliasString();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("7"));
        lv_.setClassName(stringType_);
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("8"));
        lv_.setClassName(stringType_);
        localVars_.put("2", lv_);
        lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("varargs;{0} {1} {2}"));
        lv_.setClassName(stringType_);
        localVars_.put("f", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = processElNormal("f.format()", context_);
        assertEq("varargs;{0} {1} {2}",getString(arg_));
    }
    @Test
    public void processEl63Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$new $int[1i]", context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INT, res_.getClassName(context_.getContext()));
        assertEq(1, (((ArrayStruct) res_).getInstance()).length);
        assertEq(0, ((NumberStruct) (((ArrayStruct) res_).getInstance())[0]).intStruct());
    }
    @Test
    public void processEl64Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$new $int[1i][]", context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_ARR_INT, res_.getClassName(context_.getContext()));
        assertEq(1, (((ArrayStruct) res_).getInstance()).length);
        assertSame(NullStruct.NULL_VALUE, (((ArrayStruct) res_).getInstance())[0]);
    }
    @Test
    public void processEl65Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$new java.lang.Integer[2i]", context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INTEGER, res_.getClassName(context_.getContext()));
        assertEq(2, (((ArrayStruct) res_).getInstance()).length);
        assertSame(NullStruct.NULL_VALUE, (((ArrayStruct) res_).getInstance())[0]);
        assertSame(NullStruct.NULL_VALUE, (((ArrayStruct) res_).getInstance())[1]);
    }
    @Test
    public void processEl66Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$new java.lang.Integer[2i][]", context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_ARR_INTEGER, res_.getClassName(context_.getContext()));
        assertEq(2, (((ArrayStruct) res_).getInstance()).length);
        assertSame(NullStruct.NULL_VALUE, (((ArrayStruct) res_).getInstance())[0]);
        assertSame(NullStruct.NULL_VALUE, (((ArrayStruct) res_).getInstance())[1]);
    }
    @Test
    public void processEl68Test() {
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] ints_ = new Struct[2];
        ints_[0] = new IntStruct(0);
        ints_[1] = new IntStruct(0);
        lv_.setStruct(new ArrayStruct(ints_,ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("arrays", lv_);
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = processElNormal("arrays[0i]", context_);
        assertEq(0, getNumber(arg_));
    }
    @Test
    public void processEl69Test() {
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] ints_ = new Struct[2];
        Struct[] elt_ = new Struct[2];
        elt_[0] = new IntStruct(0);
        elt_[1] = new IntStruct(0);
        ints_[0] = new ArrayStruct(elt_, ARR_INT);
        elt_ = new Struct[2];
        elt_[0] = new IntStruct(0);
        elt_[1] = new IntStruct(0);
        ints_[1] = new ArrayStruct(elt_, ARR_INT);
        lv_.setStruct(new ArrayStruct(ints_,ARR_ARR_INT));
        lv_.setClassName(ARR_ARR_INT);
        localVars_.put("arrays", lv_);
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = processElNormal("arrays[0i].length", context_);
        assertEq(2, getNumber(arg_));
    }
    @Test
    public void processEl70Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("!!$false", context_);
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl72Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$static(java.lang.Byte).MAX_VALUE", context_);
        assertEq((byte)127, getNumber(arg_));
    }
    @Test
    public void processEl77Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("(\"Hello\\\\\"+\"World\").length()", context_);
        assertEq(11, getNumber(arg_));
    }
    @Test
    public void processEl78Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("(\"Hello\\\"\"+\"World\").length()", context_);
        assertEq(11, getNumber(arg_));
    }
    @Test
    public void processEl79Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("(\"Hello\\\\\"+'\\\\').length()", context_);
        assertEq(7, getNumber(arg_));
    }
    @Test
    public void processEl80Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("(\"Hello\\\"\"+'\\'').length()", context_);
        assertEq(7, getNumber(arg_));
    }
    @Test
    public void processEl81Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        String stringType_ = context_.getStandards().getAliasString();
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
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = processElNormal("(f.format($vararg(java.lang.CharSequence),$firstopt(v),d,v)+'\\'').length()", context_);
        assertEq(14, getNumber(arg_));
    }
    @Test
    public void processEl82Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] i_ = new Struct[1];
        i_[0] = new IntStruct(8);
        lv_.setStruct(new ArrayStruct(i_,ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = processElNormal("$static($math).abs(v[0i]+2)*2", context_);
        assertEq(20L, getNumber(arg_));
    }
    @Test
    public void processEl83Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] i_ = new Struct[1];
        i_[0] = new IntStruct(8);
        lv_.setStruct(new ArrayStruct(i_,ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = processElNormal("(v[0i]+2)*2", context_);
        assertEq(20L, getNumber(arg_));
    }
    @Test
    public void processEl87Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = processElNormal("$bool(1>0,0i,1i)", context_);
        assertEq(0, getNumber(arg_));
    }
    @Test
    public void processEl88Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = processElNormal("$bool(1<0,0i,1i)", context_);
        assertEq(1, getNumber(arg_));
    }
    @Test
    public void processEl89Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = processElNormal("$bool(1>0,0i,1i/0i)", context_);
        assertEq(0, getNumber(arg_));
    }
    @Test
    public void processEl90Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = processElNormal("$bool(1<0,1i/0i,1i)", context_);
        assertEq(1, getNumber(arg_));
    }
    @Test
    public void processEl95Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = processElNormal("($new $int[1i])[0i]", context_);
        assertEq(0, getNumber(arg_));
    }
    @Test
    public void processEl96Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$new $int[]{2i}", context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INT, res_.getClassName(context_.getContext()));
        Struct[] o_ = ((ArrayStruct) res_).getInstance();
        assertEq(1, o_.length);
        assertEq(2, ((NumberStruct)o_[0]).intStruct());
    }
    @Test
    public void processEl97Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$new $int[]{3i,7i}", context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INT, res_.getClassName(context_.getContext()));
        Struct[] o_ = ((ArrayStruct) res_).getInstance();
        assertEq(2, o_.length);
        assertEq(3, ((NumberStruct)o_[0]).intStruct());
        assertEq(7, ((NumberStruct)o_[1]).intStruct());
    }
    @Test
    public void processEl98Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$new $int[]{}", context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INT, res_.getClassName(context_.getContext()));
        Struct[] o_ = ((ArrayStruct) res_).getInstance();
        assertEq(0, o_.length);
    }
    @Test
    public void processEl99Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$new java.lang.Integer[]{3i,7i}", context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INTEGER, res_.getClassName(context_.getContext()));
        Struct[] o_ = ((ArrayStruct) res_).getInstance();
        assertEq(2, o_.length);
        assertEq(3, ((NumberStruct)o_[0]).intStruct());
        assertEq(7, ((NumberStruct)o_[1]).intStruct());
    }
    @Test
    public void processEl100Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal(" {(1+2)*3+\" hello\"+\" world {every body ;)\"} ", context_, 2);
        assertEq("9 hello world {every body ;)",getString(arg_));
        assertEq(43, context_.getNextIndex());
    }
    @Test
    public void processEl101Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal(" {(\"hello \"+\"world\").length()} ", context_, 2);
        assertEq(11, getNumber(arg_));
        assertEq(30, context_.getNextIndex());
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.Ex).exmeth()", cont_);
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.Ex).exmeth(6i)", cont_);
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$new pkg.Ex()", cont_);
        Struct res_ = arg_.getStruct();
        assertEq("pkg.Ex",res_.getClassName(cont_.getContext()));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$classchoice(pkg.Ex)exmeth(6i)", cont_);
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$classchoice(pkg.Ex)inst", cont_);
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
        Configuration context_ = getConfiguration4(files_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct str_ = context_.getContext().getInit().processInit(context_.getContext(), NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        setStruct(str_,new ClassField("pkg.Ex","inst"), new IntStruct(2));
        lv_.setStruct(str_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        context_.getLastPage().getCatchVars().putAllMap(localVars_);
        Argument arg_ = processElNormal("v.inst", context_);
        assertEq(2, getNumber(arg_));
    }
    @Test
    public void processEl119Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("(1y+2y)*3", context_);
        assertEq(9L, getNumber(arg_));
    }
    @Test
    public void processEl120Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("(1s+2y)*3", context_);
        assertEq(9L, getNumber(arg_));
    }
    @Test
    public void processEl121Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("- -1y", context_);
        assertEq(1, getNumber(arg_));
    }
    @Test
    public void processEl122Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("-1y", context_);
        assertEq(-1, getNumber(arg_));
    }
    @Test
    public void processEl123Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("java.lang.Byte.MAX_VALUE+java.lang.Byte.MAX_VALUE", context_);
        int max_ = Byte.MAX_VALUE+Byte.MAX_VALUE;
        assertEq(max_, getNumber(arg_));
    }
    @Test
    public void processEl123FailTest() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("+1y", context_);
        assertEq(1, getNumber(arg_));
    }
    @Test
    public void processEl124Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("+-1y", context_);
        assertEq(-1, getNumber(arg_));
    }
    @Test
    public void processEl125Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("-.25e0+.5", context_);
        assertEq(0.25d, getDouble(arg_));
    }
    @Test
    public void processEl126Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal(" {(1+2)*3+\" hello\"+\" world {every body ;)\"}{5*8} ", context_, 2);
        assertEq("9 hello world {every body ;)",getString(arg_));
        int nextIndex_ = context_.getNextIndex();
        assertEq(43, nextIndex_);
        arg_ = processElNormal(" {(1+2)*3+\" hello\"+\" world {every body ;)\"}{5*8} ", context_, nextIndex_+1);
        assertEq(40, getNumber(arg_));
        nextIndex_ = context_.getNextIndex();
        assertEq(48, nextIndex_);
    }
    @Test
    public void processEl127Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal(" {(\"hello \"+\"world\").length()}{5*8} ", context_, 2);
        assertEq(11, getNumber(arg_));
        int nextIndex_ = context_.getNextIndex();
        assertEq(30, nextIndex_);
        arg_ = processElNormal(" {(\"hello \"+\"world\").length()}{5*8} ", context_, nextIndex_+1);
        assertEq(40, getNumber(arg_));
        nextIndex_ = context_.getNextIndex();
        assertEq(35, nextIndex_);
    }
    @Test
    public void processEl128Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("1_0+2*3", context_);
        assertEq(16L, getNumber(arg_));
    }
    @Test
    public void processEl129Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$static($math).mod(-8l,3l)", context_);
        assertEq(1L, getNumber(arg_));
    }
    @Test
    public void processEl130Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$static($math).quot(-8l,3l)", context_);
        assertEq(-3L, getNumber(arg_));
    }
    @Test
    public void processEl131Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$new $int[1i][1i]", context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_ARR_INT, res_.getClassName(context_.getContext()));
        assertEq(1, (((ArrayStruct) res_).getInstance()).length);
        assertEq(ARR_INT, (((ArrayStruct) res_).getInstance())[0].getClassName(context_.getContext()));
        assertEq(1, (((ArrayStruct)(((ArrayStruct) res_).getInstance())[0]).getInstance()).length);
        assertEq(0, ((NumberStruct) (((ArrayStruct)(((ArrayStruct) res_).getInstance())[0]).getInstance())[0]).intStruct());
    }
    @Test
    public void processEl132Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$new java.lang.Integer[1i][1i]", context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_ARR_INTEGER, res_.getClassName(context_.getContext()));
        assertEq(1, (((ArrayStruct) res_).getInstance()).length);
        assertEq(ARR_INTEGER, (((ArrayStruct) res_).getInstance())[0].getClassName(context_.getContext()));
        assertSame(NullStruct.NULL_VALUE, (((ArrayStruct)(((ArrayStruct) res_).getInstance())[0]).getInstance())[0]);
    }
    @Test
    public void processEl133Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("($double)(1 + 2) * 3.0", context_);
        assertEq(9L, getNumber(arg_));
    }
    @Test
    public void processEl134Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal(" 2.0 + ($double)$static($math). quot( -8l, 3l) + 3.0", context_);
        assertEq(2L, getNumber(arg_));
    }
    @Test
    public void processEl135Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("1 + 2 ", context_);
        assertEq(3L, getNumber(arg_));
    }
    @Test
    public void processEl136Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("1. + 2. ", context_);
        assertEq(3L, getNumber(arg_));
    }
    @Test
    public void processEl137Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("1.d + 2.d ", context_);
        assertEq(3L, getNumber(arg_));
    }
    @Test
    public void processEl138Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("-.2_5e0+.5", context_);
        assertEq(0.25d, getDouble(arg_));
    }
    @Test
    public void processEl139Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("-.25e0_0+.5", context_);
        assertEq(0.25d, getDouble(arg_));
    }
    @Test
    public void processEl140Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("1_0.d + 2.d ", context_);
        assertEq(12L, getNumber(arg_));
    }
    @Test
    public void processEl141Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("1.05e1", context_);
        assertEq(10.5d, getDouble(arg_));
    }
    @Test
    public void processEl142Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("1.00625e1", context_);
        assertEq(10.0625d, getDouble(arg_));
    }
    @Test
    public void processEl143Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("100.625e-1", context_);
        assertEq(10.0625d, getDouble(arg_));
    }
    @Test
    public void processEl144Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("100.625", context_);
        assertEq(100.625d, getDouble(arg_));
    }
    @Test
    public void processEl145Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("123456789123456789123456789.0", context_);
        assertEq(1.2345678912345678912e26, getDouble(arg_));
    }
    @Test
    public void processEl147Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("123456789123456789123456789.", context_);
        assertEq(1.2345678912345678912e26, getDouble(arg_));
    }
    @Test
    public void processEl148Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("123456789123456789123456789.e-1", context_);
        assertEq(1.2345678912345678912e25, getDouble(arg_));
    }
    @Test
    public void processEl149Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("123456789123456789123456789.e1", context_);
        assertEq(1.2345678912345678912e27, getDouble(arg_));
    }
    @Test
    public void processEl150Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("123456.e1", context_);
        assertEq(1234560, getDouble(arg_));
    }
    @Test
    public void processEl151Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal(".078125e-1", context_);
        assertEq(.078125e-1, getDouble(arg_));
    }
    @Test
    public void processEl152Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("123456789123456789123456789.0e-36", context_);
        assertEq(1.2345678912345678912e-10, getDouble(arg_));
    }
    @Test
    public void processEl153Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("0.0e-36", context_);
        assertEq(0.0, getDouble(arg_));
    }
    @Test
    public void processEl154Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("-0.0e-36", context_);
        assertEq(-0.0, getDouble(arg_));
    }
    @Test
    public void processEl155Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("0.625e-1", context_);
        assertEq(0.0625, getDouble(arg_));
    }
    @Test
    public void processEl156Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal(".625e-1", context_);
        assertEq(0.0625, getDouble(arg_));
    }
    @Test
    public void processEl157Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("0.625e1", context_);
        assertEq(6.25, getDouble(arg_));
    }
    @Test
    public void processEl158Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal(".625e1", context_);
        assertEq(6.25, getDouble(arg_));
    }
    @Test
    public void processEl159Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("0.625e0", context_);
        assertEq(0.625, getDouble(arg_));
    }
    @Test
    public void processEl160Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal(".625e0", context_);
        assertEq(0.625, getDouble(arg_));
    }
    @Test
    public void processEl161Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("-.625e1", context_);
        assertEq(-6.25, getDouble(arg_));
    }
    @Test
    public void processEl162Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("-.6e1", context_);
        assertEq(-6.0, getDouble(arg_));
    }
    @Test
    public void processEl163Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("-.60e1", context_);
        assertEq(-6.0, getDouble(arg_));
    }
    @Test
    public void processEl164Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal(".6e1", context_);
        assertEq(6.0, getDouble(arg_));
    }
    @Test
    public void processEl165Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal(".6e2", context_);
        assertEq(60.0, getDouble(arg_));
    }
    @Test
    public void processEl166Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("123456789123456789123456789.1e1", context_);
        assertEq(1.2345678912345678912e27, getDouble(arg_));
    }
    @Test
    public void processEl167Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("100.e-1", context_);
        assertEq(10.0, getDouble(arg_));
    }
    @Test
    public void processEl168Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("-100.e-1", context_);
        assertEq(-10.0, getDouble(arg_));
    }
    @Test
    public void processEl169Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("-1.e1", context_);
        assertEq(-10.0, getDouble(arg_));
    }
    @Test
    public void processEl170Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("-1.", context_);
        assertEq(-1.0, getDouble(arg_));
    }
    @Test
    public void processEl171Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("1e-123456789123456789123", context_);
        assertEq(0.0, getDouble(arg_));
    }
    @Test
    public void processEl172Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("-1e-123456789123456789123", context_);
        assertEq(-0.0, getDouble(arg_));
    }
    @Test
    public void processEl173Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("1e123456789123456789123", context_);
        assertEq(Double.POSITIVE_INFINITY, getDouble(arg_));
    }
    @Test
    public void processEl174Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("-1e123456789123456789123", context_);
        assertEq(Double.NEGATIVE_INFINITY, getDouble(arg_));
    }
    @Test
    public void processEl175Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("'\\u9FCB'", context_);
        assertEq(40907, getNumber(arg_));
    }
    @Test
    public void processEl176Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("\"\\u9FCB\"", context_);
        assertEq("\u9fcb",getString(arg_));
    }
    @Test
    public void processEl177Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("\"\\u9fcb\"", context_);
        assertEq("\u9fcb",getString(arg_));
    }
    @Test
    public void processEl178Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$static(java.lang.Long) .MAX_VALUE", context_);
        assertEq(Long.MAX_VALUE, getNumber(arg_));
    }

    @Test
    public void processEl331Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(StringExpUtil.getPrettyArrayType(context_.getStandards().getAliasPrimInteger()));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        processElNormal("arg={2}", context_);
        ArrayStruct struct_ = (ArrayStruct)lv_.getStruct();
        assertEq(1,struct_.getInstance().length);
        assertEq(2,((NumberStruct)struct_.getInstance()[0]).intStruct());
    }

    @Test
    public void processEl332Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(2));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg<<2", context_);
        assertEq(8,getNumber(argument_));
    }

    @Test
    public void processEl333Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(8));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg>>2", context_);
        assertEq(2,getNumber(argument_));
    }

    @Test
    public void processEl334Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(2));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg<<<2", context_);
        assertEq(8,getNumber(argument_));
    }

    @Test
    public void processEl335Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(8));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg>>>2", context_);
        assertEq(2,getNumber(argument_));
    }

    @Test
    public void processEl336Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(2));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg<<<<2", context_);
        assertEq(8,getNumber(argument_));
    }

    @Test
    public void processEl337Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(8));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg>>>>2", context_);
        assertEq(2,getNumber(argument_));
    }

    @Test
    public void processEl338Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(8));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg>2", context_);
        assertTrue(argument_.isTrue());
    }

    @Test
    public void processEl339Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(false));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(false));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg&&arg2", context_);
        assertTrue(argument_.isFalse());
    }
    @Test
    public void processEl340Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(true));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(false));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg&&arg2", context_);
        assertTrue(argument_.isFalse());
    }
    @Test
    public void processEl341Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(true));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(true));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg&&arg2", context_);
        assertTrue(argument_.isTrue());
    }

    @Test
    public void processEl342Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(10));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg&3", context_);
        assertEq(2,getNumber(argument_));
    }

    @Test
    public void processEl343Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(8));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg|2", context_);
        assertEq(10,getNumber(argument_));
    }

    @Test
    public void processEl344Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(5));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg^3", context_);
        assertEq(6,getNumber(argument_));
    }

    @Test
    public void processEl345Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(true));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(true));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg||arg2", context_);
        assertTrue(argument_.isTrue());
    }
    @Test
    public void processEl346Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(false));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(true));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg||arg2", context_);
        assertTrue(argument_.isTrue());
    }
    @Test
    public void processEl347Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(false));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(false));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg||arg2", context_);
        assertTrue(argument_.isFalse());
    }

    @Test
    public void processEl348Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasString());
        lv_.setStruct(new StringStruct("8"));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg>\"2\"", context_);
        assertTrue(argument_.isTrue());
    }

    @Test
    public void processEl349Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(5));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("~arg", context_);
        assertEq(-6,getNumber(argument_));
    }

    @Test
    public void processEl350Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(5));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("+arg", context_);
        assertEq(5,getNumber(argument_));
    }

    @Test
    public void processEl351Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(true));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("!arg", context_);
        assertTrue(argument_.isFalse());
    }

    @Test
    public void processEl352Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(false));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("!arg", context_);
        assertTrue(argument_.isTrue());
    }

    @Test
    public void processEl353Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(false));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("$bool(arg,5,6)", context_);
        assertEq(6,getNumber(argument_));
    }
    @Test
    public void processEl354Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(true));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("$bool(arg,5,6)", context_);
        assertEq(5,getNumber(argument_));
    }

    @Test
    public void processEl355Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(5));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg%3", context_);
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
        Configuration context_ = getConfiguration4(files_);
        addImportingPage(context_);
        Struct str_ = context_.getContext().getInit().processInit(context_.getContext(), NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        setStruct(str_,new ClassField("pkg.Ex","inst"), new IntStruct(2));
        context_.getLastPage().setGlobalArgumentStruct(str_,context_);
        Argument arg_ = processElNormal("$this.inst", context_);
        assertEq(2, getNumber(arg_));
    }

    @Test
    public void processEl357Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(false));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(0));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("$bool(arg,5+arg2,6+arg2)", context_);
        assertEq(6,getNumber(argument_));
    }
    @Test
    public void processEl358Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(true));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(0));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("$bool(arg,5+arg2,6+arg2)", context_);
        assertEq(5,getNumber(argument_));
    }
    @Test
    public void processEl500Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(false));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(false));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg&&=1/0>1", context_);
        assertTrue(argument_.isFalse());
    }
    @Test
    public void processEl500_Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(false));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(false));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg??=1/0>1", context_);
        assertTrue(argument_.isFalse());
    }
    @Test
    public void processEl501_Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasBoolean());
        lv_.setStruct(NullStruct.NULL_VALUE);
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasBoolean());
        lv_.setStruct(BooleanStruct.of(false));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg??=arg2", context_);
        assertTrue(argument_.isFalse());
    }
    @Test
    public void processEl500__Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(false));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(false));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg??arg2", context_);
        assertTrue(argument_.isFalse());
    }
    @Test
    public void processEl501__Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasBoolean());
        lv_.setStruct(NullStruct.NULL_VALUE);
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasBoolean());
        lv_.setStruct(BooleanStruct.of(false));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg??arg2", context_);
        assertTrue(argument_.isFalse());
    }
    @Test
    public void processEl501Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(true));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(false));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg||=1/0>1", context_);
        assertTrue(argument_.isTrue());
    }
    @Test
    public void processEl502Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(true));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(false));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg&&=arg2", context_);
        assertTrue(argument_.isFalse());
    }
    @Test
    public void processEl503Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(false));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(true));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg||=arg2", context_);
        assertTrue(argument_.isTrue());
    }
    @Test
    public void processEl198Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$(java.lang.Number)5", context_);
        assertEq(5L, getNumber(arg_));
    }
    @Test
    public void processEl199Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$($byte)5", context_);
        assertEq(5L, getNumber(arg_));
    }
    @Test
    public void processEl201Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$(java.lang.Byte)$null", context_);
        assertTrue(context_.isEmptyErrors());
        assertNull(getException(context_));
        assertSame(NullStruct.NULL_VALUE, arg_.getStruct());
    }
    @Test
    public void processEl203Test() {
        Configuration conf_ = getConfiguration4();
        addImportingPage(conf_);
        String el_ = "1!=2!=3";
        Argument arg_ = processElNormal(el_, conf_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl210Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("'1'+'2'", context_);
        assertEq(99, getNumber(arg_));
    }
    @Test
    public void processEl211Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("\"\"+$new $char[]{'1','2'}[0]+$new $char[]{'1','2'}[1]", context_);
        assertEq("12",getString(arg_));
    }
    @Test
    public void processEl212Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("('1'+'2')*3i", context_);
        assertEq(297, getNumber(arg_));
    }
    @Test
    public void processEl213Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("'1'>1i", context_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl214Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("'1'<1i", context_);
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl215Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("'1'<1i", context_);
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl216Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("'1'>1i", context_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl217Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("'1'==49i", context_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl218Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("49i=='1'", context_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl213FailTest() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("('1'+'2')*'3'", context_);
        assertEq(5049, getNumber(arg_));
    }
    @Test
    public void processEl219Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("6 + $($int) - $static($math).quot(8,5) - 2", context_);
        assertEq(3, getNumber(arg_));
    }
    @Test
    public void processEl220Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$class($int).getName()", context_);
        assertEq("$int",getString(arg_));
    }
    @Test
    public void processEl221Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$class(java.lang.Integer).getName()", context_);
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$class(pkg.Ex).getName()", cont_);
        assertEq("pkg.Ex",getString(arg_));
    }
    @Test
    public void processEl223Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$class(pkg.Ex).getName()", cont_);
        assertEq("pkg.Ex",getString(arg_));
    }
    @Test
    public void processEl224Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return $class(#T).getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$new pkg.Ex<java.lang.Integer>().exmeth()", cont_);
        assertEq("java.lang.Integer",getString(arg_));
    }
    @Test
    public void processEl225Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<#T>).getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$new pkg.Ex<java.lang.Integer>().exmeth()", cont_);
        assertEq("pkg.Ex<java.lang.Integer>",getString(arg_));
    }
    @Test
    public void processEl226Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$static($Class).getClass(\"\").getName()", context_);
        assertEq("java.lang.String",getString(arg_));
    }
    @Test
    public void processEl227Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$static($Class).getClass(1i).getName()", context_);
        assertEq("java.lang.Integer",getString(arg_));
    }
    @Test
    public void processEl228Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$static($Class).getClass($null)", context_);
        assertTrue(arg_.isNull());
        assertNull(getException(context_));
    }
    @Test
    public void processEl229Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<#T>).getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static($Class).getClass($new pkg.Ex<java.lang.Integer>()).getName()", cont_);
        assertEq("pkg.Ex<java.lang.Integer>",getString(arg_));
    }
    @Test
    public void processEl230Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$class($int[]).getName()", context_);
        assertEq("[$int",getString(arg_));
    }
    @Test
    public void processEl231Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$class(java.lang.Integer[]).getName()", context_);
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$class(pkg.Ex[]).getName()", cont_);
        assertEq("[pkg.Ex",getString(arg_));
    }
    @Test
    public void processEl233Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$class(pkg.Ex[]).getName()", cont_);
        assertEq("[pkg.Ex",getString(arg_));
    }
    @Test
    public void processEl234Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$class(pkg.Ex<java.lang.Integer>[]).getName()", cont_);
        assertEq("[pkg.Ex<java.lang.Integer>",getString(arg_));
    }
    @Test
    public void processEl235Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return $class(#T[]).getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$new pkg.Ex<java.lang.Integer>().exmeth()", cont_);
        assertEq("[java.lang.Integer",getString(arg_));
    }
    @Test
    public void processEl236Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<#T[]>).getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$new pkg.Ex<java.lang.Integer>().exmeth()", cont_);
        assertEq("pkg.Ex<[java.lang.Integer>",getString(arg_));
    }
    @Test
    public void processEl237Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$static($Class).getClass($new $int[]{1i}).getName()", context_);
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static($Class).forName(\"pkg.Ex\",$true).getName()", cont_);
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq("pkg.Ex",getString(arg_));
        assertEq(14, ((NumberStruct)cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "inst"))).intStruct());
    }
    @Test
    public void processEl240Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$class($void).getName()", context_);
        assertEq("$void",getString(arg_));
    }
    @Test
    public void processEl241Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$static($Class).forName(\"$void\",$true).getName()", context_);
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(14, getNumber(arg_));
    }
    @Test
    public void processEl251Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$class($math).getDeclaredMethods(\"mod\",$true,$false,$class($int),$class($int))[0i].invoke($null,4i,3i)", context_);
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(15, getNumber(arg_));
        NumberStruct res_ = (NumberStruct) cont_.getContext().getClasses().getStaticField(new ClassField("pkg.Ex", "inst"));
        assertEq(14, res_.intStruct());
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(19, getNumber(arg_));
    }
    @Test
    public void processEl257Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $normal $int exmeth(#T e){\n");
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(14, getNumber(arg_));
    }
    @Test
    public void processEl258Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach(#T i:e){\n");
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(19, getNumber(arg_));
    }
    @Test
    public void processEl259Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(#T i:e){\n");
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(9, getNumber(arg_));
    }
    @Test
    public void processEl260Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(#T i:e){\n");
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(19, getNumber(arg_));
    }
    @Test
    public void processEl261Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst=6i;\n");
        xml_.append(" $public $static $void set($int i){\n");
        xml_.append("  inst+=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(#T i:e){\n");
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        assertTrue(arg_.isNull());
        assertNull(getException(cont_));
        assertEq(10, ((NumberStruct)cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "inst"))).intStruct());
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(22, getNumber(arg_));
    }
    @Test
    public void processEl269Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public (#T e){\n");
        xml_.append("  inst=$($int)e;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach(#T i:e){\n");
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(28, getNumber(arg_));
    }
    @Test
    public void processEl270Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public (#T... e){\n");
        xml_.append("  $foreach(#T i:e){\n");
        xml_.append("   inst+=$($int)i;;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach(#T i:e){\n");
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(32, getNumber(arg_));
    }
    @Test
    public void processEl271Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public (#T... e){\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(#T i:e){\n");
        xml_.append("    inst+=$($int)i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach(#T i:e){\n");
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(19, getNumber(arg_));
    }

    @Test
    public void processEl275Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(#T i:e){\n");
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(19, getNumber(arg_));
    }
    @Test
    public void processEl276Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(#T i:e){\n");
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(19, getNumber(arg_));
    }
    @Test
    public void processEl277Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$static($Class).getAllClasses().length > 10", context_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl278Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $final $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst=15i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(#T i:e){\n");
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq("hello", getString(arg_));
    }
    @Test
    public void processEl286Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static java.lang.Integer inst=15i;\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(#T i:e){\n");
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        assertTrue(arg_.isNull());
        assertNull(getException(cont_));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ =processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ =processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(arg_.isNull());
        assertTrue(cont_.isEmptyErrors());
        assertNull(getException(cont_));
    }
    @Test
    public void processEl293Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public #T inst;\n");
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ =processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(16, getNumber(arg_));
    }
    @Test
    public void processEl299Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> : pkg.ExAbs {\n");
        xml_.append(" $public $static $final $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst=$static(pkg.ExAbs).sup;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(#T i:e){\n");
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(15, getNumber(arg_));
    }
    @Test
    public void processEl300Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> : pkg.ExAbs {\n");
        xml_.append(" $public $static $final $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst=$static(pkg.ExAbs).sup;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(#T i:e){\n");
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(15, getNumber(arg_));
    }
    @Test
    public void processEl301Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> : pkg.ExAbs {\n");
        xml_.append(" $public $static $final $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst=sup;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(#T i:e){\n");
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(15, getNumber(arg_));
    }
    @Test
    public void processEl302Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> : pkg.ExAbs {\n");
        xml_.append(" $public $static $final $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst=$static(pkg.ExAbs).sup;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(#T i:e){\n");
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(15, getNumber(arg_));
    }
    @Test
    public void processEl307Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$class(java.lang.String[]).getDeclaredMethods(\"clone\",$false,$false)[0i].invoke($new java.lang.Number[]{})", context_);
        assertTrue(context_.isEmptyErrors());
        assertTrue(arg_.getStruct() instanceof ArrayStruct);
        assertEq("[java.lang.Number",arg_.getStruct().getClassName(context_.getContext()));
        assertEq(0,(((ArrayStruct)arg_.getStruct()).getInstance()).length);
    }
    @Test
    public void processEl308Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$class(java.lang.String[]).getDeclaredMethods(\"clone\",$false,$false)[0i].invoke($new java.lang.String[]{})", context_);
        assertTrue(context_.isEmptyErrors());
        assertTrue(arg_.getStruct() instanceof ArrayStruct);
        assertEq("[java.lang.String",arg_.getStruct().getClassName(context_.getContext()));
        assertEq(0,(((ArrayStruct)arg_.getStruct()).getInstance()).length);
    }
    @Test
    public void processEl309Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$class(java.lang.String[]).getDeclaredMethods(\"clone\",$false,$false)[0i].invoke($new java.lang.String[]{\"sample\"})", context_);
        assertTrue(context_.isEmptyErrors());
        assertTrue(arg_.getStruct() instanceof ArrayStruct);
        assertEq("[java.lang.String",arg_.getStruct().getClassName(context_.getContext()));
        assertEq(1,(((ArrayStruct)arg_.getStruct()).getInstance()).length);
        assertEq("java.lang.String",(((ArrayStruct)arg_.getStruct()).getInstance())[0].getClassName(context_.getContext()));
        assertEq("sample",((StringStruct)(((ArrayStruct)arg_.getStruct()).getInstance())[0]).getInstance());
    }
    @Test
    public void processEl310Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$class(java.lang.Object[]).getDeclaredMethods(\"clone\",$false,$false)[0i].invoke($new java.lang.String[]{\"sample\"})", context_);
        assertTrue(context_.isEmptyErrors());
        assertTrue(arg_.getStruct() instanceof ArrayStruct);
        assertEq("[java.lang.String",arg_.getStruct().getClassName(context_.getContext()));
        assertEq(1,(((ArrayStruct)arg_.getStruct()).getInstance()).length);
        assertEq("java.lang.String",(((ArrayStruct)arg_.getStruct()).getInstance())[0].getClassName(context_.getContext()));
        assertEq("sample",((StringStruct)(((ArrayStruct)arg_.getStruct()).getInstance())[0]).getInstance());
    }
    @Test
    public void processEl311Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal("$math.abs(-8l)", context_);
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("pkg.Ex.exmeth()", cont_);
        assertEq(9, getNumber(arg_));
    }
    @Test
    public void processEl317Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        Argument arg_ = processElNormal(" {(1+2)*3+\" hello\"+\" world {every body ;)\\\\\\\"\"+$new $int[]{0i,1i}.length} ", context_, 2);
        assertEq("9 hello world {every body ;)\\\"2",getString(arg_));
        assertEq(73, context_.getNextIndex());
    }
    @Test
    public void processEl318Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth($null);\n");
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq("[pkg.Ex",getString(arg_));
        assertTrue(!cont_.getClasses().isInitialized("pkg.Ex"));
    }
    @Test
    public void processEl319Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth($null);\n");
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq("[pkg.Ex",getString(arg_));
        assertTrue(!cont_.getClasses().isInitialized("pkg.Ex"));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$static(pkg.ExTwo).exmeth()+pkg.Ex.inst", cont_);
        assertEq(6,getNumber(arg_));
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isSuccessfulInitialized("pkg.Ex"));
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
        Configuration cont_ = getConfiguration4(files_,false);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$new{} pkg.Ex().inst", cont_);
        assertEq(1,getNumber(arg_));
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isSuccessfulInitialized("pkg.Ex"));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$new pkg.Ex().$new Inner().inst", cont_);
        assertEq(5,getNumber(arg_));
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isSuccessfulInitialized("pkg.Ex"));
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
        Configuration conf_ = getConfiguration4(files_,false);
        addImportingPage(conf_);
        Argument arg_ = processElNormal("$new{} pkg.Ex().inst", conf_);
        assertEq(1,getNumber(arg_));
        assertTrue(conf_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(conf_.getClasses().isSuccessfulInitialized("pkg.Ex"));
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
        Configuration conf_ = getConfiguration4(files_,false);
        addImportingPage(conf_);
        Argument arg_ = processElNormal("$new{} pkg.Ex(5).inst", conf_);
        assertEq(5,getNumber(arg_));
        assertTrue(conf_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(conf_.getClasses().isSuccessfulInitialized("pkg.Ex"));
    }
    @Test
    public void processEl361Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LoopVariable> localVars_ = new StringMap<LoopVariable>();
        LoopVariable lv_ = new LoopVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setIndexClassName(context_.getStandards().getAliasPrimLong());
        lv_.setStruct(BooleanStruct.of(true));
        lv_.setIndex(5);
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setVars(localVars_);
        Argument argument_ = processElNormal("([arg])", context_);
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("pkg.ExTwo.exmeth($vararg($int),4,$firstopt(8))", cont_);
        assertEq(12,getNumber(arg_));
    }
    @Test
    public void processEl365Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(true));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(0));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("($boolean)arg", context_);
        assertTrue(argument_.isTrue());
    }
    @Test
    public void processEl366Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(new StringStruct("str"));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(0));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("(String)arg", context_);
        assertEq("str",getString(argument_));
    }
    @Test
    public void processEl369Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("1 $instanceof pkg.ExTwo", cont_);
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl370Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$new pkg.ExTwo<java.lang.Object>() $instanceof pkg.ExTwo", cont_);
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl371Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasString());
        lv_.setStruct(new StringStruct("8"));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg!=\"2\"", context_);
        assertTrue(argument_.isTrue());
    }

    @Test
    public void processEl372Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasString());
        lv_.setStruct(new StringStruct("8"));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg==\"2\"", context_);
        assertTrue(argument_.isFalse());
    }

    @Test
    public void processEl373Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasString());
        lv_.setStruct(new StringStruct("8"));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg!=\"8\"", context_);
        assertTrue(argument_.isFalse());
    }

    @Test
    public void processEl374Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasString());
        lv_.setStruct(new StringStruct("8"));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("arg==\"8\"", context_);
        assertTrue(argument_.isTrue());
    }

    @Test
    public void processEl376Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] i_ = new Struct[1];
        i_[0] = new IntStruct(8);
        lv_.setStruct(new ArrayStruct(i_,ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = processElNormal("v.clone()", context_);
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("pkg.ExTwo.exmeth($id(pkg.ExTwo,$static,$int,$int...),4,8)", cont_);
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("pkg.ExTwo.exmeth($id(pkg.ExTwo,$static,$int,$int),4,8)", cont_);
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
        Configuration context_ = getConfiguration4(files_);
        addImportingPage(context_);
        Struct str_ = context_.getContext().getInit().processInit(context_.getContext(), NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        setStruct(str_,new ClassField("pkg.Ex","inst"), new IntStruct(2));
        context_.getLastPage().setGlobalArgumentStruct(str_,context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(0));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processElNormal("(v)=$this.inst", context_);
        assertEq(context_.getStandards().getAliasPrimInteger(), lv_.getClassName());
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
        Configuration context_ = getConfiguration4(files_);
        addImportingPage(context_);
        Struct str_ = context_.getContext().getInit().processInit(context_.getContext(), NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        context_.getLastPage().setGlobalArgumentStruct(str_,context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(2));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processElNormal("$this.inst=(v)", context_);
        assertEq(context_.getStandards().getAliasPrimInteger(), lv_.getClassName());
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument arg_ = processElNormal("++pkg.Ex.inst", conf_);
        assertEq(2,getNumber(arg_));
        assertTrue(conf_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(conf_.getClasses().isSuccessfulInitialized("pkg.Ex"));
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument arg_ = processElNormal("pkg.Ex.inst++", conf_);
        assertEq(1,getNumber(arg_));
        assertTrue(conf_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(conf_.getClasses().isSuccessfulInitialized("pkg.Ex"));
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument arg_ = processElNormal("pkg.Ex.inst+=5", conf_);
        assertEq(6,getNumber(arg_));
        assertTrue(conf_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(conf_.getClasses().isSuccessfulInitialized("pkg.Ex"));
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
        Configuration conf_ = getConfiguration4(files_,false);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("$new{} pkg.Ex(5).inst=10", conf_);
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
        Configuration conf_ = getConfiguration4(files_,false);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("pkg.Ex.inst=10", conf_);
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
        Configuration conf_ = getConfiguration4(files_,false);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("$new{} pkg.Ex(52).$that.res(8)", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("pkg.Ex.res(8)", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("$new pkg.Ex(52).$classchoice(pkg.Ex)res(8)", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("pkg.Ex.$classchoice(pkg.Ex)res(8)", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("$new pkg.Ex(52).$superaccess(pkg.Ex)res(8)", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("pkg.Ex.$superaccess(pkg.Ex)res(8)", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("$values(pkg.ExTwo).length", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("$valueOf(pkg.ExTwo,\"ONE\").myval", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("$valueOf(pkg.ExTwo,\"TWO\").myval", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("$values(pkg.ExTwo).length", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("$valueOf(pkg.ExTwo,\"ONE\").myval", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("$valueOf(pkg.ExTwo,\"TWO\").myval", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("$lambda(pkg.Ex,,inst,$int).call(4)", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("$lambda(java.lang.String,length).call(\"mystr\")", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("((java.lang.String)$lambda(java.lang.String,$new,$char...).call($new $char[]{'m','y','s','t','r'})).length()", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("\"mystr\".$lambda(java.lang.String,length).call()", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("pkg.Ex.inst.$lambda(java.lang.String,length).call()", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("$lambda(pkg.Ex,,inst,java.lang.String).call(\"mystr\")", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("($new pkg.Ex(6)+$new pkg.Ex(8)).inst", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("$operator(+)($new pkg.Ex(6),$new pkg.Ex(8)).inst", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("$operator(+,pkg.Ex)($new pkg.Ex(6),$new pkg.Ex(8)).inst", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("($(pkg.Int)($static().$lambda($math,plus,$int,$int),$interfaces(pkg.Int)())).pl(6,8)", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("($(pkg.Int)($static().$lambda($math,plus,$int,$int),$interfaces(pkg.Int2)(),$interfaces(pkg.Int)())).pl(6,8)", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("$($int)(1??2)", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("$($int)($null??2)", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("\"\"?.length()", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("($new pkg.Cl().str)?.length()", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("$new pkg.Cl()?.str=\"hello\"", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("$new pkg.ClOwner()?.owner?.str=\"hello\"", conf_);
        assertNull(getException(conf_));
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("$new pkg.Cl()?.str+=\"hello\"", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("$new pkg.ClOwner()?.owner?.str+=\"hello\"", conf_);
        assertNull(getException(conf_));
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("$new pkg.Cl()?.str++", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("++$new pkg.Cl()?.str", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("$new pkg.ClOwner()?.owner?.str++", conf_);
        assertNull(getException(conf_));
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("++$new pkg.ClOwner()?.owner?.str", conf_);
        assertNull(getException(conf_));
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("(pkg.Ex.res+=$new pkg.Ex(8)).inst", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("(pkg.Ex.res++).inst", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("(++pkg.Ex.res).inst", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("(pkg.Ex.res[0]+=$new pkg.Ex(8)).inst", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("(pkg.Ex.res[0]++).inst", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("(++pkg.Ex.res[0]).inst", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("(pkg.Ex.res.res[0]+=$new pkg.Ex(8)).inst", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("(pkg.Ex.res.res[0]++).inst", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("(++pkg.Ex.res.res[0]).inst", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("(pkg.Ex.res.res+=$new pkg.Ex(8)).inst", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("(pkg.Ex.res.res++).inst", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument argument_ = processElNormal("(++pkg.Ex.res.res).inst", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        setStruct(value_,new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("(v+=$new pkg.Ex(8)).inst", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        setStruct(value_,new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("(v++).inst", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        setStruct(value_,new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("(++v).inst", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        setStruct(value_,new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("$new pkg.Ex(5)[0]", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        setStruct(value_,new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("$new pkg.Ex(5)[0]=15", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        setStruct(value_,new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("$new pkg.Ex(5)[0]+=15", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        setStruct(value_,new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("++$new pkg.Ex(5)[0]", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        setStruct(value_,new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("$new pkg.Ex(5)[0]++", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        setStruct(value_,new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("$new pkg.Ex(5)[0].myval", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        setStruct(value_,new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("($new pkg.Ex(5)[0]=$new pkg.ExTwo(15)).myval", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        setStruct(value_,new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("($new pkg.Ex(5)[0]+=$new pkg.ExTwo(15)).myval", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        setStruct(value_,new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("(++$new pkg.Ex(5)[0]).myval", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        setStruct(value_,new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("($new pkg.Ex(5)[0]++).myval", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        setStruct(value_,new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processElNormal("$new pkg.ExSub(5).$super[0].myval", conf_);
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("pkg.Apply.test()", cont_);
        assertEq("2,4",getString(arg_));
    }
    @Test
    public void processEl452Test() {
        StringMap<String> files_ = new StringMap<String>();
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument argument_ = processElNormal("$defaultValue($char)", cont_);
        assertEq(0,getChar(argument_));
    }
    @Test
    public void processEl454Test() {
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
        Argument argument_ = processElNormal("$new pkg.Ex<$int>().res($id(pkg.Ex,#T),15)", conf_);
        assertEq(29,getNumber(argument_));
    }
    @Test
    public void processEl460Test() {
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
        Argument argument_ = processElNormal("$new pkg.Outer.Ex<$int>().res($id(pkg.Outer.Ex,#T),15)", conf_);
        assertEq(29,getNumber(argument_));
    }
    @Test
    public void processEl463Test() {
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
        Argument argument_ = processElNormal("$new pkg.Outer.Ex<$int>().res($id(pkg.Outer.Ex,#T),15)", conf_);
        assertEq(29,getNumber(argument_));
    }
    @Test
    public void processEl465Test() {
        StringMap<String> files_ = new StringMap<String>();
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument arg_ = processElNormal("explicit($int)5", conf_);
        assertTrue(conf_.isEmptyErrors());
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument arg_ = processElNormal("explicit(pkg.Ex)5", conf_);
        assertTrue(conf_.isEmptyErrors());
        Struct struct_ = arg_.getStruct();
        assertEq("pkg.Ex", struct_.getClassName(conf_.getContext()));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$staticCall(pkg.Ex).exmeth()", cont_);
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("((pkg.Interface) $static($math).$lambda($math,plus,$int,$int)).opTwo(1,2)", cont_);
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("$staticCall(pkg.Ex<$int>).exmeth()", cont_);
        assertEq(9, getNumber(arg_));
    }
    @Test
    public void processEl471Test() {
        StringMap<String> files_ = new StringMap<String>();
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument argument_ = processElNormal("$default(0)", cont_);
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Argument arg_ = processElNormal("pkg.ExTwo.exmeth($vararg($int),4,$firstopt($null))", cont_);
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        Argument arg_ = processElNormal("$(pkg.Ex,$int)5", conf_);
        assertTrue(conf_.isEmptyErrors());
        Struct struct_ = arg_.getStruct();
        assertEq("pkg.Ex", struct_.getClassName(conf_.getContext()));
        assertEq(5, ((IntStruct)getStruct(struct_,new ClassField("pkg.Ex","field"))).intStruct());

    }
    @Test
    public void procesAffect00Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(0));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processElNormal("(v)=1i", context_);
        assertEq(context_.getStandards().getAliasPrimInteger(), lv_.getClassName());
        assertEq(1, ((NumberStruct)lv_.getStruct()).intStruct());
    }
    @Test
    public void processAffect1Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(0));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processElNormal("v=1i", context_);
        assertEq(context_.getStandards().getAliasPrimInteger(), lv_.getClassName());
        assertEq(1, ((NumberStruct)lv_.getStruct()).intStruct());
    }

    @Test
    public void processAffect3Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(0);
        lv_.setStruct(new ArrayStruct(in_, ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processElNormal("v[0i]=12i", context_);
        assertEq(12, ((NumberStruct) in_[0]).intStruct());
    }
    @Test
    public void processAffect4Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(0);
        Struct[] elt_ = new Struct[1];
        elt_[0] = new IntStruct(0);
        in_[0] = new ArrayStruct(elt_, ARR_INT);
        lv_.setStruct(new ArrayStruct(in_,ARR_ARR_INT));
        lv_.setClassName(ARR_ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processElNormal("v[0i][0i]=12i", context_);
        assertEq(12, ((NumberStruct)(((ArrayStruct)in_[0]).getInstance())[0]).intStruct());
    }
    @Test
    public void processAffect5Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(1));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processElNormal("v+=1i", context_);
        assertEq(context_.getStandards().getAliasPrimInteger(), lv_.getClassName());
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        ContextEl context_ = cont_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct var_ = context_.getInit().processInit(context_, NullStruct.NULL_VALUE, "pkg.Composite", "", -1);
        lv_.setStruct(var_);
        lv_.setClassName("pkg.Composite");
        localVars_.put("v", lv_);
        cont_.getLastPage().setLocalVars(localVars_);
        Argument res_ = processElNormal("v.integer-=12i", cont_);
        assertEq(-12, ((NumberStruct)getStruct(var_,new ClassField("pkg.Composite","integer"))).intStruct());
        assertEq(-12, getNumber(res_));
    }
    @Test
    public void processAffect7Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(0);
        lv_.setStruct(new ArrayStruct(in_, ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument res_ = processElNormal("v[0i]-=12i", context_);
        assertEq(-12, ((NumberStruct) in_[0]).intStruct());
        assertEq(-12, getNumber(res_));
    }
    @Test
    public void processAffect8Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(0);
        Struct[] elt_ = new Struct[1];
        elt_[0] = new IntStruct(0);
        in_[0] = new ArrayStruct(elt_, ARR_INT);
        lv_.setStruct(new ArrayStruct(in_,ARR_ARR_INT));
        lv_.setClassName(ARR_ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument res_ = processElNormal("v[0i][0i]-=12i", context_);
        assertEq(-12, ((NumberStruct)(((ArrayStruct)in_[0]).getInstance())[0]).intStruct());
        assertEq(-12, getNumber(res_));
    }
    @Test
    public void processAffect9Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(0);
        Struct[] elt_ = new Struct[1];
        elt_[0] = new IntStruct(0);
        in_[0] = new ArrayStruct(elt_, ARR_INT);
        lv_.setStruct(new ArrayStruct(in_,ARR_ARR_INT));
        lv_.setClassName(ARR_ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument res_ = processElNormal("v[0i][0i]++", context_);
        assertEq(1, ((NumberStruct)(((ArrayStruct)in_[0]).getInstance())[0]).intStruct());
        assertEq(0, getNumber(res_));
    }
    @Test
    public void processAffect10Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(0);
        Struct[] elt_ = new Struct[1];
        elt_[0] = new IntStruct(0);
        in_[0] = new ArrayStruct(elt_, ARR_INT);
        lv_.setStruct(new ArrayStruct(in_,ARR_ARR_INT));
        lv_.setClassName(ARR_ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument res_ = processElNormal("v[0i][0i]--", context_);
        assertEq(-1, ((NumberStruct)(((ArrayStruct)in_[0]).getInstance())[0]).intStruct());
        assertEq(0, getNumber(res_));
    }
    @Test
    public void processAffect11Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(0);
        Struct[] elt_ = new Struct[1];
        elt_[0] = new IntStruct(0);
        in_[0] = new ArrayStruct(elt_, ARR_INT);
        lv_.setStruct(new ArrayStruct(in_,ARR_ARR_INT));
        lv_.setClassName(ARR_ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument res_ = processElNormal("++v[0i][0i]", context_);
        assertEq(1, ((NumberStruct)(((ArrayStruct)in_[0]).getInstance())[0]).intStruct());
        assertEq(1, getNumber(res_));
    }
    @Test
    public void processAffect12Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(0);
        Struct[] elt_ = new Struct[1];
        elt_[0] = new IntStruct(0);
        in_[0] = new ArrayStruct(elt_, ARR_INT);
        lv_.setStruct(new ArrayStruct(in_,ARR_ARR_INT));
        lv_.setClassName(ARR_ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument res_ = processElNormal("--v[0i][0i]", context_);
        assertEq(-1, ((NumberStruct)(((ArrayStruct)in_[0]).getInstance())[0]).intStruct());
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(null);
        lv_.setClassName(cont_.getStandards().getAliasInteger());
        localVars_.put("v", lv_);
        cont_.getLastPage().setLocalVars(localVars_);
        NumberStruct arg_;
        processElNormal("$classchoice(pkg.Ex)inst=2i", cont_);
        arg_ = (NumberStruct) cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "inst"));
        assertEq(2, arg_.intStruct());
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(NullStruct.NULL_VALUE);
        lv_.setClassName(cont_.getStandards().getAliasInteger());
        localVars_.put("v", lv_);
        cont_.getLastPage().setLocalVars(localVars_);
        Struct arg_;
        processElNormal("$classchoice(pkg.Ex)inst=2i", cont_);
        processElNormal("$classchoice(pkg.Ex)inst=v", cont_);
        arg_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "inst"));
        assertSame(NullStruct.NULL_VALUE,arg_);
    }
    @Test
    public void processAffect23Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("add "));
        lv_.setClassName(context_.getStandards().getAliasString());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processElNormal("v+=1i", context_);
        assertEq(context_.getStandards().getAliasString(), lv_.getClassName());
        assertEq("add 1", ((StringStruct)lv_.getStruct()).getInstance());
    }
    @Test
    public void processAffect24Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] arr_ = new Struct[1];
        arr_[0] = new StringStruct("add ");
        String arrayType_ = StringExpUtil.getPrettyArrayType(context_.getStandards().getAliasString());
        lv_.setStruct(new ArrayStruct(arr_, arrayType_));
        lv_.setClassName(arrayType_);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processElNormal("v[0i]+=1i", context_);
        assertEq(arrayType_, lv_.getClassName());
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        Struct arg_;
        Struct[] res_;
        processElNormal("$static(pkg.Ex).exmeth()[0i]=2i", cont_);
        arg_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "inst"));
        res_ = ((ArrayStruct) arg_).getInstance();
        assertEq(1,res_.length);
        assertEq(2,((NumberStruct)res_[0]).intStruct());
    }
    @Test
    public void processAffect26Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(BooleanStruct.of(true));
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processElNormal("v&=$false", context_);
        assertEq(context_.getStandards().getAliasPrimBoolean(), lv_.getClassName());
        assertTrue(BooleanStruct.isFalse(lv_.getStruct()));
    }
    @Test
    public void processAffect27Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(BooleanStruct.of(false));
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processElNormal("v|=$true", context_);
        assertEq(context_.getStandards().getAliasPrimBoolean(), lv_.getClassName());
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        ContextEl context_ = cont_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct var_ = context_.getInit().processInit(context_, NullStruct.NULL_VALUE, "pkg.Composite", "", -1);
        lv_.setStruct(var_);
        lv_.setClassName("pkg.Composite");
        localVars_.put("v", lv_);
        cont_.getLastPage().setLocalVars(localVars_);
        Argument res_ = processElNormal("v.integer++", cont_);
        assertEq("pkg.Composite", lv_.getClassName());
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        ContextEl context_ = cont_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct var_ = context_.getInit().processInit(context_, NullStruct.NULL_VALUE, "pkg.Composite", "", -1);
        lv_.setStruct(var_);
        lv_.setClassName("pkg.Composite");
        localVars_.put("v", lv_);
        cont_.getLastPage().setLocalVars(localVars_);
        Argument res_ = processElNormal("++v.integer", cont_);
        assertEq("pkg.Composite", lv_.getClassName());
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        ContextEl context_ = cont_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct var_ = context_.getInit().processInit(context_, NullStruct.NULL_VALUE, "pkg.Composite", "", -1);
        lv_.setStruct(var_);
        lv_.setClassName("pkg.Composite");
        localVars_.put("v", lv_);
        cont_.getLastPage().setLocalVars(localVars_);
        Argument res_ = processElNormal("(v.integer-=12i)", cont_);
        assertEq(-12, ((NumberStruct)getStruct(var_,new ClassField("pkg.Composite","integer"))).intStruct());
        assertEq(-12, getNumber(res_));
    }
    @Test
    public void processAffect31Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(0);
        Struct[] elt_ = new Struct[1];
        elt_[0] = new IntStruct(0);
        in_[0] = new ArrayStruct(elt_, ARR_INT);
        lv_.setStruct(new ArrayStruct(in_,ARR_ARR_INT));
        lv_.setClassName(ARR_ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processElNormal("(v[0i][0i])=12i", context_);
        assertEq(12, ((NumberStruct)(((ArrayStruct)in_[0]).getInstance())[0]).intStruct());
    }

    @Test
    public void processEl179Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        context_.setupAnalyzing();
        ContextEl ctx_ = context_.getContext();
        String elr_ = "v+=1i";
        Delimiters d_ = checkSyntax(ctx_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getPageEl().getGlobalArgument();
        boolean static_ = setupStaticCtx(ctx_, argGl_);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, context_);
        assertTrue(context_.isEmptyErrors());
        ctx_.setNullAnalyzing();
        calculate(all_, context_);
        assertEq(4, ((NumberStruct)lv_.getStruct()).intStruct());
    }
    @Test
    public void processEl180Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(BooleanStruct.of(true));
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v&=$false";
        Delimiters d_ = checkSyntax(ctx_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getPageEl().getGlobalArgument();
        boolean static_ = setupStaticCtx(ctx_, argGl_);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, context_);
        assertTrue(context_.isEmptyErrors());
        ctx_.setNullAnalyzing();
        calculate(all_, context_);
        assertTrue(BooleanStruct.isFalse(lv_.getStruct()));
    }
    @Test
    public void processEl181Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(BooleanStruct.of(false));
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v|=$true";
        Delimiters d_ = checkSyntax(ctx_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getPageEl().getGlobalArgument();
        boolean static_ = setupStaticCtx(ctx_, argGl_);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, context_);
        assertTrue(context_.isEmptyErrors());
        ctx_.setNullAnalyzing();
        calculate(all_, context_);
        assertTrue(BooleanStruct.isTrue(lv_.getStruct()));
    }
    @Test
    public void processEl186Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v==1i";
        Delimiters d_ = checkSyntax(ctx_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getPageEl().getGlobalArgument();
        boolean static_ = setupStaticCtx(ctx_, argGl_);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, context_);
        assertTrue(context_.isEmptyErrors());
        ctx_.setNullAnalyzing();
        Argument arg_ = calculate(all_, context_);
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl187Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v++";
        Delimiters d_ = checkSyntax(ctx_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getPageEl().getGlobalArgument();
        boolean static_ = setupStaticCtx(ctx_, argGl_);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, context_);
        assertTrue(context_.isEmptyErrors());
        ctx_.setNullAnalyzing();
        Argument arg_ = calculate(all_, context_);
        assertEq(4, ((NumberStruct)lv_.getStruct()).intStruct());
        assertEq(3, getNumber(arg_));
    }
    @Test
    public void processEl188Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "++v";
        Delimiters d_ = checkSyntax(ctx_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getPageEl().getGlobalArgument();
        boolean static_ = setupStaticCtx(ctx_, argGl_);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, context_);
        assertTrue(context_.isEmptyErrors());
        ctx_.setNullAnalyzing();
        Argument arg_ = calculate(all_, context_);
        assertEq(4, ((NumberStruct)lv_.getStruct()).intStruct());
        assertEq(4, getNumber(arg_));
    }
    @Test
    public void processEl189Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(5);
        lv_.setStruct(new ArrayStruct(in_, ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v[0i]++";
        Delimiters d_ = checkSyntax(ctx_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getPageEl().getGlobalArgument();
        boolean static_ = setupStaticCtx(ctx_, argGl_);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, context_);
        assertTrue(context_.isEmptyErrors());
        ctx_.setNullAnalyzing();
        Argument arg_ = calculate(all_, context_);
        assertEq(6, ((NumberStruct) in_[0]).intStruct());
        assertEq(5, getNumber(arg_));
    }
    @Test
    public void processEl190Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(5);
        lv_.setStruct(new ArrayStruct(in_, ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "++v[0i]";
        Delimiters d_ = checkSyntax(ctx_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getPageEl().getGlobalArgument();
        boolean static_ = setupStaticCtx(ctx_, argGl_);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, context_);
        assertTrue(context_.isEmptyErrors());
        ctx_.setNullAnalyzing();
        Argument arg_ = calculate(all_, context_);
        assertEq(6, ((NumberStruct) in_[0]).intStruct());
        assertEq(6, getNumber(arg_));
    }
    @Test
    public void processEl191Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v+=2i";
        Delimiters d_ = checkSyntax(ctx_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getPageEl().getGlobalArgument();
        boolean static_ = setupStaticCtx(ctx_, argGl_);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, context_);
        assertTrue(context_.isEmptyErrors());
        ctx_.setNullAnalyzing();
        Argument arg_ = calculate(all_, context_);
        assertEq(5, ((NumberStruct)lv_.getStruct()).intStruct());
        assertEq(5, getNumber(arg_));
    }
    @Test
    public void processEl192Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(5);
        lv_.setStruct(new ArrayStruct(in_, ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v[0i]+=3i";
        Delimiters d_ = checkSyntax(ctx_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getPageEl().getGlobalArgument();
        boolean static_ = setupStaticCtx(ctx_, argGl_);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, context_);
        assertTrue(context_.isEmptyErrors());
        ctx_.setNullAnalyzing();
        Argument arg_ = calculate(all_, context_);
        assertEq(8, ((NumberStruct) in_[0]).intStruct());
        assertEq(8, getNumber(arg_));
    }
    @Test
    public void processEl193Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        LocalVariable lv2_ = new LocalVariable();
        lv2_.setStruct(new IntStruct(12));
        lv2_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v2", lv2_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v+++v2";
        Delimiters d_ = checkSyntax(ctx_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getPageEl().getGlobalArgument();
        boolean static_ = setupStaticCtx(ctx_, argGl_);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, context_);
        assertTrue(context_.isEmptyErrors());
        ctx_.setNullAnalyzing();
        Argument arg_ = calculate(all_, context_);
        assertEq(12, ((NumberStruct)lv2_.getStruct()).intStruct());
        assertEq(4, ((NumberStruct)lv_.getStruct()).intStruct());
        assertEq(15, getNumber(arg_));
    }
    @Test
    public void processEl194Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        LocalVariable lv2_ = new LocalVariable();
        lv2_.setStruct(new IntStruct(12));
        lv2_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v2", lv2_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v---v2";
        Delimiters d_ = checkSyntax(ctx_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getPageEl().getGlobalArgument();
        boolean static_ = setupStaticCtx(ctx_, argGl_);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, context_);
        assertTrue(context_.isEmptyErrors());
        ctx_.setNullAnalyzing();
        Argument arg_ = calculate(all_, context_);
        assertEq(12, ((NumberStruct)lv2_.getStruct()).intStruct());
        assertEq(2, ((NumberStruct)lv_.getStruct()).intStruct());
        assertEq(-9, getNumber(arg_));
    }
    @Test
    public void processEl195Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        LocalVariable lv2_ = new LocalVariable();
        lv2_.setStruct(new IntStruct(12));
        lv2_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v2", lv2_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v=++v2";
        Delimiters d_ = checkSyntax(ctx_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getPageEl().getGlobalArgument();
        boolean static_ = setupStaticCtx(ctx_, argGl_);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, context_);
        assertTrue(context_.isEmptyErrors());
        ctx_.setNullAnalyzing();
        Argument arg_ = calculate(all_, context_);
        assertEq(13, ((NumberStruct)lv2_.getStruct()).intStruct());
        assertEq(13, ((NumberStruct)lv_.getStruct()).intStruct());
        assertEq(13, getNumber(arg_));
    }
    @Test
    public void processEl196Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        LocalVariable lv2_ = new LocalVariable();
        lv2_.setStruct(new IntStruct(12));
        lv2_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v2", lv2_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v= ++v2";
        Delimiters d_ = checkSyntax(ctx_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getPageEl().getGlobalArgument();
        boolean static_ = setupStaticCtx(ctx_, argGl_);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, context_);
        assertTrue(context_.isEmptyErrors());
        ctx_.setNullAnalyzing();
        Argument arg_ = calculate(all_, context_);
        assertEq(13, ((NumberStruct)lv2_.getStruct()).intStruct());
        assertEq(13, ((NumberStruct)lv_.getStruct()).intStruct());
        assertEq(13, getNumber(arg_));
    }
    @Test
    public void processEl197Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        ContextEl ctx_ = context_.getContext();
        setupAna(context_);
        String elr_ = "+1y";
        Delimiters d_ = checkSyntax(ctx_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getPageEl().getGlobalArgument();
        boolean static_ = setupStaticCtx(ctx_, argGl_);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, context_);
        assertTrue(context_.isEmptyErrors());
        ctx_.setNullAnalyzing();
        Argument arg_ = calculate(all_, context_);
        assertEq(1, getNumber(arg_));
    }

    private static Delimiters checkSyntax(ContextEl ctx_, String elr_) {
        return ElResolver.checkSyntax(elr_, ctx_, 0);
    }

    @Test
    public void processEl305Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        LocalVariable lv2_ = new LocalVariable();
        lv2_.setStruct(new IntStruct(12));
        lv2_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v2", lv2_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v=v2=4i";
        Delimiters d_ = checkSyntax(ctx_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getPageEl().getGlobalArgument();
        boolean static_ = setupStaticCtx(ctx_, argGl_);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, context_);
        assertTrue(context_.isEmptyErrors());
        ctx_.setNullAnalyzing();
        Argument arg_ = calculate(all_, context_);
        assertEq(4, ((NumberStruct)lv2_.getStruct()).intStruct());
        assertEq(4, ((NumberStruct)lv_.getStruct()).intStruct());
        assertEq(4, getNumber(arg_));
    }

    private static Argument processElNormal(String _el, Configuration _cont) {
        if (_cont.hasPages() && _cont.getContext().getAnalyzing() != null) {
            _cont.getContext().setGlobalClass(_cont.getLastPage().getGlobalClass());
        }
        Argument arg_ = RenderExpUtil.processEl(_el, 0, _cont);
        assertNull(getException(_cont));
        return arg_;
    }
    private static Argument processElNormal(String _el, Configuration _conf, int _minIndex) {
        ContextEl context_ = _conf.getContext();
        _conf.setupAnalyzing();
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(_el, _conf.getContext(), _minIndex);
        assertTrue(d_.getBadOffset() < 0);
        int beg_ = d_.getIndexBegin();
        int end_ = d_.getIndexEnd();
        _conf.setNextIndex(end_+2);
        String el_ = _el.substring(beg_,end_+1);
        OperationsSequence opTwo_ = RenderExpUtil.getOperationsSequence(_minIndex, el_, _conf, d_);
        OperationNode op_ = RenderExpUtil.createOperationNode(_minIndex, CustList.FIRST_INDEX, null, opTwo_, _conf);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, _conf);
        CustList<RendDynOperationNode> out_ = RenderExpUtil.getExecutableNodes(all_);
        assertTrue(context_.isEmptyErrors());
        context_.setNullAnalyzing();
        out_ = RenderExpUtil.getReducedNodes(out_.last());
        Argument arg_ = RenderExpUtil.calculateReuse(out_, _conf);
        assertNull(getException(_conf));
        return arg_;
    }
    private static boolean setupStaticCtx(ContextEl _ctx, Argument _argGl) {
        boolean static_ = _argGl == null || _argGl.isNull();
        _ctx.getAnalyzing().setAccessStaticContext(MethodId.getKind(static_));
        return static_;
    }

    private static Argument calculate(CustList<OperationNode> _ops, Configuration _an) {
        CustList<RendDynOperationNode> out_ = RenderExpUtil.getExecutableNodes(_ops);
        out_ = RenderExpUtil.getReducedNodes(out_.last());
        Argument arg_ = RenderExpUtil.calculateReuse(out_, _an);
        assertNull(getException(_an));
        return arg_;
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
        Classes.tryInitStaticlyTypes(cont_);
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

}
