package code.gui;

public interface EnabledMenu {

    void setEnabled(boolean _enabled);

    Menu getParentMenu();

    void setParentMenu(Menu _parentMenu);
}
