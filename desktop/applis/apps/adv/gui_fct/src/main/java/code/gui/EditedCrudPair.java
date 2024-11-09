package code.gui;

public final class EditedCrudPair<K,V> {
    private final K first;
    private final V second;

    public EditedCrudPair(K _k, V _v) {
        first = _k;
        second = _v;
    }

    public K getKey() {
        return first;
    }

    public V getValue() {
        return second;
    }

}
