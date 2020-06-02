package code.minirts.rts;

public final class Delta {

    private int dx;

    private int dy;

    public int getDx() {
        return dx;
    }

    public void setDx(int _dx) {
        dx = _dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int _dy) {
        dy = _dy;
    }

    public boolean isMoving() {
        return Math.abs(dx) > 0 || Math.abs(dy) > 0;
    }
}
