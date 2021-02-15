package code.images;

public final class IntPoint {
    private int xcoords;
    private int ycoords;

    public IntPoint() {

    }
    public IntPoint(int _first,int _second) {
        xcoords = _first;
        ycoords = _second;
    }
    public IntPoint(IntPoint _other) {
        xcoords = _other.xcoords;
        ycoords = _other.ycoords;
    }

    public int getXcoords() {
        return xcoords;
    }

    public void setXcoords(int _xcoords) {
        xcoords = _xcoords;
    }

    public int getYcoords() {
        return ycoords;
    }

    public void setYcoords(int _ycoords) {
        ycoords = _ycoords;
    }

    public boolean eq(IntPoint _g) {
        return xcoords == _g.xcoords && ycoords == _g.ycoords;
    }
}
