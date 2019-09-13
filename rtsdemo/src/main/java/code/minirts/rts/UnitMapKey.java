package code.minirts.rts;

import code.util.ints.Equallable;

public final class UnitMapKey implements Equallable<UnitMapKey> {

    private int curx;

    private int cury;

    private long millis;

    public UnitMapKey() {
    }

    public UnitMapKey(int _curx, int _cury) {
        curx = _curx;
        cury = _cury;
        millis = System.currentTimeMillis();
    }

    public UnitMapKey(int _curx, int _cury, long _millis) {
        curx = _curx;
        cury = _cury;
        millis = _millis;
    }

    public int getCurx() {
        return curx;
    }

    public void setCurx(int _curx) {
        curx = _curx;
    }

    public int getCury() {
        return cury;
    }

    public void setCury(int _cury) {
        cury = _cury;
    }

    public long getMillis() {
        return millis;
    }

    public void setMillis(long _millis) {
        millis = _millis;
    }

    @Override
    public boolean eq(UnitMapKey _u) {
        if (_u.curx != curx) {
            return false;
        }
        if (_u.cury != cury) {
            return false;
        }
        if (_u.millis != millis) {
            return false;
        }
        return true;
    }
}
