package code.util;
import code.util.annot.CapacityInit;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;




public final class EnumMap<K extends Enum<K>, V> extends AbsMap<K,V> {

//    //list cannot be null, even by reflection
//    private final transient CustList<EntryCust<K,V>> list = new CustList<EntryCust<K,V>>();

    public EnumMap() {
    }

    public EnumMap(ListableEntries<? extends K, ? extends V> _arg0) {
        putAllMap(_arg0);
    }
    @CapacityInit
    public EnumMap(int _capacity) {
        super(_capacity);
    }
//    @Override
//    CustList<EntryCust<K,V>> getList() {
//        return list;
//    }

//    public static <T extends Enum<T>> void deleteLineReturn(EnumMap<T,String> _map) {
//        for (EntryCust<T, String> e: _map.list) {
//            e.setValue(StringList.removeStrings(e.getValue(), Constants.RETURN_LINE));
//        }
//    }

//    public EnumMap<V,CustList<K>> reverseMap() {
//        EnumMap<V,CustList<K>> reverseMap_ = new EnumMap<V,CustList<K>>();
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
//    public CustList<EntryCust<K,V>> entryList() {
//        return list;
//    }

    public synchronized V synchronizedGet(K _key) {
        return getVal(_key);
    }

    @Override
    public Listable<V> getValues(K _key) {
        Listable<V> values_ = new CustList<V>();
        if (_key == null) {
            for (EntryCust<K, V> e:getList()) {
                if (e.getKey() == null) {
                    values_.add(e.getValue());
                }
            }
            return values_;
        }
        for (EntryCust<K, V> e:getList()) {
            if (_key == e.getKey()) {
                values_.add(e.getValue());
            }
        }
        return values_;
    }

    @Override
    public Listable<V> values() {
        Listable<V> s_ = new CustList<V>();
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
//    public void retainValues(CustList<V> _values) {
//        for (EntryCust<K,V> e: list) {
//            if (!_values.containsObj(e.getValue())) {
//                removeKey(e.getKey());
//            }
//        }
//    }
    public synchronized void synchronizedPut(K _key,V _v) {
        put(_key, _v);
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
    public Listable<K> getKeysNullValue() {
        Listable<K> list_ = new CustList<K>();
        for (EntryCust<K, V> e: getList()) {
            if (e.getValue() != null) {
                continue;
            }
            list_.add(e.getKey());
        }
        return list_;
    }

    @Override
    public void putAllMap(ListableEntries<? extends K, ? extends V> _m) {
        //setModified();
        for (EntryCust<? extends K,? extends V> e: _m.entryList()) {
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
//
//    @Override
//    public String toString() {
//        return list.toString();
//    }
}
