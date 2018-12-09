package code.maths.geo;
import code.maths.Rate;
import code.maths.matrix.Matrix;
import code.maths.matrix.Vect;
import code.util.StringList;
import code.util.ints.Displayable;
import code.util.ints.Equallable;

public final class VectThreeDims implements Equallable<VectThreeDims>, Displayable {

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
        StringList elts_ = StringList.splitStrings(_input, SEPARATOR);
        int x_ = Integer.parseInt(elts_.first());
        int y_ = Integer.parseInt(elts_.get(1));
        int z_ = Integer.parseInt(elts_.last());
        return new VectThreeDims(x_, y_, z_);
    }

    public long squareLength() {
        return getDeltax() * getDeltax() + getDeltay() * getDeltay() + getDeltaz() * getDeltaz();
    }

    public long scal(VectThreeDims _b) {
        return getDeltax() * _b.getDeltax() + getDeltay() * _b.getDeltay() + getDeltaz() * _b.getDeltaz();
    }

    public VectThreeDims vectProd(VectThreeDims _b) {
        long rx_ = getDeltay() * _b.getDeltaz() - getDeltaz() * _b.getDeltay();
        long ry_ = -getDeltax() * _b.getDeltaz() + getDeltaz() * _b.getDeltax();
        long rz_ = getDeltax() * _b.getDeltay() - getDeltay() * _b.getDeltax();
        return new VectThreeDims((int)rx_, (int) ry_, (int) rz_);
    }

    public long quickDet(VectThreeDims _b, VectThreeDims _c) {
        long rx_ = _b.getDeltay() * _c.getDeltaz() - _b.getDeltaz() * _c.getDeltay();
        long ry_ = _b.getDeltax() * _c.getDeltaz() - _b.getDeltaz() * _c.getDeltax();
        long rz_ = _b.getDeltax() * _c.getDeltay() - _b.getDeltay() * _c.getDeltax();
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

    @Override
    public boolean eq(VectThreeDims _obj) {
        if (_obj.deltax != deltax) {
            return false;
        }
        if (_obj.deltay != deltay) {
            return false;
        }
        return true;
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
