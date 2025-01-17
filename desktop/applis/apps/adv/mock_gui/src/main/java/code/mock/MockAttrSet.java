package code.mock;

import code.gui.AbsAttrSet;
import code.gui.AbsTabStops;

public final class MockAttrSet implements AbsAttrSet {
    private int back;
    private int fore;
    private int fontSize;
    private String fontFamily;
    private boolean italic;
    private boolean bold;
    private boolean underline;
    private AbsTabStops tabs = new MockTabStops(0);
    @Override
    public void addBackground(int _color) {
        back = _color;
    }

    @Override
    public void addForeground(int _color) {
        fore = _color;
    }

    @Override
    public void addFontSize(int _color) {
        fontSize = _color;
    }

    @Override
    public void addFontFamily(String _color) {
        fontFamily = _color;
    }

    @Override
    public void addTabs(AbsTabStops _t) {
        tabs = _t;
    }

    @Override
    public void addItalic(boolean _color) {
        italic = _color;
    }

    @Override
    public void addBold(boolean _color) {
        bold = _color;
    }

    @Override
    public void addUnderline(boolean _color) {
        underline = _color;
    }

    public int getBack() {
        return back;
    }

    public int getFontSize() {
        return fontSize;
    }

    public int getFore() {
        return fore;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public boolean isBold() {
        return bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public boolean isUnderline() {
        return underline;
    }

    public AbsTabStops getTabs() {
        return tabs;
    }
}
