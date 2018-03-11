package aiki.map.util;
import code.util.Numbers;
import code.util.ints.Displayable;
import code.util.ints.Equallable;

public final class ScreenCoords implements Equallable<ScreenCoords>, Displayable {

    private static final String SEPARATOR = ";";

    private int xCoords;

    private int yCoords;

    public ScreenCoords() {
    }

    public ScreenCoords(int _x, int _y) {
        xCoords = _x;
        yCoords = _y;
    }

    @Override
    public boolean eq(ScreenCoords _g) {
        if (!Numbers.eq(xCoords, _g.xCoords)) {
            return false;
        }
        if (!Numbers.eq(yCoords, _g.yCoords)) {
            return false;
        }
        return true;
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
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(xCoords);
        str_.append(SEPARATOR);
        str_.append(yCoords);
        return str_.toString();
    }
}
