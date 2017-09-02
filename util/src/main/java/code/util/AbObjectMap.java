package code.util;
import code.util.ints.Equallable;
import code.util.ints.ListableEntries;

public abstract class AbObjectMap<K extends Equallable<K>, V> extends AbsMap<K, V> {

//    //list cannot be null, even by reflection
//    private final transient CustList<EntryCust<K, V>> list = new CustList<EntryCust<K, V>>();

    public AbObjectMap() {
    }

    public AbObjectMap(ListableEntries<K, V> _arg0) {
        putAllMap(_arg0);
    }

    protected AbObjectMap(CollCapacity _capacity) {
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
    public void putAllMap(ListableEntries<K, V> _m) {
        //setModified();
        for (EntryCust<K,V> e: _m.entryList()) {
            put(e.getKey(), e.getValue());
        }
    }

}
