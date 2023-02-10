package code.mock;

import code.gui.AbsAttrSet;
import code.gui.AbsTabStops;

public final class MockAttrSet implements AbsAttrSet {
    private int back;
    private int fore;
    private int fontSize;
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
    public void addTabs(AbsTabStops _t) {
        tabs = _t;
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

    public AbsTabStops getTabs() {
        return tabs;
    }
}
