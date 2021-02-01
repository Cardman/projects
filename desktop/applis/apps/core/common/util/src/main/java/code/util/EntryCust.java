package code.util;

public final class EntryCust<K, V> {

    private final K key;

    private V value;

    EntryCust(K _k, V _v) {
        key = _k;
        value = _v;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V _v) {
        value = _v;
    }

}
