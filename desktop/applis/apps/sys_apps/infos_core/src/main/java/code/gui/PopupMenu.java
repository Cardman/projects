package code.gui;

import javax.swing.*;

public final class PopupMenu extends CustComponent implements AbsPopupMenu {
    private final JPopupMenu popupMenu = new JPopupMenu();
    @Override
    protected JComponent getNatComponent() {
        return popupMenu;
    }

    public void show(AbsCustComponent _panel, int _i, int _height) {
        popupMenu.show(((CustComponent) _panel).getNatComponent(),_i,_height);
    }

    public void show(int _i, int _height) {
        popupMenu.show(null,_i,_height);
    }
    public void add(AbsCustComponent _global) {
        popupMenu.add(((CustComponent)_global).getNatComponent());
    }
    public void add(Menu _global) {
        popupMenu.add(_global.getMenu());
    }
    public void add(MenuItem _global) {
        popupMenu.add(_global.getMenu());
    }
    public void add(CheckBoxMenuItem _global) {
        popupMenu.add(_global.getMenu());
    }
    public void remove(AbsCustComponent _global) {
        popupMenu.remove(((CustComponent)_global).getNatComponent());
    }
    public void remove(Menu _global) {
        popupMenu.remove(_global.getMenu());
    }
    public void remove(MenuItem _global) {
        popupMenu.remove(_global.getMenu());
    }
    public void remove(CheckBoxMenuItem _global) {
        popupMenu.remove(_global.getMenu());
    }
}
