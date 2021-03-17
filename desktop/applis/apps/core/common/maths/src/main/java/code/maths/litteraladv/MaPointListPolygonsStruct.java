package code.maths.litteraladv;

import code.maths.geo.Polygon;
import code.maths.geo.RatePoint;
import code.maths.geo.Triangle;
import code.util.CustList;

public final class MaPointListPolygonsStruct extends MaPairPointStruct {
    private final CustList<Triangle> nextTriangles;
    protected MaPointListPolygonsStruct(RatePoint _point, CustList<Triangle> _nextTriangles) {
        super(_point);
        nextTriangles = _nextTriangles;
    }

    public CustList<Triangle> getNextTriangles() {
        return nextTriangles;
    }

    @Override
    public boolean sameReferenceMath(MaStruct _other) {
        if (!(_other instanceof MaPointListPolygonsStruct)) {
            return false;
        }
        CustList<Triangle> edgesOth_ = ((MaPointListPolygonsStruct) _other).nextTriangles;
        return Polygon.eqPolygonsMath(getPoint(),MaListPolygonStruct.toPolygons(nextTriangles),
                ((MaPointListPolygonsStruct) _other).getPoint(),MaListPolygonStruct.toPolygons(edgesOth_));
    }

    @Override
    protected boolean sameValue(MaPairPointStruct _other) {
        if (!(_other instanceof MaPointListPolygonsStruct)) {
            return false;
        }
        CustList<Triangle> edgesOth_ = ((MaPointListPolygonsStruct) _other).nextTriangles;
        return MaListPolygonStruct.eqPolygons(MaListPolygonStruct.toPolygons(nextTriangles),MaListPolygonStruct.toPolygons(edgesOth_));
    }

    @Override
    protected String displayValue() {
        return MaListPolygonStruct.displayRsult(MaListPolygonStruct.toPolygons(nextTriangles));
    }
}
