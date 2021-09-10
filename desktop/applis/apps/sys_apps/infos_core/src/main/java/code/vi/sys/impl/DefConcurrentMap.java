package code.vi.sys.impl;

import code.threads.AbstractConcurrentMap;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class DefConcurrentMap<K,V> implements AbstractConcurrentMap<K,V> {
    private final ConcurrentMap<K,V> elements = new ConcurrentHashMap<K, V>();
    @Override
    public Iterable<K> keySet() {
        return elements.keySet();
    }

    @Override
    public Iterable<V> values() {
        return elements.values();
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return elements.entrySet();
    }

    @Override
    public boolean containsKey(K _key) {
        return elements.containsKey(_key);
    }

    @Override
    public boolean containsValue(V _value) {
        return elements.containsValue(_value);
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public void clear() {
        elements.clear();
    }

    @Override
    public V get(K _key) {
        return elements.get(_key);
    }

    @Override
    public V remove(K _key) {
        return elements.remove(_key);
    }

    @Override
    public V put(K _key, V _value) {
        return elements.put(_key, _value);
    }

    @Override
    public V replace(K _key, V _value) {
        return elements.replace(_key, _value);
    }

    @Override
    public V putIfAbsent(K _key, V _value) {
        return elements.putIfAbsent(_key, _value);
    }
}
