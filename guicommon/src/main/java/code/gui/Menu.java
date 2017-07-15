package code.gui;
import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Menu extends JMenu implements EnabledMenu {

    private Menu parentMenu;

    public Menu() {
    }

    public Menu(Action _a) {
        super(_a);
    }

    public Menu(String _s, boolean _b) {
        super(_s, _b);
    }

    public Menu(String _s) {
        super(_s);
    }

    @Override
    public Menu getParentMenu() {
        return parentMenu;
    }

    @Override
    public void setParentMenu(Menu _parentMenu) {
        parentMenu = _parentMenu;
    }

    @Override
    public void setEnabled(boolean _b) {
        super.setEnabled(_b);
        MenuItemUtils.setEnabled(_b, this);
    }

    @Override
    public JMenuItem add(JMenuItem _menuItem) {
        if (_menuItem instanceof EnabledMenu) {
            ((EnabledMenu)_menuItem).setParentMenu(this);
        }
        return super.add(_menuItem);
    }
}
