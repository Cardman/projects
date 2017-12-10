package code.maths.geo;
import code.serialize.CheckedData;
import code.sml.FromAndToString;
import code.util.StringList;
import code.util.ints.Equallable;

@CheckedData
public final class VectTwoDims implements Equallable<VectTwoDims>{

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

    @FromAndToString
    public static VectTwoDims newCustPoint(String _input) {
        StringList elts_ = StringList.splitStrings(_input, SEPARATOR);
        int x_ = Integer.parseInt(elts_.first());
        int y_ = Integer.parseInt(elts_.last());
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

    @FromAndToString
    @Override
    public String toString() {
        return deltax+SEPARATOR+deltay;
    }
}
