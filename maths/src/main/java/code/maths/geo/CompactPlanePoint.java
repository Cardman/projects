package code.maths.geo;
import code.util.PairNumber;

public final class CompactPlanePoint {

    private final PairNumber<Long,Long> pair;
    private final long common;
    public CompactPlanePoint(PairNumber<Long, Long> _pair, long _common) {
        pair = _pair;
        common = _common;
    }
    public PairNumber<Long, Long> getPair() {
        return pair;
    }
    public long getCommon() {
        return common;
    }

}
