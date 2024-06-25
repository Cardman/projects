package code.util;

import code.util.ints.Listable;

public final class Shorts extends Numbers<Short> {

    public Shorts() {
    }

    public Shorts(Listable<Short> _list) {
        super(_list);
    }


    public Shorts(CollCapacity _capacity) {
        super(_capacity);
    }

    public static Shorts newList(short... _values) {
        Shorts sh_ = new Shorts(new CollCapacity(_values.length));
        for (short b: _values) {
            sh_.add(b);
        }
        return sh_;
    }

    @Override
    long getLong(int _index) {
        return get(_index);
    }
}
