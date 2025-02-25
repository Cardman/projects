package aiki.beans.fight;

public final class AbsRowGridPkBean<T> {
    private final String first;
    private final int second;
    private final T info;

    public AbsRowGridPkBean(String _f, int _s, T _i) {
        this.first = _f;
        this.second = _s;
        this.info = _i;
    }

    public String getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public T getInfo() {
        return info;
    }
}
