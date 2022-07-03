package code.mock;

import code.gui.initialize.AbstractAddressList;
import code.gui.initialize.AbstractNetworkInterfaceList;
import code.util.CustList;

public final class MockNetworkInterfaceList implements AbstractNetworkInterfaceList {
    private final CustList<MockNetworkInterface> list = new CustList<MockNetworkInterface>();

    public CustList<MockNetworkInterface> getList() {
        return list;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public AbstractAddressList list(int _index) {
        return list.get(_index).getAddressLists();
    }

    @Override
    public int size(int _index) {
        return list.get(_index).getAddressLists().size();
    }

    @Override
    public String getName(int _index) {
        return list.get(_index).getName();
    }

    @Override
    public boolean isVirtual(int _index) {
        return list.get(_index).isVirtual();
    }
}
