package code.maths.geo;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class CustPointThreeDims implements Displayable {

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

    public boolean eq(CustPointThreeDims _obj) {
        if (_obj.xCoords != xCoords) {
            return false;
        }
        if (_obj.yCoords != yCoords) {
            return false;
        }
        return _obj.zCoords == zCoords;
    }

    
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
