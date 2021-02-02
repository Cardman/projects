package code.util.comparators;

import code.util.EquallableExUtil;
import code.util.core.SortConstants;
import org.junit.Test;

public final class ComparatorBooleanTest extends EquallableExUtil {
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
        assertEq(SortConstants.SWAP_SORT,ComparatorBoolean.cmp(true,false));
    }
    @Test
    public void cmp2Test() {
        assertEq(SortConstants.NO_SWAP_SORT,ComparatorBoolean.cmp(false,true));
    }
    @Test
    public void cmp3Test() {
        assertEq(SortConstants.EQ_CMP,ComparatorBoolean.cmp(true,true));
    }
    @Test
    public void cmp4Test() {
        assertEq(SortConstants.EQ_CMP,ComparatorBoolean.cmp(false,false));
    }
}
