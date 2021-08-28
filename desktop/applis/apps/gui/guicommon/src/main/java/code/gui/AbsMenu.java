package code.gui;

public interface AbsMenu extends EnabledMenu {
    int getSubCount();

    EnabledMenu getItem(int _i);

    void addMenuItem(AbsMenuItem _quit);

    void addSeparator();

    void addMenuItem(AbsCheckBoxMenuItem _pause);

    void addMenuItem(AbsMenu _edit);

    void removeMenuItem(AbsMenu _component);

    void removeMenuItem(AbsMenuItem _component);

    void removeMenuItem(AbsCheckBoxMenuItem _component);

    int getItemCount();
}
