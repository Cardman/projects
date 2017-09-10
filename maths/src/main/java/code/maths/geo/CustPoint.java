package code.maths.geo;
import code.serialize.CheckedData;
import code.util.StringList;
import code.util.ints.Equallable;
import code.xml.FromAndToString;

@CheckedData
public final class CustPoint implements Equallable<CustPoint> {

    private static final String SEPARATOR = ",";
    private int xCoords;
    private int yCoords;
    public CustPoint() {
    }

    public CustPoint(int _x, int _y) {
        xCoords = _x;
        yCoords = _y;
    }

    @FromAndToString
    public static CustPoint newCustPoint(String _input) {
        StringList elts_ = StringList.splitStrings(_input, SEPARATOR);
        int x_ = Integer.parseInt(elts_.first());
        int y_ = Integer.parseInt(elts_.last());
        return new CustPoint(x_, y_);
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

    @Override
    public boolean eq(CustPoint _obj) {
        if (_obj.xCoords != xCoords) {
            return false;
        }
        if (_obj.yCoords != yCoords) {
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
