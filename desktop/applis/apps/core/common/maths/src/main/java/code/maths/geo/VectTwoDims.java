package code.maths.geo;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Displayable;
import code.util.ints.Equallable;

public final class VectTwoDims implements Equallable<VectTwoDims>, Displayable {

    private static final String SEPARATOR = ",";
    private int deltax;
    private int deltay;
    public VectTwoDims() {
    }

    public VectTwoDims(int _deltax, int _deltay) {
        deltax = _deltax;
        deltay = _deltay;
    }

    public VectTwoDims(CustPoint _one, CustPoint _two) {
        deltax = _two.getXcoords() - _one.getXcoords();
        deltay = _two.getYcoords() - _one.getYcoords();
    }

    
    public static VectTwoDims newCustPoint(String _input) {
        StringList elts_ = StringUtil.splitStrings(_input, SEPARATOR);
        int x_ = NumberUtil.parseInt(elts_.first());
        int y_ = NumberUtil.parseInt(elts_.last());
        return new VectTwoDims(x_, y_);
    }

    public long squareLength() {
        return sq(getDeltax()) + sq(getDeltay());
    }
    private static long sq(long _sq) {
        return _sq * _sq;
    }
    public long scal(VectTwoDims _b) {
        long f_ = (long)getDeltax() * _b.getDeltax();
        long s_ = (long)getDeltay() * _b.getDeltay();
        return f_ + s_;
    }

    public long det(VectTwoDims _b) {
        long f_ = (long)getDeltax() * _b.getDeltay();
        long s_ = (long)getDeltay() * _b.getDeltax();
        return f_ - s_;
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

    @Override
    public boolean eq(VectTwoDims _obj) {
        if (_obj.deltax != deltax) {
            return false;
        }
        return _obj.deltay == deltay;
    }

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(deltax);
        str_.append(SEPARATOR);
        str_.append(deltay);
        return str_.toString();
    }
}
