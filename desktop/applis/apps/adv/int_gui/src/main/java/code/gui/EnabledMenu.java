package code.gui;

public interface EnabledMenu extends AbsCustComponent{

    void setEnabledMenu(boolean _b);
    boolean isEnabled();
    void setEnabled(boolean _enabled);
    String getText();
    void setText(String _val);
    AbsMenu getParentMenu();

    void setParentMenu(AbsMenu _parentMenu);
}
