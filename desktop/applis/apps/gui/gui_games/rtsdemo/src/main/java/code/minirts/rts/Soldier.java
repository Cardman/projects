package code.minirts.rts;

public final class Soldier {

    private int x;

    private int y;

    private int destx;

    private int desty;

    private boolean selected;

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

    public int getDestx() {
        return destx;
    }

    public void setDestx(int _destx) {
        destx = _destx;
    }

    public int getDesty() {
        return desty;
    }

    public void setDesty(int _desty) {
        desty = _desty;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean _selected) {
        selected = _selected;
    }
}
