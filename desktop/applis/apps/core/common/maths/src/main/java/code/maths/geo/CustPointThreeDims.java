package code.maths.geo;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class CustPointThreeDims {

    private static final String SEPARATOR = ",";
    private final int xCoords;
    private final int yCoords;
    private final int zCoords;

    public CustPointThreeDims(int _x, int _y, int _z) {
        xCoords = _x;
        yCoords = _y;
        zCoords = _z;
    }

    
    public static CustPointThreeDims newCustPointThreeDims(String _input) {
        StringList elts_ = StringUtil.splitStrings(_input, SEPARATOR);
        int x_ = NumberUtil.parseInt(elts_.first());
        int y_ = NumberUtil.parseInt(elts_.get(1));
        int z_ = NumberUtil.parseInt(elts_.last());
        return new CustPointThreeDims(x_, y_, z_);
    }

    public int getXcoords() {
        return xCoords;
    }

    public int getYcoords() {
        return yCoords;
    }

    public int getZcoords() {
        return zCoords;
    }

    public boolean eq(CustPointThreeDims _obj) {
        if (_obj.xCoords != xCoords) {
            return false;
        }
        if (_obj.yCoords != yCoords) {
            return false;
        }
        return _obj.zCoords == zCoords;
    }

}
