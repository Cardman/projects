package code.util;

import code.util.annot.CapacityInit;
import code.util.ints.ListableEntries;

public final class StringMapObject extends AbsMap<String,Object> {


    public StringMapObject() {
    }

    public StringMapObject(ListableEntries<String, Object> _arg0) {
        putAllMap(_arg0);
    }

    @CapacityInit
    public StringMapObject(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    public CustList<Object> getValues(String _key) {
        CustList<Object> values_ = new CustList<Object>();
        if (_key == null) {
            for (EntryCust<String, Object> e:getList()) {
                if (e.getKey() == null) {
                    values_.add(e.getValue());
                }
            }
            return values_;
        }
        for (EntryCust<String, Object> e:getList()) {
            String k_ = e.getKey();
            if (k_ == null) {
                continue;
            }
            if (StringList.quickEq(_key, k_)) {
                values_.add(e.getValue());
            }
        }
        return values_;
    }

    @Override
    public CustList<Object> values() {
        CustList<Object> s_ = new CustList<Object>();
        for (EntryCust<String, Object> e: getList()) {
            s_.add(e.getValue());
        }
        return s_;
    }
    public void retainKeys(StringList _keys) {
        for (String k: getKeys()) {
            if (!_keys.containsObj(k)) {
                removeKey(k);
            }
        }
    }

    @Override
    public StringList getKeys() {
        StringList s_ = new StringList();
        for (EntryCust<String, Object> e: getList()) {
            s_.add(e.getKey());
        }
        return s_;
    }
    @Override
    public StringList getKeysNullValue() {
        StringList list_ = new StringList();
        for (EntryCust<String, Object> e: getList()) {
            if (e.getValue() != null) {
                continue;
            }
            list_.add(e.getKey());
        }
        return list_;
    }

    @Override
    public void putAllMap(ListableEntries<String, Object> _m) {
        for (EntryCust<String,Object> e: _m.entryList()) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override
    int indexOfEntry(String _key) {
        int index_ = CustList.FIRST_INDEX;
        if (_key == null) {
            for (EntryCust<String, Object> e:getList()) {
                if (e.getKey() == null) {
                    return index_;
                }
                index_++;
            }
            return CustList.INDEX_NOT_FOUND_ELT;
        }
        for (EntryCust<String, Object> e:getList()) {
            String k_ = e.getKey();
            if (k_ == null) {
                continue;
            }
            if (StringList.quickEq(_key, k_)) {
                return index_;
            }
            index_++;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }
}
