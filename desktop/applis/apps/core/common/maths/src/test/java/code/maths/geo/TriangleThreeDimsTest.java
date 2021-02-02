package code.maths.geo;

import code.maths.EquallableMathUtil;
import org.junit.Test;

public class TriangleThreeDimsTest extends EquallableMathUtil {
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
        TriangleThreeDims t_ = new TriangleThreeDims(one_,two_,three_);
        assertNotNull(t_.getCircumCenter());
        assertNotNull(t_.getGravityCenter());
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
        TriangleThreeDims t_ = new TriangleThreeDims(one_,two_,three_);
        assertNotNull(t_.getCircumCenter());
        assertNotNull(t_.display());
    }
}
