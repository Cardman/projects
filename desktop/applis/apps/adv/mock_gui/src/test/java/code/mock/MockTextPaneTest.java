package code.mock;

import org.junit.Test;

public final class MockTextPaneTest extends EquallableMockGuiUtil {
    @Test
    public void f1() {
        MockTextPane t_ = (MockTextPane) init().getCompoFactory().newTextPane();
        t_.setFontSize(12);
        t_.registerKeyboardAction(new MockAdvAbstractAction(new MockAdvAction(0,new MockWithAdvActionSample())),0,0);
        assertEq(1, t_.getActionsMap().size());
    }
    @Test
    public void f2() {
        MockWithAdvActionSample e_ = new MockWithAdvActionSample();
        MockAdvAbstractAction ac_ = new MockAdvAbstractAction(new MockAdvAction(0, e_));
        ac_.action(null,null);
        assertTrue(e_.isAct());
    }
    @Test
    public void f3() {
        MockWithAdvActionSample e_ = new MockWithAdvActionSample();
        MockAdvAbstractAction ac_ = new MockAdvAbstractAction(new MockAdvAction(0, e_));
        ac_.setEnabled(false);
        ac_.action(null,null);
        assertFalse(e_.isAct());
    }
    @Test
    public void f4() {
        MockTextPane t_ = (MockTextPane) init().getCompoFactory().newTextPane();
        t_.setSelectedTextColor(2);
        assertEq(2,t_.getSelectedTextColor());
    }
    @Test
    public void f5() {
        MockTextPane t_ = (MockTextPane) init().getCompoFactory().newTextPane();
        t_.setSelectionColor(2);
        assertEq(2,t_.getSelectionColor());
    }
    @Test
    public void f6() {
        MockTextPane t_ = (MockTextPane) init().getCompoFactory().newTextPane();
        t_.setCaretColor(2);
        assertEq(2,t_.getCaretColor());
    }
}
