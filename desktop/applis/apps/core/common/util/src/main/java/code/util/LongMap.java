package code.util;

public final class LongMap<V> extends NumberMap<Long, V> {

    public LongMap() {
    }
    public LongMap(int _capacity) {
        this(new CollCapacity(_capacity));
    }
    public LongMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    long convert(Long _key) {
        return _key;
    }
}
