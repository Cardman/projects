package code.gui;

public interface AbsPopupMenu extends AbsCustComponent {
    void show(AbsCustComponent _panel, int _i, int _height);
    void show(int _i, int _height);
    void add(AbsCustComponent _global);
    void add(AbsMenu _global);
    void add(AbsMenuItem _global);
    void add(AbsCheckBoxMenuItem _global);
    void remove(AbsCustComponent _global);
    void remove(AbsMenu _global);
    void remove(AbsMenuItem _global);
    void remove(AbsCheckBoxMenuItem _global);
}
