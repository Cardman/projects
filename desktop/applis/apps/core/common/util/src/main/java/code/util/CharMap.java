package code.util;
import code.util.core.IndexConstants;
import code.util.ints.ListableEntries;




public final class CharMap<V> extends AbsBasicMap<Character,V> {

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
    protected boolean matchKeys(Character _one, Character _two) {
        return _one.charValue() == _two.charValue();
    }
}
