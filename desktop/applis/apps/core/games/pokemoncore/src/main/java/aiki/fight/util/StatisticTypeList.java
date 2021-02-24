package aiki.fight.util;

import code.util.CollCapacity;
import code.util.CustList;
import code.util.core.IndexConstants;

public abstract class StatisticTypeList<T> {
    private final CustList<StatisticTypeParam<T>> list;
    public StatisticTypeList() {
        list = new CustList<StatisticTypeParam<T>>();
    }
    public StatisticTypeList(CollCapacity _cap) {
        list = new CustList<StatisticTypeParam<T>>(_cap);
    }

    public CustList<StatisticTypeParam<T>> entryList() {
        return getList();
    }


    public CustList<StatisticType> getKeys() {
        CustList<StatisticType> l_ = new CustList<StatisticType>();
        for (StatisticTypeParam<T> e: entryList()) {
            l_.add(e.getStatistic());
        }
        return l_;
    }

    public CustList<StatisticTypeParam<T>> getList() {
        return list;
    }


    public boolean isEmpty() {
        return getList().isEmpty();
    }


    public StatisticTypeParam<T> getEntryByKey(StatisticType _key) {
        int index_ = indexOfEntry(_key);
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return null;
        }
        return getList().get(index_);
    }

    public boolean contains(StatisticType _key) {
        return getEntryByKey(_key) != null;
    }

    public T getVal(StatisticType _key) {
        StatisticTypeParam<T> e_ = getEntryByKey(_key);
        if (e_ == null) {
            return def();
        }
        return e_.getValue();
    }

    protected abstract T def();


    public int indexOfEntry(StatisticType _key){
        int len_ = list.size();
        for (int i = 0; i < len_; i++) {
            if (_key.eq(list.get(i).getStatistic())){
                return i;
            }
        }
        return -1;
    }

    public void addEntry(StatisticType _k, T _v) {
        list.add(new StatisticTypeParam<T>(_k, _v));
    }

}
