package code.mock;

public final class MockAddress {
    private final String host;
    private final boolean loopbackAddress;
    private final boolean ipFour;
    private final boolean ipSix;

    public MockAddress(String _h, boolean _l, boolean _four, boolean _six) {
        this.host = _h;
        this.loopbackAddress = _l;
        this.ipFour = _four;
        this.ipSix = _six;
    }

    public String getHost() {
        return host;
    }

    public boolean isIpFour() {
        return ipFour;
    }

    public boolean isIpSix() {
        return ipSix;
    }

    public boolean isLoopbackAddress() {
        return loopbackAddress;
    }
}
