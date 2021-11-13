package code.vi.sys.impl.gui;
import code.gui.AbsMenu;
import code.gui.AbsMenuItem;
import code.gui.MenuItemUtils;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsAdvActionListener;
import code.vi.sys.impl.gui.events.WrActionListener;
import code.util.IdMap;
import code.vi.sys.impl.gui.events.WrAdvActionListener;

import javax.swing.*;

public final class MenuItem implements AbsMenuItem {

    private final IdMap<AbsActionListener,WrActionListener> mapAction = new IdMap<AbsActionListener, WrActionListener>();
    private final IdMap<AbsAdvActionListener, WrAdvActionListener> mapAdvAction = new IdMap<AbsAdvActionListener, WrAdvActionListener>();
    private AbsMenu parentMenu;

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
    public AbsMenu getParentMenu() {
        return parentMenu;
    }

    @Override
    public void setParentMenu(AbsMenu _parentMenu) {
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

    public void addActionListener(AbsAdvActionListener _showDataFightEvent) {
        WrAdvActionListener wr_ = new WrAdvActionListener(_showDataFightEvent);
        menu.addActionListener(wr_);
        mapAdvAction.addEntry(_showDataFightEvent,wr_);
    }

    public boolean isEnabled() {
        return menu.isEnabled();
    }

    public void setAccelerator(char _a) {
        menu.setAccelerator(KeyStroke.getKeyStroke(_a));
    }

    public void setAccelerator(String _a) {
        menu.setAccelerator(KeyStroke.getKeyStroke(_a));
    }

    public void setAccelerator(int _a, int _b) {
        menu.setAccelerator(KeyStroke.getKeyStroke(_a, _b));
    }
}
