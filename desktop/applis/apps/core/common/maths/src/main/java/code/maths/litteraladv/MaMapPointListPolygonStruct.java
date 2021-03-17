package code.maths.litteraladv;

import code.maths.geo.Polygon;
import code.maths.geo.RatePoint;
import code.maths.geo.Triangle;
import code.util.*;
import code.util.core.StringUtil;

public final class MaMapPointListPolygonStruct implements MaAddonStruct {
    private final IdMap<RatePoint,CustList<Triangle>> nextTriangles;

    public MaMapPointListPolygonStruct(IdMap<RatePoint,CustList<Triangle>> _nextPoints) {
        this.nextTriangles = _nextPoints;
    }
    public CustList<Triangle> getNextTriangles(RatePoint _point) {
        return Polygon.getNextTriangles(nextTriangles,_point);
    }
    public IdMap<RatePoint,CustList<Triangle>> getNextTriangles() {
        return nextTriangles;
    }

    @Override
    public boolean sameReferenceMath(MaStruct _other) {
        if (!(_other instanceof MaMapPointListPolygonStruct)) {
            return false;
        }
        MaMapPointListPolygonStruct oth_ = (MaMapPointListPolygonStruct) _other;
        return Polygon.eqPolygonsMath(nextTriangles, oth_.nextTriangles);
    }
    @Override
    public boolean sameReference(MaStruct _other) {
        if (!(_other instanceof MaMapPointListPolygonStruct)) {
            return false;
        }
        MaMapPointListPolygonStruct oth_ = (MaMapPointListPolygonStruct) _other;
        return eqEdges(nextTriangles,oth_.nextTriangles);
    }

    static boolean eqEdges(IdMap<RatePoint,CustList<Triangle>> _this,IdMap<RatePoint,CustList<Triangle>> _other) {
        int len_ = _this.size();
        if (_other.size() != len_) {
            return false;
        }
        for (int i = 0; i < len_; i++) {
            if (!_this.getKey(i).eq(_other.getKey(i))) {
                return false;
            }
            CustList<Polygon> polsThis_ = Polygon.toPolygons(_this.getValue(i));
            CustList<Polygon> polsOther_ = Polygon.toPolygons(_other.getValue(i));
            if (!MaListPolygonStruct.eqPolygons(polsThis_,polsOther_)) {
                return false;
            }
        }
        return true;
    }
    @Override
    public String displayRsult() {
        return displayRsult(nextTriangles);
    }

    static String displayRsult(IdMap<RatePoint,CustList<Triangle>> _edges) {
        StringList edges_ = new StringList(new CollCapacity(_edges.size()));
        for (EntryCust<RatePoint,CustList<Triangle>> e: _edges.entryList()) {
            CustList<Polygon> pols_ = Polygon.toPolygons(e.getValue());
            edges_.add(MaRatePointStruct.displayRsult(e.getKey())+":"+MaListPolygonStruct.displayRsult(pols_));
        }
        return "("+ StringUtil.join(edges_,",")+")";
    }

}
