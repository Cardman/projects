package code.util;

import code.util.ints.Listable;

public final class Bytes extends Numbers<Byte> {

    public Bytes() {
    }

    public Bytes(Listable<Byte> _list) {
        super(_list);
    }

    public Bytes(Byte... _array) {
        super(_array);
    }


    public Bytes(CollCapacity _capacity) {
        super(_capacity);
    }
    @Override
    long getLong(int _index) {
        return get(_index);
    }
}
