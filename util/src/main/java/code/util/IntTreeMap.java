package code.util;

/**
    @author Cardman
*/
public final class IntTreeMap<V> extends NatTreeMap<Integer, V>  {

    public IntTreeMap() {
    }

    public IntTreeMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    long convert(Integer _key) {
        return _key;
    }
}
