package code.minirts.rts;

import code.maths.Rate;

public final class Soldier {

    private Rate locx = Rate.zero();

    private Rate locy = Rate.zero();

    private Rate destx = Rate.zero();

    private Rate desty = Rate.zero();

    private boolean selected;

    public Rate getLocx() {
        return locx;
    }

    public void setLocx(Rate _x) {
        locx = _x;
    }

    public Rate getLocy() {
        return locy;
    }

    public void setLocy(Rate _y) {
        locy = _y;
    }

    public Rate getDestx() {
        return destx;
    }

    public void setDestx(Rate _destx) {
        destx = _destx;
    }

    public Rate getDesty() {
        return desty;
    }

    public void setDesty(Rate _desty) {
        desty = _desty;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean _selected) {
        selected = _selected;
    }
}
