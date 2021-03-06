package code.maths.litteraladv;

import code.maths.geo.RatePoint;
import code.util.*;
import code.util.core.StringUtil;

public final class MaMapPointListPointStruct implements MaStruct {
    private final IdMap<RatePoint, IdList<RatePoint>> nextPoints;

    public MaMapPointListPointStruct(IdMap<RatePoint, IdList<RatePoint>> _nextPoints) {
        this.nextPoints = _nextPoints;
    }
    public IdList<RatePoint> getNextPoints(RatePoint _point) {
        IdList<RatePoint> edges_ = new IdList<RatePoint>();
        for (EntryCust<RatePoint,IdList<RatePoint>> e: nextPoints.entryList()) {
            if (_point.eq(e.getKey())) {
                edges_.addAllElts(e.getValue());
            }
        }
        return edges_;
    }
    public IdMap<RatePoint, IdList<RatePoint>> getNextPoints() {
        return nextPoints;
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        if (!(_other instanceof MaMapPointListPointStruct)) {
            return false;
        }
        MaMapPointListPointStruct oth_ = (MaMapPointListPointStruct) _other;
        return eqEdges(nextPoints,oth_.nextPoints);
    }

    static boolean eqEdges(IdMap<RatePoint, IdList<RatePoint>> _this,IdMap<RatePoint, IdList<RatePoint>> _other) {
        int len_ = _this.size();
        if (_other.size() != len_) {
            return false;
        }
        for (int i = 0; i < len_; i++) {
            if (!_this.getKey(i).eq(_other.getKey(i))) {
                return false;
            }
            if (!MaListPointStruct.eqPts(_this.getValue(i),_other.getValue(i))) {
                return false;
            }
        }
        return true;
    }
    @Override
    public String displayRsult() {
        return displayRsult(nextPoints);
    }

    static String displayRsult(IdMap<RatePoint, IdList<RatePoint>> _edges) {
        StringList edges_ = new StringList(new CollCapacity(_edges.size()));
        for (EntryCust<RatePoint,IdList<RatePoint>> e: _edges.entryList()) {
            edges_.add(MaRatePointStruct.displayRsult(e.getKey())+":"+MaListPointStruct.displayRsult(e.getValue()));
        }
        return "("+ StringUtil.join(edges_,",")+")";
    }

}
