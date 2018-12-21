package code.expressionlanguage.opers;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.InitializationLgNames;
import code.expressionlanguage.calls.MethodPageEl;
import code.expressionlanguage.classes.Composite;
import code.expressionlanguage.classes.StdStruct;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.FieldBlock;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.text.ElUtil;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.expressionlanguage.variables.VariableSuffix;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public class ExpressionLanguageTest {

    private static final String ARR_INT = "[$int";
    private static final String ARR_ARR_INT = "[[$int";
    private static final String ARR_INTEGER = "[java.lang.Integer";
    private static final String ARR_ARR_INTEGER = "[[java.lang.Integer";
    private static final String COMPOSITE = "code.formathtml.classes.Composite";

    @Test
    public void processEl1Test() {
        Argument arg_ = directCalculate("5");
        assertEq(5L, arg_.getNumber());
    }
    @Test
    public void processEl2Test() {
        Argument arg_ = directCalculate("$static(java.lang.Long).MAX_VALUE");
        assertEq(Long.MAX_VALUE, arg_.getNumber());
    }
    @Test
    public void processEl3Test() {
        Argument arg_ = directCalculate("(1+2)*3");
        assertEq(9L, arg_.getNumber());
    }
    @Test
    public void processEl4Test() {
        Argument arg_ = directCalculate("1- -1");
        assertEq(2L, arg_.getNumber());
    }
    @Test
    public void processEl5Test() {
        Argument arg_ = directCalculate("1+2*3");
        assertEq(7L, arg_.getNumber());
    }
    @Test
    public void processEl6Test() {
        Argument arg_ = directCalculate("- -1");
        assertEq(1L, arg_.getNumber());
    }
    @Test
    public void processEl7Test() {
        Argument arg_ = directCalculate("$static($math).abs(-8l)");
        assertEq(8L, arg_.getNumber());
    }
    @Test
    public void processEl8Test() {
        Argument arg_ = directCalculate("$static($math).abs(8l)");
        assertEq(8L, arg_.getNumber());
    }
    @Test
    public void processEl9Test() {
        Argument arg_ = calculateIndirect("integer", COMPOSITE);
        assertEq(0, arg_.getNumber());
    }
    @Test
    public void processEl10Test() {
        Argument arg_ = directCalculate("40908c");
        assertEq(40908, arg_.getNumber());
    }
    @Test
    public void processEl11Test() {
        Argument arg_ = directCalculate("'\\u9fcb'");
        assertEq(40907, arg_.getNumber());
    }
    @Test
    public void processEl12Test() {
        Argument arg_ = directCalculate("'\\\\'");
        assertEq((int)'\\', arg_.getNumber());
    }
    @Test
    public void processEl13Test() {
        Argument arg_ = directCalculate("'\\''");
         assertEq((int)'\'', arg_.getNumber());
    }
    @Test
    public void processEl14Test() {
        Argument arg_ = directCalculate("'\"'");
        assertEq((int)'"', arg_.getNumber());
    }
    @Test
    public void processEl15Test() {
        Argument arg_ = directCalculate("'\\n'");
        assertEq((int)'\n', arg_.getNumber());
    }
    @Test
    public void processEl16Test() {
        Argument arg_ = calculateIndirectLocalVars("v;.integer","v",COMPOSITE);
        assertEq(0, arg_.getNumber());
    }
    @Test
    public void processEl17Test() {
        Argument arg_ = calculateIndirectLoopVars("v;integer","v",COMPOSITE);
        assertEq(0, arg_.getNumber());
    }
    @Test
    public void processEl18Test() {
        Argument arg_ = directCalculate("5 $instanceof java.lang.Number");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl19Test() {
        Argument arg_ = directCalculate("'5' $instanceof java.lang.Number");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl20Test() {
        Argument arg_ = directCalculate("!('5' $instanceof java.lang.Number)");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl21Test() {
        Argument arg_ = directCalculate("1+1==2");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl22Test() {
        Argument arg_ = directCalculate("1+1!=2");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl23Test() {
        Argument arg_ = directCalculate("1+1==2&&1+0==8");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl24Test() {
        Argument arg_ = directCalculate("1+1!=2||1+7==8");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl25Test() {
        Argument arg_ = directCalculate("1+1==2&&(1+0==8||3*3==9)");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl26Test() {
        Argument arg_ = directCalculate("1+1==2||1+6==8&&1==1");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl29Test() {
        Argument arg_ = directCalculate("1+1==2||1/0>8");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl30Test() {
        Argument arg_ = directCalculate("(($long)-1i)");
        assertEq(-1, arg_.getNumber());
    }
    @Test
    public void processEl31Test() {
        Argument arg_ = directCalculate("$static($math).abs(-8i)");
        assertEq(8, arg_.getNumber());
    }
    @Test
    public void processEl32Test() {
        Argument arg_ = directCalculate("$static($math).abs(8i)");
        assertEq(8, arg_.getNumber());
    }
    @Test
    public void processEl33Test() {
        Argument arg_ = directCalculate("$static($math).abs(-8I)");
        assertEq(8, arg_.getNumber());
    }
    @Test
    public void processEl34Test() {
        Argument arg_ = directCalculate("$static($math).abs(8I)");
        assertEq(8, arg_.getNumber());
    }
    @Test
    public void processEl35Test() {
        Argument arg_ = directCalculate("$static($math).abs(-8L)");
        assertEq(8L, arg_.getNumber());
    }
    @Test
    public void processEl36Test() {
        Argument arg_ = directCalculate("$static($math).abs(8L)");
        assertEq(8L, arg_.getNumber());
    }
    @Test
    public void processEl37Test() {
        Argument arg_ = directCalculate("\"\\nnew line\"");
        assertEq("\nnew line", arg_.getString());
    }

    @Test
    public void processEl38Test() {
        Argument arg_ = directCalculate("\"\".trim()");
        assertEq("", arg_.getString());
    }

    @Test
    public void processEl39Test() {
        Argument arg_ = directCalculate("$null $instanceof java.lang.Object");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl40Test() {
        Argument arg_ = directCalculate("\"abc\".trim()");
        assertEq("abc", arg_.getString());
    }

    @Test
    public void processEl41Test() {
        Argument arg_ = directCalculate("\" abc\".trim()");
        assertEq("abc", arg_.getString());
    }

    @Test
    public void processEl42Test() {
        Argument arg_ = directCalculate("\"abc \".trim()");
        assertEq("abc", arg_.getString());
    }

    @Test
    public void processEl43Test() {
        Argument arg_ = directCalculate("\" abc \".trim()");
        assertEq("abc", arg_.getString());
    }

    @Test
    public void processEl44Test() {
        Argument arg_ = directCalculate("\" \".trim()");
        assertEq("", arg_.getString());
    }
    @Test
    public void processEl45Test() {
        Argument arg_ = directCalculate("\"hello word\".splitStrings(\"e\",\"o\")");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        Struct[] res_ = arr_.getInstance();
        assertEq(4, res_.length);
        assertEq("h", (String)res_[0].getInstance());
        assertEq("ll", (String)res_[1].getInstance());
        assertEq(" w", (String)res_[2].getInstance());
        assertEq("rd", (String)res_[3].getInstance());
    }
    public void processEl62Test() {
        ContextEl context_ = contextEl();
        Composite compos_ = new Composite();
        compos_.setInteger(2);
        addBean(context_, compos_, "code.expressionlanguage.classes.Composite");
        StringMap<LoopVariable> localVars_ = new StringMap<LoopVariable>();
        LoopVariable lv_ = new LoopVariable();
        lv_.setStruct(new IntStruct(4));
        lv_.setClassName("$int");
        localVars_.put("v", lv_);
        context_.getLastPage().getVars().putAllMap(localVars_);
        Argument arg_ = simpleCaculateEl("v;+integer",context_);
        assertEq(6, arg_.getNumber());
    }

    @Test
    public void processEl63Test() {
        Argument arg_ = directCalculate("$new $int[1i]");
        ArrayStruct res_ = (ArrayStruct) arg_.getStruct();
        assertEq(ARR_INT, res_.getClassName());
        assertEq(1, res_.getInstance().length);
        assertEq(0, (Number) res_.getInstance()[0].getInstance());
    }
    @Test
    public void processEl64Test() {
        Argument arg_ = directCalculate("$new $int[1i][]");
        ArrayStruct res_ = (ArrayStruct) arg_.getStruct();
        assertEq(ARR_ARR_INT, res_.getClassName());
        assertEq(1, res_.getInstance().length);
        assertSame(NullStruct.NULL_VALUE, res_.getInstance()[0]);
    }
    @Test
    public void processEl65Test() {
        Argument arg_ = directCalculate("$new java.lang.Integer[2i]");
        ArrayStruct res_ = (ArrayStruct) arg_.getStruct();
        assertEq(ARR_INTEGER, res_.getClassName());
        assertEq(2, res_.getInstance().length);
        assertSame(NullStruct.NULL_VALUE, res_.getInstance()[0]);
        assertSame(NullStruct.NULL_VALUE, res_.getInstance()[1]);
    }
    @Test
    public void processEl66Test() {
        Argument arg_ = directCalculate("$new java.lang.Integer[2i][]");
        ArrayStruct res_ = (ArrayStruct) arg_.getStruct();
        assertEq(ARR_ARR_INTEGER, res_.getClassName());
        assertEq(2, res_.getInstance().length);
        assertSame(NullStruct.NULL_VALUE, res_.getInstance()[0]);
        assertSame(NullStruct.NULL_VALUE, res_.getInstance()[1]);
    }
    @Test
    public void processEl70Test() {
        Argument arg_ = directCalculate("!!$false");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl72Test() {
        Argument arg_ = directCalculate("$static(java.lang.Byte).MAX_VALUE");
        assertEq((byte)127, arg_.getNumber());
    }

    @Test
    public void processEl77Test() {
        Argument arg_ = directCalculate("(\"Hello\\\\\"+\"World\").length()");
        assertEq(11, arg_.getNumber());
    }
    @Test
    public void processEl78Test() {
        Argument arg_ = directCalculate("(\"Hello\\\"\"+\"World\").length()");
        assertEq(11, arg_.getNumber());
    }
    @Test
    public void processEl79Test() {
        Argument arg_ = directCalculate("(\"Hello\\\\\"+'\\\\').length()");
        assertEq(7, arg_.getNumber());
    }
    @Test
    public void processEl80Test() {
        Argument arg_ = directCalculate("(\"Hello\\\"\"+'\\'').length()");
        assertEq(7, arg_.getNumber());
    }
    @Test
    public void processEl87Test() {
        Argument arg_ = directCalculate("$bool(1>0,0i,1i)");
        assertEq(0, arg_.getNumber());
    }
    @Test
    public void processEl88Test() {
        Argument arg_ = directCalculate("$bool(1<0,0i,1i)");
        assertEq(1, arg_.getNumber());
    }
    @Test
    public void processEl89Test() {
        Argument arg_ = directCalculate("$bool(1>0,0i,1i/0i)");
        assertEq(0, arg_.getNumber());
    }
    @Test
    public void processEl90Test() {
        Argument arg_ = directCalculate("$bool(1<0,1i/0i,1i)");
        assertEq(1, arg_.getNumber());
    }
    @Test
    public void processEl95Test() {
        Argument arg_ = directCalculate("($new $int[1i])[0i]");
        assertEq(0, arg_.getNumber());
    }
    @Test
    public void processEl96Test() {
        Argument arg_ = directCalculate("$new $int[]{2i}");
        ArrayStruct res_ = (ArrayStruct) arg_.getStruct();
        assertEq(ARR_INT, res_.getClassName());
        Struct[] o_ = res_.getInstance();
        assertEq(1, o_.length);
        assertEq(2, ((Number)o_[0].getInstance()).intValue());
    }
    @Test
    public void processEl97Test() {
        Argument arg_ = directCalculate("$new $int[]{3i,7i}");
        ArrayStruct res_ = (ArrayStruct) arg_.getStruct();
        assertEq(ARR_INT, res_.getClassName());
        Struct[] o_ = res_.getInstance();
        assertEq(2, o_.length);
        assertEq(3, ((Number)o_[0].getInstance()).intValue());
        assertEq(7, ((Number)o_[1].getInstance()).intValue());
    }
    @Test
    public void processEl98Test() {
        Argument arg_ = directCalculate("$new $int[]{}");
        ArrayStruct res_ = (ArrayStruct) arg_.getStruct();
        assertEq(ARR_INT, res_.getClassName());
        Struct[] o_ = res_.getInstance();
        assertEq(0, o_.length);
    }
    @Test
    public void processEl99Test() {
        Argument arg_ = directCalculate("$new java.lang.Integer[]{3i,7i}");
        ArrayStruct res_ = (ArrayStruct) arg_.getStruct();
        assertEq(ARR_INTEGER, res_.getClassName());
        Struct[] o_ = res_.getInstance();
        assertEq(2, o_.length);
        assertEq(3, ((Number)o_[0].getInstance()).intValue());
        assertEq(7, ((Number)o_[1].getInstance()).intValue());
    }

    @Test
    public void processEl119Test() {
        Argument arg_ = directCalculate("(1b+2b)*3");
        assertEq(9L, arg_.getNumber());
    }

    @Test
    public void processEl120Test() {
        Argument arg_ = directCalculate("(1s+2b)*3");
        assertEq(9L, arg_.getNumber());
    }

    @Test
    public void processEl121Test() {
        Argument arg_ = directCalculate("- -1b");
        assertEq(1, arg_.getNumber());
    }

    @Test
    public void processEl122Test() {
        Argument arg_ = directCalculate("-1b");
        assertEq(-1, arg_.getNumber());
    }
    @Test
    public void processEl123Test() {
        Argument arg_ = directCalculate("($int)(java.lang.Byte.MAX_VALUE+java.lang.Byte.MAX_VALUE)");
        int max_ = Byte.MAX_VALUE+Byte.MAX_VALUE;
        assertEq(max_, arg_.getNumber());
    }
    @Test
    public void processEl123FailTest() {
        Argument arg_ = directCalculate("+1b");
        assertEq(1, arg_.getNumber());
    }

    @Test
    public void processEl124Test() {
        Argument arg_ = directCalculate("+-1b");
        assertEq(-1, arg_.getNumber());
    }

    @Test
    public void processEl125Test() {
        Argument arg_ = directCalculate("-.25e0+.5");
        assertEq(0.25d, arg_.getNumber());
    }

    @Test
    public void processEl128Test() {
        Argument arg_ = directCalculate("1_0+2*3");
        assertEq(16L, arg_.getNumber());
    }

    @Test
    public void processEl129Test() {
        Argument arg_ = directCalculate("$static($math).mod(-8l,3l)");
        assertEq(1L, arg_.getNumber());
    }

    @Test
    public void processEl130Test() {
        Argument arg_ = directCalculate("$static($math).quot(-8l,3l)");
        assertEq(-3L, arg_.getNumber());
    }

    @Test
    public void processEl131Test() {
        Argument arg_ = directCalculate("$new $int[1i][1i]");
        ArrayStruct res_ = (ArrayStruct) arg_.getStruct();
        assertEq(ARR_ARR_INT, res_.getClassName());
        assertEq(1, res_.getInstance().length);
        assertEq(ARR_INT, ((ArrayStruct) res_.getInstance()[0]).getClassName());
        assertEq(1, ((Struct[])res_.getInstance()[0].getInstance()).length);
        assertEq(0, (Number) ((Struct[])res_.getInstance()[0].getInstance())[0].getInstance());
    }

    @Test
    public void processEl132Test() {
        Argument arg_ = directCalculate("$new java.lang.Integer[1i][1i]");
        ArrayStruct res_ = (ArrayStruct) arg_.getStruct();
        assertEq(ARR_ARR_INTEGER, res_.getClassName());
        assertEq(1, res_.getInstance().length);
        assertEq(ARR_INTEGER, ((ArrayStruct) res_.getInstance()[0]).getClassName());
        assertSame(NullStruct.NULL_VALUE, ((Struct[])res_.getInstance()[0].getInstance())[0]);
    }

    @Test
    public void processEl133Test() {
        Argument arg_ = directCalculate("(1 + 2) * 3.0");
        assertEq(9L, arg_.getNumber());
    }

    @Test
    public void processEl134Test() {
        Argument arg_ = directCalculate(" 2.0 + $static($math). quot( -8l, 3l) + 3.0");
        assertEq(2L, arg_.getNumber());
    }
    
    @Test
    public void processEl135Test() {
        Argument arg_ = directCalculate("1 + 2 ");
        assertEq(3L, arg_.getNumber());
    }

    @Test
    public void processEl136Test() {
        Argument arg_ = directCalculate("1. + 2. ");
        assertEq(3L, arg_.getNumber());
    }

    @Test
    public void processEl137Test() {
        Argument arg_ = directCalculate("1.d + 2.d ");
        assertEq(3L, arg_.getNumber());
    }

    @Test
    public void processEl138Test() {
        Argument arg_ = directCalculate("-.2_5e0+.5");
        assertEq(0.25d, arg_.getNumber());
    }

    @Test
    public void processEl139Test() {
        Argument arg_ = directCalculate("-.25e0_0+.5");
        assertEq(0.25d, arg_.getNumber());
    }

    @Test
    public void processEl140Test() {
        Argument arg_ = directCalculate("1_0.d + 2.d ");
        assertEq(12L, arg_.getNumber());
    }
    @Test
    public void processEl141Test() {
        Argument arg_ = directCalculate("1.05e1");
        assertEq(10.5d, arg_.getNumber());
    }
    @Test
    public void processEl142Test() {
        Argument arg_ = directCalculate("1.00625e1");
        assertEq(10.0625d, arg_.getNumber());
    }
    @Test
    public void processEl143Test() {
        Argument arg_ = directCalculate("100.625e-1");
        assertEq(10.0625d, arg_.getNumber());
    }
    @Test
    public void processEl144Test() {
        Argument arg_ = directCalculate("100.625");
        assertEq(100.625d, arg_.getNumber());
    }
    @Test
    public void processEl145Test() {
        Argument arg_ = directCalculate("123456789123456789123456789.0");
        assertEq(1.2345678912345678912e26, arg_.getNumber());
    }
    @Test
    public void processEl147Test() {
        Argument arg_ = directCalculate("123456789123456789123456789.");
        assertEq(1.2345678912345678912e26, arg_.getNumber());
    }
    @Test
    public void processEl148Test() {
        Argument arg_ = directCalculate("123456789123456789123456789.e-1");
        assertEq(1.2345678912345678912e25, arg_.getNumber());
    }
    @Test
    public void processEl149Test() {
        Argument arg_ = directCalculate("123456789123456789123456789.e1");
        assertEq(1.2345678912345678912e27, arg_.getNumber());
    }
    @Test
    public void processEl150Test() {
        Argument arg_ = directCalculate("123456.e1");
        assertEq(1234560, arg_.getNumber());
    }
    @Test
    public void processEl151Test() {
        Argument arg_ = directCalculate(".078125e-1");
        assertEq(.078125e-1, arg_.getNumber());
    }
    @Test
    public void processEl152Test() {
        Argument arg_ = directCalculate("123456789123456789123456789.0e-36");
        assertEq(1.2345678912345678912e-10, arg_.getNumber());
    }
    @Test
    public void processEl153Test() {
        Argument arg_ = directCalculate("0.0e-36");
        assertEq(0.0, arg_.getNumber());
    }
    @Test
    public void processEl154Test() {
        Argument arg_ = directCalculate("-0.0e-36");
        assertEq(-0.0, arg_.getNumber());
    }
    @Test
    public void processEl155Test() {
        Argument arg_ = directCalculate("0.625e-1");
        assertEq(0.0625, arg_.getNumber());
    }
    @Test
    public void processEl156Test() {
        Argument arg_ = directCalculate(".625e-1");
        assertEq(0.0625, arg_.getNumber());
    }
    @Test
    public void processEl157Test() {
        Argument arg_ = directCalculate("0.625e1");
        assertEq(6.25, arg_.getNumber());
    }
    @Test
    public void processEl158Test() {
        Argument arg_ = directCalculate(".625e1");
        assertEq(6.25, arg_.getNumber());
    }
    @Test
    public void processEl159Test() {
        Argument arg_ = directCalculate("0.625e0");
        assertEq(0.625, arg_.getNumber());
    }
    @Test
    public void processEl160Test() {
        Argument arg_ = directCalculate(".625e0");
        assertEq(0.625, arg_.getNumber());
    }
    @Test
    public void processEl161Test() {
        Argument arg_ = directCalculate("-.625e1");
        assertEq(-6.25, arg_.getNumber());
    }
    @Test
    public void processEl162Test() {
        Argument arg_ = directCalculate("-.6e1");
        assertEq(-6.0, arg_.getNumber());
    }
    @Test
    public void processEl163Test() {
        Argument arg_ = directCalculate("-.60e1");
        assertEq(-6.0, arg_.getNumber());
    }
    @Test
    public void processEl164Test() {
        Argument arg_ = directCalculate(".6e1");
        assertEq(6.0, arg_.getNumber());
    }
    @Test
    public void processEl165Test() {
        Argument arg_ = directCalculate(".6e2");
        assertEq(60.0, arg_.getNumber());
    }
    @Test
    public void processEl166Test() {
        Argument arg_ = directCalculate("123456789123456789123456789.1e1");
        assertEq(1.2345678912345678912e27, arg_.getNumber());
    }
    @Test
    public void processEl167Test() {
        Argument arg_ = directCalculate("100.e-1");
        assertEq(10.0, arg_.getNumber());
    }
    @Test
    public void processEl168Test() {
        Argument arg_ = directCalculate("-100.e-1");
        assertEq(-10.0, arg_.getNumber());
    }
    @Test
    public void processEl169Test() {
        Argument arg_ = directCalculate("-1.e1");
        assertEq(-10.0, arg_.getNumber());
    }
    @Test
    public void processEl170Test() {
        Argument arg_ = directCalculate("-1.");
        assertEq(-1.0, arg_.getNumber());
    }
    @Test
    public void processEl171Test() {
        Argument arg_ = directCalculate("1e-123456789123456789123");
        assertEq(0.0, arg_.getNumber());
    }
    @Test
    public void processEl172Test() {
        Argument arg_ = directCalculate("-1e-123456789123456789123");
        assertEq(-0.0, arg_.getNumber());
    }
    @Test
    public void processEl173Test() {
        Argument arg_ = directCalculate("1e123456789123456789123");
        assertEq(Double.POSITIVE_INFINITY, arg_.getNumber());
    }
    @Test
    public void processEl174Test() {
        Argument arg_ = directCalculate("-1e123456789123456789123");
        assertEq(Double.NEGATIVE_INFINITY, arg_.getNumber());
    }
    @Test
    public void processEl175Test() {
        Argument arg_ = directCalculate("'\\u9FCB'");
        assertEq(40907, arg_.getNumber());
    }
    @Test
    public void processEl176Test() {
        Argument arg_ = directCalculate("\"\\u9FCB\"");
        assertEq("\u9fcb", arg_.getString());
    }
    @Test
    public void processEl177Test() {
        Argument arg_ = directCalculate("\"\\u9fcb\"");
        assertEq("\u9fcb", arg_.getString());
    }
    @Test
    public void processEl178Test() {
        Argument arg_ = directCalculate("$static(java.lang.Long) .MAX_VALUE");
        assertEq(Long.MAX_VALUE, arg_.getNumber());
    }

    @Test
    public void processEl7FailTest() {
        assertNotNull(directCalculateExc("$new $int[-1i]"));
    }

    @Test
    public void processEl8FailTest() {
        assertNotNull(directCalculateExc("$new java.lang.Integer[-1i]"));
    }

    @Test
    public void processEl198Test() {
        Argument arg_ = directCalculate("$(java.lang.Number)5");
        assertEq(5L, arg_.getNumber());
    }
    @Test
    public void processEl199Test() {
        Argument arg_ = directCalculate("$($byte)5");
        assertEq(5L, arg_.getNumber());
    }
    @Test
    public void processEl201Test() {
        Argument arg_ = directCalculate("$(java.lang.Byte)$null");
        assertSame(NullStruct.NULL_VALUE, arg_.getStruct());
    }
    @Test
    public void processEl202Test() {
        Struct exc_ = directCalculateExc("$(java.lang.Byte)\"not cast\"");
        assertTrue(exc_ instanceof ErrorStruct);
        assertEq("code.expressionlanguage.exceptions.DynamicCastClassException",((ErrorStruct)exc_).getClassName());
    }

    @Test
    public void processEl203Test() {
        String el_ = "1!=2!=3";
        Argument arg_ = directCalculate(el_);
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl210Test() {
        Argument arg_ = directCalculate("($int)('1'+'2')");
        assertEq(99, arg_.getNumber());
    }

    @Test
    public void processEl211Test() {
        Argument arg_ = directCalculate("\"\"+$new $char[]{'1','2'}[0]+$new $char[]{'1','2'}[1]");
        assertEq("12", arg_.getString());
    }
    @Test
    public void processEl212Test() {
        Argument arg_ = directCalculate("('1'+'2')*3i");
        assertEq(297, arg_.getNumber());
    }
    @Test
    public void processEl213Test() {
        Argument arg_ = directCalculate("'1'>1i");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl214Test() {
        Argument arg_ = directCalculate("'1'<1i");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl215Test() {
        Argument arg_ = directCalculate("'1'<1i");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl216Test() {
        Argument arg_ = directCalculate("'1'>1i");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl213FailTest() {
        Argument arg_ = directCalculate("('1'+'2')*'3'");
        assertEq(5049, arg_.getNumber());
    }
    @Test
    public void processEl219Test() {
        Argument arg_ = directCalculate("6 + $($int) - $static($math).quot(8,5) - 2");
        assertEq(3, arg_.getNumber());
    }
    @Test
    public void processEl320Test() {
        Argument arg_ = directCalculate("0x1");
        assertEq(1, arg_.getNumber());
    }
    @Test
    public void processEl321Test() {
        Argument arg_ = directCalculate("0xffff_ffff");
        assertEq(-1, arg_.getNumber());
    }
    @Test
    public void processEl322Test() {
        Argument arg_ = directCalculate("0x1p0");
        assertEq(1, arg_.getNumber());
    }
    @Test
    public void processEl323Test() {
        Argument arg_ = directCalculate("0x1.0p0");
        assertEq(1, arg_.getNumber());
    }
    @Test
    public void processEl324Test() {
        Argument arg_ = directCalculate("0x1.1p4");
        assertEq(17, arg_.getNumber());
    }
    @Test
    public void processEl325Test() {
        Argument arg_ = directCalculate("0x110.0p-4");
        assertEq(17, arg_.getNumber());
    }
    @Test
    public void processEl326Test() {
        Argument arg_ = directCalculate("0x1l");
        assertEq(1, arg_.getNumber());
    }
    @Test
    public void processEl327Test() {
        Argument arg_ = directCalculate("0x1xl");
        assertEq(1, arg_.getNumber());
    }
    @Test
    public void processEl328Test() {
        Argument arg_ = directCalculate("0b1");
        assertEq(1, arg_.getNumber());
    }
    @Test
    public void processEl329Test() {
        Argument arg_ = directCalculate("0b10");
        assertEq(2, arg_.getNumber());
    }
    @Test
    public void processEl330Test() {
        Argument arg_ = directCalculate("01");
        assertEq(1, arg_.getNumber());
    }
    @Test
    public void processEl331Test() {
        Argument arg_ = directCalculate("017_7777_7777_7777_7777_7777l");
        assertEq(-1, arg_.getNumber());
    }
    @Test
    public void processEl332Test() {
        Argument arg_ = directCalculate("007_7777_7777_7777_7777_7777l");
        assertEq(Long.MAX_VALUE, arg_.getNumber());
    }
    @Test
    public void processEl333Test() {
        Argument arg_ = directCalculate("0377_7777_7777i");
        assertEq(-1, arg_.getNumber());
    }
    @Test
    public void processEl334Test() {
        Argument arg_ = directCalculate("0177_7777_7777i");
        assertEq(Integer.MAX_VALUE, arg_.getNumber());
    }
    @Test
    public void processEl335Test() {
        Argument arg_ = directCalculate("1&2");
        assertEq(0, arg_.getNumber());
    }
    @Test
    public void processEl336Test() {
        Argument arg_ = directCalculate("1|2");
        assertEq(3, arg_.getNumber());
    }
    @Test
    public void processEl337Test() {
        Argument arg_ = directCalculate("1^2");
        assertEq(3, arg_.getNumber());
    }
    @Test
    public void processEl338Test() {
        Argument arg_ = directCalculate("1^3");
        assertEq(2, arg_.getNumber());
    }
    @Test
    public void processEl339Test() {
        Argument arg_ = directCalculate("1c|2c");
        assertEq(3, arg_.getNumber());
    }
    @Test
    public void processEl340Test() {
        Argument arg_ = directCalculate("~0");
        assertEq(-1, arg_.getNumber());
    }
    @Test
    public void processEl341Test() {
        Argument arg_ = directCalculate("~-1");
        assertEq(0, arg_.getNumber());
    }
    @Test
    public void processEl342Test() {
        Argument arg_ = directCalculate("1<<2");
        assertEq(4, arg_.getNumber());
    }
    @Test
    public void processEl343Test() {
        Argument arg_ = directCalculate("4>>2");
        assertEq(1, arg_.getNumber());
    }
    @Test
    public void processEl344Test() {
        Argument arg_ = directCalculate("1<<34");
        assertEq(4, arg_.getNumber());
    }
    @Test
    public void processEl345Test() {
        Argument arg_ = directCalculate("4>>34");
        assertEq(1, arg_.getNumber());
    }
    @Test
    public void processEl346Test() {
        Argument arg_ = directCalculate("1l<<2");
        assertEq(4, arg_.getNumber());
    }
    @Test
    public void processEl347Test() {
        Argument arg_ = directCalculate("4l>>2");
        assertEq(1, arg_.getNumber());
    }
    @Test
    public void processEl348Test() {
        Argument arg_ = directCalculate("1l<<66");
        assertEq(4, arg_.getNumber());
    }
    @Test
    public void processEl349Test() {
        Argument arg_ = directCalculate("4l>>66");
        assertEq(1, arg_.getNumber());
    }
    @Test
    public void processEl350Test() {
        Argument arg_ = directCalculate("-1<<2");
        assertEq(-4, arg_.getNumber());
    }
    @Test
    public void processEl351Test() {
        Argument arg_ = directCalculate("-4>>2");
        assertEq(-1, arg_.getNumber());
    }
    @Test
    public void processEl352Test() {
        Argument arg_ = directCalculate("0200_0000_0000i");
        assertEq(Integer.MIN_VALUE, arg_.getNumber());
    }
    @Test
    public void processEl353Test() {
        Argument arg_ = directCalculate("0x1.0xd");
        assertEq(1, arg_.getNumber());
    }
    @Test
    public void processEl354Test() {
        Argument arg_ = directCalculate("0x1.0p1d");
        assertEq(2, arg_.getNumber());
    }
    @Test
    public void processEl355Test() {
        Argument arg_ = directCalculate("0x1p1d");
        assertEq(2, arg_.getNumber());
    }
    @Test
    public void processEl356Test() {
        Argument arg_ = directCalculate("0x1.8");
        assertEq(1.5d, arg_.getNumber());
    }
    @Test
    public void processEl357Test() {
        Argument arg_ = directCalculate("0x1.8xd");
        assertEq(1.5d, arg_.getNumber());
    }
    @Test
    public void processEl358Test() {
        Argument arg_ = directCalculate("0377b");
        assertEq(-1, arg_.getNumber());
    }
    @Test
    public void processEl359Test() {
        Argument arg_ = directCalculate("0200b");
        assertEq(Byte.MIN_VALUE, arg_.getNumber());
    }
    @Test
    public void processEl360Test() {
        Argument arg_ = directCalculate("0377777s");
        assertEq(-1, arg_.getNumber());
    }
    @Test
    public void processEl361Test() {
        Argument arg_ = directCalculate("0200000s");
        assertEq(Short.MIN_VALUE, arg_.getNumber());
    }
    @Test
    public void processEl362Test() {
        Argument arg_ = directCalculate("$new java.lang.String(\"\u1000\".getBytes())");
        assertEq("\u1000", arg_.getString());
    }
    @Test
    public void processEl363Test() {
        Argument arg_ = directCalculate("$new java.lang.String(\"\u0800\".getBytes())");
        assertEq("\u0800", arg_.getString());
    }
    @Test
    public void processEl364Test() {
        Argument arg_ = directCalculate("$new java.lang.String(\"\u07FF\".getBytes())");
        assertEq("\u07FF", arg_.getString());
    }
    @Test
    public void processEl365Test() {
        Argument arg_ = directCalculate("$new java.lang.String(\"\u0050\".getBytes())");
        assertEq("\u0050", arg_.getString());
    }

    @Test
    public void processEl366Test() {
        Argument arg_ = directCalculate("0xafff_ffff<<<1");
        assertEq(-536870914, arg_.getNumber());
    }
    @Test
    public void processEl367Test() {
        Argument arg_ = directCalculate("0xafff_ffff<<1");
        assertEq(1610612734, arg_.getNumber());
    }

    @Test
    public void processEl368Test() {
        Argument arg_ = directCalculate("0xafff_ffff>>>1");
        assertEq(1476395007, arg_.getNumber());
    }

    @Test
    public void processEl369Test() {
        Argument arg_ = directCalculate("0xafff_ffff<<<<1");
        assertEq(1610612735, arg_.getNumber());
    }

    @Test
    public void processEl370Test() {
        Argument arg_ = directCalculate("0xafff_ffff>>>>1");
        assertEq(-671088641, arg_.getNumber());
    }
    private static Argument directCalculate(String _el) {
        ContextEl c_ = analyze(_el);
        addImportingPage(c_);
        return calculatePrepareStaticResult(c_,false);
    }
    private static Struct directCalculateExc(String _el) {
        ContextEl c_ = analyze(_el);
        addImportingPage(c_);
        calculatePrepareStaticResult(c_,true);
        return c_.getException();
    }

    private static ContextEl analyze(String _el) {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", file());
        files_.put("pkg/ExTwo", addonFileStaticResult(_el));
        return contextEl(files_);
    }
    private static Argument calculateIndirectLocalVars(String _el, String _var, String _className) {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", file());
        ContextEl cont_ = contextEl(files_);
        cont_.setAnalyzing(new AnalyzedPageEl());
        LocalVariable lv_ = new LocalVariable();
        Struct fresh_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, _className, "", -1);
        lv_.setStruct(fresh_);
        lv_.setClassName(_className);
        cont_.getAnalyzing().initLocalVars();
        cont_.getAnalyzing().putLocalVar(_var, lv_);
        cont_.getAnalyzing().setGlobalClass(_className);
        Calculation calc_ = Calculation.staticCalculation(true);
        CustList<ExecOperationNode> list_ = ElUtil.getAnalyzedOperations(_el, cont_, calc_);
        addImportingPage(cont_);
        lv_ = new LocalVariable();
        lv_.setStruct(fresh_);
        lv_.setClassName(_className);
        cont_.getLastPage().putLocalVar(_var, lv_);
        cont_.getLastPage().setGlobalArgumentStruct(fresh_);
        cont_.setGlobalClass(_className);
        ExpressionLanguage el_ = new ExpressionLanguage(list_);
        return el_.calculateMember(cont_);
        
    }
    private static Argument calculateIndirectLoopVars(String _el, String _var, String _className) {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", file());
        ContextEl cont_ = contextEl(files_);
        cont_.setAnalyzing(new AnalyzedPageEl());
        LoopVariable lv_ = new LoopVariable();
        Struct fresh_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, _className, "", -1);
        lv_.setStruct(fresh_);
        lv_.setClassName(_className);
        cont_.getAnalyzing().initVars();
        cont_.getAnalyzing().putVar(_var, lv_);
        cont_.getAnalyzing().setGlobalClass(_className);
        Calculation calc_ = Calculation.staticCalculation(true);
        CustList<ExecOperationNode> list_ = ElUtil.getAnalyzedOperations(_el, cont_, calc_);
        addImportingPage(cont_);
        lv_ = new LoopVariable();
        lv_.setStruct(fresh_);
        lv_.setClassName(_className);
        cont_.getLastPage().getVars().put(_var, lv_);
        cont_.getLastPage().setGlobalArgumentStruct(fresh_);
        cont_.setGlobalClass(_className);
        ExpressionLanguage el_ = new ExpressionLanguage(list_);
        return el_.calculateMember(cont_);
        
    }
    private static Argument calculateIndirect(String _el, String _className) {
        String var_ = "temp";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", file());
        ContextEl cont_ = contextEl(files_);
        cont_.setAnalyzing(new AnalyzedPageEl());
        LocalVariable lv_ = new LocalVariable();
        Struct fresh_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, _className, "", -1);
        lv_.setStruct(fresh_);
        lv_.setClassName(_className);
        cont_.getAnalyzing().initLocalVars();
        cont_.getAnalyzing().putLocalVar(var_, lv_);
        cont_.getAnalyzing().setGlobalClass(_className);
        String form_ = StringList.concat(var_,";.",_el);
        Calculation calc_ = Calculation.staticCalculation(true);
        CustList<ExecOperationNode> list_ = ElUtil.getAnalyzedOperations(form_, cont_, calc_);
        addImportingPage(cont_);
        lv_ = new LocalVariable();
        lv_.setStruct(fresh_);
        lv_.setClassName(_className);
        cont_.getLastPage().putLocalVar(var_, lv_);
        cont_.getLastPage().setGlobalArgumentStruct(fresh_);
        cont_.setGlobalClass(_className);
        ExpressionLanguage el_ = new ExpressionLanguage(list_);
        return el_.calculateMember(cont_);
    }
    private static Argument calculatePrepareStaticResult(ContextEl _context, boolean _exc) {
        RootBlock cl_ = _context.getClasses().getClassBody("code.formathtml.classes.Apply");
        _context.getLastPage().setGlobalClass("code.formathtml.classes.Apply");
        FieldBlock f_ = (FieldBlock) cl_.getFirstChild();
        ExpressionLanguage el_ = f_.getValueEl();
        Argument arg_ = el_.calculateMember(_context);
        if (!_exc) {
            assertNull(_context.getException());
        } else {
            assertNotNull(_context.getException());
        }
        return arg_;
    }
    private static String addonFileStaticResult(String _el) {
        StringBuilder str_ = new StringBuilder();
        str_.append("$public $class code.formathtml.classes.Apply {\n");
        str_.append(" $public $static $final java.lang.Object result = ");
        str_.append(_el);
        str_.append(":\n");
        str_.append("}");
        return str_.toString();
    }
    private static String file() {
        StringBuilder str_ = new StringBuilder();
        str_.append("$public $class code.formathtml.classes.InheritedComposite : Composite {\n");
        str_.append("\n");
        str_.append("}\n");
        str_.append("\n");
        str_.append("$public $class code.formathtml.classes.Composite {\n");
        str_.append("\n");
        str_.append("    $public $int integer:\n");
        str_.append("\n");
        str_.append("    $public java.lang.Integer objInteger:\n");
        str_.append("\n");
        str_.append("    $public CompositeSec composite = $new CompositeSec():\n");
        str_.append("\n");
        str_.append("    $public $int privateInt:\n");
        str_.append("\n");
        str_.append("    $public code.util.StringList strings:\n");
        str_.append("\n");
        str_.append("    $public java.lang.String string:\n");
        str_.append("\n");
        str_.append("    $public $char myChar = 't':\n");
        str_.append("\n");
        str_.append("    $public $boolean displayed = $true:\n");
        str_.append("\n");
        str_.append("    $public() {\n");
        str_.append("        $this(0i):\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $private($int _privateInt) {\n");
        str_.append("        privateInt = _privateInt;.;:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public(java.lang.String..._strings) {\n");
        str_.append("        strings = $new code.util.StringList(_strings;.;):\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public($int _param, java.lang.String..._strings) {\n");
        str_.append("        privateInt = _param;.;:\n");
        str_.append("        strings = $new code.util.StringList(_strings;.;):\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal CompositeSec getComposite() {\n");
        str_.append("        $return composite:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $int getInteger() {\n");
        str_.append("        $return integer:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $void setInteger($int _integer) {\n");
        str_.append("        integer = _integer;.;:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal java.lang.Integer getObjInteger() {\n");
        str_.append("        $return objInteger:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $int getPrivateInt() {\n");
        str_.append("        $return privateInt:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $void setPrivateInt($int _privateInt) {\n");
        str_.append("        privateInt = _privateInt;.;:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $int summum($int _other) {\n");
        str_.append("        $return integer + _other;.;:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $int sum(java.lang.Long _other) {\n");
        str_.append("        $return integer + _other;.;intValue():\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $int sum(java.lang.Long _other, java.lang.Long _otherTwo) {\n");
        str_.append("        $return integer + _other;.;intValue() + _otherTwo;.;intValue():\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal java.lang.String getOverridenOne(java.lang.String _string) {\n");
        str_.append("        $return \"one\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenOne(java.lang.Object _string) {\n");
        str_.append("        $return \"two\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenOne(java.lang.Boolean _string) {\n");
        str_.append("        $return \"three\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenTwo(java.lang.String _string) {\n");
        str_.append("        $return \"one\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenTwo(java.lang.Object _string) {\n");
        str_.append("        $return \"two\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenThree(java.lang.Double _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenThree(java.lang.Long _double) {\n");
        str_.append("        $return \"Long\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenThree($double _double) {\n");
        str_.append("        $return \"double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenThree($long _double) {\n");
        str_.append("        $return \"long\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenFour(java.lang.Long _double) {\n");
        str_.append("        $return \"Long\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenFour($long _double) {\n");
        str_.append("        $return \"long\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenFive(java.lang.Long _double) {\n");
        str_.append("        $return \"Long\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenFive($double _double) {\n");
        str_.append("        $return \"double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenSix(java.lang.Long _double) {\n");
        str_.append("        $return \"Long\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenSix($long _double) {\n");
        str_.append("        $return \"long\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenSix(java.lang.Double _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $package $normal $int sum() {\n");
        str_.append("        $return integer + privateInt:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal java.lang.String getStringElt($int _ind) {\n");
        str_.append("        $return strings.get(_ind;.;):\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal code.util.StringList getStrings() {\n");
        str_.append("        $return strings:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $void setStrings(code.util.StringList _strings) {\n");
        str_.append("        strings = _strings;.;:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("\n");
        str_.append("    $public $normal java.lang.String internMethod() {\n");
        str_.append("        $return \"sample\":\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $package $normal java.lang.String privateMethod() {\n");
        str_.append("        $return \"sample\":\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal java.lang.String getString() {\n");
        str_.append("        $return string:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $void setString(java.lang.String _string) {\n");
        str_.append("        string = _string;.;:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $char getMyChar() {\n");
        str_.append("        $return myChar:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $void setMyChar($char _myChar) {\n");
        str_.append("        myChar = _myChar;.;:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $boolean isDisplayed() {\n");
        str_.append("        $return displayed:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $void setDisplayed($boolean _displayed) {\n");
        str_.append("        displayed = _displayed;.;:\n");
        str_.append("    }\n");
        str_.append("}\n");
        str_.append("\n");
        str_.append("$public $class code.formathtml.classes.CompositeSec {\n");
        str_.append("\n");
        str_.append("    $public $int integer:\n");
        str_.append("\n");
        str_.append("    $public $normal $int getInteger() {\n");
        str_.append("        $return integer:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $void setInteger($int _integer) {\n");
        str_.append("        integer = _integer;.;:\n");
        str_.append("    }\n");
        str_.append("}\n");
        str_.append("\n");
        str_.append("\n");
        str_.append("$public $class code.formathtml.classes.BeanOne {\n");
        str_.append("\n");
        str_.append("    $public Composite composite = $new Composite():\n");
        str_.append("\n");
        str_.append("    $public() {\n");
        str_.append("        composite.setStrings($new code.util.StringList()):\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("\n");
        str_.append("    $public $normal Composite getComposite() {\n");
        str_.append("        $return composite:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("}\n");
        return str_.toString();
    }
    private static Argument simpleCaculateEl(String _el, ContextEl _context) {
        return caculateEl(_el, _context, true);
    }
    private static Argument caculateEl(String _el, ContextEl _context, boolean _static) {
        _context.setAnalyzing(new AnalyzedPageEl());
        if (!_context.isEmptyPages()) {
            _context.getAnalyzing().setGlobalClass(_context.getGlobalClass());
            _context.getAnalyzing().setLocalVars(_context.getLastPage().getLocalVars());
            _context.getAnalyzing().setVars(_context.getLastPage().getVars());
            _context.getAnalyzing().setCatchVars(_context.getLastPage().getCatchVars());
            _context.getAnalyzing().getParameters().putAllMap(_context.getLastPage().getParameters());
        } else {
            _context.getAnalyzing().setGlobalClass(_context.getGlobalClass());
            addImportingPage(_context);
        }
        return caculateCustEl(_el, _context, _static);
    }
    private static Argument caculateCustEl(String _el, ContextEl _context, boolean _static) {
        Calculation calc_ = Calculation.staticCalculation(_static);
        CustList<ExecOperationNode> ops_ = ElUtil.getAnalyzedOperations(_el, _context, calc_);
        _context.setAnalyzing(null);
        ExpressionLanguage el_ = new ExpressionLanguage(ops_);
        return el_.calculateMember(_context);
    }
    private static void addImportingPage(ContextEl _conf) {
        _conf.addPage(new MethodPageEl());
    }
    private static void addBean(ContextEl _conf, Object _bean, String _beanClass) {
        _conf.getLastPage().setGlobalArgumentStruct(StdStruct.newInstance(_bean, _beanClass));
        _conf.setGlobalClass(_beanClass);
    }

    private static ContextEl contextEl() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        return cont_;
    }
    private static ContextEl contextEl(StringMap<String> _files) {
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(_files, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        return cont_;
    }
}
