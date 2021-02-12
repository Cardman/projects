package code.util.classestest;

import code.util.CustList;
import code.util.ints.SafeRemove;

public final class SafeRemImpl implements SafeRemove {
    private final CustList<String> list =new CustList<String>();

    public CustList<String> getList() {
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
