package code.mock;

import org.junit.Test;

public final class MockSliderTest extends EquallableMockGuiUtil{
    @Test
    public void s1() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getSlider().setValue(4);
        assertEq("4", fr_.getText());
        assertEq(4,fr_.getSlider().getValue());
    }
    @Test
    public void s2() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getSlider().setValue(5);
        assertEq("", fr_.getText());
    }
    @Test
    public void s3() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getSlider().setMaximum(8);
        assertEq("5", fr_.getText());
        assertEq(8,fr_.getSlider().getMaximum());
    }
    @Test
    public void s4() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getSlider().setMinimum(2);
        assertEq("5", fr_.getText());
        assertEq(2,fr_.getSlider().getMinimum());
    }
    @Test
    public void s5() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getSlider().setMaximum(7);
        assertEq("", fr_.getText());
        assertEq(7,fr_.getSlider().getMaximum());
    }
    @Test
    public void s6() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getSlider().setMinimum(3);
        assertEq("", fr_.getText());
        assertEq(3,fr_.getSlider().getMinimum());
    }
    @Test
    public void s7() {
        MockSlider sl_ = new MockSlider();
        assertEq(0,sl_.getMinimum());
        assertEq(50,sl_.getValue());
        assertEq(100,sl_.getMaximum());
    }
    @Test
    public void s8() {
        MockSlider sl_ = new MockSlider(25,75);
        assertEq(25,sl_.getMinimum());
        assertEq(50,sl_.getValue());
        assertEq(75,sl_.getMaximum());
    }
    @Test
    public void s9() {
        MockSlider sl_ = new MockSlider(0,25,75,50);
        assertEq(25,sl_.getMinimum());
        assertEq(50,sl_.getValue());
        assertEq(75,sl_.getMaximum());
        assertEq(0,sl_.getOrientation());
    }
    @Test
    public void s10() {
        MockSlider sl_ = new MockSlider(1,25,75,50);
        assertEq(25,sl_.getMinimum());
        assertEq(50,sl_.getValue());
        assertEq(75,sl_.getMaximum());
        assertEq(1,sl_.getOrientation());
    }
    @Test
    public void s11() {
        MockSlider sl_ = new MockSlider(0);
        assertEq(0,sl_.getMinimum());
        assertEq(50,sl_.getValue());
        assertEq(100,sl_.getMaximum());
        assertEq(0,sl_.getOrientation());
    }
    @Test
    public void s12() {
        MockSlider sl_ = new MockSlider(1);
        assertEq(0,sl_.getMinimum());
        assertEq(50,sl_.getValue());
        assertEq(100,sl_.getMaximum());
        assertEq(1,sl_.getOrientation());
    }
}
