package aiki.util;

import code.util.CollCapacity;
import code.util.CustList;
import code.util.core.IndexConstants;

public abstract class CommonMap<K,V> {
    private final CustList<CommonParam<K,V>> list;
    protected CommonMap() {
        list = new CustList<CommonParam<K,V>>();
    }
    protected CommonMap(CollCapacity _cap) {
        list = new CustList<CommonParam<K,V>>(_cap);
    }
    protected CommonMap(CommonMap<K,V> _cap) {
        list = new CustList<CommonParam<K,V>>(_cap.list);
    }

    public CustList<CommonParam<K,V>> entryList() {
        return getList();
    }


    public CustList<K> getKeys() {
        CustList<K> l_ = new CustList<K>();
        for (CommonParam<K,V> e: entryList()) {
            l_.add(e.getKey());
        }
        return l_;
    }

    public CustList<CommonParam<K,V>> getList() {
        return list;
    }


    public boolean isEmpty() {
        return getList().isEmpty();
    }


    public int size() {
        return getList().size();
    }
    public CommonParam<K,V> getEntryByKey(K _key) {
        int index_ = indexOfEntry(_key);
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return null;
        }
        return getList().get(index_);
    }


    public boolean contains(K _key) {
        return getEntryByKey(_key) != null;
    }

    public V getVal(K _key) {
        CommonParam<K,V> e_ = getEntryByKey(_key);
        if (e_ == null) {
            return def();
        }
        return e_.getValue();
    }

    protected abstract V def();
    public CustList<V> values() {
        CustList<V> lk_ = new CustList<V>();
        for (CommonParam<K,V> e: getList()) {
            lk_.add(e.getValue());
        }
        return lk_;
    }

    public int indexOfEntry(K _key){
        int len_ = list.size();
        for (int i = 0; i < len_; i++) {
            if (eq(_key,list.get(i).getKey())){
                return i;
            }
        }
        return -1;
    }

    public void addEntry(K _k, V _v) {
        list.add(new CommonParam<K,V>(_k, _v));
    }

    public void put(K _key, V _value) {
        int index_ = indexOfEntry(_key);
        if (index_ < 0) {
            addEntry(_key, _value);
            return;
        }
        list.get(index_).setValue(_value);
    }

    public void removeKey(K _key) {
        int index_ = indexOfEntry(_key);
        if (index_ < 0) {
            return;
        }
        list.remove(index_);
    }

    protected abstract boolean eq(K _one, K _two);

    public void set(K _key, V _value) {
        int index_ = indexOfEntry(_key);
        if (index_ < 0) {
            return;
        }
        list.get(index_).setValue(_value);
    }

    public void clear() {
        list.clear();
    }

    public void putAllMap(CommonMap<K,V> _map) {
        for (CommonParam<K,V> e: _map.list) {
            put(e.getKey(),e.getValue());
        }
    }
}
