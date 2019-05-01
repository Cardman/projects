package code.util;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;




public final class BooleanMap<V> extends AbsMap<Boolean,V> {

    //list cannot be null, even by reflection
//    private final CustList<EntryCust<Boolean,V>> list = new CustList<EntryCust<Boolean,V>>();

    public BooleanMap() {
    }

    public BooleanMap(ListableEntries<Boolean, V> _arg0) {
        putAllMap(_arg0);
    }

    
    public BooleanMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    public BooleanList getKeys() {
        BooleanList s_ = new BooleanList();
        for (EntryCust<Boolean, V> e: getList()) {
            s_.add(e.getKey());
        }
        return s_;
    }

    @Override
    int indexOfEntry(Boolean _key) {
        int index_ = CustList.FIRST_INDEX;
        for (EntryCust<Boolean, V> e:getList()) {
            if (e.getKey() == _key) {
                return index_;
            }
            index_++;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }

}
