package code.maths;
import static code.maths.EquallableMathUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.util.CustList;
import code.util.EqList;


public class RateTest {
    @Test
    public void new_Rate_1(){
        Rate rate_ = Rate.zero();
        assertEq(new LgInt(0), rate_.getNumerator());
        assertEq(new LgInt(1), rate_.getDenominator());
        assertTrue(rate_.isZeroOrGt());
        assertTrue(rate_.isZero());
    }
    @Test
    public void new_Rate_long_1Test(){
        Rate rate_ = new Rate(1L);
        assertEq(new LgInt(1L), rate_.getNumerator());
        assertEq(new LgInt(1L), rate_.getDenominator());
        assertEq(true,rate_.isZeroOrGt());
        assertEq(false,rate_.isZero());
    }
    @Test
    public void new_Rate_long_2Test(){
        Rate rate_ = new Rate(0L);
        assertEq(new LgInt(0L), rate_.getNumerator());
        assertEq(new LgInt(1L), rate_.getDenominator());
        assertEq(true,rate_.isZeroOrGt());
        assertEq(true,rate_.isZero());
    }
    @Test
    public void new_Rate_long_3Test(){
        Rate rate_ = new Rate(-1L);
        assertEq(new LgInt(-1L), rate_.getNumerator());
        assertEq(new LgInt(1L), rate_.getDenominator());
        assertEq(false,rate_.isZeroOrGt());
        assertEq(false,rate_.isZero());
    }
    @Test
    public void new_Rate_long_4Test(){
        Rate rate_ = new Rate(1234567890L);
        assertEq(new LgInt(1234567890L), rate_.getNumerator());
        assertEq(new LgInt(1L), rate_.getDenominator());
        assertEq(true,rate_.isZeroOrGt());
        assertEq(false,rate_.isZero());
    }
    @Test
    public void new_Rate_long_5Test(){
        Rate rate_ = new Rate(-1234567890L);
        assertEq(new LgInt(-1234567890L), rate_.getNumerator());
        assertEq(new LgInt(1L), rate_.getDenominator());
        assertEq(false,rate_.isZeroOrGt());
        assertEq(false,rate_.isZero());
    }
    @Test
    public void new_Rate_LgInt_1Test(){
        Rate rate_ = new Rate(new LgInt(1L));
        assertEq(new LgInt(1L), rate_.getNumerator());
        assertEq(new LgInt(1L), rate_.getDenominator());
        assertEq(true,rate_.isZeroOrGt());
        assertEq(false,rate_.isZero());
    }
    @Test
    public void new_Rate_LgInt_2Test(){
        Rate rate_ = new Rate(new LgInt(0L));
        assertEq(new LgInt(0L), rate_.getNumerator());
        assertEq(new LgInt(1L), rate_.getDenominator());
        assertEq(true,rate_.isZeroOrGt());
        assertEq(true,rate_.isZero());
    }
    @Test
    public void new_Rate_LgInt_3Test(){
        Rate rate_ = new Rate(new LgInt(-1L));
        assertEq(new LgInt(-1L), rate_.getNumerator());
        assertEq(new LgInt(1L), rate_.getDenominator());
        assertEq(false,rate_.isZeroOrGt());
        assertEq(false,rate_.isZero());
    }
    @Test
    public void new_Rate_LgInt_4Test(){
        Rate rate_ = new Rate(new LgInt(1234567890L));
        assertEq(new LgInt(1234567890L), rate_.getNumerator());
        assertEq(new LgInt(1L), rate_.getDenominator());
        assertEq(true,rate_.isZeroOrGt());
        assertEq(false,rate_.isZero());
    }
    @Test
    public void new_Rate_LgInt_5Test(){
        Rate rate_ = new Rate(new LgInt(-1234567890L));
        assertEq(new LgInt(-1234567890L), rate_.getNumerator());
        assertEq(new LgInt(1L), rate_.getDenominator());
        assertEq(false,rate_.isZeroOrGt());
        assertEq(false,rate_.isZero());
    }
    @Test
    public void new_Rate_LgInt_LgInt_1Test(){
        Rate rate_ = new Rate(new LgInt(1L), new LgInt(2L));
        assertEq(new LgInt(1L), rate_.getNumerator());
        assertEq(new LgInt(2L), rate_.getDenominator());
        assertEq(true,rate_.isZeroOrGt());
    }
    @Test
    public void new_Rate_LgInt_LgInt_2Test(){
        Rate rate_ = new Rate(new LgInt(-1L), new LgInt(2L));
        assertEq(new LgInt(-1L), rate_.getNumerator());
        assertEq(new LgInt(2L), rate_.getDenominator());
        assertEq(false,rate_.isZeroOrGt());
    }
    @Test
    public void new_Rate_LgInt_LgInt_3Test(){
        Rate rate_ = new Rate(new LgInt(1L), new LgInt(-2L));
        assertEq(new LgInt(-1L), rate_.getNumerator());
        assertEq(new LgInt(2L), rate_.getDenominator());
        assertEq(false,rate_.isZeroOrGt());
    }
    @Test
    public void new_Rate_LgInt_LgInt_4Test(){
        Rate rate_ = new Rate(new LgInt(-1L), new LgInt(-2L));
        assertEq(new LgInt(1L), rate_.getNumerator());
        assertEq(new LgInt(2L), rate_.getDenominator());
        assertEq(true,rate_.isZeroOrGt());
    }
    @Test
    public void new_Rate_LgInt_LgInt_5Test(){
        Rate rate_ = new Rate(new LgInt(2L), new LgInt(4L));
        assertEq(new LgInt(1L), rate_.getNumerator());
        assertEq(new LgInt(2L), rate_.getDenominator());
        assertEq(true,rate_.isZeroOrGt());
    }
    @Test
    public void new_Rate_long_long_1Test(){
        Rate rate_ = new Rate(1L, 2L);
        assertEq(new LgInt(1L), rate_.getNumerator());
        assertEq(new LgInt(2L), rate_.getDenominator());
        assertEq(true,rate_.isZeroOrGt());
    }
    @Test
    public void new_Rate_long_long_2Test(){
        Rate rate_ = new Rate(-1L, 2L);
        assertEq(new LgInt(-1L), rate_.getNumerator());
        assertEq(new LgInt(2L), rate_.getDenominator());
        assertEq(false,rate_.isZeroOrGt());
    }
    @Test
    public void new_Rate_long_long_3Test(){
        Rate rate_ = new Rate(1L, -2L);
        assertEq(new LgInt(-1L), rate_.getNumerator());
        assertEq(new LgInt(2L), rate_.getDenominator());
        assertEq(false,rate_.isZeroOrGt());
    }
    @Test
    public void new_Rate_long_long_4Test(){
        Rate rate_ = new Rate(-1L, -2L);
        assertEq(new LgInt(1L), rate_.getNumerator());
        assertEq(new LgInt(2L), rate_.getDenominator());
        assertEq(true,rate_.isZeroOrGt());
    }
    @Test
    public void new_Rate_long_long_5Test(){
        Rate rate_ = new Rate(2L, 4L);
        assertEq(new LgInt(1L), rate_.getNumerator());
        assertEq(new LgInt(2L), rate_.getDenominator());
        assertEq(true,rate_.isZeroOrGt());
    }
    @Test
    public void new_Rate_String_1Test(){
        Rate rate_ = new Rate("1");
        assertEq(new LgInt(1L), rate_.getNumerator());
        assertEq(new LgInt(1L), rate_.getDenominator());
        assertEq(true,rate_.isZeroOrGt());
        assertEq(false,rate_.isZero());
    }
    @Test
    public void new_Rate_String_2Test(){
        Rate rate_ = new Rate("-1");
        assertEq(new LgInt(-1L), rate_.getNumerator());
        assertEq(new LgInt(1L), rate_.getDenominator());
        assertEq(false,rate_.isZeroOrGt());
        assertEq(false,rate_.isZero());
    }
    @Test
    public void new_Rate_String_3Test(){
        Rate rate_ = new Rate("0");
        assertEq(new LgInt(0L), rate_.getNumerator());
        assertEq(new LgInt(1L), rate_.getDenominator());
        assertEq(true,rate_.isZeroOrGt());
        assertEq(true,rate_.isZero());
    }
    @Test
    public void new_Rate_String_4Test(){
        Rate rate_ = new Rate("01");
        assertEq(new LgInt(1L), rate_.getNumerator());
        assertEq(new LgInt(1L), rate_.getDenominator());
        assertEq(true,rate_.isZeroOrGt());
        assertEq(false,rate_.isZero());
    }
    @Test
    public void new_Rate_String_5Test(){
        Rate rate_ = new Rate("1234567890");
        assertEq(new LgInt(1234567890L), rate_.getNumerator());
        assertEq(new LgInt(1L), rate_.getDenominator());
        assertEq(true,rate_.isZeroOrGt());
        assertEq(false,rate_.isZero());
    }
    @Test
    public void new_Rate_String_6Test(){
        Rate rate_ = new Rate("-1234567890");
        assertEq(new LgInt(-1234567890L), rate_.getNumerator());
        assertEq(new LgInt(1L), rate_.getDenominator());
        assertEq(false,rate_.isZeroOrGt());
        assertEq(false,rate_.isZero());
    }
    @Test
    public void new_Rate_String_7Test(){
        Rate rate_ = new Rate("1/2");
        assertEq(new LgInt(1L), rate_.getNumerator());
        assertEq(new LgInt(2L), rate_.getDenominator());
        assertEq(true,rate_.isZeroOrGt());
        assertEq(false,rate_.isZero());
    }
    @Test
    public void new_Rate_String_8Test(){
        Rate rate_ = new Rate("2/4");
        assertEq(new LgInt(1L), rate_.getNumerator());
        assertEq(new LgInt(2L), rate_.getDenominator());
        assertEq(true,rate_.isZeroOrGt());
        assertEq(false,rate_.isZero());
    }
    @Test
    public void new_Rate_String_9Test(){
        Rate rate_ = new Rate("-1/2");
        assertEq(new LgInt(-1L), rate_.getNumerator());
        assertEq(new LgInt(2L), rate_.getDenominator());
        assertEq(false,rate_.isZeroOrGt());
        assertEq(false,rate_.isZero());
    }
    @Test
    public void new_Rate_String_10Test(){
        Rate rate_ = new Rate("1/-2");
        assertEq(new LgInt(-1L), rate_.getNumerator());
        assertEq(new LgInt(2L), rate_.getDenominator());
        assertEq(false,rate_.isZeroOrGt());
        assertEq(false,rate_.isZero());
    }
    @Test
    public void new_Rate_String_11Test(){
        Rate rate_ = new Rate("-1/-2");
        assertEq(new LgInt(1L), rate_.getNumerator());
        assertEq(new LgInt(2L), rate_.getDenominator());
        assertEq(true,rate_.isZeroOrGt());
        assertEq(false,rate_.isZero());
    }
    @Test
    public void new_Rate_String_12Test(){
        Rate rate_ = new Rate(".1");
        assertEq(new LgInt(1L), rate_.getNumerator());
        assertEq(new LgInt(10L), rate_.getDenominator());
        assertEq(true,rate_.isZeroOrGt());
        assertEq(false,rate_.isZero());
    }
    @Test
    public void new_Rate_String_13Test(){
        Rate rate_ = new Rate("-.1");
        assertEq(new LgInt(-1L), rate_.getNumerator());
        assertEq(new LgInt(10L), rate_.getDenominator());
        assertEq(false,rate_.isZeroOrGt());
        assertEq(false,rate_.isZero());
    }
    @Test
    public void new_Rate_String_14Test(){
        Rate rate_ = new Rate("1.");
        assertEq(new LgInt(1L), rate_.getNumerator());
        assertEq(new LgInt(1L), rate_.getDenominator());
        assertEq(true,rate_.isZeroOrGt());
        assertEq(false,rate_.isZero());
    }
    @Test
    public void new_Rate_String_15Test(){
        Rate rate_ = new Rate("1.2");
        assertEq(new LgInt(6L), rate_.getNumerator());
        assertEq(new LgInt(5L), rate_.getDenominator());
        assertEq(true,rate_.isZeroOrGt());
        assertEq(false,rate_.isZero());
    }
    @Test
    public void new_Rate_String_16Test(){
        Rate rate_ = new Rate("-1.2");
        assertEq(new LgInt(-6L), rate_.getNumerator());
        assertEq(new LgInt(5L), rate_.getDenominator());
        assertEq(false,rate_.isZeroOrGt());
        assertEq(false,rate_.isZero());
    }
    @Test
    public void new_Rate_String_17Test(){
        Rate rate_ = new Rate("-1.");
        assertEq(new LgInt(-1L), rate_.getNumerator());
        assertEq(new LgInt(1L), rate_.getDenominator());
        assertEq(false,rate_.isZeroOrGt());
        assertEq(false,rate_.isZero());
    }
    @Test
    public void new_Rate_String_18Test(){
        Rate rate_ = new Rate("1/0");
        assertEq(new LgInt(0L), rate_.getNumerator());
        assertEq(new LgInt(1L), rate_.getDenominator());
        assertEq(true,rate_.isZeroOrGt());
        assertEq(true,rate_.isZero());
    }
    @Test
    public void isValid1(){
        assertTrue(Rate.isValid("1"));
    }
    @Test
    public void isValid2(){
        assertTrue(Rate.isValid("1/2"));
    }
    @Test
    public void isValid3(){
        assertTrue(Rate.isValid("1.2"));
    }
    @Test
    public void isValid4(){
        assertTrue(!Rate.isValid("1/0"));
    }
    @Test
    public void isValid5(){
        assertTrue(!Rate.isValid(""));
    }
    @Test
    public void isValid6(){
        assertTrue(!Rate.isValid(null));
    }
    @Test
    public void toString1Test(){
        Rate rate_ = new Rate("1");
        String str_ = rate_.toNumberString();
        assertEq("1", str_);
    }
    @Test
    public void toString2Test(){
        Rate rate_ = new Rate("-1");
        String str_ = rate_.toNumberString();
        assertEq("-1", str_);
    }
    @Test
    public void toString3Test(){
        Rate rate_ = new Rate("-0");
        String str_ = rate_.toNumberString();
        assertEq("0", str_);
    }
    @Test
    public void toString4Test(){
        Rate rate_ = new Rate("0");
        String str_ = rate_.toNumberString();
        assertEq("0", str_);
    }
    @Test
    public void toString5Test(){
        Rate rate_ = new Rate("1234567890");
        String str_ = rate_.toNumberString();
        assertEq("1234567890", str_);
    }
    @Test
    public void toString6Test(){
        Rate rate_ = new Rate("-1234567890");
        String str_ = rate_.toNumberString();
        assertEq("-1234567890", str_);
    }
    @Test
    public void toString7Test(){
        Rate rate_ = new Rate("12345678901234567890");
        String str_ = rate_.toNumberString();
        assertEq("12345678901234567890", str_);
    }
    @Test
    public void toString8Test(){
        Rate rate_ = new Rate("-12345678901234567890");
        String str_ = rate_.toNumberString();
        assertEq("-12345678901234567890", str_);
    }
    @Test
    public void toString9Test(){
        Rate rate_ = new Rate("1/2");
        String str_ = rate_.toNumberString();
        assertEq("1/2", str_);
    }
    @Test
    public void toString10Test(){
        Rate rate_ = new Rate("-1/2");
        String str_ = rate_.toNumberString();
        assertEq("-1/2", str_);
    }
    @Test
    public void toString11Test(){
        Rate rate_ = new Rate("2/4");
        String str_ = rate_.toNumberString();
        assertEq("1/2", str_);
    }
    @Test
    public void toString12Test(){
        Rate rate_ = new Rate("-2/4");
        String str_ = rate_.toNumberString();
        assertEq("-1/2", str_);
    }
    @Test
    public void eq1Test(){
        Rate rateOne_ = new Rate("1");
        Rate rateTwo_ = new Rate("1");
        assertEq(true, rateOne_.eq(rateTwo_));
    }
    @Test
    public void eq2Test(){
        Rate rateOne_ = new Rate("-1");
        Rate rateTwo_ = new Rate("1");
        assertEq(false, rateOne_.eq(rateTwo_));
    }
    @Test
    public void eq3Test(){
        Rate rateOne_ = new Rate("1");
        Rate rateTwo_ = new Rate("-1");
        assertEq(false, rateOne_.eq(rateTwo_));
    }
    @Test
    public void eq4Test(){
        Rate rateOne_ = new Rate("-1");
        Rate rateTwo_ = new Rate("-1");
        assertEq(true, rateOne_.eq(rateTwo_));
    }
    @Test
    public void eq5Test(){
        Rate rateOne_ = new Rate("1/2");
        Rate rateTwo_ = new Rate("1/4");
        assertEq(false, rateOne_.eq(rateTwo_));
    }
    @Test
    public void eq6Test(){
        Rate rateOne_ = new Rate("1/4");
        Rate rateTwo_ = new Rate("1/2");
        assertEq(false, rateOne_.eq(rateTwo_));
    }
    @Test
    public void eq7Test(){
        Rate rateOne_ = new Rate("1/2");
        Rate rateTwo_ = new Rate("1/2");
        assertEq(true, rateOne_.eq(rateTwo_));
    }
    @Test
    public void eq8Test(){
        Rate rateOne_ = new Rate("1/2");
        Rate rateTwo_ = new Rate("3/2");
        assertEq(false, rateOne_.eq(rateTwo_));
    }
    @Test
    public void eq9Test(){
        Rate rateOne_ = new Rate("3/2");
        Rate rateTwo_ = new Rate("1/2");
        assertEq(false, rateOne_.eq(rateTwo_));
    }
    @Test
    public void different1Test(){
        Rate rateOne_ = new Rate("1");
        Rate rateTwo_ = new Rate("1");
        assertEq(false, Rate.different(rateOne_, rateTwo_));
    }
    @Test
    public void different2Test(){
        Rate rateOne_ = new Rate("-1");
        Rate rateTwo_ = new Rate("1");
        assertEq(true, Rate.different(rateOne_, rateTwo_));
    }
    @Test
    public void different3Test(){
        Rate rateOne_ = new Rate("1");
        Rate rateTwo_ = new Rate("-1");
        assertEq(true, Rate.different(rateOne_, rateTwo_));
    }
    @Test
    public void different4Test(){
        Rate rateOne_ = new Rate("-1");
        Rate rateTwo_ = new Rate("-1");
        assertEq(false, Rate.different(rateOne_, rateTwo_));
    }
    @Test
    public void different5Test(){
        Rate rateOne_ = new Rate("1/2");
        Rate rateTwo_ = new Rate("1/4");
        assertEq(true, Rate.different(rateOne_, rateTwo_));
    }
    @Test
    public void different6Test(){
        Rate rateOne_ = new Rate("1/4");
        Rate rateTwo_ = new Rate("1/2");
        assertEq(true, Rate.different(rateOne_, rateTwo_));
    }
    @Test
    public void different7Test(){
        Rate rateOne_ = new Rate("1/2");
        Rate rateTwo_ = new Rate("1/2");
        assertEq(false, Rate.different(rateOne_, rateTwo_));
    }
    @Test
    public void different8Test(){
        Rate rateOne_ = new Rate("1/2");
        Rate rateTwo_ = new Rate("3/2");
        assertEq(true, Rate.different(rateOne_, rateTwo_));
    }
    @Test
    public void different9Test(){
        Rate rateOne_ = new Rate("3/2");
        Rate rateTwo_ = new Rate("1/2");
        assertEq(true, Rate.different(rateOne_, rateTwo_));
    }
    @Test
    public void isInteger1Test(){
        assertEq(true, new Rate("1").isInteger());
    }
    @Test
    public void isInteger2Test(){
        assertEq(true, new Rate("-1").isInteger());
    }
    @Test
    public void isInteger3Test(){
        assertEq(true, new Rate("0").isInteger());
    }
    @Test
    public void isInteger4Test(){
        assertEq(false, new Rate("-1/2").isInteger());
    }
    @Test
    public void isInteger5Test(){
        assertEq(false, new Rate("1/2").isInteger());
    }
    @Test
    public void changeSignum1Test(){
        Rate rate_ = new Rate("1");
        rate_.changeSignum();
        assertEq(new Rate("-1"),rate_);
    }
    @Test
    public void changeSignum2Test(){
        Rate rate_ = new Rate("-1");
        rate_.changeSignum();
        assertEq(new Rate("1"),rate_);
    }
    @Test
    public void changeSignum3Test(){
        Rate rate_ = new Rate("0");
        rate_.changeSignum();
        assertEq(new Rate("0"),rate_);
    }
    @Test
    public void changeSignum4Test(){
        Rate rate_ = new Rate("-1/2");
        rate_.changeSignum();
        assertEq(new Rate("1/2"),rate_);
    }
    @Test
    public void changeSignum5Test(){
        Rate rate_ = new Rate("1/2");
        rate_.changeSignum();
        assertEq(new Rate("-1/2"),rate_);
    }
    @Test
    public void ll1Test(){
        Rate rate_ = new Rate("1");
        assertEq(1L,rate_.ll());
    }
    @Test
    public void ll2Test(){
        Rate rate_ = new Rate("-1");
        assertEq(-1L,rate_.ll());
    }
    @Test
    public void ll3Test(){
        Rate rate_ = new Rate("0");
        assertEq(0L,rate_.ll());
    }
    @Test
    public void ll4Test(){
        Rate rate_ = new Rate("-1/2");
        assertEq(-1L,rate_.ll());
    }
    @Test
    public void ll5Test(){
        Rate rate_ = new Rate("1/2");
        assertEq(0L,rate_.ll());
    }
    @Test
    public void ll6Test(){
        Rate rate_ = new Rate("-1234567890");
        assertEq(-234567890L,rate_.ll());
    }
    @Test
    public void ll7Test(){
        Rate rate_ = new Rate("1234567890");
        assertEq(234567890L,rate_.ll());
    }
    @Test
    public void ll8Test(){
        Rate rate_ = new Rate("-12345678901234567890");
        assertEq(-234567890L,rate_.ll());
    }
    @Test
    public void ll9Test(){
        Rate rate_ = new Rate("12345678901234567890");
        assertEq(234567890L,rate_.ll());
    }
    @Test
    public void ll10Test(){
        Rate rate_ = new Rate("-3/2");
        assertEq(-2L,rate_.ll());
    }
    @Test
    public void ll11Test(){
        Rate rate_ = new Rate("3/2");
        assertEq(1L,rate_.ll());
    }
    @Test
    public void toLgInt1Test(){
        Rate rate_ = new Rate("1");
        assertEq(new LgInt("1"),rate_.toLgInt());
    }
    @Test
    public void toLgInt2Test(){
        Rate rate_ = new Rate("-1");
        assertEq(new LgInt("-1"),rate_.toLgInt());
    }
    @Test
    public void toLgInt3Test(){
        Rate rate_ = new Rate("0");
        assertEq(new LgInt("0"),rate_.toLgInt());
    }
    @Test
    public void toLgInt4Test(){
        Rate rate_ = new Rate("-1/2");
        assertEq(new LgInt("0"),rate_.toLgInt());
    }
    @Test
    public void toLgInt5Test(){
        Rate rate_ = new Rate("1/2");
        assertEq(new LgInt("0"),rate_.toLgInt());
    }
    @Test
    public void toLgInt6Test(){
        Rate rate_ = new Rate("-1234567890");
        assertEq(new LgInt("-1234567890"),rate_.toLgInt());
    }
    @Test
    public void toLgInt7Test(){
        Rate rate_ = new Rate("1234567890");
        assertEq(new LgInt("1234567890"),rate_.toLgInt());
    }
    @Test
    public void toLgInt8Test(){
        Rate rate_ = new Rate("-12345678901234567890");
        assertEq(new LgInt("-12345678901234567890"),rate_.toLgInt());
    }
    @Test
    public void toLgInt9Test(){
        Rate rate_ = new Rate("12345678901234567890");
        assertEq(new LgInt("12345678901234567890"),rate_.toLgInt());
    }
    @Test
    public void toLgInt10Test(){
        Rate rate_ = new Rate("-3/2");
        assertEq(new LgInt("-1"),rate_.toLgInt());
    }
    @Test
    public void toLgInt11Test(){
        Rate rate_ = new Rate("3/2");
        assertEq(new LgInt("1"),rate_.toLgInt());
    }
    @Test
    public void strLower1Test(){
        Rate rateOne_ = new Rate("0");
        Rate rateTwo_ = new Rate("1");
        assertEq(true, Rate.strLower(rateOne_, rateTwo_));
    }
    @Test
    public void strLower2Test(){
        Rate rateOne_ = new Rate("1");
        Rate rateTwo_ = new Rate("0");
        assertEq(false, Rate.strLower(rateOne_, rateTwo_));
    }
    @Test
    public void strLower3Test(){
        Rate rateOne_ = new Rate("0");
        Rate rateTwo_ = new Rate("0");
        assertEq(false, Rate.strLower(rateOne_, rateTwo_));
    }
    @Test
    public void strLower4Test(){
        Rate rateOne_ = new Rate("1");
        Rate rateTwo_ = new Rate("1");
        assertEq(false, Rate.strLower(rateOne_, rateTwo_));
    }
    @Test
    public void strLower5Test(){
        Rate rateOne_ = new Rate("-1");
        Rate rateTwo_ = new Rate("0");
        assertEq(true, Rate.strLower(rateOne_, rateTwo_));
    }
    @Test
    public void strLower6Test(){
        Rate rateOne_ = new Rate("0");
        Rate rateTwo_ = new Rate("-1");
        assertEq(false, Rate.strLower(rateOne_, rateTwo_));
    }
    @Test
    public void strLower7Test(){
        Rate rateOne_ = new Rate("-1");
        Rate rateTwo_ = new Rate("-1");
        assertEq(false, Rate.strLower(rateOne_, rateTwo_));
    }
    @Test
    public void strLower8Test(){
        Rate rateOne_ = new Rate("3");
        Rate rateTwo_ = new Rate("1234567890");
        assertEq(true, Rate.strLower(rateOne_, rateTwo_));
    }
    @Test
    public void strLower9Test(){
        Rate rateOne_ = new Rate("-3");
        Rate rateTwo_ = new Rate("1234567890");
        assertEq(true, Rate.strLower(rateOne_, rateTwo_));
    }
    @Test
    public void strLower10Test(){
        Rate rateOne_ = new Rate("-1234567890");
        Rate rateTwo_ = new Rate("3");
        assertEq(true, Rate.strLower(rateOne_, rateTwo_));
    }
    @Test
    public void strLower11Test(){
        Rate rateOne_ = new Rate("-1234567890");
        Rate rateTwo_ = new Rate("-3");
        assertEq(true, Rate.strLower(rateOne_, rateTwo_));
    }
    @Test
    public void strLower12Test(){
        Rate rateOne_ = new Rate("1234567890");
        Rate rateTwo_ = new Rate("3");
        assertEq(false, Rate.strLower(rateOne_, rateTwo_));
    }
    @Test
    public void strLower13Test(){
        Rate rateOne_ = new Rate("1234567890");
        Rate rateTwo_ = new Rate("-3");
        assertEq(false, Rate.strLower(rateOne_, rateTwo_));
    }
    @Test
    public void strLower14Test(){
        Rate rateOne_ = new Rate("3");
        Rate rateTwo_ = new Rate("-1234567890");
        assertEq(false, Rate.strLower(rateOne_, rateTwo_));
    }
    @Test
    public void strLower15Test(){
        Rate rateOne_ = new Rate("-3");
        Rate rateTwo_ = new Rate("-1234567890");
        assertEq(false, Rate.strLower(rateOne_, rateTwo_));
    }
    @Test
    public void strLower16Test(){
        Rate rateOne_ = new Rate("2/3");
        Rate rateTwo_ = new Rate("1/2");
        assertEq(false, Rate.strLower(rateOne_, rateTwo_));
    }
    @Test
    public void strLower17Test(){
        Rate rateOne_ = new Rate("1/2");
        Rate rateTwo_ = new Rate("2/3");
        assertEq(true, Rate.strLower(rateOne_, rateTwo_));
    }
    @Test
    public void strLower18Test(){
        Rate rateOne_ = new Rate("-1/2");
        Rate rateTwo_ = new Rate("-2/3");
        assertEq(false, Rate.strLower(rateOne_, rateTwo_));
    }
    @Test
    public void strLower19Test(){
        Rate rateOne_ = new Rate("-2/3");
        Rate rateTwo_ = new Rate("-1/2");
        assertEq(true, Rate.strLower(rateOne_, rateTwo_));
    }
    @Test
    public void strGreater1Test(){
        Rate rateOne_ = new Rate("0");
        Rate rateTwo_ = new Rate("1");
        assertEq(false, Rate.strGreater(rateOne_, rateTwo_));
    }
    @Test
    public void strGreater2Test(){
        Rate rateOne_ = new Rate("1");
        Rate rateTwo_ = new Rate("0");
        assertEq(true, Rate.strGreater(rateOne_, rateTwo_));
    }
    @Test
    public void strGreater3Test(){
        Rate rateOne_ = new Rate("0");
        Rate rateTwo_ = new Rate("0");
        assertEq(false, Rate.strGreater(rateOne_, rateTwo_));
    }
    @Test
    public void strGreater4Test(){
        Rate rateOne_ = new Rate("1");
        Rate rateTwo_ = new Rate("1");
        assertEq(false, Rate.strGreater(rateOne_, rateTwo_));
    }
    @Test
    public void strGreater5Test(){
        Rate rateOne_ = new Rate("-1");
        Rate rateTwo_ = new Rate("0");
        assertEq(false, Rate.strGreater(rateOne_, rateTwo_));
    }
    @Test
    public void strGreater6Test(){
        Rate rateOne_ = new Rate("0");
        Rate rateTwo_ = new Rate("-1");
        assertEq(true, Rate.strGreater(rateOne_, rateTwo_));
    }
    @Test
    public void strGreater7Test(){
        Rate rateOne_ = new Rate("-1");
        Rate rateTwo_ = new Rate("-1");
        assertEq(false, Rate.strGreater(rateOne_, rateTwo_));
    }
    @Test
    public void strGreater8Test(){
        Rate rateOne_ = new Rate("3");
        Rate rateTwo_ = new Rate("1234567890");
        assertEq(false, Rate.strGreater(rateOne_, rateTwo_));
    }
    @Test
    public void strGreater9Test(){
        Rate rateOne_ = new Rate("-3");
        Rate rateTwo_ = new Rate("1234567890");
        assertEq(false, Rate.strGreater(rateOne_, rateTwo_));
    }
    @Test
    public void strGreater10Test(){
        Rate rateOne_ = new Rate("-1234567890");
        Rate rateTwo_ = new Rate("3");
        assertEq(false, Rate.strGreater(rateOne_, rateTwo_));
    }
    @Test
    public void strGreater11Test(){
        Rate rateOne_ = new Rate("-1234567890");
        Rate rateTwo_ = new Rate("-3");
        assertEq(false, Rate.strGreater(rateOne_, rateTwo_));
    }
    @Test
    public void strGreater12Test(){
        Rate rateOne_ = new Rate("1234567890");
        Rate rateTwo_ = new Rate("3");
        assertEq(true, Rate.strGreater(rateOne_, rateTwo_));
    }
    @Test
    public void strGreater13Test(){
        Rate rateOne_ = new Rate("1234567890");
        Rate rateTwo_ = new Rate("-3");
        assertEq(true, Rate.strGreater(rateOne_, rateTwo_));
    }
    @Test
    public void strGreater14Test(){
        Rate rateOne_ = new Rate("3");
        Rate rateTwo_ = new Rate("-1234567890");
        assertEq(true, Rate.strGreater(rateOne_, rateTwo_));
    }
    @Test
    public void strGreater15Test(){
        Rate rateOne_ = new Rate("-3");
        Rate rateTwo_ = new Rate("-1234567890");
        assertEq(true, Rate.strGreater(rateOne_, rateTwo_));
    }
    @Test
    public void strGreater16Test(){
        Rate rateOne_ = new Rate("2/3");
        Rate rateTwo_ = new Rate("1/2");
        assertEq(true, Rate.strGreater(rateOne_, rateTwo_));
    }
    @Test
    public void strGreater17Test(){
        Rate rateOne_ = new Rate("1/2");
        Rate rateTwo_ = new Rate("2/3");
        assertEq(false, Rate.strGreater(rateOne_, rateTwo_));
    }
    @Test
    public void strGreater18Test(){
        Rate rateOne_ = new Rate("-1/2");
        Rate rateTwo_ = new Rate("-2/3");
        assertEq(true, Rate.strGreater(rateOne_, rateTwo_));
    }
    @Test
    public void strGreater19Test(){
        Rate rateOne_ = new Rate("-2/3");
        Rate rateTwo_ = new Rate("-1/2");
        assertEq(false, Rate.strGreater(rateOne_, rateTwo_));
    }
    @Test
    public void lowerEq1Test(){
        Rate rateOne_ = new Rate("0");
        Rate rateTwo_ = new Rate("1");
        assertEq(true, Rate.lowerEq(rateOne_, rateTwo_));
    }
    @Test
    public void lowerEq2Test(){
        Rate rateOne_ = new Rate("1");
        Rate rateTwo_ = new Rate("0");
        assertEq(false, Rate.lowerEq(rateOne_, rateTwo_));
    }
    @Test
    public void lowerEq3Test(){
        Rate rateOne_ = new Rate("0");
        Rate rateTwo_ = new Rate("0");
        assertEq(true, Rate.lowerEq(rateOne_, rateTwo_));
    }
    @Test
    public void lowerEq4Test(){
        Rate rateOne_ = new Rate("1");
        Rate rateTwo_ = new Rate("1");
        assertEq(true, Rate.lowerEq(rateOne_, rateTwo_));
    }
    @Test
    public void lowerEq5Test(){
        Rate rateOne_ = new Rate("-1");
        Rate rateTwo_ = new Rate("0");
        assertEq(true, Rate.lowerEq(rateOne_, rateTwo_));
    }
    @Test
    public void lowerEq6Test(){
        Rate rateOne_ = new Rate("0");
        Rate rateTwo_ = new Rate("-1");
        assertEq(false, Rate.lowerEq(rateOne_, rateTwo_));
    }
    @Test
    public void lowerEq7Test(){
        Rate rateOne_ = new Rate("-1");
        Rate rateTwo_ = new Rate("-1");
        assertEq(true, Rate.lowerEq(rateOne_, rateTwo_));
    }
    @Test
    public void lowerEq8Test(){
        Rate rateOne_ = new Rate("3");
        Rate rateTwo_ = new Rate("1234567890");
        assertEq(true, Rate.lowerEq(rateOne_, rateTwo_));
    }
    @Test
    public void lowerEq9Test(){
        Rate rateOne_ = new Rate("-3");
        Rate rateTwo_ = new Rate("1234567890");
        assertEq(true, Rate.lowerEq(rateOne_, rateTwo_));
    }
    @Test
    public void lowerEq10Test(){
        Rate rateOne_ = new Rate("-1234567890");
        Rate rateTwo_ = new Rate("3");
        assertEq(true, Rate.lowerEq(rateOne_, rateTwo_));
    }
    @Test
    public void lowerEq11Test(){
        Rate rateOne_ = new Rate("-1234567890");
        Rate rateTwo_ = new Rate("-3");
        assertEq(true, Rate.lowerEq(rateOne_, rateTwo_));
    }
    @Test
    public void lowerEq12Test(){
        Rate rateOne_ = new Rate("1234567890");
        Rate rateTwo_ = new Rate("3");
        assertEq(false, Rate.lowerEq(rateOne_, rateTwo_));
    }
    @Test
    public void lowerEq13Test(){
        Rate rateOne_ = new Rate("1234567890");
        Rate rateTwo_ = new Rate("-3");
        assertEq(false, Rate.lowerEq(rateOne_, rateTwo_));
    }
    @Test
    public void lowerEq14Test(){
        Rate rateOne_ = new Rate("3");
        Rate rateTwo_ = new Rate("-1234567890");
        assertEq(false, Rate.lowerEq(rateOne_, rateTwo_));
    }
    @Test
    public void lowerEq15Test(){
        Rate rateOne_ = new Rate("-3");
        Rate rateTwo_ = new Rate("-1234567890");
        assertEq(false, Rate.lowerEq(rateOne_, rateTwo_));
    }
    @Test
    public void lowerEq16Test(){
        Rate rateOne_ = new Rate("2/3");
        Rate rateTwo_ = new Rate("1/2");
        assertEq(false, Rate.lowerEq(rateOne_, rateTwo_));
    }
    @Test
    public void lowerEq17Test(){
        Rate rateOne_ = new Rate("1/2");
        Rate rateTwo_ = new Rate("2/3");
        assertEq(true, Rate.lowerEq(rateOne_, rateTwo_));
    }
    @Test
    public void lowerEq18Test(){
        Rate rateOne_ = new Rate("-1/2");
        Rate rateTwo_ = new Rate("-2/3");
        assertEq(false, Rate.lowerEq(rateOne_, rateTwo_));
    }
    @Test
    public void lowerEq19Test(){
        Rate rateOne_ = new Rate("-2/3");
        Rate rateTwo_ = new Rate("-1/2");
        assertEq(true, Rate.lowerEq(rateOne_, rateTwo_));
    }
    @Test
    public void greaterEq1Test(){
        Rate rateOne_ = new Rate("0");
        Rate rateTwo_ = new Rate("1");
        assertEq(false, Rate.greaterEq(rateOne_, rateTwo_));
    }
    @Test
    public void greaterEq2Test(){
        Rate rateOne_ = new Rate("1");
        Rate rateTwo_ = new Rate("0");
        assertEq(true, Rate.greaterEq(rateOne_, rateTwo_));
    }
    @Test
    public void greaterEq3Test(){
        Rate rateOne_ = new Rate("0");
        Rate rateTwo_ = new Rate("0");
        assertEq(true, Rate.greaterEq(rateOne_, rateTwo_));
    }
    @Test
    public void greaterEq4Test(){
        Rate rateOne_ = new Rate("1");
        Rate rateTwo_ = new Rate("1");
        assertEq(true, Rate.greaterEq(rateOne_, rateTwo_));
    }
    @Test
    public void greaterEq5Test(){
        Rate rateOne_ = new Rate("-1");
        Rate rateTwo_ = new Rate("0");
        assertEq(false, Rate.greaterEq(rateOne_, rateTwo_));
    }
    @Test
    public void greaterEq6Test(){
        Rate rateOne_ = new Rate("0");
        Rate rateTwo_ = new Rate("-1");
        assertEq(true, Rate.greaterEq(rateOne_, rateTwo_));
    }
    @Test
    public void greaterEq7Test(){
        Rate rateOne_ = new Rate("-1");
        Rate rateTwo_ = new Rate("-1");
        assertEq(true, Rate.greaterEq(rateOne_, rateTwo_));
    }
    @Test
    public void greaterEq8Test(){
        Rate rateOne_ = new Rate("3");
        Rate rateTwo_ = new Rate("1234567890");
        assertEq(false, Rate.greaterEq(rateOne_, rateTwo_));
    }
    @Test
    public void greaterEq9Test(){
        Rate rateOne_ = new Rate("-3");
        Rate rateTwo_ = new Rate("1234567890");
        assertEq(false, Rate.greaterEq(rateOne_, rateTwo_));
    }
    @Test
    public void greaterEq10Test(){
        Rate rateOne_ = new Rate("-1234567890");
        Rate rateTwo_ = new Rate("3");
        assertEq(false, Rate.greaterEq(rateOne_, rateTwo_));
    }
    @Test
    public void greaterEq11Test(){
        Rate rateOne_ = new Rate("-1234567890");
        Rate rateTwo_ = new Rate("-3");
        assertEq(false, Rate.greaterEq(rateOne_, rateTwo_));
    }
    @Test
    public void greaterEq12Test(){
        Rate rateOne_ = new Rate("1234567890");
        Rate rateTwo_ = new Rate("3");
        assertEq(true, Rate.greaterEq(rateOne_, rateTwo_));
    }
    @Test
    public void greaterEq13Test(){
        Rate rateOne_ = new Rate("1234567890");
        Rate rateTwo_ = new Rate("-3");
        assertEq(true, Rate.greaterEq(rateOne_, rateTwo_));
    }
    @Test
    public void greaterEq14Test(){
        Rate rateOne_ = new Rate("3");
        Rate rateTwo_ = new Rate("-1234567890");
        assertEq(true, Rate.greaterEq(rateOne_, rateTwo_));
    }
    @Test
    public void greaterEq15Test(){
        Rate rateOne_ = new Rate("-3");
        Rate rateTwo_ = new Rate("-1234567890");
        assertEq(true, Rate.greaterEq(rateOne_, rateTwo_));
    }
    @Test
    public void greaterEq16Test(){
        Rate rateOne_ = new Rate("2/3");
        Rate rateTwo_ = new Rate("1/2");
        assertEq(true, Rate.greaterEq(rateOne_, rateTwo_));
    }
    @Test
    public void greaterEq17Test(){
        Rate rateOne_ = new Rate("1/2");
        Rate rateTwo_ = new Rate("2/3");
        assertEq(false, Rate.greaterEq(rateOne_, rateTwo_));
    }
    @Test
    public void greaterEq18Test(){
        Rate rateOne_ = new Rate("-1/2");
        Rate rateTwo_ = new Rate("-2/3");
        assertEq(true, Rate.greaterEq(rateOne_, rateTwo_));
    }
    @Test
    public void greaterEq19Test(){
        Rate rateOne_ = new Rate("-2/3");
        Rate rateTwo_ = new Rate("-1/2");
        assertEq(false, Rate.greaterEq(rateOne_, rateTwo_));
    }
    @Test
    public void plus1Test(){
        Rate rateOne_ = new Rate("0");
        Rate rateTwo_ = new Rate("20");
        assertEq(new Rate("20"), Rate.plus(rateOne_, rateTwo_));
    }
    @Test
    public void plus2Test(){
        Rate rateOne_ = new Rate("1");
        Rate rateTwo_ = new Rate("20");
        assertEq(new Rate("21"), Rate.plus(rateOne_, rateTwo_));
    }
    @Test
    public void plus3Test(){
        Rate rateOne_ = new Rate("-1");
        Rate rateTwo_ = new Rate("20");
        assertEq(new Rate("19"), Rate.plus(rateOne_, rateTwo_));
    }
    @Test
    public void plus4Test(){
        Rate rateOne_ = new Rate("1");
        Rate rateTwo_ = new Rate("-20");
        assertEq(new Rate("-19"), Rate.plus(rateOne_, rateTwo_));
    }
    @Test
    public void plus5Test(){
        Rate rateOne_ = new Rate("-1");
        Rate rateTwo_ = new Rate("-20");
        assertEq(new Rate("-21"), Rate.plus(rateOne_, rateTwo_));
    }
    @Test
    public void plus6Test(){
        Rate rateOne_ = new Rate("1234567890");
        Rate rateTwo_ = new Rate("11234567890");
        assertEq(new Rate("12469135780"), Rate.plus(rateOne_, rateTwo_));
    }
    @Test
    public void plus7Test(){
        Rate rateOne_ = new Rate("12345678901234567890");
        Rate rateTwo_ = new Rate("11234567890");
        assertEq(new Rate("12345678912469135780"), Rate.plus(rateOne_, rateTwo_));
    }
    @Test
    public void plus8Test(){
        Rate rateOne_ = new Rate("1/6");
        Rate rateTwo_ = new Rate("3/4");
        assertEq(new Rate("11/12"), Rate.plus(rateOne_, rateTwo_));
    }
    @Test
    public void plus9Test(){
        Rate rateOne_ = new Rate("1/2");
        Rate rateTwo_ = new Rate("2/3");
        assertEq(new Rate("7/6"), Rate.plus(rateOne_, rateTwo_));
    }
    @Test
    public void plus10Test(){
        Rate rateOne_ = new Rate("-1/2");
        Rate rateTwo_ = new Rate("2/3");
        assertEq(new Rate("1/6"), Rate.plus(rateOne_, rateTwo_));
    }
    @Test
    public void plus11Test(){
        Rate rateOne_ = new Rate("1/2");
        Rate rateTwo_ = new Rate("-2/3");
        assertEq(new Rate("-1/6"), Rate.plus(rateOne_, rateTwo_));
    }
    @Test
    public void plus12Test(){
        Rate rateOne_ = new Rate("-1/2");
        Rate rateTwo_ = new Rate("-2/3");
        assertEq(new Rate("-7/6"), Rate.plus(rateOne_, rateTwo_));
    }
    @Test
    public void opposNb1Test(){
        Rate rate_ = new Rate("1");
        assertEq(new Rate("-1"), rate_.opposNb());
    }
    @Test
    public void opposNb2Test(){
        Rate rate_ = new Rate("-1");
        assertEq(new Rate("1"), rate_.opposNb());
    }
    @Test
    public void opposNb3Test(){
        Rate rate_ = new Rate("0");
        assertEq(new Rate("0"), rate_.opposNb());
    }
    @Test
    public void opposNb4Test(){
        Rate rate_ = new Rate("-1/2");
        assertEq(new Rate("1/2"), rate_.opposNb());
    }
    @Test
    public void opposNb5Test(){
        Rate rate_ = new Rate("1/2");
        assertEq(new Rate("-1/2"), rate_.opposNb());
    }
    @Test
    public void opposNb6Test(){
        Rate rate_ = new Rate("-1234567890");
        assertEq(new Rate("1234567890"), rate_.opposNb());
    }
    @Test
    public void opposNb7Test(){
        Rate rate_ = new Rate("1234567890");
        assertEq(new Rate("-1234567890"), rate_.opposNb());
    }
    @Test
    public void opposNb8Test(){
        Rate rate_ = new Rate("-12345678901234567890");
        assertEq(new Rate("12345678901234567890"), rate_.opposNb());
    }
    @Test
    public void opposNb9Test(){
        Rate rate_ = new Rate("12345678901234567890");
        assertEq(new Rate("-12345678901234567890"), rate_.opposNb());
    }
    @Test
    public void opposNb10Test(){
        Rate rate_ = new Rate("-3/2");
        assertEq(new Rate("3/2"), rate_.opposNb());
    }
    @Test
    public void opposNb11Test(){
        Rate rate_ = new Rate("3/2");
        assertEq(new Rate("-3/2"), rate_.opposNb());
    }
    @Test
    public void absNb1Test(){
        Rate rate_ = new Rate("1");
        assertEq(new Rate("1"), rate_.absNb());
    }
    @Test
    public void absNb2Test(){
        Rate rate_ = new Rate("-1");
        assertEq(new Rate("1"), rate_.absNb());
    }
    @Test
    public void absNb3Test(){
        Rate rate_ = new Rate("0");
        assertEq(new Rate("0"), rate_.absNb());
    }
    @Test
    public void absNb4Test(){
        Rate rate_ = new Rate("-1/2");
        assertEq(new Rate("1/2"), rate_.absNb());
    }
    @Test
    public void absNb5Test(){
        Rate rate_ = new Rate("1/2");
        assertEq(new Rate("1/2"), rate_.absNb());
    }
    @Test
    public void absNb6Test(){
        Rate rate_ = new Rate("-1234567890");
        assertEq(new Rate("1234567890"), rate_.absNb());
    }
    @Test
    public void absNb7Test(){
        Rate rate_ = new Rate("1234567890");
        assertEq(new Rate("1234567890"), rate_.absNb());
    }
    @Test
    public void absNb8Test(){
        Rate rate_ = new Rate("-12345678901234567890");
        assertEq(new Rate("12345678901234567890"), rate_.absNb());
    }
    @Test
    public void absNb9Test(){
        Rate rate_ = new Rate("12345678901234567890");
        assertEq(new Rate("12345678901234567890"), rate_.absNb());
    }
    @Test
    public void absNb10Test(){
        Rate rate_ = new Rate("-3/2");
        assertEq(new Rate("3/2"), rate_.absNb());
    }
    @Test
    public void absNb11Test(){
        Rate rate_ = new Rate("3/2");
        assertEq(new Rate("3/2"), rate_.absNb());
    }
    @Test
    public void minus1Test(){
        Rate rateOne_ = new Rate("0");
        Rate rateTwo_ = new Rate("20");
        assertEq(new Rate("-20"), Rate.minus(rateOne_, rateTwo_));
    }
    @Test
    public void minus2Test(){
        Rate rateOne_ = new Rate("1");
        Rate rateTwo_ = new Rate("20");
        assertEq(new Rate("-19"), Rate.minus(rateOne_, rateTwo_));
    }
    @Test
    public void minus3Test(){
        Rate rateOne_ = new Rate("-1");
        Rate rateTwo_ = new Rate("20");
        assertEq(new Rate("-21"), Rate.minus(rateOne_, rateTwo_));
    }
    @Test
    public void minus4Test(){
        Rate rateOne_ = new Rate("1");
        Rate rateTwo_ = new Rate("-20");
        assertEq(new Rate("21"), Rate.minus(rateOne_, rateTwo_));
    }
    @Test
    public void minus5Test(){
        Rate rateOne_ = new Rate("-1");
        Rate rateTwo_ = new Rate("-20");
        assertEq(new Rate("19"), Rate.minus(rateOne_, rateTwo_));
    }
    @Test
    public void minus6Test(){
        Rate rateOne_ = new Rate("1234567890");
        Rate rateTwo_ = new Rate("11234567890");
        assertEq(new Rate("-10000000000"), Rate.minus(rateOne_, rateTwo_));
    }
    @Test
    public void minus7Test(){
        Rate rateOne_ = new Rate("12345678901234567890");
        Rate rateTwo_ = new Rate("11234567890");
        assertEq(new Rate("12345678890000000000"), Rate.minus(rateOne_, rateTwo_));
    }
    @Test
    public void minus8Test(){
        Rate rateOne_ = new Rate("1/6");
        Rate rateTwo_ = new Rate("3/4");
        assertEq(new Rate("-7/12"), Rate.minus(rateOne_, rateTwo_));
    }
    @Test
    public void minus9Test(){
        Rate rateOne_ = new Rate("1/2");
        Rate rateTwo_ = new Rate("2/3");
        assertEq(new Rate("-1/6"), Rate.minus(rateOne_, rateTwo_));
    }
    @Test
    public void minus10Test(){
        Rate rateOne_ = new Rate("-1/2");
        Rate rateTwo_ = new Rate("2/3");
        assertEq(new Rate("-7/6"), Rate.minus(rateOne_, rateTwo_));
    }
    @Test
    public void minus11Test(){
        Rate rateOne_ = new Rate("1/2");
        Rate rateTwo_ = new Rate("-2/3");
        assertEq(new Rate("7/6"), Rate.minus(rateOne_, rateTwo_));
    }
    @Test
    public void minus12Test(){
        Rate rateOne_ = new Rate("-1/2");
        Rate rateTwo_ = new Rate("-2/3");
        assertEq(new Rate("1/6"), Rate.minus(rateOne_, rateTwo_));
    }
    @Test
    public void multiply1Test(){
        Rate rateOne_ = new Rate("0");
        Rate rateTwo_ = new Rate("20");
        assertEq(new Rate("0"), Rate.multiply(rateOne_, rateTwo_));
    }
    @Test
    public void multiply2Test(){
        Rate rateOne_ = new Rate("1");
        Rate rateTwo_ = new Rate("20");
        assertEq(new Rate("20"), Rate.multiply(rateOne_, rateTwo_));
    }
    @Test
    public void multiply3Test(){
        Rate rateOne_ = new Rate("-1");
        Rate rateTwo_ = new Rate("20");
        assertEq(new Rate("-20"), Rate.multiply(rateOne_, rateTwo_));
    }
    @Test
    public void multiply4Test(){
        Rate rateOne_ = new Rate("1");
        Rate rateTwo_ = new Rate("-20");
        assertEq(new Rate("-20"), Rate.multiply(rateOne_, rateTwo_));
    }
    @Test
    public void multiply5Test(){
        Rate rateOne_ = new Rate("-1");
        Rate rateTwo_ = new Rate("-20");
        assertEq(new Rate("20"), Rate.multiply(rateOne_, rateTwo_));
    }
    @Test
    public void multiply6Test(){
        Rate rateOne_ = new Rate("1234567890");
        Rate rateTwo_ = new Rate("11234567890");
        assertEq(new Rate("13869836775019052100"), Rate.multiply(rateOne_, rateTwo_));
    }
    @Test
    public void multiply7Test(){
        Rate rateOne_ = new Rate("1/6");
        Rate rateTwo_ = new Rate("3/4");
        assertEq(new Rate("1/8"), Rate.multiply(rateOne_, rateTwo_));
    }
    @Test
    public void multiply8Test(){
        Rate rateOne_ = new Rate("1/2");
        Rate rateTwo_ = new Rate("2/3");
        assertEq(new Rate("1/3"), Rate.multiply(rateOne_, rateTwo_));
    }
    @Test
    public void multiply9Test(){
        Rate rateOne_ = new Rate("-1/2");
        Rate rateTwo_ = new Rate("2/3");
        assertEq(new Rate("-1/3"), Rate.multiply(rateOne_, rateTwo_));
    }
    @Test
    public void multiply10Test(){
        Rate rateOne_ = new Rate("1/2");
        Rate rateTwo_ = new Rate("-2/3");
        assertEq(new Rate("-1/3"), Rate.multiply(rateOne_, rateTwo_));
    }
    @Test
    public void multiply11Test(){
        Rate rateOne_ = new Rate("-1/2");
        Rate rateTwo_ = new Rate("-2/3");
        assertEq(new Rate("1/3"), Rate.multiply(rateOne_, rateTwo_));
    }
    @Test
    public void divide1Test(){
        Rate rateOne_ = new Rate("0");
        Rate rateTwo_ = new Rate("20");
        assertEq(new Rate("0"), Rate.divide(rateOne_, rateTwo_));
    }
    @Test
    public void divide2Test(){
        Rate rateOne_ = new Rate("1");
        Rate rateTwo_ = new Rate("20");
        assertEq(new Rate("1/20"), Rate.divide(rateOne_, rateTwo_));
    }
    @Test
    public void divide3Test(){
        Rate rateOne_ = new Rate("-1");
        Rate rateTwo_ = new Rate("20");
        assertEq(new Rate("-1/20"), Rate.divide(rateOne_, rateTwo_));
    }
    @Test
    public void divide4Test(){
        Rate rateOne_ = new Rate("1");
        Rate rateTwo_ = new Rate("-20");
        assertEq(new Rate("-1/20"), Rate.divide(rateOne_, rateTwo_));
    }
    @Test
    public void divide5Test(){
        Rate rateOne_ = new Rate("-1");
        Rate rateTwo_ = new Rate("-20");
        assertEq(new Rate("1/20"), Rate.divide(rateOne_, rateTwo_));
    }
    @Test
    public void divide6Test(){
        Rate rateOne_ = new Rate("1234567890");
        Rate rateTwo_ = new Rate("11234567890");
        assertEq(new Rate("123456789/1123456789"), Rate.divide(rateOne_, rateTwo_));
    }
    @Test
    public void divide7Test(){
        Rate rateOne_ = new Rate("11234567890");
        Rate rateTwo_ = new Rate("1234567890");
        assertEq(new Rate("1123456789/123456789"), Rate.divide(rateOne_, rateTwo_));
    }
    @Test
    public void divide8Test(){
        Rate rateOne_ = new Rate("12345678901234567890");
        Rate rateTwo_ = new Rate("11234567890");
        assertEq(new Rate("1234567890123456789/1123456789"), Rate.divide(rateOne_, rateTwo_));
    }
    @Test
    public void divide9Test(){
        Rate rateOne_ = new Rate("11234567890");
        Rate rateTwo_ = new Rate("12345678901234567890");
        assertEq(new Rate("1123456789/1234567890123456789"), Rate.divide(rateOne_, rateTwo_));
    }
    @Test
    public void divide10Test(){
        Rate rateOne_ = new Rate("1/6");
        Rate rateTwo_ = new Rate("3/4");
        assertEq(new Rate("2/9"), Rate.divide(rateOne_, rateTwo_));
    }
    @Test
    public void divide11Test(){
        Rate rateOne_ = new Rate("1/2");
        Rate rateTwo_ = new Rate("2/3");
        assertEq(new Rate("3/4"), Rate.divide(rateOne_, rateTwo_));
    }
    @Test
    public void divide12Test(){
        Rate rateOne_ = new Rate("-1/2");
        Rate rateTwo_ = new Rate("2/3");
        assertEq(new Rate("-3/4"), Rate.divide(rateOne_, rateTwo_));
    }
    @Test
    public void divide13Test(){
        Rate rateOne_ = new Rate("1/2");
        Rate rateTwo_ = new Rate("-2/3");
        assertEq(new Rate("-3/4"), Rate.divide(rateOne_, rateTwo_));
    }
    @Test
    public void divide14Test(){
        Rate rateOne_ = new Rate("-1/2");
        Rate rateTwo_ = new Rate("-2/3");
        assertEq(new Rate("3/4"), Rate.divide(rateOne_, rateTwo_));
    }
    @Test
    public void inv1Test(){
        Rate rate_ = new Rate("1");
        assertEq(new Rate("1"), rate_.inv());
    }
    @Test
    public void inv2Test(){
        Rate rate_ = new Rate("-1");
        assertEq(new Rate("-1"), rate_.inv());
    }
    @Test
    public void inv3Test(){
        Rate rate_ = new Rate("-1/2");
        assertEq(new Rate("-2"), rate_.inv());
    }
    @Test
    public void inv4Test(){
        Rate rate_ = new Rate("1/2");
        assertEq(new Rate("2"), rate_.inv());
    }
    @Test
    public void inv5Test(){
        Rate rate_ = new Rate("2");
        assertEq(new Rate("1/2"), rate_.inv());
    }
    @Test
    public void inv6Test(){
        Rate rate_ = new Rate("-2");
        assertEq(new Rate("-1/2"), rate_.inv());
    }
    @Test
    public void powNb1Test(){
        Rate rateOne_ = new Rate("0");
        Rate rateTwo_ = new Rate("0");
        assertEq(new Rate("1"), Rate.powNb(rateOne_, rateTwo_));
    }
    @Test
    public void powNb2Test(){
        Rate rateOne_ = new Rate("0");
        Rate rateTwo_ = new Rate("20");
        assertEq(new Rate("0"), Rate.powNb(rateOne_, rateTwo_));
    }
    @Test
    public void powNb3Test(){
        Rate rateOne_ = new Rate("0");
        Rate rateTwo_ = new Rate("1/2");
        assertEq(new Rate("0"), Rate.powNb(rateOne_, rateTwo_));
    }
    @Test
    public void powNb4Test(){
        Rate rateOne_ = new Rate("1");
        Rate rateTwo_ = new Rate("20");
        assertEq(new Rate("1"), Rate.powNb(rateOne_, rateTwo_));
    }
    @Test
    public void powNb5Test(){
        Rate rateOne_ = new Rate("-1");
        Rate rateTwo_ = new Rate("20");
        assertEq(new Rate("1"), Rate.powNb(rateOne_, rateTwo_));
    }
    @Test
    public void powNb6Test(){
        Rate rateOne_ = new Rate("-1");
        Rate rateTwo_ = new Rate("19");
        assertEq(new Rate("-1"), Rate.powNb(rateOne_, rateTwo_));
    }
    @Test
    public void powNb7Test(){
        Rate rateOne_ = new Rate("2");
        Rate rateTwo_ = new Rate("3");
        assertEq(new Rate("8"), Rate.powNb(rateOne_, rateTwo_));
    }
    @Test
    public void powNb8Test(){
        Rate rateOne_ = new Rate("3");
        Rate rateTwo_ = new Rate("2");
        assertEq(new Rate("9"), Rate.powNb(rateOne_, rateTwo_));
    }
    @Test
    public void powNb9Test(){
        Rate rateOne_ = new Rate("1234567890");
        Rate rateTwo_ = new Rate("0");
        assertEq(new Rate("1"), Rate.powNb(rateOne_, rateTwo_));
    }
    @Test
    public void powNb10Test(){
        Rate rateOne_ = new Rate("5");
        Rate rateTwo_ = new Rate("-1");
        assertEq(new Rate("1/5"), Rate.powNb(rateOne_, rateTwo_));
    }
    @Test
    public void powNb11Test(){
        Rate rateOne_ = new Rate("-5");
        Rate rateTwo_ = new Rate("-1");
        assertEq(new Rate("-1/5"), Rate.powNb(rateOne_, rateTwo_));
    }
    @Test
    public void powNb12Test(){
        Rate rateOne_ = new Rate("4");
        Rate rateTwo_ = new Rate("1/2");
        assertEq(new Rate("2"), Rate.powNb(rateOne_, rateTwo_));
    }
    @Test
    public void powNb13Test(){
        Rate rateOne_ = new Rate("5");
        Rate rateTwo_ = new Rate("1/2");
        assertEq(new Rate("2"), Rate.powNb(rateOne_, rateTwo_));
    }
    @Test
    public void powNb14Test(){
        Rate rateOne_ = new Rate("4");
        Rate rateTwo_ = new Rate("-1/2");
        assertEq(new Rate("1/2"), Rate.powNb(rateOne_, rateTwo_));
    }
    @Test
    public void powNb15Test(){
        Rate rateOne_ = new Rate("4/9");
        Rate rateTwo_ = new Rate("1/2");
        assertEq(new Rate("2/3"), Rate.powNb(rateOne_, rateTwo_));
    }
    @Test
    public void powNb16Test(){
        Rate rateOne_ = new Rate("27");
        Rate rateTwo_ = new Rate("-2/3");
        assertEq(new Rate("1/9"), Rate.powNb(rateOne_, rateTwo_));
    }
    @Test
    public void powNb17Test(){
        Rate rateOne_ = new Rate("27");
        Rate rateTwo_ = new Rate("2/3");
        assertEq(new Rate("9"), Rate.powNb(rateOne_, rateTwo_));
    }
    @Test
    public void powNb18Test(){
        Rate rateOne_ = new Rate("32");
        Rate rateTwo_ = new Rate("-1/5");
        assertEq(new Rate("1/2"), Rate.powNb(rateOne_, rateTwo_));
    }
    @Test
    public void powNb19Test(){
        Rate rateOne_ = new Rate("32");
        Rate rateTwo_ = new Rate("1/5");
        assertEq(new Rate("2"), Rate.powNb(rateOne_, rateTwo_));
    }
    @Test
    public void powNb20Test(){
        Rate rateOne_ = new Rate("-32");
        Rate rateTwo_ = new Rate("1/5");
        assertEq(new Rate("-2"), Rate.powNb(rateOne_, rateTwo_));
    }
    @Test
    public void powNb21Test(){
        Rate rateOne_ = new Rate("-32");
        Rate rateTwo_ = new Rate("2/5");
        assertEq(new Rate("4"), Rate.powNb(rateOne_, rateTwo_));
    }
    @Test
    public void powNb22Test(){
        Rate rateOne_ = new Rate("-16");
        Rate rateTwo_ = new Rate("1/4");
        assertEq(new Rate("2"), Rate.powNb(rateOne_, rateTwo_));
    }
    @Test
    public void proba1(){
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
    }
    @Test
    public void evaluate1Test(){
        Rate rate_ = new Rate("0");
        assertEq("0",rate_.evaluate(0));
    }
    @Test
    public void evaluate2Test(){
        Rate rate_ = new Rate("0");
        assertEq("0",rate_.evaluate(1));
    }
    @Test
    public void evaluate3Test(){
        Rate rate_ = new Rate("1");
        assertEq("1",rate_.evaluate(0));
    }
    @Test
    public void evaluate4Test(){
        Rate rate_ = new Rate("1");
        assertEq("1.E0",rate_.evaluate(1));
    }
    @Test
    public void evaluate5Test(){
        Rate rate_ = new Rate("2");
        assertEq("2",rate_.evaluate(0));
    }
    @Test
    public void evaluate6Test(){
        Rate rate_ = new Rate("2");
        assertEq("2.E0",rate_.evaluate(1));
    }
    @Test
    public void evaluate7Test(){
        Rate rate_ = new Rate("2");
        assertEq("2.0E0",rate_.evaluate(2));
    }
    @Test
    public void evaluate8Test(){
        Rate rate_ = new Rate("3/2");
        assertEq("1.5E0",rate_.evaluate(2));
    }
    @Test
    public void evaluate9Test(){
        Rate rate_ = new Rate("1/2");
        assertEq("5.0E-1",rate_.evaluate(2));
    }
    @Test
    public void evaluate10Test(){
        Rate rate_ = new Rate("-3/2");
        assertEq("-1.5E0",rate_.evaluate(2));
    }
    @Test
    public void evaluate11Test(){
        Rate rate_ = new Rate("-30/2");
        assertEq("-1.5E1",rate_.evaluate(2));
    }
    @Test
    public void evaluate12Test(){
        Rate rate_ = new Rate("-300001/20000");
        assertEq("-1.5E1",rate_.evaluate(2));
    }
    @Test
    public void evaluate13Test(){
        Rate rate_ = new Rate("31/2");
        assertEq("",rate_.evaluate(-1));
    }
    @Test
    public void evaluatePoint1Test(){
        Rate rate_ = new Rate("0");
        assertEq("0",rate_.evaluatePoint(0));
    }
    @Test
    public void evaluatePoint2Test(){
        Rate rate_ = new Rate("0");
        assertEq("0",rate_.evaluatePoint(1));
    }
    @Test
    public void evaluatePoint3Test(){
        Rate rate_ = new Rate("1");
        assertEq("1",rate_.evaluatePoint(0));
    }
    @Test
    public void evaluatePoint4Test(){
        Rate rate_ = new Rate("1");
        assertEq("1",rate_.evaluatePoint(1));
    }
    @Test
    public void evaluatePoint5Test(){
        Rate rate_ = new Rate("2");
        assertEq("2",rate_.evaluatePoint(0));
    }
    @Test
    public void evaluatePoint6Test(){
        Rate rate_ = new Rate("2");
        assertEq("2",rate_.evaluatePoint(1));
    }
    @Test
    public void evaluatePoint7Test(){
        Rate rate_ = new Rate("2");
        assertEq("2",rate_.evaluatePoint(2));
    }
    @Test
    public void evaluatePoint8Test(){
        Rate rate_ = new Rate("3/2");
        assertEq("1.50",rate_.evaluatePoint(2));
    }
    @Test
    public void evaluatePoint9Test(){
        Rate rate_ = new Rate("1/2");
        assertEq("0.50",rate_.evaluatePoint(2));
    }
    @Test
    public void evaluatePoint10Test(){
        Rate rate_ = new Rate("-1/2");
        assertEq("-0.50",rate_.evaluatePoint(2));
    }
    @Test
    public void evaluatePoint11Test(){
        Rate rate_ = new Rate("-3/2");
        assertEq("-1.50",rate_.evaluatePoint(2));
    }
    @Test
    public void evaluatePoint12Test(){
        Rate rate_ = new Rate("-3/20");
        assertEq("-0.15",rate_.evaluatePoint(2));
    }
    @Test
    public void evaluatePoint13Test(){
        Rate rate_ = new Rate("-3/20");
        assertEq("0",rate_.evaluatePoint(0));
    }
    @Test
    public void evaluatePoint14Test(){
        Rate rate_ = new Rate("3/20");
        assertEq("0",rate_.evaluatePoint(0));
    }
    @Test
    public void evaluatePoint15Test(){
        Rate rate_ = new Rate("-3/200");
        assertEq("-0.01",rate_.evaluatePoint(2));
    }
    @Test
    public void evaluatePoint16Test(){
        Rate rate_ = new Rate("-3/200");
        assertEq("0.0",rate_.evaluatePoint(1));
    }
    @Test
    public void evaluatePoint17Test(){
        Rate rate_ = new Rate("-30/2");
        assertEq("-15",rate_.evaluatePoint(2));
    }
    @Test
    public void evaluatePoint18Test(){
        Rate rate_ = new Rate("30/2");
        assertEq("15",rate_.evaluatePoint(2));
    }
    @Test
    public void evaluatePoint19Test(){
        Rate rate_ = new Rate("-31/2");
        assertEq("-15.50",rate_.evaluatePoint(2));
    }
    @Test
    public void evaluatePoint20Test(){
        Rate rate_ = new Rate("31/2");
        assertEq("15.50",rate_.evaluatePoint(2));
    }
    @Test
    public void evaluatePoint21Test(){
        Rate rate_ = new Rate("-31001/2000");
        assertEq("-15.50",rate_.evaluatePoint(2));
    }
    @Test
    public void evaluatePoint22Test(){
        Rate rate_ = new Rate("31001/2000");
        assertEq("15.50",rate_.evaluatePoint(2));
    }
    @Test
    public void evaluatePoint23Test(){
        Rate rate_ = new Rate("-31001/2000");
        assertEq("-15.5",rate_.evaluatePoint(1));
    }
    @Test
    public void evaluatePoint24Test(){
        Rate rate_ = new Rate("31001/2000");
        assertEq("15.5",rate_.evaluatePoint(1));
    }
    @Test
    public void evaluatePoint25Test(){
        Rate rate_ = new Rate("-31/2");
        assertEq("-15.500000",rate_.evaluatePoint(6));
    }
    @Test
    public void evaluatePoint26Test(){
        Rate rate_ = new Rate("31/2");
        assertEq("15.500000",rate_.evaluatePoint(6));
    }
    @Test
    public void evaluatePoint27Test(){
        Rate rate_ = new Rate("31/2");
        assertEq("",rate_.evaluatePoint(-1));
    }
    @Test
    public void greaterThanOne1(){
        assertTrue(new Rate("3/2").greaterThanOne());
        assertTrue(!new Rate("-3/2").greaterThanOne());
        assertTrue(!new Rate("1").greaterThanOne());
    }
    @Test
    public void greaterOrEquals1Fail(){
        assertTrue(new Rate("3/2").greaterOrEqualsOne());
        assertTrue(!new Rate("-3/2").greaterOrEqualsOne());
        assertTrue(new Rate("1").greaterOrEqualsOne());
    }
    @Test
    public void lowerThanOne1(){
        assertTrue(!new Rate("3/2").lowerThanOne());
        assertTrue(new Rate("-3/2").lowerThanOne());
        assertTrue(!new Rate("1").lowerThanOne());
    }
    @Test
    public void lowerOrEqualsOnee1(){
        assertTrue(!new Rate("3/2").lowerOrEqualsOne());
        assertTrue(new Rate("-3/2").lowerOrEqualsOne());
        assertTrue(new Rate("1").lowerOrEqualsOne());
    }
    @Test
    public void percent1(){
        assertEq(new LgInt("50"),new Rate("1/2").percent());
    }
    @Test
    public void percent2(){
        assertEq(new LgInt("100"),new Rate("1").percent());
    }
    @Test
    public void percent3(){
        assertEq(new LgInt("33"),new Rate("1/3").percent());
    }
}