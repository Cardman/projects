package code.util.comparators;

import code.util.CustList;
import org.junit.Test;

import static code.util.EquallableExUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class ComparatorBooleanTest {
    @Test
    public void diff1Test() {
        assertTrue(ComparatorBoolean.diff(true,false));
    }
    @Test
    public void diff2Test() {
        assertTrue(ComparatorBoolean.diff(false,true));
    }
    @Test
    public void diff3Test() {
        assertTrue(!ComparatorBoolean.diff(true,true));
    }
    @Test
    public void diff4Test() {
        assertTrue(!ComparatorBoolean.diff(false,false));
    }
    @Test
    public void eq1Test() {
        assertTrue(!ComparatorBoolean.eq(true,false));
    }
    @Test
    public void eq2Test() {
        assertTrue(!ComparatorBoolean.eq(false,true));
    }
    @Test
    public void eq3Test() {
        assertTrue(ComparatorBoolean.eq(true,true));
    }
    @Test
    public void eq4Test() {
        assertTrue(ComparatorBoolean.eq(false,false));
    }
    @Test
    public void cmp1Test() {
        assertEq(CustList.SWAP_SORT,ComparatorBoolean.cmp(true,false));
    }
    @Test
    public void cmp2Test() {
        assertEq(CustList.NO_SWAP_SORT,ComparatorBoolean.cmp(false,true));
    }
    @Test
    public void cmp3Test() {
        assertEq(CustList.EQ_CMP,ComparatorBoolean.cmp(true,true));
    }
    @Test
    public void cmp4Test() {
        assertEq(CustList.EQ_CMP,ComparatorBoolean.cmp(false,false));
    }
}
