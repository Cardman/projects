package code.gui;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

public final class MenuItem implements EnabledMenu {

    private Menu parentMenu;

    private JMenuItem menu;

    public MenuItem() {
        menu =new JMenuItem();
    }

    public MenuItem(BufferedImage _icon) {
        menu =new JMenuItem(new ImageIcon(_icon));
    }

    public MenuItem(String _text, BufferedImage _icon) {
        menu =new JMenuItem(_text, new ImageIcon(_icon));
    }

    public MenuItem(String _text, int _mnemonic) {
        menu =new JMenuItem(_text, _mnemonic);
    }

    public MenuItem(String _text) {
        menu =new JMenuItem(_text);
    }

    JMenuItem getMenu() {
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

    @Override
    public String getText() {
        return menu.getText();
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
