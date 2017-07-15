package code.util;
import code.util.ints.ListableEntries;




public final class StringMap<V> extends AbsMap<String,V> {

//    //list cannot be null, even by reflection
//    private final transient CustList<EntryCust<String,V>> list = new CustList<EntryCust<String,V>>();

    public StringMap() {
    }

    public StringMap(ListableEntries<String, ? extends V> _arg0) {
        putAllMap(_arg0);
    }

//    @Override
//    CustList<EntryCust<String,V>> getList() {
//        return list;
//    }

//    public static void deleteLineReturn(StringMap<String> _map) {
//        for (EntryCust<String, String> e: _map.entryList()) {
//            e.setValue(StringList.removeStrings(e.getValue(), Constants.RETURN_LINE));
//        }
//    }

//    public StringMap<V,CustList<K>> reverseMap() {
//        StringMap<V,CustList<K>> reverseMap_ = new StringMap<V,CustList<K>>();
//        for (EntryCust<K, V> k:list) {
//            CustList<K> keys_ = reverseMap_.getVal(k.getValue());
//            if (keys_ == null) {
//                keys_ = new CustList<K>();
//                keys_.add(k.getKey());
//                reverseMap_.put(k.getValue(), keys_);
//            } else {
//                keys_.add(k.getKey());
//            }
//        }
//        return reverseMap_;
//    }

//    @Override
//    public CustList<EntryCust<String,V>> entryList() {
//        return list;
//    }

    public synchronized V synchronizedGet(String _key) {
        return getVal(_key);
    }

    @Override
    public CustList<V> getValues(String _key) {
        CustList<V> values_ = new CustList<V>();
        if (_key == null) {
            for (EntryCust<String, V> e:getList()) {
                if (e.getKey() == null) {
                    values_.add(e.getValue());
                }
            }
            return values_;
        }
        for (EntryCust<String, V> e:getList()) {
            String k_ = e.getKey();
            if (k_ == null) {
                continue;
            }
            if (StringList.quickEq(_key, k_)) {
                values_.add(e.getValue());
            }
        }
        return values_;
    }

    @Override
    public CustList<V> values() {
        CustList<V> s_ = new CustList<V>();
        for (EntryCust<String, V> e: getList()) {
            s_.add(e.getValue());
        }
//        return new CustList<>(super.values());
        return s_;
    }
    public void retainKeys(StringList _keys) {
        for (String k: getKeys()) {
            if (!_keys.containsObj(k)) {
                removeKey(k);
            }
        }
    }
//    public void retainValues(CustList<V> _values) {
//        for (EntryCust<String,V> e: list) {
//            if (!_values.containsObj(e.getValue())) {
//                removeKey(e.getKey());
//            }
//        }
//    }
    public synchronized void synchronizedPut(String _key,V _v) {
        put(_key, _v);
    }
    @Override
    public StringList getKeys() {
        StringList s_ = new StringList();
        for (EntryCust<String, V> e: getList()) {
            s_.add(e.getKey());
        }
        return s_;
    }
    @Override
    public StringList getKeysNullValue() {
        StringList list_ = new StringList();
        for (EntryCust<String, V> e: getList()) {
            if (e.getValue() != null) {
                continue;
            }
            list_.add(e.getKey());
        }
        return list_;
    }

    @Override
    public void putAllMap(ListableEntries<? extends String, ? extends V> _m) {
        //setModified();
        for (EntryCust<? extends String,? extends V> e: _m.entryList()) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override
    int indexOfEntry(String _key) {
        int index_ = CustList.FIRST_INDEX;
        if (_key == null) {
            for (EntryCust<String, V> e:getList()) {
                if (e.getKey() == null) {
                    return index_;
                }
                index_++;
            }
            return CustList.INDEX_NOT_FOUND_ELT;
        }
        for (EntryCust<String, V> e:getList()) {
            String k_ = e.getKey();
            if (k_ == null) {
                continue;
            }
            if (StringList.quickEq(_key, k_)) {
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
//
//    @Override
//    public String toString() {
//        return list.toString();
//    }
}
