package code.gui;

public interface AbsMenuBar extends AbsCustComponent {
    int getMenuCount();

    void add(AbsMenu _file);

    void remove(AbsMenu _component);
}
