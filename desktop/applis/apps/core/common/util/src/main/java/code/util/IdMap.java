package code.util;
import code.util.ints.ListableEntries;



public final class IdMap<K,V> extends AbsBasicMap<K,V> {

    public IdMap() {
    }

    public IdMap(ListableEntries<K, V> _arg0) {
        super(new CollCapacity(_arg0.size()));
        addAllEntries(_arg0);
    }

    
    public IdMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    protected boolean matchKeys(K _one, K _two) {
        return _one == _two;
    }
}
