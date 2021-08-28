package code.gui;
import code.util.IdList;

import javax.swing.JMenu;

public final class Menu implements AbsMenu {

    private AbsMenu parentMenu;
    private final IdList<EnabledMenu> subs = new IdList<EnabledMenu>();

    private final JMenu menu;

    public Menu() {
        menu = new JMenu();
    }

    public Menu(String _s, boolean _b) {
        menu = new JMenu(_s, _b);
    }

    public Menu(String _s) {
        menu = new JMenu(_s);
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
        menu.add(((CheckBoxMenuItem)_menuItem).getMenu());
        subs.add(_menuItem);
    }
    public void addMenuItem(AbsMenuItem _menuItem) {
        _menuItem.setParentMenu(this);
        menu.add(((MenuItem)_menuItem).getMenu());
        subs.add(_menuItem);
    }
    public void addMenuItem(AbsMenu _menuItem) {
        _menuItem.setParentMenu(this);
        menu.add(((Menu)_menuItem).menu);
        subs.add(_menuItem);
    }

    public void removeMenuItem(AbsCheckBoxMenuItem _menuItem) {
        _menuItem.setParentMenu(null);
        menu.remove(((CheckBoxMenuItem)_menuItem).getMenu());
        subs.removeObj(_menuItem);
    }
    public void removeMenuItem(AbsMenuItem _menuItem) {
        _menuItem.setParentMenu(null);
        menu.remove(((MenuItem)_menuItem).getMenu());
        subs.removeObj(_menuItem);
    }
    public void removeMenuItem(AbsMenu _menuItem) {
        _menuItem.setParentMenu(null);
        menu.remove(((Menu)_menuItem).menu);
        subs.removeObj(_menuItem);
    }
    JMenu getMenu() {
        return menu;
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

    public boolean isEnabled() {
        return menu.isEnabled();
    }

    public void addSeparator() {
        menu.addSeparator();
    }

    public EnabledMenu getItem(int _i) {
        return MenuItemUtils.get(subs,_i);
    }

    public int getSubCount() {
        return subs.size();
    }

    public int getItemCount() {
        return menu.getItemCount();
    }
}
