package code.util;

import code.util.ints.Listable;

public final class Shorts extends Numbers<Short> {

    public Shorts() {
    }

    public Shorts(Listable<Short> _list) {
        super(_list);
    }

    public Shorts(Short... _array) {
        super(_array);
    }


    public Shorts(CollCapacity _capacity) {
        super(_capacity);
    }
    @Override
    long getLong(int _index) {
        return get(_index);
    }
}
