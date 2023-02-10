package code.mock;

import code.gui.AbsAttrSet;

public final class MockAttrSet implements AbsAttrSet {
    private int back;
    private int fore;
    private int fontSize;
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

    public int getBack() {
        return back;
    }

    public int getFontSize() {
        return fontSize;
    }

    public int getFore() {
        return fore;
    }
}
