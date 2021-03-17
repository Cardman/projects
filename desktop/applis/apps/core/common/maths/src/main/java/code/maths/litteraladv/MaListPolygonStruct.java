package code.maths.litteraladv;

import code.maths.geo.Polygon;
import code.maths.geo.Triangle;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class MaListPolygonStruct implements MaAddonStruct {
    private final CustList<Polygon> polygons;

    public MaListPolygonStruct(CustList<Polygon> _polygons) {
        this.polygons = _polygons;
    }

    public CustList<Polygon> getPolygons() {
        return polygons;
    }

    @Override
    public boolean sameReferenceMath(MaStruct _other) {
        if (!(_other instanceof MaListPolygonStruct)) {
            return false;
        }
        MaListPolygonStruct oth_ = (MaListPolygonStruct) _other;
        return Polygon.eqPolygonsMath(polygons,oth_.polygons);
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        if (!(_other instanceof MaListPolygonStruct)) {
            return false;
        }
        MaListPolygonStruct oth_ = (MaListPolygonStruct) _other;
        return eqPolygons(polygons,oth_.polygons);
    }

    static boolean eqPolygons(CustList<Polygon> _this, CustList<Polygon> _other) {
        int len_ = _this.size();
        if (len_ != _other.size()) {
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
        return displayRsult(polygons);
    }

    static String displayRsult(CustList<Polygon> _polygons) {
        StringList parts_ = new StringList(new CollCapacity(_polygons.size()));
        for (Polygon r: _polygons) {
            parts_.add(MaPolygonStruct.displayRsult(r));
        }
        return "("+ StringUtil.join(parts_,",")+")";
    }
}
