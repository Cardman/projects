package code.util;

import java.util.ArrayList;
import java.util.List;

public final class NonIterableBytes {

    private List<Byte> array;

    public NonIterableBytes() {
        array = new ArrayList<Byte>();
    }

    private NonIterableBytes(int _c) {
        array = new ArrayList<Byte>(_c);
    }

    public static NonIterableBytes newCompositeList(byte... _values) {
        NonIterableBytes nonIt_ = new NonIterableBytes(_values.length);
        for (byte b: _values) {
            nonIt_.add(b);
        }
        return nonIt_;
    }

    public byte[] toComposArrByte() {
        int size_ = size();
        byte[] arrIt_ = new byte[size_];
        for (int i = 0; i < size_; i++) {
            arrIt_[i] = get(i);
        }
        return arrIt_;
    }
    public int size() {
        return array.size();
    }

    public List<Byte> getArray() {
        return array;
    }

    public void setArray(List<Byte> _ls) {
        this.array = _ls;
    }

    public void add(byte _e) {
        array.add(_e);
    }

    public byte get(int _index) {
        return array.get(_index);
    }

    public void set(int _index, byte _element) {
        array.set(_index, _element);
    }

    public void remove(int _index) {
        array.remove(_index);
    }
}
