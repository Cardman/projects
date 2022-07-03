package code.mock;

import code.threads.AbstractConcurrentMap;
import code.util.AbsMap;
import code.util.CustList;
import code.util.EntryCust;

import java.util.AbstractMap;
import java.util.Map;

public abstract class MockConcurrentMap<K,V> implements AbstractConcurrentMap<K,V> {
    private final AbsMap<K,V> map;

    protected MockConcurrentMap(AbsMap<K, V> _m) {
        this.map = _m;
    }

    @Override
    public Iterable<K> keySet() {
        return map.getKeys().getList();
    }

    @Override
    public Iterable<V> values() {
        return map.values().getList();
    }

    @Override
    public Iterable<Map.Entry<K, V>> entrySet() {
        CustList<Map.Entry<K, V>> list_ = new CustList<Map.Entry<K, V>>();
        for (EntryCust<K,V> e: map.entryList()) {
            list_.add(new AbstractMap.SimpleEntry<K, V>(e.getKey(),e.getValue()));
        }
        return list_.getList();
    }

    @Override
    public boolean containsKey(K _k) {
        return map.contains(_k);
    }

    @Override
    public boolean containsValue(V _v) {
        for (EntryCust<K,V> e: map.entryList()) {
            if (_v == e.getValue()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public V get(K _key) {
        return map.getVal(_key);
    }

    @Override
    public V remove(K _key) {
        V old_ = map.getVal(_key);
        map.removeKey(_key);
        return old_;
    }

    @Override
    public V put(K _key, V _value) {
        V old_ = map.getVal(_key);
        map.put(_key,_value);
        return old_;
    }

    @Override
    public V replace(K _key, V _value) {
        V old_ = map.getVal(_key);
        if (old_ == null) {
            return null;
        }
        map.put(_key,_value);
        return old_;
    }

    @Override
    public V putIfAbsent(K _key, V _value) {
        V old_ = map.getVal(_key);
        if (old_ != null) {
            return old_;
        }
        map.put(_key,_value);
        return null;
    }
}
