package code.maths.geo;
import code.util.CustList;
import code.util.ints.Cmp;


public final class SitePointThreeDims implements Cmp<SitePointThreeDims> {

    public static final int QUAD_ONE = 1;

    public static final int QUAD_TWO = 2;

    public static final int QUAD_THREE = 3;

    public static final int QUAD_FOUR = 4;

    private CustPointThreeDims point;

    private int number;

    private long scal;

    private long squareLength;

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
            number = QUAD_ONE;
        } else if (scal_ < 0 && negOrZero_) {
            number = QUAD_TWO;
        } else if (scal_ <= 0) {
            //det_ < 0
            number = QUAD_THREE;
        } else {
            number = QUAD_FOUR;
        }
        squareLength = vect_.squareLength();
        scal = scal_;
    }

    public CustPointThreeDims getPoint() {
        return point;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean eq(SitePointThreeDims _g) {
        return cmp(_g) == CustList.EQ_CMP;
    }

    @Override
    public int cmp(SitePointThreeDims _o) {
        return compare(this, _o);
    }

    public static int compare(SitePointThreeDims _one, SitePointThreeDims _two) {
        if (_one.number < _two.number) {
            return CustList.NO_SWAP_SORT;
        }
        if (_one.number > _two.number) {
            return CustList.SWAP_SORT;
        }
        long firstMember_ = _one.scal * _one.scal * _two.squareLength;
        long secondMember_ = _two.scal * _two.scal * _one.squareLength;
        if (_one.number == QUAD_ONE || _one.number == QUAD_THREE) {
            if (firstMember_ > secondMember_) {
                return CustList.NO_SWAP_SORT;
            }
            if (firstMember_ < secondMember_) {
                return CustList.SWAP_SORT;
            }
        } else {
            if (firstMember_ < secondMember_) {
                return CustList.NO_SWAP_SORT;
            }
            if (firstMember_ > secondMember_) {
                return CustList.SWAP_SORT;
            }
        }
        return CustList.EQ_CMP;
    }
}
