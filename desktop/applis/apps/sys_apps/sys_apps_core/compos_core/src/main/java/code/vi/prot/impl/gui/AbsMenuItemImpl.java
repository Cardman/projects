package code.vi.prot.impl.gui;

import code.gui.AbsCustComponent;
import code.gui.EnabledMenu;
import code.util.CustList;
import code.util.IdList;

import javax.swing.*;

public abstract class AbsMenuItemImpl extends AbsComButton implements EnabledMenu {

    private final IdList<AbsCustComponent> subs = new IdList<AbsCustComponent>();

    private EnabledMenu parentMenu;
    private final JMenuItem menu;

    protected AbsMenuItemImpl(JMenuItem _inst) {
        menu = _inst;
    }

    JMenuItem getMenu() {
        return menu;
    }

    @Override
    public void addMenuItem(EnabledMenu _menuItem) {
        _menuItem.setParentMenu(this);
        getMenu().add(((AbsMenuItemImpl)_menuItem).getMenu());
        subs.add(_menuItem);
    }

    @Override
    public void addMenuItem(AbsCustComponent _menu) {
        getMenu().add(((CustComponent)_menu).getNatComponent());
        subs.add(_menu);
    }

    @Override
    public void removeMenuItem(EnabledMenu _menuItem) {
        _menuItem.setParentMenu(null);
        getMenu().remove(((AbsMenuItemImpl)_menuItem).getMenu());
        subs.removeObj(_menuItem);
    }

    @Override
    public void removeMenuItem(AbsCustComponent _component) {
        getMenu().remove(((CustComponent)_component).getNatComponent());
        subs.removeObj(_component);
    }

    @Override
    public int getItemCount() {
        return subs.size();
    }
    public CustList<AbsCustComponent> getItems() {
        return subs;
    }

    @Override
    public EnabledMenu getParentMenu() {
        return parentMenu;
    }

    @Override
    public void setParentMenu(EnabledMenu _parentMenu) {
        parentMenu = _parentMenu;
    }

    public void setEnabledMenu(boolean _b) {
        setEnabled(_b);
    }

    public boolean isSelected() {
        return menu.isSelected();
    }
    public void setSelected(boolean _selected) {
        menu.setSelected(_selected);
    }

    @Override
    protected AbstractButton button() {
        return menu;
    }

    public void setAccelerator(int _a, int _b) {
        menu.setAccelerator(KeyStroke.getKeyStroke(_a, _b));
    }
}
