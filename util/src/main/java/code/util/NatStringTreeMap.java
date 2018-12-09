package code.util;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;
import code.util.ints.SortableMap;

/**
    @author Cardman
*/
public final class NatStringTreeMap<V> extends AbsMap<String, V> implements SortableMap<String, V> {

    public NatStringTreeMap() {
    }
    public NatStringTreeMap(ListableEntries<String,V> _map) {
        putAllMap(_map);
    }

    public NatStringTreeMap(NatStringTreeMap<V> _map) {
        putAllTreeMap(_map);
    }
    
    public NatStringTreeMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    public void putAllMap(ListableEntries<String, V> _m) {
        for (EntryCust<String,V> e: _m.entryList()) {
            put(e.getKey(), e.getValue());
        }
    }

    public void putAllTreeMap(NatStringTreeMap<V> _m) {
        for (EntryCust<String,V> e: _m.getList()) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override
    public StringList getKeysNullValue() {
        StringList list_ = new StringList();
        for (EntryCust<String, V> e: getList()) {
            if (e.getValue() != null) {
                continue;
            }
            list_.add(e.getKey());
        }
        return list_;
    }
    @Override
    public CustList<V> getValues(String _key) {
        CustList<V> c_;
        c_ = new CustList<V>();
        for (EntryCust<String, V> e: getList()) {
            int res_ = _key.compareTo(e.getKey());
            if (res_ == CustList.EQ_CMP) {
                c_.add(e.getValue());
            }
        }
        return c_;
    }

    @Override
    public V getValue(int _index) {
        return getList().get(_index).getValue();
    }

    @Override
    public String getKey(int _index) {
        return getList().get(_index).getKey();
    }

    @Override
    public StringList getKeys() {
        StringList s_ = new StringList();
        for (EntryCust<String, V> e: getList()) {
            s_.add(e.getKey());
        }
        return s_;
    }

    @Override
    public void put(String _key, V _value) {
        int index_ = 0;
        while (true) {
            if (index_ >= getList().size()) {
                getList().add(new EntryCust<String, V>(_key, _value));
                return;
            }
            EntryCust<String, V> c_ = getList().get(index_);
            int res_ = _key.compareTo(c_.getKey());
            if (res_ < 0) {
                getList().add(index_, new EntryCust<String, V>(_key, _value));
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
    int indexOfEntry(String _key) {
        int index_ = CustList.FIRST_INDEX;
        for (EntryCust<String, V> e:getList()) {
            Comparable<String> c_ = _key;
            int res_ = c_.compareTo(e.getKey());
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
        for (EntryCust<String, V> e: getList()) {
            s_.add(e.getValue());
        }
        return s_;
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
    public String firstKey() {
        return getList().first().getKey();
    }

    @Override
    public String lastKey() {
        return getList().last().getKey();
    }

    @Override
    public EntryCust<String, V> lowerEntry(String _key) {
        CustList<EntryCust<String,V>> l_;
        l_ = new CustList<EntryCust<String,V>>();
        for (EntryCust<String, V> e: getList()) {
            int res_ = e.getKey().compareTo(_key);
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
    public String lowerKey(String _key) {
        return getKeyOrNull(lowerEntry(_key));
    }

    @Override
    public EntryCust<String, V> floorEntry(String _key) {
        CustList<EntryCust<String,V>> l_;
        l_ = new CustList<EntryCust<String,V>>();
        for (EntryCust<String, V> e: getList()) {
            int res_ = e.getKey().compareTo(_key);
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
    public String floorKey(String _key) {
        return getKeyOrNull(floorEntry(_key));
    }

    @Override
    public EntryCust<String, V> ceilingEntry(String _key) {
        for (EntryCust<String, V> e: getList()) {
            int res_ = e.getKey().compareTo(_key);
            if (res_ >= 0) {
                return e;
            }
        }
        return null;
    }

    @Override
    public String ceilingKey(String _key) {
        return getKeyOrNull(ceilingEntry(_key));
    }

    @Override
    public EntryCust<String, V> higherEntry(String _key) {
        for (EntryCust<String, V> e: getList()) {
            int res_ = e.getKey().compareTo(_key);
            if (res_ > 0) {
                return e;
            }
        }
        return null;
    }

    @Override
    public String higherKey(String _key) {
        return getKeyOrNull(higherEntry(_key));
    }

    @Override
    public EntryCust<String, V> firstEntry() {
        return getList().first();
    }

    @Override
    public EntryCust<String, V> lastEntry() {
        return getList().last();
    }

    public void applyChanges() {
        for (int i = CustList.FIRST_INDEX; i < getList().size(); i++) {
            for (int j = i + 1; j < getList().size(); j++) {
                Comparable<String> c_ = getList().get(i).getKey();
                int res_ = c_.compareTo(getList().get(j).getKey());
                if (res_ > CustList.EQ_CMP) {
                    getList().swapIndexes(i, j);
                }
            }
        }
    }
}
