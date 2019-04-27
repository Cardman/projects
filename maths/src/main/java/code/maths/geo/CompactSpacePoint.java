package code.maths.geo;


public final class CompactSpacePoint {

    private final long xcoords;
    private final long ycoords;
    private final long zcoords;
    private final long common;
    public CompactSpacePoint(long _x,long _y,long _z, long _common) {
        xcoords = _x;
        ycoords = _y;
        zcoords = _z;
        common = _common;
    }

    public long getXcoords() {
        return xcoords;
    }

    public long getYcoords() {
        return ycoords;
    }

    public long getZcoords() {
        return zcoords;
    }
    public long getCommon() {
        return common;
    }

}
