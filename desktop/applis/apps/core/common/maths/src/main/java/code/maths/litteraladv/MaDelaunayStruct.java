package code.maths.litteraladv;

import code.maths.geo.Delaunay;
import code.maths.geo.Polygon;
import code.util.CustList;

public final class MaDelaunayStruct implements MaStruct {
    private final Delaunay delaunay;

    public MaDelaunayStruct(Delaunay _delaunay) {
        this.delaunay = _delaunay;
    }

    public Delaunay getDelaunay() {
        return delaunay;
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        if (!(_other instanceof MaDelaunayStruct)) {
            return false;
        }
        CustList<Polygon> this_ = MaListPolygonStruct.toPolygons(delaunay.getTriangles());
        CustList<Polygon> oth_ = MaListPolygonStruct.toPolygons(((MaDelaunayStruct) _other).delaunay.getTriangles());
        return MaListPolygonStruct.eqPolygons(this_,oth_);
    }

    @Override
    public String displayRsult() {
        CustList<Polygon> this_ = MaListPolygonStruct.toPolygons(delaunay.getTriangles());
        return MaListPolygonStruct.displayRsult(this_);
    }
}
