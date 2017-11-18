package code.maths.geo;
import code.maths.Rate;
import code.maths.matrix.Matrix;
import code.maths.matrix.Vect;
import code.util.CustList;
import code.util.EqList;
import code.util.PairNumber;

public final class Tetrahedron {

    public static final int NB_POINTS = 4;

    private static final String SEPARATOR = ";";

    private final CustPointThreeDims firstPoint;

    private final CustPointThreeDims secondPoint;

    private final CustPointThreeDims thirdPoint;

    private final CustPointThreeDims fourthPoint;

    public Tetrahedron(CustPointThreeDims _firstPoint, CustPointThreeDims _secondPoint,
            CustPointThreeDims _thirdPoint, CustPointThreeDims _fourthPoint) {
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

    public EqList<CustPointThreeDims> getPoints() {
        EqList<CustPointThreeDims> l_ = new EqList<CustPointThreeDims>();
        l_.add(firstPoint);
        l_.add(secondPoint);
        l_.add(thirdPoint);
        l_.add(fourthPoint);
        return l_;
    }

    public boolean isInCircum(CustPointThreeDims _point) {
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

    private static Rate square(Rate _x, Rate _y, Rate _z, CustPointThreeDims _point) {
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
        long x_ = firstPoint.getXcoords();
        x_ += secondPoint.getXcoords();
        x_ += thirdPoint.getXcoords();
        x_ += fourthPoint.getXcoords();
        long y_ = firstPoint.getYcoords();
        y_ += secondPoint.getYcoords();
        y_ += thirdPoint.getYcoords();
        y_ += fourthPoint.getYcoords();
        long z_ = firstPoint.getZcoords();
        z_ += secondPoint.getZcoords();
        z_ += thirdPoint.getZcoords();
        z_ += fourthPoint.getZcoords();
        Rate xr_ = new Rate(x_, NB_POINTS);
        Rate yr_ = new Rate(y_, NB_POINTS);
        Rate zr_ = new Rate(z_, NB_POINTS);
        return new RatePointThreeDims(xr_, yr_, zr_);
    }

    public RatePointThreeDims getCircumCenter() {
        long sumOne_ = firstPoint.getXcoords() * firstPoint.getXcoords();
        sumOne_ += firstPoint.getYcoords() * firstPoint.getYcoords();
        sumOne_ += firstPoint.getZcoords() * firstPoint.getZcoords();
        long sumTwo_ = secondPoint.getXcoords() * secondPoint.getXcoords();
        sumTwo_ += secondPoint.getYcoords() * secondPoint.getYcoords();
        sumTwo_ += secondPoint.getZcoords() * secondPoint.getZcoords();
        long sumThree_ = thirdPoint.getXcoords() * thirdPoint.getXcoords();
        sumThree_ += thirdPoint.getYcoords() * thirdPoint.getYcoords();
        sumThree_ += thirdPoint.getZcoords() * thirdPoint.getZcoords();
        long sumFour_ = fourthPoint.getXcoords() * fourthPoint.getXcoords();
        sumFour_ += fourthPoint.getYcoords() * fourthPoint.getYcoords();
        sumFour_ += fourthPoint.getZcoords() * fourthPoint.getZcoords();
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

    public static CompactSpacePoint getEq(CustPointThreeDims _one,
            CustPointThreeDims _two,
            CustPointThreeDims _three) {
        long a_ = (_two.getYcoords() - _one.getYcoords()) * (_three.getZcoords() - _two.getZcoords());
        a_ -= (_two.getZcoords() - _one.getZcoords()) * (_three.getYcoords() - _two.getYcoords());
        long b_ = -(_two.getXcoords() - _one.getXcoords()) * (_three.getZcoords() - _two.getZcoords());
        b_ += (_two.getZcoords() - _one.getZcoords()) * (_three.getXcoords() - _two.getXcoords());
        long c_ = (_two.getXcoords() - _one.getXcoords()) * (_three.getYcoords() - _two.getYcoords());
        c_ -= (_two.getYcoords() - _one.getYcoords()) * (_three.getXcoords() - _two.getXcoords());
        long d_ = a_ * _one.getXcoords() + b_ * _one.getYcoords() + c_ * _one.getZcoords();
        return new CompactSpacePoint(new CompactPlanePoint(new PairNumber<Long,Long>(b_, c_), a_),d_);
    }
    public static CompactSpacePoint getCircumCenter(CustPointThreeDims _one,
            CustPointThreeDims _two,
            CustPointThreeDims _three) {
        long a_ = (_two.getYcoords() - _one.getYcoords()) * (_three.getZcoords() - _two.getZcoords());
        a_ -= (_two.getZcoords() - _one.getZcoords()) * (_three.getYcoords() - _two.getYcoords());
        long b_ = -(_two.getXcoords() - _one.getXcoords()) * (_three.getZcoords() - _two.getZcoords());
        b_ += (_two.getZcoords() - _one.getZcoords()) * (_three.getXcoords() - _two.getXcoords());
        long c_ = (_two.getXcoords() - _one.getXcoords()) * (_three.getYcoords() - _two.getYcoords());
        c_ -= (_two.getYcoords() - _one.getYcoords()) * (_three.getXcoords() - _two.getXcoords());
//        long d_ = a_ * _one.getXcoords() + b_ * _one.getYcoords() + c_ * _one.getZcoords();
        VectThreeDims n_ = new VectThreeDims((int) a_, (int) b_, (int) c_);
        VectThreeDims eOneTwo_ = new VectThreeDims(_one, _two);
        long x_;
        long y_;
        long z_;
        x_ = _one.getXcoords() + _two.getXcoords();
        y_ = _one.getYcoords() + _two.getYcoords();
        z_ = _one.getZcoords() + _two.getZcoords();
        CompactSpacePoint midOneTwo_;
        midOneTwo_ = new CompactSpacePoint(new CompactPlanePoint(new PairNumber<Long,Long>(y_, z_), x_),2l);
        VectThreeDims lOneTwo_ = n_.vectProd(eOneTwo_);
        VectThreeDims eOneThree_ = new VectThreeDims(_one, _three);
        x_ = _one.getXcoords() + _three.getXcoords();
        y_ = _one.getYcoords() + _three.getYcoords();
        z_ = _one.getZcoords() + _three.getZcoords();
        CompactSpacePoint midOneThree_;
        midOneThree_ = new CompactSpacePoint(new CompactPlanePoint(new PairNumber<Long,Long>(y_, z_), x_),2l);
        VectThreeDims lOneThree_ = n_.vectProd(eOneThree_);
        return intersection(midOneTwo_, lOneTwo_, midOneThree_, lOneThree_);
    }

    public static CompactSpacePoint intersection(
            CompactSpacePoint _ptOne,
            VectThreeDims _vOne,
            CompactSpacePoint _ptTwo,
            VectThreeDims _vTwo) {
        long n_ = (_ptTwo.getPair().getCommon() - _ptOne.getPair().getCommon()) * _vTwo.getDeltay();
        n_ -= (_ptTwo.getPair().getPair().getFirst() - _ptOne.getPair().getPair().getFirst()) * _vTwo.getDeltax();
        long d_ = 2 *(_vOne.getDeltax() * _vTwo.getDeltay() - _vOne.getDeltay() * _vTwo.getDeltax());
        long x_ = _ptOne.getCommon() * n_ * _vOne.getDeltax() + d_ * _ptOne.getPair().getCommon();
        long y_ = _ptOne.getCommon() * n_ * _vOne.getDeltay() + d_ * _ptOne.getPair().getPair().getFirst();
        long z_ = _ptOne.getCommon() * n_ * _vOne.getDeltaz() + d_ * _ptOne.getPair().getPair().getSecond();
        long dint_ = _ptOne.getCommon() * d_;
        return new CompactSpacePoint(new CompactPlanePoint(new PairNumber<Long,Long>(y_, z_), x_),dint_);
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

    public CustPointThreeDims getFourthPoint() {
        return fourthPoint;
    }

    @Override
    public String toString() {
        return firstPoint+SEPARATOR+secondPoint+SEPARATOR+thirdPoint+SEPARATOR+fourthPoint;
    }
}
