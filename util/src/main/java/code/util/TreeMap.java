package code.util;
import java.util.Comparator;

import code.util.annot.CapacityInit;
import code.util.annot.NullableField;
import code.util.annot.RwXml;
import code.util.exceptions.NullComparatorException;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;
import code.util.ints.SortableMap;

/**
    @author Cardman
*/

public final class TreeMap<K, V> extends AbsMap<K, V> implements SortableMap<K, V> {

//    //list cannot be null, even by reflection
//    private final transient CustList<EntryCust<K,V>> list = new CustList<EntryCust<K,V>>();

    @NullableField
    @RwXml
    private final Comparator<K> comparator;

    //private transient boolean modified;

    @RwXml
    TreeMap() {
        comparator = null;
    }

    @CapacityInit
    TreeMap(CollCapacity _capacity) {
        super(_capacity);
        comparator = null;
    }

    public TreeMap(CollCapacity _capacity, Comparator<K> _cmp) {
        super(_capacity);
        comparator = _cmp;
        if (_cmp == null) {
            throw new NullComparatorException();
        }
    }

    public TreeMap(Comparator<K> _cmp) {
        comparator = _cmp;
        if (_cmp == null) {
            throw new NullComparatorException();
        }
    }

    public TreeMap(SortableMap<K, V> _sorted) {
        comparator = _sorted.comparator();
        putAllMap(_sorted);
    }

//    public TreeMap(ListableEntries<K,V> _map) {
//        comparator = null;
//        putAllMap(_map);
//    }

//    public TreeMap(ListableEntries<K,V> _map, Comparator<K> _cmp) {
//        comparator = _cmp;
//        putAllMap(_map);
//    }

    @Override
    public boolean isCorrect() {
        return comparator != null && super.isCorrect();
    }

//    @Override
//    CustList<EntryCust<K,V>> getList() {
//        return list;
//    }

//    public TreeMap(SortedMap<K,V> _map) {
//        putAll(_map);
//    }

//    @Override
//    public void move(K _oldKey, K _newKey) {
//        int size_ = size();
//        V value_ = getVal(_oldKey);
//        removeKey(_oldKey);
//        if (size_ != size()) {
//            put(_newKey, value_);
//        }
//    }

    @Override
    public void putAllMap(ListableEntries<? extends K, ? extends V> _m) {
        //setModified();
        for (EntryCust<? extends K,? extends V> e: _m.entryList()) {
            put(e.getKey(), e.getValue());
        }
    }
    
    public void putAllTreeMap(SortableMap<? extends K, ? extends V> _m) {
        //setModified();
        for (EntryCust<? extends K,? extends V> e: _m.entryList()) {
            put(e.getKey(), e.getValue());
        }
    }
    
    public void putAllNatTreeMap(NatTreeMap<? extends K, ? extends V> _m) {
        //setModified();
        for (EntryCust<? extends K,? extends V> e: _m.getList()) {
            put(e.getKey(), e.getValue());
        }
    }

    public void putAllCmpTreeMap(NatCmpTreeMap<? extends K, ? extends V> _m) {
        //setModified();
        for (EntryCust<? extends K,? extends V> e: _m.getList()) {
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
//    public static <K,V extends Number> boolean hasNumber(TreeMap<K,V> _map, V _value) {
//        if (_value == null) {
//            for (Number s: _map.values()) {
//                if (s == null) {
//                    return true;
//                }
//            }
//            return false;
//        }
//        for (Number s: _map.values()) {
//            if (_value.longValue() == s.longValue()) {
//                return true;
//            }
//        }
//        return false;
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
//    @Override
//    public V getVal(K _key) {
//        EntryCust<K,V> e_ = getEntryByKey(_key);
//        if (e_ == null) {
//            return null;
//        }
//        return e_.getValue();
//    }
    @Override
    public CustList<V> getValues(K _key) {
        CustList<V> c_;
        c_ = new CustList<V>();
//        if (comparator == null) {
//            for (EntryCust<K, V> e: getList()) {
//                Comparable<K> k_ = (Comparable<K>) _key;
//                int res_ = k_.compareTo(e.getKey());
//                if (res_ == CustList.EQ_CMP) {
//                    c_.add(e.getValue());
//                }
//            }
//        } else {
//            for (EntryCust<K, V> e: getList()) {
//                int res_ = comparator.compare(_key, e.getKey());
//                if (res_ == CustList.EQ_CMP) {
//                    c_.add(e.getValue());
//                }
//            }
//        }
        for (EntryCust<K, V> e: getList()) {
            int res_ = comparator.compare(_key, e.getKey());
            if (res_ == CustList.EQ_CMP) {
                c_.add(e.getValue());
            }
        }
        return c_;
    }
//    @Override
//    public void removeKey(K _key) {
//        int index_ = indexOfEntry(_key);
//        if (index_ == CustList.INDEX_NOT_FOUND_ELT) {
//            return;
//        }
//        list.removeAt(index_);
//    }

//    public V removedKey(K _key) {
//        V val_ = getVal(_key);
//        remove(_key);
//        return val_;
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
        Listable<K> l_ = new CustList<K>();
        for (EntryCust<K, V> e: getList()) {
            l_.add(e.getKey());
        }
        return l_;
    }
//
//    @Override
//    public CustList<EntryCust<K,V>> entryList() {
//        return list;
//    }

    @Override
    public void put(K _key, V _value) {
//        if (comparator == null) {
//            int index_ = 0;
//            while (true) {
//                if (index_ >= getList().size()) {
//                    getList().add(new EntryCust<K, V>(_key, _value));
//                    return;
//                }
//                EntryCust<K, V> e_ = getList().get(index_);
//                Comparable<K> c_ = (Comparable<K>) _key;
//                int res_ = c_.compareTo(e_.getKey());
//                if (res_ < 0) {
//                    getList().add(index_, new EntryCust<K, V>(_key, _value));
//                    return;
//                }
//                if (res_ == 0) {
////                    V v_ = list.get(index_).getValue();
//                    getList().get(index_).setValue(_value);
//                    return;
//                }
//                index_++;
//            }
//        }
        int index_ = 0;
        while (true) {
            if (index_ >= getList().size()) {
                getList().add(new EntryCust<K, V>(_key, _value));
                return;
            }
            EntryCust<K, V> c_ = getList().get(index_);
            int res_ = comparator.compare(_key, c_.getKey());
            if (res_ < 0) {
                getList().add(index_, new EntryCust<K, V>(_key, _value));
                return;
            }
            if (res_ == 0) {
//                V v_ = list.get(index_).getValue();
                getList().get(index_).setValue(_value);
                return;
            }
            index_++;
        }
    }

//    @Override
//    public void remove(Object _key) {
//        int index_ = indexOfEntry(_key);
//        if (index_ == CustList.INDEX_NOT_FOUND_ELT) {
//            return;
//        }
//        list.removeAt(index_);
//        if (comparator == null) {
//            int index_ = 0;
//            for (EntryCust<K, V> e:entryList()) {
//                Comparable<K> c_ = (Comparable<K>) _key;
//                int res_ = c_.compareTo(e.getKey());
//                if (res_ == CustList.EQ_CMP) {
//                    list.removeAt(index_);
//                    return;
//                }
//                index_++;
//            }
//            return;
//        }
//        int index_ = 0;
//        K key_ = (K) _key;
//        for (EntryCust<K, V> e:entryList()) {
//            int res_ = comparator.compare(key_, e.getKey());
//            if (res_ == CustList.EQ_CMP) {
//                list.removeAt(index_);
//                return;
//            }
//            index_++;
//        }
//    }

//    private EntryCust<K,V> getEntryByKey(K _key) {
//        int index_ = indexOfEntry(_key);
//        if (index_ == CustList.INDEX_NOT_FOUND_ELT) {
//            return null;
//        }
//        return list.get(index_);
//    }

    @Override
    int indexOfEntry(K _key) {
        int index_ = CustList.FIRST_INDEX;
//        if (comparator == null) {
//            for (EntryCust<K, V> e:getList()) {
//                Comparable<K> c_ = (Comparable<K>) _key;
//                int res_ = c_.compareTo(e.getKey());
//                if (res_ == CustList.EQ_CMP) {
//                    return index_;
//                }
////                if (res_ < CustList.EQ_CMP) {
////                    return CustList.INDEX_NOT_FOUND_ELT;
////                }
//                index_++;
//            }
//            return CustList.INDEX_NOT_FOUND_ELT;
//        }
        K key_ = _key;
        for (EntryCust<K, V> e:getList()) {
            int res_ = comparator.compare(key_, e.getKey());
            if (res_ == CustList.EQ_CMP) {
                return index_;
            }
//            if (res_ < CustList.EQ_CMP) {
//                return CustList.INDEX_NOT_FOUND_ELT;
//            }
            index_++;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }

//    @Override
//    public void clear() {
//        list.clear();
//    }

    @Override
    public Listable<V> values() {
        Listable<V> s_ = new CustList<V>();
        for (EntryCust<K, V> e: getList()) {
            s_.add(e.getValue());
        }
        return s_;
    }

    @Override
    public Comparator<K> comparator() {
        return comparator;
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
    public EntryCust<K, V> lowerEntry(K _key) {
        CustList<EntryCust<K,V>> l_;
        l_ = new CustList<EntryCust<K,V>>();
//        if (comparator == null) {
//            for (EntryCust<K, V> e: getList()) {
//                Comparable<K> c_ = (Comparable<K>) e.getKey();
//                int res_ = c_.compareTo(_key);
//                if (res_ >= 0) {
//                    continue;
//                }
//                l_.add(e);
//            }
//            if (l_.isEmpty()) {
//                return null;
//            }
//            return l_.last();
//        }
        for (EntryCust<K, V> e: getList()) {
            int res_ = comparator.compare(e.getKey(), _key);
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
//        if (comparator == null) {
//            for (EntryCust<K, V> e: getList()) {
//                Comparable<K> c_ = (Comparable<K>) e.getKey();
//                int res_ = c_.compareTo(_key);
//                if (res_ > 0) {
//                    continue;
//                }
//                l_.add(e);
//            }
//            if (l_.isEmpty()) {
//                return null;
//            }
//            return l_.last();
//        }
        for (EntryCust<K, V> e: getList()) {
            int res_ = comparator.compare(e.getKey(), _key);
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
//        if (comparator == null) {
//            for (EntryCust<K, V> e: getList()) {
//                Comparable<K> c_ = (Comparable<K>) e.getKey();
//                int res_ = c_.compareTo(_key);
//                if (res_ >= 0) {
//                    return e;
//                }
//            }
//            return null;
//        }
        for (EntryCust<K, V> e: getList()) {
            int res_ = comparator.compare(e.getKey(), _key);
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
//        if (comparator == null) {
//            for (EntryCust<K, V> e: getList()) {
//                Comparable<K> c_ = (Comparable<K>) e.getKey();
//                int res_ = c_.compareTo(_key);
//                if (res_ > 0) {
//                    return e;
//                }
//            }
//            return null;
//        }
        for (EntryCust<K, V> e: getList()) {
            int res_ = comparator.compare(e.getKey(), _key);
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

//    @Override
//    public String toString() {
//        return list.toString();
//    }

    @Override
    public void applyChanges() {
//        if (comparator == null) {
//            for (int i = CustList.FIRST_INDEX; i < getList().size(); i++) {
//                for (int j = i + 1; j < getList().size(); j++) {
//                    Comparable<K> c_ = (Comparable<K>) getList().get(i).getKey();
//                    int res_ = c_.compareTo(getList().get(j).getKey());
//                    if (res_ > CustList.EQ_CMP) {
//                        getList().swapIndexes(i, j);
//                    }
//                }
//            }
//        } else {
//            for (int i = CustList.FIRST_INDEX; i < getList().size(); i++) {
//                for (int j = i + 1; j < getList().size(); j++) {
//                    int res_ = comparator.compare(getList().get(i).getKey(), getList().get(j).getKey());
//                    if (res_ > CustList.EQ_CMP) {
//                        getList().swapIndexes(i, j);
//                    }
//                }
//            }
//        }
        for (int i = CustList.FIRST_INDEX; i < getList().size(); i++) {
            for (int j = i + 1; j < getList().size(); j++) {
                int res_ = comparator.compare(getList().get(i).getKey(), getList().get(j).getKey());
                if (res_ > CustList.EQ_CMP) {
                    getList().swapIndexes(i, j);
                }
            }
        }
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
}
