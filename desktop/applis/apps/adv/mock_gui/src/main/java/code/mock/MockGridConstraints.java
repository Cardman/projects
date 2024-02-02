package code.mock;

import code.gui.AbsGridConstraints;

public final class MockGridConstraints implements AbsGridConstraints {
    private int gridwidth = 1;
    @Override
    public int gridwidth() {
        return gridwidth;
    }

    @Override
    public void gridwidth(int _w) {
        gridwidth = _w;
    }
}
