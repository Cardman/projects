package code.vi.sys.impl.gui;
import code.gui.*;
import code.util.IdList;

import javax.swing.JMenu;

public final class Menu implements AbsMenu {

    private AbsMenu parentMenu;
    private final IdList<EnabledMenu> subs = new IdList<EnabledMenu>();

    private final JMenu meCo;

    public Menu() {
        meCo = new JMenu();
    }

    public Menu(String _s, boolean _b) {
        meCo = new JMenu(_s, _b);
    }

    public Menu(String _s) {
        meCo = new JMenu(_s);
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

    public void addMenuItem(AbsCheckBoxMenuItem _menuItem) {
        _menuItem.setParentMenu(this);
        meCo.add(((CheckBoxMenuItem)_menuItem).getMenu());
        subs.add(_menuItem);
    }
    public void addMenuItem(AbsMenuItem _menuItem) {
        _menuItem.setParentMenu(this);
        meCo.add(((MenuItem)_menuItem).getMenu());
        subs.add(_menuItem);
    }
    public void addMenuItem(AbsMenu _menuItem) {
        _menuItem.setParentMenu(this);
        meCo.add(((Menu)_menuItem).meCo);
        subs.add(_menuItem);
    }

    public void removeMenuItem(AbsCheckBoxMenuItem _menuItem) {
        _menuItem.setParentMenu(null);
        meCo.remove(((CheckBoxMenuItem)_menuItem).getMenu());
        subs.removeObj(_menuItem);
    }
    public void removeMenuItem(AbsMenuItem _menuItem) {
        _menuItem.setParentMenu(null);
        meCo.remove(((MenuItem)_menuItem).getMenu());
        subs.removeObj(_menuItem);
    }
    public void removeMenuItem(AbsMenu _menuItem) {
        _menuItem.setParentMenu(null);
        meCo.remove(((Menu)_menuItem).meCo);
        subs.removeObj(_menuItem);
    }
    JMenu getMeCo() {
        return meCo;
    }
    @Override
    public void setEnabled(boolean _enabled) {
        meCo.setEnabled(_enabled);
    }

    @Override
    public String getText() {
        return meCo.getText();
    }

    public void setText(String _val) {
        meCo.setText(_val);
    }

    public boolean isEnabled() {
        return meCo.isEnabled();
    }

    public void addSeparator() {
        meCo.addSeparator();
    }

    public EnabledMenu getItem(int _i) {
        return MenuItemUtils.get(subs,_i);
    }

    public int getSubCount() {
        return subs.size();
    }

    public int getItemCount() {
        return meCo.getItemCount();
    }
}
