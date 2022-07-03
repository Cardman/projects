package code.mock;

public final class MockNetworkInterface {
    private final MockAddressList addressLists;
    private final String name;
    private final boolean virtual;

    public MockNetworkInterface(MockAddressList _a,String _b, boolean _v) {
        this.addressLists = _a;
        this.name = _b;
        this.virtual = _v;
    }

    public String getName() {
        return name;
    }

    public MockAddressList getAddressLists() {
        return addressLists;
    }

    public boolean isVirtual() {
        return virtual;
    }
}
