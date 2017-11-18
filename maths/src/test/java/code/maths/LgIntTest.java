package code.maths;
import static code.maths.EquallableMathUtil.assertEq;
import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertTrue;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import code.maths.exceptions.BadDivisionException;
import code.maths.exceptions.FormatException;
import code.maths.exceptions.NegatifExposantException;
import code.maths.exceptions.NegativeNumberException;
import code.util.CustList;
import code.util.EqList;
import code.util.Numbers;
import code.util.SortableCustList;
import code.util.TreeMap;

@RunWith(JUnitParamsRunner.class)
@SuppressWarnings("static-method")
public class LgIntTest {

    @Test
    public void base1Test() {
        assertEq(1000000000, LgInt.base());
    }

    @Test
    public void logBase1Test() {
        assertEq(9, LgInt.logBase());
    }

    @Test
    public void new_LgInt_1Test() {
        LgInt entier_ = LgInt.zero();
        assertEq(1, entier_.getGrDigits().size());
        assertEq(0, entier_.getGrDigits().first().intValue());
        assertEq(LgInt.SIGNE_POSITIF,entier_.getSignum());
        assertTrue(entier_.isZero());
    }

    Object[] inputsNewLgIntLong() {
        return $($(1L,new Numbers<Long>(1L),LgInt.SIGNE_POSITIF),
                        $(0L,new Numbers<Long>(0L),LgInt.SIGNE_POSITIF),
                        $(-1L,new Numbers<Long>(1L),!LgInt.SIGNE_POSITIF),
                        $(1234567890L,new Numbers<Long>(1L,234567890L),LgInt.SIGNE_POSITIF),
                        $(-1234567890L,new Numbers<Long>(1L,234567890L),!LgInt.SIGNE_POSITIF),
                        $(Long.MAX_VALUE,new Numbers<Long>(9L,223372036L,854775807L),LgInt.SIGNE_POSITIF));
    }

    @Test
    @Parameters(method="inputsNewLgIntLong")
    public void new_LgInt_long_1Test(long _input,Numbers<Long> _expectedDigits, boolean _signum) {
        LgInt int_ = new LgInt(_input);
        Numbers<Long> resDigits_ = int_.getGrDigits();
        assertEqDigits(_expectedDigits, resDigits_);
        assertEq(_signum, int_.getSignum());
    }

    Object[] inputsNewLgIntString() {
        return $($("1",new Numbers<Long>(1L),LgInt.SIGNE_POSITIF),
                        $("0",new Numbers<Long>(0L),LgInt.SIGNE_POSITIF),
                        $("-1",new Numbers<Long>(1L),!LgInt.SIGNE_POSITIF),
                        $("1234567890",new Numbers<Long>(1L,234567890L),LgInt.SIGNE_POSITIF),
                        $("-1234567890",new Numbers<Long>(1L,234567890L),!LgInt.SIGNE_POSITIF),
                        $("1000000000",new Numbers<Long>(1L,0L),LgInt.SIGNE_POSITIF),
                        $("-1000000000",new Numbers<Long>(1L,0L),!LgInt.SIGNE_POSITIF),
                        $("12345678901234567890",new Numbers<Long>(12L,345678901L,234567890L),LgInt.SIGNE_POSITIF),
                        $("-12345678901234567890",new Numbers<Long>(12L,345678901L,234567890L),!LgInt.SIGNE_POSITIF),
                        $("12000000000234567890",new Numbers<Long>(12L,0L,234567890L),LgInt.SIGNE_POSITIF),
                        $("-12000000000234567890",new Numbers<Long>(12L,0L,234567890L),!LgInt.SIGNE_POSITIF),
                        $("12000000001234567890",new Numbers<Long>(12L,1L,234567890L),LgInt.SIGNE_POSITIF),
                        $("-12000000001234567890",new Numbers<Long>(12L,1L,234567890L),!LgInt.SIGNE_POSITIF));
    }

    @Test
    @Parameters(method="inputsNewLgIntString")
    public void new_LgInt_String_1Test(String _input,Numbers<Long> _expectedDigits, boolean _signum) {
        LgInt int_ = new LgInt(_input);
        Numbers<Long> resDigits_ = int_.getGrDigits();
        assertEqDigits(_expectedDigits, resDigits_);
        assertEq(_signum, int_.getSignum());
    }

    private static void assertEqDigits(Numbers<Long> _expected, Numbers<Long> _result) {
        int expectedLen_ = _expected.size();
        assertEq(expectedLen_,_result.size());
        for (int i = 0; i < expectedLen_; i++) {
            assertEq(_expected.get(i),_result.get(i));
        }
    }

    @Test(expected = FormatException.class)
    public void new_LgInt_String_1FailTest() {
        falseInteger1();
    }

    private static LgInt falseInteger1() {
        return new LgInt("-");
    }

    @Test(expected = FormatException.class)
    public void new_LgInt_String_2FailTest() {
        falseInteger2();
    }

    private static LgInt falseInteger2() {
        return new LgInt("");
    }

    @Test(expected = FormatException.class)
    public void new_LgInt_String_3FailTest() {
        falseInteger3();
    }

    private static LgInt falseInteger3() {
        return new LgInt("2a");
    }

    @Test(expected = FormatException.class)
    public void new_LgInt_String_4FailTest() {
        falseInteger4();
    }

    private static LgInt falseInteger4() {
        return new LgInt("a");
    }

    @Test(expected = FormatException.class)
    public void new_LgInt_String_5FailTest() {
        falseInteger5();
    }

    private static LgInt falseInteger5() {
        return new LgInt("a2");
    }

    Object[] inputsToString() {
        return $($(new LgInt(1),"1"),
                $(new LgInt(-1),"-1"),
                $(new LgInt("-0"),"0"),
                $(LgInt.zero(),"0"),
                $(new LgInt(1234567890),"1234567890"),
                $(new LgInt(1000000000),"1000000000"),
                $(new LgInt(-1000000000),"-1000000000"),
                $(new LgInt(-1234567890),"-1234567890"),
                $(new LgInt("12345678901234567890"),"12345678901234567890"),
                $(new LgInt("-12345678901234567890"),"-12345678901234567890"),
                $(new LgInt("12000000000234567890"),"12000000000234567890"),
                $(new LgInt("-12000000000234567890"),"-12000000000234567890"),
                $(new LgInt("12000000001234567890"),"12000000001234567890"),
                $(new LgInt("-12000000001234567890"),"-12000000001234567890"));
    }

    @Parameters(method="inputsToString")
    @Test
    public void toString1Test(LgInt _input, String _expected) {
        //_input.setUnmodified();
        assertEq(_expected, _input.toString());
        //assertTrue(!_input.isModified());
    }

    Object[] inputsEquals() {
        return $($("1","1",true),
                $("1","-1",false),
                $("10","1",false),
                $("-1","10",false),
                $("1000000000","1",false),
                $("1","1000000000",false),
                $("1000000000","1000000000",true));
    }

    @Test
    @Parameters(method="inputsEquals")
    public void eq1Test(String _one, String _two, boolean _expected) {
        LgInt intOne_ = new LgInt(_one);
        LgInt intTwo_ = new LgInt(_two);
        //intOne_.setUnmodified();
        //intTwo_.setUnmodified();
        assertEq(_expected, intOne_.eq(intTwo_));
        //assertTrue(!intOne_.isModified());
        //assertTrue(!intTwo_.isModified());
    }

    Object[] inputsDifferent() {
        return $($("1","1",false),
                $("1","-1",true),
                $("10","1",true),
                $("-1","10",true),
                $("1000000000","1",true),
                $("1","1000000000",true),
                $("1000000000","1000000000",false));
    }

    @Test
    @Parameters(method="inputsDifferent")
    public void different1Test(String _one, String _two, boolean _expected) {
        LgInt intOne_ = new LgInt(_one);
        LgInt intTwo_ = new LgInt(_two);
        //intOne_.setUnmodified();
        //intTwo_.setUnmodified();
        assertEq(_expected, LgInt.different(intOne_, intTwo_));
        //assertTrue(!intOne_.isModified());
        //assertTrue(!intTwo_.isModified());
    }

    Object[] inputsLl() {
        return $($("1",1L),
                $("-1",-1L),
                $("0",0L),
                $("1234567890",234567890L),
                $("-1234567890",-234567890L),
                $("12345678901234567890",234567890L),
                $("-12345678901234567890",-234567890L));
    }

    @Test
    @Parameters(method="inputsLl")
    public void ll1Test(String _input, long _expected) {
        LgInt int_ = new LgInt(_input);
        assertEq(_expected, int_.ll());
        assertEq(new LgInt(_input), int_);
    }

    Object[] inputsInfEg() {
        return $($("0", "3", true),
                $("3", "3", true),
                $("-3", "0", true),
                $("3", "1234567890", true),
                $("-3", "1234567890", true),
                $("-1234567890", "3", true),
                $("-1234567890", "-3", true),
                $("3", "0", false),
                $("0", "-3", false),
                $("1234567890", "3", false),
                $("1234567890", "-3", false),
                $("3", "-1234567890", false),
                $("-3", "-1234567890", false));
    }

    @Test
    @Parameters(method="inputsInfEg")
    public void lowerEq1Test(String _one, String _two, boolean _expected) {
        LgInt intOne_ = new LgInt(_one);
        LgInt intTwo_ = new LgInt(_two);
        assertEq(_expected, LgInt.lowerEq(intOne_, intTwo_));
        assertEq(new LgInt(_one), intOne_);
        assertEq(new LgInt(_two), intTwo_);
    }

    Object[] inputsSupEg() {
        return $($("0", "3", false),
                $("3", "3", true),
                $("-3", "0", false),
                $("3", "1234567890", false),
                $("-3", "1234567890", false),
                $("-1234567890", "3", false),
                $("-1234567890", "-3", false),
                $("3", "0", true),
                $("0", "-3", true),
                $("1234567890", "3", true),
                $("1234567890", "-3", true),
                $("3", "-1234567890", true),
                $("-3", "-1234567890", true));
    }

    @Test
    @Parameters(method="inputsSupEg")
    public void greaterEq1Test(String _one, String _two, boolean _expected) {
        LgInt intOne_ = new LgInt(_one);
        LgInt intTwo_ = new LgInt(_two);
        assertEq(_expected, LgInt.greaterEq(intOne_, intTwo_));
        assertEq(new LgInt(_one), intOne_);
        assertEq(new LgInt(_two), intTwo_);
    }

    Object[] inputsStrPlusPetit() {
        return $($("0", "3", true),
                $("3", "3", false),
                $("-3", "0", true),
                $("3", "1234567890", true),
                $("-3", "1234567890", true),
                $("-1234567890", "3", true),
                $("-1234567890", "-3", true),
                $("3", "0", false),
                $("0", "-3", false),
                $("1234567890", "3", false),
                $("1234567890", "-3", false),
                $("3", "-1234567890", false),
                $("-3", "-1234567890", false));
    }

    @Test
    @Parameters(method="inputsStrPlusPetit")
    public void strLower1Test(String _one, String _two, boolean _expected) {
        LgInt intOne_ = new LgInt(_one);
        LgInt intTwo_ = new LgInt(_two);
        assertEq(_expected, LgInt.strLower(intOne_, intTwo_));
        assertEq(new LgInt(_one), intOne_);
        assertEq(new LgInt(_two), intTwo_);
    }

    Object[] inputsStrPlusGrand() {
        return $($("0", "3", false),
                $("3", "3", false),
                $("-3", "0", false),
                $("3", "1234567890", false),
                $("-3", "1234567890", false),
                $("-1234567890", "3", false),
                $("-1234567890", "-3", false),
                $("3", "0", true),
                $("0", "-3", true),
                $("1234567890", "3", true),
                $("1234567890", "-3", true),
                $("3", "-1234567890", true),
                $("-3", "-1234567890", true));
    }

    @Test
    @Parameters(method="inputsStrPlusGrand")
    public void strGreater1Test(String _one, String _two, boolean _expected) {
        LgInt intOne_ = new LgInt(_one);
        LgInt intTwo_ = new LgInt(_two);
        assertEq(_expected, LgInt.strGreater(intOne_, intTwo_));
        assertEq(new LgInt(_one), intOne_);
        assertEq(new LgInt(_two), intTwo_);
    }

    Object[] inputsPlus() {
        return $($("0","20","20"),
                $("1","20","21"),
                $("-1","20","19"),
                $("1","-20","-19"),
                $("-1","-20","-21"),
                $("999999999999999999","1","1000000000000000000"),
                $("1","999999999999999999","1000000000000000000"),
                $("1234567890","11234567890","12469135780"),
                $("11234567890","1234567890","12469135780"),
                $("12345678901234567890","11234567890","12345678912469135780"),
                $("11234567890","12345678901234567890","12345678912469135780"),
                $("12345678901234567890","11876543210","12345678913111111100"),
                $("11876543210","12345678901234567890","12345678913111111100"),
                $("4444444444444444444","-555555555555555555","3888888888888888889"),
                $("4444444444444444444","-333333333555555555","4111111110888888889"),
                $("4444444444444444444","-555555555","4444444443888888889"),
                $("4444444444000000000","-1","4444444443999999999"),
                $("4000000000000000000","-1","3999999999999999999"));
    }

    @Test
    @Parameters(method="inputsPlus")
    public void plus1Test(String _one, String _two, String _expected) {
        LgInt one_ = new LgInt(_one);
        LgInt two_ = new LgInt(_two);
        assertEq(new LgInt(_expected), LgInt.plus(one_, two_));
        assertEq(new LgInt(_one), one_);
        assertEq(new LgInt(_two), two_);
    }

    Object[] inputsSoustraire() {
        return $($("0","-20","20"),
                $("1","-20","21"),
                $("-1","-20","19"),
                $("1","20","-19"),
                $("-1","20","-21"),
                $("999999999999999999","-1","1000000000000000000"),
                $("1","-999999999999999999","1000000000000000000"),
                $("1234567890","-11234567890","12469135780"),
                $("11234567890","-1234567890","12469135780"),
                $("12345678901234567890","-11234567890","12345678912469135780"),
                $("11234567890","-12345678901234567890","12345678912469135780"),
                $("12345678901234567890","-11876543210","12345678913111111100"),
                $("11876543210","-12345678901234567890","12345678913111111100"),
                $("4444444444444444444","555555555555555555","3888888888888888889"),
                $("4444444444444444444","333333333555555555","4111111110888888889"),
                $("4444444444444444444","555555555","4444444443888888889"),
                $("4444444444000000000","1","4444444443999999999"),
                $("4000000000000000000","1","3999999999999999999"));
    }
    @Test
    @Parameters(method="inputsSoustraire")
    public void removeNb1Test(String _one, String _two, String _expected) {
        LgInt intOne_ = new LgInt(_one);
        LgInt intTwo_ = new LgInt(_two);
        intOne_.removeNb(intTwo_);
        assertEq(new LgInt(_expected), intOne_);
        assertEq(new LgInt(_two), intTwo_);
    }

    Object[] inputsFact() {
        return $($("0", "1"),
                $("1", "1"),
                $("2", "2"),
                $("3", "6"),
                $("4", "24"));
    }

    @Test
    @Parameters(method="inputsFact")
    public void fact1Test(String _input, String _expected) {
        LgInt int_ = new LgInt(_input);
        assertEq(new LgInt(_expected), int_.fact());
        assertEq(new LgInt(_input), int_);
    }

    Object[] inputsOppose() {
        return $($("0", "0"),
                $("1", "-1"),
                $("-1", "1"),
                $("1234567890", "-1234567890"),
                $("-1234567890", "1234567890"),
                $("12345678901234567890", "-12345678901234567890"),
                $("-12345678901234567890", "12345678901234567890"));
    }

    @Test
    @Parameters(method="inputsOppose")
    public void opposNb1Test(String _input, String _expected) {
        LgInt int_ = new LgInt(_input);
        assertEq(new LgInt(_expected), int_.opposNb());
        assertEq(new LgInt(_input), int_);
    }

    Object[] inputsAbsNb() {
        return $($("0", "0"),
                $("1", "1"),
                $("-1", "1"),
                $("1234567890", "1234567890"),
                $("-1234567890", "1234567890"),
                $("12345678901234567890", "12345678901234567890"),
                $("-12345678901234567890", "12345678901234567890"));
    }

    @Test
    @Parameters(method="inputsAbsNb")
    public void absNb1Test(String _input, String _expected) {
        LgInt int_ = new LgInt(_input);
        assertEq(new LgInt(_expected), int_.absNb());
        assertEq(new LgInt(_input), int_);
    }

    Object[] inputsIncrement() {
        return $($("0", "1"),
                $("1", "2"),
                $("-1", "0"),
                $("1234567890", "1234567891"),
                $("-1234567890", "-1234567889"),
                $("12345678901234567890", "12345678901234567891"),
                $("-12345678901234567890", "-12345678901234567889"));
    }

    @Test
    @Parameters(method="inputsIncrement")
    public void increment1Test(String _input, String _expected) {
        LgInt int_ = new LgInt(_input);
        int_.increment();
        LgInt res_ = new LgInt(_expected);
        assertEq(res_, int_);
    }

    Object[] inputsDecrement() {
        return $($("0", "-1"),
                $("1", "0"),
                $("-1", "-2"),
                $("1234567890", "1234567889"),
                $("-1234567890", "-1234567891"),
                $("12345678901234567890", "12345678901234567889"),
                $("-12345678901234567890", "-12345678901234567891"));
    }

    @Test
    @Parameters(method="inputsDecrement")
    public void decrement1Test(String _input, String _expected) {
        LgInt int_ = new LgInt(_input);
        int_.decrement();
        LgInt res_ = new LgInt(_expected);
        assertEq(res_, int_);
    }

    Object[] inputsMoins() {
        return $($("0","20","-20"),
                $("1","20","-19"),
                $("-1","20","-21"),
                $("1","-20","21"),
                $("-1","-20","19"),
                $("1234567890","11234567890","-10000000000"),
                $("12345678901234567890","11234567890","12345678890000000000"));
    }

    @Test
    @Parameters(method="inputsMoins")
    public void minus1Test(String _one, String _two, String _expected) {
        LgInt intOne_ = new LgInt(_one);
        LgInt intTwo_ = new LgInt(_two);
        assertEq(new LgInt(_expected), LgInt.minus(intOne_, intTwo_));
        assertEq(new LgInt(_one), intOne_);
        assertEq(new LgInt(_two), intTwo_);
    }

    Object[] inputsFois() {
        return $($("0","20","0"),
                $("1","20","20"),
                $("-1","20","-20"),
                $("1","-20","-20"),
                $("-1","-20","20"),
                $("1234567890","11234567890","13869836775019052100"),
                $("11234567890","1234567890","13869836775019052100"),
                $("1234567890","1","1234567890"),
                $("1","1234567890","1234567890"),
                $("1234567890","11234567890000000000","13869836775019052100000000000"),
                $("1000000000000000000","2","2000000000000000000"),
                $("2","1000000000000000000","2000000000000000000"),
                $("1000000000000000000","2000000000","2000000000000000000000000000"),
                $("2000000000","1000000000000000000","2000000000000000000000000000"));
    }

    @Test
    @Parameters(method="inputsFois")
    public void multiply1Test(String _one, String _two, String _expected) {
        LgInt intOne_ = new LgInt(_one);
        LgInt intTwo_ = new LgInt(_two);
        assertEq(new LgInt(_expected), LgInt.multiply(intOne_, intTwo_));
        assertEq(new LgInt(_one), intOne_);
        assertEq(new LgInt(_two), intTwo_);
    }

    Object[] inputsDivision() {
        return $($("0","20","0"),
                $("1","20","0"),
                $("-1","20","-1"),
                $("1","-20","0"),
                $("-1","-20","1"),
                $("-40","-20","2"),
                $("-21","20","-2"),
                $("21","-20","-1"),
                $("-21","-20","2"),
                $("1234567890","11234567890","0"),
                $("11234567890","1234567890","9"),
                $("12345678901234567890","11234567890","1098901090"),
                $("11234567890","12345678901234567890","0"));
    }

    @Test
    @Parameters(method="inputsDivision")
    public void divide1Test(String _one, String _two, String _expected) {
        LgInt intOne_ = new LgInt(_one);
        LgInt intTwo_ = new LgInt(_two);
        assertEq(new LgInt(_expected), LgInt.divide(intOne_, intTwo_));
        assertEq(new LgInt(_one), intOne_);
        assertEq(new LgInt(_two), intTwo_);
    }

    @Test(expected = BadDivisionException.class)
    public void divide1FailTest() {
        LgInt one_ = new LgInt("1");
        LgInt two_ = new LgInt("0");
        LgInt.divide(one_, two_);
    }

    @Test(expected = BadDivisionException.class)
    public void divide2FailTest() {
        LgInt one_ = new LgInt("0");
        LgInt two_ = new LgInt("0");
        LgInt.divide(one_, two_);
    }

    @Test(expected = BadDivisionException.class)
    public void divide3FailTest() {
        LgInt one_ = new LgInt("-1");
        LgInt two_ = new LgInt("0");
        LgInt.divide(one_, two_);
    }

    Object[] inputsModulo() {
        return $($("0","20","0"),
                $("1","20","1"),
                $("-1","20","19"),
                $("1","-20","1"),
                $("-1","-20","19"),
                $("-21","20","19"),
                $("21","-20","1"),
                $("-21","-20","19"),
                $("-40","-20","0"),
                $("1234567890","11234567890","1234567890"),
                $("11234567890","1234567890","123456880"),
                $("12345678901234567890","11234567890","1234567790"),
                $("11234567890","12345678901234567890","11234567890"));
    }

    @Test
    @Parameters(method="inputsModulo")
    public void remain1Test(String _one, String _two, String _expected) {
        LgInt intOne_ = new LgInt(_one);
        LgInt intTwo_ = new LgInt(_two);
        assertEq(new LgInt(_expected), LgInt.remain(intOne_, intTwo_));
        assertEq(new LgInt(_one), intOne_);
        assertEq(new LgInt(_two), intTwo_);
    }

    @Test(expected = BadDivisionException.class)
    public void remain1FailTest() {
        LgInt one_ = new LgInt("1");
        LgInt two_ = new LgInt("0");
        LgInt.remain(one_, two_);
    }

    @Test(expected = BadDivisionException.class)
    public void remain2FailTest() {
        LgInt one_ = new LgInt("0");
        LgInt two_ = new LgInt("0");
        LgInt.remain(one_, two_);
    }

    @Test(expected = BadDivisionException.class)
    public void remain3FailTest() {
        LgInt one_ = new LgInt("-1");
        LgInt two_ = new LgInt("0");
        LgInt.remain(one_, two_);
    }

    Object[] inputsPuissance() {
        return $($("0", "0", "1"),
                $("0", "20", "0"),
                $("1", "20", "1"),
                $("-1", "20", "1"),
                $("-1", "19", "-1"),
                $("2", "3", "8"),
                $("3", "2", "9"),
                $("1234567890", "0", "1"));
    }

    @Test
    @Parameters(method="inputsPuissance")
    public void powNb1Test(String _one, String _two, String _expected) {
        LgInt intOne_ = new LgInt(_one);
        LgInt intTwo_ = new LgInt(_two);
        assertEq(new LgInt(_expected), LgInt.powNb(intOne_, intTwo_));
        assertEq(new LgInt(_one), intOne_);
        assertEq(new LgInt(_two), intTwo_);
    }

    @Test(expected = NegatifExposantException.class)
    public void powNb1FailTest() {
        LgInt one_ = new LgInt("2");
        LgInt two_ = new LgInt("-1");
        LgInt.powNb(one_, two_);
    }

    @Test(expected = NegatifExposantException.class)
    public void powNb2FailTest() {
        LgInt one_ = new LgInt("0");
        LgInt two_ = new LgInt("-1");
        LgInt.powNb(one_, two_);
    }

    Object[] inputsPgcd() {
        return $($("0", "0", "0"),
                $("0", "20", "20"),
                $("20", "0", "20"),
                $("1", "20", "1"),
                $("1", "19", "1"),
                $("-2", "-19", "1"),
                $("2", "-19", "1"),
                $("2", "19", "1"),
                $("-2", "19", "1"),
                $("2", "4", "2"),
                $("6", "4", "2"),
                $("6", "9", "3"));
    }

    @Test
    @Parameters(method="inputsPgcd")
    public void pgcd1Test(String _one, String _two, String _expected) {
        LgInt intOne_ = new LgInt(_one);
        LgInt intTwo_ = new LgInt(_two);
        assertEq(new LgInt(_expected), LgInt.pgcd(intOne_, intTwo_));
        assertEq(new LgInt(_one), intOne_);
        assertEq(new LgInt(_two), intTwo_);
    }

    Object[] inputsPpcm() {
        return $($("0", "0", "0"),
                $("0", "20", "0"),
                $("20", "0", "0"),
                $("1", "20", "20"),
                $("1", "19", "19"),
                $("-2", "-19", "38"),
                $("2", "-19", "38"),
                $("2", "19", "38"),
                $("-2", "19", "38"),
                $("2", "4", "4"),
                $("6", "4", "12"),
                $("6", "9", "18"));
    }

    @Test
    @Parameters(method="inputsPpcm")
    public void ppcm1Test(String _one, String _two, String _expected) {
        LgInt intOne_ = new LgInt(_one);
        LgInt intTwo_ = new LgInt(_two);
        assertEq(new LgInt(_expected), LgInt.ppcm(intOne_, intTwo_));
        assertEq(new LgInt(_one), intOne_);
        assertEq(new LgInt(_two), intTwo_);
    }

    Object[] inputsParmi() {
        return $($("0", "0", "1"),
                $("0", "20", "1"),
                $("1", "20", "20"),
                $("19", "20", "20"),
                $("2", "3", "3"),
                $("3", "2", "0"),
                $("2", "4", "6"),
                $("3", "6", "20"));
    }

    @Test
    @Parameters(method="inputsParmi")
    public void among1Test(String _one, String _two, String _expected) {
        LgInt intOne_ = new LgInt(_one);
        LgInt intTwo_ = new LgInt(_two);
        assertEq(new LgInt(_expected), LgInt.among(intOne_, intTwo_));
        assertEq(new LgInt(_one), intOne_);
        assertEq(new LgInt(_two), intTwo_);
    }

    @Test(expected = NegativeNumberException.class)
    public void among1FailTest() {
        LgInt.among(new LgInt(-1),new LgInt(2));
    }

    @Test(expected = NegativeNumberException.class)
    public void among2FailTest() {
        LgInt.among(new LgInt(2),new LgInt(-1));
    }

    @Test
    public void seqAmong1Test() {
        EqList<LgInt> repartitions_ = new EqList<LgInt>();
        repartitions_.add(new LgInt(8));
        repartitions_.add(new LgInt(8));
        repartitions_.add(new LgInt(8));
        repartitions_.add(new LgInt(8));
        LgInt sommeTotale_ = new LgInt(8);
        TreeMap<SortableCustList<LgInt>,LgInt> combinatoire_ = LgInt.seqAmong(repartitions_, sommeTotale_);
        assertEq(15,combinatoire_.size());
        assertTrue(combinatoire_.contains(new SortableCustList<LgInt>(LgInt.zero(),LgInt.zero(),LgInt.zero(),new LgInt(8))));
        assertTrue(combinatoire_.contains(new SortableCustList<LgInt>(LgInt.zero(),LgInt.zero(),new LgInt(1),new LgInt(7))));
        assertTrue(combinatoire_.contains(new SortableCustList<LgInt>(LgInt.zero(),LgInt.zero(),new LgInt(2),new LgInt(6))));
        assertTrue(combinatoire_.contains(new SortableCustList<LgInt>(LgInt.zero(),new LgInt(1),new LgInt(1),new LgInt(6))));
        assertTrue(combinatoire_.contains(new SortableCustList<LgInt>(LgInt.zero(),LgInt.zero(),new LgInt(3),new LgInt(5))));
        assertTrue(combinatoire_.contains(new SortableCustList<LgInt>(LgInt.zero(),new LgInt(1),new LgInt(2),new LgInt(5))));
        assertTrue(combinatoire_.contains(new SortableCustList<LgInt>(new LgInt(1),new LgInt(1),new LgInt(1),new LgInt(5))));
        assertTrue(combinatoire_.contains(new SortableCustList<LgInt>(LgInt.zero(),LgInt.zero(),new LgInt(4),new LgInt(4))));
        assertTrue(combinatoire_.contains(new SortableCustList<LgInt>(LgInt.zero(),new LgInt(2),new LgInt(2),new LgInt(4))));
        assertTrue(combinatoire_.contains(new SortableCustList<LgInt>(LgInt.zero(),new LgInt(1),new LgInt(3),new LgInt(4))));
        assertTrue(combinatoire_.contains(new SortableCustList<LgInt>(new LgInt(1),new LgInt(1),new LgInt(2),new LgInt(4))));
        assertTrue(combinatoire_.contains(new SortableCustList<LgInt>(LgInt.zero(),new LgInt(2),new LgInt(3),new LgInt(3))));
        assertTrue(combinatoire_.contains(new SortableCustList<LgInt>(new LgInt(1),new LgInt(1),new LgInt(3),new LgInt(3))));
        assertTrue(combinatoire_.contains(new SortableCustList<LgInt>(new LgInt(1),new LgInt(2),new LgInt(2),new LgInt(3))));
        assertTrue(combinatoire_.contains(new SortableCustList<LgInt>(new LgInt(2),new LgInt(2),new LgInt(2),new LgInt(2))));
        repartitions_ = new EqList<LgInt>();
        repartitions_.add(new LgInt(22));
        repartitions_.add(new LgInt(14));
        repartitions_.add(new LgInt(14));
        repartitions_.add(new LgInt(14));
        repartitions_.add(new LgInt(14));
    }

    Object[] seqAmong() {
        CustList<Object> list_ = new CustList<Object>();
        EqList<LgInt> repartitions_ = new EqList<LgInt>();
        repartitions_.add(new LgInt(8));
        repartitions_.add(new LgInt(8));
        repartitions_.add(new LgInt(8));
        repartitions_.add(new LgInt(8));
        LgInt sommeTotale_ = new LgInt(8);
        TreeMap<SortableCustList<LgInt>,LgInt> combinatoire_ = LgInt.seqAmong(repartitions_, sommeTotale_);
        repartitions_ = new EqList<LgInt>();
        repartitions_.add(new LgInt(22));
        repartitions_.add(new LgInt(14));
        repartitions_.add(new LgInt(14));
        repartitions_.add(new LgInt(14));
        repartitions_.add(new LgInt(14));
        combinatoire_ = LgInt.seqAmong(repartitions_, sommeTotale_);
        for (SortableCustList<LgInt> l : combinatoire_.getKeys()) {
            LgInt somme_ = LgInt.zero();
            for (LgInt e: l) {
                somme_.addNb(e);
            }
            list_.add(new Object[]{sommeTotale_, somme_, repartitions_.size(), l.size()});
        }
        return list_.toArray();
    }

    @Parameters(method="seqAmong")
    @Test
    public void seqAmong2Test(LgInt _expected, LgInt _resSum, int _expectedSize, int _resSize) {
        assertEq(_expected, _resSum);
        assertEq(_expectedSize, _resSize);
    }

    Object[] inputsFoisDouble() {
        return $($("0", 0.5, "0"),
                $("1", 0.5, "0"),
                $("1", 1.0, "1"),
                $("2", 1.0, "2"),
                $("2", 1.5, "3"),
                $("4", 0.75, "3"),
                $("12", 0.75, "9"),
                $("900000000", 1.5, "1350000000"),
                $("1234567890123456789", 0.75, "925925917592592591"),
                $("1234567890123456789", 0.0, "0"));
    }

    @Test
    @Parameters(method="inputsFoisDouble")
    public void multiply1Test(String _int, double _double, String _expected) {
        LgInt int_ = new LgInt(_int);
        LgInt res_ = int_.multiply(_double);
        assertEq(new LgInt(_expected), res_);
        assertEq(new LgInt(_int), int_);
    }


    Object[] inputsRacineAbs() {
        return $($("0", "1", "0"),
                $("0", "2", "0"),
                $("1", "0", "1"),
                $("0", "0", "0"),
                $("-1", "20", "1"),
                $("-1", "19", "1"),
                $("4", "2", "2"),
                $("5", "2", "2"),
                $("32", "5", "2"),
                $("32", "1", "32"),
                $("-32", "5", "2"),
                $("-16", "4", "2"));
    }

    @Test
    @Parameters(method="inputsRacineAbs")
    public void rootAbs1Test(String _one, String _two, String _expected) {
        LgInt intOne_ = new LgInt(_one);
        LgInt intTwo_ = new LgInt(_two);
        assertEq(new LgInt(_expected), intOne_.rootAbs(intTwo_));
        assertEq(new LgInt(_one), intOne_);
        assertEq(new LgInt(_two), intTwo_);
    }
}
