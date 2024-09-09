package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.blocks.ExecFieldBlock;
import code.expressionlanguage.exec.blocks.ExecHelperBlocks;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.CommonMethodPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.methods.ProcessMethodCommon;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.sample.CustLgNames;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import org.junit.Test;

public final class ExpressionLanguageTest extends ProcessMethodCommon {

    private static final String ARR_INT = "[$int";
    private static final String ARR_ARR_INT = "[[$int";
    private static final String ARR_INTEGER = "[java.lang.Integer";
    private static final String ARR_ARR_INTEGER = "[[java.lang.Integer";
    private static final String COMPOSITE = "code.formathtml.classes.Composite";

    @Test
    public void processEl0Test() {
        assertEq(1L, getNumber(directCalculate("$math.mod(5,2)")));
    }
    @Test
    public void processEl1Test() {
        assertEq(5L, getNumber(directCalculate("5")));
    }

    @Test
    public void processEl2Test() {
        assertEq(Long.MAX_VALUE, getNumber(directCalculate("$static(java.lang.Long).MAX_VALUE")));
    }

    @Test
    public void processEl3Test() {
        assertEq(9L, getNumber(directCalculate("(1+2)*3")));
    }

    @Test
    public void processEl4Test() {
        assertEq(2L, getNumber(directCalculate("1- -1")));
    }

    @Test
    public void processEl5Test() {
        assertEq(7L, getNumber(directCalculate("1+2*3")));
    }

    @Test
    public void processEl6Test() {
        assertEq(1L, getNumber(directCalculate("- -1")));
    }

    @Test
    public void processEl7Test() {
        assertEq(8L, getNumber(directCalculate("$static($math).abs(-8l)")));
    }

    @Test
    public void processEl8Test() {
        assertEq(8L, getNumber(directCalculate("$static($math).abs(8l)")));
    }

    @Test
    public void processEl8_Test() {
        assertEq(8.0, getDouble(directCalculate("$static($math).abs(8f)")));
    }

    @Test
    public void processEl_8Test() {
        assertEq(8.0, getDouble(directCalculate("$static($math).abs(8d)")));
    }
    @Test
    public void processEl9Test() {
        assertEq(0, getNumber(calculateIndirect(COMPOSITE)));
    }

    @Test
    public void processEl10Test() {
        assertEq(40908, getNumber(directCalculate("40908c")));
    }

    @Test
    public void processEl11Test() {
        assertEq(40907, getNumber(directCalculate("'\\u9fcb'")));
    }

    @Test
    public void processEl12Test() {
        assertEq('\\', getNumber(directCalculate("'\\\\'")));
    }

    @Test
    public void processEl13Test() {
        assertEq('\'', getNumber(directCalculate("'\\''")));
    }

    @Test
    public void processEl14Test() {
        assertEq('"', getNumber(directCalculate("'\"'")));
    }

    @Test
    public void processEl15Test() {
        assertEq('\n', getNumber(directCalculate("'\\n'")));
    }

    @Test
    public void processEl16Test() {
        assertEq(0, getNumber(calculateIndirectLocalVars("v", COMPOSITE)));
    }

    @Test
    public void processEl17Test() {
        assertEq(0, getNumber(calculateIndirectLoopVars("v", COMPOSITE)));
    }

    @Test
    public void processEl18Test() {
        assertTrue(directCalculate("5 $instanceof java.lang.Number"));
    }

    @Test
    public void processEl19Test() {
        assertTrue(directCalculate("'5' $instanceof java.lang.Number"));
    }

    @Test
    public void processEl20Test() {
        assertFalse(directCalculate("!('5' $instanceof java.lang.Number)"));
    }

    @Test
    public void processEl21Test() {
        assertTrue(directCalculate("1+1==2"));
    }

    @Test
    public void processEl22Test() {
        assertFalse(directCalculate("1+1!=2"));
    }

    @Test
    public void processEl23Test() {
        assertFalse(directCalculate("1+1==2&&1+0==8"));
    }

    @Test
    public void processEl24Test() {
        assertTrue(directCalculate("1+1!=2||1+7==8"));
    }

    @Test
    public void processEl25Test() {
        assertTrue(directCalculate("1+1==2&&(1+0==8||3*3==9)"));
    }

    @Test
    public void processEl26Test() {
        assertTrue(directCalculate("1+1==2||1+6==8&&1==1"));
    }

    @Test
    public void processEl29Test() {
        assertTrue(directCalculate("1+1==2||1/0>8"));
    }

    @Test
    public void processEl30Test() {
        assertEq(-1, getNumber(directCalculate("(($long)-1i)")));
    }

    @Test
    public void processEl31Test() {
        assertEq(8, getNumber(directCalculate("$static($math).abs(-8i)")));
    }

    @Test
    public void processEl32Test() {
        assertEq(8, getNumber(directCalculate("$static($math).abs(8i)")));
    }

    @Test
    public void processEl33Test() {
        assertEq(8, getNumber(directCalculate("$static($math).abs(-8I)")));
    }

    @Test
    public void processEl34Test() {
        assertEq(8, getNumber(directCalculate("$static($math).abs(8I)")));
    }

    @Test
    public void processEl35Test() {
        assertEq(8L, getNumber(directCalculate("$static($math).abs(-8L)")));
    }

    @Test
    public void processEl36Test() {
        assertEq(8L, getNumber(directCalculate("$static($math).abs(8L)")));
    }

    @Test
    public void processEl37Test() {
        assertEq("\nnew line", getString(directCalculate("\"\\nnew line\"")));
    }

    @Test
    public void processEl38Test() {
        assertEq("", getString(directCalculate("\"\".trim()")));
    }

    @Test
    public void processEl39Test() {
        assertFalse(directCalculate("$null $instanceof java.lang.Object"));
    }

    @Test
    public void processEl40Test() {
        assertEq("abc", getString(directCalculate("\"abc\".trim()")));
    }

    @Test
    public void processEl41Test() {
        assertEq("abc", getString(directCalculate("\" abc\".trim()")));
    }

    @Test
    public void processEl42Test() {
        assertEq("abc", getString(directCalculate("\"abc \".trim()")));
    }

    @Test
    public void processEl43Test() {
        assertEq("abc", getString(directCalculate("\" abc \".trim()")));
    }

    @Test
    public void processEl44Test() {
        assertEq("", getString(directCalculate("\" \".trim()")));
    }

    @Test
    public void processEl45Test() {
        Struct arg_ = directCalculate("\"hello word\".splitStrings(\"e\",\"o\")");
        ArrayStruct arr_ = (ArrayStruct) arg_;
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
        Struct arg_ = directCalculate("$new $int[1i]");
        ArrayStruct res_ = (ArrayStruct) arg_;
        assertEq(ARR_INT, res_.getClassName());
        assertEq(1, res_.getInstance().length);
        assertEq(0, ((NumberStruct) res_.getInstance()[0]).intStruct());
    }

    @Test
    public void processEl64Test() {
        Struct arg_ = directCalculate("$new $int[1i][]");
        ArrayStruct res_ = (ArrayStruct) arg_;
        assertEq(ARR_ARR_INT, res_.getClassName());
        assertEq(1, res_.getInstance().length);
        assertSame(NullStruct.NULL_VALUE, res_.getInstance()[0]);
    }

    @Test
    public void processEl65Test() {
        Struct arg_ = directCalculate("$new java.lang.Integer[2i]");
        ArrayStruct res_ = (ArrayStruct) arg_;
        assertEq(ARR_INTEGER, res_.getClassName());
        assertEq(2, res_.getInstance().length);
        assertSame(NullStruct.NULL_VALUE, res_.getInstance()[0]);
        assertSame(NullStruct.NULL_VALUE, res_.getInstance()[1]);
    }

    @Test
    public void processEl66Test() {
        Struct arg_ = directCalculate("$new java.lang.Integer[2i][]");
        ArrayStruct res_ = (ArrayStruct) arg_;
        assertEq(ARR_ARR_INTEGER, res_.getClassName());
        assertEq(2, res_.getInstance().length);
        assertSame(NullStruct.NULL_VALUE, res_.getInstance()[0]);
        assertSame(NullStruct.NULL_VALUE, res_.getInstance()[1]);
    }

    @Test
    public void processEl70Test() {
        assertFalse(directCalculate("!!$false"));
    }

    @Test
    public void processEl72Test() {
        assertEq((byte) 127, getNumber(directCalculate("$static(java.lang.Byte).MAX_VALUE")));
    }

    @Test
    public void processEl77Test() {
        assertEq(11, getNumber(directCalculate("(\"Hello\\\\\"+\"World\").length()")));
    }

    @Test
    public void processEl78Test() {
        assertEq(11, getNumber(directCalculate("(\"Hello\\\"\"+\"World\").length()")));
    }

    @Test
    public void processEl79Test() {
        assertEq(7, getNumber(directCalculate("(\"Hello\\\\\"+'\\\\').length()")));
    }

    @Test
    public void processEl80Test() {
        assertEq(7, getNumber(directCalculate("(\"Hello\\\"\"+'\\'').length()")));
    }

    @Test
    public void processEl87Test() {
        assertEq(0, getNumber(directCalculate("$bool(1>0,0i,1i)")));
    }

    @Test
    public void processEl88Test() {
        assertEq(1, getNumber(directCalculate("$bool(1<0,0i,1i)")));
    }

    @Test
    public void processEl89Test() {
        assertEq(0, getNumber(directCalculate("$bool(1>0,0i,1i/0i)")));
    }

    @Test
    public void processEl90Test() {
        assertEq(1, getNumber(directCalculate("$bool(1<0,1i/0i,1i)")));
    }

    @Test
    public void processEl95Test() {
        assertEq(0, getNumber(directCalculate("($new $int[1i])[0i]")));
    }

    @Test
    public void processEl96Test() {
        Struct arg_ = directCalculate("$new $int[]{2i}");
        ArrayStruct res_ = (ArrayStruct) arg_;
        assertEq(ARR_INT, res_.getClassName());
        Struct[] o_ = res_.getInstance();
        assertEq(1, o_.length);
        assertEq(2, ((NumberStruct) o_[0]).intStruct());
    }

    @Test
    public void processEl97Test() {
        Struct arg_ = directCalculate("$new $int[]{3i,7i}");
        ArrayStruct res_ = (ArrayStruct) arg_;
        assertEq(ARR_INT, res_.getClassName());
        Struct[] o_ = res_.getInstance();
        assertEq(2, o_.length);
        assertEq(3, ((NumberStruct) o_[0]).intStruct());
        assertEq(7, ((NumberStruct) o_[1]).intStruct());
    }

    @Test
    public void processEl98Test() {
        Struct arg_ = directCalculate("$new $int[]{}");
        ArrayStruct res_ = (ArrayStruct) arg_;
        assertEq(ARR_INT, res_.getClassName());
        Struct[] o_ = res_.getInstance();
        assertEq(0, o_.length);
    }

    @Test
    public void processEl99Test() {
        Struct arg_ = directCalculate("$new java.lang.Integer[]{3i,7i}");
        ArrayStruct res_ = (ArrayStruct) arg_;
        assertEq(ARR_INTEGER, res_.getClassName());
        Struct[] o_ = res_.getInstance();
        assertEq(2, o_.length);
        assertEq(3, ((NumberStruct) o_[0]).intStruct());
        assertEq(7, ((NumberStruct) o_[1]).intStruct());
    }

    @Test
    public void processEl119Test() {
        assertEq(9L, getNumber(directCalculate("(1y+2y)*3")));
    }

    @Test
    public void processEl120Test() {
        assertEq(9L, getNumber(directCalculate("(1s+2y)*3")));
    }

    @Test
    public void processEl121Test() {
        assertEq(1, getNumber(directCalculate("- -1y")));
    }

    @Test
    public void processEl122Test() {
        assertEq(-1, getNumber(directCalculate("-1y")));
    }

    @Test
    public void processEl123Test() {
        int max_ = Byte.MAX_VALUE + Byte.MAX_VALUE;
        assertEq(max_, getNumber(directCalculate("($int)(java.lang.Byte.MAX_VALUE+java.lang.Byte.MAX_VALUE)")));
    }

    @Test
    public void processEl123FailTest() {
        assertEq(1, getNumber(directCalculate("+1y")));
    }

    @Test
    public void processEl124Test() {
        assertEq(-1, getNumber(directCalculate("+-1y")));
    }

    @Test
    public void processEl125Test() {
        assertEq(0.25d, getDouble(directCalculate("-.25e0+.5")));
    }

    @Test
    public void processEl128Test() {
        assertEq(16L, getNumber(directCalculate("1_0+2*3")));
    }

    @Test
    public void processEl129Test() {
        assertEq(1L, getNumber(directCalculate("$static($math).mod(-8l,3l)")));
    }

    @Test
    public void processEl130Test() {
        assertEq(-3L, getNumber(directCalculate("$static($math).quot(-8l,3l)")));
    }

    @Test
    public void processEl131Test() {
        Struct arg_ = directCalculate("$new $int[1i][1i]");
        ArrayStruct res_ = (ArrayStruct) arg_;
        assertEq(ARR_ARR_INT, res_.getClassName());
        assertEq(1, res_.getInstance().length);
        assertEq(ARR_INT, ((ArrayStruct) res_.getInstance()[0]).getClassName());
        assertEq(1, ((ArrayStruct) res_.getInstance()[0]).getInstance().length);
        assertEq(0, ((NumberStruct) ((ArrayStruct) res_.getInstance()[0]).getInstance()[0]).intStruct());
    }

    @Test
    public void processEl132Test() {
        Struct arg_ = directCalculate("$new java.lang.Integer[1i][1i]");
        ArrayStruct res_ = (ArrayStruct) arg_;
        assertEq(ARR_ARR_INTEGER, res_.getClassName());
        assertEq(1, res_.getInstance().length);
        assertEq(ARR_INTEGER, ((ArrayStruct) res_.getInstance()[0]).getClassName());
        assertSame(NullStruct.NULL_VALUE, (((ArrayStruct) res_.getInstance()[0]).getInstance())[0]);
    }

    @Test
    public void processEl133Test() {
        assertEq(9L, getNumber(directCalculate("($double)(1 + 2) * 3.0")));
    }

    @Test
    public void processEl134Test() {
        assertEq(2L, getNumber(directCalculate(" 2.0 + ($double)$static($math). quot( -8l, 3l) + 3.0")));
    }

    @Test
    public void processEl135Test() {
        assertEq(3L, getNumber(directCalculate("1 + 2 ")));
    }

    @Test
    public void processEl136Test() {
        assertEq(3L, getNumber(directCalculate("1. + 2. ")));
    }

    @Test
    public void processEl137Test() {
        assertEq(3L, getNumber(directCalculate("1.d + 2.d ")));
    }

    @Test
    public void processEl138Test() {
        assertEq(0.25d, getDouble(directCalculate("-.2_5e0+.5")));
    }

    @Test
    public void processEl139Test() {
        assertEq(0.25d, getDouble(directCalculate("-.25e0_0+.5")));
    }

    @Test
    public void processEl140Test() {
        assertEq(12L, getNumber(directCalculate("1_0.d + 2.d ")));
    }

    @Test
    public void processEl141Test() {
        assertEq(10.5d, getDouble(directCalculate("1.05e1")));
    }

    @Test
    public void processEl142Test() {
        assertEq(10.0625d, getDouble(directCalculate("1.00625e1")));
    }

    @Test
    public void processEl143Test() {
        assertEq(10.0625d, getDouble(directCalculate("100.625e-1")));
    }

    @Test
    public void processEl144Test() {
        assertEq(100.625d, getDouble(directCalculate("100.625")));
    }

    @Test
    public void processEl145Test() {
        assertEq(1.2345678912345678912e26, getDouble(directCalculate("123456789123456789123456789.0")));
    }

    @Test
    public void processEl147Test() {
        assertEq(1.2345678912345678912e26, getDouble(directCalculate("123456789123456789123456789.")));
    }

    @Test
    public void processEl148Test() {
        assertEq(1.2345678912345678912e25, getDouble(directCalculate("123456789123456789123456789.e-1")));
    }

    @Test
    public void processEl149Test() {
        assertEq(1.2345678912345678912e27, getDouble(directCalculate("123456789123456789123456789.e1")));
    }

    @Test
    public void processEl150Test() {
        assertEq(1234560.0, getDouble(directCalculate("123456.e1")));
    }

    @Test
    public void processEl151Test() {
        assertEq(.078125e-1,getDouble(directCalculate(".078125e-1")));
    }

    @Test
    public void processEl152Test() {
        assertEq(1.2345678912345678912e-10,getDouble(directCalculate("123456789123456789123456789.0e-36")));
    }

    @Test
    public void processEl153Test() {
        assertEq(0.0, getDouble(directCalculate("0.0e-36")));
    }

    @Test
    public void processEl154Test() {
        assertEq(-0.0,getDouble(directCalculate("-0.0e-36")));
    }

    @Test
    public void processEl155Test() {
        assertEq(0.0625, getDouble(directCalculate("0.625e-1")));
    }

    @Test
    public void processEl156Test() {
        assertEq(0.0625, getDouble(directCalculate(".625e-1")));
    }

    @Test
    public void processEl157Test() {
        assertEq(6.25, getDouble(directCalculate("0.625e1")));
    }

    @Test
    public void processEl158Test() {
        assertEq(6.25, getDouble(directCalculate(".625e1")));
    }

    @Test
    public void processEl159Test() {
        assertEq(0.625, getDouble(directCalculate("0.625e0")));
    }

    @Test
    public void processEl160Test() {
        assertEq(0.625, getDouble(directCalculate(".625e0")));
    }

    @Test
    public void processEl161Test() {
        assertEq(-6.25,getDouble(directCalculate("-.625e1")));
    }

    @Test
    public void processEl162Test() {
        assertEq(-6.0,getDouble(directCalculate("-.6e1")));
    }

    @Test
    public void processEl163Test() {
        assertEq(-6.0,getDouble(directCalculate("-.60e1")));
    }

    @Test
    public void processEl164Test() {
        assertEq(6.0, getDouble(directCalculate(".6e1")));
    }

    @Test
    public void processEl165Test() {
        assertEq(60.0, getDouble(directCalculate(".6e2")));
    }

    @Test
    public void processEl166Test() {
        assertEq(1.2345678912345678912e27, getDouble(directCalculate("123456789123456789123456789.1e1")));
    }

    @Test
    public void processEl167Test() {
        assertEq(10.0, getDouble(directCalculate("100.e-1")));
    }

    @Test
    public void processEl168Test() {
        assertEq(-10.0,getDouble(directCalculate("-100.e-1")));
    }

    @Test
    public void processEl169Test() {
        assertEq(-10.0,getDouble(directCalculate("-1.e1")));
    }

    @Test
    public void processEl170Test() {
        assertEq(-1.0,getDouble(directCalculate("-1.")));
    }

    @Test
    public void processEl171Test() {
        assertEq(0.0, getDouble(directCalculate("1e-123456789123456789123")));
    }

    @Test
    public void processEl172Test() {
        assertEq(-0.0,getDouble(directCalculate("-1e-123456789123456789123")));
    }

    @Test
    public void processEl173Test() {
        assertEq(Double.POSITIVE_INFINITY, getDouble(directCalculate("1e123456789123456789123")));
    }

    @Test
    public void processEl174Test() {
        assertEq(Double.NEGATIVE_INFINITY, getDouble(directCalculate("-1e123456789123456789123")));
    }

    @Test
    public void processEl175Test() {
        assertEq(40907, getNumber(directCalculate("'\\u9FCB'")));
    }

    @Test
    public void processEl176Test() {
        assertEq("\u9fcb", getString(directCalculate("\"\\u9FCB\"")));
    }

    @Test
    public void processEl177Test() {
        assertEq("\u9fcb", getString(directCalculate("\"\\u9fcb\"")));
    }

    @Test
    public void processEl178Test() {
        assertEq(Long.MAX_VALUE, getNumber(directCalculate("$static(java.lang.Long) .MAX_VALUE")));
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
        assertEq(5L, getNumber(directCalculate("$(java.lang.Number)5")));
    }

    @Test
    public void processEl199Test() {
        assertEq(5L, getNumber(directCalculate("$($byte)5")));
    }

    @Test
    public void processEl201Test() {
        Struct arg_ = directCalculate("$(java.lang.Byte)$null");
        assertSame(NullStruct.NULL_VALUE, arg_);
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
        assertTrue(directCalculate(el_));
    }

    @Test
    public void processEl210Test() {
        assertEq(99, getNumber(directCalculate("($int)('1'+'2')")));
    }

    @Test
    public void processEl211Test() {
        assertEq("12", getString(directCalculate("\"\"+$new $char[]{'1','2'}[0]+$new $char[]{'1','2'}[1]")));
    }

    @Test
    public void processEl212Test() {
        assertEq(297, getNumber(directCalculate("('1'+'2')*3i")));
    }

    @Test
    public void processEl213Test() {
        assertTrue(directCalculate("'1'>1i"));
    }

    @Test
    public void processEl214Test() {
        assertFalse(directCalculate("'1'<1i"));
    }

    @Test
    public void processEl215Test() {
        assertFalse(directCalculate("'1'<1i"));
    }

    @Test
    public void processEl216Test() {
        assertTrue(directCalculate("'1'>1i"));
    }

    @Test
    public void processEl213FailTest() {
        assertEq(5049, getNumber(directCalculate("('1'+'2')*'3'")));
    }

    @Test
    public void processEl219Test() {
        assertEq(3, getNumber(directCalculate("6 + $($int) - $static($math).quot(8,5) - 2")));
    }

    @Test
    public void processEl320Test() {
        assertEq(1, getNumber(directCalculate("0x1")));
    }

    @Test
    public void processEl321Test() {
        assertEq(-1, getNumber(directCalculate("0xffff_ffff")));
    }

    @Test
    public void processEl322Test() {
        assertEq(1, getNumber(directCalculate("0x1p0")));
    }

    @Test
    public void processEl323Test() {
        assertEq(1, getNumber(directCalculate("0x1.0p0")));
    }

    @Test
    public void processEl324Test() {
        assertEq(17, getNumber(directCalculate("0x1.1p4")));
    }

    @Test
    public void processEl325Test() {
        assertEq(17, getNumber(directCalculate("0x110.0p-4")));
    }

    @Test
    public void processEl326Test() {
        assertEq(1, getNumber(directCalculate("0x1l")));
    }

    @Test
    public void processEl327Test() {
        assertEq(1, getNumber(directCalculate("0x1xl")));
    }

    @Test
    public void processEl328Test() {
        assertEq(1, getNumber(directCalculate("0b1")));
    }

    @Test
    public void processEl329Test() {
        assertEq(2, getNumber(directCalculate("0b10")));
    }

    @Test
    public void processEl330Test() {
        assertEq(1, getNumber(directCalculate("01")));
    }

    @Test
    public void processEl331Test() {
        assertEq(-1, getNumber(directCalculate("017_7777_7777_7777_7777_7777l")));
    }

    @Test
    public void processEl332Test() {
        assertEq(Long.MAX_VALUE, getNumber(directCalculate("007_7777_7777_7777_7777_7777l")));
    }

    @Test
    public void processEl333Test() {
        assertEq(-1, getNumber(directCalculate("0377_7777_7777i")));
    }

    @Test
    public void processEl334Test() {
        assertEq(Integer.MAX_VALUE, getNumber(directCalculate("0177_7777_7777i")));
    }

    @Test
    public void processEl335Test() {
        assertEq(0, getNumber(directCalculate("1&2")));
    }

    @Test
    public void processEl336Test() {
        assertEq(3, getNumber(directCalculate("1|2")));
    }

    @Test
    public void processEl337Test() {
        assertEq(3, getNumber(directCalculate("1^2")));
    }

    @Test
    public void processEl338Test() {
        assertEq(2, getNumber(directCalculate("1^3")));
    }

    @Test
    public void processEl339Test() {
        assertEq(3, getNumber(directCalculate("1c|2c")));
    }

    @Test
    public void processEl340Test() {
        assertEq(-1, getNumber(directCalculate("~0")));
    }

    @Test
    public void processEl341Test() {
        assertEq(0, getNumber(directCalculate("~-1")));
    }

    @Test
    public void processEl342Test() {
        assertEq(4, getNumber(directCalculate("1<<2")));
    }

    @Test
    public void processEl343Test() {
        assertEq(1, getNumber(directCalculate("4>>2")));
    }

    @Test
    public void processEl344Test() {
        assertEq(4, getNumber(directCalculate("1<<34")));
    }

    @Test
    public void processEl345Test() {
        assertEq(1, getNumber(directCalculate("4>>34")));
    }

    @Test
    public void processEl346Test() {
        assertEq(4, getNumber(directCalculate("1l<<2")));
    }

    @Test
    public void processEl347Test() {
        assertEq(1, getNumber(directCalculate("4l>>2")));
    }

    @Test
    public void processEl348Test() {
        assertEq(4, getNumber(directCalculate("1l<<66")));
    }

    @Test
    public void processEl349Test() {
        assertEq(1, getNumber(directCalculate("4l>>66")));
    }

    @Test
    public void processEl350Test() {
        assertEq(-4, getNumber(directCalculate("-1<<2")));
    }

    @Test
    public void processEl351Test() {
        assertEq(-1, getNumber(directCalculate("-4>>2")));
    }

    @Test
    public void processEl352Test() {
        assertEq(Integer.MIN_VALUE, getNumber(directCalculate("0200_0000_0000i")));
    }

    @Test
    public void processEl353Test() {
        assertEq(1, getNumber(directCalculate("0x1.0xd")));
    }

    @Test
    public void processEl354Test() {
        assertEq(2, getNumber(directCalculate("0x1.0p1d")));
    }

    @Test
    public void processEl355Test() {
        assertEq(2, getNumber(directCalculate("0x1p1d")));
    }

    @Test
    public void processEl356Test() {
        assertEq(1.5d, getDouble(directCalculate("0x1.8")));
    }

    @Test
    public void processEl357Test() {
        assertEq(1.5d, getDouble(directCalculate("0x1.8xd")));
    }

    @Test
    public void processEl358Test() {
        assertEq(-1, getNumber(directCalculate("0377y")));
    }

    @Test
    public void processEl359Test() {
        assertEq(Byte.MIN_VALUE, getNumber(directCalculate("0200y")));
    }

    @Test
    public void processEl360Test() {
        assertEq(-1, getNumber(directCalculate("0377777s")));
    }

    @Test
    public void processEl361Test() {
        assertEq(Short.MIN_VALUE, getNumber(directCalculate("0200000s")));
    }

    @Test
    public void processEl362Test() {
        assertEq("\u1000", getString(directCalculate("$new java.lang.String(\"\u1000\".getBytes())")));
    }

    @Test
    public void processEl363Test() {
        assertEq("\u0800", getString(directCalculate("$new java.lang.String(\"\u0800\".getBytes())")));
    }

    @Test
    public void processEl364Test() {
        assertEq("\u07FF", getString(directCalculate("$new java.lang.String(\"\u07FF\".getBytes())")));
    }

    @Test
    public void processEl365Test() {
        assertEq("\u0050", getString(directCalculate("$new java.lang.String(\"\u0050\".getBytes())")));
    }

    @Test
    public void processEl366Test() {
        assertEq(-536870914, getNumber(directCalculate("0xafff_ffff<<<1")));
    }

    @Test
    public void processEl367Test() {
        assertEq(1610612734, getNumber(directCalculate("0xafff_ffff<<1")));
    }

    @Test
    public void processEl368Test() {
        assertEq(1476395007, getNumber(directCalculate("0xafff_ffff>>>1")));
    }

    @Test
    public void processEl369Test() {
        assertEq(1610612735, getNumber(directCalculate("0xafff_ffff<<<<1")));
    }

    @Test
    public void processEl370Test() {
        assertEq(-671088641, getNumber(directCalculate("0xafff_ffff>>>>1")));
    }

    @Test
    public void processEl371Test() {
        Struct arg_ = directCalculate("0.5d*2d");
        NumberStruct res_ = (NumberStruct) arg_;
        assertTrue(res_ instanceof DoubleStruct);
        assertEq(1d, res_.doubleStruct());
    }

    @Test
    public void processEl372Test() {
        Struct arg_ = directCalculate("$math.random()");
        NumberStruct res_ = (NumberStruct)  arg_;
        assertTrue(res_ instanceof DoubleStruct);
        assertEq(1, NumberUtil.signum(NumParsers.compareGene(res_,new DoubleStruct(0.0d))+ SortConstants.SWAP_SORT));
        assertEq(-1, NumberUtil.signum(NumParsers.compareGene(res_,new DoubleStruct(1.0d))));
    }

    @Test
    public void processEl373Test() {
        Struct arg_ = directCalculate("$math.random(8l)");
        NumberStruct res_ = (NumberStruct)  arg_;
        assertTrue(res_ instanceof LongStruct);
        assertEq(1, NumberUtil.signum(NumberUtil.compareLg(res_.longStruct(),0)+ SortConstants.SWAP_SORT));
        assertEq(-1, NumberUtil.signum(NumberUtil.compareLg(res_.longStruct(),8)));
    }

    @Test
    public void processEl372_Test() {
        Struct arg_ = directCalculate("$math.natRandom()");
        NumberStruct res_ = (NumberStruct)  arg_;
        assertTrue(res_ instanceof DoubleStruct);
        assertEq(1, NumberUtil.signum(NumParsers.compareGene(res_,new DoubleStruct(0.0d))+ SortConstants.SWAP_SORT));
        assertEq(-1, NumberUtil.signum(NumParsers.compareGene(res_,new DoubleStruct(1.0d))));
    }

    @Test
    public void processEl373_Test() {
        Struct arg_ = directCalculate("$math.natRandom(8l)");
        NumberStruct res_ = (NumberStruct)  arg_;
        assertTrue(res_ instanceof LongStruct);
        assertEq(1, NumberUtil.signum(NumberUtil.compareLg(res_.longStruct(),0)+ SortConstants.SWAP_SORT));
        assertEq(-1, NumberUtil.signum(NumberUtil.compareLg(res_.longStruct(),8)));
    }

    @Test
    public void processEl374Test() {
        assertTrue(directCalculate("(Double)1.5 $instanceof Double"));
    }

    @Test
    public void processEl375Test() {
        assertEq(1, getNumber(directCalculate("\"my_string\".indexOf(\"y\")")));
    }

    @Test
    public void processEl376Test() {
        assertEq(1, getNumber(directCalculate("\"my_string\".indexOf(\"y\",1)")));
    }

    @Test
    public void processEl377Test() {
        assertEq(-1, getNumber(directCalculate("\"my_string\".indexOf(\"y\",2)")));
    }

    @Test
    public void processEl378Test() {
        assertEq(-1, getNumber(directCalculate("\"my_string\".indexOf(\"a\")")));
    }

    @Test
    public void processEl379Test() {
        assertEq(1, getNumber(directCalculate("\"my_string\".indexOf('y')")));
    }

    @Test
    public void processEl380Test() {
        assertEq(1, getNumber(directCalculate("\"my_string\".indexOf('y',1)")));
    }

    @Test
    public void processEl381Test() {
        assertEq(-1, getNumber(directCalculate("\"my_string\".indexOf('y',2)")));
    }

    @Test
    public void processEl382Test() {
        assertEq(-1, getNumber(directCalculate("\"my_string\".indexOf('a')")));
    }

    @Test
    public void processEl383Test() {
        assertEq(1, getNumber(directCalculate("\"my_string\".lastIndexOf(\"y\")")));
    }

    @Test
    public void processEl384Test() {
        assertEq(1, getNumber(directCalculate("\"my_string\".lastIndexOf(\"y\",1)")));
    }

    @Test
    public void processEl385Test() {
        assertEq(1, getNumber(directCalculate("\"my_string\".lastIndexOf(\"y\",2)")));
    }

    @Test
    public void processEl386Test() {
        assertEq(-1, getNumber(directCalculate("\"my_string\".lastIndexOf(\"a\")")));
    }

    @Test
    public void processEl387Test() {
        assertEq(1, getNumber(directCalculate("\"my_string\".lastIndexOf('y')")));
    }

    @Test
    public void processEl388Test() {
        assertEq(1, getNumber(directCalculate("\"my_string\".lastIndexOf('y',1)")));
    }

    @Test
    public void processEl389Test() {
        assertEq(1, getNumber(directCalculate("\"my_string\".lastIndexOf('y',2)")));
    }

    @Test
    public void processEl390Test() {
        assertEq(-1, getNumber(directCalculate("\"my_string\".lastIndexOf('a')")));
    }

    @Test
    public void processEl391Test() {
        assertEq("my_string", getString(directCalculate("\"my_string\".replace('a','t')")));
    }

    @Test
    public void processEl392Test() {
        assertEq("mytstring", getString(directCalculate("\"my_string\".replace('_','t')")));
    }

    @Test
    public void processEl393Test() {
        assertEq("my_string", getString(directCalculate("\"my_string\".replace(\"a\",\"t\")")));
    }

    @Test
    public void processEl394Test() {
        assertEq("mytstring", getString(directCalculate("\"my_string\".replace(\"_\",\"t\")")));
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
        assertEq("my_string", getString(directCalculate("\"my_string\".replace($null,\"t\")")));
    }

    @Test
    public void processEl400Test() {
        assertEq("my_string", getString(directCalculate("\"my_string\".replace(\"\",$null)")));
    }

    @Test
    public void processEl401Test() {
        assertEq("mystring", getString(directCalculate("\"my_string\".replace(\"_\",$null)")));
    }

    @Test
    public void processEl402Test() {
        assertEq("_m_y_s_t_r_i_n_g_", getString(directCalculate("\"mystring\".replace(\"\",\"_\")")));
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
        assertEq("y_string", getString(directCalculate("\"my_string\".substring(1)")));
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
        assertEq("y_string", getString(directCalculate("\"my_string\".substring(1,9)")));
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
        assertFalse(directCalculate("\"my_string\".contains(\"-\")"));
    }

    @Test
    public void processEl414Test() {
        assertTrue(directCalculate("\"my_string\".contains(\"_\")"));
    }
    @Test
    public void processEl415Test() {
        Struct arg_ = directCalculateExc("\"my_string\".startsWith($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processEl416Test() {
        assertFalse(directCalculate("\"my_string\".startsWith(\"_\")"));
    }

    @Test
    public void processEl417Test() {
        assertTrue(directCalculate("\"my_string\".startsWith(\"m\")"));
    }
    @Test
    public void processEl418Test() {
        Struct arg_ = directCalculateExc("\"my_string\".startsWith($null,0)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processEl419Test() {
        assertFalse(directCalculate("\"my_string\".startsWith(\"_\",0)"));
    }

    @Test
    public void processEl420Test() {
        assertTrue(directCalculate("\"my_string\".startsWith(\"_\",2)"));
    }
    @Test
    public void processEl421Test() {
        Struct arg_ = directCalculateExc("\"my_string\".endsWith($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processEl422Test() {
        assertFalse(directCalculate("\"my_string\".endsWith(\"_\")"));
    }

    @Test
    public void processEl423Test() {
        assertTrue(directCalculate("\"my_string\".endsWith(\"g\")"));
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
        assertEq("my_string", getString(directCalculate("\"{0}_{1}\".format(\"my\",\"string\")")));
    }

    @Test
    public void processE427Test() {
        Struct arg_ = directCalculate("\"hello word\".splitStrings(2,\"e\",\"o\")");
        ArrayStruct arr_ = (ArrayStruct) arg_;
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("h", ((StringStruct) res_[0]).getInstance());
        assertEq("ll", ((StringStruct) res_[1]).getInstance());
    }

    @Test
    public void processE428Test() {
        Struct arg_ = directCalculate("\"hello word\".splitStrings(-1,\"e\",\"o\")");
        ArrayStruct arr_ = (ArrayStruct) arg_;
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
        Struct arg_ = directCalculate("\"hello word\".splitStrings(-2,\"e\",\"o\")");
        ArrayStruct arr_ = (ArrayStruct) arg_;
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
        Struct arg_ = directCalculate("\"hello word\".splitChars('e','o')");
        ArrayStruct arr_ = (ArrayStruct) arg_;
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
        Struct arg_ = directCalculate("\"hello word\".split(' ')");
        ArrayStruct arr_ = (ArrayStruct) arg_;
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
        assertEq("word", ((StringStruct) res_[1]).getInstance());
    }
    @Test
    public void processE437Test() {
        Struct arg_ = directCalculate("\"hello word\".split(' ',1)");
        ArrayStruct arr_ = (ArrayStruct) arg_;
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
        Struct arg_ = directCalculate("\"hello word\".split(\" \")");
        ArrayStruct arr_ = (ArrayStruct) arg_;
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
        assertEq("word", ((StringStruct) res_[1]).getInstance());
    }
    @Test
    public void processE441Test() {
        Struct arg_ = directCalculate("\"hello word\".split(\" \",1)");
        ArrayStruct arr_ = (ArrayStruct) arg_;
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(1, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
    }
    @Test
    public void processE442Test() {
        Struct arg_ = directCalculate("\"hello word\".split(\" \",-1)");
        ArrayStruct arr_ = (ArrayStruct) arg_;
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
        assertEq("word", ((StringStruct) res_[1]).getInstance());
    }
    @Test
    public void processE443Test() {
        Struct arg_ = directCalculate("\"hello word\".split(\" \",-2)");
        ArrayStruct arr_ = (ArrayStruct) arg_;
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
        assertEq("word", ((StringStruct) res_[1]).getInstance());
    }
    @Test
    public void processE444Test() {
        Struct arg_ = directCalculate("\"hello word\".split(' ',-1)");
        ArrayStruct arr_ = (ArrayStruct) arg_;
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
        assertEq("word", ((StringStruct) res_[1]).getInstance());
    }
    @Test
    public void processE445Test() {
        Struct arg_ = directCalculate("\"hello word\".split(' ',-2)");
        ArrayStruct arr_ = (ArrayStruct) arg_;
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
        assertEq("word", ((StringStruct) res_[1]).getInstance());
    }

    @Test
    public void processE446Test() {
        Struct arg_ = directCalculate("\"hello word\".splitStrings(-1,\"e\",\"o\")");
        ArrayStruct arr_ = (ArrayStruct) arg_;
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
        Struct arg_ = directCalculate("\"hello word\".splitStrings(-2,\"e\",\"o\")");
        ArrayStruct arr_ = (ArrayStruct) arg_;
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
        Struct arg_ = directCalculate("\"hello word\".toCharArray()");
        ArrayStruct arr_ = (ArrayStruct) arg_;
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
        assertFalse(directCalculate("\"hello word\".isEmpty()"));
    }
    @Test
    public void processE450Test() {
        assertTrue(directCalculate("\"\".isEmpty()"));
    }
    @Test
    public void processE451Test() {
        assertEq(0, getNumber(directCalculate("\"\".length()")));
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
        assertEq("y_string", getString(directCalculate("\"my_string\".subSequence(1,9)")));
    }
    @Test
    public void processE456Test() {
        assertEq(10, getNumber(directCalculate("\"hello word\".length()")));
    }
    @Test
    public void processE457Test() {
        Struct arg_ = directCalculateExc("\"hello word\".regionMatches(0,$null,0,0)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE458Test() {
        assertTrue(directCalculate("\"hello word\".regionMatches(0,\"\",0,0)"));
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
        assertEq("good world", getString(directCalculate("\"hello word\".replaceMultiple($new code.util.Replacement(\"hello\",\"good\"),$new code.util.Replacement(\"word\",\"world\"))")));
    }
    @Test
    public void processE465Test() {
        Struct arg_ = directCalculateExc("\"hello word\".regionMatches($false,0,$null,0,0)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processE466Test() {
        assertTrue(directCalculate("\"hello word\".regionMatches($false,0,\"\",0,0)"));
    }
    @Test
    public void processE467Test() {
        assertTrue(directCalculate("\"hello word\".regionMatches($true,0,\"\",0,0)"));
    }
    @Test
    public void processE468Test() {
        assertEq("hello word", getString(directCalculate("\"hello word\".toLowerCase()")));
    }
    @Test
    public void processE469Test() {
        assertEq("HELLO WORD", getString(directCalculate("\"HELLO WORD\".toUpperCase()")));
    }
    @Test
    public void processE470Test() {
        assertTrue(directCalculate("\"hello word\".equalsIgnoreCase(\"hello word\")"));
    }
    @Test
    public void processE471Test() {
        assertFalse(directCalculate("\"helloword\".equalsIgnoreCase(\"hello word\")"));
    }
    @Test
    public void processE472Test() {
        assertTrue(directCalculate("\"hello word\".compareToIgnoreCase(\"hello word\") == 0"));
    }
    @Test
    public void processE473Test() {
        assertFalse(directCalculate("\"helloword\".compareToIgnoreCase(\"hello word\") < 0"));
    }
    @Test
    public void processE474Test() {
        assertTrue(directCalculate("\"helloword\".compareToIgnoreCase(\"hello word\") > 0"));
    }
    @Test
    public void processE475Test() {
        assertTrue(directCalculate("\"hello word\".compareTo(\"hello word\") == 0"));
    }
    @Test
    public void processE476Test() {
        assertFalse(directCalculate("\"helloword\".compareTo(\"hello word\") < 0"));
    }
    @Test
    public void processE477Test() {
        assertTrue(directCalculate("\"helloword\".compareTo(\"hello word\") > 0"));
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
        assertTrue(directCalculate("String.compare(\"hello word\",\"hello word\") == 0"));
    }
    @Test
    public void processE481Test() {
        assertFalse(directCalculate("String.compare(\"helloword\",\"hello word\") < 0"));
    }
    @Test
    public void processE482Test() {
        assertTrue(directCalculate("String.compare(\"helloword\",\"hello word\") > 0"));
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
        assertEq("true", getString(directCalculate("String.valueOf($true)")));
    }
    @Test
    public void processE486Test() {
        assertEq("false", getString(directCalculate("String.valueOf($false)")));
    }
    @Test
    public void processE487Test() {
        assertEq("0", getString(directCalculate("String.valueOf(($byte)0)")));
    }
    @Test
    public void processE488Test() {
        assertEq("0", getString(directCalculate("String.valueOf(0s)")));
    }
    @Test
    public void processE489Test() {
        assertEq(" ", getString(directCalculate("String.valueOf(' ')")));
    }
    @Test
    public void processE490Test() {
        assertEq("0", getString(directCalculate("String.valueOf(0i)")));
    }
    @Test
    public void processE491Test() {
        assertEq("0", getString(directCalculate("String.valueOf(0l)")));
    }
    @Test
    public void processE492Test() {
        assertEq("1.0", getString(directCalculate("String.valueOf(1.0f)")));
    }
    @Test
    public void processE493Test() {
        assertEq("1.0", getString(directCalculate("String.valueOf(1.0d)")));
    }
    @Test
    public void processE494Test() {
        assertEq("", getString(directCalculate("String.valueOf($null)")));
    }
    @Test
    public void processE495Test() {
        assertEq("h w", getString(directCalculate("String.valueOf('h',' ','w')")));
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
        assertEq("h w", getString(directCalculate("String.valueOf(0,3,'h',' ','w')")));
    }
    @Test
    public void processE501Test() {
        assertEq("", getString(directCalculate("$new String()")));
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
        assertEq("", getString(directCalculate("$new String($new StringBuilder())")));
    }
    @Test
    public void processE514Test() {
        assertEq("", getString(directCalculate("$new String($new $byte[]{})")));
    }
    @Test
    public void processE515Test() {
        assertEq("", getString(directCalculate("$new String($new $char[]{})")));
    }

    @Test
    public void processEl516Test() {
        assertEq(1, getNumber(directCalculate("$new StringBuilder(\"my_string\").indexOf(\"y\")")));
    }

    @Test
    public void processEl517Test() {
        assertEq(1, getNumber(directCalculate("$new StringBuilder(\"my_string\").indexOf(\"y\",1)")));
    }

    @Test
    public void processEl518Test() {
        assertEq(-1, getNumber(directCalculate("$new StringBuilder(\"my_string\").indexOf(\"y\",2)")));
    }

    @Test
    public void processEl519Test() {
        assertEq(-1, getNumber(directCalculate("$new StringBuilder(\"my_string\").indexOf(\"a\")")));
    }

    @Test
    public void processEl520Test() {
        assertEq(1, getNumber(directCalculate("$new StringBuilder(\"my_string\").indexOf('y')")));
    }

    @Test
    public void processEl521Test() {
        assertEq(1, getNumber(directCalculate("$new StringBuilder(\"my_string\").indexOf('y',1)")));
    }

    @Test
    public void processEl522Test() {
        assertEq(-1, getNumber(directCalculate("$new StringBuilder(\"my_string\").indexOf('y',2)")));
    }

    @Test
    public void processEl523Test() {
        assertEq(-1, getNumber(directCalculate("$new StringBuilder(\"my_string\").indexOf('a')")));
    }

    @Test
    public void processEl524Test() {
        assertEq(1, getNumber(directCalculate("$new StringBuilder(\"my_string\").lastIndexOf(\"y\")")));
    }

    @Test
    public void processEl525Test() {
        assertEq(1, getNumber(directCalculate("$new StringBuilder(\"my_string\").lastIndexOf(\"y\",1)")));
    }

    @Test
    public void processEl526Test() {
        assertEq(1, getNumber(directCalculate("$new StringBuilder(\"my_string\").lastIndexOf(\"y\",2)")));
    }

    @Test
    public void processEl527Test() {
        assertEq(-1, getNumber(directCalculate("$new StringBuilder(\"my_string\").lastIndexOf(\"a\")")));
    }

    @Test
    public void processEl528Test() {
        assertEq(1, getNumber(directCalculate("$new StringBuilder(\"my_string\").lastIndexOf('y')")));
    }

    @Test
    public void processEl529Test() {
        assertEq(1, getNumber(directCalculate("$new StringBuilder(\"my_string\").lastIndexOf('y',1)")));
    }

    @Test
    public void processEl530Test() {
        assertEq(1, getNumber(directCalculate("$new StringBuilder(\"my_string\").lastIndexOf('y',2)")));
    }

    @Test
    public void processEl531Test() {
        assertEq(-1, getNumber(directCalculate("$new StringBuilder(\"my_string\").lastIndexOf('a')")));
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
        assertEq("y_string", getString(directCalculate("$new StringBuilder(\"my_string\").substring(1)")));
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
        assertEq("y_string", getString(directCalculate("$new StringBuilder(\"my_string\").substring(1,9)")));
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
        assertFalse(directCalculate("$new StringBuilder(\"my_string\").contains(\"-\")"));
    }

    @Test
    public void processEl548Test() {
        assertTrue(directCalculate("$new StringBuilder(\"my_string\").contains(\"_\")"));
    }
    @Test
    public void processEl549Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"my_string\").startsWith($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processEl550Test() {
        assertFalse(directCalculate("$new StringBuilder(\"my_string\").startsWith(\"_\")"));
    }

    @Test
    public void processEl551Test() {
        assertTrue(directCalculate("$new StringBuilder(\"my_string\").startsWith(\"m\")"));
    }
    @Test
    public void processEl552Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"my_string\").startsWith($null,0)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processEl553Test() {
        assertFalse(directCalculate("$new StringBuilder(\"my_string\").startsWith(\"_\",0)"));
    }

    @Test
    public void processEl554Test() {
        assertTrue(directCalculate("$new StringBuilder(\"my_string\").startsWith(\"_\",2)"));
    }
    @Test
    public void processEl555Test() {
        Struct arg_ = directCalculateExc("$new StringBuilder(\"my_string\").endsWith($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException", err_.getClassName());
    }
    @Test
    public void processEl556Test() {
        assertFalse(directCalculate("$new StringBuilder(\"my_string\").endsWith(\"_\")"));
    }

    @Test
    public void processEl557Test() {
        assertTrue(directCalculate("$new StringBuilder(\"my_string\").endsWith(\"g\")"));
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
        assertEq("my_string", getString(directCalculate("$new StringBuilder(\"{0}_{1}\").format(\"my\",\"string\")")));
    }

    @Test
    public void processE561Test() {
        Struct arg_ = directCalculate("$new StringBuilder(\"hello word\").splitStrings(2,\"e\",\"o\")");
        ArrayStruct arr_ = (ArrayStruct) arg_;
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("h", ((StringStruct) res_[0]).getInstance());
        assertEq("ll", ((StringStruct) res_[1]).getInstance());
    }

    @Test
    public void processE562Test() {
        Struct arg_ = directCalculate("$new StringBuilder(\"hello word\").splitStrings(-1,\"e\",\"o\")");
        ArrayStruct arr_ = (ArrayStruct) arg_;
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
        Struct arg_ = directCalculate("$new StringBuilder(\"hello word\").splitStrings(-2,\"e\",\"o\")");
        ArrayStruct arr_ = (ArrayStruct) arg_;
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
        Struct arg_ = directCalculate("$new StringBuilder(\"hello word\").splitChars('e','o')");
        ArrayStruct arr_ = (ArrayStruct) arg_;
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
        Struct arg_ = directCalculate("$new StringBuilder(\"hello word\").split(' ')");
        ArrayStruct arr_ = (ArrayStruct) arg_;
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
        assertEq("word", ((StringStruct) res_[1]).getInstance());
    }
    @Test
    public void processE571Test() {
        Struct arg_ = directCalculate("$new StringBuilder(\"hello word\").split(' ',1)");
        ArrayStruct arr_ = (ArrayStruct) arg_;
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
        Struct arg_ = directCalculate("$new StringBuilder(\"hello word\").split(\" \")");
        ArrayStruct arr_ = (ArrayStruct) arg_;
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
        assertEq("word", ((StringStruct) res_[1]).getInstance());
    }
    @Test
    public void processE575Test() {
        Struct arg_ = directCalculate("$new StringBuilder(\"hello word\").split(\" \",1)");
        ArrayStruct arr_ = (ArrayStruct) arg_;
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(1, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
    }
    @Test
    public void processE576Test() {
        Struct arg_ = directCalculate("$new StringBuilder(\"hello word\").split(\" \",-1)");
        ArrayStruct arr_ = (ArrayStruct) arg_;
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
        assertEq("word", ((StringStruct) res_[1]).getInstance());
    }
    @Test
    public void processE577Test() {
        Struct arg_ = directCalculate("$new StringBuilder(\"hello word\").split(\" \",-2)");
        ArrayStruct arr_ = (ArrayStruct) arg_;
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
        assertEq("word", ((StringStruct) res_[1]).getInstance());
    }
    @Test
    public void processE578Test() {
        Struct arg_ = directCalculate("$new StringBuilder(\"hello word\").split(' ',-1)");
        ArrayStruct arr_ = (ArrayStruct) arg_;
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
        assertEq("word", ((StringStruct) res_[1]).getInstance());
    }
    @Test
    public void processE579Test() {
        Struct arg_ = directCalculate("$new StringBuilder(\"hello word\").split(' ',-2)");
        ArrayStruct arr_ = (ArrayStruct) arg_;
        assertEq("[java.lang.String", arr_.getClassName());
        Struct[] res_ = arr_.getInstance();
        assertEq(2, res_.length);
        assertEq("hello", ((StringStruct) res_[0]).getInstance());
        assertEq("word", ((StringStruct) res_[1]).getInstance());
    }

    @Test
    public void processE580Test() {
        Struct arg_ = directCalculate("$new StringBuilder(\"hello word\").splitStrings(-1,\"e\",\"o\")");
        ArrayStruct arr_ = (ArrayStruct) arg_;
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
        Struct arg_ = directCalculate("$new StringBuilder(\"hello word\").splitStrings(-2,\"e\",\"o\")");
        ArrayStruct arr_ = (ArrayStruct) arg_;
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
        assertEq("new line", getString(directCalculate("$new StringBuilder(\"\\nnew line\").trim()")));
    }

    @Test
    public void processEl583Test() {
        assertEq("\nnew line", getString(directCalculate("$new StringBuilder(\"\\nnew line\").toString()")));
    }

    @Test
    public void processEl584Test() {
        assertEq("\u1000", getString(directCalculate("$new java.lang.String($new StringBuilder(\"\u1000\").getBytes())")));
    }

    @Test
    public void processEl585Test() {
        assertEq("\u0800", getString(directCalculate("$new java.lang.String($new StringBuilder(\"\u0800\").getBytes())")));
    }

    @Test
    public void processEl586Test() {
        assertEq("\u07FF", getString(directCalculate("$new java.lang.String($new StringBuilder(\"\u07FF\").getBytes())")));
    }

    @Test
    public void processEl587Test() {
        assertEq("\u0050", getString(directCalculate("$new java.lang.String($new StringBuilder(\"\u0050\").getBytes())")));
    }

    @Test
    public void processEl588Test() {
        assertEq("", getString(directCalculate("$new StringBuilder()")));
    }

    @Test
    public void processEl589Test() {
        assertEq("a string", getString(directCalculate("$new StringBuilder().append(\"a string\").toString()")));
    }

    @Test
    public void processEl590Test() {
        assertEq("0", getString(directCalculate("$new StringBuilder().append(0i).toString()")));
    }

    @Test
    public void processEl591Test() {
        assertEq("true", getString(directCalculate("$new StringBuilder().append($true).toString()")));
    }

    @Test
    public void processEl592Test() {
        assertEq("false", getString(directCalculate("$new StringBuilder().append($false).toString()")));
    }

    @Test
    public void processEl593Test() {
        assertEq("0", getString(directCalculate("$new StringBuilder().append(($byte)0).toString()")));
    }

    @Test
    public void processEl594Test() {
        assertEq("0", getString(directCalculate("$new StringBuilder().append(0l).toString()")));
    }

    @Test
    public void processEl595Test() {
        assertEq("0", getString(directCalculate("$new StringBuilder().append(0s).toString()")));
    }

    @Test
    public void processEl596Test() {
        assertEq("c", getString(directCalculate("$new StringBuilder().append('c').toString()")));
    }

    @Test
    public void processEl597Test() {
        assertEq("1.0", getString(directCalculate("$new StringBuilder().append(1.0f).toString()")));
    }

    @Test
    public void processEl598Test() {
        assertEq("1.0", getString(directCalculate("$new StringBuilder().append(1.0d).toString()")));
    }
    @Test
    public void processEl599Test() {
        assertEq("", getString(directCalculate("$new StringBuilder().append((String)$null).toString()")));
    }
    @Test
    public void processEl600Test() {
        assertEq("", getString(directCalculate("$new StringBuilder().append((StringBuilder)$null).toString()")));
    }

    @Test
    public void processEl601Test() {
        assertEq("a string", getString(directCalculate("$new StringBuilder().append($new $char[]{'a',' ','s','t','r','i','n','g'}).toString()")));
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
        assertEq("string", getString(directCalculate("$new StringBuilder().append($new $char[]{'a',' ','s','t','r','i','n','g'},2,6).toString()")));
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
        assertEq("string", getString(directCalculate("$new StringBuilder().append(\"a string\",2,8).toString()")));
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
        assertEq("string", getString(directCalculate("$new StringBuilder().append($new StringBuilder(\"a string\"),2,8).toString()")));
    }

    @Test
    public void processEl618Test() {
        assertEq("a string", getString(directCalculate("$new StringBuilder().append($new StringBuilder(\"a string\")).toString()")));
    }

    @Test
    public void processEl619Test() {
        assertEq(8, getNumber(directCalculate("$new StringBuilder(8).capacity()")));
    }

    @Test
    public void processEl620Test() {
        assertEq("a string", getString(directCalculate("$new StringBuilder($new StringBuilder(\"a string\")).toString()")));
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
        assertEq("a string", getString(directCalculate("$new StringBuilder().insert(0,\"a string\").toString()")));
    }

    @Test
    public void processEl624Test() {
        assertEq("0", getString(directCalculate("$new StringBuilder().insert(0,0i).toString()")));
    }

    @Test
    public void processEl625Test() {
        assertEq("true", getString(directCalculate("$new StringBuilder().insert(0,$true).toString()")));
    }

    @Test
    public void processEl626Test() {
        assertEq("false", getString(directCalculate("$new StringBuilder().insert(0,$false).toString()")));
    }

    @Test
    public void processEl627Test() {
        assertEq("0", getString(directCalculate("$new StringBuilder().insert(0,($byte)0).toString()")));
    }

    @Test
    public void processEl628Test() {
        assertEq("0", getString(directCalculate("$new StringBuilder().insert(0,0l).toString()")));
    }

    @Test
    public void processEl629Test() {
        assertEq("0", getString(directCalculate("$new StringBuilder().insert(0,0s).toString()")));
    }

    @Test
    public void processEl630Test() {
        assertEq("c", getString(directCalculate("$new StringBuilder().insert(0,'c').toString()")));
    }

    @Test
    public void processEl631Test() {
        assertEq("1.0", getString(directCalculate("$new StringBuilder().insert(0,1.0f).toString()")));
    }

    @Test
    public void processEl632Test() {
        assertEq("1.0", getString(directCalculate("$new StringBuilder().insert(0,1.0d).toString()")));
    }
    @Test
    public void processEl633Test() {
        assertEq("", getString(directCalculate("$new StringBuilder().insert(0,(String)$null).toString()")));
    }
    @Test
    public void processEl634Test() {
        assertEq("", getString(directCalculate("$new StringBuilder().insert(0,(StringBuilder)$null).toString()")));
    }

    @Test
    public void processEl635Test() {
        assertEq("a string", getString(directCalculate("$new StringBuilder().insert(0,$new $char[]{'a',' ','s','t','r','i','n','g'}).toString()")));
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
        assertEq("string", getString(directCalculate("$new StringBuilder().insert(0,$new $char[]{'a',' ','s','t','r','i','n','g'},2,6).toString()")));
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
        assertEq("string", getString(directCalculate("$new StringBuilder().insert(0,\"a string\",2,8).toString()")));
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
        assertEq("string", getString(directCalculate("$new StringBuilder().insert(0,$new StringBuilder(\"a string\"),2,8).toString()")));
    }

    @Test
    public void processEl652Test() {
        assertEq("a string", getString(directCalculate("$new StringBuilder().insert(0,$new StringBuilder(\"a string\")).toString()")));
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
        assertEq("a super string", getString(directCalculate("$new StringBuilder(\"a bad string\").replace(1,6,\" super \").toString()")));
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
        assertEq("a string", getString(directCalculate("$new StringBuilder(\"a bad string\").delete(1,5).toString()")));
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
        assertEq("a string", getString(directCalculate("$new StringBuilder(\"a  string\").deleteCharAt(1).toString()")));
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
        assertEq("a string", getString(directCalculate("$new StringBuilder(\"a_string\").setCharAt(1,' ').toString()")));
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
        assertEq("gnirts a", getString(directCalculate("$new StringBuilder(\"a string\").reverse()")));
    }
    @Test
    public void processEl682Test() {
        assertTrue(directCalculate("$new StringBuilder(\"a string\").ensureCapacity(32).capacity() >= 32"));
    }
    @Test
    public void processEl683Test() {
        assertEq(8, getNumber(directCalculate("$new StringBuilder(\"a string\").trimToSize().capacity()")));
    }
    @Test
    public void processEl684Test() {
        assertEq("a", getString(directCalculate("$new StringBuilder(\"a string\").setLength(1)")));
    }
    @Test
    public void processEl685Test() {
        assertEq("", getString(directCalculate("$new StringBuilder(\"a string\").clear()")));
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
        assertEq(" ", getString(directCalculate("$new String(' ')")));
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
        assertEq(" ", getString(directCalculate("$new String(0,1,($byte)32)")));
    }
    @Test
    public void processEl691Test() {
        assertEq(" ", getString(directCalculate("$new String(0,1,' ')")));
    }
    @Test
    public void processEl692Test() {
        assertFalse(directCalculate("\"helloword\".equalsIgnoreCase($null)"));
    }

    @Test
    public void processEl693Test() {
        Struct arg_ = directCalculate("\"my_string\".charAt(0)");
        assertEq('m',getChar(arg_));
    }

    @Test
    public void processEl694Test() {
        Struct arg_ = directCalculate("$new StringBuilder(\"my_string\").charAt(0)");
        assertEq('m',getChar(arg_));
    }

    @Test
    public void processEl695Test() {
        assertTrue(directCalculate("\"my_string\" != \"mystring\""));
    }

    @Test
    public void processEl696Test() {
        assertTrue(directCalculate("\"my_string\" != \"my string\""));
    }

    @Test
    public void processEl697Test() {
        assertFalse(directCalculate("CharSequence.equals(\"my_string\", \"my string\")"));
    }

    @Test
    public void processEl698Test() {
        assertTrue(directCalculate("CharSequence.equals(\"my_string\", \"my_string\")"));
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
        assertEq(4.0, getDouble(directCalculate("8.0/2d")));
    }

    @Test
    public void processEl702Test() {
        assertEq(1.0, getDouble(directCalculate("7.0%2d")));
    }

    @Test
    public void processEl703Test() {
        assertEq(4.0, getDouble(directCalculate("8.0f/2f")));
    }

    @Test
    public void processEl704Test() {
        assertEq(1.0, getDouble(directCalculate("7.0f%2f")));
    }

    @Test
    public void processEl705Test() {
        assertEq(-1, getNumber(directCalculate("~0l")));
    }

    @Test
    public void processEl706Test() {
        assertEq(0, getNumber(directCalculate("~-1l")));
    }

    @Test
    public void processEl707Test() {
        assertEq(2.0, getDouble(directCalculate("1f+1f")));
    }

    @Test
    public void processEl708Test() {
        assertEq(2.0, getDouble(directCalculate("3.0-1.0")));
    }

    @Test
    public void processEl709Test() {
        assertEq(2.0, getDouble(directCalculate("3.0f-1.0f")));
    }

    @Test
    public void processEl710Test() {
        assertEq(8, getNumber(directCalculate("4l*2")));
    }

    @Test
    public void processEl711Test() {
        assertEq(8.0, getDouble(directCalculate("4f*2f")));
    }

    @Test
    public void processEl712Test() {
        assertEq(1, getNumber(directCalculate("5&3")));
    }

    @Test
    public void processEl713Test() {
        assertEq(1, getNumber(directCalculate("3&5")));
    }

    @Test
    public void processEl714Test() {
        assertEq(7, getNumber(directCalculate("5|3")));
    }

    @Test
    public void processEl715Test() {
        assertEq(7, getNumber(directCalculate("3|5")));
    }

    @Test
    public void processEl716Test() {
        assertFalse(directCalculate("$true&$false"));
    }

    @Test
    public void processEl717Test() {
        assertFalse(directCalculate("$false&$true"));
    }

    @Test
    public void processEl718Test() {
        assertTrue(directCalculate("$true|$false"));
    }

    @Test
    public void processEl719Test() {
        assertTrue(directCalculate("$false|$true"));
    }


    @Test
    public void processEl720Test() {
        assertTrue(directCalculate("$true&$true"));
    }

    @Test
    public void processEl721Test() {
        assertFalse(directCalculate("$false&$false"));
    }

    @Test
    public void processEl722Test() {
        assertTrue(directCalculate("$true|$true"));
    }

    @Test
    public void processEl723Test() {
        assertFalse(directCalculate("$false|$false"));
    }


    @Test
    public void processEl724Test() {
        assertTrue(directCalculate("$true^$false"));
    }

    @Test
    public void processEl725Test() {
        assertTrue(directCalculate("$false^$true"));
    }

    @Test
    public void processEl726Test() {
        assertFalse(directCalculate("$true^$true"));
    }

    @Test
    public void processEl727Test() {
        assertFalse(directCalculate("$false^$false"));
    }

    @Test
    public void processEl728Test() {
        assertEq(1, getNumber(directCalculate("5l&3l")));
    }

    @Test
    public void processEl729Test() {
        assertEq(1, getNumber(directCalculate("3l&5l")));
    }

    @Test
    public void processEl730Test() {
        assertTrue(directCalculate("\" \"<\"a\""));
    }

    @Test
    public void processEl731Test() {
        assertFalse(directCalculate("\"a\"<\" \""));
    }

    @Test
    public void processEl732Test() {
        assertTrue(directCalculate("\" \"<=\"a\""));
    }

    @Test
    public void processEl733Test() {
        assertFalse(directCalculate("\"a\"<=\" \""));
    }

    @Test
    public void processEl734Test() {
        assertFalse(directCalculate("\" \">\"a\""));
    }

    @Test
    public void processEl735Test() {
        assertTrue(directCalculate("\"a\">\" \""));
    }

    @Test
    public void processEl736Test() {
        assertFalse(directCalculate("\" \">=\"a\""));
    }

    @Test
    public void processEl737Test() {
        assertTrue(directCalculate("\"a\">=\" \""));
    }

    @Test
    public void processEl738Test() {
        assertTrue(directCalculate("((Boolean)$true).booleanValue()"));
    }

    @Test
    public void processEl739Test() {
        assertEq(0, getNumber(directCalculate("((Boolean)$true).compareTo((Boolean)$true)")));
    }

    @Test
    public void processEl740Test() {
        assertEq(0, getNumber(directCalculate("Boolean.compare((Boolean)$true,(Boolean)$true)")));
    }

    @Test
    public void processEl741Test() {
        assertTrue(directCalculate("((Boolean)$true).equals((Boolean)$true)"));
    }

    @Test
    public void processEl742Test() {
        assertTrue(directCalculate("Boolean.parseBoolean(\"true\")"));
    }

    @Test
    public void processEl743Test() {
        assertEq("true", getString(directCalculate("Boolean.toString($true)")));
    }

    @Test
    public void processEl744Test() {
        assertEq("true", getString(directCalculate("((Boolean)$true).toString()")));
    }

    @Test
    public void processEl745Test() {
        assertTrue(directCalculate("Boolean.valueOf($true)"));
    }

    @Test
    public void processEl746Test() {
        assertTrue(directCalculate("Boolean.valueOf(\"true\")"));
    }

    @Test
    public void processEl747Test() {
        assertEq(0, getNumber(directCalculate("Integer.compare(1,1)")));
    }

    @Test
    public void processEl748Test() {
        assertEq(0, getNumber(directCalculate("((Integer)1).compareTo(1)")));
    }

    @Test
    public void processEl749Test() {
        assertEq(1, getNumber(directCalculate("((Integer)1).intValue()")));
    }

    @Test
    public void processEl750Test() {
        assertEq(1, getNumber(directCalculate("((Integer)1).longValue()")));
    }

    @Test
    public void processEl751Test() {
        assertEq(1, getNumber(directCalculate("((Integer)1).shortValue()")));
    }

    @Test
    public void processEl752Test() {
        assertEq(1, getNumber(directCalculate("((Integer)1).byteValue()")));
    }

    @Test
    public void processEl753Test() {
        assertEq(1.0, getDouble(directCalculate("((Integer)1).floatValue()")));
    }

    @Test
    public void processEl754Test() {
        assertEq(1.0, getDouble(directCalculate("((Integer)1).doubleValue()")));
    }

    @Test
    public void processEl755Test() {
        assertEq(0, getNumber(directCalculate("((Long)1).compareTo(1)")));
    }

    @Test
    public void processEl756Test() {
        assertEq(1, getNumber(directCalculate("((Long)1).intValue()")));
    }

    @Test
    public void processEl757Test() {
        assertEq(1, getNumber(directCalculate("((Long)1).longValue()")));
    }

    @Test
    public void processEl758Test() {
        assertEq(1, getNumber(directCalculate("((Long)1).shortValue()")));
    }

    @Test
    public void processEl759Test() {
        assertEq(1, getNumber(directCalculate("((Long)1).byteValue()")));
    }

    @Test
    public void processEl760Test() {
        assertEq(1.0, getDouble(directCalculate("((Long)1).floatValue()")));
    }

    @Test
    public void processEl761Test() {
        assertEq(1.0, getDouble(directCalculate("((Long)1).doubleValue()")));
    }

    @Test
    public void processEl762Test() {
        assertEq(0, getNumber(directCalculate("Long.compare(1,1)")));
    }

    @Test
    public void processEl763Test() {
        assertFalse(directCalculate("Boolean.parseBoolean(\"false\")"));
    }

    @Test
    public void processEl764Test() {
        assertFalse(directCalculate("Boolean.valueOf(\"false\")"));
    }

    @Test
    public void processEl765Test() {
        assertTrue(directCalculate("((Integer)1).equals(1)"));
    }

    @Test
    public void processEl765_Test() {
        assertTrue(directCalculate("Number.equals(1,1)"));
    }

    @Test
    public void processEl765__Test() {
        assertFalse(directCalculate("Number.equals(1,2)"));
    }

    @Test
    public void processEl766Test() {
        assertTrue(directCalculate("((Long)1).equals(1)"));
    }

    @Test
    public void processEl767Test() {
        assertEq("1", getString(directCalculate("((Integer)1).toString()")));
    }

    @Test
    public void processEl768Test() {
        assertEq("1", getString(directCalculate("((Long)1).toString()")));
    }

    @Test
    public void processEl769Test() {
        assertEq(1, getNumber(directCalculate("0x1s")));
    }

    @Test
    public void processEl770Test() {
        assertEq(1, getNumber(directCalculate("0x1xy")));
    }

    @Test
    public void processEl771Test() {
        assertEq("1", getString(directCalculate("Byte.toString(1y)")));
    }

    @Test
    public void processEl772Test() {
        assertEq("1", getString(directCalculate("Short.toString(1s)")));
    }
    @Test
    public void processEl773Test() {
        assertEq("1", getString(directCalculate("Integer.toString(1)")));
    }
    @Test
    public void processEl773_Test() {
        assertEq("1", getString(directCalculate("Integer.toString(1y)")));
    }

    @Test
    public void processEl774Test() {
        assertEq("1", getString(directCalculate("Long.toString(1)")));
    }
    @Test
    public void processEl775Test() {
        assertEq("1.0", getString(directCalculate("Float.toString(1f)")));
    }

    @Test
    public void processEl776Test() {
        assertEq("1.0", getString(directCalculate("Double.toString(1d)")));
    }

    @Test
    public void processEl777Test() {
        assertEq(1, getNumber(directCalculate("Byte.parseByte(\"1\",10)")));
    }

    @Test
    public void processEl778Test() {
        assertEq(1, getNumber(directCalculate("Short.parseShort(\"1\",10)")));
    }

    @Test
    public void processEl779Test() {
        assertEq(1, getNumber(directCalculate("Integer.parseInt(\"1\",10)")));
    }

    @Test
    public void processEl780Test() {
        assertEq(1, getNumber(directCalculate("Long.parseLong(\"1\",10)")));
    }

    @Test
    public void processEl781Test() {
        assertEq(1, getNumber(directCalculate("Short.parseShort(\"1\")")));
    }

    @Test
    public void processEl782Test() {
        assertEq(1, getNumber(directCalculate("Integer.parseInt(\"1\")")));
    }

    @Test
    public void processEl783Test() {
        assertEq(1, getNumber(directCalculate("Long.parseLong(\"1\")")));
    }

    @Test
    public void processEl784Test() {
        assertEq(1.0, getDouble(directCalculate("Float.parseFloat(\"1\")")));
    }

    @Test
    public void processEl784_Test() {
        assertEq(-1.0,getDouble(directCalculate("Float.parseFloat(\"-1\")")));
    }

    @Test
    public void processEl785Test() {
        assertEq(1.0, getDouble(directCalculate("Double.parseDouble(\"1\")")));
    }

    @Test
    public void processEl786Test() {
        assertFalse(directCalculate("((Float)1).isNan()"));
    }

    @Test
    public void processEl787Test() {
        assertFalse(directCalculate("Float.isNan(1f)"));
    }

    @Test
    public void processEl788Test() {
        assertFalse(directCalculate("((Float)1).isInfinite()"));
    }

    @Test
    public void processEl789Test() {
        assertFalse(directCalculate("Float.isInfinite(1f)"));
    }

    @Test
    public void processEl791Test() {
        assertFalse(directCalculate("((Double)1).isNan()"));
    }

    @Test
    public void processEl791_Test() {
        assertFalse(directCalculate("((Double)1d).isNan()"));
    }

    @Test
    public void processEl792Test() {
        assertFalse(directCalculate("Double.isNan(1d)"));
    }

    @Test
    public void processEl793Test() {
        assertFalse(directCalculate("((Double)1).isInfinite()"));
    }

    @Test
    public void processEl793_Test() {
        assertFalse(directCalculate("((Double)1d).isInfinite()"));
    }

    @Test
    public void processEl794Test() {
        assertFalse(directCalculate("Double.isInfinite(1d)"));
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
    public void processEl805_Test() {
        Struct arg_ = directCalculateExc("Float.parseFloat(\"1e-100\")");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat",err_.getClassName());
        assertEq("1e-100",((StringStruct)err_.getMessage()).getInstance());
    }

    @Test
    public void processEl806Test() {
        assertEq(0, getNumber(directCalculate("((Number)1).compareTo(1)")));
    }

    @Test
    public void processEl807Test() {
        assertEq(0, getNumber(directCalculate("Number.compare(1,1)")));
    }

    @Test
    public void processEl808Test() {
        assertEq(0, getNumber(directCalculate("((Character)'1').compareTo('1')")));
    }

    @Test
    public void processEl809Test() {
        assertEq(0, getNumber(directCalculate("Character.compare('1','1')")));
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
        assertEq(1, getNumber(directCalculate("Byte.parseByte(\"1\")")));
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
    public void processEl820_Test() {
        Struct arg_ = directCalculateExc("Float.parseFloat(\"-1e-100\")");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat",err_.getClassName());
        assertEq("-1e-100",((StringStruct)err_.getMessage()).getInstance());
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
        assertEq("1", getString(directCalculate("Number.toString(1)")));
    }

    @Test
    public void processEl823Test() {
        assertEq("", getString(directCalculate("Number.toString($null)")));
    }

    @Test
    public void processEl824Test() {
        assertEq(1, getNumber(directCalculate("Character.digit('1',10)")));
    }

    @Test
    public void processEl825Test() {
        assertEq('1', getNumber(directCalculate("Character.forDigit(1,10)")));
    }

    @Test
    public void processEl826Test() {
        assertEq(3, getNumber(directCalculate("Character.getDirectionality('1')")));
    }

    @Test
    public void processEl827Test() {
        assertEq(9, getNumber(directCalculate("Character.getType('1')")));
    }

    @Test
    public void processEl828Test() {
        assertTrue(directCalculate("Character.isDigit('1')"));
    }

    @Test
    public void processEl829Test() {
        assertFalse(directCalculate("Character.isLetter('1')"));
    }

    @Test
    public void processEl830Test() {
        assertTrue(directCalculate("Character.isLetterOrDigit('1')"));
    }

    @Test
    public void processEl831Test() {
        assertTrue(directCalculate("Character.isLowerCase('a')"));
    }

    @Test
    public void processEl832Test() {
        assertTrue(directCalculate("Character.isUpperCase('A')"));
    }

    @Test
    public void processEl833Test() {
        assertTrue(directCalculate("Character.isSpace(' ')"));
    }

    @Test
    public void processEl834Test() {
        assertTrue(directCalculate("Character.isWhitespace(' ')"));
    }

    @Test
    public void processEl835Test() {
        assertTrue(directCalculate("Character.isWordChar('_')"));
    }

    @Test
    public void processEl836Test() {
        assertEq('a', getNumber(directCalculate("Character.toLowerCase('a')")));
    }
    @Test
    public void processEl837Test() {
        assertEq('A', getNumber(directCalculate("Character.toUpperCase('A')")));
    }
    @Test
    public void processEl838Test() {
        assertEq(' ', getNumber(directCalculate("((Character)' ').charValue()")));
    }

    @Test
    public void processEl839Test() {
        assertEq(0, getNumber(directCalculate("((Character)' ').compareTo(' ')")));
    }

    @Test
    public void processEl840Test() {
        assertTrue(directCalculate("((Character)' ').equals(' ')"));
    }

    @Test
    public void processEl841Test() {
        Struct arg_ = directCalculateExc("((Character)' ').compareTo($null)");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("code.util.exceptions.NullObjectException",err_.getClassName());
    }
    @Test
    public void processEl842Test() {
        assertEq(" ", getString(directCalculate("((Character)' ').toString()")));
    }
    @Test
    public void processEl843Test() {
        assertEq(" ", getString(directCalculate("Character.toString(' ')")));
    }

    @Test
    public void processEl844Test() {
        assertEq(1, getNumber(directCalculate("$new Byte(\"1\")")));
    }

    @Test
    public void processEl845Test() {
        assertEq(1, getNumber(directCalculate("$new Short(\"1\")")));
    }

    @Test
    public void processEl846Test() {
        assertEq(1, getNumber(directCalculate("$new Integer(\"1\")")));
    }

    @Test
    public void processEl847Test() {
        assertEq(1, getNumber(directCalculate("$new Long(\"1\")")));
    }

    @Test
    public void processEl848Test() {
        assertEq(1.0, getDouble(directCalculate("$new Float(\"1\")")));
    }

    @Test
    public void processEl849Test() {
        assertEq(1.0, getDouble(directCalculate("$new Double(\"1\")")));
    }

    @Test
    public void processEl850Test() {
        assertEq(1, getNumber(directCalculate("$new Byte(1y)")));
    }

    @Test
    public void processEl851Test() {
        assertEq(1, getNumber(directCalculate("$new Short(1s)")));
    }

    @Test
    public void processEl852Test() {
        assertEq(1, getNumber(directCalculate("$new Integer(1)")));
    }

    @Test
    public void processEl853Test() {
        assertEq(1, getNumber(directCalculate("$new Long(1)")));
    }

    @Test
    public void processEl853_Test() {
        assertEq(1.0, getDouble(directCalculate("$new Long(1).floatValue()")));
    }
    @Test
    public void processEl854Test() {
        assertEq(1.0, getDouble(directCalculate("$new Float(1f)")));
    }

    @Test
    public void processEl855Test() {
        assertEq(1.0, getDouble(directCalculate("$new Double(1d)")));
    }

    @Test
    public void processEl856Test() {
        assertEq('1', getNumber(directCalculate("$new Character('1')")));
    }

    @Test
    public void processEl857Test() {
        assertTrue(directCalculate("$new Boolean($true)"));
    }

    @Test
    public void processEl858Test() {
        assertTrue(directCalculate("$new Boolean(\"true\")"));
    }

    @Test
    public void processEl859Test() {
        assertFalse(directCalculate("$new Boolean(\"false\")"));
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
        assertEq(0, getNumber(directCalculate("$math.binMod(1,1)")));
    }

    @Test
    public void processEl867Test() {
        assertEq(1, getNumber(directCalculate("$math.binQuot(1,1)")));
    }

    @Test
    public void processEl868Test() {
        assertEq(1, getNumber(directCalculate("$math.plus(1)")));
    }

    @Test
    public void processEl869Test() {
        assertEq(3, getNumber(directCalculate("$math.plus(1,2)")));
    }

    @Test
    public void processEl870Test() {
        assertEq(-1, getNumber(directCalculate("$math.minus(1)")));
    }

    @Test
    public void processEl871Test() {
        assertEq(-1, getNumber(directCalculate("$math.minus(1,2)")));
    }

    @Test
    public void processEl872Test() {
        assertEq(-1, getNumber(directCalculate("$math.minus(1l)")));
    }

    @Test
    public void processEl873Test() {
        assertEq(-1.0,getDouble(directCalculate("$math.minus(1f)")));
    }

    @Test
    public void processEl874Test() {
        assertEq(-1.0,getDouble(directCalculate("$math.minus(1d)")));
    }

    @Test
    public void processEl875Test() {
        assertEq(6, getNumber(directCalculate("$math.mult(3,2)")));
    }

    @Test
    public void processEl876Test() {
        assertEq(-1, getNumber(directCalculate("$math.negBin(0)")));
    }

    @Test
    public void processEl877Test() {
        assertEq(-1, getNumber(directCalculate("$math.negBin(0l)")));
    }

    @Test
    public void processEl878Test() {
        assertEq(0, getNumber(directCalculate("$math.negBin(-1)")));
    }

    @Test
    public void processEl879Test() {
        assertEq(0, getNumber(directCalculate("$math.negBin(-1l)")));
    }

    @Test
    public void processEl880Test() {
        assertEq(0, getNumber(directCalculate("$math.and(1,2)")));
    }

    @Test
    public void processEl881Test() {
        assertEq(3, getNumber(directCalculate("$math.or(1,2)")));
    }

    @Test
    public void processEl882Test() {
        assertEq(6, getNumber(directCalculate("$math.xor(5,3)")));
    }

    @Test
    public void processEl883Test() {
        assertEq(4, getNumber(directCalculate("$math.shiftLeft(1,2)")));
    }

    @Test
    public void processEl884Test() {
        assertEq(1, getNumber(directCalculate("$math.shiftRight(4,2)")));
    }

    @Test
    public void processEl885Test() {
        assertEq(4, getNumber(directCalculate("$math.bitShiftLeft(1,2)")));
    }

    @Test
    public void processEl886Test() {
        assertEq(1, getNumber(directCalculate("$math.bitShiftRight(4,2)")));
    }

    @Test
    public void processEl887Test() {
        assertEq(4, getNumber(directCalculate("$math.rotateLeft(1,2)")));
    }

    @Test
    public void processEl888Test() {
        assertEq(1, getNumber(directCalculate("$math.rotateRight(4,2)")));
    }

    @Test
    public void processEl889Test() {
        assertTrue(directCalculate("$math.lt(1,2)"));
    }

    @Test
    public void processEl890Test() {
        assertFalse(directCalculate("$math.lt(1,1)"));
    }

    @Test
    public void processEl891Test() {
        assertTrue(directCalculate("$math.gt(2,1)"));
    }

    @Test
    public void processEl892Test() {
        assertFalse(directCalculate("$math.gt(1,1)"));
    }

    @Test
    public void processEl893Test() {
        assertTrue(directCalculate("$math.le(1,2)"));
    }

    @Test
    public void processEl894Test() {
        assertTrue(directCalculate("$math.le(1,1)"));
    }

    @Test
    public void processEl895Test() {
        assertTrue(directCalculate("$math.ge(2,1)"));
    }

    @Test
    public void processEl896Test() {
        assertTrue(directCalculate("$math.ge(1,1)"));
    }

    @Test
    public void processEl897Test() {
        assertFalse(directCalculate("$math.le(2,1)"));
    }

    @Test
    public void processEl898Test() {
        assertFalse(directCalculate("$math.ge(1,2)"));
    }

    @Test
    public void processEl899Test() {
        assertFalse(directCalculate("$math.neg($true)"));
    }

    @Test
    public void processEl900Test() {
        assertTrue(directCalculate("$math.neg($false)"));
    }

    @Test
    public void processEl901Test() {
        assertTrue(directCalculate("\"\"!=$null"));
    }

    @Test
    public void processEl902Test() {
        assertTrue(directCalculate("0!=$null"));
    }

    @Test
    public void processEl903Test() {
        assertTrue(directCalculate("$new $int[]{}!=$new $int[]{}"));
    }

    @Test
    public void processEl904Test() {
        assertTrue(directCalculate("$null!=0"));
    }

    @Test
    public void processEl905Test() {
        assertTrue(directCalculate("$null==$null"));
    }

    @Test
    public void processEl906Test() {
        assertTrue(directCalculate("$true!=$null"));
    }

    @Test
    public void processEl907Test() {
        assertTrue(directCalculate("$true==$true"));
    }

    @Test
    public void processEl908Test() {
        assertSame(NullStruct.NULL_VALUE,directCalculate("$ObjectsUtil.getParent(0)"));
    }

    @Test
    public void processEl909Test() {
        assertSame(NullStruct.NULL_VALUE,directCalculate("$ObjectsUtil.getParent($true)"));
    }

    @Test
    public void processEl910Test() {
        assertSame(NullStruct.NULL_VALUE,directCalculate("$ObjectsUtil.getParent(\"\")"));
    }

    @Test
    public void processEl911Test() {
        assertSame(NullStruct.NULL_VALUE,directCalculate("$ObjectsUtil.getParent($new code.util.Replacement(\"\",\"\"))"));
    }

    @Test
    public void processEl912Test() {
        assertTrue(directCalculate("$new code.util.Replacement(\"\",\"\") != $null"));
    }

    @Test
    public void processEl913Test() {
        assertSame(NullStruct.NULL_VALUE,directCalculate("$ObjectsUtil.getParent($new $int[]{})"));
    }

    @Test
    public void processEl914Test() {
        assertSame(NullStruct.NULL_VALUE,directCalculate("$ObjectsUtil.getParent($null)"));
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
        assertEq(1.5, getDouble(directCalculate("Double.parseDouble(\"1.5\")")));
    }

    @Test
    public void processEl946Test() {
        assertEq(15.0, getDouble(directCalculate("Double.parseDouble(\"1.5e1\")")));
    }

    @Test
    public void processEl947Test() {
        assertEq(15.0, getDouble(directCalculate("Double.parseDouble(\"1.5E1\")")));
    }

    @Test
    public void processEl948Test() {
        assertEq(1.5, getDouble(directCalculate("Double.parseDouble(\"15.0e-1\")")));
    }

    @Test
    public void processEl949Test() {
        assertEq(10.0, getDouble(directCalculate("Double.parseDouble(\"1E1\")")));
    }

    @Test
    public void processEl950Test() {
        assertEq(0.5, getDouble(directCalculate("Double.parseDouble(\"5E-1\")")));
    }

    @Test
    public void processEl951Test() {
        assertEq(10.0, getDouble(directCalculate("Double.parseDouble(\"1e1\")")));
    }

    @Test
    public void processEl952Test() {
        assertEq(0.5, getDouble(directCalculate("Double.parseDouble(\"5e-1\")")));
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
        assertEq(0.5, getDouble(directCalculate("Double.parseDouble(\".5\")")));
    }
    @Test
    public void processEl959Test() {
        Struct arg_ = directCalculateExc("Double.parseDouble(\"\")");
        ErrorStruct err_ = (ErrorStruct) arg_;
        assertEq("java.lang.badFormat", err_.getClassName());
    }

    @Test
    public void processEl960Test() {
        assertEq(-1.0,getDouble(directCalculate("Double.parseDouble(\"-1\")")));
    }

    @Test
    public void processEl961Test() {
        assertEq(-1.5,getDouble(directCalculate("Double.parseDouble(\"-1.5\")")));
    }

    @Test
    public void processEl962Test() {
        assertEq(-15.0,getDouble(directCalculate("Double.parseDouble(\"-1.5e1\")")));
    }

    @Test
    public void processEl963Test() {
        assertEq(-15.0,getDouble(directCalculate("Double.parseDouble(\"-1.5E1\")")));
    }

    @Test
    public void processEl964Test() {
        assertEq(-1.5,getDouble(directCalculate("Double.parseDouble(\"-15.0e-1\")")));
    }

    @Test
    public void processEl965Test() {
        assertEq(-10.0,getDouble(directCalculate("Double.parseDouble(\"-1E1\")")));
    }

    @Test
    public void processEl966Test() {
        assertEq(-0.5,getDouble(directCalculate("Double.parseDouble(\"-5E-1\")")));
    }

    @Test
    public void processEl967Test() {
        assertEq(-10.0,getDouble(directCalculate("Double.parseDouble(\"-1e1\")")));
    }

    @Test
    public void processEl968Test() {
        assertEq(-0.5,getDouble(directCalculate("Double.parseDouble(\"-5e-1\")")));
    }

    @Test
    public void processEl969Test() {
        assertEq(-0.5,getDouble(directCalculate("Double.parseDouble(\"-.5\")")));
    }

    @Test
    public void processEl970Test() {
        assertEq(-1.2345678912345678912e26,getDouble(directCalculate("Double.parseDouble(\"-123456789123456789123456789.0\")")));
    }

    @Test
    public void processEl971Test() {
        assertEq(-0.0,getDouble(directCalculate("Double.parseDouble(\"-0.0e-36\")")));
    }

    @Test
    public void processEl972Test() {
        assertEq(-1.0,getDouble(directCalculate("Double.parseDouble(\"-1.0\")")));
    }

    @Test
    public void processEl973Test() {
        assertEq(-0.0,getDouble(directCalculate("Double.parseDouble(\"-1e-123456789123456789123\")")));
    }

    @Test
    public void processEl974Test() {
        assertEq(Double.NEGATIVE_INFINITY, getDouble(directCalculate("Double.parseDouble(\"-1e123456789123456789123\")")));
    }

    @Test
    public void processEl975Test() {
        assertEq(-10, getNumber(directCalculate("-0xa")));
    }

    @Test
    public void processEl976Test() {
        Struct arg_ = directCalculate("$new $int[ ]{2i}");
        ArrayStruct res_ = (ArrayStruct) arg_;
        assertEq(ARR_INT, res_.getClassName());
        Struct[] o_ = res_.getInstance();
        assertEq(1, o_.length);
        assertEq(2, ((NumberStruct) o_[0]).intStruct());
    }
    @Test
    public void processEl977Test() {
        assertFalse(directCalculate("$new $int[1] $instanceof $int[][]"));
    }
    @Test
    public void processEl978Test() {
        assertFalse(directCalculate("($lambda(String,charAt,$int)$instanceof $Fct<String,$int>)"));
    }
    @Test
    public void processEl979Test() {
        assertTrue(directCalculate("($lambda(String,charAt,$int)$instanceof $Fct<CharSequence,$int,$char>)"));
    }
    @Test
    public void processEl980Test() {
        assertFalse(directCalculate("($lambda(String,charAt,$int)$instanceof $Fct<CharSequence,Object,$char>)"));
    }
    @Test
    public void processEl981Test() {
        assertFalse(directCalculate("($lambda(String,charAt,$int)$instanceof $Fct<CharSequence,$int,String>)"));
    }
    @Test
    public void processEl982Test() {
        assertTrue(directCalculate("($lambda(String,charAt,$int)$instanceof $Fct<CharSequence,$int,?>)"));
    }
    @Test
    public void processEl983Test() {
        assertTrue(directCalculate("($lambda(String,charAt,$int)$instanceof $Fct<CharSequence,?,$char>)"));
    }
    @Test
    public void processEl984Test() {
        assertTrue(directCalculate("($lambda($Fct<?,?>,call,Object...)$instanceof $Fct<$Fct<?,?>,Object[],Object>)"));
    }
    @Test
    public void processEl985Test() {
        assertTrue(directCalculate("($lambda($Fct<?,?>,call,Object...)$instanceof $Fct<$Fct<String,?>,Object[],Object>)"));
    }
    @Test
    public void processEl986Test() {
        assertTrue(directCalculate("($lambda($Fct<?,?>,call,Object...)$instanceof $Fct<$Fct<?,String>,Object[],Object>)"));
    }
    @Test
    public void processEl987Test() {
        assertTrue(directCalculate("($lambda($Fct<String,String>,call,Object...)$instanceof $Fct<$Fct<?,?>,Object[],Object>)"));
    }
    @Test
    public void processEl988Test() {
        assertTrue(directCalculate("($lambda($Fct<String,?>,call,Object...)$instanceof $Fct<$Fct<?,String>,Object[],Object>)"));
    }
    @Test
    public void processEl989Test() {
        assertTrue(directCalculate("($lambda($Fct<?,String>,call,Object...)$instanceof $Fct<$Fct<String,?>,Object[],Object>)"));
    }
    @Test
    public void processEl990Test() {
        assertFalse(directCalculate("($lambda($Fct<?,String>,call,Object...)$instanceof Integer)"));
    }
    @Test
    public void processEl991Test() {
        assertFalse(directCalculate("(0 $instanceof $Fct<?,String>)"));
    }
    @Test
    public void processEl992Test() {
        assertFalse(directCalculate("($new $int[0] $instanceof $Fct<?,String>)"));
    }
    @Test
    public void processEl993Test() {
        assertFalse(directCalculate("($new $int[0] $instanceof $Fct<?,String>[])"));
    }
    @Test
    public void processEl994Test() {
        assertFalse(directCalculate("($new Integer[0] $instanceof $Fct<?,String>)"));
    }
    @Test
    public void processEl995Test() {
        assertFalse(directCalculate("($new Integer[0] $instanceof $Fct<?,String>[])"));
    }
    @Test
    public void processEl996Test() {
        assertFalse(directCalculate("$lambda(String,charAt,$int)$instanceof $Fct<String,$int>"));
    }

    @Test
    public void processEl997Test() {
        assertEq(1, getNumber(directCalculate("Byte.parseByteOrNull(\"1\",10)")));
    }

    @Test
    public void processEl998Test() {
        assertEq(1, getNumber(directCalculate("Short.parseShortOrNull(\"1\",10)")));
    }

    @Test
    public void processEl999Test() {
        assertEq(1, getNumber(directCalculate("Integer.parseIntOrNull(\"1\",10)")));
    }

    @Test
    public void processEl1000Test() {
        assertEq(1, getNumber(directCalculate("Long.parseLongOrNull(\"1\",10)")));
    }

    @Test
    public void processEl1001Test() {
        assertEq(1, getNumber(directCalculate("Short.parseShortOrNull(\"1\")")));
    }

    @Test
    public void processEl1002Test() {
        assertEq(1, getNumber(directCalculate("Integer.parseIntOrNull(\"1\")")));
    }

    @Test
    public void processEl1003Test() {
        assertEq(1, getNumber(directCalculate("Long.parseLongOrNull(\"1\")")));
    }

    @Test
    public void processEl1004Test() {
        assertEq(1.0, getDouble(directCalculate("Float.parseFloatOrNull(\"1\")")));
    }

    @Test
    public void processEl1005Test() {
        assertEq(1.0, getDouble(directCalculate("Double.parseDoubleOrNull(\"1\")")));
    }

    @Test
    public void processEl1006Test() {
        assertEq(1, getNumber(directCalculate("Byte.parseByteOrNull(\"1\")")));
    }

    @Test
    public void processEl1007Test() {
        assertSame(NullStruct.NULL_VALUE,directCalculate("Short.parseShortOrNull($null)"));
    }

    @Test
    public void processEl1008Test() {
        assertSame(NullStruct.NULL_VALUE,directCalculate("Integer.parseIntOrNull($null)"));
    }

    @Test
    public void processEl1009Test() {
        assertSame(NullStruct.NULL_VALUE,directCalculate("Long.parseLongOrNull($null)"));
    }

    @Test
    public void processEl1010Test() {
        assertSame(NullStruct.NULL_VALUE,directCalculate("Float.parseFloatOrNull($null)"));
    }

    @Test
    public void processEl1011Test() {
        assertSame(NullStruct.NULL_VALUE,directCalculate("Double.parseDoubleOrNull($null)"));
    }

    @Test
    public void processEl1012Test() {
        assertSame(NullStruct.NULL_VALUE,directCalculate("Byte.parseByteOrNull($null)"));
    }

    @Test
    public void processEl1013Test() {
        assertSame(NullStruct.NULL_VALUE,directCalculate("Short.parseShortOrNull(\"\")"));
    }

    @Test
    public void processEl1014Test() {
        assertSame(NullStruct.NULL_VALUE,directCalculate("Integer.parseIntOrNull(\"\")"));
    }

    @Test
    public void processEl1015Test() {
        assertSame(NullStruct.NULL_VALUE,directCalculate("Long.parseLongOrNull(\"\")"));
    }

    @Test
    public void processEl1016Test() {
        assertSame(NullStruct.NULL_VALUE,directCalculate("Float.parseFloatOrNull(\"\")"));
    }

    @Test
    public void processEl1017Test() {
        assertSame(NullStruct.NULL_VALUE,directCalculate("Double.parseDoubleOrNull(\"\")"));
    }

    @Test
    public void processEl1018Test() {
        assertSame(NullStruct.NULL_VALUE,directCalculate("Byte.parseByteOrNull(\"\")"));
    }

    @Test
    public void processEl1019Test() {
        assertEq(1, getNumber(directCalculate("((Byte)1).intValue()")));
    }

    @Test
    public void processEl1020Test() {
        assertEq(1, getNumber(directCalculate("((Byte)1).longValue()")));
    }

    @Test
    public void processEl1021Test() {
        assertEq(1, getNumber(directCalculate("((Byte)1).shortValue()")));
    }

    @Test
    public void processEl1022Test() {
        assertEq(1, getNumber(directCalculate("((Byte)1).byteValue()")));
    }

    @Test
    public void processEl1023Test() {
        assertEq(1.0, getDouble(directCalculate("((Byte)1).floatValue()")));
    }

    @Test
    public void processEl1024Test() {
        assertEq(1.0, getDouble(directCalculate("((Byte)1).doubleValue()")));
    }


    @Test
    public void processEl1025Test() {
        assertEq(1, getNumber(directCalculate("((Short)1).intValue()")));
    }

    @Test
    public void processEl1026Test() {
        assertEq(1, getNumber(directCalculate("((Short)1).longValue()")));
    }

    @Test
    public void processEl1027Test() {
        assertEq(1, getNumber(directCalculate("((Short)1).shortValue()")));
    }

    @Test
    public void processEl1028Test() {
        assertEq(1, getNumber(directCalculate("((Short)1).byteValue()")));
    }

    @Test
    public void processEl1029Test() {
        assertEq(1.0, getDouble(directCalculate("((Short)1).floatValue()")));
    }

    @Test
    public void processEl1030est() {
        assertEq(1.0, getDouble(directCalculate("((Short)1).doubleValue()")));
    }

    @Test
    public void processEl10251Test() {
        assertEq(1, getNumber(directCalculate("((Character)1).intValue()")));
    }

    @Test
    public void processEl10261Test() {
        assertEq(1, getNumber(directCalculate("((Character)1).longValue()")));
    }

    @Test
    public void processEl10271Test() {
        assertEq(1, getNumber(directCalculate("((Character)1).shortValue()")));
    }

    @Test
    public void processEl10281Test() {
        assertEq(1, getNumber(directCalculate("((Character)1).byteValue()")));
    }

    @Test
    public void processEl10291Test() {
        assertEq(1.0, getDouble(directCalculate("((Character)1).floatValue()")));
    }

    @Test
    public void processEl10301est() {
        assertEq(1.0, getDouble(directCalculate("((Character)1).doubleValue()")));
    }

    @Test
    public void processEl10252Test() {
        assertEq(1, getNumber(directCalculate("((Float)1).intValue()")));
    }

    @Test
    public void processEl10262Test() {
        assertEq(1, getNumber(directCalculate("((Float)1).longValue()")));
    }

    @Test
    public void processEl10272Test() {
        assertEq(1, getNumber(directCalculate("((Float)1).shortValue()")));
    }

    @Test
    public void processEl10282Test() {
        assertEq(1, getNumber(directCalculate("((Float)1).byteValue()")));
    }

    @Test
    public void processEl10292Test() {
        assertEq(1.0, getDouble(directCalculate("((Float)1).floatValue()")));
    }

    @Test
    public void processEl10302est() {
        assertEq(1.0, getDouble(directCalculate("((Float)1).doubleValue()")));
    }

    @Test
    public void processEl10253Test() {
        assertEq(1, getNumber(directCalculate("((Double)1).intValue()")));
    }

    @Test
    public void processEl10263Test() {
        assertEq(1, getNumber(directCalculate("((Double)1).longValue()")));
    }

    @Test
    public void processEl10273Test() {
        assertEq(1, getNumber(directCalculate("((Double)1).shortValue()")));
    }

    @Test
    public void processEl10283Test() {
        assertEq(1, getNumber(directCalculate("((Double)1).byteValue()")));
    }

    @Test
    public void processEl10293Test() {
        assertEq(1.0, getDouble(directCalculate("((Double)1).floatValue()")));
    }

    @Test
    public void processEl10303est() {
        assertEq(1.0, getDouble(directCalculate("((Double)1).doubleValue()")));
    }

    @Test
    public void processEl103031est() {
        assertTrue(directCalculate("1d == 1f"));
    }

    @Test
    public void processEl103032est() {
        assertTrue(directCalculate("1d == ($double)1L"));
    }

    @Test
    public void processEl103034est() {
        assertFalse(directCalculate("1d == 2f"));
    }

    @Test
    public void processEl103034_est() {
        assertFalse(directCalculate("$new Long(1).equals(2f)"));
    }
    @Test
    public void processEl103035est() {
        assertFalse(directCalculate("1d == 2L"));
    }

    @Test
    public void processEl103036est() {
        assertTrue(directCalculate("1f == 1d"));
    }

    @Test
    public void processEl103037est() {
        assertTrue(directCalculate("1f == ($float)1L"));
    }

    @Test
    public void processEl103038est() {
        assertFalse(directCalculate("1f == 2d"));
    }

    @Test
    public void processEl103039est() {
        assertFalse(directCalculate("1f == 2L"));
    }

    @Test
    public void processEl103040est() {
        assertTrue(directCalculate("($double)1L == 1d"));
    }

    @Test
    public void processEl103041est() {
        assertTrue(directCalculate("($float)1L == 1f"));
    }

    @Test
    public void processEl103042est() {
        assertFalse(directCalculate("1L == 2d"));
    }

    @Test
    public void processEl103043est() {
        assertFalse(directCalculate("1L == 2f"));
    }

    @Test
    public void processEl103044Test() {
        assertFalse(directCalculate("$defaultValue($boolean)"));
    }

    @Test
    public void processEl103045Test() {
        assertEq(0, getNumber(directCalculate("$defaultValue($int)")));
    }

    @Test
    public void processEl103046Test() {
        Struct arg_ = directCalculate("$defaultValue($char)");
        assertEq(0, getChar(arg_));
    }

    @Test
    public void processEl103047Test() {
        assertSame(NullStruct.NULL_VALUE,directCalculate("$defaultValue(String)"));
    }

    @Test
    public void processEl103048Test() {
        assertEq("", getString(directCalculate("$null+$null")));
    }

    @Test
    public void processEl103049Test() {
        assertEq("", getString(directCalculate("$null+\"\"")));
    }

    @Test
    public void processEl103050Test() {
        assertEq("", getString(directCalculate("\"\"+$null")));
    }

    @Test
    public void processEl103051Test() {
        assertFalse(directCalculate("$class($Fct<Object>[]).isAssignableFrom($class($Fct<Object>))"));
    }

    @Test
    public void processEl103052Test() {
        Struct arg_ = directCalculateExc("$($Fct<String,$int>[])$lambda(String,length)");
        assertNotNull(arg_);
    }

    @Test
    public void processEl103053Test() {
        assertEq(150.0, getDouble(directCalculate("Double.parseDouble(\"15.0e+1\")")));
    }

    @Test
    public void processEl103054Test() {
        assertEq(150.0, getDouble(directCalculate("Double.parseDouble(\"+15.0e1\")")));
    }
    @Test
    public void processElCmp1est() {
        assertEq(-1, getNumber(directCalculate("Number.compare(1L,2d)")));
    }

    @Test
    public void processElCmp2est() {
        assertEq(-1, getNumber(directCalculate("Number.compare(1L,2f)")));
    }

    @Test
    public void processElCmp3est() {
        assertEq(-1, getNumber(directCalculate("Number.compare(1f,2d)")));
    }

    @Test
    public void processElCmp4est() {
        assertEq(-1, getNumber(directCalculate("Number.compare(1f,2f)")));
    }

    @Test
    public void processElCmp5est() {
        assertEq(1, getNumber(directCalculate("Number.compare(2d,1L)")));
    }

    @Test
    public void processElCmp6est() {
        assertEq(1, getNumber(directCalculate("Number.compare(2f,1L)")));
    }

    @Test
    public void processElCmp7est() {
        assertEq(1, getNumber(directCalculate("Number.compare(2d,1f)")));
    }

    @Test
    public void processElCmp8est() {
        assertEq(1, getNumber(directCalculate("Number.compare(2f,1f)")));
    }

    @Test
    public void processElCmp9est() {
        assertEq(0, getNumber(directCalculate("Number.compare(1L,1d)")));
    }

    @Test
    public void processElCmp10est() {
        assertEq(0, getNumber(directCalculate("Number.compare(1L,1f)")));
    }

    @Test
    public void processElCmp11est() {
        assertEq(0, getNumber(directCalculate("Number.compare(1d,1L)")));
    }

    @Test
    public void processElCmp12est() {
        assertEq(0, getNumber(directCalculate("Number.compare(1f,1L)")));
    }

    @Test
    public void processEl1LtG11test() {
        assertTrue(directCalculate("($float)1L < 2f"));
    }

    @Test
    public void processEl1LtG2test() {
        assertTrue(directCalculate("($double)1L < 2d"));
    }

    @Test
    public void processEl1LtG13test() {
        assertTrue(directCalculate("1d < ($double)2L"));
    }

    @Test
    public void processEl1LtG4test() {
        assertTrue(directCalculate("1f < ($float)2L"));
    }

    @Test
    public void processEl1LtG15test() {
        assertFalse(directCalculate("($float)2L < 1f"));
    }

    @Test
    public void processEl1LtG6test() {
        assertFalse(directCalculate("($double)2L < 1d"));
    }

    @Test
    public void processEl1LtG17test() {
        assertFalse(directCalculate("2d < ($double)1L"));
    }

    @Test
    public void processEl1LtG8test() {
        assertFalse(directCalculate("2f < ($float)1L"));
    }

    @Test
    public void processEl1LtG19test() {
        assertFalse(directCalculate("($float)1L > 2f"));
    }

    @Test
    public void processEl1LtG10test() {
        assertFalse(directCalculate("($double)1L > 2d"));
    }

    @Test
    public void processEl1LtG111test() {
        assertFalse(directCalculate("1d > ($double)2L"));
    }

    @Test
    public void processEl1LtG12test() {
        assertFalse(directCalculate("1f > ($float)2L"));
    }

    @Test
    public void processEl1LtG113test() {
        assertTrue(directCalculate("($float)2L > 1f"));
    }

    @Test
    public void processEl1LtG14test() {
        assertTrue(directCalculate("($float)2L > 1d"));
    }

    @Test
    public void processEl1LtG115test() {
        assertTrue(directCalculate("2d > ($double)1L"));
    }

    @Test
    public void processEl1LtG16test() {
        assertTrue(directCalculate("2f > ($float)1L"));
    }

    @Test
    public void processEl103055Test() {
        assertSame(NullStruct.NULL_VALUE,directCalculate("Double.parseDoubleOrNull(\".\")"));
    }

    @Test
    public void processEl103056Test() {
        assertEq(0.5, getDouble(directCalculate("Double.parseDoubleOrNull(\".5\")")));
    }

    @Test
    public void processEl103057Test() {
        assertEq(1.0, getDouble(directCalculate("Double.parseDoubleOrNull(\"1.\")")));
    }

    @Test
    public void processEl103058Test() {
        assertSame(NullStruct.NULL_VALUE,directCalculate("Double.parseDoubleOrNull(\"..\")"));
    }

    @Test
    public void processEl103059Test() {
        assertSame(NullStruct.NULL_VALUE,directCalculate("Double.parseDoubleOrNull(\".e\")"));
    }

    @Test
    public void processEl103060Test() {
        assertSame(NullStruct.NULL_VALUE,directCalculate("Double.parseDoubleOrNull(\".e1\")"));
    }

    @Test
    public void processEl103061Test() {
        assertSame(NullStruct.NULL_VALUE,directCalculate("Double.parseDoubleOrNull(\"1e+\")"));
    }

    @Test
    public void processEl103062Test() {
        assertSame(NullStruct.NULL_VALUE,directCalculate("Double.parseDoubleOrNull(\"1e-\")"));
    }

    @Test
    public void processEl103063Test() {
        assertEq("21", getString(directCalculate("Long.toString(33,16)")));
    }

    @Test
    public void processEl103064Test() {
        assertEq(0, getNumber(directCalculate("Long.sgn(0)")));
    }

    @Test
    public void processEl103064_Test() {
        assertEq(1.0, getDouble(directCalculate("Double.sgn(1d)")));
    }
    @Test
    public void processEl103065Test() {
        assertEq("0", getString(directCalculate("Byte.bin(($byte)0)")));
    }

    @Test
    public void processEl103066Test() {
        assertEq("0", getString(directCalculate("Byte.oct(($byte)0)")));
    }

    @Test
    public void processEl103067Test() {
        assertEq("0", getString(directCalculate("Byte.hex(($byte)0)")));
    }

    @Test
    public void processEl103068Test() {
        assertEq("0", getString(directCalculate("Short.bin(($short)0)")));
    }

    @Test
    public void processEl103069Test() {
        assertEq("0", getString(directCalculate("Short.oct(($short)0)")));
    }

    @Test
    public void processEl103070Test() {
        assertEq("0", getString(directCalculate("Short.hex(($short)0)")));
    }

    @Test
    public void processEl103071Test() {
        assertEq("0", getString(directCalculate("Integer.bin(0)")));
    }

    @Test
    public void processEl103072Test() {
        assertEq("0", getString(directCalculate("Integer.oct(0)")));
    }

    @Test
    public void processEl103073Test() {
        assertEq("0", getString(directCalculate("Integer.hex(0)")));
    }

    @Test
    public void processEl103074Test() {
        assertEq("0", getString(directCalculate("Long.bin(0)")));
    }

    @Test
    public void processEl103075Test() {
        assertEq("0", getString(directCalculate("Long.oct(0)")));
    }

    @Test
    public void processEl103076Test() {
        assertEq("0", getString(directCalculate("Long.hex(0)")));
    }

    @Test
    public void processEl103077Test() {
        assertEq(0, getNumber(directCalculate("$math.max(0,0)")));
    }

    @Test
    public void processEl103078Test() {
        assertEq(0, getNumber(directCalculate("$math.max(0,0L)")));
    }

    @Test
    public void processEl103077_Test() {
        assertEq(1.0, getDouble(directCalculate("$math.max(1f,1f)")));
    }

    @Test
    public void processEl103078_Test() {
        assertEq(1.0, getDouble(directCalculate("$math.max(1d,1d)")));
    }
    @Test
    public void processEl103079Test() {
        assertEq(0, getNumber(directCalculate("$math.min(0,0)")));
    }

    @Test
    public void processEl103080Test() {
        assertEq(0, getNumber(directCalculate("$math.min(0,0L)")));
    }
    @Test
    public void processEl103079_Test() {
        assertEq(1.0, getDouble(directCalculate("$math.min(1f,1f)")));
    }

    @Test
    public void processEl103080_Test() {
        assertEq(1.0, getDouble(directCalculate("$math.min(1d,1d)")));
    }

    @Test
    public void processEl103081Test() {
        assertEq(0, getNumber(directCalculate("Long.compare(1,1)")));
    }

    @Test
    public void processEl103082Test() {
        assertEq(0, getNumber(directCalculate("((Long)1).compareTo(1L)")));
    }

    @Test
    public void processEl103083Test() {
        assertEq(0, getNumber(directCalculate("Short.compare(1s,1s)")));
    }

    @Test
    public void processEl103084Test() {
        assertEq(0, getNumber(directCalculate("((Short)1S).compareTo(1S)")));
    }

    @Test
    public void processEl103085Test() {
        assertEq(0, getNumber(directCalculate("Byte.compare(1y,1y)")));
    }

    @Test
    public void processEl103086Test() {
        assertEq(0, getNumber(directCalculate("((Byte)1Y).compareTo(1Y)")));
    }

    @Test
    public void processEl103087Test() {
        assertEq(0, getNumber(directCalculate("Float.compare(1f,1f)")));
    }

    @Test
    public void processEl103088Test() {
        assertEq(0, getNumber(directCalculate("((Float)1F).compareTo(1F)")));
    }

    @Test
    public void processEl103089Test() {
        assertEq(0, getNumber(directCalculate("Double.compare(1d,1d)")));
    }

    @Test
    public void processEl103090Test() {
        assertEq(0, getNumber(directCalculate("((Double)1D).compareTo(1D)")));
    }
    @Test
    public void processEl103091Test() {
        assertFalse(directCalculate("\"hello_word\".equalsIgnoreCase(\"hello word\")"));
    }
    @Test
    public void processEl103092Test() {
        assertFalse(directCalculate("\"hello word\".regionMatches(-1,\"\",0,0)"));
    }
    @Test
    public void processEl103093Test() {
        assertFalse(directCalculate("\"hello word\".regionMatches($true,0,\"\",-1,0)"));
    }
    @Test
    public void processEl103094Test() {
        assertFalse(directCalculate("\"hello word\".regionMatches(11,\"ab\",0,0)"));
    }
    @Test
    public void processEl103095Test() {
        assertFalse(directCalculate("\"hello word\".regionMatches($true,0,\"ab\",3,0)"));
    }
    @Test
    public void processEl103096Test() {
        assertFalse(directCalculate("\"hello word\".regionMatches(0,\"ab\",0,1)"));
    }
    @Test
    public void processEl103097Test() {
        assertFalse(directCalculate("\"hello word\".regionMatches(0,\"hab\",0,2)"));
    }

    @Test
    public void processEl103098Test() {
        assertEq(" ", getString(directCalculate("\"\\s\"")));
    }

    @Test
    public void processEl103099Test() {
        assertEq("c", getString(directCalculate("\"\"\"\nc\"\"\"")));
    }

    @Test
    public void processEl103100Test() {
        assertEq("c", getString(directCalculate("\"\"\" \nc\"\"\"")));
    }

    @Test
    public void processEl103101Test() {
        assertEq("ch", getString(directCalculate("\"\"\" \nc\\\nh\"\"\"")));
    }

    @Test
    public void processEl103102Test() {
        assertEq("c", getString(directCalculate("\"\"\" \n c\"\"\"")));
    }

    @Test
    public void processEl103103Test() {
        assertEq("c h", getString(directCalculate("\"\"\" \n c h\"\"\"")));
    }

    @Test
    public void processEl103104Test() {
        assertEq("c\nt", getString(directCalculate("\"\"\" \nc\nt\"\"\"")));
    }

    @Test
    public void processEl103105Test() {
        assertEq("c\nt", getString(directCalculate("\"\"\" \nc \nt\"\"\"")));
    }

    @Test
    public void processEl103106Test() {
        assertEq("c h\nt", getString(directCalculate("\"\"\" \nc h \nt\"\"\"")));
    }

    @Test
    public void processEl103107Test() {
        assertEq("l\n\nc", getString(directCalculate("\"\"\" \nl\n \nc\"\"\"")));
    }

    @Test
    public void processEl103108Test() {
        assertEq("l\nc", getString(directCalculate("\"\"\" \nl\nc \"\"\"")));
    }

    @Test
    public void processEl103109Test() {
        assertEq("\"\"\";", getString(directCalculate("\"\"\"\n\\\"\"\";\"\"\"")));
    }

    @Test
    public void processEl103110Test() {
        assertEq("\"\"\"", getString(directCalculate("\"\"\"\n\\\"\\\"\\\"\"\"\"")));
    }

    @Test
    public void processEl103111Test() {
        assertEq("l\n \nc", getString(directCalculate("\"\"\" \nl\n\\s\nc\"\"\"")));
    }

    @Test
    public void processEl103112Test() {
        assertEq("l\n\t\nc", getString(directCalculate("\"\"\" \nl\n\\t\nc\"\"\"")));
    }


    @Test
    public void processEl103113Test() {
        assertEq("l\n\f\nc", getString(directCalculate("\"\"\" \nl\n\\f\nc\"\"\"")));
    }

    @Test
    public void processEl103114Test() {
        assertEq("l\n\r\nc", getString(directCalculate("\"\"\" \nl\n\\r\nc\"\"\"")));
    }


    @Test
    public void processEl103115Test() {
        assertEq("l\n\b\nc", getString(directCalculate("\"\"\" \nl\n\\b\nc\"\"\"")));
    }

    @Test
    public void processEl103116Test() {
        assertEq("\"a", getString(directCalculate("\"\"\"\n\"a\"\"\"")));
    }

    @Test
    public void processEl103117Test() {
        assertEq("\"\"a", getString(directCalculate("\"\"\"\n\"\"a\"\"\"")));
    }

    @Test
    public void processEl103118Test() {
        assertEq("l\n \nc", getString(directCalculate("\"\"\" \nl\n\\u0020\nc\"\"\"")));
    }

    @Test
    public void processEl103119Test() {
        assertEq("l\n\uaaaa\nc", getString(directCalculate("\"\"\" \nl\n\\uaaaa\nc\"\"\"")));
    }

    @Test
    public void processEl103120Test() {
        assertEq("l\n\uaaaa\nc", getString(directCalculate("\"\"\" \nl\n\\uAAAA\nc\"\"\"")));
    }

    @Test
    public void processEl103121Test() {
        assertEq("\\", getString(directCalculate("\"\"\" \n\\\\\"\"\"")));
    }

    @Test
    public void processEl103122Test() {
        assertEq("c h", getString(directCalculate("\"\"\" \nc \\\nh\"\"\"")));
    }

    @Test
    public void processEl103123Test() {
        assertEq("c h", getString(directCalculate("\"\"\" \nc\\\n h\"\"\"")));
    }

    @Test
    public void processEl103124Test() {
        assertEq("c\n h", getString(directCalculate("\"\"\" \nc\\n h\"\"\"")));
    }

    @Test
    public void processEl113099Test() {
        assertEq("c", getString(directCalculate("'''\nc'''")));
    }

    @Test
    public void processEl113100Test() {
        assertEq("c", getString(directCalculate("''' \nc'''")));
    }

    @Test
    public void processEl113101Test() {
        assertEq("ch", getString(directCalculate("''' \nc\\\nh'''")));
    }

    @Test
    public void processEl113102Test() {
        assertEq("c", getString(directCalculate("''' \n c'''")));
    }

    @Test
    public void processEl113103Test() {
        assertEq("c h", getString(directCalculate("''' \n c h'''")));
    }

    @Test
    public void processEl113104Test() {
        assertEq("c\nt", getString(directCalculate("''' \nc\nt'''")));
    }

    @Test
    public void processEl113105Test() {
        assertEq("c\nt", getString(directCalculate("''' \nc \nt'''")));
    }

    @Test
    public void processEl113106Test() {
        assertEq("c h\nt", getString(directCalculate("''' \nc h \nt'''")));
    }

    @Test
    public void processEl113107Test() {
        assertEq("l\n\nc", getString(directCalculate("''' \nl\n \nc'''")));
    }

    @Test
    public void processEl113108Test() {
        assertEq("l\nc", getString(directCalculate("''' \nl\nc '''")));
    }

    @Test
    public void processEl113109Test() {
        assertEq("''';", getString(directCalculate("'''\n\\''';'''")));
    }

    @Test
    public void processEl113110Test() {
        assertEq("'''", getString(directCalculate("'''\n\\'\\'\\''''")));
    }

    @Test
    public void processEl113111Test() {
        assertEq("l\n \nc", getString(directCalculate("''' \nl\n\\s\nc'''")));
    }

    @Test
    public void processEl113112Test() {
        assertEq("l\n\t\nc", getString(directCalculate("''' \nl\n\\t\nc'''")));
    }


    @Test
    public void processEl113113Test() {
        assertEq("l\n\f\nc", getString(directCalculate("''' \nl\n\\f\nc'''")));
    }

    @Test
    public void processEl113114Test() {
        assertEq("l\n\r\nc", getString(directCalculate("''' \nl\n\\r\nc'''")));
    }


    @Test
    public void processEl113115Test() {
        assertEq("l\n\b\nc", getString(directCalculate("''' \nl\n\\b\nc'''")));
    }

    @Test
    public void processEl113116Test() {
        assertEq("'a", getString(directCalculate("'''\n'a'''")));
    }

    @Test
    public void processEl113117Test() {
        assertEq("''a", getString(directCalculate("'''\n''a'''")));
    }

    @Test
    public void processEl113118Test() {
        assertEq("l\n \nc", getString(directCalculate("''' \nl\n\\u0020\nc'''")));
    }

    @Test
    public void processEl113119Test() {
        assertEq("l\n\uaaaa\nc", getString(directCalculate("''' \nl\n\\uaaaa\nc'''")));
    }

    @Test
    public void processEl113120Test() {
        assertEq("l\n\uaaaa\nc", getString(directCalculate("''' \nl\n\\uAAAA\nc'''")));
    }

    @Test
    public void processEl113121Test() {
        assertEq("\\", getString(directCalculate("''' \n\\\\'''")));
    }

    @Test
    public void processEl113122Test() {
        assertEq("c h", getString(directCalculate("''' \nc \\\nh'''")));
    }

    @Test
    public void processEl113123Test() {
        assertEq("c h", getString(directCalculate("''' \nc\\\n h'''")));
    }

    @Test
    public void processEl113124Test() {
        assertEq("c\n h", getString(directCalculate("''' \nc\\n h'''")));
    }

    @Test
    public void processElLineRetTest() {
        assertEq("ab", getString(directCalculate("\"a\\\nb\"")));
    }

    @Test
    public void processElNbFct1Test() {
        assertEq(1, getNumber(directCalculate("1.intValue()")));
    }

    @Test
    public void processElNbFct2Test() {
        assertEq(1, getNumber(directCalculate("1l.longValue()")));
    }

    @Test
    public void processElNbFct3Test() {
        assertEq(1, getNumber(directCalculate("1y.intValue()")));
    }

    @Test
    public void processElNbFct4Test() {
        assertEq(1, getNumber(directCalculate("1s.intValue()")));
    }

    @Test
    public void processElNbFct5Test() {
        assertEq(1, getNumber(directCalculate("1i.intValue()")));
    }

    @Test
    public void processElNbFct6Test() {
        assertEq(1, getNumber(directCalculate("01.intValue()")));
    }

    @Test
    public void processElNbFct7Test() {
        assertEq(1, getNumber(directCalculate("01l.longValue()")));
    }

    @Test
    public void processElNbFct8Test() {
        assertEq(1, getNumber(directCalculate("01y.intValue()")));
    }

    @Test
    public void processElNbFct9Test() {
        assertEq(1, getNumber(directCalculate("01s.intValue()")));
    }

    @Test
    public void processElNbFct10Test() {
        assertEq(1, getNumber(directCalculate("01i.intValue()")));
    }

    @Test
    public void processElNbFct11Test() {
        assertEq(1, getNumber(directCalculate("0b1.intValue()")));
    }

    @Test
    public void processElNbFct12Test() {
        assertEq(1, getNumber(directCalculate("0b1l.longValue()")));
    }

    @Test
    public void processElNbFct13Test() {
        assertEq(1, getNumber(directCalculate("0b1y.intValue()")));
    }

    @Test
    public void processElNbFct14Test() {
        assertEq(1, getNumber(directCalculate("0b1s.intValue()")));
    }

    @Test
    public void processElNbFct15Test() {
        assertEq(1, getNumber(directCalculate("0b1i.intValue()")));
    }

    @Test
    public void processElNbFct16Test() {
        assertEq(1, getNumber(directCalculate("0x1.intValue()")));
    }

    @Test
    public void processElNbFct17Test() {
        assertEq(1, getNumber(directCalculate("0x1l.longValue()")));
    }

    @Test
    public void processElNbFct18Test() {
        assertEq(1, getNumber(directCalculate("0x1y.intValue()")));
    }

    @Test
    public void processElNbFct19Test() {
        assertEq(1, getNumber(directCalculate("0x1s.intValue()")));
    }

    @Test
    public void processElNbFct20Test() {
        assertEq(1, getNumber(directCalculate("0x1i.intValue()")));
    }

    @Test
    public void processElNbFct21Test() {
        assertEq(1.0, getDouble(directCalculate("1.0.doubleValue()")));
    }

    @Test
    public void processElNbFct22Test() {
        assertEq(1.0, getDouble(directCalculate("1.0d.doubleValue()")));
    }

    @Test
    public void processElNbFct23Test() {
        assertEq(1.0, getDouble(directCalculate("1.0f.doubleValue()")));
    }

    @Test
    public void processElNbFct24Test() {
        assertEq(1.0, getDouble(directCalculate("1..doubleValue()")));
    }

    @Test
    public void processElNbFct25Test() {
        assertEq(1.0, getDouble(directCalculate("1d.doubleValue()")));
    }

    @Test
    public void processElNbFct26Test() {
        assertEq(1.0, getDouble(directCalculate("1f.doubleValue()")));
    }

    @Test
    public void processElNbFct27Test() {
        assertEq(10.0, getDouble(directCalculate("1.0e1.doubleValue()")));
    }

    @Test
    public void processElNbFct28Test() {
        assertEq(10.0, getDouble(directCalculate("1.0e1d.doubleValue()")));
    }

    @Test
    public void processElNbFct29Test() {
        assertEq(10.0, getDouble(directCalculate("1.0e1f.doubleValue()")));
    }

    @Test
    public void processElNbFct30Test() {
        assertEq(10.0, getDouble(directCalculate("1e1.doubleValue()")));
    }

    @Test
    public void processElNbFct31Test() {
        assertEq(10.0, getDouble(directCalculate("1e1d.doubleValue()")));
    }

    @Test
    public void processElNbFct32Test() {
        assertEq(10.0, getDouble(directCalculate("1e1f.doubleValue()")));
    }

    @Test
    public void processElNbFct33Test() {
        assertEq(0.5, getDouble(directCalculate("5.0e-1.doubleValue()")));
    }

    @Test
    public void processElNbFct34Test() {
        assertEq(0.5, getDouble(directCalculate("5.0e-1d.doubleValue()")));
    }

    @Test
    public void processElNbFct35Test() {
        assertEq(0.5, getDouble(directCalculate("5.0e-1f.doubleValue()")));
    }

    @Test
    public void processElNbFct36Test() {
        assertEq(0.5, getDouble(directCalculate("5e-1.doubleValue()")));
    }

    @Test
    public void processElNbFct37Test() {
        assertEq(0.5, getDouble(directCalculate("5e-1d.doubleValue()")));
    }

    @Test
    public void processElNbFct38Test() {
        assertEq(0.5, getDouble(directCalculate("5e-1f.doubleValue()")));
    }

    @Test
    public void processElNbFct39Test() {
        assertEq(0.5, getDouble(directCalculate(".5.doubleValue()")));
    }

    @Test
    public void processElNbFct40Test() {
        assertEq(0.5, getDouble(directCalculate(".5d.doubleValue()")));
    }

    @Test
    public void processElNbFct41Test() {
        assertEq(0.5, getDouble(directCalculate(".5f.doubleValue()")));
    }

    @Test
    public void processElNbFct42Test() {
        assertEq(1.0, getDouble(directCalculate(".1e1.doubleValue()")));
    }

    @Test
    public void processElNbFct43Test() {
        assertEq(1.0, getDouble(directCalculate(".1e1d.doubleValue()")));
    }

    @Test
    public void processElNbFct44Test() {
        assertEq(1.0, getDouble(directCalculate(".1e1f.doubleValue()")));
    }

    @Test
    public void processElNbFct45Test() {
        assertEq(0.0625, getDouble(directCalculate(".625e-1.doubleValue()")));
    }

    @Test
    public void processElNbFct46Test() {
        assertEq(0.0625, getDouble(directCalculate(".625e-1d.doubleValue()")));
    }

    @Test
    public void processElNbFct47Test() {
        assertEq(0.0625, getDouble(directCalculate(".625e-1f.doubleValue()")));
    }

    @Test
    public void processElNbFct48Test() {
        assertEq(1, getNumber(directCalculate("1 .intValue()")));
    }

    @Test
    public void processElNbFct49Test() {
        assertEq(1.0, getDouble(directCalculate("1.0 .doubleValue()")));
    }

    @Test
    public void processElNbFct50Test() {
        assertEq(1.0, getDouble(directCalculate("0x1.0.doubleValue()")));
    }

    @Test
    public void processElNbFct51Test() {
        assertEq(1.0, getDouble(directCalculate("0x1.0xd.doubleValue()")));
    }

    @Test
    public void processElNbFct52Test() {
        assertEq(1.0, getDouble(directCalculate("0x1.0xf.doubleValue()")));
    }

    @Test
    public void processElNbFct53Test() {
        assertEq(1.0, getDouble(directCalculate("0x1..doubleValue()")));
    }

    @Test
    public void processElNbFct54est() {
        assertEq(1.0, getDouble(directCalculate("0x1xd.doubleValue()")));
    }

    @Test
    public void processElNbFct55Test() {
        assertEq(1.0, getDouble(directCalculate("0x1xf.doubleValue()")));
    }

    @Test
    public void processElNbFct56Test() {
        assertEq(2.0, getDouble(directCalculate("0x1.0p1.doubleValue()")));
    }

    @Test
    public void processElNbFct57Test() {
        assertEq(2.0, getDouble(directCalculate("0x1.0p1d.doubleValue()")));
    }

    @Test
    public void processElNbFct58Test() {
        assertEq(2.0, getDouble(directCalculate("0x1.0p1f.doubleValue()")));
    }

    @Test
    public void processElNbFct59Test() {
        assertEq(2.0, getDouble(directCalculate("0x1p1.doubleValue()")));
    }

    @Test
    public void processElNbFct60Test() {
        assertEq(2, getDouble(directCalculate("0x1p1d.doubleValue()")));
    }

    @Test
    public void processElNbFct61Test() {
        assertEq(2.0, getDouble(directCalculate("0x1p1f.doubleValue()")));
    }

    @Test
    public void processElNbFct62Test() {
        assertEq(2.5, getDouble(directCalculate("0x5.0p-1.doubleValue()")));
    }

    @Test
    public void processElNbFct63Test() {
        assertEq(2.5, getDouble(directCalculate("0x5.0p-1d.doubleValue()")));
    }

    @Test
    public void processElNbFct64Test() {
        assertEq(2.5, getDouble(directCalculate("0x5.0p-1f.doubleValue()")));
    }

    @Test
    public void processElNbFct65Test() {
        assertEq(2.5, getDouble(directCalculate("0x5p-1.doubleValue()")));
    }

    @Test
    public void processElNbFct66Test() {
        assertEq(2.5, getDouble(directCalculate("0x5p-1d.doubleValue()")));
    }

    @Test
    public void processElNbFct67Test() {
        assertEq(2.5, getDouble(directCalculate("0x5p-1f.doubleValue()")));
    }

    @Test
    public void processElNbFct68Test() {
        assertEq(0.3125, getDouble(directCalculate("0x0.5.doubleValue()")));
    }

    @Test
    public void processElNbFct69Test() {
        assertEq(0.3125, getDouble(directCalculate("0x0.5xd.doubleValue()")));
    }

    @Test
    public void processElNbFct70Test() {
        assertEq(0.3125, getDouble(directCalculate("0x0.5xf.doubleValue()")));
    }

    @Test
    public void processElNbFct71Test() {
        assertEq(0.125, getDouble(directCalculate("0x0.1p1.doubleValue()")));
    }

    @Test
    public void processElNbFct72Test() {
        assertEq(0.125, getDouble(directCalculate("0x0.1p1d.doubleValue()")));
    }

    @Test
    public void processElNbFct73Test() {
        assertEq(0.125, getDouble(directCalculate("0x0.1p1f.doubleValue()")));
    }

    @Test
    public void processElNbFct74Test() {
        assertEq(0.1920166015625, getDouble(directCalculate("0x0.625p-1.doubleValue()")));
    }

    @Test
    public void processElNbFct75Test() {
        assertEq(0.1920166015625, getDouble(directCalculate("0x0.625p-1d.doubleValue()")));
    }

    @Test
    public void processElNbFct76Test() {
        assertEq(0.1920166015625, getDouble(directCalculate("0x0.625p-1f.doubleValue()")));
    }
    private static Struct directCalculate(String _el) {
        ContextEl c_ = analyze(_el);
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,c_);
        Struct arg_ = tryCalculate(c_, stackCall_);
        assertNull(stackCall_.getCallingState());
        return arg_;
    }

    private static Struct directCalculateExc(String _el) {
        ContextEl c_ = analyze(_el);
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,c_);
        tryCalculate(c_, stackCall_);
        assertNotNull(((CustomFoundExc) stackCall_.getCallingState()).getStruct());
        return ((CustomFoundExc) stackCall_.getCallingState()).getStruct();
    }

    private static ContextEl analyze(String _el) {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/ExTwo", addonFileStaticResult(_el));
        return contextEl(files_);
    }

    private static Struct calculateIndirectLocalVars(String _var, String _className) {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", file());
        ContextEl cont_ = contextEl(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody(_className);
        Struct fresh_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(classBody_,_className), "", -1);
        ExecFieldBlock f_ = fetchInstanceField(classBody_);
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,cont_);
        addImportingPage(stackCall_);
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(fresh_);
        lv_.setClassName(_className);
        stackCall_.getLastPage().putValueVar(_var, lv_);
        stackCall_.getLastPage().setGlobalArgumentStruct(fresh_);
        Struct arg_ = tryToCalculate(cont_, f_, stackCall_);
        assertNull(stackCall_.getCallingState());
        return arg_;

    }

    private static Struct calculateIndirectLoopVars(String _var, String _className) {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", file());
        ContextEl cont_ = contextEl(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody(_className);
        Struct fresh_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(classBody_,_className), "", -1);
        ExecFieldBlock f_ = fetchInstanceField(classBody_);
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,cont_);
        addImportingPage(stackCall_);
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(fresh_);
        lv_.setClassName(_className);
        stackCall_.getLastPage().getRefParams().put(_var, new VariableWrapper(lv_));
        stackCall_.getLastPage().setGlobalArgumentStruct(fresh_);
        Struct arg_ = tryToCalculate(cont_, f_, stackCall_);
        assertNull(stackCall_.getCallingState());
        return arg_;

    }

    private static Struct calculateIndirect(String _className) {
        String var_ = "temp";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", file());
        ContextEl cont_ = contextEl(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody(_className);
        Struct fresh_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(classBody_,_className), "", -1);
        ExecFieldBlock f_ = fetchInstanceField(classBody_);
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,cont_);
        addImportingPage(stackCall_);
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(fresh_);
        lv_.setClassName(_className);
        stackCall_.getLastPage().putValueVar(var_, lv_);
        stackCall_.getLastPage().setGlobalArgumentStruct(fresh_);
        Struct arg_ = tryToCalculate(cont_, f_, stackCall_);
        assertNull(stackCall_.getCallingState());
        return arg_;
    }

    private static Struct tryCalculate(ContextEl _context, StackCall _stackCall) {
        ExecRootBlock cl_ = _context.getClasses().getClassBody("code.formathtml.classes.Apply");
        CommonMethodPageEl page_ = new CommonMethodPageEl(new ExecFormattedRootBlock(cl_, "code.formathtml.classes.Apply"));
        ExecutingUtil.addPage(page_, _stackCall);
        ExecFieldBlock f_ = fetchStaticField(cl_);
        return tryToCalculate(_context,f_, _stackCall);
    }

    private static Struct tryToCalculate(ContextEl _conf, ExecFieldBlock _f, StackCall _stackCall) {
        return ArgumentListCall.toStr(ExecHelperBlocks.tryToCalculate(_conf, 0,_stackCall,_f.getElementContent().getOpValue(), 0, _f,-1));
    }

    private static ExecFieldBlock fetchInstanceField(ExecRootBlock _cl) {
        return (ExecFieldBlock) _cl.getAllInstanceMembers().first();
    }

    private static ExecFieldBlock fetchStaticField(ExecRootBlock _cl) {
        return (ExecFieldBlock) _cl.getAllStaticMembers().first();
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

    private static void addImportingPage(StackCall _stackCall) {
        ExecutingUtil.addPage(new CommonMethodPageEl(new ExecFormattedRootBlock((ExecRootBlock)null,"")), _stackCall);
    }

    private static ContextEl contextEl(StringMap<String> _files) {
        Options opt_ = newOptions();
        addTypesInit(opt_);
        CustLgNames lgName_ = getLgNames();
        KeyWords kw_ = new KeyWords();
        setOpts(opt_,IndexConstants.INDEX_NOT_FOUND_ELT);
        return getContextEl(_files, opt_, lgName_, kw_);
    }
}
