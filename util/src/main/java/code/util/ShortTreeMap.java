package code.util;

/**
    @author Cardman
*/
public class ShortTreeMap<V> extends NatTreeMap<Short, V>  {

    @Override
    long convert(Short _key) {
        return _key;
    }
}
