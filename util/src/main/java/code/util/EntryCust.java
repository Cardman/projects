package code.util;

import code.util.ints.SimpleEntry;


public final class EntryCust<K, V> implements SimpleEntry {

    private K key;

    private V value;

    EntryCust(K _k, V _v) {
        key = _k;
        value = _v;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    public void setValue(V _v) {
        value = _v;
    }
}
