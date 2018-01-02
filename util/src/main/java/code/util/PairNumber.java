package code.util;
import code.util.annot.RwXml;
import code.util.ints.Equallable;

/** Not compared in tests, not use in res html, not instrospected*/
@RwXml
public final class PairNumber<T extends Number,S extends Number> implements Equallable<PairNumber<T,S>> {

    private static final String SEPARATOR = " ";

    private T first;

    private S second;

    public PairNumber() {

    }
    public PairNumber(T _first,S _second) {
        first = _first;
        second = _second;
    }
    public PairNumber(PairNumber<T,S> _other) {
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
    public boolean eq(PairNumber<T, S> _g) {
        if (first == null) {
            if (second == null) {
                if (_g.second != null) {
                    return false;
                }
                return _g.first == null;
            }
            if (Numbers.compare(second, _g.second) != CustList.EQ_CMP) {
                return false;
            }
            return _g.first == null;
        }
        if (second == null) {
            if (Numbers.compare(first, _g.first) != CustList.EQ_CMP) {
                return false;
            }
            return _g.second == null;
        }
        if (Numbers.compare(first, _g.first) != CustList.EQ_CMP) {
            return false;
        }
        if (Numbers.compare(second, _g.second) != CustList.EQ_CMP) {
            return false;
        }
        return true;
    }
}
