package code.gui;

import code.util.CustList;

public interface AbsMenu extends EnabledMenu {

    CustList<EnabledMenu> getItems();

    void addMenuItem(AbsMenuItem _quit);

    void addSeparator();

    void addMenuItem(AbsCheckBoxMenuItem _pause);

    void addMenuItem(AbsMenu _edit);

    void removeMenuItem(AbsMenu _component);

    void removeMenuItem(AbsMenuItem _component);

    void removeMenuItem(AbsCheckBoxMenuItem _component);

    int getItemCount();
}
