package aiki.util;

import aiki.map.Condition;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class CoordsLists {
    private final CustList<CoordsListCoords> list;
    public CoordsLists() {
        list = new CustList<CoordsListCoords>();
    }
    public CoordsLists(CollCapacity _cap) {
        list = new CustList<CoordsListCoords>(_cap);
    }

    public CustList<CoordsListCoords> entryList() {
        return getList();
    }


    public Condition getKeys() {
        Condition l_ = new Condition();
        for (CoordsListCoords e: entryList()) {
            l_.add(e.getKey());
        }
        return l_;
    }

    public CustList<CoordsListCoords> getList() {
        return list;
    }


    public boolean isEmpty() {
        return getList().isEmpty();
    }


    public int size() {
        return getList().size();
    }
    public CoordsListCoords getEntryByKey(Coords _key) {
        int index_ = indexOfEntry(_key);
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return null;
        }
        return getList().get(index_);
    }


    public boolean contains(Coords _key) {
        return getEntryByKey(_key) != null;
    }

    public Condition getVal(Coords _key) {
        CoordsListCoords e_ = getEntryByKey(_key);
        if (e_ == null) {
            return new Condition();
        }
        return e_.getValue();
    }

    public CustList<Condition> values() {
        CustList<Condition> lk_ = new CustList<Condition>();
        for (CoordsListCoords e: getList()) {
            lk_.add(e.getValue());
        }
        return lk_;
    }

    public int indexOfEntry(Coords _key){
        int len_ = list.size();
        for (int i = 0; i < len_; i++) {
            if (_key.eq(list.get(i).getKey())){
                return i;
            }
        }
        return -1;
    }

    public void addEntry(Coords _k, Condition _v) {
        list.add(new CoordsListCoords(_k, _v));
    }

    public void put(Coords _key, Condition _value) {
        int index_ = indexOfEntry(_key);
        if (index_ < 0) {
            addEntry(_key, _value);
            return;
        }
        list.get(index_).setValue(_value);
    }

    public void removeKey(Coords _key) {
        int index_ = indexOfEntry(_key);
        if (index_ < 0) {
            return;
        }
        list.remove(index_);
    }

}
