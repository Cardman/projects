package code.maths.geo;
import code.maths.Rate;
import code.maths.matrix.Matrix;
import code.maths.matrix.Vect;
import code.util.CustList;
import code.util.ints.Displayable;

public final class Tetrahedron implements Displayable {

    public static final int NB_POINTS = 4;

    private static final String SEPARATOR = ";";

    private final RatePointThreeDims firstPoint;

    private final RatePointThreeDims secondPoint;

    private final RatePointThreeDims thirdPoint;

    private final RatePointThreeDims fourthPoint;

    public Tetrahedron(RatePointThreeDims _firstPoint, RatePointThreeDims _secondPoint,
            RatePointThreeDims _thirdPoint, RatePointThreeDims _fourthPoint) {
        firstPoint = _firstPoint;
        secondPoint = _secondPoint;
        thirdPoint = _thirdPoint;
        fourthPoint = _fourthPoint;
    }

    public CustList<EdgeThreeDimensions> getEdges() {
        CustList<EdgeThreeDimensions> l_ = new CustList<EdgeThreeDimensions>();
        l_.add(new EdgeThreeDimensions(firstPoint, secondPoint));
        l_.add(new EdgeThreeDimensions(secondPoint, thirdPoint));
        l_.add(new EdgeThreeDimensions(thirdPoint, firstPoint));
        l_.add(new EdgeThreeDimensions(firstPoint, fourthPoint));
        l_.add(new EdgeThreeDimensions(secondPoint, fourthPoint));
        l_.add(new EdgeThreeDimensions(thirdPoint, fourthPoint));
        return l_;
    }

    public CustList<TriangleThreeDims> getTriangles() {
        CustList<TriangleThreeDims> l_ = new CustList<TriangleThreeDims>();
        l_.add(new TriangleThreeDims(firstPoint, secondPoint, thirdPoint));
        l_.add(new TriangleThreeDims(firstPoint, secondPoint, fourthPoint));
        l_.add(new TriangleThreeDims(secondPoint, thirdPoint, fourthPoint));
        l_.add(new TriangleThreeDims(firstPoint, thirdPoint, fourthPoint));
        return l_;
    }

    public CustList<RatePointThreeDims> getPoints() {
        CustList<RatePointThreeDims> l_ = new CustList<RatePointThreeDims>();
        l_.add(firstPoint);
        l_.add(secondPoint);
        l_.add(thirdPoint);
        l_.add(fourthPoint);
        return l_;
    }

    public boolean isInCircum(RatePointThreeDims _point) {
        RatePointThreeDims omega_;
        omega_ = getCircumCenter();
        if (omega_ == null) {
            return false;
        }
        Rate xr_ = omega_.getXcoords();
        Rate yr_ = omega_.getYcoords();
        Rate zr_ = omega_.getZcoords();
        Rate firstMember_ = square(xr_, yr_, zr_, firstPoint);
        Rate secondMember_ = square(xr_, yr_, zr_, _point);
        return Rate.strGreater(firstMember_, secondMember_);
    }

    private static Rate square(Rate _x, Rate _y, Rate _z, RatePointThreeDims _point) {
        Rate sum_ = Rate.zero();
        sum_.addNb(Rate.powNb(Rate.minus(_x, new Rate(_point.getXcoords())), new Rate(2)));
        sum_.addNb(Rate.powNb(Rate.minus(_y, new Rate(_point.getYcoords())), new Rate(2)));
        sum_.addNb(Rate.powNb(Rate.minus(_z, new Rate(_point.getZcoords())), new Rate(2)));
        return sum_;
    }

//    public CustLine euler() {
//        Pair<Pair<Long,Long>,Long> g_ = getGravityCenter();
//        Pair<Pair<Long,Long>,Long> c_ = getCircumCenter();
//        Pair<Long,Long> gxy_ = g_.getFirst();
//        long gd_ = g_.getSecond();
//        Rate gx_ = new Rate(gxy_.getFirst(), gd_);
//        Rate gy_ = new Rate(gxy_.getSecond(), gd_);
//        Pair<Long,Long> cxy_ = c_.getFirst();
//        long cd_ = c_.getSecond();
//        Rate cx_ = new Rate(cxy_.getFirst(), cd_);
//        Rate cy_ = new Rate(cxy_.getSecond(), cd_);
//        return new CustLine(new RatePoint(gx_, gy_), new RatePoint(cx_, cy_));
//    }

    public RatePointThreeDims getGravityCenter() {
        Rate x_ = new Rate(firstPoint.getXcoords());
        x_.addNb(secondPoint.getXcoords());
        x_.addNb(thirdPoint.getXcoords());
        x_.addNb(fourthPoint.getXcoords());
        Rate y_ = new Rate(firstPoint.getYcoords());
        y_.addNb(secondPoint.getYcoords());
        y_.addNb(thirdPoint.getYcoords());
        y_.addNb(fourthPoint.getYcoords());
        Rate z_ = new Rate(firstPoint.getZcoords());
        z_.addNb(secondPoint.getZcoords());
        z_.addNb(thirdPoint.getZcoords());
        z_.addNb(fourthPoint.getZcoords());
        Rate xr_ = Rate.divide(x_, new Rate(NB_POINTS));
        Rate yr_ = Rate.divide(y_, new Rate(NB_POINTS));
        Rate zr_ = Rate.divide(z_, new Rate(NB_POINTS));
        return new RatePointThreeDims(xr_, yr_, zr_);
    }

    public RatePointThreeDims getCircumCenter() {
        Rate sumOne_ = Rate.multiply(firstPoint.getXcoords(), firstPoint.getXcoords());
        sumOne_.addNb(Rate.multiply(firstPoint.getYcoords(), firstPoint.getYcoords()));
        sumOne_.addNb(Rate.multiply(firstPoint.getZcoords(), firstPoint.getZcoords()));
        Rate sumTwo_ = Rate.multiply(secondPoint.getXcoords(), secondPoint.getXcoords());
        sumTwo_.addNb(Rate.multiply(secondPoint.getYcoords(), secondPoint.getYcoords()));
        sumTwo_.addNb(Rate.multiply(secondPoint.getZcoords(), secondPoint.getZcoords()));
        Rate sumThree_ = Rate.multiply(thirdPoint.getXcoords(), thirdPoint.getXcoords());
        sumThree_.addNb(Rate.multiply(thirdPoint.getYcoords(), thirdPoint.getYcoords()));
        sumThree_.addNb(Rate.multiply(thirdPoint.getZcoords(), thirdPoint.getZcoords()));
        Rate sumFour_ = Rate.multiply(fourthPoint.getXcoords(), fourthPoint.getXcoords());
        sumFour_.addNb(Rate.multiply(fourthPoint.getYcoords(), fourthPoint.getYcoords()));
        sumFour_.addNb(Rate.multiply(fourthPoint.getZcoords(), fourthPoint.getZcoords()));
        Matrix denMatrix_ = new Matrix();
        Vect v_ = new Vect();
        v_.add(new Rate(firstPoint.getXcoords()));
        v_.add(new Rate(firstPoint.getYcoords()));
        v_.add(new Rate(firstPoint.getZcoords()));
        v_.add(Rate.one());
        denMatrix_.addLineRef(v_);
        v_ = new Vect();
        v_.add(new Rate(secondPoint.getXcoords()));
        v_.add(new Rate(secondPoint.getYcoords()));
        v_.add(new Rate(secondPoint.getZcoords()));
        v_.add(Rate.one());
        denMatrix_.addLineRef(v_);
        v_ = new Vect();
        v_.add(new Rate(thirdPoint.getXcoords()));
        v_.add(new Rate(thirdPoint.getYcoords()));
        v_.add(new Rate(thirdPoint.getZcoords()));
        v_.add(Rate.one());
        denMatrix_.addLineRef(v_);
        v_ = new Vect();
        v_.add(new Rate(fourthPoint.getXcoords()));
        v_.add(new Rate(fourthPoint.getYcoords()));
        v_.add(new Rate(fourthPoint.getZcoords()));
        v_.add(Rate.one());
        denMatrix_.addLineRef(v_);
        Rate r_= Rate.multiply(denMatrix_.detSquare(), new Rate(2));
        if (r_.isZero()) {
            return null;
        }
        Matrix xMatrix_ = new Matrix();
        v_ = new Vect();
        v_.add(new Rate(sumOne_));
        v_.add(new Rate(firstPoint.getYcoords()));
        v_.add(new Rate(firstPoint.getZcoords()));
        v_.add(Rate.one());
        xMatrix_.addLineRef(v_);
        v_ = new Vect();
        v_.add(new Rate(sumTwo_));
        v_.add(new Rate(secondPoint.getYcoords()));
        v_.add(new Rate(secondPoint.getZcoords()));
        v_.add(Rate.one());
        xMatrix_.addLineRef(v_);
        v_ = new Vect();
        v_.add(new Rate(sumThree_));
        v_.add(new Rate(thirdPoint.getYcoords()));
        v_.add(new Rate(thirdPoint.getZcoords()));
        v_.add(Rate.one());
        xMatrix_.addLineRef(v_);
        v_ = new Vect();
        v_.add(new Rate(sumFour_));
        v_.add(new Rate(fourthPoint.getYcoords()));
        v_.add(new Rate(fourthPoint.getZcoords()));
        v_.add(Rate.one());
        xMatrix_.addLineRef(v_);
        Rate x_ = xMatrix_.detSquare();
        Matrix yMatrix_ = new Matrix();
        v_ = new Vect();
        v_.add(new Rate(sumOne_));
        v_.add(new Rate(firstPoint.getXcoords()));
        v_.add(new Rate(firstPoint.getZcoords()));
        v_.add(Rate.one());
        yMatrix_.addLineRef(v_);
        v_ = new Vect();
        v_.add(new Rate(sumTwo_));
        v_.add(new Rate(secondPoint.getXcoords()));
        v_.add(new Rate(secondPoint.getZcoords()));
        v_.add(Rate.one());
        yMatrix_.addLineRef(v_);
        v_ = new Vect();
        v_.add(new Rate(sumThree_));
        v_.add(new Rate(thirdPoint.getXcoords()));
        v_.add(new Rate(thirdPoint.getZcoords()));
        v_.add(Rate.one());
        yMatrix_.addLineRef(v_);
        v_ = new Vect();
        v_.add(new Rate(sumFour_));
        v_.add(new Rate(fourthPoint.getXcoords()));
        v_.add(new Rate(fourthPoint.getZcoords()));
        v_.add(Rate.one());
        yMatrix_.addLineRef(v_);
        Rate y_ = yMatrix_.detSquare().opposNb();
        Matrix zMatrix_ = new Matrix();
        v_ = new Vect();
        v_.add(new Rate(sumOne_));
        v_.add(new Rate(firstPoint.getXcoords()));
        v_.add(new Rate(firstPoint.getYcoords()));
        v_.add(Rate.one());
        zMatrix_.addLineRef(v_);
        v_ = new Vect();
        v_.add(new Rate(sumTwo_));
        v_.add(new Rate(secondPoint.getXcoords()));
        v_.add(new Rate(secondPoint.getYcoords()));
        v_.add(Rate.one());
        zMatrix_.addLineRef(v_);
        v_ = new Vect();
        v_.add(new Rate(sumThree_));
        v_.add(new Rate(thirdPoint.getXcoords()));
        v_.add(new Rate(thirdPoint.getYcoords()));
        v_.add(Rate.one());
        zMatrix_.addLineRef(v_);
        v_ = new Vect();
        v_.add(new Rate(sumFour_));
        v_.add(new Rate(fourthPoint.getXcoords()));
        v_.add(new Rate(fourthPoint.getYcoords()));
        v_.add(Rate.one());
        zMatrix_.addLineRef(v_);
        Rate z_ = zMatrix_.detSquare();
        return new RatePointThreeDims(Rate.divide(x_, r_), Rate.divide(y_, r_), Rate.divide(z_, r_));
//        return new Pair<Pair<Long,Pair<Long,Long>>,Long>(new Pair<Long,Pair<Long,Long>>(x_, new Pair<Long,Long>(y_, z_)),r_);
//        Pair<Pair<Long,Pair<Long,Long>>,Long> omegaOne_;
//        omegaOne_ = getCircumCenter(firstPoint, secondPoint, thirdPoint);
//        Pair<Pair<Long,Pair<Long,Long>>,Long> eqOne_;
//        eqOne_ = getEq(firstPoint, secondPoint, thirdPoint);
//        Pair<Pair<Long,Pair<Long,Long>>,Long> omegaTwo_;
//        omegaTwo_ = getCircumCenter(firstPoint, secondPoint, fourthPoint);
//        Pair<Pair<Long,Pair<Long,Long>>,Long> eqTwo_;
//        eqTwo_ = getEq(firstPoint, secondPoint, fourthPoint);
//        Pair<Long,Pair<Long,Long>> rOne_ = eqOne_.getFirst();
//        VectThreeDims vOne_ = new VectThreeDims(rOne_.getFirst().intValue(),rOne_.getSecond().getFirst().intValue(),rOne_.getSecond().getSecond().intValue());
//        Pair<Long,Pair<Long,Long>> rTwo_ = eqTwo_.getFirst();
//        VectThreeDims vTwo_ = new VectThreeDims(rTwo_.getFirst().intValue(),rTwo_.getSecond().getFirst().intValue(),rTwo_.getSecond().getSecond().intValue());
//        return intersection(omegaOne_, vOne_, omegaTwo_, vTwo_);
//        Triangle t_ = new Triangle(_firstPoint, _secondPoint, _thirdPoint);
//        long d_ = ();
//        long bpx_ = secondPoint.getXcoords() - firstPoint.getXcoords();
//        long cpx_ = thirdPoint.getXcoords() - firstPoint.getXcoords();
//        long bpy_ = secondPoint.getYcoords() - firstPoint.getYcoords();
//        long cpy_ = thirdPoint.getYcoords() - firstPoint.getYcoords();
//        long dp_ = 2*(bpx_ * cpy_ - bpy_ * cpx_);
//        long x_ = cpy_ * (bpx_ * bpx_ + bpy_ * bpy_);
//        x_ -= bpy_ * (cpx_ * cpx_ + cpy_ * cpy_);
//        long y_ = bpx_ * (cpx_ * cpx_ + cpy_ * cpy_);
//        y_ -= cpx_ * (bpx_ * bpx_ + bpy_ * bpy_);
//        x_ += firstPoint.getXcoords() * dp_;
//        y_ += firstPoint.getYcoords() * dp_;
//        return new Pair<Pair<Long,Long>,Long>(new Pair<Long,Long>(x_, y_),dp_);
    }

//    public static CompactSpacePoint getEq(RatePointThreeDims _one,
//            RatePointThreeDims _two,
//            RatePointThreeDims _three) {
//        long a_ =(long) (_two.getYcoords() - _one.getYcoords()) * (_three.getZcoords() - _two.getZcoords());
//        a_ -= (long)(_two.getZcoords() - _one.getZcoords()) * (_three.getYcoords() - _two.getYcoords());
//        long b_ = (long)-(_two.getXcoords() - _one.getXcoords()) * (_three.getZcoords() - _two.getZcoords());
//        b_ += (long)(_two.getZcoords() - _one.getZcoords()) * (_three.getXcoords() - _two.getXcoords());
//        long c_ = (long)(_two.getXcoords() - _one.getXcoords()) * (_three.getYcoords() - _two.getYcoords());
//        c_ -= (long)(_two.getYcoords() - _one.getYcoords()) * (_three.getXcoords() - _two.getXcoords());
//        long d_ = a_ * _one.getXcoords() + b_ * _one.getYcoords() + c_ * _one.getZcoords();
//        return new CompactSpacePoint(b_, c_, a_,d_);
//    }
//    public static CompactSpacePoint getCircumCenter(RatePointThreeDims _one,
//            RatePointThreeDims _two,
//            RatePointThreeDims _three) {
//        long a_ = (long)(_two.getYcoords() - _one.getYcoords()) * (_three.getZcoords() - _two.getZcoords());
//        a_ -= (long)(_two.getZcoords() - _one.getZcoords()) * (_three.getYcoords() - _two.getYcoords());
//        long b_ = (long)-(_two.getXcoords() - _one.getXcoords()) * (_three.getZcoords() - _two.getZcoords());
//        b_ += (long)(_two.getZcoords() - _one.getZcoords()) * (_three.getXcoords() - _two.getXcoords());
//        long c_ =(long)(_two.getXcoords() - _one.getXcoords()) * (_three.getYcoords() - _two.getYcoords());
//        c_ -= (long)(_two.getYcoords() - _one.getYcoords()) * (_three.getXcoords() - _two.getXcoords());
////        long d_ = a_ * _one.getXcoords() + b_ * _one.getYcoords() + c_ * _one.getZcoords();
//        VectThreeDims n_ = new VectThreeDims((int) a_, (int) b_, (int) c_);
//        VectThreeDims eOneTwo_ = new VectThreeDims(_one, _two);
//        long x_;
//        long y_;
//        long z_;
//        x_ = (long)_one.getXcoords() + _two.getXcoords();
//        y_ = (long)_one.getYcoords() + _two.getYcoords();
//        z_ = (long)_one.getZcoords() + _two.getZcoords();
//        CompactSpacePoint midOneTwo_;
//        midOneTwo_ = new CompactSpacePoint(y_, z_, x_,2L);
//        VectThreeDims lOneTwo_ = n_.vectProd(eOneTwo_);
//        VectThreeDims eOneThree_ = new VectThreeDims(_one, _three);
//        x_ = (long)_one.getXcoords() + _three.getXcoords();
//        y_ = (long)_one.getYcoords() + _three.getYcoords();
//        z_ = (long)_one.getZcoords() + _three.getZcoords();
//        CompactSpacePoint midOneThree_;
//        midOneThree_ = new CompactSpacePoint(y_, z_, x_,2L);
//        VectThreeDims lOneThree_ = n_.vectProd(eOneThree_);
//        return intersection(midOneTwo_, lOneTwo_, midOneThree_, lOneThree_);
//    }

//    public static CompactSpacePoint intersection(
//            CompactSpacePoint _ptOne,
//            VectThreeDims _vOne,
//            CompactSpacePoint _ptTwo,
//            VectThreeDims _vTwo) {
//        long n_ = (_ptTwo.getZcoords() - _ptOne.getZcoords()) * _vTwo.getDeltay();
//        n_ -= (_ptTwo.getXcoords() - _ptOne.getXcoords()) * _vTwo.getDeltax();
//        long firstPart_ = (long) _vOne.getDeltax() * _vTwo.getDeltay();
//        long secondPart_ = (long) _vOne.getDeltay() * _vTwo.getDeltax();
//        long d_ = 2 *(firstPart_ - secondPart_);
//        long x_ = _ptOne.getCommon() * n_ * _vOne.getDeltax() + d_ * _ptOne.getZcoords();
//        long y_ = _ptOne.getCommon() * n_ * _vOne.getDeltay() + d_ * _ptOne.getXcoords();
//        long z_ = _ptOne.getCommon() * n_ * _vOne.getDeltaz() + d_ * _ptOne.getYcoords();
//        long dint_ = _ptOne.getCommon() * d_;
//        return new CompactSpacePoint(y_, z_, x_,dint_);
//    }

    public RatePointThreeDims getFirstPoint() {
        return firstPoint;
    }

    public RatePointThreeDims getSecondPoint() {
        return secondPoint;
    }

    public RatePointThreeDims getThirdPoint() {
        return thirdPoint;
    }

    public RatePointThreeDims getFourthPoint() {
        return fourthPoint;
    }

    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder(firstPoint.display());
        str_.append(SEPARATOR);
        str_.append(secondPoint.display());
        str_.append(SEPARATOR);
        str_.append(thirdPoint.display());
        str_.append(SEPARATOR);
        str_.append(fourthPoint.display());
        return str_.toString();
    }
}
