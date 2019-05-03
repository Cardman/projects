package code.util;

public final class PairEq<T,S> {

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

}
