package code.maths.geo;
import code.maths.Rate;
import code.util.StringList;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class RatePointThreeDims implements Displayable {

    private static final String SEPARATOR = ",";
    private Rate xCoords = Rate.zero();
    private Rate yCoords = Rate.zero();
    private Rate zCoords = Rate.zero();

    public RatePointThreeDims() {
    }

    public RatePointThreeDims(CustPointThreeDims _r) {
        xCoords = new Rate(_r.getXcoords());
        yCoords = new Rate(_r.getYcoords());
        zCoords = new Rate(_r.getZcoords());
    }

    public RatePointThreeDims(RatePointThreeDims _r) {
        this(_r.xCoords, _r.yCoords, _r.zCoords);
    }

    public RatePointThreeDims(Rate _x, Rate _y, Rate _z) {
        xCoords = new Rate(_x);
        yCoords = new Rate(_y);
        zCoords = new Rate(_z);
    }

    
    public static RatePointThreeDims newCustRatePoint(String _input) {
        StringList elts_ = StringUtil.splitStrings(_input, SEPARATOR);
        Rate x_ = new Rate(elts_.first());
        Rate y_ = new Rate(elts_.get(1));
        Rate z_ = new Rate(elts_.last());
        return newRefRatePoint(x_, y_, z_);
    }

    public static RatePointThreeDims newRefRatePoint(Rate _x, Rate _y, Rate _z) {
        RatePointThreeDims r_ = new RatePointThreeDims();
        r_.xCoords = _x;
        r_.yCoords = _y;
        r_.zCoords = _z;
        return r_;
    }

    public CustPointThreeDims toCustPoint() {
        return new CustPointThreeDims((int)xCoords.intPart().ll(), (int)yCoords.intPart().ll(), (int)zCoords.intPart().ll());
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
    public Rate getZcoords() {
        return zCoords;
    }
    public void setZcoords(Rate _z) {
        zCoords = _z;
    }

    public boolean eq(RatePointThreeDims _obj) {
        if (Rate.different(_obj.xCoords , xCoords)) {
            return false;
        }
        if (Rate.different(_obj.yCoords , yCoords)) {
            return false;
        }
        return !Rate.different(_obj.zCoords, zCoords);
    }

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder(xCoords.toNumberString());
        str_.append(SEPARATOR);
        str_.append(yCoords.toNumberString());
        str_.append(SEPARATOR);
        str_.append(zCoords.toNumberString());
        return str_.toString();
    }
}
