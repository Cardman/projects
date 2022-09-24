package aiki.util;

import aiki.map.levels.Link;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class LevelPoints {
    private final CustList<LevelPointLink> list;
    public LevelPoints() {
        list = new CustList<LevelPointLink>();
    }
    public LevelPoints(CollCapacity _cap) {
        list = new CustList<LevelPointLink>(_cap);
    }

    public CustList<LevelPointLink> entryList() {
        return getList();
    }

    
    public CustList<LevelPoint> getKeys() {
        CustList<LevelPoint> l_ = new CustList<LevelPoint>();
        for (LevelPointLink e: entryList()) {
            l_.add(e.getLevelPoint());
        }
        return l_;
    }

    public CustList<LevelPointLink> getList() {
        return list;
    }


    public boolean isEmpty() {
        return getList().isEmpty();
    }


    public LevelPointLink getEntryByKey(LevelPoint _key) {
        int index_ = indexOfEntry(_key);
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return null;
        }
        return getList().get(index_);
    }


    public boolean contains(LevelPoint _key) {
        return getEntryByKey(_key) != null;
    }
    
    public Link getVal(LevelPoint _key) {
        LevelPointLink e_ = getEntryByKey(_key);
        if (e_ == null) {
            return new Link("");
        }
        return e_.getLink();
    }

    public int indexOfEntry(LevelPoint _key){
        int len_ = list.size();
        for (int i = 0; i < len_; i++) {
            if (_key.eq(list.get(i).getLevelPoint())){
                return i;
            }
        }
        return -1;
    }

    public void addEntry(LevelPoint _k, Link _v) {
        list.add(new LevelPointLink(_k, _v));
    }

}
