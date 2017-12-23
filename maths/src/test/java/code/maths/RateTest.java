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
import code.util.CustList;
import code.util.EqList;

@RunWith(JUnitParamsRunner.class)
@SuppressWarnings("static-method")
public class RateTest {

    @Test
    public void new_Rate_1Test() {
        Rate rate_ = Rate.zero();
        assertEq(new LgInt(0), rate_.getNumerator());
        assertEq(new LgInt(1), rate_.getDenominator());
        assertTrue(rate_.isZeroOrGt());
        assertTrue(rate_.isZero());
    }

    Object[] inputsNewRateLong() {
        return $($(1L,new LgInt(1L),new LgInt(1L),true,false),
                        $(0L,new LgInt(0L),new LgInt(1L),true,true),
                        $(-1L,new LgInt(-1L),new LgInt(1L),false,false),
                        $(1234567890L,new LgInt(1234567890L),new LgInt(1L),true,false),
                        $(-1234567890L,new LgInt(-1234567890L),new LgInt(1L),false,false));
    }

    @Test
    @Parameters(method="inputsNewRateLong")
    public void new_Rate_long_1Test(long _input, LgInt _num, LgInt _den, boolean _isPositive, boolean _isZero) {
        Rate rate_ = new Rate(_input);
        assertEq(_num, rate_.getNumerator());
        assertEq(_den, rate_.getDenominator());
        assertEq(_isPositive,rate_.isZeroOrGt());
        assertEq(_isZero,rate_.isZero());
    }

    Object[] inputsNewRateLgInt() {
        return $($(new LgInt(1L),new LgInt(1L),new LgInt(1L),true,false),
                        $(new LgInt(0L),new LgInt(0L),new LgInt(1L),true,true),
                        $(new LgInt(-1L),new LgInt(-1L),new LgInt(1L),false,false),
                        $(new LgInt(1234567890L),new LgInt(1234567890L),new LgInt(1L),true,false),
                        $(new LgInt(-1234567890L),new LgInt(-1234567890L),new LgInt(1L),false,false));
    }

    @Test
    @Parameters(method="inputsNewRateLgInt")
    public void new_Rate_LgInt_1Test(LgInt _input, LgInt _num, LgInt _den, boolean _isPositive, boolean _isZero) {
        Rate rate_ = new Rate(_input);
        assertEq(_num, rate_.getNumerator());
        assertEq(_den, rate_.getDenominator());
        assertEq(_isPositive,rate_.isZeroOrGt());
        assertEq(_isZero,rate_.isZero());
    }

    Object[] inputsNewRateLgIntLgInt() {
        return $($(new LgInt(1L), new LgInt(2L) ,new LgInt(1L),new LgInt(2L),true),
                        $(new LgInt(-1L),new LgInt(2L),new LgInt(-1L),new LgInt(2L),false),
                        $(new LgInt(1L),new LgInt(-2L),new LgInt(-1L),new LgInt(2L),false),
                        $(new LgInt(-1L),new LgInt(-2L),new LgInt(1L),new LgInt(2L),true),
                        $(new LgInt(2L), new LgInt(4L) ,new LgInt(1L),new LgInt(2L),true));
    }

    @Test
    @Parameters(method="inputsNewRateLgIntLgInt")
    public void new_Rate_LgInt_LgInt_1Test(LgInt _inputNum, LgInt _inputDen, LgInt _num, LgInt _den, boolean _isPositive) {
        Rate rate_ = new Rate(_inputNum, _inputDen);
        assertEq(_num, rate_.getNumerator());
        assertEq(_den, rate_.getDenominator());
        assertEq(_isPositive,rate_.isZeroOrGt());
    }

    Object[] inputsNewRatelonglong() {
        return $($(1L, 2L ,new LgInt(1L),new LgInt(2L),true),
                        $(-1L,2L,new LgInt(-1L),new LgInt(2L),false),
                        $(1L,-2L,new LgInt(-1L),new LgInt(2L),false),
                        $(-1L,-2L,new LgInt(1L),new LgInt(2L),true),
                        $(2L,4L,new LgInt(1L),new LgInt(2L),true));
    }

    @Test
    @Parameters(method="inputsNewRatelonglong")
    public void new_Rate_long_long_1Test(long _inputNum, long _inputDen, LgInt _num, LgInt _den, boolean _isPositive) {
        Rate rate_ = new Rate(_inputNum, _inputDen);
        assertEq(_num, rate_.getNumerator());
        assertEq(_den, rate_.getDenominator());
        assertEq(_isPositive,rate_.isZeroOrGt());
    }

    Object[] inputsNewRateString() {
        return $($("1" ,new LgInt(1L),new LgInt(1L),true,false),
                        $("-1",new LgInt(-1L),new LgInt(1L),false,false),
                        $("0",new LgInt(0L),new LgInt(1L),true,true),
                        $("01",new LgInt(1L),new LgInt(1L),true,false),
                        $("1234567890",new LgInt(1234567890L),new LgInt(1L),true,false),
                        $("-1234567890",new LgInt(-1234567890L),new LgInt(1L),false,false),
                        $("1/2",new LgInt(1L),new LgInt(2L),true,false),
                        $("2/4",new LgInt(1L),new LgInt(2L),true,false),
                        $("-1/2",new LgInt(-1L),new LgInt(2L),false,false),
                        $("1/-2",new LgInt(-1L),new LgInt(2L),false,false),
                        $("-1/-2",new LgInt(1L),new LgInt(2L),true,false),
                        $(".1",new LgInt(1L),new LgInt(10L),true,false),
                        $("-.1",new LgInt(-1L),new LgInt(10L),false,false),
                        $("1.",new LgInt(1L),new LgInt(1L),true,false),
                        $("1.2",new LgInt(6L),new LgInt(5L),true,false),
                        $("-1.2",new LgInt(-6L),new LgInt(5L),false,false),
                        $("-1.",new LgInt(-1L),new LgInt(1L),false,false));
    }

    @Test
    @Parameters(method="inputsNewRateString")
    public void new_Rate_String_1Test(String _input, LgInt _num, LgInt _den, boolean _isPositive, boolean _isZero) {
        Rate rate_ = new Rate(_input);
        assertEq(_num, rate_.getNumerator());
        assertEq(_den, rate_.getDenominator());
        assertEq(_isPositive,rate_.isZeroOrGt());
        assertEq(_isZero,rate_.isZero());
    }

    @Test(expected=FormatException.class)
    public void new_Rate_String_1FailTest() {
        falseRate1();
    }

    private static Rate falseRate1() {
        return new Rate("");
    }

    @Test(expected=FormatException.class)
    public void new_Rate_String_2FailTest() {
        falseRate2();
    }

    private static Rate falseRate2() {
        return new Rate("1/0");
    }

    @Test
    public void isValid1Test() {
        assertTrue(Rate.isValid("1"));
    }

    @Test
    public void isValid2Test() {
        assertTrue(Rate.isValid("1/2"));
    }

    @Test
    public void isValid3Test() {
        assertTrue(Rate.isValid("1.2"));
    }

    @Test
    public void isValid4Test() {
        assertTrue(!Rate.isValid("1/0"));
    }

    @Test
    public void isValid5Test() {
        assertTrue(!Rate.isValid(""));
    }

    @Test
    public void isValid6Test() {
        assertTrue(!Rate.isValid(null));
    }

    Object[] inputsToString() {
        return $($("1","1"),
                $("-1","-1"),
                $("-0","0"),
                $("0","0"),
                $("1234567890","1234567890"),
                $("-1234567890","-1234567890"),
                $("12345678901234567890","12345678901234567890"),
                $("-12345678901234567890","-12345678901234567890"),
                $("1/2","1/2"),
                $("-1/2","-1/2"),
                $("2/4","1/2"),
                $("-2/4","-1/2"));
    }

    @Test
    @Parameters(method="inputsToString")
    public void toString1Test(String _input, String _expected) {
        Rate rate_ = new Rate(_input);
        String str_ = rate_.toNumberString();
        assertEq(_expected, str_);
    }

    Object[] inputsEquals() {
        return $($("1","1",true),
                $("-1","1",false),
                $("1","-1",false),
                $("-1","-1",true),
                $("1/2","1/4",false),
                $("1/4","1/2",false),
                $("1/2","1/2",true),
                $("1/2","3/2",false),
                $("3/2","1/2",false));
    }

    @Test
    @Parameters(method="inputsEquals")
    public void eq1Test(String _one, String _two, boolean _expected) {
        Rate rateOne_ = new Rate(_one);
        Rate rateTwo_ = new Rate(_two);
        assertEq(_expected, rateOne_.eq(rateTwo_));
    }

    Object[] inputsDifferent() {
        return $($("1","1",false),
                $("-1","1",true),
                $("1","-1",true),
                $("-1","-1",false),
                $("1/2","1/4",true),
                $("1/4","1/2",true),
                $("1/2","1/2",false),
                $("1/2","3/2",true),
                $("3/2","1/2",true));
    }

    @Test
    @Parameters(method="inputsDifferent")
    public void different1Test(String _one, String _two, boolean _expected) {
        Rate rateOne_ = new Rate(_one);
        Rate rateTwo_ = new Rate(_two);
        assertEq(_expected, Rate.different(rateOne_, rateTwo_));
    }

    Object[] inputsIsInteger() {
        return $($("1",true),
                $("-1",true),
                $("0",true),
                $("-1/2",false),
                $("1/2",false));
    }

    @Test
    @Parameters(method="inputsIsInteger")
    public void isInteger1Test(String _input, boolean _expected) {
        assertEq(_expected, new Rate(_input).isInteger());
    }

    Object[] inputsChangeSignum() {
        return $($("1","-1"),
                $("-1","1"),
                $("0","0"),
                $("-1/2","1/2"),
                $("1/2","-1/2"));
    }

    @Test
    @Parameters(method="inputsChangeSignum")
    public void changeSignum1Test(String _input, String _expected) {
        Rate rate_ = new Rate(_input);
        rate_.changeSignum();
        assertEq(new Rate(_expected),rate_);
    }

    Object[] inputsLl() {
        return $($("1",1L),
                $("-1",-1L),
                $("0",0L),
                $("-1/2",-1L),
                $("1/2",0L),
                $("-1234567890",-234567890L),
                $("1234567890",234567890L),
                $("-12345678901234567890",-234567890L),
                $("12345678901234567890",234567890L),
                $("-3/2",-2L),
                $("3/2",1L));
    }

    @Test
    @Parameters(method="inputsLl")
    public void ll1Test(String _input, long _expected) {
        Rate rate_ = new Rate(_input);
        assertEq(_expected,rate_.ll());
    }

    Object[] inputsEnt() {
        return $($("1","1"),
                $("-1","-1"),
                $("0","0"),
                $("-1/2","0"),
                $("1/2","0"),
                $("-1234567890","-1234567890"),
                $("1234567890","1234567890"),
                $("-12345678901234567890","-12345678901234567890"),
                $("12345678901234567890","12345678901234567890"),
                $("-3/2","-1"),
                $("3/2","1"));
    }

    @Test
    @Parameters(method="inputsEnt")
    public void toLgInt1Test(String _input, String _expected) {
        Rate rate_ = new Rate(_input);
        assertEq(new LgInt(_expected),rate_.toLgInt());
    }

    Object[] inputsStrPlusPetit() {
        return $($("0","1",true),
                $("1","0",false),
                $("0","0",false),
                $("1","1",false),
                $("-1","0",true),
                $("0","-1",false),
                $("-1","-1",false),
                $("3","1234567890",true),
                $("-3","1234567890",true),
                $("-1234567890","3",true),
                $("-1234567890","-3",true),
                $("1234567890","3",false),
                $("1234567890","-3",false),
                $("3","-1234567890",false),
                $("-3","-1234567890",false),
                $("2/3","1/2",false),
                $("1/2","2/3",true),
                $("-1/2","-2/3",false),
                $("-2/3","-1/2",true));
    }

    @Test
    @Parameters(method="inputsStrPlusPetit")
    public void strLower1Test(String _one, String _two, boolean _expected) {
        Rate rateOne_ = new Rate(_one);
        Rate rateTwo_ = new Rate(_two);
        assertEq(_expected, Rate.strLower(rateOne_, rateTwo_));
    }

    Object[] inputsStrPlusGrand() {
        return $($("0","1",false),
                $("1","0",true),
                $("0","0",false),
                $("1","1",false),
                $("-1","0",false),
                $("0","-1",true),
                $("-1","-1",false),
                $("3","1234567890",false),
                $("-3","1234567890",false),
                $("-1234567890","3",false),
                $("-1234567890","-3",false),
                $("1234567890","3",true),
                $("1234567890","-3",true),
                $("3","-1234567890",true),
                $("-3","-1234567890",true),
                $("2/3","1/2",true),
                $("1/2","2/3",false),
                $("-1/2","-2/3",true),
                $("-2/3","-1/2",false));
    }

    @Test
    @Parameters(method="inputsStrPlusGrand")
    public void strGreater1Test(String _one, String _two, boolean _expected) {
        Rate rateOne_ = new Rate(_one);
        Rate rateTwo_ = new Rate(_two);
        assertEq(_expected, Rate.strGreater(rateOne_, rateTwo_));
    }

    Object[] inputsInfEg() {
        return $($("0","1",true),
                $("1","0",false),
                $("0","0",true),
                $("1","1",true),
                $("-1","0",true),
                $("0","-1",false),
                $("-1","-1",true),
                $("3","1234567890",true),
                $("-3","1234567890",true),
                $("-1234567890","3",true),
                $("-1234567890","-3",true),
                $("1234567890","3",false),
                $("1234567890","-3",false),
                $("3","-1234567890",false),
                $("-3","-1234567890",false),
                $("2/3","1/2",false),
                $("1/2","2/3",true),
                $("-1/2","-2/3",false),
                $("-2/3","-1/2",true));
    }

    @Test
    @Parameters(method="inputsInfEg")
    public void lowerEq1Test(String _one, String _two, boolean _expected) {
        Rate rateOne_ = new Rate(_one);
        Rate rateTwo_ = new Rate(_two);
        assertEq(_expected, Rate.lowerEq(rateOne_, rateTwo_));
    }

    Object[] inputsSupEg() {
        return $($("0","1",false),
                $("1","0",true),
                $("0","0",true),
                $("1","1",true),
                $("-1","0",false),
                $("0","-1",true),
                $("-1","-1",true),
                $("3","1234567890",false),
                $("-3","1234567890",false),
                $("-1234567890","3",false),
                $("-1234567890","-3",false),
                $("1234567890","3",true),
                $("1234567890","-3",true),
                $("3","-1234567890",true),
                $("-3","-1234567890",true),
                $("2/3","1/2",true),
                $("1/2","2/3",false),
                $("-1/2","-2/3",true),
                $("-2/3","-1/2",false));
    }

    @Test
    @Parameters(method="inputsSupEg")
    public void greaterEq1Test(String _one, String _two, boolean _expected) {
        Rate rateOne_ = new Rate(_one);
        Rate rateTwo_ = new Rate(_two);
        assertEq(_expected, Rate.greaterEq(rateOne_, rateTwo_));
    }

    Object[] inputsPlus() {
        return $($("0","20","20"),
                $("1","20","21"),
                $("-1","20","19"),
                $("1","-20","-19"),
                $("-1","-20","-21"),
                $("1234567890","11234567890","12469135780"),
                $("12345678901234567890","11234567890","12345678912469135780"),
                $("1/6","3/4","11/12"),
                $("1/2","2/3","7/6"),
                $("-1/2","2/3","1/6"),
                $("1/2","-2/3","-1/6"),
                $("-1/2","-2/3","-7/6"));
    }

    @Test
    @Parameters(method="inputsPlus")
    public void plus1Test(String _one, String _two, String _expected) {
        Rate rateOne_ = new Rate(_one);
        Rate rateTwo_ = new Rate(_two);
        assertEq(new Rate(_expected), Rate.plus(rateOne_, rateTwo_));
    }

    Object[] inputsOppose() {
        return $($("1","-1"),
                $("-1","1"),
                $("0","0"),
                $("-1/2","1/2"),
                $("1/2","-1/2"),
                $("-1234567890","1234567890"),
                $("1234567890","-1234567890"),
                $("-12345678901234567890","12345678901234567890"),
                $("12345678901234567890","-12345678901234567890"),
                $("-3/2","3/2"),
                $("3/2","-3/2"));
    }

    @Test
    @Parameters(method="inputsOppose")
    public void opposNb1Test(String _input, String _expected) {
        Rate rate_ = new Rate(_input);
        assertEq(new Rate(_expected), rate_.opposNb());
    }

    Object[] inputsAbsNb() {
        return $($("1","1"),
                $("-1","1"),
                $("0","0"),
                $("-1/2","1/2"),
                $("1/2","1/2"),
                $("-1234567890","1234567890"),
                $("1234567890","1234567890"),
                $("-12345678901234567890","12345678901234567890"),
                $("12345678901234567890","12345678901234567890"),
                $("-3/2","3/2"),
                $("3/2","3/2"));
    }

    @Test
    @Parameters(method="inputsAbsNb")
    public void absNb1Test(String _input, String _expected) {
        Rate rate_ = new Rate(_input);
        assertEq(new Rate(_expected), rate_.absNb());
    }

    Object[] inputsMoins() {
        return $($("0","20","-20"),
                $("1","20","-19"),
                $("-1","20","-21"),
                $("1","-20","21"),
                $("-1","-20","19"),
                $("1234567890","11234567890","-10000000000"),
                $("12345678901234567890","11234567890","12345678890000000000"),
                $("1/6","3/4","-7/12"),
                $("1/2","2/3","-1/6"),
                $("-1/2","2/3","-7/6"),
                $("1/2","-2/3","7/6"),
                $("-1/2","-2/3","1/6"));
    }

    @Test
    @Parameters(method="inputsMoins")
    public void minus1Test(String _one, String _two, String _expected) {
        Rate rateOne_ = new Rate(_one);
        Rate rateTwo_ = new Rate(_two);
        assertEq(new Rate(_expected), Rate.minus(rateOne_, rateTwo_));
    }

    Object[] inputsFois() {
        return $($("0","20","0"),
                $("1","20","20"),
                $("-1","20","-20"),
                $("1","-20","-20"),
                $("-1","-20","20"),
                $("1234567890","11234567890","13869836775019052100"),
                $("1/6","3/4","1/8"),
                $("1/2","2/3","1/3"),
                $("-1/2","2/3","-1/3"),
                $("1/2","-2/3","-1/3"),
                $("-1/2","-2/3","1/3"));
    }

    @Test
    @Parameters(method="inputsFois")
    public void multiply1Test(String _one, String _two, String _expected) {
        Rate rateOne_ = new Rate(_one);
        Rate rateTwo_ = new Rate(_two);
        assertEq(new Rate(_expected), Rate.multiply(rateOne_, rateTwo_));
    }

    Object[] inputsDivision() {
        return $($("0","20","0"),
                $("1","20","1/20"),
                $("-1","20","-1/20"),
                $("1","-20","-1/20"),
                $("-1","-20","1/20"),
                $("1234567890","11234567890","123456789/1123456789"),
                $("11234567890","1234567890","1123456789/123456789"),
                $("12345678901234567890","11234567890","1234567890123456789/1123456789"),
                $("11234567890","12345678901234567890","1123456789/1234567890123456789"),
                $("1/6","3/4","2/9"),
                $("1/2","2/3","3/4"),
                $("-1/2","2/3","-3/4"),
                $("1/2","-2/3","-3/4"),
                $("-1/2","-2/3","3/4"));
    }

    @Test
    @Parameters(method="inputsDivision")
    public void divide1Test(String _one, String _two, String _expected) {
        Rate rateOne_ = new Rate(_one);
        Rate rateTwo_ = new Rate(_two);
        assertEq(new Rate(_expected), Rate.divide(rateOne_, rateTwo_));
    }

    @Test(expected=BadDivisionException.class)
    public void invertNb1FailTest() {
        Rate nb_ = Rate.zero();
        nb_.invertNb();
    }

    @Test(expected=BadDivisionException.class)
    public void inv1FailTest() {
        Rate.zero().inv();
    }

    Object[] inputsInv() {
        return $($("1","1"),
                $("-1","-1"),
                $("-1/2","-2"),
                $("1/2","2"),
                $("2","1/2"),
                $("-2","-1/2"));
    }

    @Test
    @Parameters(method="inputsInv")
    public void inv1Test(String _input, String _expected) {
        Rate rate_ = new Rate(_input);
        assertEq(new Rate(_expected), rate_.inv());
    }

    Object[] inputsPuissance() {
        return $($("0", "0", "1"),
                $("0", "20", "0"),
                $("0", "1/2", "0"),
                $("1", "20", "1"),
                $("-1", "20", "1"),
                $("-1", "19", "-1"),
                $("2", "3", "8"),
                $("3", "2", "9"),
                $("1234567890", "0", "1"),
                $("5", "-1", "1/5"),
                $("-5", "-1", "-1/5"),
                $("4", "1/2", "2"),
                $("5", "1/2", "2"),
                $("4", "-1/2", "1/2"),
                $("4/9", "1/2", "2/3"),
                $("27", "-2/3", "1/9"),
                $("27", "2/3", "9"),
                $("32", "-1/5", "1/2"),
                $("32", "1/5", "2"),
                $("-32", "1/5", "-2"),
                $("-32", "2/5", "4"),
                $("-16", "1/4", "2"));
    }

    @Test
    @Parameters(method="inputsPuissance")
    public void powNb1Test(String _one, String _two, String _expected) {
        Rate rateOne_ = new Rate(_one);
        Rate rateTwo_ = new Rate(_two);
        assertEq(new Rate(_expected), Rate.powNb(rateOne_, rateTwo_));
    }

    @Test
    public void proba1Test(){
        EqList<Rate> probas_ = new EqList<Rate>();
        CustList<EqList<Rate>> tableProbas_ = new CustList<EqList<Rate>>();
        for(long t=1;t<=18;t++) {
            LgInt casFavorables_ = LgInt.zero();
            for(long i=0;i<t;i++) {
                casFavorables_.addNb(LgInt.multiply(LgInt.among(new LgInt(i), new LgInt(18)), LgInt.among(new LgInt(t-i-1), new LgInt(18))));
            }
            probas_.add(new Rate(casFavorables_,LgInt.among(new LgInt(t-1), new LgInt(53))));
        }
        tableProbas_.add(probas_);
        for(long n=0;n<18;n++) {
            EqList<Rate> probasLoc_ = tableProbas_.get((int)n);
            EqList<Rate> probasAjoutees_ = new EqList<Rate>();
            for(int i=0;i<=n;i++) {
                probasAjoutees_.add(Rate.zero());
            }
            for(long t=n+1;t<=18;t++) {
                Rate coeff_ = Rate.divide(Rate.multiply(new Rate(t), new Rate(18-n-1)), Rate.multiply(new Rate(n+1), new Rate(54-t)));
                probasAjoutees_.add(Rate.multiply(coeff_, probasLoc_.get((int) t-1)));
            }
            tableProbas_.add(probasAjoutees_);
        }
        EqList<Rate> sumColumns_ = new EqList<Rate>();
        for(short n=0;n<19;n++) {
            Rate rate_ = Rate.zero();
            for(short p=0;p<19;p++) {
                if (n >= tableProbas_.get(p).size()) {
                    continue;
                }
                rate_.addNb(tableProbas_.get(p).get(n));
            }
            sumColumns_.add(rate_);
        }
//        List<List<Rate>> cumulsProbas_ = new List<List<Rate>>();
//        for(int i=tableProbas_.size()-1;i>-1;i--){
//            List<Rate> probasLoc_ = tableProbas_.get((int)i);
//            List<Rate> probasAjoutees_ = new List<Rate>();
//            for(int j=probasLoc_.size()-1;j>-1;j--){
//                probasAjoutees_
//            }
//        }
    }

    Object[] inputsEvaluate() {
        return $($("0", 0, "0"),
            $("0", 1, "0"),
            $("1", 0, "1"),
            $("1", 1, "1.E0"),
            $("2", 0, "2"),
            $("2", 1, "2.E0"),
            $("2", 2, "2.0E0"),
            $("3/2", 2, "1.5E0"),
            $("1/2", 2, "5.0E-1"),
            $("-3/2", 2, "-1.5E0"),
            $("-30/2", 2, "-1.5E1"),
            $("-300001/20000", 2, "-1.5E1"));
    }

    @Test
    @Parameters(method="inputsEvaluate")
    public void evaluate1Test(String _input, int _nbDigits, String _expected) {
        Rate rate_ = new Rate(_input);
        assertEq(_expected,rate_.evaluate(_nbDigits));
    }


    Object[] inputsEvaluatePoint() {
        return $($("0", 0, "0"),
            $("0", 1, "0"),
            $("1", 0, "1"),
            $("1", 1, "1"),
            $("2", 0, "2"),
            $("2", 1, "2"),
            $("2", 2, "2"),
            $("3/2", 2, "1.50"),
            $("1/2", 2, "0.50"),
            $("-1/2", 2, "-0.50"),
            $("-3/2", 2, "-1.50"),
            $("-3/20", 2, "-0.15"),
            $("-3/20", 0, "0"),
            $("3/20", 0, "0"),
            $("-3/200", 2, "-0.01"),
            $("-3/200", 1, "0.0"),
            $("-30/2", 2, "-15"),
            $("30/2", 2, "15"),
            $("-31/2", 2, "-15.50"),
            $("31/2", 2, "15.50"),
            $("-31001/2000", 2, "-15.50"),
            $("31001/2000", 2, "15.50"),
            $("-31001/2000", 1, "-15.5"),
            $("31001/2000", 1, "15.5"),
            $("-31/2", 6, "-15.500000"),
            $("31/2", 6, "15.500000"));
    }

    @Test
    @Parameters(method="inputsEvaluatePoint")
    public void evaluatePoint1Test(String _input, int _nbDigits, String _expected) {
        Rate rate_ = new Rate(_input);
        assertEq(_expected,rate_.evaluatePoint(_nbDigits));
    }

    @Test(expected=NegatifExposantException.class)
    public void evaluate1FailTest() {
        Rate rate_ = new Rate("1");
        assertEq("1",rate_.evaluate(-1));
    }

    @Test(expected=NegatifExposantException.class)
    public void evaluatePoint1FailTest() {
        Rate rate_ = new Rate("1");
        assertEq("1",rate_.evaluatePoint(-1));
    }

    @Test
    public void greaterThanOne1Test() {
        assertTrue(new Rate("3/2").greaterThanOne());
        assertTrue(!new Rate("-3/2").greaterThanOne());
        assertTrue(!new Rate("1").greaterThanOne());
    }

    @Test
    public void greaterOrEqualsOneT1est() {
        assertTrue(new Rate("3/2").greaterOrEqualsOne());
        assertTrue(!new Rate("-3/2").greaterOrEqualsOne());
        assertTrue(new Rate("1").greaterOrEqualsOne());
    }

    @Test
    public void lowerThanOne1Test() {
        assertTrue(!new Rate("3/2").lowerThanOne());
        assertTrue(new Rate("-3/2").lowerThanOne());
        assertTrue(!new Rate("1").lowerThanOne());
    }

    @Test
    public void lowerOrEqualsOnee1Test() {
        assertTrue(!new Rate("3/2").lowerOrEqualsOne());
        assertTrue(new Rate("-3/2").lowerOrEqualsOne());
        assertTrue(new Rate("1").lowerOrEqualsOne());
    }

    @Test
    public void percent1Test() {
        assertEq(new LgInt("50"),new Rate("1/2").percent());
    }

    @Test
    public void percent2Test() {
        assertEq(new LgInt("100"),new Rate("1").percent());
    }

    @Test
    public void percent3Test() {
        assertEq(new LgInt("33"),new Rate("1/3").percent());
    }
}
