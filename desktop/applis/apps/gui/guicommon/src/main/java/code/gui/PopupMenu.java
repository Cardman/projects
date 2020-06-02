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

    public void show(CustComponent panel, int i, int height) {
        popupMenu.show(panel.getComponent(),i,height);
    }

    public void show(int i, int height) {
        popupMenu.show(null,i,height);
    }
    public void add(CustComponent global) {
        popupMenu.add(global.getComponent());
    }
    public void add(Menu global) {
        popupMenu.add(global.getMenu());
    }
    public void add(MenuItem global) {
        popupMenu.add(global.getMenu());
    }
    public void add(CheckBoxMenuItem global) {
        popupMenu.add(global.getMenu());
    }
    public void remove(CustComponent global) {
        popupMenu.remove(global.getComponent());
    }
    public void remove(Menu global) {
        popupMenu.remove(global.getMenu());
    }
    public void remove(MenuItem global) {
        popupMenu.remove(global.getMenu());
    }
    public void remove(CheckBoxMenuItem global) {
        popupMenu.remove(global.getMenu());
    }
}
