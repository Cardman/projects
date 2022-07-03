package code.mock;

import code.gui.ListSelection;
import code.gui.SelectionInfo;

public final class MockListSelection implements ListSelection {
    private final int nb;
    private final MockWithListSelection withListSelection;

    public MockListSelection(int _n, MockWithListSelection _w) {
        this.nb = _n;
        this.withListSelection = _w;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        withListSelection.action(nb);
    }
}
