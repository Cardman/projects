package code.util;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;
import code.util.ints.SortableMap;

/**
    @author Cardman
*/
public final class NatTreeMap<K extends Number, V> extends AbsMap<K, V> implements SortableMap<K, V> {

    public NatTreeMap() {
    }
    public NatTreeMap(ListableEntries<K,V> _map) {
        putAllMap(_map);
    }

    public NatTreeMap(NatTreeMap<K,V> _map) {
        putAllTreeMap(_map);
    }
    
    public NatTreeMap(CollCapacity _capacity) {
        super(_capacity);
    }

    public void putAllTreeMap(NatTreeMap<K, V> _m) {
        for (EntryCust<K,V> e: _m.getList()) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override
    public CustList<K> getKeys() {
        CustList<K> s_ = new CustList<K>();
        for (EntryCust<K, V> e: getList()) {
            s_.add(e.getKey());
        }
        return s_;
    }

    @Override
    public void put(K _key, V _value) {
        int index_ = 0;
        while (true) {
            if (index_ >= getList().size()) {
                getList().add(new EntryCust<K, V>(_key, _value));
                return;
            }
            EntryCust<K, V> c_ = getList().get(index_);
            int res_ = Numbers.compare(_key,c_.getKey());
            if (res_ < 0) {
                getList().add(index_, new EntryCust<K, V>(_key, _value));
                return;
            }
            if (res_ == 0) {
                setValue(index_, _value);
                return;
            }
            index_++;
        }
    }

    @Override
    int indexOfEntry(K _key) {
        int index_ = CustList.FIRST_INDEX;
        for (EntryCust<K, V> e:getList()) {
            Number c_ = _key;
            int res_ = Numbers.compare(c_,e.getKey());
            if (res_ == CustList.EQ_CMP) {
                return index_;
            }
            index_++;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }

    @Override
    public EntryCust<K, V> lowerEntry(K _key) {
        CustList<EntryCust<K,V>> l_;
        l_ = new CustList<EntryCust<K,V>>();
        for (EntryCust<K, V> e: getList()) {
            int res_ = Numbers.compare(e.getKey(),_key);
            if (res_ >= 0) {
                continue;
            }
            l_.add(e);
        }
        if (l_.isEmpty()) {
            return null;
        }
        return l_.last();
    }

    @Override
    public K lowerKey(K _key) {
        return getKeyOrNull(lowerEntry(_key));
    }

    @Override
    public EntryCust<K, V> floorEntry(K _key) {
        CustList<EntryCust<K,V>> l_;
        l_ = new CustList<EntryCust<K,V>>();
        for (EntryCust<K, V> e: getList()) {
            int res_ = Numbers.compare(e.getKey(),_key);
            if (res_ > 0) {
                continue;
            }
            l_.add(e);
        }
        if (l_.isEmpty()) {
            return null;
        }
        return l_.last();
    }

    @Override
    public K floorKey(K _key) {
        return getKeyOrNull(floorEntry(_key));
    }

    @Override
    public EntryCust<K, V> ceilingEntry(K _key) {
        for (EntryCust<K, V> e: getList()) {
            int res_ = Numbers.compare(e.getKey(),_key);
            if (res_ >= 0) {
                return e;
            }
        }
        return null;
    }

    @Override
    public K ceilingKey(K _key) {
        return getKeyOrNull(ceilingEntry(_key));
    }

    @Override
    public EntryCust<K, V> higherEntry(K _key) {
        for (EntryCust<K, V> e: getList()) {
            int res_ = Numbers.compare(e.getKey(),_key);
            if (res_ > 0) {
                return e;
            }
        }
        return null;
    }

    @Override
    public K higherKey(K _key) {
        return getKeyOrNull(higherEntry(_key));
    }

    @Override
    public EntryCust<K, V> firstEntry() {
        return getList().first();
    }

    @Override
    public EntryCust<K, V> lastEntry() {
        return getList().last();
    }

}
