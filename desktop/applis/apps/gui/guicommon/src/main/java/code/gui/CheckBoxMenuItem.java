package code.gui;
import code.gui.images.AbstractImage;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class CheckBoxMenuItem implements EnabledMenu {

    private Menu parentMenu;
    private JCheckBoxMenuItem menu;

    public CheckBoxMenuItem() {
        menu = new JCheckBoxMenuItem();
    }

//    public CheckBoxMenuItem(AbstractImage _icon) {
//        menu = new JCheckBoxMenuItem(new ImageIcon(_icon.data()));
//    }

    public CheckBoxMenuItem(String _text) {
        menu = new JCheckBoxMenuItem(_text);
    }

//    public CheckBoxMenuItem(String _text, AbstractImage _icon) {
//        menu = new JCheckBoxMenuItem(_text, new ImageIcon(_icon.data()));
//    }

    public CheckBoxMenuItem(String _text, boolean _b) {
        menu = new JCheckBoxMenuItem(_text, _b);
    }

//    public CheckBoxMenuItem(String _text, AbstractImage _icon, boolean _b) {
//        menu = new JCheckBoxMenuItem(_text, new ImageIcon(_icon.data()), _b);
//    }

    JCheckBoxMenuItem getMenu() {
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
    public boolean isEnabled() {
        return menu.isEnabled();
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

    public boolean isSelected() {
        return menu.isSelected();
    }
    public void setSelected(boolean _selected) {
        menu.setSelected(_selected);
    }
    public void addActionListener(ActionListener _pauseEvent) {
        menu.addActionListener(_pauseEvent);
    }

    public void setAccelerator(KeyStroke _keyStroke) {
        menu.setAccelerator(_keyStroke);
    }
}
