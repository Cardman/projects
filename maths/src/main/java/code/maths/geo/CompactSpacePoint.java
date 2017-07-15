package code.maths.geo;


public final class CompactSpacePoint {

    private final CompactPlanePoint pair;
    private final long common;
    public CompactSpacePoint(CompactPlanePoint _pair, long _common) {
        pair = _pair;
        common = _common;
    }
    public CompactPlanePoint getPair() {
        return pair;
    }
    public long getCommon() {
        return common;
    }

}
