package code.util;
import code.util.annot.CapacityInit;
import code.util.ints.Equallable;
import code.util.ints.ListableEntries;

public final class ObjectNotNullMap<K extends Equallable<K>, V>  extends AbObjectMap<K,V> {

    public ObjectNotNullMap() {
    }

    public ObjectNotNullMap(ListableEntries<K, V> _arg0) {
        putAllMap(_arg0);
    }

    @CapacityInit
    public ObjectNotNullMap(CollCapacity _capacity) {
        super(_capacity);
    }
//    public static <T extends Equallable<T>> void deleteLineReturn(ObjectNotNullMap<T,String> _map) {
//        for (EntryCust<T, String> e: _map.entryList()) {
//            e.setValue(StringList.removeStrings(e.getValue(), Constants.RETURN_LINE));
//        }
//    }

    @Override
    public boolean isCorrect() {
        if (!super.isCorrect()) {
            return false;
        }
        for (EntryCust<K, V> e:getList()) {
            if (e.getKey() == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void put(K _key, V _v) {
        if (_key == null) {
            return;
        }
        super.put(_key, _v);
    }

    @Override
    int indexOfEntry(K _key) {
        int index_ = CustList.FIRST_INDEX;
        Equallable<K> k_ = _key;
        if (k_ == null) {
            return CustList.INDEX_NOT_FOUND_ELT;
        }
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
