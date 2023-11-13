package code.gui;

import code.util.CustList;

public interface EnabledMenu extends AbsCustComponent,AbsButton{
    boolean isSelected();

    void setSelected(boolean _value);
    void setAccelerator(int _a, int _b);

    void setEnabledMenu(boolean _b);
    EnabledMenu getParentMenu();

    void addMenuItem(AbsCustComponent _menu);
    void addMenuItem(EnabledMenu _menu);

    void removeMenuItem(AbsCustComponent _component);
    void removeMenuItem(EnabledMenu _component);

    void setParentMenu(EnabledMenu _parentMenu);
    int getItemCount();
    CustList<AbsCustComponent> getItems();
}
