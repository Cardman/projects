package code.maths.litteraladv;

import code.maths.geo.RatePoint;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class MaListPointStruct implements MaAddonStruct {
    private final CustList<RatePoint> points;

    public MaListPointStruct(CustList<RatePoint> _points) {
        this.points = _points;
    }

    public CustList<RatePoint> getPoints() {
        return points;
    }

    @Override
    public boolean sameReferenceMath(MaStruct _other) {
        if (!(_other instanceof MaListPointStruct)) {
            return false;
        }
        MaListPointStruct oth_ = (MaListPointStruct) _other;
        return RatePoint.eqPtsMath(points,oth_.points);
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        if (!(_other instanceof MaListPointStruct)) {
            return false;
        }
        MaListPointStruct oth_ = (MaListPointStruct) _other;
        return eqPts(points,oth_.points);
    }

    static boolean eqPts(CustList<RatePoint> _this,CustList<RatePoint> _other) {
        int len_ = _this.size();
        if (_other.size() != len_) {
            return false;
        }
        for (int i = 0; i < len_; i++) {
            if (!_this.get(i).eq(_other.get(i))) {
                return false;
            }
        }
        return true;
    }
    @Override
    public String displayRsult() {
        return displayRsult(points);
    }

    static String displayRsult(CustList<RatePoint> _edges) {
        StringList edges_ = new StringList(new CollCapacity(_edges.size()));
        for (RatePoint e: _edges) {
            edges_.add(MaRatePointStruct.displayRsult(e));
        }
        return "("+ StringUtil.join(edges_,",")+")";
    }

}
