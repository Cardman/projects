package aiki.util;

public final class CommonParam<K,V> {
    private final K key;
    private V value;

    public CommonParam(K _key, V _value) {
        this.key = _key;
        this.value = _value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V _v) {
        this.value = _v;
    }
}
