package code.maths.geo;

import code.maths.EquallableMathUtil;
import org.junit.Test;

public class TetrahedronTest extends EquallableMathUtil {
    @Test
    public void getCircumCenter1Test() {
        CustPointThreeDims one_ = new CustPointThreeDims();
        one_.setXcoords(0);
        one_.setYcoords(0);
        one_.setZcoords(0);
        CustPointThreeDims two_ = new CustPointThreeDims();
        two_.setXcoords(0);
        two_.setYcoords(0);
        two_.setZcoords(0);
        CustPointThreeDims three_ = new CustPointThreeDims();
        three_.setXcoords(0);
        three_.setYcoords(0);
        three_.setZcoords(0);
        CustPointThreeDims four_ = new CustPointThreeDims();
        four_.setXcoords(0);
        four_.setYcoords(0);
        four_.setZcoords(0);
        Tetrahedron t_ = new Tetrahedron(one_,two_,three_,four_);
        assertNull(t_.getCircumCenter());
    }
    @Test
    public void getCircumCenter2Test() {
        CustPointThreeDims one_ = new CustPointThreeDims();
        one_.setXcoords(0);
        one_.setYcoords(0);
        one_.setZcoords(0);
        CustPointThreeDims two_ = new CustPointThreeDims();
        two_.setXcoords(0);
        two_.setYcoords(0);
        two_.setZcoords(0);
        CustPointThreeDims three_ = new CustPointThreeDims();
        three_.setXcoords(0);
        three_.setYcoords(0);
        three_.setZcoords(0);
        CustPointThreeDims four_ = new CustPointThreeDims();
        four_.setXcoords(0);
        four_.setYcoords(0);
        four_.setZcoords(0);
        Tetrahedron t_ = new Tetrahedron(one_,two_,three_,four_);
        CustPointThreeDims pt_ = new CustPointThreeDims();
        pt_.setXcoords(1);
        pt_.setYcoords(0);
        pt_.setZcoords(0);
        assertTrue(!t_.isInCircum(pt_));
        assertNotNull(t_.getGravityCenter());
        assertNotNull(t_.display());
        assertNotNull(t_.getEdges());
    }
    @Test
    public void getCircumCenter3Test() {
        CustPointThreeDims one_ = new CustPointThreeDims();
        one_.setXcoords(0);
        one_.setYcoords(0);
        one_.setZcoords(0);
        CustPointThreeDims two_ = new CustPointThreeDims();
        two_.setXcoords(1);
        two_.setYcoords(0);
        two_.setZcoords(0);
        CustPointThreeDims three_ = new CustPointThreeDims();
        three_.setXcoords(0);
        three_.setYcoords(1);
        three_.setZcoords(0);
        CustPointThreeDims four_ = new CustPointThreeDims();
        four_.setXcoords(0);
        four_.setYcoords(0);
        four_.setZcoords(0);
        assertNotNull(Tetrahedron.getCircumCenter(one_,two_,three_));
        assertNotNull(Tetrahedron.getEq(one_,two_,three_));
    }
}
