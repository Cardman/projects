package code.gui;

import code.gui.events.MockProgramInfosSecSample;
import org.junit.Test;

public final class MenuItemUtilsTest extends EquallableGuiFctUtil {
    @Test
    public void setEnabledMenu1() {
        MockProgramInfosSecSample init_ = init();
        AbsMenu p_ = init_.getCompoFactory().newMenu("0");
        p_.setEnabled(false);
        AbsMenuItem s_ = init_.getCompoFactory().newMenuItem("0");
        s_.setEnabled(false);
        p_.addMenuItem(s_);
        MenuItemUtils.setEnabledMenu(s_,true);
        assertTrue(s_.isEnabled());
        assertTrue(p_.isEnabled());
    }
    @Test
    public void setEnabledMenu2() {
        MockProgramInfosSecSample init_ = init();
        AbsMenu p_ = init_.getCompoFactory().newMenu("0");
        p_.setEnabled(true);
        AbsMenuItem s_ = init_.getCompoFactory().newMenuItem("0");
        s_.setEnabled(true);
        p_.addMenuItem(s_);
        MenuItemUtils.setEnabledMenu(s_,false);
        assertFalse(s_.isEnabled());
        assertFalse(p_.isEnabled());
    }
    @Test
    public void setEnabledMenu3() {
        MockProgramInfosSecSample init_ = init();
        AbsMenu p_ = init_.getCompoFactory().newMenu("0");
        p_.setEnabled(true);
        AbsMenuItem s_ = init_.getCompoFactory().newMenuItem("0");
        s_.setEnabled(true);
        p_.addMenuItem(s_);
        AbsMenuItem t_ = init_.getCompoFactory().newMenuItem("1");
        t_.setEnabled(true);
        p_.addMenuItem(t_);
        MenuItemUtils.setEnabledMenu(s_,false);
        assertFalse(s_.isEnabled());
        assertTrue(p_.isEnabled());
        assertTrue(t_.isEnabled());
    }
}
