package code.datacheck.classes;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;
import code.util.ints.SimpleIterable;

public class MyMap<V> implements ListableEntries<String, V> {

    private Snapshot<String> keys = new Snapshot<String>();

    private UnmodifiedList<V> values = new UnmodifiedList<V>();

    private transient Snapshot<ECust<String, V>> entries = new Snapshot<ECust<String, V>>();

    public MyMap() {
    }

    @Override
    public void move(String _oldKey, String _newKey) {
        int size_ = size();
        V value_ = getVal(_oldKey);
        removeKey(_oldKey);
        if (size_ != size()) {
            put(_newKey, value_);
        }
    }

    @Override
    public int size() {
        return keys.size();
    }

    @Override
    public boolean isEmpty() {
        return keys.isEmpty();
    }

    @Override
    public boolean contains(String _key) {
//        return keys.contains(_key);
        return keys.indexOfObj(_key) == CustList.INDEX_NOT_FOUND_ELT;
    }
//
//    @Override
//    public boolean containsKey(Object _key) {
//        return keys.contains(_key);
//    }

//    public boolean has(V _value) {
//        return containsValue(_value);
//    }
//
//    @Override
//    public boolean containsValue(Object _value) {
//        return values.contains(_value);
//    }

    @Override
    public Listable<V> getValues(String _key) {
        Listable<V> list_ = new CustList<V>();
        if (_key == null) {
            int len_ = keys.size();
            for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                if (_key == keys.get(i)) {
                    list_.add(values.get(i));
                }
            }
            return list_;
        }
        int len_ = keys.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (StringList.quickEq(_key, keys.get(i))) {
                list_.add(values.get(i));
            }
        }
        return list_;
    }

    @Override
    public V getVal(String _key) {
        if (_key == null) {
            int len_ = keys.size();
            for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                if (_key == keys.get(i)) {
                    return values.get(i);
                }
            }
            return null;
        }
        int len_ = keys.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (StringList.quickEq(_key, keys.get(i))) {
                return values.get(i);
            }
        }
        return null;
    }
    @Override
    public Listable<String> getKeysNullValue() {
        Listable<String> list_ = new CustList<String>();
        for (ECust<String, V> e: entries) {
            if (e.getValue() != null) {
                continue;
            }
            list_.add(e.getKey());
        }
        return list_;
    }

    @Override
    public void put(String _key, V _value) {
        if (_key == null) {
            int len_ = keys.size();
            for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                if (_key == keys.get(i)) {
//                    V oldValue_ = values.get(i);
                    values.set(i, _value);
                    entries.get(i).setValue(_value);
                    return;
                }
            }
            addKeyValue(_key, _value);
            return;
        }
        int len_ = keys.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String k_ = keys.get(i);
            if (k_ == null) {
                continue;
            }
            if (StringList.quickEq(_key, k_)) {
//                V oldValue_ = values.get(i);
                values.set(i, _value);
                entries.get(i).setValue(_value);
                return;
            }
        }
        addKeyValue(_key, _value);
    }

    void addKeyValue(String _key, V _value) {
        keys.setAddRemove(true);
        keys.add(_key);
        keys.setAddRemove(false);
        values.setAddRemove(true);
        values.add(_value);
        values.setAddRemove(false);
        entries.setAddRemove(true);
        addGeneEntry(_key, _value);
//        entries.add(new EntryCust<>(_key, _value));
        entries.setAddRemove(false);
    }
    private void addGeneEntry(
            String _k, V _v) {
        entries.add(new ECust<String,V>(_k, _v));
    }

    @Override
    public void removeKey(String _key) {
        if (_key == null) {
            int len_ = keys.size();
            for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                if (_key == keys.get(i)) {
//                    V oldValue_ = values.get(i);
                    removeIndex(i);
                    return;
                }
            }
            return;
        }
        int len_ = keys.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (StringList.quickEq(_key, keys.get(i))) {
//                V oldValue_ = values.get(i);
                removeIndex(i);
                return;
            }
        }
    }

    void removeIndex(int _index) {
        keys.setAddRemove(true);
        keys.remove(_index);
        keys.setAddRemove(false);
        values.setAddRemove(true);
        values.remove(_index);
        values.setAddRemove(false);
        entries.setAddRemove(true);
        entries.remove(_index);
        entries.setAddRemove(false);
    }

    @Override
    public void clear() {
        keys.clear();
        values.clear();
        if (entries != null) {
            entries.clear();
        }
    }

    @Override
    public Listable<V> values() {
        return values;
    }

    @Override
    public Listable<EntryCust<String, V>> entryList() {
        return new CustList<EntryCust<String, V>>();
    }

    @Override
    public void putAllMap(ListableEntries<String, V> _m) {
        for (EntryCust<String, V> e: _m.entryList()) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override
    public Listable<String> getKeys() {
        return new CustList<String>(keys);
    }

    @Override
    public boolean isCorrect() {
        return false;
    }

    @Override
    public void setValue(int _i, V _object) {
    }

    @Override
    public V getValue(int _i) {
        return null;
    }

    @Override
    public String getKey(int _i) {
        return null;
    }

    @Override
    public SimpleIterable entries() {
        // TODO Auto-generated method stub
        return null;
    }

}
