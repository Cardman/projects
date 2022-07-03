package code.mock;

import code.gui.initialize.AbstractAddressList;
import code.util.CustList;

public final class MockAddressList implements AbstractAddressList {
    private final CustList<MockAddress> hosts = new CustList<MockAddress>();
    @Override
    public int size() {
        return hosts.size();
    }

    public CustList<MockAddress> getHosts() {
        return hosts;
    }

    @Override
    public String getHost(int _index) {
        return hosts.get(_index).getHost();
    }

    @Override
    public boolean isLoopbackAddress(int _index) {
        return hosts.get(_index).isLoopbackAddress();
    }

    @Override
    public boolean isIpFour(int _index) {
        return hosts.get(_index).isIpFour();
    }

    @Override
    public boolean isIpSix(int _index) {
        return hosts.get(_index).isIpSix();
    }
}
