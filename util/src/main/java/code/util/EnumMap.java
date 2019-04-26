package code.util;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;




public final class EnumMap<K extends Enum<K>, V> extends AbsMap<K,V> {

//    //list cannot be null, even by reflection
//    private final CustList<EntryCust<K,V>> list = new CustList<EntryCust<K,V>>();

    public EnumMap() {
    }

    public EnumMap(ListableEntries<K, V> _arg0) {
        putAllMap(_arg0);
    }
    
    public EnumMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    public CustList<V> getValues(K _key) {
        CustList<V> values_ = new CustList<V>();
        for (EntryCust<K, V> e:getList()) {
            if (_key == e.getKey()) {
                values_.add(e.getValue());
            }
        }
        return values_;
    }

    @Override
    public CustList<V> values() {
        CustList<V> s_ = new CustList<V>();
        for (EntryCust<K, V> e: getList()) {
            s_.add(e.getValue());
        }
//        return new CustList<>(super.values());
        return s_;
    }

    public void retainKeys(EnumList<K> _keys) {
        for (K k: getKeys()) {
            if (!_keys.containsObj(k)) {
                removeKey(k);
            }
        }
    }

    @Override
    public EnumList<K> getKeys() {
        EnumList<K> s_ = new EnumList<K>();
        for (EntryCust<K, V> e: getList()) {
            s_.add(e.getKey());
        }
        return s_;
    }

    @Override
    public CustList<K> getKeysNullValue() {
        CustList<K> list_ = new CustList<K>();
        for (EntryCust<K, V> e: getList()) {
            if (e.getValue() != null) {
                continue;
            }
            list_.add(e.getKey());
        }
        return list_;
    }

    @Override
    public void putAllMap(ListableEntries<K, V> _m) {
        //setModified();
        for (EntryCust<K,V> e: _m.entryList()) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override
    int indexOfEntry(K _key) {
        int index_ = CustList.FIRST_INDEX;
        for (EntryCust<K, V> e:getList()) {
            if (e.getKey() == _key) {
                return index_;
            }
            index_++;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }

//    @Override
//    public boolean isEmpty() {
//        return list.isEmpty();
//    }
//
//    @Override
//    public int size() {
//        return list.size();
//    }
}
