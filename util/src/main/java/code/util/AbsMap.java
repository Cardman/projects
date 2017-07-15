package code.util;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;



public abstract class AbsMap<K, V> implements ListableEntries<K, V> {

    //list cannot be null, even by reflection
    private final transient CustList<EntryCust<K,V>> list = new CustList<EntryCust<K,V>>();

    K getKeyOrNull(EntryCust<K, V> _e) {
        if(_e == null) {
            return null;
        }
        return _e.getKey();
    }

    @Override
    public Listable<EntryCust<K, V>> entryList() {
        return new CustList<EntryCust<K,V>>(getList());
    }

    public boolean containsAllAsKeys(Listable<K> _elements) {
        for (K k: _elements) {
            if (!contains(k)) {
                return false;
            }
        }
        return true;
    }

    final Listable<EntryCust<K, V>> getList() {
        return list;
    }

    @Override
    public boolean isCorrect() {
        return isCorrectBase();
    }

    public final boolean isCorrectBase() {
        for (EntryCust<K,V> e:getList()) {
            if (getValues(e.getKey()).size() != CustList.ONE_ELEMENT) {
                return false;
            }
        }
        return true;
    }

    public static void put(ListableEntries<?, ?> _l,
            Object _k, Object _v) {
        ListableEntries<? super Object, ? super Object> l_;
        l_ = (ListableEntries<? super Object, ? super Object>) _l;
        l_.put(_k, _v);
    }

    public static void addEntry(ListableEntries<?, ?> _l,
            Object _k, Object _v) {
        ListableEntries<? super Object, ? super Object> l_;
        l_ = (ListableEntries<? super Object, ? super Object>) _l;
        if (l_ instanceof AbsMap<?, ?>) {
            CustList.add(((AbsMap<?, ?>)l_).getList(), new EntryCust<Object, Object>(_k, _v));
//            ((AbsMap<?, ?>)l_).getList().add(new EntryCust<Object, Object>(_k, _v));
        } else {
            CustList.add(l_.entryList(), new EntryCust<Object, Object>(_k, _v));
//            l_.entryList().add(new EntryCust<Object, Object>(_k, _v));
        }
//        _l.add(new EntryCust<Object, Object>(_k, _v));
    }
    
    public static void setGeneKey(ListableEntries<?, ?> _l,
            int _i, Object _k) {
        if (_l instanceof AbsMap<?, ?>) {
            Listable<? extends EntryCust<?,?>> l_;
            l_ = ((AbsMap<?, ?>) _l).getList();
            EntryCust<?,?> bk_ = l_.get(_i);
//            l_.set(_i, new EntryCust<Object,Object>(_k, bk_.getValue()));
            CustList.set(l_, _i, new EntryCust<Object,Object>(_k, bk_.getValue()));
        } else {
            Listable<? extends EntryCust<?,?>> l_;
            l_ = _l.entryList();
            EntryCust<?,?> bk_ = l_.get(_i);
//            l_.set(_i, new EntryCust<Object,Object>(_k, bk_.getValue()));
            CustList.set(l_, _i, new EntryCust<Object,Object>(_k, bk_.getValue()));
        }
//        _l.set(_i, new EntryCust<L,U>(_k, bk_.getValue()));
    }

    public static void setGeneValue(ListableEntries<?, ?> _l,
            int _i, Object _v) {
        if (_l instanceof AbsMap<?, ?>) {
            Listable<? extends EntryCust<?, ? super Object>> l_;
            l_ = ((AbsMap<?, ? super Object>) _l).getList();
            EntryCust<?, ? super Object> bk_ = l_.get(_i);
            bk_.setValue(_v);
        } else {
            Listable<? extends EntryCust<?, ?>> l_;
            l_ = _l.entryList();
            EntryCust<?,?> bk_ = l_.get(_i);
            CustList.set(l_, _i, new EntryCust<Object,Object>(bk_.getKey(), _v));
//            EntryCust<?,? super Object> bk_ = l_.get(_i);
//            bk_.setValue(_v);
        }
//        EntryCust<L,U> bk_ = _l.get(_i);
//        bk_.setValue(_v);
    }

    public void add(K _key,V _v) {
        if (contains(_key)) {
            return;
        }
        put(_key, _v);
    }
    public void set(K _key,V _v) {
        int index_ = indexOfEntry(_key);
        if (index_ == CustList.INDEX_NOT_FOUND_ELT) {
            return;
        }
        EntryCust<K, V> e_ = getList().get(index_);
        e_.setValue(_v);
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
    @Override
    public String toString() {
        return getList().toString();
    }
    EntryCust<K,V> getEntryByKey(K _key) {
        int index_ = indexOfEntry(_key);
        if (index_ == CustList.INDEX_NOT_FOUND_ELT) {
            return null;
        }
        return getList().get(index_);
    }

    

    @Override
    public void put(K _key, V _v) {
        int index_ = indexOfEntry(_key);
        if (index_ == CustList.INDEX_NOT_FOUND_ELT) {
            getList().add(new EntryCust<K, V>(_key, _v));
            return;
        }
        EntryCust<K, V> e_ = getList().get(index_);
        e_.setValue(_v);
    }
    @Override
    public boolean contains(K _key) {
        return getEntryByKey(_key) != null;
    }
    @Override
    public V getVal(K _key) {
        EntryCust<K,V> e_ = getEntryByKey(_key);
        if (e_ == null) {
            return null;
        }
        return e_.getValue();
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
    public void removeKey(K _key) {
        geneRemove(_key);
    }

    abstract int indexOfEntry(K _key);

    void geneRemove(K _key) {
        int index_ = indexOfEntry(_key);
        if (index_ == CustList.INDEX_NOT_FOUND_ELT) {
            return;
        }
        getList().removeAt(index_);
    }
}
