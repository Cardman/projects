package code.maths.geo;
import code.util.CustList;
import code.util.ints.Displayable;

public final class TriangleThreeDims implements Displayable {

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

    public CustList<CustPointThreeDims> getPoints() {
        CustList<CustPointThreeDims> l_ = new CustList<CustPointThreeDims>();
        l_.add(firstPoint);
        l_.add(secondPoint);
        l_.add(thirdPoint);
        return l_;
    }

    public CompactSpacePoint getGravityCenter() {
        long x_ = firstPoint.getXcoords();
        x_ += secondPoint.getXcoords();
        x_ += thirdPoint.getXcoords();
        long y_ = firstPoint.getYcoords();
        y_ += secondPoint.getYcoords();
        y_ += thirdPoint.getYcoords();
        long z_ = firstPoint.getZcoords();
        z_ += secondPoint.getZcoords();
        z_ += thirdPoint.getZcoords();
        return new CompactSpacePoint(x_, y_, z_,NB_POINTS);
    }

    public CompactSpacePoint getCircumCenter() {
        long bpx_ = (long)secondPoint.getXcoords() - firstPoint.getXcoords();
        long cpx_ = (long)thirdPoint.getXcoords() - firstPoint.getXcoords();
        long bpy_ = (long)secondPoint.getYcoords() - firstPoint.getYcoords();
        long cpy_ = (long)thirdPoint.getYcoords() - firstPoint.getYcoords();
        long bpz_ = (long)secondPoint.getZcoords() - firstPoint.getZcoords();
        long cpz_ = (long)thirdPoint.getZcoords() - firstPoint.getZcoords();
        long dp_ = 2*(bpx_ * cpy_ - bpy_ * cpx_);
        long x_ = cpy_ * (bpx_ * bpx_ + bpy_ * bpy_);
        x_ -= bpy_ * (cpx_ * cpx_ + cpy_ * cpy_);
        long y_ = bpx_ * (cpx_ * cpx_ + cpy_ * cpy_);
        y_ -= cpx_ * (bpx_ * bpx_ + bpy_ * bpy_);
        long z_ = bpx_ * (cpx_ * cpx_ + cpz_ * cpz_);
        y_ -= cpx_ * (bpx_ * bpx_ + bpz_ * bpz_);
        x_ += firstPoint.getXcoords() * dp_;
        y_ += firstPoint.getYcoords() * dp_;
        return new CompactSpacePoint(x_, y_,z_,NB_POINTS);
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

    public boolean isSame(TriangleThreeDims _e) {
        CustList<CustPointThreeDims> listOne_ = getPoints();
        CustList<CustPointThreeDims> listTwo_ = _e.getPoints();
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
