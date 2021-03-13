package code.maths.litteraladv;

import code.maths.geo.Polygon;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class MaListPolygonStruct implements MaStruct {
    private final CustList<Polygon> polygons;

    public MaListPolygonStruct(CustList<Polygon> _polygons) {
        this.polygons = _polygons;
    }

    public CustList<Polygon> getPolygons() {
        return polygons;
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        if (!(_other instanceof MaListPolygonStruct)) {
            return false;
        }
        MaListPolygonStruct oth_ = (MaListPolygonStruct) _other;
        int len_ = polygons.size();
        if (polygons.size() != oth_.polygons.size()) {
            return false;
        }
        for (int i = 0; i < len_; i++) {
            if (!polygons.get(i).eq(oth_.polygons.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String displayRsult() {
        StringList parts_ = new StringList(new CollCapacity(polygons.size()));
        for (Polygon r: polygons) {
            parts_.add(MaPolygonStruct.displayRsult(r));
        }
        return "("+ StringUtil.join(parts_,",")+")";
    }
}
