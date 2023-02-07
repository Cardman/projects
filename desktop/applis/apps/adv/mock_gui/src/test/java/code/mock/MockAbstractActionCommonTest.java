package code.mock;

import code.gui.events.AbsActionListener;
import code.gui.events.AbsAdvActionListener;
import code.gui.events.AbsEnabledAction;
import org.junit.Test;

public final class MockAbstractActionCommonTest extends EquallableMockGuiUtil {
    @Test
    public void t1() {
        MockProgramInfosSample i_ = init();
        MockWithActionSample e_ = new MockWithActionSample();
        AbsEnabledAction a_ = i_.getCompoFactory().wrap(new MockAction(1, e_));
        assertTrue(a_.isEnabled());
        ((AbsActionListener)a_).action();
        assertEq(1,e_.getNb());
    }
    @Test
    public void t2() {
        MockProgramInfosSample i_ = init();
        MockWithActionSample e_ = new MockWithActionSample();
        AbsEnabledAction a_ = i_.getCompoFactory().wrap(new MockAction(1, e_));
        a_.setEnabled(true);
        assertTrue(a_.isEnabled());
        ((AbsActionListener)a_).action();
        assertEq(1,e_.getNb());
    }
    @Test
    public void t3() {
        MockProgramInfosSample i_ = init();
        MockWithActionSample e_ = new MockWithActionSample();
        AbsEnabledAction a_ = i_.getCompoFactory().wrap(new MockAction(1, e_));
        a_.setEnabled(false);
        assertFalse(a_.isEnabled());
        ((AbsActionListener)a_).action();
        assertEq(0,e_.getNb());
    }
    @Test
    public void t4() {
        MockProgramInfosSample i_ = init();
        MockWithAdvActionSample e_ = new MockWithAdvActionSample();
        AbsEnabledAction a_ = i_.getCompoFactory().wrap(new MockAdvAction(1, e_));
        assertTrue(a_.isEnabled());
        ((AbsAdvActionListener)a_).action(null,null);
        assertTrue(e_.isAct());
    }
    @Test
    public void t5() {
        MockProgramInfosSample i_ = init();
        MockWithAdvActionSample e_ = new MockWithAdvActionSample();
        AbsEnabledAction a_ = i_.getCompoFactory().wrap(new MockAdvAction(1, e_));
        a_.setEnabled(true);
        assertTrue(a_.isEnabled());
        ((AbsAdvActionListener)a_).action(null,null);
        assertTrue(e_.isAct());
    }
    @Test
    public void t6() {
        MockProgramInfosSample i_ = init();
        MockWithAdvActionSample e_ = new MockWithAdvActionSample();
        AbsEnabledAction a_ = i_.getCompoFactory().wrap(new MockAdvAction(1, e_));
        a_.setEnabled(false);
        assertFalse(a_.isEnabled());
        ((AbsAdvActionListener)a_).action(null,null);
        assertFalse(e_.isAct());
    }
}
