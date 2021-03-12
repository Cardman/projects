package code.maths.geo;
import code.maths.Rate;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class VectTwoDims implements Displayable {

    private static final String SEPARATOR = ",";
    private Rate deltax = Rate.zero();
    private Rate deltay = Rate.zero();
    public VectTwoDims() {
    }

    public VectTwoDims(int _deltax, int _deltay) {
        deltax = new Rate(_deltax);
        deltay = new Rate(_deltay);
    }

    public VectTwoDims(RatePoint _one, RatePoint _two) {
        deltax = Rate.minus(_two.getXcoords(), _one.getXcoords());
        deltay = Rate.minus(_two.getYcoords(), _one.getYcoords());
    }

    
    public static VectTwoDims newCustPoint(String _input) {
        StringList elts_ = StringUtil.splitStrings(_input, SEPARATOR);
        int x_ = NumberUtil.parseInt(elts_.first());
        int y_ = NumberUtil.parseInt(elts_.last());
        return new VectTwoDims(x_, y_);
    }

    public Rate squareLength() {
        return Rate.plus(sq(getDeltax()), sq(getDeltay()));
    }
    private static Rate sq(Rate _sq) {
        return Rate.multiply(_sq, _sq);
    }
    public Rate scal(VectTwoDims _b) {
        Rate f_ = Rate.multiply(getDeltax(), _b.getDeltax());
        Rate s_ = Rate.multiply(getDeltay(), _b.getDeltay());
        return Rate.plus(f_, s_);
    }

    public Rate det(VectTwoDims _b) {
        Rate f_ = Rate.multiply(getDeltax(), _b.getDeltay());
        Rate s_ = Rate.multiply(getDeltay(), _b.getDeltax());
        return Rate.minus(f_, s_);
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

    public boolean eq(VectTwoDims _obj) {
        if (!_obj.deltax.eq(deltax)) {
            return false;
        }
        return _obj.deltay.eq(deltay);
    }

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(deltax.toNumberString());
        str_.append(SEPARATOR);
        str_.append(deltay.toNumberString());
        return str_.toString();
    }
}
