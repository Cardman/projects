package code.maths.geo;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class CustPoint {

    private static final String SEPARATOR = ",";
    private final int xCoords;
    private final int yCoords;

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

    public int getYcoords() {
        return yCoords;
    }

    public boolean eq(CustPoint _obj) {
        if (_obj.xCoords != xCoords) {
            return false;
        }
        return _obj.yCoords == yCoords;
    }

}
