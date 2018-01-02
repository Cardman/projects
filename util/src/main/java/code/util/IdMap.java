package code.util;
import code.util.annot.CapacityInit;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;



public final class IdMap<K,V> extends AbsMap<K,V> {

//    //list cannot be null, even by reflection
//    private final transient CustList<EntryCust<K,V>> list = new CustList<EntryCust<K,V>>();

    public IdMap() {
    }

    public IdMap(ListableEntries<K, V> _arg0) {
        putAllMap(_arg0);
    }

    @CapacityInit
    public IdMap(CollCapacity _capacity) {
        super(_capacity);
    }
//    @Override
//    CustList<EntryCust<K,V>> getList() {
//        return list;
//    }

//    public static <T> void deleteLineReturn(IdMap<T,String> _map) {
//        for (EntryCust<T, String> e: _map.list) {
//            e.setValue(StringList.removeStrings(e.getValue(), Constants.RETURN_LINE));
//        }
//    }

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
    public Listable<V> getValues(K _key) {
        return getValuesObj(_key);
    }

    public Listable<V> getValuesObj(Object _key) {
        Listable<V> values_ = new CustList<V>();
        for (EntryCust<K, V> e:getList()) {
            if (e.getKey() == _key) {
                values_.add(e.getValue());
            }
        }
        return values_;
    }

//    @Override
//    public V getVal(K _key) {
////        for (EntryCust<K, V> e:entryList()) {
////            if (e.getKey() == _key) {
////                return e.getValue();
////            }
////        }
////        return null;
//        EntryCust<K,V> e_ = getEntryByKey(_key);
//        if (e_ == null) {
//            return null;
//        }
//        return e_.getValue();
//    }
//
//    @Override
//    public boolean contains(K _key) {
////        for (K k: getKeys()) {
////            if (k == _key) {
////                return true;
////            }
////        }
////        return false;
//        return getEntryByKey(_key) != null;
//    }
//    public boolean has(V _value) {
////        return super.values().contains(_value);
////        return values().contains(_value);
//        return containsValue(_value);
//    }
    @Override
    public Listable<V> values() {
        Listable<V> s_ = new CustList<V>();
        for (EntryCust<K, V> e: getList()) {
            s_.add(e.getValue());
        }
//        return new CustList<>(super.values());
        return s_;
    }

    public void retainKeys(CustList<K> _keys) {
        for (K k: getKeys()) {
            boolean contained_ = false;
            for (K l: _keys) {
                if (l == k) {
                    contained_ = true;
                    break;
                }
            }
            if (!contained_) {
                removeKey(k);
            }
        }
    }

    @Override
    public IdList<K> getKeys() {
        IdList<K> s_ = new IdList<K>();
        for (EntryCust<K, V> e: getList()) {
            s_.add(e.getKey());
        }
        return s_;
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

    public V firstValue() {
        return getList().first().getValue();
    }
    public V lastValue() {
        return getList().last().getValue();
    }
    public K firstKey() {
        return getList().first().getKey();
    }

    public K lastKey() {
        return getList().last().getKey();
    }
}
