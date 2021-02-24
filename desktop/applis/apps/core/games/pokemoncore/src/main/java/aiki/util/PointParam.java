package aiki.util;

public final class PointParam<T> {
    private final Point key;
    private T value;

    public PointParam(Point _key, T _value) {
        this.key = _key;
        this.value = _value;
    }

    public Point getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T _value) {
        this.value = _value;
    }
}
