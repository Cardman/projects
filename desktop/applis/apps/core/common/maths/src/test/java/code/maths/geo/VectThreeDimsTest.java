package code.maths.geo;

import code.maths.EquallableMathUtil;
import code.maths.Rate;
import org.junit.Test;

public class VectThreeDimsTest extends EquallableMathUtil {
    @Test
    public void eq1Test() {
        VectThreeDims vOne_ =new VectThreeDims();
        vOne_.setDeltax(1);
        vOne_.setDeltay(2);
        vOne_.setDeltaz(3);
        VectThreeDims vTwo_ =new VectThreeDims(1,2,3);
        assertTrue(vOne_.eq(vTwo_));
    }
    @Test
    public void eq2Test() {
        VectThreeDims vOne_ =new VectThreeDims();
        vOne_.setDeltax(1);
        vOne_.setDeltay(2);
        vOne_.setDeltaz(3);
        VectThreeDims vTwo_ =new VectThreeDims(1,2,4);
        assertTrue(!vOne_.eq(vTwo_));
    }
    @Test
    public void eq3Test() {
        VectThreeDims vOne_ =new VectThreeDims();
        vOne_.setDeltax(1);
        vOne_.setDeltay(2);
        vOne_.setDeltaz(3);
        VectThreeDims vTwo_ =new VectThreeDims(1,4,3);
        assertTrue(!vOne_.eq(vTwo_));
    }
    @Test
    public void eq4Test() {
        VectThreeDims vOne_ =new VectThreeDims();
        vOne_.setDeltax(1);
        vOne_.setDeltay(2);
        vOne_.setDeltaz(3);
        VectThreeDims vTwo_ =new VectThreeDims(4,2,3);
        assertTrue(!vOne_.eq(vTwo_));
    }
    @Test
    public void new_VectThreeDims_Test() {
        VectThreeDims vOne_ =VectThreeDims.newCustPoint("1,2,3");
        VectThreeDims vTwo_ =new VectThreeDims(1,2,3);
        assertTrue(vOne_.eq(vTwo_));
    }
    @Test
    public void displayTest() {
        VectThreeDims vOne_ =new VectThreeDims(1,2,3);
        assertEq("1,2,3",vOne_.display());
    }
    @Test
    public void detTest() {
        VectThreeDims vOne_ =new VectThreeDims(1,0,0);
        VectThreeDims vTwo_ =new VectThreeDims(0,1,0);
        VectThreeDims vThree_ =new VectThreeDims(0,0,1);
        assertEq(Rate.one(),vOne_.det(vTwo_,vThree_));
    }
}
