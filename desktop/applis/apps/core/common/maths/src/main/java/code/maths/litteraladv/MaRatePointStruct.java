package code.maths.litteraladv;

import code.maths.Rate;
import code.maths.geo.RatePoint;
import code.util.CustList;

public final class MaRatePointStruct extends MaListNbStruct {
    private final RatePoint point;

    public MaRatePointStruct(RatePoint _point) {
        this.point = _point;
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
        return "("+x_+","+y_+","+MaOperationNode.POINT+")";
    }
}
