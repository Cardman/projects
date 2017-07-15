package code.util;
import code.util.ints.Equallable;
import code.util.ints.ListableEntries;

public final class ObjectMap<K extends Equallable<K>, V> extends AbObjectMap<K,V> {

    public ObjectMap() {
    }

    public ObjectMap(ListableEntries<? extends K, ? extends V> _arg0) {
        putAllMap(_arg0);
    }

//    public static <T extends Equallable<T>> void deleteLineReturn(ObjectMap<T,String> _map) {
//        for (EntryCust<T, String> e: _map.entryList()) {
//            e.setValue(StringList.removeStrings(e.getValue(), Constants.RETURN_LINE));
//        }
//    }
//    public static <K,V extends Number> boolean hasNumber(Map<K,V> _map, V _value) {
//        if (_value == null) {
//            for (V s: _map.values()) {
//                if (s == null) {
//                    return true;
//                }
//            }
//            return false;
//        }
//        for (V s: _map.values()) {
//            if (_value.longValue() == s.longValue()) {
//                return true;
//            }
//        }
//        return false;
//    }
    @Override
    int indexOfEntry(K _key) {
        int index_ = CustList.FIRST_INDEX;
        if (_key == null) {
            for (EntryCust<K, V> e:getList()) {
                if (e.getKey() == null) {
                    return index_;
                }
                index_++;
            }
            return CustList.INDEX_NOT_FOUND_ELT;
        }
//        if (!(_key instanceof Equallable<?>)) {
//            return CustList.INDEX_NOT_FOUND_ELT;
//        }
        Equallable<K> k_ = _key;
        for (EntryCust<K, V> e:getList()) {
            K key_ = e.getKey();
            if (key_ == null) {
                continue;
            }
            if (k_.eq(key_)) {
                return index_;
            }
            index_++;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }
}
