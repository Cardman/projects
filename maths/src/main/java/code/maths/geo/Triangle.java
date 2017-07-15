package code.maths.geo;
import code.maths.Rate;
import code.util.CustList;
import code.util.PairNumber;

public final class Triangle implements HasEdges {

    public static final int NB_POINTS = 3;

    private static final String SEPARATOR = ";";

    private final CustPoint firstPoint;

    private final CustPoint secondPoint;

    private final CustPoint thirdPoint;

    public Triangle(CustPoint _firstPoint, CustPoint _secondPoint, CustPoint _thirdPoint) {
        firstPoint = _firstPoint;
        secondPoint = _secondPoint;
        thirdPoint = _thirdPoint;
    }

    @Override
    public CustList<Edge> getEdges() {
        CustList<Edge> l_ = new CustList<Edge>();
        l_.add(new Edge(firstPoint, secondPoint));
        l_.add(new Edge(secondPoint, thirdPoint));
        l_.add(new Edge(thirdPoint, firstPoint));
        return l_;
    }

    public boolean areSame(Triangle _t) {
        int nb_ = 0;
        for (CustPoint p: getPoints()) {
            boolean contained_ = false;
            for (CustPoint q: _t.getPoints()) {
                if (p == q) {
                    contained_ = true;
                    break;
                }
            }
            if (contained_) {
                nb_++;
            }
        }
        return nb_ == NB_POINTS;
    }

    @Override
    public CustList<CustPoint> getPoints() {
        CustList<CustPoint> l_ = new CustList<CustPoint>();
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
            return firstMember_ < secondMember_;
        }
        return firstMember_ > secondMember_;
    }

    public boolean isInCircumBorder(CustPoint _point) {
        CompactPlanePoint omega_;
        omega_ = getCircumCenter();
        long x_ = omega_.getPair().getFirst();
        long y_ = omega_.getPair().getSecond();
        long den_ = omega_.getCommon();
        long firstMember_ = firstPoint.getXcoords() - _point.getXcoords();
        firstMember_ *= 2 * x_ - den_ *(_point.getXcoords() + firstPoint.getXcoords());
        long secondMember_ = _point.getYcoords() - firstPoint.getYcoords();
        secondMember_ *= 2 * y_ - den_ *(_point.getYcoords() + firstPoint.getYcoords());
        return firstMember_ == secondMember_;
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
        Rate cx_ = new Rate(cxy_.getFirst(), cd_);
        Rate cy_ = new Rate(cxy_.getSecond(), cd_);
        return new CustLine(new RatePoint(gx_, gy_), new RatePoint(cx_, cy_));
    }

    public CompactPlanePoint getGravityCenter() {
        long x_ = firstPoint.getXcoords();
        x_ += secondPoint.getXcoords();
        x_ += thirdPoint.getXcoords();
        long y_ = firstPoint.getYcoords();
        y_ += secondPoint.getYcoords();
        y_ += thirdPoint.getYcoords();
        return new CompactPlanePoint(new PairNumber<Long,Long>(x_, y_),(long) NB_POINTS);
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

    public CustPoint getFirstPoint() {
        return firstPoint;
    }

    public CustPoint getSecondPoint() {
        return secondPoint;
    }

    public CustPoint getThirdPoint() {
        return thirdPoint;
    }

    @Override
    public String toString() {
        return firstPoint+SEPARATOR+secondPoint+SEPARATOR+thirdPoint;
    }
}
