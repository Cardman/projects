package code.maths.geo;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Displayable;
import code.util.ints.Equallable;

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

    
    public static CustPoint newCustPoint(String _input) {
        StringList elts_ = StringUtil.splitStrings(_input, SEPARATOR);
        int x_ = NumberUtil.parseInt(elts_.first());
        int y_ = NumberUtil.parseInt(elts_.last());
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
        return _obj.yCoords == yCoords;
    }

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(xCoords);
        str_.append(SEPARATOR);
        str_.append(yCoords);
        return str_.toString();
    }
}
