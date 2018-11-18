package code.util;
import code.util.ints.Comparing;

import code.util.annot.CapacityInit;
import code.util.comparators.NatComparator;
import code.util.ints.Cmp;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;
import code.util.ints.SortableMap;

/**
    @author Cardman
*/
public final class NatCmpTreeMap<K extends Cmp<K>, V> extends AbsMap<K, V> implements SortableMap<K, V> {

    public NatCmpTreeMap() {
    }

    public NatCmpTreeMap(ListableEntries<K,V> _map) {
        putAllMap(_map);
    }
    @CapacityInit
    public NatCmpTreeMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    public void putAllMap(ListableEntries<K, V> _m) {
        for (EntryCust<K,V> e: _m.entryList()) {
            put(e.getKey(), e.getValue());
        }
    }

    public void putAllTreeMap(NatCmpTreeMap<K, V> _m) {
        for (EntryCust<K,V> e: _m.getList()) {
            put(e.getKey(), e.getValue());
        }
    }

//    @Override
//    public boolean contains(K _key) {
//        return getEntryByKey(_key) != null;
//    }

//    public boolean has(V _value) {
//        return containsValue(_value);
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
    public CustList<V> getValues(K _key) {
        CustList<V> c_;
        c_ = new CustList<V>();
        for (EntryCust<K, V> e: getList()) {
            int res_ = _key.cmp(e.getKey());
            if (res_ == CustList.EQ_CMP) {
                c_.add(e.getValue());
            }
        }
        return c_;
    }
//    @Override
//    public V getVal(K _key) {
//        EntryCust<K,V> e_ = getEntryByKey(_key);
//        if (e_ == null) {
//            return null;
//        }
//        return e_.getValue();
//    }
//
//    @Override
//    public void removeKey(K _key) {
//        int index_ = indexOfEntry(_key);
//        if (index_ == CustList.INDEX_NOT_FOUND_ELT) {
//            return;
//        }
//        list.removeAt(index_);
//    }

//    public V removedKey(K _key) {
//        return remove(_key);
//    }

    @Override
    public V getValue(int _index) {
        return getList().get(_index).getValue();
    }

    @Override
    public K getKey(int _index) {
        return getList().get(_index).getKey();
    }

    @Override
    public Listable<K> getKeys() {
        Listable<K> s_ = new CustList<K>();
        for (EntryCust<K, V> e: getList()) {
            s_.add(e.getKey());
        }
        return s_;
    }

//    @Override
//    public CustList<EntryCust<K,V>> entryList() {
//        return getList();
//    }

    @Override
    public void put(K _key, V _value) {
        int index_ = 0;
        while (true) {
            if (index_ >= getList().size()) {
                getList().add(new EntryCust<K, V>(_key, _value));
                return;
            }
            EntryCust<K, V> c_ = getList().get(index_);
            int res_ = _key.cmp(c_.getKey());
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
            Cmp<K> c_ = _key;
            int res_ = c_.cmp(e.getKey());
            if (res_ == CustList.EQ_CMP) {
                return index_;
            }
            index_++;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }

    @Override
    public Listable<V> values() {
        Listable<V> s_ = new CustList<V>();
        for (EntryCust<K, V> e: getList()) {
            s_.add(e.getValue());
        }
        return s_;
    }

    public Comparing<K> comparator() {
        return new NatComparator<K>();
    }

    @Override
    public K firstKey() {
        return getList().first().getKey();
    }

    @Override
    public K lastKey() {
        return getList().last().getKey();
    }

    @Override
    public V firstValue() {
        return getList().first().getValue();
    }

    @Override
    public V lastValue() {
        return getList().last().getValue();
    }

    @Override
    public EntryCust<K, V> lowerEntry(K _key) {
        CustList<EntryCust<K,V>> l_;
        l_ = new CustList<EntryCust<K,V>>();
        for (EntryCust<K, V> e: getList()) {
            int res_ = e.getKey().cmp(_key);
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
            int res_ = e.getKey().cmp(_key);
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
            int res_ = e.getKey().cmp(_key);
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
            int res_ = e.getKey().cmp(_key);
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

//    (non-Javadoc)
//        @see flux.utils.Viewable#setModified()
//
//    @Override
//    public void setModified() {
//        modified = true;
//    }
//
//    (non-Javadoc)
//        @see flux.utils.Viewable#setUnmodified()
//
//    @Override
//    public void setUnmodified() {
//        modified = false;
//    }
//
//    (non-Javadoc)
//        @see flux.utils.Viewable#isModified()
//
//    @Override
//    public boolean isModified() {
//        return modified;
//    }

    public void applyChanges() {
        for (int i = CustList.FIRST_INDEX; i < getList().size(); i++) {
            for (int j = i + 1; j < getList().size(); j++) {
                Cmp<K> c_ = getList().get(i).getKey();
                int res_ = c_.cmp(getList().get(j).getKey());
                if (res_ > CustList.EQ_CMP) {
                    getList().swapIndexes(i, j);
                }
            }
        }
    }
}
