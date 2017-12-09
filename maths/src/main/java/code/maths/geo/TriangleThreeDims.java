package code.maths.geo;
import code.maths.Rate;
import code.util.EqList;
import code.util.PairNumber;

public final class TriangleThreeDims {

    public static final int NB_POINTS = 3;

    private static final String SEPARATOR = ";";

    private final CustPointThreeDims firstPoint;

    private final CustPointThreeDims secondPoint;

    private final CustPointThreeDims thirdPoint;

    public TriangleThreeDims(CustPointThreeDims _firstPoint, CustPointThreeDims _secondPoint, CustPointThreeDims _thirdPoint) {
        firstPoint = _firstPoint;
        secondPoint = _secondPoint;
        thirdPoint = _thirdPoint;
    }

    public EqList<CustPointThreeDims> getPoints() {
        EqList<CustPointThreeDims> l_ = new EqList<CustPointThreeDims>();
        l_.add(firstPoint);
        l_.add(secondPoint);
        l_.add(thirdPoint);
        return l_;
    }

    public boolean isInCircum(CustPoint _point) {
        CompactPlanePoint omega_;
        omega_ = getCircumCenter();
        long x_ = omega_.getPair().getFirst();
        long y_ = omega_.getPair().getSecond();
        long den_ = omega_.getCommon();
        long firstMember_ = firstPoint.getXcoords() - _point.getXcoords();
        firstMember_ *= 2 * x_ - den_ *(_point.getXcoords() + firstPoint.getXcoords());
        long secondMember_ = _point.getYcoords() - firstPoint.getYcoords();
        secondMember_ *= 2 * y_ - den_ *(_point.getYcoords() + firstPoint.getYcoords());
        if (den_ > 0) {
            return firstMember_ <= secondMember_;
        }
        return firstMember_ >= secondMember_;
    }

    public CustLine euler() {
        CompactPlanePoint g_ = getGravityCenter();
        CompactPlanePoint c_ = getCircumCenter();
        PairNumber<Long,Long> gxy_ = g_.getPair();
        long gd_ = g_.getCommon();
        Rate gx_ = new Rate(gxy_.getFirst(), gd_);
        Rate gy_ = new Rate(gxy_.getSecond(), gd_);
        PairNumber<Long,Long> cxy_ = c_.getPair();
        long cd_ = c_.getCommon();
        Rate cx_;
        Rate cy_;
        if (cd_ == 0) {
            cx_ = new Rate(gx_);
            cy_ = new Rate(gy_);
        } else {
            cx_ = new Rate(cxy_.getFirst(), cd_);
            cy_ = new Rate(cxy_.getSecond(), cd_);
        }
        return new CustLine(new RatePoint(gx_, gy_), new RatePoint(cx_, cy_));
    }

    public CompactPlanePoint getGravityCenter() {
        long x_ = firstPoint.getXcoords();
        x_ += secondPoint.getXcoords();
        x_ += thirdPoint.getXcoords();
        long y_ = firstPoint.getYcoords();
        y_ += secondPoint.getYcoords();
        y_ += thirdPoint.getYcoords();
        return new CompactPlanePoint(new PairNumber<Long,Long>(x_, y_),NB_POINTS);
    }

    public CompactPlanePoint getCircumCenter() {
        long bpx_ = secondPoint.getXcoords() - firstPoint.getXcoords();
        long cpx_ = thirdPoint.getXcoords() - firstPoint.getXcoords();
        long bpy_ = secondPoint.getYcoords() - firstPoint.getYcoords();
        long cpy_ = thirdPoint.getYcoords() - firstPoint.getYcoords();
        long dp_ = 2*(bpx_ * cpy_ - bpy_ * cpx_);
        long x_ = cpy_ * (bpx_ * bpx_ + bpy_ * bpy_);
        x_ -= bpy_ * (cpx_ * cpx_ + cpy_ * cpy_);
        long y_ = bpx_ * (cpx_ * cpx_ + cpy_ * cpy_);
        y_ -= cpx_ * (bpx_ * bpx_ + bpy_ * bpy_);
        x_ += firstPoint.getXcoords() * dp_;
        y_ += firstPoint.getYcoords() * dp_;
        return new CompactPlanePoint(new PairNumber<Long,Long>(x_, y_),dp_);
    }

    public CustPointThreeDims getFirstPoint() {
        return firstPoint;
    }

    public CustPointThreeDims getSecondPoint() {
        return secondPoint;
    }

    public CustPointThreeDims getThirdPoint() {
        return thirdPoint;
    }

    @Override
    public String toString() {
        return firstPoint+SEPARATOR+secondPoint+SEPARATOR+thirdPoint;
    }

    public boolean isSame(TriangleThreeDims _e) {
        EqList<CustPointThreeDims> listOne_ = getPoints();
        EqList<CustPointThreeDims> listTwo_ = _e.getPoints();
        for (CustPointThreeDims p: listOne_) {
            boolean c_ = false;
            for (CustPointThreeDims c: listTwo_) {
                if (c == p) {
                    c_ = true;
                    break;
                }
            }
            if (!c_) {
                return false;
            }
        }
        return true;
    }
}
