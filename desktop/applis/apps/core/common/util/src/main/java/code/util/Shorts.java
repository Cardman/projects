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

    public static Shorts newList(short... _values) {
        Shorts sh_ = new Shorts(new CollCapacity(_values.length));
        for (short b: _values) {
            sh_.add(b);
        }
        return sh_;
    }

    public short[] toArrShort() {
        int size_ = size();
        short[] sh_ = new short[size_];
        for (int i = 0; i < size_; i++) {
            sh_[i] = (short) getLong(i);
        }
        return sh_;
    }

    @Override
    long getLong(int _index) {
        return get(_index);
    }
}
