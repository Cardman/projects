package code.sys.impl.gui;

import code.gui.*;

import javax.swing.*;

public final class PopupMenu extends CustComponent implements AbsPopupMenu {
    private final JPopupMenu popupMenu = new JPopupMenu();
    @Override
    public JComponent getNatComponent() {
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
    public void add(AbsMenu _global) {
        popupMenu.add(((Menu)_global).getMenu());
    }
    public void add(AbsMenuItem _global) {
        popupMenu.add(((MenuItem)_global).getMenu());
    }
    public void add(AbsCheckBoxMenuItem _global) {
        popupMenu.add(((CheckBoxMenuItem)_global).getMenu());
    }
    public void remove(AbsCustComponent _global) {
        popupMenu.remove(((CustComponent)_global).getNatComponent());
    }
    public void remove(AbsMenu _global) {
        popupMenu.remove(((Menu)_global).getMenu());
    }
    public void remove(AbsMenuItem _global) {
        popupMenu.remove(((MenuItem)_global).getMenu());
    }
    public void remove(AbsCheckBoxMenuItem _global) {
        popupMenu.remove(((CheckBoxMenuItem)_global).getMenu());
    }
}
