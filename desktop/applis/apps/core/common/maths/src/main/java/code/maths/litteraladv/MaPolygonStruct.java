package code.maths.litteraladv;

import code.maths.geo.Polygon;
import code.maths.geo.RatePoint;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class MaPolygonStruct implements MaStruct {
    private final Polygon polygon;

    public MaPolygonStruct(Polygon _polygon) {
        this.polygon = _polygon;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        if (!(_other instanceof MaPolygonStruct)) {
            return false;
        }
        return polygon.eq(((MaPolygonStruct)_other).polygon);
    }

    @Override
    public String displayRsult() {
        return displayRsult(polygon);
    }

    static String displayRsult(Polygon _polygon) {
        CustList<RatePoint> points_ = _polygon.getPoints();
        StringList parts_ = new StringList(new CollCapacity(points_.size()));
        for (RatePoint r: points_) {
            parts_.add(MaRatePointStruct.displayRsult(r));
        }
        return "("+ StringUtil.join(parts_,",")+",|||)";
    }
}
