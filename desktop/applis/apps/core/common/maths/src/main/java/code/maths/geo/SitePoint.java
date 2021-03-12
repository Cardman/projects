package code.maths.geo;


import code.maths.Rate;

public final class SitePoint implements Site {

    private final RatePoint point;

    private final SiteInfo info = new SiteInfo();

    public SitePoint(RatePoint _point, RatePoint _first, VectTwoDims _vect) {
        point = _point;
        VectTwoDims vect_ = new VectTwoDims(_first, point);
        Rate det_ = _vect.det(vect_);
        Rate scal_ = _vect.scal(vect_);
        if (scal_.isZeroOrGt() && det_.isZeroOrLt()) {
            info.setNumber(SiteInfo.QUAD_ONE);
        } else if (!scal_.isZeroOrGt() && det_.isZeroOrLt()) {
            info.setNumber(SiteInfo.QUAD_TWO);
        } else if (scal_.isZeroOrLt()) {
            //det_ < 0
            info.setNumber(SiteInfo.QUAD_THREE);
        } else {
            info.setNumber(SiteInfo.QUAD_FOUR);
        }
        info.setSquareLength(vect_.squareLength());
        info.setScal(scal_);
    }

    public RatePoint getPoint() {
        return point;
    }

    @Override
    public SiteInfo getInfo() {
        return info;
    }

}
