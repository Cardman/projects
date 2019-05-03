package code.util;

/** Not compared in tests, not use in res html, not instrospected*/

public final class PairNumber<T extends Number,S extends Number> {

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

}
