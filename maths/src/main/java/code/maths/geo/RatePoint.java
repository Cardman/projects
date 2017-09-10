package code.maths.geo;
import code.maths.Rate;
import code.serialize.CheckedData;
import code.util.StringList;
import code.util.ints.Equallable;
import code.xml.FromAndToString;

@CheckedData
public final class RatePoint implements Equallable<RatePoint> {

    private static final String SEPARATOR = ",";
    private Rate xCoords;
    private Rate yCoords;

    private RatePoint() {
    }

    public RatePoint(CustPoint _r) {
        xCoords = new Rate(_r.getXcoords());
        yCoords = new Rate(_r.getYcoords());
    }

    public RatePoint(RatePoint _r) {
        this(_r.xCoords, _r.yCoords);
    }

    public RatePoint(Rate _x, Rate _y) {
        xCoords = new Rate(_x);
        yCoords = new Rate(_y);
    }

    @FromAndToString
    public static RatePoint newCustRatePoint(String _input) {
        StringList elts_ = StringList.splitStrings(_input, SEPARATOR);
        Rate x_ = new Rate(elts_.first());
        Rate y_ = new Rate(elts_.last());
        return newRefRatePoint(x_, y_);
    }

    public static RatePoint newRefRatePoint(Rate _x, Rate _y) {
        RatePoint r_ = new RatePoint();
        r_.xCoords = _x;
        r_.yCoords = _y;
        return r_;
    }

    public CustPoint toCustPoint() {
        return new CustPoint((int)xCoords.intPart().ll(), (int)yCoords.intPart().ll());
    }

    public Rate getXcoords() {
        return xCoords;
    }
    public void setXcoords(Rate _x) {
        xCoords = _x;
    }
    public Rate getYcoords() {
        return yCoords;
    }
    public void setYcoords(Rate _y) {
        yCoords = _y;
    }

    @Override
    public boolean eq(RatePoint _obj) {
        if (Rate.different(_obj.xCoords , xCoords)) {
            return false;
        }
        if (Rate.different(_obj.yCoords , yCoords)) {
            return false;
        }
        return true;
    }

    @FromAndToString
    @Override
    public String toString() {
        return xCoords+SEPARATOR+yCoords;
    }
}
