package code.maths.geo;
import code.util.Numbers;
import code.util.StringList;
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
        StringList elts_ = StringList.splitStrings(_input, SEPARATOR);
        int x_ = Numbers.parseInt(elts_.first());
        int y_ = Numbers.parseInt(elts_.last());
        return new VectTwoDims(x_, y_);
    }

    public long squareLength() {
        return getDeltax() * getDeltax() + getDeltay() * getDeltay();
    }

    public long scal(VectTwoDims _b) {
        return getDeltax() * _b.getDeltax() + getDeltay() * _b.getDeltay();
    }

    public long det(VectTwoDims _b) {
        return getDeltax()*_b.getDeltay()-getDeltay()*_b.getDeltax();
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
        return str_.toString();
    }
}
