package code.maths.geo;
import code.sml.FromAndToString;
import code.util.StringList;
import code.util.ints.Displayable;
import code.util.ints.Equallable;

public final class CustPointThreeDims implements Equallable<CustPointThreeDims>, Displayable {

    private static final String SEPARATOR = ",";
    private int xCoords;
    private int yCoords;
    private int zCoords;
    public CustPointThreeDims() {
    }

    public CustPointThreeDims(int _x, int _y, int _z) {
        xCoords = _x;
        yCoords = _y;
        zCoords = _z;
    }

    @FromAndToString
    public static CustPointThreeDims newCustPointThreeDims(String _input) {
        StringList elts_ = StringList.splitStrings(_input, SEPARATOR);
        int x_ = Integer.parseInt(elts_.first());
        int y_ = Integer.parseInt(elts_.get(1));
        int z_ = Integer.parseInt(elts_.last());
        return new CustPointThreeDims(x_, y_, z_);
    }

    public int getXcoords() {
        return xCoords;
    }
    public void setXcoords(int _x) {
        xCoords = _x;
    }
    public int getYcoords() {
        return yCoords;
    }
    public void setYcoords(int _y) {
        yCoords = _y;
    }

    public int getZcoords() {
        return zCoords;
    }

    public void setZcoords(int _z) {
        zCoords = _z;
    }

    @Override
    public boolean eq(CustPointThreeDims _obj) {
        if (_obj.xCoords != xCoords) {
            return false;
        }
        if (_obj.yCoords != yCoords) {
            return false;
        }
        if (_obj.zCoords != zCoords) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return display();
    }

    @FromAndToString
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(xCoords);
        str_.append(SEPARATOR);
        str_.append(yCoords);
        str_.append(SEPARATOR);
        str_.append(zCoords);
        return str_.toString();
    }
}
