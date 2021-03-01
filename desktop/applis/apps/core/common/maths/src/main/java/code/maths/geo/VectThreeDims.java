package code.maths.geo;
import code.maths.Rate;
import code.maths.matrix.Matrix;
import code.maths.matrix.Vect;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class VectThreeDims implements Displayable {

    private static final String SEPARATOR = ",";
    private int deltax;
    private int deltay;
    private int deltaz;
    public VectThreeDims() {
    }

    public VectThreeDims(int _deltax, int _deltay, int _deltaz) {
        deltax = _deltax;
        deltay = _deltay;
        deltaz = _deltaz;
    }

    public VectThreeDims(CustPointThreeDims _one, CustPointThreeDims _two) {
        deltax = _two.getXcoords() - _one.getXcoords();
        deltay = _two.getYcoords() - _one.getYcoords();
        deltaz = _two.getZcoords() - _one.getZcoords();
    }

    
    public static VectThreeDims newCustPoint(String _input) {
        StringList elts_ = StringUtil.splitStrings(_input, SEPARATOR);
        int x_ = NumberUtil.parseInt(elts_.first());
        int y_ = NumberUtil.parseInt(elts_.get(1));
        int z_ = NumberUtil.parseInt(elts_.last());
        return new VectThreeDims(x_, y_, z_);
    }

    public long squareLength() {
        return sq(getDeltax()) + sq(getDeltay()) + sq(getDeltaz());
    }
    private static long sq(long _sq) {
        return _sq * _sq;
    }

    public long scal(VectThreeDims _b) {
        long f_ = (long)getDeltax() * _b.getDeltax();
        long s_ = (long)getDeltay() * _b.getDeltay();
        long t_ = (long)getDeltaz() * _b.getDeltaz();
        return f_ + s_ + t_;
    }
    public VectThreeDims vectProd(VectThreeDims _b) {
        long f1_ = (long)getDeltay() * _b.getDeltaz();
        long f2_ = (long)getDeltaz() * _b.getDeltay();
        long rx_ = f1_ - f2_;
        long s1_ = (long)-getDeltax() * _b.getDeltaz();
        long s2_ = (long)getDeltaz() * _b.getDeltax();
        long ry_ = s1_ + s2_;
        long t1_ = (long)getDeltax() * _b.getDeltay();
        long t2_ = (long)getDeltay() * _b.getDeltax();
        long rz_ = t1_ - t2_;
        return new VectThreeDims((int)rx_, (int) ry_, (int) rz_);
    }

    public long quickDet(VectThreeDims _b, VectThreeDims _c) {
        long f1_ = (long)_b.getDeltay() * _c.getDeltaz();
        long f2_ = (long)_b.getDeltaz() * _c.getDeltay();
        long rx_ = f1_ - f2_;
        long s1_ = (long)_b.getDeltax() * _c.getDeltaz();
        long s2_ = (long)_b.getDeltaz() * _c.getDeltax();
        long ry_ = s1_ - s2_;
        long t1_ = (long)_b.getDeltax() * _c.getDeltay();
        long t2_ = (long)_b.getDeltay() * _c.getDeltax();
        long rz_ = t1_ - t2_;
        return getDeltax()*rx_ - getDeltay()*ry_ + getDeltaz()*rz_;
    }

    public Rate det(VectThreeDims _b, VectThreeDims _c) {
        Matrix m_ = new Matrix();
        Vect v_ = new Vect();
        v_.add(new Rate(getDeltax()));
        v_.add(new Rate(_b.getDeltax()));
        v_.add(new Rate(_c.getDeltax()));
        m_.addLineRef(v_);
        v_ = new Vect();
        v_.add(new Rate(getDeltay()));
        v_.add(new Rate(_b.getDeltay()));
        v_.add(new Rate(_c.getDeltay()));
        m_.addLineRef(v_);
        v_ = new Vect();
        v_.add(new Rate(getDeltaz()));
        v_.add(new Rate(_b.getDeltaz()));
        v_.add(new Rate(_c.getDeltaz()));
        m_.addLineRef(v_);
        return m_.detSquare();
    }

    public int getDeltax() {
        return deltax;
    }

    public void setDeltax(int _deltax) {
        deltax = _deltax;
    }

    public int getDeltay() {
        return deltay;
    }

    public void setDeltay(int _deltay) {
        deltay = _deltay;
    }

    public int getDeltaz() {
        return deltaz;
    }

    public void setDeltaz(int _deltaz) {
        deltaz = _deltaz;
    }

    public boolean eq(VectThreeDims _obj) {
        if (_obj.deltax != deltax) {
            return false;
        }
        if (_obj.deltay != deltay) {
            return false;
        }
        return _obj.deltaz == deltaz;
    }

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(deltax);
        str_.append(SEPARATOR);
        str_.append(deltay);
        str_.append(SEPARATOR);
        str_.append(deltaz);
        return str_.toString();
    }
}
