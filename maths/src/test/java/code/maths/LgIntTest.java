package code.maths;
import static code.maths.EquallableMathUtil.assertEq;
import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertTrue;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

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
        assertEq(_expected, _input.toNumberString());
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

    @Test
    public void seqAmong2Test(){
    	LgInt sommeTotale_ = new LgInt(8);
        EqList<LgInt> repartitions_ = new EqList<LgInt>();
        repartitions_.add(new LgInt(22));
        repartitions_.add(new LgInt(14));
        repartitions_.add(new LgInt(14));
        repartitions_.add(new LgInt(14));
        repartitions_.add(new LgInt(14));
        TreeMap<SortableCustList<LgInt>,LgInt> combinatoire_ = LgInt.seqAmong(repartitions_, sommeTotale_);
    	assertEq(18,combinatoire_.size());
        assertEq(5,combinatoire_.getKey(0).size());
        assertEq(new LgInt(0),combinatoire_.getKey(0).get(0));
        assertEq(new LgInt(0),combinatoire_.getKey(0).get(1));
        assertEq(new LgInt(0),combinatoire_.getKey(0).get(2));
        assertEq(new LgInt(0),combinatoire_.getKey(0).get(3));
        assertEq(new LgInt(8),combinatoire_.getKey(0).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(0));
        assertEq(5,combinatoire_.getKey(1).size());
        assertEq(new LgInt(0),combinatoire_.getKey(1).get(0));
        assertEq(new LgInt(0),combinatoire_.getKey(1).get(1));
        assertEq(new LgInt(0),combinatoire_.getKey(1).get(2));
        assertEq(new LgInt(1),combinatoire_.getKey(1).get(3));
        assertEq(new LgInt(7),combinatoire_.getKey(1).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(1));
        assertEq(5,combinatoire_.getKey(2).size());
        assertEq(new LgInt(0),combinatoire_.getKey(2).get(0));
        assertEq(new LgInt(0),combinatoire_.getKey(2).get(1));
        assertEq(new LgInt(0),combinatoire_.getKey(2).get(2));
        assertEq(new LgInt(2),combinatoire_.getKey(2).get(3));
        assertEq(new LgInt(6),combinatoire_.getKey(2).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(2));
        assertEq(5,combinatoire_.getKey(3).size());
        assertEq(new LgInt(0),combinatoire_.getKey(3).get(0));
        assertEq(new LgInt(0),combinatoire_.getKey(3).get(1));
        assertEq(new LgInt(0),combinatoire_.getKey(3).get(2));
        assertEq(new LgInt(3),combinatoire_.getKey(3).get(3));
        assertEq(new LgInt(5),combinatoire_.getKey(3).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(3));
        assertEq(5,combinatoire_.getKey(4).size());
        assertEq(new LgInt(0),combinatoire_.getKey(4).get(0));
        assertEq(new LgInt(0),combinatoire_.getKey(4).get(1));
        assertEq(new LgInt(0),combinatoire_.getKey(4).get(2));
        assertEq(new LgInt(4),combinatoire_.getKey(4).get(3));
        assertEq(new LgInt(4),combinatoire_.getKey(4).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(4));
        assertEq(5,combinatoire_.getKey(5).size());
        assertEq(new LgInt(0),combinatoire_.getKey(5).get(0));
        assertEq(new LgInt(0),combinatoire_.getKey(5).get(1));
        assertEq(new LgInt(1),combinatoire_.getKey(5).get(2));
        assertEq(new LgInt(1),combinatoire_.getKey(5).get(3));
        assertEq(new LgInt(6),combinatoire_.getKey(5).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(5));
        assertEq(5,combinatoire_.getKey(6).size());
        assertEq(new LgInt(0),combinatoire_.getKey(6).get(0));
        assertEq(new LgInt(0),combinatoire_.getKey(6).get(1));
        assertEq(new LgInt(1),combinatoire_.getKey(6).get(2));
        assertEq(new LgInt(2),combinatoire_.getKey(6).get(3));
        assertEq(new LgInt(5),combinatoire_.getKey(6).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(6));
        assertEq(5,combinatoire_.getKey(7).size());
        assertEq(new LgInt(0),combinatoire_.getKey(7).get(0));
        assertEq(new LgInt(0),combinatoire_.getKey(7).get(1));
        assertEq(new LgInt(1),combinatoire_.getKey(7).get(2));
        assertEq(new LgInt(3),combinatoire_.getKey(7).get(3));
        assertEq(new LgInt(4),combinatoire_.getKey(7).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(7));
        assertEq(5,combinatoire_.getKey(8).size());
        assertEq(new LgInt(0),combinatoire_.getKey(8).get(0));
        assertEq(new LgInt(0),combinatoire_.getKey(8).get(1));
        assertEq(new LgInt(2),combinatoire_.getKey(8).get(2));
        assertEq(new LgInt(2),combinatoire_.getKey(8).get(3));
        assertEq(new LgInt(4),combinatoire_.getKey(8).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(8));
        assertEq(5,combinatoire_.getKey(9).size());
        assertEq(new LgInt(0),combinatoire_.getKey(9).get(0));
        assertEq(new LgInt(0),combinatoire_.getKey(9).get(1));
        assertEq(new LgInt(2),combinatoire_.getKey(9).get(2));
        assertEq(new LgInt(3),combinatoire_.getKey(9).get(3));
        assertEq(new LgInt(3),combinatoire_.getKey(9).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(9));
        assertEq(5,combinatoire_.getKey(10).size());
        assertEq(new LgInt(0),combinatoire_.getKey(10).get(0));
        assertEq(new LgInt(1),combinatoire_.getKey(10).get(1));
        assertEq(new LgInt(1),combinatoire_.getKey(10).get(2));
        assertEq(new LgInt(1),combinatoire_.getKey(10).get(3));
        assertEq(new LgInt(5),combinatoire_.getKey(10).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(10));
        assertEq(5,combinatoire_.getKey(11).size());
        assertEq(new LgInt(0),combinatoire_.getKey(11).get(0));
        assertEq(new LgInt(1),combinatoire_.getKey(11).get(1));
        assertEq(new LgInt(1),combinatoire_.getKey(11).get(2));
        assertEq(new LgInt(2),combinatoire_.getKey(11).get(3));
        assertEq(new LgInt(4),combinatoire_.getKey(11).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(11));
        assertEq(5,combinatoire_.getKey(12).size());
        assertEq(new LgInt(0),combinatoire_.getKey(12).get(0));
        assertEq(new LgInt(1),combinatoire_.getKey(12).get(1));
        assertEq(new LgInt(1),combinatoire_.getKey(12).get(2));
        assertEq(new LgInt(3),combinatoire_.getKey(12).get(3));
        assertEq(new LgInt(3),combinatoire_.getKey(12).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(12));
        assertEq(5,combinatoire_.getKey(13).size());
        assertEq(new LgInt(0),combinatoire_.getKey(13).get(0));
        assertEq(new LgInt(1),combinatoire_.getKey(13).get(1));
        assertEq(new LgInt(2),combinatoire_.getKey(13).get(2));
        assertEq(new LgInt(2),combinatoire_.getKey(13).get(3));
        assertEq(new LgInt(3),combinatoire_.getKey(13).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(13));
        assertEq(5,combinatoire_.getKey(14).size());
        assertEq(new LgInt(0),combinatoire_.getKey(14).get(0));
        assertEq(new LgInt(2),combinatoire_.getKey(14).get(1));
        assertEq(new LgInt(2),combinatoire_.getKey(14).get(2));
        assertEq(new LgInt(2),combinatoire_.getKey(14).get(3));
        assertEq(new LgInt(2),combinatoire_.getKey(14).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(14));
        assertEq(5,combinatoire_.getKey(15).size());
        assertEq(new LgInt(1),combinatoire_.getKey(15).get(0));
        assertEq(new LgInt(1),combinatoire_.getKey(15).get(1));
        assertEq(new LgInt(1),combinatoire_.getKey(15).get(2));
        assertEq(new LgInt(1),combinatoire_.getKey(15).get(3));
        assertEq(new LgInt(4),combinatoire_.getKey(15).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(15));
        assertEq(5,combinatoire_.getKey(16).size());
        assertEq(new LgInt(1),combinatoire_.getKey(16).get(0));
        assertEq(new LgInt(1),combinatoire_.getKey(16).get(1));
        assertEq(new LgInt(1),combinatoire_.getKey(16).get(2));
        assertEq(new LgInt(2),combinatoire_.getKey(16).get(3));
        assertEq(new LgInt(3),combinatoire_.getKey(16).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(16));
        assertEq(5,combinatoire_.getKey(17).size());
        assertEq(new LgInt(1),combinatoire_.getKey(17).get(0));
        assertEq(new LgInt(1),combinatoire_.getKey(17).get(1));
        assertEq(new LgInt(2),combinatoire_.getKey(17).get(2));
        assertEq(new LgInt(2),combinatoire_.getKey(17).get(3));
        assertEq(new LgInt(2),combinatoire_.getKey(17).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(17));

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
