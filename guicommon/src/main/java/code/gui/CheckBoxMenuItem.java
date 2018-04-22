package code.gui;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.KeyStroke;

public class CheckBoxMenuItem implements EnabledMenu {

    private Menu parentMenu;
    private JCheckBoxMenuItem menu;

    public CheckBoxMenuItem() {
        menu = new JCheckBoxMenuItem();
    }

    public CheckBoxMenuItem(Icon _icon) {
        menu = new JCheckBoxMenuItem(_icon);
    }

    public CheckBoxMenuItem(String _text) {
        menu = new JCheckBoxMenuItem(_text);
    }

    public CheckBoxMenuItem(Action _a) {
        menu = new JCheckBoxMenuItem(_a);
    }

    public CheckBoxMenuItem(String _text, Icon _icon) {
        menu = new JCheckBoxMenuItem(_text, _icon);
    }

    public CheckBoxMenuItem(String _text, boolean _b) {
        menu = new JCheckBoxMenuItem(_text, _b);
    }

    public CheckBoxMenuItem(String _text, Icon _icon, boolean _b) {
        menu = new JCheckBoxMenuItem(_text, _icon, _b);
    }

    public JCheckBoxMenuItem getMenu() {
        return menu;
    }

    @Override
    public Menu getParentMenu() {
        return parentMenu;
    }

    @Override
    public void setParentMenu(Menu _parentMenu) {
        parentMenu = _parentMenu;
    }

    public void setEnabledMenu(boolean _b) {
        setEnabled(_b);
        MenuItemUtils.setEnabled(_b, this);
    }

    @Override
    public void setEnabled(boolean _enabled) {
        menu.setEnabled(_enabled);
    }

    public void setText(String _val) {
        menu.setText(_val);
    }

    public void addActionListener(ActionListener _pauseEvent) {
        menu.addActionListener(_pauseEvent);
    }

    public void setAccelerator(KeyStroke _keyStroke) {
        menu.setAccelerator(_keyStroke);
    }
}
