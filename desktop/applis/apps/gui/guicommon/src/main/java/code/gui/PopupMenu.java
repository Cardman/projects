package code.gui;

import javax.swing.*;

public final class PopupMenu extends CustComponent {
    private JPopupMenu popupMenu = new JPopupMenu();
    @Override
    protected JComponent getComponent() {
        return popupMenu;
    }

    JPopupMenu getPopupMenu() {
        return popupMenu;
    }

    public void show(CustComponent _panel, int _i, int _height) {
        popupMenu.show(_panel.getComponent(),_i,_height);
    }

    public void show(int _i, int _height) {
        popupMenu.show(null,_i,_height);
    }
    public void add(CustComponent _global) {
        popupMenu.add(_global.getComponent());
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
    public void remove(CustComponent _global) {
        popupMenu.remove(_global.getComponent());
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
