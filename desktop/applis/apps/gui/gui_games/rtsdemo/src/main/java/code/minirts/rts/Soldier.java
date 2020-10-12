package code.minirts.rts;

public final class Soldier {

    private int locx;

    private int locy;

    private int destx;

    private int desty;

    private boolean selected;

    public int getLocx() {
        return locx;
    }

    public void setLocx(int _x) {
        locx = _x;
    }

    public int getLocy() {
        return locy;
    }

    public void setLocy(int _y) {
        locy = _y;
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
