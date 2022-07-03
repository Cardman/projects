package code.mock;

import org.junit.Test;

public final class MockSplitPaneTest extends EquallableMockGuiUtil {
    @Test
    public void s0() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        assertTrue(((MockSplitPane)fr_.getHorizontalSplitPane()).isHorizontal());
        assertFalse(((MockSplitPane)fr_.getVerticalSplitPane()).isHorizontal());
    }
    @Test
    public void s1() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getRadioButton().setSelected(false);
        assertEq("->0",fr_.getText());
    }
    @Test
    public void s2() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getRadioButton().setSelected(true);
        assertEq("->1",fr_.getText());
        fr_.getRadioButton().setButtonGroup(fr_.getRadioButton().getButtonGroup());
        assertEq(1, ((MockRadioButton)fr_.getRadioButton()).getActionListeners().size());
        fr_.getRadioButton().setText("__");
        assertEq("__",fr_.getRadioButton().getText());
    }
    @Test
    public void s3() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        MockPlainLabel chg_ = new MockPlainLabel("");
        fr_.getHorizontalSplitPane().setRightComponent(chg_);
        assertSame(chg_,fr_.getHorizontalSplitPane().getChildren().get(1));
    }
    @Test
    public void s4() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        MockPlainLabel chg_ = new MockPlainLabel("");
        fr_.getHorizontalSplitPane().setLeftComponent(chg_);
        assertSame(chg_,fr_.getHorizontalSplitPane().getChildren().get(0));
    }
    @Test
    public void s5() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        MockPlainLabel chg_ = new MockPlainLabel("");
        fr_.getHorizontalSplitPane().setRightComponent(chg_);
        fr_.getHorizontalSplitPane().setRightComponent(chg_);
        assertSame(chg_,fr_.getHorizontalSplitPane().getChildren().get(1));
    }
    @Test
    public void s6() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        MockPlainLabel chg_ = new MockPlainLabel("");
        fr_.getHorizontalSplitPane().setLeftComponent(chg_);
        fr_.getHorizontalSplitPane().setLeftComponent(chg_);
        assertSame(chg_,fr_.getHorizontalSplitPane().getChildren().get(0));
    }
    @Test
    public void s7() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getVerticalSplitPane().setContinuousLayout(true);
        fr_.getVerticalSplitPane().setOneTouchExpandable(true);
        fr_.getVerticalSplitPane().setDividerLocation(10);
        fr_.getVerticalSplitPane().setDividerSize(5);
        assertEq(10, fr_.getVerticalSplitPane().getDividerLocation());
        assertEq(5, fr_.getVerticalSplitPane().getDividerSize());
        assertTrue(fr_.getVerticalSplitPane().isContinuousLayout());
        assertTrue(fr_.getVerticalSplitPane().isOneTouchExpandable());
    }
    @Test
    public void s8() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getProgressBar().setHorizontal(true);
        assertTrue(fr_.getProgressBar().isHorizontal());
    }
    @Test
    public void s9() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getProgressBar().setHorizontal(false);
        assertFalse(fr_.getProgressBar().isHorizontal());
    }
    @Test
    public void s10() {
        MockWithChangeListenerSample fr_ = new MockWithChangeListenerSample(init(), "");
        fr_.getProgressBar().setMinimum(0);
        fr_.getProgressBar().setValue(50);
        fr_.getProgressBar().setMaximum(100);
        assertEq(0, fr_.getProgressBar().getMinimum());
        assertEq(50,fr_.getProgressBar().getValue());
        assertEq(100,fr_.getProgressBar().getMaximum());
    }
}
