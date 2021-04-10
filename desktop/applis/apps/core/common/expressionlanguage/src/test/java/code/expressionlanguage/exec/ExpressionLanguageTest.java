package code.expressionlanguage.exec;

import code.expressionlanguage.AnalyzedTestContext;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecFieldBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.CommonMethodPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.methods.ProcessMethodCommon;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.util.StringMap;
import org.junit.Test;

public final class ExpressionLanguageTest extends ProcessMethodCommon {

    private static final String ARR_INT = "[$int";
    private static final String ARR_ARR_INT = "[[$int";
    private static final String ARR_INTEGER = "[java.lang.Integer";
    private static final String ARR_ARR_INTEGER = "[[java.lang.Integer";
    private static final String COMPOSITE = "code.formathtml.classes.Composite";

    @Test
    public void processEl0Test() {
        Argument arg_ = directCalculate("$math.mod(5,2)");
        assertEq(1L, getNumber(arg_));
    }
    @Test
    public void processEl1Test() {
        Argument arg_ = directCalculate("5");
        assertEq(5L, getNumber(arg_));
    }

    @Test
    public void processEl2Test() {
        Argument arg_ = directCalculate("$static(java.lang.Long).MAX_VALUE");
        assertEq(Long.MAX_VALUE, getNumber(arg_));
    }

    @Test
    public void processEl3Test() {
        Argument arg_ = directCalculate("(1+2)*3");
        assertEq(9L, getNumber(arg_));
    }

    @Test
    public void processEl4Test() {
        Argument arg_ = directCalculate("1- -1");
        assertEq(2L, getNumber(arg_));
    }

    @Test
    public void processEl5Test() {
        Argument arg_ = directCalculate("1+2*3");
        assertEq(7L, getNumber(arg_));
    }

    @Test
    public void processEl6Test() {
        Argument arg_ = directCalculate("- -1");
        assertEq(1L, getNumber(arg_));
    }

    @Test
    public void processEl7Test() {
        Argument arg_ = directCalculate("$static($math).abs(-8l)");
        assertEq(8L, getNumber(arg_));
    }

    @Test
    public void processEl8Test() {
        Argument arg_ = directCalculate("$static($math).abs(8l)");
        assertEq(8L, getNumber(arg_));
    }

    @Test
    public void processEl8_Test() {
        Argument arg_ = directCalculate("$static($math).abs(8f)");
        assertEq(8.0, getDouble(arg_));
    }

    @Test
    public void processEl_8Test() {
        Argument arg_ = directCalculate("$static($math).abs(8d)");
        assertEq(8.0, getDouble(arg_));
    }
    @Test
    public void processEl9Test() {
        Argument arg_ = calculateIndirect(COMPOSITE);
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl10Test() {
        Argument arg_ = directCalculate("40908c");
        assertEq(40908, getNumber(arg_));
    }

    @Test
    public void processEl11Test() {
        Argument arg_ = directCalculate("'\\u9fcb'");
        assertEq(40907, getNumber(arg_));
    }

    @Test
    public void processEl12Test() {
        Argument arg_ = directCalculate("'\\\\'");
        assertEq('\\', getNumber(arg_));
    }

    @Test
    public void processEl13Test() {
        Argument arg_ = directCalculate("'\\''");
        assertEq('\'', getNumber(arg_));
    }

    @Test
    public void processEl14Test() {
        Argument arg_ = directCalculate("'\"'");
        assertEq('"', getNumber(arg_));
    }

    @Test
    public void processEl15Test() {
        Argument arg_ = directCalculate("'\\n'");
        assertEq('\n', getNumber(arg_));
    }

    @Test
    public void processEl16Test() {
        Argument arg_ = calculateIndirectLocalVars("v", COMPOSITE);
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl17Test() {
        Argument arg_ = calculateIndirectLoopVars("v", COMPOSITE);
        assertEq(0, getNumber(arg_));
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
        assertEq(-1, getNumber(arg_));
    }

    @Test
    public void processEl31Test() {
        Argument arg_ = directCalculate("$static($math).abs(-8i)");
        assertEq(8, getNumber(arg_));
    }

    @Test
    public void processEl32Test() {
        Argument arg_ = directCalculate("$static($math).abs(8i)");
        assertEq(8, getNumber(arg_));
    }

    @Test
    public void processEl33Test() {
        Argument arg_ = directCalculate("$static($math).abs(-8I)");
        assertEq(8, getNumber(arg_));
    }

    @Test
    public void processEl34Test() {
        Argument arg_ = directCalculate("$static($math).abs(8I)");
        assertEq(8, getNumber(arg_));
    }

    @Test
    public void processEl35Test() {
        Argument arg_ = directCalculate("$static($math).abs(-8L)");
        assertEq(8L, getNumber(arg_));
    }

    @Test
    public void processEl36Test() {
        Argument arg_ = directCalculate("$static($math).abs(8L)");
        assertEq(8L, getNumber(arg_));
    }

    @Test
    public void processEl37Test() {
        Argument arg_ = directCalculate("\"\\nnew line\"");
        assertEq("\nnew line", getString(arg_));
    }

    @Test
    public void processEl38Test() {
        Argument arg_ = directCalculate("\"\".trim()");
        assertEq("", getString(arg_));
    }

    @Test
    public void processEl39Test() {
        Argument arg_ = directCalculate("$null $instanceof java.lang.Object");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl40Test() {
        Argument arg_ = directCalculate("\"abc\".trim()");
        assertEq("abc", getString(arg_));
    }

    @Test
    public void processEl41Test() {
        Argument arg_ = directCalculate("\" abc\".trim()");
        assertEq("abc", getString(arg_));
    }

    @Test
    public void processEl42Test() {
        Argument arg_ = directCalculate("\"abc \".trim()");
        assertEq("abc", getString(arg_));
    }

    @Test
    public void processEl43Test() {
        Argument arg_ = directCalculate("\" abc \".trim()");
        assertEq("abc", getString(arg_));
    }

    @Test
    public void processEl44Test() {
        Argument arg_ = directCalculate("\" \".trim()");
        assertEq("", getString(arg_));
    }

    @Test
    public void processEl45Test() {
        Argument arg_ = directCalculate("\"hello word\".splitStrings(\"e\",\"o\")");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(4, res_.length);
        assertEq("h", ((StringStruct) res_[0]).getInstance());
        assertEq("ll", ((StringStruct) res_[1]).getInstance());
        assertEq(" w", ((StringStruct) res_[2]).getInstance());
        assertEq("rd", ((StringStruct) res_[3]).getInstance());
    }

    @Test
    public void processEl63Test() {
        Argument arg_ = directCalculate("$new $int[1i]");
        ArrayStruct res_ = (ArrayStruct) arg_.getStruct();
        assertEq(ARR_INT, res_.getClassName());
        assertEq(1, res_.getInstance().length);
        assertEq(0, ((NumberStruct) res_.getInstance()[0]).intStruct());
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
        assertEq((byte) 127, getNumber(arg_));
    }

    @Test
    public void processEl77Test() {
        Argument arg_ = directCalculate("(\"Hello\\\\\"+\"World\").length()");
        assertEq(11, getNumber(arg_));
    }

    @Test
    public void processEl78Test() {
        Argument arg_ = directCalculate("(\"Hello\\\"\"+\"World\").length()");
        assertEq(11, getNumber(arg_));
    }

    @Test
    public void processEl79Test() {
        Argument arg_ = directCalculate("(\"Hello\\\\\"+'\\\\').length()");
        assertEq(7, getNumber(arg_));
    }

    @Test
    public void processEl80Test() {
        Argument arg_ = directCalculate("(\"Hello\\\"\"+'\\'').length()");
        assertEq(7, getNumber(arg_));
    }

    @Test
    public void processEl87Test() {
        Argument arg_ = directCalculate("$bool(1>0,0i,1i)");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl88Test() {
        Argument arg_ = directCalculate("$bool(1<0,0i,1i)");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl89Test() {
        Argument arg_ = directCalculate("$bool(1>0,0i,1i/0i)");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl90Test() {
        Argument arg_ = directCalculate("$bool(1<0,1i/0i,1i)");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl95Test() {
        Argument arg_ = directCalculate("($new $int[1i])[0i]");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl96Test() {
        Argument arg_ = directCalculate("$new $int[]{2i}");
        ArrayStruct res_ = (ArrayStruct) arg_.getStruct();
        assertEq(ARR_INT, res_.getClassName());
        Struct[] o_ = res_.getInstance();
        assertEq(1, o_.length);
        assertEq(2, ((NumberStruct) o_[0]).intStruct());
    }

    @Test
    public void processEl97Test() {
        Argument arg_ = directCalculate("$new $int[]{3i,7i}");
        ArrayStruct res_ = (ArrayStruct) arg_.getStruct();
        assertEq(ARR_INT, res_.getClassName());
        Struct[] o_ = res_.getInstance();
        assertEq(2, o_.length);
        assertEq(3, ((NumberStruct) o_[0]).intStruct());
        assertEq(7, ((NumberStruct) o_[1]).intStruct());
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
        assertEq(3, ((NumberStruct) o_[0]).intStruct());
        assertEq(7, ((NumberStruct) o_[1]).intStruct());
    }

    @Test
    public void processEl119Test() {
        Argument arg_ = directCalculate("(1y+2y)*3");
        assertEq(9L, getNumber(arg_));
    }

    @Test
    public void processEl120Test() {
        Argument arg_ = directCalculate("(1s+2y)*3");
        assertEq(9L, getNumber(arg_));
    }

    @Test
    public void processEl121Test() {
        Argument arg_ = directCalculate("- -1y");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl122Test() {
        Argument arg_ = directCalculate("-1y");
        assertEq(-1, getNumber(arg_));
    }

    @Test
    public void processEl123Test() {
        Argument arg_ = directCalculate("($int)(java.lang.Byte.MAX_VALUE+java.lang.Byte.MAX_VALUE)");
        int max_ = Byte.MAX_VALUE + Byte.MAX_VALUE;
        assertEq(max_, getNumber(arg_));
    }

    @Test
    public void processEl123FailTest() {
        Argument arg_ = directCalculate("+1y");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl124Test() {
        Argument arg_ = directCalculate("+-1y");
        assertEq(-1, getNumber(arg_));
    }

    @Test
    public void processEl125Test() {
        Argument arg_ = directCalculate("-.25e0+.5");
        assertEq(0.25d, getDouble(arg_));
    }

    @Test
    public void processEl128Test() {
        Argument arg_ = directCalculate("1_0+2*3");
        assertEq(16L, getNumber(arg_));
    }

    @Test
    public void processEl129Test() {
        Argument arg_ = directCalculate("$static($math).mod(-8l,3l)");
        assertEq(1L, getNumber(arg_));
    }

    @Test
    public void processEl130Test() {
        Argument arg_ = directCalculate("$static($math).quot(-8l,3l)");
        assertEq(-3L, getNumber(arg_));
    }

    @Test
    public void processEl131Test() {
        Argument arg_ = directCalculate("$new $int[1i][1i]");
        ArrayStruct res_ = (ArrayStruct) arg_.getStruct();
        assertEq(ARR_ARR_INT, res_.getClassName());
        assertEq(1, res_.getInstance().length);
        assertEq(ARR_INT, ((ArrayStruct) res_.getInstance()[0]).getClassName());
        assertEq(1, ((ArrayStruct) res_.getInstance()[0]).getInstance().length);
        assertEq(0, ((NumberStruct) ((ArrayStruct) res_.getInstance()[0]).getInstance()[0]).intStruct());
    }

    @Test
    public void processEl132Test() {
        Argument arg_ = directCalculate("$new java.lang.Integer[1i][1i]");
        ArrayStruct res_ = (ArrayStruct) arg_.getStruct();
        assertEq(ARR_ARR_INTEGER, res_.getClassName());
        assertEq(1, res_.getInstance().length);
        assertEq(ARR_INTEGER, ((ArrayStruct) res_.getInstance()[0]).getClassName());
        assertSame(NullStruct.NULL_VALUE, (((ArrayStruct) res_.getInstance()[0]).getInstance())[0]);
    }

    @Test
    public void processEl133Test() {
        Argument arg_ = directCalculate("($double)(1 + 2) * 3.0");
        assertEq(9L, getNumber(arg_));
    }

    @Test
    public void processEl134Test() {
        Argument arg_ = directCalculate(" 2.0 + ($double)$static($math). quot( -8l, 3l) + 3.0");
        assertEq(2L, getNumber(arg_));
    }

    @Test
    public void processEl135Test() {
        Argument arg_ = directCalculate("1 + 2 ");
        assertEq(3L, getNumber(arg_));
    }

    @Test
    public void processEl136Test() {
        Argument arg_ = directCalculate("1. + 2. ");
        assertEq(3L, getNumber(arg_));
    }

    @Test
    public void processEl137Test() {
        Argument arg_ = directCalculate("1.d + 2.d ");
        assertEq(3L, getNumber(arg_));
    }

    @Test
    public void processEl138Test() {
        Argument arg_ = directCalculate("-.2_5e0+.5");
        assertEq(0.25d, getDouble(arg_));
    }

    @Test
    public void processEl139Test() {
        Argument arg_ = directCalculate("-.25e0_0+.5");
        assertEq(0.25d, getDouble(arg_));
    }

    @Test
    public void processEl140Test() {
        Argument arg_ = directCalculate("1_0.d + 2.d ");
        assertEq(12L, getNumber(arg_));
    }

    @Test
    public void processEl141Test() {
        Argument arg_ = directCalculate("1.05e1");
        assertEq(10.5d, getDouble(arg_));
    }

    @Test
    public void processEl142Test() {
        Argument arg_ = directCalculate("1.00625e1");
        assertEq(10.0625d, getDouble(arg_));
    }

    @Test
    public void processEl143Test() {
        Argument arg_ = directCalculate("100.625e-1");
        assertEq(10.0625d, getDouble(arg_));
    }

    @Test
    public void processEl144Test() {
        Argument arg_ = directCalculate("100.625");
        assertEq(100.625d, getDouble(arg_));
    }

    @Test
    public void processEl145Test() {
        Argument arg_ = directCalculate("123456789123456789123456789.0");
        assertEq(1.2345678912345678912e26, getDouble(arg_));
    }

    @Test
    public void processEl147Test() {
        Argument arg_ = directCalculate("123456789123456789123456789.");
        assertEq(1.2345678912345678912e26, getDouble(arg_));
    }

    @Test
    public void processEl148Test() {
        Argument arg_ = directCalculate("123456789123456789123456789.e-1");
        assertEq(1.2345678912345678912e25, getDouble(arg_));
    }

    @Test
    public void processEl149Test() {
        Argument arg_ = directCalculate("123456789123456789123456789.e1");
        assertEq(1.2345678912345678912e27, getDouble(arg_));
    }

    @Test
    public void processEl150Test() {
        Argument arg_ = directCalculate("123456.e1");
        assertEq(1234560.0, getDouble(arg_));
    }

    @Test
    public void processEl151Test() {
        Argument arg_ = directCalculate(".078125e-1");
        assertEq(.078125e-1, getDouble(arg_));
    }

    @Test
    public void processEl152Test() {
        Argument arg_ = directCalculate("123456789123456789123456789.0e-36");
        assertEq(1.2345678912345678912e-10, getDouble(arg_));
    }

    @Test
    public void processEl153Test() {
        Argument arg_ = directCalculate("0.0e-36");
        assertEq(0.0, getDouble(arg_));
    }

    @Test
    public void processEl154Test() {
        Argument arg_ = directCalculate("-0.0e-36");
        assertEq(-0.0, getDouble(arg_));
    }

    @Test
    public void processEl155Test() {
        Argument arg_ = directCalculate("0.625e-1");
        assertEq(0.0625, getDouble(arg_));
    }

    @Test
    public void processEl156Test() {
        Argument arg_ = directCalculate(".625e-1");
        assertEq(0.0625, getDouble(arg_));
    }

    @Test
    public void processEl157Test() {
        Argument arg_ = directCalculate("0.625e1");
        assertEq(6.25, getDouble(arg_));
    }

    @Test
    public void processEl158Test() {
        Argument arg_ = directCalculate(".625e1");
        assertEq(6.25, getDouble(arg_));
    }

    @Test
    public void processEl159Test() {
        Argument arg_ = directCalculate("0.625e0");
        assertEq(0.625, getDouble(arg_));
    }

    @Test
    public void processEl160Test() {
        Argument arg_ = directCalculate(".625e0");
        assertEq(0.625, getDouble(arg_));
    }

    @Test
    public void processEl161Test() {
        Argument arg_ = directCalculate("-.625e1");
        assertEq(-6.25, getDouble(arg_));
    }

    @Test
    public void processEl162Test() {
        Argument arg_ = directCalculate("-.6e1");
        assertEq(-6.0, getDouble(arg_));
    }

    @Test
    public void processEl163Test() {
        Argument arg_ = directCalculate("-.60e1");
        assertEq(-6.0, getDouble(arg_));
    }

    @Test
    public void processEl164Test() {
        Argument arg_ = directCalculate(".6e1");
        assertEq(6.0, getDouble(arg_));
    }

    @Test
    public void processEl165Test() {
        Argument arg_ = directCalculate(".6e2");
        assertEq(60.0, getDouble(arg_));
    }

    @Test
    public void processEl166Test() {
        Argument arg_ = directCalculate("123456789123456789123456789.1e1");
        assertEq(1.2345678912345678912e27, getDouble(arg_));
    }

    @Test
    public void processEl167Test() {
        Argument arg_ = directCalculate("100.e-1");
        assertEq(10.0, getDouble(arg_));
    }

    @Test
    public void processEl168Test() {
        Argument arg_ = directCalculate("-100.e-1");
        assertEq(-10.0, getDouble(arg_));
    }

    @Test
    public void processEl169Test() {
        Argument arg_ = directCalculate("-1.e1");
        assertEq(-10.0, getDouble(arg_));
    }

    @Test
    public void processEl170Test() {
        Argument arg_ = directCalculate("-1.");
        assertEq(-1.0, getDouble(arg_));
    }

    @Test
    public void processEl171Test() {
        Argument arg_ = directCalculate("1e-123456789123456789123");
        assertEq(0.0, getDouble(arg_));
    }

    @Test
    public void processEl172Test() {
        Argument arg_ = directCalculate("-1e-123456789123456789123");
        assertEq(-0.0, getDouble(arg_));
    }

    @Test
    public void processEl173Test() {
        Argument arg_ = directCalculate("1e123456789123456789123");
        assertEq(Double.POSITIVE_INFINITY, getDouble(arg_));
    }

    @Test
    public void processEl174Test() {
        Argument arg_ = directCalculate("-1e123456789123456789123");
        assertEq(Double.NEGATIVE_INFINITY, getDouble(arg_));
    }

    @Test
    public void processEl175Test() {
        Argument arg_ = directCalculate("'\\u9FCB'");
        assertEq(40907, getNumber(arg_));
    }

    @Test
    public void processEl176Test() {
        Argument arg_ = directCalculate("\"\\u9FCB\"");
        assertEq("\u9fcb", getString(arg_));
    }

    @Test
    public void processEl177Test() {
        Argument arg_ = directCalculate("\"\\u9fcb\"");
        assertEq("\u9fcb", getString(arg_));
    }

    @Test
    public void processEl178Test() {
        Argument arg_ = directCalculate("$static(java.lang.Long) .MAX_VALUE");
        assertEq(Long.MAX_VALUE, getNumber(arg_));
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
        assertEq(5L, getNumber(arg_));
    }

    @Test
    public void processEl199Test() {
        Argument arg_ = directCalculate("$($byte)5");
        assertEq(5L, getNumber(arg_));
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
        assertEq("code.expressionlanguage.exceptions.DynamicCastClassException", ((ErrorStruct) exc_).getClassName());
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
        assertEq(99, getNumber(arg_));
    }

    @Test
    public void processEl211Test() {
        Argument arg_ = directCalculate("\"\"+$new $char[]{'1','2'}[0]+$new $char[]{'1','2'}[1]");
        assertEq("12", getString(arg_));
    }

    @Test
    public void processEl212Test() {
        Argument arg_ = directCalculate("('1'+'2')*3i");
        assertEq(297, getNumber(arg_));
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
        assertEq(5049, getNumber(arg_));
    }

    @Test
    public void processEl219Test() {
        Argument arg_ = directCalculate("6 + $($int) - $static($math).quot(8,5) - 2");
        assertEq(3, getNumber(arg_));
    }

    @Test
    public void processEl320Test() {
        Argument arg_ = directCalculate("0x1");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl321Test() {
        Argument arg_ = directCalculate("0xffff_ffff");
        assertEq(-1, getNumber(arg_));
    }

    @Test
    public void processEl322Test() {
        Argument arg_ = directCalculate("0x1p0");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl323Test() {
        Argument arg_ = directCalculate("0x1.0p0");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl324Test() {
        Argument arg_ = directCalculate("0x1.1p4");
        assertEq(17, getNumber(arg_));
    }

    @Test
    public void processEl325Test() {
        Argument arg_ = directCalculate("0x110.0p-4");
        assertEq(17, getNumber(arg_));
    }

    @Test
    public void processEl326Test() {
        Argument arg_ = directCalculate("0x1l");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl327Test() {
        Argument arg_ = directCalculate("0x1xl");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl328Test() {
        Argument arg_ = directCalculate("0b1");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl329Test() {
        Argument arg_ = directCalculate("0b10");
        assertEq(2, getNumber(arg_));
    }

    @Test
    public void processEl330Test() {
        Argument arg_ = directCalculate("01");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl331Test() {
        Argument arg_ = directCalculate("017_7777_7777_7777_7777_7777l");
        assertEq(-1, getNumber(arg_));
    }

    @Test
    public void processEl332Test() {
        Argument arg_ = directCalculate("007_7777_7777_7777_7777_7777l");
        assertEq(Long.MAX_VALUE, getNumber(arg_));
    }

    @Test
    public void processEl333Test() {
        Argument arg_ = directCalculate("0377_7777_7777i");
        assertEq(-1, getNumber(arg_));
    }

    @Test
    public void processEl334Test() {
        Argument arg_ = directCalculate("0177_7777_7777i");
        assertEq(Integer.MAX_VALUE, getNumber(arg_));
    }

    @Test
    public void processEl335Test() {
        Argument arg_ = directCalculate("1&2");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl336Test() {
        Argument arg_ = directCalculate("1|2");
        assertEq(3, getNumber(arg_));
    }

    @Test
    public void processEl337Test() {
        Argument arg_ = directCalculate("1^2");
        assertEq(3, getNumber(arg_));
    }

    @Test
    public void processEl338Test() {
        Argument arg_ = directCalculate("1^3");
        assertEq(2, getNumber(arg_));
    }

    @Test
    public void processEl339Test() {
        Argument arg_ = directCalculate("1c|2c");
        assertEq(3, getNumber(arg_));
    }

    @Test
    public void processEl340Test() {
        Argument arg_ = directCalculate("~0");
        assertEq(-1, getNumber(arg_));
    }

    @Test
    public void processEl341Test() {
        Argument arg_ = directCalculate("~-1");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl342Test() {
        Argument arg_ = directCalculate("1<<2");
        assertEq(4, getNumber(arg_));
    }

    @Test
    public void processEl343Test() {
        Argument arg_ = directCalculate("4>>2");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl344Test() {
        Argument arg_ = directCalculate("1<<34");
        assertEq(4, getNumber(arg_));
    }

    @Test
    public void processEl345Test() {
        Argument arg_ = directCalculate("4>>34");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl346Test() {
        Argument arg_ = directCalculate("1l<<2");
        assertEq(4, getNumber(arg_));
    }

    @Test
    public void processEl347Test() {
        Argument arg_ = directCalculate("4l>>2");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl348Test() {
        Argument arg_ = directCalculate("1l<<66");
        assertEq(4, getNumber(arg_));
    }

    @Test
    public void processEl349Test() {
        Argument arg_ = directCalculate("4l>>66");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl350Test() {
        Argument arg_ = directCalculate("-1<<2");
        assertEq(-4, getNumber(arg_));
    }

    @Test
    public void processEl351Test() {
        Argument arg_ = directCalculate("-4>>2");
        assertEq(-1, getNumber(arg_));
    }

    @Test
    public void processEl352Test() {
        Argument arg_ = directCalculate("0200_0000_0000i");
        assertEq(Integer.MIN_VALUE, getNumber(arg_));
    }

    @Test
    public void processEl353Test() {
        Argument arg_ = directCalculate("0x1.0xd");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl354Test() {
        Argument arg_ = directCalculate("0x1.0p1d");
        assertEq(2, getNumber(arg_));
    }

    @Test
    public void processEl355Test() {
        Argument arg_ = directCalculate("0x1p1d");
        assertEq(2, getNumber(arg_));
    }

    @Test
    public void processEl356Test() {
        Argument arg_ = directCalculate("0x1.8");
        assertEq(1.5d, getDouble(arg_));
    }

    @Test
    public void processEl357Test() {
        Argument arg_ = directCalculate("0x1.8xd");
        assertEq(1.5d, getDouble(arg_));
    }

    @Test
    public void processEl358Test() {
        Argument arg_ = directCalculate("0377y");
        assertEq(-1, getNumber(arg_));
    }

    @Test
    public void processEl359Test() {
        Argument arg_ = directCalculate("0200y");
        assertEq(Byte.MIN_VALUE, getNumber(arg_));
    }

    @Test
    public void processEl360Test() {
        Argument arg_ = directCalculate("0377777s");
        assertEq(-1, getNumber(arg_));
    }

    @Test
    public void processEl361Test() {
        Argument arg_ = directCalculate("0200000s");
        assertEq(Short.MIN_VALUE, getNumber(arg_));
    }

    @Test
    public void processEl362Test() {
        Argument arg_ = directCalculate("$new java.lang.String(\"\u1000\".getBytes())");
        assertEq("\u1000", getString(arg_));
    }

    @Test
    public void processEl363Test() {
        Argument arg_ = directCalculate("$new java.lang.String(\"\u0800\".getBytes())");
        assertEq("\u0800", getString(arg_));
    }

    @Test
    public void processEl364Test() {
        Argument arg_ = directCalculate("$new java.lang.String(\"\u07FF\".getBytes())");
        assertEq("\u07FF", getString(arg_));
    }

    @Test
    public void processEl365Test() {
        Argument arg_ = directCalculate("$new java.lang.String(\"\u0050\".getBytes())");
        assertEq("\u0050", getString(arg_));
    }

    @Test
    public void processEl366Test() {
        Argument arg_ = directCalculate("0xafff_ffff<<<1");
        assertEq(-536870914, getNumber(arg_));
    }

    @Test
    public void processEl367Test() {
        Argument arg_ = directCalculate("0xafff_ffff<<1");
        assertEq(1610612734, getNumber(arg_));
    }

    @Test
    public void processEl368Test() {
        Argument arg_ = directCalculate("0xafff_ffff>>>1");
        assertEq(1476395007, getNumber(arg_));
    }

    @Test
    public void processEl369Test() {
        Argument arg_ = directCalculate("0xafff_ffff<<<<1");
        assertEq(1610612735, getNumber(arg_));
    }

    @Test
    public void processEl370Test() {
        Argument arg_ = directCalculate("0xafff_ffff>>>>1");
        assertEq(-671088641, getNumber(arg_));
    }

    @Test
    public void processEl371Test() {
        Argument arg_ = directCalculate("0.5d*2d");
        NumberStruct res_ = (NumberStruct) arg_.getStruct();
        assertTrue(res_ instanceof DoubleStruct);
        assertEq(1d, res_.doubleStruct());
    }

    @Test
    public void processEl372Test() {
        Argument arg_ = directCalculate("$math.random()");
        NumberStruct res_ = (NumberStruct)  arg_.getStruct();
        assertTrue(res_ instanceof DoubleStruct);
        assertTrue(res_.doubleStruct() >= 0.0d);
        assertTrue(res_.doubleStruct() < 1.0d);
    }

    @Test
    public void processEl373Test() {
        Argument arg_ = directCalculate("$math.random(8l)");
        NumberStruct res_ = (NumberStruct)  arg_.getStruct();
        assertTrue(res_ instanceof LongStruct);
        assertTrue(res_.longStruct() >= 0);
        assertTrue(res_.longStruct() < 8);
    }

    @Test
    public void processEl372_Test() {
        Argument arg_ = directCalculate("$math.natRandom()");
        NumberStruct res_ = (NumberStruct)  arg_.getStruct();
        assertTrue(res_ instanceof DoubleStruct);
        assertTrue(res_.doubleStruct() >= 0.0d);
        assertTrue(res_.doubleStruct() < 1.0d);
    }

    @Test
    public void processEl373_Test() {
        Argument arg_ = directCalculate("$math.natRandom(8l)");
        NumberStruct res_ = (NumberStruct)  arg_.getStruct();
        assertTrue(res_ instanceof LongStruct);
        assertTrue(res_.longStruct() >= 0);
        assertTrue(res_.longStruct() < 8);
    }

    @Test
    public void processEl374Test() {
        Argument arg_ = directCalculate("(Double)1.5 $instanceof Double");
        boolean isTrue_ = arg_.isTrue();
        assertTrue(isTrue_);
    }

    @Test
    public void processEl375Test() {
        Argument arg_ = directCalculate("\"my_string\".indexOf(\"y\")");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl376Test() {
        Argument arg_ = directCalculate("\"my_string\".indexOf(\"y\",1)");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl377Test() {
        Argument arg_ = directCalculate("\"my_string\".indexOf(\"y\",2)");
        assertEq(-1, getNumber(arg_));
    }

    @Test
    public void processEl378Test() {
        Argument arg_ = directCalculate("\"my_string\".indexOf(\"a\")");
        assertEq(-1, getNumber(arg_));
    }

    @Test
    public void processEl379Test() {
        Argument arg_ = directCalculate("\"my_string\".indexOf('y')");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl380Test() {
        Argument arg_ = directCalculate("\"my_string\".indexOf('y',1)");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl381Test() {
        Argument arg_ = directCalculate("\"my_string\".indexOf('y',2)");
        assertEq(-1, getNumber(arg_));
    }

    @Test
    public void processEl382Test() {
        Argument arg_ = directCalculate("\"my_string\".indexOf('a')");
        assertEq(-1, getNumber(arg_));
    }

    @Test
    public void processEl383Test() {
        Argument arg_ = directCalculate("\"my_string\".lastIndexOf(\"y\")");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl384Test() {
        Argument arg_ = directCalculate("\"my_string\".lastIndexOf(\"y\",1)");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl385Test() {
        Argument arg_ = directCalculate("\"my_string\".lastIndexOf(\"y\",2)");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl386Test() {
        Argument arg_ = directCalculate("\"my_string\".lastIndexOf(\"a\")");
        assertEq(-1, getNumber(arg_));
    }

    @Test
    public void processEl387Test() {
        Argument arg_ = directCalculate("\"my_string\".lastIndexOf('y')");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl388Test() {
        Argument arg_ = directCalculate("\"my_string\".lastIndexOf('y',1)");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl389Test() {
        Argument arg_ = directCalculate("\"my_string\".lastIndexOf('y',2)");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl390Test() {
        Argument arg_ = directCalculate("\"my_string\".lastIndexOf('a')");
        assertEq(-1, getNumber(arg_));
    }

    @Test
    public void processEl391Test() {
        Argument arg_ = directCalculate("\"my_string\".replace('a','t')");
        assertEq("my_string", getString(arg_));
    }

    @Test
    public void processEl392Test() {
        Argument arg_ = directCalculate("\"my_string\".replace('_','t')");
        assertEq("mytstring", getString(arg_));
    }

    @Test
    public void processEl393Test() {
        Argument arg_ = directCalculate("\"my_string\".replace(\"a\",\"t\")");
        assertEq("my_string", getString(arg_));
    }

    @Test
    public void processEl394Test() {
        Argument arg_ = directCalculate("\"my_string\".replace(\"_\",\"t\")");
        assertEq("mytstring", getString(arg_));
    }

    @Test
    public void processEl395Test() {
        Struct arg_ = directCalculateExc("\"my_string\".indexOf($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl396Test() {
        Struct arg_ = directCalculateExc("\"my_string\".indexOf($null,1)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl397Test() {
        Struct arg_ = directCalculateExc("\"my_string\".lastIndexOf($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl398Test() {
        Struct arg_ = directCalculateExc("\"my_string\".lastIndexOf($null,1)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl399Test() {
        Argument arg_ = directCalculate("\"my_string\".replace($null,\"t\")");
        assertEq("my_string", getString(arg_));
    }

    @Test
    public void processEl400Test() {
        Argument arg_ = directCalculate("\"my_string\".replace(\"\",$null)");
        assertEq("my_string", getString(arg_));
    }

    @Test
    public void processEl401Test() {
        Argument arg_ = directCalculate("\"my_string\".replace(\"_\",$null)");
        assertEq("mystring", getString(arg_));
    }

    @Test
    public void processEl402Test() {
        Argument arg_ = directCalculate("\"mystring\".replace(\"\",\"_\")");
        assertEq("_m_y_s_t_r_i_n_g_", getString(arg_));
    }

    @Test
    public void processEl403Test() {
        Struct arg_ = directCalculateExc("\"my_string\".substring(-1)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-1<0", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl404Test() {
        Struct arg_ = directCalculateExc("\"my_string\".substring(10)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("10>9", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl405Test() {
        Argument arg_ = directCalculate("\"my_string\".substring(1)");
        assertEq("y_string", getString(arg_));
    }

    @Test
    public void processEl406Test() {
        Struct arg_ = directCalculateExc("\"my_string\".substring(-1,2)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-1<0", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl407Test() {
        Struct arg_ = directCalculateExc("\"my_string\".substring(2,1)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("2>1", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl408Test() {
        Struct arg_ = directCalculateExc("\"my_string\".substring(1,10)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("10>9", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl409Test() {
        Argument arg_ = directCalculate("\"my_string\".substring(1,9)");
        assertEq("y_string", getString(arg_));
    }

    @Test
    public void processEl410Test() {
        Struct arg_ = directCalculateExc("\"my_string\".charAt(10)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("10>=9", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl411Test() {
        Struct arg_ = directCalculateExc("\"my_string\".charAt(-1)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-1<0", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl412Test() {
        Struct arg_ = directCalculateExc("\"my_string\".contains($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl413Test() {
        Argument arg_ = directCalculate("\"my_string\".contains(\"-\")");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl414Test() {
        Argument arg_ = directCalculate("\"my_string\".contains(\"_\")");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl415Test() {
        Struct arg_ = directCalculateExc("\"my_string\".startsWith($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processEl416Test() {
        Argument arg_ = directCalculate("\"my_string\".startsWith(\"_\")");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl417Test() {
        Argument arg_ = directCalculate("\"my_string\".startsWith(\"m\")");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl418Test() {
        Struct arg_ = directCalculateExc("\"my_string\".startsWith($null,0)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processEl419Test() {
        Argument arg_ = directCalculate("\"my_string\".startsWith(\"_\",0)");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl420Test() {
        Argument arg_ = directCalculate("\"my_string\".startsWith(\"_\",2)");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl421Test() {
        Struct arg_ = directCalculateExc("\"my_string\".endsWith($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processEl422Test() {
        Argument arg_ = directCalculate("\"my_string\".endsWith(\"_\")");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl423Test() {
        Argument arg_ = directCalculate("\"my_string\".endsWith(\"g\")");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl424Test() {
        Struct arg_ = directCalculateExc("\"my_string\".format($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processEl425Test() {
        Struct arg_ = directCalculateExc("\"my_string\".format((CharSequence)$null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processEl426Test() {
        Argument arg_ = directCalculate("\"{0}_{1}\".format(\"my\",\"string\")");
        assertEq("my_string", getString(arg_));
    }

    @Test
    public void processE427Test() {
        Argument arg_ = directCalculate("\"hello word\".splitStrings(2,\"e\",\"o\")");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("h", ((StringStruct) res_[0]).getInstance());
        assertEq("ll", ((StringStruct) res_[1]).getInstance());
    }

    @Test
    public void processE428Test() {
        Argument arg_ = directCalculate("\"hello word\".splitStrings(-1,\"e\",\"o\")");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(4, res_.length);
        assertEq("h", ((StringStruct) res_[0]).getInstance());
        assertEq("ll", ((StringStruct) res_[1]).getInstance());
        assertEq(" w", ((StringStruct) res_[2]).getInstance());
        assertEq("rd", ((StringStruct) res_[3]).getInstance());
    }

    @Test
    public void processE429Test() {
        Argument arg_ = directCalculate("\"hello word\".splitStrings(-2,\"e\",\"o\")");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(4, res_.length);
        assertEq("h", ((StringStruct) res_[0]).getInstance());
        assertEq("ll", ((StringStruct) res_[1]).getInstance());
        assertEq(" w", ((StringStruct) res_[2]).getInstance());
        assertEq("rd", ((StringStruct) res_[3]).getInstance());
    }
    @Test
    public void processE430Test() {
        Struct arg_ = directCalculateExc("\"hello word\".splitStrings($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE431Test() {
        Struct arg_ = directCalculateExc("\"hello word\".splitStrings((CharSequence)$null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE432Test() {
        Struct arg_ = directCalculateExc("\"hello word\".splitStrings(-1,$null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE433Test() {
        Struct arg_ = directCalculateExc("\"hello word\".splitStrings(-1,(CharSequence)$null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE434Test() {
        Struct arg_ = directCalculateExc("\"hello word\".splitChars($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE435Test() {
        Argument arg_ = directCalculate("\"hello word\".splitChars('e','o')");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(4, res_.length);
        assertEq("h", ((StringStruct) res_[0]).getInstance());
        assertEq("ll", ((StringStruct) res_[1]).getInstance());
        assertEq(" w", ((StringStruct) res_[2]).getInstance());
        assertEq("rd", ((StringStruct) res_[3]).getInstance());
    }
    @Test
    public void processE436Test() {
        Argument arg_ = directCalculate("\"hello word\".split(' ')");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
        assertEq("word", ((StringStruct) res_[1]).getInstance());
    }
    @Test
    public void processE437Test() {
        Argument arg_ = directCalculate("\"hello word\".split(' ',1)");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(1, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
    }
    @Test
    public void processE438Test() {
        Struct arg_ = directCalculateExc("\"hello word\".split($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE439Test() {
        Struct arg_ = directCalculateExc("\"hello word\".split($null,1)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE440Test() {
        Argument arg_ = directCalculate("\"hello word\".split(\" \")");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
        assertEq("word", ((StringStruct) res_[1]).getInstance());
    }
    @Test
    public void processE441Test() {
        Argument arg_ = directCalculate("\"hello word\".split(\" \",1)");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(1, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
    }
    @Test
    public void processE442Test() {
        Argument arg_ = directCalculate("\"hello word\".split(\" \",-1)");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
        assertEq("word", ((StringStruct) res_[1]).getInstance());
    }
    @Test
    public void processE443Test() {
        Argument arg_ = directCalculate("\"hello word\".split(\" \",-2)");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
        assertEq("word", ((StringStruct) res_[1]).getInstance());
    }
    @Test
    public void processE444Test() {
        Argument arg_ = directCalculate("\"hello word\".split(' ',-1)");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
        assertEq("word", ((StringStruct) res_[1]).getInstance());
    }
    @Test
    public void processE445Test() {
        Argument arg_ = directCalculate("\"hello word\".split(' ',-2)");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
        assertEq("word", ((StringStruct) res_[1]).getInstance());
    }

    @Test
    public void processE446Test() {
        Argument arg_ = directCalculate("\"hello word\".splitStrings(-1,\"e\",\"o\")");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(4, res_.length);
        assertEq("h", ((StringStruct) res_[0]).getInstance());
        assertEq("ll", ((StringStruct) res_[1]).getInstance());
        assertEq(" w", ((StringStruct) res_[2]).getInstance());
        assertEq("rd", ((StringStruct) res_[3]).getInstance());
    }

    @Test
    public void processE447Test() {
        Argument arg_ = directCalculate("\"hello word\".splitStrings(-2,\"e\",\"o\")");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(4, res_.length);
        assertEq("h", ((StringStruct) res_[0]).getInstance());
        assertEq("ll", ((StringStruct) res_[1]).getInstance());
        assertEq(" w", ((StringStruct) res_[2]).getInstance());
        assertEq("rd", ((StringStruct) res_[3]).getInstance());
    }
    @Test
    public void processE448Test() {
        Argument arg_ = directCalculate("\"hello word\".toCharArray()");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[$char", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(10, res_.length);
        assertEq('h', ((CharStruct) res_[0]).getChar());
        assertEq('e', ((CharStruct) res_[1]).getChar());
        assertEq('l', ((CharStruct) res_[2]).getChar());
        assertEq('l', ((CharStruct) res_[3]).getChar());
        assertEq('o', ((CharStruct) res_[4]).getChar());
        assertEq(' ', ((CharStruct) res_[5]).getChar());
        assertEq('w', ((CharStruct) res_[6]).getChar());
        assertEq('o', ((CharStruct) res_[7]).getChar());
        assertEq('r', ((CharStruct) res_[8]).getChar());
        assertEq('d', ((CharStruct) res_[9]).getChar());
    }
    @Test
    public void processE449Test() {
        Argument arg_ = directCalculate("\"hello word\".isEmpty()");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processE450Test() {
        Argument arg_ = directCalculate("\"\".isEmpty()");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processE451Test() {
        Argument arg_ = directCalculate("\"\".length()");
        assertEq(0,getNumber(arg_));
    }

    @Test
    public void processEl452Test() {
        Struct arg_ = directCalculateExc("\"my_string\".subSequence(-1,2)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-1<0", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl453Test() {
        Struct arg_ = directCalculateExc("\"my_string\".subSequence(2,1)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("2>1", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl454Test() {
        Struct arg_ = directCalculateExc("\"my_string\".subSequence(1,10)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("10>9", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl455Test() {
        Argument arg_ = directCalculate("\"my_string\".subSequence(1,9)");
        assertEq("y_string", getString(arg_));
    }
    @Test
    public void processE456Test() {
        Argument arg_ = directCalculate("\"hello word\".length()");
        assertEq(10,getNumber(arg_));
    }
    @Test
    public void processE457Test() {
        Struct arg_ = directCalculateExc("\"hello word\".regionMatches(0,$null,0,0)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE458Test() {
        Argument arg_ = directCalculate("\"hello word\".regionMatches(0,\"\",0,0)");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processE459Test() {
        Struct arg_ = directCalculateExc("\"hello word\".replaceMultiple($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE460Test() {
        Struct arg_ = directCalculateExc("\"hello word\".replaceMultiple((code.util.Replacement)$null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processE462Test() {
        Struct arg_ = directCalculateExc("\"hello word\".replaceMultiple($new code.util.Replacement($null,\"good\"))");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE463Test() {
        Struct arg_ = directCalculateExc("\"hello word\".replaceMultiple($new code.util.Replacement(\"hello\",$null))");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE464Test() {
        Argument arg_ = directCalculate("\"hello word\".replaceMultiple($new code.util.Replacement(\"hello\",\"good\"),$new code.util.Replacement(\"word\",\"world\"))");
        assertEq("good world", getString(arg_));
    }
    @Test
    public void processE465Test() {
        Struct arg_ = directCalculateExc("\"hello word\".regionMatches($false,0,$null,0,0)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE466Test() {
        Argument arg_ = directCalculate("\"hello word\".regionMatches($false,0,\"\",0,0)");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processE467Test() {
        Argument arg_ = directCalculate("\"hello word\".regionMatches($true,0,\"\",0,0)");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processE468Test() {
        Argument arg_ = directCalculate("\"hello word\".toLowerCase()");
        assertEq("hello word",getString(arg_));
    }
    @Test
    public void processE469Test() {
        Argument arg_ = directCalculate("\"HELLO WORD\".toUpperCase()");
        assertEq("HELLO WORD",getString(arg_));
    }
    @Test
    public void processE470Test() {
        Argument arg_ = directCalculate("\"hello word\".equalsIgnoreCase(\"hello word\")");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processE471Test() {
        Argument arg_ = directCalculate("\"helloword\".equalsIgnoreCase(\"hello word\")");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processE472Test() {
        Argument arg_ = directCalculate("\"hello word\".compareToIgnoreCase(\"hello word\") == 0");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processE473Test() {
        Argument arg_ = directCalculate("\"helloword\".compareToIgnoreCase(\"hello word\") < 0");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processE474Test() {
        Argument arg_ = directCalculate("\"helloword\".compareToIgnoreCase(\"hello word\") > 0");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processE475Test() {
        Argument arg_ = directCalculate("\"hello word\".compareTo(\"hello word\") == 0");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processE476Test() {
        Argument arg_ = directCalculate("\"helloword\".compareTo(\"hello word\") < 0");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processE477Test() {
        Argument arg_ = directCalculate("\"helloword\".compareTo(\"hello word\") > 0");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processE478est() {
        Struct arg_ = directCalculateExc("\"hello word\".compareToIgnoreCase($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE479est() {
        Struct arg_ = directCalculateExc("\"hello word\".compareTo($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE480Test() {
        Argument arg_ = directCalculate("String.compare(\"hello word\",\"hello word\") == 0");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processE481Test() {
        Argument arg_ = directCalculate("String.compare(\"helloword\",\"hello word\") < 0");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processE482Test() {
        Argument arg_ = directCalculate("String.compare(\"helloword\",\"hello word\") > 0");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processE483Test() {
        Struct arg_ = directCalculateExc("String.compare($null,\"hello word\") == 0");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE484Test() {
        Struct arg_ = directCalculateExc("String.compare(\"hello word\",$null) == 0");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE485Test() {
        Argument arg_ = directCalculate("String.valueOf($true)");
        assertEq("true", getString(arg_));
    }
    @Test
    public void processE486Test() {
        Argument arg_ = directCalculate("String.valueOf($false)");
        assertEq("false", getString(arg_));
    }
    @Test
    public void processE487Test() {
        Argument arg_ = directCalculate("String.valueOf(($byte)0)");
        assertEq("0", getString(arg_));
    }
    @Test
    public void processE488Test() {
        Argument arg_ = directCalculate("String.valueOf(0s)");
        assertEq("0", getString(arg_));
    }
    @Test
    public void processE489Test() {
        Argument arg_ = directCalculate("String.valueOf(' ')");
        assertEq(" ", getString(arg_));
    }
    @Test
    public void processE490Test() {
        Argument arg_ = directCalculate("String.valueOf(0i)");
        assertEq("0", getString(arg_));
    }
    @Test
    public void processE491Test() {
        Argument arg_ = directCalculate("String.valueOf(0l)");
        assertEq("0", getString(arg_));
    }
    @Test
    public void processE492Test() {
        Argument arg_ = directCalculate("String.valueOf(1.0f)");
        assertEq("1.0", getString(arg_));
    }
    @Test
    public void processE493Test() {
        Argument arg_ = directCalculate("String.valueOf(1.0d)");
        assertEq("1.0", getString(arg_));
    }
    @Test
    public void processE494Test() {
        Argument arg_ = directCalculate("String.valueOf($null)");
        assertEq("", getString(arg_));
    }
    @Test
    public void processE495Test() {
        Argument arg_ = directCalculate("String.valueOf('h',' ','w')");
        assertEq("h w", getString(arg_));
    }
    @Test
    public void processE496Test() {
        Struct arg_ = directCalculateExc("String.valueOf(0,3,$null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE497Test() {
        Struct arg_ = directCalculateExc("String.valueOf(-1,3,'h',' ','w')");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-1<0", ((StringStruct) err_.getMessage()).getInstance());
    }
    @Test
    public void processE498Test() {
        Struct arg_ = directCalculateExc("String.valueOf(0,-3,'h',' ','w')");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-3<0", ((StringStruct) err_.getMessage()).getInstance());
    }
    @Test
    public void processE499Test() {
        Struct arg_ = directCalculateExc("String.valueOf(2,2,'h',' ','w')");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("4>3", ((StringStruct) err_.getMessage()).getInstance());
    }
    @Test
    public void processE500Test() {
        Argument arg_ = directCalculate("String.valueOf(0,3,'h',' ','w')");
        assertEq("h w", getString(arg_));
    }
    @Test
    public void processE501Test() {
        Argument arg_ = directCalculate("$new String()");
        assertEq("", getString(arg_));
    }
    @Test
    public void processE502Test() {
        Struct arg_ = directCalculateExc("$new String(($byte[])$null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE503Test() {
        Struct arg_ = directCalculateExc("$new String(($char[])$null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE504Test() {
        Struct arg_ = directCalculateExc("$new String(-1,2,' ')");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-1<0", ((StringStruct) err_.getMessage()).getInstance());
    }
    @Test
    public void processE505Test() {
        Struct arg_ = directCalculateExc("$new String(0,-2,' ')");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-2<0", ((StringStruct) err_.getMessage()).getInstance());
    }
    @Test
    public void processE506Test() {
        Struct arg_ = directCalculateExc("$new String(0,2,' ')");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("2>1", ((StringStruct) err_.getMessage()).getInstance());
    }
    @Test
    public void processE507Test() {
        Struct arg_ = directCalculateExc("$new String(-1,2,($byte)32)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-1<0", ((StringStruct) err_.getMessage()).getInstance());
    }
    @Test
    public void processE508Test() {
        Struct arg_ = directCalculateExc("$new String(0,-2,($byte)32)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-2<0", ((StringStruct) err_.getMessage()).getInstance());
    }
    @Test
    public void processE509Test() {
        Struct arg_ = directCalculateExc("$new String(0,2,($byte)32)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("2>1", ((StringStruct) err_.getMessage()).getInstance());
    }
    @Test
    public void processE510Test() {
        Struct arg_ = directCalculateExc("$new String(($byte)-16)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("0", ((StringStruct) err_.getMessage()).getInstance());
    }
    @Test
    public void processE511Test() {
        Struct arg_ = directCalculateExc("$new String(0,1,($byte)-16)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("0", ((StringStruct) err_.getMessage()).getInstance());
    }
    @Test
    public void processE512Test() {
        Struct arg_ = directCalculateExc("$new String((StringBuilder)$null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE513Test() {
        Argument arg_ = directCalculate("$new String($new StringBuilder())");
        assertEq("", getString(arg_));
    }
    @Test
    public void processE514Test() {
        Argument arg_ = directCalculate("$new String($new $byte[]{})");
        assertEq("", getString(arg_));
    }
    @Test
    public void processE515Test() {
        Argument arg_ = directCalculate("$new String($new $char[]{})");
        assertEq("", getString(arg_));
    }

    @Test
    public void processEl516Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"my_string\").indexOf(\"y\")");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl517Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"my_string\").indexOf(\"y\",1)");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl518Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"my_string\").indexOf(\"y\",2)");
        assertEq(-1, getNumber(arg_));
    }

    @Test
    public void processEl519Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"my_string\").indexOf(\"a\")");
        assertEq(-1, getNumber(arg_));
    }

    @Test
    public void processEl520Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"my_string\").indexOf('y')");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl521Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"my_string\").indexOf('y',1)");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl522Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"my_string\").indexOf('y',2)");
        assertEq(-1, getNumber(arg_));
    }

    @Test
    public void processEl523Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"my_string\").indexOf('a')");
        assertEq(-1, getNumber(arg_));
    }

    @Test
    public void processEl524Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"my_string\").lastIndexOf(\"y\")");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl525Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"my_string\").lastIndexOf(\"y\",1)");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl526Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"my_string\").lastIndexOf(\"y\",2)");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl527Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"my_string\").lastIndexOf(\"a\")");
        assertEq(-1, getNumber(arg_));
    }

    @Test
    public void processEl528Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"my_string\").lastIndexOf('y')");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl529Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"my_string\").lastIndexOf('y',1)");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl530Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"my_string\").lastIndexOf('y',2)");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl531Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"my_string\").lastIndexOf('a')");
        assertEq(-1, getNumber(arg_));
    }

    @Test
    public void processEl532Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"my_string\").indexOf($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl533Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"my_string\").indexOf($null,1)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl534Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"my_string\").lastIndexOf($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl535Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"my_string\").lastIndexOf($null,1)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }


    @Test
    public void processEl536Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"my_string\").substring(-1)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-1<0", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl537Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"my_string\").substring(10)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("10>9", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl539Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"my_string\").substring(1)");
        assertEq("y_string", getString(arg_));
    }

    @Test
    public void processEl540Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"my_string\").substring(-1,2)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-1<0", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl541Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"my_string\").substring(2,1)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("2>1", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl542Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"my_string\").substring(1,10)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("10>9", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl543Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"my_string\").substring(1,9)");
        assertEq("y_string", getString(arg_));
    }

    @Test
    public void processEl544Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"my_string\").charAt(10)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("10>=9", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl545Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"my_string\").charAt(-1)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-1<0", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl546Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"my_string\").contains($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl547Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"my_string\").contains(\"-\")");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl548Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"my_string\").contains(\"_\")");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl549Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"my_string\").startsWith($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processEl550Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"my_string\").startsWith(\"_\")");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl551Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"my_string\").startsWith(\"m\")");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl552Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"my_string\").startsWith($null,0)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processEl553Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"my_string\").startsWith(\"_\",0)");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl554Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"my_string\").startsWith(\"_\",2)");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl555Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"my_string\").endsWith($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processEl556Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"my_string\").endsWith(\"_\")");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl557Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"my_string\").endsWith(\"g\")");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl558Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"my_string\").format($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processEl559Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"my_string\").format((CharSequence)$null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processEl560Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"{0}_{1}\").format(\"my\",\"string\")");
        assertEq("my_string", getString(arg_));
    }

    @Test
    public void processE561Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"hello word\").splitStrings(2,\"e\",\"o\")");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("h", ((StringStruct) res_[0]).getInstance());
        assertEq("ll", ((StringStruct) res_[1]).getInstance());
    }

    @Test
    public void processE562Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"hello word\").splitStrings(-1,\"e\",\"o\")");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(4, res_.length);
        assertEq("h", ((StringStruct) res_[0]).getInstance());
        assertEq("ll", ((StringStruct) res_[1]).getInstance());
        assertEq(" w", ((StringStruct) res_[2]).getInstance());
        assertEq("rd", ((StringStruct) res_[3]).getInstance());
    }

    @Test
    public void processE563Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"hello word\").splitStrings(-2,\"e\",\"o\")");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(4, res_.length);
        assertEq("h", ((StringStruct) res_[0]).getInstance());
        assertEq("ll", ((StringStruct) res_[1]).getInstance());
        assertEq(" w", ((StringStruct) res_[2]).getInstance());
        assertEq("rd", ((StringStruct) res_[3]).getInstance());
    }
    @Test
    public void processE564Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"hello word\").splitStrings($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE565Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"hello word\").splitStrings((CharSequence)$null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE566Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"hello word\").splitStrings(-1,$null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE567Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"hello word\").splitStrings(-1,(CharSequence)$null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE568Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"hello word\").splitChars($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE569Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"hello word\").splitChars('e','o')");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(4, res_.length);
        assertEq("h", ((StringStruct) res_[0]).getInstance());
        assertEq("ll", ((StringStruct) res_[1]).getInstance());
        assertEq(" w", ((StringStruct) res_[2]).getInstance());
        assertEq("rd", ((StringStruct) res_[3]).getInstance());
    }
    @Test
    public void processE570Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"hello word\").split(' ')");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
        assertEq("word", ((StringStruct) res_[1]).getInstance());
    }
    @Test
    public void processE571Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"hello word\").split(' ',1)");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(1, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
    }
    @Test
    public void processE572Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"hello word\").split($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE573Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"hello word\").split($null,1)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE574Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"hello word\").split(\" \")");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
        assertEq("word", ((StringStruct) res_[1]).getInstance());
    }
    @Test
    public void processE575Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"hello word\").split(\" \",1)");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(1, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
    }
    @Test
    public void processE576Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"hello word\").split(\" \",-1)");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
        assertEq("word", ((StringStruct) res_[1]).getInstance());
    }
    @Test
    public void processE577Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"hello word\").split(\" \",-2)");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
        assertEq("word", ((StringStruct) res_[1]).getInstance());
    }
    @Test
    public void processE578Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"hello word\").split(' ',-1)");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
        assertEq("word", ((StringStruct) res_[1]).getInstance());
    }
    @Test
    public void processE579Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"hello word\").split(' ',-2)");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
        assertEq("word", ((StringStruct) res_[1]).getInstance());
    }

    @Test
    public void processE580Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"hello word\").splitStrings(-1,\"e\",\"o\")");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(4, res_.length);
        assertEq("h", ((StringStruct) res_[0]).getInstance());
        assertEq("ll", ((StringStruct) res_[1]).getInstance());
        assertEq(" w", ((StringStruct) res_[2]).getInstance());
        assertEq("rd", ((StringStruct) res_[3]).getInstance());
    }

    @Test
    public void processE581Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"hello word\").splitStrings(-2,\"e\",\"o\")");
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(4, res_.length);
        assertEq("h", ((StringStruct) res_[0]).getInstance());
        assertEq("ll", ((StringStruct) res_[1]).getInstance());
        assertEq(" w", ((StringStruct) res_[2]).getInstance());
        assertEq("rd", ((StringStruct) res_[3]).getInstance());
    }

    @Test
    public void processEl582Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"\\nnew line\").trim()");
        assertEq("new line", getString(arg_));
    }

    @Test
    public void processEl583Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"\\nnew line\").toString()");
        assertEq("\nnew line", getString(arg_));
    }

    @Test
    public void processEl584Test() {
        Argument arg_ = directCalculate("$new java.lang.String($new StringBuilder(\"\u1000\").getBytes())");
        assertEq("\u1000", getString(arg_));
    }

    @Test
    public void processEl585Test() {
        Argument arg_ = directCalculate("$new java.lang.String($new StringBuilder(\"\u0800\").getBytes())");
        assertEq("\u0800", getString(arg_));
    }

    @Test
    public void processEl586Test() {
        Argument arg_ = directCalculate("$new java.lang.String($new StringBuilder(\"\u07FF\").getBytes())");
        assertEq("\u07FF", getString(arg_));
    }

    @Test
    public void processEl587Test() {
        Argument arg_ = directCalculate("$new java.lang.String($new StringBuilder(\"\u0050\").getBytes())");
        assertEq("\u0050", getString(arg_));
    }

    @Test
    public void processEl588Test() {
        Argument arg_ = directCalculate("$new StringBuilder()");
        assertEq("", getString(arg_));
    }

    @Test
    public void processEl589Test() {
        Argument arg_ = directCalculate("$new StringBuilder().append(\"a string\").toString()");
        assertEq("a string", getString(arg_));
    }

    @Test
    public void processEl590Test() {
        Argument arg_ = directCalculate("$new StringBuilder().append(0i).toString()");
        assertEq("0", getString(arg_));
    }

    @Test
    public void processEl591Test() {
        Argument arg_ = directCalculate("$new StringBuilder().append($true).toString()");
        assertEq("true", getString(arg_));
    }

    @Test
    public void processEl592Test() {
        Argument arg_ = directCalculate("$new StringBuilder().append($false).toString()");
        assertEq("false", getString(arg_));
    }

    @Test
    public void processEl593Test() {
        Argument arg_ = directCalculate("$new StringBuilder().append(($byte)0).toString()");
        assertEq("0", getString(arg_));
    }

    @Test
    public void processEl594Test() {
        Argument arg_ = directCalculate("$new StringBuilder().append(0l).toString()");
        assertEq("0", getString(arg_));
    }

    @Test
    public void processEl595Test() {
        Argument arg_ = directCalculate("$new StringBuilder().append(0s).toString()");
        assertEq("0", getString(arg_));
    }

    @Test
    public void processEl596Test() {
        Argument arg_ = directCalculate("$new StringBuilder().append('c').toString()");
        assertEq("c", getString(arg_));
    }

    @Test
    public void processEl597Test() {
        Argument arg_ = directCalculate("$new StringBuilder().append(1.0f).toString()");
        assertEq("1.0", getString(arg_));
    }

    @Test
    public void processEl598Test() {
        Argument arg_ = directCalculate("$new StringBuilder().append(1.0d).toString()");
        assertEq("1.0", getString(arg_));
    }
    @Test
    public void processEl599Test() {
        Argument arg_ = directCalculate("$new StringBuilder().append((String)$null).toString()");
        assertEq("", getString(arg_));
    }
    @Test
    public void processEl600Test() {
        Argument arg_ = directCalculate("$new StringBuilder().append((StringBuilder)$null).toString()");
        assertEq("", getString(arg_));
    }

    @Test
    public void processEl601Test() {
        Argument arg_ = directCalculate("$new StringBuilder().append($new $char[]{'a',' ','s','t','r','i','n','g'}).toString()");
        assertEq("a string", getString(arg_));
    }
    @Test
    public void processEl602Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().append(($char[])$null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl603Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().append($new $char[]{'a',' ','s','t','r','i','n','g'},-1,8).toString()");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-1<0", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl604Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().append($new $char[]{'a',' ','s','t','r','i','n','g'},0,-8).toString()");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-8<0", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl605Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().append($new $char[]{'a',' ','s','t','r','i','n','g'},1,9).toString()");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("10>8", ((StringStruct) err_.getMessage()).getInstance());
    }
    @Test
    public void processEl606Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().append(($char[])$null,0,0)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processEl607Test() {
        Argument arg_ = directCalculate("$new StringBuilder().append($new $char[]{'a',' ','s','t','r','i','n','g'},2,6).toString()");
        assertEq("string", getString(arg_));
    }

    @Test
    public void processEl608Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().append(\"a string\",-1,8).toString()");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-1<0", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl609Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().append(\"a string\",0,-8).toString()");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("0>-8", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl610Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().append(\"a string\",1,9).toString()");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("9>8", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl611Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().append(\"a string\",10,8).toString()");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("10>8", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl612Test() {
        Argument arg_ = directCalculate("$new StringBuilder().append(\"a string\",2,8).toString()");
        assertEq("string", getString(arg_));
    }
    @Test
    public void processEl613Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().append($new StringBuilder(\"a string\"),-1,8).toString()");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-1<0", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl614Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().append($new StringBuilder(\"a string\"),0,-8).toString()");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("0>-8", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl615Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().append($new StringBuilder(\"a string\"),1,9).toString()");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("9>8", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl616Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().append($new StringBuilder(\"a string\"),10,8).toString()");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("10>8", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl617Test() {
        Argument arg_ = directCalculate("$new StringBuilder().append($new StringBuilder(\"a string\"),2,8).toString()");
        assertEq("string", getString(arg_));
    }

    @Test
    public void processEl618Test() {
        Argument arg_ = directCalculate("$new StringBuilder().append($new StringBuilder(\"a string\")).toString()");
        assertEq("a string", getString(arg_));
    }

    @Test
    public void processEl619Test() {
        Argument arg_ = directCalculate("$new StringBuilder(8).capacity()");
        assertEq(8, getNumber(arg_));
    }

    @Test
    public void processEl620Test() {
        Argument arg_ = directCalculate("$new StringBuilder($new StringBuilder(\"a string\")).toString()");
        assertEq("a string", getString(arg_));
    }

    @Test
    public void processEl621Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder((StringBuilder)$null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl622Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(-8)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-8", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl623Test() {
        Argument arg_ = directCalculate("$new StringBuilder().insert(0,\"a string\").toString()");
        assertEq("a string", getString(arg_));
    }

    @Test
    public void processEl624Test() {
        Argument arg_ = directCalculate("$new StringBuilder().insert(0,0i).toString()");
        assertEq("0", getString(arg_));
    }

    @Test
    public void processEl625Test() {
        Argument arg_ = directCalculate("$new StringBuilder().insert(0,$true).toString()");
        assertEq("true", getString(arg_));
    }

    @Test
    public void processEl626Test() {
        Argument arg_ = directCalculate("$new StringBuilder().insert(0,$false).toString()");
        assertEq("false", getString(arg_));
    }

    @Test
    public void processEl627Test() {
        Argument arg_ = directCalculate("$new StringBuilder().insert(0,($byte)0).toString()");
        assertEq("0", getString(arg_));
    }

    @Test
    public void processEl628Test() {
        Argument arg_ = directCalculate("$new StringBuilder().insert(0,0l).toString()");
        assertEq("0", getString(arg_));
    }

    @Test
    public void processEl629Test() {
        Argument arg_ = directCalculate("$new StringBuilder().insert(0,0s).toString()");
        assertEq("0", getString(arg_));
    }

    @Test
    public void processEl630Test() {
        Argument arg_ = directCalculate("$new StringBuilder().insert(0,'c').toString()");
        assertEq("c", getString(arg_));
    }

    @Test
    public void processEl631Test() {
        Argument arg_ = directCalculate("$new StringBuilder().insert(0,1.0f).toString()");
        assertEq("1.0", getString(arg_));
    }

    @Test
    public void processEl632Test() {
        Argument arg_ = directCalculate("$new StringBuilder().insert(0,1.0d).toString()");
        assertEq("1.0", getString(arg_));
    }
    @Test
    public void processEl633Test() {
        Argument arg_ = directCalculate("$new StringBuilder().insert(0,(String)$null).toString()");
        assertEq("", getString(arg_));
    }
    @Test
    public void processEl634Test() {
        Argument arg_ = directCalculate("$new StringBuilder().insert(0,(StringBuilder)$null).toString()");
        assertEq("", getString(arg_));
    }

    @Test
    public void processEl635Test() {
        Argument arg_ = directCalculate("$new StringBuilder().insert(0,$new $char[]{'a',' ','s','t','r','i','n','g'}).toString()");
        assertEq("a string", getString(arg_));
    }
    @Test
    public void processEl636Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().insert(0,($char[])$null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl637Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().insert(0,$new $char[]{'a',' ','s','t','r','i','n','g'},-1,8).toString()");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-1<0", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl638Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().insert(0,$new $char[]{'a',' ','s','t','r','i','n','g'},0,-8).toString()");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-8<0", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl639Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().insert(0,$new $char[]{'a',' ','s','t','r','i','n','g'},1,9).toString()");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("10>8", ((StringStruct) err_.getMessage()).getInstance());
    }
    @Test
    public void processEl640Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().insert(0,($char[])$null,0,0)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processEl641Test() {
        Argument arg_ = directCalculate("$new StringBuilder().insert(0,$new $char[]{'a',' ','s','t','r','i','n','g'},2,6).toString()");
        assertEq("string", getString(arg_));
    }

    @Test
    public void processEl642Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().insert(0,\"a string\",-1,8).toString()");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-1<0", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl643Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().insert(0,\"a string\",0,-8).toString()");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-8<0", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl644Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().insert(0,\"a string\",1,9).toString()");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("9>8", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl645Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().insert(0,\"a string\",10,8).toString()");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("10>8", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl646Test() {
        Argument arg_ = directCalculate("$new StringBuilder().insert(0,\"a string\",2,8).toString()");
        assertEq("string", getString(arg_));
    }
    @Test
    public void processEl647Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().insert(0,$new StringBuilder(\"a string\"),-1,8).toString()");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-1<0", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl648Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().insert(0,$new StringBuilder(\"a string\"),0,-8).toString()");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-8<0", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl649Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().insert(0,$new StringBuilder(\"a string\"),1,9).toString()");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("9>8", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl650Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().insert(0,$new StringBuilder(\"a string\"),10,8).toString()");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("10>8", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl651Test() {
        Argument arg_ = directCalculate("$new StringBuilder().insert(0,$new StringBuilder(\"a string\"),2,8).toString()");
        assertEq("string", getString(arg_));
    }

    @Test
    public void processEl652Test() {
        Argument arg_ = directCalculate("$new StringBuilder().insert(0,$new StringBuilder(\"a string\")).toString()");
        assertEq("a string", getString(arg_));
    }

    @Test
    public void processEl653Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder((String)$null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl654Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().insert(-1,\"a string\").toString()");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-1<0", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl655Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().insert(1,\"a string\").toString()");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("1>0", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl656Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"a string\").replace(-1,1,\" super \")");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-1<0", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl657Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"a string\").replace(9,1,\" super \")");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("9>8", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl658Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"a string\").replace(8,7,\" super \")");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("8>7", ((StringStruct) err_.getMessage()).getInstance());
    }
    @Test
    public void processEl659Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"a bad string\").replace(1,6,$null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processEl660Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"a bad string\").replace(1,6,\" super \").toString()");
        assertEq("a super string", getString(arg_));
    }

    @Test
    public void processEl661Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().insert(-1,$new StringBuilder(\"a string\"))");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-1<0", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl662Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().insert(1,$new StringBuilder(\"a string\"))");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("1>0", ((StringStruct) err_.getMessage()).getInstance());
    }
    @Test
    public void processEl663Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().insert(1,$new $char[]{'a',' ','s','t','r','i','n','g'},2,6)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("1>0", ((StringStruct) err_.getMessage()).getInstance());
    }
    @Test
    public void processEl664Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().insert(-1,$new $char[]{'a',' ','s','t','r','i','n','g'},2,6)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-1<0", ((StringStruct) err_.getMessage()).getInstance());
    }
    @Test
    public void processEl665Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().insert(1,$new $char[]{'a',' ','s','t','r','i','n','g'})");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("1>0", ((StringStruct) err_.getMessage()).getInstance());
    }
    @Test
    public void processEl666Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().insert(-1,$new $char[]{'a',' ','s','t','r','i','n','g'})");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-1<0", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl667Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"a string\").delete(-1,1)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-1<0", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl668Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"a string\").delete(9,1)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("9>8", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl669Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"a string\").delete(8,7)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("8>7", ((StringStruct) err_.getMessage()).getInstance());
    }
    @Test
    public void processEl670Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"a bad string\").delete(1,5).toString()");
        assertEq("a string", getString(arg_));
    }
    @Test
    public void processEl671Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"a string\").deleteCharAt(-1)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-1<0", ((StringStruct) err_.getMessage()).getInstance());
    }
    @Test
    public void processEl672Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"a string\").deleteCharAt(8)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("8>=8", ((StringStruct) err_.getMessage()).getInstance());
    }
    @Test
    public void processEl673Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"a  string\").deleteCharAt(1).toString()");
        assertEq("a string", getString(arg_));
    }
    @Test
    public void processEl674Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"a string\").setCharAt(-1,' ')");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-1<0", ((StringStruct) err_.getMessage()).getInstance());
    }
    @Test
    public void processEl675Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"a string\").setCharAt(8,' ')");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("8>=8", ((StringStruct) err_.getMessage()).getInstance());
    }
    @Test
    public void processEl676Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"a_string\").setCharAt(1,' ').toString()");
        assertEq("a string", getString(arg_));
    }

    @Test
    public void processEl677Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().insert(-1,$new StringBuilder(\"a string\"),0,0)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-1<0", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl678Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().insert(1,$new StringBuilder(\"a string\"),0,0)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("1>0", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl679Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().insert(-1,\"a string\",0,0)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-1<0", ((StringStruct) err_.getMessage()).getInstance());
    }

    @Test
    public void processEl680Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder().insert(1,\"a string\",0,0)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("1>0", ((StringStruct) err_.getMessage()).getInstance());
    }
    @Test
    public void processEl681Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"a string\").reverse()");
        assertEq("gnirts a", getString(arg_));
    }
    @Test
    public void processEl682Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"a string\").ensureCapacity(32).capacity() >= 32");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl683Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"a string\").trimToSize().capacity()");
        assertEq(8, getNumber(arg_));
    }
    @Test
    public void processEl684Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"a string\").setLength(1)");
        assertEq("a", getString(arg_));
    }
    @Test
    public void processEl685Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"a string\").clear()");
        assertEq("", getString(arg_));
    }
    @Test
    public void processEl686Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"a string\").setLength(-1)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.BadIndexException", err_.getClassName());
        assertEq("-1<0", ((StringStruct) err_.getMessage()).getInstance());
    }
    @Test
    public void processE687Test() {
        Argument arg_ = directCalculate("$new String(' ')");
        assertEq(" ", getString(arg_));
    }
    @Test
    public void processEl688Test() {
        Struct arg_ = directCalculateExc("$new String(-1,2,($char[])$null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processEl689Test() {
        Struct arg_ = directCalculateExc("$new String(-1,2,($byte[])$null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processEl690Test() {
        Argument arg_ = directCalculate("$new String(0,1,($byte)32)");
        assertEq(" ", getString(arg_));
    }
    @Test
    public void processEl691Test() {
        Argument arg_ = directCalculate("$new String(0,1,' ')");
        assertEq(" ", getString(arg_));
    }
    @Test
    public void processEl692Test() {
        Argument arg_ = directCalculate("\"helloword\".equalsIgnoreCase($null)");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl693Test() {
        Argument arg_ = directCalculate("\"my_string\".charAt(0)");
        assertEq('m',getChar(arg_));
    }

    @Test
    public void processEl694Test() {
        Argument arg_ = directCalculate("$new StringBuilder(\"my_string\").charAt(0)");
        assertEq('m',getChar(arg_));
    }

    @Test
    public void processEl695Test() {
        Argument arg_ = directCalculate("\"my_string\" != \"mystring\"");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl696Test() {
        Argument arg_ = directCalculate("\"my_string\" != \"my string\"");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl697Test() {
        Argument arg_ = directCalculate("CharSequence.equals(\"my_string\", \"my string\")");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl698Test() {
        Argument arg_ = directCalculate("CharSequence.equals(\"my_string\", \"my_string\")");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl699Test() {
        Struct arg_ = directCalculateExc("1%0");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.DivideZeroException",err_.getClassName());
    }

    @Test
    public void processEl700Test() {
        Struct arg_ = directCalculateExc("1%0l");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.DivideZeroException",err_.getClassName());
    }

    @Test
    public void processEl701Test() {
        Argument arg_ = directCalculate("8.0/2d");
        assertEq(4.0, getDouble(arg_));
    }

    @Test
    public void processEl702Test() {
        Argument arg_ = directCalculate("7.0%2d");
        assertEq(1.0, getDouble(arg_));
    }

    @Test
    public void processEl703Test() {
        Argument arg_ = directCalculate("8.0f/2f");
        assertEq(4.0, getDouble(arg_));
    }

    @Test
    public void processEl704Test() {
        Argument arg_ = directCalculate("7.0f%2f");
        assertEq(1.0, getDouble(arg_));
    }

    @Test
    public void processEl705Test() {
        Argument arg_ = directCalculate("~0l");
        assertEq(-1, getNumber(arg_));
    }

    @Test
    public void processEl706Test() {
        Argument arg_ = directCalculate("~-1l");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl707Test() {
        Argument arg_ = directCalculate("1f+1f");
        assertEq(2.0, getDouble(arg_));
    }

    @Test
    public void processEl708Test() {
        Argument arg_ = directCalculate("3.0-1.0");
        assertEq(2.0, getDouble(arg_));
    }

    @Test
    public void processEl709Test() {
        Argument arg_ = directCalculate("3.0f-1.0f");
        assertEq(2.0, getDouble(arg_));
    }

    @Test
    public void processEl710Test() {
        Argument arg_ = directCalculate("4l*2");
        assertEq(8, getNumber(arg_));
    }

    @Test
    public void processEl711Test() {
        Argument arg_ = directCalculate("4f*2f");
        assertEq(8.0, getDouble(arg_));
    }

    @Test
    public void processEl712Test() {
        Argument arg_ = directCalculate("5&3");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl713Test() {
        Argument arg_ = directCalculate("3&5");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl714Test() {
        Argument arg_ = directCalculate("5|3");
        assertEq(7, getNumber(arg_));
    }

    @Test
    public void processEl715Test() {
        Argument arg_ = directCalculate("3|5");
        assertEq(7, getNumber(arg_));
    }

    @Test
    public void processEl716Test() {
        Argument arg_ = directCalculate("$true&$false");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl717Test() {
        Argument arg_ = directCalculate("$false&$true");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl718Test() {
        Argument arg_ = directCalculate("$true|$false");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl719Test() {
        Argument arg_ = directCalculate("$false|$true");
        assertTrue(arg_.isTrue());
    }


    @Test
    public void processEl720Test() {
        Argument arg_ = directCalculate("$true&$true");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl721Test() {
        Argument arg_ = directCalculate("$false&$false");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl722Test() {
        Argument arg_ = directCalculate("$true|$true");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl723Test() {
        Argument arg_ = directCalculate("$false|$false");
        assertTrue(arg_.isFalse());
    }


    @Test
    public void processEl724Test() {
        Argument arg_ = directCalculate("$true^$false");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl725Test() {
        Argument arg_ = directCalculate("$false^$true");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl726Test() {
        Argument arg_ = directCalculate("$true^$true");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl727Test() {
        Argument arg_ = directCalculate("$false^$false");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl728Test() {
        Argument arg_ = directCalculate("5l&3l");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl729Test() {
        Argument arg_ = directCalculate("3l&5l");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl730Test() {
        Argument arg_ = directCalculate("\" \"<\"a\"");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl731Test() {
        Argument arg_ = directCalculate("\"a\"<\" \"");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl732Test() {
        Argument arg_ = directCalculate("\" \"<=\"a\"");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl733Test() {
        Argument arg_ = directCalculate("\"a\"<=\" \"");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl734Test() {
        Argument arg_ = directCalculate("\" \">\"a\"");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl735Test() {
        Argument arg_ = directCalculate("\"a\">\" \"");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl736Test() {
        Argument arg_ = directCalculate("\" \">=\"a\"");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl737Test() {
        Argument arg_ = directCalculate("\"a\">=\" \"");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl738Test() {
        Argument arg_ = directCalculate("((Boolean)$true).booleanValue()");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl739Test() {
        Argument arg_ = directCalculate("((Boolean)$true).compareTo((Boolean)$true)");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl740Test() {
        Argument arg_ = directCalculate("Boolean.compare((Boolean)$true,(Boolean)$true)");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl741Test() {
        Argument arg_ = directCalculate("((Boolean)$true).equals((Boolean)$true)");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl742Test() {
        Argument arg_ = directCalculate("Boolean.parseBoolean(\"true\")");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl743Test() {
        Argument arg_ = directCalculate("Boolean.toString($true)");
        assertEq("true",getString(arg_));
    }

    @Test
    public void processEl744Test() {
        Argument arg_ = directCalculate("((Boolean)$true).toString()");
        assertEq("true",getString(arg_));
    }

    @Test
    public void processEl745Test() {
        Argument arg_ = directCalculate("Boolean.valueOf($true)");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl746Test() {
        Argument arg_ = directCalculate("Boolean.valueOf(\"true\")");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl747Test() {
        Argument arg_ = directCalculate("Integer.compare(1,1)");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl748Test() {
        Argument arg_ = directCalculate("((Integer)1).compareTo(1)");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl749Test() {
        Argument arg_ = directCalculate("((Integer)1).intValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl750Test() {
        Argument arg_ = directCalculate("((Integer)1).longValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl751Test() {
        Argument arg_ = directCalculate("((Integer)1).shortValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl752Test() {
        Argument arg_ = directCalculate("((Integer)1).byteValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl753Test() {
        Argument arg_ = directCalculate("((Integer)1).floatValue()");
        assertEq(1.0, getDouble(arg_));
    }

    @Test
    public void processEl754Test() {
        Argument arg_ = directCalculate("((Integer)1).doubleValue()");
        assertEq(1.0, getDouble(arg_));
    }

    @Test
    public void processEl755Test() {
        Argument arg_ = directCalculate("((Long)1).compareTo(1)");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl756Test() {
        Argument arg_ = directCalculate("((Long)1).intValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl757Test() {
        Argument arg_ = directCalculate("((Long)1).longValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl758Test() {
        Argument arg_ = directCalculate("((Long)1).shortValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl759Test() {
        Argument arg_ = directCalculate("((Long)1).byteValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl760Test() {
        Argument arg_ = directCalculate("((Long)1).floatValue()");
        assertEq(1.0, getDouble(arg_));
    }

    @Test
    public void processEl761Test() {
        Argument arg_ = directCalculate("((Long)1).doubleValue()");
        assertEq(1.0, getDouble(arg_));
    }

    @Test
    public void processEl762Test() {
        Argument arg_ = directCalculate("Long.compare(1,1)");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl763Test() {
        Argument arg_ = directCalculate("Boolean.parseBoolean(\"false\")");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl764Test() {
        Argument arg_ = directCalculate("Boolean.valueOf(\"false\")");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl765Test() {
        Argument arg_ = directCalculate("((Integer)1).equals(1)");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl765_Test() {
        Argument arg_ = directCalculate("Number.equals(1,1)");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl765__Test() {
        Argument arg_ = directCalculate("Number.equals(1,2)");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl766Test() {
        Argument arg_ = directCalculate("((Long)1).equals(1)");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl767Test() {
        Argument arg_ = directCalculate("((Integer)1).toString()");
        assertEq("1",getString(arg_));
    }

    @Test
    public void processEl768Test() {
        Argument arg_ = directCalculate("((Long)1).toString()");
        assertEq("1",getString(arg_));
    }

    @Test
    public void processEl769Test() {
        Argument arg_ = directCalculate("0x1s");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl770Test() {
        Argument arg_ = directCalculate("0x1xy");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl771Test() {
        Argument arg_ = directCalculate("Byte.toString(1y)");
        assertEq("1",getString(arg_));
    }

    @Test
    public void processEl772Test() {
        Argument arg_ = directCalculate("Short.toString(1s)");
        assertEq("1",getString(arg_));
    }
    @Test
    public void processEl773Test() {
        Argument arg_ = directCalculate("Integer.toString(1)");
        assertEq("1",getString(arg_));
    }
    @Test
    public void processEl773_Test() {
        Argument arg_ = directCalculate("Integer.toString(1y)");
        assertEq("1",getString(arg_));
    }

    @Test
    public void processEl774Test() {
        Argument arg_ = directCalculate("Long.toString(1)");
        assertEq("1",getString(arg_));
    }
    @Test
    public void processEl775Test() {
        Argument arg_ = directCalculate("Float.toString(1f)");
        assertEq("1.0",getString(arg_));
    }

    @Test
    public void processEl776Test() {
        Argument arg_ = directCalculate("Double.toString(1d)");
        assertEq("1.0",getString(arg_));
    }

    @Test
    public void processEl777Test() {
        Argument arg_ = directCalculate("Byte.parseByte(\"1\",10)");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl778Test() {
        Argument arg_ = directCalculate("Short.parseShort(\"1\",10)");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl779Test() {
        Argument arg_ = directCalculate("Integer.parseInt(\"1\",10)");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl780Test() {
        Argument arg_ = directCalculate("Long.parseLong(\"1\",10)");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl781Test() {
        Argument arg_ = directCalculate("Short.parseShort(\"1\")");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl782Test() {
        Argument arg_ = directCalculate("Integer.parseInt(\"1\")");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl783Test() {
        Argument arg_ = directCalculate("Long.parseLong(\"1\")");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl784Test() {
        Argument arg_ = directCalculate("Float.parseFloat(\"1\")");
        assertEq(1.0, getDouble(arg_));
    }

    @Test
    public void processEl785Test() {
        Argument arg_ = directCalculate("Double.parseDouble(\"1\")");
        assertEq(1.0, getDouble(arg_));
    }

    @Test
    public void processEl786Test() {
        Argument arg_ = directCalculate("((Float)1).isNan()");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl787Test() {
        Argument arg_ = directCalculate("Float.isNan(1f)");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl788Test() {
        Argument arg_ = directCalculate("((Float)1).isInfinite()");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl789Test() {
        Argument arg_ = directCalculate("Float.isInfinite(1f)");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl791Test() {
        Argument arg_ = directCalculate("((Double)1).isNan()");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl792Test() {
        Argument arg_ = directCalculate("Double.isNan(1d)");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl793Test() {
        Argument arg_ = directCalculate("((Double)1).isInfinite()");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl794Test() {
        Argument arg_ = directCalculate("Double.isInfinite(1d)");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl795Test() {
        Struct arg_ = directCalculateExc("Byte.parseByte(\"1\",0)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat",err_.getClassName());
        assertEq("1,0",((StringStruct)err_.getMessage()).getInstance());
    }

    @Test
    public void processEl796Test() {
        Struct arg_ = directCalculateExc("Short.parseShort(\"1\",0)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat",err_.getClassName());
        assertEq("1,0",((StringStruct)err_.getMessage()).getInstance());
    }

    @Test
    public void processEl797Test() {
        Struct arg_ = directCalculateExc("Integer.parseInt(\"1\",0)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat",err_.getClassName());
        assertEq("1,0",((StringStruct)err_.getMessage()).getInstance());
    }

    @Test
    public void processEl798Test() {
        Struct arg_ = directCalculateExc("Long.parseLong(\"1\",0)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat",err_.getClassName());
        assertEq("1,0",((StringStruct)err_.getMessage()).getInstance());
    }

    @Test
    public void processEl799Test() {
        Struct arg_ = directCalculateExc("Byte.parseByte(\" \",10)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat",err_.getClassName());
        assertEq(" ,10",((StringStruct)err_.getMessage()).getInstance());
    }

    @Test
    public void processEl800Test() {
        Struct arg_ = directCalculateExc("Short.parseShort(\" \",10)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat",err_.getClassName());
        assertEq(" ,10",((StringStruct)err_.getMessage()).getInstance());
    }

    @Test
    public void processEl801Test() {
        Struct arg_ = directCalculateExc("Integer.parseInt(\" \",10)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat",err_.getClassName());
        assertEq(" ,10",((StringStruct)err_.getMessage()).getInstance());
    }

    @Test
    public void processEl802Test() {
        Struct arg_ = directCalculateExc("Long.parseLong(\" \",10)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat",err_.getClassName());
        assertEq(" ,10",((StringStruct)err_.getMessage()).getInstance());
    }

    @Test
    public void processEl803Test() {
        Struct arg_ = directCalculateExc("Float.parseFloat(\" \")");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat",err_.getClassName());
        assertEq(" ",((StringStruct)err_.getMessage()).getInstance());
    }

    @Test
    public void processEl804Test() {
        Struct arg_ = directCalculateExc("Double.parseDouble(\" \")");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat",err_.getClassName());
        assertEq(" ",((StringStruct)err_.getMessage()).getInstance());
    }

    @Test
    public void processEl805Test() {
        Struct arg_ = directCalculateExc("Float.parseFloat(\"1e100\")");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat",err_.getClassName());
        assertEq("1e100",((StringStruct)err_.getMessage()).getInstance());
    }

    @Test
    public void processEl806Test() {
        Argument arg_ = directCalculate("((Number)1).compareTo(1)");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl807Test() {
        Argument arg_ = directCalculate("Number.compare(1,1)");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl808Test() {
        Argument arg_ = directCalculate("((Character)'1').compareTo('1')");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl809Test() {
        Argument arg_ = directCalculate("Character.compare('1','1')");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl810Test() {
        Struct arg_ = directCalculateExc("((Number)1).compareTo((Number)$null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException",err_.getClassName());
    }

    @Test
    public void processEl811Test() {
        Struct arg_ = directCalculateExc("Number.compare((Number)$null,1)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException",err_.getClassName());
    }

    @Test
    public void processEl812Test() {
        Struct arg_ = directCalculateExc("Number.compare(1,(Number)$null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException",err_.getClassName());
    }

    @Test
    public void processEl813Test() {
        Argument arg_ = directCalculate("Byte.parseByte(\"1\")");
        assertEq(1, getNumber(arg_));
    }


    @Test
    public void processEl814Test() {
        Struct arg_ = directCalculateExc("Byte.parseByte(\"1000\")");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat",err_.getClassName());
        assertEq("1000,10",((StringStruct)err_.getMessage()).getInstance());
    }

    @Test
    public void processEl815Test() {
        Struct arg_ = directCalculateExc("Short.parseShort(\"100000\")");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat",err_.getClassName());
        assertEq("100000,10",((StringStruct)err_.getMessage()).getInstance());
    }

    @Test
    public void processEl816Test() {
        Struct arg_ = directCalculateExc("Integer.parseInt(\"100000000000\")");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat",err_.getClassName());
        assertEq("100000000000,10",((StringStruct)err_.getMessage()).getInstance());
    }

    @Test
    public void processEl817Test() {
        Struct arg_ = directCalculateExc("Byte.parseByte(\"-1000\")");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat",err_.getClassName());
        assertEq("-1000,10",((StringStruct)err_.getMessage()).getInstance());
    }

    @Test
    public void processEl818Test() {
        Struct arg_ = directCalculateExc("Short.parseShort(\"-100000\")");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat",err_.getClassName());
        assertEq("-100000,10",((StringStruct)err_.getMessage()).getInstance());
    }

    @Test
    public void processEl819Test() {
        Struct arg_ = directCalculateExc("Integer.parseInt(\"-100000000000\")");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat",err_.getClassName());
        assertEq("-100000000000,10",((StringStruct)err_.getMessage()).getInstance());
    }

    @Test
    public void processEl820Test() {
        Struct arg_ = directCalculateExc("Float.parseFloat(\"-1e100\")");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat",err_.getClassName());
        assertEq("-1e100",((StringStruct)err_.getMessage()).getInstance());
    }

    @Test
    public void processEl821Test() {
        Struct arg_ = directCalculateExc("Integer.parseInt(\"1000000000000000000000000\")");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat",err_.getClassName());
        assertEq("1000000000000000000000000,10",((StringStruct)err_.getMessage()).getInstance());
    }

    @Test
    public void processEl822Test() {
        Argument arg_ = directCalculate("Number.toString(1)");
        assertEq("1", getString(arg_));
    }

    @Test
    public void processEl823Test() {
        Argument arg_ = directCalculate("Number.toString($null)");
        assertEq("", getString(arg_));
    }

    @Test
    public void processEl824Test() {
        Argument arg_ = directCalculate("Character.digit('1',10)");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl825Test() {
        Argument arg_ = directCalculate("Character.forDigit(1,10)");
        assertEq('1', getNumber(arg_));
    }

    @Test
    public void processEl826Test() {
        Argument arg_ = directCalculate("Character.getDirectionality('1')");
        assertEq(3, getNumber(arg_));
    }

    @Test
    public void processEl827Test() {
        Argument arg_ = directCalculate("Character.getType('1')");
        assertEq(9, getNumber(arg_));
    }

    @Test
    public void processEl828Test() {
        Argument arg_ = directCalculate("Character.isDigit('1')");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl829Test() {
        Argument arg_ = directCalculate("Character.isLetter('1')");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl830Test() {
        Argument arg_ = directCalculate("Character.isLetterOrDigit('1')");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl831Test() {
        Argument arg_ = directCalculate("Character.isLowerCase('a')");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl832Test() {
        Argument arg_ = directCalculate("Character.isUpperCase('A')");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl833Test() {
        Argument arg_ = directCalculate("Character.isSpace(' ')");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl834Test() {
        Argument arg_ = directCalculate("Character.isWhitespace(' ')");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl835Test() {
        Argument arg_ = directCalculate("Character.isWordChar('_')");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl836Test() {
        Argument arg_ = directCalculate("Character.toLowerCase('a')");
        assertEq('a', getNumber(arg_));
    }
    @Test
    public void processEl837Test() {
        Argument arg_ = directCalculate("Character.toUpperCase('A')");
        assertEq('A', getNumber(arg_));
    }
    @Test
    public void processEl838Test() {
        Argument arg_ = directCalculate("((Character)' ').charValue()");
        assertEq(' ', getNumber(arg_));
    }

    @Test
    public void processEl839Test() {
        Argument arg_ = directCalculate("((Character)' ').compareTo(' ')");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl840Test() {
        Argument arg_ = directCalculate("((Character)' ').equals(' ')");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl841Test() {
        Struct arg_ = directCalculateExc("((Character)' ').compareTo($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException",err_.getClassName());
    }
    @Test
    public void processEl842Test() {
        Argument arg_ = directCalculate("((Character)' ').toString()");
        assertEq(" ", getString(arg_));
    }
    @Test
    public void processEl843Test() {
        Argument arg_ = directCalculate("Character.toString(' ')");
        assertEq(" ", getString(arg_));
    }

    @Test
    public void processEl844Test() {
        Argument arg_ = directCalculate("$new Byte(\"1\")");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl845Test() {
        Argument arg_ = directCalculate("$new Short(\"1\")");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl846Test() {
        Argument arg_ = directCalculate("$new Integer(\"1\")");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl847Test() {
        Argument arg_ = directCalculate("$new Long(\"1\")");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl848Test() {
        Argument arg_ = directCalculate("$new Float(\"1\")");
        assertEq(1.0, getDouble(arg_));
    }

    @Test
    public void processEl849Test() {
        Argument arg_ = directCalculate("$new Double(\"1\")");
        assertEq(1.0, getDouble(arg_));
    }

    @Test
    public void processEl850Test() {
        Argument arg_ = directCalculate("$new Byte(1y)");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl851Test() {
        Argument arg_ = directCalculate("$new Short(1s)");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl852Test() {
        Argument arg_ = directCalculate("$new Integer(1)");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl853Test() {
        Argument arg_ = directCalculate("$new Long(1)");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl853_Test() {
        Argument arg_ = directCalculate("$new Long(1).floatValue()");
        assertEq(1.0, getDouble(arg_));
    }
    @Test
    public void processEl854Test() {
        Argument arg_ = directCalculate("$new Float(1f)");
        assertEq(1.0, getDouble(arg_));
    }

    @Test
    public void processEl855Test() {
        Argument arg_ = directCalculate("$new Double(1d)");
        assertEq(1.0, getDouble(arg_));
    }

    @Test
    public void processEl856Test() {
        Argument arg_ = directCalculate("$new Character('1')");
        assertEq('1', getNumber(arg_));
    }

    @Test
    public void processEl857Test() {
        Argument arg_ = directCalculate("$new Boolean($true)");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl858Test() {
        Argument arg_ = directCalculate("$new Boolean(\"true\")");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl859Test() {
        Argument arg_ = directCalculate("$new Boolean(\"false\")");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl860Test() {
        Struct arg_ = directCalculateExc("$math.mod(1,0)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.DivideZeroException",err_.getClassName());
    }

    @Test
    public void processEl861Test() {
        Struct arg_ = directCalculateExc("$math.mod(1l,0)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.DivideZeroException",err_.getClassName());
    }

    @Test
    public void processEl862Test() {
        Struct arg_ = directCalculateExc("$math.quot(1,0)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.DivideZeroException",err_.getClassName());
    }

    @Test
    public void processEl863Test() {
        Struct arg_ = directCalculateExc("$math.quot(1l,0)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.DivideZeroException",err_.getClassName());
    }

    @Test
    public void processEl864Test() {
        Struct arg_ = directCalculateExc("$math.binMod(1,0)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.DivideZeroException",err_.getClassName());
    }

    @Test
    public void processEl865Test() {
        Struct arg_ = directCalculateExc("$math.binQuot(1,0)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.expressionlanguage.exceptions.DivideZeroException",err_.getClassName());
    }

    @Test
    public void processEl866Test() {
        Argument arg_ = directCalculate("$math.binMod(1,1)");
        assertEq(0,getNumber(arg_));
    }

    @Test
    public void processEl867Test() {
        Argument arg_ = directCalculate("$math.binQuot(1,1)");
        assertEq(1,getNumber(arg_));
    }

    @Test
    public void processEl868Test() {
        Argument arg_ = directCalculate("$math.plus(1)");
        assertEq(1,getNumber(arg_));
    }

    @Test
    public void processEl869Test() {
        Argument arg_ = directCalculate("$math.plus(1,2)");
        assertEq(3,getNumber(arg_));
    }

    @Test
    public void processEl870Test() {
        Argument arg_ = directCalculate("$math.minus(1)");
        assertEq(-1,getNumber(arg_));
    }

    @Test
    public void processEl871Test() {
        Argument arg_ = directCalculate("$math.minus(1,2)");
        assertEq(-1,getNumber(arg_));
    }

    @Test
    public void processEl872Test() {
        Argument arg_ = directCalculate("$math.minus(1l)");
        assertEq(-1,getNumber(arg_));
    }

    @Test
    public void processEl873Test() {
        Argument arg_ = directCalculate("$math.minus(1f)");
        assertEq(-1.0,getDouble(arg_));
    }

    @Test
    public void processEl874Test() {
        Argument arg_ = directCalculate("$math.minus(1d)");
        assertEq(-1.0,getDouble(arg_));
    }

    @Test
    public void processEl875Test() {
        Argument arg_ = directCalculate("$math.mult(3,2)");
        assertEq(6,getNumber(arg_));
    }

    @Test
    public void processEl876Test() {
        Argument arg_ = directCalculate("$math.negBin(0)");
        assertEq(-1,getNumber(arg_));
    }

    @Test
    public void processEl877Test() {
        Argument arg_ = directCalculate("$math.negBin(0l)");
        assertEq(-1,getNumber(arg_));
    }

    @Test
    public void processEl878Test() {
        Argument arg_ = directCalculate("$math.negBin(-1)");
        assertEq(0,getNumber(arg_));
    }

    @Test
    public void processEl879Test() {
        Argument arg_ = directCalculate("$math.negBin(-1l)");
        assertEq(0,getNumber(arg_));
    }

    @Test
    public void processEl880Test() {
        Argument arg_ = directCalculate("$math.and(1,2)");
        assertEq(0,getNumber(arg_));
    }

    @Test
    public void processEl881Test() {
        Argument arg_ = directCalculate("$math.or(1,2)");
        assertEq(3,getNumber(arg_));
    }

    @Test
    public void processEl882Test() {
        Argument arg_ = directCalculate("$math.xor(5,3)");
        assertEq(6,getNumber(arg_));
    }

    @Test
    public void processEl883Test() {
        Argument arg_ = directCalculate("$math.shiftLeft(1,2)");
        assertEq(4,getNumber(arg_));
    }

    @Test
    public void processEl884Test() {
        Argument arg_ = directCalculate("$math.shiftRight(4,2)");
        assertEq(1,getNumber(arg_));
    }

    @Test
    public void processEl885Test() {
        Argument arg_ = directCalculate("$math.bitShiftLeft(1,2)");
        assertEq(4,getNumber(arg_));
    }

    @Test
    public void processEl886Test() {
        Argument arg_ = directCalculate("$math.bitShiftRight(4,2)");
        assertEq(1,getNumber(arg_));
    }

    @Test
    public void processEl887Test() {
        Argument arg_ = directCalculate("$math.rotateLeft(1,2)");
        assertEq(4,getNumber(arg_));
    }

    @Test
    public void processEl888Test() {
        Argument arg_ = directCalculate("$math.rotateRight(4,2)");
        assertEq(1,getNumber(arg_));
    }

    @Test
    public void processEl889Test() {
        Argument arg_ = directCalculate("$math.lt(1,2)");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl890Test() {
        Argument arg_ = directCalculate("$math.lt(1,1)");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl891Test() {
        Argument arg_ = directCalculate("$math.gt(2,1)");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl892Test() {
        Argument arg_ = directCalculate("$math.gt(1,1)");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl893Test() {
        Argument arg_ = directCalculate("$math.le(1,2)");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl894Test() {
        Argument arg_ = directCalculate("$math.le(1,1)");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl895Test() {
        Argument arg_ = directCalculate("$math.ge(2,1)");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl896Test() {
        Argument arg_ = directCalculate("$math.ge(1,1)");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl897Test() {
        Argument arg_ = directCalculate("$math.le(2,1)");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl898Test() {
        Argument arg_ = directCalculate("$math.ge(1,2)");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl899Test() {
        Argument arg_ = directCalculate("$math.neg($true)");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl900Test() {
        Argument arg_ = directCalculate("$math.neg($false)");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl901Test() {
        Argument arg_ = directCalculate("\"\"!=$null");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl902Test() {
        Argument arg_ = directCalculate("0!=$null");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl903Test() {
        Argument arg_ = directCalculate("$new $int[]{}!=$new $int[]{}");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl904Test() {
        Argument arg_ = directCalculate("$null!=0");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl905Test() {
        Argument arg_ = directCalculate("$null==$null");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl906Test() {
        Argument arg_ = directCalculate("$true!=$null");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl907Test() {
        Argument arg_ = directCalculate("$true==$true");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl908Test() {
        Argument arg_ = directCalculate("$ObjectsUtil.getParent(0)");
        assertTrue(arg_.isNull());
    }

    @Test
    public void processEl909Test() {
        Argument arg_ = directCalculate("$ObjectsUtil.getParent($true)");
        assertTrue(arg_.isNull());
    }

    @Test
    public void processEl910Test() {
        Argument arg_ = directCalculate("$ObjectsUtil.getParent(\"\")");
        assertTrue(arg_.isNull());
    }

    @Test
    public void processEl911Test() {
        Argument arg_ = directCalculate("$ObjectsUtil.getParent($new code.util.Replacement(\"\",\"\"))");
        assertTrue(arg_.isNull());
    }

    @Test
    public void processEl912Test() {
        Argument arg_ = directCalculate("$new code.util.Replacement(\"\",\"\") != $null");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl913Test() {
        Argument arg_ = directCalculate("$ObjectsUtil.getParent($new $int[]{})");
        assertTrue(arg_.isNull());
    }

    @Test
    public void processEl914Test() {
        Argument arg_ = directCalculate("$ObjectsUtil.getParent($null)");
        assertTrue(arg_.isNull());
    }

    @Test
    public void processEl915Test() {
        Struct arg_ = directCalculateExc("Integer.parseInt($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl916Test() {
        Struct arg_ = directCalculateExc("Byte.parseByte($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl917Test() {
        Struct arg_ = directCalculateExc("Short.parseShort($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl918Test() {
        Struct arg_ = directCalculateExc("Long.parseLong($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl919Test() {
        Struct arg_ = directCalculateExc("Float.parseFloat($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl920Test() {
        Struct arg_ = directCalculateExc("Double.parseDouble($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl921Test() {
        Struct arg_ = directCalculateExc("Integer.parseInt($null,2)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl922Test() {
        Struct arg_ = directCalculateExc("Byte.parseByte($null,2)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl923Test() {
        Struct arg_ = directCalculateExc("Short.parseShort($null,2)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl924Test() {
        Struct arg_ = directCalculateExc("Long.parseLong($null,2)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl925Test() {
        Struct arg_ = directCalculateExc("$new Integer($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl926Test() {
        Struct arg_ = directCalculateExc("$new Byte($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl927Test() {
        Struct arg_ = directCalculateExc("$new Short($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl928Test() {
        Struct arg_ = directCalculateExc("$new Long($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl929Test() {
        Struct arg_ = directCalculateExc("$new Float($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl930Test() {
        Struct arg_ = directCalculateExc("$new Double($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }

    @Test
    public void processEl931Test() {
        Struct arg_ = directCalculateExc("Short.parseShort(\"\",2)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat", err_.getClassName());
    }

    @Test
    public void processEl932Test() {
        Struct arg_ = directCalculateExc("Short.parseShort(\"-\",2)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat", err_.getClassName());
    }

    @Test
    public void processEl933Test() {
        Struct arg_ = directCalculateExc("Integer.parseInt(\"g\",2)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat", err_.getClassName());
    }

    @Test
    public void processEl934Test() {
        Struct arg_ = directCalculateExc("Integer.parseInt(\"G\",2)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat", err_.getClassName());
    }

    @Test
    public void processEl935Test() {
        Struct arg_ = directCalculateExc("Integer.parseInt(\"1g\",2)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat", err_.getClassName());
    }

    @Test
    public void processEl936Test() {
        Struct arg_ = directCalculateExc("Integer.parseInt(\"1G\",2)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat", err_.getClassName());
    }

    @Test
    public void processEl937Test() {
        Struct arg_ = directCalculateExc("Integer.parseInt(\"1|\",2)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat", err_.getClassName());
    }

    @Test
    public void processEl938Test() {
        Struct arg_ = directCalculateExc("Integer.parseInt(\"|\",2)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat", err_.getClassName());
    }

    @Test
    public void processEl939Test() {
        Struct arg_ = directCalculateExc("Integer.parseInt(\"0\",37)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat", err_.getClassName());
    }

    @Test
    public void processEl940Test() {
        Struct arg_ = directCalculateExc("Integer.parseInt(\"0\",37)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat", err_.getClassName());
    }
    @Test
    public void processEl941Test() {
        Struct arg_ = directCalculateExc("Long.parseLong(\"9223372036854775808\",10)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat", err_.getClassName());
    }

    @Test
    public void processEl942Test() {
        Struct arg_ = directCalculateExc("Integer.parseInt(\"1!\",2)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat", err_.getClassName());
    }

    @Test
    public void processEl943Test() {
        Struct arg_ = directCalculateExc("Integer.parseInt(\"2\",2)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat", err_.getClassName());
    }

    @Test
    public void processEl944Test() {
        Struct arg_ = directCalculateExc("Integer.parseInt(\"12\",2)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat", err_.getClassName());
    }

    @Test
    public void processEl945Test() {
        Argument arg_ = directCalculate("Double.parseDouble(\"1.5\")");
        assertEq(1.5, getDouble(arg_));
    }

    @Test
    public void processEl946Test() {
        Argument arg_ = directCalculate("Double.parseDouble(\"1.5e1\")");
        assertEq(15.0, getDouble(arg_));
    }

    @Test
    public void processEl947Test() {
        Argument arg_ = directCalculate("Double.parseDouble(\"1.5E1\")");
        assertEq(15.0, getDouble(arg_));
    }

    @Test
    public void processEl948Test() {
        Argument arg_ = directCalculate("Double.parseDouble(\"15.0e-1\")");
        assertEq(1.5, getDouble(arg_));
    }

    @Test
    public void processEl949Test() {
        Argument arg_ = directCalculate("Double.parseDouble(\"1E1\")");
        assertEq(10.0, getDouble(arg_));
    }

    @Test
    public void processEl950Test() {
        Argument arg_ = directCalculate("Double.parseDouble(\"5E-1\")");
        assertEq(0.5, getDouble(arg_));
    }

    @Test
    public void processEl951Test() {
        Argument arg_ = directCalculate("Double.parseDouble(\"1e1\")");
        assertEq(10.0, getDouble(arg_));
    }

    @Test
    public void processEl952Test() {
        Argument arg_ = directCalculate("Double.parseDouble(\"5e-1\")");
        assertEq(0.5, getDouble(arg_));
    }

    @Test
    public void processEl953Test() {
        Struct arg_ = directCalculateExc("Double.parseDouble(\"1E\")");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat", err_.getClassName());
    }

    @Test
    public void processEl954Test() {
        Struct arg_ = directCalculateExc("Double.parseDouble(\"1t\")");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat", err_.getClassName());
    }

    @Test
    public void processEl955Test() {
        Struct arg_ = directCalculateExc("Double.parseDouble(\"1et\")");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat", err_.getClassName());
    }

    @Test
    public void processEl956Test() {
        Struct arg_ = directCalculateExc("Double.parseDouble(\"1e-t\")");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat", err_.getClassName());
    }
    @Test
    public void processEl957Test() {
        Struct arg_ = directCalculateExc("Double.parseDouble(\"1.t\")");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat", err_.getClassName());
    }

    @Test
    public void processEl958Test() {
        Argument arg_ = directCalculate("Double.parseDouble(\".5\")");
        assertEq(0.5, getDouble(arg_));
    }
    @Test
    public void processEl959Test() {
        Struct arg_ = directCalculateExc("Double.parseDouble(\"\")");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat", err_.getClassName());
    }

    @Test
    public void processEl960Test() {
        Argument arg_ = directCalculate("Double.parseDouble(\"-1\")");
        assertEq(-1.0, getDouble(arg_));
    }

    @Test
    public void processEl961Test() {
        Argument arg_ = directCalculate("Double.parseDouble(\"-1.5\")");
        assertEq(-1.5, getDouble(arg_));
    }

    @Test
    public void processEl962Test() {
        Argument arg_ = directCalculate("Double.parseDouble(\"-1.5e1\")");
        assertEq(-15.0, getDouble(arg_));
    }

    @Test
    public void processEl963Test() {
        Argument arg_ = directCalculate("Double.parseDouble(\"-1.5E1\")");
        assertEq(-15.0, getDouble(arg_));
    }

    @Test
    public void processEl964Test() {
        Argument arg_ = directCalculate("Double.parseDouble(\"-15.0e-1\")");
        assertEq(-1.5, getDouble(arg_));
    }

    @Test
    public void processEl965Test() {
        Argument arg_ = directCalculate("Double.parseDouble(\"-1E1\")");
        assertEq(-10.0, getDouble(arg_));
    }

    @Test
    public void processEl966Test() {
        Argument arg_ = directCalculate("Double.parseDouble(\"-5E-1\")");
        assertEq(-0.5, getDouble(arg_));
    }

    @Test
    public void processEl967Test() {
        Argument arg_ = directCalculate("Double.parseDouble(\"-1e1\")");
        assertEq(-10.0, getDouble(arg_));
    }

    @Test
    public void processEl968Test() {
        Argument arg_ = directCalculate("Double.parseDouble(\"-5e-1\")");
        assertEq(-0.5, getDouble(arg_));
    }

    @Test
    public void processEl969Test() {
        Argument arg_ = directCalculate("Double.parseDouble(\"-.5\")");
        assertEq(-0.5, getDouble(arg_));
    }

    @Test
    public void processEl970Test() {
        Argument arg_ = directCalculate("Double.parseDouble(\"-123456789123456789123456789.0\")");
        assertEq(-1.2345678912345678912e26, getDouble(arg_));
    }

    @Test
    public void processEl971Test() {
        Argument arg_ = directCalculate("Double.parseDouble(\"-0.0e-36\")");
        assertEq(-0.0, getDouble(arg_));
    }

    @Test
    public void processEl972Test() {
        Argument arg_ = directCalculate("Double.parseDouble(\"-1.0\")");
        assertEq(-1.0, getDouble(arg_));
    }

    @Test
    public void processEl973Test() {
        Argument arg_ = directCalculate("Double.parseDouble(\"-1e-123456789123456789123\")");
        assertEq(-0.0, getDouble(arg_));
    }

    @Test
    public void processEl974Test() {
        Argument arg_ = directCalculate("Double.parseDouble(\"-1e123456789123456789123\")");
        assertEq(Double.NEGATIVE_INFINITY, getDouble(arg_));
    }

    @Test
    public void processEl975Test() {
        Argument arg_ = directCalculate("-0xa");
        assertEq(-10, getNumber(arg_));
    }

    @Test
    public void processEl976Test() {
        Argument arg_ = directCalculate("$new $int[ ]{2i}");
        ArrayStruct res_ = (ArrayStruct) arg_.getStruct();
        assertEq(ARR_INT, res_.getClassName());
        Struct[] o_ = res_.getInstance();
        assertEq(1, o_.length);
        assertEq(2, ((NumberStruct) o_[0]).intStruct());
    }
    @Test
    public void processEl977Test() {
        Argument arg_ = directCalculate("$new $int[1] $instanceof $int[][]");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl978Test() {
        Argument arg_ = directCalculate("($lambda(String,charAt,$int)$instanceof $Fct<String,$int>)");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl979Test() {
        Argument arg_ = directCalculate("($lambda(String,charAt,$int)$instanceof $Fct<CharSequence,$int,$char>)");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl980Test() {
        Argument arg_ = directCalculate("($lambda(String,charAt,$int)$instanceof $Fct<CharSequence,Object,$char>)");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl981Test() {
        Argument arg_ = directCalculate("($lambda(String,charAt,$int)$instanceof $Fct<CharSequence,$int,String>)");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl982Test() {
        Argument arg_ = directCalculate("($lambda(String,charAt,$int)$instanceof $Fct<CharSequence,$int,?>)");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl983Test() {
        Argument arg_ = directCalculate("($lambda(String,charAt,$int)$instanceof $Fct<CharSequence,?,$char>)");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl984Test() {
        Argument arg_ = directCalculate("($lambda($Fct<?,?>,call,Object...)$instanceof $Fct<$Fct<?,?>,Object[],Object>)");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl985Test() {
        Argument arg_ = directCalculate("($lambda($Fct<?,?>,call,Object...)$instanceof $Fct<$Fct<String,?>,Object[],Object>)");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl986Test() {
        Argument arg_ = directCalculate("($lambda($Fct<?,?>,call,Object...)$instanceof $Fct<$Fct<?,String>,Object[],Object>)");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl987Test() {
        Argument arg_ = directCalculate("($lambda($Fct<String,String>,call,Object...)$instanceof $Fct<$Fct<?,?>,Object[],Object>)");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl988Test() {
        Argument arg_ = directCalculate("($lambda($Fct<String,?>,call,Object...)$instanceof $Fct<$Fct<?,String>,Object[],Object>)");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl989Test() {
        Argument arg_ = directCalculate("($lambda($Fct<?,String>,call,Object...)$instanceof $Fct<$Fct<String,?>,Object[],Object>)");
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl990Test() {
        Argument arg_ = directCalculate("($lambda($Fct<?,String>,call,Object...)$instanceof Integer)");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl991Test() {
        Argument arg_ = directCalculate("(0 $instanceof $Fct<?,String>)");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl992Test() {
        Argument arg_ = directCalculate("($new $int[0] $instanceof $Fct<?,String>)");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl993Test() {
        Argument arg_ = directCalculate("($new $int[0] $instanceof $Fct<?,String>[])");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl994Test() {
        Argument arg_ = directCalculate("($new Integer[0] $instanceof $Fct<?,String>)");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl995Test() {
        Argument arg_ = directCalculate("($new Integer[0] $instanceof $Fct<?,String>[])");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl996Test() {
        Argument arg_ = directCalculate("$lambda(String,charAt,$int)$instanceof $Fct<String,$int>");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl997Test() {
        Argument arg_ = directCalculate("Byte.parseByteOrNull(\"1\",10)");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl998Test() {
        Argument arg_ = directCalculate("Short.parseShortOrNull(\"1\",10)");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl999Test() {
        Argument arg_ = directCalculate("Integer.parseIntOrNull(\"1\",10)");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl1000Test() {
        Argument arg_ = directCalculate("Long.parseLongOrNull(\"1\",10)");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl1001Test() {
        Argument arg_ = directCalculate("Short.parseShortOrNull(\"1\")");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl1002Test() {
        Argument arg_ = directCalculate("Integer.parseIntOrNull(\"1\")");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl1003Test() {
        Argument arg_ = directCalculate("Long.parseLongOrNull(\"1\")");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl1004Test() {
        Argument arg_ = directCalculate("Float.parseFloatOrNull(\"1\")");
        assertEq(1.0, getDouble(arg_));
    }

    @Test
    public void processEl1005Test() {
        Argument arg_ = directCalculate("Double.parseDoubleOrNull(\"1\")");
        assertEq(1.0, getDouble(arg_));
    }

    @Test
    public void processEl1006Test() {
        Argument arg_ = directCalculate("Byte.parseByteOrNull(\"1\")");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl1007Test() {
        Argument arg_ = directCalculate("Short.parseShortOrNull($null)");
        assertTrue(arg_.isNull());
    }

    @Test
    public void processEl1008Test() {
        Argument arg_ = directCalculate("Integer.parseIntOrNull($null)");
        assertTrue(arg_.isNull());
    }

    @Test
    public void processEl1009Test() {
        Argument arg_ = directCalculate("Long.parseLongOrNull($null)");
        assertTrue(arg_.isNull());
    }

    @Test
    public void processEl1010Test() {
        Argument arg_ = directCalculate("Float.parseFloatOrNull($null)");
        assertTrue(arg_.isNull());
    }

    @Test
    public void processEl1011Test() {
        Argument arg_ = directCalculate("Double.parseDoubleOrNull($null)");
        assertTrue(arg_.isNull());
    }

    @Test
    public void processEl1012Test() {
        Argument arg_ = directCalculate("Byte.parseByteOrNull($null)");
        assertTrue(arg_.isNull());
    }

    @Test
    public void processEl1013Test() {
        Argument arg_ = directCalculate("Short.parseShortOrNull(\"\")");
        assertTrue(arg_.isNull());
    }

    @Test
    public void processEl1014Test() {
        Argument arg_ = directCalculate("Integer.parseIntOrNull(\"\")");
        assertTrue(arg_.isNull());
    }

    @Test
    public void processEl1015Test() {
        Argument arg_ = directCalculate("Long.parseLongOrNull(\"\")");
        assertTrue(arg_.isNull());
    }

    @Test
    public void processEl1016Test() {
        Argument arg_ = directCalculate("Float.parseFloatOrNull(\"\")");
        assertTrue(arg_.isNull());
    }

    @Test
    public void processEl1017Test() {
        Argument arg_ = directCalculate("Double.parseDoubleOrNull(\"\")");
        assertTrue(arg_.isNull());
    }

    @Test
    public void processEl1018Test() {
        Argument arg_ = directCalculate("Byte.parseByteOrNull(\"\")");
        assertTrue(arg_.isNull());
    }

    @Test
    public void processEl1019Test() {
        Argument arg_ = directCalculate("((Byte)1).intValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl1020Test() {
        Argument arg_ = directCalculate("((Byte)1).longValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl1021Test() {
        Argument arg_ = directCalculate("((Byte)1).shortValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl1022Test() {
        Argument arg_ = directCalculate("((Byte)1).byteValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl1023Test() {
        Argument arg_ = directCalculate("((Byte)1).floatValue()");
        assertEq(1.0, getDouble(arg_));
    }

    @Test
    public void processEl1024Test() {
        Argument arg_ = directCalculate("((Byte)1).doubleValue()");
        assertEq(1.0, getDouble(arg_));
    }


    @Test
    public void processEl1025Test() {
        Argument arg_ = directCalculate("((Short)1).intValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl1026Test() {
        Argument arg_ = directCalculate("((Short)1).longValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl1027Test() {
        Argument arg_ = directCalculate("((Short)1).shortValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl1028Test() {
        Argument arg_ = directCalculate("((Short)1).byteValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl1029Test() {
        Argument arg_ = directCalculate("((Short)1).floatValue()");
        assertEq(1.0, getDouble(arg_));
    }

    @Test
    public void processEl1030est() {
        Argument arg_ = directCalculate("((Short)1).doubleValue()");
        assertEq(1.0, getDouble(arg_));
    }

    @Test
    public void processEl10251Test() {
        Argument arg_ = directCalculate("((Character)1).intValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl10261Test() {
        Argument arg_ = directCalculate("((Character)1).longValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl10271Test() {
        Argument arg_ = directCalculate("((Character)1).shortValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl10281Test() {
        Argument arg_ = directCalculate("((Character)1).byteValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl10291Test() {
        Argument arg_ = directCalculate("((Character)1).floatValue()");
        assertEq(1.0, getDouble(arg_));
    }

    @Test
    public void processEl10301est() {
        Argument arg_ = directCalculate("((Character)1).doubleValue()");
        assertEq(1.0, getDouble(arg_));
    }

    @Test
    public void processEl10252Test() {
        Argument arg_ = directCalculate("((Float)1).intValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl10262Test() {
        Argument arg_ = directCalculate("((Float)1).longValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl10272Test() {
        Argument arg_ = directCalculate("((Float)1).shortValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl10282Test() {
        Argument arg_ = directCalculate("((Float)1).byteValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl10292Test() {
        Argument arg_ = directCalculate("((Float)1).floatValue()");
        assertEq(1.0, getDouble(arg_));
    }

    @Test
    public void processEl10302est() {
        Argument arg_ = directCalculate("((Float)1).doubleValue()");
        assertEq(1.0, getDouble(arg_));
    }

    @Test
    public void processEl10253Test() {
        Argument arg_ = directCalculate("((Double)1).intValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl10263Test() {
        Argument arg_ = directCalculate("((Double)1).longValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl10273Test() {
        Argument arg_ = directCalculate("((Double)1).shortValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl10283Test() {
        Argument arg_ = directCalculate("((Double)1).byteValue()");
        assertEq(1, getNumber(arg_));
    }

    @Test
    public void processEl10293Test() {
        Argument arg_ = directCalculate("((Double)1).floatValue()");
        assertEq(1.0, getDouble(arg_));
    }

    @Test
    public void processEl10303est() {
        Argument arg_ = directCalculate("((Double)1).doubleValue()");
        assertEq(1.0, getDouble(arg_));
    }

    @Test
    public void processEl103031est() {
        Argument arg_ = directCalculate("1d == 1f");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl103032est() {
        Argument arg_ = directCalculate("1d == ($double)1L");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl103034est() {
        Argument arg_ = directCalculate("1d == 2f");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl103034_est() {
        Argument arg_ = directCalculate("$new Long(1).equals(2f)");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl103035est() {
        Argument arg_ = directCalculate("1d == 2L");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl103036est() {
        Argument arg_ = directCalculate("1f == 1d");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl103037est() {
        Argument arg_ = directCalculate("1f == ($float)1L");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl103038est() {
        Argument arg_ = directCalculate("1f == 2d");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl103039est() {
        Argument arg_ = directCalculate("1f == 2L");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl103040est() {
        Argument arg_ = directCalculate("($double)1L == 1d");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl103041est() {
        Argument arg_ = directCalculate("($float)1L == 1f");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl103042est() {
        Argument arg_ = directCalculate("1L == 2d");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl103043est() {
        Argument arg_ = directCalculate("1L == 2f");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl103044Test() {
        Argument arg_ = directCalculate("$defaultValue($boolean)");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl103045Test() {
        Argument arg_ = directCalculate("$defaultValue($int)");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl103046Test() {
        Argument arg_ = directCalculate("$defaultValue($char)");
        assertEq(0, getChar(arg_));
    }

    @Test
    public void processEl103047Test() {
        Argument arg_ = directCalculate("$defaultValue(String)");
        assertTrue(arg_.isNull());
    }

    @Test
    public void processEl103048Test() {
        Argument arg_ = directCalculate("$null+$null");
        assertEq("", getString(arg_));
    }

    @Test
    public void processEl103049Test() {
        Argument arg_ = directCalculate("$null+\"\"");
        assertEq("", getString(arg_));
    }

    @Test
    public void processEl103050Test() {
        Argument arg_ = directCalculate("\"\"+$null");
        assertEq("", getString(arg_));
    }

    @Test
    public void processEl103051Test() {
        Argument arg_ = directCalculate("$class($Fct<Object>[]).isAssignableFrom($class($Fct<Object>))");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl103052Test() {
        Struct arg_ = directCalculateExc("$($Fct<String,$int>[])$lambda(String,length)");
        assertNotNull(arg_);
    }

    @Test
    public void processEl103053Test() {
        Argument arg_ = directCalculate("Double.parseDouble(\"15.0e+1\")");
        assertEq(150.0, getDouble(arg_));
    }

    @Test
    public void processEl103054Test() {
        Argument arg_ = directCalculate("Double.parseDouble(\"+15.0e1\")");
        assertEq(150.0, getDouble(arg_));
    }
    @Test
    public void processElCmp1est() {
        Argument arg_ = directCalculate("Number.compare(1L,2d)");
        assertEq(-1,getNumber(arg_));
    }

    @Test
    public void processElCmp2est() {
        Argument arg_ = directCalculate("Number.compare(1L,2f)");
        assertEq(-1,getNumber(arg_));
    }

    @Test
    public void processElCmp3est() {
        Argument arg_ = directCalculate("Number.compare(1f,2d)");
        assertEq(-1,getNumber(arg_));
    }

    @Test
    public void processElCmp4est() {
        Argument arg_ = directCalculate("Number.compare(1f,2f)");
        assertEq(-1,getNumber(arg_));
    }

    @Test
    public void processElCmp5est() {
        Argument arg_ = directCalculate("Number.compare(2d,1L)");
        assertEq(1,getNumber(arg_));
    }

    @Test
    public void processElCmp6est() {
        Argument arg_ = directCalculate("Number.compare(2f,1L)");
        assertEq(1,getNumber(arg_));
    }

    @Test
    public void processElCmp7est() {
        Argument arg_ = directCalculate("Number.compare(2d,1f)");
        assertEq(1,getNumber(arg_));
    }

    @Test
    public void processElCmp8est() {
        Argument arg_ = directCalculate("Number.compare(2f,1f)");
        assertEq(1,getNumber(arg_));
    }

    @Test
    public void processElCmp9est() {
        Argument arg_ = directCalculate("Number.compare(1L,1d)");
        assertEq(0,getNumber(arg_));
    }

    @Test
    public void processElCmp10est() {
        Argument arg_ = directCalculate("Number.compare(1L,1f)");
        assertEq(0,getNumber(arg_));
    }

    @Test
    public void processElCmp11est() {
        Argument arg_ = directCalculate("Number.compare(1d,1L)");
        assertEq(0,getNumber(arg_));
    }

    @Test
    public void processElCmp12est() {
        Argument arg_ = directCalculate("Number.compare(1f,1L)");
        assertEq(0,getNumber(arg_));
    }

    @Test
    public void processEl1LtG11test() {
        Argument arg_ = directCalculate("($float)1L < 2f");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl1LtG2test() {
        Argument arg_ = directCalculate("($double)1L < 2d");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl1LtG13test() {
        Argument arg_ = directCalculate("1d < ($double)2L");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl1LtG4test() {
        Argument arg_ = directCalculate("1f < ($float)2L");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl1LtG15test() {
        Argument arg_ = directCalculate("($float)2L < 1f");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl1LtG6test() {
        Argument arg_ = directCalculate("($double)2L < 1d");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl1LtG17test() {
        Argument arg_ = directCalculate("2d < ($double)1L");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl1LtG8test() {
        Argument arg_ = directCalculate("2f < ($float)1L");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl1LtG19test() {
        Argument arg_ = directCalculate("($float)1L > 2f");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl1LtG10test() {
        Argument arg_ = directCalculate("($double)1L > 2d");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl1LtG111test() {
        Argument arg_ = directCalculate("1d > ($double)2L");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl1LtG12test() {
        Argument arg_ = directCalculate("1f > ($float)2L");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl1LtG113test() {
        Argument arg_ = directCalculate("($float)2L > 1f");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl1LtG14test() {
        Argument arg_ = directCalculate("($float)2L > 1d");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl1LtG115test() {
        Argument arg_ = directCalculate("2d > ($double)1L");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl1LtG16test() {
        Argument arg_ = directCalculate("2f > ($float)1L");
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl103055Test() {
        Argument arg_ = directCalculate("Double.parseDoubleOrNull(\".\")");
        assertTrue(arg_.isNull());
    }

    @Test
    public void processEl103056Test() {
        Argument arg_ = directCalculate("Double.parseDoubleOrNull(\".5\")");
        assertEq(0.5,getDouble(arg_));
    }

    @Test
    public void processEl103057Test() {
        Argument arg_ = directCalculate("Double.parseDoubleOrNull(\"1.\")");
        assertEq(1.0,getDouble(arg_));
    }

    @Test
    public void processEl103058Test() {
        Argument arg_ = directCalculate("Double.parseDoubleOrNull(\"..\")");
        assertTrue(arg_.isNull());
    }

    @Test
    public void processEl103059Test() {
        Argument arg_ = directCalculate("Double.parseDoubleOrNull(\".e\")");
        assertTrue(arg_.isNull());
    }

    @Test
    public void processEl103060Test() {
        Argument arg_ = directCalculate("Double.parseDoubleOrNull(\".e1\")");
        assertTrue(arg_.isNull());
    }

    @Test
    public void processEl103061Test() {
        Argument arg_ = directCalculate("Double.parseDoubleOrNull(\"1e+\")");
        assertTrue(arg_.isNull());
    }

    @Test
    public void processEl103062Test() {
        Argument arg_ = directCalculate("Double.parseDoubleOrNull(\"1e-\")");
        assertTrue(arg_.isNull());
    }

    @Test
    public void processEl103063Test() {
        Argument arg_ = directCalculate("Long.toString(33,16)");
        assertEq("21", getString(arg_));
    }

    @Test
    public void processEl103064Test() {
        Argument arg_ = directCalculate("Long.sgn(0)");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl103065Test() {
        Argument arg_ = directCalculate("Byte.bin(($byte)0)");
        assertEq("0", getString(arg_));
    }

    @Test
    public void processEl103066Test() {
        Argument arg_ = directCalculate("Byte.oct(($byte)0)");
        assertEq("0", getString(arg_));
    }

    @Test
    public void processEl103067Test() {
        Argument arg_ = directCalculate("Byte.hex(($byte)0)");
        assertEq("0", getString(arg_));
    }

    @Test
    public void processEl103068Test() {
        Argument arg_ = directCalculate("Short.bin(($short)0)");
        assertEq("0", getString(arg_));
    }

    @Test
    public void processEl103069Test() {
        Argument arg_ = directCalculate("Short.oct(($short)0)");
        assertEq("0", getString(arg_));
    }

    @Test
    public void processEl103070Test() {
        Argument arg_ = directCalculate("Short.hex(($short)0)");
        assertEq("0", getString(arg_));
    }

    @Test
    public void processEl103071Test() {
        Argument arg_ = directCalculate("Integer.bin(0)");
        assertEq("0", getString(arg_));
    }

    @Test
    public void processEl103072Test() {
        Argument arg_ = directCalculate("Integer.oct(0)");
        assertEq("0", getString(arg_));
    }

    @Test
    public void processEl103073Test() {
        Argument arg_ = directCalculate("Integer.hex(0)");
        assertEq("0", getString(arg_));
    }

    @Test
    public void processEl103074Test() {
        Argument arg_ = directCalculate("Long.bin(0)");
        assertEq("0", getString(arg_));
    }

    @Test
    public void processEl103075Test() {
        Argument arg_ = directCalculate("Long.oct(0)");
        assertEq("0", getString(arg_));
    }

    @Test
    public void processEl103076Test() {
        Argument arg_ = directCalculate("Long.hex(0)");
        assertEq("0", getString(arg_));
    }

    @Test
    public void processEl103077Test() {
        Argument arg_ = directCalculate("$math.max(0,0)");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl103078Test() {
        Argument arg_ = directCalculate("$math.max(0,0L)");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl103077_Test() {
        Argument arg_ = directCalculate("$math.max(1f,1f)");
        assertEq(1.0, getDouble(arg_));
    }

    @Test
    public void processEl103078_Test() {
        Argument arg_ = directCalculate("$math.max(1d,1d)");
        assertEq(1.0, getDouble(arg_));
    }
    @Test
    public void processEl103079Test() {
        Argument arg_ = directCalculate("$math.min(0,0)");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl103080Test() {
        Argument arg_ = directCalculate("$math.min(0,0L)");
        assertEq(0, getNumber(arg_));
    }
    @Test
    public void processEl103079_Test() {
        Argument arg_ = directCalculate("$math.min(1f,1f)");
        assertEq(1.0, getDouble(arg_));
    }

    @Test
    public void processEl103080_Test() {
        Argument arg_ = directCalculate("$math.min(1d,1d)");
        assertEq(1.0, getDouble(arg_));
    }

    @Test
    public void processEl103081Test() {
        Argument arg_ = directCalculate("Long.compare(1,1)");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl103082Test() {
        Argument arg_ = directCalculate("((Long)1).compareTo(1L)");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl103083Test() {
        Argument arg_ = directCalculate("Short.compare(1s,1s)");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl103084Test() {
        Argument arg_ = directCalculate("((Short)1S).compareTo(1S)");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl103085Test() {
        Argument arg_ = directCalculate("Byte.compare(1y,1y)");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl103086Test() {
        Argument arg_ = directCalculate("((Byte)1Y).compareTo(1Y)");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl103087Test() {
        Argument arg_ = directCalculate("Float.compare(1f,1f)");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl103088Test() {
        Argument arg_ = directCalculate("((Float)1F).compareTo(1F)");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl103089Test() {
        Argument arg_ = directCalculate("Double.compare(1d,1d)");
        assertEq(0, getNumber(arg_));
    }

    @Test
    public void processEl103090Test() {
        Argument arg_ = directCalculate("((Double)1D).compareTo(1D)");
        assertEq(0, getNumber(arg_));
    }
    @Test
    public void processEl103091Test() {
        Argument arg_ = directCalculate("\"hello_word\".equalsIgnoreCase(\"hello word\")");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl103092Test() {
        Argument arg_ = directCalculate("\"hello word\".regionMatches(-1,\"\",0,0)");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl103093Test() {
        Argument arg_ = directCalculate("\"hello word\".regionMatches($true,0,\"\",-1,0)");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl103094Test() {
        Argument arg_ = directCalculate("\"hello word\".regionMatches(11,\"ab\",0,0)");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl103095Test() {
        Argument arg_ = directCalculate("\"hello word\".regionMatches($true,0,\"ab\",3,0)");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl103096Test() {
        Argument arg_ = directCalculate("\"hello word\".regionMatches(0,\"ab\",0,1)");
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl103097Test() {
        Argument arg_ = directCalculate("\"hello word\".regionMatches(0,\"hab\",0,2)");
        assertTrue(arg_.isFalse());
    }

    @Test
    public void processEl103098Test() {
        Argument arg_ = directCalculate("\"\\s\"");
        assertEq(" ", getString(arg_));
    }

    @Test
    public void processEl103099Test() {
        Argument arg_ = directCalculate("\"\"\"\nc\"\"\"");
        assertEq("c", getString(arg_));
    }

    @Test
    public void processEl103100Test() {
        Argument arg_ = directCalculate("\"\"\" \nc\"\"\"");
        assertEq("c", getString(arg_));
    }

    @Test
    public void processEl103101Test() {
        Argument arg_ = directCalculate("\"\"\" \nc\\\nh\"\"\"");
        assertEq("ch", getString(arg_));
    }

    @Test
    public void processEl103102Test() {
        Argument arg_ = directCalculate("\"\"\" \n c\"\"\"");
        assertEq("c", getString(arg_));
    }

    @Test
    public void processEl103103Test() {
        Argument arg_ = directCalculate("\"\"\" \n c h\"\"\"");
        assertEq("c h", getString(arg_));
    }

    @Test
    public void processEl103104Test() {
        Argument arg_ = directCalculate("\"\"\" \nc\nt\"\"\"");
        assertEq("c\nt", getString(arg_));
    }

    @Test
    public void processEl103105Test() {
        Argument arg_ = directCalculate("\"\"\" \nc \nt\"\"\"");
        assertEq("c\nt", getString(arg_));
    }

    @Test
    public void processEl103106Test() {
        Argument arg_ = directCalculate("\"\"\" \nc h \nt\"\"\"");
        assertEq("c h\nt", getString(arg_));
    }

    @Test
    public void processEl103107Test() {
        Argument arg_ = directCalculate("\"\"\" \nl\n \nc\"\"\"");
        assertEq("l\n\nc", getString(arg_));
    }

    @Test
    public void processEl103108Test() {
        Argument arg_ = directCalculate("\"\"\" \nl\nc \"\"\"");
        assertEq("l\nc", getString(arg_));
    }

    @Test
    public void processEl103109Test() {
        Argument arg_ = directCalculate("\"\"\"\n\\\"\"\";\"\"\"");
        assertEq("\"\"\";", getString(arg_));
    }

    @Test
    public void processEl103110Test() {
        Argument arg_ = directCalculate("\"\"\"\n\\\"\\\"\\\"\"\"\"");
        assertEq("\"\"\"", getString(arg_));
    }

    @Test
    public void processEl103111Test() {
        Argument arg_ = directCalculate("\"\"\" \nl\n\\s\nc\"\"\"");
        assertEq("l\n \nc", getString(arg_));
    }

    @Test
    public void processEl103112Test() {
        Argument arg_ = directCalculate("\"\"\" \nl\n\\t\nc\"\"\"");
        assertEq("l\n\t\nc", getString(arg_));
    }


    @Test
    public void processEl103113Test() {
        Argument arg_ = directCalculate("\"\"\" \nl\n\\f\nc\"\"\"");
        assertEq("l\n\f\nc", getString(arg_));
    }

    @Test
    public void processEl103114Test() {
        Argument arg_ = directCalculate("\"\"\" \nl\n\\r\nc\"\"\"");
        assertEq("l\n\r\nc", getString(arg_));
    }


    @Test
    public void processEl103115Test() {
        Argument arg_ = directCalculate("\"\"\" \nl\n\\b\nc\"\"\"");
        assertEq("l\n\b\nc", getString(arg_));
    }

    @Test
    public void processEl103116Test() {
        Argument arg_ = directCalculate("\"\"\"\n\"a\"\"\"");
        assertEq("\"a", getString(arg_));
    }

    @Test
    public void processEl103117Test() {
        Argument arg_ = directCalculate("\"\"\"\n\"\"a\"\"\"");
        assertEq("\"\"a", getString(arg_));
    }

    @Test
    public void processEl103118Test() {
        Argument arg_ = directCalculate("\"\"\" \nl\n\\u0020\nc\"\"\"");
        assertEq("l\n \nc", getString(arg_));
    }

    @Test
    public void processEl103119Test() {
        Argument arg_ = directCalculate("\"\"\" \nl\n\\uaaaa\nc\"\"\"");
        assertEq("l\n\uaaaa\nc", getString(arg_));
    }

    @Test
    public void processEl103120Test() {
        Argument arg_ = directCalculate("\"\"\" \nl\n\\uAAAA\nc\"\"\"");
        assertEq("l\n\uaaaa\nc", getString(arg_));
    }

    @Test
    public void processEl103121Test() {
        Argument arg_ = directCalculate("\"\"\" \n\\\\\"\"\"");
        assertEq("\\", getString(arg_));
    }

    @Test
    public void processEl103122Test() {
        Argument arg_ = directCalculate("\"\"\" \nc \\\nh\"\"\"");
        assertEq("c h", getString(arg_));
    }

    @Test
    public void processEl103123Test() {
        Argument arg_ = directCalculate("\"\"\" \nc\\\n h\"\"\"");
        assertEq("c h", getString(arg_));
    }

    @Test
    public void processEl103124Test() {
        Argument arg_ = directCalculate("\"\"\" \nc\\n h\"\"\"");
        assertEq("c\n h", getString(arg_));
    }

    @Test
    public void processEl113099Test() {
        Argument arg_ = directCalculate("'''\nc'''");
        assertEq("c", getString(arg_));
    }

    @Test
    public void processEl113100Test() {
        Argument arg_ = directCalculate("''' \nc'''");
        assertEq("c", getString(arg_));
    }

    @Test
    public void processEl113101Test() {
        Argument arg_ = directCalculate("''' \nc\\\nh'''");
        assertEq("ch", getString(arg_));
    }

    @Test
    public void processEl113102Test() {
        Argument arg_ = directCalculate("''' \n c'''");
        assertEq("c", getString(arg_));
    }

    @Test
    public void processEl113103Test() {
        Argument arg_ = directCalculate("''' \n c h'''");
        assertEq("c h", getString(arg_));
    }

    @Test
    public void processEl113104Test() {
        Argument arg_ = directCalculate("''' \nc\nt'''");
        assertEq("c\nt", getString(arg_));
    }

    @Test
    public void processEl113105Test() {
        Argument arg_ = directCalculate("''' \nc \nt'''");
        assertEq("c\nt", getString(arg_));
    }

    @Test
    public void processEl113106Test() {
        Argument arg_ = directCalculate("''' \nc h \nt'''");
        assertEq("c h\nt", getString(arg_));
    }

    @Test
    public void processEl113107Test() {
        Argument arg_ = directCalculate("''' \nl\n \nc'''");
        assertEq("l\n\nc", getString(arg_));
    }

    @Test
    public void processEl113108Test() {
        Argument arg_ = directCalculate("''' \nl\nc '''");
        assertEq("l\nc", getString(arg_));
    }

    @Test
    public void processEl113109Test() {
        Argument arg_ = directCalculate("'''\n\\''';'''");
        assertEq("''';", getString(arg_));
    }

    @Test
    public void processEl113110Test() {
        Argument arg_ = directCalculate("'''\n\\'\\'\\''''");
        assertEq("'''", getString(arg_));
    }

    @Test
    public void processEl113111Test() {
        Argument arg_ = directCalculate("''' \nl\n\\s\nc'''");
        assertEq("l\n \nc", getString(arg_));
    }

    @Test
    public void processEl113112Test() {
        Argument arg_ = directCalculate("''' \nl\n\\t\nc'''");
        assertEq("l\n\t\nc", getString(arg_));
    }


    @Test
    public void processEl113113Test() {
        Argument arg_ = directCalculate("''' \nl\n\\f\nc'''");
        assertEq("l\n\f\nc", getString(arg_));
    }

    @Test
    public void processEl113114Test() {
        Argument arg_ = directCalculate("''' \nl\n\\r\nc'''");
        assertEq("l\n\r\nc", getString(arg_));
    }


    @Test
    public void processEl113115Test() {
        Argument arg_ = directCalculate("''' \nl\n\\b\nc'''");
        assertEq("l\n\b\nc", getString(arg_));
    }

    @Test
    public void processEl113116Test() {
        Argument arg_ = directCalculate("'''\n'a'''");
        assertEq("'a", getString(arg_));
    }

    @Test
    public void processEl113117Test() {
        Argument arg_ = directCalculate("'''\n''a'''");
        assertEq("''a", getString(arg_));
    }

    @Test
    public void processEl113118Test() {
        Argument arg_ = directCalculate("''' \nl\n\\u0020\nc'''");
        assertEq("l\n \nc", getString(arg_));
    }

    @Test
    public void processEl113119Test() {
        Argument arg_ = directCalculate("''' \nl\n\\uaaaa\nc'''");
        assertEq("l\n\uaaaa\nc", getString(arg_));
    }

    @Test
    public void processEl113120Test() {
        Argument arg_ = directCalculate("''' \nl\n\\uAAAA\nc'''");
        assertEq("l\n\uaaaa\nc", getString(arg_));
    }

    @Test
    public void processEl113121Test() {
        Argument arg_ = directCalculate("''' \n\\\\'''");
        assertEq("\\", getString(arg_));
    }

    @Test
    public void processEl113122Test() {
        Argument arg_ = directCalculate("''' \nc \\\nh'''");
        assertEq("c h", getString(arg_));
    }

    @Test
    public void processEl113123Test() {
        Argument arg_ = directCalculate("''' \nc\\\n h'''");
        assertEq("c h", getString(arg_));
    }

    @Test
    public void processEl113124Test() {
        Argument arg_ = directCalculate("''' \nc\\n h'''");
        assertEq("c\n h", getString(arg_));
    }

    @Test
    public void processElLineRetTest() {
        Argument arg_ = directCalculate("\"a\\\nb\"");
        assertEq("ab", getString(arg_));
    }

    private static Argument directCalculate(String _el) {
        ContextEl c_ = analyze(_el);
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,c_);
        addImportingPage(c_, stackCall_);
        Argument arg_ = tryCalculate(c_, stackCall_);
        assertNull(stackCall_.getCallingState());
        return arg_;
    }

    private static Struct directCalculateExc(String _el) {
        ContextEl c_ = analyze(_el);
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,c_);
        addImportingPage(c_, stackCall_);
        tryCalculate(c_, stackCall_);
        assertNotNull(((CustomFoundExc) stackCall_.getCallingState()).getStruct());
        return ((CustomFoundExc) stackCall_.getCallingState()).getStruct();
    }

    private static ContextEl analyze(String _el) {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/ExTwo", addonFileStaticResult(_el));
        return contextEl(files_);
    }

    private static Argument calculateIndirectLocalVars(String _var, String _className) {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", file());
        ContextEl cont_ = contextEl(files_);
        Struct fresh_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, _className,cont_.getClasses().getClassBody(_className), "", -1);
        ExecFieldBlock f_ = fetchField(cont_.getClasses().getClassBody(_className));
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,cont_);
        addImportingPage(cont_, stackCall_);
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(fresh_);
        lv_.setClassName(_className);
        stackCall_.getLastPage().putValueVar(_var, lv_);
        stackCall_.getLastPage().setGlobalArgumentStruct(fresh_);
        ExpressionLanguage el_ = new ExpressionLanguage(f_.getEl(cont_,0));
        Argument arg_ = ExpressionLanguage.tryToCalculate(cont_, el_, 0, stackCall_);
        assertNull(stackCall_.getCallingState());
        return arg_;

    }

    private static Argument calculateIndirectLoopVars(String _var, String _className) {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", file());
        ContextEl cont_ = contextEl(files_);
        Struct fresh_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, _className,cont_.getClasses().getClassBody(_className), "", -1);
        ExecFieldBlock f_ = fetchField(cont_.getClasses().getClassBody(_className));
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,cont_);
        addImportingPage(cont_, stackCall_);
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(fresh_);
        lv_.setClassName(_className);
        stackCall_.getLastPage().getRefParams().put(_var, new VariableWrapper(lv_));
        stackCall_.getLastPage().setGlobalArgumentStruct(fresh_);
        ExpressionLanguage el_ = new ExpressionLanguage(f_.getEl(cont_,0));
        Argument arg_ = ExpressionLanguage.tryToCalculate(cont_, el_, 0, stackCall_);
        assertNull(stackCall_.getCallingState());
        return arg_;

    }

    private static Argument calculateIndirect(String _className) {
        String var_ = "temp";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", file());
        ContextEl cont_ = contextEl(files_);
        Struct fresh_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, _className,cont_.getClasses().getClassBody(_className), "", -1);
        ExecFieldBlock f_ = fetchField(cont_.getClasses().getClassBody(_className));
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,cont_);
        addImportingPage(cont_, stackCall_);
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(fresh_);
        lv_.setClassName(_className);
        stackCall_.getLastPage().putValueVar(var_, lv_);
        stackCall_.getLastPage().setGlobalArgumentStruct(fresh_);
        ExpressionLanguage el_ = new ExpressionLanguage(f_.getEl(cont_, 0));
        Argument arg_ = ExpressionLanguage.tryToCalculate(cont_, el_, 0, stackCall_);
        assertNull(stackCall_.getCallingState());
        return arg_;
    }

    private static Argument tryCalculate(ContextEl _context, StackCall _stackCall) {
        ExecRootBlock cl_ = _context.getClasses().getClassBody("code.formathtml.classes.Apply");
        _stackCall.getLastPage().setGlobalClass("code.formathtml.classes.Apply");
        ExecFieldBlock f_ = fetchField(cl_);
        ExpressionLanguage el_ = new ExpressionLanguage(f_.getEl(_context,0));
        return ExpressionLanguage.tryToCalculate(_context,el_,0, _stackCall);
    }

    private static ExecFieldBlock fetchField(ExecRootBlock _cl) {
        ExecFieldBlock f_;
        if (!_cl.getAllInstanceMembers().isEmpty()) {
            f_ = (ExecFieldBlock) _cl.getAllInstanceMembers().first();
        } else {
            f_ = (ExecFieldBlock) _cl.getAllStaticMembers().first();
        }
        return f_;
    }

    private static String addonFileStaticResult(String _el) {
        StringBuilder str_ = new StringBuilder();
        str_.append("$public $class code.formathtml.classes.Apply {\n");
        str_.append(" $public $static $final java.lang.Object result = ");
        str_.append(_el);
        str_.append(";\n");
        str_.append("}");
        return str_.toString();
    }

    private static String file() {
        StringBuilder str_ = new StringBuilder();
        str_.append("\n");
        str_.append("$public $class code.formathtml.classes.Composite {\n");
        str_.append("\n");
        str_.append("    $public $int integer;\n");
        str_.append("\n");
        str_.append("}\n");
        str_.append("\n");
        str_.append("\n");
        return str_.toString();
    }

    private static void addImportingPage(ContextEl _conf, StackCall _stackCall) {
        ExecutingUtil.addPage(_conf,new CommonMethodPageEl(Argument.createVoid(),""), _stackCall);
    }

    private static ContextEl contextEl(StringMap<String> _files) {
        AnalyzedTestContext cont_ = ctxAna();
        validateAll(_files, cont_);
        assertTrue(isEmptyErrors(cont_));
        return cont_.getContext();
    }
}
