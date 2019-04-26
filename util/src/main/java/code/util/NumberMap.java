package code.util;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;

public final class NumberMap<K extends Number, V> extends AbsMap<K, V> {

//    //list cannot be null, even by reflection
//    private final CustList<EntryCust<K,V>> list = new CustList<EntryCust<K,V>>();

    public NumberMap() {
    }

    public NumberMap(ListableEntries<K, V> _arg0) {
        putAllMap(_arg0);
    }

    
    public NumberMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    public CustList<V> getValues(K _key) {
        CustList<V> values_ = new CustList<V>();
        if (_key == null) {
            for (EntryCust<K, V> e:getList()) {
                if (e.getKey() == null) {
                    values_.add(e.getValue());
                }
            }
            return values_;
        }
        long k_ = _key.longValue();
        for (EntryCust<K, V> e:getList()) {
            Number o_ = e.getKey();
            if (o_ == null) {
                continue;
            }
            if (k_ == o_.longValue()) {
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
        return s_;
    }
    public void retainKeys(Numbers<K> _keys) {
        for (K k: getKeys()) {
            long lg_ = k.longValue();
            if (!_keys.containsObj(lg_)) {
                removeKey(k);
            }
        }
    }

    @Override
    public Numbers<K> getKeys() {
        Numbers<K> s_ = new Numbers<K>();
        for (EntryCust<K, V> e: getList()) {
            s_.add(e.getKey());
        }
        return s_;
    }

    @Override
    public Numbers<K> getKeysNullValue() {
        Numbers<K> list_ = new Numbers<K>();
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
        if (_key == null) {
            for (EntryCust<K, V> e:getList()) {
                if (e.getKey() == null) {
                    return index_;
                }
                index_++;
            }
            return CustList.INDEX_NOT_FOUND_ELT;
        }
//        if (!(_key instanceof Number)) {
//            return CustList.INDEX_NOT_FOUND_ELT;
//        }
        Number k_ = _key;
        for (EntryCust<K, V> e:getList()) {
            Number o_ = e.getKey();
            if (o_ == null) {
                continue;
            }
            if (k_.longValue() == o_.longValue()) {
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
