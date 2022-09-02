package code.vi.prot.impl.gui;

import code.gui.AbsMenu;
import code.gui.AbsMenuItem;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsAdvActionListener;
import code.util.IdMap;
import code.vi.prot.impl.gui.events.WrActionListener;
import code.vi.prot.impl.gui.events.WrAdvActionListener;

import javax.swing.*;

public abstract class AbsMenuItemImpl extends CustComponent implements AbsMenuItem {

    private AbsMenu parentMenu;
    private final JMenuItem menu;
    private final IdMap<AbsActionListener, WrActionListener> mapAction = new IdMap<AbsActionListener, WrActionListener>();
    private final IdMap<AbsAdvActionListener, WrAdvActionListener> mapAdvAction = new IdMap<AbsAdvActionListener, WrAdvActionListener>();

    protected AbsMenuItemImpl(JMenuItem _inst) {
        menu = _inst;
    }

    @Override
    public JComponent getNatComponent() {
        return menu;
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

    public void addActionListener(AbsActionListener _pauseEvent) {
        WrActionListener wr_ = new WrActionListener(_pauseEvent);
        menu.addActionListener(wr_);
        mapAction.addEntry(_pauseEvent,wr_);
    }

    public void addActionListener(AbsAdvActionListener _pauseEvent) {
        WrAdvActionListener wr_ = new WrAdvActionListener(_pauseEvent);
        menu.addActionListener(wr_);
        mapAdvAction.addEntry(_pauseEvent,wr_);
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
