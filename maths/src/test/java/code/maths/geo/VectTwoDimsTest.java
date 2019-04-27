package code.maths.geo;

import org.junit.Test;

import static code.maths.EquallableMathUtil.assertEq;
import static org.junit.Assert.assertTrue;

public class VectTwoDimsTest {
    @Test
    public void eq1Test() {
        VectTwoDims vOne_ =new VectTwoDims();
        vOne_.setDeltax(1);
        vOne_.setDeltay(2);
        VectTwoDims vTwo_ =new VectTwoDims(1,2);
        assertTrue(vOne_.eq(vTwo_));
    }
    @Test
    public void eq2Test() {
        VectTwoDims vOne_ =new VectTwoDims();
        vOne_.setDeltax(1);
        vOne_.setDeltay(2);
        VectTwoDims vTwo_ =new VectTwoDims(1,1);
        assertTrue(!vOne_.eq(vTwo_));
    }
    @Test
    public void eq3Test() {
        VectTwoDims vOne_ =new VectTwoDims();
        vOne_.setDeltax(1);
        vOne_.setDeltay(2);
        VectTwoDims vTwo_ =new VectTwoDims(2,1);
        assertTrue(!vOne_.eq(vTwo_));
    }
    @Test
    public void eq4Test() {
        VectTwoDims vOne_ =new VectTwoDims();
        vOne_.setDeltax(1);
        vOne_.setDeltay(2);
        VectTwoDims vTwo_ =new VectTwoDims(2,2);
        assertTrue(!vOne_.eq(vTwo_));
    }
    @Test
    public void new_VectTwoDims_Test() {
        VectTwoDims vOne_ =VectTwoDims.newCustPoint("1,2");
        VectTwoDims vTwo_ =new VectTwoDims(1,2);
        assertTrue(vOne_.eq(vTwo_));
    }
    @Test
    public void displayTest() {
        VectTwoDims vOne_ =new VectTwoDims(1,2);
        assertEq("1,2",vOne_.display());
    }
}
