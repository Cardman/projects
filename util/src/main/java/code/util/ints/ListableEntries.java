package code.util.ints;
import code.util.CustList;
import code.util.EntryCust;


public interface ListableEntries<K,V> extends CheckableMap, Countable, SimpleEntries {

    void move(K _oldKey, K _newKey);

    Iterable<EntryCust<K,V>> entryList();

    void putAllMap(ListableEntries<K, V> _m);

    CustList<K> getKeys();
    CustList<V> getValues(K _key);

    V getVal(K _key);

    K getKey(int _i);

    V getValue(int _i);

    void setValue(int _i, V _object);

    CustList<V> values();

    void put(K _k, V _v);

    void clear();

    CustList<K> getKeysNullValue();

    boolean contains(K _key);

    void removeKey(K _key);

}
