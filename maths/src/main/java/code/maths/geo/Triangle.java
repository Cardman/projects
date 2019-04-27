package code.maths.geo;
import code.maths.Rate;
import code.util.CustList;
import code.util.PairNumber;
import code.util.ints.Displayable;

public final class Triangle implements HasEdges, Displayable {

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
        long x_ = omega_.getXcoords();
        long y_ = omega_.getYcoords();
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
        long x_ = omega_.getXcoords();
        long y_ = omega_.getYcoords();
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
        long gd_ = g_.getCommon();
        Rate gx_ = new Rate(g_.getXcoords(), gd_);
        Rate gy_ = new Rate(g_.getYcoords(), gd_);
        long cd_ = c_.getCommon();
        Rate cx_;
        Rate cy_;
        if (cd_ == 0) {
            cx_ = new Rate(gx_);
            cy_ = new Rate(gy_);
        } else {
            cx_ = new Rate(c_.getXcoords(), cd_);
            cy_ = new Rate(c_.getYcoords(), cd_);
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
        return new CompactPlanePoint(x_, y_,NB_POINTS);
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
        return new CompactPlanePoint(x_, y_,dp_);
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
    public String display() {
        StringBuilder str_ = new StringBuilder(firstPoint.display());
        str_.append(SEPARATOR);
        str_.append(secondPoint.display());
        str_.append(SEPARATOR);
        str_.append(thirdPoint.display());
        return str_.toString();
    }
}
