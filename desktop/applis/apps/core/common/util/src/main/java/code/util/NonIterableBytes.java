package code.util;

import java.util.ArrayList;
import java.util.List;

public final class NonIterableBytes {

    private List<Byte> list;

    public NonIterableBytes() {
        list = new ArrayList<Byte>();
    }

    private NonIterableBytes(int _c) {
        list = new ArrayList<Byte>(_c);
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
        return list.size();
    }

    public List<Byte> getList() {
        return list;
    }

    public void setList(List<Byte> _ls) {
        this.list = _ls;
    }

    public void add(byte _e) {
        list.add(_e);
    }

    public byte get(int _index) {
        return list.get(_index);
    }

    public void set(int _index, byte _element) {
        list.set(_index, _element);
    }

    public void remove(int _index) {
        list.remove(_index);
    }
}
