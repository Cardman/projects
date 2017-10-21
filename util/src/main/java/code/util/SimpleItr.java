package code.util;

import java.util.Iterator;

public class SimpleItr implements Iterator<Object>{

    private Object[] list;
    private int index;
    private int length;

    public SimpleItr(Object[] _list) {
        list = _list;
        length = _list.length;
    }

    @Override
    public boolean hasNext() {
        return index < length;
    }

    @Override
    public Object next() {
        Object element_ = list[index];
        index++;
        return element_;
    }

    @Override
    public void remove() {
    }

}
