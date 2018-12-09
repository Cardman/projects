package code.util;
import code.util.ints.Equallable;


/** Not compared in tests, not use in res html, not instrospected*/

public final class PairBoolean implements Equallable<PairBoolean> {

    private boolean first;

    private boolean second;

    public PairBoolean() {

    }
    public PairBoolean(boolean _first,boolean _second) {
        first = _first;
        second = _second;
    }
    public PairBoolean(PairBoolean _other) {
        first = _other.first;
        second = _other.second;
    }

    public boolean getFirst() {
        return first;
    }

    public void setFirst(boolean _first) {
        first = _first;
    }

    public boolean getSecond() {
        return second;
    }

    public void setSecond(boolean _second) {
        second = _second;
    }

    @Override
    public boolean eq(PairBoolean _g) {
        if (first != _g.first) {
            return false;
        }
        if (second != _g.second) {
            return false;
        }
        return true;
    }
}
