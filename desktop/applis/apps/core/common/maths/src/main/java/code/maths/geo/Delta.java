package code.maths.geo;

import code.maths.Rate;

public final class Delta {

    private Rate dx = Rate.zero();

    private Rate dy = Rate.zero();

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
