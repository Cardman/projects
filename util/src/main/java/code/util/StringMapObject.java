package code.util;

import code.util.ints.ListableEntries;

public final class StringMapObject extends AbsMap<String,Object> {


    public StringMapObject() {
    }

    public StringMapObject(ListableEntries<String, Object> _arg0) {
        putAllMap(_arg0);
    }

    
    public StringMapObject(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    public StringList getKeys() {
        StringList s_ = new StringList();
        for (EntryCust<String, Object> e: getList()) {
            s_.add(e.getKey());
        }
        return s_;
    }

    @Override
    int indexOfEntry(String _key) {
        int index_ = CustList.FIRST_INDEX;
        for (EntryCust<String, Object> e:getList()) {
            String k_ = e.getKey();
            if (StringList.quickEq(_key, k_)) {
                return index_;
            }
            index_++;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }
}
