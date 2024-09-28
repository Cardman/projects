package code.util;
import code.util.ints.ListableEntries;

public final class ByteMap<V> extends NumberMap<Byte, V> {

    public ByteMap() {
    }

    public ByteMap(ListableEntries<Byte, V> _arg0) {
        this(_arg0.size());
        addAllEntries(_arg0);
    }

    public ByteMap(int _capacity) {
        this(new CollCapacity(_capacity));
    }
    public ByteMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    long convert(Byte _key) {
        return _key;
    }
}
