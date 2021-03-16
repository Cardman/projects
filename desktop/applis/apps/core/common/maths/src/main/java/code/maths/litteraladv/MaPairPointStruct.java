package code.maths.litteraladv;

import code.maths.geo.RatePoint;

public abstract class MaPairPointStruct implements MaAddonStruct {
    private final RatePoint point;

    protected MaPairPointStruct(RatePoint _point) {
        this.point = _point;
    }

    @Override
    public boolean sameReferenceMath(MaStruct _other) {
        MaPairPointStruct conv_ = conv(_other);
        boolean same_ = sameValueMath(convWide(_other));
        if (conv_ == null) {
            return false;
        }
        return same_;
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        MaPairPointStruct conv_ = conv(_other);
        if (conv_ == null) {
            return false;
        }
        return sameValue(conv_);
    }

    private MaPairPointStruct conv(MaStruct _other) {
        MaPairPointStruct othe_ = convWide(_other);
        if (othe_ == null) {
            return null;
        }
        RatePoint oth_ = othe_.getPoint();
        if (!point.eq(oth_)) {
            return null;
        }
        return (MaPairPointStruct) _other;
    }
    private static MaPairPointStruct convWide(MaStruct _other) {
        if (!(_other instanceof MaPairPointStruct)) {
            return null;
        }
        return (MaPairPointStruct) _other;
    }
    protected abstract boolean sameValueMath(MaPairPointStruct _other);
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
