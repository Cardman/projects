package aiki.fight.util;

import code.util.CollCapacity;
import code.util.CustList;
import code.util.core.IndexConstants;

public abstract class StatisticCategoryList<T> {
    private final CustList<StatisticCategoryParam<T>> list;
    protected StatisticCategoryList() {
        list = new CustList<StatisticCategoryParam<T>>();
    }
    protected StatisticCategoryList(CollCapacity _cap) {
        list = new CustList<StatisticCategoryParam<T>>(_cap);
    }

    public CustList<StatisticCategoryParam<T>> entryList() {
        return getList();
    }


    public CustList<T> values() {
        CustList<T> l_ = new CustList<T>();
        for (StatisticCategoryParam<T> e: entryList()) {
            l_.add(e.getValue());
        }
        return l_;
    }
    public CustList<StatisticCategory> getKeys() {
        CustList<StatisticCategory> l_ = new CustList<StatisticCategory>();
        for (StatisticCategoryParam<T> e: entryList()) {
            l_.add(e.getStatistic());
        }
        return l_;
    }

    public CustList<StatisticCategoryParam<T>> getList() {
        return list;
    }


    public boolean isEmpty() {
        return getList().isEmpty();
    }


    public StatisticCategoryParam<T> getEntryByKey(StatisticCategory _key) {
        int index_ = indexOfEntry(_key);
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return null;
        }
        return getList().get(index_);
    }


    public boolean contains(StatisticCategory _key) {
        return getEntryByKey(_key) != null;
    }

    public T getVal(StatisticCategory _key) {
        StatisticCategoryParam<T> e_ = getEntryByKey(_key);
        if (e_ == null) {
            return def();
        }
        return e_.getValue();
    }

    protected abstract T def();

    public int indexOfEntry(StatisticCategory _key){
        int len_ = list.size();
        for (int i = 0; i < len_; i++) {
            if (_key.eq(list.get(i).getStatistic())){
                return i;
            }
        }
        return -1;
    }

    public void addEntry(StatisticCategory _k, T _v) {
        list.add(new StatisticCategoryParam<T>(_k, _v));
    }

}
