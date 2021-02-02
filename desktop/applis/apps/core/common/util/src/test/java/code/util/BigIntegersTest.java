package code.util;

import java.math.BigInteger;

import org.junit.Test;


public class BigIntegersTest extends EquallableExUtil {

    @Test
    public void sort1Test() {
        BigIntegers nbs_ = new BigIntegers();
        nbs_.add(new BigInteger("5"));
        nbs_.add(new BigInteger("1"));
        nbs_.add(new BigInteger("4"));
        nbs_.add(new BigInteger("10"));
        nbs_.add(new BigInteger("8"));
        nbs_.sort();
        assertEq(5,nbs_.size());
        assertEq(new BigInteger("1"),nbs_.get(0));
        assertEq(new BigInteger("4"),nbs_.get(1));
        assertEq(new BigInteger("5"),nbs_.get(2));
        assertEq(new BigInteger("8"),nbs_.get(3));
        assertEq(new BigInteger("10"),nbs_.get(4));
    }

    @Test
    public void sumElts1Test() {
        BigIntegers nbs_ = new BigIntegers();
        nbs_.add(new BigInteger("5"));
        nbs_.add(new BigInteger("1"));
        nbs_.add(new BigInteger("4"));
        nbs_.add(new BigInteger("10"));
        nbs_.add(new BigInteger("8"));
        assertEq(new BigInteger("28"), nbs_.sumElts());
    }

    @Test
    public void prodElts1Test() {
        BigIntegers nbs_ = new BigIntegers();
        nbs_.add(new BigInteger("5"));
        nbs_.add(new BigInteger("1"));
        nbs_.add(new BigInteger("4"));
        nbs_.add(new BigInteger("10"));
        nbs_.add(new BigInteger("8"));
        assertEq(new BigInteger("1600"), nbs_.prodElts());
    }

    @Test
    public void eq1Test() {
        BigIntegers nbs_ = new BigIntegers();
        nbs_.add(new BigInteger("4"));
        BigIntegers nbs2_ = new BigIntegers();
        nbs2_.add(new BigInteger("4"));
        assertTrue(nbs_.eq(nbs2_));
    }

    @Test
    public void eq2Test() {
        BigIntegers nbs_ = new BigIntegers();
        nbs_.add(new BigInteger("4"));
        nbs_.add(new BigInteger("4"));
        BigIntegers nbs2_ = new BigIntegers();
        nbs2_.add(new BigInteger("4"));
        nbs2_.add(new BigInteger("5"));
        assertTrue(!nbs_.eq(nbs2_));
    }

    @Test
    public void eq3Test() {
        BigIntegers nbs_ = new BigIntegers();
        nbs_.add(new BigInteger("4"));
        BigIntegers nbs2_ = new BigIntegers();
        nbs2_.add(new BigInteger("4"));
        nbs2_.add(new BigInteger("5"));
        assertTrue(!nbs_.eq(nbs2_));
    }
}
