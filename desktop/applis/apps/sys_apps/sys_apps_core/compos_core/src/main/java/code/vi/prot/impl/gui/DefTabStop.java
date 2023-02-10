package code.vi.prot.impl.gui;

import code.gui.AbsTabStop;

import javax.swing.text.TabStop;

public final class DefTabStop implements AbsTabStop {
    private final TabStop tab;
    private final int value;

    public DefTabStop(int _v) {
        this.tab = new TabStop(_v);
        value = _v;
    }

    public TabStop getTab() {
        return tab;
    }

    @Override
    public int getValue() {
        return value;
    }
}
