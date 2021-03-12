package code.maths.geo;
import code.maths.Rate;
import code.util.StringList;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class RatePoint implements Displayable {

    private static final String SEPARATOR = ",";
    private Rate xCoords = Rate.zero();
    private Rate yCoords = Rate.zero();

    public RatePoint() {
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

    
    public static RatePoint newCustRatePoint(String _input) {
        StringList elts_ = StringUtil.splitStrings(_input, SEPARATOR);
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

    public Rate sqDist(RatePoint _other){
        Rate ds_ = Rate.zero();
        ds_.addNb(square(Rate.minus(xCoords,_other.xCoords)));
        ds_.addNb(square(Rate.minus(yCoords,_other.yCoords)));
        return ds_;
    }
    private static Rate square(Rate _nb) {
        return Rate.multiply(_nb,_nb);
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

    public boolean eq(RatePoint _obj) {
        if (Rate.different(_obj.xCoords , xCoords)) {
            return false;
        }
        return !Rate.different(_obj.yCoords, yCoords);
    }

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(xCoords.toNumberString());
        str_.append(SEPARATOR);
        str_.append(yCoords.toNumberString());
        return str_.toString();
    }
}
