package code.maths.litteraladv;

import code.maths.geo.RatePoint;
import code.util.IdList;

public final class MaPointListPointsStruct extends MaPairPointStruct {
    private final IdList<RatePoint> nextPoints;
    protected MaPointListPointsStruct(RatePoint _point, IdList<RatePoint> _nextPoints) {
        super(_point);
        nextPoints = _nextPoints;
    }

    public IdList<RatePoint> getNextPoints() {
        return nextPoints;
    }

    @Override
    protected boolean sameValueMath(MaPairPointStruct _other) {
        if (!(_other instanceof MaPointListPointsStruct)) {
            return false;
        }
        IdList<RatePoint> edgesOth_ = ((MaPointListPointsStruct) _other).nextPoints;
        return MaRatePointStruct.eqPtsMath(nextPoints,edgesOth_);
    }

    @Override
    protected boolean sameValue(MaPairPointStruct _other) {
        if (!(_other instanceof MaPointListPointsStruct)) {
            return false;
        }
        IdList<RatePoint> edgesOth_ = ((MaPointListPointsStruct) _other).nextPoints;
        return MaListPointStruct.eqPts(nextPoints,edgesOth_);
    }

    @Override
    protected String displayValue() {
        return MaListPointStruct.displayRsult(nextPoints);
    }
}
