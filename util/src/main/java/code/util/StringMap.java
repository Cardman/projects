package code.util;
import code.util.annot.CapacityInit;
import code.util.ints.ListableEntries;




public final class StringMap<V> extends AbsMap<String,V> {

//    //list cannot be null, even by reflection
//    private final transient CustList<EntryCust<String,V>> list = new CustList<EntryCust<String,V>>();

    public StringMap() {
    }

    public StringMap(ListableEntries<String, V> _arg0) {
        putAllMap(_arg0);
    }

    @CapacityInit
    public StringMap(CollCapacity _capacity) {
        super(_capacity);
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
    public void putAllMap(ListableEntries<String, V> _m) {
        //setModified();
        for (EntryCust<String,V> e: _m.entryList()) {
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
