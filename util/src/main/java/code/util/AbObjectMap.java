package code.util;
import code.util.ints.Equallable;
import code.util.ints.ListableEntries;

public abstract class AbObjectMap<K extends Equallable<K>, V> extends AbsMap<K, V> {

//    //list cannot be null, even by reflection
//    private final transient CustList<EntryCust<K, V>> list = new CustList<EntryCust<K, V>>();

    public AbObjectMap() {
    }

    public AbObjectMap(ListableEntries<? extends K, ? extends V> _arg0) {
        putAllMap(_arg0);
    }

    protected AbObjectMap(int _capacity) {
        super(_capacity);
    }
//    @Override
//    CustList<EntryCust<K, V>> getList() {
//        return list;
//    }

//    public Map<V,List<K>> reverseMap() {
//        Map<V,List<K>> reverseMap_ = new Map<V,List<K>>();
//        for (EntryCust<K, V> k:list) {
//            List<K> keys_ = reverseMap_.getVal(k.getValue());
//            if (keys_ == null) {
//                keys_ = new List<K>();
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
        for (EntryCust<K, V> e:getList()) {
            K k_ = e.getKey();
            if (k_ == null) {
                continue;
            }
            if (_key.eq(k_)) {
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
//        return new List<>(super.values());
        return s_;
    }

    public void retainKeys(EqList<K> _keys) {
        for (K k: getKeys()) {
            if (!_keys.containsObj(k)) {
                removeKey(k);
            }
        }
    }
//    public void retainValues(List<V> _values) {
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
    public EqList<K> getKeys() {
        EqList<K> s_ = new EqList<K>();
        for (EntryCust<K, V> e: getList()) {
            s_.add(e.getKey());
        }
        return s_;
    }

    @Override
    public EqList<K> getKeysNullValue() {
        EqList<K> list_ = new EqList<K>();
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

}
