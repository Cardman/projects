package aiki.map.util;

import code.util.core.NumberUtil;

public final class ScreenCoords {

    private static final String SEPARATOR = ";";

    private int xCoords;

    private int yCoords;

    public ScreenCoords() {
    }

    public ScreenCoords(int _x, int _y) {
        xCoords = _x;
        yCoords = _y;
    }

    public boolean eq(ScreenCoords _g) {
        if (!NumberUtil.eq(xCoords, _g.xCoords)) {
            return false;
        }
        return NumberUtil.eq(yCoords, _g.yCoords);
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

    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(xCoords);
        str_.append(SEPARATOR);
        str_.append(yCoords);
        return str_.toString();
    }
}
