package code.mock;

import code.gui.AbsMouseLocation;

public final class MockMouseCoords implements AbsMouseLocation {
    private final int xcoord;
    private final int ycoord;

    public MockMouseCoords(int _x, int _y) {
        this.xcoord = _x;
        this.ycoord = _y;
    }

    @Override
    public int getXcoord() {
        return xcoord;
    }

    @Override
    public int getYcoord() {
        return ycoord;
    }
}
