package code.maths.geo;

public final class CompactPlanePoint {

    private final long xcoords;
    private final long ycoords;
    private final long common;
    public CompactPlanePoint(long _x,long _y, long _common) {
        xcoords = _x;
        ycoords = _y;
        common = _common;
    }

    public long getXcoords() {
        return xcoords;
    }

    public long getYcoords() {
        return ycoords;
    }

    public long getCommon() {
        return common;
    }

}
