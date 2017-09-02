package code.util;
import code.util.annot.CapacityInit;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;




public final class BooleanMap<V> extends AbsMap<Boolean,V> {

    //list cannot be null, even by reflection
//    private final transient CustList<EntryCust<Boolean,V>> list = new CustList<EntryCust<Boolean,V>>();

    public BooleanMap() {
    }

    public BooleanMap(ListableEntries<Boolean, V> _arg0) {
        putAllMap(_arg0);
    }

    @CapacityInit
    public BooleanMap(CollCapacity _capacity) {
        super(_capacity);
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
    public void putAllMap(ListableEntries<Boolean, V> _m) {
        //setModified();
        for (EntryCust<Boolean,V> e: _m.entryList()) {
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
