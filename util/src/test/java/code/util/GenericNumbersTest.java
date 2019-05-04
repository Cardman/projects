package code.util;
import static code.util.EquallableExUtil.assertEq;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import org.junit.Test;


public class GenericNumbersTest {

    @Test
    public void sort1Test() {
        GenericNumbers nbs_ = new GenericNumbers();
        nbs_.addBigIntCopy(new BigInteger("5"));
        nbs_.addBigIntCopy(new BigInteger("1"));
        nbs_.addBigIntCopy(new BigInteger("4"));
        nbs_.addBigIntCopy(new BigInteger("10"));
        nbs_.addBigIntCopy(new BigInteger("8"));
        nbs_.sort();
        assertEq(5,nbs_.size());
        assertEq(new BigDecimal("1"),nbs_.first());
        assertEq(new BigDecimal("4"),nbs_.get(1));
        assertEq(new BigDecimal("5"),nbs_.get(2));
        assertEq(new BigDecimal("8"),nbs_.get(3));
        assertEq(new BigDecimal("10"),nbs_.get(4));
    }


    @Test
    public void sumElts1Test() {
        GenericNumbers nbs_ = new GenericNumbers();
        nbs_.addBigIntCopy(new BigInteger("5"));
        nbs_.addBigIntCopy(new BigInteger("1"));
        nbs_.addBigIntCopy(new BigInteger("4"));
        nbs_.addBigIntCopy(new BigInteger("10"));
        nbs_.addBigIntCopy(new BigInteger("8"));
        assertEq(new BigDecimal("28"), nbs_.sumElts());
    }

    @Test
    public void sumElts2Test() {
        GenericNumbers nbs_ = new GenericNumbers();
        nbs_.add(new BigDecimal("5.0001", MathContext.UNLIMITED));
        nbs_.add(new BigDecimal("1.9999", MathContext.UNLIMITED));
        nbs_.add(new BigDecimal("4.5", MathContext.UNLIMITED));
        nbs_.add(new BigDecimal("10.5", MathContext.UNLIMITED));
        nbs_.add(new BigDecimal("8", MathContext.UNLIMITED));
        assertEq(new BigDecimal("30.0000"), nbs_.sumElts());
        assertEq(new BigDecimal("7.00000000000000000000000000000000000"), new BigDecimal("5.00000000000000000000000000000000001").add(new BigDecimal("1.99999999999999999999999999999999999")));
        assertEq(new BigDecimal("937.00"), new BigDecimal("9.37", MathContext.UNLIMITED).multiply(new BigDecimal("100", MathContext.UNLIMITED)));
        assertEq(new BigDecimal("937.00"), new BigDecimal("9.37").multiply(new BigDecimal("100")));
    }

    @Test
    public void prodElts1Test() {
        GenericNumbers nbs_ = new GenericNumbers();
        nbs_.addBigIntCopy(0,new BigInteger("4"));
        nbs_.addBigIntCopy(0,new BigInteger("8"));
        nbs_.setBigIntCopy(0,new BigInteger("8"));
        assertEq(new BigDecimal("32"), nbs_.prodElts());
    }

    @Test
    public void eq1Test() {
        GenericNumbers nbs_ = new GenericNumbers();
        nbs_.addBigIntCopy(new BigInteger("4"));
        GenericNumbers nbs2_ = new GenericNumbers();
        nbs2_.addBigIntCopy(new BigInteger("4"));
        assertTrue(nbs_.eq(nbs2_));
    }

    @Test
    public void eq2Test() {
        GenericNumbers nbs_ = new GenericNumbers();
        nbs_.addBigIntCopy(new BigInteger("4"));
        nbs_.addBigIntCopy(new BigInteger("4"));
        GenericNumbers nbs2_ = new GenericNumbers();
        nbs2_.addBigIntCopy(new BigInteger("4"));
        nbs2_.addBigIntCopy(new BigInteger("5"));
        assertTrue(!nbs_.eq(nbs2_));
    }

    @Test
    public void eq3Test() {
        GenericNumbers nbs_ = new GenericNumbers();
        nbs_.addBigIntCopy(new BigInteger("4"));
        GenericNumbers nbs2_ = new GenericNumbers();
        nbs2_.addBigIntCopy(new BigInteger("4"));
        nbs2_.addBigIntCopy(new BigInteger("5"));
        assertTrue(!nbs_.eq(nbs2_));
    }
}
