package code.util;
import code.util.ints.ListableEntries;

public final class IntMap<V> extends NumberMap<Integer, V> {

    public IntMap() {
    }

    public IntMap(ListableEntries<Integer, V> _arg0) {
        this(_arg0.size());
        addAllEntries(_arg0);
    }
    public IntMap(int _capacity) {
        this(new CollCapacity(_capacity));
    }
    public IntMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    long convert(Integer _key) {
        return _key;
    }
}
