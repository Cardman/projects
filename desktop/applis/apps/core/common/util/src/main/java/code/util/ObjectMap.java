package code.util;
import code.util.ints.Equallable;
import code.util.ints.ListableEntries;

public final class ObjectMap<K extends Equallable<K>, V> extends AbsBasicMap<K,V> {

    public ObjectMap() {
    }

    public ObjectMap(ListableEntries<K, V> _arg0) {
        super(new CollCapacity(_arg0.size()));
        addAllEntries(_arg0);
    }

    
    public ObjectMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    protected boolean matchKeys(K _one, K _two) {
        return _one.eq(_two);
    }
}
