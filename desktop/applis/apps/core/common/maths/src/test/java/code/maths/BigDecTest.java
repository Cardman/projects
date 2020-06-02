package code.maths;
import static code.maths.EquallableMathUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


public class BigDecTest {

    @Test
    public void new_BigDec_1Test() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        assertEq(1, b_.getNumber());
    }

    @Test
    public void new_BigDec_2Test() {
        BigDec b_ = new BigDec(new Rate(1));
        assertEq(1, b_.getNumber());
    }

    @Test
    public void new_BigDec_3Test() {
        BigDec b_ = new BigDec(new Rate(1),1);
        assertEq(1, b_.getNumber());
    }

    @Test
    public void new_BigDec_4Test() {
        BigDec b_ = new BigDec("1");
        assertEq(1, b_.getNumber());
    }

    @Test
    public void newBigDecTest() {
        BigDec b_ = BigDec.newBigDec("1");
        assertEq(1, b_.getNumber());
    }

    @Test
    public void valueTest() {
        BigDec b_ = BigDec.newBigDec("1");
        assertEq(1, b_.doubleValue());
        assertEq(1, b_.floatValue());
        assertEq(1, b_.longValue());
        assertEq(1, b_.longValueExact());
        assertEq(1, b_.intValue());
        assertEq(1, b_.intValueExact());
        assertEq(1, b_.shortValue());
        assertEq(1, b_.shortValueExact());
        assertEq(1, b_.byteValue());
        assertEq(1, b_.byteValueExact());
        assertEq(1, b_.toBigInteger());
        assertEq(1, b_.toBigIntegerExact());
        assertEq(1, b_.signum());
        assertEq(0, b_.scale());
        assertEq(1, b_.precision());
        assertEq(1, b_.unscaledValue());
    }

    @Test
    public void displayTest() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        assertEq("1",b_.display());
    }

    @Test
    public void toEngineeringStringTest() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        assertEq("1",b_.toEngineeringString());
    }

    @Test
    public void toPlainStringTest() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        assertEq("1",b_.toPlainString());
    }

    @Test
    public void eq1Test() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        BigDec c_ = new BigDec(new BigDecimal(2));
        assertTrue(!b_.eq(c_));
    }

    @Test
    public void eq2Test() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        BigDec c_ = new BigDec(new BigDecimal(1));
        assertTrue(b_.eq(c_));
    }

    @Test
    public void cmp1Test() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        BigDec c_ = new BigDec(new BigDecimal(2));
        assertTrue(b_.cmp(c_) < 0);
    }

    @Test
    public void cmp2Test() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        BigDec c_ = new BigDec(new BigDecimal(1));
        assertEq(0,b_.cmp(c_));
    }

    @Test
    public void cmp3Test() {
        BigDec b_ = new BigDec(new BigDecimal(2));
        BigDec c_ = new BigDec(new BigDecimal(1));
        assertTrue(b_.cmp(c_) > 0);
    }

    @Test
    public void toRateTest() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        assertEq(new Rate(1),b_.toRate());
    }

    @Test
    public void plus1Test() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(1)),b_.plus());
    }

    @Test
    public void plus2Test() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(1)),b_.plus(MathContext.UNLIMITED));
    }

    @Test
    public void negate1Test() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(-1)),b_.negate());
    }

    @Test
    public void negate2Test() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(-1)),b_.negate(MathContext.UNLIMITED));
    }

    @Test
    public void abs1Test() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(1)),b_.abs());
    }
    @Test
    public void abs2Test() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(1)),b_.abs(MathContext.UNLIMITED));
    }
    @Test
    public void ulpTest() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(1)),b_.ulp());
    }
    @Test
    public void add1Test() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        BigDec c_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(2)),b_.add(c_));
    }
    @Test
    public void add2Test() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        BigDec c_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(2)),b_.add(c_,MathContext.UNLIMITED));
    }
    @Test
    public void subtract1Test() {
        BigDec b_ = new BigDec(new BigDecimal(2));
        BigDec c_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(1)),b_.subtract(c_));
    }
    @Test
    public void subtract2Test() {
        BigDec b_ = new BigDec(new BigDecimal(2));
        BigDec c_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(1)),b_.subtract(c_,MathContext.UNLIMITED));
    }
    @Test
    public void multiply1Test() {
        BigDec b_ = new BigDec(new BigDecimal(2));
        BigDec c_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(2)),b_.multiply(c_));
    }
    @Test
    public void multiply2Test() {
        BigDec b_ = new BigDec(new BigDecimal(2));
        BigDec c_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(2)),b_.multiply(c_,MathContext.UNLIMITED));
    }
    @Test
    public void divide1Test() {
        BigDec b_ = new BigDec(new BigDecimal(2));
        BigDec c_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(2)),b_.divide(c_));
    }
    @Test
    public void divide2Test() {
        BigDec b_ = new BigDec(new BigDecimal(2));
        BigDec c_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(2)),b_.divide(c_,MathContext.UNLIMITED));
    }
    @Test
    public void divide3Test() {
        BigDec b_ = new BigDec(new BigDecimal(2));
        BigDec c_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(2)),b_.divide(c_,RoundingMode.UNNECESSARY));
    }
    @Test
    public void divide4Test() {
        BigDec b_ = new BigDec(new BigDecimal(2));
        BigDec c_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(2)),b_.divide(c_,BigDecimal.ROUND_UNNECESSARY));
    }
    @Test
    public void divide5Test() {
        BigDec b_ = new BigDec(new BigDecimal(2));
        BigDec c_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(2)),b_.divide(c_,0,RoundingMode.UNNECESSARY));
    }
    @Test
    public void divide6Test() {
        BigDec b_ = new BigDec(new BigDecimal(2));
        BigDec c_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(2)),b_.divide(c_,0,BigDecimal.ROUND_UNNECESSARY));
    }
    @Test
    public void divideToIntegralValue1Test() {
        BigDec b_ = new BigDec(new BigDecimal(2));
        BigDec c_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(2)),b_.divideToIntegralValue(c_));
    }
    @Test
    public void divideToIntegralValue2Test() {
        BigDec b_ = new BigDec(new BigDecimal(2));
        BigDec c_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(2)),b_.divideToIntegralValue(c_,MathContext.UNLIMITED));
    }
    @Test
    public void remainder1Test() {
        BigDec b_ = new BigDec(new BigDecimal(3));
        BigDec c_ = new BigDec(new BigDecimal(2));
        assertEq(new BigDec(new BigDecimal(1)),b_.remainder(c_));
    }
    @Test
    public void remainder2Test() {
        BigDec b_ = new BigDec(new BigDecimal(3));
        BigDec c_ = new BigDec(new BigDecimal(2));
        assertEq(new BigDec(new BigDecimal(1)),b_.remainder(c_,MathContext.UNLIMITED));
    }
    @Test
    public void roundTest() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(1)),b_.round(MathContext.UNLIMITED));
    }
    @Test
    public void divideAndRemainder1Test() {
        BigDec b_ = new BigDec(new BigDecimal(3));
        BigDec c_ = new BigDec(new BigDecimal(2));
        BigDec[] dr_ = b_.divideAndRemainder(c_);
        assertEq(new BigDec(new BigDecimal(1)),dr_[0]);
        assertEq(new BigDec(new BigDecimal(1)),dr_[1]);
    }
    @Test
    public void divideAndRemainder2Test() {
        BigDec b_ = new BigDec(new BigDecimal(3));
        BigDec c_ = new BigDec(new BigDecimal(2));
        BigDec[] dr_ = b_.divideAndRemainder(c_,MathContext.UNLIMITED);
        assertEq(new BigDec(new BigDecimal(1)),dr_[0]);
        assertEq(new BigDec(new BigDecimal(1)),dr_[1]);
    }
    @Test
    public void pow1Test() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(1)),b_.pow(1));
    }
    @Test
    public void pow2Test() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(1)),b_.pow(1,MathContext.UNLIMITED));
    }
    @Test
    public void powInv1Test() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(1)),b_.powInv(1));
    }
    @Test
    public void powInv2Test() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(1)),b_.powInv(1,MathContext.UNLIMITED));
    }
    @Test
    public void min1Test() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        BigDec c_ = new BigDec(new BigDecimal(2));
        assertEq(new BigDec(new BigDecimal(1)),b_.min(c_));
    }
    @Test
    public void max2Test() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        BigDec c_ = new BigDec(new BigDecimal(2));
        assertEq(new BigDec(new BigDecimal(2)),b_.max(c_));
    }
    @Test
    public void movePointLeftTest() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(1)),b_.movePointLeft(0));
    }

    @Test
    public void movePointRightTest() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(1)),b_.movePointRight(0));
    }

    @Test
    public void scaleByPowerOfTenTest() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(1)),b_.scaleByPowerOfTen(0));
    }
    @Test
    public void nthRoot1Test() {
        assertEq("1.2",new BigDec("1.44").nthRoot(2).stripTrailingZeros().display());
    }

    @Test
    public void nthRoot2Test() {
        assertEq("1.2",new BigDec("1.728").nthRoot(3).stripTrailingZeros().display());
    }

    @Test
    public void nthRoot3Test() {
        assertEq("1.414213562373095048801688724209698078569671875376948073176679738",new BigDec("2").nthRoot(2).stripTrailingZeros().display());
    }

    @Test
    public void nthRoot4Test() {
        assertEq("0",new BigDec("0").nthRoot(2).stripTrailingZeros().display());
    }

    @Test
    public void setScale1Test() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(1)),b_.setScale(0));
    }

    @Test
    public void setScale2Test() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(1)),b_.setScale(0,BigDecimal.ROUND_UNNECESSARY));
    }

    @Test
    public void setScale3Test() {
        BigDec b_ = new BigDec(new BigDecimal(1));
        assertEq(new BigDec(new BigDecimal(1)),b_.setScale(0,RoundingMode.UNNECESSARY));
    }
}
