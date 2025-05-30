package code.util;
import code.util.core.IndexConstants;
import code.util.core.IntIndexOfEntryUtil;
import code.util.ints.*;



public abstract class AbsMap<K, V> implements ListableEntries<K, V>, IntIndexOfEntry<K> {

    //list cannot be null, even by reflection
    private final CustList<EntryCust<K,V>> list;

    protected AbsMap() {
        list = new CustList<EntryCust<K,V>>();
    }
    protected AbsMap(CollCapacity _capacity) {
        list = new CustList<EntryCust<K,V>>(_capacity);
    }

    @Override
    public final Iterable<EntryCust<K, V>> entryList() {
        return new CustList<EntryCust<K,V>>(getList());
    }

    @Override
    public CustList<K> getKeys() {
        CustList<K> l_ = new CustList<K>();
        for (EntryCust<K,V> e: list) {
            l_.add(e.getKey());
        }
        return l_;
    }

    public boolean containsAllAsKeys(CustList<K> _elements) {
        for (K k: _elements) {
            if (!contains(k)) {
                return false;
            }
        }
        return true;
    }

    public K firstKey() {
        return getList().first().getKey();
    }

    public K lastKey() {
        return getList().last().getKey();
    }

    public V firstValue() {
        return getList().first().getValue();
    }

    public V lastValue() {
        return getList().last().getValue();
    }

    public final CustList<EntryCust<K, V>> getList() {
        return list;
    }

    public void setKey(int _i, K _k) {
        EntryCust<K,V> bk_ = list.get(_i);
        list.set(_i, new EntryCust<K,V>(_k, bk_.getValue()));
    }

    @Override
    public K getKey(int _i) {
        return getEntry(_i).getKey();
    }

    @Override
    public V getValue(int _i) {
        return getEntry(_i).getValue();
    }

    @Override
    public void setValue(int _i, V _object) {
        getEntry(_i).setValue(_object);
    }

    public EntryCust<K,V> getEntry(int _i) {
        return list.get(_i);
    }

    public void tryAdd(K _key, V _v) {
        int index_ = indexOfEntry(_key);
        if (index_ != IndexConstants.INDEX_NOT_FOUND_ELT) {
            return;
        }
        addEntry(_key,_v);
    }
    public void set(K _key,V _v) {
        int index_ = indexOfEntry(_key);
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return;
        }
        setValue(index_, _v);
    }
    @Override
    public void clear() {
        getList().clear();
    }
    @Override
    public boolean isEmpty() {
        return getList().isEmpty();
    }

    @Override
    public int size() {
        return getList().size();
    }
    public EntryCust<K,V> getEntryByKey(K _key) {
        int index_ = indexOfEntry(_key);
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return null;
        }
        return getList().get(index_);
    }


    public void reset() {
        CustList<EntryCust<K,V>> ls_ = new CustList<EntryCust<K, V>>(list);
        list.clear();
        for (EntryCust<K,V> e: ls_) {
            put(e.getKey(),e.getValue());
        }
    }

    @Override
    public void put(K _key, V _v) {
        int index_ = indexOfEntry(_key);
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            addEntry(_key, _v);
            return;
        }
        setValue(index_, _v);
    }
    @Override
    public boolean contains(K _key) {
        return getEntryByKey(_key) != null;
    }
    @Override
    public V getVal(K _key) {
        EntryCust<K,V> e_ = getEntryByKey(_key);
        if (e_ == null) {
            return def();
        }
        return e_.getValue();
    }
    protected V def(){
        return null;
    }
    @Override
    public void move(K _oldKey, K _newKey) {
        int size_ = size();
        V value_ = getVal(_oldKey);
        removeKey(_oldKey);
        if (size_ != size()) {
            put(_newKey, value_);
        }
    }

    @Override
    public CustList<V> values() {
        CustList<V> s_ = new CustList<V>();
        for (EntryCust<K, V> e: getList()) {
            s_.add(e.getValue());
        }
        return s_;
    }

    @Override
    public void putAllMap(ListableEntries<K, V> _m) {
        for (EntryCust<K,V> e: _m.entryList()) {
            put(e.getKey(), e.getValue());
        }
    }
    public void addAllEntries(ListableEntries<K, V> _m) {
        for (EntryCust<K,V> e: _m.entryList()) {
            addEntry(e.getKey(), e.getValue());
        }
    }
    @Override
    public void removeKey(K _key) {
        int index_ = indexOfEntry(_key);
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return;
        }
        getList().remove(index_);
    }

    public void remove(int _index) {
        getList().remove(_index);
    }
    public boolean isValidIndex(int _index) {
        return getList().isValidIndex(_index);
    }

    public int indexOfEntry(K _key) {
        return indexOfEntry(_key, 0);
    }

    public CustList<V> valuesKey(K _key) {
        Ints is_ = valuesKeyIndexes(_key);
        int len_ = is_.size();
        CustList<V> v_ = new CustList<V>();
        for (int i = 0; i < len_; i++) {
            v_.add(getValue(is_.get(i)));
        }
        return v_;
    }

    public CustList<K> differentKeys() {
        return new IntIndexOfEntryUtil<K>(this).differentKeys();
    }
    public Ints valuesKeyIndexes(K _key) {
        return new IntIndexOfEntryUtil<K>(this).valuesKeyIndexes(_key);
    }

    public void addEntry(K _k, V _v) {
        list.add(new EntryCust<K, V>(_k, _v));
    }
}
