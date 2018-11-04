package code.util;
import code.util.annot.CapacityInit;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;



public final class InsCaseStringMap<V> extends AbsMap<String,V> {

//    //list cannot be null, even by reflection
//    private final CustList<EntryCust<String,V>> list = new CustList<EntryCust<String,V>>();

    public InsCaseStringMap() {
    }

    public InsCaseStringMap(ListableEntries<String, V> _arg0) {
        putAllMap(_arg0);
    }
    @CapacityInit
    public InsCaseStringMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    public Listable<V> values() {
        Listable<V> s_ = new CustList<V>();
        for (EntryCust<String, V> e: getList()) {
            s_.add(e.getValue());
        }
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
            if (_key.equalsIgnoreCase(e.getKey())) {
                return index_;
            }
            index_++;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }

//    @Override
//    public V getVal(String _key) {
//        if (_key == null) {
//            for (EntryCust<String, V> e:entryList()) {
//                if (e.getKey() == null) {
//                    return e.getValue();
//                }
//            }
//            return null;
//        }
//        for (EntryCust<String, V> e:entryList()) {
//            if (_key.equalsIgnoreCase(e.getKey())) {
//                return e.getValue();
//            }
//        }
//        return null;
//    }

    public StringList getKeysSameLetters(String _key) {
        StringList list_ = new StringList();
        if (_key == null) {
            for (EntryCust<String, V> e:getList()) {
                if (e.getKey() == null) {
                    list_.add(e.getKey());
                }
            }
            return list_;
        }
        for (EntryCust<String, V> e:getList()) {
            if (_key.equalsIgnoreCase(e.getKey())) {
                list_.add(e.getKey());
            }
        }
        return list_;
    }

    @Override
    public Listable<V> getValues(String _key) {
        Listable<V> list_ = new CustList<V>();
        if (_key == null) {
            for (EntryCust<String, V> e:getList()) {
                if (e.getKey() == null) {
                    list_.add(e.getValue());
                }
            }
            return list_;
        }
        for (EntryCust<String, V> e:getList()) {
            if (_key.equalsIgnoreCase(e.getKey())) {
                list_.add(e.getValue());
            }
        }
        return list_;
    }

//    @Override
//    public void put(String _key, V _v) {
//        if (_key == null) {
//            super.put(null, _v);
//        }
//        for (EntryCust<String, V> e:entryList()) {
//            String key_ = e.getKey();
//            if (_key.equalsIgnoreCase(key_)) {
////                V old_ = e.getValue();
//                e.setValue(_v);
//                return;
//            }
//        }
//        super.put(_key, _v);
//    }
}
