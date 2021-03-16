package code.maths.litteraladv;

import code.maths.geo.Delaunay;
import code.maths.geo.Polygon;
import code.util.CustList;

public final class MaDelaunayStruct implements MaAddonStruct {
    private final Delaunay delaunay;
    private final boolean withMids;

    public MaDelaunayStruct(Delaunay _delaunay,boolean _withMids) {
        this.delaunay = _delaunay;
        withMids = _withMids;
    }

    public boolean isWithMids() {
        return withMids;
    }

    public Delaunay getDelaunay() {
        return delaunay;
    }

    @Override
    public boolean sameReferenceMath(MaStruct _other) {
        if (!(_other instanceof MaDelaunayStruct)) {
            return false;
        }
        if (withMids != ((MaDelaunayStruct) _other).withMids) {
            return false;
        }
        CustList<Polygon> this_ = MaListPolygonStruct.toPolygons(delaunay.getTriangles());
        CustList<Polygon> oth_ = MaListPolygonStruct.toPolygons(((MaDelaunayStruct) _other).delaunay.getTriangles());
        return MaListPolygonStruct.eqPolygonsMath(this_,oth_);
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        if (!(_other instanceof MaDelaunayStruct)) {
            return false;
        }
        if (withMids != ((MaDelaunayStruct) _other).withMids) {
            return false;
        }
        CustList<Polygon> this_ = MaListPolygonStruct.toPolygons(delaunay.getTriangles());
        CustList<Polygon> oth_ = MaListPolygonStruct.toPolygons(((MaDelaunayStruct) _other).delaunay.getTriangles());
        return MaListPolygonStruct.eqPolygons(this_,oth_);
    }

    @Override
    public String displayRsult() {
        CustList<Polygon> this_ = MaListPolygonStruct.toPolygons(delaunay.getTriangles());
        if (withMids) {
            return "-"+MaListPolygonStruct.displayRsult(this_);
        }
        return MaListPolygonStruct.displayRsult(this_);
    }
}
