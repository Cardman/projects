package code.util;


public final class SimpleItr {

    private Object[] list;
    private int index;
    private int length;

    public SimpleItr(Object[] _list) {
        list = _list;
        length = _list.length;
    }

    public boolean hasNext() {
        return index < length;
    }

    public Object next() {
        Object element_ = list[index];
        index++;
        return element_;
    }


}
