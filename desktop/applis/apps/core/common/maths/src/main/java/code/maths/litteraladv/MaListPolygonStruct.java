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
        return eqPolygonsMath(polygons,oth_.polygons);
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        if (!(_other instanceof MaListPolygonStruct)) {
            return false;
        }
        MaListPolygonStruct oth_ = (MaListPolygonStruct) _other;
        return eqPolygons(polygons,oth_.polygons);
    }
    static CustList<Polygon> toPolygons(CustList<Triangle> _tris) {
        CustList<Polygon> list_ = new CustList<Polygon>();
        for (Triangle t: _tris) {
            list_.add(new Polygon(t));
        }
        return list_;
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
    static boolean eqPolygonsMath(CustList<Polygon> _this, CustList<Polygon> _other) {
        return contains(_this,_other)&&contains(_other, _this);
    }
    static boolean contains(CustList<Polygon> _outer, CustList<Polygon> _inner) {
        for (Polygon r: _inner) {
            boolean cont_ = false;
            for (Polygon s: _outer) {
                if (r.eqMath(s)) {
                    cont_ = true;
                    break;
                }
            }
            if (!cont_) {
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
