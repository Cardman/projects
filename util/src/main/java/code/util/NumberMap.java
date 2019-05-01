package code.util;
import code.util.ints.ListableEntries;

public final class NumberMap<K extends Number, V> extends AbsMap<K, V> {

//    //list cannot be null, even by reflection
//    private final CustList<EntryCust<K,V>> list = new CustList<EntryCust<K,V>>();

    public NumberMap() {
    }

    public NumberMap(ListableEntries<K, V> _arg0) {
        putAllMap(_arg0);
    }

    
    public NumberMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    public Numbers<K> getKeys() {
        Numbers<K> s_ = new Numbers<K>();
        for (EntryCust<K, V> e: getList()) {
            s_.add(e.getKey());
        }
        return s_;
    }

    @Override
    int indexOfEntry(K _key) {
        int index_ = CustList.FIRST_INDEX;
        Number k_ = _key;
        for (EntryCust<K, V> e:getList()) {
            Number o_ = e.getKey();
            if (k_.longValue() == o_.longValue()) {
                return index_;
            }
            index_++;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }

}
