package code.util;




public final class BooleanMap<V> extends AbsMap<Boolean,V> {

    //list cannot be null, even by reflection
//    private final CustList<EntryCust<Boolean,V>> list = new CustList<EntryCust<Boolean,V>>();

    public BooleanMap() {
    }
    
    public BooleanMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    protected int indexOfEntry(Boolean _key) {
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
