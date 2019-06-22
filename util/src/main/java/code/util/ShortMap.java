package code.util;

public final class ShortMap<V> extends NumberMap<Short, V> {

    public ShortMap() {
    }

    public ShortMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    long convert(Short _key) {
        return _key;
    }
}
