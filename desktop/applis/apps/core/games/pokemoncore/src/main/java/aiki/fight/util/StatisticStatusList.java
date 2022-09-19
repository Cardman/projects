package aiki.fight.util;

import code.util.CollCapacity;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class StatisticStatusList {
    private final CustList<StatisticStatusByte> list;
    public StatisticStatusList() {
        list = new CustList<StatisticStatusByte>();
    }
    public StatisticStatusList(CollCapacity _cap) {
        list = new CustList<StatisticStatusByte>(_cap);
    }

    public CustList<StatisticStatusByte> entryList() {
        return getList();
    }

    public CustList<Byte> values() {
        CustList<Byte> l_ = new CustList<Byte>();
        for (StatisticStatusByte e: entryList()) {
            l_.add(e.getValue());
        }
        return l_;
    }

    public CustList<StatisticStatus> getKeys() {
        CustList<StatisticStatus> l_ = new CustList<StatisticStatus>();
        for (StatisticStatusByte e: entryList()) {
            l_.add(e.getStat());
        }
        return l_;
    }

    public CustList<StatisticStatusByte> getList() {
        return list;
    }


    public boolean isEmpty() {
        return getList().isEmpty();
    }


    public StatisticStatusByte getEntryByKey(StatisticStatus _key) {
        int index_ = indexOfEntry(_key);
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return null;
        }
        return getList().get(index_);
    }


    public boolean contains(StatisticStatus _key) {
        return getEntryByKey(_key) != null;
    }

    public byte getVal(StatisticStatus _key) {
        StatisticStatusByte e_ = getEntryByKey(_key);
        if (e_ == null) {
            return (byte)0;
        }
        return e_.getValue();
    }


    public int indexOfEntry(StatisticStatus _key){
        int len_ = list.size();
        for (int i = 0; i < len_; i++) {
            if (_key.eq(list.get(i).getStat())){
                return i;
            }
        }
        return -1;
    }

    public void addEntry(StatisticStatus _k, Byte _v) {
        list.add(new StatisticStatusByte(_k, _v));
    }

}
