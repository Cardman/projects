package code.mock;

import org.junit.Test;

public final class MockTextPaneTest extends EquallableMockGuiUtil {
    @Test
    public void f1() {
        MockTextPane t_ = (MockTextPane) init().getCompoFactory().newTextPane();
        t_.setFontSize(12);
        t_.registerKeyboardAction(new MockAdvAbstractAction(new MockAdvAction(0,new MockWithAdvActionSample())),0,0);
        assertEq(1, t_.getActions().size());
        assertEq(1, t_.getKeysAction().size());
    }
    @Test
    public void f2() {
        MockTextPane t_ = (MockTextPane) init().getCompoFactory().newTextPane();
        MockWithAdvActionSample e_ = new MockWithAdvActionSample();
        t_.registerKeyboardAction(new MockAdvAbstractAction(new MockAdvAction(0, e_)),0,0);
        ((MockAdvAbstractAction)t_.getAction(0,0)).action(null,null);
        assertTrue(e_.isAct());
    }
    @Test
    public void f3() {
        MockTextPane t_ = (MockTextPane) init().getCompoFactory().newTextPane();
        MockWithAdvActionSample e_ = new MockWithAdvActionSample();
        MockAdvAbstractAction ac_ = new MockAdvAbstractAction(new MockAdvAction(0, e_));
        t_.registerKeyboardAction(ac_,0,0);
        ac_.setEnabled(false);
        ((MockAdvAbstractAction)t_.getAction(0,0)).action(null,null);
        assertFalse(e_.isAct());
    }
}
