package aiki.util;

import code.util.CollCapacity;
import code.util.CustList;
import code.util.core.IndexConstants;

public abstract class Points<T> {
    private final CustList<PointParam<T>> list;
    public Points() {
        list = new CustList<PointParam<T>>();
    }
    public Points(CollCapacity _cap) {
        list = new CustList<PointParam<T>>(_cap);
    }

    public CustList<PointParam<T>> entryList() {
        return getList();
    }


    public CustList<Point> getKeys() {
        CustList<Point> l_ = new CustList<Point>();
        for (PointParam<T> e: entryList()) {
            l_.add(e.getKey());
        }
        return l_;
    }

    public CustList<PointParam<T>> getList() {
        return list;
    }


    public boolean isEmpty() {
        return getList().isEmpty();
    }


    public int size() {
        return getList().size();
    }
    public PointParam<T> getEntryByKey(Point _key) {
        int index_ = indexOfEntry(_key);
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return null;
        }
        return getList().get(index_);
    }


    public boolean contains(Point _key) {
        return getEntryByKey(_key) != null;
    }

    public T getVal(Point _key) {
        PointParam<T> e_ = getEntryByKey(_key);
        if (e_ == null) {
            return def();
        }
        return e_.getValue();
    }

    protected abstract T def();
    public CustList<T> values() {
        CustList<T> lk_ = new CustList<T>();
        for (PointParam<T> e: getList()) {
            lk_.add(e.getValue());
        }
        return lk_;
    }

    public int indexOfEntry(Point _key){
        int len_ = list.size();
        for (int i = 0; i < len_; i++) {
            if (_key.eq(list.get(i).getKey())){
                return i;
            }
        }
        return -1;
    }

    public void addEntry(Point _k, T _v) {
        list.add(new PointParam<T>(_k, _v));
    }

    public void put(Point _key, T _value) {
        int index_ = indexOfEntry(_key);
        if (index_ < 0) {
            addEntry(_key, _value);
            return;
        }
        list.get(index_).setValue(_value);
    }

    public void removeKey(Point _key) {
        int index_ = indexOfEntry(_key);
        if (index_ < 0) {
            return;
        }
        list.remove(index_);
    }
}
