package code.vi.prot.impl.gui;

import code.gui.AbsTabStop;
import code.gui.AbsTabStops;

import javax.swing.text.TabStop;

public final class DefTabStops implements AbsTabStops {
    private final TabStop[] value;
    private final AbsTabStop[] meta;

    public DefTabStops(int _v) {
        value = new TabStop[_v];
        meta = new AbsTabStop[_v];
    }

    @Override
    public AbsTabStop getTab(int _index) {
        return meta[_index];
    }

    @Override
    public void setTab(int _index, AbsTabStop _tab) {
        meta[_index] = _tab;
        value[_index] = ((DefTabStop)_tab).getTab();
    }

    @Override
    public int getLength() {
        return value.length;
    }

    public TabStop[] getValue() {
        return value;
    }
}
