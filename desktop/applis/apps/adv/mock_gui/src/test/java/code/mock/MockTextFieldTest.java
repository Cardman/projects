package code.mock;

import code.gui.images.MetaDimension;
import org.junit.Test;

public final class MockTextFieldTest extends EquallableMockGuiUtil {
    @Test
    public void f1() {
        MockTextField t_ = new MockTextField();
        t_.addAutoComplete(new MockAutoCompleteListener(t_));
        t_.setText("length");
        assertEq(6,t_.getCaretPosition());
        assertEq(1, t_.getAutoCompleteListeners().size());
    }
    @Test
    public void f2() {
        MockTextField t_ = new MockTextField("length");
        MockAutoCompleteListener l_ = new MockAutoCompleteListener(t_);
        t_.addAutoComplete(l_);
        l_.changedUpdate();
        assertEq(6,t_.getCaretPosition());
    }
    @Test
    public void f3() {
        MockTextField t_ = new MockTextField("length");
        MockAutoCompleteListener l_ = new MockAutoCompleteListener(t_);
        t_.addAutoComplete(l_);
        l_.insertUpdate();
        assertEq(6,t_.getCaretPosition());
    }
    @Test
    public void f4() {
        MockTextField t_ = new MockTextField("length");
        MockAutoCompleteListener l_ = new MockAutoCompleteListener(t_);
        t_.addAutoComplete(l_);
        l_.removeUpdate();
        assertEq(6,t_.getCaretPosition());
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
        t_.addActionListener(new MockAction(0,null));
        assertEq(1,t_.getAbsActionListeners().size());
    }
    @Test
    public void f8() {
        MockTextField t_ = new MockTextField("",16);
        t_.addActionListener(new MockAdvAction(0,null));
        assertEq(1,t_.getAbsAdvActionListeners().size());
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
        t_.requestFocus();
        t_.setSize(new MetaDimension(100,100));
        assertTrue(t_.isAccessible());
    }
    @Test
    public void f13() {
        MockTextField t_ = new MockTextField("",16);
        t_.setFocusable(false);
        t_.requestFocus();
        t_.setSize(new MetaDimension(100,100));
        assertFalse(t_.isAccessible());
    }
}
