package code.util;
import code.util.ints.ListableEntries;



public final class IdMap<K,V> extends AbsMap<K,V> {

    public IdMap() {
    }

    public IdMap(ListableEntries<K, V> _arg0) {
        putAllMap(_arg0);
    }

    
    public IdMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    protected int indexOfEntry(K _key) {
        int index_ = CustList.FIRST_INDEX;
        for (EntryCust<K, V> e:getList()) {
            if (e.getKey() == _key) {
                return index_;
            }
            index_++;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }

}
