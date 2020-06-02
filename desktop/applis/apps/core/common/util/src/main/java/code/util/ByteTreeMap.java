package code.util;

/**
    @author Cardman
*/
public final class ByteTreeMap<V> extends NatTreeMap<Byte, V>  {

    public ByteTreeMap() {
    }

    public ByteTreeMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    long convert(Byte _key) {
        return _key;
    }
}
