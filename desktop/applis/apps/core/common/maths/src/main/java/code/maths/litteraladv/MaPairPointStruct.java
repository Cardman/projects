package code.maths.litteraladv;

import code.maths.geo.RatePoint;

public abstract class MaPairPointStruct implements MaStruct {
    private final RatePoint point;

    protected MaPairPointStruct(RatePoint _point) {
        this.point = _point;
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        if (!(_other instanceof MaPairPointStruct)) {
            return false;
        }
        RatePoint oth_ = ((MaPairPointStruct) _other).getPoint();
        if (!point.eq(oth_)) {
            return false;
        }
        return sameValue((MaPairPointStruct) _other);
    }

    protected abstract boolean sameValue(MaPairPointStruct _other);

    @Override
    public String displayRsult() {
        return "("+MaRatePointStruct.displayRsult(point)+","+displayValue()+")";
    }

    protected abstract String displayValue();

    public RatePoint getPoint() {
        return point;
    }
}
