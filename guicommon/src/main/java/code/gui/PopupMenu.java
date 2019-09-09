package code.gui;

import javax.swing.*;

public final class PopupMenu extends CustComponent {
    private JPopupMenu popupMenu = new JPopupMenu();
    @Override
    public JComponent getComponent() {
        return popupMenu;
    }

    JPopupMenu getPopupMenu() {
        return popupMenu;
    }

    public void setVisible(boolean b) {
        popupMenu.setVisible(b);
    }

    public void show(CustComponent panel, int i, int height) {
        popupMenu.show(panel.getComponent(),i,height);
    }

    public void add(CustComponent global) {
        popupMenu.add(global.getComponent());
    }
}
