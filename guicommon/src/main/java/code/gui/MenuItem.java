package code.gui;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JMenuItem;

public class MenuItem extends JMenuItem implements EnabledMenu {

    private Menu parentMenu;

    public MenuItem() {
    }

    public MenuItem(Action _a) {
        super(_a);
    }

    public MenuItem(Icon _icon) {
        super(_icon);
    }

    public MenuItem(String _text, Icon _icon) {
        super(_text, _icon);
    }

    public MenuItem(String _text, int _mnemonic) {
        super(_text, _mnemonic);
    }

    public MenuItem(String _text) {
        super(_text);
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
}
