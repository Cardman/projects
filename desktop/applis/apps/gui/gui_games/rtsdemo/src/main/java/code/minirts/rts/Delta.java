package code.minirts.rts;

import code.maths.Rate;

public final class Delta {

    private Rate dx = Rate.zero();

    private Rate dy = Rate.zero();

    public static Rate max(Rate _x, Rate _y) {
        if (Rate.strGreater(_x, _y)){
            return _x;
        }
        return _y;
    }

    public static Rate min(Rate _x, Rate _y) {
        if (Rate.strGreater(_x, _y)){
            return _y;
        }
        return _x;
    }

    public Rate getDx() {
        return dx;
    }

    public void setDx(Rate _dx) {
        dx = _dx;
    }

    public Rate getDy() {
        return dy;
    }

    public void setDy(Rate _dy) {
        dy = _dy;
    }

    public boolean isMoving() {
        return !dx.isZero() || !dy.isZero();
    }
}
