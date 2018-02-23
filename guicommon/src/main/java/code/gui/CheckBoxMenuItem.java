package code.gui;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JCheckBoxMenuItem;

public class CheckBoxMenuItem extends JCheckBoxMenuItem implements EnabledMenu {

    private Menu parentMenu;

    public CheckBoxMenuItem() {
    }

    public CheckBoxMenuItem(Icon _icon) {
        super(_icon);
    }

    public CheckBoxMenuItem(String _text) {
        super(_text);
    }

    public CheckBoxMenuItem(Action _a) {
        super(_a);
    }

    public CheckBoxMenuItem(String _text, Icon _icon) {
        super(_text, _icon);
    }

    public CheckBoxMenuItem(String _text, boolean _b) {
        super(_text, _b);
    }

    public CheckBoxMenuItem(String _text, Icon _icon, boolean _b) {
        super(_text, _icon, _b);
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
}
