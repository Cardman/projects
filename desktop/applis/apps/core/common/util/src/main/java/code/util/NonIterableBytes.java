package code.util;

import java.util.ArrayList;
import java.util.List;

public final class NonIterableBytes {

    private final List<Integer> array;

    public NonIterableBytes() {
        array = new ArrayList<Integer>();
    }

    private NonIterableBytes(int _c) {
        array = new ArrayList<Integer>(_c);
    }

    public static NonIterableBytes newCompositeList(byte... _values) {
        NonIterableBytes nonIt_ = new NonIterableBytes(_values.length);
        for (byte b: _values) {
            nonIt_.add(b);
        }
        return nonIt_;
    }

    public byte[] toCompos() {
        int size_ = size();
        Ints arrIt_ = new Ints(new CollCapacity(size_));
        for (int i = 0; i < size_; i++) {
            arrIt_.add(get(i));
        }
        return arrIt_.toArrByte();
    }
    public int size() {
        return array.size();
    }

    public List<Integer> getArray() {
        return array;
    }

    public void add(int _e) {
        array.add(_e);
    }

    public int get(int _index) {
        return array.get(_index);
    }

    public void set(int _index, int _element) {
        array.set(_index, _element);
    }

    public void remove(int _index) {
        array.remove(_index);
    }
}
