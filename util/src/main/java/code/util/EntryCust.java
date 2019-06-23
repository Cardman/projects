package code.util;

import code.util.ints.SimpleEntry;


public final class EntryCust<K, V> implements SimpleEntry {

    private K key;

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

    @Override
    public Object getSimpleKey() {
        return getKey();
    }

    @Override
    public Object getSimpleValue() {
        return getValue();
    }
}
