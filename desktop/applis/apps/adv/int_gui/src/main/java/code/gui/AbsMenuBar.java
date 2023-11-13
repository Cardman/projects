package code.gui;

public interface AbsMenuBar extends AbsCustComponent {
    int getMenuCount();

    void add(EnabledMenu _file);

    void remove(EnabledMenu _component);
}
