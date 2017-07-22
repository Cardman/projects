package minirts.rts;

public final class CustPoint {

    private static final String SEP = ",";
    private int x;
    private int y;
    public CustPoint() {
    }

    public CustPoint(int _x, int _y) {
        x = _x;
        y = _y;
    }

    public void translatex(int _deltax) {
        x += _deltax;
    }

    public void translatey(int _deltay) {
        y += _deltay;
    }

    public int getX() {
        return x;
    }
    public void setX(int _x) {
        x = _x;
    }
    public int getY() {
        return y;
    }
    public void setY(int _y) {
        y = _y;
    }
    @Override
    public String toString() {
        return x+SEP+y;
    }
}
