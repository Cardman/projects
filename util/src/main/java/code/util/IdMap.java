package code.util;
import code.util.annot.CapacityInit;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;



public final class IdMap<K,V> extends AbsMap<K,V> {

//    //list cannot be null, even by reflection
//    private final transient CustList<EntryCust<K,V>> list = new CustList<EntryCust<K,V>>();

    public IdMap() {
    }

    public IdMap(ListableEntries<? extends K, ? extends V> _arg0) {
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
//    public IdMap<V,CustList<K>> reverseMap() {
//        IdMap<V,CustList<K>> reverseMap_ = new IdMap<V,CustList<K>>();
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

//    @Override
//    public V get(Object _key) {
//        for (EntryCust<K, V> e:entryList()) {
//            if (e.getKey() == _key) {
//                return e.getValue();
//            }
//        }
//        return null;
//        EntryCust<K,V> e_ = getEntryByKey(_key);
//        if (e_ == null) {
//            return null;
//        }
//        return e_.getValue();
//    }

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
//    @Override
//    public void put(K _key, V _v) {
////        int i_ = CustList.FIRST_INDEX;
////        for (EntryCust<K, V> e: list) {
////            if (e.getKey() == _key) {
//////                V old_ = list.get(i_).getValue();
////                list.get(i_).setValue(_v);
////                return;
////            }
////            i_++;
////        }
////        list.add(new EntryCust<K, V>(_key, _v));
//        int index_ = indexOfEntry(_key);
//        if (index_ == CustList.INDEX_NOT_FOUND_ELT) {
//            list.add(new EntryCust<K, V>(_key, _v));
//            return;
//        }
//        EntryCust<K, V> e_ = list.get(index_);
////        V old_ = e_.getValue();
//        e_.setValue(_v);
//    }
//
//    @Override
//    public void removeKey(K _key) {
//        //setModified();
////        for (EntryCust<K, V> e:entryList()) {
////            K key_ = e.getKey();
////            if (_key == key_) {
////                remove(key_);
////                break;
////            }
////        }
//        geneRemove(_key);
//    }

//    public V removedKey(K _key) {
//        //setModified();
//        for (EntryCust<K, V> e:entryList()) {
//            K key_ = e.getKey();
//            if (_key == key_) {
//                return remove(key_);
//            }
//        }
//        return null;
//    }

    @Override
    public IdList<K> getKeys() {
        IdList<K> s_ = new IdList<K>();
        for (EntryCust<K, V> e: getList()) {
            s_.add(e.getKey());
        }
        return s_;
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
