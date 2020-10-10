package code.util;
import code.util.ints.ListableEntries;

public final class ByteMap<V> extends NumberMap<Byte, V> {

    public ByteMap() {
    }

    public ByteMap(ListableEntries<Byte, V> _arg0) {
        super(new CollCapacity(_arg0.size()));
        addAllEntries(_arg0);
    }


    public ByteMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    long convert(Byte _key) {
        return _key;
    }
}
