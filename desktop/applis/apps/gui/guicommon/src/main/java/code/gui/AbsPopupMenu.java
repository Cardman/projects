package code.gui;

public interface AbsPopupMenu extends AbsCustComponent {
    void show(AbsCustComponent _panel, int _i, int _height);
    void show(int _i, int _height);
    void add(AbsCustComponent _global);
    void add(Menu _global);
    void add(MenuItem _global);
    void add(CheckBoxMenuItem _global);
    void remove(AbsCustComponent _global);
    void remove(Menu _global);
    void remove(MenuItem _global);
    void remove(CheckBoxMenuItem _global);
}
