package code.adv;

import code.util.CustList;

public final class SafeRemAdvImpl implements SafeRemoveAdv {
    private final CustList<String> list =new CustList<String>();

    public CustList<String> getList() {
        return list;
    }
    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void removeItemAt(int _index) {
        list.remove(_index);
    }
}
