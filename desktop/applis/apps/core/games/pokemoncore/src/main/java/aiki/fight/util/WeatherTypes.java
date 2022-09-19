package aiki.fight.util;

import code.maths.Rate;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class WeatherTypes {
    private final CustList<WeatherTypeRate> list;
    public WeatherTypes() {
        list = new CustList<WeatherTypeRate>();
    }
    public WeatherTypes(CollCapacity _cap) {
        list = new CustList<WeatherTypeRate>(_cap);
    }

    public CustList<WeatherTypeRate> entryList() {
        return getList();
    }


    public CustList<Rate> values() {
        CustList<Rate> l_ = new CustList<Rate>();
        for (WeatherTypeRate e: entryList()) {
            l_.add(e.getValue());
        }
        return l_;
    }
    public CustList<WeatherType> getKeys() {
        CustList<WeatherType> l_ = new CustList<WeatherType>();
        for (WeatherTypeRate e: entryList()) {
            l_.add(e.getStat());
        }
        return l_;
    }

    public CustList<WeatherTypeRate> getList() {
        return list;
    }


    public boolean isEmpty() {
        return getList().isEmpty();
    }


    public WeatherTypeRate getEntryByKey(WeatherType _key) {
        int index_ = indexOfEntry(_key);
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return null;
        }
        return getList().get(index_);
    }


    public Rate getVal(WeatherType _key) {
        WeatherTypeRate e_ = getEntryByKey(_key);
        if (e_ == null) {
            return Rate.zero();
        }
        return e_.getValue();
    }


    public int indexOfEntry(WeatherType _key){
        int len_ = list.size();
        for (int i = 0; i < len_; i++) {
            if (_key.eq(list.get(i).getStat())){
                return i;
            }
        }
        return -1;
    }

    public void addEntry(WeatherType _k, Rate _v) {
        list.add(new WeatherTypeRate(_k, _v));
    }

}
