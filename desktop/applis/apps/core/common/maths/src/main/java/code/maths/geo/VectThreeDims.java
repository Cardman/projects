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
    private Rate deltax = Rate.zero();
    private Rate deltay = Rate.zero();
    private Rate deltaz = Rate.zero();
    public VectThreeDims() {
    }

    public VectThreeDims(int _deltax, int _deltay, int _deltaz) {
        deltax = new Rate(_deltax);
        deltay = new Rate(_deltay);
        deltaz = new Rate(_deltaz);
    }

    public VectThreeDims(Rate _deltax, Rate _deltay, Rate _deltaz) {
        deltax = new Rate(_deltax);
        deltay = new Rate(_deltay);
        deltaz = new Rate(_deltaz);
    }
    public VectThreeDims(RatePointThreeDims _one, RatePointThreeDims _two) {
        deltax = Rate.minus(_two.getXcoords(), _one.getXcoords());
        deltay = Rate.minus(_two.getYcoords(), _one.getYcoords());
        deltaz = Rate.minus(_two.getZcoords(), _one.getZcoords());
    }

    
    public static VectThreeDims newCustPoint(String _input) {
        StringList elts_ = StringUtil.splitStrings(_input, SEPARATOR);
        int x_ = NumberUtil.parseInt(elts_.first());
        int y_ = NumberUtil.parseInt(elts_.get(1));
        int z_ = NumberUtil.parseInt(elts_.last());
        return new VectThreeDims(x_, y_, z_);
    }

    public Rate squareLength() {
        return Rate.plus(Rate.plus(sq(getDeltax()), sq(getDeltay())), sq(getDeltaz()));
    }
    private static Rate sq(Rate _sq) {
        return Rate.multiply(_sq,_sq);
    }

    public Rate scal(VectThreeDims _b) {
        Rate f_ = Rate.multiply(getDeltax(), _b.getDeltax());
        Rate s_ = Rate.multiply(getDeltay(), _b.getDeltay());
        Rate t_ = Rate.multiply(getDeltaz(), _b.getDeltaz());
        return Rate.plus(Rate.plus(f_, s_), t_);
    }
    public VectThreeDims vectProd(VectThreeDims _b) {
        Rate f1_ = Rate.multiply(getDeltay(), _b.getDeltaz());
        Rate f2_ = Rate.multiply(getDeltaz(), _b.getDeltay());
        Rate rx_ = Rate.minus(f1_, f2_);
        Rate s1_ = Rate.multiply(getDeltax(), _b.getDeltaz()).opposNb();
        Rate s2_ = Rate.multiply(getDeltaz(), _b.getDeltax());
        Rate ry_ = Rate.plus(s1_,s2_);
        Rate t1_ = Rate.multiply(getDeltax(), _b.getDeltay());
        Rate t2_ = Rate.multiply(getDeltay(), _b.getDeltax());
        Rate rz_ = Rate.minus(t1_, t2_);
        return new VectThreeDims(rx_, ry_, rz_);
    }

    public Rate quickDet(VectThreeDims _b, VectThreeDims _c) {
        Rate f1_ = Rate.multiply(_b.getDeltay(), _c.getDeltaz());
        Rate f2_ = Rate.multiply(_b.getDeltaz(), _c.getDeltay());
        Rate rx_ = Rate.minus(f1_, f2_);
        Rate s1_ = Rate.multiply(_b.getDeltax(), _c.getDeltaz());
        Rate s2_ = Rate.multiply(_b.getDeltaz(), _c.getDeltax());
        Rate ry_ = Rate.minus(s1_, s2_);
        Rate t1_ = Rate.multiply(_b.getDeltax(), _c.getDeltay());
        Rate t2_ = Rate.multiply(_b.getDeltay(), _c.getDeltax());
        Rate rz_ = Rate.minus(t1_, t2_);
        return Rate.plus(Rate.minus(Rate.multiply(getDeltax(),rx_), Rate.multiply(getDeltay(),ry_)), Rate.multiply(getDeltaz(),rz_));
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

    public Rate getDeltax() {
        return deltax;
    }

    public void setDeltax(Rate _deltax) {
        deltax = _deltax;
    }

    public Rate getDeltay() {
        return deltay;
    }

    public void setDeltay(Rate _deltay) {
        deltay = _deltay;
    }

    public Rate getDeltaz() {
        return deltaz;
    }

    public void setDeltaz(Rate _deltaz) {
        deltaz = _deltaz;
    }

    public boolean eq(VectThreeDims _obj) {
        if (!_obj.deltax.eq(deltax)) {
            return false;
        }
        if (!_obj.deltay.eq(deltay)) {
            return false;
        }
        return _obj.deltaz.eq(deltaz);
    }

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(deltax.toNumberString());
        str_.append(SEPARATOR);
        str_.append(deltay.toNumberString());
        str_.append(SEPARATOR);
        str_.append(deltaz.toNumberString());
        return str_.toString();
    }
}
