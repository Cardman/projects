package code.maths.geo;
import code.serialize.CheckedData;
import code.sml.FromAndToString;
import code.util.StringList;
import code.util.ints.Displayable;
import code.util.ints.Equallable;

@CheckedData
public final class CustPoint implements Equallable<CustPoint>, Displayable {

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
        return str_.toString();
    }
}
