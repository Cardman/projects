package code.util;
import code.util.ints.ListableEntries;




public final class StringMap<V> extends AbsMap<String,V> {

    public StringMap() {
    }

    public StringMap(ListableEntries<String, V> _arg0) {
        putAllMap(_arg0);
    }

    
    public StringMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    public StringList getKeys() {
        StringList s_ = new StringList();
        for (EntryCust<String, V> e: getList()) {
            s_.add(e.getKey());
        }
        return s_;
    }

    @Override
    int indexOfEntry(String _key) {
        int index_ = CustList.FIRST_INDEX;
        for (EntryCust<String, V> e:getList()) {
            String k_ = e.getKey();
            if (StringList.quickEq(_key, k_)) {
                return index_;
            }
            index_++;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }

}
