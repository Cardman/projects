package code.stream;

public final class BytesInfo {
    private final byte[] bytes;
    private final boolean nul;

    public BytesInfo(byte[] _b, boolean _n) {
        this.bytes = _b;
        this.nul = _n;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public boolean isNul() {
        return nul;
    }
}
