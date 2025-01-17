package code.gui;

public interface AbsAttrSet {
    void addBackground(int _color);
    void addForeground(int _color);
    void addFontSize(int _color);
    void addFontFamily(String _color);
    void addItalic(boolean _color);
    void addBold(boolean _color);
    void addUnderline(boolean _color);
    void addTabs(AbsTabStops _tabs);
}
