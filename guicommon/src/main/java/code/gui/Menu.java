package code.gui;
import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Menu implements EnabledMenu {

    private Menu parentMenu;

    private JMenu menu;

    public Menu() {
        menu = new JMenu();
    }

    public Menu(Action _a) {
        menu = new JMenu(_a);
    }

    public Menu(String _s, boolean _b) {
        menu = new JMenu(_s, _b);
    }

    public Menu(String _s) {
        menu = new JMenu(_s);
    }

    @Override
    public Menu getParentMenu() {
        return parentMenu;
    }

    @Override
    public void setParentMenu(Menu _parentMenu) {
        parentMenu = _parentMenu;
    }

    public void setEnabledMenu(boolean _b) {
        setEnabled(_b);
        MenuItemUtils.setEnabled(_b, this);
    }

    public void addMenuItem(JMenuItem _menuItem) {
        if (_menuItem instanceof EnabledMenu) {
            ((EnabledMenu)_menuItem).setParentMenu(this);
        }
        menu.add(_menuItem);
    }

    public void addMenuItem(CheckBoxMenuItem _menuItem) {
        _menuItem.setParentMenu(this);
        menu.add(_menuItem.getMenu());
    }
    public void addMenuItem(MenuItem _menuItem) {
        _menuItem.setParentMenu(this);
        menu.add(_menuItem.getMenu());
    }
    public JMenu getMenu() {
        return menu;
    }
    public void addMenuItem(Menu _menuItem) {
        _menuItem.setParentMenu(this);
        menu.add(_menuItem.menu);
    }
    @Override
    public void setEnabled(boolean _enabled) {
        menu.setEnabled(_enabled);
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

    public JMenuItem getItem(int _i) {
        return menu.getItem(_i);
    }

    public int getItemCount() {
        return menu.getItemCount();
    }
}
