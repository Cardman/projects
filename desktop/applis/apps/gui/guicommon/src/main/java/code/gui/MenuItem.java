package code.gui;
import code.gui.events.AbsActionListener;
import code.gui.events.WrActionListener;
import code.util.IdMap;

import javax.swing.*;

public final class MenuItem implements EnabledMenu {

    private final IdMap<AbsActionListener,WrActionListener> mapAction = new IdMap<AbsActionListener, WrActionListener>();
    private Menu parentMenu;

    private JMenuItem menu;

    public MenuItem() {
        menu =new JMenuItem();
    }

//    public MenuItem(AbstractImage _icon) {
//        menu =new JMenuItem(new ImageIcon(_icon.data()));
//    }

//    public MenuItem(String _text, AbstractImage _icon) {
//        menu =new JMenuItem(_text, new ImageIcon(_icon.data()));
//    }

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

    public void addActionListener(AbsActionListener _showDataFightEvent) {
        WrActionListener wr_ = new WrActionListener(_showDataFightEvent);
        menu.addActionListener(wr_);
        mapAction.addEntry(_showDataFightEvent,wr_);
    }

    public boolean isEnabled() {
        return menu.isEnabled();
    }

    public void setAccelerator(KeyStroke _keyStroke) {
        menu.setAccelerator(_keyStroke);
    }
}
