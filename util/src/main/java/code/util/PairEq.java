package code.util;
import code.util.ints.Equallable;


/** Not compared in tests, not use in res html, not instrospected*/

public final class PairEq<T extends Equallable<T>,S extends Equallable<S>> implements Equallable<PairEq<T,S>> {

    private T first;

    private S second;

    public PairEq() {

    }
    public PairEq(T _first,S _second) {
        first = _first;
        second = _second;
    }
    public PairEq(PairEq<T,S> _other) {
        first = _other.first;
        second = _other.second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T _first) {
        first = _first;
    }

    public S getSecond() {
        return second;
    }

    public void setSecond(S _second) {
        second = _second;
    }

    @Override
    public boolean eq(PairEq<T, S> _g) {
        if (first == null) {
            if (second == null) {
                if (_g.second != null) {
                    return false;
                }
                return _g.first == null;
            }
            if (_g.second == null) {
                return false;
            }
            if (!second.eq(_g.second)) {
                return false;
            }
            return _g.first == null;
        }
        if (_g.first == null) {
            return false;
        }
        if (second == null) {
            if (!first.eq(_g.first)) {
                return false;
            }
            return _g.second == null;
        }
        if (_g.second == null) {
            return false;
        }
        if (!first.eq(_g.first)) {
            return false;
        }
        if (!second.eq(_g.second)) {
            return false;
        }
        return true;
    }
}
