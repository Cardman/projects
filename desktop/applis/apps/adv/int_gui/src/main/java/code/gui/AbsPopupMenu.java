package code.gui;

public interface AbsPopupMenu extends AbsCustComponent {
    void show(AbsCustComponent _panel, int _i, int _height);
    void show(int _i, int _height);
    void add(AbsCustComponent _global);
    void remove(AbsCustComponent _global);
}
