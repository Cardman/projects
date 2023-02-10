package code.mock;

import code.gui.AbsTabStop;
import code.gui.AbsTabStops;

public final class MockTabStops implements AbsTabStops {
    private final AbsTabStop[] value;

    public MockTabStops(int _v) {
        this.value = new AbsTabStop[_v];
    }

    @Override
    public AbsTabStop getTab(int _index) {
        return value[_index];
    }

    @Override
    public void setTab(int _index, AbsTabStop _tab) {
        value[_index] = _tab;
    }

    @Override
    public int getLength() {
        return value.length;
    }
}
