package code.maths.geo;

import code.maths.*;
import org.junit.Test;

public final class DeltaSelectionUnitTest extends EquallableMathUtil {
    @Test
    public void d1() {
        Delta d_ = new Delta();
        d_.setDx(Rate.one());
        d_.setDy(Rate.one());
        assertTrue(d_.isMoving());
    }
    @Test
    public void d2() {
        Delta d_ = new Delta();
        d_.setDx(Rate.one());
        assertTrue(d_.isMoving());
    }
    @Test
    public void d3() {
        Delta d_ = new Delta();
        d_.setDy(Rate.one());
        assertTrue(d_.isMoving());
    }
    @Test
    public void d4() {
        Delta d_ = new Delta();
        assertTrue(!d_.isMoving());
    }
    @Test
    public void isOutside1() {
        DeltaSelectionUnit d_ = new DeltaSelectionUnit();
        d_.setLocx(new Rate(2));
        d_.setLocy(new Rate(3));
        Delta delta_ = new Delta();
        delta_.setDx(new Rate(0));
        delta_.setDy(new Rate(1));
        Rect area_ = new Rect(new Rate(40),new Rate(40),new Rate(200),new Rate(200));
        assertTrue(isOutside(d_,area_,delta_));
    }
    @Test
    public void isOutside2() {
        DeltaSelectionUnit d_ = new DeltaSelectionUnit();
        d_.setLocx(new Rate(2));
        d_.setLocy(new Rate(3));
        Delta delta_ = new Delta();
        delta_.setDx(new Rate(0));
        delta_.setDy(new Rate(1));
        Rect area_ = new Rect(new Rate(30),new Rate(30),new Rate(200),new Rate(200));
        assertTrue(!isOutside(d_,area_,delta_));
    }
    @Test
    public void isOutside3() {
        DeltaSelectionUnit d_ = new DeltaSelectionUnit();
        d_.setLocx(new Rate(210));
        d_.setLocy(new Rate(3));
        Delta delta_ = new Delta();
        delta_.setDx(new Rate(0));
        delta_.setDy(new Rate(1));
        Rect area_ = new Rect(new Rate(30),new Rate(30),new Rate(200),new Rate(200));
        assertTrue(!isOutside(d_,area_,delta_));
    }
   @Test
    public void isOutside4() {
        DeltaSelectionUnit d_ = new DeltaSelectionUnit();
        d_.setLocx(new Rate(2));
        d_.setLocy(new Rate(210));
        Delta delta_ = new Delta();
        delta_.setDx(new Rate(0));
        delta_.setDy(new Rate(1));
        Rect area_ = new Rect(new Rate(30),new Rate(30),new Rate(200),new Rate(200));
        assertTrue(!isOutside(d_,area_,delta_));
    }
    @Test
    public void isOutside5() {
        DeltaSelectionUnit d_ = new DeltaSelectionUnit();
        d_.setLocx(new Rate(210));
        d_.setLocy(new Rate(210));
        Delta delta_ = new Delta();
        delta_.setDx(new Rate(0));
        delta_.setDy(new Rate(1));
        Rect area_ = new Rect(new Rate(30),new Rate(30),new Rate(200),new Rate(200));
        assertTrue(!isOutside(d_,area_,delta_));
    }
    @Test
    public void getDelta1() {
        DeltaSelectionUnit d_ = new DeltaSelectionUnit();
        d_.setLocx(new Rate(210));
        d_.setLocy(new Rate(210));
        d_.setDestx(new Rate(200));
        d_.setDesty(new Rate(215));
        Delta dm_ = d_.getDelta();
        assertEq(Rate.minusOne(),dm_.getDx());
        assertEq(Rate.zero(),dm_.getDy());
    }
    @Test
    public void getDelta2() {
        DeltaSelectionUnit d_ = new DeltaSelectionUnit();
        d_.setLocx(new Rate(210));
        d_.setLocy(new Rate(210));
        d_.setDestx(new Rate(225));
        d_.setDesty(new Rate(200));
        Delta dm_ = d_.getDelta();
        assertEq(Rate.one(),dm_.getDx());
        assertEq(Rate.zero(),dm_.getDy());
    }
    @Test
    public void getDelta3() {
        DeltaSelectionUnit d_ = new DeltaSelectionUnit();
        d_.setLocx(new Rate(210));
        d_.setLocy(new Rate(210));
        d_.setDestx(new Rate(220));
        d_.setDesty(new Rate(230));
        Delta dm_ = d_.getDelta();
        assertEq(Rate.zero(),dm_.getDx());
        assertEq(Rate.one(),dm_.getDy());
    }
    @Test
    public void getDelta4() {
        DeltaSelectionUnit d_ = new DeltaSelectionUnit();
        d_.setLocx(new Rate(210));
        d_.setLocy(new Rate(210));
        d_.setDestx(new Rate(220));
        d_.setDesty(new Rate(190));
        Delta dm_ = d_.getDelta();
        assertEq(Rate.zero(),dm_.getDx());
        assertEq(Rate.minusOne(),dm_.getDy());
    }
    @Test
    public void getDelta5() {
        DeltaSelectionUnit d_ = new DeltaSelectionUnit();
        d_.setLocx(new Rate(210));
        d_.setLocy(new Rate(210));
        d_.setDestx(new Rate(210));
        d_.setDesty(new Rate(230));
        Delta dm_ = d_.getDelta();
        assertEq(Rate.zero(),dm_.getDx());
        assertEq(Rate.one(),dm_.getDy());
    }
    @Test
    public void getDelta6() {
        DeltaSelectionUnit d_ = new DeltaSelectionUnit();
        d_.setLocx(new Rate(210));
        d_.setLocy(new Rate(210));
        d_.setDestx(new Rate(210));
        d_.setDesty(new Rate(190));
        Delta dm_ = d_.getDelta();
        assertEq(Rate.zero(),dm_.getDx());
        assertEq(Rate.minusOne(),dm_.getDy());
    }
    @Test
    public void getDelta7() {
        DeltaSelectionUnit d_ = new DeltaSelectionUnit();
        d_.setLocx(new Rate(210));
        d_.setLocy(new Rate(210));
        d_.setDestx(new Rate(230));
        d_.setDesty(new Rate(210));
        Delta dm_ = d_.getDelta();
        assertEq(Rate.one(),dm_.getDx());
        assertEq(Rate.zero(),dm_.getDy());
    }
    @Test
    public void getDelta8() {
        DeltaSelectionUnit d_ = new DeltaSelectionUnit();
        d_.setLocx(new Rate(210));
        d_.setLocy(new Rate(210));
        d_.setDestx(new Rate(190));
        d_.setDesty(new Rate(210));
        Delta dm_ = d_.getDelta();
        assertEq(Rate.minusOne(),dm_.getDx());
        assertEq(Rate.zero(),dm_.getDy());
    }
    @Test
    public void getDelta9() {
        DeltaSelectionUnit d_ = new DeltaSelectionUnit();
        d_.setLocx(new Rate(210));
        d_.setLocy(new Rate(210));
        d_.setDestx(new Rate(230));
        d_.setDesty(new Rate(230));
        Delta dm_ = d_.getDelta();
        assertEq(Rate.one(),dm_.getDx());
        assertEq(Rate.one(),dm_.getDy());
    }
    @Test
    public void getDelta10() {
        DeltaSelectionUnit d_ = new DeltaSelectionUnit();
        d_.setLocx(new Rate(210));
        d_.setLocy(new Rate(210));
        d_.setDestx(new Rate(190));
        d_.setDesty(new Rate(190));
        Delta dm_ = d_.getDelta();
        assertEq(Rate.minusOne(),dm_.getDx());
        assertEq(Rate.minusOne(),dm_.getDy());
    }
    @Test
    public void getDelta11() {
        DeltaSelectionUnit d_ = new DeltaSelectionUnit();
        d_.setLocx(new Rate(210));
        d_.setLocy(new Rate(210));
        d_.setDestx(new Rate(190));
        d_.setDesty(new Rate(230));
        Delta dm_ = d_.getDelta();
        assertEq(Rate.minusOne(),dm_.getDx());
        assertEq(Rate.one(),dm_.getDy());
    }
    @Test
    public void getDelta12() {
        DeltaSelectionUnit d_ = new DeltaSelectionUnit();
        d_.setLocx(new Rate(210));
        d_.setLocy(new Rate(210));
        d_.setDestx(new Rate(230));
        d_.setDesty(new Rate(190));
        Delta dm_ = d_.getDelta();
        assertEq(Rate.one(),dm_.getDx());
        assertEq(Rate.minusOne(),dm_.getDy());
    }
    @Test
    public void getDelta13() {
        DeltaSelectionUnit d_ = new DeltaSelectionUnit();
        d_.setLocx(new Rate(210));
        d_.setLocy(new Rate(210));
        d_.setDestx(new Rate(210));
        d_.setDesty(new Rate(210));
        Delta dm_ = d_.getDelta();
        assertEq(Rate.zero(),dm_.getDx());
        assertEq(Rate.zero(),dm_.getDy());
    }
    @Test
    public void moveCamera1() {
        RatePoint rp_ = DeltaSelectionUnit.moveCamera(new RatePoint(new Rate(10), new Rate(20)), new Rate(15), new Rate(25), new Rate(200), new Rate(220));
        assertEq(new Rate(10),rp_.getXcoords());
        assertEq(new Rate(20),rp_.getYcoords());
    }
    @Test
    public void moveCamera2() {
        RatePoint rp_ = DeltaSelectionUnit.moveCamera(new RatePoint(new Rate(10), new Rate(20)), new Rate(150), new Rate(250), new Rate(200), new Rate(220));
        assertEq(new Rate(10),rp_.getXcoords());
        assertEq(new Rate(30),rp_.getYcoords());
    }
    @Test
    public void moveCamera3() {
        RatePoint rp_ = DeltaSelectionUnit.moveCamera(new RatePoint(new Rate(10), new Rate(20)), new Rate(250), new Rate(210), new Rate(200), new Rate(220));
        assertEq(new Rate(20),rp_.getXcoords());
        assertEq(new Rate(20),rp_.getYcoords());
    }
    @Test
    public void moveCamera4() {
        RatePoint rp_ = DeltaSelectionUnit.moveCamera(new RatePoint(new Rate(150), new Rate(210)), new Rate(10), new Rate(210), new Rate(200), new Rate(220));
        assertEq(new Rate(140),rp_.getXcoords());
        assertEq(new Rate(210),rp_.getYcoords());
    }
    @Test
    public void moveCamera5() {
        RatePoint rp_ = DeltaSelectionUnit.moveCamera(new RatePoint(new Rate(150), new Rate(210)), new Rate(150), new Rate(10), new Rate(200), new Rate(220));
        assertEq(new Rate(150),rp_.getXcoords());
        assertEq(new Rate(200),rp_.getYcoords());
    }
    @Test
    public void moveCamera6() {
        RatePoint rp_ = DeltaSelectionUnit.moveCamera(new RatePoint(new Rate(10), new Rate(20)), new Rate(250), new Rate(250), new Rate(200), new Rate(220));
        assertEq(new Rate(10),rp_.getXcoords());
        assertEq(new Rate(20),rp_.getYcoords());
    }
    @Test
    public void moveCamera7() {
        RatePoint rp_ = DeltaSelectionUnit.moveCamera(new RatePoint(new Rate(10), new Rate(20)), new Rate(250), new Rate(5), new Rate(200), new Rate(220));
        assertEq(new Rate(10),rp_.getXcoords());
        assertEq(new Rate(20),rp_.getYcoords());
    }
    @Test
    public void moveCamera8() {
        RatePoint rp_ = DeltaSelectionUnit.moveCamera(new RatePoint(new Rate(10), new Rate(20)), new Rate(5), new Rate(10), new Rate(200), new Rate(220));
        assertEq(new Rate(10),rp_.getXcoords());
        assertEq(new Rate(20),rp_.getYcoords());
    }
    @Test
    public void selected1() {
        DeltaSelectionUnit d_ = new DeltaSelectionUnit();
        d_.setSelected(true);
        assertTrue(d_.isSelected());
    }
    @Test
    public void selected2() {
        DeltaSelectionUnit d_ = new DeltaSelectionUnit();
        d_.setSelected(false);
        assertTrue(!d_.isSelected());
    }
    private boolean isOutside(DeltaSelectionUnit _o,Rect _p, Delta _d) {
        return _o.isOutside(_p,_d,new Rect(Rate.zero(),Rate.zero(),new Rate(32),new Rate(32)));
    }
}
