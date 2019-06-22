package code.util;

import code.util.ints.Listable;

public final class Longs extends Numbers<Long> {

    public Longs() {
    }

    public Longs(Listable<Long> _list) {
        super(_list);
    }

    public Longs(Long... _array) {
        super(_array);
    }


    public Longs(CollCapacity _capacity) {
        super(_capacity);
    }
    @Override
    long getLong(int _index) {
        return get(_index);
    }
}
