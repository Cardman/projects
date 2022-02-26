package code.sml;

public final class EncodedChar {
    private final String key;
    private final char value;

    public EncodedChar(String _k, char _v) {
        this.key = _k;
        this.value = _v;
    }

    public String getKey() {
        return key;
    }

    public char getValue() {
        return value;
    }
}
