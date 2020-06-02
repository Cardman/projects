package code.gui.images;

import code.images.IntPoint;

public class IntPointPair {
    private IntPoint xcoords;
    private IntPoint ycoords;

    public IntPointPair(IntPoint _first, IntPoint _second) {
        xcoords = _first;
        ycoords = _second;
    }

    public IntPoint getXcoords() {
        return xcoords;
    }

    public IntPoint getYcoords() {
        return ycoords;
    }
}
