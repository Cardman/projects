package code.maths.litteraladv;

import code.maths.Rate;
import code.maths.geo.RatePoint;
import code.util.CustList;

public final class MaRatePointStruct extends MaListNbStruct {
    private final RatePoint point;

    public MaRatePointStruct(RatePoint _point) {
        this.point = _point;
    }

    public static boolean eqPtsMath(CustList<RatePoint> _this, CustList<RatePoint> _other) {
        return contains(_this,_other) &&contains(_other, _this);
    }

    static boolean contains(CustList<RatePoint> _outer, CustList<RatePoint> _inner) {
        for (RatePoint r: _inner) {
            boolean cont_ = false;
            for (RatePoint s: _outer) {
                if (r.eq(s)) {
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

    public RatePoint getPoint() {
        return point;
    }

    @Override
    public CustList<Rate> getNumberList() {
        return new CustList<Rate>(point.getXcoords(),point.getYcoords());
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        if (!(_other instanceof MaRatePointStruct)) {
            return false;
        }
        return point.eq(((MaRatePointStruct)_other).point);
    }

    @Override
    public String displayRsult() {
        return displayRsult(point);
    }

    static String displayRsult(RatePoint _point) {
        String x_ = _point.getXcoords().display();
        String y_ = _point.getYcoords().display();
        return "("+x_+","+y_+",.)";
    }
}
