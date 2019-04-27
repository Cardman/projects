package code.maths.geo;
import code.util.CustList;
import code.util.ints.Cmp;


public final class SitePoint implements Cmp<SitePoint> {

    private CustPoint point;

    private SiteInfo info = new SiteInfo();

    public SitePoint(CustPoint _point, CustPoint _first, VectTwoDims _vect) {
        point = _point;
        VectTwoDims vect_ = new VectTwoDims(_first, point);
        long det_ = _vect.det(vect_);
        long scal_ = _vect.scal(vect_);
        if (scal_ >= 0 && det_ <= 0) {
            info.setNumber(SiteInfo.QUAD_ONE);
        } else if (scal_ < 0 && det_ <= 0) {
            info.setNumber(SiteInfo.QUAD_TWO);
        } else if (scal_ <= 0) {
            //det_ < 0
            info.setNumber(SiteInfo.QUAD_THREE);
        } else {
            info.setNumber(SiteInfo.QUAD_FOUR);
        }
        info.setSquareLength(vect_.squareLength());
        info.setScal(scal_);
    }

    public CustPoint getPoint() {
        return point;
    }

    public int getNumber() {
        return info.getNumber();
    }

    @Override
    public boolean eq(SitePoint _g) {
        return cmp(_g) == CustList.EQ_CMP;
    }

    @Override
    public int cmp(SitePoint _o) {
        return compare(this, _o);
    }

    public static int compare(SitePoint _one, SitePoint _two) {
        return SiteInfo.compare(_one.info,_two.info);
    }
}
