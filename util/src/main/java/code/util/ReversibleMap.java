package code.util;
import code.util.annot.CapacityInit;
import code.util.ints.Equallable;
import code.util.ints.ListableEntries;


public final class ReversibleMap<K extends Equallable<K>, V extends Equallable<V>> extends AbObjectMap<K, V> {

    public ReversibleMap() {
    }

    public ReversibleMap(ListableEntries<? extends K, ? extends V> _map) {
        putAllMap(_map);
    }

    @CapacityInit
    public ReversibleMap(CollCapacity _capacity) {
        super(_capacity);
    }

    public K getKey(V _v) {
        if (_v == null) {
            for (EntryCust<K, V> e: getList()) {
                if (e.getValue() == _v) {
                    return e.getKey();
                }
            }
        } else {
            for (EntryCust<K, V> e: getList()) {
                V v_ = e.getValue();
                if (v_ == null) {
                    continue;
                }
                if (_v.eq(v_)) {
                    return e.getKey();
                }
            }
        }
        return null;
    }

    public ReversibleMap<V, K> reverse() {
        ReversibleMap<V, K> rev_ = new ReversibleMap<V, K>();
        for (EntryCust<K, V> e: getList()) {
            rev_.put(e.getValue(), e.getKey());
        }
        return rev_;
    }

    public ObjectMap<V, EqList<K>> reverseMap() {
        ObjectMap<V, EqList<K>> rev_ = new ObjectMap<V, EqList<K>>();
        for (EntryCust<K, V> k:getList()) {
            EqList<K> keys_ = rev_.getVal(k.getValue());
            if (keys_ == null) {
                keys_ = new EqList<K>();
                keys_.add(k.getKey());
                rev_.put(k.getValue(), keys_);
            } else {
                keys_.add(k.getKey());
            }
        }
        return rev_;
    }
//
//    public void putInMap(K _key, V _value) {
//        super.put(_key, _value);
//    }

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
//        if (!(_key instanceof Equallable<?>)) {
//            return CustList.INDEX_NOT_FOUND_ELT;
//        }
        Equallable<K> k_ = _key;
        for (EntryCust<K, V> e:getList()) {
            K key_ = e.getKey();
            if (key_ == null) {
                continue;
            }
            if (k_.eq(key_)) {
                return index_;
            }
            index_++;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }
}
