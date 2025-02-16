package code.mock;

import code.gui.images.MetaDimension;
import org.junit.Test;

public final class MockTextFieldTest extends EquallableMockGuiUtil {
    @Test
    public void f1() {
        MockTextField t_ = new MockTextField();
        t_.addAutoComplete(new MockAutoCompleteListener(t_));
        t_.setText("length");
        assertEq(6,t_.getSelectionEnd());
        assertEq(1, t_.getAutoCompleteListeners().size());
    }
    @Test
    public void f2() {
        MockTextField t_ = new MockTextField("length");
        MockAutoCompleteListener l_ = new MockAutoCompleteListener(t_);
        t_.addAutoComplete(l_);
        l_.changedUpdate(0,0);
        assertEq(6,t_.getSelectionEnd());
    }
    @Test
    public void f3() {
        MockTextField t_ = new MockTextField("length");
        MockAutoCompleteListener l_ = new MockAutoCompleteListener(t_);
        t_.addAutoComplete(l_);
        l_.insertUpdate(0, 0);
        assertEq(6,t_.getSelectionEnd());
    }
    @Test
    public void f4() {
        MockTextField t_ = new MockTextField("length");
        MockAutoCompleteListener l_ = new MockAutoCompleteListener(t_);
        t_.addAutoComplete(l_);
        l_.removeUpdate(0, 0);
        assertEq(6,t_.getSelectionEnd());
    }
    @Test
    public void f5() {
        MockTextField t_ = new MockTextField(16);
        assertEq(16,t_.getCols());
    }
    @Test
    public void f6() {
        MockTextField t_ = new MockTextField("",16);
        assertEq(16,t_.getCols());
    }
    @Test
    public void f7() {
        MockTextField t_ = new MockTextField("",16);
        t_.addActionListener(new MockAdvAction(0,null));
        assertEq(1,t_.getAbsAdvActionListeners().size());
    }
    @Test
    public void f8() {
        MockTextField t_ = new MockTextField("",16);
        MockAdvAction adv_ = new MockAdvAction(0, null);
        t_.addActionListener(adv_);
        t_.removeActionListener(adv_);
        assertEq(0,t_.getAbsAdvActionListeners().size());
        assertEq(0,t_.getAbsActionListeners().size());
    }
    @Test
    public void f9() {
        MockTextField t_ = new MockTextField("",16);
        t_.setEditable(false);
        assertFalse(t_.isAccessible());
        assertFalse(t_.isEnabled());
    }
    @Test
    public void f10() {
        MockTextField t_ = new MockTextField("",16);
        t_.setEditable(true);
        t_.setSize(new MetaDimension(100,100));
        assertTrue(t_.isAccessible());
        assertTrue(t_.isEnabled());
    }
    @Test
    public void f11() {
        MockTextField t_ = new MockTextField("",16);
        t_.setFocusable(false);
        t_.setSize(new MetaDimension(100,100));
        assertFalse(t_.isAccessible());
    }
    @Test
    public void f12() {
        MockTextField t_ = new MockTextField("",16);
        t_.setFocusable(false);
        t_.setFocusable(true);
        t_.requestFocusInWindow();
        t_.setSize(new MetaDimension(100,100));
        assertTrue(t_.isAccessible());
        assertTrue(t_.isFocused());
    }
    @Test
    public void f13() {
        MockTextField t_ = new MockTextField("",16);
        t_.setFocusable(false);
        t_.requestFocusInWindow();
        t_.setSize(new MetaDimension(100,100));
        assertFalse(t_.isAccessible());
        assertFalse(t_.isFocused());
    }
    @Test
    public void f14() {
        MockTextField t_ = new MockTextField();
        t_.addAutoComplete(new MockAutoCompleteListener(t_));
        t_.setText("length");
        t_.setText("length is 12");
        assertEq(12,t_.getSelectionEnd());
        assertEq(1, t_.getAutoCompleteListeners().size());
    }
    @Test
    public void f15() {
        MockTextField t_ = new MockTextField();
        t_.addAutoComplete(new MockAutoCompleteListener(t_));
        t_.setText("length");
        t_.setText("");
        assertEq(0,t_.getSelectionEnd());
        assertEq(1, t_.getAutoCompleteListeners().size());
    }
}
