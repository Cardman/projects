package code.util;
import code.util.annot.CapacityInit;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;




public final class BooleanMap<V> extends AbsMap<Boolean,V> {

    //list cannot be null, even by reflection
//    private final transient CustList<EntryCust<Boolean,V>> list = new CustList<EntryCust<Boolean,V>>();

    public BooleanMap() {
    }

    public BooleanMap(ListableEntries<? extends Boolean, ? extends V> _arg0) {
        putAllMap(_arg0);
    }

    @CapacityInit
    public BooleanMap(CollCapacity _capacity) {
        super(_capacity);
    }
//    @Override
//    CustList<EntryCust<Boolean,V>> getList() {
//        return list;
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
//    public CustList<EntryCust<Boolean,V>> entryList() {
//        return list;
//    }

    public synchronized V synchronizedGet(Boolean _key) {
        return getVal(_key);
    }

    @Override
    public Listable<V> getValues(Boolean _key) {
        Listable<V> values_ = new CustList<V>();
        for (EntryCust<Boolean, V> e:getList()) {
            if (_key == e.getKey()) {
                values_.add(e.getValue());
            }
        }
        return values_;
    }

    @Override
    public Listable<V> values() {
        Listable<V> s_ = new CustList<V>();
        for (EntryCust<Boolean, V> e: getList()) {
            s_.add(e.getValue());
        }
//        return new CustList<>(super.values());
        return s_;
    }

    public void retainKeys(BooleanList _keys) {
        for (Boolean k: getKeys()) {
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
    public synchronized void synchronizedPut(Boolean _key,V _v) {
        put(_key, _v);
    }

    @Override
    public BooleanList getKeys() {
        BooleanList s_ = new BooleanList();
        for (EntryCust<Boolean, V> e: getList()) {
            s_.add(e.getKey());
        }
        return s_;
    }
    @Override
    public BooleanList getKeysNullValue() {
        BooleanList list_ = new BooleanList();
        for (EntryCust<Boolean, V> e: getList()) {
            if (e.getValue() != null) {
                continue;
            }
            list_.add(e.getKey());
        }
        return list_;
    }

    @Override
    public void putAllMap(ListableEntries<? extends Boolean, ? extends V> _m) {
        //setModified();
        for (EntryCust<? extends Boolean,? extends V> e: _m.entryList()) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override
    int indexOfEntry(Boolean _key) {
        int index_ = CustList.FIRST_INDEX;
        for (EntryCust<Boolean, V> e:getList()) {
            if (e.getKey() == _key) {
                return index_;
            }
            index_++;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }

}
