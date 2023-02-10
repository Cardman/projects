package code.gui;

public interface AbsTabStops {
    AbsTabStop getTab(int _index);
    void setTab(int _index, AbsTabStop _tab);
    int getLength();
}
