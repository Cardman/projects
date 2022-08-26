package code.util;

import code.util.ints.Listable;

public final class Longs extends Numbers<Long> {

    public Longs() {
    }

    public Longs(Listable<Long> _list) {
        super(_list);
    }


    public Longs(CollCapacity _capacity) {
        super(_capacity);
    }

    public static Longs newList(long... _values) {
        Longs sh_ = new Longs(new CollCapacity(_values.length));
        for (long b: _values) {
            sh_.add(b);
        }
        return sh_;
    }

    public long[] toArrLong() {
        int size_ = size();
        long[] sh_ = new long[size_];
        for (int i = 0; i < size_; i++) {
            sh_[i] = getLong(i);
        }
        return sh_;
    }
    @Override
    long getLong(int _index) {
        return get(_index);
    }
}
