package code.mock;

import org.junit.Test;

public final class MockSpinnerTest extends EquallableMockGuiUtil{
    @Test
    public void s1() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getSpinner().setValue(4);
        assertEq("4", fr_.getText());
        assertEq(4,fr_.getSpinner().getValue());
    }
    @Test
    public void s2() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getSpinner().setValue(5);
        assertEq("", fr_.getText());
    }
    @Test
    public void s6() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getSpinner().setStep(2);
        assertEq("5", fr_.getText());
        assertEq(2,fr_.getSpinner().getStep());
    }
    @Test
    public void s7() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getSpinner().setMax(8);
        assertEq("5", fr_.getText());
        assertEq(8,fr_.getSpinner().getMax());
    }
    @Test
    public void s8() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getSpinner().setMin(2);
        assertEq("5", fr_.getText());
        assertEq(2,fr_.getSpinner().getMin());
    }
    @Test
    public void s9() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getSpinner().setMax(2);
        assertEq("", fr_.getText());
    }
    @Test
    public void s10() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getSpinner().setMin(8);
        assertEq("", fr_.getText());
    }
    @Test
    public void s11() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getSpinner().setValue(8);
        assertEq("", fr_.getText());
    }
    @Test
    public void s12() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getSpinner().setStep(1);
        assertEq("", fr_.getText());
        assertEq(1,fr_.getSpinner().getStep());
    }
    @Test
    public void s13() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getSpinner().setMax(7);
        assertEq("", fr_.getText());
        assertEq(7,fr_.getSpinner().getMax());
    }
    @Test
    public void s14() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getSpinner().setMin(3);
        assertEq("", fr_.getText());
        assertEq(3,fr_.getSpinner().getMin());
    }
    @Test
    public void s15() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getSpinner().mod(5,7,3,1);
        assertEq("", fr_.getText());
        assertEq(Integer.MIN_VALUE,fr_.getSpinner().getMin());
        assertEq(Integer.MAX_VALUE,fr_.getSpinner().getMax());
        assertEq(1,fr_.getSpinner().getStep());
        assertEq(5,fr_.getSpinner().getValue());
    }
    @Test
    public void s16() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getSpinner().updateModel(10);
        assertEq("", fr_.getText());
        assertEq(Integer.MIN_VALUE,fr_.getSpinner().getMin());
        assertEq(Integer.MAX_VALUE,fr_.getSpinner().getMax());
        assertEq(1,fr_.getSpinner().getStep());
        assertEq(5,fr_.getSpinner().getValue());
    }
    @Test
    public void s17() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getSpinner().setRange(7,3);
        assertEq("", fr_.getText());
        assertEq(Integer.MIN_VALUE,fr_.getSpinner().getMin());
        assertEq(Integer.MAX_VALUE,fr_.getSpinner().getMax());
        assertEq(1,fr_.getSpinner().getStep());
        assertEq(5,fr_.getSpinner().getValue());
    }
    @Test
    public void s18() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getSpinner().setRangeValue(5,7,3);
        assertEq("", fr_.getText());
        assertEq(Integer.MIN_VALUE,fr_.getSpinner().getMin());
        assertEq(Integer.MAX_VALUE,fr_.getSpinner().getMax());
        assertEq(1,fr_.getSpinner().getStep());
        assertEq(5,fr_.getSpinner().getValue());
    }
    @Test
    public void s19() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getSpinner().rangeValue(5,7,3);
        assertEq("", fr_.getText());
        assertEq(Integer.MIN_VALUE,fr_.getSpinner().getMin());
        assertEq(Integer.MAX_VALUE,fr_.getSpinner().getMax());
        assertEq(1,fr_.getSpinner().getStep());
        assertEq(5,fr_.getSpinner().getValue());
    }
    @Test
    public void s20() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getSpinner().updateModel();
        assertEq("", fr_.getText());
        assertEq(3,fr_.getSpinner().getMin());
        assertEq(7,fr_.getSpinner().getMax());
        assertEq(1,fr_.getSpinner().getStep());
        assertEq(5,fr_.getSpinner().getValue());
    }
}
