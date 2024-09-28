package code.util;

public final class ShortMap<V> extends NumberMap<Short, V> {

    public ShortMap() {
    }
    public ShortMap(int _capacity) {
        this(new CollCapacity(_capacity));
    }
    public ShortMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    long convert(Short _key) {
        return _key;
    }
}
