package code.maths.geo;
import code.util.CustList;
import code.util.ints.Cmp;


public final class SitePointThreeDims implements Site {

    private CustPointThreeDims point;

    private SiteInfo info = new SiteInfo();

    public SitePointThreeDims(CustPointThreeDims _point, CustPointThreeDims _first, VectThreeDims _vect) {
        point = _point;
        VectThreeDims vect_ = new VectThreeDims(_first, point);
        VectThreeDims cross_ = _vect.vectProd(vect_);
        boolean negOrZero_ = cross_.getDeltaz() <= 0;
        if (cross_.getDeltaz() == 0) {
            negOrZero_ = cross_.getDeltay() >= 0;
            if (cross_.getDeltay() == 0) {
                negOrZero_ = cross_.getDeltax() <= 0;
            }
        }
        long scal_ = _vect.scal(vect_);
        if (scal_ >= 0 && negOrZero_) {
            info.setNumber(SiteInfo.QUAD_ONE);
        } else if (scal_ < 0 && negOrZero_) {
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

    public CustPointThreeDims getPoint() {
        return point;
    }

    public int getNumber() {
        return info.getNumber();
    }

    @Override
    public SiteInfo getInfo() {
        return info;
    }

    public static int compare(SitePointThreeDims _one, SitePointThreeDims _two) {
        return SiteInfo.compare(_one.info,_two.info);
    }
}
