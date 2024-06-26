package code.util;

import code.util.ints.Listable;

public final class Bytes extends Numbers<Byte> {

    public Bytes() {
    }

    public Bytes(Listable<Byte> _list) {
        super(_list);
    }


    public Bytes(CollCapacity _capacity) {
        super(_capacity);
    }


    public static Bytes newList(byte... _values) {
        Bytes list_ = new Bytes(new CollCapacity(_values.length));
        for (byte b: _values) {
            list_.add(b);
        }
        return list_;
    }

    @Override
    long getLong(int _index) {
        return get(_index);
    }
}
