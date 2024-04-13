package aiki.gui.components.fight;

public final class PointBattle {
    private int xPoint;

    private int yPoint;

    public void addx(int _d) {
        xPoint += _d;
    }

    public void addy(int _d) {
        yPoint += _d;
    }
    public int getxPoint() {
        return xPoint;
    }

    public void setxPoint(int _x) {
        this.xPoint = _x;
    }

    public int getyPoint() {
        return yPoint;
    }

    public void setyPoint(int _y) {
        this.yPoint = _y;
    }
}
