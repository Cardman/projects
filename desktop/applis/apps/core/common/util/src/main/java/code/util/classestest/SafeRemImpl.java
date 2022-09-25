package code.util.classestest;

import code.util.CustList;
import code.util.ints.SafeRemove;

public final class SafeRemImpl<T> implements SafeRemove {
    private final CustList<T> list =new CustList<T>();

    public CustList<T> getList() {
        return list;
    }

    @Override
    public boolean ok(int _index) {
        return list.isValidIndex(_index);
    }

    @Override
    public void removeItemAt(int _index) {
        list.remove(_index);
    }
}
