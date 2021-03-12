package code.maths.geo;
import code.maths.Rate;
import code.util.CustList;
import code.util.ints.Displayable;

public final class TriangleThreeDims implements Displayable {

//    public static final int NB_POINTS = 3;

    private static final String SEPARATOR = ";";

    private final RatePointThreeDims firstPoint;

    private final RatePointThreeDims secondPoint;

    private final RatePointThreeDims thirdPoint;

    public TriangleThreeDims(RatePointThreeDims _firstPoint, RatePointThreeDims _secondPoint, RatePointThreeDims _thirdPoint) {
        firstPoint = _firstPoint;
        secondPoint = _secondPoint;
        thirdPoint = _thirdPoint;
    }

    public CustList<RatePointThreeDims> getPoints() {
        CustList<RatePointThreeDims> l_ = new CustList<RatePointThreeDims>();
        l_.add(firstPoint);
        l_.add(secondPoint);
        l_.add(thirdPoint);
        return l_;
    }

    public RatePointThreeDims getGravityCenter() {
        Rate xg_ = Rate.zero();
        xg_.addNb(firstPoint.getXcoords());
        xg_.addNb(secondPoint.getXcoords());
        xg_.addNb(thirdPoint.getXcoords());
        xg_.divideBy(new Rate(3));
        Rate yg_ = Rate.zero();
        yg_.addNb(firstPoint.getYcoords());
        yg_.addNb(secondPoint.getYcoords());
        yg_.addNb(thirdPoint.getYcoords());
        yg_.divideBy(new Rate(3));
        Rate zg_ = Rate.zero();
        zg_.addNb(firstPoint.getZcoords());
        zg_.addNb(secondPoint.getZcoords());
        zg_.addNb(thirdPoint.getZcoords());
        zg_.divideBy(new Rate(3));
        return new RatePointThreeDims(xg_, yg_, zg_);
    }

    /*a=A-C,b=B-C
    a x (b x c) = (a.c).b - (a.b).c
    omega = ((normeCar(a).b - normeCar(b).a) x (a x b))
    /(2 * (normeCar(a) x normeCar(b) - (a.b) x (a.b)))+C
    */
    public RatePointThreeDims getCircumCenter() {
//        Rate bpx_ = Rate.minus(secondPoint.getXcoords(), firstPoint.getXcoords());
//        Rate cpx_ = Rate.minus(thirdPoint.getXcoords(), firstPoint.getXcoords());
//        Rate bpy_ = Rate.minus(secondPoint.getYcoords(), firstPoint.getYcoords());
//        Rate cpy_ = Rate.minus(thirdPoint.getYcoords(), firstPoint.getYcoords());
//        Rate bpz_ = Rate.minus(secondPoint.getZcoords(), firstPoint.getZcoords());
//        Rate cpz_ = Rate.minus(thirdPoint.getZcoords(), firstPoint.getZcoords());
//        long dp_ = 2*(bpx_ * cpy_ - bpy_ * cpx_);
//        long x_ = cpy_ * (bpx_ * bpx_ + bpy_ * bpy_);
//        x_ -= bpy_ * (cpx_ * cpx_ + cpy_ * cpy_);
//        long y_ = bpx_ * (cpx_ * cpx_ + cpy_ * cpy_);
//        y_ -= cpx_ * (bpx_ * bpx_ + bpy_ * bpy_);
//        long z_ = bpx_ * (cpx_ * cpx_ + cpz_ * cpz_);
//        y_ -= cpx_ * (bpx_ * bpx_ + bpz_ * bpz_);
//        x_ += firstPoint.getXcoords() * dp_;
//        y_ += firstPoint.getYcoords() * dp_;
        return new RatePointThreeDims(firstPoint.getXcoords(),secondPoint.getXcoords(),thirdPoint.getXcoords());
//        return new CompactSpacePoint(x_, y_,z_,NB_POINTS);
    }

    public RatePointThreeDims getFirstPoint() {
        return firstPoint;
    }

    public RatePointThreeDims getSecondPoint() {
        return secondPoint;
    }

    public RatePointThreeDims getThirdPoint() {
        return thirdPoint;
    }

    public boolean isSame(TriangleThreeDims _e) {
        CustList<RatePointThreeDims> listOne_ = getPoints();
        CustList<RatePointThreeDims> listTwo_ = _e.getPoints();
        for (RatePointThreeDims p: listOne_) {
            boolean c_ = false;
            for (RatePointThreeDims c: listTwo_) {
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
