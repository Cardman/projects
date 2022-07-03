package code.threads;

import java.util.Map.Entry;

public interface AbstractConcurrentMap<K,V> {
    Iterable<K> keySet();
    Iterable<V> values();
    Iterable<Entry<K,V>> entrySet();
    boolean containsKey(K _key);
    boolean containsValue(V _value);
    boolean isEmpty();
    int size();
    void clear();
    V get(K _key);
    V remove(K _key);
    V put(K _key, V _value);

    V replace(K _key, V _value);
    V putIfAbsent(K _key, V _value);
}
