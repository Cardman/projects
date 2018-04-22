package code.gui;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuItem implements EnabledMenu {

    private Menu parentMenu;

    private JMenuItem menu;

    public MenuItem() {
        menu =new JMenuItem();
    }

    public MenuItem(Action _a) {
        menu =new JMenuItem(_a);
    }

    public MenuItem(Icon _icon) {
        menu =new JMenuItem(_icon);
    }

    public MenuItem(String _text, Icon _icon) {
        menu =new JMenuItem(_text, _icon);
    }

    public MenuItem(String _text, int _mnemonic) {
        menu =new JMenuItem(_text, _mnemonic);
    }

    public MenuItem(String _text) {
        menu =new JMenuItem(_text);
    }

    public JMenuItem getMenu() {
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

    public void addActionListener(ActionListener _showDataFightEvent) {
        menu.addActionListener(_showDataFightEvent);
    }

    public boolean isEnabled() {
        return menu.isEnabled();
    }

    public void setAccelerator(KeyStroke _keyStroke) {
        menu.setAccelerator(_keyStroke);
    }
}
