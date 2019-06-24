package code.util;
import code.util.ints.ListableEntries;




public final class CharMap<V> extends AbsMap<Character,V> {

    //list cannot be null, even by reflection
//    private final CustList<EntryCust<Character,V>> list = new CustList<EntryCust<Character,V>>();

    public CharMap() {
    }

    public CharMap(ListableEntries<Character, V> _arg0) {
        putAllMap(_arg0);
    }

    
    public CharMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    protected int indexOfEntry(Character _key) {
        int index_ = CustList.FIRST_INDEX;
        for (EntryCust<Character, V> e:getList()) {
            Character key_ = e.getKey();
            if (_key.charValue() == key_.charValue()) {
                return index_;
            }
            index_++;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }

}
