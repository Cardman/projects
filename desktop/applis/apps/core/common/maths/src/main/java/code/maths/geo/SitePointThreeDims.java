package code.maths.geo;


import code.maths.Rate;

public final class SitePointThreeDims implements Site {

    private final RatePointThreeDims point;

    private final SiteInfo info = new SiteInfo();

    public SitePointThreeDims(RatePointThreeDims _point, RatePointThreeDims _first, VectThreeDims _vect) {
        point = _point;
        VectThreeDims vect_ = new VectThreeDims(_first, point);
        VectThreeDims cross_ = _vect.vectProd(vect_);
        boolean negOrZero_ = cross_.getDeltaz().isZeroOrLt();
        if (cross_.getDeltaz().isZero()) {
            negOrZero_ = cross_.getDeltay().isZeroOrGt();
            if (cross_.getDeltay().isZero()) {
                negOrZero_ = cross_.getDeltax().isZeroOrLt();
            }
        }
        Rate scal_ = _vect.scal(vect_);
        if (scal_.isZeroOrGt() && negOrZero_) {
            info.setNumber(SiteInfo.QUAD_ONE);
        } else if (!scal_.isZeroOrGt() && negOrZero_) {
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

    public RatePointThreeDims getPoint() {
        return point;
    }

    public int getNumber() {
        return info.getNumber();
    }

    @Override
    public SiteInfo getInfo() {
        return info;
    }
}
