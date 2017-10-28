package code.util;

import java.util.Iterator;

public final class CustIter<T> implements Iterator<T> {

    private CustList<T> list;
    private int index;
    private int length;

    public CustIter(CustList<T> _list) {
        list = _list;
        length = _list.size();
    }

    @Override
    public boolean hasNext() {
        return index < length;
    }

    @Override
    public T next() {
        T element_ = list.get(index);
        index++;
        return element_;
    }

    @Override
    public void remove() {
    }

}
