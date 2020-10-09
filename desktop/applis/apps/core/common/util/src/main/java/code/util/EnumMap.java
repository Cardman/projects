package code.util;
import code.util.core.IndexConstants;
import code.util.ints.ListableEntries;




public final class EnumMap<K, V> extends AbsBasicMap<K,V> {

//    //list cannot be null, even by reflection
//    private final CustList<EntryCust<K,V>> list = new CustList<EntryCust<K,V>>();

    public EnumMap() {
    }

    public EnumMap(ListableEntries<K, V> _arg0) {
        putAllMap(_arg0);
    }
    
    public EnumMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    protected boolean matchKeys(K _one, K _two) {
        return _one == _two;
    }
}
