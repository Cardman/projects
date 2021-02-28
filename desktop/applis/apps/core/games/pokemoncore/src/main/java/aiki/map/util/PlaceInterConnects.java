package aiki.map.util;

import aiki.util.Coords;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class PlaceInterConnects {
    private final CustList<PlaceInterConnectCoords> list;
    public PlaceInterConnects() {
        list = new CustList<PlaceInterConnectCoords>();
    }
    public PlaceInterConnects(CollCapacity _cap) {
        list = new CustList<PlaceInterConnectCoords>(_cap);
    }

    public CustList<PlaceInterConnectCoords> entryList() {
        return getList();
    }


    public CustList<PlaceInterConnect> getKeys() {
        CustList<PlaceInterConnect> l_ = new CustList<PlaceInterConnect>();
        for (PlaceInterConnectCoords e: entryList()) {
            l_.add(e.getPlaceInterConnect());
        }
        return l_;
    }

    public static boolean contains(CustList<PlaceInterConnect> _elements, PlaceInterConnect _s) {
        for (PlaceInterConnect k: _elements) {
            if (_s.eq(k)) {
                return true;
            }
        }
        return false;
    }

    public CustList<PlaceInterConnectCoords> getList() {
        return list;
    }


    public int size() {
        return getList().size();
    }
    public PlaceInterConnectCoords getEntryByKey(PlaceInterConnect _key) {
        int index_ = indexOfEntry(_key);
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return null;
        }
        return getList().get(index_);
    }


    public boolean contains(PlaceInterConnect _key) {
        return getEntryByKey(_key) != null;
    }

    public Coords getVal(PlaceInterConnect _key) {
        PlaceInterConnectCoords e_ = getEntryByKey(_key);
        if (e_ == null) {
            return new Coords("");
        }
        return e_.getCoords();
    }

    public int indexOfEntry(PlaceInterConnect _key){
        int len_ = list.size();
        for (int i = 0; i < len_; i++) {
            if (_key.eq(list.get(i).getPlaceInterConnect())){
                return i;
            }
        }
        return -1;
    }

    public void putAllMap(PlaceInterConnects _l) {
        for (PlaceInterConnectCoords e:  _l.entryList()) {
            put(e.getPlaceInterConnect(),e.getCoords());
        }
    }

    public void put(PlaceInterConnect _k, Coords _v) {
        int index_ = indexOfEntry(_k);
        if (index_ < 0) {
            addEntry(_k, _v);
            return;
        }
        list.get(index_).setCoords(_v);
    }
    public void addEntry(PlaceInterConnect _k, Coords _v) {
        list.add(new PlaceInterConnectCoords(_k, _v));
    }

    public void clear() {
        list.clear();
    }
}
