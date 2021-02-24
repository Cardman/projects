package aiki.map.util;

import aiki.instances.Instances;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class MiniMapCoordsList {
    private final CustList<MiniMapCoordsTile> list;
    public MiniMapCoordsList() {
        list = new CustList<MiniMapCoordsTile>();
    }
    public MiniMapCoordsList(CollCapacity _cap) {
        list = new CustList<MiniMapCoordsTile>(_cap);
    }

    public CustList<MiniMapCoordsTile> entryList() {
        return getList();
    }


    public CustList<MiniMapCoords> getKeys() {
        CustList<MiniMapCoords> l_ = new CustList<MiniMapCoords>();
        for (MiniMapCoordsTile e: entryList()) {
            l_.add(e.getMiniMapCoords());
        }
        return l_;
    }

    public CustList<MiniMapCoordsTile> getList() {
        return list;
    }
    public CustList<TileMiniMap> values() {
        CustList<TileMiniMap> l_ = new CustList<TileMiniMap>();
        for (MiniMapCoordsTile e: entryList()) {
            l_.add(e.getTileMap());
        }
        return l_;
    }


    public MiniMapCoordsTile getEntryByKey(MiniMapCoords _key) {
        int index_ = indexOfEntry(_key);
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return null;
        }
        return getList().get(index_);
    }


    public boolean contains(MiniMapCoords _key) {
        return getEntryByKey(_key) != null;
    }

    public TileMiniMap getVal(MiniMapCoords _key) {
        MiniMapCoordsTile e_ = getEntryByKey(_key);
        if (e_ == null) {
            return Instances.newTileMiniMap();
        }
        return e_.getTileMap();
    }

    public int indexOfEntry(MiniMapCoords _key){
        int len_ = list.size();
        for (int i = 0; i < len_; i++) {
            if (_key.eq(list.get(i).getMiniMapCoords())){
                return i;
            }
        }
        return -1;
    }

    public void addEntry(MiniMapCoords _k, TileMiniMap _v) {
        list.add(new MiniMapCoordsTile(_k, _v));
    }


}
