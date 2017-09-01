package code.util.ints;
import code.util.EntryCust;


public interface ListableEntries<K,V> {

    boolean isCorrect();

    void move(K _oldKey, K _newKey);

    Listable<EntryCust<K,V>> entryList();

    void putAllMap(ListableEntries<K, V> _m);

    Listable<K> getKeys();
    Listable<V> getValues(K _key);

    V getVal(K _key);

    void setValue(int _i, V _object);

    int size();

    boolean isEmpty();

    Listable<V> values();

    void put(K _k, V _v);

    void clear();

    Listable<K> getKeysNullValue();

    boolean contains(K _key);

    void removeKey(K _key);

}
