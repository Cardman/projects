package code.gui;

public interface AbsMenuBar {
    int getMenuCount();

    void add(AbsMenu _file);

    void remove(AbsMenu _component);
}
