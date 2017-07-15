package code.util;


public final class EntryCust<K, V> {

    private static final String SEPARATOR = " ";

    private K key;

    private V value;

    EntryCust(K _k, V _v) {
        key = _k;
        value = _v;
    }

    //@Override
    public K getKey() {
        return key;
    }

    //@Override
    public V getValue() {
        return value;
    }

    //@Override
//    public V setValue(V _v) {
//        V old_ = value;
//        setValueOnly(_v);
//        return old_;
//    }

    public void setValue(V _v) {
        value = _v;
    }

    @Override
    public String toString() {
        return key+SEPARATOR+value;
    }
}
