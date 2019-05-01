package code.util;
import code.util.ints.Equallable;
import code.util.ints.ListableEntries;

public final class ObjectMap<K extends Equallable<K>, V> extends AbObjectMap<K,V> {

    public ObjectMap() {
    }

    public ObjectMap(ListableEntries<K, V> _arg0) {
        putAllMap(_arg0);
    }

    
    public ObjectMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    int indexOfEntry(K _key) {
        int index_ = CustList.FIRST_INDEX;
        Equallable<K> k_ = _key;
        for (EntryCust<K, V> e:getList()) {
            K key_ = e.getKey();
            if (k_.eq(key_)) {
                return index_;
            }
            index_++;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }
}
