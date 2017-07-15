package code.maths.geo;
import code.util.CustList;
import code.util.ints.Cmp;


public final class SitePoint implements Cmp<SitePoint> {

    public static final int QUAD_ONE = 1;

    public static final int QUAD_TWO = 2;

    public static final int QUAD_THREE = 3;

    public static final int QUAD_FOUR = 4;

    private CustPoint point;

    private int number;

    private long scal;

    private long squareLength;

    public SitePoint(CustPoint _point, CustPoint _first, VectTwoDims _vect) {
        point = _point;
        VectTwoDims vect_ = new VectTwoDims(_first, point);
        long det_ = _vect.det(vect_);
        long scal_ = _vect.scal(vect_);
        if (scal_ >= 0 && det_ <= 0) {
            number = QUAD_ONE;
        } else if (scal_ < 0 && det_ <= 0) {
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

    public CustPoint getPoint() {
        return point;
    }

    public int getNumber() {
        return number;
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
