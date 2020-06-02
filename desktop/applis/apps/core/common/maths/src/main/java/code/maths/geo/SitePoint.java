package code.maths.geo;


public final class SitePoint implements Site {

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

    @Override
    public SiteInfo getInfo() {
        return info;
    }

}
